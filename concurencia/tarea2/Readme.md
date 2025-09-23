# Code, Learn & Practice  
**Procesos y Servicios (modo alumno, sin root) — Trabajo en `$HOME/dam` con *user units* de systemd**

> **Importante:** Esta guía está adaptada para **usuarios sin privilegios de root**.  
> Todo se hace **en tu carpeta** `~/dam` usando **systemd --user** (un administrador por usuario), sin tocar `/etc` ni `/usr/local`.  
> Pega **salidas reales** y responde a las preguntas cortas.

---

## Preparación

Crea tu área de trabajo y variables útiles:

```bash
mkdir -p ~/dam/{bin,logs,units}
export DAM=~/dam
echo 'export DAM=~/dam' >> ~/.bashrc
```

Comprobar versión de systemd y que el *user manager* está activo:

```bash
systemctl --user --version | head -n1
systemctl --user status --no-pager | head -n5
```
**Pega salida aquí:**

```text
    systemd 255 (255.4-1ubuntu8.6)

● a108pc16
    State: running
    Units: 261 loaded (incl. loaded aliases)
     Jobs: 0 queued
   Failed: 0 units

```

**Reflexiona la salida:**

```text
Por lo que entiendo el usuario que acabamos de crear tiene 261 procesos cargados
```

---

## Bloque 1 — Conceptos (breve + fuentes)

1) ¿Qué es **systemd** y en qué se diferencia de SysV init?  

**Respuesta:**  

Systemd es un sistema de inicialización moderno que reemplaza al clásico SysV init, está diseñado para superar las limitaciones de los sistemas basados en scripts mediante un enfoque paralelo y modular.

Init System V es uno de los primeros sistemas de inicialización utilizados en Linux. Se basa en un diseño secuencial, donde los servicios se inician uno por uno en un orden predefinido utilizando scripts.

_Fuentes:_


[www.maxizamorano.com](https://www.maxizamorano.com/entrada/19/proceso-de-arranque-en-linux-systemd-vs-sysv-init)

2) **Servicio** vs **proceso** (ejemplos).  

**Respuesta:**  

_Fuentes:_

3) ¿Qué son los **cgroups** y para qué sirven?  

**Respuesta:**  

Los grupos de control, cgroups, de Linux son una excelente herramienta para controlar la asignación de recursos a los procesos.

_Fuentes:_

[elpuig.xeill.net](https://elpuig.xeill.net/Members/vcarceler/articulos/introduccion-a-los-grupos-de-control-cgroups-de-linux)

4) ¿Qué es un **unit file** y tipos (`service`, `timer`, `socket`, `target`)?  

**Respuesta:**  

Units are the objects that systemd knows how to manage. These are basically a standardized representation of system resources that can be managed by the suite of daemons and manipulated by the provided utilities.

Service: A service unit describes how to manage a service or application on the server. 

Timer: Defines a timer that will be managed by systemd, similar to a cron job for delayed or scheduled activation.

Socket: Describes a network or IPC socket, or a FIFO buffer that systemd uses for socket-based activation.

Target: Used to provide synchronization points for other units when booting up or changing states.

_Fuentes:_

[www.digitalocean.com](https://www.digitalocean.com/community/tutorials/understanding-systemd-units-and-unit-files)

5) ¿Qué hace `journalctl` y cómo ver logs **de usuario**?  

**Respuesta:**  

Cuando journalctl se utilice solo, cada entrada de diario (log) que se encuentre en el sistema se mostrará dentro de un localizador para que pueda navegar en él.

_Fuentes:_

[www.digitalocean.com](https://www.digitalocean.com/community/tutorials/how-to-use-journalctl-to-view-and-manipulate-systemd-logs-es)

---

## Bloque 2 — Práctica guiada (todo en tu `$DAM`)

> Si un comando pide permisos que no tienes, usa la **versión `--user`** o consulta el **ayuda** con `--help` para alternativas.

### 2.1 — PIDs básicos

**11.** PID de tu shell y su PPID.

```bash
echo "PID=$$  PPID=$PPID"
```
**Salida:**

```text
PID=20363  PPID=20354
```

**Pregunta:** ¿Qué proceso es el padre (PPID) de tu shell ahora?  

**Respuesta:**
El proceso 20354
---

**12.** PID del `systemd` (manager de usuario) y explicación.

```bash
pidof systemd || pgrep -u "$USER" -x systemd
```

**Salida:**

```text
3296
```
**Pregunta:** ¿Qué hace el *user manager* de systemd para tu sesión?  

**Respuesta:**
ejecutar servicios y tareas sin necesitar privilegios de administrador de raíz
---

### 2.2 — Servicios **de usuario** con systemd

Vamos a crear un servicio sencillo y un timer **en tu carpeta** `~/.config/systemd/user` o en `$DAM/units` (usaremos la primera para que `systemctl --user` lo encuentre).

**13.** Prepara directorios y script de práctica.

```bash
mkdir -p ~/.config/systemd/user "$DAM"/{bin,logs}
cat << 'EOF' > "$DAM/bin/fecha_log.sh"
#!/usr/bin/env bash
mkdir -p "$HOME/dam/logs"
echo "$(date --iso-8601=seconds) :: hello from user timer" >> "$HOME/dam/logs/fecha.log"
EOF
chmod +x "$DAM/bin/fecha_log.sh"
```

**14.** Crea el servicio **de usuario** `fecha-log.service` (**Type=simple**, ejecuta tu script).

```bash
cat << 'EOF' > ~/.config/systemd/user/fecha-log.service
[Unit]
Description=Escribe fecha en $HOME/dam/logs/fecha.log

[Service]
Type=simple
ExecStart=%h/dam/bin/fecha_log.sh
EOF

systemctl --user daemon-reload
systemctl --user start fecha-log.service
systemctl --user status fecha-log.service --no-pager -l | sed -n '1,10p'
```
**Salida (pega un extracto):**

```text
○ fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log
     Loaded: loaded (/home/dam/.config/systemd/user/fecha-log.service; static)
     Active: inactive (dead)

sep 23 18:41:43 a108pc16 systemd[3296]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
```
**Pregunta:** ¿Se creó/actualizó `~/dam/logs/fecha.log`? Muestra las últimas líneas:

```bash
tail -n 5 "$DAM/logs/fecha.log"
```

**Salida:**

```text
2025-09-23T18:41:43+01:00 :: hello from user timer
```

**Reflexiona la salida:**

```text
creamos un servicio que cada vez que se inicia escribe eso en el archivo
```

---

**15.** Diferencia **enable** vs **start** (modo usuario). Habilita el **timer**.

```bash
cat << 'EOF' > ~/.config/systemd/user/fecha-log.timer
[Unit]
Description=Timer (usuario): ejecuta fecha-log.service cada minuto

[Timer]
OnCalendar=*:0/1
Unit=fecha-log.service
Persistent=true

[Install]
WantedBy=timers.target
EOF

systemctl --user daemon-reload
systemctl --user enable --now fecha-log.timer
systemctl --user list-timers --all | grep fecha-log || true
```

**Salida (recorta):**

```text
Tue 2025-09-23 18:50:00 WEST   7s -    - fecha-log.timer   fecha-log.service

```
**Pregunta:** ¿Qué diferencia hay entre `enable` y `start` cuando usas `systemctl --user`?  

**Respuesta:**
Parece que se guarda en el log que se ha iniciado pero no el mesnsaje propio que escribe cuando se inicia
---

**16.** Logs recientes **del servicio de usuario** con `journalctl --user`.

```bash
journalctl --user -u fecha-log.service -n 10 --no-pager
```

**Salida:**

```text
sep 23 18:41:43 a108pc16 systemd[3296]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
sep 23 18:50:05 a108pc16 systemd[3296]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
sep 23 18:51:05 a108pc16 systemd[3296]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
sep 23 18:52:05 a108pc16 systemd[3296]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
sep 23 18:53:05 a108pc16 systemd[3296]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
sep 23 18:54:05 a108pc16 systemd[3296]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
```
**Pregunta:** ¿Ves ejecuciones activadas por el timer? ¿Cuándo fue la última?  

**Respuesta:**
se estan ejecutando cada minuto
---

### 2.3 — Observación de procesos sin root

**17.** Puertos en escucha (lo que puedas ver como usuario).

```bash
lsof -i -P -n | grep LISTEN || ss -lntp
```
**Salida:**

```text
State          Recv-Q         Send-Q                 Local Address:Port                    Peer Address:Port         Process         
LISTEN         0              4096                         0.0.0.0:111                          0.0.0.0:*
LISTEN         0              4096                         0.0.0.0:35457                        0.0.0.0:*
LISTEN         0              64                           0.0.0.0:2049                         0.0.0.0:*
LISTEN         0              32                     192.168.122.1:53                           0.0.0.0:*
LISTEN         0              4096                      127.0.0.54:53                           0.0.0.0:*
LISTEN         0              4096                       127.0.0.1:631                          0.0.0.0:*
LISTEN         0              4096                         0.0.0.0:37937                        0.0.0.0:*                            
LISTEN         0              4096                   127.0.0.53%lo:53                           0.0.0.0:*
LISTEN         0              4096                         0.0.0.0:57341                        0.0.0.0:*
LISTEN         0              4096                         0.0.0.0:8000                         0.0.0.0:*
LISTEN         0              64                           0.0.0.0:44969                        0.0.0.0:*
LISTEN         0              4096                         0.0.0.0:60553                        0.0.0.0:*                  
```
**Pregunta:** ¿Qué procesos *tuyos* están escuchando? (si no hay, explica por qué)  

**Respuesta:**
ninguno, no se esta ejecutando ningun proceso que escuchen la red
---

**18.** Ejecuta un proceso bajo **cgroup de usuario** con límite de memoria.

```bash
systemd-run --user --scope -p MemoryMax=50M sleep 200
ps -eo pid,ppid,cmd,stat | grep "[s]leep 200"
```

**Salida:**

```text
  64247   20363 /usr/bin/sleep 200
```
**Pregunta:** ¿Qué ventaja tiene lanzar con `systemd-run --user` respecto a ejecutarlo “a pelo”?  

**Respuesta:**

```text
Te permite ejecutarlo a nivel de usuario sin tener que usar sudo para muchas cosas
```

---

**19.** Observa CPU en tiempo real con `top` (si tienes `htop`, también vale).

```bash
top -b -n 1 | head -n 15
```
**Salida (resumen):**

```text

```
**Pregunta:** ¿Cuál es tu proceso con mayor `%CPU` en ese momento?  

**Respuesta:**
    PID USUARIO   PR  NI    VIRT    RES    SHR S  %CPU  %MEM     HORA+ ORDEN
  71164 dam       20   0   17224   5760   3584 R   9,1   0,0   0:00.02 top
---

**20.** Traza syscalls de **tu propio proceso** (p. ej., el `sleep` anterior).
> Nota: Sin root, no podrás adjuntarte a procesos de otros usuarios ni a algunos del sistema.

```bash
pid=$(pgrep -u "$USER" -x sleep | head -n1)
strace -p "$pid" -e trace=nanosleep -tt -c -f 2>&1 | sed -n '1,10p'
```

**Salida (fragmento):**

```text
strace: -t/--absolute-timestamps has no effect with -c/--summary-only
strace: attach: ptrace(PTRACE_SEIZE, 64247): Operación no permitida
```
**Pregunta:** Explica brevemente la syscall que observaste.  

**Respuesta:**

---

### 2.4 — Estados y jerarquía (sin root)

**21.** Árbol de procesos con PIDs.

```bash
pstree -p | head -n 50
```

**Salida (recorta):**

```text

```
**Pregunta:** ¿Bajo qué proceso aparece tu `systemd --user`?  

**Respuesta:**

---

**22.** Estados y relación PID/PPID.

```bash
ps -eo pid,ppid,stat,cmd | head -n 20
```
**Salida:**

```text

```
**Pregunta:** Explica 3 flags de `STAT` que veas (ej.: `R`, `S`, `T`, `Z`, `+`).  

**Respuesta:**

---

**23.** Suspende y reanuda **uno de tus procesos** (no crítico).

```bash
# Crea un proceso propio en segundo plano
sleep 120 &
pid=$!
# Suspende
kill -STOP "$pid"
# Estado
ps -o pid,stat,cmd -p "$pid"
# Reanuda
kill -CONT "$pid"
# Estado
ps -o pid,stat,cmd -p "$pid"
```
**Pega los dos estados (antes/después):**

```text

```
**Pregunta:** ¿Qué flag indicó la suspensión?  

**Respuesta:**

---

**24. (Opcional)** Genera un **zombie** controlado (no requiere root).

```bash
cat << 'EOF' > "$DAM/bin/zombie.c"
#include <stdlib.h>
#include <unistd.h>
int main() {
  if (fork() == 0) { exit(0); } // hijo termina
  sleep(60); // padre no hace wait(), hijo queda zombie hasta que padre termine
  return 0;
}
EOF
gcc "$DAM/bin/zombie.c" -o "$DAM/bin/zombie" && "$DAM/bin/zombie" &
ps -el | grep ' Z '
```
**Salida (recorta):**

```text

```
**Pregunta:** ¿Por qué el estado `Z` y qué lo limpia finalmente?  

**Respuesta:**

---

### 2.5 — Limpieza (solo tu usuario)

Detén y deshabilita tu **timer/servicio de usuario** y borra artefactos si quieres.

```bash
systemctl --user disable --now fecha-log.timer
systemctl --user stop fecha-log.service
rm -f ~/.config/systemd/user/fecha-log.{service,timer}
systemctl --user daemon-reload
rm -rf "$DAM/bin" "$DAM/logs" "$DAM/units"
```

---

## ¿Qué estás prácticando?
- [ ] Pegaste **salidas reales**.  
- [ ] Explicaste **qué significan**.  
- [ ] Usaste **systemd --user** y **journalctl --user**.  
- [ ] Probaste `systemd-run --user` con límites de memoria.  
- [ ] Practicaste señales (`STOP`/`CONT`), `pstree`, `ps` y `strace` **sobre tus procesos**.

---
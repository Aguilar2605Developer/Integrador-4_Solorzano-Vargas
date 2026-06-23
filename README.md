#  SecurityDev

App de seguridad para el campus: ver y reportar alertas (robos, asaltos, accidentes) en un mapa en tiempo real, con botón de SOS y compartir ubicación.

##  Stack

**📱 Frontend** (`SecurityApp`) — Ionic + React + Capacitor → se compila a APK Android y también corre en navegador.

** Backend** (`securitydev`) — Kotlin + Spring Boot + JWT, conectado a PostgreSQL en Neon.

** Infra** — Backend desplegado en AWS Elastic Beanstalk. Base de datos en Neon.

##  Cómo funciona el flujo

1. 🗺️ La app abre directo en el **Mapa**, sin pedir login.
2. 👀 Cualquiera puede ver el mapa y las alertas activas.
3. 🔒 Para **crear una alerta**, **marcar zona de riesgo** o **compartir ubicación**, te pide loguearte.
4. 🆘 El botón **SOS** siempre está libre, sin login (es para emergencias).
5. 👤 La pestaña **Perfil** sí exige login.

## ✨ Ventajas de este tipo de app

- 🚨 **Respuesta más rápida ante incidentes** — reportar y ver alertas en tiempo real ayuda a que la comunidad reaccione antes (evitar zonas, alertar a otros, pedir ayuda).
- 🌍 **Visibilidad colectiva del riesgo** — en vez de que cada persona sepa de un incidente por rumor, todos ven la misma información en el mapa, con ubicación exacta.
- 📡 **Acceso sin barreras en emergencias** — el botón SOS y la vista del mapa no piden login, así que nadie pierde tiempo crítico creando una cuenta en medio de una emergencia.
- 📲 **Multiplataforma con un solo código** — gracias a Ionic, la misma base sirve para Android y navegador, ahorrando tiempo de desarrollo duplicado.
- 🧩 **Bajo costo de entrada para un MVP** — Neon (DB) y AWS Elastic Beanstalk en capa gratuita permiten validar la idea sin inversión inicial fuerte en infraestructura.
- 📍 **Compartir ubicación sin friction** — un contacto puede ver tu ubicación en tiempo real con solo un link, sin instalar nada ni crear cuenta.
- 🔐 **Seguridad básica ya integrada** — JWT, contraseñas hasheadas y rutas protegidas dan una base sólida para seguir creciendo sin rehacer la autenticación desde cero.

## 💻 Correr el proyecto en local

**Backend:**
```bash
cd securitydev
# setear DATABASE_URL, DB_USER, DB_PASSWORD (pedir credenciales de Neon)
./gradlew clean bootJar
java -jar build/libs/securitydev.jar
```

**Frontend:**
```bash
cd SecurityApp
npm install
ionic serve          # 🌐 para verlo en el navegador
```

## 🚀 Backend en producción

Ya está corriendo en AWS:
```
http://56.125.68.157
```
Es la URL que usa el frontend en `src/services/api.ts` (`BASE_URL`). Si la IP cambia (por ejemplo si se reinicia la instancia de AWS), hay que actualizarla ahí. ⚠️

## ⏳ Cosas pendientes / a tener en cuenta

- 🔓 El backend corre por **HTTP, no HTTPS** — está bien para pruebas, no para producción real.
- 📍 La IP del backend **no es fija**, puede cambiar si se reinicia la instancia.
- ✉️ Falta validar que no se puedan registrar emails duplicados.
- 🐢 El mapa tarda un poco en actualizarse después de crear una alerta nueva.

## 📂 Estructura rápida

```
securitydev/          → ⚙️ backend Kotlin
  controller/          → endpoints (login, register, hotspots...)
  service/              → lógica de negocio
  security/             → JWT y configuración de seguridad

SecurityApp/           → 📱 frontend Ionic
  src/pages/            → Login, Register, Map, Profile, Report
  src/services/api.ts   → URL del backend + token JWT
```

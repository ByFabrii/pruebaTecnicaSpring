# 🔒 Guía de Seguridad para GitHub

## ✅ Archivos que SÍ se suben a GitHub

```
✅ .env.example          (Plantilla sin credenciales reales)
✅ .gitignore            (Protege archivos sensibles)
✅ docker-compose.yml    (Usa variables de entorno)
✅ README.md             (Documentación)
✅ DEPLOYMENT.md         (Guía de deployment)
✅ PruebaTecSupermercado/ (Código fuente completo)
   ✅ src/
   ✅ pom.xml
   ✅ Dockerfile
   ✅ .mvn/
   ✅ mvnw
```

## ❌ Archivos que NO se suben a GitHub (protegidos por .gitignore)

```
❌ .env                  (Contiene credenciales reales)
❌ target/               (Archivos compilados)
❌ *.jar                 (Archivos ejecutables)
❌ *.log                 (Logs)
❌ .idea/                (Configuración de IDE)
❌ .vscode/              (Configuración de IDE)
```

## 🚀 Pasos para subir a GitHub

### 1. Verificar que .env NO esté en staging

```bash
git status
# El archivo .env NO debe aparecer en la lista
```

### 2. Verificar que .env esté siendo ignorado

```bash
git check-ignore -v .env
# Debe mostrar: .gitignore:2:.env       .env
```

### 3. Hacer commit

```bash
git add .
git commit -m "Initial commit: Spring Boot API with Docker"
```

### 4. Crear repositorio en GitHub

1. Ve a https://github.com/new
2. Crea un nuevo repositorio (ejemplo: `supermercado-api`)
3. NO inicialices con README (ya lo tienes)

### 5. Conectar y subir

```bash
git remote add origin https://github.com/TU_USUARIO/TU_REPOSITORIO.git
git branch -M main
git push -u origin main
```

## 🔐 Seguridad Garantizada

### ¿Cómo funciona?

1. **`.env`**: Contiene tus credenciales reales (solo en tu máquina)
2. **`.env.example`**: Plantilla que se sube a GitHub (sin credenciales)
3. **`.gitignore`**: Bloquea `.env` para que nunca se suba
4. **`docker-compose.yml`**: Usa variables `${VARIABLE}` que lee de `.env`

### ¿Qué pasa cuando alguien clona el repo?

1. Clona el repositorio
2. Ve el archivo `.env.example`
3. Copia `.env.example` a `.env`
4. Edita `.env` con sus propias credenciales
5. Ejecuta `docker-compose up -d`
6. ¡Funciona con sus credenciales!

## ✅ Verificación Final

Antes de hacer push, verifica:

```bash
# 1. Ver qué archivos se van a subir
git status

# 2. Verificar que .env esté ignorado
git check-ignore -v .env

# 3. Ver el contenido de docker-compose.yml
cat docker-compose.yml
# Debe tener ${DB_USER}, NO credenciales hardcodeadas

# 4. Ver el contenido de .env.example
cat .env.example
# Debe tener placeholders, NO credenciales reales
```

## 🆘 Si accidentalmente subiste credenciales

Si ya subiste el archivo `.env` con credenciales:

### Opción 1: Eliminar del historial (si es reciente)

```bash
# Eliminar el último commit
git reset --soft HEAD~1

# Eliminar .env del staging
git reset HEAD .env

# Hacer commit nuevamente
git add .
git commit -m "Initial commit: Spring Boot API with Docker"
git push -f origin main
```

### Opción 2: Usar git-filter-repo (si ya tiene varios commits)

```bash
# Instalar git-filter-repo
pip install git-filter-repo

# Eliminar .env del historial
git filter-repo --path .env --invert-paths

# Forzar push
git push -f origin main
```

### ⚠️ IMPORTANTE: Cambiar credenciales

Si subiste credenciales a GitHub, **DEBES cambiarlas inmediatamente**:

1. Cambia la contraseña de la base de datos
2. Actualiza tu archivo `.env` local
3. Actualiza las credenciales en el servidor de producción

## 📝 Checklist antes de Push

- [ ] `.env` NO aparece en `git status`
- [ ] `git check-ignore -v .env` confirma que está ignorado
- [ ] `.env.example` tiene solo placeholders
- [ ] `docker-compose.yml` usa variables `${VARIABLE}`
- [ ] `README.md` tiene instrucciones claras
- [ ] `.gitignore` incluye `.env`
- [ ] Has probado que la app funciona con las variables de entorno

## 🎯 Resultado Final

Tu repositorio en GitHub será **100% seguro**:
- ✅ Sin credenciales expuestas
- ✅ Fácil de clonar y configurar
- ✅ Documentación completa
- ✅ Listo para producción

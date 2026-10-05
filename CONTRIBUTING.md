# Cómo colaborar
Para que el repositorio no se vuelva un caos, sigue estas pautas sencillas.

## Ramas

- `main` es la rama principal y siempre tiene que funcionar. **No se trabaja directamente en ella.**
- Para cada cambio crea tu propia rama a partir de `main`, con un nombre que diga lo que haces:

  - `feature/...` para algo nuevo → `feature/descuento-estudiante`
  - `fix/...` para arreglar un error → `fix/calculo-iva`
  - `docs/...` para documentación → `docs/mejorar-readme`

```bash
git switch main
git pull
git switch -c feature/mi-cambio
```

## Commits

Escribe mensajes cortos que expliquen qué has hecho, empezando por el tipo de cambio:

```
feat: añadir descuento para estudiantes
fix: corregir el cálculo del IVA
docs: añadir comentarios Javadoc
```

Tipos habituales: `feat` (algo nuevo), `fix` (arreglo), `docs` (documentación), `test` (tests).

Intenta hacer un commit por cada cosa que cambies, y no subas la carpeta `target/`.

## Pull Requests

1. Comprueba que todo compila y que los tests pasan (`mvn test`).
2. Sube tu rama: `git push -u origin feature/mi-cambio`.
3. Abre un Pull Request hacia `main` y cuenta brevemente qué has cambiado y por qué.
4. Pide que alguien lo revise y contesta a sus comentarios.
5. Cuando esté aprobado, haz el merge y borra la rama.

## Código

- Comenta con Javadoc las clases y los métodos.
- Si añades algo nuevo, añade también su test.

Payaso
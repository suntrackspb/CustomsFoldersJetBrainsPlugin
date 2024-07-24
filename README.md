# Plugin for JetBrains (WebStorm) English

The plugin creates pre-made folder and file structures for convenience and speeding up development, reducing the amount of routine actions.

### When using "Create TSx Package" and entering "RegisterPage", you will get the following structure:
```
└───RegisterPage
    ├───index.ts
    └───RegisterPage.tsx
```
index.ts
```js
export { RegisterPage } from './RegisterPage';
```
RegisterPage.tsx
```js
export function RegisterPage() {
  return (
    <>
      <p>RegisterPage</p>
    </>
  );
}
```
Similarly, using "Create JSx Package" will give you the same structure but with .js and .jsx files.

### Using "Create Custom Folder And Index.Ts" and selecting "components" will create:
```
└───components
    └───index.ts
```
There are presets for folders like components, hooks, utils.

Please don't be to hate on the code, this is my first experience writing in Java/Kotlin.



# Plugin for JetBrains (WebStorm) Russian

Плагин создаёт заготовленные структуры папок и файлов для удобства и ускорения разработки.
Уменьшая количество рутинных действий.

### При использование "Create TSx Package" и вводе "RegisterPage" вы получите такую структуру
```
└───RegisterPage
    ├───index.ts
    └───RegisterPage.tsx
```
index.ts
```js
export { RegisterPage } from './RegisterPage';
```
RegisterPage.tsx
```js
export function RegisterPage() {
  return (
    <>
      <p>RegisterPage</p>
    </>
  );
}
```

соответственно "Create JSx Package" даст такую же структуру только с .js и .jsx

### Использование "Create Custom Folder And Index.Ts" и выбор "components" создаст
```
└───components
    └───index.ts
```
Есть пресеты под папки components, hooks, utils


Прошу сильно не ругать за код, это первый опыт написания на Java/Kotlin

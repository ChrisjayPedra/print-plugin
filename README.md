# print-plugin

print

## Install

```bash
npm install print-plugin
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`testPluginMethod(...)`](#testpluginmethod)
* [`enableServices()`](#enableservices)
* [`PrintAddCallBack()`](#printaddcallback)
* [`PrintRemoveCallBack()`](#printremovecallback)
* [`PrintOpenPort(...)`](#printopenport)
* [`EnumBle()`](#enumble)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### testPluginMethod(...)

```typescript
testPluginMethod(options: { msg: string; }) => Promise<{ value: string; }>
```

| Param         | Type                          |
| ------------- | ----------------------------- |
| **`options`** | <code>{ msg: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### enableServices()

```typescript
enableServices() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### PrintAddCallBack()

```typescript
PrintAddCallBack() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### PrintRemoveCallBack()

```typescript
PrintRemoveCallBack() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### PrintOpenPort(...)

```typescript
PrintOpenPort(options: { content: string; }) => Promise<void>
```

| Param         | Type                              |
| ------------- | --------------------------------- |
| **`options`** | <code>{ content: string; }</code> |

--------------------


### EnumBle()

```typescript
EnumBle() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------

</docgen-api>

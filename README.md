# print-plugin

print

## Install

```bash
npm i print-plugin-ionic-cjp
npx cap sync
```

## API

<docgen-index>

* [`testPluginMethod(...)`](#testpluginmethod)
* [`DataFromPlugin()`](#datafromplugin)
* [`AddCallback()`](#addcallback)
* [`enableServices()`](#enableservices)
* [`RemoveCallback()`](#removecallback)
* [`EnumBle()`](#enumble)
* [`EnumBt()`](#enumbt)
* [`EnumNet()`](#enumnet)
* [`EnumCom()`](#enumcom)
* [`EnumUsb()`](#enumusb)
* [`EnumWiFiP2P()`](#enumwifip2p)
* [`Test_Pos_SampleTicket_80MM_1()`](#test_pos_sampleticket_80mm_1)
* [`Test_Pos_SampleTicket_58MM_2()`](#test_pos_sampleticket_58mm_2)
* [`Test_Pos_SampleTicket_58MM_1()`](#test_pos_sampleticket_58mm_1)
* [`Test_Port_Read()`](#test_port_read)
* [`Test_Pos_PrintSelfTestPage()`](#test_pos_printselftestpage)
* [`OpenPort(...)`](#openport)
* [`ClosePort()`](#closeport)
* [`Test_Pos_PrintBarcode()`](#test_pos_printbarcode)
* [`Test_Pos_PrintQRCode()`](#test_pos_printqrcode)
* [`DisplayScreen(...)`](#displayscreen)
* [`Test_Custom_Ticket_Receipt(...)`](#test_custom_ticket_receipt)
* [`standby()`](#standby)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### testPluginMethod(...)

```typescript
testPluginMethod(options: { msg: string; }) => Promise<{ value: string; }>
```

| Param         | Type                          |
| ------------- | ----------------------------- |
| **`options`** | <code>{ msg: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### DataFromPlugin()

```typescript
DataFromPlugin() => Promise<{ payload: Payload; }>
```

**Returns:** <code>Promise&lt;{ payload: <a href="#payload">Payload</a>; }&gt;</code>

--------------------


### AddCallback()

```typescript
AddCallback() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### enableServices()

```typescript
enableServices() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### RemoveCallback()

```typescript
RemoveCallback() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### EnumBle()

```typescript
EnumBle() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### EnumBt()

```typescript
EnumBt() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### EnumNet()

```typescript
EnumNet() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### EnumCom()

```typescript
EnumCom() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### EnumUsb()

```typescript
EnumUsb() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### EnumWiFiP2P()

```typescript
EnumWiFiP2P() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### Test_Pos_SampleTicket_80MM_1()

```typescript
Test_Pos_SampleTicket_80MM_1() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### Test_Pos_SampleTicket_58MM_2()

```typescript
Test_Pos_SampleTicket_58MM_2() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### Test_Pos_SampleTicket_58MM_1()

```typescript
Test_Pos_SampleTicket_58MM_1() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### Test_Port_Read()

```typescript
Test_Port_Read() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### Test_Pos_PrintSelfTestPage()

```typescript
Test_Pos_PrintSelfTestPage() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### OpenPort(...)

```typescript
OpenPort(options: { content: any; }) => Promise<void>
```

| Param         | Type                           |
| ------------- | ------------------------------ |
| **`options`** | <code>{ content: any; }</code> |

--------------------


### ClosePort()

```typescript
ClosePort() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### Test_Pos_PrintBarcode()

```typescript
Test_Pos_PrintBarcode() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### Test_Pos_PrintQRCode()

```typescript
Test_Pos_PrintQRCode() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### DisplayScreen(...)

```typescript
DisplayScreen(options: { content: any; }) => Promise<{ value: string; }>
```

| Param         | Type                           |
| ------------- | ------------------------------ |
| **`options`** | <code>{ content: any; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### Test_Custom_Ticket_Receipt(...)

```typescript
Test_Custom_Ticket_Receipt(options: { content: any; }) => Promise<void>
```

| Param         | Type                           |
| ------------- | ------------------------------ |
| **`options`** | <code>{ content: any; }</code> |

--------------------


### standby()

```typescript
standby() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### Interfaces


#### Payload

| Prop          | Type                |
| ------------- | ------------------- |
| **`payload`** | <code>string</code> |

</docgen-api>

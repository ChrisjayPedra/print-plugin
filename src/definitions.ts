declare module "@capacitor/core"{
    interface registerPlugin{
      PrintPlugin:printPluginPlugin
    }
}
export interface printPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  testPluginMethod(options: { msg: string }): Promise<{ value: string }>;
  enableServices(): Promise<{ value: string }>;
  PrintAddCallBack(): Promise<{ value: string }>;
  PrintRemoveCallBack(): Promise<{ value: string }>;
  PrintOpenPort(options:{content:string}):Promise<void>
  EnumBle(): Promise<{ value: string }>;
}

declare module "@capacitor/core"{
    interface registerPlugin{
      PrintPlugin:printPluginPlugin
    }
}
export interface printPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  testPluginMethod(options: { msg: string }): Promise<{ value: string }>;
}

declare module "@capacitor/core"{
    interface PluginRegistry{
      PrintPlugin:printPluginPlugin
    }
}
export interface printPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  testPluginMethod(options: { msg: string }): Promise<{ value: string }>;
}

declare module "@capacitor/core"{
    interface registerPlugin{
      PrintPlugin:printPluginPlugin
    }
}
export interface printPluginPlugin {
  testPluginMethod(options: { msg: string }): Promise<{ value: string }>;
  enableServices(): Promise<{ value: string }>;
  AddCallback(): Promise<{ value: string }>;
  EnumBle(): Promise<{ value: string }>;
  EnumBt(): Promise<{ value: string }>;
  EnumNet(): Promise<{ value: string }>;
  EnumCom(): Promise<{ value: string }>;
  EnumUsb(): Promise<{ value: string }>;
  EnumWiFiP2P(): Promise<{ value: string }>;
  Test_Pos_SampleTicket_80MM_1(): Promise<{ value: string }>;
  Test_Pos_SampleTicket_58MM_2(): Promise<{ value: string }>;
  OpenPort(options: {content: any}) : Promise<void>
  
}

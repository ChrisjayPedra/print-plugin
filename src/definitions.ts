declare module "@capacitor/core"{
    interface registerPlugin{
      PrintPlugin:printPluginPlugin
    }
}
export interface printPluginPlugin {
  testPluginMethod(options: { msg: string }): Promise<{ value: string }>;
  DataFromPlugin(): Promise<{payload: Payload }> 
  AddCallback(): Promise<{ value: string }>;
  enableServices(): Promise<{ value: string }>;
  RemoveCallback(): Promise<{ value: string }>;
  EnumBle(): Promise<{ value: string }>;
  EnumBt(): Promise<{ value: string }>;
  EnumNet(): Promise<{ value: string }>;
  EnumCom(): Promise<{ value: string }>;
  EnumUsb(): Promise<{ value: string }>;
  EnumWiFiP2P(): Promise<{ value: string }>;
  Test_Pos_SampleTicket_80MM_1(): Promise<{ value: string }>;
  Test_Pos_SampleTicket_58MM_2(): Promise<{ value: string }>;
  Test_Pos_SampleTicket_58MM_1(): Promise<{ value: string }>;
  Test_Port_Read(): Promise<{ value: string }>;
  Test_Pos_PrintSelfTestPage(): Promise<{ value: string }>;
  OpenPort(options: {content: any}) : Promise<void>
  ClosePort(): Promise<{ value: string }>;
  Test_Pos_PrintBarcode(): Promise<{ value: string }>;
  Test_Pos_PrintQRCode(): Promise<{ value: string }>;
  DisplayScreen(options: {content: any}): Promise<{ value: string }>;
  Test_Custom_Ticket_Receipt(options: {content: any}): Promise<void> 
  standby(): Promise<{ value: string }>;
}
export interface Payload {
  payload: string;
}


import { WebPlugin } from '@capacitor/core';

import type { printPluginPlugin } from './definitions';

export class printPluginWeb extends WebPlugin implements printPluginPlugin {
  async ClosePort(): Promise<{ value: string }> {
    throw new Error('Method not implemented.');
  }
  async OpenPort(_options: { content: any }): Promise<void> {
    throw new Error('Method not implemented.');
  }
  async EnumBle(): Promise<{ value: string }> {
    throw new Error('Method not implemented.');
  }
  async EnumBt(): Promise<{ value: string }> {
    throw new Error('Method not implemented.');
  }
  async EnumNet(): Promise<{ value: string }> {
    throw new Error('Method not implemented.');
  }
  async EnumCom(): Promise<{ value: string }> {
    throw new Error('Method not implemented.');
  }
  async EnumUsb(): Promise<{ value: string }> {
    throw new Error('Method not implemented.');
  }
  async EnumWiFiP2P(): Promise<{ value: string }> {
    throw new Error('Method not implemented.');
  }
  async Test_Pos_SampleTicket_80MM_1(): Promise<{ value: string }> {
    throw new Error('Method not implemented.');
  }
  async Test_Pos_SampleTicket_58MM_2(): Promise<{ value: string }> {
    throw new Error('Method not implemented.');
  }
  async Test_Pos_SampleTicket_58MM_1(): Promise<{ value: string }> {
    throw new Error('Method not implemented.');
  }
  async Test_Pos_PrintSelfTestPage(): Promise<{ value: string; }> {
    throw new Error('Method not implemented.');
  }
  async Test_Port_Read(): Promise<{ value: string; }> {
    throw new Error('Method not implemented.');
  }
  async RemoveCallback(): Promise<{ value: string }> {
    throw new Error('Method not implemented.');
  }
  async AddCallback(): Promise<{ value: string }> {
    throw new Error('Method not implemented.');
  }
  async enableServices(): Promise<{ value: string }> {
    console.log('Services has been enabled');
    return { value: 'Services enabled' };
  }
  async testPluginMethod(options: { msg: string }): Promise<{ value: string }> {
    // alert(options.msg);
    console.log('testPluginMethod');
    return { value: options.msg };
    // throw new Error('Method not implemented.');
  }
}

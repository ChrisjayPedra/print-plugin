import { WebPlugin } from '@capacitor/core';

import type { printPluginPlugin } from './definitions';

export class printPluginWeb extends WebPlugin implements printPluginPlugin {
  EnumBle(): Promise<{ value: string; }> {
    throw new Error('Method not implemented.');
  }
  PrintOpenPort(options: { content: string; }): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      try {
          console.log(options.content);
          resolve();
      } catch (error) {
          reject(error);
      }
  }); 
  }
  async PrintAddCallBack(): Promise<{ value: string }> {
    return { value: 'PrintAddCallback' };
  }
  async PrintRemoveCallBack(): Promise<{ value: string }> {
    return { value: 'PrintRemoveCallBack' };
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
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}

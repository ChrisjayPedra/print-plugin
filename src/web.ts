import { WebPlugin } from '@capacitor/core';

import type { printPluginPlugin } from './definitions';

export class printPluginWeb extends WebPlugin implements printPluginPlugin {
 async testPluginMethod(options: { msg: string; }): Promise<{ value: string; }> {
  alert(options.msg)
  console.log("testPluginMethod")
  return{value:options.msg}
    // throw new Error('Method not implemented.');
  }
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}

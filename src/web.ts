import { WebPlugin } from '@capacitor/core';

import type { printPluginPlugin } from './definitions';

export class printPluginWeb extends WebPlugin implements printPluginPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}

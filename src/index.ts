import { registerPlugin } from '@capacitor/core';

import type { printPluginPlugin } from './definitions';

const printPlugin = registerPlugin<printPluginPlugin>('printPlugin', {
  web: () => import('./web').then(m => new m.printPluginWeb()),
});

export * from './definitions';
export { printPlugin };

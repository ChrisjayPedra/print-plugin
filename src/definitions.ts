export interface printPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}

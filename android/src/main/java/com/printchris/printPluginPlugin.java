package com.printchris;

import android.widget.Toast;

import com.caysn.autoreplyprint.AutoReplyPrint;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.sun.jna.Pointer;

@CapacitorPlugin(name = "printPlugin")
public class printPluginPlugin extends Plugin {

    private printPlugin implementation = new printPlugin();
    private Pointer h = Pointer.NULL;
    private static final int nBaudTable[] = {1200, 2400, 4800, 9600, 19200, 38400, 57600, 115200, 230400, 256000, 500000, 750000, 1125000, 1500000};

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
        AddCallback();
    }
    AutoReplyPrint.CP_OnPortOpenedEvent_Callback opened_callback = new AutoReplyPrint.CP_OnPortOpenedEvent_Callback() {
        @Override
        public void CP_OnPortOpenedEvent(Pointer handle, String name, Pointer private_data) {

        }
    };
    private void AddCallback() {
        AutoReplyPrint.INSTANCE.CP_Port_AddOnPortOpenedEvent(opened_callback, Pointer.NULL);
    }

    @PluginMethod()
    public void testPluginMethod(PluginCall call){
            String value = call.getString("msg");
            JSObject ret = new JSObject();
            ret.put("value",value);
            call.resolve(ret);
    }
}

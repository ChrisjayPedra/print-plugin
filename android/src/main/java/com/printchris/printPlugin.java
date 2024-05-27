package com.printchris;
import android.util.Log;

import com.caysn.autoreplyprint.AutoReplyPrint;
import com.sun.jna.Pointer;

public class printPlugin {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }


}

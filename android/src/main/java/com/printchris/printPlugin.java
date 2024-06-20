package com.printchris;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Presentation;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.display.DisplayManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import android.app.Activity;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.caysn.autoreplyprint.AutoReplyPrint;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@CapacitorPlugin(name = "printPlugin")
public class printPlugin extends Plugin {

    private static final int RequestCode_RequestAllPermissions = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private static final int MY_BLUETOOTH_PERMISSIONS_REQUEST_CODE = 1001;
    private Pointer h = Pointer.NULL;
    String strBT2Address = "";
    String strBT4Address = "";
    String strNETAddress = "";
    String strUSBAddress = "";
    String strCOMAddress = "";
    String strWiFiP2PAddress = "";
    //TODO
    // private static final int nBaudTable[] = {1200, 2400, 4800, 9600, 19200, 38400, 57600, 115200, 230400, 256000, 500000, 750000, 1125000, 1500000};
    int nComBaudrate;
    //TODO
    // getcurrent index
    // cbxListCOMFlowControl.addString("No Flow Control");
    // cbxListCOMFlowControl.addString("Xon/Xoff");
    // cbxListCOMFlowControl.addString("Rts/Cts");
    // cbxListCOMFlowControl.addString("Dtr/Dsr");
    // cbxListCOMFlowControl.setText("No Flow Control");
    int nComFlowControl;
    String strUSBPort = "";
    String strCOMPort = "";
    private JSONObject Content;
    private JSObject ret = new JSObject();
    private DisplayManager mDisplayManager;
    private Display[] displays;

    @PluginMethod
    public void Test_Pos_SampleTicket_80MM_1(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SampleTicket_80MM_1(h);
            Log.d("try", "Test_Pos_SampleTicket_80MM_1: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();

            Log.d("Catch", "Test_Pos_SampleTicket_80MM_1: "+e.getMessage());

            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SampleTicket_80MM_2(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SampleTicket_80MM_2(h);
            Log.d("try", "Test_Pos_SampleTicket_80MM_2: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SampleTicket_80MM_2: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SampleTicket_58MM_2(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SampleTicket_58MM_2(h);
            Log.d("try", "Test_Pos_SampleTicket_58MM_2: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SampleTicket_58MM_2: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SampleTicket_58MM_1(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SampleTicket_58MM_1(h);
            Log.d("try", "Test_Pos_SampleTicket_58MM_1: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SampleTicket_58MM_1: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintSelfTestPage(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintSelfTestPage(h);
            Log.d("try", "Test_Pos_PrintSelfTestPage: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintSelfTestPage: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintBarcode(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintBarcode(h);
            Log.d("try", "Test_Pos_PrintBarcode: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintBarcode: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintQRCode(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintQRCode(h);
            Log.d("try", "Test_Pos_PrintQRCode: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintQRCode: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Port_Read(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Port_Read(h);
            Log.d("try", "Test_Port_Read: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Port_Read: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Port_Write(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Port_Write(h);
            Log.d("try", "Test_Port_Write: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Port_Write: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_MultipleLanguages_CompressNone(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_MultipleLanguages_CompressNone(h);
            Log.d("try", "Test_Pos_MultipleLanguages_CompressNone: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_MultipleLanguages_CompressNone: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_MultipleLanguages_CompressLevel1(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_MultipleLanguages_CompressLevel1(h);
            Log.d("try", "Test_Pos_MultipleLanguages_CompressLevel1: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_MultipleLanguages_CompressLevel1: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_MultipleLanguages_CompressLevel2(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_MultipleLanguages_CompressLevel2(h);
            Log.d("try", "Test_Pos_MultipleLanguages_CompressLevel2: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_MultipleLanguages_CompressLevel2: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SampleImage_1_CompressNone(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SampleImage_1_CompressNone(h);
            Log.d("try", "Test_Pos_SampleImage_1_CompressNone: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SampleImage_1_CompressNone: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SampleImage_1_CompressLevel1(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SampleImage_1_CompressLevel1(h);
            Log.d("try", "Test_Pos_SampleImage_1_CompressLevel1: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SampleImage_1_CompressLevel1: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SampleImage_1_CompressLevel2(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SampleImage_1_CompressLevel2(h);
            Log.d("try", "Test_Pos_SampleImage_1_CompressLevel2: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SampleImage_1_CompressLevel2: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SampleImage_2_CompressLevel2(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SampleImage_2_CompressLevel2(h);
            Log.d("try", "Test_Pos_SampleImage_2_CompressLevel2: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SampleImage_2_CompressLevel2: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Port_Available(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Port_Available(h);
            Log.d("try", "Test_Port_Available: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Port_Available: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Port_SkipAvailable(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Port_SkipAvailable(h);
            Log.d("try", "Test_Port_SkipAvailable: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Port_SkipAvailable: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Port_IsConnectionValid(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Port_IsConnectionValid(h);
            Log.d("try", "Test_Port_IsConnectionValid: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Port_IsConnectionValid: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Printer_GetPrinterInfo(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Printer_GetPrinterInfo(h);
            Log.d("try", "Test_Printer_GetPrinterInfo: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Printer_GetPrinterInfo: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Printer_ClearPrinterBuffer(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Printer_ClearPrinterBuffer(h);
            Log.d("try", "Test_Printer_ClearPrinterBuffer: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Printer_ClearPrinterBuffer: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Printer_ClearPrinterError(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Printer_ClearPrinterError(h);
            Log.d("try", "Test_Printer_ClearPrinterError: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Printer_ClearPrinterError: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Proto_QueryBatteryLevel(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Proto_QueryBatteryLevel(h);
            Log.d("try", "Test_Proto_QueryBatteryLevel: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Proto_QueryBatteryLevel: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintQRCodeUseEpsonCmd(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintQRCodeUseEpsonCmd(h);
            Log.d("try", "Test_Pos_PrintQRCodeUseEpsonCmd: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintQRCodeUseEpsonCmd: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintQRCodeUseImageCmd(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintQRCodeUseImageCmd(h);
            Log.d("try", "Test_Pos_PrintQRCodeUseImageCmd: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintQRCodeUseImageCmd: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintDoubleQRCode(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintDoubleQRCode(h);
            Log.d("try", "Test_Pos_PrintDoubleQRCode: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintDoubleQRCode: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintPDF417BarcodeUseEpsonCmd(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintPDF417BarcodeUseEpsonCmd(h);
            Log.d("try", "Test_Pos_PrintPDF417BarcodeUseEpsonCmd: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintPDF417BarcodeUseEpsonCmd: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintRasterImageFromBitmap(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintRasterImageFromBitmap(h);
            Log.d("try", "Test_Pos_PrintRasterImageFromBitmap: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintRasterImageFromBitmap: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_KickOutDrawer(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_KickOutDrawer(h);
            Log.d("try", "Test_Pos_KickOutDrawer: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_KickOutDrawer: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_Beep(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_Beep(h);
            Log.d("try", "Test_Pos_Beep: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_Beep: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_FeedAndHalfCutPaper(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_FeedAndHalfCutPaper(h);
            Log.d("try", "Test_Pos_FeedAndHalfCutPaper: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_FeedAndHalfCutPaper: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_FullCutPaper(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_FullCutPaper(h);
            Log.d("try", "Test_Pos_FullCutPaper: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_FullCutPaper: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_HalfCutPaper(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_HalfCutPaper(h);
            Log.d("try", "Test_Pos_HalfCutPaper: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_HalfCutPaper: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_Feed(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_Feed(h);
            Log.d("try", "Test_Pos_Feed: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_Feed: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintText(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintText(h);
            Log.d("try", "Test_Pos_PrintText: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintText: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintTextInUTF8(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintTextInUTF8(h);
            Log.d("try", "Test_Pos_PrintTextInUTF8: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintTextInUTF8: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintTextInGBK(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintTextInGBK(h);
            Log.d("try", "Test_Pos_PrintTextInGBK: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintTextInGBK: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintTextInBIG5(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintTextInBIG5(h);
            Log.d("try", "Test_Pos_PrintTextInBIG5: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintTextInBIG5: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintTextInShiftJIS(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintTextInShiftJIS(h);
            Log.d("try", "Test_Pos_PrintTextInShiftJIS: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintTextInShiftJIS: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintTextInEUCKR(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintTextInEUCKR(h);
            Log.d("try", "Test_Pos_PrintTextInEUCKR: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintTextInEUCKR: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintHorizontalLine(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintHorizontalLine(h);
            Log.d("try", "Test_Pos_PrintHorizontalLine: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintHorizontalLine: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintHorizontalLineSpecifyThickness(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintHorizontalLineSpecifyThickness(h);
            Log.d("try", "Test_Pos_PrintHorizontalLineSpecifyThickness: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintHorizontalLineSpecifyThickness: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_PrintMultipleHorizontalLinesAtOneRow(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_PrintMultipleHorizontalLinesAtOneRow(h);
            Log.d("try", "Test_Pos_PrintMultipleHorizontalLinesAtOneRow: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_PrintMultipleHorizontalLinesAtOneRow: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SetMovementUnit(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SetMovementUnit(h);
            Log.d("try", "Test_Pos_SetMovementUnit: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SetMovementUnit: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SetPrintAreaLeftMargin(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SetPrintAreaLeftMargin(h);
            Log.d("try", "Test_Pos_SetPrintAreaLeftMargin: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SetPrintAreaLeftMargin: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SetPrintAreaWidth(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SetPrintAreaWidth(h);
            Log.d("try", "Test_Pos_SetPrintAreaWidth: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SetPrintAreaWidth: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SetPrintPosition(PluginCall call){

        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SetPrintPosition(h);
            Log.d("try", "Test_Pos_SetPrintPosition: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SetPrintPosition: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SetAlignment(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SetAlignment(h);
            Log.d("try", "Test_Pos_SetAlignment: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SetAlignment: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SetTextScale(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SetTextScale(h);
            Log.d("try", "Test_Pos_SetTextScale: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SetTextScale: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SetAsciiTextFontType(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SetAsciiTextFontType(h);
            Log.d("try", "Test_Pos_SetAsciiTextFontType: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SetAsciiTextFontType: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }
    @PluginMethod
    public void Test_Pos_SetTextBold(PluginCall call){
        try{
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Pos_SetTextBold(h);
            Log.d("try", "Test_Pos_SetTextBold: ");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Catch", "Test_Pos_SetTextBold: "+e.getMessage());
            call.reject(e.getMessage());
        }
    }


    @PluginMethod()
    public void Test_Custom_Ticket_Receipt(PluginCall call) {
        Log.d("Plugin_Custom_ticket", "Received call: " + call.getData());

        try {
            JSObject content = call.getData();
            Log.d("Plugin_Custom_ticket", "Test_Custom_Ticket_Receipt: "+content);
            function printer = new function();
            printer.setActivity(getActivity());
            printer.Test_Custom_Ticket_Receipt(h,content);
            Log.d("try", "Test_Custom_Ticket_Receipt: ");
            call.resolve(ret.put("Custom_ticket","success"));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Plugin", e.getMessage());
        }
    }
    @PluginMethod()
    public void DataFromPlugin(PluginCall call) {
        try {

            JSObject jsonPayload = new JSObject();
            jsonPayload.put("payload","test data");
            call.resolve(jsonPayload);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Plugin", e.getMessage());
        }
    }

    private void showToast(Context context, String message) {
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
    public void OpenBluetooth() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            bridge.getActivity().startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

        }
    }
    @PluginMethod
    public void DisplayScreen(PluginCall call) {
        Log.d("Plugin_Display", "Received call: " + call.getData());
        Context context = getContext();
        JSObject data = call.getData();
        try {
            DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
            Display[] displays = displayManager.getDisplays();

            JSONObject content = data.getJSONObject("content");
            JSONArray items = content.getJSONArray("item");
            double payment = content.getDouble("payment");
            double change = content.getDouble("change");
            double tax = content.getDouble("tax");
            double itemSubtotal = content.getDouble("itemSubtotal");
            double totalAmount = content.getDouble("totalAmount");
            String date = content.getString("date");
            String tranID = content.getString("tranID");
            String paymentType = content.getString("paymentType");
            Log.d("payment", "payment: "+payment);
            Log.d("change", "change: "+change);
            Log.d("tax", "tax: "+tax);
            Log.d("itemSubtotal", "itemSubtotal: "+itemSubtotal);
            Log.d("totalAmount", "totalAmount: "+totalAmount);
            Log.d("date", "date: "+date);
            Log.d("tranID", "tranID: "+tranID);
            Log.d("paymentType", "paymentType: "+paymentType);
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                String itemName = item.getString("title");
                String itemDesc = item.getString("desc");
                String quantity = item.getString("quantity");// Retrieve description field
                String itemPrice = item.getString("price");
                Log.d("item", "itemName: "+itemName);
                Log.d("itemDesc", "itemDesc: "+itemDesc);
                Log.d("itemPrice", "itemPrice: "+itemPrice);
                Log.d("quantity", "quantity: "+quantity);
            }
            Log.d("payment", "payment: "+payment);
            int numberOfDisplays = displays.length;
            showToast(context,"Number of Displays Detected: " + numberOfDisplays);

            for (Display display : displays) {
                int displayId = display.getDisplayId();
                String displayName = display.getName();

                if (displayId == 0) {
                    showToast(context,"Main Screen: " + displayName+ displayId);
                } else if (displayId == 1 || displayId == 6) {
                    showToast(context,"Auxiliary Screen: " + displayName + displayId);
                    //TODO  printPayload testing
                    SecondaryScreen presentation = new SecondaryScreen(getContext(), display,content);
                    presentation.show();
                }
            }
            call.resolve(ret.put("DisplayScreen", "Success"));
        } catch (Exception e) {
            showToast(context,"Error detecting displays: " + e.getMessage());
            Log.d("MenuItemClicked", "Error detecting displays:  " + e.getMessage());
        }
    }
    @PluginMethod
    public void testPluginMethod(PluginCall call) {
//        String value = call.getString("msg");
        JSObject ret = new JSObject();
        ret.put("value", "sample");
        call.resolve(ret);
    }

    @PluginMethod
    public void enableServices(PluginCall call) {
        JSObject ret = new JSObject();
        try{
            enableBluetooth();
            enableWiFi();
            enableLocation();
            if (hasAllPermissions()) {

            } else {
                requestAllPermissions(call);
            }
            call.resolve(ret.put("enableServices", "Success"));
        }catch (Exception e){
            e.printStackTrace();
            call.reject("Error:"+e.getMessage());
        }
    }

    @PluginMethod
    public void AddCallback(PluginCall call) {
        JSObject ret = new JSObject();
        OpenBluetooth();
        try {
            Log.d("Try", "AutoReplyPrint Library_Version" + AutoReplyPrint.INSTANCE.CP_Library_Version());
            AutoReplyPrint.INSTANCE.CP_Port_AddOnPortOpenedEvent(opened_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Port_AddOnPortOpenFailedEvent(openfailed_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Port_AddOnPortClosedEvent(closed_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Printer_AddOnPrinterStatusEvent(status_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Printer_AddOnPrinterReceivedEvent(received_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Printer_AddOnPrinterPrintedEvent(printed_callback, Pointer.NULL);
            ret.put("AddCallback", "Success");
            call.resolve(ret);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Catch", "addCallBack: e" + e.getMessage());
            call.reject("AddCallback",e.getMessage());
        }
    }

    @PluginMethod
    public void RemoveCallback(PluginCall call) {
        JSObject ret = new JSObject();
        try {
            AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortOpenedEvent(opened_callback);
            AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortOpenFailedEvent(openfailed_callback);
            AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortClosedEvent(closed_callback);
            AutoReplyPrint.INSTANCE.CP_Printer_RemoveOnPrinterStatusEvent(status_callback);
            AutoReplyPrint.INSTANCE.CP_Printer_RemoveOnPrinterReceivedEvent(received_callback);
            AutoReplyPrint.INSTANCE.CP_Printer_RemoveOnPrinterPrintedEvent(printed_callback);
            Log.d("Try", "RemoveCallBack Method");
            call.resolve(ret.put("RemoveCallback", "Success"));
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Catch", "RemoveCallBack: e" + e.getMessage());
            call.reject("RemoveCallback",e.getMessage());
        }
    }

    @PluginMethod
    public void EnumBle(PluginCall call) {
        JSObject ret = new JSObject();
        try {
            IntByReference cancel = new IntByReference(0);
            AutoReplyPrint.CP_OnBluetoothDeviceDiscovered_Callback callback = new AutoReplyPrint.CP_OnBluetoothDeviceDiscovered_Callback() {
                @Override
                public void CP_OnBluetoothDeviceDiscovered(String device_name, final String device_address, Pointer private_data) {
                    getActivity()
                            .runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d("BT4", "run: BT4" + device_address);
                                            strBT4Address = device_address;
                                            Log.d("BT4", "run: BT4" + device_address);
                                            call.resolve(ret.put("EnumBle", "Success "+device_address));
                                        }
                                    }
                            );
                }
            };
            AutoReplyPrint.INSTANCE.CP_Port_EnumBleDevice(20000, cancel, callback, null);
            Log.d("BT4", "EnumBle: try ");
            call.resolve(ret.put("EnumBle", "Scan Stop"));
        } catch (Exception e) {
            Log.d("BT4", "EnumBle: catch " + e.getMessage());
            call.reject("error");
        }

    }

    @PluginMethod
    public void EnumBt(PluginCall call) {
        JSObject ret = new JSObject();
        try {
            IntByReference cancel = new IntByReference(0);
            AutoReplyPrint.CP_OnBluetoothDeviceDiscovered_Callback callback = new AutoReplyPrint.CP_OnBluetoothDeviceDiscovered_Callback() {
                @Override
                public void CP_OnBluetoothDeviceDiscovered(String device_name, final String device_address, Pointer private_data) {
                    getActivity()
                            .runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d("BT2", "run: BT2" + device_address);
                                            strBT2Address = device_address;
                                            Log.d("BT2", "run: BT2" + strBT2Address);
                                            call.resolve(ret.put("EnumBt", "Success"));
                                        }
                                    }
                            );
                }
            };
            AutoReplyPrint.INSTANCE.CP_Port_EnumBtDevice(12000, cancel, callback, null);
            Log.d("BT2", "EnumBt: try ");
            call.resolve(ret.put("EnumBt", "Scan Stop"));
        } catch (Exception e) {
            Log.d("BT2", "EnumBt: catch " + e.getMessage());
            call.reject("error");
        }

    }

    @PluginMethod
    public void EnumNet(PluginCall call) {
        JSObject ret = new JSObject();
        try {
            IntByReference cancel = new IntByReference(0);
            AutoReplyPrint.CP_OnNetPrinterDiscovered_Callback callback = new AutoReplyPrint.CP_OnNetPrinterDiscovered_Callback() {
                @Override
                public void CP_OnNetPrinterDiscovered(String local_ip,String disconvered_mac,final String disconvered_ip,String discovered_name,Pointer private_data) {
                    getActivity().runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("cbxListNET", "cbxListNET: " + disconvered_ip);
                                    strNETAddress = disconvered_ip;
                                    call.resolve(ret.put("EnumNet", "Success"));
                                }
                            }
                    );
                }
            };
            AutoReplyPrint.INSTANCE.CP_Port_EnumNetPrinter(3000, cancel, callback, null);
            Log.d("NET", "NET: try ");
            call.resolve(ret.put("NET", "Scan Stop"));
        } catch (Exception e) {
            Log.d("NET", "NET: catch " + e.getMessage());
            call.reject("error");
        }

    }

    @PluginMethod
    public void EnumCom(PluginCall call) {
        JSObject ret = new JSObject();
        try {
            String[] devicePaths = AutoReplyPrint.CP_Port_EnumCom_Helper.EnumCom();
            if (devicePaths != null) {
                for (int i = 0; i < devicePaths.length; ++i) {
                    String name = devicePaths[i];
                    if (strCOMPort.trim().isEmpty()) {
                        strCOMPort = name;
                        Log.d("Device COM name", "EnumCom: " + strCOMPort);
                        call.resolve(ret.put("EnumCom", "Success"));
                    }
                    Log.d("Device name", "EnumCom: " + name);
                }
            }
            Log.d("COM", "COM: try ");
            call.resolve(ret.put("COM", "Scan Stop"));
        } catch (Exception e) {
            Log.d("COM", "COM: catch " + e.getMessage());
            call.reject("error");
        }


    }

    @PluginMethod
    public void EnumUsb(PluginCall call) {
        JSObject ret = new JSObject();
        try {
            String[] devicePaths = AutoReplyPrint.CP_Port_EnumUsb_Helper.EnumUsb();
            if (devicePaths != null) {
                for (int i = 0; i < devicePaths.length; ++i) {
                    String name = devicePaths[i];
                    if (strUSBPort.trim().isEmpty()) {
                        strUSBPort = name;
                        Log.d("Device usb name", "EnumUsb: " + strUSBPort);
                        call.resolve(ret.put("EnumUsb", "Success"));
                    }
                }
            }
            Log.d("USB", "USB: try ");
            call.resolve(ret.put("USB", "Scan Stop"));
        } catch (Exception e) {
            Log.d("USB", "USB: catch " + e.getMessage());
            call.reject("error");
        }
    }

    @PluginMethod
    public void EnumWiFiP2P(PluginCall call) {
        JSObject ret = new JSObject();
        try {
            IntByReference cancel = new IntByReference(0);
            AutoReplyPrint.CP_OnWiFiP2PDeviceDiscovered_Callback callback = new AutoReplyPrint.CP_OnWiFiP2PDeviceDiscovered_Callback() {
                @Override
                public void CP_OnWiFiP2PDeviceDiscovered(String device_name,final String device_address, String device_type, Pointer private_data) {
                    getActivity().runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("device name Wifi", "run: " + device_address);
                                    strWiFiP2PAddress = device_address;
                                    call.resolve(ret.put("EnumWiFiP2P", "Success"));
                                }
                            }
                    );
                }
            };
            AutoReplyPrint.INSTANCE.CP_Port_EnumWiFiP2PDevice(5000, cancel, callback, null);
            Log.d("WIFI", "WIFI: try ");
            call.resolve(ret.put("WIFI", "Scan Stop"));
        } catch (Exception e) {
            Log.d("WIFI", "WIFI: catch " + e.getMessage());
            call.reject("error");
        }

    }

    @PluginMethod
    public void OpenPort(PluginCall call) {
        JSObject ret = new JSObject();
        try {
            JSObject contentObject = call.getObject("content");
            Log.d("Content", "OpenPort: "+contentObject);
            String type = contentObject.getString("type");
            int nBaudTable = contentObject.getInteger("nBaudTable");
            int flowControlIndex = contentObject.getInteger("flowControlIndex", -1);
            Log.d("Content", "OpenPort: "+type);
            Log.d("Content", "OpenPort: "+contentObject);
            if (type.equals("BT2")) {
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenBtSpp(strBT2Address, 1);
                Log.d("strBT2Address", "run: "+strBT2Address);
                call.resolve(ret.put("BT2", "Success"));
            } else if (type.equals("BT4")) {
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenBtBle(strBT4Address, 1);
                Log.d("strBT4Address", "run: "+strBT4Address);
                call.resolve(ret.put("BT4", "Success"));
            } else if (type.equals("NET")) {
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenTcp(null, strNETAddress, (short) 9100, 5000, 1);
                call.resolve(ret.put("NET", "Success"));
            } else if (type.equals("USB")) {
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenUsb(strUSBPort, 1);
                call.resolve(ret.put("USB", "Success"));
            } else if (type.equals("COM")) {
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenCom(strCOMPort, nBaudTable, AutoReplyPrint.CP_ComDataBits_8, AutoReplyPrint.CP_ComParity_NoParity, AutoReplyPrint.CP_ComStopBits_One, flowControlIndex, 1);
                call.resolve(ret.put("COM", "Success"));
            } else if (type.equals("WiFi")) {
                //if (AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_IsConnected())
                //    AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_Disconnect();
                int host_address = AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_Connect(strWiFiP2PAddress, 20000);
                if (host_address != 0) {
                    String host_address_string = String.format("%d.%d.%d.%d", host_address & 0x000000ffl, (host_address & 0x0000ff00l) >> 8, (host_address & 0x00ff0000l) >> 16, (host_address & 0xff000000l) >> 24);
                    h = AutoReplyPrint.INSTANCE.CP_Port_OpenTcp(null, host_address_string, (short) 9100, 5000, 1);
                    call.resolve(ret.put("WiFi", "Success"));
                }
            }

            Log.d("Open port", "OpenPort: try ");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error Open port", "OpenPort: catch Error" + e.getMessage());
        }
    }
    @PluginMethod
    public void ClosePort(PluginCall call) {
        JSObject ret = new JSObject();
        try{
            if (h != Pointer.NULL) {
                AutoReplyPrint.INSTANCE.CP_Port_Close(h);
                h = Pointer.NULL;
                call.resolve(ret.put("ClosePort", "Success"));
            }
            //if (AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_IsConnected())
            //    AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_Disconnect();
            Log.d("ClosePort", "ClosePort: try");

        }catch (Exception e){
            e.printStackTrace();
            Log.d("ClosePort", "ClosePort: "+e.getMessage());
            call.reject("ClosePort error:"+e.getMessage());
        }

    }

    public void Test_Pos_QueryPrintResult(Pointer h) {
        boolean result = AutoReplyPrint.INSTANCE.CP_Pos_QueryPrintResult(h, 30000);
        if (!result) {
            Log.d("TestPrintResult", "Test_Pos_QueryPrintResult:Print failed ");
            TestUtils.showMessageOnUiThread(getActivity(), "Print failed");
        } else {
            Log.d("TestPrintResult", "Test_Pos_QueryPrintResult:Print failed ");
            TestUtils.showMessageOnUiThread(getActivity(), "Print Success");
        }
    }
    AutoReplyPrint.CP_OnPortOpenedEvent_Callback opened_callback = new AutoReplyPrint.CP_OnPortOpenedEvent_Callback() {
        @Override
        public void CP_OnPortOpenedEvent(Pointer handle, String name, Pointer private_data) {
            getActivity()
                    .runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "Open Success", Toast.LENGTH_SHORT).show();

                                    Log.d("opened_callback", "Open Success ");
                                }
                            }
                    );
        }
    };
    AutoReplyPrint.CP_OnPortOpenFailedEvent_Callback openfailed_callback = new AutoReplyPrint.CP_OnPortOpenFailedEvent_Callback() {
        @Override
        public void CP_OnPortOpenFailedEvent(Pointer handle, String name, Pointer private_data) {
            getActivity()
                    .runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "Open Failed", Toast.LENGTH_SHORT).show();
                                    Log.d("openfailed_callback", "Open Failed");
                                }
                            }
                    );
        }
    };
    AutoReplyPrint.CP_OnPortClosedEvent_Callback closed_callback = new AutoReplyPrint.CP_OnPortClosedEvent_Callback() {
        @Override
        public void CP_OnPortClosedEvent(Pointer h, Pointer private_data) {
            getActivity()
                    .runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    //TODO close callback
                                    //                    ClosePort();
                                    Log.d("closed_callback", "closed_callback");
                                    Toast.makeText(getContext(),"closed_callback", Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
        }
    };
    AutoReplyPrint.CP_OnPrinterStatusEvent_Callback status_callback = new AutoReplyPrint.CP_OnPrinterStatusEvent_Callback() {
        @Override
        public void CP_OnPrinterStatusEvent(
                Pointer h,
                final long printer_error_status,
                final long printer_info_status,
                Pointer private_data
        ) {
            getActivity()
                    .runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    Calendar calendar = Calendar.getInstance();
                                    Date calendarDate = calendar.getTime();
                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CUPCAKE) {
                                        String time = DateFormat.format("yyyy-MM-dd kk:mm:ss", calendarDate).toString();
                                    }
                                    AutoReplyPrint.CP_PrinterStatus status = new AutoReplyPrint.CP_PrinterStatus(
                                            printer_error_status,
                                            printer_info_status
                                    );
                                    String error_status_string = String.format(" Printer Error Status: 0x%04X", printer_error_status & 0xffff);
                                    if (status.ERROR_OCCURED()) {
                                        if (status.ERROR_CUTTER()) {
                                            error_status_string += "[ERROR_CUTTER]";
                                            Toast.makeText(getContext(), error_status_string += "[ERROR_CUTTER]", Toast.LENGTH_SHORT).show();
                                        }
                                          if (status.ERROR_FLASH()){
                                              error_status_string += "[ERROR_FLASH]";

                                              Toast.makeText(getContext(),error_status_string += "[ERROR_FLASH]", Toast.LENGTH_SHORT).show();

                                          }
                                        if (status.ERROR_NOPAPER()){
                                            error_status_string += "[ERROR_NOPAPER]";

                                            Toast.makeText(getContext(),error_status_string += "[ERROR_NOPAPER]", Toast.LENGTH_SHORT).show();

                                        }
                                         if (status.ERROR_VOLTAGE()){
                                             error_status_string += "[ERROR_VOLTAGE]";

                                             Toast.makeText(getContext(),error_status_string += "[ERROR_VOLTAGE]", Toast.LENGTH_SHORT).show();

                                         }
                                        if (status.ERROR_MARKER()){
                                            error_status_string += "[ERROR_MARKER]";

                                            Toast.makeText(getContext(),error_status_string += "[ERROR_MARKER]", Toast.LENGTH_SHORT).show();

                                        }
                                        if (status.ERROR_ENGINE()){
                                            error_status_string += "[ERROR_MOVEMENT]";

                                            Toast.makeText(getContext(),error_status_string += "[ERROR_MOVEMENT]", Toast.LENGTH_SHORT).show();

                                        }
                                        if (status.ERROR_OVERHEAT()){
                                            error_status_string += "[ERROR_OVERHEAT]";

                                            Toast.makeText(getContext(),error_status_string += "[ERROR_MOVEMENT]", Toast.LENGTH_SHORT).show();

                                        }
                                         if (status.ERROR_COVERUP()){
                                             error_status_string += "[ERROR_COVERUP]";

                                             Toast.makeText(getContext(),error_status_string += "[ERROR_COVERUP]", Toast.LENGTH_SHORT).show();

                                         }
                                        if (status.ERROR_MOTOR()){
                                            Toast.makeText(getContext(),error_status_string += "[ERROR_COVERUP]", Toast.LENGTH_SHORT).show();

                                        }

                                        error_status_string += "[ERROR_MOTOR]";
                                        Toast.makeText(getContext(),error_status_string += "[ERROR_MOTOR]", Toast.LENGTH_SHORT).show();
                                    }
                                    String info_status_string = String.format(" Printer Info Status: 0x%04X", printer_info_status & 0xffff);
                                    if (status.INFO_LABELMODE()){
                                        info_status_string += "[Label Mode]";
                                        Toast.makeText(getContext(),error_status_string += "[Label Mode]", Toast.LENGTH_SHORT).show();
                                    }
                                    if (status.INFO_LABELPAPER()){
                                        info_status_string += "[Label Paper]";
                                        Toast.makeText(getContext(),error_status_string += "[Label Paper]", Toast.LENGTH_SHORT).show();
                                    }
                                    if (status.INFO_PAPERNOFETCH()){
                                        info_status_string += "[Paper Not Fetch]";
                                        Toast.makeText(getContext(),error_status_string += "[Paper Not Fetch]", Toast.LENGTH_SHORT).show();

                                    }
                                    Log.d("PrinterErrorStatus", "textViewPrinterErrorStatus: " + error_status_string);
                                    Log.d("PrinterInfoStatus", "textViewPrinterErrorStatus: " + info_status_string);
                                }
                            }
                    );
        }
    };
    AutoReplyPrint.CP_OnPrinterReceivedEvent_Callback received_callback = new AutoReplyPrint.CP_OnPrinterReceivedEvent_Callback() {
        @Override
        public void CP_OnPrinterReceivedEvent(Pointer h, final int printer_received_byte_count, Pointer private_data) {
            getActivity()
                    .runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    Calendar calendar = Calendar.getInstance();
                                    Date calendarDate = calendar.getTime();
                                    String time = DateFormat.format("yyyy-MM-dd kk:mm:ss", calendarDate).toString();
                                    Log.d("printer_byte_count", time + "PrinterReceived" + printer_received_byte_count);
                                }
                            }
                    );
        }
    };
    AutoReplyPrint.CP_OnPrinterPrintedEvent_Callback printed_callback = new AutoReplyPrint.CP_OnPrinterPrintedEvent_Callback() {
        @Override
        public void CP_OnPrinterPrintedEvent(Pointer h, final int printer_printed_page_id, Pointer private_data) {
            getActivity()
                    .runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    Calendar calendar = Calendar.getInstance();
                                    Date calendarDate = calendar.getTime();
                                    String time = DateFormat.format("yyyy-MM-dd kk:mm:ss", calendarDate).toString();
                                    Log.d("PrinterPrinted", "PrinterPrinted" + printer_printed_page_id);
                                }
                            }
                    );
        }
    };

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RequestCode_RequestAllPermissions:
                if (hasAllPermissions()) {
                } else {
                    Log.d("onPermissionGranted", "onRequestPermissionsResult: ");
                    Toast.makeText(getContext(),"onRequestPermissionsResult", Toast.LENGTH_SHORT).show();

                }
                Log.d("finish", "onRequestPermissionsResult: ");
                break;
        }
    }
    public boolean hasAllPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean hasLocationPermission =
                    (
                            ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                                    PackageManager.PERMISSION_GRANTED
                    );
            boolean hasCameraPermission =
                    (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
            boolean hasStoragePermission =
                    (
                            ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                    PackageManager.PERMISSION_GRANTED
                    );

            boolean hasBluetoothConnectPermission =
                    (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED);
            boolean hasBluetoothScanPermission =
                    (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED);
            boolean hasBluetoothAdminPermission =
                    (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED);

            return hasLocationPermission && hasCameraPermission && hasStoragePermission &&
                    hasBluetoothConnectPermission && hasBluetoothScanPermission && hasBluetoothAdminPermission;

        }
        return true;
    }
    public void requestAllPermissions(PluginCall call) {
        saveCall(call);
        pluginRequestPermissions(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        }, MY_BLUETOOTH_PERMISSIONS_REQUEST_CODE);
    }
    protected void handleRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.handleRequestPermissionsResult(requestCode, permissions, grantResults);

        PluginCall savedCall = getSavedCall();
        if (savedCall == null) {
            return;
        }

        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                savedCall.reject("Permission denied");
                return;
            }
        }

        savedCall.resolve();
    }
    @SuppressLint("MissingPermission")
    public void enableBluetooth() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter != null && !adapter.isEnabled()) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH_CONNECT)
                    != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH_SCAN)
                            != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED){
                // Request permissions
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.BLUETOOTH_SCAN
                                ,Manifest.permission.BLUETOOTH_CONNECT
                                ,Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_BLUETOOTH_PERMISSIONS_REQUEST_CODE);

                Toast.makeText(getContext(), "enableBluetooth: true", Toast.LENGTH_SHORT).show();
                Log.d("Bluetooth", "enableBluetooth: true");
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                bridge.getActivity().startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                Toast.makeText(getContext(), "Already granted", Toast.LENGTH_SHORT).show();
                Log.d("Bluetooth", "enableBluetooth: true");
                Log.d("Bluetooth", "Already granted");
            }

        }
    }
    public void enableWiFi() {
        WifiManager wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (null != wifiManager) {
            if (!wifiManager.isWifiEnabled()) {
                if (!wifiManager.setWifiEnabled(true)) {
                    Toast.makeText(getContext(), "enableWiFi: off", Toast.LENGTH_SHORT).show();
                    Log.d("WIFI", "enableWiFi: off");
                }
            }
        }
    }
    public void enableLocation() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (null != locationManager) {
            boolean gpsLocation = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean networkLocation = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!gpsLocation && !networkLocation) {
                Toast.makeText(getContext(), "Please enable location else will not search ble printer", Toast.LENGTH_SHORT).show();
                Log.d("Failed", "Please enable location else will not search ble printer");
            }
        }
    }
}

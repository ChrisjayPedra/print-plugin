package com.printchris;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
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
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;

import org.json.JSONObject;

import java.security.PublicKey;
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

    @PluginMethod
    public void testPluginMethod(PluginCall call) {
        String value = call.getString("msg");
        JSObject ret = new JSObject();
        ret.put("value", value);
        call.resolve(ret);
    }

    @PluginMethod
    public void enableServices(PluginCall call) {
        enableBluetooth();
        enableWiFi();
        enableLocation();
        if (hasAllPermissions()) {
            call.resolve();
        } else {
            requestAllPermissions(call);
        }
    }

    @PluginMethod
    public void AddCallback(PluginCall call) {
        try {
            AutoReplyPrint.INSTANCE.CP_Port_AddOnPortOpenedEvent(opened_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Port_AddOnPortOpenFailedEvent(openfailed_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Port_AddOnPortClosedEvent(closed_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Printer_AddOnPrinterStatusEvent(status_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Printer_AddOnPrinterReceivedEvent(received_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Printer_AddOnPrinterPrintedEvent(printed_callback, Pointer.NULL);
            Log.d("Try", "addCallBack Method" + AutoReplyPrint.INSTANCE.CP_Library_Version());
            call.resolve();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Catch", "addCallBack: e" + e.getMessage());
            call.reject(e.getMessage());
        }
    }

    @PluginMethod
    public void RemoveCallback(PluginCall call) {
        try {
            AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortOpenedEvent(opened_callback);
            AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortOpenFailedEvent(openfailed_callback);
            AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortClosedEvent(closed_callback);
            AutoReplyPrint.INSTANCE.CP_Printer_RemoveOnPrinterStatusEvent(status_callback);
            AutoReplyPrint.INSTANCE.CP_Printer_RemoveOnPrinterReceivedEvent(received_callback);
            AutoReplyPrint.INSTANCE.CP_Printer_RemoveOnPrinterPrintedEvent(printed_callback);
            Log.d("Try", "RemoveCallBack Method");
            call.resolve();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Catch", "RemoveCallBack: e" + e.getMessage());
            call.reject(e.getMessage());
        }
    }

    @PluginMethod
    public void Test_Pos_SampleTicket_80MM_1(PluginCall call) {
        Log.d("Test_Pos_SampleTicket", "Test_Pos_SampleTicket_80MM_1: ");
        try {
            int paperWidth = 384;
            int paperHeight = 800;

            AutoReplyPrint.INSTANCE.CP_Page_SelectPageModeEx(h, 200, 200, 0, 0, paperWidth, paperHeight);
            AutoReplyPrint.INSTANCE.CP_Pos_SetMultiByteMode(h);
            AutoReplyPrint.INSTANCE.CP_Pos_SetMultiByteEncoding(h, AutoReplyPrint.CP_MultiByteEncoding_UTF8);

            AutoReplyPrint.INSTANCE.CP_Pos_SetTextScale(h, 1, 1);
            AutoReplyPrint.INSTANCE.CP_Page_DrawTextInUTF8(
                    h,
                    AutoReplyPrint.CP_Page_DrawAlignment_HCenter,
                    10,
                    new WString("中国福利彩票")
            );
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextScale(h, 0, 0);
            AutoReplyPrint.INSTANCE.CP_Page_DrawTextInUTF8(h, 0, 60, new WString("销售期2015033"));
            AutoReplyPrint.INSTANCE.CP_Page_DrawTextInUTF8(h, AutoReplyPrint.CP_Page_DrawAlignment_Right, 60, new WString("兑奖期2015033"));
            AutoReplyPrint.INSTANCE.CP_Page_DrawTextInUTF8(h, 0, 90, new WString("站号230902001"));
            AutoReplyPrint.INSTANCE.CP_Page_DrawTextInUTF8(h, AutoReplyPrint.CP_Page_DrawAlignment_Right, 90, new WString("7639-A"));
            AutoReplyPrint.INSTANCE.CP_Page_DrawTextInUTF8(h, 0, 120, new WString("注数5"));
            AutoReplyPrint.INSTANCE.CP_Page_DrawTextInUTF8(h, AutoReplyPrint.CP_Page_DrawAlignment_Right, 120, new WString("金额10.00"));

            AutoReplyPrint.INSTANCE.CP_Pos_SetTextLineHeight(h, 60);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextScale(h, 0, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextUnderline(h, 2);
            AutoReplyPrint.INSTANCE.CP_Page_DrawTextInUTF8(
                    h,
                    0,
                    160,
                    new WString(
                            " A: 02  07  10  17  20  21  25\r\n A: 02  07  10  17  20  21  25\r\n A: 02  07  10  17  20  21  25\r\n A: 02  07  10  17  20  21  25\r\n A: 02  07  10  17  20  21  25\r\n"
                    )
            );
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextScale(h, 0, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextUnderline(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextLineHeight(h, 30);

            AutoReplyPrint.INSTANCE.CP_Pos_SetBarcodeHeight(h, 60);
            AutoReplyPrint.INSTANCE.CP_Pos_SetBarcodeUnitWidth(h, 3);
            AutoReplyPrint.INSTANCE.CP_Pos_SetBarcodeReadableTextPosition(h, AutoReplyPrint.CP_Pos_BarcodeTextPrintPosition_BelowBarcode);
            AutoReplyPrint.INSTANCE.CP_Page_DrawBarcode(h, 0, 460, AutoReplyPrint.CP_Pos_BarcodeType_CODE128, "1234567890");
            AutoReplyPrint.INSTANCE.CP_Page_DrawQRCode(h, 284, 460, 0, AutoReplyPrint.CP_QRCodeECC_L, "1234567890");

            AutoReplyPrint.INSTANCE.CP_Page_PrintPage(h);
            AutoReplyPrint.INSTANCE.CP_Page_ExitPageMode(h);

            AutoReplyPrint.INSTANCE.CP_Pos_FeedAndHalfCutPaper(h);
            AutoReplyPrint.INSTANCE.CP_Pos_KickOutDrawer(h, 0, 100, 100);
            AutoReplyPrint.INSTANCE.CP_Pos_KickOutDrawer(h, 1, 100, 100);
            AutoReplyPrint.INSTANCE.CP_Pos_Beep(h, 1, 500);

            {
                Test_Pos_QueryPrintResult(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TestFuntion", "TestFuntion: " + e.getMessage());
        }
    }

    @PluginMethod
    public void Test_Pos_SampleTicket_58MM_2(PluginCall call) {
        Log.d("Test_Pos_SampleTicket", "Test_Pos_SampleTicket_58MM_2: ");
        Bitmap bitmap = TestUtils.getPrintBitmap(getContext(), 384, 400);
        AutoReplyPrint.CP_Pos_PrintRasterImageFromData_Helper.PrintRasterImageFromBitmap(h, bitmap.getWidth(), bitmap.getHeight(), bitmap, AutoReplyPrint.CP_ImageBinarizationMethod_Thresholding, AutoReplyPrint.CP_ImageCompressionMethod_None);
        {
            Test_Pos_QueryPrintResult(h);
        }
    }

    @PluginMethod
    public void  Test_Pos_SampleTicket_58MM_1(PluginCall call) {
        try{
            int paperWidth = 384;

            AutoReplyPrint.INSTANCE.CP_Pos_ResetPrinter(h);
            AutoReplyPrint.INSTANCE.CP_Pos_SetMultiByteMode(h);
            AutoReplyPrint.INSTANCE.CP_Pos_SetMultiByteEncoding(h, AutoReplyPrint.CP_MultiByteEncoding_UTF8);

            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "123xxstreet,xxxcity,xxxxstate\r\n");
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_Right);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "TEL 9999-99-9999  C#2\r\n");
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "yyyy-MM-dd HH:mm:ss");
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);

            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "apples");
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, paperWidth - 12 * 6);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "$10.00");
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "grapes");
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, paperWidth - 12 * 6);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "$20.00");
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "bananas");
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, paperWidth - 12 * 6);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "$30.00");
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "lemons");
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, paperWidth - 12 * 6);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "$40.00");
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "oranges");
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, paperWidth - 12 * 7);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "$100.00");
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "Before adding tax");
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, paperWidth - 12 * 7);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "$200.00");
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "tax 5.0%");
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, paperWidth - 12 * 6);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "$10.00");
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            String line = "";
            for (int i = 0; i < paperWidth / 12; ++i)
                line += " ";
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextUnderline(h, 2);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, line);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextUnderline(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextScale(h, 1, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "total");
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, paperWidth - 12 * 2 * 7);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "$190.00");
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextScale(h, 0, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "Customer's payment");
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, paperWidth - 12 * 7);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "$200.00");
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "Change");
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, paperWidth - 12 * 6);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintText(h, "$10.00");
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);

            AutoReplyPrint.INSTANCE.CP_Pos_SetBarcodeHeight(h, 60);
            AutoReplyPrint.INSTANCE.CP_Pos_SetBarcodeUnitWidth(h, 3);
            AutoReplyPrint.INSTANCE.CP_Pos_SetBarcodeReadableTextPosition(h, AutoReplyPrint.CP_Pos_BarcodeTextPrintPosition_BelowBarcode);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintBarcode(h, AutoReplyPrint.CP_Pos_BarcodeType_UPCA, "12345678901");

            AutoReplyPrint.INSTANCE.CP_Pos_Beep(h, 1, 500);

            {
                Test_Pos_QueryPrintResult(h);
            }
            Log.d("TestPosTicket58MM1", "Test_Pos_SampleTicket_58MM_1: try ");
            call.resolve();
        }catch(Exception e){
            e.printStackTrace();
            Log.d("TestPosTicket58MM1", "Test_Pos_SampleTicket_58MM_1:  catch"+e.getMessage());
            call.reject("Test_Pos_SampleTicket_58MM_1:  catch"+e.getMessage());
        }

    }
//    TODO
//    testprint
    @PluginMethod
    public void  Test_Port_Read(PluginCall call) {
        try{
            // send this cmd to query printer status
            byte cmd[] = {0x10, 0x04, 0x01};
            AutoReplyPrint.INSTANCE.CP_Port_SkipAvailable(h);
            if (AutoReplyPrint.INSTANCE.CP_Port_Write(h, cmd, cmd.length, 1000) == cmd.length) {
                byte status[] = new byte[1];
                if (AutoReplyPrint.INSTANCE.CP_Port_Read(h, status, 1, 2000) == 1) {
                    TestUtils.showMessageOnUiThread(getActivity(), String.format("Status 0x%02X", status[0] & 0xff));
                    Log.d("Test_Port_Read", "Test_Port_Read:  try"+String.format("Status 0x%02X", status[0] & 0xff));
                } else {
                    TestUtils.showMessageOnUiThread(getActivity(), "Read failed");
                    Test_Pos_QueryPrintResult(h);
                }
            } else {
                TestUtils.showMessageOnUiThread(getActivity(), "Write failed");
            }
            Log.d("Test_Port_Read", "Test_Port_Read:  try");
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Test_Port_Read", "Test_Port_Read: Catch"+e.getMessage());
            call.reject("Test_Port_Read: Catch"+e.getMessage());
        }


    }

    @PluginMethod
    public void Test_Pos_PrintSelfTestPage(PluginCall call) {
        try{
            boolean result = AutoReplyPrint.INSTANCE.CP_Pos_PrintSelfTestPage(h);
            if (!result){
                TestUtils.showMessageOnUiThread(getActivity(), "Write failed");
                Log.d("PrintSelfTestPage", "Test_Pos_PrintSelfTestPage: Write failed");
            }
         Log.d("PrintSelfTestPage", "Test_Pos_PrintSelfTestPage");
         call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("PrintSelfTestPage", "Test_Pos_PrintSelfTestPage: catch"+e.getMessage());
            call.reject("Test_Pos_PrintSelfTestPage: catch"+e.getMessage());
        }

    }

    @PluginMethod
    public void EnumBle(PluginCall call) {
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
                                        }
                                    }
                            );
                }
            };
            AutoReplyPrint.INSTANCE.CP_Port_EnumBleDevice(20000, cancel, callback, null);
            Log.d("BT4", "EnumBle: try ");
            call.resolve();
        } catch (Exception e) {
            Log.d("BT4", "EnumBle: catch " + e.getMessage());
            call.reject("error");
        }

    }

    @PluginMethod
    public void EnumBt(PluginCall call) {
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
                                        }
                                    }
                            );
                }
            };
            AutoReplyPrint.INSTANCE.CP_Port_EnumBtDevice(12000, cancel, callback, null);
            Log.d("BT2", "EnumBt: try ");
            call.resolve();
        } catch (Exception e) {
            Log.d("BT2", "EnumBt: catch " + e.getMessage());
            call.reject("error");
        }

    }

    @PluginMethod
    public void EnumNet(PluginCall call) {
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
                                        }
                                    }
                            );
                }
            };
            AutoReplyPrint.INSTANCE.CP_Port_EnumNetPrinter(3000, cancel, callback, null);
            Log.d("NET", "NET: try ");
            call.resolve();
        } catch (Exception e) {
            Log.d("NET", "NET: catch " + e.getMessage());
            call.reject("error");
        }

    }

    @PluginMethod
    public void EnumCom(PluginCall call) {
        try {
            String[] devicePaths = AutoReplyPrint.CP_Port_EnumCom_Helper.EnumCom();
            if (devicePaths != null) {
                for (int i = 0; i < devicePaths.length; ++i) {
                    String name = devicePaths[i];
                    if (strCOMPort.trim().isEmpty()) {
                        strCOMPort = name;
                        Log.d("Device COM name", "EnumCom: " + strCOMPort);
                    }
                    Log.d("Device name", "EnumCom: " + name);
                }
            }
            Log.d("COM", "COM: try ");
            call.resolve();
        } catch (Exception e) {
            Log.d("COM", "COM: catch " + e.getMessage());
            call.reject("error");
        }


    }

    @PluginMethod
    public void EnumUsb(PluginCall call) {
        try {
            String[] devicePaths = AutoReplyPrint.CP_Port_EnumUsb_Helper.EnumUsb();
            if (devicePaths != null) {
                for (int i = 0; i < devicePaths.length; ++i) {
                    String name = devicePaths[i];
                    if (strUSBPort.trim().isEmpty()) {
                        strUSBPort = name;
                        Log.d("Device usb name", "EnumUsb: " + strUSBPort);
                    }
                }
            }
            Log.d("USB", "USB: try ");
            call.resolve();
        } catch (Exception e) {
            Log.d("USB", "USB: catch " + e.getMessage());
            call.reject("error");
        }
    }

    @PluginMethod
    public void EnumWiFiP2P(PluginCall call) {
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
                                        }
                                    }
                            );
                }
            };
            AutoReplyPrint.INSTANCE.CP_Port_EnumWiFiP2PDevice(5000, cancel, callback, null);
            Log.d("WIFI", "WIFI: try ");
            call.resolve();
        } catch (Exception e) {
            Log.d("WIFI", "WIFI: catch " + e.getMessage());
            call.reject("error");
        }

    }

    @PluginMethod
    public void OpenPort(PluginCall call) {
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
                    } else if (type.equals("BT4")) {
                        h = AutoReplyPrint.INSTANCE.CP_Port_OpenBtBle(strBT4Address, 1);
                        Log.d("strBT4Address", "run: "+strBT4Address);
                    } else if (type.equals("NET")) {
                        h = AutoReplyPrint.INSTANCE.CP_Port_OpenTcp(null, strNETAddress, (short) 9100, 5000, 1);
                    } else if (type.equals("USB")) {
                        h = AutoReplyPrint.INSTANCE.CP_Port_OpenUsb(strUSBPort, 1);
                    } else if (type.equals("COM")) {
                        h = AutoReplyPrint.INSTANCE.CP_Port_OpenCom(strCOMPort, nBaudTable, AutoReplyPrint.CP_ComDataBits_8, AutoReplyPrint.CP_ComParity_NoParity, AutoReplyPrint.CP_ComStopBits_One, flowControlIndex, 1);
                    } else if (type.equals("WiFi")) {
                        //if (AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_IsConnected())
                        //    AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_Disconnect();
                        int host_address = AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_Connect(strWiFiP2PAddress, 20000);
                        if (host_address != 0) {
                            String host_address_string = String.format("%d.%d.%d.%d", host_address & 0x000000ffl, (host_address & 0x0000ff00l) >> 8, (host_address & 0x00ff0000l) >> 16, (host_address & 0xff000000l) >> 24);
                            h = AutoReplyPrint.INSTANCE.CP_Port_OpenTcp(null, host_address_string, (short) 9100, 5000, 1);
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
        try{
            if (h != Pointer.NULL) {
                AutoReplyPrint.INSTANCE.CP_Port_Close(h);
                h = Pointer.NULL;
            }
            //if (AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_IsConnected())
            //    AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_Disconnect();
            Log.d("ClosePort", "ClosePort: try");
            call.resolve();
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
                                        if (status.ERROR_CUTTER())
                                            error_status_string += "[ERROR_CUTTER]";
                                        if (status.ERROR_FLASH())
                                            error_status_string += "[ERROR_FLASH]";
                                        if (status.ERROR_NOPAPER())
                                            error_status_string += "[ERROR_NOPAPER]";
                                        if (status.ERROR_VOLTAGE())
                                            error_status_string += "[ERROR_VOLTAGE]";
                                        if (status.ERROR_MARKER())
                                            error_status_string += "[ERROR_MARKER]";
                                        if (status.ERROR_ENGINE())
                                            error_status_string += "[ERROR_MOVEMENT]";
                                        if (status.ERROR_OVERHEAT())
                                            error_status_string += "[ERROR_OVERHEAT]";
                                        if (status.ERROR_COVERUP())
                                            error_status_string += "[ERROR_COVERUP]";
                                        if (status.ERROR_MOTOR())
                                            error_status_string += "[ERROR_MOTOR]";
                                    }
                                    String info_status_string = String.format(" Printer Info Status: 0x%04X", printer_info_status & 0xffff);
                                    if (status.INFO_LABELMODE())
                                        info_status_string += "[Label Mode]";
                                    if (status.INFO_LABELPAPER())
                                        info_status_string += "[Label Paper]";
                                    if (status.INFO_PAPERNOFETCH())
                                        info_status_string += "[Paper Not Fetch]";
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RequestCode_RequestAllPermissions:
                if (hasAllPermissions()) {
                } else {
                    Log.d("onPermissionGranted", "onRequestPermissionsResult: ");
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
            return hasLocationPermission && hasCameraPermission && hasStoragePermission;
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

    @Override
    protected void handleRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.handleRequestPermissionsResult(requestCode, permissions, grantResults);

        PluginCall savedCall = getSavedCall();
        if (savedCall == null) {
            return;
        }

        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
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
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH_SCAN)
                    != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                // Request permissions
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_BLUETOOTH_PERMISSIONS_REQUEST_CODE);
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                bridge.getActivity().startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                Log.d("Bluetooth", "enableBluetooth: true");
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                bridge.getActivity().startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                Log.d("Bluetooth", "enableBluetooth: true");
                // Permissions already granted, start Bluetooth scanning
                Log.d("Bluetooth", "Already granted");
            }

        } else {

        }
    }

    public void enableWiFi() {
        WifiManager wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (null != wifiManager) {
            if (!wifiManager.isWifiEnabled()) {
                if (!wifiManager.setWifiEnabled(true)) {
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
                Log.d("Failed", "Please enable location else will not search ble printer");
            }
        }
    }
}

package com.printchris;
import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.sun.jna.Pointer;
import com.caysn.autoreplyprint.AutoReplyPrint;
import com.sun.jna.ptr.IntByReference;
import java.util.Calendar;
import java.util.Date;

@CapacitorPlugin(name = "printPlugin")
public class printPluginPlugin extends Plugin {
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    private Pointer h = Pointer.NULL;
    @PluginMethod
    public void testPluginMethod(PluginCall call) {
        String value = call.getString("msg");
        JSObject ret = new JSObject();
        ret.put("value", value);
        call.resolve(ret);
    }
    @PluginMethod
    public void enableServices(PluginCall call) {
        try {
            if (hasPermissions()) {
                enableBluetooth();
                enableWifi();
                enableLocation();

            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{
                        Manifest.permission.BLUETOOTH,
                        Manifest.permission.BLUETOOTH_ADMIN,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.CHANGE_WIFI_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, PERMISSIONS_REQUEST_CODE);
            }
        Log.d("enableServices", "enableServices: try ");
        call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            call.reject("Failed to enable services: catch" + e.getMessage());
        }

    }
    @PluginMethod
    public void PrintAddCallBack(PluginCall call) {
        try{
            AutoReplyPrint.INSTANCE.CP_Port_AddOnPortOpenedEvent(opened_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Port_AddOnPortOpenFailedEvent(openfailed_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Port_AddOnPortClosedEvent(closed_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Printer_AddOnPrinterStatusEvent(status_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Printer_AddOnPrinterReceivedEvent(received_callback, Pointer.NULL);
            AutoReplyPrint.INSTANCE.CP_Printer_AddOnPrinterPrintedEvent(printed_callback, Pointer.NULL);
            Log.d("TAG", "PrintAddCallBack: ");
            call.resolve();
        }catch (Exception e ){
            e.printStackTrace();
            call.reject("Failed to failed printAddCallback: catch " + e.getMessage());
        }

    }
    @PluginMethod
    public void PrintRemoveCallback(PluginCall call) {
        try{
            AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortOpenedEvent(opened_callback);
            AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortOpenFailedEvent(openfailed_callback);
            AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortClosedEvent(closed_callback);
            AutoReplyPrint.INSTANCE.CP_Printer_RemoveOnPrinterStatusEvent(status_callback);
            AutoReplyPrint.INSTANCE.CP_Printer_RemoveOnPrinterReceivedEvent(received_callback);
            AutoReplyPrint.INSTANCE.CP_Printer_RemoveOnPrinterPrintedEvent(printed_callback);
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            call.reject("failed PrintRemoveCallback: catch"+e.getMessage());
        }

    }
    @PluginMethod
    public void OpenPort(PluginCall call) {
        String Content = call.getString("content");
        Log.d("String Value", "OpenPort: "+Content);
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    assert Content != null;
//                    if (Content.equals("BT2.0")) {
//                            h = AutoReplyPrint.INSTANCE.CP_Port_OpenBtSpp(strBT2Address, 1);
//                        } else if (Content.equals("BT4.0")) {
//                            h = AutoReplyPrint.INSTANCE.CP_Port_OpenBtBle(strBT4Address, 1);
//                        } else if (Content.equals("NET")) {
//                            h = AutoReplyPrint.INSTANCE.CP_Port_OpenTcp(null, strNETAddress, (short) 9100, 5000, 1);
//                        } else if (Content.equals("BSU")) {
//                            h = AutoReplyPrint.INSTANCE.CP_Port_OpenUsb(strUSBPort, 1);
//                        } else if (Content.equals("COM")) {
//                            h = AutoReplyPrint.INSTANCE.CP_Port_OpenCom(strCOMPort, nComBaudrate, AutoReplyPrint.CP_ComDataBits_8, AutoReplyPrint.CP_ComParity_NoParity, AutoReplyPrint.CP_ComStopBits_One, nComFlowControl, 1);
//                        } else if (Content.equals("P2P")) {
//                            //if (AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_IsConnected())
//                            //    AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_Disconnect();
//                            int host_address = AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_Connect(strWiFiP2PAddress, 20000);
//                            if (host_address != 0) {
//                                String host_address_string = String.format("%d.%d.%d.%d", host_address & 0x000000ffl, (host_address & 0x0000ff00l) >> 8, (host_address & 0x00ff0000l) >> 16, (host_address & 0xff000000l) >> 24);
//                                h = AutoReplyPrint.INSTANCE.CP_Port_OpenTcp(null, host_address_string, (short) 9100, 5000, 1);
//                            }
//                        }
                }
            }).start();
            call.resolve();
        }catch (Exception e){
            e.printStackTrace();
            call.reject("failed OpenPort: catch"+e.getMessage());
        }
    }
    @PluginMethod
    public void EnumBle(PluginCall call) {
        try {
            IntByReference cancel = new IntByReference(0);
            AutoReplyPrint.CP_OnBluetoothDeviceDiscovered_Callback callback = new AutoReplyPrint.CP_OnBluetoothDeviceDiscovered_Callback() {
                @Override
                public void CP_OnBluetoothDeviceDiscovered(String deviceName, final String deviceAddress, Pointer privateData) {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("EnumBle", "Discovered device: " + deviceAddress);
                            }
                        });
                    } else {
                        Log.d("EnumBle", "Activity is null");
                    }
                }
            };

            Log.d("EnumBle", "EnumBle: try");
            // Assuming you need to register the callback with some Bluetooth discovery process
            AutoReplyPrint.INSTANCE.CP_Port_EnumBleDevice(20000, cancel, callback, null);
            call.resolve();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error EnumBle", "EnumBle: catch " + e.getMessage());
            call.reject("Failed EnumBle: " + e.getMessage());
        }
    }

    AutoReplyPrint.CP_OnPortOpenedEvent_Callback opened_callback = new AutoReplyPrint.CP_OnPortOpenedEvent_Callback() {
        @Override
        public void CP_OnPortOpenedEvent(Pointer handle, String name, Pointer private_data) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "Open Success", Toast.LENGTH_SHORT).show();
               }
            });
        }
    };
    AutoReplyPrint.CP_OnPortOpenFailedEvent_Callback openfailed_callback = new AutoReplyPrint.CP_OnPortOpenFailedEvent_Callback() {
        @Override
        public void CP_OnPortOpenFailedEvent(Pointer handle, String name, Pointer private_data) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("TAG", "run: ");
                    Toast.makeText(getContext(), "Open Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
    AutoReplyPrint.CP_OnPortClosedEvent_Callback closed_callback = new AutoReplyPrint.CP_OnPortClosedEvent_Callback() {
        @Override
        public void CP_OnPortClosedEvent(Pointer h, Pointer private_data) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    TODO: Close port
//                    ClosePort();
                }
            });
        }
    };
    AutoReplyPrint.CP_OnPrinterStatusEvent_Callback status_callback = new AutoReplyPrint.CP_OnPrinterStatusEvent_Callback() {
        @Override
        public void CP_OnPrinterStatusEvent(Pointer h, final long printer_error_status, final long printer_info_status, Pointer private_data) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Calendar calendar = Calendar.getInstance();
                    Date calendarDate = calendar.getTime();
                    String time = DateFormat.format("yyyy-MM-dd kk:mm:ss", calendarDate).toString();
                    AutoReplyPrint.CP_PrinterStatus status = new AutoReplyPrint.CP_PrinterStatus(printer_error_status, printer_info_status);
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

                    Toast.makeText(getContext(), "Status error"+ error_status_string, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Status info"+info_status_string, Toast.LENGTH_SHORT).show();

                }
            });
        }
    };
    AutoReplyPrint.CP_OnPrinterReceivedEvent_Callback received_callback = new AutoReplyPrint.CP_OnPrinterReceivedEvent_Callback() {
        @Override
        public void CP_OnPrinterReceivedEvent(Pointer h, final int printer_received_byte_count, Pointer private_data) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Calendar calendar = Calendar.getInstance();
                    Date calendarDate = calendar.getTime();
                    String time = DateFormat.format("yyyy-MM-dd kk:mm:ss", calendarDate).toString();
                    Toast.makeText(getContext(), "PrinterReceived"+printer_received_byte_count, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
    AutoReplyPrint.CP_OnPrinterPrintedEvent_Callback printed_callback = new AutoReplyPrint.CP_OnPrinterPrintedEvent_Callback() {
        @Override
        public void CP_OnPrinterPrintedEvent(Pointer h, final int printer_printed_page_id, Pointer private_data) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Calendar calendar = Calendar.getInstance();
                    Date calendarDate = calendar.getTime();
                    String time = DateFormat.format("yyyy-MM-dd kk:mm:ss", calendarDate).toString();
                    Toast.makeText(getContext(), "PrinterReceived" + printer_printed_page_id, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

//    public void AddCallback() {
//        AutoReplyPrint.INSTANCE.CP_Port_AddOnPortOpenedEvent(opened_callback, Pointer.NULL);
//        AutoReplyPrint.INSTANCE.CP_Port_AddOnPortOpenFailedEvent(openfailed_callback, Pointer.NULL);
//        AutoReplyPrint.INSTANCE.CP_Port_AddOnPortClosedEvent(closed_callback, Pointer.NULL);
//        AutoReplyPrint.INSTANCE.CP_Printer_AddOnPrinterStatusEvent(status_callback, Pointer.NULL);
//        AutoReplyPrint.INSTANCE.CP_Printer_AddOnPrinterReceivedEvent(received_callback, Pointer.NULL);
//        AutoReplyPrint.INSTANCE.CP_Printer_AddOnPrinterPrintedEvent(printed_callback, Pointer.NULL);
//        Log.d("addCallback", "AddCallback: ");
//    }
//    public void RemoveCallback() {
//        AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortOpenedEvent(opened_callback);
//        AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortOpenFailedEvent(openfailed_callback);
//        AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortClosedEvent(closed_callback);
//        AutoReplyPrint.INSTANCE.CP_Printer_RemoveOnPrinterStatusEvent(status_callback);
//        AutoReplyPrint.INSTANCE.CP_Printer_RemoveOnPrinterReceivedEvent(received_callback);
//        AutoReplyPrint.INSTANCE.CP_Printer_RemoveOnPrinterPrintedEvent(printed_callback);
//        Log.d("removeCallBack", "RemoveCallback: ");
//    }

    public void ClosePort() {
        if (h != Pointer.NULL) {
            AutoReplyPrint.INSTANCE.CP_Port_Close(h);
            h = Pointer.NULL;
        }
        //if (AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_IsConnected())
        //    AutoReplyPrint.INSTANCE.CP_Port_WiFiP2P_Disconnect();
    }
    public boolean hasPermissions() {
        Context context = getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.checkSelfPermission(Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED &&
                    context.checkSelfPermission(Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED &&
                    context.checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED &&
                    context.checkSelfPermission(Manifest.permission.CHANGE_WIFI_STATE) == PackageManager.PERMISSION_GRANTED &&
                    context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        }
        return true; // For devices below Android 6.0, permissions are granted at installation time
    }

    public void enableBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null && !bluetoothAdapter.isEnabled()) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            } else {
                Log.d("bluetooth", "Failed permission to enable Bluetooth");
                Toast.makeText(getContext(), "Failed permission to enable Bluetooth", Toast.LENGTH_SHORT).show();
            }
            boolean success = bluetoothAdapter.enable();
            if (success) {
                Log.d("bluetooth", "Bluetooth enabled successfully");
                Toast.makeText(getContext(), "Successfully enabled Bluetooth", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("bluetooth", "Failed to enable Bluetooth");
                Toast.makeText(getContext(), "Failed to enable Bluetooth", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.d("bluetooth", "Bluetooth is already enabled");
        }
    }

    public void enableWifi() {
        WifiManager wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null && !wifiManager.isWifiEnabled()) {
            boolean success = wifiManager.setWifiEnabled(true);
            if (success) {
                Log.d("Wifi", "Wi-Fi enabled successfully");
                Toast.makeText(getContext(), "Successfully enabled Wi-Fi", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("Wifi", "Failed to enable Wi-Fi");
                Toast.makeText(getContext(), "Failed to enable Wi-Fi", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.d("Wifi", "Wi-Fi is already enabled");
        }
    }

    public void enableLocation() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d("location", "Location is enabled");
            Toast.makeText(getContext(), "Location is enabled", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("location", "Location is not enabled");
            Toast.makeText(getContext(), "Please enable Location", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == PERMISSIONS_REQUEST_CODE) {
//            for (int result : grantResults) {
//                if (result != PackageManager.PERMISSION_GRANTED) {
//                    break;
//                }
//            }
//        } else {
//            Toast.makeText(getContext(), "Permissions denied", Toast.LENGTH_SHORT).show();
//        }
    }
}

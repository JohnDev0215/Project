package com.byd.vrassistant;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothProfile;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SystemPropertiesUtils;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class PhoneDemoActivity extends AppCompatActivity {
    private static final String BLUE_TOOTH_PROFILE_FIELD = "HEADSET_CLIENT";
    private static final String CALLLOG_URI = "content://com.android.bluetooth/callrecordsprovider";
    private static final String CONTACTS_URI = "content://com.android.bluetooth/contactprovider";
    private static final String SERVICE_CLASS = "com.byd.dialogservice.DialogService";
    private static final String SERVICE_PKG = "com.byd.carsettings";
    private static final String TAG = "BYDPHONE";
    private static final String schemeBluetooth = "byd_pad://byd_car_settings:2933/launcher?activity=Bluetooth";
    private BluetoothAdapter mbluetoothAdapter = null;
    private BluetoothHeadsetClient mbluetoothClient = null;
    private BluetoothDevice mBluetoothDevice = null;
    private BluetoothProfile.ServiceListener serviceListener = new BluetoothProfile.ServiceListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.1
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            PhoneDemoActivity.this.mbluetoothClient = (BluetoothHeadsetClient) bluetoothProfile;
            RLog.d(PhoneDemoActivity.TAG, "onServiceConnected OK");
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            RLog.d(PhoneDemoActivity.TAG, "onServiceDisconnected ");
        }
    };

    private void getConnectStatus(BluetoothProfile.ServiceListener serviceListener) {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!defaultAdapter.isEnabled()) {
                RLog.d(TAG, "adapter disabled");
                return;
            }
            int iIntValue = ((Integer) BluetoothProfile.class.getField(BLUE_TOOTH_PROFILE_FIELD).get(null)).intValue();
            if (defaultAdapter.getProfileConnectionState(iIntValue) == 0) {
                RLog.d(TAG, "getProfileConnectionState disconnected");
            } else {
                defaultAdapter.getProfileProxy(this, serviceListener, iIntValue);
            }
        } catch (Exception e) {
            RLog.e(TAG, "adapter getConnectStatus error", e);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RLog.i(TAG, "onCreate");
        setContentView(R.layout.activity_phone_demo);
        init();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        RLog.i(TAG, "onResume");
        this.mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        RLog.i(TAG, "get BluetoothAdapter");
        getConnectStatus(this.serviceListener);
        RLog.i(TAG, "getConnectStatus -----");
        Set<BluetoothDevice> bondedDevices = this.mbluetoothAdapter.getBondedDevices();
        RLog.i(TAG, "get Bluetooth ConnectDevices");
        if (bondedDevices != null) {
            RLog.d(TAG, "connectDevices = >" + bondedDevices.size());
            int size = bondedDevices.size();
            BluetoothDevice[] bluetoothDeviceArr = new BluetoothDevice[size];
            bondedDevices.toArray(bluetoothDeviceArr);
            if (size > 0) {
                this.mBluetoothDevice = bluetoothDeviceArr[0];
                return;
            }
            return;
        }
        RLog.d(TAG, "connectDevice is none");
    }

    private void init() {
        RLog.d(TAG, "init begin");
        ((Button) findViewById(R.id.btn_openBTPhone)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                ComponentName componentName = new ComponentName(PhoneHelper.BTPHONE_PACKAGE, "com.byd.bluetoothcall.view.activity.MainActivity");
                intent.putExtra("page", 0);
                intent.setComponent(componentName);
                PhoneDemoActivity.this.startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.btn_openBTPhoneSearch)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                ComponentName componentName = new ComponentName(PhoneHelper.BTPHONE_PACKAGE, "com.byd.bluetoothcall.view.activity.MainActivity");
                intent.putExtra("page", 6);
                intent.setComponent(componentName);
                PhoneDemoActivity.this.startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.btn_openBTPhoneConTacts)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                ComponentName componentName = new ComponentName(PhoneHelper.BTPHONE_PACKAGE, "com.byd.bluetoothcall.view.activity.MainActivity");
                intent.putExtra("page", 0);
                intent.setComponent(componentName);
                PhoneDemoActivity.this.startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.btn_openBTPhoneAllHistory)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                ComponentName componentName = new ComponentName(PhoneHelper.BTPHONE_PACKAGE, "com.byd.bluetoothcall.view.activity.MainActivity");
                intent.putExtra("page", 1);
                intent.setComponent(componentName);
                PhoneDemoActivity.this.startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.btn_openBTPhoneMissHistory)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                ComponentName componentName = new ComponentName(PhoneHelper.BTPHONE_PACKAGE, "com.byd.bluetoothcall.view.activity.MainActivity");
                intent.putExtra("page", 2);
                intent.setComponent(componentName);
                PhoneDemoActivity.this.startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.btn_dial)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PhoneDemoActivity.this.mbluetoothClient != null) {
                    PhoneDemoActivity.this.mbluetoothClient.dial(PhoneDemoActivity.this.mBluetoothDevice, "10086");
                } else {
                    RLog.d(PhoneDemoActivity.TAG, "bluetoothClient is null");
                }
            }
        });
        ((Button) findViewById(R.id.btn_hungup)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PhoneDemoActivity.this.mbluetoothClient != null) {
                    PhoneDemoActivity.this.mbluetoothClient.rejectCall(PhoneDemoActivity.this.mBluetoothDevice);
                } else {
                    RLog.d(PhoneDemoActivity.TAG, "bluetoothClient is null");
                }
            }
        });
        ((Button) findViewById(R.id.btn_accept)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PhoneDemoActivity.this.mbluetoothClient != null) {
                    PhoneDemoActivity.this.mbluetoothClient.acceptCall(PhoneDemoActivity.this.mBluetoothDevice, 0);
                } else {
                    RLog.d(PhoneDemoActivity.TAG, "bluetoothClient is null");
                }
            }
        });
        ((Button) findViewById(R.id.btn_enable)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.i(PhoneDemoActivity.TAG, "onClick:蓝牙打开状态");
                if (PhoneDemoActivity.this.mbluetoothAdapter.isEnabled()) {
                    RLog.i(PhoneDemoActivity.TAG, "onClick:蓝牙打开状态 = 蓝牙已打开");
                    Toast.makeText(PhoneDemoActivity.this, "蓝牙已打开", 1).show();
                } else {
                    RLog.i(PhoneDemoActivity.TAG, "onClick:蓝牙打开状态 = 蓝牙未打开");
                    Toast.makeText(PhoneDemoActivity.this, "蓝牙未打开", 1).show();
                }
            }
        });
        ((Button) findViewById(R.id.btn_searchdevice)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.i(PhoneDemoActivity.TAG, "onClick:启动蓝牙设备搜索");
                if (PhoneDemoActivity.this.mbluetoothAdapter.isDiscovering()) {
                    Toast.makeText(PhoneDemoActivity.this, "搜索中。。。。。", 1).show();
                    PhoneDemoActivity.this.mbluetoothAdapter.cancelDiscovery();
                    RLog.i(PhoneDemoActivity.TAG, "onClick:启动蓝牙设备搜索 = 搜索中。。。。。");
                } else {
                    Toast.makeText(PhoneDemoActivity.this, "启动搜索。。。", 1).show();
                    PhoneDemoActivity.this.mbluetoothAdapter.startDiscovery();
                    RLog.i(PhoneDemoActivity.TAG, "onClick:启动蓝牙设备搜索 = 启动搜索。。。");
                }
            }
        });
        ((Button) findViewById(R.id.btn_connectionState)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PhoneDemoActivity.this.mbluetoothClient.getConnectionState(PhoneDemoActivity.this.mBluetoothDevice) == 2) {
                    RLog.i(PhoneDemoActivity.TAG, "onClick:蓝牙链接状态 = 蓝牙已连接");
                    Toast.makeText(PhoneDemoActivity.this, "蓝牙已连接", 1).show();
                } else {
                    RLog.i(PhoneDemoActivity.TAG, "onClick:蓝牙链接状态 = 蓝牙未连接");
                    Toast.makeText(PhoneDemoActivity.this, "蓝牙未连接", 1).show();
                }
            }
        });
        ((Button) findViewById(R.id.btn_openbluebooth)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PhoneDemoActivity.this.mbluetoothAdapter.isEnabled()) {
                    RLog.d(PhoneDemoActivity.TAG, "bluetooth disable...");
                    PhoneDemoActivity.this.mbluetoothAdapter.disable();
                } else {
                    RLog.d(PhoneDemoActivity.TAG, "bluetooth enable...");
                    PhoneDemoActivity.this.mbluetoothAdapter.enable();
                }
            }
        });
        ((Button) findViewById(R.id.btn_openblueboothSet)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(PhoneDemoActivity.schemeBluetooth));
                intent.setComponent(new ComponentName(PhoneDemoActivity.SERVICE_PKG, PhoneDemoActivity.SERVICE_CLASS));
                List<ResolveInfo> listQueryIntentServices = PhoneDemoActivity.this.getPackageManager().queryIntentServices(intent, 0);
                RLog.i(PhoneDemoActivity.TAG, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
                if (listQueryIntentServices.isEmpty()) {
                    return;
                }
                PhoneDemoActivity.this.startService(intent);
            }
        });
        ((Button) findViewById(R.id.btn_blueboothConnect)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        ((Button) findViewById(R.id.btn_phoneConnectState)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        ((Button) findViewById(R.id.btn_loadState)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SystemPropertiesUtils.getInt(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE, -1) == 0) {
                    Toast.makeText(PhoneDemoActivity.this, "通讯录下载中", 1).show();
                } else if (SystemPropertiesUtils.getInt(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE, -1) == 1) {
                    Toast.makeText(PhoneDemoActivity.this, "通讯录下载完成", 1).show();
                } else if (SystemPropertiesUtils.getInt(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE, -1) == 2) {
                    Toast.makeText(PhoneDemoActivity.this, "通讯录下载失败", 1).show();
                } else if (SystemPropertiesUtils.getInt(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE, -1) == 3) {
                    Toast.makeText(PhoneDemoActivity.this, "数据库有更新", 1).show();
                } else {
                    Toast.makeText(PhoneDemoActivity.this, "通讯录无同步权限", 1).show();
                }
                RLog.i(PhoneDemoActivity.TAG, "onClick: 通信录同步状态接口 老方法：" + SystemPropertiesUtils.getInt(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE, -1));
            }
        });
        ((Button) findViewById(R.id.btn_contact_data)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ContentResolver contentResolver = PhoneDemoActivity.this.getContentResolver();
                Uri uri = Uri.parse(PhoneDemoActivity.CONTACTS_URI);
                RLog.i(PhoneDemoActivity.TAG, "uri=" + uri.toString());
                Cursor cursorQuery = contentResolver.query(uri, null, null, null, null);
                RLog.i(PhoneDemoActivity.TAG, "query contacts");
                if (cursorQuery != null) {
                    cursorQuery.getCount();
                    cursorQuery.close();
                } else {
                    RLog.i(PhoneDemoActivity.TAG, " contacts is null");
                }
            }
        });
        ((Button) findViewById(R.id.btn_calllog_data)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.PhoneDemoActivity.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Cursor cursorQuery = PhoneDemoActivity.this.getContentResolver().query(Uri.parse(PhoneDemoActivity.CALLLOG_URI), null, null, null, null);
                cursorQuery.getCount();
                cursorQuery.close();
            }
        });
        RLog.d(TAG, "init end");
    }
}

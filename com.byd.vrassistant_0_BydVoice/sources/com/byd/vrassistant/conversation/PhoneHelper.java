package com.byd.vrassistant.conversation;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothProfile;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.media.AudioManager;
import android.net.Uri;
import android.os.CancellationSignal;
import android.telephony.TelephonyManager;
import cerence.ark.assistant.api.ArkAssistant;
import cerence.ark.assistant.api.dynamic.Contact;
import cerence.ark.assistant.api.dynamic.ContentType;
import cerence.ark.assistant.controller.domain.phone.PhoneRequestsEnum;
import cerence.ark.assistant.controller.enumeration.CallLogTypeEnum;
import cerence.ark.assistant.controller.event.CallEndedEvent;
import cerence.ark.assistant.controller.struct.CallLogInfo;
import cerence.ark.assistant.controller.struct.ContactInfo;
import cerence.ark.assistant.controller.struct.PhoneNumberInfo;
import cerence.ark.assistant.core.dcc.DccConstant;
import cerence.ark.assistant.library.Utils;
import com.blankj.utilcode.util.ActivityUtils;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.receiver.BluetoothReceiver;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.RLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class PhoneHelper {
    private static final String BLUE_TOOTH_PROFILE_FIELD = "HEADSET_CLIENT";
    public static final String BTPHONE_PACKAGE = "com.byd.bluetoothcall";
    public static final String BTPHONE_SETTING_PACKAGE = "com.byd.btsetting";
    public static final String BTPHONE_SETTING_PAGE = "com.byd.btsetting.BluetoothManagerDialog";
    private static final String CALLLOG_URI = "content://com.android.bluetooth/callrecordsprovider";
    private static final String CONTACTS_URI = "content://com.android.bluetooth/contactprovider";
    private static final String[] CONTACT_PROJECTION = {"_id", "display_name"};
    public static final String PHONESTATUS_BTOFF = "BLUETOOTH_OFF";
    public static final String PHONESTATUS_DISCONNECTED = "DISCONNECTED";
    public static final String PHONESTATUS_ERROR_SYNC = "CONTACT_LOADING_ERROR";
    public static final String PHONESTATUS_FUNCTIONOFF = "PHONE_FUNCTION_OFF";
    public static final String PHONESTATUS_LAUNCHFAILED = "PHONE_LAUNCH_FAILED";
    public static final String PHONESTATUS_NOPERMISSION = "NO_PERMISSION_SYNC_CONTACTS";
    public static final String PHONESTATUS_NOSIGNAL = "NO_SIGNAL";
    public static final String PHONESTATUS_OK = "OK";
    public static final String PHONESTATUS_SYNC = "LOADING_CONTACTS";
    private static final String SERVICE_CLASS = "com.byd.dialogservice.DialogService";
    private static final String SERVICE_PKG = "com.byd.carsettings";
    private static final String TAG = "NEU_PHONEHELPER";
    private static final String schemeBluetooth = "byd_pad://byd_car_settings:2933/launcher?activity=Bluetooth";
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;
    public phoneState callState;
    private Boolean isAcceptCallEvent;
    public Boolean isEcallModel;
    private Boolean isMakeCallEvent;
    private Boolean isRejectCallEvent;
    private Boolean isUpDataContactEvent;
    private short mBTRssi;
    public BluetoothDevice mBluetoothDevice;
    private BluetoothReceiver mBluetoothReceiver;
    public List<String> mCallingNumbers;
    private boolean mCallingRing;
    private String mDeviceMac;
    private TelephonyManager mTelephonyManager;
    private Thread mThreadCheckSyncUpdate;
    private String makeCallNumber;
    private BluetoothAdapter mbluetoothAdapter;
    private BluetoothHeadsetClient mbluetoothClient;
    private Runnable runnableSyncUpdate;
    private BluetoothProfile.ServiceListener serviceListener;

    public enum phoneState {
        CALL_STATE_IDLE,
        CALL_STATE_OFFHOOK,
        CALL_STATE_RINGING,
        CALL_STATE_DIALING
    }

    private static final class SingletonHolder {
        private static final PhoneHelper instance = new PhoneHelper();

        private SingletonHolder() {
        }
    }

    public static PhoneHelper getInstance() {
        return SingletonHolder.instance;
    }

    private PhoneHelper() {
        this.mbluetoothAdapter = null;
        this.mbluetoothClient = null;
        this.mBluetoothDevice = null;
        this.mTelephonyManager = null;
        this.mBluetoothReceiver = new BluetoothReceiver();
        this.callState = phoneState.CALL_STATE_IDLE;
        this.isMakeCallEvent = false;
        this.isAcceptCallEvent = false;
        this.isRejectCallEvent = false;
        this.isUpDataContactEvent = false;
        this.isEcallModel = false;
        this.audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.byd.vrassistant.conversation.PhoneHelper.1
            public String toString() {
                return "----VR Audio---";
            }

            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                RLog.i(PhoneHelper.TAG, "----onAudioFocusChange----" + i);
                if (i == -1 || i == -2) {
                    RLog.i(PhoneHelper.TAG, "----AUDIOFOCUS_LOSS");
                } else if (i == 1 || i == 2) {
                    RLog.i(PhoneHelper.TAG, "----AUDIOFOCUS_GAIN");
                }
            }
        };
        this.serviceListener = new BluetoothProfile.ServiceListener() { // from class: com.byd.vrassistant.conversation.PhoneHelper.2
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                PhoneHelper.this.mbluetoothClient = (BluetoothHeadsetClient) bluetoothProfile;
                PhoneHelper.this.setBluetoothDevice();
                RLog.d(PhoneHelper.TAG, "BluetoothProfile onServiceConnected OK");
                if (PhoneHelper.this.isMakeCallEvent.booleanValue()) {
                    try {
                        PhoneHelper phoneHelper = PhoneHelper.this;
                        phoneHelper.makeCall(phoneHelper.makeCallNumber);
                    } catch (Exception unused) {
                        PhoneHelper.this.setCallingState(false);
                    }
                    PhoneHelper.this.isMakeCallEvent = false;
                    PhoneHelper.this.makeCallNumber = "";
                }
                if (PhoneHelper.this.isAcceptCallEvent.booleanValue()) {
                    try {
                        PhoneHelper.this.acceptCall();
                    } catch (Exception unused2) {
                    }
                    PhoneHelper.this.isAcceptCallEvent = false;
                }
                if (PhoneHelper.this.isRejectCallEvent.booleanValue()) {
                    try {
                        PhoneHelper.this.rejectCall();
                    } catch (Exception unused3) {
                    }
                    PhoneHelper.this.isRejectCallEvent = false;
                }
                if (PhoneHelper.this.isUpDataContactEvent.booleanValue()) {
                    try {
                        ArkAssistant.get().notifyContentChanged(ContentType.CONTACT);
                    } catch (Exception unused4) {
                    }
                    PhoneHelper.this.isUpDataContactEvent = false;
                }
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
                RLog.d(PhoneHelper.TAG, "onServiceDisconnected ");
                PhoneHelper.this.mbluetoothClient = null;
            }
        };
        this.mThreadCheckSyncUpdate = null;
        this.runnableSyncUpdate = new Runnable() { // from class: com.byd.vrassistant.conversation.PhoneHelper.3
            @Override // java.lang.Runnable
            public void run() {
                int systemPersistInt;
                RLog.i(PhoneHelper.TAG, "check SyncUpdate");
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    if (i >= 20) {
                        break;
                    }
                    try {
                        Thread.sleep(1000L);
                        systemPersistInt = BYDAutoSysPropertiesDevice.getInstance(BydVoiceApplication.getContext()).getSystemPersistInt(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE);
                    } catch (Exception unused) {
                    }
                    if (systemPersistInt == 3 || systemPersistInt == 1) {
                        RLog.i(PhoneHelper.TAG, "BTPhone Database Update Finish.");
                    } else if (systemPersistInt == 2) {
                        RLog.i(PhoneHelper.TAG, "BTPhone Database download fail.");
                    } else {
                        continue;
                        i = i2;
                    }
                }
                RLog.i(PhoneHelper.TAG, "BTPhone check SyncUpdate timeout");
                ArkAssistant.get().notifyContentChanged(ContentType.CONTACT);
            }
        };
        this.mBTRssi = (short) 0;
        this.mCallingRing = false;
        this.mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        getConnectStatus(this.serviceListener);
        this.mTelephonyManager = (TelephonyManager) BydVoiceApplication.getContext().getSystemService("phone");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        BydVoiceApplication.getContext().registerReceiver(this.mBluetoothReceiver, intentFilter);
        this.mCallingNumbers = new ArrayList();
    }

    public void startCheckSyncUpdate() {
        Thread thread = this.mThreadCheckSyncUpdate;
        if (thread != null && thread.isAlive()) {
            this.mThreadCheckSyncUpdate.interrupt();
        }
        Thread thread2 = new Thread(this.runnableSyncUpdate, "CheckSyncUpdate");
        this.mThreadCheckSyncUpdate = thread2;
        thread2.start();
    }

    public boolean getConnectionState() {
        return this.mbluetoothAdapter.isEnabled() && this.mbluetoothAdapter.getConnectionState() == 2;
    }

    public String checkPhoneStatus() {
        RLog.i(TAG, "enter checkPhoneStatus");
        if (this.mbluetoothAdapter.isEnabled()) {
            RLog.i(TAG, "BT already open");
            if (this.mbluetoothAdapter.getConnectionState() == 2) {
                RLog.i(TAG, "BT already connection");
                if (this.mbluetoothAdapter.getProfileConnectionState(16) == 2) {
                    RLog.i(TAG, "HEADSET_CLIENT ConnectionState OK");
                    int systemPersistInt = BYDAutoSysPropertiesDevice.getInstance(BydVoiceApplication.getContext()).getSystemPersistInt(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE);
                    RLog.i(TAG, "phone sync status =>" + systemPersistInt);
                    if (systemPersistInt == -1) {
                        try {
                            openContactsPage();
                            return "NO_PERMISSION_SYNC_CONTACTS";
                        } catch (Exception e) {
                            RLog.e(TAG, "start phone fail", e);
                            return "PHONE_LAUNCH_FAILED";
                        }
                    }
                    if (systemPersistInt != 0) {
                        if (systemPersistInt != 2) {
                            return "OK";
                        }
                        openContactsPage();
                        return "CONTACT_LOADING_ERROR";
                    }
                    try {
                        openContactsPage();
                        return "LOADING_CONTACTS";
                    } catch (Exception e2) {
                        RLog.e(TAG, "start phone fail", e2);
                        return "PHONE_LAUNCH_FAILED";
                    }
                }
                RLog.i(TAG, "HEADSET_CLIENT ConnectionState DISCONNECTED");
                ForegroundAppUtil.startActivity(BydVoiceApplication.getContext(), BydVoiceApplication.getContext().getPackageManager().getLaunchIntentForPackage(BTPHONE_PACKAGE));
                return "PHONE_FUNCTION_OFF";
            }
            RLog.i(TAG, "BT disconnection");
            openBluetoothSetting();
            return "DISCONNECTED";
        }
        RLog.i(TAG, "BT disabled, open BT");
        try {
            this.mbluetoothAdapter.enable();
            this.mbluetoothAdapter.startDiscovery();
            openBluetoothSetting();
            return "BLUETOOTH_OFF";
        } catch (Exception e3) {
            RLog.e(TAG, "open BT fail", e3);
            return "BLUETOOTH_OFF";
        }
    }

    public List<Contact> readContacts(CancellationSignal cancellationSignal) {
        Cursor cursorQuery;
        RLog.i(TAG, "readContacts");
        if (this.mbluetoothAdapter.isEnabled()) {
            if (this.mbluetoothAdapter.getConnectionState() == 2) {
                int systemPersistInt = BYDAutoSysPropertiesDevice.getInstance(BydVoiceApplication.getContext()).getSystemPersistInt(BYDAutoSysPropertiesDevice.PABP_LOAD_STATE);
                RLog.i(TAG, "readContact syncStatus = " + systemPersistInt);
                if (systemPersistInt == 1 || systemPersistInt == 3) {
                    ArrayList arrayList = new ArrayList();
                    if (!Utils.checkSelfPermission(BydVoiceApplication.getContext(), "android.permission.READ_CONTACTS")) {
                        RLog.i(TAG, "getContacts: the android.permission.READ_CONTACTS was denied.");
                        return null;
                    }
                    ContentResolver contentResolver = BydVoiceApplication.getContext().getContentResolver();
                    Uri uri = Uri.parse(CONTACTS_URI);
                    if (cancellationSignal != null && !cancellationSignal.isCanceled()) {
                        RLog.i(TAG, "cancellationSignal exist");
                        cursorQuery = contentResolver.query(uri, null, null, null, null, cancellationSignal);
                    } else {
                        if (cancellationSignal == null) {
                            RLog.i(TAG, "cancellationSignal is null");
                        } else {
                            RLog.i(TAG, "cancellationSignal is canceled");
                        }
                        cursorQuery = contentResolver.query(uri, null, null, null, null, null);
                    }
                    try {
                        if (cursorQuery != null) {
                            RLog.i(TAG, "getContacts: begin, contact count = " + cursorQuery.getCount());
                            int columnIndex = cursorQuery.getColumnIndex("_id");
                            int columnIndex2 = cursorQuery.getColumnIndex("name");
                            cursorQuery.getColumnIndex("mac");
                            cursorQuery.getColumnIndex("pinyin");
                            setBluetoothDevice();
                            while (cursorQuery.moveToNext()) {
                                Contact contact = new Contact();
                                contact.setId(cursorQuery.getLong(columnIndex));
                                contact.setName(ReplaceString(cursorQuery.getString(columnIndex2)));
                                arrayList.add(contact);
                            }
                        } else {
                            RLog.i(TAG, "getContact is null");
                        }
                    } catch (Exception e) {
                        RLog.i(TAG, "getContact catch：" + e);
                        if (this.mbluetoothClient == null) {
                            this.isUpDataContactEvent = true;
                            connectService();
                        }
                        if (cursorQuery != null) {
                            try {
                                cursorQuery.close();
                            } catch (Throwable unused) {
                            }
                        }
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return arrayList;
                }
                RLog.i(TAG, "bluetooth.load_state error");
                return null;
            }
            RLog.i(TAG, "BT phone disconnection");
            return null;
        }
        RLog.i(TAG, "BT disEnabled");
        return null;
    }

    public Map<Long, List<String>> readContactPhone(List<Long> list) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Cursor cursorQuery = BydVoiceApplication.getContext().getContentResolver().query(Uri.parse(CONTACTS_URI), new String[]{"_id", "number"}, null, null, null);
        RLog.i(TAG, "readContactPhone: phoneCursor.count =" + cursorQuery.getCount());
        while (cursorQuery.moveToNext()) {
            long j = cursorQuery.getLong(cursorQuery.getColumnIndex("_id"));
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("number"));
            if (list.contains(Long.valueOf(j))) {
                List arrayList = (List) concurrentHashMap.get(Long.valueOf(j));
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                if (string.contains("and")) {
                    RLog.i(TAG, "contactInfo : contains and");
                    arrayList.addAll(Arrays.asList(string.split("and")));
                } else {
                    RLog.i(TAG, "contactInfo : no and");
                    arrayList.add(string);
                }
                concurrentHashMap.put(Long.valueOf(j), arrayList);
            }
        }
        cursorQuery.close();
        return concurrentHashMap;
    }

    public List<PhoneNumberInfo> readContactPhone(long j) {
        RLog.i(TAG, "enter readContactPhone + contactID:" + j);
        Cursor cursorQuery = BydVoiceApplication.getContext().getContentResolver().query(Uri.parse(CONTACTS_URI), null, null, null, null);
        ArrayList arrayList = new ArrayList();
        RLog.i(TAG, "readContactPhone: phoneCursor.count =" + cursorQuery.getCount());
        while (cursorQuery.moveToNext()) {
            long j2 = cursorQuery.getLong(cursorQuery.getColumnIndex("_id"));
            cursorQuery.getString(cursorQuery.getColumnIndex("name"));
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("number"));
            if (j == j2) {
                RLog.i(TAG, "contactInfo : phoneID =" + j2);
                if (string.contains("and")) {
                    RLog.i(TAG, "contactInfo : contains and");
                    for (String str : string.split("and")) {
                        PhoneNumberInfo phoneNumberInfo = new PhoneNumberInfo();
                        phoneNumberInfo.setNumber(str);
                        arrayList.add(phoneNumberInfo);
                    }
                } else {
                    RLog.i(TAG, "contactInfo : no and");
                    PhoneNumberInfo phoneNumberInfo2 = new PhoneNumberInfo();
                    phoneNumberInfo2.setNumber(string);
                    arrayList.add(phoneNumberInfo2);
                }
            }
        }
        cursorQuery.close();
        return arrayList;
    }

    public ContactInfo readContactWithId(long j) {
        RLog.i(TAG, "enter readContactWithId + contactID:" + j);
        Cursor cursorQuery = BydVoiceApplication.getContext().getContentResolver().query(Uri.parse(CONTACTS_URI), null, "_id=" + j, null, null);
        ContactInfo contactInfo = new ContactInfo();
        if (cursorQuery.getCount() <= 0) {
            return null;
        }
        while (cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("name"));
            contactInfo.setContactId(cursorQuery.getLong(cursorQuery.getColumnIndex("_id")));
            contactInfo.setContactName(string);
            RLog.i(TAG, "contact.id =" + contactInfo.getContactId());
        }
        cursorQuery.close();
        return contactInfo;
    }

    public void acceptCall() throws Exception {
        if (SystemSettingManager.get().isCPConnect()) {
            RLog.i(TAG, "isCPConnected");
            if (SystemSettingManager.get().isCarplayCallRinging()) {
                RLog.i(TAG, "isCarplayCallRinging");
                if (SystemSettingManager.get().getCarPlayAppManager() != null) {
                    SystemSettingManager.get().getCarPlayAppManager().acceptCall(0, "");
                    RLog.i(TAG, "getCarPlayAppManager().acceptCall");
                    return;
                }
            }
        }
        if (!setBluetoothDevice()) {
            RLog.d(TAG, "connectDevice is none");
            throw new Exception("acceptCall fail，connectDevice is none");
        }
        if (this.mbluetoothClient != null) {
            if (this.callState == phoneState.CALL_STATE_RINGING) {
                RLog.i(TAG, "acceptCall...");
                this.mbluetoothClient.acceptCall(this.mBluetoothDevice, 0);
                return;
            } else {
                RLog.i(TAG, "CallState = " + phoneState.CALL_STATE_RINGING);
                throw new Exception("call state is not ringring, not acceptCall");
            }
        }
        RLog.d(TAG, "bluetoothClient is null");
        this.isAcceptCallEvent = true;
        getConnectStatus(this.serviceListener);
    }

    public void rejectCall() throws Exception {
        if (SystemSettingManager.get().isCPConnect()) {
            RLog.i(TAG, "isCPConnected");
            if (SystemSettingManager.get().isCarplayCallRinging()) {
                RLog.i(TAG, "isCarplayCallRinging");
                if (SystemSettingManager.get().getCarPlayAppManager() != null) {
                    RLog.i(TAG, "getCarPlayAppManager().endCall");
                    SystemSettingManager.get().getCarPlayAppManager().endCall(0, "");
                    return;
                }
            }
        }
        if (!setBluetoothDevice()) {
            RLog.d(TAG, "connectDevice is none");
            throw new Exception("rejectCall fail，connectDevice is none");
        }
        if (this.mbluetoothClient != null) {
            if (this.callState == phoneState.CALL_STATE_RINGING) {
                RLog.i(TAG, "rejectCall...");
                this.mbluetoothClient.rejectCall(this.mBluetoothDevice);
                return;
            } else {
                RLog.i(TAG, "CallState = " + phoneState.CALL_STATE_RINGING);
                throw new Exception("call state is not ringing, not rejectCall");
            }
        }
        RLog.d(TAG, "bluetoothClient is null");
        this.isRejectCallEvent = true;
        getConnectStatus(this.serviceListener);
    }

    private void openBluetoothSetting() {
        RLog.i(TAG, "openBluetoothSetting");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(schemeBluetooth));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = BydVoiceApplication.getContext().getPackageManager().queryIntentServices(intent, 0);
        RLog.i(TAG, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        BydVoiceApplication.getContext().startService(intent);
    }

    public void connectService() {
        if (this.mbluetoothClient == null) {
            RLog.i(TAG, "mbluetoothClient == null");
            getConnectStatus(this.serviceListener);
        } else {
            RLog.i(TAG, "mbluetoothClient != null");
        }
    }

    private void getConnectStatus(BluetoothProfile.ServiceListener serviceListener) {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!defaultAdapter.isEnabled()) {
                RLog.d(TAG, "adapter disabled");
            } else {
                defaultAdapter.getProfileProxy(BydVoiceApplication.getContext(), serviceListener, 16);
                RLog.d(TAG, "adapter 建立与代理的连接");
            }
        } catch (Exception unused) {
            RLog.e(TAG, "adapter getConnectStatus error");
        }
    }

    public void makeCall(String str) throws Exception {
        RLog.i(TAG, PhoneRequestsEnum.MAKE_CALL);
        if (this.mbluetoothClient != null) {
            if (!setBluetoothDevice()) {
                RLog.d(TAG, "connectDevice is none");
                throw new Exception("makeCall fail，connectDevice is none");
            }
            try {
                this.mbluetoothClient.dial(this.mBluetoothDevice, str);
                RLog.d(TAG, "mbluetoothClient.dial == end");
                setCallingState(true);
                return;
            } catch (Exception unused) {
                setCallingState(false);
                RLog.d(TAG, "mbluetoothClient.dial fail");
                throw new Exception("makeCall--mbluetoothClient.dial fail，");
            }
        }
        RLog.d(TAG, "bluetoothClient is null");
        this.isMakeCallEvent = true;
        this.makeCallNumber = str;
        getConnectStatus(this.serviceListener);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public List<CallLogInfo> getCallLog(String str) {
        byte b;
        String[] strArr;
        String str2;
        String str3;
        String str4;
        String str5;
        RLog.i(TAG, PhoneRequestsEnum.GET_CALL_LOG);
        RLog.i(TAG, "callLogType :" + str);
        ArrayList arrayList = new ArrayList();
        ContentResolver contentResolver = BydVoiceApplication.getContext().getContentResolver();
        str.hashCode();
        int iHashCode = str.hashCode();
        String str6 = CallLogTypeEnum.MISSED_CALL_LOG;
        String str7 = CallLogTypeEnum.INCOMING_CALL_LOG;
        String str8 = CallLogTypeEnum.OUTGOING_CALL_LOG;
        switch (iHashCode) {
            case -28012234:
                b = !str.equals(CallLogTypeEnum.OUTGOING_CALL_LOG) ? (byte) -1 : (byte) 0;
                break;
            case 699563452:
                b = !str.equals(CallLogTypeEnum.INCOMING_CALL_LOG) ? (byte) -1 : (byte) 1;
                break;
            case 1900806887:
                b = !str.equals(CallLogTypeEnum.MISSED_CALL_LOG) ? (byte) -1 : (byte) 2;
                break;
            default:
                b = -1;
                break;
        }
        String str9 = "type = ?";
        switch (b) {
            case 0:
                strArr = new String[]{"DIALED"};
                break;
            case 1:
                strArr = new String[]{"RECEIVED", "MISSED"};
                str9 = "type = ? or type = ?";
                break;
            case 2:
                strArr = new String[]{"MISSED"};
                break;
            default:
                strArr = new String[]{"MISSED", "DIALED", "RECEIVED"};
                str9 = "type = ? or type = ? or type = ?";
                break;
        }
        Cursor cursorQuery = contentResolver.query(Uri.parse(CALLLOG_URI), null, str9, strArr, "time DESC");
        RLog.i(TAG, "CallLogCount :" + cursorQuery.getCount());
        setBluetoothDevice();
        while (cursorQuery.moveToNext()) {
            ContactInfo contactInfo = new ContactInfo();
            PhoneNumberInfo phoneNumberInfo = new PhoneNumberInfo();
            CallLogInfo callLogInfo = new CallLogInfo();
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("name"));
            RLog.i(TAG, "----------------begin-------------------");
            str2 = str7;
            long j = cursorQuery.getLong(cursorQuery.getColumnIndex("_id"));
            RLog.i(TAG, "LogId:" + j);
            String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("number"));
            str3 = str8;
            long j2 = cursorQuery.getLong(cursorQuery.getColumnIndex("time"));
            str4 = str6;
            ArrayList arrayList2 = arrayList;
            RLog.i(TAG, "time:" + j2);
            String string3 = cursorQuery.getString(cursorQuery.getColumnIndex("type"));
            Cursor cursor = cursorQuery;
            RLog.i(TAG, "CallLogType =" + string3);
            RLog.i(TAG, "-------------------end-------------------");
            string3.hashCode();
            switch (string3) {
                case "MISSED":
                    str5 = str4;
                    break;
                case "RECEIVED":
                    str5 = str2;
                    break;
                case "DIALED":
                    str5 = str3;
                    break;
                default:
                    str5 = CallLogTypeEnum.ALL_CALL_LOG;
                    break;
            }
            if (string.equals(string2)) {
                string = "";
            }
            contactInfo.setContactName(string);
            phoneNumberInfo.setNumber(string2);
            callLogInfo.setCallLogId(j);
            callLogInfo.setContact(contactInfo);
            callLogInfo.setTimestamp(j2);
            callLogInfo.setPhoneNumber(phoneNumberInfo);
            callLogInfo.setCallLogType(str5);
            arrayList = arrayList2;
            arrayList.add(callLogInfo);
            cursorQuery = cursor;
            str7 = str2;
            str8 = str3;
            str6 = str4;
        }
        cursorQuery.close();
        return arrayList;
    }

    public void openContactsPage() {
        RLog.i(TAG, "enter  openContactsPage");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        ComponentName componentName = new ComponentName(BTPHONE_PACKAGE, "com.byd.bluetoothcall.view.activity.MainActivity");
        intent.putExtra("page", 0);
        intent.setComponent(componentName);
        ActivityUtils.startActivity(intent);
    }

    public void openContactPageWithName(String str) {
        RLog.i(TAG, "enter  openContactPageWithName name ");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        ComponentName componentName = new ComponentName(BTPHONE_PACKAGE, "com.byd.bluetoothcall.view.activity.MainActivity");
        intent.putExtra("page", 6);
        intent.putExtra("vr_search_name", str);
        intent.setComponent(componentName);
        ActivityUtils.startActivity(intent);
    }

    public void openBTPhonePage() {
        RLog.i(TAG, "enter  openBTPhonePage");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        ComponentName componentName = new ComponentName(BTPHONE_PACKAGE, "com.byd.bluetoothcall.view.activity.MainActivity");
        intent.putExtra("page", 0);
        intent.setComponent(componentName);
        ActivityUtils.startActivity(intent);
    }

    public void openCallLogPage() {
        RLog.i(TAG, "enter  openCallLogPage");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        ComponentName componentName = new ComponentName(BTPHONE_PACKAGE, "com.byd.bluetoothcall.view.activity.MainActivity");
        intent.putExtra("page", 1);
        intent.setComponent(componentName);
        ActivityUtils.startActivity(intent);
    }

    public void openMissCallPage() {
        RLog.i(TAG, "enter  openMissCallPage");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        ComponentName componentName = new ComponentName(BTPHONE_PACKAGE, "com.byd.bluetoothcall.view.activity.MainActivity");
        intent.putExtra("page", 2);
        intent.setComponent(componentName);
        ActivityUtils.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setBluetoothDevice() {
        Set<BluetoothDevice> bondedDevices = this.mbluetoothAdapter.getBondedDevices();
        if (bondedDevices != null) {
            RLog.d(TAG, "connectDevices = >" + bondedDevices.size());
            RLog.i(TAG, "setBluetoothDevice: devices.count =" + bondedDevices.size());
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                if (this.mbluetoothClient.getConnectionState(bluetoothDevice) == 2) {
                    this.mBluetoothDevice = bluetoothDevice;
                    this.mDeviceMac = bluetoothDevice.getAddress();
                    RLog.d(TAG, "connectDevice ok");
                    return true;
                }
                RLog.d(TAG, "connectDevice no STATE_CONNECTED");
            }
            return false;
        }
        RLog.d(TAG, "connectDevice is none");
        return false;
    }

    public short getBTRssi() {
        return this.mBTRssi;
    }

    public void setBTRssi(short s) {
        this.mBTRssi = s;
    }

    public void unregisterBTReceiver() {
        BydVoiceApplication.getContext().unregisterReceiver(this.mBluetoothReceiver);
    }

    public void setCallingState(boolean z) {
        this.mCallingRing = z;
        if (z) {
            return;
        }
        RLog.d(TAG, "挂断电话，通知语音恢复");
        ArkAssistant.get().sendAppEvent(new CallEndedEvent.Builder().build(), null);
    }

    public boolean checkCommandPhoneCarplay() {
        if (SystemSettingManager.get().isCPConnect()) {
            RLog.i(TAG, "isCPConnected");
            if (SystemSettingManager.get().isCarplayCallRinging()) {
                RLog.i(TAG, "isCarplayCallRinging");
                return true;
            }
        }
        return false;
    }

    public boolean checkCommandPhone() {
        return this.mCallingRing || checkCommandPhoneCarplay();
    }

    public phoneState getCallState() {
        return this.callState;
    }

    private String ReplaceString(String str) {
        return str.replace(DccConstant.SEPARATOR, " ").replace(";", " ");
    }
}

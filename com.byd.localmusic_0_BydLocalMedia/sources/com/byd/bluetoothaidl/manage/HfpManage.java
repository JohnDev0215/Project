package com.byd.bluetoothaidl.manage;

import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.byd.bluetoothaidl.listener.UIListener_Call;
import com.byd.bluetoothaidl.listener.UIListener_Connect;
import com.byd.bluetoothaidl.listener.UIListener_Muisc;
import com.byd.bluetoothaidl.listener.UIListener_PbapSync;
import com.byd.bluetoothservice.IBTServiceCallBack;
import com.byd.bluetoothservice.IBluetoothInterface;
import com.byd.info.BtCallInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class HfpManage {
    private static volatile HfpManage instance;
    private IBluetoothInterface mBTService;
    private Context mContext;
    private final String TAG = HfpManage.class.getSimpleName();
    private String mPkg_name = "";
    private boolean mCallingKeyBoardShow = false;
    private volatile boolean isServiceConnect = false;
    private ArrayList<UIListener_Muisc> mGroupMusicUIListener = new ArrayList<>();
    private ArrayList<UIListener_Connect> mGroupConnectUIListener = new ArrayList<>();
    private ArrayList<UIListener_Call> mGroupCallUIListener = new ArrayList<>();
    private ArrayList<UIListener_PbapSync> mGroupSyncListener = new ArrayList<>();
    private final int A2DP_STATE_CHANGE = 100001;
    private final int BATTERY_CHANGE = 100002;
    private final int MUSIC_TITLE_CHANGE = 100003;
    private final int TOTAL_TIME_CHANGE = 100004;
    private final int CURRENT_TIME_CHANGE = 100005;
    private final int MUSIC_PLAY_STATE_CHANGE = 100006;
    private final int HFP_STATE_CHANGE = 100007;
    private final int CALL_STATE_CHANGE = 100008;
    private final int CALL_TIME_CHANGE = 100009;
    private final int CALL_SCO_CHANGE = 100010;
    private final int APPWIDGET_CALLING_KEYBOARD_SHOW = 100011;
    private final int SYNC_STATE = 100012;
    private final int BIND_BT_SERVICE = 100013;
    private Handler mHandler = new Handler(Looper.myLooper()) { // from class: com.byd.bluetoothaidl.manage.HfpManage.1
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            int i = 0;
            switch (message.what) {
                case 100001:
                    int i2 = message.arg1;
                    while (i < HfpManage.this.mGroupMusicUIListener.size()) {
                        ((UIListener_Muisc) HfpManage.this.mGroupMusicUIListener.get(i)).updateA2dpConnectState(i2);
                        i++;
                    }
                    return;
                case 100002:
                    int i3 = message.arg1;
                    while (i < HfpManage.this.mGroupMusicUIListener.size()) {
                        ((UIListener_Muisc) HfpManage.this.mGroupMusicUIListener.get(i)).updateBattery(i3);
                        i++;
                    }
                    return;
                case 100003:
                    Bundle data = message.getData();
                    String string = data.getString("title");
                    String string2 = data.getString("artist");
                    String string3 = data.getString("album");
                    String string4 = data.getString("iconUrl");
                    while (i < HfpManage.this.mGroupMusicUIListener.size()) {
                        ((UIListener_Muisc) HfpManage.this.mGroupMusicUIListener.get(i)).updateTitle(string, string2, string3, string4);
                        i++;
                    }
                    return;
                case 100004:
                    long jLongValue = ((Long) message.obj).longValue();
                    while (i < HfpManage.this.mGroupMusicUIListener.size()) {
                        ((UIListener_Muisc) HfpManage.this.mGroupMusicUIListener.get(i)).updateTotalTime(jLongValue);
                        i++;
                    }
                    return;
                case 100005:
                    long jLongValue2 = ((Long) message.obj).longValue();
                    while (i < HfpManage.this.mGroupMusicUIListener.size()) {
                        ((UIListener_Muisc) HfpManage.this.mGroupMusicUIListener.get(i)).updateCurrentTime(jLongValue2);
                        i++;
                    }
                    return;
                case 100006:
                    int i4 = message.arg1;
                    while (i < HfpManage.this.mGroupMusicUIListener.size()) {
                        ((UIListener_Muisc) HfpManage.this.mGroupMusicUIListener.get(i)).updatePlayState(i4);
                        i++;
                    }
                    return;
                case 100007:
                    int i5 = message.arg1;
                    while (i < HfpManage.this.mGroupConnectUIListener.size()) {
                        ((UIListener_Connect) HfpManage.this.mGroupConnectUIListener.get(i)).updateHFPConnectState(i5);
                        i++;
                    }
                    return;
                case 100008:
                    List<BtCallInfo> list = (List) message.obj;
                    while (i < HfpManage.this.mGroupCallUIListener.size()) {
                        ((UIListener_Call) HfpManage.this.mGroupCallUIListener.get(i)).CallState_Change(list);
                        i++;
                    }
                    return;
                case 100009:
                case 100010:
                case 100011:
                default:
                    return;
                case 100012:
                    break;
                case 100013:
                    HfpManage hfpManage = HfpManage.this;
                    if (hfpManage.BindBTService(hfpManage.mPkg_name)) {
                        return;
                    }
                    HfpManage.this.mHandler.sendEmptyMessageDelayed(100013, 800L);
                    return;
            }
            while (i < HfpManage.this.mGroupSyncListener.size()) {
                ((UIListener_PbapSync) HfpManage.this.mGroupSyncListener.get(i)).SyncContactsState(message.arg1);
                i++;
            }
        }
    };
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.byd.bluetoothaidl.manage.HfpManage.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            HfpManage.this.mBTService = IBluetoothInterface.Stub.asInterface(iBinder);
            int i = 1;
            HfpManage.this.isServiceConnect = true;
            Log.d("DANDAN", "onServiceConnected======");
            try {
                HfpManage.this.mBTService.registerBTServiceCallBack(HfpManage.this.mServiceCallBack);
                Message messageObtainMessage = HfpManage.this.mHandler.obtainMessage();
                messageObtainMessage.what = 100001;
                if (!HfpManage.this.mBTService.isA2DPConnect()) {
                    i = 0;
                }
                messageObtainMessage.arg1 = i;
                HfpManage.this.mHandler.sendMessage(messageObtainMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                Log.d("DANDAN", "onServiceDisconnected======");
                HfpManage.this.isServiceConnect = false;
                HfpManage.this.mBTService = null;
                HfpManage.this.mHandler.sendEmptyMessageDelayed(100013, 800L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private IBTServiceCallBack.Stub mServiceCallBack = new IBTServiceCallBack.Stub() { // from class: com.byd.bluetoothaidl.manage.HfpManage.3
        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void BlueSwitch(int i) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void Call_Activity_is_Top(boolean z) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updateHFPConnectState(int i) throws RemoteException {
            Message messageObtainMessage = HfpManage.this.mHandler.obtainMessage();
            messageObtainMessage.what = 100007;
            messageObtainMessage.arg1 = i;
            HfpManage.this.mHandler.sendMessage(messageObtainMessage);
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void CallState_Change(List<BtCallInfo> list) throws RemoteException {
            Log.d("DANDAN", "CallState_Change ======================" + list.size());
            Message messageObtainMessage = HfpManage.this.mHandler.obtainMessage();
            messageObtainMessage.what = 100008;
            messageObtainMessage.obj = list;
            HfpManage.this.mHandler.sendMessage(messageObtainMessage);
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void Update_Call_Time(BtCallInfo btCallInfo) throws RemoteException {
            Message messageObtainMessage = HfpManage.this.mHandler.obtainMessage();
            messageObtainMessage.what = 100009;
            messageObtainMessage.obj = btCallInfo;
            HfpManage.this.mHandler.sendMessage(messageObtainMessage);
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void Update_Call_Channel(int i) throws RemoteException {
            Message messageObtainMessage = HfpManage.this.mHandler.obtainMessage();
            messageObtainMessage.what = 100010;
            messageObtainMessage.arg1 = i;
            HfpManage.this.mHandler.sendMessage(messageObtainMessage);
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void AppWidgetKeyBoardShow(boolean z) throws RemoteException {
            HfpManage.this.mCallingKeyBoardShow = z;
            Message messageObtainMessage = HfpManage.this.mHandler.obtainMessage();
            messageObtainMessage.what = 100011;
            messageObtainMessage.obj = Boolean.valueOf(z);
            HfpManage.this.mHandler.sendMessage(messageObtainMessage);
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updateA2dpConnectState(int i) throws RemoteException {
            Message messageObtainMessage = HfpManage.this.mHandler.obtainMessage();
            messageObtainMessage.what = 100001;
            messageObtainMessage.arg1 = i;
            HfpManage.this.mHandler.sendMessage(messageObtainMessage);
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updateBattery(int i) throws RemoteException {
            Message messageObtainMessage = HfpManage.this.mHandler.obtainMessage();
            messageObtainMessage.what = 100002;
            messageObtainMessage.arg1 = i;
            HfpManage.this.mHandler.sendMessage(messageObtainMessage);
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updateTitle(String str, String str2, String str3, String str4) throws RemoteException {
            Message messageObtainMessage = HfpManage.this.mHandler.obtainMessage();
            Bundle bundle = new Bundle();
            messageObtainMessage.what = 100003;
            bundle.putString("title", str);
            bundle.putString("artist", str2);
            bundle.putString("album", str3);
            bundle.putString("iconUrl", str4);
            messageObtainMessage.setData(bundle);
            HfpManage.this.mHandler.sendMessage(messageObtainMessage);
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updateTotalTime(long j) throws RemoteException {
            Message messageObtainMessage = HfpManage.this.mHandler.obtainMessage();
            messageObtainMessage.what = 100004;
            messageObtainMessage.obj = Long.valueOf(j);
            HfpManage.this.mHandler.sendMessage(messageObtainMessage);
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updateCurrentTime(long j) throws RemoteException {
            Message messageObtainMessage = HfpManage.this.mHandler.obtainMessage();
            messageObtainMessage.what = 100005;
            messageObtainMessage.obj = Long.valueOf(j);
            HfpManage.this.mHandler.sendMessage(messageObtainMessage);
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updatePlayState(int i) throws RemoteException {
            Message messageObtainMessage = HfpManage.this.mHandler.obtainMessage();
            messageObtainMessage.what = 100006;
            messageObtainMessage.arg1 = i;
            HfpManage.this.mHandler.sendMessage(messageObtainMessage);
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void SyncContactsState(int i) throws RemoteException {
            Log.d("DANDAN", "SyncContactsState===================================== = state = " + i);
            Message messageObtainMessage = HfpManage.this.mHandler.obtainMessage();
            messageObtainMessage.what = 100012;
            messageObtainMessage.arg1 = i;
            HfpManage.this.mHandler.sendMessage(messageObtainMessage);
        }
    };

    public static HfpManage getInstance() {
        if (instance == null) {
            synchronized (HfpManage.class) {
                if (instance == null) {
                    instance = new HfpManage();
                }
            }
        }
        return instance;
    }

    public void init(Context context, String str) {
        this.mContext = context;
        this.mPkg_name = str;
        this.mHandler.sendEmptyMessage(100013);
    }

    public void destroy() {
        try {
            this.mBTService.unRegisterBTServiceCallBack(this.mServiceCallBack);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.mContext.unbindService(this.mServiceConnection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean BindBTService(String str) {
        Log.d("DANDAN", "BindBTService ============");
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setAction("com.byd.bluetoothservice.BTService");
        return this.mContext.bindService(intent, this.mServiceConnection, 1);
    }

    public int getCallState() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return 0;
            }
            return this.mBTService.getCallState();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void WidgetCardShow(boolean z) {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return;
            }
            this.mBTService.WidgetCardShow(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void BT_Card_Slides_up() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return;
            }
            this.mBTService.BT_Card_Slides_up();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean ismCallingKeyBoardShow() {
        return this.mCallingKeyBoardShow;
    }

    public boolean getBluetoothEnable() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return false;
            }
            return this.mBTService.getBluetoothEnable();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setBluetoothEnable(boolean z) {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return;
            }
            this.mBTService.setBluetoothEnable(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isHFPConnect() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return false;
            }
            return this.mBTService.isHFPConnect();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isA2DPConnect() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return false;
            }
            return this.mBTService.isA2DPConnect();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getBluetoothName() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return null;
            }
            return this.mBTService.getBluetoothName();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getHFPConnectDeviceName() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return null;
            }
            return this.mBTService.getHFPConnectDeviceName();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BluetoothDevice getHFPConnectDevice() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return null;
            }
            return this.mBTService.getHFPConnectDevice();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getA2dpConnectDeviceName() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return null;
            }
            return this.mBTService.getA2dpConnectDeviceName();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BluetoothDevice getA2dpConnectDevice() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return null;
            }
            return this.mBTService.getA2dpConnectDevice();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getBattery() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return -1;
            }
            return this.mBTService.getBattery();
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getmMusicTile() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return null;
            }
            return this.mBTService.getMusicTile();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getArtist() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return null;
            }
            return this.mBTService.getArtist();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIconUrl() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return null;
            }
            return this.mBTService.getIconUrl();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getmPlay_state() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return 0;
            }
            return this.mBTService.getPlay_state();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public long getmTotal_time() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return 0L;
            }
            return this.mBTService.getTotal_time();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public long getCurrentTime() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return 0L;
            }
            return this.mBTService.getCurrentTime();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public void Play_Music() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return;
            }
            this.mBTService.Play_Music();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void Pause_Music() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return;
            }
            this.mBTService.Pause_Music();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void Play_or_Pause() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return;
            }
            this.mBTService.Play_or_Pause();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void Pre_Music() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return;
            }
            this.mBTService.Pre_Music();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void Next_Music() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return;
            }
            this.mBTService.Next_Music();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void StartSyncContacts() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return;
            }
            this.mBTService.StartSyncContacts();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getSyncContactsState() {
        try {
            if (!this.isServiceConnect || this.mBTService == null) {
                return 0;
            }
            return this.mBTService.getSyncContactsState();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void registerUIListener_Music(UIListener_Muisc uIListener_Muisc) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.mGroupMusicUIListener.size()) {
                break;
            }
            if (this.mGroupMusicUIListener.get(i) == uIListener_Muisc) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return;
        }
        this.mGroupMusicUIListener.add(uIListener_Muisc);
    }

    public void unRegisterMusicUIListener(UIListener_Muisc uIListener_Muisc) {
        for (int i = 0; i < this.mGroupMusicUIListener.size(); i++) {
            if (this.mGroupMusicUIListener.get(i) == uIListener_Muisc) {
                this.mGroupMusicUIListener.remove(i);
                return;
            }
        }
    }

    public void registerUIListener_Connect(UIListener_Connect uIListener_Connect) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.mGroupConnectUIListener.size()) {
                break;
            }
            if (this.mGroupConnectUIListener.get(i) == uIListener_Connect) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return;
        }
        this.mGroupConnectUIListener.add(uIListener_Connect);
    }

    public void unRegisterConnectUIListener(UIListener_Connect uIListener_Connect) {
        for (int i = 0; i < this.mGroupConnectUIListener.size(); i++) {
            if (this.mGroupConnectUIListener.get(i) == uIListener_Connect) {
                this.mGroupConnectUIListener.remove(i);
                return;
            }
        }
    }

    public void registerUIListener_Call(UIListener_Call uIListener_Call) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.mGroupCallUIListener.size()) {
                break;
            }
            if (this.mGroupCallUIListener.get(i) == uIListener_Call) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return;
        }
        this.mGroupCallUIListener.add(uIListener_Call);
    }

    public void unRegisterCallUIListener(UIListener_Call uIListener_Call) {
        for (int i = 0; i < this.mGroupCallUIListener.size(); i++) {
            if (this.mGroupCallUIListener.get(i) == uIListener_Call) {
                this.mGroupCallUIListener.remove(i);
                return;
            }
        }
    }

    public void registSyncUIListener(UIListener_PbapSync uIListener_PbapSync) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.mGroupSyncListener.size()) {
                break;
            }
            if (this.mGroupSyncListener.get(i) == uIListener_PbapSync) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return;
        }
        this.mGroupSyncListener.add(uIListener_PbapSync);
    }

    public void unRegistSyncUIListener(UIListener_PbapSync uIListener_PbapSync) {
        for (int i = 0; i < this.mGroupSyncListener.size(); i++) {
            if (this.mGroupSyncListener.get(i) == uIListener_PbapSync) {
                this.mGroupSyncListener.remove(i);
                return;
            }
        }
    }
}

package com.visualon.OSMPOutputControl;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.visualon.OSMPOutputControl.voOSHDMIBroadcastReceiver;
import com.visualon.OSMPUtils.voLog;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes7.dex */
public class voOSHDMIStateCheck implements voOSHDMIBroadcastReceiver.ICheckHDMIState {
    public static final int HDMISTATE_CONNECT = 1;
    public static final int HDMISTATE_DISCONNECT = 0;
    public static final int HDMISTATE_INITED = 2048;
    private static final String TAG = "HDMIStateCheck";
    protected Context m_Context;
    private EventHandler m_Handler;
    protected voOSHDMIBroadcastReceiver m_HDMIBroadcastReceiver = null;
    protected voOSHDMIDeviceType m_ManufacturerModel = voOSHDMIDeviceType.NoHDMI;
    protected onHDMIStateChangeListener m_OnHDMIStateChangeListener = null;
    protected IntentFilter m_ifReceiveIntent = null;
    protected int m_nHDMIState = -1;

    public interface onHDMIStateChangeListener {
        void onHDMIStateChangeEvent(int i, Object obj);
    }

    public void restart(Context context) {
    }

    protected class EventHandler extends Handler {
        public EventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Object obj;
            onHDMIStateChangeListener onhdmistatechangelistener = voOSHDMIStateCheck.this.m_OnHDMIStateChangeListener;
            if (onhdmistatechangelistener != null && (obj = message.obj) != null) {
                onhdmistatechangelistener.onHDMIStateChangeEvent(message.what, obj);
            } else if (voLog.enablePrintLog()) {
                if (voOSHDMIStateCheck.this.m_OnHDMIStateChangeListener == null) {
                    voLog.e(voOSHDMIStateCheck.TAG, "m_OnHDMIStateChangeListener should no be null", new Object[0]);
                }
                if (message.obj == null) {
                    voLog.e(voOSHDMIStateCheck.TAG, "Message.obj field should not be null", new Object[0]);
                }
            }
            super.handleMessage(message);
        }
    }

    protected void excuteStart() {
        voOSHDMIBroadcastReceiver vooshdmibroadcastreceiver = new voOSHDMIBroadcastReceiver(this.m_ManufacturerModel);
        this.m_HDMIBroadcastReceiver = vooshdmibroadcastreceiver;
        vooshdmibroadcastreceiver.setCheckHDMIStateInterface(this);
        switch (AnonymousClass1.$SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[this.m_ManufacturerModel.ordinal()]) {
            case 1:
                IntentFilter intentFilter = new IntentFilter();
                this.m_ifReceiveIntent = intentFilter;
                intentFilter.addAction("android.intent.action.HDMI_AUDIO_PLUG");
                this.m_Context.registerReceiver(this.m_HDMIBroadcastReceiver, this.m_ifReceiveIntent);
                if (isSupported()) {
                    InitCheck_v3();
                }
                break;
            case 2:
                IntentFilter intentFilter2 = new IntentFilter();
                this.m_ifReceiveIntent = intentFilter2;
                intentFilter2.addAction("com.motorola.intent.action.externaldisplaystate");
                this.m_Context.registerReceiver(this.m_HDMIBroadcastReceiver, this.m_ifReceiveIntent);
                break;
            case 3:
                IntentFilter intentFilter3 = new IntentFilter();
                this.m_ifReceiveIntent = intentFilter3;
                intentFilter3.addAction("com.sonyericsson.intent.action.HDMI_EVENT");
                this.m_Context.registerReceiver(this.m_HDMIBroadcastReceiver, this.m_ifReceiveIntent);
                break;
            case 4:
                IntentFilter intentFilter4 = new IntentFilter();
                this.m_ifReceiveIntent = intentFilter4;
                intentFilter4.addAction("android.intent.action.HTC.HEADSET_PLUG");
                this.m_Context.registerReceiver(this.m_HDMIBroadcastReceiver, this.m_ifReceiveIntent);
                break;
            case 5:
                IntentFilter intentFilter5 = new IntentFilter();
                this.m_ifReceiveIntent = intentFilter5;
                intentFilter5.addAction("android.intent.action.HDMI_PLUG");
                this.m_Context.registerReceiver(this.m_HDMIBroadcastReceiver, this.m_ifReceiveIntent);
                break;
            case 6:
                initSharpHDMIState();
                IntentFilter intentFilter6 = new IntentFilter();
                this.m_ifReceiveIntent = intentFilter6;
                intentFilter6.addAction("HDMI_CONNECTED");
                this.m_ifReceiveIntent.addAction("HDMI_DISCONNECTED");
                this.m_ifReceiveIntent.addCategory("android.intent.category.DEFAULT");
                this.m_Context.registerReceiver(this.m_HDMIBroadcastReceiver, this.m_ifReceiveIntent);
                break;
            case 7:
                IntentFilter intentFilter7 = new IntentFilter();
                this.m_ifReceiveIntent = intentFilter7;
                intentFilter7.addAction("android.intent.action.HDMI_PLUGGED");
                this.m_Context.registerReceiver(this.m_HDMIBroadcastReceiver, this.m_ifReceiveIntent);
                break;
        }
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "HDMI module register completed, ready to receive HDMI broadcast, HDMI model is " + this.m_ManufacturerModel, new Object[0]);
        }
    }

    /* JADX INFO: renamed from: com.visualon.OSMPOutputControl.voOSHDMIStateCheck$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType;

        static {
            int[] iArr = new int[voOSHDMIDeviceType.values().length];
            $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType = iArr;
            try {
                iArr[voOSHDMIDeviceType.StandardVersion3.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[voOSHDMIDeviceType.Motorola_V2_3.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[voOSHDMIDeviceType.SE_LTs.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[voOSHDMIDeviceType.HTC_PC36100.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[voOSHDMIDeviceType.LG_P920.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[voOSHDMIDeviceType.SHARP_SHs.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[voOSHDMIDeviceType.StandardVersion4.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    protected void initSharpHDMIState() {
        try {
            Class<?> cls = Class.forName("jp.co.sharp.android.hdmi.service.external.HDMIState", true, this.m_Context.createPackageContext("jp.co.sharp.android.hdmi", 3).getClassLoader());
            checkHDMIState(((Integer) cls.getMethod("getHDMIState", null).invoke(cls.getConstructor(Context.class).newInstance(this.m_Context), null)).intValue());
        } catch (Exception unused) {
        }
    }

    public voOSHDMIStateCheck(Context context) {
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null) {
            this.m_Handler = new EventHandler(looperMyLooper);
            if (voLog.enablePrintLog()) {
                voLog.v(TAG, "Looper.myLooper()", new Object[0]);
            }
        } else {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.m_Handler = new EventHandler(mainLooper);
                if (voLog.enablePrintLog()) {
                    voLog.v(TAG, "getMainLooper()", new Object[0]);
                }
            } else {
                this.m_Handler = null;
            }
        }
        this.m_Context = context;
        checkManufacturerAndModel();
    }

    public void Release() {
        voOSHDMIDeviceType vooshdmidevicetype;
        try {
            voOSHDMIBroadcastReceiver vooshdmibroadcastreceiver = this.m_HDMIBroadcastReceiver;
            if (vooshdmibroadcastreceiver != null && (vooshdmidevicetype = this.m_ManufacturerModel) != voOSHDMIDeviceType.NoHDMI && vooshdmidevicetype != voOSHDMIDeviceType.SHARP_SHs) {
                this.m_Context.unregisterReceiver(vooshdmibroadcastreceiver);
            }
            this.m_HDMIBroadcastReceiver.setCheckHDMIStateInterface(null);
        } catch (Exception e) {
            if (voLog.enablePrintLog()) {
                voLog.e(TAG, "HDMI Check module release occur error! error message is %s", e.getMessage());
            }
        }
        this.m_HDMIBroadcastReceiver = null;
        EventHandler eventHandler = this.m_Handler;
        if (eventHandler != null) {
            eventHandler.removeCallbacksAndMessages(null);
        }
        this.m_OnHDMIStateChangeListener = null;
    }

    protected boolean isDeviceSupportMultiGraphicsAnd1080p() {
        File file = new File("/sys/class/graphics/");
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        String[] list = file.list();
        if (list.length < 2) {
            return false;
        }
        boolean z = false;
        for (String str : list) {
            File file2 = new File(file.getPath() + "/" + str + "/modes");
            if (file2.exists()) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file2);
                    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                    while (true) {
                        String line = dataInputStream.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.contains("1280")) {
                            z = true;
                            break;
                        }
                    }
                    fileInputStream.close();
                } catch (FileNotFoundException | IOException unused) {
                }
            }
        }
        return z;
    }

    protected boolean isLGHDMISupported() {
        try {
            return ((Integer) Class.forName("android.media.HDMINative", true, this.m_Context.createPackageContext("android", 3).getClassLoader()).getMethod("nativeIsHdmiEnabled", null).invoke(null, new Object[0])).intValue() == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    protected boolean isSharpHDMISupported() {
        try {
            this.m_Context.createPackageContext("jp.co.sharp.android.hdmi", 3);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public boolean isSupported() {
        if (this.m_ManufacturerModel == voOSHDMIDeviceType.NoHDMI) {
            return false;
        }
        boolean zExists = new File("/sys/class/switch/hdmi/").exists();
        if (zExists) {
            return zExists;
        }
        voOSHDMIDeviceType vooshdmidevicetype = this.m_ManufacturerModel;
        if (vooshdmidevicetype == voOSHDMIDeviceType.SE_LTs) {
            return isDeviceSupportMultiGraphicsAnd1080p();
        }
        if (vooshdmidevicetype == voOSHDMIDeviceType.LG_P920) {
            return isLGHDMISupported();
        }
        if (vooshdmidevicetype == voOSHDMIDeviceType.SHARP_SHs) {
            return isSharpHDMISupported();
        }
        File file = new File("/sys/class/drm/");
        if (!file.exists() || !file.isDirectory()) {
            return zExists;
        }
        for (String str : file.list()) {
            if (str.contains("HDMI")) {
                return true;
            }
        }
        return zExists;
    }

    public boolean isHDMIConnected() {
        return this.m_nHDMIState == 1;
    }

    public void setOnHDMIStateChangeListener(onHDMIStateChangeListener onhdmistatechangelistener) {
        this.m_OnHDMIStateChangeListener = onhdmistatechangelistener;
        excuteStart();
    }

    private void checkManufacturerAndModel() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        this.m_ManufacturerModel = voOSHDMIDeviceType.StandardVersion4;
    }

    protected void InitCheck_v3() {
        File file = new File("/sys/class/switch/hdmi/state");
        int i = 0;
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                int iIntValue = Integer.valueOf(new DataInputStream(fileInputStream).readLine()).intValue();
                fileInputStream.close();
                i = iIntValue;
            } catch (FileNotFoundException | IOException unused) {
            }
        }
        checkHDMIState(i);
    }

    @Override // com.visualon.OSMPOutputControl.voOSHDMIBroadcastReceiver.ICheckHDMIState
    public void checkHDMIState(int i) {
        int i2;
        int i3 = this.m_nHDMIState;
        if (i3 == -1) {
            this.m_nHDMIState = i;
            i2 = 2048;
        } else {
            if (i == i3) {
                return;
            }
            this.m_nHDMIState = i;
            i2 = i == 1 ? 1 : 0;
        }
        EventHandler eventHandler = this.m_Handler;
        if (eventHandler != null) {
            this.m_Handler.sendMessage(eventHandler.obtainMessage(i2, Integer.valueOf(i)));
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "sendMessage HDMI status, ID is %d, status is %d.", Integer.valueOf(i2), Integer.valueOf(i));
            }
        }
    }
}

package org.chromium.media;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.provider.Settings;
import defpackage.Is;
import defpackage.TG;
import defpackage.Ts;
import defpackage.UG;
import defpackage.VG;
import defpackage.WG;
import defpackage.XG;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AudioManagerAndroid {
    public static final String[] t = {"Speakerphone", "Wired headset", "Headset earpiece", "Bluetooth headset", "USB audio"};
    public static final Integer[] u = {0, 1, 2, 3, 4};
    public static final Method v;
    public final long b;
    public boolean c;
    public boolean d;
    public boolean f;
    public boolean g;
    public boolean h;
    public ContentObserver m;
    public HandlerThread n;
    public BroadcastReceiver o;
    public BroadcastReceiver p;
    public BroadcastReceiver q;
    public BroadcastReceiver s;
    public int e = -1;
    public int i = -1;
    public final Object j = new Object();
    public boolean[] k = new boolean[5];
    public final AudioManager a = (AudioManager) Is.a.getSystemService("audio");
    public final ContentResolver l = Is.a.getContentResolver();
    public final UsbManager r = (UsbManager) Is.a.getSystemService("usb");

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class AudioDeviceName {
        public final int a;
        public final String b;

        public AudioDeviceName(int i, String str, TG tg) {
            this.a = i;
            this.b = str;
        }

        private String id() {
            return String.valueOf(this.a);
        }

        private String name() {
            return this.b;
        }
    }

    static {
        Method method;
        try {
            method = AudioManager.class.getMethod("getOutputLatency", Integer.TYPE);
        } catch (NoSuchMethodException unused) {
            method = null;
        }
        v = method;
    }

    public AudioManagerAndroid(long j) {
        this.b = j;
    }

    public static boolean acousticEchoCancelerIsAvailable() {
        return AcousticEchoCanceler.isAvailable();
    }

    public static boolean b(AudioManagerAndroid audioManagerAndroid) {
        boolean z;
        synchronized (audioManagerAndroid.j) {
            z = audioManagerAndroid.i != -1;
        }
        return z;
    }

    public static void c(AudioManagerAndroid audioManagerAndroid) {
        int i;
        boolean[] zArr;
        synchronized (audioManagerAndroid.j) {
            i = audioManagerAndroid.i;
            zArr = (boolean[]) audioManagerAndroid.k.clone();
        }
        if (i == -1) {
            g("Unable to activate device since no device is selected");
        } else if (i == -2 || !zArr[i]) {
            audioManagerAndroid.i(h(zArr));
        } else {
            audioManagerAndroid.i(i);
        }
    }

    private void close() {
        if (this.f) {
            l();
            Is.a.unregisterReceiver(this.o);
            this.o = null;
            if (this.d) {
                this.a.stopBluetoothSco();
                Is.a.unregisterReceiver(this.p);
                this.p = null;
                Is.a.unregisterReceiver(this.q);
                this.q = null;
            }
            Is.a.unregisterReceiver(this.s);
            this.s = null;
            this.f = false;
        }
    }

    public static AudioManagerAndroid createAudioManagerAndroid(long j) {
        return new AudioManagerAndroid(j);
    }

    public static void g(String str) {
        Ts.a("media", str, new Object[0]);
    }

    private AudioDeviceName[] getAudioInputDeviceNames() {
        boolean[] zArr;
        String[] strArr = t;
        if (!this.f) {
            return null;
        }
        boolean zD = d("android.permission.RECORD_AUDIO");
        if (!this.c || !zD) {
            Ts.f("media", "Requires MODIFY_AUDIO_SETTINGS and RECORD_AUDIO. No audio device will be available for recording", new Object[0]);
            return null;
        }
        synchronized (this.j) {
            zArr = (boolean[]) this.k.clone();
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            if (zArr[i2]) {
                i++;
            }
        }
        AudioDeviceName[] audioDeviceNameArr = new AudioDeviceName[i];
        int i3 = 0;
        for (int i4 = 0; i4 < 5; i4++) {
            if (zArr[i4]) {
                audioDeviceNameArr[i3] = new AudioDeviceName(i4, strArr[i4], null);
                arrayList.add(strArr[i4]);
                i3++;
            }
        }
        return audioDeviceNameArr;
    }

    private int getAudioLowLatencyOutputFrameSize() {
        String property = this.a.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
        if (property == null) {
            return 256;
        }
        return Integer.parseInt(property);
    }

    public static int getMinInputFrameSize(int i, int i2) {
        int i3;
        if (i2 == 1) {
            i3 = 16;
        } else {
            if (i2 != 2) {
                return -1;
            }
            i3 = 12;
        }
        return (AudioRecord.getMinBufferSize(i, i3, 2) / 2) / i2;
    }

    public static int getMinOutputFrameSize(int i, int i2) {
        int i3;
        if (i2 == 1) {
            i3 = 4;
        } else {
            if (i2 != 2) {
                return -1;
            }
            i3 = 12;
        }
        return (AudioTrack.getMinBufferSize(i, i3, 2) / 2) / i2;
    }

    private int getNativeOutputSampleRate() {
        String property = this.a.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
        if (property == null) {
            return 44100;
        }
        return Integer.parseInt(property);
    }

    private int getOutputLatency() {
        Method method = v;
        if (method == null) {
            return 0;
        }
        try {
            return ((Integer) method.invoke(this.a, 3)).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static int h(boolean[] zArr) {
        if (zArr[1]) {
            return 1;
        }
        if (zArr[4]) {
            return 4;
        }
        return zArr[3] ? 3 : 0;
    }

    private void init() {
        if (this.f) {
            return;
        }
        this.c = d("android.permission.MODIFY_AUDIO_SETTINGS");
        this.k[2] = Is.a.getPackageManager().hasSystemFeature("android.hardware.telephony");
        this.k[1] = this.a.isWiredHeadsetOn();
        this.k[4] = e();
        boolean z = false;
        this.k[0] = true;
        boolean zD = d("android.permission.BLUETOOTH");
        this.d = zD;
        if (zD) {
            boolean[] zArr = this.k;
            if (zD) {
                BluetoothAdapter adapter = ((BluetoothManager) Is.a.getSystemService("bluetooth")).getAdapter();
                if (adapter != null) {
                    int profileConnectionState = adapter.getProfileConnectionState(1);
                    if (adapter.isEnabled() && profileConnectionState == 2) {
                        z = true;
                    }
                }
            } else {
                Ts.f("media", "hasBluetoothHeadset() requires BLUETOOTH permission", new Object[0]);
            }
            zArr[3] = z;
            IntentFilter intentFilter = new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            UG ug = new UG(this);
            this.p = ug;
            Is.a.registerReceiver(ug, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
            VG vg = new VG(this);
            this.q = vg;
            Is.a.registerReceiver(vg, intentFilter2);
        } else {
            Ts.f("media", "Requires BLUETOOTH permission", new Object[0]);
        }
        IntentFilter intentFilter3 = new IntentFilter("android.intent.action.HEADSET_PLUG");
        TG tg = new TG(this);
        this.o = tg;
        Is.a.registerReceiver(tg, intentFilter3);
        this.s = new XG(this);
        IntentFilter intentFilter4 = new IntentFilter();
        intentFilter4.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter4.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        Is.a.registerReceiver(this.s, intentFilter4);
        this.f = true;
    }

    private boolean isAudioLowLatencySupported() {
        return Is.a.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    private void setCommunicationAudioModeOn(boolean z) {
        if (this.f) {
            if (!this.c) {
                Ts.f("media", "MODIFY_AUDIO_SETTINGS is missing => client will run with reduced functionality", new Object[0]);
                return;
            }
            if (z) {
                this.g = this.a.isSpeakerphoneOn();
                this.h = this.a.isMicrophoneMute();
                if (this.n == null) {
                    HandlerThread handlerThread = new HandlerThread("SettingsObserver");
                    this.n = handlerThread;
                    handlerThread.start();
                    WG wg = new WG(this, new Handler(this.n.getLooper()));
                    this.m = wg;
                    this.l.registerContentObserver(Settings.System.CONTENT_URI, true, wg);
                }
            } else {
                l();
                k();
                synchronized (this.j) {
                    this.i = -1;
                }
                boolean z2 = this.h;
                if (this.a.isMicrophoneMute() != z2) {
                    this.a.setMicrophoneMute(z2);
                }
                j(this.g);
            }
            if (z) {
                try {
                    this.a.setMode(3);
                    return;
                } catch (SecurityException e) {
                    String str = Build.VERSION.RELEASE;
                    String str2 = Build.BRAND;
                    String str3 = Build.DEVICE;
                    String str4 = Build.ID;
                    String str5 = Build.HARDWARE;
                    String str6 = Build.MANUFACTURER;
                    String str7 = Build.MODEL;
                    String str8 = Build.PRODUCT;
                    throw e;
                }
            }
            try {
                this.a.setMode(0);
            } catch (SecurityException e2) {
                String str9 = Build.VERSION.RELEASE;
                String str10 = Build.BRAND;
                String str11 = Build.DEVICE;
                String str12 = Build.ID;
                String str13 = Build.HARDWARE;
                String str14 = Build.MANUFACTURER;
                String str15 = Build.MODEL;
                String str16 = Build.PRODUCT;
                throw e2;
            }
        }
    }

    private boolean setDevice(String str) {
        boolean[] zArr;
        if (!this.f) {
            return false;
        }
        boolean zD = d("android.permission.RECORD_AUDIO");
        if (!this.c || !zD) {
            Ts.f("media", "Requires MODIFY_AUDIO_SETTINGS and RECORD_AUDIO. Selected device will not be available for recording", new Object[0]);
            return false;
        }
        int i = str.isEmpty() ? -2 : Integer.parseInt(str);
        if (i == -2) {
            synchronized (this.j) {
                zArr = (boolean[]) this.k.clone();
                this.i = -2;
            }
            i(h(zArr));
            return true;
        }
        if (!Arrays.asList(u).contains(Integer.valueOf(i)) || !this.k[i]) {
            return false;
        }
        synchronized (this.j) {
            this.i = i;
        }
        i(i);
        return true;
    }

    public final boolean d(String str) {
        return Is.a.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    public final boolean e() {
        try {
            Iterator<UsbDevice> it = this.r.getDeviceList().values().iterator();
            while (it.hasNext()) {
                if (f(it.next())) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public final boolean f(UsbDevice usbDevice) {
        for (int i = 0; i < usbDevice.getInterfaceCount(); i++) {
            UsbInterface usbInterface = usbDevice.getInterface(i);
            if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 2) {
                return true;
            }
        }
        return false;
    }

    public final void i(int i) {
        int i2;
        if (i != 3) {
            k();
        } else if (this.d && (i2 = this.e) != 1 && i2 != 2) {
            if (this.a.isBluetoothScoOn()) {
                this.e = 1;
            } else {
                this.e = 2;
                this.a.startBluetoothSco();
            }
        }
        if (i == 0) {
            j(true);
            return;
        }
        if (i == 1) {
            j(false);
            return;
        }
        if (i == 2) {
            j(false);
        } else if (i != 3) {
            if (i != 4) {
                g("Invalid audio device selection");
            } else {
                j(false);
            }
        }
    }

    public final void j(boolean z) {
        if (this.a.isSpeakerphoneOn() == z) {
            return;
        }
        this.a.setSpeakerphoneOn(z);
    }

    public final void k() {
        if (this.d) {
            int i = this.e;
            if (i == 1 || i == 2) {
                if (this.a.isBluetoothScoOn()) {
                    this.e = 3;
                    this.a.stopBluetoothSco();
                } else {
                    g("Unable to stop BT SCO since it is already disabled");
                    this.e = 0;
                }
            }
        }
    }

    public final void l() {
        if (this.n == null) {
            return;
        }
        this.l.unregisterContentObserver(this.m);
        this.m = null;
        this.n.quit();
        try {
            this.n.join();
        } catch (InterruptedException e) {
            Ts.a("media", "Thread.join() exception: ", e);
        }
        this.n = null;
    }
}

package org.chromium.media;

import android.content.ContentResolver;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.util.Log;
import defpackage.m9;
import defpackage.xz;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.media.e;

/* JADX INFO: loaded from: classes3.dex */
public class AudioManagerAndroid {
    public static final Method l;
    public final AudioManager a;
    public final long b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public final ThreadUtils.a g = new ThreadUtils.a();
    public final ContentResolver h;
    public m9 i;
    public HandlerThread j;
    public final e k;

    public static class AudioDeviceName {
        public final int a;
        public final String b;

        public AudioDeviceName(int i, String str) {
            this.a = i;
            this.b = str;
        }

        @CalledByNative
        public final String id() {
            return String.valueOf(this.a);
        }

        @CalledByNative
        public final String name() {
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
        l = method;
    }

    public AudioManagerAndroid(long j) {
        this.b = j;
        AudioManager audioManager = (AudioManager) xz.a.getSystemService("audio");
        this.a = audioManager;
        this.h = xz.a.getContentResolver();
        if (Build.VERSION.SDK_INT < 31) {
            this.k = new g(audioManager);
        } else {
            this.k = new f(audioManager);
        }
    }

    @CalledByNative
    public static boolean acousticEchoCancelerIsAvailable() {
        return AcousticEchoCanceler.isAvailable();
    }

    @CalledByNative
    public static AudioManagerAndroid createAudioManagerAndroid(long j) {
        return new AudioManagerAndroid(j);
    }

    @CalledByNative
    public static int getAudioEncodingFormatsSupported() {
        boolean z = true;
        int i = 0;
        for (AudioDeviceInfo audioDeviceInfo : ((AudioManager) xz.a.getSystemService("audio")).getDevices(2)) {
            int[] encodings = audioDeviceInfo.getEncodings();
            if (audioDeviceInfo.isSink() && audioDeviceInfo.getType() == 9) {
                int i2 = 0;
                for (int i3 : encodings) {
                    if (i3 == 2) {
                        i2 |= 1;
                    } else if (i3 == 13) {
                        i2 |= 128;
                    } else if (i3 == 5) {
                        i2 |= 4;
                    } else if (i3 == 6) {
                        i2 |= 8;
                    } else if (i3 == 7) {
                        i2 |= 16;
                    } else if (i3 == 8) {
                        i2 |= 32;
                    }
                }
                if (z) {
                    z = false;
                    i = i2;
                } else {
                    i &= i2;
                }
            }
        }
        return i;
    }

    @CalledByNative
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

    @CalledByNative
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

    @CalledByNative
    public static boolean isAudioSinkConnected() {
        for (AudioDeviceInfo audioDeviceInfo : ((AudioManager) xz.a.getSystemService("audio")).getDevices(2)) {
            if (audioDeviceInfo.isSink()) {
                return true;
            }
        }
        return false;
    }

    public final void a() {
        if (this.j == null) {
            return;
        }
        this.h.unregisterContentObserver(this.i);
        this.i = null;
        this.j.quit();
        try {
            this.j.join();
        } catch (InterruptedException e) {
            Log.e("cr_media", "Thread.join() exception: ", e);
        }
        this.j = null;
    }

    @CalledByNative
    public final void close() {
        this.g.getClass();
        if (this.d) {
            a();
            this.k.a();
            this.d = false;
        }
    }

    @CalledByNative
    public final AudioDeviceName[] getAudioInputDeviceNames() {
        boolean[] zArrB;
        if (!this.d) {
            return null;
        }
        boolean z = xz.a.checkSelfPermission("android.permission.RECORD_AUDIO") == 0;
        if (!this.c || !z) {
            Log.w("cr_media", "Requires MODIFY_AUDIO_SETTINGS and RECORD_AUDIO. No audio device will be available for recording");
            return null;
        }
        e.b bVar = this.k.a;
        synchronized (bVar.a) {
            zArrB = e.this.b();
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (boolean z2 : zArrB) {
            if (z2) {
                i++;
            }
        }
        AudioDeviceName[] audioDeviceNameArr = new AudioDeviceName[i];
        int i2 = 0;
        for (int i3 = 0; i3 < zArrB.length; i3++) {
            if (zArrB[i3]) {
                String[] strArr = e.a.a;
                audioDeviceNameArr[i2] = new AudioDeviceName(i3, strArr[i3]);
                arrayList.add(strArr[i3]);
                i2++;
            }
        }
        return audioDeviceNameArr;
    }

    @CalledByNative
    public final int getAudioLowLatencyOutputFrameSize() {
        String property = this.a.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
        if (property == null) {
            return 256;
        }
        return Integer.parseInt(property);
    }

    @CalledByNative
    public final int getNativeOutputSampleRate() {
        String property = this.a.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
        if (property == null) {
            return 44100;
        }
        return Integer.parseInt(property);
    }

    @CalledByNative
    public final int getOutputLatency() {
        this.g.getClass();
        Method method = l;
        if (method == null) {
            return 0;
        }
        try {
            return ((Integer) method.invoke(this.a, 3)).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    @CalledByNative
    public final void init() {
        this.g.getClass();
        if (this.d) {
            return;
        }
        this.c = xz.a.checkSelfPermission("android.permission.MODIFY_AUDIO_SETTINGS") == 0;
        this.k.c();
        this.d = true;
    }

    @CalledByNative
    public final boolean isAudioLowLatencySupported() {
        return xz.a.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    @CalledByNative
    public final void setCommunicationAudioModeOn(boolean z) {
        this.g.getClass();
        if (this.d) {
            if (!this.c) {
                Log.w("cr_media", "MODIFY_AUDIO_SETTINGS is missing => client will run with reduced functionality");
                return;
            }
            AudioManager audioManager = this.a;
            e eVar = this.k;
            if (z) {
                this.e = eVar.d();
                this.f = audioManager.isMicrophoneMute();
                eVar.g(true);
                if (this.j == null) {
                    HandlerThread handlerThread = new HandlerThread("SettingsObserver");
                    this.j = handlerThread;
                    handlerThread.start();
                    m9 m9Var = new m9(this, new Handler(this.j.getLooper()));
                    this.i = m9Var;
                    this.h.registerContentObserver(Settings.System.CONTENT_URI, true, m9Var);
                }
            } else {
                a();
                eVar.g(false);
                boolean z2 = this.f;
                if (audioManager.isMicrophoneMute() != z2) {
                    audioManager.setMicrophoneMute(z2);
                }
                eVar.i(this.e);
            }
            if (z) {
                try {
                    audioManager.setMode(3);
                } catch (SecurityException e) {
                    throw e;
                }
            } else {
                try {
                    audioManager.setMode(0);
                } catch (SecurityException e2) {
                    throw e2;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0042  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean setDevice(java.lang.String r9) {
        /*
            r8 = this;
            boolean r0 = r8.d
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            java.lang.String r0 = "android.permission.RECORD_AUDIO"
            android.content.Context r2 = defpackage.xz.a
            int r0 = r2.checkSelfPermission(r0)
            r2 = 1
            if (r0 != 0) goto L13
            r0 = r2
            goto L14
        L13:
            r0 = r1
        L14:
            boolean r3 = r8.c
            if (r3 == 0) goto L7f
            if (r0 != 0) goto L1c
            goto L7f
        L1c:
            org.chromium.media.e r8 = r8.k
            r8.getClass()
            boolean r0 = r9.isEmpty()
            r3 = -2
            if (r0 == 0) goto L2a
            r9 = r3
            goto L2e
        L2a:
            int r9 = java.lang.Integer.parseInt(r9)
        L2e:
            org.chromium.media.e$b r0 = r8.a
            r0.getClass()
            r4 = 4
            if (r9 == r3) goto L42
            if (r9 < 0) goto L3c
            if (r9 > r4) goto L3c
            r5 = r2
            goto L3d
        L3c:
            r5 = r1
        L3d:
            if (r5 == 0) goto L40
            goto L42
        L40:
            r5 = r1
            goto L43
        L42:
            r5 = r2
        L43:
            r6 = -1
            if (r5 != 0) goto L48
            r4 = r6
            goto L75
        L48:
            java.lang.Object r5 = r0.a
            monitor-enter(r5)
            r0.b = r9     // Catch: java.lang.Throwable -> L69
            org.chromium.media.e r7 = org.chromium.media.e.this     // Catch: java.lang.Throwable -> L69
            boolean[] r7 = r7.b()     // Catch: java.lang.Throwable -> L69
            if (r9 != r3) goto L6b
            boolean r9 = r7[r2]     // Catch: java.lang.Throwable -> L69
            if (r9 == 0) goto L5b
            r4 = r2
            goto L67
        L5b:
            boolean r9 = r7[r4]     // Catch: java.lang.Throwable -> L69
            if (r9 == 0) goto L60
            goto L67
        L60:
            r4 = 3
            boolean r9 = r7[r4]     // Catch: java.lang.Throwable -> L69
            if (r9 == 0) goto L66
            goto L67
        L66:
            r4 = r1
        L67:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L69
            goto L75
        L69:
            r8 = move-exception
            goto L7d
        L6b:
            boolean r9 = r7[r9]     // Catch: java.lang.Throwable -> L69
            if (r9 == 0) goto L73
            int r9 = r0.b     // Catch: java.lang.Throwable -> L69
            r4 = r9
            goto L74
        L73:
            r4 = r6
        L74:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L69
        L75:
            if (r4 != r6) goto L78
            goto L7c
        L78:
            r8.f(r4)
            r1 = r2
        L7c:
            return r1
        L7d:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L69
            throw r8
        L7f:
            java.lang.String r8 = "Requires MODIFY_AUDIO_SETTINGS and RECORD_AUDIO. Selected device will not be available for recording"
            java.lang.String r9 = "cr_media"
            android.util.Log.w(r9, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.AudioManagerAndroid.setDevice(java.lang.String):boolean");
    }
}

package org.chromium.midi;

import J.N;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.os.Handler;
import defpackage.rh1;
import defpackage.xz;
import java.util.ArrayList;
import java.util.HashSet;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class MidiManagerAndroid {
    public boolean a;
    public final ArrayList b = new ArrayList();
    public final HashSet c = new HashSet();
    public final MidiManager d = (MidiManager) xz.a.getSystemService("midi");
    public final Handler e = new Handler(ThreadUtils.d());
    public final long f;
    public boolean g;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (MidiManagerAndroid.this) {
                MidiManagerAndroid midiManagerAndroid = MidiManagerAndroid.this;
                if (midiManagerAndroid.g) {
                    return;
                }
                N.MfmZ2$zu(midiManagerAndroid.f);
            }
        }
    }

    public class b extends MidiManager.DeviceCallback {
        public b() {
        }

        @Override // android.media.midi.MidiManager.DeviceCallback
        public final void onDeviceAdded(MidiDeviceInfo midiDeviceInfo) {
            MidiManagerAndroid midiManagerAndroid = MidiManagerAndroid.this;
            if (!midiManagerAndroid.a) {
                midiManagerAndroid.c.add(midiDeviceInfo);
            }
            midiManagerAndroid.d.openDevice(midiDeviceInfo, new rh1(midiManagerAndroid, midiDeviceInfo), midiManagerAndroid.e);
        }

        @Override // android.media.midi.MidiManager.DeviceCallback
        public final void onDeviceRemoved(MidiDeviceInfo midiDeviceInfo) {
            MidiManagerAndroid midiManagerAndroid = MidiManagerAndroid.this;
            synchronized (midiManagerAndroid) {
                if (midiManagerAndroid.g) {
                    return;
                }
                for (MidiDeviceAndroid midiDeviceAndroid : midiManagerAndroid.b) {
                    if (midiDeviceAndroid.d && midiDeviceAndroid.a.getInfo().getId() == midiDeviceInfo.getId()) {
                        midiDeviceAndroid.d = false;
                        for (MidiInputPortAndroid midiInputPortAndroid : midiDeviceAndroid.b) {
                            midiInputPortAndroid.close();
                        }
                        for (MidiOutputPortAndroid midiOutputPortAndroid : midiDeviceAndroid.c) {
                            midiOutputPortAndroid.close();
                        }
                        N.Md3XPFG5(midiManagerAndroid.f, midiDeviceAndroid);
                    }
                }
            }
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (MidiManagerAndroid.this) {
                MidiManagerAndroid midiManagerAndroid = MidiManagerAndroid.this;
                if (midiManagerAndroid.g) {
                    return;
                }
                if (midiManagerAndroid.c.isEmpty()) {
                    MidiManagerAndroid midiManagerAndroid2 = MidiManagerAndroid.this;
                    if (!midiManagerAndroid2.a) {
                        N.M3znzcE9(midiManagerAndroid2.f, (MidiDeviceAndroid[]) midiManagerAndroid2.b.toArray(new MidiDeviceAndroid[0]));
                        MidiManagerAndroid.this.a = true;
                    }
                }
            }
        }
    }

    public MidiManagerAndroid(long j) {
        this.f = j;
    }

    @CalledByNative
    public static MidiManagerAndroid create(long j) {
        return new MidiManagerAndroid(j);
    }

    @CalledByNative
    public static boolean hasSystemFeatureMidi() {
        return xz.a.getPackageManager().hasSystemFeature("android.software.midi");
    }

    @CalledByNative
    public void initialize() {
        Handler handler = this.e;
        MidiManager midiManager = this.d;
        if (midiManager == null) {
            handler.post(new a());
            return;
        }
        midiManager.registerDeviceCallback(new b(), handler);
        for (MidiDeviceInfo midiDeviceInfo : midiManager.getDevices()) {
            this.c.add(midiDeviceInfo);
            midiManager.openDevice(midiDeviceInfo, new rh1(this, midiDeviceInfo), handler);
        }
        handler.post(new c());
    }

    @CalledByNative
    public synchronized void stop() {
        this.g = true;
    }
}

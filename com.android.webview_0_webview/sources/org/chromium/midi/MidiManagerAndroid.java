package org.chromium.midi;

import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.os.Handler;
import defpackage.AI;
import defpackage.BI;
import defpackage.CI;
import defpackage.DI;
import defpackage.Is;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MidiManagerAndroid {
    public boolean a;
    public final List b = new ArrayList();
    public final Set c = new HashSet();
    public final MidiManager d = (MidiManager) Is.a.getSystemService("midi");
    public final Handler e = new Handler(ThreadUtils.d());
    public final long f;
    public boolean g;

    public MidiManagerAndroid(long j) {
        this.f = j;
    }

    public static MidiManagerAndroid create(long j) {
        return new MidiManagerAndroid(j);
    }

    public static boolean hasSystemFeatureMidi() {
        return Is.a.getPackageManager().hasSystemFeature("android.software.midi");
    }

    public void initialize() {
        MidiManager midiManager = this.d;
        if (midiManager == null) {
            this.e.post(new AI(this));
            return;
        }
        midiManager.registerDeviceCallback(new BI(this), this.e);
        for (MidiDeviceInfo midiDeviceInfo : this.d.getDevices()) {
            this.c.add(midiDeviceInfo);
            this.d.openDevice(midiDeviceInfo, new DI(this, midiDeviceInfo), this.e);
        }
        this.e.post(new CI(this));
    }

    public synchronized void stop() {
        this.g = true;
    }
}

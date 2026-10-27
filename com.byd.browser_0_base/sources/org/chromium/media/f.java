package org.chromium.media;

import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Process;
import android.util.Log;
import defpackage.c6;
import defpackage.xz;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.media.e;

/* JADX INFO: compiled from: AudioDeviceSelectorPostS.java */
/* JADX INFO: loaded from: classes3.dex */
public final class f extends e {
    public boolean d;

    public f(AudioManager audioManager) {
        super(audioManager);
    }

    @Override // org.chromium.media.e
    public final void a() {
        this.b.a();
    }

    @Override // org.chromium.media.e
    public final boolean[] b() {
        boolean[] zArr = new boolean[5];
        Iterator it = this.c.getAvailableCommunicationDevices().iterator();
        while (it.hasNext()) {
            int type = ((AudioDeviceInfo) it.next()).getType();
            if (type == 1) {
                zArr[2] = true;
            } else if (type == 2) {
                zArr[0] = true;
            } else if (type == 3 || type == 4) {
                zArr[1] = true;
            } else if (type == 7 || type == 8) {
                zArr[3] = true;
            } else if (type == 11 || type == 22) {
                zArr[4] = true;
            }
        }
        return zArr;
    }

    @Override // org.chromium.media.e
    public final void c() {
        boolean z = c6.a(Process.myPid(), Process.myUid(), xz.a, "android.permission.BLUETOOTH_CONNECT") == 0;
        this.d = z;
        if (!z) {
            Log.w("cr_media", "BLUETOOTH_CONNECT permission is missing.");
        }
        this.b.d(this.d);
    }

    @Override // org.chromium.media.e
    public final boolean d() {
        AudioDeviceInfo communicationDevice = this.c.getCommunicationDevice();
        return communicationDevice != null && communicationDevice.getType() == 2;
    }

    @Override // org.chromium.media.e
    public final void f(int i) {
        AudioDeviceInfo audioDeviceInfo;
        if (i >= 0 && i <= 4) {
            ArrayList arrayList = new ArrayList();
            if (i == 0) {
                arrayList.add(2);
            } else if (i == 1) {
                arrayList.add(3);
                arrayList.add(4);
            } else if (i == 2) {
                arrayList.add(1);
            } else if (i == 3) {
                arrayList.add(7);
                arrayList.add(8);
            } else if (i == 4) {
                arrayList.add(22);
                arrayList.add(11);
            }
            AudioManager audioManager = this.c;
            Iterator it = audioManager.getAvailableCommunicationDevices().iterator();
            while (true) {
                if (!it.hasNext()) {
                    audioDeviceInfo = null;
                    break;
                } else {
                    audioDeviceInfo = (AudioDeviceInfo) it.next();
                    if (arrayList.contains(Integer.valueOf(audioDeviceInfo.getType()))) {
                        break;
                    }
                }
            }
            if (audioDeviceInfo != null) {
                if (audioManager.setCommunicationDevice(audioDeviceInfo)) {
                    return;
                }
                Log.e("cr_media", "Error setting communication device");
            } else {
                Log.e("cr_media", "Couldn't find available device for: " + (i == -1 ? "invalid-ID" : i == -2 ? "default-device" : e.a.a[i]));
            }
        }
    }

    @Override // org.chromium.media.e
    public final void g(boolean z) {
        if (z) {
            return;
        }
        e.b bVar = this.a;
        synchronized (bVar.a) {
            bVar.b = -1;
        }
        this.c.clearCommunicationDevice();
    }

    @Override // org.chromium.media.e
    public final void i(boolean z) {
        if (d() == z) {
            return;
        }
        if (z) {
            f(0);
        } else {
            this.c.clearCommunicationDevice();
            e();
        }
    }
}

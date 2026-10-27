package org.chromium.media;

import android.media.AudioManager;

/* JADX INFO: compiled from: AudioDeviceSelector.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class e {
    public final b a;
    public final d b;
    public final AudioManager c;

    /* JADX INFO: compiled from: AudioDeviceSelector.java */
    public static class a {
        public static final String[] a = {"Speakerphone", "Wired headset", "Headset earpiece", "Bluetooth headset", "USB audio"};
    }

    /* JADX INFO: compiled from: AudioDeviceSelector.java */
    public class b {
        public final Object a = new Object();
        public int b = -1;

        public b() {
        }

        public final void a(int i, boolean z) {
            if (i >= 0 && i <= 4) {
                synchronized (this.a) {
                    e.this.h(i, z);
                }
            }
        }
    }

    public e(AudioManager audioManager) {
        b bVar = new b();
        this.a = bVar;
        this.c = audioManager;
        this.b = new d(bVar);
    }

    public abstract void a();

    public abstract boolean[] b();

    public abstract void c();

    public abstract boolean d();

    public final void e() {
        int i;
        b bVar = this.a;
        synchronized (bVar.a) {
            if (bVar.b == -1) {
                i = -1;
            } else {
                boolean[] zArrB = e.this.b();
                i = bVar.b;
                if (i == -2 || !zArrB[i]) {
                    i = 1;
                    if (!zArrB[1]) {
                        i = 4;
                        if (!zArrB[4]) {
                            i = 3;
                            if (!zArrB[3]) {
                                i = 0;
                            }
                        }
                    }
                }
            }
        }
        if (i == -1) {
            return;
        }
        f(i);
    }

    public abstract void f(int i);

    public abstract void g(boolean z);

    public abstract void i(boolean z);

    public void h(int i, boolean z) {
    }
}

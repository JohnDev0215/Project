package defpackage;

import android.media.VolumeProvider;
import android.os.Build;

/* JADX INFO: compiled from: VolumeProviderCompat.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class xo3 {
    public final int a;
    public final int b;
    public final String c;
    public int d;
    public a e;
    public VolumeProvider f;

    /* JADX INFO: compiled from: VolumeProviderCompat.java */
    public static abstract class a {
        public abstract void onVolumeChanged(xo3 xo3Var);
    }

    public xo3(int i, int i2, int i3, String str) {
        this.a = i;
        this.b = i2;
        this.d = i3;
        this.c = str;
    }

    public final VolumeProvider a() {
        if (this.f == null) {
            if (Build.VERSION.SDK_INT >= 30) {
                this.f = new vo3(this, this.a, this.b, this.d, this.c);
            } else {
                this.f = new wo3(this, this.a, this.b, this.d);
            }
        }
        return this.f;
    }
}

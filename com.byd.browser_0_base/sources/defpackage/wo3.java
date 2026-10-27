package defpackage;

import android.media.VolumeProvider;
import androidx.mediarouter.media.h;
import androidx.mediarouter.media.j;
import androidx.mediarouter.media.k;
import androidx.mediarouter.media.l;

/* JADX INFO: compiled from: VolumeProviderCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class wo3 extends VolumeProvider {
    public final /* synthetic */ xo3 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public wo3(xo3 xo3Var, int i, int i2, int i3) {
        super(i, i2, i3);
        this.a = xo3Var;
    }

    @Override // android.media.VolumeProvider
    public final void onAdjustVolume(int i) {
        l lVar = (l) this.a;
        h.d.this.m.post(new k(lVar, i));
    }

    @Override // android.media.VolumeProvider
    public final void onSetVolumeTo(int i) {
        l lVar = (l) this.a;
        h.d.this.m.post(new j(lVar, i));
    }
}

package defpackage;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: ShareImageFileUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kk2 implements Runnable {
    public final /* synthetic */ Bitmap e;
    public final /* synthetic */ lk2 f;

    public kk2(lk2 lk2Var, Bitmap bitmap) {
        this.f = lk2Var;
        this.e = bitmap;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f.j.onResult(this.e);
    }
}

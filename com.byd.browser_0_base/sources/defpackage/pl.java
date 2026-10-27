package defpackage;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CallbackWithHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class pl implements Runnable {
    public final /* synthetic */ a8 e;
    public final /* synthetic */ Typeface f;

    public pl(a8 a8Var, Typeface typeface) {
        this.e = a8Var;
        this.f = typeface;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.Z(this.f);
    }
}

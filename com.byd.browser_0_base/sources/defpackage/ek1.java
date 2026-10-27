package defpackage;

import android.content.Context;
import defpackage.fc;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: NativeBackgroundTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ek1 implements Runnable {
    public final /* synthetic */ Context e;
    public final /* synthetic */ a73 f;
    public final /* synthetic */ fc.a g;
    public final /* synthetic */ fk1 h;

    public ek1(fk1 fk1Var, Context context, a73 a73Var, du duVar) {
        this.h = fk1Var;
        this.e = context;
        this.f = a73Var;
        this.g = duVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj = ThreadUtils.a;
        fk1 fk1Var = this.h;
        if (fk1Var.b) {
            return;
        }
        fk1Var.d(this.e, this.f, this.g);
    }
}

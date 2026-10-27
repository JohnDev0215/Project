package defpackage;

import defpackage.fc;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: NativeBackgroundTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dk1 implements Runnable {
    public final /* synthetic */ fc.a e;
    public final /* synthetic */ fk1 f;

    public dk1(fk1 fk1Var, fc.a aVar) {
        this.f = fk1Var;
        this.e = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj = ThreadUtils.a;
        if (this.f.b) {
            return;
        }
        this.e.b(true);
    }
}

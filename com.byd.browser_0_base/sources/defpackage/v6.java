package defpackage;

import java.io.File;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class v6 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ String f;

    public /* synthetic */ v6(String str, int i) {
        this.e = i;
        this.f = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        String str = this.f;
        switch (i) {
            case 0:
                x6.u.onResult(new Throwable(str));
                break;
            default:
                new File(str).delete();
                break;
        }
    }
}

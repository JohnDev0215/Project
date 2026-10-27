package p;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

/* JADX INFO: loaded from: classes.dex */
public final class bd implements ul0 {
    public final /* synthetic */ int a;
    public final Object b;

    public /* synthetic */ bd(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                ((z90) obj).getClass();
                return new zc();
            default:
                ((u11) obj).getClass();
                FirebaseCrashlytics firebaseCrashlytics = FirebaseCrashlytics.getInstance();
                v03.i(firebaseCrashlytics, "getInstance()");
                return firebaseCrashlytics;
        }
    }
}

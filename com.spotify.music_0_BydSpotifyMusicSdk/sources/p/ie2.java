package p;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public final class ie2 implements xy1 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ ie2(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                return (SharedPreferences) obj;
            default:
                ue ueVar = (ue) ((sj0) obj);
                int i2 = ueVar.a;
                return ueVar.b;
        }
    }
}

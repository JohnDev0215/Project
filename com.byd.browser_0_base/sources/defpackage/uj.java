package defpackage;

import android.content.SharedPreferences;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class uj implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ uj(int i, int i2, Object obj) {
        this.e = i2;
        this.g = obj;
        this.f = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        int i2 = this.f;
        Object obj = this.g;
        switch (i) {
            case 0:
                ((vj) obj).h.c(i2);
                break;
            case 1:
                ((i7) ((h7) obj)).c(Integer.valueOf(i2));
                break;
            default:
                SharedPreferences sharedPreferences = g03.u;
                ((g03) obj).getClass();
                Object obj2 = ThreadUtils.a;
                if (g03.u == null) {
                    g03.u = xz.a.getSharedPreferences("tab_group_pref", 0);
                }
                SharedPreferences sharedPreferences2 = g03.u;
                String strJ = C0175b.j("SessionsCountForGroup-", Integer.toString(i2));
                if (sharedPreferences2.contains(strJ)) {
                    sharedPreferences2.edit().remove(strJ).apply();
                }
                break;
        }
    }
}

package p;

import android.database.Cursor;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class h30 implements Callable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ h30(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    public final Boolean a() {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                try {
                    g90 g90Var = ((i30) obj).e;
                    gu0 gu0Var = (gu0) g90Var.k;
                    String str = (String) g90Var.e;
                    gu0Var.getClass();
                    return Boolean.valueOf(new File((File) gu0Var.b, str).delete());
                } catch (Exception e) {
                    Log.e("FirebaseCrashlytics", "Problem encountered deleting Crashlytics initialization marker.", e);
                    return Boolean.FALSE;
                }
            default:
                com.google.firebase.crashlytics.internal.common.a aVar = ((i30) obj).h;
                g90 g90Var2 = aVar.c;
                gu0 gu0Var2 = (gu0) g90Var2.k;
                String str2 = (String) g90Var2.e;
                gu0Var2.getClass();
                boolean zExists = new File((File) gu0Var2.b, str2).exists();
                boolean z = true;
                if (zExists) {
                    Log.isLoggable("FirebaseCrashlytics", 2);
                    gu0 gu0Var3 = (gu0) g90Var2.k;
                    String str3 = (String) g90Var2.e;
                    gu0Var3.getClass();
                    new File((File) gu0Var3.b, str3).delete();
                } else {
                    String strF = aVar.f();
                    if (strF == null || !aVar.j.d(strF)) {
                        z = false;
                    }
                }
                return Boolean.valueOf(z);
        }
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.c) {
            case 0:
                return a();
            case 1:
                return a();
            default:
                f02 f02Var = ((k02) this.e).a;
                f02Var.getClass();
                m52 m52VarI = m52.i(0, "\n        SELECT * FROM RateLimitedEvents\n        ");
                i52 i52Var = f02Var.a;
                i52Var.b();
                Cursor cursorM = i52Var.m(m52VarI, null);
                try {
                    int iR = f60.r(cursorM, "eventName");
                    int iR2 = f60.r(cursorM, "count");
                    int iR3 = f60.r(cursorM, "timestamp");
                    ArrayList arrayList = new ArrayList(cursorM.getCount());
                    while (cursorM.moveToNext()) {
                        arrayList.add(new d02(cursorM.getInt(iR2), cursorM.getLong(iR3), cursorM.isNull(iR) ? null : cursorM.getString(iR)));
                        break;
                    }
                    return arrayList;
                } finally {
                    cursorM.close();
                    m52VarI.q();
                }
        }
    }
}

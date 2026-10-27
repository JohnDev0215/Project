package p;

import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class fr2 implements Runnable {
    public final /* synthetic */ jr2 c;
    public final /* synthetic */ wi e;
    public final /* synthetic */ int k;
    public final /* synthetic */ Runnable l;

    public /* synthetic */ fr2(jr2 jr2Var, wi wiVar, int i, Runnable runnable) {
        this.c = jr2Var;
        this.e = wiVar;
        this.k = i;
        this.l = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        wi wiVar = this.e;
        int i = this.k;
        Runnable runnable = this.l;
        jr2 jr2Var = this.c;
        hy2 hy2Var = jr2Var.d;
        wh2 wh2Var = jr2Var.f;
        try {
            try {
                jk0 jk0Var = jr2Var.c;
                Objects.requireNonNull(jk0Var);
                ((j62) wh2Var).J(new af2(3, jk0Var));
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) jr2Var.a.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                    j62 j62Var = (j62) wh2Var;
                    SQLiteDatabase sQLiteDatabaseB = j62Var.b();
                    j62Var.E(new af2(8, sQLiteDatabaseB), new iy2(6));
                    try {
                        ((s61) hy2Var).a(wiVar, i + 1, false);
                        sQLiteDatabaseB.setTransactionSuccessful();
                    } finally {
                        sQLiteDatabaseB.endTransaction();
                    }
                } else {
                    jr2Var.a(wiVar, i);
                }
            } catch (SynchronizationException unused) {
                ((s61) hy2Var).a(wiVar, i + 1, false);
            }
            runnable.run();
        } catch (Throwable th) {
            runnable.run();
            throw th;
        }
    }
}

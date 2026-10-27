package p;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public final class fp1 implements Callback {
    public final /* synthetic */ pp c;
    public final /* synthetic */ ip1 e;

    public fp1(ip1 ip1Var, pp ppVar) {
        this.e = ip1Var;
        this.c = ppVar;
    }

    @Override // okhttp3.Callback
    public final void onFailure(Call call, IOException iOException) {
        try {
            this.c.a(this.e, iOException);
        } catch (Throwable th) {
            bk0.T(th);
            th.printStackTrace();
        }
    }

    @Override // okhttp3.Callback
    public final void onResponse(Call call, Response response) {
        pp ppVar = this.c;
        ip1 ip1Var = this.e;
        try {
            try {
                ppVar.b(ip1Var, ip1Var.e(response));
            } catch (Throwable th) {
                bk0.T(th);
                th.printStackTrace();
            }
        } catch (Throwable th2) {
            bk0.T(th2);
            try {
                ppVar.a(ip1Var, th2);
            } catch (Throwable th3) {
                bk0.T(th3);
                th3.printStackTrace();
            }
        }
    }
}

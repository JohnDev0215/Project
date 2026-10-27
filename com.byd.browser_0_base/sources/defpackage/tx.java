package defpackage;

import android.os.Handler;
import android.os.Looper;
import java.util.Objects;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: ConstraintsChecker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tx implements Callback<Integer> {
    public final pn3 e;
    public final zp1<Integer> f;
    public final Handler g;

    public tx(pn3 pn3Var, zp1<Integer> zp1Var, Looper looper) {
        this.e = pn3Var;
        this.f = zp1Var;
        this.g = new Handler(looper);
    }

    public final boolean a() {
        Integer num = this.f.get();
        return num == null || num.intValue() == 1;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Integer num) {
        if (a()) {
            return;
        }
        this.f.d(this);
        Handler handler = this.g;
        pn3 pn3Var = this.e;
        Objects.requireNonNull(pn3Var);
        handler.post(new d92(11, pn3Var));
    }
}

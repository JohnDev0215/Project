package defpackage;

import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: SharedFileCollator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class nl2 implements Callback<Integer> {
    public int e;
    public Callback<Boolean> f;

    public nl2(yo1 yo1Var, int i) {
        this.e = i;
        this.f = yo1Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Integer num) {
        Integer num2 = num;
        if (this.f == null) {
            return;
        }
        if (num2.intValue() == 0) {
            int i = this.e - 1;
            this.e = i;
            if (i > 0) {
                return;
            }
        }
        Callback<Boolean> callback = this.f;
        this.f = null;
        PostTask.c(rh3.a, new y70(callback, 20, num2));
    }
}

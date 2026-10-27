package p;

import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class jg1 implements IBinder.DeathRecipient {
    public final String a;
    public final int b;
    public final int c;
    public final wg1 d;
    public final HashMap e = new HashMap();
    public hg1 f;
    public final /* synthetic */ bh1 g;

    public jg1(bh1 bh1Var, String str, int i, int i2, wg1 wg1Var) {
        this.g = bh1Var;
        this.a = str;
        this.b = i;
        this.c = i2;
        if (str == null) {
            throw new NullPointerException("package shouldn't be null");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("packageName should be nonempty");
        }
        if (Build.VERSION.SDK_INT >= 28) {
            q2.w(str, i, i2);
        }
        this.d = wg1Var;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        this.g.n.post(new ig1(this));
    }
}

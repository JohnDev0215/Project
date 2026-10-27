package p;

import android.os.Messenger;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class ng1 implements kg1 {
    public final ArrayList a = new ArrayList();
    public mg1 b;
    public Messenger c;
    public final /* synthetic */ bh1 d;

    public ng1(bh1 bh1Var) {
        this.d = bh1Var;
    }

    public void a(String str) {
        this.b.notifyChildrenChanged(str);
    }
}

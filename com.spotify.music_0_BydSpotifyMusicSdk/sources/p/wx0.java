package p;

import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class wx0 {
    public static final by0 a = new by0();
    public static final dy0 b;

    static {
        dy0 dy0Var;
        try {
            dy0Var = (dy0) gy0.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            dy0Var = null;
        }
        b = dy0Var;
    }

    public static final void a(int i, ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((View) it.next()).setVisibility(i);
        }
    }
}

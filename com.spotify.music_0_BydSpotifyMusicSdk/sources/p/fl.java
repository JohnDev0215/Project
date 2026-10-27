package p;

import android.content.Context;
import android.view.MenuItem;

/* JADX INFO: loaded from: classes.dex */
public abstract class fl {
    public final Context a;
    public kd2 b;

    public fl(Context context) {
        this.a = context;
    }

    public final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof mh2)) {
            return menuItem;
        }
        mh2 mh2Var = (mh2) menuItem;
        if (this.b == null) {
            this.b = new kd2();
        }
        MenuItem menuItem2 = (MenuItem) this.b.get(mh2Var);
        if (menuItem2 != null) {
            return menuItem2;
        }
        dj1 dj1Var = new dj1(this.a, mh2Var);
        this.b.put(mh2Var, dj1Var);
        return dj1Var;
    }
}

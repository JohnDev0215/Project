package p;

import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class yi1 {
    public final ActionProvider a;
    public final /* synthetic */ dj1 b;

    public yi1(dj1 dj1Var, ActionProvider actionProvider) {
        this.b = dj1Var;
        this.a = actionProvider;
    }

    public abstract /* bridge */ /* synthetic */ boolean a();

    public abstract View b(MenuItem menuItem);

    public abstract /* bridge */ /* synthetic */ boolean c();

    public abstract void d(oh1 oh1Var);
}

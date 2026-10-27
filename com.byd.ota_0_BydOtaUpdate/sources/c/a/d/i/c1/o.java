package c.a.d.i.c1;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;

/* JADX INFO: compiled from: BaseFullScreenDialog.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class o extends Dialog {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2532c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2533d;
    public boolean s;

    public o(Context context) {
        super(context);
        c.a.d.h.e.c(context);
        this.f2532c = c.a.b.d.h.b() == 1;
        this.f2533d = context.getResources().getConfiguration().getLayoutDirection() == 1;
        this.s = context.getResources().getConfiguration().orientation == 2;
        requestWindowFeature(1);
        setCancelable(false);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(5894);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setStatusBarColor(0);
        window.setNavigationBarColor(0);
        window.addFlags(131072);
        window.addFlags(Integer.MIN_VALUE);
        window.setType(2044);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setAttributes(attributes);
    }

    public void dismissDialog() {
        dismiss();
    }
}

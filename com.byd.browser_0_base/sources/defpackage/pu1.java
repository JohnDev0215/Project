package defpackage;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import defpackage.di1;
import org.chromium.components.page_info.PageInfoContainer;
import org.chromium.components.page_info.R$style;

/* JADX INFO: compiled from: PageInfoDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class pu1 {
    public final ou1 a;
    public final nu1 b;
    public final di1.a c;

    public pu1(Context context, PageInfoContainer pageInfoContainer, View view, bi1 bi1Var, di1.a aVar) {
        this.c = aVar;
        ou1 ou1Var = new ou1(context, view);
        this.a = ou1Var;
        ou1Var.setVisibility(4);
        ou1Var.addOnLayoutChangeListener(new mu1(this));
        ou1Var.addView(pageInfoContainer);
        nu1 nu1Var = new nu1(this, context, R$style.Dialog_Style);
        nu1Var.requestWindowFeature(1);
        nu1Var.setCanceledOnTouchOutside(true);
        Window window = nu1Var.getWindow();
        window.setGravity(17);
        nu1Var.setOnDismissListener(new hk(2, this));
        nu1Var.addContentView(ou1Var, new LinearLayout.LayoutParams(-1, -1));
        DisplayMetrics displayMetrics = ou1Var.getResources().getDisplayMetrics();
        window.setLayout((Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) * 78) / 100, -2);
        this.b = nu1Var;
    }
}

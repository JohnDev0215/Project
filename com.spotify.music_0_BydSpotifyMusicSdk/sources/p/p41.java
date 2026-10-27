package p;

import android.view.View;
import android.view.ViewGroup;
import com.spotify.automotive.baseui.login.AAMLoginScannableActivity;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p41 implements kp1 {
    public final /* synthetic */ View c;

    public /* synthetic */ p41(View view) {
        this.c = view;
    }

    @Override // p.kp1
    public final fx2 i(View view, fx2 fx2Var) {
        int i = AAMLoginScannableActivity.W;
        ((ViewGroup.MarginLayoutParams) ((dy) this.c.getLayoutParams())).topMargin = fx2Var.a.f(7).b;
        return fx2Var;
    }
}

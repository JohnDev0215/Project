package defpackage;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.appbar.AppBarLayout;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;

/* JADX INFO: compiled from: FeedStreamViewResizer.java */
/* JADX INFO: loaded from: classes.dex */
public final class ll0 extends on3 {
    public final Activity k;

    public ll0(Activity activity, AppBarLayout appBarLayout, qh3 qh3Var, int i, int i2) {
        super(appBarLayout, qh3Var, i, i2);
        this.k = activity;
    }

    @Override // defpackage.on3
    public final int b() {
        View viewFindViewById;
        qh3 qh3Var = this.i;
        boolean zC = zk0.c(qh3Var.c);
        int height = 0;
        Activity activity = this.k;
        if (zC) {
            if (this.j == 2) {
                float f = r0.getConfiguration().screenWidthDp * qh3Var.c.getResources().getDisplayMetrics().density;
                return Math.max(Math.max(Math.min((int) ((f - activity.getResources().getDimensionPixelSize(R$dimen.ntp_wide_card_width_breakpoint)) / 2.0f), activity.getResources().getDimensionPixelSize(R$dimen.ntp_wide_card_lateral_margins_max)), ((int) (f - activity.getResources().getDimensionPixelSize(R$dimen.ntp_wide_card_width_max))) / 2), this.f);
            }
        }
        int iB = super.b();
        Context context = qh3Var.c;
        if (context.getResources().getConfiguration().orientation != 2 || activity.isInMultiWindowMode()) {
            return iB;
        }
        float f2 = context.getResources().getDisplayMetrics().density;
        float f3 = r0.getConfiguration().screenWidthDp * qh3Var.c.getResources().getDisplayMetrics().density;
        float f4 = r1.getConfiguration().screenHeightDp * f2;
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(new Rect());
        float f5 = f4 - r5.top;
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(R.id.content);
        if (viewGroup != null && (viewFindViewById = viewGroup.findViewById(R$id.toolbar_container)) != null) {
            height = viewFindViewById.getHeight();
        }
        return Math.max((int) ((f3 - ((f5 - height) * 1.778f)) / 2.0f), iB);
    }
}

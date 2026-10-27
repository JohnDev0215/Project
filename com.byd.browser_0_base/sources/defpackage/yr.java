package defpackage;

import android.content.SharedPreferences;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.tasks.tab_management.r;
import org.chromium.components.messages.MessageBannerView;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class yr implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ yr(int i, int i2, Object obj) {
        this.e = i2;
        this.g = obj;
        this.f = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        int i2 = this.f;
        Object obj = this.g;
        switch (i) {
            case 0:
                ((ChromeTabbedActivity) obj).d1.t(i2, true);
                break;
            case 1:
                SharedPreferences sharedPreferences = g03.u;
                ((g03) obj).getClass();
                Object obj2 = ThreadUtils.a;
                String strJ = C0175b.j("SessionsCountForGroup-", Integer.toString(i2));
                if (g03.u == null) {
                    g03.u = xz.a.getSharedPreferences("tab_group_pref", 0);
                }
                SharedPreferences sharedPreferences2 = g03.u;
                int i3 = sharedPreferences2.getInt(strJ, 0) + 1;
                sharedPreferences2.edit().putInt(strJ, i3).apply();
                oj2.R(i3, "TabGroups.SessionsPerGroup");
                break;
            case 2:
                r.a(i2, (RecyclerView) obj);
                break;
            default:
                lg1 lg1Var = (lg1) obj;
                MessageBannerView messageBannerView = lg1Var.b;
                MessageBannerView messageBannerView2 = lg1Var.b;
                mg1 mg1Var = lg1Var.a;
                ig1 ig1Var = lg1Var.d;
                if (i2 == 1) {
                    messageBannerView.getClass();
                    messageBannerView.setImportantForAccessibility(0);
                    Objects.requireNonNull(ig1Var);
                    mg1Var.e.p(ng1.D, new ve3(15, ig1Var));
                    lg1Var.a(i2);
                    messageBannerView2.setOnTitleChanged(new sb2(i2, 6, lg1Var));
                    long jLongValue = lg1Var.e.get().longValue();
                    ig1Var.a = jLongValue;
                    Runnable runnable = lg1Var.f;
                    ig1Var.b = runnable;
                    ig1Var.c.postDelayed(runnable, jLongValue);
                } else {
                    mg1Var.e.p(ng1.D, null);
                    messageBannerView2.setOnTitleChanged(null);
                    ig1Var.a();
                    messageBannerView.getClass();
                    messageBannerView.setImportantForAccessibility(4);
                    lg1Var.a(i2);
                }
                break;
        }
    }
}

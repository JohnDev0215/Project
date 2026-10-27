package p;

import android.view.View;
import com.spotify.automotive.baseui.login.AAMFullscreenDialogActivity;
import com.spotify.automotive.baseui.settings.LicensesActivity;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s implements View.OnClickListener {
    public final /* synthetic */ int c;
    public final /* synthetic */ t7 e;

    public /* synthetic */ s(t7 t7Var, int i) {
        this.c = i;
        this.e = t7Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.c;
        t7 t7Var = this.e;
        switch (i) {
            case 0:
                int i2 = AAMFullscreenDialogActivity.H;
                ((AAMFullscreenDialogActivity) t7Var).finish();
                break;
            default:
                int i3 = LicensesActivity.G;
                ((LicensesActivity) t7Var).finish();
                break;
        }
    }
}

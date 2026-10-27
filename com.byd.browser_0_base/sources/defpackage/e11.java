package defpackage;

import android.app.Dialog;
import android.view.View;
import org.chromium.components.page_info.PageInfoCookiesPreference;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class e11 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Dialog f;

    public /* synthetic */ e11(Dialog dialog, int i) {
        this.e = i;
        this.f = dialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        Dialog dialog = this.f;
        switch (i) {
            case 0:
                dialog.dismiss();
                break;
            default:
                int i2 = PageInfoCookiesPreference.p0;
                dialog.dismiss();
                break;
        }
    }
}

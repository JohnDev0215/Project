package defpackage;

import android.app.Activity;
import android.widget.TextView;

/* JADX INFO: renamed from: Br, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0035Br {
    public TextView a;

    public C0035Br(Activity activity, int i, EnumC0017Ar enumC0017Ar) {
        this.a = (TextView) activity.findViewById(i);
        int iOrdinal = enumC0017Ar.ordinal();
        if (iOrdinal == 0) {
            this.a.setBackgroundResource(AbstractC0281Pm.U2);
            this.a.setTextColor(-1);
        } else {
            if (iOrdinal != 1) {
                return;
            }
            this.a.setBackgroundResource(AbstractC0281Pm.a6);
            this.a.setTextColor(-16777216);
        }
    }

    public void a() {
        this.a.setVisibility(8);
    }
}

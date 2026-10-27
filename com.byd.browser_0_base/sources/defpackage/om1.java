package defpackage;

import android.view.View;
import defpackage.wm3;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class om1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ View f;

    public /* synthetic */ om1(View view, int i) {
        this.e = i;
        this.f = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        View view = this.f;
        switch (i) {
            case 0:
                int i2 = NewTabPageLayout.L;
                wm3.b(view, new wm3.a(1));
                break;
            default:
                r03.b("IPH_TabGroupsYourTabsTogether", view, null);
                break;
        }
    }
}

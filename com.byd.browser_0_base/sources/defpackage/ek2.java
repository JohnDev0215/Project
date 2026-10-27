package defpackage;

import android.net.Uri;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ShareDelegateImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ek2 implements Callback<GURL> {
    public final /* synthetic */ GURL e;
    public final /* synthetic */ Tab f;
    public final /* synthetic */ WindowAndroid g;
    public final /* synthetic */ WebContents h;
    public final /* synthetic */ String i;
    public final /* synthetic */ int j;
    public final /* synthetic */ boolean k;
    public final /* synthetic */ fk2 l;

    public ek2(fk2 fk2Var, GURL gurl, Tab tab, WindowAndroid windowAndroid, WebContents webContents, String str, int i, boolean z) {
        this.l = fk2Var;
        this.e = gurl;
        this.f = tab;
        this.g = windowAndroid;
        this.h = webContents;
        this.i = str;
        this.j = i;
        this.k = z;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(GURL gurl) {
        final GURL gurl2 = gurl;
        GURL gurl3 = this.e;
        String encodedFragment = Uri.parse(gurl3.j()).getEncodedFragment();
        if (!(encodedFragment != null && encodedFragment.contains(":~:text="))) {
            fk2.d(gurl3, gurl2);
            this.l.e(this.g, this.h, this.i, this.e, gurl2, this.j, this.k);
            return;
        }
        final GURL gurl4 = this.e;
        final WindowAndroid windowAndroid = this.g;
        final WebContents webContents = this.h;
        final String str = this.i;
        final int i = this.j;
        final boolean z = this.k;
        y71.a(new ArrayList(), this.f.c().y().c(), new Callback() { // from class: dk2
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                WindowAndroid windowAndroid2 = windowAndroid;
                WebContents webContents2 = webContents;
                String str2 = str;
                int i2 = i;
                boolean z2 = z;
                ek2 ek2Var = this.e;
                ek2Var.getClass();
                GURL gurl5 = new GURL(y71.b(gurl2.j(), (String) obj));
                GURL gurl6 = gurl4;
                fk2.d(gurl6, gurl5);
                ek2Var.l.e(windowAndroid2, webContents2, str2, gurl6, gurl5, i2, z2);
            }
        }, 0);
    }
}

package org.chromium.content.browser;

import android.app.Activity;
import android.util.SparseArray;
import defpackage.Xz;
import defpackage.Yz;
import org.chromium.base.Callback;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NfcHost extends Xz implements Yz {
    public static final SparseArray v = new SparseArray();
    public final WebContents s;
    public final int t;
    public Callback u;

    public NfcHost(WebContents webContents, int i) {
        this.s = webContents;
        this.t = i;
        v.put(i, this);
    }

    public static void create(WebContents webContents, int i) {
        new NfcHost(webContents, i);
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void u(WindowAndroid windowAndroid) {
        this.u.a(windowAndroid != null ? (Activity) windowAndroid.Q().get() : null);
    }
}

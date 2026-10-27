package org.chromium.content.browser;

import android.app.Activity;
import android.util.SparseArray;
import defpackage.jt3;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class NfcHost implements jt3 {
    public static final SparseArray<NfcHost> h = new SparseArray<>();
    public final WebContents e;
    public final int f;
    public Callback<Activity> g;

    public NfcHost(WebContents webContents, int i) {
        this.e = webContents;
        this.f = i;
        h.put(i, this);
    }

    @CalledByNative
    public static void create(WebContents webContents, int i) {
        new NfcHost(webContents, i);
    }

    @Override // defpackage.jt3
    public final void a(WindowAndroid windowAndroid) {
        this.g.onResult(windowAndroid != null ? (Activity) windowAndroid.k().get() : null);
    }
}

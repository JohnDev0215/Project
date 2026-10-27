package defpackage;

import android.content.Context;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;

/* JADX INFO: compiled from: StylusApiOption.java */
/* JADX INFO: loaded from: classes3.dex */
public interface tv2 {
    static void c(int i) {
        oj2.U(i, 2, "InputMethod.StylusHandwriting.Triggered");
    }

    default ViewAndroidDelegate.a f() {
        return null;
    }

    void h(Context context, WebContents webContents);

    default void k(Context context, boolean z) {
    }
}

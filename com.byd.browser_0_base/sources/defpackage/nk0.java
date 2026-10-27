package defpackage;

import android.content.Context;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.ui.favicon.a;

/* JADX INFO: compiled from: FaviconHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class nk0 {
    public final Context a;
    public final uc2 b;
    public final int c;

    static {
        new wd0(12);
    }

    public nk0(Context context) {
        this.a = context;
        this.c = context.getResources().getDimensionPixelSize(R$dimen.keyboard_accessory_suggestion_icon_size);
        this.b = a.a(context);
    }
}

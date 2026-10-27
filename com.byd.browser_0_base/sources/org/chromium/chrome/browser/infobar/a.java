package org.chromium.chrome.browser.infobar;

import android.content.Context;
import defpackage.oj2;
import defpackage.xe3;
import org.chromium.chrome.browser.infobar.b;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: IPHBubbleDelegateImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements b.a {
    public final Context a;
    public final xe3 b;
    public final Tab c;

    public a(Context context, Tab tab) {
        this.a = context;
        this.b = oj2.t(Profile.b(tab.c()));
        this.c = tab;
    }
}

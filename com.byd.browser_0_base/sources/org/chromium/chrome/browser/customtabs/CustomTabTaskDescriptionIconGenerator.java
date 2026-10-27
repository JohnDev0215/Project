package org.chromium.chrome.browser.customtabs;

import android.content.Context;
import android.graphics.Bitmap;
import defpackage.uc2;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class CustomTabTaskDescriptionIconGenerator {
    public final Context a;
    public final int b;
    public GURL c;
    public Bitmap d;
    public uc2 e;

    public CustomTabTaskDescriptionIconGenerator(Context context) {
        this.a = context;
        this.b = ((int) context.getResources().getDisplayMetrics().density) * 32;
    }
}

package defpackage;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/* JADX INFO: renamed from: fC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0759fC extends Resources {
    public AbstractC0759fC(AssetManager assetManager, DisplayMetrics displayMetrics, Configuration configuration) {
        super(assetManager, displayMetrics, configuration);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x005c  */
    @Override // android.content.res.Resources
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getString(int r5, java.lang.Object... r6) {
        /*
            r4 = this;
            java.lang.String r5 = super.getString(r5, r6)     // Catch: java.util.IllegalFormatConversionException -> L5
            return r5
        L5:
            r0 = move-exception
            java.lang.String r5 = super.getString(r5)
            char r0 = r0.getConversion()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "%"
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = java.util.regex.Pattern.quote(r1)
            java.lang.String r2 = "%s"
            java.lang.String r5 = r5.replaceAll(r1, r2)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "%1$"
            r1.append(r3)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r0 = java.util.regex.Pattern.quote(r0)
            java.lang.String r5 = r5.replaceAll(r0, r2)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 24
            if (r0 < r1) goto L5c
            android.content.res.Configuration r0 = r4.getConfiguration()
            android.os.LocaleList r0 = r0.getLocales()
            int r1 = r0.size()
            if (r1 <= 0) goto L5c
            r1 = 0
            java.util.Locale r0 = r0.get(r1)
            goto L62
        L5c:
            android.content.res.Configuration r0 = r4.getConfiguration()
            java.util.Locale r0 = r0.locale
        L62:
            java.lang.String r5 = java.lang.String.format(r0, r5, r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC0759fC.getString(int, java.lang.Object[]):java.lang.String");
    }
}

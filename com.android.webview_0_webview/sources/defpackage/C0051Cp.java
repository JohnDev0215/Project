package defpackage;

import J.N;
import android.content.Context;
import android.net.Uri;
import org.chromium.base.ContentUriUtils;

/* JADX INFO: renamed from: Cp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0051Cp extends Su {
    public final int h;
    public final int i;
    public final int j;
    public final String[] k;
    public final Context l;

    public C0051Cp(Context context, int i, int i2, int i3, String[] strArr) {
        this.h = i;
        this.i = i2;
        this.j = i3;
        this.k = strArr;
        this.l = context;
    }

    @Override // defpackage.Su
    public Object c() {
        String[] strArr = new String[this.k.length];
        int i = 0;
        while (true) {
            String[] strArr2 = this.k;
            if (i >= strArr2.length) {
                return strArr;
            }
            String str = strArr2[i];
            strArr[i] = str == null ? "" : ContentUriUtils.c(Uri.parse(str), this.l, "_display_name");
            i++;
        }
    }

    @Override // defpackage.Su
    public void n(Object obj) {
        N.MB5V$olG(this.h, this.i, this.j, this.k, (String[]) obj);
    }
}

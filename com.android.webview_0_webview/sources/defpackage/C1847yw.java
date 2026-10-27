package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.components.autofill.FormData;

/* JADX INFO: renamed from: yw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1847yw {
    public static int d = 1;
    public final int a;
    public FormData b;
    public C1902zw c;

    public C1847yw(FormData formData, C1902zw c1902zw) {
        ThreadUtils.b();
        if (d == 65535) {
            d = 1;
        }
        int i = d;
        d = i + 1;
        this.a = i;
        this.b = formData;
        this.c = c1902zw;
    }

    public static int a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return -1;
        }
        for (int i = 0; i < strArr.length; i++) {
            if (str.equals(strArr[i])) {
                return i;
            }
        }
        return -1;
    }

    public int b(short s) {
        return s | (this.a << 16);
    }
}

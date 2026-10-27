package defpackage;

import java.util.HashMap;

/* JADX INFO: renamed from: Xn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0426Xn {
    public String a;
    public boolean b;
    public boolean c;
    public boolean d;
    public String e;
    public HashMap f;

    public C0426Xn(String str, boolean z, boolean z2, String str2, HashMap map) {
        this.a = str;
        this.b = z;
        this.c = z2;
        this.e = str2;
        this.f = null;
    }

    public C0426Xn(String str, boolean z, boolean z2, String str2, String[] strArr, String[] strArr2) {
        HashMap map = new HashMap(strArr2.length);
        this.a = str;
        this.b = z;
        this.c = z2;
        this.e = str2;
        this.f = map;
        for (int i = 0; i < strArr.length; i++) {
            this.f.put(strArr[i], strArr2[i]);
        }
    }
}

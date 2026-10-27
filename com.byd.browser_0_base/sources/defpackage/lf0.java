package defpackage;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;

/* JADX INFO: compiled from: EditableOption.java */
/* JADX INFO: loaded from: classes2.dex */
public class lf0 implements ov {
    public boolean a;
    public int b;
    public boolean c;
    public String d;
    public String e;
    public String f;
    public String g;
    public Drawable h;
    public final String[] i;
    public boolean j;

    public lf0(Drawable drawable, String str, String str2, String str3) {
        this(str, str2, str3, null, drawable);
    }

    @Override // defpackage.ov
    public final int a() {
        return this.b;
    }

    public final String b(int i, String str) {
        String[] strArr = this.i;
        StringBuilder sb = new StringBuilder(strArr[0]);
        if (!TextUtils.isEmpty(strArr[1])) {
            if (sb.length() > 0) {
                sb.append(str);
            }
            sb.append(strArr[1]);
        }
        if (!TextUtils.isEmpty(strArr[2])) {
            if (sb.length() > 0) {
                sb.append(str);
            }
            sb.append(strArr[2]);
        }
        return (i < 0 || sb.length() < i) ? sb.toString() : sb.substring(0, i / 2);
    }

    public boolean c() {
        return this.a;
    }

    public final void d(String str, String str2, String str3, String str4) {
        this.g = str;
        String[] strArr = this.i;
        strArr[0] = str2;
        strArr[1] = str3;
        strArr[2] = str4;
    }

    public lf0(String str, String str2, String str3, String str4, Drawable drawable) {
        this.a = true;
        this.b = Integer.MAX_VALUE;
        this.i = new String[]{null, null, null};
        this.j = true;
        d(str, str2, str3, str4);
        this.h = drawable;
    }
}

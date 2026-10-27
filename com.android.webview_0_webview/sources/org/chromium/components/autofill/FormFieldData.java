package org.chromium.components.autofill;

import android.graphics.RectF;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FormFieldData {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String[] g;
    public final String[] h;
    public final int i;
    public final int j;
    public final String k;
    public final RectF l;
    public RectF m;
    public boolean n;
    public String o;
    public boolean p;
    public boolean q;

    public FormFieldData(String str, String str2, String str3, String str4, boolean z, String str5, String str6, String str7, String[] strArr, String[] strArr2, boolean z2, boolean z3, int i, String str8, float f, float f2, float f3, float f4) {
        this.b = str;
        this.a = str2;
        this.o = str3;
        this.c = str4;
        this.d = str5;
        this.e = str6;
        this.f = str7;
        this.g = strArr;
        this.h = strArr2;
        this.n = z3;
        if (strArr != null && strArr.length != 0) {
            this.i = 2;
        } else if (z2) {
            this.i = 1;
        } else {
            this.i = 0;
        }
        this.j = i;
        this.k = str8;
        this.l = new RectF(f, f2, f3, f4);
    }

    public static FormFieldData createFormFieldData(String str, String str2, String str3, String str4, boolean z, String str5, String str6, String str7, String[] strArr, String[] strArr2, boolean z2, boolean z3, int i, String str8, float f, float f2, float f3, float f4) {
        return new FormFieldData(str, str2, str3, str4, z, str5, str6, str7, strArr, strArr2, z2, z3, i, str8, f, f2, f3, f4);
    }

    private void updateValue(String str) {
        this.o = str;
        a(false);
    }

    public final void a(boolean z) {
        if (this.p && !z) {
            this.q = true;
        }
        this.p = z;
    }

    public String getValue() {
        return this.o;
    }

    public boolean isChecked() {
        return this.n;
    }
}

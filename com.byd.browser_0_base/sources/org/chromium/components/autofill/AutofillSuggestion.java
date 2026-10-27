package org.chromium.components.autofill;

import android.graphics.Bitmap;
import defpackage.de0;
import java.util.Objects;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public final class AutofillSuggestion extends de0 {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final int f;
    public final boolean g;
    public final int h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final String l;
    public final GURL m;
    public final Bitmap n;

    public AutofillSuggestion(String str, String str2, String str3, String str4, String str5, int i, boolean z, int i2, boolean z2, boolean z3, boolean z4, String str6, GURL gurl, Bitmap bitmap) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = i;
        this.g = z;
        this.h = i2;
        this.i = z2;
        this.j = z3;
        this.k = z4;
        this.l = str6;
        this.m = gurl;
        this.n = bitmap;
    }

    @Override // defpackage.de0, defpackage.ce0
    public final boolean b() {
        return this.j;
    }

    @Override // defpackage.de0, defpackage.ce0
    public final String c() {
        return this.c;
    }

    @Override // defpackage.de0, defpackage.ce0
    public final String e() {
        return this.e;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AutofillSuggestion)) {
            return false;
        }
        AutofillSuggestion autofillSuggestion = (AutofillSuggestion) obj;
        if (this.a.equals(autofillSuggestion.a) && Objects.equals(this.b, autofillSuggestion.b) && this.c.equals(autofillSuggestion.c) && Objects.equals(this.d, autofillSuggestion.d) && Objects.equals(this.e, autofillSuggestion.e) && this.f == autofillSuggestion.f && this.g == autofillSuggestion.g && this.h == autofillSuggestion.h && this.i == autofillSuggestion.i && this.j == autofillSuggestion.j && this.k == autofillSuggestion.k && Objects.equals(this.l, autofillSuggestion.l) && Objects.equals(this.m, autofillSuggestion.m)) {
            Bitmap bitmap = autofillSuggestion.n;
            Bitmap bitmap2 = this.n;
            if (bitmap2 == null) {
                if (bitmap == null) {
                    return true;
                }
            } else if (bitmap2.sameAs(bitmap)) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.ce0
    public final String f() {
        return this.a;
    }

    @Override // defpackage.de0, defpackage.ce0
    public final int h() {
        return this.h == -1 ? R$color.insecure_context_payment_disabled_message_text : org.chromium.ui.R$color.default_text_color_list_baseline;
    }

    @Override // defpackage.de0, defpackage.ce0
    public final boolean i() {
        return this.g;
    }

    @Override // defpackage.de0, defpackage.ce0
    public final String j() {
        return this.d;
    }

    @Override // defpackage.de0, defpackage.ce0
    public final boolean k() {
        return this.k;
    }

    @Override // defpackage.de0, defpackage.ce0
    public final String l() {
        return this.b;
    }

    @Override // defpackage.de0, defpackage.ce0
    public final Bitmap m() {
        return this.n;
    }

    @Override // defpackage.de0, defpackage.ce0
    public final int o() {
        return this.f;
    }
}

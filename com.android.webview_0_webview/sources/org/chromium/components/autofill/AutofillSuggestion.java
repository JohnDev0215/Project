package org.chromium.components.autofill;

import defpackage.AbstractC0281Pm;
import defpackage.AbstractC1381qN;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AutofillSuggestion extends AbstractC1381qN {
    public final String a;
    public final String b;
    public final int c;
    public final boolean d;
    public final int e;
    public final boolean f;
    public final boolean g;
    public final boolean h;

    public AutofillSuggestion(String str, String str2, int i, boolean z, int i2, boolean z2, boolean z3, boolean z4) {
        this.a = str;
        this.b = str2;
        this.c = i;
        this.d = z;
        this.e = i2;
        this.f = z2;
        this.g = z3;
        this.h = z4;
    }

    @Override // defpackage.InterfaceC1326pN
    public String a() {
        return this.a;
    }

    @Override // defpackage.AbstractC1381qN, defpackage.InterfaceC1326pN
    public int b() {
        return this.e == -1 ? AbstractC0281Pm.G3 : AbstractC0281Pm.m2;
    }

    @Override // defpackage.AbstractC1381qN, defpackage.InterfaceC1326pN
    public boolean c() {
        return this.d;
    }

    @Override // defpackage.AbstractC1381qN, defpackage.InterfaceC1326pN
    public boolean e() {
        return this.g;
    }

    @Override // defpackage.AbstractC1381qN, defpackage.InterfaceC1326pN
    public String f() {
        return this.b;
    }

    @Override // defpackage.AbstractC1381qN, defpackage.InterfaceC1326pN
    public boolean g() {
        return this.h;
    }

    @Override // defpackage.AbstractC1381qN, defpackage.InterfaceC1326pN
    public int h() {
        return this.c;
    }
}

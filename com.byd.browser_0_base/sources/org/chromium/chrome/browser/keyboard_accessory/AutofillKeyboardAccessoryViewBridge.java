package org.chromium.chrome.browser.keyboard_accessory;

import J.N;
import defpackage.aj3;
import defpackage.ta;
import defpackage.td2;
import defpackage.vb1;
import defpackage.x62;
import defpackage.zp1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.autofill.AutofillSuggestion;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class AutofillKeyboardAccessoryViewBridge implements ta {
    public long e;
    public zp1<a> f;
    public a g;
    public final x62<AutofillSuggestion[]> h = new x62<>(2);
    public final td2 i = new td2(1, this);

    @CalledByNative
    public static void addToAutofillSuggestionArray(AutofillSuggestion[] autofillSuggestionArr, int i, String str, String str2, int i2, int i3, boolean z, String str3, GURL gurl) {
        autofillSuggestionArr[i] = new AutofillSuggestion(str, null, str2, null, null, i2 == 0 ? 0 : i2, false, i3, z, false, false, str3, gurl, null);
    }

    @CalledByNative
    public static AutofillKeyboardAccessoryViewBridge create() {
        return new AutofillKeyboardAccessoryViewBridge();
    }

    @CalledByNative
    public static AutofillSuggestion[] createAutofillSuggestionArray(int i) {
        return new AutofillSuggestion[i];
    }

    @Override // defpackage.ta
    public final void a(int i) {
        long j = this.e;
        if (j == 0) {
            return;
        }
        N.Mg8PCuPV(j, this, i);
    }

    @Override // defpackage.ta
    public final void b(int i) {
        this.g.dismiss();
        long j = this.e;
        if (j == 0) {
            return;
        }
        N.MjgUnUA$(j, this, i);
    }

    @CalledByNative
    public final void confirmDeletion(String str, String str2) {
        this.g.z();
    }

    @Override // defpackage.ta
    public final void d() {
        long j = this.e;
        if (j == 0) {
            return;
        }
        N.M$YFyQSp(j, this);
    }

    @CalledByNative
    public final void dismiss() {
        if (this.f != null) {
            this.h.b(new AutofillSuggestion[0]);
            this.f.d(this.i);
        }
        d();
    }

    @CalledByNative
    public final void init(long j, WindowAndroid windowAndroid) {
        a aVarP;
        aj3<vb1> aj3Var = vb1.j;
        zp1<a> zp1Var = (zp1) vb1.j.e(windowAndroid.r);
        this.f = zp1Var;
        if (zp1Var != null && this.g != (aVarP = zp1Var.p(this.i))) {
            this.g = aVarP;
            if (aVarP != null) {
                aVarP.m1();
            }
        }
        this.e = j;
    }

    @CalledByNative
    public final void resetNativeViewPointer() {
        this.e = 0L;
    }

    @CalledByNative
    public final void show(AutofillSuggestion[] autofillSuggestionArr) {
        this.h.b(autofillSuggestionArr);
    }

    @Override // defpackage.ta
    public final void c() {
    }
}

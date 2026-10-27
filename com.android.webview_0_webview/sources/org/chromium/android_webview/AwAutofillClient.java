package org.chromium.android_webview;

import J.N;
import android.content.Context;
import android.view.View;
import defpackage.C0845gn;
import defpackage.C1243nw;
import defpackage.C1408qw;
import defpackage.C1682vw;
import defpackage.C1737ww;
import defpackage.Is;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import org.chromium.components.autofill.AutofillSuggestion;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwAutofillClient {
    public final long a;
    public C1737ww b;
    public Context c;

    public AwAutofillClient(long j) {
        this.a = j;
    }

    public static void addToAutofillSuggestionArray(AutofillSuggestion[] autofillSuggestionArr, int i, String str, String str2, int i2) {
        autofillSuggestionArr[i] = new AutofillSuggestion(str, str2, 0, false, i2, false, false, false);
    }

    public static AwAutofillClient create(long j) {
        return new AwAutofillClient(j);
    }

    public static AutofillSuggestion[] createAutofillSuggestionArray(int i) {
        return new AutofillSuggestion[i];
    }

    private void showAutofillPopup(View view, boolean z, AutofillSuggestion[] autofillSuggestionArr) {
        if (this.b == null) {
            if (Is.a(this.c) == null) {
                N.M2z_jbiy(this.a, this);
                return;
            } else {
                try {
                    this.b = new C1737ww(this.c, view, new C0845gn(this));
                } catch (RuntimeException unused) {
                    N.M2z_jbiy(this.a, this);
                    return;
                }
            }
        }
        C1737ww c1737ww = this.b;
        Objects.requireNonNull(c1737ww);
        c1737ww.v = new ArrayList(Arrays.asList(autofillSuggestionArr));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        HashSet hashSet = new HashSet();
        for (int i = 0; i < autofillSuggestionArr.length; i++) {
            if (autofillSuggestionArr[i].e == -3) {
                hashSet.add(Integer.valueOf(arrayList.size()));
            } else {
                arrayList.add(autofillSuggestionArr[i]);
            }
        }
        if (!arrayList2.isEmpty() && !c1737ww.s.h()) {
            c1737ww.s.k(new C1408qw(c1737ww.t, arrayList2, c1737ww));
        }
        c1737ww.s.l(new C1243nw(c1737ww.t, arrayList, hashSet, false));
        c1737ww.s.g(z);
        c1737ww.s.a();
        c1737ww.a().setOnItemLongClickListener(c1737ww);
        c1737ww.a().setAccessibilityDelegate(new C1682vw(c1737ww));
    }

    public void hideAutofillPopup() {
        C1737ww c1737ww = this.b;
        if (c1737ww == null) {
            return;
        }
        c1737ww.s.dismiss();
        this.b = null;
    }
}

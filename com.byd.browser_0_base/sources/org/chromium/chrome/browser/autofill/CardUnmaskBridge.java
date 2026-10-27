package org.chromium.chrome.browser.autofill;

import android.app.Activity;
import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;
import defpackage.aw;
import defpackage.bi1;
import defpackage.di1;
import defpackage.i9;
import defpackage.wv;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.e;
import org.chromium.chrome.browser.autofill.e.a;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class CardUnmaskBridge implements e.b {
    public final long a;
    public final e b;

    public CardUnmaskBridge(long j, String str, String str2, int i, String str3, String str4, String str5, GURL gurl, String str6, int i2, String str7, int i3, boolean z, boolean z2, boolean z3, boolean z4, long j2, WindowAndroid windowAndroid) {
        this.a = j;
        Activity activity = (Activity) windowAndroid.k().get();
        if (activity != null) {
            this.b = new e(activity, this, str, str2, i, str3, str4, str5, gurl, str6, i2, str7, i3, z, z2, z3, z4, j2);
        } else {
            this.b = null;
            new Handler().post(new androidx.appcompat.widget.a(12, this));
        }
    }

    @CalledByNative
    public static CardUnmaskBridge create(long j, String str, String str2, int i, String str3, String str4, String str5, GURL gurl, String str6, int i2, String str7, int i3, boolean z, boolean z2, boolean z3, boolean z4, long j2, WindowAndroid windowAndroid) {
        return new CardUnmaskBridge(j, str, str2, i, str3, str4, str5, gurl, str6, i2, str7, i3, z, z2, z3, z4, j2, windowAndroid);
    }

    @CalledByNative
    public final void disableAndWaitForVerification() {
        e eVar = this.b;
        if (eVar != null) {
            eVar.k.setEnabled(false);
            eVar.l.setEnabled(false);
            eVar.m.setEnabled(false);
            eVar.f.l(di1.l, true);
            eVar.e(0);
            eVar.t.setVisibility(0);
            int i = R$string.autofill_card_unmask_verification_in_progress;
            TextView textView = eVar.u;
            textView.setText(i);
            textView.announceForAccessibility(textView.getText());
            eVar.a();
        }
    }

    @CalledByNative
    public final void dismiss() {
        e eVar = this.b;
        if (eVar != null) {
            eVar.A.b(4, eVar.f);
        }
    }

    @CalledByNative
    public final void show(WindowAndroid windowAndroid) {
        e eVar = this.b;
        if (eVar != null) {
            Activity activity = (Activity) windowAndroid.k().get();
            bi1 bi1VarN = windowAndroid.n();
            if (activity == null || bi1VarN == null) {
                return;
            }
            eVar.B = activity;
            eVar.A = bi1VarN;
            bi1VarN.j(eVar.f, 1, false);
            eVar.f();
            eVar.f.l(di1.l, true);
            EditText editText = eVar.k;
            editText.addTextChangedListener(eVar);
            editText.post(new aw(7, eVar));
        }
    }

    @CalledByNative
    public final void update(String str, String str2, boolean z) {
        e eVar = this.b;
        if (eVar != null) {
            eVar.g(eVar.B, str);
            eVar.i.setText(str2);
            eVar.g = z;
            if (z && (eVar.y == -1 || eVar.z == -1)) {
                eVar.new a().c(i9.e);
            }
            eVar.f();
        }
    }

    @CalledByNative
    public final void verificationFinished(String str, boolean z) {
        e eVar = this.b;
        if (eVar != null) {
            if (str == null) {
                wv wvVar = new wv(17, eVar);
                long j = eVar.v;
                if (j <= 0) {
                    new Handler().post(wvVar);
                    return;
                }
                eVar.t.setVisibility(8);
                eVar.h.findViewById(R$id.verification_success).setVisibility(0);
                int i = R$string.autofill_card_unmask_verification_success;
                TextView textView = eVar.u;
                textView.setText(i);
                textView.announceForAccessibility(textView.getText());
                new Handler().postDelayed(wvVar, j);
                return;
            }
            eVar.e(8);
            if (!z) {
                eVar.a();
                TextView textView2 = eVar.j;
                textView2.setText(str);
                textView2.setVisibility(0);
                textView2.announceForAccessibility(str);
                return;
            }
            TextView textView3 = eVar.p;
            textView3.setText(str);
            textView3.setVisibility(0);
            textView3.announceForAccessibility(str);
            eVar.k.setEnabled(true);
            eVar.l.setEnabled(true);
            eVar.m.setEnabled(true);
            eVar.f.l(di1.l, false);
            eVar.d();
            boolean z2 = eVar.g;
            TextView textView4 = eVar.o;
            if (z2 || eVar.x) {
                textView4.setVisibility(8);
            } else {
                textView4.setVisibility(0);
            }
        }
    }
}

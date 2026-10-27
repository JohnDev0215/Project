package org.chromium.chrome.browser.autofill;

import android.content.Context;
import defpackage.bi1;
import defpackage.c01;
import defpackage.c3;
import java.util.Objects;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.autofill.settings.VirtualCardEnrollmentFields;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public class VirtualCardEnrollmentDialogViewBridge {
    public final String a;
    public final String b;
    public final VirtualCardEnrollmentDelegate c;
    public final VirtualCardEnrollmentFields d;
    public final WindowAndroid e;
    public org.chromium.chrome.browser.autofill.settings.b f;

    public VirtualCardEnrollmentDialogViewBridge(String str, String str2, VirtualCardEnrollmentDelegate virtualCardEnrollmentDelegate, VirtualCardEnrollmentFields virtualCardEnrollmentFields, WindowAndroid windowAndroid) {
        this.a = str;
        this.b = str2;
        this.c = virtualCardEnrollmentDelegate;
        this.d = virtualCardEnrollmentFields;
        this.e = windowAndroid;
    }

    @CalledByNative
    public static VirtualCardEnrollmentDialogViewBridge create(String str, String str2, VirtualCardEnrollmentDelegate virtualCardEnrollmentDelegate, VirtualCardEnrollmentFields virtualCardEnrollmentFields, WindowAndroid windowAndroid) {
        if (virtualCardEnrollmentDelegate == null || virtualCardEnrollmentFields == null || windowAndroid == null) {
            return null;
        }
        return new VirtualCardEnrollmentDialogViewBridge(str, str2, virtualCardEnrollmentDelegate, virtualCardEnrollmentFields, windowAndroid);
    }

    @CalledByNative
    public final void dismiss() {
        org.chromium.chrome.browser.autofill.settings.b bVar = this.f;
        if (bVar != null) {
            bVar.b.b(4, bVar.h);
        }
    }

    @CalledByNative
    public final void showDialog() {
        c3 c3Var = new c3(6, this);
        WindowAndroid windowAndroid = this.e;
        Context context = (Context) windowAndroid.k().get();
        bi1 bi1VarN = windowAndroid.n();
        VirtualCardEnrollmentFields virtualCardEnrollmentFields = this.d;
        String str = this.a;
        String str2 = this.b;
        VirtualCardEnrollmentDelegate virtualCardEnrollmentDelegate = this.c;
        Objects.requireNonNull(virtualCardEnrollmentDelegate);
        org.chromium.chrome.browser.autofill.settings.b bVar = new org.chromium.chrome.browser.autofill.settings.b(context, bi1VarN, virtualCardEnrollmentFields, str, str2, new c01(4, virtualCardEnrollmentDelegate), c3Var);
        this.f = bVar;
        bVar.a();
    }
}

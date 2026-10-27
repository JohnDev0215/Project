package org.chromium.components.autofill;

import J.N;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.view.ViewStructure;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AutofillProvider {
    public void a(long j, FormData formData) {
        N.MxVH2h8w(j, this, formData);
    }

    public abstract void b(SparseArray sparseArray);

    public abstract void c(ViewGroup viewGroup);

    public abstract void d(ViewStructure viewStructure, int i);

    public abstract void e();

    public abstract void f(WebContents webContents);

    public abstract boolean g();

    public abstract void onDidFillAutofillFormData();

    public abstract void onFocusChanged(boolean z, int i, float f, float f2, float f3, float f4);

    public abstract void onFormFieldDidChange(int i, float f, float f2, float f3, float f4);

    public abstract void onFormSubmitted(int i);

    public abstract void onTextFieldDidScroll(int i, float f, float f2, float f3, float f4);

    public abstract void reset();

    public abstract void setNativeAutofillProvider(long j);

    public abstract void startAutofillSession(FormData formData, int i, float f, float f2, float f3, float f4);
}

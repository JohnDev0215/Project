package org.chromium.components.autofill;

import J.N;
import java.util.ArrayList;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FormData {
    public final String a;
    public final String b;
    public final ArrayList c;
    public long d;

    public FormData(long j, String str, String str2, int i) {
        this.d = j;
        this.a = str;
        this.b = str2;
        this.c = new ArrayList(i);
        FormFieldData formFieldData = (FormFieldData) N.MPHorj3A(this.d, this);
        while (formFieldData != null) {
            this.c.add(formFieldData);
            formFieldData = (FormFieldData) N.MPHorj3A(this.d, this);
        }
    }

    public static FormData createFormData(long j, String str, String str2, int i) {
        return new FormData(j, str, str2, i);
    }

    private void onNativeDestroyed() {
        this.d = 0L;
    }
}

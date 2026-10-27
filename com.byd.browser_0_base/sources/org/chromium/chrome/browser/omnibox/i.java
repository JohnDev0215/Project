package org.chromium.chrome.browser.omnibox;

import J.N;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import defpackage.C0173a;
import defpackage.ko0;
import defpackage.lj1;
import defpackage.rj3;
import defpackage.xz;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.omnibox.UrlBar;
import org.chromium.chrome.browser.omnibox.j;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: UrlBarMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i implements UrlBar.d, UrlBar.e, TextWatcher {
    public final PropertyModel e;
    public Callback<Boolean> f;
    public boolean g;
    public rj3 h;
    public int i = 0;
    public int j = 0;
    public final ArrayList k = new ArrayList();
    public final ArrayList l = new ArrayList();

    public i(lj1 lj1Var, PropertyModel propertyModel) {
        this.e = propertyModel;
        this.f = lj1Var;
        propertyModel.p(j.e, new ko0(22, this));
        propertyModel.l(j.f, false);
        propertyModel.p(j.g, this);
        propertyModel.p(j.j, this);
        propertyModel.p(j.k, this);
        PropertyModel.p pVar = j.l;
        propertyModel.h(pVar);
        propertyModel.n(pVar, 3);
    }

    @Override // org.chromium.chrome.browser.omnibox.UrlBar.d
    public final String a() {
        Context context = xz.a;
        ClipData primaryClip = ((ClipboardManager) context.getSystemService("clipboard")).getPrimaryClip();
        if (primaryClip == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < primaryClip.getItemCount(); i++) {
            sb.append(primaryClip.getItemAt(i).coerceToText(context));
        }
        return N.M14CHwRm(sb.toString());
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.l;
            if (i >= arrayList.size()) {
                return;
            }
            ((TextWatcher) arrayList.get(i)).afterTextChanged(editable);
            i++;
        }
    }

    @Override // org.chromium.chrome.browser.omnibox.UrlBar.e
    public final void b(String str, String str2) {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.k;
            if (i >= arrayList.size()) {
                return;
            }
            ((UrlBar.e) arrayList.get(i)).b(str, str2);
            i++;
        }
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = 0;
        while (true) {
            ArrayList arrayList = this.l;
            if (i4 >= arrayList.size()) {
                return;
            }
            ((TextWatcher) arrayList.get(i4)).beforeTextChanged(charSequence, i, i2, i3);
            i4++;
        }
    }

    @Override // org.chromium.chrome.browser.omnibox.UrlBar.d
    public final String c(int i, int i2, String str) {
        int iIndexOf;
        int iIndexOf2;
        rj3 rj3Var = this.h;
        if (rj3Var == null || rj3Var.a == null || i != 0) {
            return null;
        }
        String strSubstring = str.substring(i, i2);
        try {
            URL url = new URL(this.h.a);
            rj3 rj3Var2 = this.h;
            CharSequence charSequence = rj3Var2.c;
            if (charSequence == null) {
                charSequence = rj3Var2.b;
            }
            String string = charSequence.toString();
            int iIndexOf3 = string.indexOf(url.getHost());
            if (iIndexOf3 != -1 && (iIndexOf = string.indexOf(47, iIndexOf3)) > 0) {
                string = string.substring(0, iIndexOf);
            }
            String strSubstring2 = this.h.a;
            int iIndexOf4 = strSubstring2.indexOf(url.getHost());
            if (iIndexOf4 != -1 && (iIndexOf2 = strSubstring2.indexOf(47, iIndexOf4)) > 0) {
                strSubstring2 = strSubstring2.substring(0, iIndexOf2);
            }
            if (strSubstring.startsWith(string) && i2 >= string.length()) {
                StringBuilder sbJ = C0173a.j(strSubstring2);
                sbJ.append(strSubstring.substring(string.length()));
                return sbJ.toString();
            }
        } catch (MalformedURLException unused) {
        }
        return null;
    }

    public final void d() {
        String str;
        String str2;
        boolean z = this.g;
        rj3 rj3Var = this.h;
        CharSequence charSequence = (z && (str = rj3Var.c) != null) ? str : rj3Var.b;
        CharSequence charSequence2 = (z || TextUtils.isEmpty(charSequence) || (str2 = this.h.a) == null) ? charSequence : str2;
        int i = this.g ? 0 : this.i;
        if (charSequence == null) {
            charSequence = "";
        }
        this.e.p(j.h, new j.b(i, this.h.e, this.j, charSequence, charSequence2));
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = 0;
        while (true) {
            ArrayList arrayList = this.l;
            if (i4 >= arrayList.size()) {
                return;
            }
            ((TextWatcher) arrayList.get(i4)).onTextChanged(charSequence, i, i2, i3);
            i4++;
        }
    }
}

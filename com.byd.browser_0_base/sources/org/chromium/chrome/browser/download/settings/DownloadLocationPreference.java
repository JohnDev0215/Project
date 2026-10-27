package org.chromium.chrome.browser.download.settings;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import androidx.preference.DialogPreference;
import defpackage.ma0;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.download.settings.a;

/* JADX INFO: loaded from: classes.dex */
public class DownloadLocationPreference extends DialogPreference implements a.InterfaceC0080a {
    public final b Y;

    public DownloadLocationPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.X = R$layout.download_location_preference;
        b bVar = new b(this.e, this);
        this.Y = bVar;
        bVar.c();
    }

    public final void Y() {
        b bVar = this.Y;
        int i = bVar.e;
        if (i < 0) {
            return;
        }
        ma0 ma0Var = (ma0) bVar.getItem(i);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) ma0Var.a);
        spannableStringBuilder.append((CharSequence) " ");
        spannableStringBuilder.append((CharSequence) ma0Var.b);
        spannableStringBuilder.setSpan(new StyleSpan(1), 0, ma0Var.a.length(), 33);
        R(spannableStringBuilder);
    }

    @Override // org.chromium.chrome.browser.download.settings.a.InterfaceC0080a
    public final void e() {
        Y();
    }

    @Override // org.chromium.chrome.browser.download.settings.a.InterfaceC0080a
    public final void g() {
        b bVar = this.Y;
        int i = bVar.e;
        int i2 = a.l;
        if (i == -1) {
            bVar.d();
        }
        Y();
    }
}

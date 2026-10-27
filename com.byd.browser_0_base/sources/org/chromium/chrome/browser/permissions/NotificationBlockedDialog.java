package org.chromium.chrome.browser.permissions;

import J.N;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.ContextThemeWrapper;
import android.widget.TextView;
import defpackage.bi1;
import defpackage.di1;
import defpackage.q11;
import defpackage.td2;
import defpackage.un1;
import defpackage.yn2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationBlockedDialog implements di1.a {
    public final bi1 e;
    public final Context f;
    public long g;
    public PropertyModel h;

    public NotificationBlockedDialog(long j, WindowAndroid windowAndroid) {
        this.g = j;
        this.f = (Context) windowAndroid.k().get();
        this.e = windowAndroid.n();
    }

    @CalledByNative
    public static NotificationBlockedDialog create(long j, WindowAndroid windowAndroid) {
        return new NotificationBlockedDialog(j, windowAndroid);
    }

    @Override // di1.a
    public final void b(int i) {
        N.Mw7abwHJ(this.g);
        this.g = 0L;
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        bi1 bi1Var = this.e;
        if (i == 0) {
            N.MWK1BN6b(this.g);
            bi1Var.b(1, this.h);
        } else if (i == 1) {
            N.MlY43xw2(this.g);
            bi1Var.b(2, this.h);
        }
    }

    @CalledByNative
    public final void dismissDialog() {
        this.e.b(4, this.h);
    }

    @CalledByNative
    public void show(String str, String str2, String str3, String str4, String str5) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int i = R$style.NotificationBlockedDialogContent;
        Context context = this.f;
        TextView textView = new TextView(new ContextThemeWrapper(context, i));
        spannableStringBuilder.append((CharSequence) str2);
        if (str5 != null) {
            spannableStringBuilder.append((CharSequence) " ");
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) str5);
            spannableStringBuilder.setSpan(new un1(context, new td2(13, this)), length, spannableStringBuilder.length(), 33);
        }
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, this);
        bVar.e(di1.c, str);
        bVar.e(di1.h, textView);
        bVar.e(di1.j, str3);
        bVar.e(di1.m, str4);
        bVar.b(di1.q, true);
        PropertyModel propertyModelA = bVar.a();
        this.h = propertyModelA;
        this.e.j(propertyModelA, 1, false);
    }

    @CalledByNative
    public final void showSettings() {
        Bundle bundle = new Bundle();
        bundle.putString("category", yn2.m(14));
        String name = SingleCategorySettings.class.getName();
        Intent intent = new Intent();
        Context context = this.f;
        intent.setClass(context, SettingsActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
            intent.addFlags(67108864);
        }
        intent.putExtra("show_fragment", name);
        intent.putExtra("show_fragment_args", bundle);
        ComponentName componentName = q11.a;
        try {
            context.startActivity(intent, null);
        } catch (ActivityNotFoundException unused) {
        }
    }
}

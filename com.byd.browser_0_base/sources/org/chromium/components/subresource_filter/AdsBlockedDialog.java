package org.chromium.components.subresource_filter;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
import defpackage.bi1;
import defpackage.di1;
import defpackage.es3;
import defpackage.se3;
import defpackage.un1;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes3.dex */
public class AdsBlockedDialog implements di1.a {
    public long e;
    public final Context f;
    public final bi1 g;
    public PropertyModel h;
    public un1 i;
    public final Handler j = new Handler(ThreadUtils.d());

    public AdsBlockedDialog(long j, WindowAndroid windowAndroid) {
        this.e = j;
        this.f = windowAndroid.i.get();
        this.g = windowAndroid.n();
    }

    @CalledByNative
    public static AdsBlockedDialog create(long j, WindowAndroid windowAndroid) {
        return new AdsBlockedDialog(j, windowAndroid);
    }

    @Override // di1.a
    public final void b(int i) {
        this.j.removeCallbacksAndMessages(null);
        N.MY4uokTa(this.e);
        this.e = 0L;
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i == 0) {
            N.M2x3Xdzi(this.e);
        }
        this.g.b(i == 0 ? 1 : 2, propertyModel);
    }

    @CalledByNative
    public void dismiss() {
        this.g.b(4, this.h);
    }

    @CalledByNative
    public void show(boolean z) {
        Context context = this.f;
        Resources resources = context.getResources();
        this.i = new un1(context, new se3(21, this));
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, this);
        bVar.d(di1.c, resources, R$string.blocked_ads_dialog_title);
        PropertyModel.r<CharSequence> rVar = di1.f;
        Resources resources2 = context.getResources();
        String string = resources2.getString(R$string.blocked_ads_dialog_message);
        String string2 = resources2.getString(R$string.blocked_ads_dialog_learn_more);
        SpannableString spannableString = new SpannableString(string2);
        spannableString.setSpan(this.i, 0, string2.length(), 17);
        bVar.e(rVar, TextUtils.expandTemplate(string, spannableString));
        bVar.d(di1.j, resources, R$string.blocked_ads_dialog_always_allow);
        bVar.d(di1.m, resources, R$string.cancel);
        bVar.b(di1.q, true);
        bVar.b(di1.x, true);
        PropertyModel propertyModelA = bVar.a();
        this.h = propertyModelA;
        if (z) {
            this.j.post(new es3(24, this));
        } else {
            this.g.j(propertyModelA, 0, false);
        }
    }
}

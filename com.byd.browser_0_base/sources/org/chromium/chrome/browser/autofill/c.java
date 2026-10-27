package org.chromium.chrome.browser.autofill;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.bi1;
import defpackage.di1;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AutofillSaveCardPromptBase.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c implements di1.a {
    public final PropertyModel e;
    public bi1 f;
    public Context g;
    public final View h;

    public c(Activity activity, int i, String str, int i2, String str2) {
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.autofill_save_card_base_layout, (ViewGroup) null);
        this.h = viewInflate;
        if (i != 0) {
            ViewStub viewStub = (ViewStub) viewInflate.findViewById(R$id.autofill_save_card_content_stub);
            viewStub.setLayoutResource(i);
            viewStub.inflate();
        }
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, this);
        bVar.e(di1.c, str);
        bVar.e(di1.h, viewInflate);
        bVar.e(di1.j, str2);
        bVar.d(di1.m, activity.getResources(), R$string.cancel);
        bVar.b(di1.q, false);
        bVar.b(di1.l, true);
        bVar.c(di1.u, 0);
        if (i2 != 0) {
            PropertyModel.r<Drawable> rVar = di1.e;
            if (i2 != 0) {
                bVar.e(rVar, AppCompatResources.a(activity, i2));
            }
        }
        this.e = bVar.a();
        this.g = activity;
    }
}

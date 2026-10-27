package org.chromium.chrome.browser.logo;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import defpackage.nd;
import defpackage.od;
import defpackage.v62;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.logo.LogoBridge;
import org.chromium.chrome.browser.logo.LogoView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: LogoViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e implements j.a<PropertyModel, View, v62> {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        v62 v62Var = (v62) obj2;
        LogoView logoView = (LogoView) ((View) obj);
        PropertyModel.o oVar = d.a;
        if (oVar == v62Var) {
            logoView.setAlpha(propertyModel.g(oVar));
            return;
        }
        PropertyModel.p pVar = d.b;
        if (pVar == v62Var) {
            ((ViewGroup.MarginLayoutParams) logoView.getLayoutParams()).topMargin = propertyModel.h(pVar);
            return;
        }
        PropertyModel.p pVar2 = d.c;
        if (pVar2 == v62Var) {
            ((ViewGroup.MarginLayoutParams) logoView.getLayoutParams()).bottomMargin = propertyModel.h(pVar2);
            return;
        }
        if (d.d == v62Var) {
            ObjectAnimator objectAnimator = logoView.i;
            if (objectAnimator != null) {
                objectAnimator.end();
                logoView.i = null;
                return;
            }
            return;
        }
        PropertyModel.n nVar = d.e;
        if (nVar == v62Var) {
            logoView.setVisibility(propertyModel.k(nVar) ? 0 : 8);
            return;
        }
        PropertyModel.n nVar2 = d.f;
        if (nVar2 == v62Var) {
            logoView.setAnimationEnabled(propertyModel.k(nVar2));
            return;
        }
        PropertyModel.r<LogoView.c> rVar = d.g;
        if (rVar == v62Var) {
            logoView.setClickHandler((LogoView.c) propertyModel.j(rVar));
            return;
        }
        boolean z = true;
        if (d.h == v62Var) {
            Bitmap bitmap = logoView.g;
            if (bitmap != null) {
                logoView.b(bitmap, null, true, false);
                i = 1;
            }
            if (i != 0) {
                return;
            }
            logoView.e = null;
            logoView.invalidate();
            logoView.q.e();
            return;
        }
        PropertyModel.r<LogoBridge.Logo> rVar2 = d.i;
        if (rVar2 == v62Var) {
            LogoBridge.Logo logo = (LogoBridge.Logo) propertyModel.j(rVar2);
            if (logo != null) {
                logoView.getClass();
                String str = logo.c;
                String string = TextUtils.isEmpty(str) ? null : logoView.getResources().getString(R$string.accessibility_google_doodle, str);
                if (TextUtils.isEmpty(logo.d) && TextUtils.isEmpty(logo.b)) {
                    z = false;
                }
                logoView.b(logo.a, string, false, z);
                return;
            }
            Bitmap bitmap2 = logoView.g;
            if (bitmap2 != null) {
                logoView.b(bitmap2, null, true, false);
                i = 1;
            }
            if (i != 0) {
                return;
            }
            logoView.e = null;
            logoView.invalidate();
            return;
        }
        PropertyModel.r<Bitmap> rVar3 = d.j;
        if (rVar3 == v62Var) {
            logoView.setDefaultGoogleLogo((Bitmap) propertyModel.j(rVar3));
            return;
        }
        if (d.k == v62Var) {
            logoView.e = null;
            logoView.invalidate();
            logoView.q.e();
            return;
        }
        PropertyModel.r<od> rVar4 = d.l;
        if (rVar4 == v62Var) {
            od odVar = (od) propertyModel.j(rVar4);
            logoView.q.c();
            logoView.h = new nd(odVar, Bitmap.Config.ARGB_8888);
            Matrix matrix = new Matrix();
            logoView.m = matrix;
            nd ndVar = logoView.h;
            logoView.a(ndVar.h, ndVar.i, matrix, false);
            logoView.h.setCallback(logoView);
            logoView.h.start();
        }
    }
}

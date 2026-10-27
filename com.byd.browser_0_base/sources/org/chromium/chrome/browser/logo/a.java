package org.chromium.chrome.browser.logo;

import J.N;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import defpackage.cl;
import defpackage.ll;
import defpackage.oj2;
import org.chromium.base.Callback;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: LogoCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public static final cl d = new cl(R$drawable.google_logo, R$color.google_logo_tint_color);
    public final b a;
    public final PropertyModel b;
    public LogoView c;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.logo.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LogoCoordinator.java */
    public interface InterfaceC0093a {
        void r();
    }

    public a(Context context, Callback callback, LogoView logoView, boolean z, ll.b bVar, ll.c cVar, boolean z2, InterfaceC0093a interfaceC0093a) {
        PropertyModel propertyModel = new PropertyModel(d.m);
        this.b = propertyModel;
        this.c = logoView;
        j.b(propertyModel, logoView, new e());
        this.a = new b(context, callback, propertyModel, z, bVar, cVar, z2, interfaceC0093a);
    }

    public final void a() {
        b bVar = this.a;
        LogoBridge logoBridge = bVar.h;
        if (logoBridge != null) {
            N.MwFfD4qv(logoBridge.a, logoBridge);
            logoBridge.a = 0L;
            bVar.h = null;
            bVar.i.b();
            bVar.i = null;
        }
        Profile profile = bVar.g;
        if (profile != null) {
            oj2.q(profile).i(bVar);
        }
        InterfaceC0093a interfaceC0093a = bVar.p;
        if (interfaceC0093a != null) {
            bVar.w.c(interfaceC0093a);
        }
        LogoView logoView = this.c;
        ObjectAnimator objectAnimator = logoView.i;
        if (objectAnimator != null) {
            objectAnimator.end();
            logoView.i = null;
        }
        logoView.q.b();
        this.c = null;
    }

    public final void b() {
        b bVar = this.a;
        if (bVar.g != null) {
            return;
        }
        bVar.g = Profile.d();
        bVar.c();
        if (bVar.r) {
            PropertyModel.r<Bitmap> rVar = d.j;
            Bitmap bitmapA = bVar.a(bVar.f);
            PropertyModel propertyModel = bVar.e;
            propertyModel.p(rVar, bitmapA);
            propertyModel.p(d.h, Boolean.TRUE);
            if (bVar.s) {
                bVar.b(false);
            }
        }
        oj2.q(bVar.g).a(bVar);
    }
}

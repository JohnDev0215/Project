package org.chromium.chrome.browser.logo;

import android.graphics.Bitmap;
import defpackage.od;
import defpackage.v62;
import org.chromium.chrome.browser.logo.LogoBridge;
import org.chromium.chrome.browser.logo.LogoView;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: LogoProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public interface d {
    public static final PropertyModel.o a;
    public static final PropertyModel.p b;
    public static final PropertyModel.p c;
    public static final PropertyModel.r<Boolean> d;
    public static final PropertyModel.n e;
    public static final PropertyModel.n f;
    public static final PropertyModel.r<LogoView.c> g;
    public static final PropertyModel.r<Boolean> h;
    public static final PropertyModel.r<LogoBridge.Logo> i;
    public static final PropertyModel.r<Bitmap> j;
    public static final PropertyModel.r<Boolean> k;
    public static final PropertyModel.r<od> l;
    public static final v62[] m;

    static {
        PropertyModel.o oVar = new PropertyModel.o();
        a = oVar;
        PropertyModel.p pVar = new PropertyModel.p();
        b = pVar;
        PropertyModel.p pVar2 = new PropertyModel.p();
        c = pVar2;
        PropertyModel.r<Boolean> rVar = new PropertyModel.r<>(null, true);
        d = rVar;
        PropertyModel.n nVar = new PropertyModel.n();
        e = nVar;
        PropertyModel.n nVar2 = new PropertyModel.n();
        f = nVar2;
        PropertyModel.r<LogoView.c> rVar2 = new PropertyModel.r<>();
        g = rVar2;
        PropertyModel.r<Boolean> rVar3 = new PropertyModel.r<>(null, true);
        h = rVar3;
        PropertyModel.r<LogoBridge.Logo> rVar4 = new PropertyModel.r<>();
        i = rVar4;
        PropertyModel.r<Bitmap> rVar5 = new PropertyModel.r<>();
        j = rVar5;
        PropertyModel.r<Boolean> rVar6 = new PropertyModel.r<>(null, true);
        k = rVar6;
        PropertyModel.r<od> rVar7 = new PropertyModel.r<>();
        l = rVar7;
        m = new v62[]{oVar, pVar, pVar2, rVar, nVar, nVar2, rVar2, rVar3, rVar4, rVar5, rVar6, rVar7};
    }
}

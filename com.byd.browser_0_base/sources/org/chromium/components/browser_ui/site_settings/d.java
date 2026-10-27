package org.chromium.components.browser_ui.site_settings;

import J.N;
import defpackage.bt3;
import defpackage.d02;
import defpackage.gx;
import defpackage.no;
import defpackage.vd;
import defpackage.ve3;
import defpackage.wy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: SiteDataCleaner.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {
    public static void a(Profile profile, bt3 bt3Var, ve3 ve3Var) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        List<f> list = bt3Var.f;
        vd vdVar = new vd(atomicInteger, list.size(), 2, ve3Var);
        Iterator<f> it = list.iterator();
        while (it.hasNext()) {
            b(profile, it.next(), vdVar);
        }
    }

    public static void b(Profile profile, f fVar, Runnable runnable) {
        String strH = fVar.e.h();
        N.Mks53EZS(profile, strH);
        N.MyQGLOqU(profile, strH);
        N.MSoF8bn2(profile, strH);
        Objects.requireNonNull(runnable);
        fVar.a(profile, new gx(29, runnable));
    }

    public static void c(Profile profile, f fVar) {
        Iterator it = fVar.g.values().iterator();
        while (it.hasNext()) {
            fVar.m(profile, ((wy) it.next()).e, 0);
        }
        Iterator it2 = fVar.h.values().iterator();
        while (it2.hasNext()) {
            fVar.m(profile, ((d02) it2.next()).h, 0);
        }
        Iterator it3 = new ArrayList(fVar.m).iterator();
        while (it3.hasNext()) {
            ((no) it3.next()).a(profile);
        }
    }
}

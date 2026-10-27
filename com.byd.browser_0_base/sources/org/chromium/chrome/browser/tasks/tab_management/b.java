package org.chromium.chrome.browser.tasks.tab_management;

import J.N;
import defpackage.dr;
import defpackage.oj2;
import defpackage.s21;
import defpackage.t21;
import defpackage.xe3;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.g;
import org.chromium.chrome.browser.tasks.tab_management.o1;

/* JADX INFO: compiled from: IphMessageService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b extends g {
    public final o1.d g;
    public final xe3 h;
    public final dr i;

    /* JADX INFO: compiled from: IphMessageService.java */
    public class a implements g.a {
        public final MessageCardView.c a;
        public final MessageCardView.a b;

        public a(s21 s21Var, t21 t21Var) {
            this.a = s21Var;
            this.b = t21Var;
        }
    }

    public b(o oVar) {
        super(2);
        this.i = new dr(7, this);
        this.g = oVar;
        Profile profileD = Profile.d();
        this.h = oj2.t((Profile) N.MD_ez$kP(profileD.b, profileD));
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.g
    public final void c(d dVar) {
        super.c(dVar);
        this.h.c(this.i);
    }
}

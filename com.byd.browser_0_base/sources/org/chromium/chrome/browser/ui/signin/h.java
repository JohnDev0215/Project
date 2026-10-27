package org.chromium.chrome.browser.ui.signin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import defpackage.C0175b;
import defpackage.bi1;
import defpackage.di1;
import defpackage.ke0;
import defpackage.zj;
import java.util.HashMap;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ConfirmSyncDataStateMachineDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h {
    public final bi1 a;
    public final Context b;
    public a c;
    public c d;
    public org.chromium.chrome.browser.ui.signin.b e;
    public org.chromium.chrome.browser.ui.signin.d f;

    /* JADX INFO: compiled from: ConfirmSyncDataStateMachineDelegate.java */
    public static final class a {
        public final b a;
        public final PropertyModel b;
        public final bi1 c;

        /* JADX WARN: Type inference failed for: r0v1, types: [T, android.view.View] */
        /* JADX WARN: Type inference failed for: r0v2, types: [T, org.chromium.chrome.browser.ui.signin.g] */
        /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.String] */
        public a(Context context, bi1 bi1Var, ke0 ke0Var) {
            ?? Inflate = LayoutInflater.from(context).inflate(R$layout.signin_progress_bar_dialog, (ViewGroup) null);
            this.a = ke0Var;
            HashMap mapE = PropertyModel.e(di1.B);
            PropertyModel.n nVar = di1.q;
            int i = zj.a;
            PropertyModel.a aVar = new PropertyModel.a();
            aVar.a = true;
            mapE.put(nVar, aVar);
            PropertyModel.r<String> rVar = di1.m;
            ?? string = context.getString(R$string.cancel);
            PropertyModel.g gVar = new PropertyModel.g();
            gVar.a = string;
            mapE.put(rVar, gVar);
            PropertyModel.r<View> rVar2 = di1.h;
            PropertyModel.g gVar2 = new PropertyModel.g();
            gVar2.a = Inflate;
            mapE.put(rVar2, gVar2);
            PropertyModel.l<di1.a> lVar = di1.a;
            ?? gVar3 = new g(this);
            PropertyModel.g gVar4 = new PropertyModel.g();
            gVar4.a = gVar3;
            PropertyModel propertyModelP = C0175b.p(mapE, lVar, gVar4, mapE);
            this.b = propertyModelP;
            this.c = bi1Var;
            bi1Var.j(propertyModelP, 1, false);
        }
    }

    /* JADX INFO: compiled from: ConfirmSyncDataStateMachineDelegate.java */
    public interface b {
    }

    /* JADX INFO: compiled from: ConfirmSyncDataStateMachineDelegate.java */
    public static final class c {
        public final d a;
        public final PropertyModel b;
        public final bi1 c;

        public c(Context context, bi1 bi1Var, e eVar) {
            this.a = eVar;
            PropertyModel.b bVar = new PropertyModel.b(di1.B);
            bVar.e(di1.c, context.getString(R$string.sign_in_timeout_title));
            bVar.e(di1.f, context.getString(R$string.sign_in_timeout_message));
            bVar.b(di1.q, true);
            bVar.e(di1.j, context.getString(R$string.try_again));
            bVar.e(di1.m, context.getString(R$string.cancel));
            bVar.e(di1.a, new i(this));
            PropertyModel propertyModelA = bVar.a();
            this.b = propertyModelA;
            this.c = bi1Var;
            bi1Var.j(propertyModelA, 1, false);
        }
    }

    /* JADX INFO: compiled from: ConfirmSyncDataStateMachineDelegate.java */
    public interface d {
    }

    public h(Context context, bi1 bi1Var) {
        this.b = context;
        this.a = bi1Var;
    }

    public final void a() {
        a aVar = this.c;
        if (aVar != null) {
            aVar.c.b(0, aVar.b);
            this.c = null;
        }
        c cVar = this.d;
        if (cVar != null) {
            cVar.c.b(0, cVar.b);
            this.d = null;
        }
        org.chromium.chrome.browser.ui.signin.b bVar = this.e;
        if (bVar != null) {
            bVar.d.b(0, bVar.c);
            this.e = null;
        }
        org.chromium.chrome.browser.ui.signin.d dVar = this.f;
        if (dVar != null) {
            dVar.c.b(0, dVar.b);
            this.f = null;
        }
    }
}

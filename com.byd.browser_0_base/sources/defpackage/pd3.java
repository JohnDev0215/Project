package defpackage;

import android.content.Context;
import android.graphics.RectF;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: TopToolbarOverlayCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pd3 implements je2 {
    public final vd3 e;
    public final nw f;
    public final td3 g;

    public pd3(Context context, z51 z51Var, Callback callback, aq1 aq1Var, jj jjVar, nw2 nw2Var, xd3 xd3Var, int i, boolean z) {
        PropertyModel.b bVar = new PropertyModel.b(ud3.k);
        bVar.c(ud3.c, R$id.control_container);
        bVar.c(ud3.g, R$drawable.modern_location_bar);
        bVar.b(ud3.h, true);
        bVar.f(ud3.i, 0.0f);
        bVar.f(ud3.j, jjVar.a() + jjVar.e());
        bVar.b(ud3.a, false);
        PropertyModel propertyModelA = bVar.a();
        vd3 vd3Var = new vd3(nw2Var);
        this.e = vd3Var;
        this.f = z51Var.s(propertyModelA, vd3Var, new cg(17));
        this.g = new td3(propertyModelA, context, z51Var, callback, aq1Var, jjVar, xd3Var, i, z);
    }

    @Override // defpackage.je2
    public final boolean G(long j) {
        return false;
    }

    @Override // defpackage.je2
    public final ke2 U(RectF rectF, ResourceManager resourceManager) {
        return this.e;
    }

    @Override // defpackage.je2
    public final boolean a() {
        return false;
    }

    public final void d(boolean z) {
        td3 td3Var = this.g;
        td3Var.m = z;
        td3Var.b();
        td3Var.d();
    }

    @Override // defpackage.je2
    public final boolean o0() {
        return false;
    }

    @Override // defpackage.je2
    public final boolean q1() {
        this.g.getClass();
        return true;
    }

    @Override // defpackage.je2
    public final EventFilter x() {
        return null;
    }

    @Override // defpackage.je2
    public final void N() {
    }

    @Override // defpackage.je2
    public final void f0(ArrayList arrayList) {
    }

    @Override // defpackage.je2
    public final void M0(float f, float f2, float f3, int i) {
    }
}

package d1;

import F0.AbstractC0191a;
import T.C0757o;
import android.content.Context;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import androidx.compose.ui.node.Owner;
import l3.P;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class o extends i {

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final View f13328H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final x0.d f13329I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public d0.j f13330J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public InterfaceC2782k f13331K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public InterfaceC2782k f13332L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public InterfaceC2782k f13333M;

    public o(Context context, InterfaceC2782k interfaceC2782k, C0757o c0757o, d0.k kVar, int i7, Owner owner) {
        View view = (View) interfaceC2782k.b(context);
        x0.d dVar = new x0.d();
        super(context, c0757o, i7, dVar, view, owner);
        this.f13328H = view;
        this.f13329I = dVar;
        setClipChildren(false);
        String strValueOf = String.valueOf(i7);
        Object objC = kVar != null ? kVar.c(strValueOf) : null;
        SparseArray<Parcelable> sparseArray = objC instanceof SparseArray ? (SparseArray) objC : null;
        if (sparseArray != null) {
            view.restoreHierarchyState(sparseArray);
        }
        if (kVar != null) {
            setSavableRegistryEntry(kVar.a(strValueOf, new h(this, 2)));
        }
        C1057a c1057a = C1057a.f13278q;
        this.f13331K = c1057a;
        this.f13332L = c1057a;
        this.f13333M = c1057a;
    }

    public static final void l(o oVar) {
        oVar.setSavableRegistryEntry(null);
    }

    private final void setSavableRegistryEntry(d0.j jVar) {
        d0.j jVar2 = this.f13330J;
        if (jVar2 != null) {
            ((P) jVar2).H();
        }
        this.f13330J = jVar;
    }

    public final x0.d getDispatcher() {
        return this.f13329I;
    }

    public final InterfaceC2782k getReleaseBlock() {
        return this.f13333M;
    }

    public final InterfaceC2782k getResetBlock() {
        return this.f13332L;
    }

    public /* bridge */ /* synthetic */ AbstractC0191a getSubCompositionView() {
        return null;
    }

    public final InterfaceC2782k getUpdateBlock() {
        return this.f13331K;
    }

    public View getViewRoot() {
        return this;
    }

    public final void setReleaseBlock(InterfaceC2782k interfaceC2782k) {
        this.f13333M = interfaceC2782k;
        setRelease(new h(this, 3));
    }

    public final void setResetBlock(InterfaceC2782k interfaceC2782k) {
        this.f13332L = interfaceC2782k;
        setReset(new h(this, 4));
    }

    public final void setUpdateBlock(InterfaceC2782k interfaceC2782k) {
        this.f13331K = interfaceC2782k;
        setUpdate(new h(this, 5));
    }
}

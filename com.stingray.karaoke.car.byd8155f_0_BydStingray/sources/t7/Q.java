package T7;

import F8.AbstractC0287w;
import F8.W;
import Q7.AbstractC0662o;
import Q7.C0661n;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0658k;
import Q7.InterfaceC0659l;
import Q7.U;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import n7.AbstractC2000o;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public class Q extends S implements Q7.J, U {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f8822r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f8823s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f8824t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final boolean f8825u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final AbstractC0287w f8826v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final Q f8827w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q(InterfaceC0649b interfaceC0649b, Q q4, int i7, R7.h hVar, C2061f c2061f, AbstractC0287w abstractC0287w, boolean z6, boolean z10, boolean z11, AbstractC0287w abstractC0287w2, Q7.N n10) {
        super(interfaceC0649b, hVar, c2061f, abstractC0287w, n10);
        A7.m.f(interfaceC0649b, "containingDeclaration");
        A7.m.f(hVar, "annotations");
        A7.m.f(c2061f, "name");
        A7.m.f(abstractC0287w, "outType");
        A7.m.f(n10, BYDAutoMultimediaDevice.PARAM_SOURCE);
        this.f8822r = i7;
        this.f8823s = z6;
        this.f8824t = z10;
        this.f8825u = z11;
        this.f8826v = abstractC0287w2;
        this.f8827w = q4 == null ? this : q4;
    }

    @Override // Q7.U
    public final boolean I() {
        return false;
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.K(this, obj);
    }

    public Q c1(O7.g gVar, C2061f c2061f, int i7) {
        R7.h hVarK = k();
        A7.m.e(hVarK, "annotations");
        AbstractC0287w abstractC0287wB = b();
        A7.m.e(abstractC0287wB, "type");
        boolean zD1 = d1();
        Q7.O o = Q7.N.c;
        return new Q(gVar, null, i7, hVarK, c2061f, abstractC0287wB, zD1, this.f8824t, this.f8825u, this.f8826v, o);
    }

    public final boolean d1() {
        return this.f8823s && ((InterfaceC0650c) o()).N() != 2;
    }

    @Override // Q7.InterfaceC0660m, Q7.InterfaceC0670x
    public final C0661n e() {
        C0661n c0661n = AbstractC0662o.f7192f;
        A7.m.e(c0661n, "LOCAL");
        return c0661n;
    }

    @Override // T7.AbstractC0795n, Q7.InterfaceC0657j
    /* JADX INFO: renamed from: e1, reason: merged with bridge method [inline-methods] */
    public final InterfaceC0649b o() {
        InterfaceC0657j interfaceC0657jO = super.o();
        A7.m.d(interfaceC0657jO, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableDescriptor");
        return (InterfaceC0649b) interfaceC0657jO;
    }

    @Override // Q7.P
    public final InterfaceC0658k f(W w10) {
        A7.m.f(w10, "substitutor");
        if (w10.f2455a.e()) {
            return this;
        }
        throw new UnsupportedOperationException();
    }

    @Override // T7.AbstractC0795n
    /* JADX INFO: renamed from: f1, reason: merged with bridge method [inline-methods] */
    public final Q b1() {
        Q q4 = this.f8827w;
        return q4 == this ? this : q4.b1();
    }

    @Override // Q7.InterfaceC0649b
    public final Collection q() {
        Collection collectionQ = o().q();
        A7.m.e(collectionQ, "containingDeclaration.overriddenDescriptors");
        Collection collection = collectionQ;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(collection, 10));
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add((Q) ((InterfaceC0649b) it.next()).D0().get(this.f8822r));
        }
        return arrayList;
    }

    @Override // Q7.U
    public final /* bridge */ /* synthetic */ t8.g x0() {
        return null;
    }
}

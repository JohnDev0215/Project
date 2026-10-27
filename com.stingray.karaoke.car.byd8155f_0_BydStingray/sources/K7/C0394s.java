package K7;

import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import java.util.ArrayList;
import java.util.Collection;
import n7.AbstractC1998m;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: K7.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0394s extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3832m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0398w f3833n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0394s(C0398w c0398w, int i7) {
        super(0);
        this.f3832m = i7;
        this.f3833n = c0398w;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f3832m) {
            case 0:
                C0398w c0398w = this.f3833n;
                c0398w.getClass();
                H7.w[] wVarArr = C0398w.l;
                H7.w wVar = wVarArr[14];
                Object objA = c0398w.f3849j.a();
                A7.m.e(objA, "<get-allNonStaticMembers>(...)");
                H7.w wVar2 = wVarArr[15];
                Object objA2 = c0398w.f3850k.a();
                A7.m.e(objA2, "<get-allStaticMembers>(...)");
                return AbstractC1998m.n0((Collection) objA, (Collection) objA2);
            case 1:
                C0398w c0398w2 = this.f3833n;
                c0398w2.getClass();
                H7.w[] wVarArr2 = C0398w.l;
                H7.w wVar3 = wVarArr2[10];
                Object objA3 = c0398w2.f3845f.a();
                A7.m.e(objA3, "<get-declaredNonStaticMembers>(...)");
                H7.w wVar4 = wVarArr2[12];
                Object objA4 = c0398w2.f3847h.a();
                A7.m.e(objA4, "<get-inheritedNonStaticMembers>(...)");
                return AbstractC1998m.n0((Collection) objA3, (Collection) objA4);
            case 2:
                C0398w c0398w3 = this.f3833n;
                c0398w3.getClass();
                H7.w[] wVarArr3 = C0398w.l;
                H7.w wVar5 = wVarArr3[11];
                Object objA5 = c0398w3.f3846g.a();
                A7.m.e(objA5, "<get-declaredStaticMembers>(...)");
                H7.w wVar6 = wVarArr3[13];
                Object objA6 = c0398w3.f3848i.a();
                A7.m.e(objA6, "<get-inheritedStaticMembers>(...)");
                return AbstractC1998m.n0((Collection) objA5, (Collection) objA6);
            case 3:
                return A0.d(this.f3833n.a());
            case 4:
                C0398w c0398w4 = this.f3833n;
                c0398w4.getClass();
                H7.w[] wVarArr4 = C0398w.l;
                H7.w wVar7 = wVarArr4[10];
                Object objA7 = c0398w4.f3845f.a();
                A7.m.e(objA7, "<get-declaredNonStaticMembers>(...)");
                H7.w wVar8 = wVarArr4[11];
                Object objA8 = c0398w4.f3846g.a();
                A7.m.e(objA8, "<get-declaredStaticMembers>(...)");
                return AbstractC1998m.n0((Collection) objA7, (Collection) objA8);
            case 5:
                y8.n nVarL0 = this.f3833n.a().l0();
                A7.m.e(nVarL0, "descriptor.unsubstitutedInnerClassesScope");
                Collection collectionI = h6.f.i(nVarL0, null, 3);
                ArrayList<InterfaceC0657j> arrayList = new ArrayList();
                for (Object obj : collectionI) {
                    if (!r8.d.m((InterfaceC0657j) obj)) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                for (InterfaceC0657j interfaceC0657j : arrayList) {
                    InterfaceC0652e interfaceC0652e = interfaceC0657j instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0657j : null;
                    Class clsJ = interfaceC0652e != null ? A0.j(interfaceC0652e) : null;
                    C0401z c0401z = clsJ != null ? new C0401z(clsJ) : null;
                    if (c0401z != null) {
                        arrayList2.add(c0401z);
                    }
                }
                return arrayList2;
            default:
                Collection<InterfaceC0652e> collectionX = this.f3833n.a().X();
                A7.m.e(collectionX, "descriptor.sealedSubclasses");
                ArrayList arrayList3 = new ArrayList();
                for (InterfaceC0652e interfaceC0652e2 : collectionX) {
                    A7.m.d(interfaceC0652e2, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                    Class clsJ2 = A0.j(interfaceC0652e2);
                    C0401z c0401z2 = clsJ2 != null ? new C0401z(clsJ2) : null;
                    if (c0401z2 != null) {
                        arrayList3.add(c0401z2);
                    }
                }
                return arrayList3;
        }
    }
}

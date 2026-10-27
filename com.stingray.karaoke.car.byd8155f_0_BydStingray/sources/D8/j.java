package D8;

import A7.A;
import H7.InterfaceC0320e;
import d8.C1127n;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j extends A7.i implements InterfaceC2782k {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f1233t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(int i7, int i10, Object obj) {
        super(i7, obj);
        this.f1233t = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f1233t) {
            case 0:
                G8.f fVar = (G8.f) obj;
                A7.m.f(fVar, "p0");
                return new g((k) this.f455m, fVar);
            case 1:
                C2061f c2061f = (C2061f) obj;
                A7.m.f(c2061f, "p0");
                return C1127n.v((C1127n) this.f455m, c2061f);
            default:
                C2061f c2061f2 = (C2061f) obj;
                A7.m.f(c2061f2, "p0");
                return C1127n.w((C1127n) this.f455m, c2061f2);
        }
    }

    @Override // A7.AbstractC0056d, H7.InterfaceC0317b
    public final String getName() {
        switch (this.f1233t) {
            case 0:
                return "<init>";
            case 1:
                return "searchMethodsByNameWithoutBuiltinMagic";
            default:
                return "searchMethodsInSupertypesWithoutBuiltinMagic";
        }
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0320e h() {
        switch (this.f1233t) {
        }
        return A.f443a.b(C1127n.class);
    }

    @Override // A7.AbstractC0056d
    public final String i() {
        switch (this.f1233t) {
            case 0:
                return "<init>(Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedClassDescriptor;Lorg/jetbrains/kotlin/types/checker/KotlinTypeRefiner;)V";
            case 1:
                return "searchMethodsByNameWithoutBuiltinMagic(Lorg/jetbrains/kotlin/name/Name;)Ljava/util/Collection;";
            default:
                return "searchMethodsInSupertypesWithoutBuiltinMagic(Lorg/jetbrains/kotlin/name/Name;)Ljava/util/Collection;";
        }
    }
}

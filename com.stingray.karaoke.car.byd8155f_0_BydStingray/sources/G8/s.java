package G8;

import A7.A;
import F8.AbstractC0287w;
import H7.InterfaceC0320e;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s extends A7.i implements InterfaceC2785n {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f2742t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ s(int i7, int i10, Object obj) {
        super(i7, obj);
        this.f2742t = i10;
    }

    @Override // A7.AbstractC0056d, H7.InterfaceC0317b
    public final String getName() {
        switch (this.f2742t) {
            case 0:
                return "isStrictSupertype";
            default:
                return "equalTypes";
        }
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0320e h() {
        switch (this.f2742t) {
            case 0:
                return A.f443a.b(t.class);
            default:
                return A.f443a.b(l.class);
        }
    }

    @Override // A7.AbstractC0056d
    public final String i() {
        switch (this.f2742t) {
            case 0:
                return "isStrictSupertype(Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;)Z";
            default:
                return "equalTypes(Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;)Z";
        }
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f2742t) {
            case 0:
                AbstractC0287w abstractC0287w = (AbstractC0287w) obj;
                AbstractC0287w abstractC0287w2 = (AbstractC0287w) obj2;
                A7.m.f(abstractC0287w, "p0");
                A7.m.f(abstractC0287w2, "p1");
                ((t) this.f455m).getClass();
                k.f2736b.getClass();
                l lVar = j.f2735b;
                return Boolean.valueOf(lVar.b(abstractC0287w, abstractC0287w2) && !lVar.b(abstractC0287w2, abstractC0287w));
            default:
                AbstractC0287w abstractC0287w3 = (AbstractC0287w) obj;
                AbstractC0287w abstractC0287w4 = (AbstractC0287w) obj2;
                A7.m.f(abstractC0287w3, "p0");
                A7.m.f(abstractC0287w4, "p1");
                return Boolean.valueOf(((l) this.f455m).a(abstractC0287w3, abstractC0287w4));
        }
    }
}

package g8;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: g8.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1320j extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f15011m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f15012n;
    public final /* synthetic */ String o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1320j(String str, int i7, String str2) {
        super(1);
        this.f15011m = i7;
        this.f15012n = str;
        this.o = str2;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f15011m) {
            case 0:
                C1324n c1324n = (C1324n) obj;
                A7.m.f(c1324n, "$this$function");
                C1315e c1315e = AbstractC1321k.f15014b;
                String str = this.f15012n;
                c1324n.a(str, c1315e);
                C1315e c1315e2 = AbstractC1321k.f15013a;
                c1324n.a(this.o, c1315e, c1315e, c1315e2, c1315e2);
                c1324n.b(str, c1315e2);
                break;
            case 1:
                C1324n c1324n2 = (C1324n) obj;
                A7.m.f(c1324n2, "$this$function");
                C1315e c1315e3 = AbstractC1321k.f15014b;
                String str2 = this.f15012n;
                c1324n2.a(str2, c1315e3);
                c1324n2.a(this.o, c1315e3, c1315e3, c1315e3);
                c1324n2.b(str2, c1315e3);
                break;
            case 2:
                C1324n c1324n3 = (C1324n) obj;
                A7.m.f(c1324n3, "$this$function");
                C1315e c1315e4 = AbstractC1321k.f15014b;
                String str3 = this.f15012n;
                c1324n3.a(str3, c1315e4);
                C1315e c1315e5 = AbstractC1321k.c;
                C1315e c1315e6 = AbstractC1321k.f15013a;
                c1324n3.a(this.o, c1315e4, c1315e4, c1315e5, c1315e6);
                c1324n3.b(str3, c1315e6);
                break;
            case 3:
                C1324n c1324n4 = (C1324n) obj;
                A7.m.f(c1324n4, "$this$function");
                C1315e c1315e7 = AbstractC1321k.f15014b;
                String str4 = this.f15012n;
                c1324n4.a(str4, c1315e7);
                C1315e c1315e8 = AbstractC1321k.c;
                c1324n4.a(str4, c1315e8);
                C1315e c1315e9 = AbstractC1321k.f15013a;
                c1324n4.a(this.o, c1315e7, c1315e8, c1315e8, c1315e9);
                c1324n4.b(str4, c1315e9);
                break;
            case 4:
                C1324n c1324n5 = (C1324n) obj;
                A7.m.f(c1324n5, "$this$function");
                C1315e c1315e10 = AbstractC1321k.c;
                c1324n5.a(this.f15012n, c1315e10);
                c1324n5.b(this.o, AbstractC1321k.f15014b, c1315e10);
                break;
            default:
                C1324n c1324n6 = (C1324n) obj;
                A7.m.f(c1324n6, "$this$function");
                c1324n6.a(this.f15012n, AbstractC1321k.f15013a);
                c1324n6.b(this.o, AbstractC1321k.f15014b, AbstractC1321k.c);
                break;
        }
        return m7.q.f19267a;
    }
}

package G;

import A.InterfaceC0033p0;
import E.C0149f;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: G.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0300j extends s7.i implements InterfaceC2785n {
    public A7.v l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public A7.z f2585m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public A7.x f2586n;
    public float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f2587p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f2588q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f2589r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f2590s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public /* synthetic */ Object f2591t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f2592u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ a1.b f2593v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ C0149f f2594w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ int f2595x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0300j(int i7, a1.b bVar, C0149f c0149f, int i10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f2592u = i7;
        this.f2593v = bVar;
        this.f2594w = c0149f;
        this.f2595x = i10;
    }

    public static final boolean m(boolean z6, C0149f c0149f, int i7, int i10) {
        if (z6) {
            if (c0149f.b() <= i7 && (c0149f.b() != i7 || c0149f.f1351a.d.c.i() <= i10)) {
                return false;
            }
        } else if (c0149f.b() >= i7 && (c0149f.b() != i7 || c0149f.f1351a.d.c.i() >= i10)) {
            return false;
        }
        return true;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0300j c0300j = new C0300j(this.f2592u, this.f2593v, this.f2594w, this.f2595x, interfaceC2189c);
        c0300j.f2591t = obj;
        return c0300j;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0300j) create((InterfaceC0033p0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x026d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x00c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:71:0x0183 -> B:150:0x018a). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r37) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 662
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: G.C0300j.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

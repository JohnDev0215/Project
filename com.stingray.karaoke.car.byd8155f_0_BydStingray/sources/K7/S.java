package K7;

/* JADX INFO: loaded from: classes.dex */
public final class S extends B {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f3746g;
    public final t0 c;
    public final t0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u0 f3747e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u0 f3748f;

    static {
        A7.B b10 = A7.A.f443a;
        f3746g = new H7.w[]{b10.h(new A7.s(b10.b(S.class), "kotlinClass", "getKotlinClass()Lorg/jetbrains/kotlin/descriptors/runtime/components/ReflectKotlinClass;")), b10.h(new A7.s(b10.b(S.class), "scope", "getScope()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;")), b10.h(new A7.s(b10.b(S.class), "multifileFacade", "getMultifileFacade()Ljava/lang/Class;")), b10.h(new A7.s(b10.b(S.class), "metadata", "getMetadata()Lkotlin/Triple;")), b10.h(new A7.s(b10.b(S.class), "members", "getMembers()Ljava/util/Collection;"))};
    }

    public S(U u4) {
        super(u4);
        this.c = v0.n(null, new O(u4, 0));
        this.d = v0.n(null, new Q(this, 1));
        this.f3747e = new u0(new P(this, u4));
        this.f3748f = new u0(new Q(this, 0));
        v0.n(null, new P(u4, this));
    }
}

package c7;

/* JADX INFO: loaded from: classes.dex */
public final class b implements InterfaceC1035a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f12409a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC1035a f12410b;
    public final /* synthetic */ A9.b c;

    public b(A9.b bVar, String str, InterfaceC1035a interfaceC1035a) {
        this.c = bVar;
        this.f12409a = str;
        this.f12410b = interfaceC1035a;
    }

    @Override // c7.InterfaceC1035a
    public final void q(Object... objArr) {
        this.c.R0(this.f12409a, this);
        this.f12410b.q(objArr);
    }
}

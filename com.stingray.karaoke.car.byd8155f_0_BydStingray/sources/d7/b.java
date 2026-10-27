package d7;

import c7.InterfaceC1035a;

/* JADX INFO: loaded from: classes.dex */
public final class b implements InterfaceC1035a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13488a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j f13489b;

    public /* synthetic */ b(j jVar, int i7) {
        this.f13488a = i7;
        this.f13489b = jVar;
    }

    @Override // c7.InterfaceC1035a
    public final void q(Object... objArr) {
        switch (this.f13488a) {
            case 0:
                this.f13489b.q("transport closed");
                break;
            default:
                this.f13489b.q("socket closed");
                break;
        }
    }
}

package o3;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: o3.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2037j extends n3.z {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C2036i f20466g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final b0.b f20467h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public InterfaceC2782k f20468i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public InterfaceC2782k f20469j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public InterfaceC2782k f20470k;
    public InterfaceC2782k l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public InterfaceC2782k f20471m;

    public C2037j(C2036i c2036i, String str, b0.b bVar) {
        super(c2036i, str);
        this.f20466g = c2036i;
        this.f20467h = bVar;
    }

    @Override // n3.z
    public final n3.y c() {
        C2035h c2035h = (C2035h) super.c();
        c2035h.f20461v = this.f20468i;
        c2035h.f20462w = this.f20469j;
        c2035h.f20463x = this.f20470k;
        c2035h.f20464y = this.l;
        c2035h.f20465z = this.f20471m;
        return c2035h;
    }

    @Override // n3.z
    public final n3.y e() {
        return new C2035h(this.f20466g, this.f20467h);
    }
}

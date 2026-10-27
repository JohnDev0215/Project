package l1;

/* JADX INFO: renamed from: l1.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1732c implements InterfaceC1733d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f18416a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f18417b;
    public String c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f18418e;

    @Override // l1.InterfaceC1733d
    public final float value() {
        float f10 = this.d;
        if (f10 >= this.f18418e) {
            this.f18416a = true;
        }
        if (!this.f18416a) {
            this.d = f10 + 1.0f;
        }
        return this.d;
    }
}

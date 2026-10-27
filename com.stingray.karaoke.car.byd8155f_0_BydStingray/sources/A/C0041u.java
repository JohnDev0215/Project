package A;

/* JADX INFO: renamed from: A.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0041u implements InterfaceC0033p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0043v f275a;

    public C0041u(C0043v c0043v) {
        this.f275a = c0043v;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [A7.o, z7.k] */
    @Override // A.InterfaceC0033p0
    public final float a(float f10) {
        if (Float.isNaN(f10)) {
            return 0.0f;
        }
        C0043v c0043v = this.f275a;
        float fFloatValue = ((Number) c0043v.f281a.b(Float.valueOf(f10))).floatValue();
        c0043v.f283e.setValue(Boolean.valueOf(fFloatValue > 0.0f));
        c0043v.f284f.setValue(Boolean.valueOf(fFloatValue < 0.0f));
        return fFloatValue;
    }
}

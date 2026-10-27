package p;

/* JADX INFO: loaded from: classes.dex */
public final class s6 implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;

    public /* synthetic */ s6(wy1 wy1Var, int i) {
        this.a = i;
        this.b = wy1Var;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.b;
        switch (i) {
            case 0:
                jx jxVar = (jx) xy1Var.get();
                v03.j(jxVar, "configProvider");
                return new com.spotify.remoteconfig.a(false, false, false, false, false, true, false, true, true, jxVar);
            case 1:
                vi0 vi0Var = (vi0) xy1Var.get();
                v03.j(vi0Var, "eventPublisher");
                return new kj0(vi0Var);
            case 2:
                return new an((vi0) xy1Var.get());
            default:
                return new re2((e52) xy1Var.get());
        }
    }
}

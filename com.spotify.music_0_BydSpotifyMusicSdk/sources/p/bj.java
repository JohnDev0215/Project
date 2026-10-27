package p;

import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class bj implements ny1, pz0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ my1 b;

    public /* synthetic */ bj(my1 my1Var, int i) {
        this.a = i;
        this.b = my1Var;
    }

    @Override // p.pz0
    public final FunctionReferenceImpl a() {
        int i = this.a;
        my1 my1Var = this.b;
        switch (i) {
            case 0:
                return new FunctionReferenceImpl(1, (com.spotify.automotive.remoteconfig.a) my1Var, com.spotify.automotive.remoteconfig.a.class, "parse", "parse(Lcom/spotify/remoteconfig/runtime/PropertyParser;)Lcom/spotify/automotive/remoteconfig/AutomotiveConnectionTypeFlagsProperties;", 0);
            case 1:
            default:
                return new FunctionReferenceImpl(1, (com.spotify.remoteconfig.resolverimpl.a) my1Var, com.spotify.remoteconfig.resolverimpl.a.class, "parse", "parse(Lcom/spotify/remoteconfig/runtime/PropertyParser;)Lcom/spotify/remoteconfig/resolverimpl/RemoteConfigSnapshotIdProperties;", 0);
            case 2:
                return new FunctionReferenceImpl(1, (com.spotify.remoteconfig.a) my1Var, com.spotify.remoteconfig.a.class, "parse", "parse(Lcom/spotify/remoteconfig/runtime/PropertyParser;)Lcom/spotify/remoteconfig/AndroidAutomotiveAppProperties;", 0);
        }
    }

    public final boolean equals(Object obj) {
        switch (this.a) {
            case 0:
                if ((obj instanceof ny1) && (obj instanceof pz0)) {
                    break;
                }
                break;
            case 1:
            default:
                if ((obj instanceof ny1) && (obj instanceof pz0)) {
                    break;
                }
                break;
            case 2:
                if ((obj instanceof ny1) && (obj instanceof pz0)) {
                    break;
                }
                break;
        }
        return v03.b(a(), ((pz0) obj).a());
    }

    public final int hashCode() {
        switch (this.a) {
        }
        return a().hashCode();
    }
}

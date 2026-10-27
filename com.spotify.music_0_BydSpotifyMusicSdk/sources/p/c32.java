package p;

import com.spotify.base.java.logging.Logger;
import com.spotify.remoteconfig.NativeRemoteConfigImpl;

/* JADX INFO: loaded from: classes.dex */
public final class c32 implements sb2 {
    public final NativeRemoteConfigImpl a;

    public c32(a42 a42Var) {
        v03.j(a42Var, "esperantoResolver");
        gm1 gm1Var = NativeRemoteConfigImpl.Companion;
        d32 d32Var = new d32(a42Var);
        gm1Var.getClass();
        NativeRemoteConfigImpl nativeRemoteConfigImplCreateInternal = NativeRemoteConfigImpl.createInternal(d32Var);
        nativeRemoteConfigImplCreateInternal.initialize();
        this.a = nativeRemoteConfigImplCreateInternal;
    }

    @Override // p.sb2
    public final void shutdown() {
        Logger.a("RemoteConfigNativeResolverService shutdown called", new Object[0]);
        this.a.destroy();
        Logger.a("RemoteConfigNativeResolverService shutdown completed", new Object[0]);
    }
}

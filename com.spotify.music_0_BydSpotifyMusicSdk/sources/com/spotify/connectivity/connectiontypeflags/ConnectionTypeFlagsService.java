package com.spotify.connectivity.connectiontypeflags;

import com.spotify.remoteconfig.b;
import p.sb2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypeFlagsService implements ConnectionTypeFlagsApi, sb2 {
    private final ConnectionTypePropertiesWriter flags;
    private final b props;

    public ConnectionTypeFlagsService(b bVar, ConnectionTypePropertiesWriter connectionTypePropertiesWriter) {
        v03.j(bVar, "props");
        v03.j(connectionTypePropertiesWriter, "flags");
        this.props = bVar;
        this.flags = connectionTypePropertiesWriter;
        bVar.b();
        connectionTypePropertiesWriter.storeNetCapabilitiesValidatedDisabled(bVar.b());
        connectionTypePropertiesWriter.storeShouldUseSingleThread(bVar.a());
    }

    /* JADX INFO: renamed from: getApi, reason: merged with bridge method [inline-methods] */
    public ConnectionTypeFlagsApi m140getApi() {
        return this;
    }

    public final ConnectionTypePropertiesWriter getFlags() {
        return this.flags;
    }

    public final b getProps() {
        return this.props;
    }

    @Override // p.sb2
    public void shutdown() {
    }
}

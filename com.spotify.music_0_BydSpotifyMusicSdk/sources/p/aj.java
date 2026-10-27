package p;

import com.spotify.connectivity.connectiontypeflags.ConnectionTypePropertiesWriter;

/* JADX INFO: loaded from: classes.dex */
public final class aj {
    public final ConnectionTypePropertiesWriter a;
    public final com.spotify.automotive.remoteconfig.a b;

    public aj(ConnectionTypePropertiesWriter connectionTypePropertiesWriter, com.spotify.automotive.remoteconfig.a aVar) {
        v03.j(connectionTypePropertiesWriter, "flags");
        v03.j(aVar, "props");
        this.a = connectionTypePropertiesWriter;
        this.b = aVar;
    }
}

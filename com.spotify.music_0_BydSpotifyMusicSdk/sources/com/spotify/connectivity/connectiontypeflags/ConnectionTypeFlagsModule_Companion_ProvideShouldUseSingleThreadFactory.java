package com.spotify.connectivity.connectiontypeflags;

import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypeFlagsModule_Companion_ProvideShouldUseSingleThreadFactory implements ul0 {
    private final xy1 connectionTypePropertiesReaderProvider;

    public ConnectionTypeFlagsModule_Companion_ProvideShouldUseSingleThreadFactory(xy1 xy1Var) {
        this.connectionTypePropertiesReaderProvider = xy1Var;
    }

    public static ConnectionTypeFlagsModule_Companion_ProvideShouldUseSingleThreadFactory create(xy1 xy1Var) {
        return new ConnectionTypeFlagsModule_Companion_ProvideShouldUseSingleThreadFactory(xy1Var);
    }

    public static boolean provideShouldUseSingleThread(ConnectionTypePropertiesReader connectionTypePropertiesReader) {
        return ConnectionTypeFlagsModule.Companion.provideShouldUseSingleThread(connectionTypePropertiesReader);
    }

    @Override // p.xy1
    public Boolean get() {
        return Boolean.valueOf(provideShouldUseSingleThread((ConnectionTypePropertiesReader) this.connectionTypePropertiesReaderProvider.get()));
    }
}

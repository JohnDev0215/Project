package com.spotify.connectivity.connectiontypeflags;

import p.v03;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectionTypeFlagsModule {
    public static final Companion Companion = Companion.$$INSTANCE;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @NetCapabilitiesValidatedDisabled
        public final boolean provideNetCapabilitiesValidatedDisabledValue(ConnectionTypePropertiesReader connectionTypePropertiesReader) {
            v03.j(connectionTypePropertiesReader, "connectionTypePropertiesReader");
            return connectionTypePropertiesReader.getNetCapabilitiesValidatedDisabled();
        }

        @ShouldUseSingleThread
        public final boolean provideShouldUseSingleThread(ConnectionTypePropertiesReader connectionTypePropertiesReader) {
            v03.j(connectionTypePropertiesReader, "connectionTypePropertiesReader");
            return connectionTypePropertiesReader.getShouldUseSingleThread();
        }
    }
}

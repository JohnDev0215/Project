package com.spotify.cosmos.cosmos;

/* JADX INFO: loaded from: classes.dex */
public interface Lifetime {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final Lifetime UNRESOLVED = new Lifetime() { // from class: com.spotify.cosmos.cosmos.Lifetime$Companion$UNRESOLVED$1
        @Override // com.spotify.cosmos.cosmos.Lifetime
        public void release() {
        }
    };

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }

    void release();
}

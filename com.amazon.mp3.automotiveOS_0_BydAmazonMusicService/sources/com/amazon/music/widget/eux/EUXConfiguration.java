package com.amazon.music.widget.eux;

/* JADX INFO: loaded from: classes4.dex */
public final class EUXConfiguration {
    public final EUXLauncher euxLauncher;
    public final boolean hasMusicInLibrary;
    public final boolean hasSubscription;
    public final boolean isStoreSupported;

    private EUXConfiguration(EUXLauncher eUXLauncher, boolean z, boolean z2, boolean z3) {
        this.euxLauncher = eUXLauncher;
        this.hasSubscription = z;
        this.hasMusicInLibrary = z2;
        this.isStoreSupported = z3;
    }

    public static EUXConfigurationBuilder builder(EUXLauncher eUXLauncher) {
        return new EUXConfigurationBuilder(eUXLauncher);
    }

    public static final class EUXConfigurationBuilder {
        private final EUXLauncher euxLauncher;
        private boolean hasMusic;
        private boolean hasSubscription;
        private boolean isStoreSupported;

        private EUXConfigurationBuilder(EUXLauncher eUXLauncher) {
            this.hasSubscription = false;
            this.hasMusic = false;
            this.isStoreSupported = false;
            this.euxLauncher = eUXLauncher;
        }

        public EUXConfigurationBuilder withSubscription() {
            this.hasSubscription = true;
            return this;
        }

        public EUXConfigurationBuilder withMusicInLibrary() {
            this.hasMusic = true;
            return this;
        }

        public EUXConfigurationBuilder withStoreSupport() {
            this.isStoreSupported = true;
            return this;
        }

        public EUXConfiguration build() {
            return new EUXConfiguration(this.euxLauncher, this.hasSubscription, this.hasMusic, this.isStoreSupported);
        }
    }
}

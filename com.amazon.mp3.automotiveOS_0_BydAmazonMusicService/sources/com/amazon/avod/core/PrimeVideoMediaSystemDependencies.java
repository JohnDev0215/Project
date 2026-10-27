package com.amazon.avod.core;

import com.amazon.avod.drm.db.DrmPersistence;
import com.amazon.avod.identity.IdentityShim;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PrimeVideoMediaSystemDependencies {
    private AdvertisingIdCollector mAdvertisingIdCollector;
    private DeviceIdentity mDeviceIdentity;
    private Supplier<DrmPersistence> mDrmPersistence;
    private IdentityShim mIdentityShim;
    private final InitializationLatch mInitLatch;

    private static final class Holder {
        static final PrimeVideoMediaSystemDependencies INSTANCE = new PrimeVideoMediaSystemDependencies();

        private Holder() {
        }
    }

    public static PrimeVideoMediaSystemDependencies getInstance() {
        return Holder.INSTANCE;
    }

    private PrimeVideoMediaSystemDependencies() {
        this.mInitLatch = new InitializationLatch(this);
    }

    public boolean isInitialized() {
        return this.mInitLatch.isInitialized();
    }

    @Nonnull
    public DeviceIdentity getDeviceIdentity() {
        Preconditions.checkArgument(this.mInitLatch.isInitialized());
        return this.mDeviceIdentity;
    }

    @Nonnull
    public IdentityShim getIdentityShim() {
        Preconditions.checkArgument(this.mInitLatch.isInitialized());
        return this.mIdentityShim;
    }

    @Nonnull
    public Supplier<DrmPersistence> getDrmPersistence() {
        Preconditions.checkArgument(this.mInitLatch.isInitialized());
        return this.mDrmPersistence;
    }

    @Nonnull
    public AdvertisingIdCollector getAdvertisingIdCollector() {
        Preconditions.checkArgument(this.mInitLatch.isInitialized());
        return this.mAdvertisingIdCollector;
    }
}

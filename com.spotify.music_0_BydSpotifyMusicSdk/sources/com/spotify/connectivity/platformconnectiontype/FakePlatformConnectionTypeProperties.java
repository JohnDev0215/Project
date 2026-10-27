package com.spotify.connectivity.platformconnectiontype;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class FakePlatformConnectionTypeProperties implements PlatformConnectionTypeProperties {
    private boolean netCapabilitiesValidatedDisregardedEnabled;
    private boolean netCapabilitiesValidatedDisregardedEnabledCalled;
    private boolean shouldUseSingleThread;
    private boolean shouldUseSingleThreadCalled;

    /* JADX WARN: Illegal instructions before constructor call */
    public FakePlatformConnectionTypeProperties() {
        boolean z = false;
        this(z, z, 3, null);
    }

    public final boolean getNetCapabilitiesValidatedDisregardedEnabled() {
        return this.netCapabilitiesValidatedDisregardedEnabled;
    }

    public final boolean getNetCapabilitiesValidatedDisregardedEnabledCalled() {
        return this.netCapabilitiesValidatedDisregardedEnabledCalled;
    }

    public final boolean getShouldUseSingleThread() {
        return this.shouldUseSingleThread;
    }

    public final boolean getShouldUseSingleThreadCalled() {
        return this.shouldUseSingleThreadCalled;
    }

    @Override // com.spotify.connectivity.platformconnectiontype.PlatformConnectionTypeProperties
    public boolean netCapabilitiesValidatedDisregardedEnabled() {
        this.netCapabilitiesValidatedDisregardedEnabledCalled = true;
        return this.netCapabilitiesValidatedDisregardedEnabled;
    }

    public final void setNetCapabilitiesValidatedDisregardedEnabled(boolean z) {
        this.netCapabilitiesValidatedDisregardedEnabled = z;
    }

    public final void setShouldUseSingleThread(boolean z) {
        this.shouldUseSingleThread = z;
    }

    @Override // com.spotify.connectivity.platformconnectiontype.PlatformConnectionTypeProperties
    public boolean shouldUseSingleThread() {
        this.shouldUseSingleThreadCalled = true;
        return this.shouldUseSingleThread;
    }

    public FakePlatformConnectionTypeProperties(boolean z, boolean z2) {
        this.netCapabilitiesValidatedDisregardedEnabled = z;
        this.shouldUseSingleThread = z2;
    }

    public /* synthetic */ FakePlatformConnectionTypeProperties(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
    }
}

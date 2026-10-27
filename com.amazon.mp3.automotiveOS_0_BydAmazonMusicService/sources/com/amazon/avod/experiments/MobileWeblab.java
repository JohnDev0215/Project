package com.amazon.avod.experiments;

import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import com.amazon.avod.experiments.override.PersistentOverride;
import com.amazon.avod.experiments.override.ServerConfigClampOverride;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MobileWeblab {
    private final PersistentOverride mDebugOverride;
    private final WeblabTreatment mDefaultTreatment;
    private final AtomicReference<TreatmentFetchResult> mFetchResult;
    private final String mName;
    private final ServerConfigClampOverride mServerConfigClampOverride;
    private final boolean mShouldPersistForAppLifeCycle;
    private final WeblabClientProxyProvider mWeblabClientProxyProvider;

    public MobileWeblab(@Nonnull String str) {
        this(str, WeblabTreatment.C, true);
    }

    public MobileWeblab(@Nonnull String str, @Nonnull WeblabTreatment weblabTreatment, boolean z) {
        this(str, weblabTreatment, z, new ServerConfigClampOverride(), new PersistentOverride(str, "debugOrQaHooks"), WeblabClientProxyProvider.getInstance());
    }

    MobileWeblab(@Nonnull String str, @Nonnull WeblabTreatment weblabTreatment, boolean z, @Nonnull ServerConfigClampOverride serverConfigClampOverride, @Nonnull PersistentOverride persistentOverride, @Nonnull WeblabClientProxyProvider weblabClientProxyProvider) {
        this.mFetchResult = new AtomicReference<>(null);
        this.mName = (String) Preconditions.checkNotNull(str, "name");
        this.mDefaultTreatment = (WeblabTreatment) Preconditions.checkNotNull(weblabTreatment, "defaultTreatment");
        this.mShouldPersistForAppLifeCycle = z;
        this.mServerConfigClampOverride = (ServerConfigClampOverride) Preconditions.checkNotNull(serverConfigClampOverride, "serverConfigClampOverride");
        this.mDebugOverride = (PersistentOverride) Preconditions.checkNotNull(persistentOverride, "debugOverride");
        this.mWeblabClientProxyProvider = (WeblabClientProxyProvider) Preconditions.checkNotNull(weblabClientProxyProvider, "weblabClientProxyProvider");
    }

    @Nonnull
    public String getName() {
        return this.mName;
    }

    @Nonnull
    public WeblabTreatment getCurrentTreatment() {
        return getResult().treatment;
    }

    public boolean isOverrideEnabledAndInUse() {
        return getResult().isConfigOverridden;
    }

    @Nonnull
    private TreatmentFetchResult getResult() {
        if (!this.mShouldPersistForAppLifeCycle) {
            return fetchResult();
        }
        CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.mFetchResult, null, fetchResult());
        return this.mFetchResult.get();
    }

    @Nonnull
    private TreatmentFetchResult fetchResult() {
        if (!this.mWeblabClientProxyProvider.isInitialized()) {
            Preconditions2.failWeakly("Early weblab access for [%s] - this weblab may be stuck returning the default treatment.", this.mName);
            return new TreatmentFetchResult(this.mDebugOverride.getTreatmentOverride(this.mName), true, "AccessedTooEarly");
        }
        if (this.mDebugOverride.isOverrideEnabled()) {
            return new TreatmentFetchResult(this.mDebugOverride.getTreatmentOverride(this.mName), true, "DebugOverride");
        }
        Optional<WeblabTreatment> clamp = this.mServerConfigClampOverride.getClamp(this.mName);
        if (clamp.isPresent()) {
            return new TreatmentFetchResult(clamp.get(), true, "Clamp");
        }
        return this.mWeblabClientProxyProvider.getClientProxy().fetchResult(this.mName, this.mDefaultTreatment);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("name", this.mName).add("defaultTreatment", this.mDefaultTreatment).add("shouldPersistForAppLifeCycle", this.mShouldPersistForAppLifeCycle).add("fetchResult", this.mFetchResult.get()).toString();
    }

    public void trigger() {
        if (this.mWeblabClientProxyProvider.isInitialized()) {
            this.mWeblabClientProxyProvider.getClientProxy().trigger(this.mName, isOverrideEnabledAndInUse(), getCurrentTreatment());
        }
    }
}

package com.amazon.mp3.platform.providers;

import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.providers.FeatureGateProvider;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CachedFeatureFlagProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u0018\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\tJ\u0017\u0010\u0011\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/platform/providers/CachedFeatureFlagProviderImpl;", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "defaultFeatureGateProvider", "(Lcom/amazon/music/platform/providers/FeatureGateProvider;)V", "accountStateChangeListener", "Lcom/amazon/music/account/AccountStateChangeListener;", "featureMap", "", "Lcom/amazon/music/platform/featuregate/Feature;", "", "lastUpdatedMap", "", "clearCache", "", "isFeatureAvailable", "feature", "useCache", "retrieveCachedValue", "(Lcom/amazon/music/platform/featuregate/Feature;)Ljava/lang/Boolean;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CachedFeatureFlagProviderImpl implements FeatureGateProvider {
    private static final String TAG = CachedFeatureFlagProviderImpl.class.getSimpleName();
    private final AccountStateChangeListener accountStateChangeListener;
    private final FeatureGateProvider defaultFeatureGateProvider;
    private final Map<Feature, Boolean> featureMap;
    private final Map<Feature, Long> lastUpdatedMap;

    public CachedFeatureFlagProviderImpl(FeatureGateProvider defaultFeatureGateProvider) {
        Intrinsics.checkNotNullParameter(defaultFeatureGateProvider, "defaultFeatureGateProvider");
        this.defaultFeatureGateProvider = defaultFeatureGateProvider;
        this.featureMap = new LinkedHashMap();
        this.lastUpdatedMap = new LinkedHashMap();
        AccountStateChangeListener accountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.platform.providers.CachedFeatureFlagProviderImpl$$ExternalSyntheticLambda0
            @Override // com.amazon.music.account.AccountStateChangeListener
            public final void onAccountStateChange(Set set) {
                CachedFeatureFlagProviderImpl.m1373accountStateChangeListener$lambda0(this.f$0, set);
            }
        };
        this.accountStateChangeListener = accountStateChangeListener;
        AccountManagerSingleton.get().registerListener(accountStateChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: accountStateChangeListener$lambda-0, reason: not valid java name */
    public static final void m1373accountStateChangeListener$lambda0(CachedFeatureFlagProviderImpl this$0, Set set) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.clearCache();
    }

    @Override // com.amazon.music.platform.providers.FeatureGateProvider
    public boolean isFeatureAvailable(Feature feature) {
        Intrinsics.checkNotNullParameter(feature, "feature");
        return this.defaultFeatureGateProvider.isFeatureAvailable(feature);
    }

    public final boolean isFeatureAvailable(Feature feature, boolean useCache) {
        Intrinsics.checkNotNullParameter(feature, "feature");
        synchronized (feature) {
            if (useCache) {
                Boolean boolRetrieveCachedValue = retrieveCachedValue(feature);
                if (boolRetrieveCachedValue != null) {
                    return boolRetrieveCachedValue.booleanValue();
                }
            }
            boolean zIsFeatureAvailable = isFeatureAvailable(feature);
            if (useCache) {
                this.featureMap.put(feature, Boolean.valueOf(zIsFeatureAvailable));
                this.lastUpdatedMap.put(feature, Long.valueOf(System.currentTimeMillis()));
            }
            return zIsFeatureAvailable;
        }
    }

    private final Boolean retrieveCachedValue(Feature feature) {
        Boolean boolValueOf;
        Long l = this.lastUpdatedMap.get(feature);
        if (l == null) {
            boolValueOf = null;
        } else {
            boolValueOf = Boolean.valueOf(System.currentTimeMillis() - l.longValue() < 60000);
        }
        if (Intrinsics.areEqual(boolValueOf, Boolean.TRUE)) {
            return this.featureMap.get(feature);
        }
        return null;
    }

    public final synchronized void clearCache() {
        this.featureMap.clear();
        this.lastUpdatedMap.clear();
    }
}

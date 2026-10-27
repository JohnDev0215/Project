package com.amazon.mp3.reactnative.modules;

import com.amazon.music.platform.find.common.provider.FindFeatureFlagProvider;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeatureGatingModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u001b\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/FeatureGatingModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "findFeatureFlagProvider", "Lcom/amazon/music/platform/find/common/provider/FindFeatureFlagProvider;", "(Lcom/facebook/react/bridge/ReactApplicationContext;Lcom/amazon/music/platform/find/common/provider/FindFeatureFlagProvider;)V", "getName", "", "isAlexaEnabled", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "isAlexaSearchFindBrowseEnabled", "isFindDJModeEnabled", "isFindFreeTierOnDemandEnabled", "isFindGenreArtworkEnabled", "isFindMerchShopEnabled", "isFindSonicRushOnDemandEnabled", "isFindUpsellEnabled", "isMusicVideosEnabled", "isPodcastEnabled", "isSonicRushEnabled", "isSpatialAudioEnabled", "isUHDEnabled", "isWoodstockEnabled", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeatureGatingModule extends ReactContextBaseJavaModule {
    private static final String TAG = FeatureGatingModule.class.getSimpleName();
    private static final String findFeatureProviderName = FindFeatureFlagProvider.class.getSimpleName();
    private final FindFeatureFlagProvider findFeatureFlagProvider;

    public FeatureGatingModule(ReactApplicationContext reactApplicationContext, FindFeatureFlagProvider findFeatureFlagProvider) {
        super(reactApplicationContext);
        this.findFeatureFlagProvider = findFeatureFlagProvider;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    @ReactMethod
    public final void isMusicVideosEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isMusicVideosEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }

    @ReactMethod
    public final void isWoodstockEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isWoodstockEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }

    @ReactMethod
    public final void isPodcastEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isPodcastEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }

    @ReactMethod
    public final void isSpatialAudioEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isSpatialAudioEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }

    @ReactMethod
    public final void isUHDEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isUHDEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }

    @ReactMethod
    public final void isFindDJModeEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isFindDJModeEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }

    @ReactMethod
    public final void isFindMerchShopEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isFindMerchShopEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }

    @ReactMethod
    public final void isFindFreeTierOnDemandEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isFindFreeTierOnDemandEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }

    @ReactMethod
    public final void isFindSonicRushOnDemandEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isFindSonicRushOnDemandEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }

    @ReactMethod
    public final void isSonicRushEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isSonicRushEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }

    @ReactMethod
    public final void isAlexaEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isAlexaEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }

    @ReactMethod
    public final void isAlexaSearchFindBrowseEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isAlexaSearchFindBrowseEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }

    @ReactMethod
    public final void isFindUpsellEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isFindUpsellEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }

    @ReactMethod
    public final void isFindGenreArtworkEnabled(Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(promise, "promise");
        FindFeatureFlagProvider findFeatureFlagProvider = this.findFeatureFlagProvider;
        if (findFeatureFlagProvider == null) {
            unit = null;
        } else {
            promise.resolve(Boolean.valueOf(findFeatureFlagProvider.isFindGenreArtworkEnabled()));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            String findFeatureProviderName2 = findFeatureProviderName;
            Intrinsics.checkNotNullExpressionValue(findFeatureProviderName2, "findFeatureProviderName");
            PromiseExtensionKt.rejectWithProviderNotFound(promise, findFeatureProviderName2);
        }
    }
}

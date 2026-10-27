package com.amazon.music.okhttp3;

import androidx.annotation.VisibleForTesting;
import com.amazon.music.featuregating.rules.WeblabRule;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: FeatureGates.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0001\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005j\u0002\b\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/music/okhttp3/FeatureGates;", "", "Lcom/amazon/music/platform/featuregate/Feature;", "(Ljava/lang/String;I)V", Constants.ENABLE_DISABLE, "", "INSTRUMENTED", "Companion", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum FeatureGates implements Feature {
    INSTRUMENTED { // from class: com.amazon.music.okhttp3.FeatureGates.INSTRUMENTED
        @Override // com.amazon.music.okhttp3.FeatureGates, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new WeblabRule("DM_PBX_UNIFIED_HTTP_ANDROID_METRICS_666091", WeblabTreatment.T1);
        }
    };


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) FeatureGates.class);

    /* synthetic */ FeatureGates(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.amazon.music.platform.featuregate.Feature
    public abstract /* synthetic */ Rule getRule();

    public final boolean isEnabled() {
        FeatureGateProvider featureGateProviderFeatureGateProvider$DMMOkHttpClient_release = INSTANCE.featureGateProvider$DMMOkHttpClient_release();
        if (featureGateProviderFeatureGateProvider$DMMOkHttpClient_release != null) {
            return featureGateProviderFeatureGateProvider$DMMOkHttpClient_release.isFeatureAvailable(this);
        }
        LOGGER.error("Error fetching Feature gate provider!!");
        return false;
    }

    /* JADX INFO: compiled from: FeatureGates.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0001¢\u0006\u0002\b\bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/music/okhttp3/FeatureGates$Companion;", "", "()V", "LOGGER", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "featureGateProvider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "featureGateProvider$DMMOkHttpClient_release", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @VisibleForTesting
        public final FeatureGateProvider featureGateProvider$DMMOkHttpClient_release() {
            AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(FeatureGateProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            if (!(interfaceProvider instanceof FeatureGateProvider)) {
                interfaceProvider = null;
            }
            return (FeatureGateProvider) interfaceProvider;
        }
    }
}

package com.amazon.mp3.platform.locator;

import android.content.Context;
import android.os.Trace;
import com.amazon.mp3.eligibility.ApplicationEligiblityProvider;
import com.amazon.mp3.featuregate.PCAEvaluator;
import com.amazon.mp3.platform.providers.AppConfigProvider;
import com.amazon.mp3.platform.providers.AuthenticationProviderImpl;
import com.amazon.mp3.platform.providers.BuildInfoProviderImpl;
import com.amazon.mp3.platform.providers.CachedFeatureFlagProviderImpl;
import com.amazon.mp3.platform.providers.CustomerMetadataProviderImpl;
import com.amazon.mp3.platform.providers.DefaultDispatcherProvider;
import com.amazon.mp3.platform.providers.DeviceInfoProviderImpl;
import com.amazon.mp3.platform.providers.ExperimentFeatureGateProviderImpl;
import com.amazon.mp3.platform.providers.HapticsProviderImpl;
import com.amazon.mp3.platform.providers.ImageUrlCacheImpl;
import com.amazon.mp3.platform.providers.LoggingProviderImpl;
import com.amazon.mp3.platform.providers.MetricsProviderImpl;
import com.amazon.mp3.platform.providers.NavigationProviderImpl;
import com.amazon.mp3.platform.providers.NetworkInfoProviderImpl;
import com.amazon.mp3.platform.providers.PlayQueueProviderImpl;
import com.amazon.mp3.platform.providers.PlaybackProviderImpl;
import com.amazon.mp3.platform.providers.WeblabProviderImpl;
import com.amazon.mp3.platform.providers.library.LibraryProviderImpl;
import com.amazon.mp3.stability.StabilityManager;
import com.amazon.mp3.util.extensions.EligibilityThrowableKt;
import com.amazon.music.authentication.ActorAuthenticationProviderImpl;
import com.amazon.music.authentication.ProfileManager;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.AtomicMapKt;
import com.amazon.music.platform.data.image.ImageUrlCache;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable;
import com.amazon.music.platform.providers.ActorAuthenticationProvider;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CapabilityActionProvider;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.ExperimentFeatureGateProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.FeatureGateProviderImpl;
import com.amazon.music.platform.providers.FireFlyServiceProvider;
import com.amazon.music.platform.providers.HapticsProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.LibraryProvider;
import com.amazon.music.platform.providers.LoggingProvider;
import com.amazon.music.platform.providers.LyricsReportProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.platform.providers.NavigationProvider;
import com.amazon.music.platform.providers.NetworkInfoProvider;
import com.amazon.music.platform.providers.PlayQueueProvider;
import com.amazon.music.platform.providers.PlaybackProvider;
import com.amazon.music.platform.providers.ProfileProvider;
import com.amazon.music.platform.providers.StabilityManagerProvider;
import com.amazon.music.platform.providers.WeblabProvider;
import com.amazon.music.platform.providers.impl.CapabilityActionProviderImpl;
import com.amazon.music.platform.providers.impl.FireFlyServiceProviderImpl;
import com.amazon.music.platform.providers.impl.LyricsReportProviderImpl;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlatformProvidersUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/platform/locator/PlatformProvidersUtil;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlatformProvidersUtil {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = PlatformProvidersUtil.class.getSimpleName();

    @JvmStatic
    public static final void init(Context context) {
        INSTANCE.init(context);
    }

    /* JADX INFO: compiled from: PlatformProvidersUtil.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/platform/locator/PlatformProvidersUtil$Companion;", "", "Landroid/content/Context;", "context", "", "registerProviderInterfaces", "registerCapabilityProviderErrorHandlers", "init", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void init(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Trace.beginSection("AMA.PlatformProvidersUtil.init");
            registerProviderInterfaces(context);
            registerCapabilityProviderErrorHandlers(context);
            Trace.endSection();
        }

        private final void registerProviderInterfaces(final Context context) {
            Providers providers = Providers.INSTANCE;
            StabilityManager companion = StabilityManager.INSTANCE.getInstance();
            AtomicMap<String, InterfaceProvider> providerInstances = providers.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(StabilityManagerProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            AtomicMapKt.plusAssign(providerInstances, TuplesKt.to(originalClassName, companion));
            AuthenticationProviderImpl authenticationProviderImpl = new AuthenticationProviderImpl(context, DefaultDispatcherProvider.INSTANCE);
            AtomicMap<String, InterfaceProvider> providerInstances2 = providers.getProviderInstances();
            String originalClassName2 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(AuthenticationProvider.class));
            Intrinsics.checkNotNull(originalClassName2);
            AtomicMapKt.plusAssign(providerInstances2, TuplesKt.to(originalClassName2, authenticationProviderImpl));
            DeviceInfoProviderImpl deviceInfoProviderImpl = new DeviceInfoProviderImpl(context);
            AtomicMap<String, InterfaceProvider> providerInstances3 = providers.getProviderInstances();
            String originalClassName3 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(DeviceInfoProvider.class));
            Intrinsics.checkNotNull(originalClassName3);
            AtomicMapKt.plusAssign(providerInstances3, TuplesKt.to(originalClassName3, deviceInfoProviderImpl));
            BuildInfoProviderImpl buildInfoProviderImpl = new BuildInfoProviderImpl();
            AtomicMap<String, InterfaceProvider> providerInstances4 = providers.getProviderInstances();
            String originalClassName4 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(BuildInfoProvider.class));
            Intrinsics.checkNotNull(originalClassName4);
            AtomicMapKt.plusAssign(providerInstances4, TuplesKt.to(originalClassName4, buildInfoProviderImpl));
            int i = 2;
            CustomerMetadataProviderImpl customerMetadataProviderImpl = new CustomerMetadataProviderImpl(context, null, 2, null);
            AtomicMap<String, InterfaceProvider> providerInstances5 = providers.getProviderInstances();
            String originalClassName5 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(CustomerMetadataProvider.class));
            Intrinsics.checkNotNull(originalClassName5);
            AtomicMapKt.plusAssign(providerInstances5, TuplesKt.to(originalClassName5, customerMetadataProviderImpl));
            NetworkInfoProviderImpl networkInfoProviderImpl = new NetworkInfoProviderImpl();
            AtomicMap<String, InterfaceProvider> providerInstances6 = providers.getProviderInstances();
            String originalClassName6 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(NetworkInfoProvider.class));
            Intrinsics.checkNotNull(originalClassName6);
            AtomicMapKt.plusAssign(providerInstances6, TuplesKt.to(originalClassName6, networkInfoProviderImpl));
            CachedFeatureFlagProviderImpl cachedFeatureFlagProviderImpl = new CachedFeatureFlagProviderImpl(new FeatureGateProviderImpl());
            AtomicMap<String, InterfaceProvider> providerInstances7 = providers.getProviderInstances();
            String originalClassName7 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(FeatureGateProvider.class));
            Intrinsics.checkNotNull(originalClassName7);
            AtomicMapKt.plusAssign(providerInstances7, TuplesKt.to(originalClassName7, cachedFeatureFlagProviderImpl));
            WeblabProvider companion2 = WeblabProviderImpl.INSTANCE.getInstance(context);
            AtomicMap<String, InterfaceProvider> providerInstances8 = providers.getProviderInstances();
            String originalClassName8 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(WeblabProvider.class));
            Intrinsics.checkNotNull(originalClassName8);
            AtomicMapKt.plusAssign(providerInstances8, TuplesKt.to(originalClassName8, companion2));
            ExperimentFeatureGateProviderImpl experimentFeatureGateProviderImpl = new ExperimentFeatureGateProviderImpl(companion2, cachedFeatureFlagProviderImpl);
            AtomicMap<String, InterfaceProvider> providerInstances9 = providers.getProviderInstances();
            String originalClassName9 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ExperimentFeatureGateProvider.class));
            Intrinsics.checkNotNull(originalClassName9);
            AtomicMapKt.plusAssign(providerInstances9, TuplesKt.to(originalClassName9, experimentFeatureGateProviderImpl));
            Configuration configuration = AppConfigProvider.INSTANCE.getInstance(context).getConfiguration();
            AtomicMap<String, InterfaceProvider> providerInstances10 = providers.getProviderInstances();
            String originalClassName10 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
            Intrinsics.checkNotNull(originalClassName10);
            AtomicMapKt.plusAssign(providerInstances10, TuplesKt.to(originalClassName10, configuration));
            MetricsProviderImpl metricsProviderImpl = new MetricsProviderImpl();
            AtomicMap<String, InterfaceProvider> providerInstances11 = providers.getProviderInstances();
            String originalClassName11 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
            Intrinsics.checkNotNull(originalClassName11);
            AtomicMapKt.plusAssign(providerInstances11, TuplesKt.to(originalClassName11, metricsProviderImpl));
            NavigationProviderImpl navigationProviderImpl = new NavigationProviderImpl(context);
            AtomicMap<String, InterfaceProvider> providerInstances12 = providers.getProviderInstances();
            String originalClassName12 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(NavigationProvider.class));
            Intrinsics.checkNotNull(originalClassName12);
            AtomicMapKt.plusAssign(providerInstances12, TuplesKt.to(originalClassName12, navigationProviderImpl));
            LibraryProviderImpl libraryProviderImpl = new LibraryProviderImpl(context);
            AtomicMap<String, InterfaceProvider> providerInstances13 = providers.getProviderInstances();
            String originalClassName13 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(LibraryProvider.class));
            Intrinsics.checkNotNull(originalClassName13);
            AtomicMapKt.plusAssign(providerInstances13, TuplesKt.to(originalClassName13, libraryProviderImpl));
            FireFlyServiceProviderImpl fireFlyServiceProviderImpl = new FireFlyServiceProviderImpl(null, null, null, null, 15, null);
            AtomicMap<String, InterfaceProvider> providerInstances14 = providers.getProviderInstances();
            String originalClassName14 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(FireFlyServiceProvider.class));
            Intrinsics.checkNotNull(originalClassName14);
            AtomicMapKt.plusAssign(providerInstances14, TuplesKt.to(originalClassName14, fireFlyServiceProviderImpl));
            LoggingProviderImpl loggingProviderImpl = new LoggingProviderImpl();
            AtomicMap<String, InterfaceProvider> providerInstances15 = providers.getProviderInstances();
            String originalClassName15 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(LoggingProvider.class));
            Intrinsics.checkNotNull(originalClassName15);
            AtomicMapKt.plusAssign(providerInstances15, TuplesKt.to(originalClassName15, loggingProviderImpl));
            CapabilityActionProviderImpl capabilityActionProviderImpl = new CapabilityActionProviderImpl(new ApplicationEligiblityProvider(context, null, i, 0 == true ? 1 : 0));
            AtomicMap<String, InterfaceProvider> providerInstances16 = providers.getProviderInstances();
            String originalClassName16 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(CapabilityActionProvider.class));
            Intrinsics.checkNotNull(originalClassName16);
            AtomicMapKt.plusAssign(providerInstances16, TuplesKt.to(originalClassName16, capabilityActionProviderImpl));
            HapticsProviderImpl hapticsProviderImpl = new HapticsProviderImpl();
            AtomicMap<String, InterfaceProvider> providerInstances17 = providers.getProviderInstances();
            String originalClassName17 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(HapticsProvider.class));
            Intrinsics.checkNotNull(originalClassName17);
            AtomicMapKt.plusAssign(providerInstances17, TuplesKt.to(originalClassName17, hapticsProviderImpl));
            ActorAuthenticationProviderImpl actorAuthenticationProviderImpl = new ActorAuthenticationProviderImpl(context, null, null, null, null, 30, null);
            ProfileManager profileManager = new ProfileManager(actorAuthenticationProviderImpl, authenticationProviderImpl, new Function0<Boolean>() { // from class: com.amazon.mp3.platform.locator.PlatformProvidersUtil$Companion$registerProviderInterfaces$profileManager$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(PCAEvaluator.isEnabled$default(PCAEvaluator.INSTANCE.getInstance(context), false, 1, null));
                }
            });
            AtomicMap<String, InterfaceProvider> providerInstances18 = providers.getProviderInstances();
            String originalClassName18 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ActorAuthenticationProvider.class));
            Intrinsics.checkNotNull(originalClassName18);
            AtomicMapKt.plusAssign(providerInstances18, TuplesKt.to(originalClassName18, actorAuthenticationProviderImpl));
            AtomicMap<String, InterfaceProvider> providerInstances19 = providers.getProviderInstances();
            String originalClassName19 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ProfileProvider.class));
            Intrinsics.checkNotNull(originalClassName19);
            AtomicMapKt.plusAssign(providerInstances19, TuplesKt.to(originalClassName19, profileManager));
            LyricsReportProviderImpl lyricsReportProviderImpl = new LyricsReportProviderImpl();
            AtomicMap<String, InterfaceProvider> providerInstances20 = providers.getProviderInstances();
            String originalClassName20 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(LyricsReportProvider.class));
            Intrinsics.checkNotNull(originalClassName20);
            AtomicMapKt.plusAssign(providerInstances20, TuplesKt.to(originalClassName20, lyricsReportProviderImpl));
            PlaybackProviderImpl playbackProviderImpl = new PlaybackProviderImpl(context, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
            AtomicMap<String, InterfaceProvider> providerInstances21 = providers.getProviderInstances();
            String originalClassName21 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(PlaybackProvider.class));
            Intrinsics.checkNotNull(originalClassName21);
            AtomicMapKt.plusAssign(providerInstances21, TuplesKt.to(originalClassName21, playbackProviderImpl));
            PlayQueueProviderImpl playQueueProviderImpl = new PlayQueueProviderImpl(context);
            AtomicMap<String, InterfaceProvider> providerInstances22 = providers.getProviderInstances();
            String originalClassName22 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(PlayQueueProvider.class));
            Intrinsics.checkNotNull(originalClassName22);
            AtomicMapKt.plusAssign(providerInstances22, TuplesKt.to(originalClassName22, playQueueProviderImpl));
            ImageUrlCacheImpl imageUrlCacheImpl = new ImageUrlCacheImpl();
            AtomicMap<String, InterfaceProvider> providerInstances23 = providers.getProviderInstances();
            String originalClassName23 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ImageUrlCache.class));
            Intrinsics.checkNotNull(originalClassName23);
            AtomicMapKt.plusAssign(providerInstances23, TuplesKt.to(originalClassName23, imageUrlCacheImpl));
        }

        private final void registerCapabilityProviderErrorHandlers(Context context) {
            EligibilityThrowableKt.setupErrorHandling(EligibilityThrowable.NoActiveSubscription.INSTANCE, context);
            EligibilityThrowableKt.setupErrorHandling(EligibilityThrowable.ContentUnavailable.INSTANCE, context);
            EligibilityThrowableKt.setupErrorHandling(EligibilityThrowable.MissingAppStateEligibility.INSTANCE, context);
            EligibilityThrowableKt.setupErrorHandling(EligibilityThrowable.MissingContentEligibility.INSTANCE, context);
            EligibilityThrowableKt.setupErrorHandling(EligibilityThrowable.FeatureDisabled.INSTANCE, context);
            EligibilityThrowableKt.setupErrorHandling(EligibilityThrowable.NoNetwork.INSTANCE, context);
        }
    }
}

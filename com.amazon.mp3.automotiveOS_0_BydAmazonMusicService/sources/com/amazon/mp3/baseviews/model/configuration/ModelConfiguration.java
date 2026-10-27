package com.amazon.mp3.baseviews.model.configuration;

import PlaybackInterface.v1_0.PlaybackEvent;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.playback.capabilities.UpsellReason;
import com.amazon.music.platform.providers.CapabilityActionProvider;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: ModelConfiguration.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "", "cachedActionProviderImpl", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "(Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;)V", "getCachedActionProviderImpl", "()Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "capabilityActionProvider", "Lcom/amazon/music/platform/providers/CapabilityActionProvider;", "canPerformCapability", "Lcom/amazon/music/platform/Outcome;", "capabilityActionType", "", "entity", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "shouldShowContextMenuDownloadUpsellButton", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class ModelConfiguration {
    private final CachedCapabilityActionProviderImpl cachedActionProviderImpl;
    private final CapabilityActionProvider capabilityActionProvider;

    public ModelConfiguration() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ModelConfiguration(CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl) {
        this.cachedActionProviderImpl = cachedCapabilityActionProviderImpl;
        CapabilityActionProvider cachedActionProviderImpl = getCachedActionProviderImpl();
        if (cachedActionProviderImpl == null) {
            AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(CapabilityActionProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            cachedActionProviderImpl = (CapabilityActionProvider) (interfaceProvider instanceof CapabilityActionProvider ? interfaceProvider : null);
        }
        this.capabilityActionProvider = cachedActionProviderImpl;
    }

    public /* synthetic */ ModelConfiguration(CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : cachedCapabilityActionProviderImpl);
    }

    public CachedCapabilityActionProviderImpl getCachedActionProviderImpl() {
        return this.cachedActionProviderImpl;
    }

    public final Outcome<?> canPerformCapability(String capabilityActionType, EntityEligibilitiesProvider entity) {
        Intrinsics.checkNotNullParameter(capabilityActionType, "capabilityActionType");
        Intrinsics.checkNotNullParameter(entity, "entity");
        CapabilityActionProvider capabilityActionProvider = this.capabilityActionProvider;
        if (capabilityActionProvider == null) {
            return null;
        }
        return capabilityActionProvider.canPerform(capabilityActionType, entity);
    }

    public boolean shouldShowContextMenuDownloadUpsellButton(EntityEligibilitiesProvider entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        CapabilityActionProvider capabilityActionProvider = this.capabilityActionProvider;
        Outcome outcomeCanPerform = capabilityActionProvider == null ? null : capabilityActionProvider.canPerform(PlaybackEvent.UPSELL, entity, false, false);
        return Intrinsics.areEqual(outcomeCanPerform, new Outcome.Success(UpsellReason.NotOnDemandPlayable)) || Intrinsics.areEqual(outcomeCanPerform, new Outcome.Success(UpsellReason.NotDownloadable));
    }
}

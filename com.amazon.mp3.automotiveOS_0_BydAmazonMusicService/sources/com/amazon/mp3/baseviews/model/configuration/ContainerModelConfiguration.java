package com.amazon.mp3.baseviews.model.configuration;

import PlaybackInterface.v1_0.PlaybackEvent;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.playback.capabilities.UpsellReason;
import com.amazon.music.platform.playback.data.ShuffleType;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContainerModelConfiguration.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J)\u0010\r\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\nJ\u000e\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\nJ\u000e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "cachedActionProviderImpl", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "(Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;)V", "shouldShowContextMenuDeleteFromLibrary", "", "isInLibrary", "shouldShowContextMenuPlayButton", "entity", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "contextMenuPageType", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuPageType;", "shouldShowContextMenuShuffleButton", "isAllOwned", "(Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;Ljava/lang/Boolean;Lcom/amazon/mp3/fragment/contextmenu/ContextMenuPageType;)Z", "shouldShowOnDemandCapability", "shouldShowRefinementsOption", "shouldShowShuffleText", "entityEligibilitiesProvider", "shouldShowShuffledWithSection", "shouldShowTracklistPreview", "shouldShowUpsellOption", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class ContainerModelConfiguration extends ModelConfiguration {

    /* JADX INFO: compiled from: ContainerModelConfiguration.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ContextMenuPageType.values().length];
            iArr[ContextMenuPageType.LONG_PRESS.ordinal()] = 1;
            iArr[ContextMenuPageType.SEARCH.ordinal()] = 2;
            iArr[ContextMenuPageType.DETAIL.ordinal()] = 3;
            iArr[ContextMenuPageType.LIBRARY.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ContainerModelConfiguration() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ContainerModelConfiguration(CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl) {
        super(cachedCapabilityActionProviderImpl);
    }

    public /* synthetic */ ContainerModelConfiguration(CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : cachedCapabilityActionProviderImpl);
    }

    public final boolean shouldShowShuffleText(EntityEligibilitiesProvider entityEligibilitiesProvider) {
        Intrinsics.checkNotNullParameter(entityEligibilitiesProvider, "entityEligibilitiesProvider");
        if (Feature.shuffle_text_visibility.isEnabled(Feature.album_detail_performance_improvements_phase1B.isEnabled(true))) {
            return !Intrinsics.areEqual(new EligibilityUtil(getCachedActionProviderImpl()).getOnDemand(entityEligibilitiesProvider), Boolean.TRUE);
        }
        return false;
    }

    public final boolean shouldShowShuffledWithSection(EntityEligibilitiesProvider entityEligibilitiesProvider) {
        Intrinsics.checkNotNullParameter(entityEligibilitiesProvider, "entityEligibilitiesProvider");
        if (FMPMFeatureGating.SHOULD_HIDE_TRACKLIST.isEnabled() || FMPMFeatureGating.SHOULD_REMOVE_TRACKLIST.isEnabled()) {
            return !shouldShowOnDemandCapability(entityEligibilitiesProvider);
        }
        return false;
    }

    public final boolean shouldShowTracklistPreview(EntityEligibilitiesProvider entityEligibilitiesProvider) {
        Intrinsics.checkNotNullParameter(entityEligibilitiesProvider, "entityEligibilitiesProvider");
        if (FMPMFeatureGating.SHOULD_HIDE_TRACKLIST.isEnabled()) {
            return !shouldShowOnDemandCapability(entityEligibilitiesProvider);
        }
        return false;
    }

    public final boolean shouldShowRefinementsOption(EntityEligibilitiesProvider entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        Feature feature = Feature.sonic_rush;
        Feature feature2 = Feature.album_detail_performance_improvements_phase1B;
        if (feature.isEnabled(feature2.isEnabled(true)) && Feature.SR_page_headers.isEnabled(feature2.isEnabled(true))) {
            return Intrinsics.areEqual(canPerformCapability("REFINE", entity), new Outcome.Success(Boolean.TRUE));
        }
        return true;
    }

    public final boolean shouldShowUpsellOption(EntityEligibilitiesProvider entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        Outcome<?> outcomeCanPerformCapability = canPerformCapability(PlaybackEvent.UPSELL, entity);
        Feature feature = Feature.sonic_rush;
        Feature feature2 = Feature.album_detail_performance_improvements_phase1B;
        return feature.isEnabled(feature2.isEnabled(true)) && Feature.detail_page_upsell.isEnabled(feature2.isEnabled(true)) && Intrinsics.areEqual(outcomeCanPerformCapability, new Outcome.Success(UpsellReason.NotOnDemandPlayable));
    }

    public static /* synthetic */ boolean shouldShowContextMenuShuffleButton$default(ContainerModelConfiguration containerModelConfiguration, EntityEligibilitiesProvider entityEligibilitiesProvider, Boolean bool, ContextMenuPageType contextMenuPageType, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: shouldShowContextMenuShuffleButton");
        }
        if ((i & 2) != 0) {
            bool = null;
        }
        return containerModelConfiguration.shouldShowContextMenuShuffleButton(entityEligibilitiesProvider, bool, contextMenuPageType);
    }

    @JvmOverloads
    public final boolean shouldShowContextMenuShuffleButton(EntityEligibilitiesProvider entity, Boolean isAllOwned, ContextMenuPageType contextMenuPageType) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        Intrinsics.checkNotNullParameter(contextMenuPageType, "contextMenuPageType");
        Outcome<?> outcomeCanPerformCapability = canPerformCapability("SHUFFLE", entity);
        int i = WhenMappings.$EnumSwitchMapping$0[contextMenuPageType.ordinal()];
        if (i == 1 || i == 2) {
            return Feature.context_menu_shuffle.isEnabled();
        }
        if (i != 3) {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            if ((!Intrinsics.areEqual(outcomeCanPerformCapability, new Outcome.Success(ShuffleType.INLINE)) && !Intrinsics.areEqual(outcomeCanPerformCapability, new Outcome.Success(ShuffleType.MIXED))) || !Feature.context_menu_shuffle.isEnabled()) {
                return false;
            }
        } else if ((!Intrinsics.areEqual(outcomeCanPerformCapability, new Outcome.Success(ShuffleType.INLINE)) && !Intrinsics.areEqual(isAllOwned, Boolean.TRUE)) || !Feature.context_menu_shuffle.isEnabled()) {
            return false;
        }
        return true;
    }

    @JvmOverloads
    public final boolean shouldShowContextMenuPlayButton(EntityEligibilitiesProvider entity, ContextMenuPageType contextMenuPageType) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        Intrinsics.checkNotNullParameter(contextMenuPageType, "contextMenuPageType");
        Outcome<?> outcomeCanPerformCapability = canPerformCapability("ON_DEMAND", entity);
        int i = WhenMappings.$EnumSwitchMapping$0[contextMenuPageType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                if (Feature.library_context_menu_play.isEnabled() && Intrinsics.areEqual(outcomeCanPerformCapability, new Outcome.Success(Boolean.TRUE))) {
                    return true;
                }
            } else if (i != 3) {
                if (i != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                if (Feature.library_context_menu_play.isEnabled()) {
                    return true;
                }
            }
        } else if ((!UserSubscriptionUtil.isNightwingOnly() || Feature.should_migrate_free_to_cq_for_non_station.isEnabled()) && (!Feature.not_library_or_detail_container_on_demand_context_menu_play.isEnabled() || !Intrinsics.areEqual(outcomeCanPerformCapability, new Outcome.Success(Boolean.FALSE)))) {
            return true;
        }
        return false;
    }

    public final boolean shouldShowContextMenuDeleteFromLibrary(boolean isInLibrary) {
        return Feature.context_menu_delete_from_library.isEnabled() && isInLibrary;
    }

    public final boolean shouldShowOnDemandCapability(EntityEligibilitiesProvider entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        return Intrinsics.areEqual(canPerformCapability("ON_DEMAND", entity), new Outcome.Success(Boolean.TRUE));
    }
}

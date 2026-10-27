package com.amazon.music.platform.providers.impl;

import PlaybackInterface.v1_0.PlaybackEvent;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.AtomicMapKt;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.AppStateEligibility;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.playback.capabilities.AllAccessUserPlaylistCanAddCapability;
import com.amazon.music.platform.playback.capabilities.AllAccessUserPlaylistCanRemoveCapability;
import com.amazon.music.platform.playback.capabilities.FixedOnlineInteractionPatternCapability;
import com.amazon.music.platform.playback.capabilities.FollowCapability;
import com.amazon.music.platform.playback.capabilities.InteractionPatternCapability;
import com.amazon.music.platform.playback.capabilities.NonBlockingUpsellCapability;
import com.amazon.music.platform.playback.capabilities.OfflineCapability;
import com.amazon.music.platform.playback.capabilities.OnDemandCapability;
import com.amazon.music.platform.playback.capabilities.OnDemandTrackUnlockCapability;
import com.amazon.music.platform.playback.capabilities.PlayPreviousTrackCapability;
import com.amazon.music.platform.playback.capabilities.PlayableEntityCapability;
import com.amazon.music.platform.playback.capabilities.PlaybackCapability;
import com.amazon.music.platform.playback.capabilities.RatingCapability;
import com.amazon.music.platform.playback.capabilities.RefinementCapability;
import com.amazon.music.platform.playback.capabilities.ShuffleCapability;
import com.amazon.music.platform.playback.capabilities.StationFromAnythingCapability;
import com.amazon.music.platform.playback.capabilities.UpsellCapability;
import com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable;
import com.amazon.music.platform.providers.CapabilityActionProvider;
import com.amazon.music.platform.providers.EligibilityProvider;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.platform.providers.NestedEligibilitiesProvider;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.fasterxml.jackson.core.JsonPointer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: CapabilityActionProviderImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0005\b\u0016\u0018\u0000 /2\u00020\u0001:\u0001/B9\b\u0016\u0012\u001c\u0010,\u001a\u0018\u0012\u0004\u0012\u00020\u001a\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0+0\u0005\u0012\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c¢\u0006\u0004\b-\u0010.B\u001b\b\u0016\u0012\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c¢\u0006\u0004\b-\u0010#J6\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0005H\u0002J,\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0005H\u0002J,\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0005H\u0002J\u0016\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f2\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J@\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00032\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100\u00052\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J*\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0015H\u0016J$\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0006H\u0016J2\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00152\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J4\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0012H\u0016J(\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100\u00052\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100\u0005H\u0014J\u001e\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0004\u001a\u00020\u0003H\u0016R*\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R-\u0010&\u001a\u0018\u0012\u0004\u0012\u00020\u001a\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0%0$8\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R$\u0010*\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010'¨\u00060"}, d2 = {"Lcom/amazon/music/platform/providers/impl/CapabilityActionProviderImpl;", "Lcom/amazon/music/platform/providers/CapabilityActionProvider;", "T", "", "capabilityActionType", "", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "Lcom/amazon/music/platform/Outcome;", "outcomes", "reduceOutcome", "getTrackOutcome", "getCollectionOutcome", "Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "getCapability", "Lcom/amazon/music/platform/data/entity/EntityItem;", "entityItem", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "eligibilities", "", "shouldHandleError", "canPerform", "", "eligibilitiesProviders", "eligibilitiesProvider", "useParentEligibility", "addAppStateIfEmpty", "Lcom/amazon/music/platform/data/entity/EntityItemType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/music/platform/providers/EligibilityProvider;", "Lcom/amazon/music/platform/data/eligibility/AppStateEligibility;", "appStateEligibilityProvider", "Lcom/amazon/music/platform/providers/EligibilityProvider;", "getAppStateEligibilityProvider", "()Lcom/amazon/music/platform/providers/EligibilityProvider;", "setAppStateEligibilityProvider", "(Lcom/amazon/music/platform/providers/EligibilityProvider;)V", "Lcom/amazon/music/platform/AtomicMap;", "", "mappings", "Lcom/amazon/music/platform/AtomicMap;", "getMappings", "()Lcom/amazon/music/platform/AtomicMap;", "allCapabilities", "", "entityCapabilities", "<init>", "(Ljava/util/Map;Lcom/amazon/music/platform/providers/EligibilityProvider;)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public class CapabilityActionProviderImpl implements CapabilityActionProvider {
    private static final String TAG = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(CapabilityActionProviderImpl.class));
    private final AtomicMap<String, PlayableEntityCapability<?>> allCapabilities;
    private EligibilityProvider<AppStateEligibility> appStateEligibilityProvider;
    private final AtomicMap<EntityItemType, Set<PlayableEntityCapability<?>>> mappings;

    public final EligibilityProvider<AppStateEligibility> getAppStateEligibilityProvider() {
        return this.appStateEligibilityProvider;
    }

    public CapabilityActionProviderImpl(Map<EntityItemType, ? extends Set<? extends PlayableEntityCapability<?>>> entityCapabilities, EligibilityProvider<AppStateEligibility> eligibilityProvider) {
        Intrinsics.checkNotNullParameter(entityCapabilities, "entityCapabilities");
        AtomicMap<EntityItemType, Set<PlayableEntityCapability<?>>> atomicMapAtomicMapOf = AtomicMapKt.atomicMapOf();
        this.mappings = atomicMapAtomicMapOf;
        AtomicMap<String, PlayableEntityCapability<?>> atomicMapAtomicMapOf2 = AtomicMapKt.atomicMapOf();
        this.allCapabilities = atomicMapAtomicMapOf2;
        this.appStateEligibilityProvider = eligibilityProvider;
        PlaybackCapability playbackCapability = new PlaybackCapability();
        Set setMutableSetOf = SetsKt__SetsKt.mutableSetOf(new InteractionPatternCapability(), playbackCapability, new ShuffleCapability(), new UpsellCapability(), new OnDemandCapability(), new OnDemandTrackUnlockCapability(), new OfflineCapability(), new StationFromAnythingCapability(), new RatingCapability(), new RefinementCapability(), new PlayPreviousTrackCapability(), new NonBlockingUpsellCapability(playbackCapability), new FollowCapability(), new AllAccessUserPlaylistCanAddCapability(), new AllAccessUserPlaylistCanRemoveCapability());
        AtomicMapKt.set(atomicMapAtomicMapOf, SingleType.Track.INSTANCE, setMutableSetOf);
        AtomicMapKt.set(atomicMapAtomicMapOf, CollectionType.Album.INSTANCE, CollectionsKt___CollectionsKt.toMutableSet(SetsKt___SetsKt.plus((Set<? extends RefinementCapability>) ((Set<? extends Object>) setMutableSetOf), new RefinementCapability())));
        AtomicMapKt.set(atomicMapAtomicMapOf, CollectionType.Playlist.INSTANCE, CollectionsKt___CollectionsKt.toMutableSet(SetsKt___SetsKt.plus((Set<? extends RefinementCapability>) ((Set<? extends Object>) setMutableSetOf), new RefinementCapability())));
        AtomicMapKt.set(atomicMapAtomicMapOf, CollectionType.Artist.INSTANCE, CollectionsKt___CollectionsKt.toMutableSet(SetsKt___SetsKt.plus((Set<? extends RefinementCapability>) ((Set<? extends Object>) setMutableSetOf), new RefinementCapability())));
        AtomicMapKt.set(atomicMapAtomicMapOf, CollectionType.Station.INSTANCE, SetsKt__SetsKt.mutableSetOf(new FixedOnlineInteractionPatternCapability(), playbackCapability, new ShuffleCapability(), new OnDemandCapability(), new OnDemandTrackUnlockCapability(), new StationFromAnythingCapability(), new RatingCapability()));
        AtomicMapKt.set(atomicMapAtomicMapOf2, "INTERACTION_PATTERN", new InteractionPatternCapability());
        AtomicMapKt.set(atomicMapAtomicMapOf2, PlaybackEvent.UPSELL, new UpsellCapability());
        AtomicMapKt.set(atomicMapAtomicMapOf2, Contexts.Upsell.playback, playbackCapability);
        AtomicMapKt.set(atomicMapAtomicMapOf2, "SHUFFLE", new ShuffleCapability());
        AtomicMapKt.set(atomicMapAtomicMapOf2, "ON_DEMAND", new OnDemandCapability());
        AtomicMapKt.set(atomicMapAtomicMapOf2, "ON_DEMAND_TRACK_UNLOCK", new OnDemandTrackUnlockCapability());
        AtomicMapKt.set(atomicMapAtomicMapOf2, "StationFromAnything", new StationFromAnythingCapability());
        AtomicMapKt.set(atomicMapAtomicMapOf2, "RATING", new RatingCapability());
        AtomicMapKt.set(atomicMapAtomicMapOf2, "OFFLINE_CAPABILITY", new OfflineCapability());
        AtomicMapKt.set(atomicMapAtomicMapOf2, "PLAY_PREVIOUS_TRACK", new PlayPreviousTrackCapability());
        AtomicMapKt.set(atomicMapAtomicMapOf2, "NON_BLOCKING_UPSELL", new NonBlockingUpsellCapability(playbackCapability));
        AtomicMapKt.set(atomicMapAtomicMapOf2, "FOLLOW_CAPABILITY", new FollowCapability());
        AtomicMapKt.set(atomicMapAtomicMapOf2, "AAUP_CAN_REMOVE", new AllAccessUserPlaylistCanRemoveCapability());
        AtomicMapKt.set(atomicMapAtomicMapOf2, "AAUP_CAN_ADD", new AllAccessUserPlaylistCanAddCapability());
        for (Map.Entry<EntityItemType, ? extends Set<? extends PlayableEntityCapability<?>>> entry : entityCapabilities.entrySet()) {
            Set<PlayableEntityCapability<?>> set = this.mappings.get(entry.getKey());
            if (set != null) {
                Set mutableSet = CollectionsKt___CollectionsKt.toMutableSet(set);
                CollectionsKt__MutableCollectionsKt.addAll(mutableSet, entry.getValue());
                AtomicMapKt.set(this.mappings, entry.getKey(), mutableSet);
            } else {
                AtomicMapKt.set(this.mappings, entry.getKey(), CollectionsKt___CollectionsKt.toMutableSet(entry.getValue()));
            }
        }
    }

    public /* synthetic */ CapabilityActionProviderImpl(EligibilityProvider eligibilityProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : eligibilityProvider);
    }

    public CapabilityActionProviderImpl(EligibilityProvider<AppStateEligibility> eligibilityProvider) {
        this(MapsKt__MapsKt.emptyMap(), eligibilityProvider);
    }

    public <T> Outcome<T> canPerform(EntityItem entityItem, String capabilityActionType, Map<String, ? extends Eligibility> eligibilities, boolean shouldHandleError) {
        EntityItemType type;
        Outcome<T> outcome;
        Intrinsics.checkNotNullParameter(entityItem, "entityItem");
        Intrinsics.checkNotNullParameter(capabilityActionType, "capabilityActionType");
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        EntityItemIdentifier entityItemIdentifier = entityItem.getEntityItemIdentifier();
        if (entityItemIdentifier == null || (type = entityItemIdentifier.getType()) == null) {
            return new Outcome.Failure(new EligibilityThrowable.UnexpectedFailure("No entity item identifier available"));
        }
        PlayableEntityCapability<?> capability = getCapability(type, capabilityActionType);
        Map<String, Eligibility> mapAddAppStateIfEmpty = addAppStateIfEmpty(eligibilities);
        if (capability != null && (outcome = (Outcome<T>) capability.canPerform(mapAddAppStateIfEmpty, entityItem, shouldHandleError)) != null) {
            return outcome;
        }
        return new Outcome.Failure(new EligibilityThrowable.UnexpectedFailure("Unsupported entity/capability of " + type + JsonPointer.SEPARATOR + capability + " combination"));
    }

    public <T> Outcome<T> canPerform(String capabilityActionType, List<? extends EntityEligibilitiesProvider> eligibilitiesProviders) {
        Intrinsics.checkNotNullParameter(capabilityActionType, "capabilityActionType");
        Intrinsics.checkNotNullParameter(eligibilitiesProviders, "eligibilitiesProviders");
        return canPerform(capabilityActionType, eligibilitiesProviders, false);
    }

    @Override // com.amazon.music.platform.providers.CapabilityActionProvider
    public <T> Outcome<T> canPerform(String capabilityActionType, EntityEligibilitiesProvider eligibilitiesProvider) {
        Intrinsics.checkNotNullParameter(capabilityActionType, "capabilityActionType");
        Intrinsics.checkNotNullParameter(eligibilitiesProvider, "eligibilitiesProvider");
        return CapabilityActionProvider.DefaultImpls.canPerform$default(this, capabilityActionType, eligibilitiesProvider, false, false, 8, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final <T> com.amazon.music.platform.Outcome<T> reduceOutcome(java.lang.String r7, java.util.Map<com.amazon.music.platform.providers.EntityEligibilitiesProvider, ? extends com.amazon.music.platform.Outcome<? extends T>> r8) {
        /*
            r6 = this;
            com.amazon.music.platform.playback.capabilities.PlayableEntityCapability r0 = r6.getCapability(r7)
            boolean r1 = r0 instanceof com.amazon.music.platform.playback.capabilities.reduce.Reducible
            r2 = 0
            if (r1 == 0) goto Ld
            r1 = r0
            com.amazon.music.platform.playback.capabilities.reduce.Reducible r1 = (com.amazon.music.platform.playback.capabilities.reduce.Reducible) r1
            goto Le
        Ld:
            r1 = r2
        Le:
            if (r1 == 0) goto L42
            java.util.Collection r3 = r8.values()
            java.util.Iterator r3 = r3.iterator()
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L3a
            java.lang.Object r4 = r3.next()
        L22:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L35
            java.lang.Object r5 = r3.next()
            com.amazon.music.platform.Outcome r5 = (com.amazon.music.platform.Outcome) r5
            com.amazon.music.platform.Outcome r4 = (com.amazon.music.platform.Outcome) r4
            com.amazon.music.platform.Outcome r4 = r1.reduce(r4, r5)
            goto L22
        L35:
            com.amazon.music.platform.Outcome r4 = (com.amazon.music.platform.Outcome) r4
            if (r4 != 0) goto L5d
            goto L42
        L3a:
            java.lang.UnsupportedOperationException r7 = new java.lang.UnsupportedOperationException
            java.lang.String r8 = "Empty collection can't be reduced."
            r7.<init>(r8)
            throw r7
        L42:
            com.amazon.music.platform.Outcome$Failure r4 = new com.amazon.music.platform.Outcome$Failure
            com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable$UnexpectedFailure r1 = new com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable$UnexpectedFailure
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r7)
            java.lang.String r7 = " result cannot be reduced"
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            r1.<init>(r7)
            r4.<init>(r1)
        L5d:
            com.amazon.music.platform.providers.EligibilityProvider<com.amazon.music.platform.data.eligibility.AppStateEligibility> r7 = r6.appStateEligibilityProvider
            if (r7 == 0) goto L68
            java.lang.Object r7 = r7.getEligibility()
            com.amazon.music.platform.data.eligibility.AppStateEligibility r7 = (com.amazon.music.platform.data.eligibility.AppStateEligibility) r7
            goto L69
        L68:
            r7 = r2
        L69:
            com.amazon.music.platform.Outcome r1 = r6.getTrackOutcome(r8)
            com.amazon.music.platform.Outcome r8 = r6.getCollectionOutcome(r8)
            if (r7 == 0) goto L78
            com.amazon.music.platform.data.subscription.SubscriptionType r3 = r7.getActiveSubscription()
            goto L79
        L78:
            r3 = r2
        L79:
            com.amazon.music.platform.data.subscription.SubscriptionType r5 = com.amazon.music.platform.data.subscription.SubscriptionType.SONIC_RUSH
            if (r3 != r5) goto La3
            com.amazon.music.platform.featuregate.MpcFeatureKeys r3 = com.amazon.music.platform.featuregate.MpcFeatureKeys.track_augmented_shuffle
            boolean r7 = com.amazon.music.platform.data.eligibility.CapabilityExtensionsKt.isMpcFeatureEnabled(r7, r3)
            if (r7 != 0) goto La3
            boolean r7 = r0 instanceof com.amazon.music.platform.playback.capabilities.PlaybackCapability
            if (r7 == 0) goto La3
            if (r8 == 0) goto La3
            if (r1 == 0) goto La3
            boolean r7 = r8 instanceof com.amazon.music.platform.Outcome.Success
            if (r7 == 0) goto L94
            com.amazon.music.platform.Outcome$Success r8 = (com.amazon.music.platform.Outcome.Success) r8
            goto L95
        L94:
            r8 = r2
        L95:
            if (r8 == 0) goto L9e
            java.lang.Object r7 = r8.getValue()
            r2 = r7
            com.amazon.music.platform.playback.PlaybackExperience r2 = (com.amazon.music.platform.playback.PlaybackExperience) r2
        L9e:
            com.amazon.music.platform.playback.PlaybackExperience r7 = com.amazon.music.platform.playback.PlaybackExperience.ON_DEMAND
            if (r2 == r7) goto La3
            r4 = r1
        La3:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.providers.impl.CapabilityActionProviderImpl.reduceOutcome(java.lang.String, java.util.Map):com.amazon.music.platform.Outcome");
    }

    private final <T> Outcome<T> getTrackOutcome(Map<EntityEligibilitiesProvider, ? extends Outcome<? extends T>> map) {
        T next;
        Iterator<T> it = map.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            Eligibility eligibility = ((EntityEligibilitiesProvider) ((Map.Entry) next).getKey()).getEligibilities().get("CONTENT");
            if ((eligibility instanceof ContentEligibility) && (((ContentEligibility) eligibility).getContentEligibilityIdentifier().getType() instanceof SingleType.Track)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) next;
        if (entry != null) {
            return (Outcome) entry.getValue();
        }
        return null;
    }

    private final <T> Outcome<T> getCollectionOutcome(Map<EntityEligibilitiesProvider, ? extends Outcome<? extends T>> map) {
        T next;
        Iterator<T> it = map.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            Eligibility eligibility = ((EntityEligibilitiesProvider) ((Map.Entry) next).getKey()).getEligibilities().get("CONTENT");
            if ((eligibility instanceof ContentEligibility) && (((ContentEligibility) eligibility).getContentEligibilityIdentifier().getType() instanceof CollectionType)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) next;
        if (entry != null) {
            return (Outcome) entry.getValue();
        }
        return null;
    }

    @Override // com.amazon.music.platform.providers.CapabilityActionProvider
    public <T> Outcome<T> canPerform(String capabilityActionType, EntityEligibilitiesProvider eligibilitiesProvider, boolean shouldHandleError, boolean useParentEligibility) {
        EntityEligibilitiesProvider parentEligibilities;
        Intrinsics.checkNotNullParameter(capabilityActionType, "capabilityActionType");
        Intrinsics.checkNotNullParameter(eligibilitiesProvider, "eligibilitiesProvider");
        if (useParentEligibility) {
            NestedEligibilitiesProvider nestedEligibilitiesProvider = eligibilitiesProvider instanceof NestedEligibilitiesProvider ? (NestedEligibilitiesProvider) eligibilitiesProvider : null;
            if (nestedEligibilitiesProvider != null && (parentEligibilities = nestedEligibilitiesProvider.getParentEligibilities()) != null) {
                return canPerform(capabilityActionType, CollectionsKt__CollectionsKt.listOf((Object[]) new EntityEligibilitiesProvider[]{parentEligibilities, eligibilitiesProvider}));
            }
        }
        EntityItem entityItem = eligibilitiesProvider.getEntityItem();
        if (entityItem.getEntityItemIdentifier() == null) {
            return new Outcome.Failure(new Exception("Null entity identifier"));
        }
        return canPerform(entityItem, capabilityActionType, eligibilitiesProvider.getEligibilities(), shouldHandleError);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected Map<String, Eligibility> addAppStateIfEmpty(Map<String, ? extends Eligibility> eligibilities) {
        EligibilityProvider<AppStateEligibility> eligibilityProvider;
        AppStateEligibility eligibility;
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        if (eligibilities.containsKey("APPLICATION_STATE") || (eligibilityProvider = this.appStateEligibilityProvider) == null || (eligibility = eligibilityProvider.getEligibility()) == null) {
            return eligibilities;
        }
        Map<String, Eligibility> mutableMap = MapsKt__MapsKt.toMutableMap(eligibilities);
        mutableMap.put(eligibility.getTYPE(), eligibility);
        return mutableMap;
    }

    public PlayableEntityCapability<?> getCapability(EntityItemType type, String capabilityActionType) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(capabilityActionType, "capabilityActionType");
        Set<PlayableEntityCapability<?>> set = this.mappings.get(type);
        Object obj = null;
        if (set == null) {
            return null;
        }
        Iterator<T> it = set.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Intrinsics.areEqual(((PlayableEntityCapability) next).getActionName(), capabilityActionType)) {
                obj = next;
                break;
            }
        }
        return (PlayableEntityCapability) obj;
    }

    private final PlayableEntityCapability<?> getCapability(String capabilityActionType) {
        return this.allCapabilities.get(capabilityActionType);
    }

    public <T> Outcome<T> canPerform(String capabilityActionType, List<? extends EntityEligibilitiesProvider> eligibilitiesProviders, boolean shouldHandleError) {
        Outcome<T> outcomeCanPerform;
        Intrinsics.checkNotNullParameter(capabilityActionType, "capabilityActionType");
        Intrinsics.checkNotNullParameter(eligibilitiesProviders, "eligibilitiesProviders");
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(eligibilitiesProviders, 10)), 16));
        for (T t : eligibilitiesProviders) {
            EntityEligibilitiesProvider entityEligibilitiesProvider = (EntityEligibilitiesProvider) t;
            EntityItem entityItem = entityEligibilitiesProvider.getEntityItem();
            if (entityItem.getEntityItemIdentifier() == null) {
                outcomeCanPerform = new Outcome.Failure<>(new Exception("Null entity identifier"));
            } else {
                outcomeCanPerform = canPerform(entityItem, capabilityActionType, entityEligibilitiesProvider.getEligibilities(), shouldHandleError);
            }
            linkedHashMap.put(t, outcomeCanPerform);
        }
        return reduceOutcome(capabilityActionType, linkedHashMap);
    }
}

package com.amazon.mp3.util;

import PlaybackInterface.v1_0.PlaybackEvent;
import android.content.Context;
import android.net.Uri;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.extensions.EligibilityThrowableKt;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.playback.OfflineStatus;
import com.amazon.music.platform.playback.PlaybackExperience;
import com.amazon.music.platform.playback.capabilities.UpsellReason;
import com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable;
import com.amazon.music.platform.playback.data.NonBlockingUpsellReason;
import com.amazon.music.platform.playback.data.PlayQueueInteractionPattern;
import com.amazon.music.platform.providers.CapabilityActionProvider;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: EligibilityUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 42\u00020\u0001:\u00014B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0002J\u001e\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\nJ\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019J\u0015\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u001cJ!\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u00132\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010\u0012\u001a\u00020\u0013J$\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010$\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J$\u0010%\u001a\u0004\u0018\u00010&2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010'\u001a\u00020\u0013J\u001a\u0010%\u001a\u0004\u0018\u00010&2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020\u0013J\u001a\u0010(\u001a\u0004\u0018\u00010)2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010\u0012\u001a\u00020\u0013J\u0015\u0010*\u001a\u0004\u0018\u00010\u001e2\u0006\u0010+\u001a\u00020\u0013¢\u0006\u0002\u0010,J\u000e\u0010-\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020\u0013J\u001a\u0010.\u001a\u0004\u0018\u00010/2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010\u0012\u001a\u00020\u0013J\u001a\u00100\u001a\u0004\u0018\u0001012\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019J$\u00102\u001a\u0002032\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010$\u001a\u0004\u0018\u00010\u00172\u0006\u0010'\u001a\u00020\u0013H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/amazon/mp3/util/EligibilityUtil;", "", "cachedActionProviderImpl", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "(Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;)V", "capabilityActionProvider", "Lcom/amazon/music/platform/providers/CapabilityActionProvider;", "convertToCapabilityAction", "", "operation", "Lcom/amazon/mp3/util/ContentAccessUtil$ContentAccessOperation;", "convertToContentUnavailableReason", "Lcom/amazon/mp3/prime/ContentUnavailableReason;", "failure", "Lcom/amazon/music/platform/Outcome$Failure;", "success", "Lcom/amazon/music/platform/Outcome$Success;", "getContentUnavailableReason", "eligibilitiesProvider", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "getNonBlockingUpsell", "Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellInformation;", "contentUri", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "getOnDemand", "", "(Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;)Ljava/lang/Boolean;", "getPlayIconType", "", "trackMetadata", "parentMetadata", "(Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;)Ljava/lang/Integer;", "getPlayMode", "Lcom/amazon/digitalmusicxp/enums/PlayModeEnum;", "collectionUri", "getPlayQueueInteractionPattern", "Lcom/amazon/music/platform/playback/data/PlayQueueInteractionPattern;", "entityEligibilityProvider", "getPlaybackExperience", "Lcom/amazon/music/platform/playback/PlaybackExperience;", "getPlaybackIcon", "entityEligibilitiesProvider", "(Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;)Ljava/lang/Integer;", "getPrevButtonEnabled", "getUpsell", "Lcom/amazon/music/platform/playback/capabilities/UpsellReason;", "sanitizeCollectionUri", "Lcom/amazon/mp3/library/item/AbstractItem;", "setParentEligibility", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EligibilityUtil {
    private static final String TAG = EligibilityUtil.class.getSimpleName();
    private final CapabilityActionProvider capabilityActionProvider;

    /* JADX INFO: compiled from: EligibilityUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            int[] iArr = new int[ContentAccessUtil.ContentAccessOperation.values().length];
            iArr[ContentAccessUtil.ContentAccessOperation.STREAM.ordinal()] = 1;
            iArr[ContentAccessUtil.ContentAccessOperation.DOWNLOAD.ordinal()] = 2;
            iArr[ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE.ordinal()] = 3;
            iArr[ContentAccessUtil.ContentAccessOperation.STREAM_UPSELL.ordinal()] = 4;
            iArr[ContentAccessUtil.ContentAccessOperation.BROWSE.ordinal()] = 5;
            iArr[ContentAccessUtil.ContentAccessOperation.INTERACT.ordinal()] = 6;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[PlaybackExperience.values().length];
            iArr2[PlaybackExperience.ON_DEMAND.ordinal()] = 1;
            iArr2[PlaybackExperience.INLINE_SHUFFLE.ordinal()] = 2;
            iArr2[PlaybackExperience.AUGMENTED_SHUFFLE.ordinal()] = 3;
            iArr2[PlaybackExperience.STATION_FROM_ANYTHING.ordinal()] = 4;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[UpsellReason.values().length];
            iArr3[UpsellReason.None.ordinal()] = 1;
            iArr3[UpsellReason.NotOnDemandPlayable.ordinal()] = 2;
            $EnumSwitchMapping$2 = iArr3;
            int[] iArr4 = new int[NonBlockingUpsellReason.values().length];
            iArr4[NonBlockingUpsellReason.NotOnDemandTrack.ordinal()] = 1;
            iArr4[NonBlockingUpsellReason.NotOnDemandAlbum.ordinal()] = 2;
            iArr4[NonBlockingUpsellReason.NotOnDemandArtist.ordinal()] = 3;
            iArr4[NonBlockingUpsellReason.NotOnDemandPlaylist.ordinal()] = 4;
            $EnumSwitchMapping$3 = iArr4;
        }
    }

    public EligibilityUtil() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public EligibilityUtil(CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl) {
        CapabilityActionProvider capabilityActionProvider = cachedCapabilityActionProviderImpl;
        if (cachedCapabilityActionProviderImpl == null) {
            AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(CapabilityActionProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            capabilityActionProvider = (CapabilityActionProvider) (interfaceProvider instanceof CapabilityActionProvider ? interfaceProvider : null);
        }
        this.capabilityActionProvider = capabilityActionProvider;
    }

    public /* synthetic */ EligibilityUtil(CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : cachedCapabilityActionProviderImpl);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amazon.mp3.prime.ContentUnavailableReason getContentUnavailableReason(com.amazon.music.platform.providers.EntityEligibilitiesProvider r5, com.amazon.mp3.util.ContentAccessUtil.ContentAccessOperation r6) {
        /*
            r4 = this;
            java.lang.String r0 = "eligibilitiesProvider"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "operation"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            com.amazon.mp3.featuregate.Feature r0 = com.amazon.mp3.featuregate.Feature.sonic_rush
            boolean r0 = r0.isEnabled()
            r1 = 0
            if (r0 == 0) goto L87
            com.amazon.mp3.featuregate.Feature r0 = com.amazon.mp3.featuregate.Feature.supports_eligibility
            boolean r0 = r0.isEnabled()
            if (r0 != 0) goto L1e
            goto L87
        L1e:
            java.lang.String r0 = r4.convertToCapabilityAction(r6)
            if (r0 != 0) goto L25
            return r1
        L25:
            int r2 = r0.hashCode()
            r3 = -2128361301(0xffffffff8123c8ab, float:-3.0082344E-38)
            if (r2 == r3) goto L5f
            r3 = -1633171941(0xffffffff9ea7c61b, float:-1.7763747E-20)
            if (r2 == r3) goto L4c
            r3 = 1755063572(0x689c2514, float:5.898985E24)
            if (r2 == r3) goto L39
            goto L67
        L39:
            java.lang.String r2 = "OFFLINE_CAPABILITY"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L42
            goto L67
        L42:
            com.amazon.music.platform.providers.CapabilityActionProvider r2 = r4.capabilityActionProvider
            if (r2 != 0) goto L47
            goto L67
        L47:
            com.amazon.music.platform.Outcome r5 = r2.canPerform(r0, r5)
            goto L72
        L4c:
            java.lang.String r2 = "PLAYBACK"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L55
            goto L67
        L55:
            com.amazon.music.platform.providers.CapabilityActionProvider r2 = r4.capabilityActionProvider
            if (r2 != 0) goto L5a
            goto L67
        L5a:
            com.amazon.music.platform.Outcome r5 = r2.canPerform(r0, r5)
            goto L72
        L5f:
            java.lang.String r2 = "ON_DEMAND"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L69
        L67:
            r5 = r1
            goto L72
        L69:
            com.amazon.music.platform.providers.CapabilityActionProvider r2 = r4.capabilityActionProvider
            if (r2 != 0) goto L6e
            goto L67
        L6e:
            com.amazon.music.platform.Outcome r5 = r2.canPerform(r0, r5)
        L72:
            boolean r0 = r5 instanceof com.amazon.music.platform.Outcome.Failure
            if (r0 == 0) goto L7d
            com.amazon.music.platform.Outcome$Failure r5 = (com.amazon.music.platform.Outcome.Failure) r5
            com.amazon.mp3.prime.ContentUnavailableReason r1 = r4.convertToContentUnavailableReason(r5)
            goto L87
        L7d:
            boolean r0 = r5 instanceof com.amazon.music.platform.Outcome.Success
            if (r0 == 0) goto L87
            com.amazon.music.platform.Outcome$Success r5 = (com.amazon.music.platform.Outcome.Success) r5
            com.amazon.mp3.prime.ContentUnavailableReason r1 = r4.convertToContentUnavailableReason(r5, r6)
        L87:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.util.EligibilityUtil.getContentUnavailableReason(com.amazon.music.platform.providers.EntityEligibilitiesProvider, com.amazon.mp3.util.ContentAccessUtil$ContentAccessOperation):com.amazon.mp3.prime.ContentUnavailableReason");
    }

    public final String convertToCapabilityAction(ContentAccessUtil.ContentAccessOperation operation) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        switch (WhenMappings.$EnumSwitchMapping$0[operation.ordinal()]) {
            case 1:
                return Contexts.Upsell.playback;
            case 2:
            case 3:
                return "OFFLINE_CAPABILITY";
            case 4:
            case 5:
            case 6:
                return null;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final ContentUnavailableReason convertToContentUnavailableReason(Outcome.Failure<?> failure) {
        Throwable cause = failure.getCause();
        return cause instanceof EligibilityThrowable ? EligibilityThrowableKt.contentUnavailableReason((EligibilityThrowable) cause) : ContentUnavailableReason.UNHANDLED_ERROR;
    }

    private final ContentUnavailableReason convertToContentUnavailableReason(Outcome.Success<?> success, ContentAccessUtil.ContentAccessOperation operation) {
        Object value = success.getValue();
        if (!(value instanceof OfflineStatus)) {
            return null;
        }
        ContentAccessUtil.ContentAccessOperation contentAccessOperation = ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE;
        if (operation == contentAccessOperation && !((OfflineStatus) value).getIsDownloaded()) {
            return ContentUnavailableReason.NO_NETWORK;
        }
        if ((operation != contentAccessOperation || ((OfflineStatus) value).getIsPlayableOffline()) && (operation != ContentAccessUtil.ContentAccessOperation.DOWNLOAD || ((OfflineStatus) value).getIsDownloadable())) {
            return null;
        }
        return ContentUnavailableReason.NOT_AVAILABLE_OFFLINE;
    }

    public final PlayModeEnum getPlayMode(EntityEligibilitiesProvider eligibilitiesProvider) {
        Intrinsics.checkNotNullParameter(eligibilitiesProvider, "eligibilitiesProvider");
        return getPlayMode(eligibilitiesProvider, null, null);
    }

    public final PlayModeEnum getPlayMode(EntityEligibilitiesProvider eligibilitiesProvider, Uri collectionUri, Context context) {
        Intrinsics.checkNotNullParameter(eligibilitiesProvider, "eligibilitiesProvider");
        setParentEligibility(context, collectionUri, eligibilitiesProvider);
        CapabilityActionProvider capabilityActionProvider = this.capabilityActionProvider;
        Outcome outcomeCanPerform = capabilityActionProvider == null ? null : capabilityActionProvider.canPerform(Contexts.Upsell.playback, eligibilitiesProvider);
        String str = TAG;
        Log.info(str, "getPlayMode PlaybackCapability playOutcome: " + outcomeCanPerform + " for entity: " + eligibilitiesProvider.getEntityItem());
        if (!(outcomeCanPerform instanceof Outcome.Success)) {
            if (outcomeCanPerform instanceof Outcome.Failure) {
                Log.warning(str, Intrinsics.stringPlus("Unable to retrieve play mode, play mode = null. error: ", ((Outcome.Failure) outcomeCanPerform).getCause()));
                return null;
            }
            Log.warning(str, "Unknown outcome, unable to determine play mode, play mode = null");
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[((PlaybackExperience) ((Outcome.Success) outcomeCanPerform).getValue()).ordinal()];
        if (i == 1 || i == 2) {
            return PlayModeEnum.ON_DEMAND;
        }
        if (i == 3) {
            return PlayModeEnum.AUGMENTED;
        }
        if (i == 4) {
            return PlayModeEnum.SIMILAR;
        }
        Log.warning(str, "Unsupported PlaybackExperience: " + outcomeCanPerform + ", play mode = null");
        return null;
    }

    public final PlaybackExperience getPlaybackExperience(Uri contentUri, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AbstractItem abstractItemSanitizeCollectionUri = sanitizeCollectionUri(contentUri, context);
        if (abstractItemSanitizeCollectionUri == null) {
            Log.error(TAG, Intrinsics.stringPlus("Unable to retrieve PlaybackExperience, Entity cannot be retrieved from uri ", contentUri));
            return null;
        }
        return getPlaybackExperience(abstractItemSanitizeCollectionUri);
    }

    public final PlaybackExperience getPlaybackExperience(EntityEligibilitiesProvider eligibilitiesProvider) {
        Intrinsics.checkNotNullParameter(eligibilitiesProvider, "eligibilitiesProvider");
        CapabilityActionProvider capabilityActionProvider = this.capabilityActionProvider;
        Outcome outcomeCanPerform = capabilityActionProvider == null ? null : capabilityActionProvider.canPerform(Contexts.Upsell.playback, eligibilitiesProvider);
        Log.info(TAG, "getPlaybackExperience PlaybackCapability playOutcome: " + outcomeCanPerform + " for entity: " + eligibilitiesProvider.getEntityItem());
        if (!(outcomeCanPerform instanceof Outcome.Success)) {
            return null;
        }
        Object value = ((Outcome.Success) outcomeCanPerform).getValue();
        if (value instanceof PlaybackExperience) {
            return (PlaybackExperience) value;
        }
        return null;
    }

    public final Integer getPlaybackIcon(EntityEligibilitiesProvider entityEligibilitiesProvider) {
        Intrinsics.checkNotNullParameter(entityEligibilitiesProvider, "entityEligibilitiesProvider");
        CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = null;
        if (!AmazonApplication.getCapabilities().eligibilityBasedPlaybackIcon()) {
            return null;
        }
        PlaybackExperience playbackExperience = new EligibilityUtil(cachedCapabilityActionProviderImpl, 1, 0 == true ? 1 : 0).getPlaybackExperience(entityEligibilitiesProvider);
        int i = playbackExperience == null ? -1 : WhenMappings.$EnumSwitchMapping$1[playbackExperience.ordinal()];
        if (i != 1) {
            return (i == 2 || i == 3 || i == 4) ? 3 : null;
        }
        return 0;
    }

    public final PlayQueueInteractionPattern getPlayQueueInteractionPattern(Uri contentUri, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (MediaProvider.Station.isStation(contentUri)) {
            return PlayQueueInteractionPattern.ONLINE;
        }
        AbstractItem abstractItemSanitizeCollectionUri = sanitizeCollectionUri(contentUri, context);
        if (abstractItemSanitizeCollectionUri == null) {
            Log.error(TAG, "Unable to retrieve PlayQueueInteractionPattern, PlayQueueInteractionPattern = null.");
            return null;
        }
        return getPlayQueueInteractionPattern(abstractItemSanitizeCollectionUri);
    }

    public final PlayQueueInteractionPattern getPlayQueueInteractionPattern(EntityEligibilitiesProvider entityEligibilityProvider) {
        Intrinsics.checkNotNullParameter(entityEligibilityProvider, "entityEligibilityProvider");
        return getPlayQueueInteractionPattern(null, null, entityEligibilityProvider);
    }

    public final PlayQueueInteractionPattern getPlayQueueInteractionPattern(Context context, Uri contentUri, EntityEligibilitiesProvider entityEligibilityProvider) {
        Intrinsics.checkNotNullParameter(entityEligibilityProvider, "entityEligibilityProvider");
        setParentEligibility(context, contentUri, entityEligibilityProvider);
        CapabilityActionProvider capabilityActionProvider = this.capabilityActionProvider;
        Outcome outcomeCanPerform = capabilityActionProvider == null ? null : capabilityActionProvider.canPerform("INTERACTION_PATTERN", entityEligibilityProvider);
        if (outcomeCanPerform == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.platform.Outcome<com.amazon.music.platform.playback.data.PlayQueueInteractionPattern>");
        }
        if (outcomeCanPerform instanceof Outcome.Success) {
            return (PlayQueueInteractionPattern) ((Outcome.Success) outcomeCanPerform).getValue();
        }
        if (outcomeCanPerform instanceof Outcome.Failure) {
            Log.error(TAG, Intrinsics.stringPlus("Unable to retrieve QueueInteractionPattern, error =", ((Outcome.Failure) outcomeCanPerform).getCause()));
            return null;
        }
        Log.error(TAG, "Unknown Outcome " + outcomeCanPerform + " returned for InteractionPatternCapability, PlayQueueInteractionPattern = null");
        return null;
    }

    private final void setParentEligibility(Context context, Uri collectionUri, EntityEligibilitiesProvider entityEligibilityProvider) {
        if (context == null || collectionUri == null || !(entityEligibilityProvider instanceof MusicTrack)) {
            return;
        }
        MusicTrack musicTrack = (MusicTrack) entityEligibilityProvider;
        if (musicTrack.getParentEligibilities() == null) {
            musicTrack.setParentEligibilities(sanitizeCollectionUri(collectionUri, context));
        }
    }

    public final UpsellReason getUpsell(Uri contentUri, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AbstractItem abstractItemSanitizeCollectionUri = sanitizeCollectionUri(contentUri, context);
        if (abstractItemSanitizeCollectionUri == null) {
            Log.error(TAG, "Unable to retrieve upsellReason, Upsell = null.");
            return null;
        }
        return getUpsell(abstractItemSanitizeCollectionUri);
    }

    public final UpsellReason getUpsell(EntityEligibilitiesProvider eligibilitiesProvider) {
        Intrinsics.checkNotNullParameter(eligibilitiesProvider, "eligibilitiesProvider");
        CapabilityActionProvider capabilityActionProvider = this.capabilityActionProvider;
        Outcome outcomeCanPerform = capabilityActionProvider == null ? null : capabilityActionProvider.canPerform(PlaybackEvent.UPSELL, eligibilitiesProvider);
        if (outcomeCanPerform == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.platform.Outcome<com.amazon.music.platform.playback.capabilities.UpsellReason>");
        }
        if (outcomeCanPerform instanceof Outcome.Success) {
            Outcome.Success success = (Outcome.Success) outcomeCanPerform;
            int i = WhenMappings.$EnumSwitchMapping$2[((UpsellReason) success.getValue()).ordinal()];
            if (i == 1) {
                return null;
            }
            if (i == 2) {
                return (UpsellReason) success.getValue();
            }
            Log.error(TAG, "Unhandled UpsellReason: " + success.getValue() + ". Upsell = null");
            return null;
        }
        if (outcomeCanPerform instanceof Outcome.Failure) {
            Log.warning(TAG, Intrinsics.stringPlus("Unable to retrieve upsell, Upsell = null. error: ", ((Outcome.Failure) outcomeCanPerform).getCause()));
            return null;
        }
        Log.warning(TAG, "Unknown outcome, unable to determine upsell, Upsell = null");
        return null;
    }

    public final UpsellInformation getNonBlockingUpsell(Uri contentUri, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AbstractItem abstractItemSanitizeCollectionUri = sanitizeCollectionUri(contentUri, context);
        if (abstractItemSanitizeCollectionUri == null) {
            Log.error(TAG, "Unable to retrieve NonBlockingUpsellReason, UpsellInformation = null.");
            return null;
        }
        return getNonBlockingUpsell(abstractItemSanitizeCollectionUri, context);
    }

    public final UpsellInformation getNonBlockingUpsell(EntityEligibilitiesProvider eligibilitiesProvider, Context context) {
        Intrinsics.checkNotNullParameter(eligibilitiesProvider, "eligibilitiesProvider");
        Intrinsics.checkNotNullParameter(context, "context");
        CapabilityActionProvider capabilityActionProvider = this.capabilityActionProvider;
        Outcome outcomeCanPerform = capabilityActionProvider == null ? null : capabilityActionProvider.canPerform("NON_BLOCKING_UPSELL", eligibilitiesProvider);
        if (outcomeCanPerform == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.platform.Outcome<com.amazon.music.platform.playback.data.NonBlockingUpsellReason>");
        }
        if (!(outcomeCanPerform instanceof Outcome.Success)) {
            if (outcomeCanPerform instanceof Outcome.Failure) {
                Log.warning(TAG, Intrinsics.stringPlus("Unable to retrieve non-blocking upsell, UpsellInformation = null. error: ", ((Outcome.Failure) outcomeCanPerform).getCause()));
                return null;
            }
            Log.warning(TAG, "Unknown outcome, unable to determine non-blocking upsell, UpsellInformation = null");
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$3[((NonBlockingUpsellReason) ((Outcome.Success) outcomeCanPerform).getValue()).ordinal()];
        if (i == 1) {
            return UpsellUtil.getPlaybackUpsellInformationForTrack(UpsellSourceEntity.TRACK, eligibilitiesProvider, context);
        }
        if (i == 2) {
            return UpsellUtil.getPlaybackUpsellInformation(UpsellSourceEntity.ALBUM, eligibilitiesProvider);
        }
        if (i == 3) {
            return UpsellUtil.getPlaybackUpsellInformation(UpsellSourceEntity.ARTIST, eligibilitiesProvider);
        }
        if (i != 4) {
            return null;
        }
        return UpsellUtil.getPlaybackUpsellInformation(UpsellSourceEntity.PLAYLIST, eligibilitiesProvider);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Boolean getOnDemand(com.amazon.music.platform.providers.EntityEligibilitiesProvider r5) {
        /*
            r4 = this;
            java.lang.String r0 = "eligibilitiesProvider"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            com.amazon.music.platform.providers.CapabilityActionProvider r0 = r4.capabilityActionProvider
            r1 = 0
            if (r0 != 0) goto Lc
            r0 = r1
            goto L12
        Lc:
            java.lang.String r2 = "ON_DEMAND"
            com.amazon.music.platform.Outcome r0 = r0.canPerform(r2, r5)
        L12:
            com.amazon.music.platform.data.entity.EntityItem r2 = r5.getEntityItem()
            com.amazon.music.platform.data.entity.EntityItemIdentifier r2 = r2.getEntityItemIdentifier()
            if (r2 != 0) goto L1e
            r2 = r1
            goto L22
        L1e:
            com.amazon.music.platform.data.entity.EntityItemType r2 = r2.getType()
        L22:
            com.amazon.music.platform.data.entity.CollectionType$Playlist r3 = com.amazon.music.platform.data.entity.CollectionType.Playlist.INSTANCE
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r2 == 0) goto L62
            com.amazon.music.platform.Outcome$Success r2 = new com.amazon.music.platform.Outcome$Success
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            r2.<init>(r3)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r0 != 0) goto L5c
            com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService$Companion r0 = com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService.INSTANCE
            com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService r0 = r0.get()
            com.amazon.music.platform.data.entity.EntityItem r5 = r5.getEntityItem()
            com.amazon.music.platform.data.entity.EntityItemIdentifier r5 = r5.getEntityItemIdentifier()
            if (r5 != 0) goto L48
            goto L53
        L48:
            com.amazon.music.platform.data.entity.EntityId r5 = r5.getId()
            if (r5 != 0) goto L4f
            goto L53
        L4f:
            java.lang.String r1 = r5.getIdentifier()
        L53:
            boolean r5 = r0.isOnDemandPlaylist(r1)
            if (r5 == 0) goto L5a
            goto L5c
        L5a:
            r5 = 0
            goto L5d
        L5c:
            r5 = 1
        L5d:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            return r5
        L62:
            boolean r5 = r0 instanceof com.amazon.music.platform.Outcome.Success
            if (r5 == 0) goto L70
            com.amazon.music.platform.Outcome$Success r0 = (com.amazon.music.platform.Outcome.Success) r0
            java.lang.Object r5 = r0.getValue()
            r1 = r5
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            goto L8d
        L70:
            boolean r5 = r0 instanceof com.amazon.music.platform.Outcome.Failure
            if (r5 == 0) goto L86
            java.lang.String r5 = com.amazon.mp3.util.EligibilityUtil.TAG
            com.amazon.music.platform.Outcome$Failure r0 = (com.amazon.music.platform.Outcome.Failure) r0
            java.lang.Throwable r0 = r0.getCause()
            java.lang.String r2 = "Unable to retrieve onDemand, error: "
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r2, r0)
            com.amazon.mp3.util.Log.warning(r5, r0)
            goto L8d
        L86:
            java.lang.String r5 = com.amazon.mp3.util.EligibilityUtil.TAG
            java.lang.String r0 = "Unknown outcome, unable to determine onDemand, onDemand = null"
            com.amazon.mp3.util.Log.warning(r5, r0)
        L8d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.util.EligibilityUtil.getOnDemand(com.amazon.music.platform.providers.EntityEligibilitiesProvider):java.lang.Boolean");
    }

    public final AbstractItem sanitizeCollectionUri(Uri contentUri, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (contentUri == null) {
            return null;
        }
        AbstractItem collectionItem = new CirrusSourceLibraryItemFactory(context).getCollectionItem(contentUri);
        if (collectionItem instanceof EntityEligibilitiesProvider) {
            return collectionItem;
        }
        Log.error(TAG, "sanitizeContent collectionEntity is not of type EntityEligibilitiesProvider, collectionEntity = null, contentUri=" + contentUri + '.');
        return null;
    }

    public final Integer getPlayIconType(EntityEligibilitiesProvider trackMetadata, EntityEligibilitiesProvider parentMetadata) {
        Intrinsics.checkNotNullParameter(trackMetadata, "trackMetadata");
        Boolean onDemand = parentMetadata == null ? null : getOnDemand(parentMetadata);
        Boolean bool = Boolean.TRUE;
        if (Intrinsics.areEqual(onDemand, bool)) {
            return null;
        }
        if (trackMetadata instanceof MusicTrack) {
            MusicTrack musicTrack = (MusicTrack) trackMetadata;
            if (musicTrack.getParentEligibilities() == null) {
                musicTrack.setParentEligibilities(parentMetadata);
            }
        }
        if (Intrinsics.areEqual(getOnDemand(trackMetadata), bool)) {
            return 0;
        }
        return 3;
    }

    public final boolean getPrevButtonEnabled(EntityEligibilitiesProvider entityEligibilitiesProvider) {
        Intrinsics.checkNotNullParameter(entityEligibilitiesProvider, "entityEligibilitiesProvider");
        CapabilityActionProvider capabilityActionProvider = this.capabilityActionProvider;
        return Intrinsics.areEqual(capabilityActionProvider == null ? null : capabilityActionProvider.canPerform("PLAY_PREVIOUS_TRACK", entityEligibilitiesProvider), new Outcome.Success(Boolean.TRUE));
    }
}

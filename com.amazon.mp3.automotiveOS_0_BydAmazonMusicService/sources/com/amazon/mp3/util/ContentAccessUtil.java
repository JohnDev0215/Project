package com.amazon.mp3.util;

import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.download.HarleyDownloadedItem;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.helper.TrackDownloadHelper;
import com.amazon.mp3.library.adapter.DefaultAlbumStateProvider;
import com.amazon.mp3.library.adapter.DefaultPlaylistStateProvider;
import com.amazon.mp3.library.adapter.DefaultRecentTrackStateProvider;
import com.amazon.mp3.library.adapter.DefaultTrackStateProvider;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.recentlyplayed.RecentTrack;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.music.ContentAccessType;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.subscription.ContentAccess;
import com.amazon.music.subscription.ContentAccessResult;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ContentAccessUtil {
    private static final String TAG = "ContentAccessUtil";

    public interface CatalogActionListener<T extends CatalogContent> {
        void continueCatalogAction(final T item, final boolean canContinue);
    }

    public enum ContentAccessOperation {
        BROWSE,
        INTERACT,
        STREAM,
        STREAM_UPSELL,
        DOWNLOAD,
        PLAY_OFFLINE
    }

    public static ContentUnavailableReason getPlayCatalogContentUnavailableReason(CatalogContent catalogContent) {
        ContentUnavailableReason catalogContentUnavailableReason;
        ContentUnavailableReason castingUnavailableReason;
        boolean zEquals = "cirrus-local".equals(catalogContent.getSource());
        boolean z = catalogContent instanceof MusicTrack;
        if (z) {
            zEquals |= "cirrus-local".equals(MediaProvider.getSource(((MusicTrack) catalogContent).getCollectionUri()));
        }
        if (zEquals && CastingUtil.isCasting() && (castingUnavailableReason = getCastingUnavailableReason(catalogContent)) != null) {
            return castingUnavailableReason;
        }
        if (!zEquals) {
            if (z) {
                MusicTrack musicTrack = (MusicTrack) catalogContent;
                if (musicTrack.getDownloadState() == 0 && ((musicTrack.isNotOwned() || MediaProvider.Tracks.isPrimeAdditionalTrack(musicTrack.getContentUri())) && (catalogContentUnavailableReason = getCatalogContentUnavailableReason(catalogContent, ContentAccessOperation.PLAY_OFFLINE)) != null && catalogContentUnavailableReason == ContentUnavailableReason.CUSTOMER_HAS_NO_KATANA_SUBSCRIPTION)) {
                    return catalogContentUnavailableReason;
                }
            }
            ContentUnavailableReason catalogContentUnavailableReason2 = getCatalogContentUnavailableReason(catalogContent, ContentAccessOperation.STREAM);
            if (catalogContentUnavailableReason2 != null) {
                return catalogContentUnavailableReason2;
            }
            return null;
        }
        return getCatalogContentUnavailableReason(catalogContent, ContentAccessOperation.PLAY_OFFLINE);
    }

    private static String getLocalUri(CatalogContent catalogContent) {
        if (catalogContent instanceof MusicTrack) {
            return ((MusicTrack) catalogContent).getLocalUri();
        }
        if (catalogContent instanceof PrimeTrack) {
            return ((PrimeTrack) catalogContent).getLocalUri();
        }
        if (catalogContent instanceof RecentTrack) {
            return ((RecentTrack) catalogContent).getLocalUri();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amazon.mp3.prime.ContentUnavailableReason getCatalogContentUnavailableReason(com.amazon.mp3.library.item.CatalogContent r4, com.amazon.mp3.util.ContentAccessUtil.ContentAccessOperation r5) {
        /*
            r0 = 0
            if (r4 == 0) goto L54
            boolean r1 = r4.isAnyCatalog()
            if (r1 == 0) goto L54
            boolean r1 = r4.isAllOwned()
            if (r1 != 0) goto L54
            boolean r1 = r4.isAllPreviouslyCatalog()
            if (r1 == 0) goto L18
            com.amazon.mp3.prime.ContentUnavailableReason r0 = com.amazon.mp3.prime.ContentUnavailableReason.TRACK_NO_LONGER_PRIME
            goto L5e
        L18:
            com.amazon.mp3.prime.ContentCatalogStatus r1 = r4.getMaxNonPreviousContentCatalogStatus()     // Catch: com.amazon.mp3.util.ContentAccessUtil.InvalidContentCatalogStatusException -> L51
            com.amazon.music.ContentAccessType r1 = getContentAccessTypeForCatalogStatus(r1)     // Catch: com.amazon.mp3.util.ContentAccessUtil.InvalidContentCatalogStatusException -> L51
            com.amazon.music.find.featuregating.FindFeatureGating r2 = com.amazon.music.find.featuregating.FindFeatureGating.RELATED_ENTITY_SEARCH_FREE     // Catch: com.amazon.mp3.util.ContentAccessUtil.InvalidContentCatalogStatusException -> L51
            boolean r2 = r2.isEnabled()     // Catch: com.amazon.mp3.util.ContentAccessUtil.InvalidContentCatalogStatusException -> L51
            if (r2 != 0) goto L30
            com.amazon.mp3.featuregate.Feature r2 = com.amazon.mp3.featuregate.Feature.should_migrate_free_to_cq_for_non_station     // Catch: com.amazon.mp3.util.ContentAccessUtil.InvalidContentCatalogStatusException -> L51
            boolean r2 = r2.isEnabled()     // Catch: com.amazon.mp3.util.ContentAccessUtil.InvalidContentCatalogStatusException -> L51
            if (r2 == 0) goto L34
        L30:
            boolean r2 = r4 instanceof com.amazon.mp3.search.model.Station     // Catch: com.amazon.mp3.util.ContentAccessUtil.InvalidContentCatalogStatusException -> L51
            if (r2 == 0) goto L39
        L34:
            com.amazon.mp3.prime.ContentUnavailableReason r1 = getContentUnavailableReason(r1, r5)     // Catch: com.amazon.mp3.util.ContentAccessUtil.InvalidContentCatalogStatusException -> L51
            goto L3a
        L39:
            r1 = r0
        L3a:
            com.amazon.mp3.prime.ContentUnavailableReason r2 = com.amazon.mp3.prime.ContentUnavailableReason.TOKEN_EXPIRED     // Catch: com.amazon.mp3.util.ContentAccessUtil.InvalidContentCatalogStatusException -> L51
            if (r1 != r2) goto L46
            boolean r3 = com.amazon.mp3.util.OfflineAvailabilityUtil.isPreWidevineAsset(r4)     // Catch: com.amazon.mp3.util.ContentAccessUtil.InvalidContentCatalogStatusException -> L51
            if (r3 == 0) goto L46
            com.amazon.mp3.prime.ContentUnavailableReason r1 = com.amazon.mp3.prime.ContentUnavailableReason.ASSET_IS_PRE_WIDEVINE     // Catch: com.amazon.mp3.util.ContentAccessUtil.InvalidContentCatalogStatusException -> L51
        L46:
            if (r1 != r2) goto L4f
            boolean r2 = com.amazon.mp3.util.OfflineAvailabilityUtil.isAssetDownloadInProgess(r4)     // Catch: com.amazon.mp3.util.ContentAccessUtil.InvalidContentCatalogStatusException -> L51
            if (r2 == 0) goto L4f
            goto L5e
        L4f:
            r0 = r1
            goto L5e
        L51:
            com.amazon.mp3.prime.ContentUnavailableReason r0 = com.amazon.mp3.prime.ContentUnavailableReason.UNHANDLED_ERROR
            goto L5e
        L54:
            boolean r1 = com.amazon.mp3.casting.CastingUtil.isCasting()
            if (r1 == 0) goto L5e
            com.amazon.mp3.prime.ContentUnavailableReason r0 = getCastingUnavailableReason(r4)
        L5e:
            if (r0 != 0) goto L64
            com.amazon.mp3.prime.ContentUnavailableReason r0 = getExplicitFilterUnavailableReason(r4, r5)
        L64:
            if (r0 != 0) goto L7e
            com.amazon.mp3.util.ContentAccessUtil$ContentAccessOperation r1 = com.amazon.mp3.util.ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE
            if (r5 != r1) goto L7e
            boolean r1 = r4 instanceof com.amazon.mp3.library.item.MusicTrack
            if (r1 != 0) goto L76
            boolean r1 = r4 instanceof com.amazon.mp3.prime.browse.metadata.PrimeTrack
            if (r1 != 0) goto L76
            boolean r1 = r4 instanceof com.amazon.mp3.recentlyplayed.RecentTrack
            if (r1 == 0) goto L7e
        L76:
            boolean r1 = r4 instanceof com.amazon.mp3.prime.station.StationTrack
            if (r1 != 0) goto L7e
            com.amazon.mp3.prime.ContentUnavailableReason r0 = getOfflineAssetUnavailableReason(r4)
        L7e:
            if (r0 != 0) goto L90
            boolean r1 = r4 instanceof com.amazon.music.platform.providers.EntityEligibilitiesProvider
            if (r1 == 0) goto L90
            com.amazon.mp3.util.EligibilityUtil r0 = new com.amazon.mp3.util.EligibilityUtil
            r0.<init>()
            r1 = r4
            com.amazon.music.platform.providers.EntityEligibilitiesProvider r1 = (com.amazon.music.platform.providers.EntityEligibilitiesProvider) r1
            com.amazon.mp3.prime.ContentUnavailableReason r0 = r0.getContentUnavailableReason(r1, r5)
        L90:
            if (r0 != 0) goto Laa
            boolean r1 = r4 instanceof com.amazon.mp3.prime.browse.metadata.PrimeTrack
            if (r1 == 0) goto Laa
            com.amazon.mp3.prime.browse.metadata.PrimeTrack r4 = (com.amazon.mp3.prime.browse.metadata.PrimeTrack) r4
            java.util.List r4 = r4.getTags()
            com.amazon.mp3.util.ContentAccessUtil$ContentAccessOperation r1 = com.amazon.mp3.util.ContentAccessUtil.ContentAccessOperation.STREAM
            if (r5 != r1) goto Laa
            boolean r5 = com.amazon.mp3.prime.upsell.nightwing.UpsellUtil.containsUpsellTag(r4)
            if (r5 == 0) goto Laa
            com.amazon.mp3.prime.ContentUnavailableReason r0 = getDisableContentForUpsellReason(r4)
        Laa:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.util.ContentAccessUtil.getCatalogContentUnavailableReason(com.amazon.mp3.library.item.CatalogContent, com.amazon.mp3.util.ContentAccessUtil$ContentAccessOperation):com.amazon.mp3.prime.ContentUnavailableReason");
    }

    public static ContentUnavailableReason getOfflineAssetUnavailableReason(final CatalogContent catalogContent) {
        if (OfflineAvailabilityUtil.isPreWidevineAsset(catalogContent)) {
            return ContentUnavailableReason.ASSET_IS_PRE_WIDEVINE;
        }
        if (OfflineAvailabilityUtil.isCorruptedAsset(catalogContent)) {
            return ContentUnavailableReason.DOWNLOAD_CORRUPTED;
        }
        HarleyDownloadedItem harleyDownloadedItem = getHarleyDownloadedItem(catalogContent);
        String localUri = null;
        if (harleyDownloadedItem == null || !harleyDownloadedItem.isAvailableOffline()) {
            TrackDownloadHelper trackDownloadHelper = new TrackDownloadHelper(AmazonApplication.getContext());
            if (catalogContent instanceof MusicTrack) {
                localUri = ((MusicTrack) catalogContent).getLocalUri();
            } else if (catalogContent instanceof PrimeTrack) {
                localUri = ((PrimeTrack) catalogContent).getLocalUri();
            }
            if (localUri != null && trackDownloadHelper.isInInternalStorageLocation(catalogContent.isAllOwned(), localUri)) {
                return ContentUnavailableReason.FILE_DOES_NOT_EXIST;
            }
            return ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK;
        }
        if (!harleyDownloadedItem.isDownloadedInKatana() || UserSubscriptionUtil.getUserSubscription().isKatana()) {
            return null;
        }
        return ContentUnavailableReason.CUSTOMER_HAS_NO_KATANA_SUBSCRIPTION;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.amazon.mp3.download.HarleyDownloadedItem getHarleyDownloadedItem(final com.amazon.mp3.library.item.CatalogContent r6) {
        /*
            java.lang.String r0 = getLocalUri(r6)
            boolean r1 = r6 instanceof com.amazon.mp3.library.item.MusicTrack
            r2 = 1
            r3 = 0
            r4 = 0
            if (r1 == 0) goto L27
            r1 = r6
            com.amazon.mp3.library.item.MusicTrack r1 = (com.amazon.mp3.library.item.MusicTrack) r1
            java.lang.String r1 = r1.getAsin()
            boolean r5 = r6.isAllOwned()
            if (r5 == 0) goto L32
            r5 = r6
            com.amazon.mp3.library.item.MusicTrack r5 = (com.amazon.mp3.library.item.MusicTrack) r5
            android.net.Uri r5 = r5.getContentUri()
            boolean r5 = isInPrimePlaylist(r5)
            if (r5 == 0) goto L32
            r5 = r2
            goto L42
        L27:
            boolean r1 = r6 instanceof com.amazon.mp3.recentlyplayed.RecentTrack
            if (r1 == 0) goto L34
            r1 = r6
            com.amazon.mp3.recentlyplayed.RecentTrack r1 = (com.amazon.mp3.recentlyplayed.RecentTrack) r1
            java.lang.String r1 = r1.getAsin()
        L32:
            r5 = r4
            goto L42
        L34:
            boolean r1 = r6 instanceof com.amazon.mp3.prime.browse.metadata.PrimeTrack
            if (r1 == 0) goto L40
            r1 = r6
            com.amazon.mp3.prime.browse.metadata.PrimeTrack r1 = (com.amazon.mp3.prime.browse.metadata.PrimeTrack) r1
            java.lang.String r1 = r1.getAsin()
            goto L32
        L40:
            r1 = r3
            goto L32
        L42:
            boolean r6 = r6.isAllOwned()
            if (r6 == 0) goto L4c
            if (r5 == 0) goto L4b
            goto L4c
        L4b:
            r2 = r4
        L4c:
            if (r2 == 0) goto L5e
            if (r1 != 0) goto L51
            return r3
        L51:
            android.content.Context r6 = com.amazon.mp3.AmazonApplication.getContext()
            com.amazon.mp3.download.MusicDownloader r6 = com.amazon.mp3.download.MusicDownloader.getInstance(r6)
            com.amazon.mp3.download.HarleyDownloadedItem r6 = r6.getDownloadedItem(r1, r2)
            return r6
        L5e:
            if (r0 != 0) goto L61
            return r3
        L61:
            android.content.Context r6 = com.amazon.mp3.AmazonApplication.getContext()
            com.amazon.mp3.download.MusicDownloader r6 = com.amazon.mp3.download.MusicDownloader.getInstance(r6)
            com.amazon.mp3.download.HarleyDownloadedItem r6 = r6.getDownloadedItem(r0, r2)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.util.ContentAccessUtil.getHarleyDownloadedItem(com.amazon.mp3.library.item.CatalogContent):com.amazon.mp3.download.HarleyDownloadedItem");
    }

    public static boolean isAvailableOnDisk(final CatalogContent catalogContent) {
        HarleyDownloadedItem harleyDownloadedItem = getHarleyDownloadedItem(catalogContent);
        return harleyDownloadedItem != null && harleyDownloadedItem.isAvailableOffline();
    }

    private static boolean isInPrimePlaylist(final Uri uri) {
        List<String> pathSegments;
        return uri != null && (pathSegments = uri.getPathSegments()) != null && pathSegments.size() >= 5 && pathSegments.get(3).equals("prime") && pathSegments.get(4).equals("primeplaylist");
    }

    private static ContentUnavailableReason getCastingUnavailableReason(final CatalogContent catalogContent) {
        if (CastingUtil.isSupportedContent(catalogContent)) {
            return null;
        }
        return ContentUnavailableReason.CONTENT_TYPE_NOT_CASTABLE;
    }

    public static ContentUnavailableReason getExplicitFilterUnavailableReason(final CatalogContent catalogContent, final ContentAccessOperation operation) {
        if (!AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()) {
            return null;
        }
        if (((catalogContent instanceof PrimeTrack) || !(operation == ContentAccessOperation.BROWSE || operation == ContentAccessOperation.INTERACT || operation == ContentAccessOperation.DOWNLOAD)) && catalogContent.isExplicit()) {
            return ContentUnavailableReason.EXPLICIT_CONTENT_FILTERED;
        }
        return null;
    }

    public static ContentUnavailableReason getCatalogContentStatusUnavailableReason(ContentCatalogStatus contentCatalogStatus, ContentAccessOperation operation) {
        try {
            return getContentUnavailableReason(getContentAccessTypeForCatalogStatus(contentCatalogStatus), operation);
        } catch (InvalidContentCatalogStatusException unused) {
            return ContentUnavailableReason.UNHANDLED_ERROR;
        }
    }

    public static ContentUnavailableReason getContentUnavailableReason(ContentAccessType accessType, ContentAccessOperation operation) {
        ContentAccessResult contentAccessResultCanBrowseContent;
        ContentAccess contentAccess = new ContentAccess(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider());
        int i = AnonymousClass2.$SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation[operation.ordinal()];
        if (i == 1) {
            contentAccessResultCanBrowseContent = contentAccess.canBrowseContent(accessType);
        } else if (i == 2) {
            contentAccessResultCanBrowseContent = contentAccess.canInteractWithContent(accessType);
        } else if (i == 3) {
            contentAccessResultCanBrowseContent = contentAccess.canStreamContent(accessType);
        } else if (i == 4) {
            contentAccessResultCanBrowseContent = contentAccess.canDownloadContent(accessType);
        } else if (i == 5) {
            contentAccessResultCanBrowseContent = contentAccess.canPlayDownloadedContent(accessType);
            if (contentAccessResultCanBrowseContent.isAccessible() && !MusicDownloader.getInstance(AmazonApplication.getContext()).isOfflineDRMLicenseAvailable()) {
                return ContentUnavailableReason.TOKEN_EXPIRED;
            }
        } else {
            throw new IllegalArgumentException("Called getContentUnavailableReason for illegal ContentAccessOperation " + operation);
        }
        return getContentUnavailableReasonForContentAccessResult(contentAccessResultCanBrowseContent);
    }

    private static ContentUnavailableReason getContentUnavailableReasonForContentAccessResult(ContentAccessResult contentAccessResult) {
        if (contentAccessResult.isAccessible()) {
            return null;
        }
        switch (AnonymousClass2.$SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason[contentAccessResult.getReason().ordinal()]) {
            case 1:
            case 2:
                return ContentUnavailableReason.ACCOUNT_INVALID;
            case 3:
            case 4:
            case 5:
                return ContentUnavailableReason.ACCOUNT_NOT_AUTHORIZED;
            case 6:
                return ContentUnavailableReason.DEVICE_NOT_AUTHORIZED;
            case 7:
                return ContentUnavailableReason.DEVICE_NOT_PRIME_AUTHORIZED;
            case 8:
                return ContentUnavailableReason.UNSUPPORTED_MARKETPLACE;
            case 9:
                if (CastingUtil.isCastingToAlexa()) {
                    return null;
                }
                return ContentUnavailableReason.CUSTOMER_HAS_HAWKFIRE_HOME_SUBSCRIPTION;
            case 10:
                return ContentUnavailableReason.CUSTOMER_HAS_NO_HAWKFIRE_SUBSCRIPTION;
            case 11:
                return ContentUnavailableReason.CUSTOMER_NOT_PRIME;
            case 12:
                return ContentUnavailableReason.CUSTOMER_DATA_NOT_READY;
            case 13:
                return ContentUnavailableReason.BOP_CONTENT_RESTRICTIONS;
            default:
                throw new IllegalArgumentException("Cannot convert ContentAccessResult " + contentAccessResult + " to ContentUnavailableReason");
        }
    }

    private static ContentUnavailableReason getDisableContentForUpsellReason(final List<String> tags) {
        if (tags != null && tags.contains("upsell-tag-browsehome-widget")) {
            return ContentUnavailableReason.DISABLE_CONTENT_FOR_UPSELL;
        }
        return null;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.util.ContentAccessUtil$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$ContentCatalogStatus;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason;

        static {
            int[] iArr = new int[ContentCatalogStatus.values().length];
            $SwitchMap$com$amazon$mp3$prime$ContentCatalogStatus = iArr;
            try {
                iArr[ContentCatalogStatus.HAWKFIRE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentCatalogStatus[ContentCatalogStatus.SONIC_RUSH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentCatalogStatus[ContentCatalogStatus.PRIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentCatalogStatus[ContentCatalogStatus.BOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentCatalogStatus[ContentCatalogStatus.NON_CATALOG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentCatalogStatus[ContentCatalogStatus.UNKNOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentCatalogStatus[ContentCatalogStatus.PREVIOUSLY_CATALOG.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[ContentAccessResult.Reason.values().length];
            $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason = iArr2;
            try {
                iArr2[ContentAccessResult.Reason.ACCOUNT_INVALID.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason[ContentAccessResult.Reason.ACCOUNT_NOT_CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason[ContentAccessResult.Reason.ACCOUNT_LOCKED.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason[ContentAccessResult.Reason.ACCOUNT_NOT_VERIFIED.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason[ContentAccessResult.Reason.TERMS_OF_USE_NOT_ACCEPTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason[ContentAccessResult.Reason.DEVICE_NOT_AUTHORIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason[ContentAccessResult.Reason.DEVICE_NOT_PRIME_AUTHORIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason[ContentAccessResult.Reason.MARKETPLACE_NOT_SUPPORTED.ordinal()] = 8;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason[ContentAccessResult.Reason.CUSTOMER_HAS_HAWKFIRE_HOME_SUBSCRIPTION.ordinal()] = 9;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason[ContentAccessResult.Reason.CUSTOMER_HAS_NO_HAWKFIRE_SUBSCRIPTION.ordinal()] = 10;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason[ContentAccessResult.Reason.CUSTOMER_HAS_NO_PRIME_SUBSCRIPTION.ordinal()] = 11;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason[ContentAccessResult.Reason.CUSTOMER_DATA_NOT_READY.ordinal()] = 12;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$ContentAccessResult$Reason[ContentAccessResult.Reason.BOP_TIER_RESTRICTIONS.ordinal()] = 13;
            } catch (NoSuchFieldError unused20) {
            }
            int[] iArr3 = new int[ContentAccessOperation.values().length];
            $SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation = iArr3;
            try {
                iArr3[ContentAccessOperation.BROWSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation[ContentAccessOperation.INTERACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation[ContentAccessOperation.STREAM.ordinal()] = 3;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation[ContentAccessOperation.DOWNLOAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation[ContentAccessOperation.PLAY_OFFLINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused25) {
            }
        }
    }

    private static ContentAccessType getContentAccessTypeForCatalogStatus(ContentCatalogStatus catalogStatus) throws InvalidContentCatalogStatusException {
        int i = AnonymousClass2.$SwitchMap$com$amazon$mp3$prime$ContentCatalogStatus[catalogStatus.ordinal()];
        if (i == 1) {
            return ContentAccessType.HAWKFIRE;
        }
        if (i == 2) {
            return ContentAccessType.SONIC_RUSH;
        }
        if (i == 3) {
            return ContentAccessType.PRIME;
        }
        if (i == 4) {
            return ContentAccessType.BOP;
        }
        if (i == 5) {
            return ContentAccessType.OWNED;
        }
        throw new InvalidContentCatalogStatusException("Cannot convert ContentCatalogStatus " + catalogStatus + " to ContentAccessType");
    }

    public static <T extends CatalogContent> void checkContinueActionWithoutEnabilityCheck(final Context context, final T item, final ContentAccessOperation operation, final CatalogActionListener listener) {
        if (listener == null) {
            Log.error(TAG, "Listener is null.  Cannot perform action: " + operation);
            return;
        }
        if (operation == ContentAccessOperation.PLAY_OFFLINE) {
            listener.continueCatalogAction(item, true);
            return;
        }
        if (ConnectivityUtil.hasAnyInternetConnection(context)) {
            listener.continueCatalogAction(item, true);
        } else if (isDownloadableContent(item) && operation == ContentAccessOperation.STREAM) {
            requestStateForOfflinePlayback(context, item, listener);
        } else {
            listener.continueCatalogAction(item, ConnectivityUtil.checkConnectivityAndShowDialog(context));
        }
    }

    public static void checkContinueActionWithoutEnabilityCheck(final FragmentActivity activity, final RecentlyPlayedItem recentlyPlayedItem, final ContentAccessOperation operation, final CatalogActionListener listener) {
        checkContinueActionWithoutEnabilityCheck(activity, recentlyPlayedItem.convertToCatalogContent(), operation, listener);
    }

    public static <T extends CatalogContent> void requestStateForOfflinePlayback(final Context context, final T item, final CatalogActionListener listener) {
        StateProvider stateProvider = getStateProvider(context, item);
        Object obj = new StateProvider.Listener<T>() { // from class: com.amazon.mp3.util.ContentAccessUtil.1
            /* JADX WARN: Incorrect types in method signature: (TT;II)V */
            @Override // com.amazon.mp3.library.provider.StateProvider.Listener
            public void onStateChanged(final CatalogContent item2, final int ownershipState, final int downloadState) {
                if (downloadState == 0) {
                    listener.continueCatalogAction(item2, true);
                } else {
                    listener.continueCatalogAction(item2, ConnectivityUtil.checkConnectivityAndShowDialog(context));
                }
            }
        };
        if (stateProvider != null) {
            stateProvider.requestState(item, obj);
        } else {
            listener.continueCatalogAction(item, false);
        }
    }

    public static <T extends SearchItem & CatalogContent> void checkCanContinueCatalogAction(final Context context, final T item, final ContentAccessOperation operation, final CatalogActionListener listener) {
        checkCanContinueCatalogAction(context, item, operation, listener, true);
    }

    public static <T extends SearchItem & CatalogContent> void checkCanContinueCatalogAction(final Context context, final T item, final ContentAccessOperation operation, final CatalogActionListener listener, final boolean showDialog) {
        DialogFragment dialogForDisabledReason;
        if (listener == null) {
            Log.error(TAG, "Listener is null.  Cannot perform action: " + operation);
            return;
        }
        if (item == null) {
            Log.error(TAG, "Item is null. Cannot perform action: " + operation);
            return;
        }
        T t = item;
        ContentUnavailableReason catalogContentUnavailableReason = getCatalogContentUnavailableReason(t, operation);
        if (catalogContentUnavailableReason != null) {
            if (showDialog && (dialogForDisabledReason = ContentEnabilityDialogUtil.getDialogForDisabledReason(context, catalogContentUnavailableReason, null, item.getItemTypeName(), item, operation)) != null && (context instanceof FragmentActivity)) {
                showDialog((FragmentActivity) context, dialogForDisabledReason);
            }
            listener.continueCatalogAction(t, false);
            return;
        }
        checkContinueActionWithoutEnabilityCheck(context, item, operation, listener);
    }

    private static <T extends CatalogContent> boolean isDownloadableContent(final T item) {
        if (item != null) {
            return (item instanceof PrimePlaylist) || (item instanceof PrimeTrack) || (item instanceof PrimeAlbum) || (item instanceof RecentTrack);
        }
        return false;
    }

    private static <T extends CatalogContent> StateProvider getStateProvider(final Context context, final T item) {
        if (item == null || context == null) {
            return null;
        }
        if (item instanceof PrimeAlbum) {
            return new DefaultAlbumStateProvider(context);
        }
        if (item instanceof PrimeTrack) {
            return new DefaultTrackStateProvider(context);
        }
        if (item instanceof PrimePlaylist) {
            return new DefaultPlaylistStateProvider(context);
        }
        if (item instanceof RecentTrack) {
            return new DefaultRecentTrackStateProvider(context);
        }
        return null;
    }

    public static <T extends SearchItem & CatalogContent> boolean canContinueCatalogAction(final Context context, final T item, final ContentAccessOperation operation) {
        return canContinueCatalogAction(context, (SearchItem) item, operation, true);
    }

    public static <T extends SearchItem & CatalogContent> boolean canContinueCatalogAction(final Context context, final T item, final ContentAccessOperation operation, final boolean handleNoNetworkError) {
        ContentUnavailableReason catalogContentUnavailableReason = getCatalogContentUnavailableReason(item, operation);
        if (catalogContentUnavailableReason == null) {
            if (handleNoNetworkError) {
                return ConnectivityUtil.checkConnectivityAndShowDialog(context);
            }
            return true;
        }
        DialogFragment dialogForDisabledReason = ContentEnabilityDialogUtil.getDialogForDisabledReason(context, catalogContentUnavailableReason, null, item.getItemTypeName(), item, operation);
        if (dialogForDisabledReason == null) {
            return false;
        }
        showDialog((FragmentActivity) context, dialogForDisabledReason);
        return false;
    }

    public static <T extends SearchItem & CatalogContent> boolean canContinueCatalogAction(final FragmentActivity activity, final T item, final ContentUnavailableReason reason, final ContentAccessOperation operation) {
        if (reason != null) {
            DialogFragment dialogForDisabledReason = ContentEnabilityDialogUtil.getDialogForDisabledReason(activity, reason, null, item.getItemTypeName(), item, operation);
            if (dialogForDisabledReason == null) {
                return false;
            }
            showDialog(activity, dialogForDisabledReason);
            return false;
        }
        return ConnectivityUtil.checkConnectivityAndShowDialog(activity);
    }

    public static boolean canContinueCatalogAction(final FragmentActivity activity, final Object item, final ContentUnavailableReason reason, final ContentAccessOperation operation) {
        if (reason == null) {
            return ConnectivityUtil.checkConnectivityAndShowDialog(activity);
        }
        DialogFragment dialogForDisabledReason = ContentEnabilityDialogUtil.getDialogForDisabledReason(activity, reason, null, "", item, operation);
        if (dialogForDisabledReason == null) {
            return false;
        }
        showDialog(activity, dialogForDisabledReason);
        return false;
    }

    public static boolean canContinueCatalogStatusAction(FragmentActivity activity, ContentCatalogStatus contentCatalogStatus, ContentAccessOperation operation) {
        return canContinueContentUnavailableReason(activity, getCatalogContentStatusUnavailableReason(contentCatalogStatus, operation), true, true);
    }

    public static boolean canContinueContentUnavailableReason(FragmentActivity activity, ContentUnavailableReason reason, boolean checkInternetConnection, boolean showDialog) {
        return canContinueContentUnavailableReason(activity, reason, checkInternetConnection, showDialog, null);
    }

    public static boolean canContinueContentUnavailableReason(FragmentActivity activity, ContentUnavailableReason reason, boolean checkInternetConnection, Object item) {
        return canContinueContentUnavailableReason(activity, reason, checkInternetConnection, true, item);
    }

    public static boolean canContinueContentUnavailableReason(FragmentActivity activity, ContentUnavailableReason reason, boolean checkInternetConnection, boolean showDialog, Object item) {
        DialogFragment dialogForDisabledReason;
        if (reason == null) {
            if (checkInternetConnection) {
                return ConnectivityUtil.checkConnectivityAndShowDialog(activity);
            }
            return true;
        }
        if (showDialog && (dialogForDisabledReason = ContentEnabilityDialogUtil.getDialogForDisabledReason(activity, reason, null, "", item, null)) != null) {
            showDialog(activity, dialogForDisabledReason);
        }
        return false;
    }

    public static boolean canBrowseContent(final ContentAccessType contentAccessType) {
        return new ContentAccess(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).canBrowseContent(contentAccessType).isAccessible();
    }

    public static ContentCatalogStatus setContentAccess(boolean isMusicSubscription, boolean isPrime, final boolean isSonicRush) {
        ContentAccess contentAccess = new ContentAccess(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider());
        if (isMusicSubscription && contentAccess.canStreamContent(ContentAccessType.HAWKFIRE).isAccessible()) {
            return ContentCatalogStatus.HAWKFIRE;
        }
        if (isSonicRush && contentAccess.canStreamContent(ContentAccessType.SONIC_RUSH).isAccessible()) {
            return ContentCatalogStatus.SONIC_RUSH;
        }
        if (isPrime && contentAccess.canStreamContent(ContentAccessType.PRIME).isAccessible()) {
            return ContentCatalogStatus.PRIME;
        }
        if (isMusicSubscription) {
            return ContentCatalogStatus.HAWKFIRE;
        }
        if (isSonicRush) {
            return ContentCatalogStatus.SONIC_RUSH;
        }
        if (isPrime) {
            return ContentCatalogStatus.PRIME;
        }
        return ContentCatalogStatus.UNKNOWN;
    }

    private static void showDialog(final FragmentActivity activity, final DialogFragment dialog) {
        FragmentTransaction fragmentTransactionBeginTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.add(dialog, (String) null);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    public static ContentAccessType[] getAllContentAccessTypeValues() {
        return AmazonApplication.getCapabilities().isSonicRushEnabled() ? ContentAccessType.values() : new ContentAccessType[]{ContentAccessType.OWNED, ContentAccessType.NIGHTWING, ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE, ContentAccessType.PRIME, ContentAccessType.HAWKFIRE, ContentAccessType.BUNDESLIGA_FREE, ContentAccessType.BUNDESLIGA_PRIME, ContentAccessType.BUNDESLIGA_HAWKFIRE, ContentAccessType.BOP};
    }

    private static class InvalidContentCatalogStatusException extends Exception {
        public InvalidContentCatalogStatusException(final String errorMessage) {
            super(errorMessage);
        }
    }
}

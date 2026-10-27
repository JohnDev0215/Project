package com.amazon.mp3.recentlyplayed.views;

import android.content.Context;
import android.net.Uri;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItemBinder;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.RecentsMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentsVerticalTileConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b\"\u0010#J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J?\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000e\u001a\u00020\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0002J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006$"}, d2 = {"Lcom/amazon/mp3/recentlyplayed/views/RecentsVerticalTileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItem;", "recentItem", "", "Lcom/amazon/music/ContentAccessType;", "getSupportTiers", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItem$Type;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "getContentType", "recentlyPlayedItem", "", "getSecondaryTitle", "contentType", "", "isFree", "isFreeOnDemand", "contentIdentifier", "getPlayIconType", "(IZZLjava/lang/String;Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItem;)Ljava/lang/Integer;", "Lcom/amazon/mp3/library/item/CatalogContent;", "catalogContent", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "convertCatalogContent", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItemBinder;", "recentsBinder", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItemBinder;", "shouldUseEligibilityForRecents", "Z", "Landroid/content/Context;", "mContext", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RecentsVerticalTileConverter implements SingleBaseModelConverter<RecentlyPlayedItem> {
    private final RecentlyPlayedItemBinder recentsBinder;
    private final boolean shouldUseEligibilityForRecents;

    /* JADX INFO: compiled from: RecentsVerticalTileConverter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlayModeEnum.values().length];
            iArr[PlayModeEnum.AUGMENTED.ordinal()] = 1;
            iArr[PlayModeEnum.SIMILAR.ordinal()] = 2;
            iArr[PlayModeEnum.ON_DEMAND.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public RecentsVerticalTileConverter(Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.recentsBinder = new RecentlyPlayedItemBinder(mContext);
        this.shouldUseEligibilityForRecents = Feature.use_eligibility_recently_played_library_v1.isEnabled();
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(RecentlyPlayedItem recentItem) {
        Intrinsics.checkNotNullParameter(recentItem, "recentItem");
        RecentlyPlayedItem.Type itemType = recentItem.getItemType();
        Intrinsics.checkNotNullExpressionValue(itemType, "recentItem.itemType");
        int contentType = getContentType(itemType);
        Set<ContentAccessType> supportTiers = getSupportTiers(recentItem);
        Uri contentUri = recentItem.getContentUri();
        String title = recentItem.getTitle();
        String artworkImageUrl = recentItem.getArtworkImageUrl();
        boolean zIsExplicit = recentItem.isExplicit();
        String collectionAsin = recentItem.getCollectionAsin();
        String collectionId = recentItem.getCollectionId();
        String trackAsin = recentItem.getTrackAsin();
        String trackLuid = recentItem.getTrackLuid();
        String string = recentItem.getItemType().toString();
        Intrinsics.checkNotNullExpressionValue(contentUri, "contentUri");
        RecentsMetadata recentsMetadata = new RecentsMetadata(null, contentUri, null, title, artworkImageUrl, trackAsin, collectionAsin, collectionId, trackLuid, string, zIsExplicit, supportTiers, 5, null);
        return new VerticalTileModel(null, recentItem.getTitle(), getSecondaryTitle(recentItem), null, new ArtworkFrameModel(null, recentItem.getArtworkImageUrl(), Integer.valueOf(contentType), null, null, getPlayIconType(contentType, supportTiers.contains(ContentAccessType.NIGHTWING), supportTiers.contains(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE), recentItem.getCollectionAsin(), recentItem), recentsMetadata, null, null, null, null, Feature.sonic_rush.isEnabled(true) ? IconStyleKey.SONIC_RUSH_OVERLAY : null, 1945, null), UserSubscriptionUtil.getUserSubscription().isKatana() ? ContentEncodingDecoder.parseFrom(recentItem.getContentEncoding()) : CollectionsKt__CollectionsJVMKt.listOf(""), contentType, recentsMetadata, false, null, null, null, null, null, null, null, null, null, null, null, null, 2096905, null);
    }

    private final Set<ContentAccessType> getSupportTiers(RecentlyPlayedItem recentItem) {
        return recentItem.getCatalogStatusTiers().toSet();
    }

    private final int getContentType(RecentlyPlayedItem.Type type) {
        if (type == RecentlyPlayedItem.Type.PRIME_ARTIST || type == RecentlyPlayedItem.Type.ARTIST) {
            return 4;
        }
        if (type == RecentlyPlayedItem.Type.GENRE) {
            return 5;
        }
        if (type == RecentlyPlayedItem.Type.ALBUM) {
            return 0;
        }
        return type == RecentlyPlayedItem.Type.STATION ? 3 : 1;
    }

    private final String getSecondaryTitle(RecentlyPlayedItem recentlyPlayedItem) {
        String secondaryTitle = this.recentsBinder.getSecondaryTitle(recentlyPlayedItem);
        Intrinsics.checkNotNullExpressionValue(secondaryTitle, "recentsBinder.getSeconda…Title(recentlyPlayedItem)");
        return secondaryTitle;
    }

    private final Integer getPlayIconType(int contentType, boolean isFree, boolean isFreeOnDemand, String contentIdentifier, RecentlyPlayedItem recentItem) {
        if (contentType == 3) {
            return 1;
        }
        if (contentType == 1 && UserSubscriptionUtil.isNightwingOnly()) {
            if (isFree && isFreeOnDemand) {
                return ((AmazonApplication.getCapabilities().isOnDemandPlaylistsEnabled() && GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandPlaylist(contentIdentifier)) ? 1 : 0) != 0 ? 0 : 2;
            }
            if (isFree) {
                return 1;
            }
        }
        if (this.shouldUseEligibilityForRecents) {
            EntityEligibilitiesProvider entityEligibilitiesProviderConvertCatalogContent = convertCatalogContent(contentType, recentItem.convertToCatalogContent());
            PlayModeEnum playMode = null;
            if (entityEligibilitiesProviderConvertCatalogContent != null) {
                playMode = new EligibilityUtil(0 == true ? 1 : 0, i, 0 == true ? 1 : 0).getPlayMode(entityEligibilitiesProviderConvertCatalogContent);
            }
            int i = playMode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[playMode.ordinal()];
            if (i == 1 || i == 2) {
                return 3;
            }
            if (i == 3) {
                return 0;
            }
            return 0;
        }
        return 0;
    }

    private final EntityEligibilitiesProvider convertCatalogContent(int contentType, CatalogContent catalogContent) {
        EntityEligibilitiesProvider entityEligibilitiesProvider;
        if (contentType != 0) {
            if (contentType == 1) {
                if (!(catalogContent instanceof PrimePlaylist)) {
                    return null;
                }
                entityEligibilitiesProvider = (PrimePlaylist) catalogContent;
            } else {
                if (contentType != 4 || !(catalogContent instanceof PrimeArtist)) {
                    return null;
                }
                entityEligibilitiesProvider = (PrimeArtist) catalogContent;
            }
        } else {
            if (!(catalogContent instanceof PrimeAlbum)) {
                return null;
            }
            entityEligibilitiesProvider = (PrimeAlbum) catalogContent;
        }
        return entityEligibilitiesProvider;
    }
}

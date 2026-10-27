package com.amazon.music.alps.liveevent.converters;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.alps.R$string;
import com.amazon.music.alps.config.URLConfig;
import com.amazon.music.alps.liveevent.ktx.MsaUrlKt;
import com.amazon.music.alps.liveevent.model.ArtistMerch;
import com.amazon.music.msautils.MsaUrl;
import com.amazon.music.platform.graphql.generated.fragment.ArtistEmbedded;
import com.amazon.music.platform.graphql.generated.fragment.Eligibility;
import com.amazon.music.platform.graphql.generated.fragment.Image;
import com.amazon.music.platform.graphql.generated.fragment.Merch;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.MerchMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtistMerchVerticalTileConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0007J \u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0007R\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/ArtistMerchVerticalTileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/alps/liveevent/model/ArtistMerch;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "", "getBaseUrl", "baseUrl", "artistId", "merchId", "Landroid/net/Uri;", "getUri", "Landroid/content/Context;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class ArtistMerchVerticalTileConverter implements SingleBaseModelConverter<ArtistMerch> {
    private final Context context;

    public ArtistMerchVerticalTileConverter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(ArtistMerch data) {
        Merch.Image image;
        Image image2;
        String url;
        Eligibility eligibility;
        String merchUrl;
        String string;
        Intrinsics.checkNotNullParameter(data, "data");
        String baseUrl = getBaseUrl();
        Merch merch = data.getMerch();
        ArtistEmbedded artist = data.getArtist();
        String id = artist.getId();
        Intrinsics.checkNotNull(id);
        String id2 = merch.getId();
        Intrinsics.checkNotNull(id2);
        Uri uri = getUri(baseUrl, id, id2);
        String id3 = merch.getId();
        Intrinsics.checkNotNull(id3);
        MerchMetadata merchMetadata = new MerchMetadata(null, uri, null, id3, artist.getId(), null, null, 101, null);
        List<Merch.Image> images = merch.getImages();
        if (images == null || (image = (Merch.Image) CollectionsKt___CollectionsKt.getOrNull(images, 0)) == null || (image2 = image.getImage()) == null || (url = image2.getUrl()) == null) {
            merchUrl = null;
        } else {
            Merch.Eligibility eligibility2 = merch.getEligibility();
            boolean zAreEqual = (eligibility2 == null || (eligibility = eligibility2.getEligibility()) == null) ? false : Intrinsics.areEqual(eligibility.getIsPrimeEligible(), Boolean.TRUE);
            MsaUrl msaUrlWith = MsaUrl.with(url);
            Intrinsics.checkNotNullExpressionValue(msaUrlWith, "with(it)");
            merchUrl = MsaUrlKt.toMerchUrl(msaUrlWith, Boolean.valueOf(zAreEqual));
        }
        List<String> relatedAssetTypes = merch.getRelatedAssetTypes();
        if (relatedAssetTypes == null || relatedAssetTypes.isEmpty()) {
            string = "";
        } else {
            List<String> relatedAssetTypes2 = merch.getRelatedAssetTypes();
            String strJoinToString$default = relatedAssetTypes2 == null ? null : CollectionsKt___CollectionsKt.joinToString$default(relatedAssetTypes2, ",", null, null, 0, null, null, 62, null);
            string = strJoinToString$default != null ? this.context.getString(R$string.other_merch_formats, strJoinToString$default) : null;
        }
        if (!(string == null || string.length() == 0)) {
            String name = merch.getName();
            String[] strArr = new String[2];
            String assetType = merch.getAssetType();
            if (assetType == null) {
                assetType = this.context.getString(R$string.merch);
                Intrinsics.checkNotNullExpressionValue(assetType, "context.getString(R.string.merch)");
            }
            strArr[0] = assetType;
            strArr[1] = merch.getPrice();
            return new VerticalTileModel(null, name, CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt__CollectionsKt.listOf((Object[]) strArr), " · ", null, null, 0, null, null, 62, null), string, new ArtworkFrameModel(null, merchUrl, 13, null, null, null, null, null, null, null, null, null, 4089, null), null, 13, merchMetadata, false, null, null, null, null, null, null, null, null, null, null, null, null, 2096929, null);
        }
        String name2 = merch.getName();
        String[] strArr2 = new String[2];
        String assetType2 = merch.getAssetType();
        if (assetType2 == null) {
            assetType2 = this.context.getString(R$string.merch);
            Intrinsics.checkNotNullExpressionValue(assetType2, "context.getString(R.string.merch)");
        }
        strArr2[0] = assetType2;
        strArr2[1] = merch.getPrice();
        return new VerticalTileModel(null, name2, CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt__CollectionsKt.listOf((Object[]) strArr2), " · ", null, null, 0, null, null, 62, null), null, new ArtworkFrameModel(null, merchUrl, 13, null, null, null, null, null, null, null, null, null, 4089, null), null, 13, merchMetadata, false, null, null, null, null, null, null, null, null, null, null, null, null, 2096937, null);
    }

    @VisibleForTesting
    public final String getBaseUrl() {
        return URLConfig.SHARE_BASE_URL.getValue();
    }

    @VisibleForTesting
    public final Uri getUri(String baseUrl, String artistId, String merchId) {
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(artistId, "artistId");
        Intrinsics.checkNotNullParameter(merchId, "merchId");
        Uri uri = Uri.parse(baseUrl + "merch/artists/" + artistId + "/items/" + merchId);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(\"${baseUrl}merch/a…artistId/items/$merchId\")");
        return uri;
    }
}

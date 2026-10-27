package com.amazon.mp3.brush.converters;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.ModelConfiguration;
import com.amazon.mp3.brush.AllAccessPlaylistsUtils;
import com.amazon.mp3.cache.AlbumCache;
import com.amazon.mp3.catalog.fragment.converter.BauhausDetailHeaderTextUtil;
import com.amazon.mp3.detailpages.album.util.AlbumDetailPageHeaderUtil;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.models.ArtistDetailHeaderModel;
import com.amazon.music.views.library.models.CollectionDetailHeaderModel;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.models.PageHeaderModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Image;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushDetailHeaderConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0007H\u0002J\b\u0010\n\u001a\u00020\tH\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0004\u001a\u00020\u0002H\u0016R\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushDetailHeaderConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "Lcom/amazon/musicensembleservice/brush/Album;", "data", "Lcom/amazon/music/views/library/models/PageHeaderModel;", "convertAlbum", "Lcom/amazon/musicensembleservice/brush/Artist;", "convertArtist", "", "shouldOverrideTitleCaps", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "modelConfiguration", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "Lcom/amazon/mp3/cache/AlbumCache;", "albumCache", "Lcom/amazon/mp3/cache/AlbumCache;", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;Lcom/amazon/mp3/cache/AlbumCache;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushDetailHeaderConverter implements SingleBaseModelConverter<Entity> {
    private final AlbumCache albumCache;
    private final Context context;
    private final ModelConfiguration modelConfiguration;

    public BrushDetailHeaderConverter(Context context, ModelConfiguration modelConfiguration, AlbumCache albumCache) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.modelConfiguration = modelConfiguration;
        this.albumCache = albumCache;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Album) {
            return convertAlbum((Album) data);
        }
        if (data instanceof Artist) {
            return convertArtist((Artist) data);
        }
        return null;
    }

    private final PageHeaderModel convertAlbum(Album data) {
        AlbumMetadata albumMetadata = BrushConverterUtils.INSTANCE.getAlbumMetadata(data, this.modelConfiguration, this.albumCache);
        String blockRef = data.getBlockRef();
        String string = this.context.getString(R.string.dmusic_library_item_name_album);
        String title = data.getTitle();
        String concatenatedArtistName = data.getConcatenatedArtistName();
        String secondaryText$default = BauhausDetailHeaderTextUtil.getSecondaryText$default(BauhausDetailHeaderTextUtil.INSTANCE, data, null, 2, null);
        List<PageHeaderActionIconModel> listGenerateCatalogAlbumDetailHeaderIcons = AlbumDetailPageHeaderUtil.INSTANCE.generateCatalogAlbumDetailHeaderIcons(data, this.modelConfiguration, this.albumCache);
        String url = data.getImage().getUrl();
        CollectionDetailHeaderModel collectionDetailHeaderModel = new CollectionDetailHeaderModel(blockRef, string, title, Integer.valueOf(R.id.AlbumName), concatenatedArtistName, Integer.valueOf(R.id.ArtistName), secondaryText$default, null, null, listGenerateCatalogAlbumDetailHeaderIcons, albumMetadata, new DownloadStateModel(DownloadStateModel.DownloadState.NO_STATE, 0), url, null, false, AllAccessPlaylistsUtils.INSTANCE.getOnDemandBadging(albumMetadata), null, null, 221440, null);
        collectionDetailHeaderModel.setSonicRushEnabled(AmazonApplication.getCapabilities().shouldShowSRPageHeaders(Feature.album_detail_performance_improvements_phase1B.isEnabled(true)));
        return collectionDetailHeaderModel;
    }

    private final PageHeaderModel convertArtist(Artist data) {
        List<PageHeaderActionIconModel> listGenerateArtistActionIconModels$default;
        if (data.getAsin() == null) {
            return null;
        }
        String blockRef = data.getBlockRef();
        String string = this.context.getResources().getString(R.string.dmusic_library_item_name_artist);
        String primaryTitle = data.getPrimaryTitle();
        boolean zShouldOverrideTitleCaps = shouldOverrideTitleCaps();
        String followerCountText = data.getFollowerCountText();
        Integer numValueOf = Integer.valueOf(R.id.follower_count_text);
        Image heroImage = data.getHeroImage();
        String url = heroImage == null ? null : heroImage.getUrl();
        Image image = data.getImage();
        String url2 = image == null ? null : image.getUrl();
        String name = data.getName();
        Boolean boolIsIsFollowing = data.isIsFollowing();
        boolean zBooleanValue = boolIsIsFollowing == null ? false : boolIsIsFollowing.booleanValue();
        String artistStationKey = data.getArtistStationKey();
        String asin = data.getAsin();
        String primaryTitle2 = data.getPrimaryTitle();
        String name2 = data.getName();
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        String asin2 = data.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin2, "data.asin");
        Uri artistUri = contentPlaybackUtils.getArtistUri(asin2);
        String blockRef2 = data.getBlockRef();
        Boolean boolIsIsFollowing2 = data.isIsFollowing();
        ArtistMetadata artistMetadata = new ArtistMetadata(blockRef2, artistUri, null, artistStationKey, asin, primaryTitle2, name2, null, null, null, null, boolIsIsFollowing2 == null ? false : boolIsIsFollowing2.booleanValue(), null, null, false, null, null, false, false, null, 1046404, null);
        if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            listGenerateArtistActionIconModels$default = PageHeaderActionIconModel.INSTANCE.generateArtistActionIconModels(new PageHeaderActionIconModel.ActionType[]{PageHeaderActionIconModel.ActionType.PLAY, PageHeaderActionIconModel.ActionType.SHUFFLE});
        } else {
            listGenerateArtistActionIconModels$default = PageHeaderActionIconModel.Companion.generateArtistActionIconModels$default(PageHeaderActionIconModel.INSTANCE, null, 1, null);
        }
        return new ArtistDetailHeaderModel(blockRef, string, primaryTitle, zShouldOverrideTitleCaps, followerCountText, numValueOf, url, url2, name, zBooleanValue, artistMetadata, listGenerateArtistActionIconModels$default, null, 4096, null);
    }

    private final boolean shouldOverrideTitleCaps() {
        return MusicTerritory.JAPAN.equalsIgnoreCase(AccountDetailUtil.getMusicTerritoryOfResidence());
    }
}

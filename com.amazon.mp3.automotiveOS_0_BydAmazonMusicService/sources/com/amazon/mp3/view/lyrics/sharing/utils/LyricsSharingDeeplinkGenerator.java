package com.amazon.mp3.view.lyrics.sharing.utils;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsSharingDeeplinkGenerator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ#\u0010\u0006\u001a\u00020\u00052\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\"\u00020\u0003H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\f\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\n¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricsSharingDeeplinkGenerator;", "", "", "", "pathArguments", "Landroid/net/Uri;", "buildUriFromPath", "([Ljava/lang/String;)Landroid/net/Uri;", "Landroid/content/Context;", "context", "Lcom/amazon/music/media/playback/MediaItem;", "currentMediaItem", "generateDeeplink", "<init>", "()V", "UriHelper", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsSharingDeeplinkGenerator {
    public static final LyricsSharingDeeplinkGenerator INSTANCE = new LyricsSharingDeeplinkGenerator();

    private LyricsSharingDeeplinkGenerator() {
    }

    public final String generateDeeplink(Context context, MediaItem currentMediaItem) {
        Intrinsics.checkNotNullParameter(currentMediaItem, "currentMediaItem");
        MediaItemId mediaItemId = currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN);
        String id = mediaItemId == null ? null : mediaItemId.getId();
        String it = currentMediaItem.getMediaCollectionInfo(MediaCollectionType.ALBUM, MediaCollectionId.Type.ASIN).getId().getId();
        String homeMarketPlaceId = AccountDetailUtil.get(context).getHomeMarketPlaceId();
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        LyricsSharingDeeplinkGenerator lyricsSharingDeeplinkGenerator = INSTANCE;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        String string = lyricsSharingDeeplinkGenerator.buildUriFromPath("albums", it).buildUpon().appendQueryParameter("marketplaceId", homeMarketPlaceId).appendQueryParameter(Splash.PARAMS_MUSIC_TERRITORY, musicTerritoryOfResidence).appendQueryParameter(ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, id).appendQueryParameter("do", PageUriUtils.DO_PLAYFULL_VALUE).appendQueryParameter("viewType", "lyrics").build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "backlinkUri.toString()");
        return string;
    }

    private final Uri buildUriFromPath(String... pathArguments) {
        Uri.Builder builderAuthority = UriHelper.getUriBuilder().scheme("https").authority(EndpointsProvider.get().getMusicDomain());
        int length = pathArguments.length;
        int i = 0;
        while (i < length) {
            String str = pathArguments[i];
            i++;
            builderAuthority.appendPath(str);
        }
        Uri uriBuild = builderAuthority.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "builder.build()");
        return uriBuild;
    }

    /* JADX INFO: compiled from: LyricsSharingDeeplinkGenerator.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricsSharingDeeplinkGenerator$UriHelper;", "", "()V", "getUriBuilder", "Landroid/net/Uri$Builder;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class UriHelper {
        public static final UriHelper INSTANCE = new UriHelper();

        private UriHelper() {
        }

        @JvmStatic
        public static final Uri.Builder getUriBuilder() {
            return new Uri.Builder();
        }
    }
}

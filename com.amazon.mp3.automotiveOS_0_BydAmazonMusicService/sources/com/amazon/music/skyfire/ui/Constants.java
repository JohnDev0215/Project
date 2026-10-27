package com.amazon.music.skyfire.ui;

import Touch.WidgetsInterface.v1_0.ImageResources;
import Touch.WidgetsInterface.v1_0.ResourceId;
import com.amazonaws.util.DateUtils;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: Constants.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001f\u0010\u0007\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001f\u0010\u0011\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u0014\u0010\u0014\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0006R\u0014\u0010\u0016\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0006¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/skyfire/ui/Constants;", "", "()V", "IS_ACTIVE_KEY", "", "getIS_ACTIVE_KEY", "()Ljava/lang/String;", "IdMap", "", "LTouch/WidgetsInterface/v1_0/ResourceId;", "", "getIdMap", "()Ljava/util/Map;", "OPEN_CALENDAR_DATE_FORMAT", "Ljava/text/SimpleDateFormat;", "getOPEN_CALENDAR_DATE_FORMAT", "()Ljava/text/SimpleDateFormat;", "ResMap", "LTouch/WidgetsInterface/v1_0/ImageResources$Image;", "getResMap", "TOGGLE_ARTIST_INTENT", "getTOGGLE_ARTIST_INTENT", "WIDGET_ID_KEY", "getWIDGET_ID_KEY", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Constants {
    public static final Constants INSTANCE = new Constants();
    private static final Map<ImageResources.Image, Integer> ResMap = MapsKt__MapsKt.mapOf(TuplesKt.to(ImageResources.Image.ALBUM_PLACEHOLDER, Integer.valueOf(R.drawable.album_placeholder)), TuplesKt.to(ImageResources.Image.ARTIST_PLACEHOLDER, Integer.valueOf(R.drawable.artist_placeholder)), TuplesKt.to(ImageResources.Image.FAVORITE_ICON, Integer.valueOf(R.drawable.ic_action_favorite_svg)), TuplesKt.to(ImageResources.Image.FAVORITE_ON_ICON, Integer.valueOf(R.drawable.ic_action_favoriteon_svg)), TuplesKt.to(ImageResources.Image.MORE_ICON, Integer.valueOf(R.drawable.ic_action_more)), TuplesKt.to(ImageResources.Image.PLAY_ICON, Integer.valueOf(R.drawable.ic_playback_play)), TuplesKt.to(ImageResources.Image.PLAYLIST_PLACEHOLDER, Integer.valueOf(R.drawable.playlist_placeholder)), TuplesKt.to(ImageResources.Image.SHARE_ICON, Integer.valueOf(R.drawable.ic_action_share)), TuplesKt.to(ImageResources.Image.SHUFFLE_ICON, Integer.valueOf(R.drawable.ic_playback_shuffle)), TuplesKt.to(ImageResources.Image.STATION_ICON, Integer.valueOf(R.drawable.ic_playback_station)), TuplesKt.to(ImageResources.Image.STATION_PLACEHOLDER, Integer.valueOf(R.drawable.station_placeholder)), TuplesKt.to(ImageResources.Image.TRACK_PLACEHOLDER, Integer.valueOf(R.drawable.song_placeholder)), TuplesKt.to(ImageResources.Image.VIDEO_PLACEHOLDER, Integer.valueOf(R.drawable.videofallback)), TuplesKt.to(ImageResources.Image.MERCH_PLACEHOLDER, Integer.valueOf(R.drawable.merch_placeholder)));
    private static final Map<ResourceId, Integer> IdMap = MapsKt__MapsKt.mapOf(TuplesKt.to(ResourceId.FOLLOW_ICON, Integer.valueOf(R.id.follow_icon)), TuplesKt.to(ResourceId.LARGE_FOLLOW_BUTTON, Integer.valueOf(R.id.large_follow_button)), TuplesKt.to(ResourceId.LARGE_STATION_BUTTON, Integer.valueOf(R.id.large_station_button)), TuplesKt.to(ResourceId.OVERFLOW_ICON, Integer.valueOf(R.id.overflow_icon)), TuplesKt.to(ResourceId.PLAY_ICON, Integer.valueOf(R.id.play_icon)), TuplesKt.to(ResourceId.SHARE_ICON, Integer.valueOf(R.id.share_icon)), TuplesKt.to(ResourceId.SHUFFLE_ICON, Integer.valueOf(R.id.shuffle_icon)), TuplesKt.to(ResourceId.STATION_ICON, Integer.valueOf(R.id.station_icon)));
    private static final String WIDGET_ID_KEY = "widgetId";
    private static final String IS_ACTIVE_KEY = "isActive";
    private static final String TOGGLE_ARTIST_INTENT = "com.amazon.music.TOGGLE_ARTIST";

    private Constants() {
    }

    public final SimpleDateFormat getOPEN_CALENDAR_DATE_FORMAT() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.ALTERNATE_ISO8601_DATE_PATTERN, Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    public final Map<ImageResources.Image, Integer> getResMap() {
        return ResMap;
    }

    public final Map<ResourceId, Integer> getIdMap() {
        return IdMap;
    }

    public final String getWIDGET_ID_KEY() {
        return WIDGET_ID_KEY;
    }

    public final String getIS_ACTIVE_KEY() {
        return IS_ACTIVE_KEY;
    }

    public final String getTOGGLE_ARTIST_INTENT() {
        return TOGGLE_ARTIST_INTENT;
    }
}

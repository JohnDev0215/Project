package com.amazon.mp3.voice.deeplink;

import android.content.Context;
import com.amazon.digitalmusicxp.enums.StationSeedTypeEnum;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.music.ContentAccessType;
import com.amazon.music.station.StationItem;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: StationItemProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006J\u001e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/voice/deeplink/StationItemProvider;", "", "()V", "getStationItemWithKey", "Lcom/amazon/music/station/StationItem;", "stationKey", "", "title", "imageUrl", "getStationItemWithPageToken", "context", "Landroid/content/Context;", "pageToken", "getStationItemWithSeedId", "stationSeedId", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StationItemProvider {
    private static final String TAG = Reflection.getOrCreateKotlinClass(StationItemProvider.class).getSimpleName();

    public final StationItem getStationItemWithPageToken(Context context, String pageToken) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pageToken, "pageToken");
        String string = context.getResources().getString(R.string.alexa_now_playing_collection_name);
        Intrinsics.checkNotNullExpressionValue(string, "context.getResources().g…_playing_collection_name)");
        StationItem stationItemWithKey = getStationItemWithKey("ALEXA_INITIATED_DYNAMIC_QUEUE", string, "");
        stationItemWithKey.setNextToken(pageToken);
        return stationItemWithKey;
    }

    public final StationItem getStationItemWithKey(String stationKey, String title, String imageUrl) {
        Intrinsics.checkNotNullParameter(stationKey, "stationKey");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        return new StationItem(new CatalogStatusTiers(true, true, true, true, false, false, false, 112, null).toSet(), StationItem.buildExplicitMap(false), title, imageUrl, null, null, stationKey, null);
    }

    public final StationItem getStationItemWithSeedId(String stationSeedId, String title, String imageUrl) {
        Intrinsics.checkNotNullParameter(stationSeedId, "stationSeedId");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        Set<ContentAccessType> set = new CatalogStatusTiers(true, true, true, true, false, false, false, 112, null).toSet();
        Map<ContentAccessType, Boolean> mapBuildExplicitMap = StationItem.buildExplicitMap(false);
        StationSeedTypeEnum stationSeedTypeEnum = StationSeedTypeEnum.STATION_SEED_ID;
        return new StationItem(set, mapBuildExplicitMap, title, imageUrl, stationSeedId, stationSeedTypeEnum.name(), "", stationSeedId, stationSeedTypeEnum.name(), stationSeedId);
    }
}

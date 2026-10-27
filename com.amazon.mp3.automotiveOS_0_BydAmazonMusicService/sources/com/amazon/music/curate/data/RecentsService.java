package com.amazon.music.curate.data;

import com.amazon.hermes.CoralError;
import com.amazon.music.curate.data.converter.Converters;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.nimblymusicservice.GetRecentTrackActivityResponse;
import com.amazon.nimblymusicservice.RecentPlayedTrackActivity;
import com.amazon.nimblymusicservice.RecentTrackActivityWithToken;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: RecentsService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \r2\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/curate/data/RecentsService;", "", "api", "Lcom/amazon/music/curate/data/RecentsServiceApi;", "(Lcom/amazon/music/curate/data/RecentsServiceApi;)V", "getApi", "()Lcom/amazon/music/curate/data/RecentsServiceApi;", "getRecents", "Lcom/amazon/music/curate/data/UserPlaylistRecsResponse;", Splash.PARAMS_DEVICE_TYPE, "", Splash.PARAMS_DEVICE_ID, "pageToken", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentsService {
    private static final Logger LOG;
    private final RecentsServiceApi api;

    public RecentsService(RecentsServiceApi api) {
        Intrinsics.checkNotNullParameter(api, "api");
        this.api = api;
    }

    public final RecentsServiceApi getApi() {
        return this.api;
    }

    static {
        Logger logger = LoggerFactory.getLogger(RecentsService.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(RecentsService::class.java.simpleName)");
        LOG = logger;
    }

    public static /* synthetic */ UserPlaylistRecsResponse getRecents$default(RecentsService recentsService, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        return recentsService.getRecents(str, str2, str3);
    }

    public final UserPlaylistRecsResponse getRecents(String deviceType, String deviceId, String pageToken) throws ServiceException {
        List<RecentPlayedTrackActivity> recentTrackList;
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        try {
            GetRecentTrackActivityResponse getRecentTrackActivityResponseFetchRecents = this.api.fetchRecents(pageToken, deviceType, deviceId);
            ArrayList arrayList = new ArrayList();
            RecentTrackActivityWithToken recentTrackActivityWithToken = getRecentTrackActivityResponseFetchRecents.getRecentActivityMap().get("PLAYED");
            if (recentTrackActivityWithToken != null && (recentTrackList = recentTrackActivityWithToken.getRecentTrackList()) != null) {
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(recentTrackList, 10));
                for (RecentPlayedTrackActivity it : recentTrackList) {
                    Converters.Companion companion = Converters.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    arrayList2.add(companion.convertRecentTrack(it));
                }
            }
            LOG.debug("Got " + arrayList.size() + " track recs");
            return new UserPlaylistRecsResponse(arrayList);
        } catch (CoralError e) {
            throw new ServiceException(e);
        }
    }
}

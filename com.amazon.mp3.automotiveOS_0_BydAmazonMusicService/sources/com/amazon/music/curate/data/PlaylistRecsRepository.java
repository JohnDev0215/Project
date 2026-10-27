package com.amazon.music.curate.data;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.data.database.RecsCache;
import com.amazon.music.curate.model.VisualModel;
import com.amazon.music.curate.provider.SubscriptionProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: PlaylistRecsRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ(\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u00142\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/curate/data/PlaylistRecsRepository;", "", "cache", "Lcom/amazon/music/curate/data/database/PlaylistDB;", "recsCache", "Lcom/amazon/music/curate/data/database/RecsCache;", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/curate/data/PlaylistService;", "subscriptionProvider", "Lcom/amazon/music/curate/provider/SubscriptionProvider;", "(Lcom/amazon/music/curate/data/database/PlaylistDB;Lcom/amazon/music/curate/data/database/RecsCache;Lcom/amazon/music/curate/data/PlaylistService;Lcom/amazon/music/curate/provider/SubscriptionProvider;)V", "getCache", "()Lcom/amazon/music/curate/data/database/PlaylistDB;", "getRecsCache", "()Lcom/amazon/music/curate/data/database/RecsCache;", "getService", "()Lcom/amazon/music/curate/data/PlaylistService;", "getSubscriptionProvider", "()Lcom/amazon/music/curate/provider/SubscriptionProvider;", "getPlaylistRecs", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lcom/amazon/music/curate/model/VisualModel;", "userPlaylistId", "", "onRecAdded", "Lkotlin/Function0;", "", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistRecsRepository {
    private static final Logger LOG;
    private static final int PAGE_SIZE = 20;
    private final PlaylistDB cache;
    private final RecsCache recsCache;
    private final PlaylistService service;
    private final SubscriptionProvider subscriptionProvider;

    public PlaylistRecsRepository(PlaylistDB cache, RecsCache recsCache, PlaylistService service, SubscriptionProvider subscriptionProvider) {
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(recsCache, "recsCache");
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(subscriptionProvider, "subscriptionProvider");
        this.cache = cache;
        this.recsCache = recsCache;
        this.service = service;
        this.subscriptionProvider = subscriptionProvider;
    }

    public final PlaylistDB getCache() {
        return this.cache;
    }

    public final RecsCache getRecsCache() {
        return this.recsCache;
    }

    public final PlaylistService getService() {
        return this.service;
    }

    public final SubscriptionProvider getSubscriptionProvider() {
        return this.subscriptionProvider;
    }

    static {
        Logger logger = LoggerFactory.getLogger(PlaylistRecsRepository.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(PlaylistRecsRe…y::class.java.simpleName)");
        LOG = logger;
    }

    public final LiveData<PagedList<VisualModel>> getPlaylistRecs(String userPlaylistId, Function0<Unit> onRecAdded) {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        Intrinsics.checkNotNullParameter(onRecAdded, "onRecAdded");
        LOG.debug("getPlaylistRecs(" + userPlaylistId + ')');
        return new LivePagedListBuilder(new PlaylistRecsDataSourceFactory(this.recsCache, this.cache, this.service, this.subscriptionProvider, onRecAdded, userPlaylistId), 20).build();
    }
}

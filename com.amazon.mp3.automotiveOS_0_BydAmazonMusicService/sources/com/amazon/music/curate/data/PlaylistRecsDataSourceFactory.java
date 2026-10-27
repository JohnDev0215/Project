package com.amazon.music.curate.data;

import androidx.core.app.NotificationCompat;
import androidx.paging.DataSource;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.data.database.RecsCache;
import com.amazon.music.curate.model.VisualModel;
import com.amazon.music.curate.provider.SubscriptionProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: PlaylistRecsDataSourceFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0013B;\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0010\u000f\u001a\u00020\u0002¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0012H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/curate/data/PlaylistRecsDataSourceFactory;", "Landroidx/paging/DataSource$Factory;", "", "Lcom/amazon/music/curate/model/VisualModel;", "recsCache", "Lcom/amazon/music/curate/data/database/RecsCache;", "cache", "Lcom/amazon/music/curate/data/database/PlaylistDB;", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/curate/data/PlaylistService;", "subscriptionProvider", "Lcom/amazon/music/curate/provider/SubscriptionProvider;", "onRecAdded", "Lkotlin/Function0;", "", "playlistId", "(Lcom/amazon/music/curate/data/database/RecsCache;Lcom/amazon/music/curate/data/database/PlaylistDB;Lcom/amazon/music/curate/data/PlaylistService;Lcom/amazon/music/curate/provider/SubscriptionProvider;Lkotlin/jvm/functions/Function0;Ljava/lang/String;)V", "create", "Landroidx/paging/DataSource;", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistRecsDataSourceFactory extends DataSource.Factory<String, VisualModel> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOG = LoggerFactory.getLogger(PlaylistRecsDataSourceFactory.class.getSimpleName());
    private final PlaylistDB cache;
    private final Function0<Unit> onRecAdded;
    private final String playlistId;
    private final RecsCache recsCache;
    private final PlaylistService service;
    private final SubscriptionProvider subscriptionProvider;

    /* JADX INFO: compiled from: PlaylistRecsDataSourceFactory.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/curate/data/PlaylistRecsDataSourceFactory$Companion;", "", "()V", "LOG", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "getLOG", "()Lorg/slf4j/Logger;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Logger getLOG() {
            return PlaylistRecsDataSourceFactory.LOG;
        }
    }

    public PlaylistRecsDataSourceFactory(RecsCache recsCache, PlaylistDB cache, PlaylistService service, SubscriptionProvider subscriptionProvider, Function0<Unit> onRecAdded, String playlistId) {
        Intrinsics.checkNotNullParameter(recsCache, "recsCache");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(subscriptionProvider, "subscriptionProvider");
        Intrinsics.checkNotNullParameter(onRecAdded, "onRecAdded");
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        this.recsCache = recsCache;
        this.cache = cache;
        this.service = service;
        this.subscriptionProvider = subscriptionProvider;
        this.onRecAdded = onRecAdded;
        this.playlistId = playlistId;
    }

    @Override // androidx.paging.DataSource.Factory
    public DataSource<String, VisualModel> create() {
        return new PlaylistRecsDataSourceFactory$create$dataSource$1(this);
    }
}

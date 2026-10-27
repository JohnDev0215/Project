package com.amazon.music.curate.data;

import androidx.core.app.NotificationCompat;
import androidx.paging.PagedList;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.VisualModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: PlaylistsBoundaryCallback.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J`\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\f2>\u0010\u0010\u001a:\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u000f\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u000e0\u00112\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0018H&J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u0002H\u0016J\b\u0010\u001b\u001a\u00020\u000eH\u0016J\u0006\u0010\u001c\u001a\u00020\u000eR\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/curate/data/PlaylistsBoundaryCallback;", "Landroidx/paging/PagedList$BoundaryCallback;", "Lcom/amazon/music/curate/model/VisualModel;", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/curate/data/PlaylistService;", "localDB", "Lcom/amazon/music/curate/data/database/PlaylistDB;", "(Lcom/amazon/music/curate/data/PlaylistService;Lcom/amazon/music/curate/data/database/PlaylistDB;)V", "hasMorePages", "", "isRequestInProgress", "pageToken", "", "getNextPage", "", "token", "onCompleted", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "", "Lcom/amazon/music/curate/model/Playlist;", "playlists", "onError", "Lkotlin/Function0;", "onItemAtEndLoaded", "itemAtEnd", "onZeroItemsLoaded", "requestAndSaveData", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class PlaylistsBoundaryCallback extends PagedList.BoundaryCallback<VisualModel> {
    private static final Logger LOG;
    private boolean hasMorePages;
    private boolean isRequestInProgress;
    private final PlaylistDB localDB;
    private String pageToken;
    private final PlaylistService service;

    public abstract void getNextPage(String token, Function2<? super String, ? super List<? extends Playlist>, Unit> onCompleted, Function0<Unit> onError);

    public PlaylistsBoundaryCallback(PlaylistService service, PlaylistDB localDB) {
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(localDB, "localDB");
        this.service = service;
        this.localDB = localDB;
        this.hasMorePages = true;
    }

    static {
        Logger logger = LoggerFactory.getLogger(PlaylistsBoundaryCallback.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(PlaylistsBound…k::class.java.simpleName)");
        LOG = logger;
    }

    @Override // androidx.paging.PagedList.BoundaryCallback
    public void onZeroItemsLoaded() {
        LOG.debug("onZeroItemsLoaded");
        requestAndSaveData();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.paging.PagedList.BoundaryCallback
    public void onItemAtEndLoaded(VisualModel itemAtEnd) {
        Intrinsics.checkNotNullParameter(itemAtEnd, "itemAtEnd");
        LOG.debug("onItemAtEndLoaded");
        requestAndSaveData();
    }

    public final void requestAndSaveData() {
        if (!this.hasMorePages || this.isRequestInProgress) {
            return;
        }
        this.isRequestInProgress = true;
        getNextPage(null, new Function2<String, List<? extends Playlist>, Unit>() { // from class: com.amazon.music.curate.data.PlaylistsBoundaryCallback$requestAndSaveData$onCompleted$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ Unit mo4invoke(String str, List<? extends Playlist> list) {
                invoke2(str, list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final String str, List<? extends Playlist> playlists) {
                Intrinsics.checkNotNullParameter(playlists, "playlists");
                PlaylistDB playlistDB = this.this$0.localDB;
                final PlaylistsBoundaryCallback playlistsBoundaryCallback = this.this$0;
                playlistDB.putPlaylists(playlists, new Function0<Unit>() { // from class: com.amazon.music.curate.data.PlaylistsBoundaryCallback$requestAndSaveData$onCompleted$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        playlistsBoundaryCallback.hasMorePages = str != null;
                        playlistsBoundaryCallback.pageToken = str;
                        playlistsBoundaryCallback.isRequestInProgress = false;
                    }
                });
            }
        }, new Function0<Unit>() { // from class: com.amazon.music.curate.data.PlaylistsBoundaryCallback$requestAndSaveData$onError$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.isRequestInProgress = false;
            }
        });
    }
}

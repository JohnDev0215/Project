package com.amazon.music.curate.data;

import androidx.core.app.NotificationCompat;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.model.Playlist;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/* JADX INFO: compiled from: UserPlaylistsBoundaryCallback.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J`\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2>\u0010\u000b\u001a:\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\t\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\b0\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/curate/data/UserPlaylistsBoundaryCallback;", "Lcom/amazon/music/curate/data/PlaylistsBoundaryCallback;", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/curate/data/PlaylistService;", "localDB", "Lcom/amazon/music/curate/data/database/PlaylistDB;", "(Lcom/amazon/music/curate/data/PlaylistService;Lcom/amazon/music/curate/data/database/PlaylistDB;)V", "getNextPage", "", "token", "", "onCompleted", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "", "Lcom/amazon/music/curate/model/Playlist;", "playlists", "onError", "Lkotlin/Function0;", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserPlaylistsBoundaryCallback extends PlaylistsBoundaryCallback {
    private static final Logger LOG;
    private final PlaylistDB localDB;
    private final PlaylistService service;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserPlaylistsBoundaryCallback(PlaylistService service, PlaylistDB localDB) {
        super(service, localDB);
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(localDB, "localDB");
        this.service = service;
        this.localDB = localDB;
    }

    static {
        Logger logger = LoggerFactory.getLogger(UserPlaylistsBoundaryCallback.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(UserPlaylistsB…k::class.java.simpleName)");
        LOG = logger;
    }

    @Override // com.amazon.music.curate.data.PlaylistsBoundaryCallback
    public void getNextPage(String token, final Function2<? super String, ? super List<? extends Playlist>, Unit> onCompleted, final Function0<Unit> onError) {
        Intrinsics.checkNotNullParameter(onCompleted, "onCompleted");
        Intrinsics.checkNotNullParameter(onError, "onError");
        this.service.getUserPlaylists(token).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GetUserPlaylistsResponse>() { // from class: com.amazon.music.curate.data.UserPlaylistsBoundaryCallback.getNextPage.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onNext(GetUserPlaylistsResponse response) {
                Logger logger = UserPlaylistsBoundaryCallback.LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("Adding ");
                Intrinsics.checkNotNull(response);
                sb.append(response.getPlaylists().size());
                sb.append(" playlists to cache. Next token is ");
                sb.append((Object) response.getToken());
                logger.debug(sb.toString());
                onCompleted.mo4invoke(response.getToken(), response.getPlaylists());
            }

            @Override // rx.Observer
            public void onError(Throwable error) {
                UserPlaylistsBoundaryCallback.LOG.warn("Failed to retrieve playlists", error);
                onError.invoke();
            }
        });
    }
}

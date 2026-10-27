package com.amazon.music.curate.data;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.VisualModel;
import com.amazon.music.curate.model.VisualRowItem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserPlaylistsRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J5\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/curate/data/UserPlaylistsRepository;", "", "cache", "Lcom/amazon/music/curate/data/database/PlaylistDB;", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/curate/data/PlaylistService;", "(Lcom/amazon/music/curate/data/database/PlaylistDB;Lcom/amazon/music/curate/data/PlaylistService;)V", "getUserPlaylists", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lcom/amazon/music/curate/model/VisualModel;", "onClickAction", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "playlistId", "", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserPlaylistsRepository {
    private static final int PAGE_SIZE = 20;
    private final PlaylistDB cache;
    private final PlaylistService service;

    public UserPlaylistsRepository(PlaylistDB cache, PlaylistService service) {
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(service, "service");
        this.cache = cache;
        this.service = service;
    }

    public final LiveData<PagedList<VisualModel>> getUserPlaylists(final Function1<? super String, Unit> onClickAction) {
        Intrinsics.checkNotNullParameter(onClickAction, "onClickAction");
        DataSource.Factory map = this.cache.getPlaylists().map(new Function1<Playlist, VisualModel>() { // from class: com.amazon.music.curate.data.UserPlaylistsRepository$getUserPlaylists$dataSourceFactory$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final VisualModel invoke(final Playlist playlist) {
                Intrinsics.checkNotNullParameter(playlist, "playlist");
                VisualRowItem.Builder builder = new VisualRowItem.Builder(playlist.getId(), playlist.getArtworkUrl(), playlist.getTitle());
                final Function1<String, Unit> function1 = onClickAction;
                return builder.onClick(new Function0<Unit>() { // from class: com.amazon.music.curate.data.UserPlaylistsRepository$getUserPlaylists$dataSourceFactory$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
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
                        function1.invoke(playlist.getId());
                    }
                }).build();
            }
        });
        return new LivePagedListBuilder(map, 20).setBoundaryCallback(new UserPlaylistsBoundaryCallback(this.service, this.cache)).build();
    }
}

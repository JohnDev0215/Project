package com.amazon.music.curate.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import com.amazon.music.curate.data.PlaylistRecsRepository;
import com.amazon.music.curate.model.VisualModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistRecsViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/curate/view/PlaylistRecsViewModel;", "Landroidx/lifecycle/ViewModel;", "userPlaylistId", "", "userPlaylistViewModel", "Lcom/amazon/music/curate/view/UserPlaylistViewModel;", "playlistRecsRepository", "Lcom/amazon/music/curate/data/PlaylistRecsRepository;", "(Ljava/lang/String;Lcom/amazon/music/curate/view/UserPlaylistViewModel;Lcom/amazon/music/curate/data/PlaylistRecsRepository;)V", "playlistRecsLiveData", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lcom/amazon/music/curate/model/VisualModel;", "getPlaylistRecsLiveData", "()Landroidx/lifecycle/LiveData;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistRecsViewModel extends ViewModel {
    private final LiveData<PagedList<VisualModel>> playlistRecsLiveData;
    private final PlaylistRecsRepository playlistRecsRepository;
    private final String userPlaylistId;
    private final UserPlaylistViewModel userPlaylistViewModel;

    public PlaylistRecsViewModel(String userPlaylistId, UserPlaylistViewModel userPlaylistViewModel, PlaylistRecsRepository playlistRecsRepository) {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        Intrinsics.checkNotNullParameter(userPlaylistViewModel, "userPlaylistViewModel");
        Intrinsics.checkNotNullParameter(playlistRecsRepository, "playlistRecsRepository");
        this.userPlaylistId = userPlaylistId;
        this.userPlaylistViewModel = userPlaylistViewModel;
        this.playlistRecsRepository = playlistRecsRepository;
        this.playlistRecsLiveData = playlistRecsRepository.getPlaylistRecs(userPlaylistId, new Function0<Unit>() { // from class: com.amazon.music.curate.view.PlaylistRecsViewModel$playlistRecsLiveData$1
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
                DataSource<Integer, VisualModel> value = this.this$0.userPlaylistViewModel.getPlaylistLiveData().getDataSource().getValue();
                if (value == null) {
                    return;
                }
                value.invalidate();
            }
        });
    }

    public final LiveData<PagedList<VisualModel>> getPlaylistRecsLiveData() {
        return this.playlistRecsLiveData;
    }
}

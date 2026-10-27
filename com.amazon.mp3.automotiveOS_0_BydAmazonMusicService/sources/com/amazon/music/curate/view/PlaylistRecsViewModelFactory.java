package com.amazon.music.curate.view;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.music.curate.data.PlaylistRecsRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistRecsViewModelFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ%\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/curate/view/PlaylistRecsViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "userPlaylistId", "", "userPlaylistViewModel", "Lcom/amazon/music/curate/view/UserPlaylistViewModel;", "playlistRecsRepository", "Lcom/amazon/music/curate/data/PlaylistRecsRepository;", "(Ljava/lang/String;Lcom/amazon/music/curate/view/UserPlaylistViewModel;Lcom/amazon/music/curate/data/PlaylistRecsRepository;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistRecsViewModelFactory implements ViewModelProvider.Factory {
    private final PlaylistRecsRepository playlistRecsRepository;
    private final String userPlaylistId;
    private final UserPlaylistViewModel userPlaylistViewModel;

    public PlaylistRecsViewModelFactory(String userPlaylistId, UserPlaylistViewModel userPlaylistViewModel, PlaylistRecsRepository playlistRecsRepository) {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        Intrinsics.checkNotNullParameter(userPlaylistViewModel, "userPlaylistViewModel");
        Intrinsics.checkNotNullParameter(playlistRecsRepository, "playlistRecsRepository");
        this.userPlaylistId = userPlaylistId;
        this.userPlaylistViewModel = userPlaylistViewModel;
        this.playlistRecsRepository = playlistRecsRepository;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(PlaylistRecsViewModel.class)) {
            return new PlaylistRecsViewModel(this.userPlaylistId, this.userPlaylistViewModel, this.playlistRecsRepository);
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("UserPlaylistViewModelFactory cannot create ", modelClass));
    }
}

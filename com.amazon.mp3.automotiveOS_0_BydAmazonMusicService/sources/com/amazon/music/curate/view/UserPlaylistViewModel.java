package com.amazon.music.curate.view;

import android.view.View;
import androidx.lifecycle.ViewModel;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.data.PlaylistLiveData;
import com.amazon.music.curate.data.UserPlaylistRepository;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;

/* JADX INFO: compiled from: UserPlaylistViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0006\u0010(\u001a\u00020\u000eJ\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000e0*2\u0006\u0010\u0002\u001a\u00020\u0003J)\u0010+\u001a\u00020\u000e2!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tJ)\u0010,\u001a\u00020\u000e2!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tJ\u0014\u0010-\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011J)\u0010.\u001a\u00020\u000e2!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000e0\tJY\u0010/\u001a\u00020\u000e2Q\u0010\u0015\u001aM\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u000e0\u0016J)\u00100\u001a\u00020\u000e2!\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u000e0\tJ)\u00101\u001a\u00020\u000e2!\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tJD\u00102\u001a\u00020\u000e2<\u0010\u001f\u001a8\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u000e0 J/\u00103\u001a\u00020\u000e2'\u0010!\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u000e0\tR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0012\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R[\u0010\u0015\u001aO\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u001c\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u001e\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000RF\u0010\u001f\u001a:\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u000e\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R1\u0010!\u001a%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u00020#X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/amazon/music/curate/view/UserPlaylistViewModel;", "Landroidx/lifecycle/ViewModel;", "userPlaylistId", "", "repository", "Lcom/amazon/music/curate/data/UserPlaylistRepository;", "fragmentSessionId", "(Ljava/lang/String;Lcom/amazon/music/curate/data/UserPlaylistRepository;Ljava/lang/String;)V", "onDownloadButtonClick", "Lkotlin/Function1;", "Lcom/amazon/music/curate/model/Playlist;", "Lkotlin/ParameterName;", "name", Environment.PLAYLIST_PATH, "", "onEditButtonClick", "onGetFragmentSessionId", "Lkotlin/Function0;", "onMoreClick", "Landroid/view/View;", "view", "onPlayButtonClick", "Lkotlin/Function3;", "", "Lcom/amazon/music/curate/model/Track;", "tracks", "", "index", "onSeeMoreClick", "playlistId", "onShareButtonClick", "onShuffleButtonClick", "Lkotlin/Function2;", "onStationButtonClick", "playlistLiveData", "Lcom/amazon/music/curate/data/PlaylistLiveData;", "getPlaylistLiveData", "()Lcom/amazon/music/curate/data/PlaylistLiveData;", "setPlaylistLiveData", "(Lcom/amazon/music/curate/data/PlaylistLiveData;)V", "refresh", "removePlaylist", "Lrx/Observable;", "setOnDownloadButtonClick", "setOnEditButtonClick", "setOnGetFragmentSessionId", "setOnMoreClick", "setOnPlayButtonClick", "setOnSeeMoreClick", "setOnShareButtonClick", "setOnShuffleButtonClick", "setOnStationButtonClick", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserPlaylistViewModel extends ViewModel {
    private String fragmentSessionId;
    private Function1<? super Playlist, Unit> onDownloadButtonClick;
    private Function1<? super Playlist, Unit> onEditButtonClick;
    private Function0<String> onGetFragmentSessionId;
    private Function1<? super View, Unit> onMoreClick;
    private Function3<? super Playlist, ? super List<Track>, ? super Integer, Unit> onPlayButtonClick;
    private Function1<? super String, Unit> onSeeMoreClick;
    private Function1<? super Playlist, Unit> onShareButtonClick;
    private Function2<? super Playlist, ? super List<Track>, Unit> onShuffleButtonClick;
    private Function1<? super List<Track>, Unit> onStationButtonClick;
    public PlaylistLiveData playlistLiveData;
    private final UserPlaylistRepository repository;
    private final String userPlaylistId;

    public UserPlaylistViewModel(String userPlaylistId, UserPlaylistRepository repository, String str) {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.userPlaylistId = userPlaylistId;
        this.repository = repository;
        this.fragmentSessionId = str;
        refresh();
    }

    public final PlaylistLiveData getPlaylistLiveData() {
        PlaylistLiveData playlistLiveData = this.playlistLiveData;
        if (playlistLiveData != null) {
            return playlistLiveData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playlistLiveData");
        return null;
    }

    public final void setPlaylistLiveData(PlaylistLiveData playlistLiveData) {
        Intrinsics.checkNotNullParameter(playlistLiveData, "<set-?>");
        this.playlistLiveData = playlistLiveData;
    }

    public final Observable<Unit> removePlaylist(String userPlaylistId) {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        return this.repository.removeUserPlaylist(userPlaylistId);
    }

    public final void refresh() {
        setPlaylistLiveData(this.repository.getUserPlaylist(this.userPlaylistId, new Function1<String, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistViewModel.refresh.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function1 function1 = UserPlaylistViewModel.this.onSeeMoreClick;
                if (function1 == null) {
                    return;
                }
                function1.invoke(it);
            }
        }, new Function1<View, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistViewModel.refresh.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function1 function1 = UserPlaylistViewModel.this.onMoreClick;
                if (function1 == null) {
                    return;
                }
                function1.invoke(it);
            }
        }, new Function3<Playlist, List<? extends Track>, Integer, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistViewModel.refresh.3
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Playlist playlist, List<? extends Track> list, Integer num) {
                invoke(playlist, (List<Track>) list, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Playlist playlist, List<Track> tracks, int i) {
                Intrinsics.checkNotNullParameter(playlist, "playlist");
                Intrinsics.checkNotNullParameter(tracks, "tracks");
                Function3 function3 = UserPlaylistViewModel.this.onPlayButtonClick;
                if (function3 == null) {
                    return;
                }
                function3.invoke(playlist, tracks, Integer.valueOf(i));
            }
        }, new Function2<Playlist, List<? extends Track>, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistViewModel.refresh.4
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ Unit mo4invoke(Playlist playlist, List<? extends Track> list) {
                invoke2(playlist, (List<Track>) list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Playlist playlist, List<Track> tracks) {
                Intrinsics.checkNotNullParameter(playlist, "playlist");
                Intrinsics.checkNotNullParameter(tracks, "tracks");
                Function2 function2 = UserPlaylistViewModel.this.onShuffleButtonClick;
                if (function2 == null) {
                    return;
                }
                function2.mo4invoke(playlist, tracks);
            }
        }, new Function1<Playlist, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistViewModel.refresh.5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Playlist playlist) {
                invoke2(playlist);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Playlist playlist) {
                Intrinsics.checkNotNullParameter(playlist, "playlist");
                Function1 function1 = UserPlaylistViewModel.this.onDownloadButtonClick;
                if (function1 == null) {
                    return;
                }
                function1.invoke(playlist);
            }
        }, new Function1<Playlist, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistViewModel.refresh.6
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Playlist playlist) {
                invoke2(playlist);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Playlist playlist) {
                Intrinsics.checkNotNullParameter(playlist, "playlist");
                Function1 function1 = UserPlaylistViewModel.this.onShareButtonClick;
                if (function1 == null) {
                    return;
                }
                function1.invoke(playlist);
            }
        }, new Function1<List<? extends Track>, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistViewModel.refresh.7
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Track> list) {
                invoke2((List<Track>) list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<Track> tracks) {
                Intrinsics.checkNotNullParameter(tracks, "tracks");
                Function1 function1 = UserPlaylistViewModel.this.onStationButtonClick;
                if (function1 == null) {
                    return;
                }
                function1.invoke(tracks);
            }
        }, new Function1<Playlist, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistViewModel.refresh.8
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Playlist playlist) {
                invoke2(playlist);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Playlist it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function1 function1 = UserPlaylistViewModel.this.onEditButtonClick;
                if (function1 == null) {
                    return;
                }
                function1.invoke(it);
            }
        }, new Function0<String>() { // from class: com.amazon.music.curate.view.UserPlaylistViewModel.refresh.9
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Function0 function0 = UserPlaylistViewModel.this.onGetFragmentSessionId;
                if (function0 == null) {
                    return null;
                }
                return (String) function0.invoke();
            }
        }, this.fragmentSessionId));
    }

    public final void setOnSeeMoreClick(Function1<? super String, Unit> onSeeMoreClick) {
        Intrinsics.checkNotNullParameter(onSeeMoreClick, "onSeeMoreClick");
        this.onSeeMoreClick = onSeeMoreClick;
    }

    public final void setOnMoreClick(Function1<? super View, Unit> onMoreClick) {
        Intrinsics.checkNotNullParameter(onMoreClick, "onMoreClick");
        this.onMoreClick = onMoreClick;
    }

    public final void setOnPlayButtonClick(Function3<? super Playlist, ? super List<Track>, ? super Integer, Unit> onPlayButtonClick) {
        Intrinsics.checkNotNullParameter(onPlayButtonClick, "onPlayButtonClick");
        this.onPlayButtonClick = onPlayButtonClick;
    }

    public final void setOnShuffleButtonClick(Function2<? super Playlist, ? super List<Track>, Unit> onShuffleButtonClick) {
        Intrinsics.checkNotNullParameter(onShuffleButtonClick, "onShuffleButtonClick");
        this.onShuffleButtonClick = onShuffleButtonClick;
    }

    public final void setOnDownloadButtonClick(Function1<? super Playlist, Unit> onDownloadButtonClick) {
        Intrinsics.checkNotNullParameter(onDownloadButtonClick, "onDownloadButtonClick");
        this.onDownloadButtonClick = onDownloadButtonClick;
    }

    public final void setOnShareButtonClick(Function1<? super Playlist, Unit> onShareButtonClick) {
        Intrinsics.checkNotNullParameter(onShareButtonClick, "onShareButtonClick");
        this.onShareButtonClick = onShareButtonClick;
    }

    public final void setOnStationButtonClick(Function1<? super List<Track>, Unit> onStationButtonClick) {
        Intrinsics.checkNotNullParameter(onStationButtonClick, "onStationButtonClick");
        this.onStationButtonClick = onStationButtonClick;
    }

    public final void setOnEditButtonClick(Function1<? super Playlist, Unit> onEditButtonClick) {
        Intrinsics.checkNotNullParameter(onEditButtonClick, "onEditButtonClick");
        this.onEditButtonClick = onEditButtonClick;
    }

    public final void setOnGetFragmentSessionId(Function0<String> onGetFragmentSessionId) {
        Intrinsics.checkNotNullParameter(onGetFragmentSessionId, "onGetFragmentSessionId");
        this.onGetFragmentSessionId = onGetFragmentSessionId;
        if (this.fragmentSessionId == null) {
            this.fragmentSessionId = onGetFragmentSessionId.invoke();
        }
    }
}

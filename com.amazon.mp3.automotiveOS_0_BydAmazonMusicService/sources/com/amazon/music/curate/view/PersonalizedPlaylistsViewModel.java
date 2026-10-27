package com.amazon.music.curate.view;

import android.view.View;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.data.PersonalizedPlaylistsRepository;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.VisualModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PersonalizedPlaylistsViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0017\u001a\u00020\u000bJ)\u0010\u0018\u001a\u00020\u000b2!\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006J)\u0010\u0019\u001a\u00020\u000b2!\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000b0\u0006R+\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/curate/view/PersonalizedPlaylistsViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/amazon/music/curate/data/PersonalizedPlaylistsRepository;", "(Lcom/amazon/music/curate/data/PersonalizedPlaylistsRepository;)V", "onClickAction", "Lkotlin/Function1;", "Lcom/amazon/music/curate/model/Playlist;", "Lkotlin/ParameterName;", "name", Environment.PLAYLIST_PATH, "", "onMoreClick", "Landroid/view/View;", "view", "personalizedPlaylists", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lcom/amazon/music/curate/model/VisualModel;", "getPersonalizedPlaylists", "()Landroidx/lifecycle/LiveData;", "getRepository", "()Lcom/amazon/music/curate/data/PersonalizedPlaylistsRepository;", "deletePersonalizedPlaylists", "setOnItemClick", "setOnMoreClick", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PersonalizedPlaylistsViewModel extends ViewModel {
    private Function1<? super Playlist, Unit> onClickAction;
    private Function1<? super View, Unit> onMoreClick;
    private final LiveData<PagedList<VisualModel>> personalizedPlaylists;
    private final PersonalizedPlaylistsRepository repository;

    public PersonalizedPlaylistsViewModel(PersonalizedPlaylistsRepository repository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        this.personalizedPlaylists = repository.getPersonalizedPlaylists(new Function1<Playlist, Unit>() { // from class: com.amazon.music.curate.view.PersonalizedPlaylistsViewModel$personalizedPlaylists$1
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
                Function1 function1 = this.this$0.onClickAction;
                if (function1 == null) {
                    return;
                }
                function1.invoke(it);
            }
        }, new Function1<View, Unit>() { // from class: com.amazon.music.curate.view.PersonalizedPlaylistsViewModel$personalizedPlaylists$2
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
                Function1 function1 = this.this$0.onMoreClick;
                if (function1 == null) {
                    return;
                }
                function1.invoke(it);
            }
        });
    }

    public final PersonalizedPlaylistsRepository getRepository() {
        return this.repository;
    }

    public final LiveData<PagedList<VisualModel>> getPersonalizedPlaylists() {
        return this.personalizedPlaylists;
    }

    public final void setOnItemClick(Function1<? super Playlist, Unit> onClickAction) {
        Intrinsics.checkNotNullParameter(onClickAction, "onClickAction");
        this.onClickAction = onClickAction;
    }

    public final void setOnMoreClick(Function1<? super View, Unit> onMoreClick) {
        Intrinsics.checkNotNullParameter(onMoreClick, "onMoreClick");
        this.onMoreClick = onMoreClick;
    }

    public final void deletePersonalizedPlaylists() {
        this.repository.deletePersonalizedPlaylists();
    }
}

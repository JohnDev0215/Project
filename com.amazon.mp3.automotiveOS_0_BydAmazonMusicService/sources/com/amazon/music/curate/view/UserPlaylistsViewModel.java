package com.amazon.music.curate.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import com.amazon.music.curate.data.UserPlaylistsRepository;
import com.amazon.music.curate.model.VisualModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserPlaylistsViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J)\u0010\u0014\u001a\u00020\u000b2!\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006R+\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/curate/view/UserPlaylistsViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/amazon/music/curate/data/UserPlaylistsRepository;", "(Lcom/amazon/music/curate/data/UserPlaylistsRepository;)V", "onClickAction", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "playlistId", "", "getRepository", "()Lcom/amazon/music/curate/data/UserPlaylistsRepository;", "userPlaylists", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lcom/amazon/music/curate/model/VisualModel;", "getUserPlaylists", "()Landroidx/lifecycle/LiveData;", "setOnItemClick", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserPlaylistsViewModel extends ViewModel {
    private Function1<? super String, Unit> onClickAction;
    private final UserPlaylistsRepository repository;
    private final LiveData<PagedList<VisualModel>> userPlaylists;

    public UserPlaylistsViewModel(UserPlaylistsRepository repository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        this.userPlaylists = repository.getUserPlaylists(new Function1<String, Unit>() { // from class: com.amazon.music.curate.view.UserPlaylistsViewModel$userPlaylists$1
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
                Function1 function1 = this.this$0.onClickAction;
                if (function1 == null) {
                    return;
                }
                function1.invoke(it);
            }
        });
    }

    public final UserPlaylistsRepository getRepository() {
        return this.repository;
    }

    public final LiveData<PagedList<VisualModel>> getUserPlaylists() {
        return this.userPlaylists;
    }

    public final void setOnItemClick(Function1<? super String, Unit> onClickAction) {
        Intrinsics.checkNotNullParameter(onClickAction, "onClickAction");
        this.onClickAction = onClickAction;
    }
}

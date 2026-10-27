package com.amazon.music.curate.view;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.music.curate.data.UserPlaylistRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserPlaylistViewModelFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J%\u0010\b\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH\u0016¢\u0006\u0002\u0010\rR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/curate/view/UserPlaylistViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "userPlaylistId", "", "repository", "Lcom/amazon/music/curate/data/UserPlaylistRepository;", "fragmentSessionId", "(Ljava/lang/String;Lcom/amazon/music/curate/data/UserPlaylistRepository;Ljava/lang/String;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserPlaylistViewModelFactory implements ViewModelProvider.Factory {
    private final String fragmentSessionId;
    private final UserPlaylistRepository repository;
    private final String userPlaylistId;

    public UserPlaylistViewModelFactory(String userPlaylistId, UserPlaylistRepository repository, String str) {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.userPlaylistId = userPlaylistId;
        this.repository = repository;
        this.fragmentSessionId = str;
    }

    public /* synthetic */ UserPlaylistViewModelFactory(String str, UserPlaylistRepository userPlaylistRepository, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, userPlaylistRepository, (i & 4) != 0 ? null : str2);
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(UserPlaylistViewModel.class)) {
            return new UserPlaylistViewModel(this.userPlaylistId, this.repository, this.fragmentSessionId);
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("UserPlaylistViewModelFactory cannot create ", modelClass));
    }
}

package com.amazon.music.curate.view;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.music.curate.data.MySoundtrackRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MySoundtrackViewModelFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u000b\u001a\u0002H\f\"\b\b\u0000\u0010\f*\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u000fH\u0016¢\u0006\u0002\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/curate/view/MySoundtrackViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "context", "Landroid/content/Context;", "repository", "Lcom/amazon/music/curate/data/MySoundtrackRepository;", "(Landroid/content/Context;Lcom/amazon/music/curate/data/MySoundtrackRepository;)V", "getContext", "()Landroid/content/Context;", "getRepository", "()Lcom/amazon/music/curate/data/MySoundtrackRepository;", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MySoundtrackViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;
    private final MySoundtrackRepository repository;

    public MySoundtrackViewModelFactory(Context context, MySoundtrackRepository repository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.context = context;
        this.repository = repository;
    }

    public final Context getContext() {
        return this.context;
    }

    public final MySoundtrackRepository getRepository() {
        return this.repository;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(MySoundtrackViewModel.class)) {
            return new MySoundtrackViewModel(this.context, this.repository);
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("MySoundtrackViewModelFactory cannot create ", modelClass));
    }
}

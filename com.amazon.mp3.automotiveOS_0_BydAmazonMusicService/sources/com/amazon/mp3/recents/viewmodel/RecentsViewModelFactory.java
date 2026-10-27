package com.amazon.mp3.recents.viewmodel;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.mp3.recents.usecase.RecentsModelUseCase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentsViewModelFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/recents/viewmodel/RecentsViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "application", "Landroid/app/Application;", "useCase", "Lcom/amazon/mp3/recents/usecase/RecentsModelUseCase;", "(Landroid/app/Application;Lcom/amazon/mp3/recents/usecase/RecentsModelUseCase;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "p0", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentsViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;
    private final RecentsModelUseCase useCase;

    public RecentsViewModelFactory(Application application, RecentsModelUseCase useCase) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(useCase, "useCase");
        this.application = application;
        this.useCase = useCase;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return new RecentsViewModel(this.application, this.useCase);
    }
}

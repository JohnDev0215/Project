package com.amazon.mp3.view.lyrics.sharing.viewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsSelectionViewModelFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/viewModel/LyricsSelectionViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "lyricsSharingDataModel", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "(Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LyricsSelectionViewModelFactory implements ViewModelProvider.Factory {
    private final LyricsSharingDataModel lyricsSharingDataModel;

    public LyricsSelectionViewModelFactory(LyricsSharingDataModel lyricsSharingDataModel) {
        Intrinsics.checkNotNullParameter(lyricsSharingDataModel, "lyricsSharingDataModel");
        this.lyricsSharingDataModel = lyricsSharingDataModel;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(LyricsSelectionViewModel.class)) {
            return new LyricsSelectionViewModel(this.lyricsSharingDataModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

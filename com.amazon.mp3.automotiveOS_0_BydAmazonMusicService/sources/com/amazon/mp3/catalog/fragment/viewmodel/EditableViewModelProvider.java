package com.amazon.mp3.catalog.fragment.viewmodel;

import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import kotlin.Metadata;

/* JADX INFO: compiled from: EditableViewModelProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/EditableViewModelProvider;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter$ItemMovedListener;", "", "index", "", "deleteModel", "clearModels", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface EditableViewModelProvider extends PageViewModelProvider, UniversalAdapter.ItemMovedListener {
    void clearModels();

    void deleteModel(int index);
}

package com.amazon.mp3.catalog.fragment.datasource.repository;

import com.amazon.mp3.catalog.fragment.datasource.dao.EditablePageViewModelDao;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EditablePageDataRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/repository/EditablePageDataRepository;", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "modelDao", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/EditablePageViewModelDao;", "(Lcom/amazon/mp3/catalog/fragment/datasource/dao/EditablePageViewModelDao;)V", "clearModels", "", "deleteModel", "index", "", "reorderModel", "from", "to", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class EditablePageDataRepository extends PageDataRepository {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditablePageDataRepository(EditablePageViewModelDao modelDao) {
        super(modelDao);
        Intrinsics.checkNotNullParameter(modelDao, "modelDao");
    }

    public final void reorderModel(int from, int to) {
        ((EditablePageViewModelDao) getModelDao()).reorderItem(from, to);
    }

    public final void deleteModel(int index) {
        ((EditablePageViewModelDao) getModelDao()).deleteItem(index);
    }

    public final void clearModels() {
        ((EditablePageViewModelDao) getModelDao()).clearItems();
    }
}

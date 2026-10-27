package com.amazon.mp3.catalog.fragment.datasource.dao;

import com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EditableViewModelDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H&¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/EditablePageViewModelDao;", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;", "clearItems", "", "deleteItem", "index", "", "reorderItem", "from", "to", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface EditablePageViewModelDao extends PageViewModelDao {
    void clearItems();

    void deleteItem(int index);

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    /* synthetic */ int getDefaultPageSize();

    void reorderItem(int from, int to);

    /* JADX INFO: compiled from: EditableViewModelDao.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static int getDefaultPageSize(EditablePageViewModelDao editablePageViewModelDao) {
            Intrinsics.checkNotNullParameter(editablePageViewModelDao, "this");
            return PageViewModelDao.DefaultImpls.getDefaultPageSize(editablePageViewModelDao);
        }
    }
}

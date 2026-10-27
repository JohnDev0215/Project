package com.byd.music.beans;

import com.byd.music.beans.SearchHistoryBean_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.internal.CursorFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class SearchHistoryBeanCursor extends Cursor<SearchHistoryBean> {
    private static final SearchHistoryBean_.SearchHistoryBeanIdGetter ID_GETTER = SearchHistoryBean_.__ID_GETTER;
    private static final int __ID_title = SearchHistoryBean_.title.id;
    private static final int __ID_type = SearchHistoryBean_.type.id;

    static final class Factory implements CursorFactory<SearchHistoryBean> {
        Factory() {
        }

        @Override // io.objectbox.internal.CursorFactory
        public Cursor<SearchHistoryBean> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new SearchHistoryBeanCursor(transaction, j, boxStore);
        }
    }

    public SearchHistoryBeanCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, SearchHistoryBean_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(SearchHistoryBean searchHistoryBean) {
        return ID_GETTER.getId(searchHistoryBean);
    }

    @Override // io.objectbox.Cursor
    public final long put(SearchHistoryBean searchHistoryBean) {
        String title = searchHistoryBean.getTitle();
        long jCollect313311 = collect313311(this.cursor, searchHistoryBean.getId(), 3, title != null ? __ID_title : 0, title, 0, null, 0, null, 0, null, __ID_type, searchHistoryBean.getType(), 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        searchHistoryBean.setId(jCollect313311);
        return jCollect313311;
    }
}

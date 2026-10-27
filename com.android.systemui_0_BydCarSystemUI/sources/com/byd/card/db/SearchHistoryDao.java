package com.byd.card.db;

import com.byd.card.bean.SearchHistory;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface SearchHistoryDao {
    void delete(SearchHistory... histories);

    void deleteAll(List<SearchHistory> histories);

    List<SearchHistory> getAllHistory();

    List<SearchHistory> getHistory();

    SearchHistory getHistoryByName(String name);

    void insert(SearchHistory... histories);

    void update(SearchHistory... histories);
}

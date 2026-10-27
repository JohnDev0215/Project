package com.byd.card.contract;

import com.byd.card.base.IModel;
import com.byd.card.base.IView;
import com.byd.card.bean.AppOrShortcutItem;
import com.byd.card.bean.SearchHistory;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface MoreAppContract {

    public interface Model extends IModel {
        void deleteAllHistory(View view);

        void deleteHistory(SearchHistory searchHistory, View view);

        void insertHistory(AppOrShortcutItem item);

        void insertHistory(String input);

        void queryRecentList(View view);

        void search(String inputTxt, View view);

        void searchHistory(View view);
    }

    public interface Presenter {
        void deleteAllHistory();

        void deleteHistory(SearchHistory searchHistory);

        void insertHistory(AppOrShortcutItem item);

        void insertHistory(String input);

        void queryRecentList();

        void search(String inputTxt);

        void searchHistory();
    }

    public interface View extends IView {
        void onDeleteAllHistory();

        void onDeleteHistory(List<SearchHistory> list, SearchHistory deleteHistory);

        void onQueryRecentResult(List<AppOrShortcutItem> list);

        void onSearchHistory(List<SearchHistory> list);

        void onSearchResult(List<AppOrShortcutItem> list, String inputText);
    }
}

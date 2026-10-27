package com.byd.card.presenter;

import com.byd.card.base.BasePresenter;
import com.byd.card.bean.AppOrShortcutItem;
import com.byd.card.bean.SearchHistory;
import com.byd.card.contract.MoreAppContract;
import com.byd.card.model.MoreAppModel;

/* JADX INFO: loaded from: classes.dex */
public class MoreAppPresenter extends BasePresenter<MoreAppContract.View, MoreAppContract.Model> implements MoreAppContract.Presenter {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.card.base.BasePresenter
    public MoreAppContract.Model createModule() {
        return new MoreAppModel();
    }

    @Override // com.byd.card.contract.MoreAppContract.Presenter
    public void queryRecentList() {
        ((MoreAppContract.Model) this.mModel).queryRecentList(getView());
    }

    @Override // com.byd.card.contract.MoreAppContract.Presenter
    public void search(String inputTxt) {
        ((MoreAppContract.Model) this.mModel).search(inputTxt, getView());
    }

    @Override // com.byd.card.contract.MoreAppContract.Presenter
    public void searchHistory() {
        ((MoreAppContract.Model) this.mModel).searchHistory(getView());
    }

    @Override // com.byd.card.contract.MoreAppContract.Presenter
    public void deleteHistory(SearchHistory searchHistory) {
        ((MoreAppContract.Model) this.mModel).deleteHistory(searchHistory, getView());
    }

    @Override // com.byd.card.contract.MoreAppContract.Presenter
    public void deleteAllHistory() {
        ((MoreAppContract.Model) this.mModel).deleteAllHistory(getView());
    }

    @Override // com.byd.card.contract.MoreAppContract.Presenter
    public void insertHistory(AppOrShortcutItem item) {
        ((MoreAppContract.Model) this.mModel).insertHistory(item);
    }

    @Override // com.byd.card.contract.MoreAppContract.Presenter
    public void insertHistory(String input) {
        ((MoreAppContract.Model) this.mModel).insertHistory(input);
    }
}

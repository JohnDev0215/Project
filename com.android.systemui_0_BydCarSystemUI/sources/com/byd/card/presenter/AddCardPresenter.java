package com.byd.card.presenter;

import com.byd.card.base.BasePresenter;
import com.byd.card.contract.AddCardContract;
import com.byd.card.model.AddCardModel;

/* JADX INFO: loaded from: classes.dex */
public class AddCardPresenter extends BasePresenter<AddCardContract.View, AddCardContract.Model> implements AddCardContract.Presenter {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.card.base.BasePresenter
    public AddCardContract.Model createModule() {
        return new AddCardModel();
    }

    @Override // com.byd.card.contract.AddCardContract.Presenter
    public void queryData() {
        ((AddCardContract.Model) this.mModel).queryData(getView());
    }
}

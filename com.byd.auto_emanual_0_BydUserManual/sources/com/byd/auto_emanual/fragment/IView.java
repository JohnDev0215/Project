package com.byd.auto_emanual.fragment;

/* JADX INFO: loaded from: classes.dex */
public interface IView {
    void loadComplete();

    void loadData();

    void showData(PageData pageData);

    void showData(PageData pageData, int loadTime);

    void showError();

    void showLoadingDialog();
}

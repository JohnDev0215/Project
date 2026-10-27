package com.byd.auto_emanual.fragment;

/* JADX INFO: loaded from: classes.dex */
public class Presenter {
    public static final int MSG_LOAD_DATA_COMPLETE = 3;
    public static final int MSG_LOAD_DATA_ERROR = 4;
    public static final int MSG_LOAD_DATA_START = 1;
    public static final int MSG_LOAD_DATA_SUCCESS = 2;
    public static final int PARAM_FROM_NET = 1;
    public static final int TIME_LOAD_START_DELAY = 500;
    public IModel model = new RecommendModel(new PresenterCallback());
    public IView view;

    public Presenter(IView view) {
        this.view = view;
    }

    public void loadData(String page) {
        this.model.loadData(page);
    }

    public void viewCount(String classify, String videoId) {
        this.model.viewCount(classify, videoId);
    }

    public class PresenterCallback {
        public PresenterCallback() {
        }

        void onLoadStart() {
            if (Presenter.this.view != null) {
                Presenter.this.view.showLoadingDialog();
            }
        }

        void onLoadSuccess(PageData pageData) {
            if (Presenter.this.view != null) {
                Presenter.this.view.showData(pageData);
            }
        }

        void onLoadSuccess(PageData pageData, int loadTime) {
            if (Presenter.this.view != null) {
                Presenter.this.view.showData(pageData, loadTime);
            }
        }

        void onLoadError() {
            if (Presenter.this.view != null) {
                Presenter.this.view.showError();
            }
        }

        void onLoadComplete() {
            if (Presenter.this.view != null) {
                Presenter.this.view.loadComplete();
            }
        }
    }
}

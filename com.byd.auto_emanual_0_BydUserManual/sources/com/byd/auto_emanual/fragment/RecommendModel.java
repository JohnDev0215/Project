package com.byd.auto_emanual.fragment;

import android.os.Handler;
import android.os.Message;
import com.byd.auto_emanual.ExecutorManager;
import com.byd.auto_emanual.JsonDataRunnable;
import com.byd.auto_emanual.ManualRunnable;
import com.byd.auto_emanual.ModelRunnable;
import com.byd.auto_emanual.RecommendModelRunnable;
import com.byd.auto_emanual.SearchHotRunnable;
import com.byd.auto_emanual.StartModelRunnable;
import com.byd.auto_emanual.ViewTimesRunnable;
import com.byd.auto_emanual.fragment.Presenter;
import com.byd.auto_emanual.util.HttpUtil;
import com.byd.auto_emanual.util.MLog;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class RecommendModel implements IModel {
    private static final String TAG = "RecommendModel";
    ModelCallback modelCallback;

    public RecommendModel(Presenter.PresenterCallback presenter) {
        this.modelCallback = new ModelCallback(presenter);
    }

    @Override // com.byd.auto_emanual.fragment.IModel
    public void loadData(String page) {
        String str;
        ModelRunnable jsonDataRunnable;
        MLog.v(TAG, "loadData " + page);
        if (Page.RECOMMEND.equals(page)) {
            str = HttpUtil.BASE_URL + "app/recommendPage";
            jsonDataRunnable = new RecommendModelRunnable(str, page, this.modelCallback);
        } else if (Page.START.equals(page)) {
            str = HttpUtil.BASE_URL + "app/newbie";
            jsonDataRunnable = new StartModelRunnable(str, page, this.modelCallback);
        } else if (Page.SEARCH.equals(page)) {
            str = HttpUtil.BASE_URL + "app/popularResource";
            jsonDataRunnable = new SearchHotRunnable(str, page, this.modelCallback);
        } else if (Page.MANUAL.equals(page)) {
            str = HttpUtil.BASE_URL + "app/checkUserhandBookVersion";
            jsonDataRunnable = new ManualRunnable(str, page, this.modelCallback);
        } else {
            if (!Page.JSON_DATA.equals(page)) {
                return;
            }
            str = HttpUtil.BASE_URL + "app/userhandBook";
            jsonDataRunnable = new JsonDataRunnable(str, page, this.modelCallback);
        }
        ExecutorManager.getInstance().execute(str, page, jsonDataRunnable);
    }

    @Override // com.byd.auto_emanual.fragment.IModel
    public void viewCount(String classify, String videoId) {
        MLog.v(TAG, "loadData " + classify);
        String str = HttpUtil.BASE_URL + "app/addBrowseVolume";
        ExecutorManager.getInstance().execute(str, classify, new ViewTimesRunnable(str, classify, this.modelCallback, videoId));
    }

    public static class ModelCallback extends Handler {
        private Presenter.PresenterCallback presenter;

        public ModelCallback(Presenter.PresenterCallback callback) {
            this.presenter = (Presenter.PresenterCallback) new WeakReference(callback).get();
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                this.presenter.onLoadStart();
                return;
            }
            if (i != 2) {
                if (i == 3) {
                    this.presenter.onLoadComplete();
                    return;
                } else if (i != 4) {
                    MLog.v(RecommendModel.TAG, "no this message!");
                    return;
                } else {
                    this.presenter.onLoadError();
                    return;
                }
            }
            PageData pageData = (PageData) msg.obj;
            if (msg.arg1 != 0) {
                if (Page.MANUAL.equals(pageData.getPage())) {
                    this.presenter.onLoadSuccess(pageData, msg.arg1);
                    return;
                } else {
                    if (Page.JSON_DATA.equals(pageData.getPage())) {
                        NormalDataAdapter.menuData = pageData.getMenuData();
                        NormalDataAdapter.netAddress = pageData.getNetAddress();
                        return;
                    }
                    return;
                }
            }
            this.presenter.onLoadSuccess(pageData);
            if (!Page.MANUAL.equals(pageData.getPage()) || pageData.getZipInfo() == null) {
                return;
            }
            NormalDataAdapter.zipUrl = pageData.getZipInfo().getZipUrl();
        }
    }
}

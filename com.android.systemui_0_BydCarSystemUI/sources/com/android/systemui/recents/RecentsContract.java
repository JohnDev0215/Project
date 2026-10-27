package com.android.systemui.recents;

import com.android.systemui.base.BaseContract;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface RecentsContract {

    public interface Presenter extends BaseContract.BasePresenter<View> {
    }

    public interface View extends BaseContract.BaseView {
        void refreshList(List<RecentTaskBean> list);
    }
}

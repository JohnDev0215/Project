package com.android.systemui.qs;

import com.android.systemui.base.BaseContract;
import com.android.systemui.qs.tileimpl.QSTile;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface QSEditContract {

    public interface Presenter extends BaseContract.BasePresenter<View> {
        String getSelectedId();

        String getSelectedName();

        int getSpanCount();

        int getSpanCountUnselect();

        String getUnselectedId();

        String getUnselectedName();
    }

    public interface View extends BaseContract.BaseView {
        int getCategoryLabelType();

        void refreshList(List<QSTile> selected, List<QSTile> unselected);

        void setTitle(String name);
    }
}

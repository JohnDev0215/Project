package com.byd.card.contract;

import com.byd.card.base.IModel;
import com.byd.card.base.IView;
import com.byd.card.bean.AppOrShortcutItem;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface AddCardContract {

    public interface Model extends IModel {
        void queryData(View view);
    }

    public interface Presenter {
        void queryData();
    }

    public interface View extends IView {
        void onUpdateView(List<AppOrShortcutItem> list);
    }
}

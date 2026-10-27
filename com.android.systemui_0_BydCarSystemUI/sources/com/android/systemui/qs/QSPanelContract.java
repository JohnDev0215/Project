package com.android.systemui.qs;

import com.android.systemui.base.BaseContract;
import com.android.systemui.qs.tileimpl.QSTile;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class QSPanelContract {

    interface Presenter extends BaseContract.BasePresenter<View> {
        int getMandatoryElectricState();

        int getSOCTarget();

        int getSocTargetLowLimit();

        int getSocTargetRange();

        int hasMessage(int id);

        void setMandatoryElectricState(int value);

        void setSOCTarget(int value);
    }

    interface View extends BaseContract.BaseView {
        String getCategoryLabelCar();

        String getCategoryLabelQs();

        String getCategoryLabelScene();

        void refreshCarSelectList(List<QSTile> selected, boolean fromEdit);

        void refreshCarUnselectedList(List<QSTile> selected, boolean fromEdit);

        void refreshQSList(List<QSTile> selected, boolean fromEdit);

        void refreshSceneModeList(List<QSTile> selected);

        void refreshSoc();

        void refreshVolumeAndBrightness();

        void updateMandatoryElectricStateChanged(int value);

        void updateSOCTargetLowLimitChanged(int value);

        void updateSocTargetChanged(int value);
    }
}

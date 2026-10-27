package com.android.systemui.statusbar;

import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class StatusListManager {
    public static final int CAMERA = 9;
    public static final int DVR = 1;
    public static final int MIC = 8;
    public static final int MORE = 10;
    public static final int OTA = 2;
    public static final int SDCARD = 3;
    public static final int SHOW_CAR = 0;
    public static final int USB = 4;
    public static final int WIRELESS_LEFT = 5;
    public static final int WIRELESS_RIGHT = 7;
    public static final int WIRELESS_SINGLE = 6;
    private List<StatusBean> mStatusBeanList = new CopyOnWriteArrayList();

    public StatusListManager() {
        BydLogHelper.logD("init");
        this.mStatusBeanList.add(new StatusBean(0));
        this.mStatusBeanList.add(new StatusBean(1));
        this.mStatusBeanList.add(new StatusBean(2));
        this.mStatusBeanList.add(new StatusBean(3));
        this.mStatusBeanList.add(new StatusBean(4));
        this.mStatusBeanList.add(new StatusBean(5));
        this.mStatusBeanList.add(new StatusBean(6));
        this.mStatusBeanList.add(new StatusBean(7));
        this.mStatusBeanList.add(new StatusBean(8));
        this.mStatusBeanList.add(new StatusBean(9));
        this.mStatusBeanList.add(new StatusBean(10));
    }

    public StatusBean getStatusBean(int id) {
        return this.mStatusBeanList.get(id);
    }

    public void setStatus(int id, int status) {
        StatusBean statusBean = this.mStatusBeanList.get(id);
        if (statusBean != null) {
            statusBean.setStatus(status);
        }
    }

    public void setVisibility(int id, boolean isVisibility) {
        StatusBean statusBean = this.mStatusBeanList.get(id);
        if (statusBean != null) {
            statusBean.setVisibility(isVisibility);
        }
    }

    public List<StatusBean> getList() {
        ArrayList arrayList = new ArrayList();
        for (StatusBean statusBean : this.mStatusBeanList) {
            if (statusBean.isVisibility()) {
                StatusBean statusBean2 = new StatusBean(statusBean.getId(), true);
                statusBean2.setStatus(statusBean.getStatus());
                arrayList.add(statusBean2);
            }
        }
        return arrayList;
    }
}

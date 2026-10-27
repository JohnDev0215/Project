package ts.car.iap.protocol.common;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class CarplayAppListUpdate {
    private int appListCount;
    private final ArrayList<CarPlayAppList> appLists = new ArrayList<>();
    private int mAppListAvailable;
    private boolean mAppListAvailableValid;
    private int mTotalListCount;
    private boolean mTotalListCountValid;

    public boolean isAppListAvailableValid() {
        return this.mAppListAvailableValid;
    }

    public void setAppListAvailable(int available) {
        this.mAppListAvailable = available;
        this.mAppListAvailableValid = true;
    }

    public int getAppListAvailable() {
        return this.mAppListAvailable;
    }

    public boolean isTotalListCountValid() {
        return this.mTotalListCountValid;
    }

    public void setTotalListCount(int count) {
        this.mTotalListCount = count;
        this.mTotalListCountValid = true;
    }

    public int getTotalListCount() {
        return this.mTotalListCount;
    }

    public void addCarPlayAppList(CarPlayAppList list) {
        this.appLists.add(list);
    }

    public ArrayList<CarPlayAppList> getCarPlayAppList() {
        return this.appLists;
    }

    public void setAppListCount(int count) {
        this.appListCount = count;
    }

    public int getAppListCount() {
        return this.appListCount;
    }
}

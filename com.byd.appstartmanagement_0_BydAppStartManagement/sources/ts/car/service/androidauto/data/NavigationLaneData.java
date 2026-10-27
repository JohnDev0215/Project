package ts.car.service.androidauto.data;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class NavigationLaneData {
    private List<NavigationDirectionData> mNavigationDirectionData;

    public List<NavigationDirectionData> getNavigationDirectionData() {
        return this.mNavigationDirectionData;
    }

    public void setNavigationDirectionData(List<NavigationDirectionData> list) {
        this.mNavigationDirectionData = list;
    }

    public String toString() {
        return "NavigationLaneData{mNavigationDirectionData=" + this.mNavigationDirectionData + '}';
    }
}

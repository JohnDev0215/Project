package ts.car.service.androidauto.data;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class NavigationStateData {
    private List<String> mDestinationData;
    private List<NavigationStepData> mStepData;

    public List<NavigationStepData> getStepData() {
        return this.mStepData;
    }

    public void setStepData(List<NavigationStepData> list) {
        this.mStepData = list;
    }

    public List<String> getNavigationDestinations() {
        return this.mDestinationData;
    }

    public void setNavigationDestinations(List<String> list) {
        this.mDestinationData = list;
    }

    public String toString() {
        return "NavigationStateData{mStepData=" + this.mStepData + ", mDestinationData=" + this.mDestinationData + '}';
    }
}

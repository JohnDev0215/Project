package ts.car.service.androidauto.data;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class NavigationStateData {
    private List<String> mDestinationData;
    private List<NavigationStepData> mStepData;

    public List<NavigationStepData> getStepData() {
        return this.mStepData;
    }

    public void setStepData(List<NavigationStepData> stepData) {
        this.mStepData = stepData;
    }

    public List<String> getNavigationDestinations() {
        return this.mDestinationData;
    }

    public void setNavigationDestinations(List<String> navigationDestinations) {
        this.mDestinationData = navigationDestinations;
    }

    public String toString() {
        return "NavigationStateData{mStepData=" + this.mStepData + ", mDestinationData=" + this.mDestinationData + '}';
    }
}

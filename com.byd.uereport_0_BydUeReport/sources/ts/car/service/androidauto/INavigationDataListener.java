package ts.car.service.androidauto;

import ts.car.service.androidauto.data.NavigationData;
import ts.car.service.androidauto.data.NavigationPositionData;
import ts.car.service.androidauto.data.NavigationStateData;

/* JADX INFO: loaded from: classes2.dex */
public interface INavigationDataListener {
    default void onNavigationCurrentPositionChanged(NavigationPositionData navigationPositionData) {
    }

    default void onNavigationStateChanged(NavigationStateData navigationStateData) {
    }

    default void onNavigationStatusChanged(int i8) {
    }

    @Deprecated
    default void onNextTurnChanged(NavigationData navigationData) {
    }

    @Deprecated
    default void onNextTurnDistanceChanged(int i8, int i9, int i10, int i11) {
    }
}

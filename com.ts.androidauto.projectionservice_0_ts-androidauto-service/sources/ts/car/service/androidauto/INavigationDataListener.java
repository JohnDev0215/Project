package ts.car.service.androidauto;

import ts.car.service.androidauto.data.NavigationData;
import ts.car.service.androidauto.data.NavigationPositionData;
import ts.car.service.androidauto.data.NavigationStateData;

/* JADX INFO: loaded from: classes2.dex */
public interface INavigationDataListener {
    default void onNavigationStatusChanged(int status) {
    }

    @Deprecated
    default void onNextTurnChanged(NavigationData navigationData) {
    }

    @Deprecated
    default void onNextTurnDistanceChanged(int distanceMeters, int timeSeconds, int displayDistanceE3, int displayDistanceUnit) {
    }

    default void onNavigationStateChanged(NavigationStateData navigationStateData) {
    }

    default void onNavigationCurrentPositionChanged(NavigationPositionData navigationPositionData) {
    }
}

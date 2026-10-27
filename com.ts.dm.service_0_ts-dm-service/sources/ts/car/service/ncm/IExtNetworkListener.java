package ts.car.service.ncm;

import ts.car.service.ncm.data.IfaceInfo;

/* JADX INFO: loaded from: classes.dex */
public interface IExtNetworkListener {
    default void onAvailabilityChanged(IfaceInfo info) {
    }

    default void onMoibleNetworkStateChanged(boolean enabled) {
    }
}

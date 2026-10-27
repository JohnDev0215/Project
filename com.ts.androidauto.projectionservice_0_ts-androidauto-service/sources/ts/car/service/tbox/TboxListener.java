package ts.car.service.tbox;

import ts.car.service.tbox.data.BaseStationInfo;
import ts.car.service.tbox.data.CallStateInfo;
import ts.car.service.tbox.data.LocationInfo;
import ts.car.service.tbox.data.MobileInfo;
import ts.car.service.tbox.data.SignalInfo;
import ts.car.service.tbox.data.TboxLogExportState;
import ts.car.service.tbox.data.TboxMessage;
import ts.car.service.tbox.data.TboxUpgradeInfo;
import ts.car.service.tbox.data.TboxVersion;

/* JADX INFO: loaded from: classes2.dex */
public interface TboxListener {
    default void onSimStateChanged(int status) {
    }

    default void onBaseStationInfo(BaseStationInfo info) {
    }

    default void onTboxConnectionStateChanged(int state) {
    }

    default void onNetworkRegStateChanged(int state) {
    }

    default void onLocationInfo(LocationInfo info) {
    }

    default void onTboxTetheringEnabled(boolean enabled) {
    }

    default void onReceiveTboxUpgradeInfo(TboxUpgradeInfo info) {
    }

    default void onTboxLogExportState(TboxLogExportState state) {
    }

    default void onAirplaneModeChanged(boolean enabled) {
    }

    default void onKeepTboxAlive(boolean enable) {
    }

    default void onTboxPowerModeChanged(int mode) {
    }

    default void onMobileInfoChanged(MobileInfo info) {
    }

    default void onCallStateInfoChanged(CallStateInfo callState) {
    }

    default void onVersionInfoChanged(TboxVersion versionInfo) {
    }

    default void onSignalInfoChanged(SignalInfo signalInfo) {
    }

    default void onRecvExtEvent(TboxMessage evtInfo) {
    }
}

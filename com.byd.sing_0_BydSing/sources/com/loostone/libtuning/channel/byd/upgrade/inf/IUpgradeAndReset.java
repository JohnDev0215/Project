package com.loostone.libtuning.channel.byd.upgrade.inf;

/* JADX INFO: loaded from: classes.dex */
public interface IUpgradeAndReset {

    public interface CheckNewVersionCallback {
        void onNewVersionAvailable(String str);

        void onUpToDate();
    }

    public interface DeviceInfoCallback {
        void onDongleInfoChange(String str);

        void onMicInfoChange(String str);
    }

    public interface ResetFactoryCallback {
        void onResetFactoryFailed(String str);

        void onResetFactorySuccess();
    }

    public interface UpgradeCallback {
        void onUpgradeFailed(String str, String str2);

        void onUpgradeProgress(int i);

        void onUpgradeSuccess();
    }

    public interface UpgradePreconditionCallback {
        void onMicConnectToVehicleStateChange(boolean z);

        void onMicMeetConditionChange(boolean z);
    }

    void checkDeviceInfo(DeviceInfoCallback deviceInfoCallback);

    void checkNewVersion(CheckNewVersionCallback checkNewVersionCallback);

    void checkUpgradePrecondition(UpgradePreconditionCallback upgradePreconditionCallback);

    String getSdkVersion();

    void resetFactory(ResetFactoryCallback resetFactoryCallback);

    void upgrade(UpgradeCallback upgradeCallback);
}

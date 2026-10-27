package com.byd.profile.mode;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import com.byd.profile.CarSettingsManager;
import com.byd.profile.ModeEvent;
import com.byd.profile.R;
import com.byd.profile.ToastManager;
import com.byd.profile.activity.RestModeActivity;
import com.byd.profile.dialog.AirStopDialog;
import com.byd.profile.dialog.BaseFullscreenDialog;
import com.byd.profile.dialog.NapModeStartDialog;
import com.byd.profile.request.RequestManager;
import com.byd.profile.request.RestRequestManager;
import com.byd.profile.utils.VoiceUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class RestMode extends Mode {
    private static final int HEAD_PERCENT = 80;
    private static final int MODE_START = 3;
    private static final int MODE_STOP = 1;
    private static final int REST_START = 1;
    private static final int REST_STOP = 2;
    private static final int SEAT_PERCENT = 80;
    private int headOrigin;
    private final AbsBYDAutoAcListener mAbsBYDAutoAcListener;
    private BYDAutoAcDevice mBYDAutoAcDevice;
    private BYDAutoSettingDevice mBYDAutoSettingDevice;
    private int seatOrigin;

    @Override // com.byd.profile.mode.Mode
    public void clear() {
    }

    @Override // com.byd.profile.mode.Mode
    public boolean isAvailable() {
        return true;
    }

    public RestMode(Context context) {
        super(context, 2);
        this.mAbsBYDAutoAcListener = new AbsBYDAutoAcListener() { // from class: com.byd.profile.mode.RestMode.2
            @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
            public void onAcStoped() {
                super.onAcStoped();
                BydLogHelper.logD("onAcStopped");
                new AirStopDialog(RestMode.this.mContext).show();
            }
        };
        this.mBYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(context);
        this.mBYDAutoAcDevice = BYDAutoAcDevice.getInstance(context);
    }

    @Override // com.byd.profile.mode.Mode
    protected RequestManager initRequestManager() {
        return new RestRequestManager();
    }

    @Override // com.byd.profile.mode.Mode
    public boolean showDialog() {
        NapModeStartDialog napModeStartDialog = new NapModeStartDialog(this.mContext);
        napModeStartDialog.show();
        napModeStartDialog.setOnDialogButtonClickListener(new BaseFullscreenDialog.OnDialogClickListener() { // from class: com.byd.profile.mode.RestMode.1
            @Override // com.byd.profile.dialog.BaseFullscreenDialog.OnDialogClickListener
            public void onConfirmClick() {
                RestMode.this.startModeReal();
            }
        });
        return true;
    }

    @Override // com.byd.profile.mode.Mode
    protected boolean isReady() {
        if (!CarSettingsManager.isP(this.mContext)) {
            ToastManager.show(this.mContext, R.string.available_in_P_gear);
            VoiceUtil.remainder(this.mContext, VoiceResIndex.NOT_P.index);
            return false;
        }
        if (!CarSettingsManager.isDoorsClosed(this.mContext, 1, 2, 3, 4, 6)) {
            ToastManager.show(this.mContext, R.string.please_close_the_door);
            VoiceUtil.remainder(this.mContext, VoiceResIndex.DOOR_NOT_CLOSE.index);
            return false;
        }
        if (!CarSettingsManager.isHasPassenger(this.mContext, 3)) {
            return true;
        }
        ToastManager.show(this.mContext, R.string.has_passenger_rear_left);
        VoiceUtil.remainder(this.mContext, VoiceResIndex.HAS_PASSENGER.index);
        return false;
    }

    @Override // com.byd.profile.mode.Mode
    public boolean toStarted() {
        BydLogHelper.logD("started");
        this.mBYDAutoSettingDevice.setIVIProfileSettings(3);
        this.mBYDAutoSettingDevice.setRestMode(1);
        ToastManager.showImage(this.mContext, R.drawable.icon_public_resticon_s_48, R.string.enter_rest);
        VoiceUtil.remainder(this.mContext, VoiceResIndex.START_SUCCESS.index);
        this.mRequestManager.setWanted(this.mContext);
        showRestScreen();
        this.mBYDAutoAcDevice.registerListener(this.mAbsBYDAutoAcListener);
        return true;
    }

    @Override // com.byd.profile.mode.Mode
    public boolean toStopped() {
        BydLogHelper.logD("stopped");
        EventBus.getDefault().post(new ModeEvent(this.modeId));
        this.mBYDAutoAcDevice.unregisterListener(this.mAbsBYDAutoAcListener);
        this.mBYDAutoSettingDevice.setIVIProfileSettings(1);
        this.mBYDAutoSettingDevice.setRestMode(2);
        ToastManager.show(this.mContext, R.string.exit_rest);
        VoiceUtil.remainder(this.mContext, VoiceResIndex.STOP_SUCCESS.index);
        this.mRequestManager.reset(this.mContext);
        return true;
    }

    private void showRestScreen() {
        BydLogHelper.logD("showRestScreen");
        Intent intent = new Intent(this.mContext, (Class<?>) RestModeActivity.class);
        intent.setFlags(268435456);
        this.mContext.startActivity(intent);
    }

    @Override // com.byd.profile.mode.Mode
    public boolean switchMode(Mode mode) {
        if (mode != null) {
            boolean z = mode instanceof WipeMode;
            BydLogHelper.logD("switchMode " + z);
            if (z) {
                BydLogHelper.logD("switchMode close wipe");
                ToastManager.show(this.mContext, R.string.close_wipe_first);
                return false;
            }
        }
        return super.switchMode(mode);
    }

    public enum VoiceResIndex {
        START_SUCCESS(75),
        START_FAILED(76),
        STARTED(77),
        NOT_SUPPORTED(78),
        NOT_P(79),
        DOOR_NOT_CLOSE(80),
        HAS_PASSENGER(81),
        CONFIRM_PASSENGER(82),
        STOP_SUCCESS(83),
        STOP_BUT_NOT_STARTED(85),
        STOP_OTHER(86),
        START_OTHER_CONFLICT(87);

        public int index;

        VoiceResIndex(int index) {
            this.index = index;
        }
    }
}

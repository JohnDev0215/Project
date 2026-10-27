package com.byd.profile.mode;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import com.byd.profile.CarSettingsManager;
import com.byd.profile.ModeEvent;
import com.byd.profile.R;
import com.byd.profile.ToastManager;
import com.byd.profile.activity.WipeActivity;
import com.byd.profile.request.RequestManager;
import com.byd.profile.utils.VoiceUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.File;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class WipeMode extends Mode {
    private static final String ENABLE_PATH = "etc/clean_disable.dat";
    private static final int MODE_OFF = 1;
    private static final int MODE_ON = 2;
    private BYDAutoSettingDevice mBYDAutoSettingDevice;

    @Override // com.byd.profile.mode.Mode
    public void clear() {
    }

    @Override // com.byd.profile.mode.Mode
    protected RequestManager initRequestManager() {
        return null;
    }

    @Override // com.byd.profile.mode.Mode
    public boolean showDialog() {
        return false;
    }

    public WipeMode(Context context) {
        super(context, 5);
        this.mBYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(context);
    }

    @Override // com.byd.profile.mode.Mode
    public boolean switchMode(Mode mode) {
        BydLogHelper.logD("switchMode");
        if (mode != null && mode.isStarted() && mode.modeId == 2) {
            ToastManager.show(this.mContext, R.string.close_rest_first);
            return false;
        }
        return startMode();
    }

    @Override // com.byd.profile.mode.Mode
    public boolean isAvailable() {
        if (!new File(ENABLE_PATH).exists()) {
            return true;
        }
        BydLogHelper.logD("disable file exists");
        return false;
    }

    @Override // com.byd.profile.mode.Mode
    protected boolean isReady() {
        if (CarSettingsManager.isP(this.mContext)) {
            return true;
        }
        ToastManager.show(this.mContext, R.string.available_in_P_gear);
        VoiceUtil.remainder(this.mContext, VoiceResIndex.NOT_P.index);
        return false;
    }

    @Override // com.byd.profile.mode.Mode
    public boolean toStarted() {
        ToastManager.show(this.mContext, R.string.enter_wipe);
        VoiceUtil.remainder(this.mContext, VoiceResIndex.START_SUCCESS.index);
        this.mBYDAutoSettingDevice.setWipeMode(2);
        Intent intent = new Intent(this.mContext, (Class<?>) WipeActivity.class);
        intent.setFlags(268435456);
        this.mContext.startActivity(intent);
        return true;
    }

    @Override // com.byd.profile.mode.Mode
    public boolean toStopped() {
        EventBus.getDefault().post(new ModeEvent(this.modeId));
        ToastManager.show(this.mContext, R.string.exit_wipe);
        VoiceUtil.remainder(this.mContext, VoiceResIndex.STOP_SUCCESS.index);
        this.mBYDAutoSettingDevice.setWipeMode(1);
        return true;
    }

    public enum VoiceResIndex {
        START_SUCCESS(88),
        START_FAILED(89),
        STARTED(90),
        NOT_SUPPORTED(84),
        NOT_P(79),
        STOP_SUCCESS(91),
        STOP_BUT_NOT_STARTED(92),
        STOP_OTHER(93),
        START_OTHER_CONFLICT(94);

        public int index;

        VoiceResIndex(int index) {
            this.index = index;
        }
    }
}

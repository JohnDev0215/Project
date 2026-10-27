package com.android.systemui.volume;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.media.AudioAttributes;
import android.media.AudioFocusInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.ContextThemeWrapper;
import android.widget.SeekBar;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.base.BaseDialog3;
import com.android.systemui.base.GestureDialogImpl;
import com.android.systemui.statusbar.datas.Icons;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.statusbar.policy.VolumeController;
import com.android.systemui.statusbar.wm.Util;
import com.android.systemui.util.StyleUtil;
import com.android.systemui.widget.VolumeUISeekBarContainer;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class VolumeDialogImpl implements VolumeDialog {
    private static boolean isRightRudder;
    private final AtomicBoolean isAdjusting = new AtomicBoolean(false);
    private final AbsBYDAutoSettingListener mAbsBYDAutoSettingListener;
    private BYDAutoSettingDevice mBYDAutoSettingDevice;
    private final Context mContext;
    private final VolumeController mController;
    private final VolumeController.Callbacks mControllerCallback;
    private final Handler mHandler;
    private VoiceDialog mVoiceDialog;
    protected final int what_dismiss;

    public VolumeDialogImpl(Context context) {
        AbsBYDAutoSettingListener absBYDAutoSettingListener = new AbsBYDAutoSettingListener() { // from class: com.android.systemui.volume.VolumeDialogImpl.1
            @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
            public void onRearviewMirrorIsStateChanged(int state) {
                super.onRearviewMirrorIsStateChanged(state);
                BydLogHelper.logD("isAdjusting state = " + state);
                VolumeDialogImpl.this.isAdjusting.set(state == 1);
            }
        };
        this.mAbsBYDAutoSettingListener = absBYDAutoSettingListener;
        this.mControllerCallback = new VolumeController.Callbacks() { // from class: com.android.systemui.volume.VolumeDialogImpl.3
            @Override // com.android.systemui.statusbar.policy.VolumeController.Callbacks
            public void onShowRequested(final VolumeController.State state, final boolean isFromMute, final int flags) {
                BydLogHelper.logD("onShowRequested");
                VolumeDialogImpl.this.mHandler.post(new Runnable() { // from class: com.android.systemui.volume.VolumeDialogImpl.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        boolean zIsShow = ((StatusBar) SysUiServiceProvider.getComponent(VolumeDialogImpl.this.mContext, StatusBar.class)).isShow();
                        BydLogHelper.logD("isShow = " + zIsShow);
                        if (zIsShow) {
                            return;
                        }
                        int backLightState = BYDAutoOtaDevice.getInstance(VolumeDialogImpl.this.mContext).getBackLightState();
                        BydLogHelper.logD("screen mode = " + backLightState);
                        BydLogHelper.logD("isAdjusting = " + VolumeDialogImpl.this.isAdjusting.get());
                        if (backLightState != 1 || VolumeDialogImpl.this.isAdjusting.get()) {
                            return;
                        }
                        VolumeDialogImpl.this.show();
                        VolumeDialogImpl.this.setVolumeBar(state, isFromMute, flags);
                    }
                });
            }

            @Override // com.android.systemui.statusbar.policy.VolumeController.Callbacks
            public void onStateChanged(boolean showUI, final VolumeController.State state, final boolean isFromMute, final int flags) {
                BydLogHelper.logD("showUI:" + showUI);
                if (showUI) {
                    return;
                }
                VolumeDialogImpl.this.mHandler.post(new Runnable() { // from class: com.android.systemui.volume.VolumeDialogImpl.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        VolumeDialogImpl.this.setVolumeBar(state, isFromMute, flags);
                    }
                });
            }
        };
        this.what_dismiss = 1;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, R.style.AppTheme);
        this.mContext = contextThemeWrapper;
        this.mController = (VolumeController) Dependency.get(VolumeController.class);
        this.mHandler = new H(Looper.getMainLooper());
        BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(contextThemeWrapper);
        this.mBYDAutoSettingDevice = bYDAutoSettingDevice;
        bYDAutoSettingDevice.registerListener(absBYDAutoSettingListener);
    }

    @Override // com.android.systemui.volume.VolumeDialog
    public void init() {
        BydLogHelper.logD("init");
        this.mController.addCallback(this.mControllerCallback, this.mHandler);
    }

    @Override // com.android.systemui.volume.VolumeDialog
    public void destroy() {
        BydLogHelper.logD("destroy");
        this.mController.removeCallback(this.mControllerCallback);
        this.mHandler.removeCallbacksAndMessages(null);
        BYDAutoSettingDevice bYDAutoSettingDevice = this.mBYDAutoSettingDevice;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.unregisterListener(this.mAbsBYDAutoSettingListener);
        }
    }

    private void initDialog() {
        VoiceDialog voiceDialog = new VoiceDialog(this.mContext);
        this.mVoiceDialog = voiceDialog;
        voiceDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.systemui.volume.VolumeDialogImpl.2
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialog) {
                VolumeDialogImpl.this.mHandler.removeMessages(1);
            }
        });
    }

    @Override // com.android.systemui.volume.VolumeDialog
    public void updateDialog() {
        BydLogHelper.logD("updateDialog");
        VoiceDialog voiceDialog = this.mVoiceDialog;
        if (voiceDialog != null) {
            voiceDialog.onCreate();
            VolumeController.State volumeState = ((VolumeController) Dependency.get(VolumeController.class)).getVolumeState();
            if (volumeState != null) {
                setVolumeBar(volumeState, false, 0);
            }
        }
        GestureDialogImpl.getInstance(this.mContext).updateDialogGesture();
    }

    @Override // com.android.systemui.volume.VolumeDialog
    public void dismiss() {
        BydLogHelper.logD("dismiss");
        VoiceDialog voiceDialog = this.mVoiceDialog;
        if (voiceDialog == null || !voiceDialog.isShowing()) {
            return;
        }
        this.mVoiceDialog.dismiss();
    }

    public void show() {
        BydLogHelper.logD("show");
        if (this.mVoiceDialog == null) {
            initDialog();
        }
        if (this.mVoiceDialog != null) {
            if (isRightRudder != AppUtils.isRightRudder()) {
                isRightRudder = AppUtils.isRightRudder();
                initDialog();
            }
            if (!this.mVoiceDialog.isShowing()) {
                this.mVoiceDialog.show();
            } else {
                this.mHandler.removeMessages(1);
                this.mHandler.sendEmptyMessageDelayed(1, 5000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVolumeBar(VolumeController.State state, boolean isFromMute, int flags) {
        if (this.mVoiceDialog == null) {
            BydLogHelper.logD("mVoiceDialog is null");
            return;
        }
        for (int i = 0; i < state.states.size(); i++) {
            int iKeyAt = state.states.keyAt(i);
            VolumeController.StreamState streamState = state.states.get(iKeyAt);
            BydLogHelper.logD("onStateChanged", "activeStream = " + state.activeStream);
            BydLogHelper.logD("onStateChanged", "state = " + iKeyAt + " , muted = " + streamState.muted + " , level = " + streamState.level + " , levelMin = " + streamState.levelMin + " , levelMax = " + streamState.levelMax);
            if (state.activeStream != iKeyAt) {
                BydLogHelper.logD("not need handle");
            } else if (!isFromMute || flags == 1) {
                if (iKeyAt == 3) {
                    if (streamState.muted || streamState.level == streamState.levelMin) {
                        VoiceDialog voiceDialog = this.mVoiceDialog;
                        voiceDialog.updateSeekBarProgressMute(voiceDialog.mSeekBarContainerMedia, streamState.levelMin);
                        return;
                    } else {
                        VoiceDialog voiceDialog2 = this.mVoiceDialog;
                        voiceDialog2.updateSeekBarProgress(voiceDialog2.mSeekBarContainerMedia, streamState.level);
                        return;
                    }
                }
                if (iKeyAt == 14) {
                    if (streamState.muted || streamState.level == streamState.levelMin) {
                        VoiceDialog voiceDialog3 = this.mVoiceDialog;
                        voiceDialog3.updateSeekBarProgressMute(voiceDialog3.mSeekBarContainerNavigation, streamState.levelMin);
                        return;
                    } else {
                        VoiceDialog voiceDialog4 = this.mVoiceDialog;
                        voiceDialog4.updateSeekBarProgress(voiceDialog4.mSeekBarContainerNavigation, streamState.level);
                        return;
                    }
                }
                if (iKeyAt == 16) {
                    VolumeUISeekBarContainer volumeUISeekBarContainer = this.mVoiceDialog.mSeekBarContainerVoice;
                    boolean zIsAndroidAutoNavi = isAndroidAutoNavi();
                    BydLogHelper.logD("isAndroidAutoNavi: " + zIsAndroidAutoNavi);
                    if (zIsAndroidAutoNavi) {
                        volumeUISeekBarContainer = this.mVoiceDialog.mSeekBarContainerNavigation;
                    }
                    if (streamState.muted || streamState.level == streamState.levelMin) {
                        this.mVoiceDialog.updateSeekBarProgressMute(volumeUISeekBarContainer, streamState.levelMin);
                        return;
                    } else {
                        this.mVoiceDialog.updateSeekBarProgress(volumeUISeekBarContainer, streamState.level);
                        return;
                    }
                }
                if (iKeyAt == 21) {
                    if (streamState.muted || streamState.level == streamState.levelMin) {
                        VoiceDialog voiceDialog5 = this.mVoiceDialog;
                        voiceDialog5.updateSeekBarProgressMute(voiceDialog5.mSeekBarContainerPhone, streamState.levelMin);
                        return;
                    } else {
                        VoiceDialog voiceDialog6 = this.mVoiceDialog;
                        voiceDialog6.updateSeekBarProgress(voiceDialog6.mSeekBarContainerPhone, streamState.level);
                        return;
                    }
                }
                if (iKeyAt == 25) {
                    if (streamState.muted || streamState.level == streamState.levelMin) {
                        VoiceDialog voiceDialog7 = this.mVoiceDialog;
                        voiceDialog7.updateSeekBarProgressMute(voiceDialog7.mSeekBarContainerBroadcast, streamState.levelMin);
                        return;
                    } else {
                        VoiceDialog voiceDialog8 = this.mVoiceDialog;
                        voiceDialog8.updateSeekBarProgress(voiceDialog8.mSeekBarContainerBroadcast, streamState.level);
                        return;
                    }
                }
                if (iKeyAt == 6) {
                    if (streamState.muted || streamState.level == streamState.levelMin) {
                        VoiceDialog voiceDialog9 = this.mVoiceDialog;
                        voiceDialog9.updateSeekBarProgressMute(voiceDialog9.mSeekBarContainerRingtone, streamState.levelMin);
                        return;
                    } else {
                        VoiceDialog voiceDialog10 = this.mVoiceDialog;
                        voiceDialog10.updateSeekBarProgress(voiceDialog10.mSeekBarContainerRingtone, streamState.level);
                        return;
                    }
                }
            } else {
                if (iKeyAt == 3) {
                    if (streamState.muted || streamState.level == streamState.levelMin) {
                        VoiceDialog voiceDialog11 = this.mVoiceDialog;
                        voiceDialog11.updateSeekBarProgressMuteFromMute(voiceDialog11.mSeekBarContainerMedia, streamState.levelMin);
                        return;
                    } else {
                        VoiceDialog voiceDialog12 = this.mVoiceDialog;
                        voiceDialog12.updateSeekBarProgressFromMute(voiceDialog12.mSeekBarContainerMedia, streamState.level);
                        return;
                    }
                }
                if (iKeyAt == 14) {
                    if (streamState.muted || streamState.level == streamState.levelMin) {
                        VoiceDialog voiceDialog13 = this.mVoiceDialog;
                        voiceDialog13.updateSeekBarProgressMuteFromMute(voiceDialog13.mSeekBarContainerNavigation, streamState.levelMin);
                        return;
                    } else {
                        VoiceDialog voiceDialog14 = this.mVoiceDialog;
                        voiceDialog14.updateSeekBarProgressFromMute(voiceDialog14.mSeekBarContainerNavigation, streamState.level);
                        return;
                    }
                }
                if (iKeyAt == 16) {
                    VolumeUISeekBarContainer volumeUISeekBarContainer2 = this.mVoiceDialog.mSeekBarContainerVoice;
                    boolean zIsAndroidAutoNavi2 = isAndroidAutoNavi();
                    BydLogHelper.logD("isAndroidAutoNavi: " + zIsAndroidAutoNavi2);
                    if (zIsAndroidAutoNavi2) {
                        volumeUISeekBarContainer2 = this.mVoiceDialog.mSeekBarContainerNavigation;
                    }
                    if (streamState.muted || streamState.level == streamState.levelMin) {
                        this.mVoiceDialog.updateSeekBarProgressMuteFromMute(volumeUISeekBarContainer2, streamState.levelMin);
                        return;
                    } else {
                        this.mVoiceDialog.updateSeekBarProgressFromMute(volumeUISeekBarContainer2, streamState.level);
                        return;
                    }
                }
                if (iKeyAt == 21) {
                    if (streamState.muted || streamState.level == streamState.levelMin) {
                        VoiceDialog voiceDialog15 = this.mVoiceDialog;
                        voiceDialog15.updateSeekBarProgressMuteFromMute(voiceDialog15.mSeekBarContainerPhone, streamState.levelMin);
                        return;
                    } else {
                        VoiceDialog voiceDialog16 = this.mVoiceDialog;
                        voiceDialog16.updateSeekBarProgressFromMute(voiceDialog16.mSeekBarContainerPhone, streamState.level);
                        return;
                    }
                }
                if (iKeyAt == 25) {
                    if (streamState.muted || streamState.level == streamState.levelMin) {
                        VoiceDialog voiceDialog17 = this.mVoiceDialog;
                        voiceDialog17.updateSeekBarProgressMuteFromMute(voiceDialog17.mSeekBarContainerBroadcast, streamState.levelMin);
                        return;
                    } else {
                        VoiceDialog voiceDialog18 = this.mVoiceDialog;
                        voiceDialog18.updateSeekBarProgressFromMute(voiceDialog18.mSeekBarContainerBroadcast, streamState.level);
                        return;
                    }
                }
                if (iKeyAt == 6) {
                    if (streamState.muted || streamState.level == streamState.levelMin) {
                        VoiceDialog voiceDialog19 = this.mVoiceDialog;
                        voiceDialog19.updateSeekBarProgressMuteFromMute(voiceDialog19.mSeekBarContainerRingtone, streamState.levelMin);
                        return;
                    } else {
                        VoiceDialog voiceDialog20 = this.mVoiceDialog;
                        voiceDialog20.updateSeekBarProgressFromMute(voiceDialog20.mSeekBarContainerRingtone, streamState.level);
                        return;
                    }
                }
            }
        }
    }

    private boolean isAndroidAutoNavi() {
        boolean z;
        AudioAttributes attributes;
        List<AudioFocusInfo> curAudioFocusInfos = ((VolumeController) Dependency.get(VolumeController.class)).getCurAudioFocusInfos();
        if (curAudioFocusInfos == null || curAudioFocusInfos.size() <= 0) {
            z = false;
        } else {
            for (AudioFocusInfo audioFocusInfo : curAudioFocusInfos) {
                if (audioFocusInfo != null && (attributes = audioFocusInfo.getAttributes()) != null) {
                    int usage = attributes.getUsage();
                    BydLogHelper.logD("getCurAudioFocusInfo  curUsage：" + usage);
                    if (usage == 35) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
        }
        if (z) {
            return !((CarController) Dependency.get(CarController.class)).isAaVrActive();
        }
        return false;
    }

    public final class VoiceDialog extends BaseDialog3 implements VolumeUISeekBarContainer.ProgressChangedCallback {
        public VolumeUISeekBarContainer mSeekBarContainerBroadcast;
        public VolumeUISeekBarContainer mSeekBarContainerMedia;
        public VolumeUISeekBarContainer mSeekBarContainerNavigation;
        public VolumeUISeekBarContainer mSeekBarContainerPhone;
        public VolumeUISeekBarContainer mSeekBarContainerRingtone;
        public VolumeUISeekBarContainer mSeekBarContainerVoice;

        @Override // com.android.systemui.base.BaseDialog3
        protected int getWindowType() {
            return 2020;
        }

        public VoiceDialog(Context context) {
            super(context);
            setCanceledOnTouchOutside(true);
        }

        private void setViewGone(VolumeUISeekBarContainer volumeUISeekBarContainer) {
            if (volumeUISeekBarContainer == null) {
                return;
            }
            VolumeUISeekBarContainer volumeUISeekBarContainer2 = this.mSeekBarContainerMedia;
            if (volumeUISeekBarContainer != volumeUISeekBarContainer2) {
                volumeUISeekBarContainer2.setVisibility(8);
            }
            VolumeUISeekBarContainer volumeUISeekBarContainer3 = this.mSeekBarContainerNavigation;
            if (volumeUISeekBarContainer != volumeUISeekBarContainer3) {
                volumeUISeekBarContainer3.setVisibility(8);
            }
            VolumeUISeekBarContainer volumeUISeekBarContainer4 = this.mSeekBarContainerVoice;
            if (volumeUISeekBarContainer != volumeUISeekBarContainer4) {
                volumeUISeekBarContainer4.setVisibility(8);
            }
            VolumeUISeekBarContainer volumeUISeekBarContainer5 = this.mSeekBarContainerPhone;
            if (volumeUISeekBarContainer != volumeUISeekBarContainer5) {
                volumeUISeekBarContainer5.setVisibility(8);
            }
            VolumeUISeekBarContainer volumeUISeekBarContainer6 = this.mSeekBarContainerBroadcast;
            if (volumeUISeekBarContainer != volumeUISeekBarContainer6) {
                volumeUISeekBarContainer6.setVisibility(8);
            }
            VolumeUISeekBarContainer volumeUISeekBarContainer7 = this.mSeekBarContainerRingtone;
            if (volumeUISeekBarContainer != volumeUISeekBarContainer7) {
                volumeUISeekBarContainer7.setVisibility(8);
            }
        }

        private void updateBydTheme() {
            this.mSeekBarContainerMedia.setBydThemeTv();
            this.mSeekBarContainerNavigation.setBydThemeTv();
            this.mSeekBarContainerVoice.setBydThemeTv();
            this.mSeekBarContainerPhone.setBydThemeTv();
            this.mSeekBarContainerBroadcast.setBydThemeTv();
            this.mSeekBarContainerRingtone.setBydThemeTv();
        }

        @Override // android.app.Dialog
        public void show() {
            super.show();
            updateBydTheme();
            VolumeDialogImpl.this.mHandler.removeMessages(1);
            VolumeDialogImpl.this.mHandler.sendEmptyMessageDelayed(1, 5000L);
        }

        @Override // com.android.systemui.base.BaseDialog3
        protected int getLayoutId() {
            return StyleUtil.getResLayout(R.layout.dialog_voice_control, "dialog_voice_control");
        }

        @Override // com.android.systemui.base.BaseDialog3
        protected void initSize() {
            int i;
            this.mWidth = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_122);
            this.mHeight = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_316);
            this.mGravity = 3;
            boolean unused = VolumeDialogImpl.isRightRudder = AppUtils.isRightRudder();
            int i2 = this.mContext.getResources().getConfiguration().orientation;
            BydLogHelper.logD("orientation:" + i2);
            if (i2 == 1) {
                i = R.dimen.dp_744;
                if (VolumeDialogImpl.isRightRudder) {
                    this.mLocationX = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_526);
                } else {
                    this.mLocationX = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_88);
                }
            } else {
                if (VolumeDialogImpl.isRightRudder) {
                    this.mLocationX = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_1086);
                } else {
                    this.mLocationX = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_88);
                }
                i = R.dimen.dp_228;
            }
            this.mLocationY = (this.mContext.getResources().getDimensionPixelSize(i) + (this.mHeight / 2)) - (Util.getWindowWidthAndHeight(this.mContext)[1] / 2);
        }

        @Override // com.android.systemui.base.BaseDialog3
        protected void initData() {
            BydLogHelper.logD("initData");
            this.mSeekBarContainerMedia = (VolumeUISeekBarContainer) findViewById(R.id.sysui_qs_seekbar_media);
            this.mSeekBarContainerNavigation = (VolumeUISeekBarContainer) findViewById(R.id.sysui_qs_seekbar_navigation);
            this.mSeekBarContainerVoice = (VolumeUISeekBarContainer) findViewById(R.id.sysui_qs_seekbar_voice);
            this.mSeekBarContainerPhone = (VolumeUISeekBarContainer) findViewById(R.id.sysui_qs_seekbar_phone);
            this.mSeekBarContainerBroadcast = (VolumeUISeekBarContainer) findViewById(R.id.sysui_qs_seekbar_broadcast);
            this.mSeekBarContainerRingtone = (VolumeUISeekBarContainer) findViewById(R.id.sysui_qs_seekbar_ringtone);
            this.mSeekBarContainerMedia.initSeekBar(0, 39, R.string.media, Icons.MUSIC_VOL_UI_ICONS, 1);
            this.mSeekBarContainerNavigation.initSeekBar(0, 10, R.string.navigation, Icons.NAVI_VOL_UI_ICONS, 12);
            this.mSeekBarContainerVoice.initSeekBar(5, 24, R.string.voice, Icons.VOICE_VOL_UI_ICONS, 16);
            this.mSeekBarContainerPhone.initSeekBar(5, 39, R.string.phone, Icons.PHONE_VOL_UI_ICONS, 2);
            this.mSeekBarContainerBroadcast.initSeekBar(5, 39, R.string.broadcast, Icons.BROADCAST_VOL_UI_ICONS, 18);
            this.mSeekBarContainerRingtone.initSeekBar(0, 39, R.string.ringtone, Icons.RINGTONE_VOL_UI_ICONS, 6);
            this.mSeekBarContainerMedia.setProgressCallback(this);
            this.mSeekBarContainerNavigation.setProgressCallback(this);
            this.mSeekBarContainerVoice.setProgressCallback(this);
            this.mSeekBarContainerPhone.setProgressCallback(this);
            this.mSeekBarContainerBroadcast.setProgressCallback(this);
            this.mSeekBarContainerRingtone.setProgressCallback(this);
        }

        @Override // android.app.Dialog
        protected void onStart() {
            super.onStart();
        }

        @Override // com.android.systemui.widget.VolumeUISeekBarContainer.ProgressChangedCallback
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser, int usage) {
            boolean zIsVolumeGroupMuted = VolumeDialogImpl.this.mController.isVolumeGroupMuted(0, VolumeDialogImpl.this.mController.getVolumeGroupIdForUsage(usage));
            if (zIsVolumeGroupMuted) {
                VolumeDialogImpl.this.mController.setVolumeGroupMute(0, VolumeDialogImpl.this.mController.getVolumeGroupIdForUsage(usage), !zIsVolumeGroupMuted, 0);
            }
            BydLogHelper.logD("onProgressChanged", "progress : " + progress + " ,usage : " + usage);
            VolumeDialogImpl.this.mController.setGroupVolume(VolumeDialogImpl.this.mController.getVolumeGroupIdForUsage(usage), progress, 0);
        }

        @Override // com.android.systemui.widget.VolumeUISeekBarContainer.ProgressChangedCallback
        public void onStartTrackingTouch(SeekBar seekBar) {
            VolumeDialogImpl.this.mHandler.removeMessages(1);
        }

        @Override // com.android.systemui.widget.VolumeUISeekBarContainer.ProgressChangedCallback
        public void onStopTrackingTouch(SeekBar seekBar) {
            VolumeDialogImpl.this.mHandler.sendEmptyMessageDelayed(1, 5000L);
        }

        @Override // com.android.systemui.widget.VolumeUISeekBarContainer.ProgressChangedCallback
        public void onIconClick(int usage) {
            handleIconClick(usage);
        }

        public void handleIconClick(int usage) {
            VolumeDialogImpl.this.mController.setVolumeGroupMute(0, VolumeDialogImpl.this.mController.getVolumeGroupIdForUsage(usage), !VolumeDialogImpl.this.mController.isVolumeGroupMuted(0, VolumeDialogImpl.this.mController.getVolumeGroupIdForUsage(usage)), 0);
            VolumeDialogImpl.this.mHandler.removeMessages(1);
            VolumeDialogImpl.this.mHandler.sendEmptyMessageDelayed(1, 5000L);
        }

        public void updateSeekBarProgress(VolumeUISeekBarContainer volumeUISeekBarContainer, int progress) {
            setViewGone(volumeUISeekBarContainer);
            volumeUISeekBarContainer.setVisibility(0);
            volumeUISeekBarContainer.setSeekBarProgress(progress);
        }

        public void updateSeekBarProgressFromMute(VolumeUISeekBarContainer volumeUISeekBarContainer, int progress) {
            if (!hasBarVisible()) {
                volumeUISeekBarContainer.setVisibility(0);
            }
            volumeUISeekBarContainer.setSeekBarProgress(progress);
        }

        public void updateSeekBarProgressMute(VolumeUISeekBarContainer volumeUISeekBarContainer, int levelMin) {
            setViewGone(volumeUISeekBarContainer);
            volumeUISeekBarContainer.setVisibility(0);
            volumeUISeekBarContainer.setSeekBarProgressMute(levelMin);
        }

        public void updateSeekBarProgressMuteFromMute(VolumeUISeekBarContainer volumeUISeekBarContainer, int levelMin) {
            if (!hasBarVisible()) {
                volumeUISeekBarContainer.setVisibility(0);
            }
            volumeUISeekBarContainer.setSeekBarProgressMute(levelMin);
        }

        private boolean hasBarVisible() {
            return this.mSeekBarContainerMedia.getVisibility() == 0 || this.mSeekBarContainerNavigation.getVisibility() == 0 || this.mSeekBarContainerVoice.getVisibility() == 0 || this.mSeekBarContainerBroadcast.getVisibility() == 0 || this.mSeekBarContainerRingtone.getVisibility() == 0 || this.mSeekBarContainerPhone.getVisibility() == 0;
        }
    }

    private class H extends Handler {
        public H(Looper mainLooper) {
            super(mainLooper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            BydLogHelper.logD("what:" + i);
            if (i == 1 && VolumeDialogImpl.this.mVoiceDialog != null) {
                VolumeDialogImpl.this.mVoiceDialog.dismiss();
            }
        }
    }
}

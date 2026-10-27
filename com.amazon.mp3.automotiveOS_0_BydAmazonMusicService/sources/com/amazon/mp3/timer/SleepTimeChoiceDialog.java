package com.amazon.mp3.timer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v4.media.session.MediaControllerCompat;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class SleepTimeChoiceDialog extends AlertDialog {
    private static final int[] SLEEP_TIME_INTERVALS_MINUTES = {15, 30, 45, 60};
    private final Activity mActivity;
    private boolean mIsTimerSet;
    private int mMinutesToSleep;

    public SleepTimeChoiceDialog(Activity activity) {
        super(activity);
        this.mMinutesToSleep = SLEEP_TIME_INTERVALS_MINUTES[0];
        this.mActivity = activity;
    }

    @Override // android.app.Dialog
    public void show() {
        String[] strArr = new String[SLEEP_TIME_INTERVALS_MINUTES.length];
        int i = 0;
        while (true) {
            int[] iArr = SLEEP_TIME_INTERVALS_MINUTES;
            if (i < iArr.length) {
                Resources resources = this.mActivity.getResources();
                int i2 = iArr[i];
                strArr[i] = resources.getQuantityString(R.plurals.dmusic_sleep_timer_minutes, i2, Integer.valueOf(i2));
                i++;
            } else {
                AlertDialog.Builder title = new AlertDialog.Builder(this.mActivity).setSingleChoiceItems(strArr, 0, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.timer.SleepTimeChoiceDialog.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        SleepTimeChoiceDialog.this.mMinutesToSleep = SleepTimeChoiceDialog.SLEEP_TIME_INTERVALS_MINUTES[which];
                    }
                }).setTitle(R.string.dmusic_sleep_timer_title);
                title.setNegativeButton(R.string.dmusic_button_cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.timer.SleepTimeChoiceDialog.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        SleepTimeChoiceDialog.this.dismiss();
                    }
                });
                title.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.mp3.timer.SleepTimeChoiceDialog.3
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(final DialogInterface dialogInterface) {
                        if (SleepTimeChoiceDialog.this.mIsTimerSet) {
                            return;
                        }
                        SettingsUtil.setSleepTimerEnabled(SleepTimeChoiceDialog.this.mActivity, false);
                    }
                });
                title.setPositiveButton(R.string.dmusic_button_done, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.timer.SleepTimeChoiceDialog.4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        SleepTimeChoiceDialog.this.mIsTimerSet = true;
                        SleepTimeChoiceDialog.startTimer(SleepTimeChoiceDialog.this.mActivity, SleepTimeChoiceDialog.this.mMinutesToSleep);
                        BauhausToastUtils.showTextToast(SleepTimeChoiceDialog.this.mActivity, R.string.dmusic_sleep_timer_toast, 1);
                    }
                });
                title.create().show();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startTimer(Context context, int minutesToSleep) {
        final Context applicationContext = context.getApplicationContext();
        SleepTimerService.setAndStartTimer(applicationContext, (int) TimeUnit.MINUTES.toSeconds(minutesToSleep), new SleepTimerCallback() { // from class: com.amazon.mp3.timer.SleepTimeChoiceDialog.5
            @Override // com.amazon.mp3.timer.SleepTimerCallback
            protected void onFinished() {
                super.onFinished();
                resetTimer();
                new MediaControllerCompat(applicationContext, Playback.getInstance().getMediaSession()).getTransportControls().pause();
                CastingSessionManager.getInstance().disconnect(TerminationReason.SLEEP_TIMER_INACTIVITY);
            }

            @Override // com.amazon.mp3.timer.SleepTimerCallback
            protected void onCanceled() {
                super.onCanceled();
                resetTimer();
            }

            @Override // com.amazon.mp3.timer.SleepTimerCallback
            protected void onTick(long seconds) {
                super.onTick(seconds);
            }

            private void resetTimer() {
                SettingsUtil.setSleepTimerEnabled(applicationContext, false);
            }
        });
    }
}

package com.byd.profile.dialog;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.byd.profile.R;
import com.byd.profile.view.TimeView;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.GregorianCalendar;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmDialog extends DialogFragment {
    private static final String ACTION_ALARM = "action_alarm";
    private static final long DELAY = 20000;
    private static final int WHAT_STOP = 1;
    private boolean isStopping;
    private AlarmManager mAlarmManager;
    private AudioManager mAudioManager;
    private IButtonClickListener mButtonClickListener;
    private MediaPlayer mMediaPlayer;
    private PendingIntent mPendingIntent;
    private TextView mTvNoon;
    private TimeView mTvTime;
    private Handler mHandler = new Handler() { // from class: com.byd.profile.dialog.AlarmDialog.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (AlarmDialog.this.mButtonClickListener != null) {
                AlarmDialog.this.mButtonClickListener.onClockDelay();
            }
            if (!AlarmDialog.this.isResumed()) {
                AlarmDialog.this.isStopping = true;
            } else {
                AlarmDialog.this.dismissAllowingStateLoss();
            }
        }
    };
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.byd.profile.dialog.AlarmDialog.4
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int focusChange) {
            BydLogHelper.logD("onAudioFocusChange " + focusChange);
        }
    };

    public interface IButtonClickListener {
        void onClockClosed();

        void onClockDelay();
    }

    public void setButtonClickListener(IButtonClickListener buttonClickListener) {
        this.mButtonClickListener = buttonClickListener;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.profile_dialog_style);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setWindowAnimations(R.style.profile_dialog_anim_style);
        return dialog;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.dialog_alarm, container, true);
        initView(viewInflate);
        return viewInflate;
    }

    private void initView(View view) {
        this.mTvTime = (TimeView) view.findViewById(R.id.tv_time);
        this.mTvNoon = (TextView) view.findViewById(R.id.tv_noon);
        ((Button) view.findViewById(R.id.btn_close)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.profile.dialog.AlarmDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (AlarmDialog.this.mButtonClickListener != null) {
                    AlarmDialog.this.mButtonClickListener.onClockClosed();
                }
                AlarmDialog.this.dismiss();
            }
        });
        ((Button) view.findViewById(R.id.btn_delay)).setOnClickListener(new View.OnClickListener() { // from class: com.byd.profile.dialog.AlarmDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (AlarmDialog.this.mButtonClickListener != null) {
                    AlarmDialog.this.mButtonClickListener.onClockDelay();
                }
                AlarmDialog.this.dismiss();
            }
        });
        playRing(requireContext());
        this.mHandler.sendEmptyMessageDelayed(1, DELAY);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        int[] iArr = new int[2];
        setWidthAndHeight(iArr);
        getDialog().getWindow().setLayout(iArr[0], iArr[1]);
        if (this.isStopping) {
            dismiss();
        }
        if (is12Hour(requireContext())) {
            this.mTvNoon.setVisibility(0);
            this.mTvTime.set12Hour(true);
            this.mTvNoon.setText(new GregorianCalendar().get(9) == 0 ? R.string.am : R.string.pm);
            return;
        }
        this.mTvNoon.setVisibility(8);
        this.mTvTime.set12Hour(false);
    }

    private void setWidthAndHeight(int[] size) {
        size[0] = getResources().getDimensionPixelSize(R.dimen.dp_560);
        size[1] = getResources().getDimensionPixelSize(R.dimen.dp_428);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mHandler.removeMessages(1);
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            audioManager.abandonAudioFocus(this.mAudioFocusChangeListener);
        }
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlayer.release();
        }
        super.onDestroyView();
    }

    private void playRing(Context context) {
        try {
            this.mAudioManager = (AudioManager) requireContext().getSystemService("audio");
            this.mAudioManager.requestAudioFocus(new AudioFocusRequest.Builder(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).build()).setAcceptsDelayedFocusGain(true).setOnAudioFocusChangeListener(this.mAudioFocusChangeListener).build());
            MediaPlayer mediaPlayerCreate = MediaPlayer.create(context, R.raw.alarm);
            this.mMediaPlayer = mediaPlayerCreate;
            mediaPlayerCreate.start();
        } catch (Throwable th) {
            BydLogHelper.logD("play error");
            th.printStackTrace();
        }
    }

    private boolean is12Hour(Context context) {
        return TimeManagerImpl.TIME_FORMAT_TWELVE.equals(Settings.System.getString(context.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24));
    }
}

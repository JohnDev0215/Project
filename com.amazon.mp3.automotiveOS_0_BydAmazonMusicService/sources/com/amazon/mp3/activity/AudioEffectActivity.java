package com.amazon.mp3.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.Toolbar;
import androidx.legacy.widget.Space;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.service.AudioEffectController;
import com.amazon.mp3.playback.service.audioeffects.EqualizerMetrics;
import com.amazon.mp3.playback.service.volume.VolumeController;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.view.Knob;
import com.amazon.mp3.view.SlidingTabBar;
import com.amazon.mp3.view.VerticalSeekBar;
import com.amazon.music.media.playback.Playback;
import java.util.ArrayList;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class AudioEffectActivity extends BaseActivity implements AudioEffectController.StateListener {
    private static final String TAG = "AudioEffectActivity";
    private AudioEffectController mAudioEffectController;
    private Knob mBassBoost;
    private EqualizerActionBarProvider mEqualizerActionBarProvider;
    private int mEqualizerNumBands;
    private AppCompatCheckBox mEqulizerToggle;
    private SlidingTabBar mSlidingTabBar;
    private Toolbar mToolbar;
    private float mTouchX1;
    private float mTouchX2;
    private Knob mTreble;
    private Knob mVolume;
    private VolumeChangeReceiver mVolumeChangeReceiver;
    private VolumeController mVolumeController;
    private final int MIN_SWIPE_DISTANCE = 100;
    private final int HZ_TO_KHZ_FACTOR = 1000;
    private final int DEFAULT_MAX_BAND_LEVEL_PERCENTAGE = 100;
    private final int UPDATE_BAND_DELAY_MILLISECONDS = 150;
    private List<VerticalSeekBar> mEqBands = new ArrayList();
    private Handler mMainHandler = new Handler();
    private final SlidingTabBar.SlidingTabBarChangeListener mSlidingTabBarChangeListener = new SlidingTabBar.SlidingTabBarChangeListener() { // from class: com.amazon.mp3.activity.AudioEffectActivity.4
        @Override // com.amazon.mp3.view.SlidingTabBar.SlidingTabBarChangeListener
        public void onTabSelected(final int tabNumber) {
            AudioEffectActivity.this.mAudioEffectController.usePreset(tabNumber);
            EqualizerMetrics.INSTANCE.emitUiClickPreset(AudioEffectActivity.this.mAudioEffectController.getPresets()[tabNumber]);
            if (AudioEffectActivity.this.mMainHandler != null) {
                AudioEffectActivity.this.mMainHandler.postDelayed(AudioEffectActivity.this.updateEqBandRunnable, 150L);
            }
        }
    };
    private final Runnable updateEqBandRunnable = new Runnable() { // from class: com.amazon.mp3.activity.AudioEffectActivity.5
        @Override // java.lang.Runnable
        public void run() {
            for (int i = 0; i < AudioEffectActivity.this.mEqBands.size(); i++) {
                int bandLevel = AudioEffectActivity.this.mAudioEffectController.getBandLevel((short) i);
                ((VerticalSeekBar) AudioEffectActivity.this.mEqBands.get(i)).setOnSeekBarChangeListener(null);
                ((VerticalSeekBar) AudioEffectActivity.this.mEqBands.get(i)).setProgress(bandLevel);
                ((VerticalSeekBar) AudioEffectActivity.this.mEqBands.get(i)).setOnSeekBarChangeListener(AudioEffectActivity.this.mOnBandChangeListener);
            }
        }
    };
    private final Knob.KnobProgressChangeListener mKnobTrebleChangeListener = new Knob.KnobProgressChangeListener() { // from class: com.amazon.mp3.activity.AudioEffectActivity.6
        @Override // com.amazon.mp3.view.Knob.KnobProgressChangeListener
        public void onProgressChanged(final Knob knob, final int level) {
            AudioEffectActivity.this.mAudioEffectController.setTrebleStrength(level);
        }

        @Override // com.amazon.mp3.view.Knob.KnobProgressChangeListener
        public void onPressWhileDisabled() {
            AudioEffectActivity.this.showKnobDisabledMessage();
        }
    };
    private final Knob.KnobProgressChangeListener mKnobBassChangeListener = new Knob.KnobProgressChangeListener() { // from class: com.amazon.mp3.activity.AudioEffectActivity.7
        @Override // com.amazon.mp3.view.Knob.KnobProgressChangeListener
        public void onProgressChanged(final Knob knob, final int level) {
            AudioEffectActivity.this.mAudioEffectController.setBassBoostStrength(level);
        }

        @Override // com.amazon.mp3.view.Knob.KnobProgressChangeListener
        public void onPressWhileDisabled() {
            AudioEffectActivity.this.showKnobDisabledMessage();
        }
    };
    private final Knob.KnobProgressChangeListener mKnobVolumeChangeListener = new Knob.KnobProgressChangeListener() { // from class: com.amazon.mp3.activity.AudioEffectActivity.8
        @Override // com.amazon.mp3.view.Knob.KnobProgressChangeListener
        public void onProgressChanged(final Knob knob, final int level) {
            AudioEffectActivity.this.mVolumeController.setVolume(level);
            if (AudioEffectActivity.this.mVolumeController.getCurrentVolume() != level) {
                knob.setProgress(AudioEffectActivity.this.mVolumeController.getCurrentVolume());
            }
        }

        @Override // com.amazon.mp3.view.Knob.KnobProgressChangeListener
        public void onPressWhileDisabled() {
            AudioEffectActivity.this.showKnobDisabledMessage();
        }
    };
    private final SeekBar.OnSeekBarChangeListener mOnBandChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.amazon.mp3.activity.AudioEffectActivity.9
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int level, boolean fromTouch) {
            int presetNum = AudioEffectActivity.this.mAudioEffectController.getPresetNum();
            short s = 0;
            while (true) {
                if (s >= AudioEffectActivity.this.mEqualizerNumBands) {
                    break;
                }
                if (seekBar == AudioEffectActivity.this.mEqBands.get(s)) {
                    AudioEffectActivity.this.mAudioEffectController.setEqBandLevel(s, (short) level);
                    break;
                }
                s = (short) (s + 1);
            }
            int presetNum2 = AudioEffectActivity.this.mAudioEffectController.getPresetNum();
            if (presetNum != presetNum2) {
                AudioEffectActivity.this.mSlidingTabBar.setSlidingTabBarChangeListener(null);
                AudioEffectActivity.this.mSlidingTabBar.selectTab(presetNum2);
                AudioEffectActivity.this.mSlidingTabBar.setSlidingTabBarChangeListener(AudioEffectActivity.this.mSlidingTabBarChangeListener);
                EqualizerMetrics.INSTANCE.emitUiClickPreset(AudioEffectActivity.this.mAudioEffectController.getPresets()[presetNum2]);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            for (short s = 0; s < AudioEffectActivity.this.mEqualizerNumBands; s = (short) (s + 1)) {
                if (seekBar == AudioEffectActivity.this.mEqBands.get(s)) {
                    EqualizerMetrics.INSTANCE.emitUiClickEQAdjust(s);
                    return;
                }
            }
        }
    };

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EqualizerMetrics.INSTANCE.emitEqualizerUIPageView();
        AudioEffectController.getInstance(this).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<AudioEffectController>() { // from class: com.amazon.mp3.activity.AudioEffectActivity.1
            @Override // rx.functions.Action1
            public void call(final AudioEffectController audioEffectController) {
                if (AudioEffectActivity.this.isFinishing() || AudioEffectActivity.this.isDestroyed()) {
                    return;
                }
                AudioEffectActivity.this.mVolumeController = VolumeController.get();
                AudioEffectActivity.this.mAudioEffectController = audioEffectController;
                AudioEffectActivity.this.setContentView(R.layout.equalizer);
                AudioEffectActivity.this.setupActionBar();
                AudioEffectActivity.this.setupPresetTabs();
                AudioEffectActivity.this.connectKnobs();
                AudioEffectActivity.this.setDynamicMargins();
                AudioEffectActivity.this.addEqualizerBands();
                AudioEffectActivity.this.addDecibelLabels();
                AudioEffectActivity.this.initEqualizerUI();
                if (Playback.getInstance().getPlaybackConfig().getLocalPlayer(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(AudioEffectActivity.this.getApplicationContext())).getAudioEffectsController().getEqualizerBandControllerList().size() == 0) {
                    throw new IllegalStateException("Harley player equalizer component has not fully initialized yet ");
                }
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.activity.AudioEffectActivity.2
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.error(AudioEffectActivity.TAG, "AudioEffectController was unable to retrieve instance of AudioEffectController ", throwable);
                if (AudioEffectActivity.this.isFinishing() || AudioEffectActivity.this.isDestroyed()) {
                    return;
                }
                BauhausToastUtils.showDefaultToast(AudioEffectActivity.this, AudioEffectActivity.this.getString(R.string.equalizer_failed_to_open_message), 1);
                AudioEffectActivity.this.finish();
            }
        });
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            this.mTouchX1 = event.getX();
        } else if (action == 1) {
            float x = event.getX();
            this.mTouchX2 = x;
            if (Math.abs(x - this.mTouchX1) > 100.0f) {
                if (this.mTouchX2 > this.mTouchX1) {
                    SlidingTabBar slidingTabBar = this.mSlidingTabBar;
                    if (slidingTabBar != null) {
                        slidingTabBar.selectPrevTab();
                        return true;
                    }
                } else {
                    SlidingTabBar slidingTabBar2 = this.mSlidingTabBar;
                    if (slidingTabBar2 != null) {
                        slidingTabBar2.selectNextTab();
                        return true;
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        updateKnobProgress();
        return super.onKeyDown(keyCode, event);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    private class EqualizerActionBarProvider implements ActionBarProvider {
        private final ActionBar mActionBar;

        public EqualizerActionBarProvider() {
            ActionBar supportActionBar = AudioEffectActivity.this.getSupportActionBar();
            this.mActionBar = supportActionBar;
            if (supportActionBar != null) {
                supportActionBar.setTitle(AudioEffectActivity.this.getString(R.string.equalizer_action_bar_title));
                supportActionBar.setDisplayShowHomeEnabled(true);
            }
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void setHeaderView(final View headerView) {
            ActionBar actionBar = this.mActionBar;
            if (actionBar != null) {
                actionBar.setDisplayShowCustomEnabled(true);
                this.mActionBar.setCustomView(headerView);
            }
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void removeHeaderView() {
            ActionBar actionBar = this.mActionBar;
            if (actionBar != null) {
                actionBar.setDisplayShowCustomEnabled(false);
            }
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void requestHomeButtonAsBack() {
            ActionBar actionBar = this.mActionBar;
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void requestHomeButtonAsStoreClose() {
            ActionBar actionBar = this.mActionBar;
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void restoreHomeButton() {
            ActionBar actionBar = this.mActionBar;
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void setHomeButtonVisibility(final int visible) {
            ActionBar actionBar = this.mActionBar;
            if (actionBar != null) {
                actionBar.setDisplayShowHomeEnabled(visible == 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initEqualizerUI() {
        this.mEqulizerToggle = (AppCompatCheckBox) findViewById(R.id.equalizer_checkbox);
        boolean z = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("setting_pref_key_equalizer_enable", false);
        this.mEqulizerToggle.setChecked(z);
        if (this.mAudioEffectController != null) {
            initializeAlexa();
            registerReceivers();
            toggleEqualizerUI(z);
            this.mEqulizerToggle.setVisibility(0);
            this.mEqulizerToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.amazon.mp3.activity.AudioEffectActivity.3
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                    PreferenceManager.getDefaultSharedPreferences(AudioEffectActivity.this).edit().putBoolean("setting_pref_key_equalizer_enable", isChecked).apply();
                    AudioEffectActivity.this.toggleEqualizerUI(isChecked);
                    AudioEffectActivity.this.mAudioEffectController.toggleEqualizerSetting(isChecked);
                    if (isChecked) {
                        EqualizerMetrics.INSTANCE.emitUiClickEQToggleOn();
                    } else {
                        EqualizerMetrics.INSTANCE.emitUiClickEQToggleOff();
                    }
                }
            });
            return;
        }
        this.mEqulizerToggle.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showKnobDisabledMessage() {
        String string;
        AudioEffectController audioEffectController = this.mAudioEffectController;
        if (audioEffectController != null && audioEffectController.isAudioEffectControllerInErrorState()) {
            string = getString(R.string.equalizer_cannot_apply_effects);
        } else {
            string = getString(R.string.equalizer_use_headset_message);
        }
        BauhausToastUtils.showTextToast(this, string, 1);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        AudioEffectController audioEffectController = this.mAudioEffectController;
        if (audioEffectController != null) {
            audioEffectController.registerListener(TAG, this);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        AudioEffectController audioEffectController = this.mAudioEffectController;
        if (audioEffectController != null) {
            audioEffectController.saveSettings();
            this.mAudioEffectController.deregisterListener(TAG);
            unregisterReceivers();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.mToolbar = toolbar;
        setSupportActionBar(toolbar);
        this.mToolbar.setNavigationIcon(R.drawable.btn_close);
        this.mToolbar.setNavigationContentDescription(R.string.close_button_content_description);
        this.mToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.AudioEffectActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                EqualizerMetrics.INSTANCE.emitUiClickEQPageClose();
                AudioEffectActivity.this.onBackPressed();
            }
        });
        this.mEqualizerActionBarProvider = new EqualizerActionBarProvider();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupPresetTabs() {
        this.mSlidingTabBar = (SlidingTabBar) findViewById(R.id.slidingTabBar);
        AudioEffectController audioEffectController = this.mAudioEffectController;
        if (audioEffectController == null) {
            return;
        }
        for (String str : audioEffectController.getPresets()) {
            this.mSlidingTabBar.addToTabStrip(str);
        }
        this.mSlidingTabBar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.activity.AudioEffectActivity.11
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                AudioEffectActivity.this.mSlidingTabBar.selectTab(AudioEffectActivity.this.mAudioEffectController.getPresetNum());
                AudioEffectActivity.this.mSlidingTabBar.setSlidingTabBarChangeListener(AudioEffectActivity.this.mSlidingTabBarChangeListener);
                AudioEffectActivity.this.mSlidingTabBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectKnobs() {
        VolumeController volumeController;
        this.mBassBoost = (Knob) findViewById(R.id.bass_boost);
        this.mTreble = (Knob) findViewById(R.id.treble);
        AudioEffectController audioEffectController = this.mAudioEffectController;
        if (audioEffectController != null) {
            this.mBassBoost.setMax(audioEffectController.getBassBoostMaxStrength());
            this.mBassBoost.setProgress(this.mAudioEffectController.getBassBoostStrength());
            this.mBassBoost.setOnKnobProgressChangeListener(this.mKnobBassChangeListener);
            this.mTreble.setMax(this.mAudioEffectController.getTrebleMaxStrength());
            this.mTreble.setProgress(this.mAudioEffectController.getTrebleStrength());
            this.mTreble.setOnKnobProgressChangeListener(this.mKnobTrebleChangeListener);
        }
        Knob knob = (Knob) findViewById(R.id.volume_control);
        this.mVolume = knob;
        if (knob == null || (volumeController = this.mVolumeController) == null) {
            return;
        }
        knob.setMax(volumeController.getMaximumVolume());
        this.mVolume.setProgress(this.mVolumeController.getCurrentVolume());
        this.mVolume.setOnKnobProgressChangeListener(this.mKnobVolumeChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDynamicMargins() {
        boolean zIsPortrait = ScreenUtil.isPortrait();
        boolean zIsNormalOrSmallScreen = ScreenUtil.isNormalOrSmallScreen();
        if (zIsPortrait && zIsNormalOrSmallScreen) {
            return;
        }
        Log.debug(TAG, "Setting Equalizer band container to use a percentage of the screen");
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ((LinearLayout) findViewById(R.id.equalizer_band_and_db_container)).getLayoutParams();
        Point activityWindowSize = ScreenUtil.getActivityWindowSize(this);
        if (zIsPortrait && !zIsNormalOrSmallScreen) {
            int i = activityWindowSize.x;
            int integer = (i - ((int) ((getResources().getInteger(R.integer.equalizer_band_and_db_area_percent_port_large) / 100.0f) * i))) / 2;
            layoutParams.setMarginStart(integer);
            layoutParams.setMarginEnd(integer);
            return;
        }
        float integer2 = getResources().getInteger(R.integer.equalizer_band_and_db_area_percent_land) / 100.0f;
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) ((LinearLayout) findViewById(R.id.knob_container)).getLayoutParams();
        Knob knob = this.mBassBoost;
        if (knob != null) {
            int knobWidth = knob.getKnobWidth() + getResources().getDimensionPixelSize(R.dimen.equalizer_layout_margin_right);
            int i2 = activityWindowSize.x;
            int i3 = (i2 - ((int) (integer2 * i2))) - knobWidth;
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.equalizer_band_padding_tb);
            int i4 = i3 / 2;
            layoutParams2.setMarginStart(i4 - dimensionPixelSize);
            layoutParams.setMarginStart(i4 + dimensionPixelSize);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addEqualizerBands() {
        AudioEffectController audioEffectController = this.mAudioEffectController;
        if (audioEffectController != null) {
            this.mEqualizerNumBands = audioEffectController.getEqualizerNumBands();
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.equalizer_band_container);
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService("layout_inflater");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1, 1.0f);
        AudioEffectController audioEffectController2 = this.mAudioEffectController;
        int equalizerMaxLevel = audioEffectController2 != null ? audioEffectController2.getEqualizerMaxLevel() : 100;
        if (layoutInflater != null) {
            for (short s = 0; s < this.mEqualizerNumBands; s = (short) (s + 1)) {
                View viewInflate = layoutInflater.inflate(R.layout.equalizer_band, (ViewGroup) null);
                if (s > 0) {
                    Space space = new Space(getApplicationContext());
                    space.setLayoutParams(layoutParams);
                    linearLayout.addView(space);
                }
                ((TextView) viewInflate.findViewById(R.id.eq_band_hz_text)).setText(getBandFreqLabel(s));
                VerticalSeekBar verticalSeekBar = (VerticalSeekBar) viewInflate.findViewById(R.id.eq_slider);
                int iGenerateViewId = View.generateViewId();
                verticalSeekBar.setId(iGenerateViewId);
                linearLayout.addView(viewInflate);
                VerticalSeekBar verticalSeekBar2 = (VerticalSeekBar) viewInflate.findViewById(iGenerateViewId);
                List<VerticalSeekBar> list = this.mEqBands;
                if (list != null) {
                    list.add(verticalSeekBar2);
                }
                verticalSeekBar2.setMax(equalizerMaxLevel);
                AudioEffectController audioEffectController3 = this.mAudioEffectController;
                if (audioEffectController3 != null) {
                    verticalSeekBar2.setProgress(audioEffectController3.getBandLevel(s));
                }
                verticalSeekBar2.setOnSeekBarChangeListener(this.mOnBandChangeListener);
            }
        }
    }

    private String getBandFreqLabel(final short bandNum) {
        AudioEffectController audioEffectController = this.mAudioEffectController;
        if (audioEffectController == null) {
            return "";
        }
        float bandHz = audioEffectController.getBandHz(bandNum);
        return bandHz >= 1000.0f ? String.format("%.0f%s", Float.valueOf(bandHz / 1000.0f), getString(R.string.equalizer_khz_label)) : String.format("%.0f%s", Float.valueOf(bandHz), getString(R.string.equalizer_hz_label));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDecibelLabels() {
        TextView textView = (TextView) findViewById(R.id.equalizer_db_min_label);
        TextView textView2 = (TextView) findViewById(R.id.equalizer_db_max_label);
        try {
            AudioEffectController audioEffectController = this.mAudioEffectController;
            if (audioEffectController == null) {
                Log.error(TAG, "Unable to set decibel label range for equalizer");
                return;
            }
            int[] bandDecibelRange = audioEffectController.getBandDecibelRange();
            textView.setText(String.format(getString(R.string.equalizer_db_min_label), Integer.valueOf(bandDecibelRange[0])));
            textView2.setText(String.format(getString(R.string.equalizer_db_max_label), Integer.valueOf(bandDecibelRange[1])));
        } catch (AudioEffectController.AudioEffectException unused) {
            Log.error(TAG, "Unable to set decibel label range for equalizer");
        }
    }

    private void registerReceivers() {
        this.mVolumeChangeReceiver = new VolumeChangeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction(SystemMediaRouteProvider.LegacyImpl.VolumeChangeReceiver.VOLUME_CHANGED_ACTION);
        registerReceiver(this.mVolumeChangeReceiver, intentFilter);
    }

    private void unregisterReceivers() {
        unregisterReceiver(this.mVolumeChangeReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleEqualizerUI(boolean enable) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.equalizer_band_container);
        this.mSlidingTabBar.setEnabled(enable);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View childAt = linearLayout.getChildAt(i);
            if (childAt instanceof LinearLayout) {
                toggleFrequencyBand((LinearLayout) childAt, enable);
            }
        }
        TextView textView = (TextView) findViewById(R.id.equalizer_db_min_label);
        TextView textView2 = (TextView) findViewById(R.id.equalizer_db_max_label);
        TextView textView3 = (TextView) findViewById(R.id.equalizer_db_0_label);
        if (!enable) {
            textView.setTextColor(getResources().getColor(R.color.dark_grey));
            textView2.setTextColor(getResources().getColor(R.color.dark_grey));
            textView3.setTextColor(getResources().getColor(R.color.dark_grey));
        } else {
            textView.setTextColor(getResources().getColor(R.color.white));
            textView2.setTextColor(getResources().getColor(R.color.white));
            textView3.setTextColor(getResources().getColor(R.color.white));
        }
    }

    private void toggleFrequencyBand(final LinearLayout frequencyBandLayout, boolean enable) {
        for (int i = 0; i < frequencyBandLayout.getChildCount(); i++) {
            View childAt = frequencyBandLayout.getChildAt(i);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (!enable) {
                    textView.setTextColor(getResources().getColor(R.color.dark_grey));
                } else {
                    textView.setTextColor(getResources().getColor(R.color.white));
                }
            } else if (childAt instanceof FrameLayout) {
                toggleSlider((FrameLayout) childAt, enable);
            }
        }
    }

    private void toggleSlider(final FrameLayout frameLayout, boolean enable) {
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.AudioEffectActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AudioEffectActivity.this.mAudioEffectController.isAudioEffectControllerInErrorState()) {
                    AudioEffectActivity audioEffectActivity = AudioEffectActivity.this;
                    BauhausToastUtils.showTextToast(audioEffectActivity, audioEffectActivity.getString(R.string.equalizer_cannot_apply_effects), 1);
                }
            }
        });
        for (int i = 0; i < frameLayout.getChildCount(); i++) {
            View childAt = frameLayout.getChildAt(i);
            if (childAt instanceof VerticalSeekBar) {
                VerticalSeekBar verticalSeekBar = (VerticalSeekBar) childAt;
                Drawable thumb = verticalSeekBar.getThumb();
                if (thumb != null) {
                    if (!enable) {
                        thumb.setTint(getResources().getColor(R.color.dark_grey));
                    } else {
                        thumb.setTint(getResources().getColor(R.color.white));
                    }
                }
                verticalSeekBar.setEnabled(enable);
            }
        }
    }

    private class VolumeChangeReceiver extends BroadcastReceiver {
        private VolumeChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            if ("android.intent.action.HEADSET_PLUG".equals(intent.getAction()) && AudioEffectActivity.this.mVolume != null && AudioEffectActivity.this.mVolumeController != null) {
                AudioEffectActivity.this.mVolume.setMax(AudioEffectActivity.this.mVolumeController.getMaximumVolume());
                AudioEffectActivity.this.mVolume.setProgress(AudioEffectActivity.this.mVolumeController.getCurrentVolume());
            } else {
                if (!SystemMediaRouteProvider.LegacyImpl.VolumeChangeReceiver.VOLUME_CHANGED_ACTION.equals(intent.getAction()) || AudioEffectActivity.this.mVolume == null || AudioEffectActivity.this.mVolumeController == null || AudioEffectActivity.this.mVolume.getProgress() == AudioEffectActivity.this.mVolumeController.getCurrentVolume()) {
                    return;
                }
                AudioEffectActivity.this.updateKnobProgress();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateKnobProgress() {
        Knob knob = this.mVolume;
        if (knob == null || this.mVolumeController == null) {
            return;
        }
        knob.setOnKnobProgressChangeListener(null);
        this.mVolume.setProgress(this.mVolumeController.getCurrentVolume());
        this.mVolume.setOnKnobProgressChangeListener(this.mKnobVolumeChangeListener);
    }
}

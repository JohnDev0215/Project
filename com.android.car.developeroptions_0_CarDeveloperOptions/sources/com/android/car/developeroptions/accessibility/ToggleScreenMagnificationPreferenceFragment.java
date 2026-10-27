package com.android.car.developeroptions.accessibility;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.VideoView;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.widget.SwitchBar;

/* JADX INFO: loaded from: classes.dex */
public class ToggleScreenMagnificationPreferenceFragment extends ToggleFeaturePreferenceFragment implements SwitchBar.OnSwitchChangeListener {
    protected Preference mConfigWarningPreference;
    protected VideoPreference mVideoPreference;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 7;
    }

    protected class VideoPreference extends Preference {
        private ViewTreeObserver.OnGlobalLayoutListener mLayoutListener;
        private ImageView mVideoBackgroundView;

        public VideoPreference(Context context) {
            super(context);
        }

        @Override // androidx.preference.Preference
        public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
            super.onBindViewHolder(preferenceViewHolder);
            Resources resources = ToggleScreenMagnificationPreferenceFragment.this.getPrefContext().getResources();
            final int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.screen_magnification_video_background_width);
            final int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.screen_magnification_video_width);
            final int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.screen_magnification_video_height);
            final int dimensionPixelSize4 = resources.getDimensionPixelSize(R.dimen.screen_magnification_video_margin_top);
            preferenceViewHolder.setDividerAllowedAbove(false);
            preferenceViewHolder.setDividerAllowedBelow(false);
            this.mVideoBackgroundView = (ImageView) preferenceViewHolder.findViewById(R.id.video_background);
            final VideoView videoView = (VideoView) preferenceViewHolder.findViewById(R.id.video);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(this) { // from class: com.android.car.developeroptions.accessibility.ToggleScreenMagnificationPreferenceFragment.VideoPreference.1
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setLooping(true);
                }
            });
            videoView.setAudioFocusRequest(0);
            Bundle arguments = ToggleScreenMagnificationPreferenceFragment.this.getArguments();
            if (arguments != null && arguments.containsKey("video_resource")) {
                videoView.setVideoURI(Uri.parse(String.format("%s://%s/%s", "android.resource", ToggleScreenMagnificationPreferenceFragment.this.getPrefContext().getPackageName(), Integer.valueOf(arguments.getInt("video_resource")))));
            }
            videoView.setMediaController(null);
            this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.car.developeroptions.accessibility.ToggleScreenMagnificationPreferenceFragment.VideoPreference.2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    int width = VideoPreference.this.mVideoBackgroundView.getWidth();
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
                    int i = dimensionPixelSize2 * width;
                    int i2 = dimensionPixelSize;
                    layoutParams.width = i / i2;
                    layoutParams.height = (dimensionPixelSize3 * width) / i2;
                    layoutParams.setMargins(0, (dimensionPixelSize4 * width) / i2, 0, 0);
                    videoView.setLayoutParams(layoutParams);
                    videoView.invalidate();
                    videoView.start();
                }
            };
            this.mVideoBackgroundView.getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutListener);
        }

        @Override // androidx.preference.Preference
        protected void onPrepareForRemoval() {
            this.mVideoBackgroundView.getViewTreeObserver().removeOnGlobalLayoutListener(this.mLayoutListener);
        }
    }

    @Override // com.android.car.developeroptions.accessibility.ToggleFeaturePreferenceFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        VideoPreference videoPreference = new VideoPreference(getPrefContext());
        this.mVideoPreference = videoPreference;
        videoPreference.setSelectable(false);
        this.mVideoPreference.setPersistent(false);
        this.mVideoPreference.setLayoutResource(R.layout.magnification_video_preference);
        Preference preference = new Preference(getPrefContext());
        this.mConfigWarningPreference = preference;
        preference.setSelectable(false);
        this.mConfigWarningPreference.setPersistent(false);
        this.mConfigWarningPreference.setVisible(false);
        this.mConfigWarningPreference.setIcon(R.drawable.ic_warning_24dp);
        PreferenceScreen preferenceScreen = getPreferenceManager().getPreferenceScreen();
        preferenceScreen.setOrderingAsAdded(false);
        this.mVideoPreference.setOrder(0);
        this.mConfigWarningPreference.setOrder(2);
        preferenceScreen.addPreference(this.mVideoPreference);
        preferenceScreen.addPreference(this.mConfigWarningPreference);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        VideoView videoView = (VideoView) getView().findViewById(R.id.video);
        if (videoView != null) {
            videoView.start();
        }
        updateConfigurationWarningIfNeeded();
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r1, boolean z) {
        onPreferenceToggled(this.mPreferenceKey, z);
    }

    protected void onPreferenceToggled(String str, boolean z) {
        MagnificationPreferenceFragment.setChecked(getContentResolver(), str, z);
        updateConfigurationWarningIfNeeded();
    }

    @Override // com.android.car.developeroptions.accessibility.ToggleFeaturePreferenceFragment
    protected void onInstallSwitchBarToggleSwitch() {
        super.onInstallSwitchBarToggleSwitch();
        this.mSwitchBar.setCheckedInternal(MagnificationPreferenceFragment.isChecked(getContentResolver(), this.mPreferenceKey));
        this.mSwitchBar.addOnSwitchChangeListener(this);
    }

    @Override // com.android.car.developeroptions.accessibility.ToggleFeaturePreferenceFragment
    protected void onRemoveSwitchBarToggleSwitch() {
        super.onRemoveSwitchBarToggleSwitch();
        this.mSwitchBar.removeOnSwitchChangeListener(this);
    }

    @Override // com.android.car.developeroptions.accessibility.ToggleFeaturePreferenceFragment
    protected void onProcessArguments(Bundle bundle) {
        int i;
        super.onProcessArguments(bundle);
        if (bundle == null) {
            return;
        }
        if (bundle.containsKey("video_resource")) {
            this.mVideoPreference.setVisible(true);
            bundle.getInt("video_resource");
        } else {
            this.mVideoPreference.setVisible(false);
        }
        if (bundle.containsKey("from_suw")) {
            bundle.getBoolean("from_suw");
        }
        if (bundle.containsKey("checked")) {
            bundle.getBoolean("checked");
        }
        if (!bundle.containsKey("title_res") || (i = bundle.getInt("title_res")) <= 0) {
            return;
        }
        getActivity().setTitle(i);
    }

    private void updateConfigurationWarningIfNeeded() {
        CharSequence configurationWarningStringForSecureSettingsKey = MagnificationPreferenceFragment.getConfigurationWarningStringForSecureSettingsKey(this.mPreferenceKey, getPrefContext());
        if (configurationWarningStringForSecureSettingsKey != null) {
            this.mConfigWarningPreference.setSummary(configurationWarningStringForSecureSettingsKey);
        }
        this.mConfigWarningPreference.setVisible(configurationWarningStringForSecureSettingsKey != null);
    }
}

package com.android.car.developeroptions.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.InstrumentedPreferenceFragment;
import com.android.car.developeroptions.core.PreferenceXmlParserUtils;
import com.android.car.developeroptions.widget.RadioButtonPreference;
import com.android.settingslib.widget.CandidateInfo;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public abstract class RadioButtonPickerFragment extends InstrumentedPreferenceFragment implements RadioButtonPreference.OnClickListener {
    static final String EXTRA_FOR_WORK = "for_work";
    boolean mAppendStaticPreferences = false;
    private final Map<String, CandidateInfo> mCandidates = new ArrayMap();
    private int mIllustrationId;
    private int mIllustrationPreviewId;
    protected int mUserId;
    protected UserManager mUserManager;
    private VideoPreference mVideoPreference;

    protected void addStaticPreferences(PreferenceScreen preferenceScreen) {
    }

    public void bindPreferenceExtra(RadioButtonPreference radioButtonPreference, String str, CandidateInfo candidateInfo, String str2, String str3) {
    }

    protected abstract List<? extends CandidateInfo> getCandidates();

    protected abstract String getDefaultKey();

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected abstract int getPreferenceScreenResId();

    protected int getRadioButtonPreferenceCustomLayoutResId() {
        return 0;
    }

    protected String getSystemDefaultKey() {
        return null;
    }

    protected void onSelectionPerformed(boolean z) {
    }

    protected abstract boolean setDefaultKey(String str);

    protected boolean shouldShowItemNone() {
        return false;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        int iMyUserId;
        super.onAttach(context);
        this.mUserManager = (UserManager) context.getSystemService("user");
        Bundle arguments = getArguments();
        boolean z = arguments != null ? arguments.getBoolean(EXTRA_FOR_WORK) : false;
        UserHandle managedProfile = Utils.getManagedProfile(this.mUserManager);
        if (z && managedProfile != null) {
            iMyUserId = managedProfile.getIdentifier();
        } else {
            iMyUserId = UserHandle.myUserId();
        }
        this.mUserId = iMyUserId;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        try {
            this.mAppendStaticPreferences = PreferenceXmlParserUtils.extractMetadata(getContext(), getPreferenceScreenResId(), 1025).get(0).getBoolean("staticPreferenceLocation");
        } catch (IOException e) {
            Log.e("RadioButtonPckrFrgmt", "Error trying to open xml file", e);
        } catch (XmlPullParserException e2) {
            Log.e("RadioButtonPckrFrgmt", "Error parsing xml", e2);
        }
        updateCandidates();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        setHasOptionsMenu(true);
        return viewOnCreateView;
    }

    public void onRadioButtonClicked(RadioButtonPreference radioButtonPreference) {
        onRadioButtonConfirmed(radioButtonPreference.getKey());
    }

    protected CandidateInfo getCandidate(String str) {
        return this.mCandidates.get(str);
    }

    protected void onRadioButtonConfirmed(String str) {
        boolean defaultKey = setDefaultKey(str);
        if (defaultKey) {
            updateCheckedState(str);
        }
        onSelectionPerformed(defaultKey);
    }

    public void updateCandidates() {
        this.mCandidates.clear();
        List<? extends CandidateInfo> candidates = getCandidates();
        if (candidates != null) {
            for (CandidateInfo candidateInfo : candidates) {
                this.mCandidates.put(candidateInfo.getKey(), candidateInfo);
            }
        }
        String defaultKey = getDefaultKey();
        String systemDefaultKey = getSystemDefaultKey();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.removeAll();
        if (this.mIllustrationId != 0) {
            addIllustration(preferenceScreen);
        }
        if (!this.mAppendStaticPreferences) {
            addStaticPreferences(preferenceScreen);
        }
        int radioButtonPreferenceCustomLayoutResId = getRadioButtonPreferenceCustomLayoutResId();
        if (shouldShowItemNone()) {
            RadioButtonPreference radioButtonPreference = new RadioButtonPreference(getPrefContext());
            if (radioButtonPreferenceCustomLayoutResId > 0) {
                radioButtonPreference.setLayoutResource(radioButtonPreferenceCustomLayoutResId);
            }
            radioButtonPreference.setIcon(R.drawable.ic_remove_circle);
            radioButtonPreference.setTitle(R.string.app_list_preference_none);
            radioButtonPreference.setChecked(TextUtils.isEmpty(defaultKey));
            radioButtonPreference.setOnClickListener(this);
            preferenceScreen.addPreference(radioButtonPreference);
        }
        if (candidates != null) {
            for (CandidateInfo candidateInfo2 : candidates) {
                RadioButtonPreference radioButtonPreference2 = new RadioButtonPreference(getPrefContext());
                if (radioButtonPreferenceCustomLayoutResId > 0) {
                    radioButtonPreference2.setLayoutResource(radioButtonPreferenceCustomLayoutResId);
                }
                bindPreference(radioButtonPreference2, candidateInfo2.getKey(), candidateInfo2, defaultKey);
                bindPreferenceExtra(radioButtonPreference2, candidateInfo2.getKey(), candidateInfo2, defaultKey, systemDefaultKey);
                preferenceScreen.addPreference(radioButtonPreference2);
            }
        }
        mayCheckOnlyRadioButton();
        if (this.mAppendStaticPreferences) {
            addStaticPreferences(preferenceScreen);
        }
    }

    public RadioButtonPreference bindPreference(RadioButtonPreference radioButtonPreference, String str, CandidateInfo candidateInfo, String str2) {
        radioButtonPreference.setTitle(candidateInfo.loadLabel());
        Utils.setSafeIcon(radioButtonPreference, candidateInfo.loadIcon());
        radioButtonPreference.setKey(str);
        if (TextUtils.equals(str2, str)) {
            radioButtonPreference.setChecked(true);
        }
        radioButtonPreference.setEnabled(candidateInfo.enabled);
        radioButtonPreference.setOnClickListener(this);
        return radioButtonPreference;
    }

    public void updateCheckedState(String str) {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            int preferenceCount = preferenceScreen.getPreferenceCount();
            for (int i = 0; i < preferenceCount; i++) {
                Preference preference = preferenceScreen.getPreference(i);
                if (preference instanceof RadioButtonPreference) {
                    RadioButtonPreference radioButtonPreference = (RadioButtonPreference) preference;
                    if (radioButtonPreference.isChecked() != TextUtils.equals(preference.getKey(), str)) {
                        radioButtonPreference.setChecked(TextUtils.equals(preference.getKey(), str));
                    }
                }
            }
        }
    }

    public void mayCheckOnlyRadioButton() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen == null || preferenceScreen.getPreferenceCount() != 1) {
            return;
        }
        Preference preference = preferenceScreen.getPreference(0);
        if (preference instanceof RadioButtonPreference) {
            ((RadioButtonPreference) preference).setChecked(true);
        }
    }

    protected void setIllustration(int i, int i2) {
        this.mIllustrationId = i;
        this.mIllustrationPreviewId = i2;
    }

    private void addIllustration(PreferenceScreen preferenceScreen) {
        VideoPreference videoPreference = new VideoPreference(getContext());
        this.mVideoPreference = videoPreference;
        videoPreference.setVideo(this.mIllustrationId, this.mIllustrationPreviewId);
        preferenceScreen.addPreference(this.mVideoPreference);
    }
}

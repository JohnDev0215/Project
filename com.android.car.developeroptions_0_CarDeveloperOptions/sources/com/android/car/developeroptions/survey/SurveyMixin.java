package com.android.car.developeroptions.survey;

import android.content.BroadcastReceiver;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.overlay.SurveyFeatureProvider;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;

/* JADX INFO: loaded from: classes.dex */
public class SurveyMixin implements LifecycleObserver, OnResume, OnPause {
    private Fragment mFragment;
    private String mName;
    private BroadcastReceiver mReceiver;

    public SurveyMixin(Fragment fragment, String str) {
        this.mName = str;
        this.mFragment = fragment;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        SurveyFeatureProvider surveyFeatureProvider;
        FragmentActivity activity = this.mFragment.getActivity();
        if (activity == null || (surveyFeatureProvider = FeatureFactory.getFactory(activity).getSurveyFeatureProvider(activity)) == null) {
            return;
        }
        String surveyId = surveyFeatureProvider.getSurveyId(activity, this.mName);
        if (surveyFeatureProvider.getSurveyExpirationDate(activity, surveyId) <= -1) {
            this.mReceiver = surveyFeatureProvider.createAndRegisterReceiver(activity);
            surveyFeatureProvider.downloadSurvey(activity, surveyId, null);
        } else {
            surveyFeatureProvider.showSurveyIfAvailable(activity, surveyId);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        FragmentActivity activity = this.mFragment.getActivity();
        BroadcastReceiver broadcastReceiver = this.mReceiver;
        if (broadcastReceiver == null || activity == null) {
            return;
        }
        SurveyFeatureProvider.unregisterReceiver(activity, broadcastReceiver);
        this.mReceiver = null;
    }
}

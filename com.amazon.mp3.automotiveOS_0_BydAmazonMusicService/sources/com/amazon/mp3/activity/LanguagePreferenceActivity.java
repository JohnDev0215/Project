package com.amazon.mp3.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.LinearLayout;
import com.amazon.hermes.Hermes;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.configuration.PreferenceUpdateProvider;
import com.amazon.mp3.language.preference.LanguagePreferenceProvider;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.languagepreference.Language;
import com.amazon.music.languagepreference.LanguagePreference;
import com.amazon.music.languagepreference.LanguagePreferenceException;
import com.amazon.music.widget.languagepreference.LanguagePreferenceView;
import com.amazon.music.widget.languagepreference.MetricConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class LanguagePreferenceActivity extends BaseActivity {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private static final String TAG = "LanguagePreferenceActivity";
    private LanguagePreference mLanguagePreference;
    private LanguagePreferenceView mLanguagePreferenceView;

    public static Intent getStartIntent(final Activity fromActivity) {
        return new Intent(fromActivity, (Class<?>) LanguagePreferenceActivity.class);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(final Bundle outState) {
        List<Language> selectedLanguagePreference;
        super.onSaveInstanceState(outState);
        if (outState == null || (selectedLanguagePreference = this.mLanguagePreferenceView.getSelectedLanguagePreference()) == null || selectedLanguagePreference.isEmpty()) {
            return;
        }
        outState.putParcelableArrayList("AvailableLanguages", new ArrayList<>(selectedLanguagePreference));
    }

    private void init(final Bundle savedInstanceState) {
        LanguagePreference languagePreferenceBuildLanguagePreference = LanguagePreferenceProvider.buildLanguagePreference(this);
        this.mLanguagePreference = languagePreferenceBuildLanguagePreference;
        if (languagePreferenceBuildLanguagePreference == null) {
            finish();
        }
        this.mLanguagePreferenceView = new LanguagePreferenceView(this);
        setContentView(this.mLanguagePreferenceView, new LinearLayout.LayoutParams(-1, -1));
        NavigationAppEvent.builder(MetricConstants.SEE_LANGUAGE_PREFERENCE.toString()).withOrientation(ScreenUtil.getScreenOrientation(getApplicationContext())).publish();
        restoreLanguagePreference(savedInstanceState);
    }

    private void restoreLanguagePreference(final Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("AvailableLanguages")) {
            bindAvailableLanguages(savedInstanceState.getParcelableArrayList("AvailableLanguages"));
        } else {
            fetchAndBindAvailableLanguages();
        }
    }

    private void fetchAndBindAvailableLanguages() {
        HandlerThread handlerThread = new HandlerThread("BackgroundHandlerThread");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(new Runnable() { // from class: com.amazon.mp3.activity.LanguagePreferenceActivity.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    final List<Language> availableContentLanguage = LanguagePreferenceActivity.this.mLanguagePreference.getAvailableContentLanguage();
                    if (availableContentLanguage == null || availableContentLanguage.isEmpty()) {
                        return;
                    }
                    LanguagePreferenceActivity.this.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.activity.LanguagePreferenceActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            LanguagePreferenceActivity.this.bindAvailableLanguages(availableContentLanguage);
                        }
                    });
                } catch (LanguagePreferenceException e) {
                    Log.error(LanguagePreferenceActivity.TAG, "Error getting available content languages or retrieving preference:", e);
                    LanguagePreferenceActivity.this.finish();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindAvailableLanguages(final List<Language> availableContentLanguageList) {
        this.mLanguagePreferenceView.setAvailableContentLanguage(availableContentLanguageList, new View.OnClickListener() { // from class: com.amazon.mp3.activity.LanguagePreferenceActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                HandlerThread handlerThread = new HandlerThread("BackgroundHandlerThread");
                handlerThread.start();
                new Handler(handlerThread.getLooper()).post(new Runnable() { // from class: com.amazon.mp3.activity.LanguagePreferenceActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            try {
                                List<Language> selectedLanguagePreference = LanguagePreferenceActivity.this.mLanguagePreferenceView.getSelectedLanguagePreference();
                                boolean z = false;
                                Iterator<Language> it = selectedLanguagePreference.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    } else if (it.next().getUserPreferred()) {
                                        z = true;
                                        break;
                                    }
                                }
                                if (z) {
                                    BauhausToastUtils.showDefaultToast(LanguagePreferenceActivity.this, R.string.dmusic_language_pref_banner_saved, 1);
                                } else {
                                    BauhausToastUtils.showDefaultToast(LanguagePreferenceActivity.this, R.string.dmusic_language_pref_banner_clear, 1);
                                }
                                Hermes.get().getRequestQueue().getCache().clear();
                                LanguagePreferenceActivity.this.mLanguagePreference.updatePreferePreference(selectedLanguagePreference);
                                PreferenceUpdateProvider.dispatchPreferenceUpdateEvent();
                            } catch (LanguagePreferenceException e) {
                                Log.error(LanguagePreferenceActivity.TAG, "Error making update preference call:" + e);
                            }
                        } finally {
                            LanguagePreferenceActivity.this.mLanguagePreferenceView.sendUiClickMetric(MetricConstants.DONE);
                            LanguagePreferenceActivity.this.finish();
                        }
                    }
                });
            }
        });
    }
}

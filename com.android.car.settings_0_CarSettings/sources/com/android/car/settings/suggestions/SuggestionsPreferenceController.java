package com.android.car.settings.suggestions;

import android.app.PendingIntent;
import android.car.drivingstate.CarUxRestrictions;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.service.settings.suggestions.Suggestion;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.suggestions.SuggestionPreference;
import com.android.settingslib.suggestions.SuggestionController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SuggestionsPreferenceController extends PreferenceController<PreferenceGroup> implements SuggestionController.ServiceConnectionListener, LoaderManager.LoaderCallbacks<List<Suggestion>>, SuggestionPreference.Callback {
    private LoaderManager mLoaderManager;
    private final SuggestionController mSuggestionController;
    private List<Suggestion> mSuggestionsList;
    private static final Logger LOG = new Logger((Class<?>) SuggestionsPreferenceController.class);
    private static final ComponentName COMPONENT_NAME = new ComponentName("com.android.settings.intelligence", "com.android.settings.intelligence.suggestions.SuggestionService");

    public SuggestionsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mSuggestionsList = new ArrayList();
        this.mSuggestionController = new SuggestionController(context, COMPONENT_NAME, this);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    public void setLoaderManager(LoaderManager loaderManager) {
        this.mLoaderManager = loaderManager;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        LOG.v("checkInitialized");
        if (this.mLoaderManager == null) {
            throw new IllegalStateException("SuggestionPreferenceController must be initialized by calling setLoaderManager(LoaderManager)");
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        LOG.v("onStartInternal");
        this.mSuggestionController.start();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        LOG.v("onStopInternal");
        this.mSuggestionController.stop();
        cleanupLoader();
    }

    @Override // com.android.settingslib.suggestions.SuggestionController.ServiceConnectionListener
    public void onServiceConnected() {
        LOG.v("onServiceConnected");
        this.mLoaderManager.restartLoader(42, null, this);
    }

    @Override // com.android.settingslib.suggestions.SuggestionController.ServiceConnectionListener
    public void onServiceDisconnected() {
        LOG.v("onServiceDisconnected");
        cleanupLoader();
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<List<Suggestion>> onCreateLoader(int id, Bundle args) {
        LOG.v("onCreateLoader: " + id);
        if (id == 42) {
            return new SettingsSuggestionsLoader(getContext(), this.mSuggestionController);
        }
        throw new IllegalArgumentException("This loader id is not supported " + id);
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<List<Suggestion>> loader, List<Suggestion> suggestions) {
        LOG.v("onLoadFinished");
        if (suggestions == null) {
            return;
        }
        updateSuggestionPreferences(suggestions);
        this.mSuggestionsList = new ArrayList(suggestions);
    }

    private void updateSuggestionPreferences(List<Suggestion> suggestions) {
        for (Suggestion oldSuggestion : this.mSuggestionsList) {
            boolean isInNewSuggestionList = false;
            Iterator<Suggestion> it = suggestions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (oldSuggestion.getId().equals(it.next().getId())) {
                    isInNewSuggestionList = true;
                    break;
                }
            }
            if (!isInNewSuggestionList) {
                getPreference().removePreference(getPreference().findPreference(getSuggestionPreferenceKey(oldSuggestion)));
            }
        }
        for (Suggestion suggestion : suggestions) {
            Preference curPref = getPreference().findPreference(getSuggestionPreferenceKey(suggestion));
            if (curPref == null) {
                SuggestionPreference newSuggPref = new SuggestionPreference(getContext(), suggestion, this);
                getPreference().addPreference(newSuggPref);
            } else {
                ((SuggestionPreference) curPref).updateSuggestion(suggestion);
            }
        }
        refreshUi();
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<List<Suggestion>> loader) {
        LOG.v("onLoaderReset");
    }

    @Override // com.android.car.settings.suggestions.SuggestionPreference.Callback
    public void launchSuggestion(SuggestionPreference preference) {
        LOG.v("launchSuggestion");
        Suggestion suggestion = preference.getSuggestion();
        try {
            if (suggestion.getPendingIntent() != null) {
                suggestion.getPendingIntent().send();
                this.mSuggestionController.launchSuggestion(suggestion);
            } else {
                LOG.w("Suggestion with null pending intent " + suggestion.getId());
            }
        } catch (PendingIntent.CanceledException e) {
            LOG.w("Failed to start suggestion " + suggestion.getId());
        }
    }

    @Override // com.android.car.settings.suggestions.SuggestionPreference.Callback
    public void dismissSuggestion(SuggestionPreference preference) {
        LOG.v("dismissSuggestion");
        Suggestion suggestion = preference.getSuggestion();
        this.mSuggestionController.dismissSuggestions(suggestion);
        this.mSuggestionsList.remove(suggestion);
        getPreference().removePreference(preference);
        refreshUi();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preference) {
        preference.setVisible(preference.getPreferenceCount() > 0);
    }

    private void cleanupLoader() {
        LOG.v("cleanupLoader");
        this.mLoaderManager.destroyLoader(42);
    }

    private String getSuggestionPreferenceKey(Suggestion suggestion) {
        return SuggestionPreference.SUGGESTION_PREFERENCE_KEY + suggestion.getId();
    }
}

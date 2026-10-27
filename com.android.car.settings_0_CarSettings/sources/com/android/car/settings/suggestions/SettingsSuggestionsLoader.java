package com.android.car.settings.suggestions;

import android.content.Context;
import android.service.settings.suggestions.Suggestion;
import com.android.car.settings.common.AsyncLoader;
import com.android.car.settings.common.Logger;
import com.android.settingslib.suggestions.SuggestionController;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SettingsSuggestionsLoader extends AsyncLoader<List<Suggestion>> {
    public static final int LOADER_ID_SUGGESTIONS = 42;
    private static final Logger LOG = new Logger((Class<?>) SettingsSuggestionsLoader.class);
    private final SuggestionController mSuggestionController;

    public SettingsSuggestionsLoader(Context context, SuggestionController controller) {
        super(context);
        this.mSuggestionController = controller;
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public List<Suggestion> loadInBackground() {
        List<Suggestion> data = this.mSuggestionController.getSuggestions();
        if (data == null) {
            LOG.d("data is null");
        } else {
            LOG.d("data size " + data.size());
        }
        return data;
    }
}

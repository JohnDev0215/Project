package com.android.car.developeroptions.homepage.contextualcards.legacysuggestion;

import android.app.PendingIntent;
import android.content.Context;
import android.service.settings.suggestions.Suggestion;
import android.util.ArrayMap;
import android.util.Log;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardController;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardUpdateListener;
import com.android.car.developeroptions.homepage.contextualcards.legacysuggestion.LegacySuggestionContextualCard;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.suggestions.SuggestionController;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class LegacySuggestionContextualCardController implements ContextualCardController, LifecycleObserver, OnStart, OnStop, SuggestionController.ServiceConnectionListener {
    private ContextualCardUpdateListener mCardUpdateListener;
    private final Context mContext;
    SuggestionController mSuggestionController;

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardController
    public void onActionClick(ContextualCard contextualCard) {
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardController
    public void onDismissed(ContextualCard contextualCard) {
    }

    @Override // com.android.settingslib.suggestions.SuggestionController.ServiceConnectionListener
    public void onServiceDisconnected() {
    }

    public LegacySuggestionContextualCardController(Context context) {
        this.mContext = context;
        if (!context.getResources().getBoolean(R.bool.config_use_legacy_suggestion)) {
            Log.w("LegacySuggestCardCtrl", "Legacy suggestion contextual card disabled, skipping.");
        } else {
            this.mSuggestionController = new SuggestionController(this.mContext, FeatureFactory.getFactory(this.mContext).getSuggestionFeatureProvider(this.mContext).getSuggestionServiceComponent(), this);
        }
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardController
    public void onPrimaryClick(ContextualCard contextualCard) {
        try {
            ((LegacySuggestionContextualCard) contextualCard).getPendingIntent().send();
        } catch (PendingIntent.CanceledException unused) {
            Log.w("LegacySuggestCardCtrl", "Failed to start suggestion " + contextualCard.getTitleText());
        }
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardController
    public void setCardUpdateListener(ContextualCardUpdateListener contextualCardUpdateListener) {
        this.mCardUpdateListener = contextualCardUpdateListener;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        SuggestionController suggestionController = this.mSuggestionController;
        if (suggestionController == null) {
            return;
        }
        suggestionController.start();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        SuggestionController suggestionController = this.mSuggestionController;
        if (suggestionController == null) {
            return;
        }
        suggestionController.stop();
    }

    @Override // com.android.settingslib.suggestions.SuggestionController.ServiceConnectionListener
    public void onServiceConnected() {
        loadSuggestions();
    }

    private void loadSuggestions() {
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.homepage.contextualcards.legacysuggestion.-$$Lambda$LegacySuggestionContextualCardController$2Tqle7KD1XMnRGYaFjeu7EvUn_8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$loadSuggestions$1$LegacySuggestionContextualCardController();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$loadSuggestions$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$loadSuggestions$1$LegacySuggestionContextualCardController() {
        SuggestionController suggestionController = this.mSuggestionController;
        if (suggestionController == null || this.mCardUpdateListener == null) {
            return;
        }
        List<Suggestion> suggestions = suggestionController.getSuggestions();
        Log.d("LegacySuggestCardCtrl", "Loaded suggests: " + (suggestions == null ? "null" : String.valueOf(suggestions.size())));
        ArrayList arrayList = new ArrayList();
        if (suggestions != null) {
            for (Suggestion suggestion : suggestions) {
                LegacySuggestionContextualCard.Builder builder = new LegacySuggestionContextualCard.Builder();
                if (suggestion.getIcon() != null) {
                    builder.setIconDrawable(suggestion.getIcon().loadDrawable(this.mContext));
                }
                builder.setPendingIntent(suggestion.getPendingIntent());
                builder.setName(suggestion.getId());
                builder.setTitleText(suggestion.getTitle().toString());
                builder.setSummaryText(suggestion.getSummary().toString());
                builder.setViewType(R.layout.legacy_suggestion_tile);
                arrayList.add(builder.build());
            }
        }
        final ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(2, arrayList);
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.homepage.contextualcards.legacysuggestion.-$$Lambda$LegacySuggestionContextualCardController$f_FuGW5GNpNK7hLmHoEybeOtT90
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$loadSuggestions$0$LegacySuggestionContextualCardController(arrayMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$loadSuggestions$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$loadSuggestions$0$LegacySuggestionContextualCardController(Map map) {
        this.mCardUpdateListener.onContextualCardUpdated(map);
    }
}

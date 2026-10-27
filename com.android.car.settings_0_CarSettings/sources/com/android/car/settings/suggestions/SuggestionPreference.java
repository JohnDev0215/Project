package com.android.car.settings.suggestions;

import android.content.Context;
import android.service.settings.suggestions.Suggestion;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import com.android.car.settings.R;
import com.android.car.ui.preference.CarUiPreference;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class SuggestionPreference extends CarUiPreference {
    public static final String SUGGESTION_PREFERENCE_KEY = "suggestion_pref_key";
    private final Callback mCallback;
    private Suggestion mSuggestion;

    public interface Callback {
        void dismissSuggestion(SuggestionPreference suggestionPreference);

        void launchSuggestion(SuggestionPreference suggestionPreference);
    }

    public SuggestionPreference(Context context, Suggestion suggestion, Callback callback) {
        super(context);
        setLayoutResource(R.layout.suggestion_preference);
        this.mCallback = callback;
        setKey(SUGGESTION_PREFERENCE_KEY + suggestion.getId());
        updateSuggestion(suggestion);
    }

    public Suggestion getSuggestion() {
        return this.mSuggestion;
    }

    public void updateSuggestion(Suggestion suggestion) {
        Suggestion suggestion2 = this.mSuggestion;
        if (suggestion2 != null && !Objects.equals(suggestion2.getId(), suggestion.getId())) {
            throw new IllegalArgumentException("Suggestion preference update must have a matching id");
        }
        this.mSuggestion = suggestion;
        setIcon(suggestion.getIcon() != null ? suggestion.getIcon().loadDrawable(getContext()) : null);
        setTitle(suggestion.getTitle());
        setSummary(suggestion.getSummary());
    }

    @Override // com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.suggestions.-$$Lambda$SuggestionPreference$6j2xuD3pR-j9pZmNkOL81LtuVc4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$0$SuggestionPreference(view);
            }
        });
        View dismissButton = holder.findViewById(R.id.dismiss_button);
        dismissButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.suggestions.-$$Lambda$SuggestionPreference$y_egptwQXlUYeJRBl26Nt5YahBw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$1$SuggestionPreference(view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$SuggestionPreference(View v) {
        this.mCallback.launchSuggestion(this);
    }

    public /* synthetic */ void lambda$onBindViewHolder$1$SuggestionPreference(View v) {
        this.mCallback.dismissSuggestion(this);
    }
}

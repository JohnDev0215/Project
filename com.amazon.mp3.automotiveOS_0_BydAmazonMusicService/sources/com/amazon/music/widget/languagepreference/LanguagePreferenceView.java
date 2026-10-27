package com.amazon.music.widget.languagepreference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.InteractionType;
import com.amazon.music.languagepreference.Language;
import com.amazon.music.widget.languagepreferenceView.R$id;
import com.amazon.music.widget.languagepreferenceView.R$layout;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class LanguagePreferenceView extends LinearLayout {
    private TextView clearAllButton;
    private Context contex;
    private TextView doneButton;
    private RecyclerView languagePreferenceList;
    private LanguagePreferenceListAdapter languagePreferenceListAdapter;
    private List<Language> languages;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar loadingSpinner;

    public LanguagePreferenceView(Context context) {
        this(context, null);
    }

    public LanguagePreferenceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LanguagePreferenceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.contex = context;
        View.inflate(context, R$layout.language_selection_view, this);
        this.languagePreferenceList = (RecyclerView) findViewById(R$id.languagePreferenceList);
        this.doneButton = (TextView) findViewById(R$id.doneButton);
        this.clearAllButton = (TextView) findViewById(R$id.clearAllButton);
        this.loadingSpinner = (ProgressBar) findViewById(R$id.loading_spinner);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.contex);
        this.linearLayoutManager = linearLayoutManager;
        this.languagePreferenceList.setLayoutManager(linearLayoutManager);
    }

    public void setAvailableContentLanguage(List<Language> list, View.OnClickListener onClickListener) {
        this.languages = list;
        LanguagePreferenceListAdapter languagePreferenceListAdapter = new LanguagePreferenceListAdapter(list);
        this.languagePreferenceListAdapter = languagePreferenceListAdapter;
        this.languagePreferenceList.setAdapter(languagePreferenceListAdapter);
        this.clearAllButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.widget.languagepreference.LanguagePreferenceView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Iterator it = LanguagePreferenceView.this.languages.iterator();
                while (it.hasNext()) {
                    ((Language) it.next()).setUserPreferred(false);
                }
                LanguagePreferenceView.this.languagePreferenceListAdapter.notifyDataSetChanged();
                LanguagePreferenceView.this.sendUiClickMetric(MetricConstants.CLEAR_ALL);
            }
        });
        this.doneButton.setOnClickListener(onClickListener);
        this.loadingSpinner.setVisibility(8);
    }

    public List<Language> getSelectedLanguagePreference() {
        return this.languages;
    }

    public void sendUiClickMetric(MetricConstants metricConstants) {
        UserInteractionAppEvent.builder(metricConstants.toString()).withPageType(MetricConstants.LANGUAGE_PREFERENCE_PAGE_TYPE.toString()).withInteractionType(InteractionType.TAP).publish();
    }
}

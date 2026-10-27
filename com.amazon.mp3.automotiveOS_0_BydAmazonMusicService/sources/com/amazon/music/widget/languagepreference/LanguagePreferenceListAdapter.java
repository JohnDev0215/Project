package com.amazon.music.widget.languagepreference;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.languagepreference.Language;
import com.amazon.music.widget.languagepreferenceView.R$id;
import com.amazon.music.widget.languagepreferenceView.R$layout;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
class LanguagePreferenceListAdapter extends RecyclerView.Adapter<LanguageSelectionViewHolder> {
    List<Language> languages;

    public LanguagePreferenceListAdapter(List<Language> list) {
        new ArrayList();
        this.languages = list;
    }

    public class LanguageSelectionViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout container;
        public final TextView deviceTextView;
        public final TextView nativeTextView;
        public final ImageView thumbup;
        public final ImageView thumbupHighlight;

        public LanguageSelectionViewHolder(View view) {
            super(view);
            this.nativeTextView = (TextView) view.findViewById(R$id.nativeTextView);
            this.deviceTextView = (TextView) view.findViewById(R$id.deviceTextView);
            this.container = (RelativeLayout) view.findViewById(R$id.tile_container);
            this.thumbup = (ImageView) view.findViewById(R$id.thumup);
            this.thumbupHighlight = (ImageView) view.findViewById(R$id.thumbupHighlighted);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public LanguageSelectionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LanguageSelectionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.language_horizontal_tile, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final LanguageSelectionViewHolder languageSelectionViewHolder, int i) {
        final Language language = this.languages.get(i);
        languageSelectionViewHolder.nativeTextView.setText(language.getNativeScript());
        languageSelectionViewHolder.deviceTextView.setText(language.getDisplayName());
        if (language.getUserPreferred()) {
            languageSelectionViewHolder.thumbup.setVisibility(8);
            languageSelectionViewHolder.thumbupHighlight.setVisibility(0);
        } else {
            languageSelectionViewHolder.thumbup.setVisibility(0);
            languageSelectionViewHolder.thumbupHighlight.setVisibility(8);
        }
        languageSelectionViewHolder.container.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.widget.languagepreference.LanguagePreferenceListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LanguagePreferenceListAdapter.this.setPreferenceView(language, languageSelectionViewHolder);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.languages.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPreferenceView(Language language, LanguageSelectionViewHolder languageSelectionViewHolder) {
        if (!language.getUserPreferred()) {
            languageSelectionViewHolder.thumbup.setVisibility(8);
            languageSelectionViewHolder.thumbupHighlight.setVisibility(0);
            language.setUserPreferred(true);
        } else {
            languageSelectionViewHolder.thumbup.setVisibility(0);
            languageSelectionViewHolder.thumbupHighlight.setVisibility(8);
            language.setUserPreferred(false);
        }
    }
}

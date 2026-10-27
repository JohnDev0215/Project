package com.amazon.music.widget.eux.adapters;

import android.content.Context;
import android.view.View;
import com.amazon.music.widget.eux.EUXConfiguration;
import com.amazon.music.widget.eux.EUXLauncher;
import com.amazon.music.widget.eux.EarlyUserExperienceView;
import com.amazon.music.widget.eux.R$drawable;
import com.amazon.music.widget.eux.R$string;

/* JADX INFO: loaded from: classes4.dex */
public class EUXStationsRecommendationsAdapter implements EUXAdapter {
    private final Context context;
    private EUXLauncher euxLauncher;

    public EUXStationsRecommendationsAdapter(Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.widget.eux.adapters.EUXAdapter
    public EarlyUserExperienceView getView(EUXConfiguration eUXConfiguration) {
        this.euxLauncher = eUXConfiguration.euxLauncher;
        EarlyUserExperienceView earlyUserExperienceView = new EarlyUserExperienceView(this.context);
        earlyUserExperienceView.setHeaderText(R$string.dmusic_empty_station_recommendations_header);
        earlyUserExperienceView.setSubtextText(R$string.dmusic_empty_recommendations_subtext);
        earlyUserExperienceView.setButtonText(R$string.dmusic_button_explore_text);
        earlyUserExperienceView.setImageDrawableResource(R$drawable.eux_headphones_andante_blue);
        earlyUserExperienceView.setButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.widget.eux.adapters.EUXStationsRecommendationsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EUXStationsRecommendationsAdapter.this.euxLauncher.launchStations();
            }
        });
        return earlyUserExperienceView;
    }
}

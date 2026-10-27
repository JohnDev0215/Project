package com.amazon.music.widget.eux.adapters;

import android.content.Context;
import android.view.View;
import com.amazon.music.widget.eux.EUXConfiguration;
import com.amazon.music.widget.eux.EUXLauncher;
import com.amazon.music.widget.eux.EarlyUserExperienceView;
import com.amazon.music.widget.eux.R$drawable;
import com.amazon.music.widget.eux.R$string;

/* JADX INFO: loaded from: classes4.dex */
public class EUXRecentlyPlayedAdapter implements EUXAdapter {
    private final Context context;
    private EUXLauncher euxLauncher;

    public EUXRecentlyPlayedAdapter(Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.widget.eux.adapters.EUXAdapter
    public EarlyUserExperienceView getView(EUXConfiguration eUXConfiguration) {
        EarlyUserExperienceView earlyUserExperienceView = new EarlyUserExperienceView(this.context);
        if (eUXConfiguration == null) {
            return getEUXView(earlyUserExperienceView);
        }
        this.euxLauncher = eUXConfiguration.euxLauncher;
        earlyUserExperienceView.setImageDrawableResource(R$drawable.eux_headphones_andante_blue);
        if (eUXConfiguration.hasSubscription) {
            return getEUXViewWithBrowseNavigation(earlyUserExperienceView);
        }
        if (eUXConfiguration.isStoreSupported) {
            return getEUXViewWithStoreNavigation(earlyUserExperienceView);
        }
        return getEUXView(earlyUserExperienceView);
    }

    private EarlyUserExperienceView getEUXViewWithBrowseNavigation(EarlyUserExperienceView earlyUserExperienceView) {
        earlyUserExperienceView.setHeaderText(R$string.dmusic_empty_played_music_header);
        earlyUserExperienceView.setSubtextText(R$string.dmusic_empty_recently_played_subtext);
        earlyUserExperienceView.setButtonText(R$string.dmusic_button_explore_text);
        earlyUserExperienceView.showButton();
        earlyUserExperienceView.setButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.widget.eux.adapters.EUXRecentlyPlayedAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EUXRecentlyPlayedAdapter.this.euxLauncher.launchHome();
            }
        });
        return earlyUserExperienceView;
    }

    private EarlyUserExperienceView getEUXViewWithStoreNavigation(EarlyUserExperienceView earlyUserExperienceView) {
        earlyUserExperienceView.setHeaderText(R$string.dmusic_empty_purchased_music_header);
        earlyUserExperienceView.setSubtextText(R$string.dmusic_empty_recently_played_purchase_subtext);
        earlyUserExperienceView.setButtonText(R$string.dmusic_button_buy_text);
        earlyUserExperienceView.showButton();
        earlyUserExperienceView.setButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.widget.eux.adapters.EUXRecentlyPlayedAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EUXRecentlyPlayedAdapter.this.euxLauncher.launchStore();
            }
        });
        return earlyUserExperienceView;
    }

    private EarlyUserExperienceView getEUXView(EarlyUserExperienceView earlyUserExperienceView) {
        earlyUserExperienceView.setHeaderText(R$string.dmusic_empty_played_music_header);
        earlyUserExperienceView.setSubtextText(R$string.dmusic_empty_recently_played_subtext);
        earlyUserExperienceView.hideButton();
        return earlyUserExperienceView;
    }
}

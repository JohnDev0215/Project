package com.amazon.music.widget.eux.adapters;

import android.content.Context;
import android.view.View;
import com.amazon.music.widget.eux.EUXConfiguration;
import com.amazon.music.widget.eux.EUXLauncher;
import com.amazon.music.widget.eux.EarlyUserExperienceView;
import com.amazon.music.widget.eux.R$drawable;
import com.amazon.music.widget.eux.R$string;

/* JADX INFO: loaded from: classes4.dex */
public final class EUXAlbumAdapter implements EUXAdapter {
    private final Context context;
    private EUXLauncher euxLauncher;

    public EUXAlbumAdapter(Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.widget.eux.adapters.EUXAdapter
    public EarlyUserExperienceView getView(EUXConfiguration eUXConfiguration) {
        this.euxLauncher = eUXConfiguration.euxLauncher;
        EarlyUserExperienceView earlyUserExperienceView = new EarlyUserExperienceView(this.context);
        earlyUserExperienceView.setImageDrawableResource(R$drawable.eux_record_andante_blue);
        if (eUXConfiguration.hasSubscription) {
            return getEUXViewWithBrowseNavigation(earlyUserExperienceView);
        }
        if (eUXConfiguration.isStoreSupported) {
            return getEUXViewWithStoreNavigation(earlyUserExperienceView);
        }
        return getEUXView(earlyUserExperienceView);
    }

    private EarlyUserExperienceView getEUXView(EarlyUserExperienceView earlyUserExperienceView) {
        earlyUserExperienceView.setHeaderText(R$string.dmusic_empty_albums_header);
        earlyUserExperienceView.setSubtextText(R$string.dmusic_empty_albums_subtext);
        earlyUserExperienceView.hideButton();
        return earlyUserExperienceView;
    }

    private EarlyUserExperienceView getEUXViewWithBrowseNavigation(EarlyUserExperienceView earlyUserExperienceView) {
        earlyUserExperienceView.setHeaderText(R$string.dmusic_empty_albums_header);
        earlyUserExperienceView.setSubtextText(R$string.dmusic_empty_albums_subtext);
        earlyUserExperienceView.setButtonText(R$string.dmusic_button_explore_text);
        earlyUserExperienceView.setButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.widget.eux.adapters.EUXAlbumAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EUXAlbumAdapter.this.euxLauncher.launchHome();
            }
        });
        return earlyUserExperienceView;
    }

    private EarlyUserExperienceView getEUXViewWithStoreNavigation(EarlyUserExperienceView earlyUserExperienceView) {
        earlyUserExperienceView.setHeaderText(R$string.dmusic_empty_purchased_music_header);
        earlyUserExperienceView.setSubtextText(R$string.dmusic_empty_albums_purchase_subtext);
        earlyUserExperienceView.setButtonText(R$string.dmusic_button_buy_text);
        earlyUserExperienceView.setButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.widget.eux.adapters.EUXAlbumAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EUXAlbumAdapter.this.euxLauncher.launchStore();
            }
        });
        return earlyUserExperienceView;
    }
}

package com.amazon.music.widget.eux.adapters;

import android.content.Context;
import android.view.View;
import com.amazon.music.widget.eux.EUXConfiguration;
import com.amazon.music.widget.eux.EUXLauncher;
import com.amazon.music.widget.eux.EarlyUserExperienceView;
import com.amazon.music.widget.eux.R$drawable;
import com.amazon.music.widget.eux.R$string;

/* JADX INFO: loaded from: classes4.dex */
public class EUXOfflineContentAdapter implements EUXAdapter {
    private final Context context;
    private EUXLauncher euxLauncher;

    public EUXOfflineContentAdapter(Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.widget.eux.adapters.EUXAdapter
    public EarlyUserExperienceView getView(EUXConfiguration eUXConfiguration) {
        this.euxLauncher = eUXConfiguration.euxLauncher;
        EarlyUserExperienceView earlyUserExperienceView = new EarlyUserExperienceView(this.context);
        earlyUserExperienceView.setImageDrawableResource(R$drawable.eux_record_andante_blue);
        if (eUXConfiguration.hasMusicInLibrary) {
            return getEUXViewWithLibraryNavigation(earlyUserExperienceView);
        }
        earlyUserExperienceView.setHeaderText(R$string.dmusic_empty_music_available_to_download_header);
        if (eUXConfiguration.hasSubscription) {
            return getEUXViewWithBrowseNavigation(earlyUserExperienceView);
        }
        if (eUXConfiguration.isStoreSupported) {
            return getEUXViewWithStoreNavigation(earlyUserExperienceView);
        }
        return getEUXView(earlyUserExperienceView);
    }

    protected EarlyUserExperienceView getEUXViewWithLibraryNavigation(EarlyUserExperienceView earlyUserExperienceView) {
        earlyUserExperienceView.setHeaderText(R$string.dmusic_empty_downloaded_music_header);
        earlyUserExperienceView.setSubtextText(R$string.dmusic_empty_recently_downloaded_subtext);
        earlyUserExperienceView.setButtonText(R$string.dmusic_button_download_text);
        earlyUserExperienceView.setButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.widget.eux.adapters.EUXOfflineContentAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EUXOfflineContentAdapter.this.euxLauncher.launchLibrary();
            }
        });
        return earlyUserExperienceView;
    }

    protected EarlyUserExperienceView getEUXViewWithBrowseNavigation(EarlyUserExperienceView earlyUserExperienceView) {
        earlyUserExperienceView.setSubtextText(R$string.dmusic_empty_library_recently_downloaded_subtext);
        earlyUserExperienceView.setButtonText(R$string.dmusic_button_explore_text);
        earlyUserExperienceView.setButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.widget.eux.adapters.EUXOfflineContentAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EUXOfflineContentAdapter.this.euxLauncher.launchHome();
            }
        });
        return earlyUserExperienceView;
    }

    protected EarlyUserExperienceView getEUXViewWithStoreNavigation(EarlyUserExperienceView earlyUserExperienceView) {
        earlyUserExperienceView.setSubtextText(R$string.dmusic_empty_recently_downloaded_purchase_subtext);
        earlyUserExperienceView.setButtonText(R$string.dmusic_button_buy_text);
        earlyUserExperienceView.setButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.widget.eux.adapters.EUXOfflineContentAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EUXOfflineContentAdapter.this.euxLauncher.launchStore();
            }
        });
        return earlyUserExperienceView;
    }

    protected EarlyUserExperienceView getEUXView(EarlyUserExperienceView earlyUserExperienceView) {
        earlyUserExperienceView.setSubtextText(R$string.dmusic_empty_downloaded_music_header);
        earlyUserExperienceView.setButtonText(R$string.dmusic_empty_recently_downloaded_subtext);
        earlyUserExperienceView.hideButton();
        return earlyUserExperienceView;
    }
}

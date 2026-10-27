package com.amazon.music.widget.eux.adapters;

import android.content.Context;
import android.view.View;
import com.amazon.music.widget.eux.EUXConfiguration;
import com.amazon.music.widget.eux.EUXLauncher;
import com.amazon.music.widget.eux.EarlyUserExperienceView;
import com.amazon.music.widget.eux.R$drawable;
import com.amazon.music.widget.eux.R$string;

/* JADX INFO: loaded from: classes4.dex */
public class EUXRecentlyPurchasedAdapter implements EUXAdapter {
    private final Context context;
    private EUXLauncher euxLauncher;

    public EUXRecentlyPurchasedAdapter(Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.widget.eux.adapters.EUXAdapter
    public EarlyUserExperienceView getView(EUXConfiguration eUXConfiguration) {
        this.euxLauncher = eUXConfiguration.euxLauncher;
        EarlyUserExperienceView earlyUserExperienceView = new EarlyUserExperienceView(this.context);
        earlyUserExperienceView.setHeaderText(R$string.dmusic_empty_purchased_music_header);
        earlyUserExperienceView.setButtonText(R$string.dmusic_button_buy_text);
        earlyUserExperienceView.setImageDrawableResource(R$drawable.eux_record_andante_blue);
        earlyUserExperienceView.setButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.widget.eux.adapters.EUXRecentlyPurchasedAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EUXRecentlyPurchasedAdapter.this.euxLauncher.launchStore();
            }
        });
        if (eUXConfiguration.hasSubscription) {
            earlyUserExperienceView.setSubtextText(R$string.dmusic_empty_recently_purchased_subtext);
        } else {
            earlyUserExperienceView.setSubtextText(R$string.dmusic_empty_recently_purchased_purchase_subtext);
        }
        return earlyUserExperienceView;
    }
}

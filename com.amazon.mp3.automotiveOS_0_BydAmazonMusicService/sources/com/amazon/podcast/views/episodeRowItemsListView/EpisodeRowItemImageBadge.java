package com.amazon.podcast.views.episodeRowItemsListView;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.AccentOutlineBadgeElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.BadgeElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.RuntimeStyleSheet;

/* JADX INFO: loaded from: classes5.dex */
public class EpisodeRowItemImageBadge extends AppCompatButton {
    public EpisodeRowItemImageBadge(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(final EpisodeRowItemElement episodeRowItemElement, final MethodsDispatcher methodsDispatcher, final String ownerId) {
        if (episodeRowItemElement.getImageBadge() == null) {
            setVisibility(8);
            return;
        }
        final BadgeElement imageBadge = episodeRowItemElement.getImageBadge();
        setText(imageBadge.getText());
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), RuntimeStyleSheet.FONT_AMAZON_EMBER_BOLD_PATH));
        if (imageBadge instanceof AccentOutlineBadgeElement) {
            setBackgroundDrawable(getResources().getDrawable(R$drawable.redhood_plus_accent_bordered_badge_background));
            setTextColor(getResources().getColor(R$color.refresh_podcast_accent));
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemImageBadge.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                methodsDispatcher.dispatch(ownerId, imageBadge.getOnItemSelected());
            }
        });
        setVisibility(0);
    }
}

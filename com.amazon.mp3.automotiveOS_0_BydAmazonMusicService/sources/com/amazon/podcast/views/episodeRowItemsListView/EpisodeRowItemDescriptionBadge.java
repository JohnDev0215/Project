package com.amazon.podcast.views.episodeRowItemsListView;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.BadgeElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.UnborderedBadgeElement;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.RuntimeStyleSheet;

/* JADX INFO: loaded from: classes5.dex */
public class EpisodeRowItemDescriptionBadge extends AppCompatButton {
    public EpisodeRowItemDescriptionBadge(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(final EpisodeRowItemElement episodeRowItemElement, final MethodsDispatcher methodsDispatcher, final String ownerId) {
        if (episodeRowItemElement.getDescriptionBadge() == null) {
            setVisibility(8);
            return;
        }
        final BadgeElement descriptionBadge = episodeRowItemElement.getDescriptionBadge();
        setText(descriptionBadge.getText());
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), RuntimeStyleSheet.FONT_AMAZON_EMBER_BOLD_PATH));
        setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemDescriptionBadge.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                methodsDispatcher.dispatch(ownerId, descriptionBadge.getOnItemSelected());
            }
        });
        if (descriptionBadge instanceof UnborderedBadgeElement) {
            ((GradientDrawable) getBackground()).setStroke(0, (ColorStateList) null);
        }
        setVisibility(0);
    }
}

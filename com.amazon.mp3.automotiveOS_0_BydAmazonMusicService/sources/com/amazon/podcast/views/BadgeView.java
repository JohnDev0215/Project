package com.amazon.podcast.views;

import Podcast.Touch.BadgeElementInterface.v1_0.AccentOutlineBadgeElement;
import Podcast.Touch.BadgeElementInterface.v1_0.BadgeElement;
import Podcast.Touch.BadgeElementInterface.v1_0.BorderedBadgeElement;
import Podcast.Touch.BadgeElementInterface.v1_0.LightUnborderedBadgeElement;
import Podcast.Touch.BadgeElementInterface.v1_0.UnborderedBadgeElement;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.RuntimeStyleSheet;

/* JADX INFO: loaded from: classes5.dex */
public class BadgeView extends AppCompatButton {
    public BadgeView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void bind(final BadgeElement badgeElement, final MethodsDispatcher methodsDispatcher, final String ownerId) {
        if (badgeElement == null) {
            setVisibility(8);
            return;
        }
        String text = badgeElement.getText();
        setTextSize(0, Podcast.getRuntimeStyleSheet().getT4TextSize());
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), RuntimeStyleSheet.FONT_AMAZON_EMBER_BOLD_PATH));
        setText(text);
        if (badgeElement instanceof AccentOutlineBadgeElement) {
            setBackgroundDrawable(getResources().getDrawable(R$drawable.redhood_plus_accent_bordered_badge_background));
            setTextColor(getResources().getColor(R$color.refresh_podcast_accent));
        } else if (badgeElement instanceof UnborderedBadgeElement) {
            setBackgroundDrawable(getResources().getDrawable(R$drawable.redhood_plus_unbordered_badge_background));
            setTextColor(getResources().getColor(R$color.primary_glass_5));
        } else if (badgeElement instanceof LightUnborderedBadgeElement) {
            setBackgroundDrawable(getResources().getDrawable(R$drawable.redhood_plus_regular_badge_background));
            setTextColor(getResources().getColor(R$color.secondary));
        } else if (badgeElement instanceof BorderedBadgeElement) {
            setBackgroundDrawable(getResources().getDrawable(R$drawable.redhood_plus_bordered_badge_background));
            setTextColor(getResources().getColor(R$color.primary_glass_5));
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.BadgeView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                methodsDispatcher.dispatch(ownerId, badgeElement.getOnItemSelected());
            }
        });
        setVisibility(0);
    }
}

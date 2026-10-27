package com.amazon.podcast.views.horizontalRowItemsListView;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.BadgeElement;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$dimen;

/* JADX INFO: loaded from: classes5.dex */
public class HorizontalRowItemBadge extends AppCompatButton {
    public HorizontalRowItemBadge(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(final BadgeElement badge, final MethodsDispatcher methodsDispatcher, final String ownerId) {
        if (badge == null) {
            setVisibility(8);
            return;
        }
        setText(badge.getText());
        setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.horizontalRowItemsListView.HorizontalRowItemBadge.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                methodsDispatcher.dispatch(ownerId, badge.getOnItemSelected());
            }
        });
        setAutoSizeTextTypeUniformWithConfiguration(getResources().getDimensionPixelSize(R$dimen.podcast_text_size_6), getResources().getDimensionPixelSize(R$dimen.podcast_text_size_t4), 1, 0);
        setVisibility(0);
    }
}

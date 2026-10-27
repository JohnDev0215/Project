package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.GalleryTemplateInterface.v2_0.TrailerElement;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
final class TrailerButton extends LinearLayout {
    private EmberTextView text;

    public TrailerButton(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_about_view_trailer_button, this);
        this.text = (EmberTextView) findViewById(R$id.trailer_button_text);
    }

    public void bind(final TrailerElement element, final String ownerId, final MethodsDispatcher methodsDispatcher) {
        this.text.setText(element.getText());
        setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.TrailerButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                methodsDispatcher.dispatch(ownerId, element.getOnItemSelected());
            }
        });
    }
}

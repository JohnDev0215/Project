package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.MessageElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amazon.music.curate.R;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.curate.skyfire.views.EmberTextView;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class MessageView extends LinearLayout {
    private final CurateBootstrapProviders curateBootstrapProviders;
    private EmberTextView messageButtonText;
    private ImageView messageIcon;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private EmberTextView primaryText;
    private EmberTextView secondaryText;

    public MessageView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final CurateBootstrapProviders curateBootstrapProviders) {
        super(context);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.curateBootstrapProviders = curateBootstrapProviders;
        init();
    }

    private final void init() {
        View.inflate(getContext(), R.layout.library_message_view, this);
        this.messageIcon = (ImageView) findViewById(R.id.message_view_icon);
        this.primaryText = (EmberTextView) findViewById(R.id.message_view_primary_text);
        this.secondaryText = (EmberTextView) findViewById(R.id.message_view_secondary_text);
        this.messageButtonText = (EmberTextView) findViewById(R.id.message_view_explore_button);
    }

    public void bind(final MessageElement element) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(element.message())) {
            this.primaryText.setText(element.message());
            this.primaryText.setVisibility(0);
            sb.append(element.message());
        }
        if (StringUtils.isNotBlank(element.subMessage())) {
            this.secondaryText.setText(element.subMessage());
            sb.append(element.subMessage());
            this.secondaryText.setVisibility(0);
        }
        if (element.buttons() == null || element.buttons().isEmpty()) {
            return;
        }
        for (final TextButtonElement textButtonElement : element.buttons()) {
            this.messageButtonText.setText(textButtonElement.text());
            this.messageButtonText.setVisibility(0);
            sb.append(textButtonElement.text());
            this.messageButtonText.setContentDescription(sb.toString());
            this.messageButtonText.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.MessageView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MessageView.this.methodsDispatcher.dispatchMethods(MessageView.this.ownerId, textButtonElement.onItemSelected());
                }
            });
        }
    }
}

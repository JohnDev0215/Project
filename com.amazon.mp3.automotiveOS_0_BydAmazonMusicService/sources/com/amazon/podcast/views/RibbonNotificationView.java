package com.amazon.podcast.views;

import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonNotificationElement;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class RibbonNotificationView extends RelativeLayout {
    private ImageButton closeButton;
    private ImageView forwardButton;
    private EmberTextView messageView;
    private RelativeLayout podcastRibbonNotification;
    private ImageView podcastRibbonNotificationBackgroundLayer2;

    public RibbonNotificationView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void bind(final RibbonNotificationElement ribbonNotificationElement, final MethodsDispatcher methodsDispatcher, final String ownerId) {
        if (ribbonNotificationElement == null) {
            return;
        }
        this.messageView.setText(ribbonNotificationElement.getMessage().getText());
        this.podcastRibbonNotification.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.RibbonNotificationView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                methodsDispatcher.dispatch(ownerId, ribbonNotificationElement.getOnItemSelected());
            }
        });
        this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.RibbonNotificationView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                methodsDispatcher.dispatch(ownerId, ribbonNotificationElement.getCloseButton().getOnItemSelected());
            }
        });
        if (ribbonNotificationElement.getTimeoutSeconds() != null && ribbonNotificationElement.getTimeoutSeconds().intValue() > 0) {
            Handlers.INSTANCE.getForeground().postDelayed(new Runnable() { // from class: com.amazon.podcast.views.RibbonNotificationView.3
                @Override // java.lang.Runnable
                public void run() {
                    if (RibbonNotificationView.this.podcastRibbonNotification != null) {
                        RibbonNotificationView.this.podcastRibbonNotification.setVisibility(8);
                    }
                }
            }, TimeUnit.SECONDS.toMillis(ribbonNotificationElement.getTimeoutSeconds().intValue()));
        }
        this.forwardButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.RibbonNotificationView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                methodsDispatcher.dispatch(ownerId, ribbonNotificationElement.getOnItemSelected());
            }
        });
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_ribbon_notification_view, this);
        this.podcastRibbonNotification = (RelativeLayout) findViewById(R$id.podcast_ribbon_notification);
        this.podcastRibbonNotificationBackgroundLayer2 = (ImageView) findViewById(R$id.podcast_ribbon_notification_background_layer2);
        if (Podcast.getRuntimeStyleSheet() != null) {
            this.podcastRibbonNotificationBackgroundLayer2.setBackground(getResources().getDrawable(Podcast.getRuntimeStyleSheet().getPodcastRibbonNotificationBackgroundLayer2DrawableId()));
        }
        this.closeButton = (ImageButton) findViewById(R$id.podcast_ribbon_notification_close_button);
        this.messageView = (EmberTextView) findViewById(R$id.podcast_ribbon_notification_message);
        this.forwardButton = (ImageView) findViewById(R$id.podcast_ribbon_notification_forward_button);
    }
}

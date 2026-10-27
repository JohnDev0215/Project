package com.amazon.podcast.views.alertTemplate;

import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplate;
import Podcast.Touch.AlertTemplateInterface.v1_0.NegativeButtonElement;
import Podcast.Touch.AlertTemplateInterface.v1_0.PositiveButtonElement;
import SOACoreInterface.v1_0.Method;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.OwnerRegistration;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.TemplateCache;
import com.amazon.podcast.TemplateCacheEntry;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.views.EmberTextView;
import com.google.android.gms.common.util.CollectionUtils;
import com.squareup.picasso.Picasso;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public class AlertTemplateActivity extends AppCompatActivity {
    private static final Logger logger = LoggerFactory.getLogger("AlertTemplateActivity");
    private ImageButton close;
    private ImageView imageView;
    private MethodsDispatcher methodsDispatcher;
    private Button negativeButton;
    private List<Method> onViewed;
    private String ownerId;
    private OwnerRegistration ownerRegistration;
    private Button positiveButton;
    private RuntimeStyleSheet runtimeStyleSheet;
    private EmberTextView subTitleView;
    private AlertTemplate template;
    private EmberTextView titleView;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        String string;
        super.onCreate(savedInstanceState);
        initViews();
        Intent intent = getIntent();
        if (intent != null) {
            string = intent.getStringExtra("ownerId");
        } else {
            string = savedInstanceState != null ? savedInstanceState.getString("ownerId") : null;
        }
        if (string == null) {
            return;
        }
        bindFromCache(string);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        bindFromCache(intent.getStringExtra("ownerId"));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        String str = this.ownerId;
        if (str == null) {
            return;
        }
        outState.putString("ownerId", str);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.ownerId == null || this.template == null) {
            return;
        }
        initViews();
        bind(this.template);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        String str = this.ownerId;
        if (str == null) {
            return;
        }
        TemplateCache.INSTANCE.delete(str);
    }

    private void initViews() {
        setContentView(R$layout.podcast_alert_template_view);
        this.imageView = (ImageView) findViewById(R$id.podcast_alert_view_image);
        this.close = (ImageButton) findViewById(R$id.podcast_alert_view_close);
        this.runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        this.close.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.alertTemplate.AlertTemplateActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AlertTemplateActivity.this.finish();
            }
        });
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.podcast_alert_view_title);
        this.titleView = emberTextView;
        RuntimeStyleSheet runtimeStyleSheet = this.runtimeStyleSheet;
        if (runtimeStyleSheet != null) {
            emberTextView.setTypeface(runtimeStyleSheet.getHeadline4TypeFace());
        }
        this.subTitleView = (EmberTextView) findViewById(R$id.podcast_alert_view_subtitle);
        if (PodcastFeatureGating.IS_SONIC_RUSH.isEnabled()) {
            this.negativeButton = (Button) findViewById(R$id.refresh_podcast_alert_view_negative_button);
            this.positiveButton = (Button) findViewById(R$id.refresh_podcast_alert_view_positive_button);
        } else {
            this.negativeButton = (Button) findViewById(R$id.podcast_alert_view_negative_button);
            this.positiveButton = (Button) findViewById(R$id.podcast_alert_view_positive_button);
        }
    }

    private void bindFromCache(final String ownerId) {
        if (ownerId == null) {
            return;
        }
        TemplateCacheEntry templateCacheEntry = TemplateCache.INSTANCE.read(ownerId);
        if (templateCacheEntry == null) {
            logger.debug("cache is null for ownerId {}", ownerId);
            return;
        }
        logger.debug("cache restored for template {}", templateCacheEntry.getTemplate().getClass().getSimpleName());
        createAndBindTemplate(ownerId, (AlertTemplate) templateCacheEntry.getTemplate(), templateCacheEntry.getMethodsDispatcher(), templateCacheEntry.getOwnerRegistration());
    }

    private void createAndBindTemplate(final String ownerId, final AlertTemplate template, final MethodsDispatcher methodsDispatcher, final OwnerRegistration ownerRegistration) {
        this.ownerId = ownerId;
        this.template = template;
        this.methodsDispatcher = methodsDispatcher;
        this.ownerRegistration = ownerRegistration;
        ownerRegistration.registerOwner(ownerId);
        bind(template);
        handleOnViewed(template);
    }

    private void bind(final AlertTemplate alertTemplate) {
        String image = alertTemplate.getImage();
        if (!StringUtils.isBlank(image)) {
            if (StringUtils.isNumeric(image)) {
                bindImage(Integer.parseInt(image));
            } else {
                bindImage(alertTemplate.getImage());
            }
        }
        bindTexts(alertTemplate.getTitle(), alertTemplate.getSubtitle());
        bindPositiveButton(alertTemplate.getPositiveButton());
        bindNegativeButton(alertTemplate.getNegativeButton());
        bindCloseButton(alertTemplate.getOnDismiss());
    }

    private void bindImage(final String imageUrl) {
        Picasso.get().load(imageUrl).into(this.imageView);
    }

    private void bindImage(final int imageResourceId) {
        this.imageView.setImageResource(imageResourceId);
    }

    private void bindTexts(final String title, final String subtitle) {
        String exoplayerLocalizedException = Podcast.getPlayback().getExoplayerLocalizedException();
        if (StringUtils.isNotBlank(exoplayerLocalizedException) && exoplayerLocalizedException.contains("Cleartext")) {
            this.titleView.setText(getResources().getString(R$string.podcast_clear_text_failure_title));
            this.subTitleView.setText(getResources().getString(R$string.podcast_clear_text_failure_subtitle));
        } else {
            this.titleView.setText(title);
            this.subTitleView.setText(subtitle);
        }
    }

    private void bindPositiveButton(final PositiveButtonElement element) {
        if (element == null) {
            return;
        }
        this.positiveButton.setVisibility(0);
        this.positiveButton.setText(element.getText());
        this.positiveButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.alertTemplate.AlertTemplateActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AlertTemplateActivity.this.methodsDispatcher.dispatch(AlertTemplateActivity.this.ownerId, element.getOnItemSelected());
                AlertTemplateActivity.this.finish();
            }
        });
    }

    private void bindNegativeButton(final NegativeButtonElement element) {
        if (element == null) {
            return;
        }
        this.negativeButton.setVisibility(0);
        this.negativeButton.setText(element.getText());
        this.negativeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.alertTemplate.AlertTemplateActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AlertTemplateActivity.this.methodsDispatcher.dispatch(AlertTemplateActivity.this.ownerId, element.getOnItemSelected());
                AlertTemplateActivity.this.finish();
            }
        });
    }

    private void bindCloseButton(final List<Method> methods) {
        this.close.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.alertTemplate.AlertTemplateActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                AlertTemplateActivity.this.methodsDispatcher.dispatch(AlertTemplateActivity.this.ownerId, methods);
                AlertTemplateActivity.this.finish();
            }
        });
    }

    private void handleOnViewed(AlertTemplate template) {
        if (!CollectionUtils.isEmpty(this.onViewed) || this.methodsDispatcher == null) {
            return;
        }
        List<Method> onViewed = template.getOnViewed();
        this.onViewed = onViewed;
        this.methodsDispatcher.dispatch(this.ownerId, onViewed);
    }
}

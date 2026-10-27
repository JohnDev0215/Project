package com.amazon.podcast.views.stackedButtonsAlertTemplate;

import Podcast.Touch.AlertTemplateInterface.v1_0.NegativeButtonElement;
import Podcast.Touch.AlertTemplateInterface.v1_0.PositiveButtonElement;
import Podcast.Touch.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplate;
import SOACoreInterface.v1_0.Method;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.OwnerRegistration;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.TemplateCache;
import com.amazon.podcast.TemplateCacheEntry;
import com.amazon.podcast.views.EmberTextView;
import com.google.android.gms.common.util.CollectionUtils;
import com.squareup.picasso.Picasso;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class StackedButtonsAlertTemplateActivity extends AppCompatActivity {
    private static final Logger logger = LoggerFactory.getLogger("StackedButtonsAlertTemplateActivity");
    private ImageButton close;
    private ImageView imageView;
    private MethodsDispatcher methodsDispatcher;
    private EmberTextView negativeButton;
    private List<Method> onViewed;
    private String ownerId;
    private OwnerRegistration ownerRegistration;
    private EmberTextView positiveButton;
    private EmberTextView subTitleView;
    private StackedButtonsAlertTemplate template;
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
        OwnerRegistration ownerRegistration = this.ownerRegistration;
        if (ownerRegistration == null) {
            return;
        }
        ownerRegistration.deregisterOwner(this.ownerId);
    }

    private void initViews() {
        setContentView(R$layout.podcast_stacked_buttons_alert_template_view);
        this.imageView = (ImageView) findViewById(R$id.podcast_stacked_alert_view_image);
        ImageButton imageButton = (ImageButton) findViewById(R$id.podcast_stacked_alert_view_close);
        this.close = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.stackedButtonsAlertTemplate.StackedButtonsAlertTemplateActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                StackedButtonsAlertTemplateActivity.this.finish();
            }
        });
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.podcast_stacked_alert_view_title);
        this.titleView = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.subTitleView = (EmberTextView) findViewById(R$id.podcast_stacked_alert_view_subtitle);
        this.negativeButton = (EmberTextView) findViewById(R$id.podcast_stacked_alert_view_negative_button);
        EmberTextView emberTextView2 = (EmberTextView) findViewById(R$id.podcast_stacked_alert_view_positive_button);
        this.positiveButton = emberTextView2;
        emberTextView2.setBackground(getResources().getDrawable(Podcast.getRuntimeStyleSheet().getAccentRoundedCornersDrawableId()));
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
        createAndBindTemplate(ownerId, (StackedButtonsAlertTemplate) templateCacheEntry.getTemplate(), templateCacheEntry.getMethodsDispatcher(), templateCacheEntry.getOwnerRegistration());
    }

    private void createAndBindTemplate(final String ownerId, final StackedButtonsAlertTemplate template, final MethodsDispatcher methodsDispatcher, final OwnerRegistration ownerRegistration) {
        this.ownerId = ownerId;
        this.template = template;
        this.methodsDispatcher = methodsDispatcher;
        this.ownerRegistration = ownerRegistration;
        ownerRegistration.registerOwner(ownerId);
        bind(template);
        handleOnViewed(template);
    }

    private void bind(final StackedButtonsAlertTemplate alertTemplate) {
        bindImage(alertTemplate.getImage());
        bindTexts(alertTemplate.getTitle(), alertTemplate.getSubtitle());
        bindPositiveButton(alertTemplate.getPositiveButton());
        bindNegativeButton(alertTemplate.getNegativeButton());
        bindCloseButton();
    }

    private void bindImage(final String image) {
        if (StringUtils.isBlank(image)) {
            return;
        }
        if (StringUtils.isNumeric(image)) {
            bindImage(Integer.parseInt(image));
        } else if (URLUtil.isValidUrl(image)) {
            Picasso.get().load(image).into(this.imageView);
        } else {
            bindImage(getResources().getIdentifier(image, "drawable", getPackageName()));
        }
    }

    private void bindImage(final int imageResourceId) {
        if (imageResourceId <= 0) {
            return;
        }
        this.imageView.setImageResource(imageResourceId);
    }

    private void bindTexts(final String title, final String subtitle) {
        this.titleView.setText(title);
        this.subTitleView.setText(subtitle);
    }

    private void bindPositiveButton(final PositiveButtonElement element) {
        if (element == null) {
            return;
        }
        this.positiveButton.setText(element.getText());
        this.positiveButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.stackedButtonsAlertTemplate.StackedButtonsAlertTemplateActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                StackedButtonsAlertTemplateActivity.this.methodsDispatcher.dispatch(StackedButtonsAlertTemplateActivity.this.ownerId, element.getOnItemSelected());
                StackedButtonsAlertTemplateActivity.this.finish();
            }
        });
    }

    private void bindNegativeButton(final NegativeButtonElement element) {
        if (element == null) {
            return;
        }
        this.negativeButton.setVisibility(0);
        this.negativeButton.setText(element.getText());
        this.negativeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.stackedButtonsAlertTemplate.StackedButtonsAlertTemplateActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                StackedButtonsAlertTemplateActivity.this.methodsDispatcher.dispatch(StackedButtonsAlertTemplateActivity.this.ownerId, element.getOnItemSelected());
                StackedButtonsAlertTemplateActivity.this.finish();
            }
        });
    }

    private void bindCloseButton() {
        this.close.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.stackedButtonsAlertTemplate.StackedButtonsAlertTemplateActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                StackedButtonsAlertTemplateActivity.this.methodsDispatcher.dispatch(StackedButtonsAlertTemplateActivity.this.ownerId, StackedButtonsAlertTemplateActivity.this.template.getOnDismiss());
                StackedButtonsAlertTemplateActivity.this.finish();
            }
        });
    }

    private void handleOnViewed(final StackedButtonsAlertTemplate template) {
        if (!CollectionUtils.isEmpty(this.onViewed) || this.methodsDispatcher == null) {
            return;
        }
        List<Method> onViewed = template.getOnViewed();
        this.onViewed = onViewed;
        this.methodsDispatcher.dispatch(this.ownerId, onViewed);
    }
}

package com.amazon.music.explore.activity;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.ActionButtonElement;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.DisplayGenericErrorMethod;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.LiveStreamPlayerTemplate;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.OverflowMenuElement;
import Touch.WidgetsInterface.v1_0.CallToActionElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import com.amazon.music.explore.R;
import com.amazon.music.explore.metrics.WatchedLiveStreamFlexEventEmitter;
import com.amazon.music.explore.providers.ExplicitFilterProvider;
import com.amazon.music.explore.views.livestream.LiveStreamVideoView;
import com.amazon.music.explore.widgets.views.CallToActionView;
import com.amazon.music.resources.R$string;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class LiveStreamActivity extends BaseExploreActivity<LiveStreamPlayerTemplate> implements LiveStreamVideoView.FullScreenCallback {
    private View errorView;
    private WatchedLiveStreamFlexEventEmitter flexEventEmitter;
    private View fullScreenView;
    private Menu menu;
    private int originalOrientation;
    private int originalSystemUiVisibility;
    private Toolbar toolbar;
    private LiveStreamVideoView videoView;
    private LinearLayout widgetsLinearLayout;

    @Override // com.amazon.music.explore.activity.BaseExploreActivity
    protected boolean shouldRunInitialMethodsOnResume() {
        return false;
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, com.amazon.music.explore.skyfire.containers.StandaloneContainerActivity
    public void createAndBindTemplate(String str, LiveStreamPlayerTemplate liveStreamPlayerTemplate) {
        super.createAndBindTemplate(str, liveStreamPlayerTemplate);
        bind(liveStreamPlayerTemplate);
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, com.amazon.music.explore.skyfire.containers.StandaloneContainerActivity
    public void bindTemplate(String str, LiveStreamPlayerTemplate liveStreamPlayerTemplate) {
        super.bindTemplate(str, liveStreamPlayerTemplate);
        bind(liveStreamPlayerTemplate);
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.live_stream_view);
        this.videoView = (LiveStreamVideoView) findViewById(R.id.video_view);
        this.widgetsLinearLayout = (LinearLayout) findViewById(R.id.widgets_linear_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.toolbar.setNavigationIcon(R.drawable.ic_close);
        this.toolbar.setNavigationContentDescription("Close");
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.activity.LiveStreamActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0(view);
            }
        });
        this.videoView.setFullScreenCallback(this);
        this.videoView.setDebug(this.buildInfoProvider.getIsDebug());
        this.errorView = findViewById(R.id.error_view);
        hideErrorView();
        keepScreenAlive();
        this.flexEventEmitter = new WatchedLiveStreamFlexEventEmitter(this.metricsProvider);
        getLifecycle().addObserver(this.flexEventEmitter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        onBackPressed();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        LiveStreamPlayerTemplate template = getTemplate();
        if (i == 108 && menu != null && template != null) {
            getMethodsDispatcher().dispatchMethods(getOwnerId(), template.titleBar().overflowMenu().onItemSelected());
        }
        return super.onMenuOpened(i, menu);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        this.videoView.onPause();
        super.onPause();
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        if (this.explicitFilterProvider.isExplicitContentFiltered()) {
            openExplicitFilterDialog();
        } else {
            init();
            this.videoView.onResume();
            this.wakeWordProvider.pauseWakeWordDetection();
        }
        super.onResume();
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.videoView.destroy();
        this.videoView = null;
        super.onDestroy();
    }

    @Override // com.amazon.music.explore.views.livestream.LiveStreamVideoView.FullScreenCallback
    @SuppressLint({"SourceLockedOrientationActivity"})
    public void onShowFullScreen(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        if (this.fullScreenView != null) {
            onHideFullScreen();
            return;
        }
        this.fullScreenView = view;
        this.originalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
        this.originalOrientation = getRequestedOrientation();
        this.toolbar.setVisibility(8);
        this.widgetsLinearLayout.setVisibility(8);
        setRequestedOrientation(4);
        ((FrameLayout) getWindow().getDecorView()).addView(this.fullScreenView, new FrameLayout.LayoutParams(-1, -1));
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    @Override // com.amazon.music.explore.views.livestream.LiveStreamVideoView.FullScreenCallback
    public void onHideFullScreen() {
        ((FrameLayout) getWindow().getDecorView()).removeView(this.fullScreenView);
        this.fullScreenView = null;
        this.toolbar.setVisibility(0);
        this.widgetsLinearLayout.setVisibility(0);
        getWindow().getDecorView().setSystemUiVisibility(this.originalSystemUiVisibility);
        setRequestedOrientation(this.originalOrientation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init() {
        runInitialMethods();
    }

    private void bind(LiveStreamPlayerTemplate liveStreamPlayerTemplate) {
        this.videoView.bindWithUrl(liveStreamPlayerTemplate.videoAndChat().url());
        String strTitle = liveStreamPlayerTemplate.titleBar().title();
        if (StringUtils.isEmpty(strTitle)) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } else {
            this.toolbar.setTitle(strTitle);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
        onPrepareOptionsMenu(this.menu);
        this.widgetsLinearLayout.removeAllViews();
        for (WidgetElement widgetElement : liveStreamPlayerTemplate.widgets()) {
            if (widgetElement instanceof CallToActionElement) {
                CallToActionView callToActionView = new CallToActionView(getApplicationContext(), getMethodsDispatcher(), getOwnerId());
                this.widgetsLinearLayout.addView(callToActionView);
                callToActionView.bind((CallToActionElement) widgetElement);
            }
        }
        hideErrorView();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        this.menu = menu;
        getMenuInflater().inflate(R.menu.empty_context, menu);
        onPrepareOptionsMenu(menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu == null) {
            return super.onPrepareOptionsMenu(menu);
        }
        menu.clear();
        LiveStreamPlayerTemplate template = getTemplate();
        if (template == null) {
            return false;
        }
        OverflowMenuElement overflowMenuElementOverflowMenu = template.titleBar().overflowMenu();
        for (int i = 0; i < overflowMenuElementOverflowMenu.actions().size(); i++) {
            final ActionButtonElement actionButtonElement = overflowMenuElementOverflowMenu.actions().get(i);
            menu.add(0, i, 0, actionButtonElement.text()).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.amazon.music.explore.activity.LiveStreamActivity$$ExternalSyntheticLambda1
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    return this.f$0.lambda$onPrepareOptionsMenu$1(actionButtonElement, menuItem);
                }
            });
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onPrepareOptionsMenu$1(ActionButtonElement actionButtonElement, MenuItem menuItem) {
        getMethodsDispatcher().dispatchMethods(getOwnerId(), actionButtonElement.onItemSelected());
        return false;
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, com.amazon.music.explore.skyfire.containers.StandaloneContainerActivity
    public void handleTemplateMethod(String str, Method method) {
        if (method instanceof DisplayGenericErrorMethod) {
            displayErrorView();
        } else {
            super.handleTemplateMethod(str, method);
        }
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity
    protected void handleExternalAction(TriggerExternalActionMethod triggerExternalActionMethod) {
        if (Methods.setWatchedLiveStreamFlexEventData.equals(triggerExternalActionMethod.name())) {
            if (this.flexEventEmitter == null) {
                return;
            }
            String str = triggerExternalActionMethod.context().get("ARTIST_ASIN");
            if (str != null) {
                this.flexEventEmitter.setArtistAsin(str);
            }
            String str2 = triggerExternalActionMethod.context().get("LIVE_STREAM_ID");
            if (str2 != null) {
                this.flexEventEmitter.setLiveStreamId(str2);
                return;
            }
            return;
        }
        super.handleExternalAction(triggerExternalActionMethod);
    }

    private void keepScreenAlive() {
        getWindow().addFlags(128);
    }

    private void openExplicitFilterDialog() {
        Resources resources = getResources();
        this.explicitFilterProvider.openBlockingExplicitFilterDialog(this, new ExplicitFilterProvider.ExplicitFilterDialogCallback() { // from class: com.amazon.music.explore.activity.LiveStreamActivity.1
            @Override // com.amazon.music.explore.providers.ExplicitFilterProvider.ExplicitFilterDialogCallback
            public void onDisabled() {
                LiveStreamActivity.this.init();
            }

            @Override // com.amazon.music.explore.providers.ExplicitFilterProvider.ExplicitFilterDialogCallback
            public void onEnabled() {
                LiveStreamActivity.this.finish();
            }
        }, resources.getString(R$string.dmusic_explicit_filter_unblock_content_title), resources.getString(R$string.dmusic_explicit_filter_unblock_livestream_message));
    }

    private void displayErrorView() {
        this.errorView.setVisibility(0);
        this.videoView.setVisibility(8);
        this.widgetsLinearLayout.setVisibility(8);
    }

    private void hideErrorView() {
        this.errorView.setVisibility(8);
        this.videoView.setVisibility(0);
        this.widgetsLinearLayout.setVisibility(0);
    }
}

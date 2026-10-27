package com.amazon.music.explore.activity;

import CoreInterface.v1_0.Method;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.DisplayGenericErrorMethod;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.LiveStreamPlayerTemplate;
import Touch.WidgetsInterface.v1_0.CallToActionElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import com.amazon.music.explore.R;
import com.amazon.music.explore.providers.ExplicitFilterProvider;
import com.amazon.music.explore.views.amp.AmpPlaybackWebView;
import com.amazon.music.explore.widgets.views.CallToActionView;
import com.amazon.music.resources.R$string;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: AmpPlaybackActivity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002H\u0002J\u0018\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u0002H\u0016J\u0018\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u0002H\u0016J\b\u0010\u001f\u001a\u00020\u0019H\u0002J\u0018\u0010 \u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0019H\u0002J\u0012\u0010$\u001a\u00020\u00192\b\u0010%\u001a\u0004\u0018\u00010&H\u0017J\b\u0010'\u001a\u00020\u0019H\u0016J\b\u0010(\u001a\u00020\u0019H\u0016J\b\u0010)\u001a\u00020\u0019H\u0002J\b\u0010*\u001a\u00020+H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006,"}, d2 = {"Lcom/amazon/music/explore/activity/AmpPlaybackActivity;", "Lcom/amazon/music/explore/activity/BaseExploreActivity;", "LTouch/LiveStreamPlayerTemplateInterface/v1_0/LiveStreamPlayerTemplate;", "()V", "errorView", "Landroid/view/View;", "getErrorView", "()Landroid/view/View;", "setErrorView", "(Landroid/view/View;)V", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "getToolbar", "()Landroidx/appcompat/widget/Toolbar;", "setToolbar", "(Landroidx/appcompat/widget/Toolbar;)V", "webView", "Lcom/amazon/music/explore/views/amp/AmpPlaybackWebView;", "widgetsLinearLayout", "Landroid/widget/LinearLayout;", "getWidgetsLinearLayout", "()Landroid/widget/LinearLayout;", "setWidgetsLinearLayout", "(Landroid/widget/LinearLayout;)V", "bind", "", "template", "bindTemplate", "ownerId", "", "createAndBindTemplate", "displayErrorView", "handleTemplateMethod", "method", "LCoreInterface/v1_0/Method;", "hideErrorView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "openExplicitFilterDialog", "shouldRunInitialMethodsOnResume", "", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AmpPlaybackActivity extends BaseExploreActivity<LiveStreamPlayerTemplate> {
    public View errorView;
    public Toolbar toolbar;
    private AmpPlaybackWebView webView;
    public LinearLayout widgetsLinearLayout;

    @Override // com.amazon.music.explore.activity.BaseExploreActivity
    protected boolean shouldRunInitialMethodsOnResume() {
        return false;
    }

    public final LinearLayout getWidgetsLinearLayout() {
        LinearLayout linearLayout = this.widgetsLinearLayout;
        if (linearLayout != null) {
            return linearLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("widgetsLinearLayout");
        return null;
    }

    public final void setWidgetsLinearLayout(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.widgetsLinearLayout = linearLayout;
    }

    public final Toolbar getToolbar() {
        Toolbar toolbar = this.toolbar;
        if (toolbar != null) {
            return toolbar;
        }
        Intrinsics.throwUninitializedPropertyAccessException("toolbar");
        return null;
    }

    public final void setToolbar(Toolbar toolbar) {
        Intrinsics.checkNotNullParameter(toolbar, "<set-?>");
        this.toolbar = toolbar;
    }

    public final View getErrorView() {
        View view = this.errorView;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("errorView");
        return null;
    }

    public final void setErrorView(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.errorView = view;
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, com.amazon.music.explore.skyfire.containers.StandaloneContainerActivity
    public void createAndBindTemplate(String ownerId, LiveStreamPlayerTemplate template) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(template, "template");
        super.createAndBindTemplate(ownerId, template);
        bind(template);
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, com.amazon.music.explore.skyfire.containers.StandaloneContainerActivity
    public void bindTemplate(String ownerId, LiveStreamPlayerTemplate template) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(template, "template");
        super.bindTemplate(ownerId, template);
        bind(template);
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amp_playback_web_view);
        this.webView = (AmpPlaybackWebView) findViewById(R.id.web_view);
        View viewFindViewById = findViewById(R.id.widgets_linear_layout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.widgets_linear_layout)");
        setWidgetsLinearLayout((LinearLayout) viewFindViewById);
        View viewFindViewById2 = findViewById(R.id.toolbar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.toolbar)");
        setToolbar((Toolbar) viewFindViewById2);
        setSupportActionBar(getToolbar());
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        getToolbar().setNavigationIcon(R.drawable.ic_close);
        getToolbar().setNavigationContentDescription("Close");
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.activity.AmpPlaybackActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AmpPlaybackActivity.m1998onCreate$lambda0(this.f$0, view);
            }
        });
        AmpPlaybackWebView ampPlaybackWebView = this.webView;
        if (ampPlaybackWebView != null) {
            ampPlaybackWebView.setDebug(this.buildInfoProvider.getIsDebug());
        }
        View viewFindViewById3 = findViewById(R.id.error_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(R.id.error_view)");
        setErrorView(viewFindViewById3);
        hideErrorView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m1998onCreate$lambda0(AmpPlaybackActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        AmpPlaybackWebView ampPlaybackWebView = this.webView;
        if (ampPlaybackWebView != null) {
            ampPlaybackWebView.destroy();
        }
        this.webView = null;
        super.onDestroy();
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        if (this.explicitFilterProvider.isExplicitContentFiltered()) {
            openExplicitFilterDialog();
        } else {
            runInitialMethods();
        }
        super.onResume();
    }

    private final void openExplicitFilterDialog() {
        String string = getResources().getString(R$string.dmusic_explicit_filter_unblock_content_title_amp);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(\n   …ntent_title_amp\n        )");
        String string2 = getResources().getString(R$string.dmusic_explicit_filter_unblock_message_amp);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …ock_message_amp\n        )");
        this.explicitFilterProvider.openBlockingExplicitFilterDialog(this, new ExplicitFilterProvider.ExplicitFilterDialogCallback() { // from class: com.amazon.music.explore.activity.AmpPlaybackActivity.openExplicitFilterDialog.1
            @Override // com.amazon.music.explore.providers.ExplicitFilterProvider.ExplicitFilterDialogCallback
            public void onDisabled() {
                AmpPlaybackActivity.this.runInitialMethods();
            }

            @Override // com.amazon.music.explore.providers.ExplicitFilterProvider.ExplicitFilterDialogCallback
            public void onEnabled() {
                AmpPlaybackActivity.this.finish();
            }
        }, string, string2);
    }

    private final void bind(LiveStreamPlayerTemplate template) {
        if (template == null) {
            return;
        }
        String url = template.videoAndChat().url();
        AmpPlaybackWebView ampPlaybackWebView = this.webView;
        if (ampPlaybackWebView != null) {
            Intrinsics.checkNotNullExpressionValue(url, "url");
            ampPlaybackWebView.bindWithUrl(url);
        }
        String strTitle = template.titleBar().title();
        Intrinsics.checkNotNullExpressionValue(strTitle, "template.titleBar().title()");
        if (StringsKt__StringsJVMKt.isBlank(strTitle)) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayShowTitleEnabled(false);
            }
        } else {
            getToolbar().setTitle(strTitle);
            ActionBar supportActionBar2 = getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayShowTitleEnabled(true);
            }
        }
        getWidgetsLinearLayout().removeAllViews();
        List<WidgetElement> listWidgets = template.widgets();
        Intrinsics.checkNotNullExpressionValue(listWidgets, "template.widgets()");
        ArrayList<CallToActionElement> arrayList = new ArrayList();
        for (Object obj : listWidgets) {
            if (obj instanceof CallToActionElement) {
                arrayList.add(obj);
            }
        }
        for (CallToActionElement callToActionElement : arrayList) {
            CallToActionView callToActionView = new CallToActionView(getApplicationContext(), getMethodsDispatcher(), getOwnerId());
            callToActionView.bind(callToActionElement);
            getWidgetsLinearLayout().addView(callToActionView);
        }
        hideErrorView();
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, com.amazon.music.explore.skyfire.containers.StandaloneContainerActivity
    public void handleTemplateMethod(String ownerId, Method method) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(method, "method");
        if (method instanceof DisplayGenericErrorMethod) {
            displayErrorView();
        } else {
            super.handleTemplateMethod(ownerId, method);
        }
    }

    private final void hideErrorView() {
        getErrorView().setVisibility(8);
        AmpPlaybackWebView ampPlaybackWebView = this.webView;
        if (ampPlaybackWebView != null) {
            ampPlaybackWebView.setVisibility(0);
        }
        getWidgetsLinearLayout().setVisibility(0);
    }

    private final void displayErrorView() {
        getErrorView().setVisibility(0);
        AmpPlaybackWebView ampPlaybackWebView = this.webView;
        if (ampPlaybackWebView != null) {
            ampPlaybackWebView.setVisibility(8);
        }
        getWidgetsLinearLayout().setVisibility(8);
    }
}

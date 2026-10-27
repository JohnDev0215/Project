package com.amazon.mp3.auto.signin.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.Fragment;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: compiled from: AutomotiveLegalPageFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b$\u0010%B\u0011\b\u0016\u0012\u0006\u0010&\u001a\u00020\u000e¢\u0006\u0004\b$\u0010\u0014J&\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016J\u001a\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016R$\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u00020\b8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\"\u0010\u0018\u001a\u00020\u00178\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0018\u0010\"\u001a\u0004\u0018\u00010!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006'"}, d2 = {"Lcom/amazon/mp3/auto/signin/fragment/AutomotiveLegalPageFragment;", "Landroidx/fragment/app/Fragment;", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "Landroid/view/View;", "onCreateView", "view", "", "onViewCreated", "onDestroyView", "Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;", "parent", "Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;", "getParent", "()Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;", "setParent", "(Lcom/amazon/mp3/auto/signin/AutomotiveSignInFlowLauncherActivity;)V", "root", "Landroid/view/View;", "Landroid/webkit/WebView;", "legalPageWebView", "Landroid/webkit/WebView;", "getLegalPageWebView", "()Landroid/webkit/WebView;", "setLegalPageWebView", "(Landroid/webkit/WebView;)V", "Lcom/amazon/ui/foundations/views/BaseButton;", "backButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "Lrx/Subscription;", "subscription", "Lrx/Subscription;", "<init>", "()V", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AutomotiveLegalPageFragment extends Fragment {
    private BaseButton backButton;
    public WebView legalPageWebView;
    private AutomotiveSignInFlowLauncherActivity parent;
    private View root;
    private Subscription subscription;

    public AutomotiveLegalPageFragment() {
    }

    public AutomotiveLegalPageFragment(AutomotiveSignInFlowLauncherActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.parent = activity;
    }

    public final WebView getLegalPageWebView() {
        WebView webView = this.legalPageWebView;
        if (webView != null) {
            return webView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("legalPageWebView");
        return null;
    }

    public final void setLegalPageWebView(WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<set-?>");
        this.legalPageWebView = webView;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.automotive_legal_page, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…l_page, container, false)");
        this.root = viewInflate;
        if (viewInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("root");
            viewInflate = null;
        }
        View viewFindViewById = viewInflate.findViewById(R.id.legal_page);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.legal_page)");
        setLegalPageWebView((WebView) viewFindViewById);
        View view = this.root;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("root");
            view = null;
        }
        this.backButton = (BaseButton) view.findViewById(R.id.sign_in_back_button);
        this.subscription = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveLegalPageFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AutomotiveLegalPageFragment.m399onCreateView$lambda1(this.f$0, (StyleSheet) obj);
            }
        });
        getLegalPageWebView().getSettings().setJavaScriptEnabled(true);
        WebSettings settings = getLegalPageWebView().getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "legalPageWebView.settings");
        settings.setDomStorageEnabled(true);
        getLegalPageWebView().setWebViewClient(new WebViewClient() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveLegalPageFragment.onCreateView.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                Intrinsics.checkNotNullParameter(webView, "webView");
                Intrinsics.checkNotNullParameter(webResourceRequest, "webResourceRequest");
                Uri uri = Uri.parse(webView.getOriginalUrl());
                Intrinsics.checkNotNullExpressionValue(uri, "parse(webView.originalUrl)");
                return !uri.equals(webResourceRequest.getUrl());
            }
        });
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this.parent;
        if (automotiveSignInFlowLauncherActivity != null) {
            AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            if (!(interfaceProvider instanceof ConfigurationProvider)) {
                interfaceProvider = null;
            }
            ConfigurationProvider configurationProvider = (ConfigurationProvider) interfaceProvider;
            String legalPoliciesUri = automotiveSignInFlowLauncherActivity.getLegalPoliciesUri(configurationProvider instanceof Configuration ? (Configuration) configurationProvider : null);
            if (legalPoliciesUri != null) {
                getLegalPageWebView().loadUrl(legalPoliciesUri);
            }
        }
        View view2 = this.root;
        if (view2 != null) {
            return view2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("root");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-1, reason: not valid java name */
    public static final void m399onCreateView$lambda1(AutomotiveLegalPageFragment this$0, StyleSheet styleSheet) {
        BaseButton.StyleBuilder buttonBuilder;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseButton baseButton = this$0.backButton;
        if (baseButton == null || (buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.OUTLINE)) == null) {
            return;
        }
        String string = this$0.getString(R.string.dmusic_automotive_back_sign_in_button);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusi…tive_back_sign_in_button)");
        BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
        if (styleBuilderWithText == null) {
            return;
        }
        styleBuilderWithText.applyStyle(baseButton);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        BaseButton baseButton = this.backButton;
        if (baseButton != null) {
            baseButton.setEnabled(true);
        }
        BaseButton baseButton2 = this.backButton;
        if (baseButton2 == null) {
            return;
        }
        baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.signin.fragment.AutomotiveLegalPageFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AutomotiveLegalPageFragment.m400onViewCreated$lambda3(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-3, reason: not valid java name */
    public static final void m400onViewCreated$lambda3(AutomotiveLegalPageFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutomotiveSignInFlowLauncherActivity automotiveSignInFlowLauncherActivity = this$0.parent;
        if (automotiveSignInFlowLauncherActivity == null) {
            return;
        }
        automotiveSignInFlowLauncherActivity.switchFragment(this$0, automotiveSignInFlowLauncherActivity == null ? null : automotiveSignInFlowLauncherActivity.getCodeBasedSignInFragment());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Subscription subscription = this.subscription;
        if (subscription == null) {
            return;
        }
        subscription.unsubscribe();
    }
}

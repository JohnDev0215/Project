package com.amazon.mp3.dialog.fragment;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.client.activity.LauncherActivity;
import com.amazon.mp3.language.preference.AppDisplayLanguageSetting;
import com.amazon.mp3.language.preference.locale.AppLocales;
import com.amazon.mp3.language.preference.persistence.RetailLopSyncManager;
import com.amazon.mp3.language.preference.util.LocaleManager;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.applocale.LocaleChangedEvent;
import com.amazon.music.metrics.mts.event.definition.applocale.RetailLanguageSyncActionEvent;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: RetailLOPFtuDialogFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 B2\u00020\u0001:\u0001BB\u0007¢\u0006\u0004\b@\u0010AJ\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0002J\b\u0010\b\u001a\u00020\u0004H\u0002J\b\u0010\n\u001a\u00020\tH\u0002J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002J4\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0014\u001a\u00020\tH\u0002J*\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tH\u0002J8\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\t2\b\u0010\u001a\u001a\u0004\u0018\u00010\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\u001f\u001a\u00020\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0016J&\u0010%\u001a\u0004\u0018\u00010$2\u0006\u0010!\u001a\u00020 2\b\u0010#\u001a\u0004\u0018\u00010\"2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010(\u001a\u00020\u00042\u0006\u0010'\u001a\u00020&H\u0016R$\u0010)\u001a\u0004\u0018\u00010$8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R$\u00100\u001a\u0004\u0018\u00010/8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u00106R\"\u00107\u001a\u00020\t8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b7\u00106\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\"\u0010\u0018\u001a\u00020\t8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0018\u00106\u001a\u0004\b<\u00109\"\u0004\b=\u0010;R\"\u0010\u0019\u001a\u00020\t8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0019\u00106\u001a\u0004\b>\u00109\"\u0004\b?\u0010;¨\u0006C"}, d2 = {"Lcom/amazon/mp3/dialog/fragment/RetailLOPFtuDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "Lcom/amazon/ui/foundations/views/BaseButton;", "acceptButton", "", "initAcceptButton", "declineButton", "initDeclineButton", "setBackground", "", "getDeviceLanguage", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "sendUiClickEventMetric", Splash.PARAMS_LOCALE, "appLocale", "previousAppLocale", "retailLocale", "preferenceAction", "sendRetailLanguageSyncActionEvent", "sendLocaleChangedEvent", "flexEventName", "bootstrapLanguage", "deviceOSLanguage", "retailLanguageOfPreference", "appDefaultedLanguage", "sendUiFlexEvent", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "Landroid/content/Context;", "context", "onAttach", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "Lcom/amazon/mp3/language/preference/persistence/RetailLopSyncManager;", "retailLopSyncManager", "Lcom/amazon/mp3/language/preference/persistence/RetailLopSyncManager;", "getRetailLopSyncManager", "()Lcom/amazon/mp3/language/preference/persistence/RetailLopSyncManager;", "setRetailLopSyncManager", "(Lcom/amazon/mp3/language/preference/persistence/RetailLopSyncManager;)V", "Ljava/lang/String;", "customerId", "getCustomerId", "()Ljava/lang/String;", "setCustomerId", "(Ljava/lang/String;)V", "getBootstrapLanguage", "setBootstrapLanguage", "getDeviceOSLanguage", "setDeviceOSLanguage", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RetailLOPFtuDialogFragment extends DialogFragment {
    public String bootstrapLanguage;
    public String customerId;
    public String deviceOSLanguage;
    private String retailLanguageOfPreference;
    private RetailLopSyncManager retailLopSyncManager;
    private View rootView;

    public final String getCustomerId() {
        String str = this.customerId;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("customerId");
        return null;
    }

    public final void setCustomerId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.customerId = str;
    }

    public final String getBootstrapLanguage() {
        String str = this.bootstrapLanguage;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bootstrapLanguage");
        return null;
    }

    public final void setBootstrapLanguage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bootstrapLanguage = str;
    }

    public final String getDeviceOSLanguage() {
        String str = this.deviceOSLanguage;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("deviceOSLanguage");
        return null;
    }

    public final void setDeviceOSLanguage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceOSLanguage = str;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        Context applicationContext;
        super.onCreate(savedInstanceState);
        String customerId = AccountManagerSingleton.get().getUser().getCustomerId();
        Intrinsics.checkNotNullExpressionValue(customerId, "get().user.customerId");
        setCustomerId(customerId);
        RetailLopSyncManager retailLopSyncManager = this.retailLopSyncManager;
        this.retailLanguageOfPreference = retailLopSyncManager == null ? null : retailLopSyncManager.fetchRetailLanguageOfPreference(getCustomerId());
        FragmentActivity activity = getActivity();
        if (activity != null && (applicationContext = activity.getApplicationContext()) != null) {
            setBootstrapLanguage(AppDisplayLanguageSetting.INSTANCE.getDisplayLanguage(applicationContext));
            setDeviceOSLanguage(getDeviceLanguage());
        }
        setStyle(0, R.style.Theme.Black.NoTitleBar.Fullscreen);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.rootView = inflater.inflate(com.amazon.mp3.automotiveOS.R.layout.retail_lop_ftu_layout, container);
        Resources resources = getResources();
        String str = this.retailLanguageOfPreference;
        Context context = getContext();
        int identifier = resources.getIdentifier(str, "string", context == null ? null : context.getPackageName());
        View view = this.rootView;
        TextView textView = view == null ? null : (TextView) view.findViewById(com.amazon.mp3.automotiveOS.R.id.description);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(com.amazon.mp3.automotiveOS.R.string.retail_lop_initial_prompt_description);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.retai…itial_prompt_description)");
        String str2 = String.format(string, Arrays.copyOf(new Object[]{getString(identifier)}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
        if (textView != null) {
            textView.setText(str2);
        }
        View view2 = this.rootView;
        TextView textView2 = view2 == null ? null : (TextView) view2.findViewById(com.amazon.mp3.automotiveOS.R.id.languagePickerInfo);
        String string2 = getString(com.amazon.mp3.automotiveOS.R.string.language_selector_information_message);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.langu…ctor_information_message)");
        String str3 = String.format(string2, Arrays.copyOf(new Object[]{getString(com.amazon.mp3.automotiveOS.R.string.dmusic_download_goto_music_settings), getString(com.amazon.mp3.automotiveOS.R.string.dmusic_setting_manage_language_title)}, 2));
        Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
        if (textView2 != null) {
            textView2.setText(str3);
        }
        View view3 = this.rootView;
        initAcceptButton(view3 == null ? null : (BaseButton) view3.findViewById(com.amazon.mp3.automotiveOS.R.id.accept_button));
        View view4 = this.rootView;
        initDeclineButton(view4 != null ? (BaseButton) view4.findViewById(com.amazon.mp3.automotiveOS.R.id.decline_button) : null);
        setBackground();
        return this.rootView;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(context)");
        this.retailLopSyncManager = new RetailLopSyncManager(context, defaultSharedPreferences);
    }

    private final void initAcceptButton(final BaseButton acceptButton) {
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.dialog.fragment.RetailLOPFtuDialogFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                RetailLOPFtuDialogFragment.m914initAcceptButton$lambda1(this.f$0, acceptButton, (StyleSheet) obj);
            }
        });
        if (acceptButton == null) {
            return;
        }
        acceptButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.RetailLOPFtuDialogFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RetailLOPFtuDialogFragment.m915initAcceptButton$lambda2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initAcceptButton$lambda-1, reason: not valid java name */
    public static final void m914initAcceptButton$lambda1(RetailLOPFtuDialogFragment this$0, BaseButton baseButton, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.SOLID);
        Intrinsics.checkNotNull(buttonBuilder);
        String string = this$0.getString(com.amazon.mp3.automotiveOS.R.string.dmusic_button_yes);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusic_button_yes)");
        BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
        if (styleBuilderWithText == null) {
            return;
        }
        Intrinsics.checkNotNull(baseButton);
        styleBuilderWithText.applyStyle(baseButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initAcceptButton$lambda-2, reason: not valid java name */
    public static final void m915initAcceptButton$lambda2(RetailLOPFtuDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = AmazonApplication.getApplication().getApplicationContext();
        String str = this$0.retailLanguageOfPreference;
        Intrinsics.checkNotNull(str);
        String str2 = (String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{Attributes.PREDEFINED_ATTRIBUTE_PREFIX}, false, 0, 6, (Object) null).get(0);
        String str3 = this$0.retailLanguageOfPreference;
        Intrinsics.checkNotNull(str3);
        Locale locale = new Locale(str2, (String) StringsKt__StringsKt.split$default((CharSequence) str3, new String[]{Attributes.PREDEFINED_ATTRIBUTE_PREFIX}, false, 0, 6, (Object) null).get(1));
        LocaleManager localeManager = LocaleManager.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        localeManager.setLocale(context, locale);
        RetailLopSyncManager retailLopSyncManager = this$0.retailLopSyncManager;
        String strFetchRetailLanguageOfPreference = retailLopSyncManager == null ? null : retailLopSyncManager.fetchRetailLanguageOfPreference(this$0.getCustomerId());
        RetailLopSyncManager retailLopSyncManager2 = this$0.retailLopSyncManager;
        if (retailLopSyncManager2 != null) {
            retailLopSyncManager2.retailLopSyncAccepted(locale);
        }
        RetailLopSyncManager retailLopSyncManager3 = this$0.retailLopSyncManager;
        if (retailLopSyncManager3 != null) {
            retailLopSyncManager3.retailLopSyncCompleted(this$0.getCustomerId());
        }
        ActionType actionType = ActionType.RETAIL_LOP_ACCEPTED;
        PageType pageType = PageType.RETAIL_LOP_SYNC_NOTIFICATION;
        this$0.sendUiClickEventMetric(actionType, pageType);
        String str4 = strFetchRetailLanguageOfPreference;
        this$0.sendUiFlexEvent("retailLoPSyncUserAcceptedLoP", this$0.getBootstrapLanguage(), this$0.getDeviceOSLanguage(), str4, strFetchRetailLanguageOfPreference);
        String deviceOSLanguage = this$0.getDeviceOSLanguage();
        String string = AppLocales.valueOf(this$0.getBootstrapLanguage()).getEntryValue().toString();
        Intrinsics.checkNotNullExpressionValue(string, "valueOf(bootstrapLanguage).entryValue.toString()");
        String metricsValue = ActionType.ACCEPT.getMetricsValue();
        Intrinsics.checkNotNullExpressionValue(metricsValue, "ACCEPT.metricValue");
        this$0.sendRetailLanguageSyncActionEvent(deviceOSLanguage, strFetchRetailLanguageOfPreference, string, str4, metricsValue);
        String deviceOSLanguage2 = this$0.getDeviceOSLanguage();
        String string2 = AppLocales.valueOf(this$0.getBootstrapLanguage()).getEntryValue().toString();
        Intrinsics.checkNotNullExpressionValue(string2, "valueOf(bootstrapLanguage).entryValue.toString()");
        String metricsValue2 = pageType.getMetricsValue();
        Intrinsics.checkNotNullExpressionValue(metricsValue2, "RETAIL_LOP_SYNC_NOTIFICATION.metricValue");
        this$0.sendLocaleChangedEvent(deviceOSLanguage2, strFetchRetailLanguageOfPreference, string2, metricsValue2);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass(context, LauncherActivity.class);
        intent.setFlags(268468224);
        context.startActivity(intent);
        this$0.dismiss();
    }

    private final void initDeclineButton(final BaseButton declineButton) {
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.dialog.fragment.RetailLOPFtuDialogFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                RetailLOPFtuDialogFragment.m916initDeclineButton$lambda3(this.f$0, declineButton, (StyleSheet) obj);
            }
        });
        if (declineButton == null) {
            return;
        }
        declineButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.RetailLOPFtuDialogFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RetailLOPFtuDialogFragment.m917initDeclineButton$lambda4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initDeclineButton$lambda-3, reason: not valid java name */
    public static final void m916initDeclineButton$lambda3(RetailLOPFtuDialogFragment this$0, BaseButton baseButton, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.OUTLINE);
        Intrinsics.checkNotNull(buttonBuilder);
        String string = this$0.getString(com.amazon.mp3.automotiveOS.R.string.dmusic_button_no);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusic_button_no)");
        BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
        if (styleBuilderWithText == null) {
            return;
        }
        Intrinsics.checkNotNull(baseButton);
        styleBuilderWithText.applyStyle(baseButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initDeclineButton$lambda-4, reason: not valid java name */
    public static final void m917initDeclineButton$lambda4(RetailLOPFtuDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RetailLopSyncManager retailLopSyncManager = this$0.retailLopSyncManager;
        String strFetchRetailLanguageOfPreference = retailLopSyncManager == null ? null : retailLopSyncManager.fetchRetailLanguageOfPreference(this$0.getCustomerId());
        this$0.sendUiClickEventMetric(ActionType.RETAIL_LOP_REJECTED, PageType.RETAIL_LOP_SYNC_NOTIFICATION);
        String str = strFetchRetailLanguageOfPreference;
        this$0.sendUiFlexEvent("retailLoPSyncUserRejectedLoP", this$0.getBootstrapLanguage(), this$0.getDeviceOSLanguage(), str, this$0.getBootstrapLanguage());
        String deviceOSLanguage = this$0.getDeviceOSLanguage();
        String string = AppLocales.valueOf(this$0.getBootstrapLanguage()).getEntryValue().toString();
        String string2 = AppLocales.valueOf(this$0.getBootstrapLanguage()).getEntryValue().toString();
        Intrinsics.checkNotNullExpressionValue(string2, "valueOf(bootstrapLanguage).entryValue.toString()");
        String metricsValue = ActionType.REJECT.getMetricsValue();
        Intrinsics.checkNotNullExpressionValue(metricsValue, "REJECT.metricValue");
        this$0.sendRetailLanguageSyncActionEvent(deviceOSLanguage, string, string2, str, metricsValue);
        RetailLopSyncManager retailLopSyncManager2 = this$0.retailLopSyncManager;
        if (retailLopSyncManager2 != null) {
            retailLopSyncManager2.retailLopSyncCompleted(this$0.getCustomerId());
        }
        this$0.dismiss();
    }

    private final void setBackground() {
        Window window;
        Drawable drawable = getResources().getDrawable(com.amazon.mp3.automotiveOS.R.drawable.bauhaus_gradient_background);
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setBackgroundDrawable(drawable);
    }

    private final String getDeviceLanguage() {
        String string = AmazonApplication.getDeviceLocale().toString();
        Intrinsics.checkNotNullExpressionValue(string, "getDeviceLocale().toString()");
        return string;
    }

    private final void sendUiClickEventMetric(ActionType actionType, PageType pageType) {
        UiClickEvent uiClickEventBuild = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withPageType(pageType).build();
        if (uiClickEventBuild != null) {
            MetricsHolder.getManager().handleEvent(uiClickEventBuild);
        }
    }

    private final void sendRetailLanguageSyncActionEvent(String locale, String appLocale, String previousAppLocale, String retailLocale, String preferenceAction) {
        MetricsLogger.sendEvent(RetailLanguageSyncActionEvent.builder().withLocale(locale).withAppLocale(appLocale).withPreviousAppLocale(previousAppLocale).withRetailLocale(retailLocale).withPreferenceAction(preferenceAction).build());
    }

    private final void sendLocaleChangedEvent(String locale, String appLocale, String previousAppLocale, String pageType) {
        MetricsLogger.sendEvent(LocaleChangedEvent.builder().withLocale(locale).withPreviousAppLocale(previousAppLocale).withAppLocale(appLocale).withPageType(pageType).build());
    }

    private final void sendUiFlexEvent(String flexEventName, String bootstrapLanguage, String deviceOSLanguage, String retailLanguageOfPreference, String appDefaultedLanguage) {
        FlexEvent flexEventBuild = FlexEvent.builder(flexEventName).withHcFlexStr1(bootstrapLanguage).withHcFlexStr2(deviceOSLanguage).withHcFlexStr3(retailLanguageOfPreference).withHcFlexStr4(appDefaultedLanguage).build();
        if (flexEventBuild != null) {
            MetricsHolder.getManager().handleEvent(flexEventBuild);
        }
    }
}

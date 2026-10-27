package com.amazon.mp3.language.preference.fragment.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.TwoLinesListPreferenceModel;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.client.activity.LauncherActivity;
import com.amazon.mp3.configuration.AlexaLocaleProvider;
import com.amazon.mp3.fragment.viewmodel.BaseViewModel;
import com.amazon.mp3.fragment.viewmodel.TwoLinesPreferenceRecyclerViewModel;
import com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks;
import com.amazon.mp3.language.preference.AppDisplayLanguageSetting;
import com.amazon.mp3.language.preference.fragment.model.AppDisplayLanguageDialogModel;
import com.amazon.mp3.language.preference.fragment.view.AppDisplayLanguagePreferenceBinding;
import com.amazon.mp3.language.preference.locale.AppLocaleProvider;
import com.amazon.mp3.language.preference.locale.AppLocales;
import com.amazon.mp3.language.preference.util.LocaleManager;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.music.metrics.mts.event.definition.applocale.LocaleChangedEvent;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.voice.VoiceManagerSingleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppDisplayLanguageViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 02\u00020\u0001:\u00010B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019¢\u0006\u0004\b.\u0010/J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J\b\u0010\t\u001a\u00020\u0004H\u0002J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\r\u001a\u00020\u0004H\u0002J\b\u0010\u000e\u001a\u00020\u0004H\u0002J\b\u0010\u000f\u001a\u00020\u0004H\u0002J\b\u0010\u0010\u001a\u00020\u0004H\u0002R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010\"\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010!R\u0018\u0010#\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010\u001bR\u0018\u0010%\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\"\u0010(\u001a\u00020'8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u00061"}, d2 = {"Lcom/amazon/mp3/language/preference/fragment/viewmodel/AppDisplayLanguageViewModel;", "Lcom/amazon/mp3/fragment/viewmodel/BaseViewModel;", "Landroid/content/Context;", "context", "", "initDialogModel", "", "Lcom/amazon/mp3/activity/TwoLinesListPreferenceModel;", "createLanguageList", "setupCurrentCheckedPosition", "", "id", "notifyPropertyChanged", "initRecyclerViewModelForDisplayLangPreferences", "initViewModelCallbacksForDisplayLang", "setupSaveButton", "setupCancelButton", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/language/preference/fragment/view/AppDisplayLanguagePreferenceBinding;", "mBinding", "Lcom/amazon/mp3/language/preference/fragment/view/AppDisplayLanguagePreferenceBinding;", "getMBinding", "()Lcom/amazon/mp3/language/preference/fragment/view/AppDisplayLanguagePreferenceBinding;", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "viewModelCallbacks", "Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "getViewModelCallbacks", "()Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;", "setViewModelCallbacks", "(Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "prevCheckedPositionForManageLang", "I", "newCheckedPositionForManageLang", "viewModelCallbacksForDisplayLang", "Lcom/amazon/mp3/fragment/viewmodel/TwoLinesPreferenceRecyclerViewModel;", "twoLinesPreferenceRecyclerViewmodelForDisplayLang", "Lcom/amazon/mp3/fragment/viewmodel/TwoLinesPreferenceRecyclerViewModel;", "Lcom/amazon/mp3/language/preference/fragment/model/AppDisplayLanguageDialogModel;", "dialogModel", "Lcom/amazon/mp3/language/preference/fragment/model/AppDisplayLanguageDialogModel;", "getDialogModel", "()Lcom/amazon/mp3/language/preference/fragment/model/AppDisplayLanguageDialogModel;", "setDialogModel", "(Lcom/amazon/mp3/language/preference/fragment/model/AppDisplayLanguageDialogModel;)V", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/language/preference/fragment/view/AppDisplayLanguagePreferenceBinding;Lcom/amazon/mp3/fragment/viewmodel/ViewModelCallbacks;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AppDisplayLanguageViewModel extends BaseViewModel {
    private final Context context;
    public AppDisplayLanguageDialogModel dialogModel;
    private final AppDisplayLanguagePreferenceBinding mBinding;
    private int newCheckedPositionForManageLang;
    private int prevCheckedPositionForManageLang;
    private TwoLinesPreferenceRecyclerViewModel twoLinesPreferenceRecyclerViewmodelForDisplayLang;
    private ViewModelCallbacks viewModelCallbacks;
    private ViewModelCallbacks viewModelCallbacksForDisplayLang;

    public Context getContext() {
        return this.context;
    }

    public final AppDisplayLanguagePreferenceBinding getMBinding() {
        return this.mBinding;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppDisplayLanguageViewModel(Context context, AppDisplayLanguagePreferenceBinding mBinding, ViewModelCallbacks viewModelCallbacks) {
        super(context, viewModelCallbacks);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mBinding, "mBinding");
        this.context = context;
        this.mBinding = mBinding;
        this.viewModelCallbacks = viewModelCallbacks;
        initDialogModel(getContext());
        initViewModelCallbacksForDisplayLang();
        initRecyclerViewModelForDisplayLangPreferences();
        setupCurrentCheckedPosition();
        notifyPropertyChanged(mBinding.getTitleView().getId());
        notifyPropertyChanged(mBinding.getSaveAndRestartButton().getId());
        notifyPropertyChanged(mBinding.getCancelButton().getId());
        notifyPropertyChanged(mBinding.getShowDisplayLanguageRecyclerView().getMRecyclerView().getId());
        setupSaveButton();
        setupCancelButton();
    }

    public ViewModelCallbacks getViewModelCallbacks() {
        return this.viewModelCallbacks;
    }

    public final AppDisplayLanguageDialogModel getDialogModel() {
        AppDisplayLanguageDialogModel appDisplayLanguageDialogModel = this.dialogModel;
        if (appDisplayLanguageDialogModel != null) {
            return appDisplayLanguageDialogModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dialogModel");
        return null;
    }

    public final void setDialogModel(AppDisplayLanguageDialogModel appDisplayLanguageDialogModel) {
        Intrinsics.checkNotNullParameter(appDisplayLanguageDialogModel, "<set-?>");
        this.dialogModel = appDisplayLanguageDialogModel;
    }

    private final void initDialogModel(Context context) {
        List<TwoLinesListPreferenceModel> listCreateLanguageList = createLanguageList();
        String string = context.getString(R.string.dmusic_setting_manage_language_title);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ng_manage_language_title)");
        String string2 = context.getString(R.string.dmusic_button_save);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(\n     …sic_button_save\n        )");
        String string3 = context.getString(R.string.dmusic_button_cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.dmusic_button_cancel)");
        setDialogModel(new AppDisplayLanguageDialogModel(string, listCreateLanguageList, null, string2, string3));
    }

    private final List<TwoLinesListPreferenceModel> createLanguageList() {
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        String displayLanguage = AppDisplayLanguageSetting.INSTANCE.getDisplayLanguage(context);
        String musicTerritory = AccountDetailUtil.getMusicTerritoryOfResidence();
        AppLocaleProvider.Companion companion = AppLocaleProvider.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(musicTerritory, "musicTerritory");
        AppLocaleProvider appLocaleProviderFromValue = companion.fromValue(musicTerritory);
        List<AppLocales> locales = appLocaleProviderFromValue == null ? null : appLocaleProviderFromValue.getLocales();
        if (locales == null) {
            locales = CollectionsKt__CollectionsKt.emptyList();
        }
        List<AppLocales> iNGatedLocales = appLocaleProviderFromValue == null ? null : appLocaleProviderFromValue.getINGatedLocales();
        if (iNGatedLocales == null) {
            iNGatedLocales = CollectionsKt__CollectionsKt.emptyList();
        }
        for (AppLocales appLocales : locales) {
            if (!iNGatedLocales.contains(appLocales)) {
                String string = context.getString(appLocales.getEntryResourceId());
                Intrinsics.checkNotNullExpressionValue(string, "it.getString(locale.getEntryResourceId())");
                arrayList.add(new TwoLinesListPreferenceModel(string, appLocales.toString(), null, Intrinsics.areEqual(appLocales.toString(), displayLanguage)));
            }
        }
        return arrayList;
    }

    private final void setupCurrentCheckedPosition() {
        for (TwoLinesListPreferenceModel twoLinesListPreferenceModel : getDialogModel().getShowAppDisplayLanguageList()) {
            if (twoLinesListPreferenceModel.getIsChecked()) {
                int iIndexOf = getDialogModel().getShowAppDisplayLanguageList().indexOf(twoLinesListPreferenceModel);
                this.prevCheckedPositionForManageLang = iIndexOf;
                this.newCheckedPositionForManageLang = iIndexOf;
            }
        }
    }

    private final void notifyPropertyChanged(int id) {
        if (id == this.mBinding.getTitleView().getId()) {
            this.mBinding.getTitleView().setText(getDialogModel().getTitle());
            return;
        }
        if (id == this.mBinding.getShowDisplayLanguageRecyclerView().getMRecyclerView().getId()) {
            this.mBinding.getShowDisplayLanguageRecyclerView().getMRecyclerView().setVisibility(0);
            return;
        }
        if (id == this.mBinding.getSaveAndRestartButton().getId()) {
            this.mBinding.getSaveAndRestartButton().setText(getDialogModel().getSaveAndRestartButtonText());
            this.mBinding.getSaveAndRestartButton().setVisibility(0);
        } else if (id == this.mBinding.getCancelButton().getId()) {
            this.mBinding.getCancelButton().setText(getDialogModel().getCancelButtonText());
        }
    }

    private final void initRecyclerViewModelForDisplayLangPreferences() {
        this.twoLinesPreferenceRecyclerViewmodelForDisplayLang = new TwoLinesPreferenceRecyclerViewModel(getContext(), this.mBinding.getShowDisplayLanguageRecyclerView(), getDialogModel().getShowAppDisplayLanguageList(), this.viewModelCallbacksForDisplayLang);
    }

    private final void initViewModelCallbacksForDisplayLang() {
        this.viewModelCallbacksForDisplayLang = new ViewModelCallbacks() { // from class: com.amazon.mp3.language.preference.fragment.viewmodel.AppDisplayLanguageViewModel.initViewModelCallbacksForDisplayLang.1
            @Override // com.amazon.mp3.fragment.viewmodel.ViewModelCallbacks
            public void onEventOccurred(int callerId, int actionId, BaseViewModel viewModelInstance) {
                TwoLinesPreferenceRecyclerViewModel twoLinesPreferenceRecyclerViewModel;
                Intrinsics.checkNotNullParameter(viewModelInstance, "viewModelInstance");
                if (actionId != 1 || (twoLinesPreferenceRecyclerViewModel = AppDisplayLanguageViewModel.this.twoLinesPreferenceRecyclerViewmodelForDisplayLang) == null) {
                    return;
                }
                AppDisplayLanguageViewModel.this.newCheckedPositionForManageLang = twoLinesPreferenceRecyclerViewModel.getCurrentCheckedPosition();
            }
        };
    }

    private final void setupSaveButton() {
        final Context context = getContext();
        getMBinding().getSaveAndRestartButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.language.preference.fragment.viewmodel.AppDisplayLanguageViewModel$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AppDisplayLanguageViewModel.m1219setupSaveButton$lambda2$lambda1(this.f$0, context, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupSaveButton$lambda-2$lambda-1, reason: not valid java name */
    public static final void m1219setupSaveButton$lambda2$lambda1(AppDisplayLanguageViewModel this$0, Context c, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(c, "$c");
        String entryValue = this$0.getDialogModel().getShowAppDisplayLanguageList().get(this$0.prevCheckedPositionForManageLang).getEntryValue();
        String entryValue2 = this$0.getDialogModel().getShowAppDisplayLanguageList().get(this$0.newCheckedPositionForManageLang).getEntryValue();
        AppDisplayLanguageSetting.INSTANCE.setDisplayLanguagePreference(c, entryValue2);
        Locale entryValue3 = entryValue2 == null ? null : AppLocales.valueOf(entryValue2).getEntryValue();
        Locale entryValue4 = entryValue != null ? AppLocales.valueOf(entryValue).getEntryValue() : null;
        LocaleManager.INSTANCE.setLocale(this$0.getContext(), entryValue3);
        if (AmazonApplication.getCapabilities().isAlexaEnabled() && VoiceManagerSingleton.isInitialized()) {
            VoiceManagerSingleton.getInstance().updateAlexaLocale(AlexaLocaleProvider.getLocale());
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass(this$0.getContext(), LauncherActivity.class);
        intent.setFlags(268468224);
        this$0.getContext().startActivity(intent);
        MetricsLogger.sendEvent(FlexEvent.builder("appDisplayLanguageSetting").withFlexStr1(AccountDetailUtil.getMusicTerritoryOfResidence()).withFlexStr2(entryValue).withFlexStr3(entryValue2).withFlexStr4(AmazonApplication.getDeviceLocale().toString()).build());
        LocaleChangedEvent.Builder builderWithAppLocale = LocaleChangedEvent.builder().withLocale(AmazonApplication.getDeviceLocale().toString()).withPreviousAppLocale(String.valueOf(entryValue4)).withAppLocale(String.valueOf(entryValue3));
        PageType pageType = PageType.APP_MANAGE_LANGUAGE_SETTING;
        MetricsLogger.sendEvent(builderWithAppLocale.withPageType(pageType.getMetricsValue()).build());
        AutoUiEventUtil.sendUiClickEvent(ActionType.SELECT_LANGUAGE, pageType);
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectSaveAndRestart");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(1, 1, this$0);
    }

    private final void setupCancelButton() {
        this.mBinding.getCancelButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.language.preference.fragment.viewmodel.AppDisplayLanguageViewModel$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AppDisplayLanguageViewModel.m1218setupCancelButton$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupCancelButton$lambda-3, reason: not valid java name */
    public static final void m1218setupCancelButton$lambda3(AppDisplayLanguageViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendUiClickMetric$DigitalMusicAndroid3P_automotiveProdRelease("selectCancel");
        ViewModelCallbacks viewModelCallbacks = this$0.getViewModelCallbacks();
        if (viewModelCallbacks == null) {
            return;
        }
        viewModelCallbacks.onEventOccurred(1, 1, this$0);
    }
}

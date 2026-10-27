package com.amazon.mp3.auto.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SubscriptionSettingsUpdater;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.AutomotiveSignOutHelper;
import com.amazon.mp3.auto.settings.SettingItemAdapter;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.net.dmls.StreamingBitrate;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: AutomotiveSettingsActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 72\u00020\u0001:\u00017B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010%\u001a\u00020\fJ\u0006\u0010&\u001a\u00020\fJ\u001c\u0010'\u001a\u00020\u001f2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0002J\u0012\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\b\u00100\u001a\u00020-H\u0002J\b\u00101\u001a\u00020-H\u0002J\u0010\u00102\u001a\u00020-2\u0006\u00103\u001a\u00020\fH\u0002J\u000e\u00104\u001a\u00020-2\u0006\u00103\u001a\u00020\fJ\u0010\u00105\u001a\u00020-2\u0006\u00103\u001a\u00020\fH\u0002J\u000e\u00106\u001a\u00020-2\u0006\u00103\u001a\u00020\fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\n\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001b\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\n\u001a\u0004\b\u001c\u0010\bR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\n\u001a\u0004\b\"\u0010#¨\u00068"}, d2 = {"Lcom/amazon/mp3/auto/settings/AutomotiveSettingsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/amazon/mp3/auto/settings/SettingItemAdapter;", "backBtn", "Lcom/amazon/ui/foundations/views/BaseButton;", "getBackBtn", "()Lcom/amazon/ui/foundations/views/BaseButton;", "backBtn$delegate", "Lkotlin/Lazy;", "invalidSubscription", "", "prefs", "Landroid/content/SharedPreferences;", "getPrefs", "()Landroid/content/SharedPreferences;", "prefs$delegate", "settingItems", "Ljava/util/ArrayList;", "Lcom/amazon/mp3/auto/settings/SettingItem;", "Lkotlin/collections/ArrayList;", "settingsLv", "Landroid/widget/ListView;", "getSettingsLv", "()Landroid/widget/ListView;", "settingsLv$delegate", "signOutBtn", "getSignOutBtn", "signOutBtn$delegate", "userConfig", "Lcom/amazon/mp3/activity/settings/SubscriptionSettingsUpdater$CustomerConfiguration;", "usernameTv", "Landroid/widget/TextView;", "getUsernameTv", "()Landroid/widget/TextView;", "usernameTv$delegate", "getLoudnessSettingPrefs", "getStreamingSettingPrefs", "getUserConfig", "subscription", "Lcom/amazon/music/subscription/UserSubscription;", "accountDetailUtil", "Lcom/amazon/mp3/account/details/AccountDetailUtil;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setUsernameWithSubscription", "setupSettings", "updateLoudnessSetting", "isChecked", "updateLoudnessSettingPrefs", "updateStreamingSetting", "updateStreamingSettingPrefs", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutomotiveSettingsActivity extends AppCompatActivity {
    private static final String TAG = AutomotiveSettingsActivity.class.getSimpleName();
    private SettingItemAdapter adapter;
    private boolean invalidSubscription;
    private SubscriptionSettingsUpdater.CustomerConfiguration userConfig;

    /* JADX INFO: renamed from: backBtn$delegate, reason: from kotlin metadata */
    private final Lazy backBtn = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.settings.AutomotiveSettingsActivity$backBtn$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseButton invoke() {
            return (BaseButton) this.this$0.findViewById(R.id.setting_back_home_btn);
        }
    });

    /* JADX INFO: renamed from: signOutBtn$delegate, reason: from kotlin metadata */
    private final Lazy signOutBtn = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.settings.AutomotiveSettingsActivity$signOutBtn$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseButton invoke() {
            return (BaseButton) this.this$0.findViewById(R.id.sign_out_btn);
        }
    });

    /* JADX INFO: renamed from: settingsLv$delegate, reason: from kotlin metadata */
    private final Lazy settingsLv = LazyKt__LazyJVMKt.lazy(new Function0<ListView>() { // from class: com.amazon.mp3.auto.settings.AutomotiveSettingsActivity$settingsLv$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListView invoke() {
            return (ListView) this.this$0.findViewById(R.id.setting_group);
        }
    });

    /* JADX INFO: renamed from: usernameTv$delegate, reason: from kotlin metadata */
    private final Lazy usernameTv = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.auto.settings.AutomotiveSettingsActivity$usernameTv$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TextView invoke() {
            return (TextView) this.this$0.findViewById(R.id.username_display);
        }
    });

    /* JADX INFO: renamed from: prefs$delegate, reason: from kotlin metadata */
    private final Lazy prefs = LazyKt__LazyJVMKt.lazy(new Function0<SharedPreferences>() { // from class: com.amazon.mp3.auto.settings.AutomotiveSettingsActivity$prefs$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SharedPreferences invoke() {
            return this.this$0.getSharedPreferences("Automotive_Settings", 0);
        }
    });
    private final ArrayList<SettingItem> settingItems = new ArrayList<>();

    private final BaseButton getBackBtn() {
        Object value = this.backBtn.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backBtn>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getSignOutBtn() {
        Object value = this.signOutBtn.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-signOutBtn>(...)");
        return (BaseButton) value;
    }

    private final ListView getSettingsLv() {
        Object value = this.settingsLv.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-settingsLv>(...)");
        return (ListView) value;
    }

    private final TextView getUsernameTv() {
        Object value = this.usernameTv.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-usernameTv>(...)");
        return (TextView) value;
    }

    private final SharedPreferences getPrefs() {
        Object value = this.prefs.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-prefs>(...)");
        return (SharedPreferences) value;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        if (!AccountCredentialUtil.get(getApplicationContext()).isSignedIn()) {
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automotive_settings);
        setUsernameWithSubscription();
        setupSettings();
        SettingItemAdapter settingItemAdapter = this.adapter;
        if (settingItemAdapter != null) {
            settingItemAdapter.setOnItemCheckedChangedListener(new SettingItemAdapter.OnItemCheckedChangeListener() { // from class: com.amazon.mp3.auto.settings.AutomotiveSettingsActivity.onCreate.1
                @Override // com.amazon.mp3.auto.settings.SettingItemAdapter.OnItemCheckedChangeListener
                public void onChangeClick(int id, boolean isChecked) {
                    if (id == 0) {
                        AutomotiveSettingsActivity.this.updateStreamingSetting(isChecked);
                    } else {
                        if (id != 1) {
                            return;
                        }
                        AutomotiveSettingsActivity.this.updateLoudnessSetting(isChecked);
                    }
                }
            });
        }
        getBackBtn().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.settings.AutomotiveSettingsActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutomotiveSettingsActivity.m362onCreate$lambda0(this.f$0, view);
            }
        });
        getSignOutBtn().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.settings.AutomotiveSettingsActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutomotiveSettingsActivity.m363onCreate$lambda1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m362onCreate$lambda0(AutomotiveSettingsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-1, reason: not valid java name */
    public static final void m363onCreate$lambda1(final AutomotiveSettingsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutoUiEventUtil.sendUiClickEvent(ActionType.SELECT_AUTOMOTIVE_OS_SIGN_OUT, PageType.AUTOMOTIVE_OS_SETTINGS);
        AutomotiveSignOutHelper automotiveSignOutHelper = AutomotiveSignOutHelper.INSTANCE;
        Context applicationContext = this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        automotiveSignOutHelper.signUserOut(applicationContext, new AutomotiveSignOutHelper.SignOutCallback() { // from class: com.amazon.mp3.auto.settings.AutomotiveSettingsActivity$onCreate$3$1
            @Override // com.amazon.mp3.auto.AutomotiveSignOutHelper.SignOutCallback
            public void signOutComplete() {
                Log.debug(AutomotiveSettingsActivity.TAG, "signOutComplete: sending broadcast");
                this.this$0.sendBroadcast(new Intent("com.amazon.mp3.MAP_SignOut_Complete"));
                this.this$0.finish();
            }

            @Override // com.amazon.mp3.auto.AutomotiveSignOutHelper.SignOutCallback
            public void signOutCancel() {
                Log.debug(AutomotiveSettingsActivity.TAG, "signOutCancel: fail to start de-registration task");
                this.this$0.finish();
            }
        });
    }

    public final boolean getLoudnessSettingPrefs() {
        return getPrefs().getBoolean(getString(R.string.setting_key_is_loudness_normalization_enabled), false);
    }

    public final void updateLoudnessSettingPrefs(boolean isChecked) {
        getPrefs().edit().putBoolean(getString(R.string.setting_key_is_loudness_normalization_enabled), isChecked).apply();
    }

    public final boolean getStreamingSettingPrefs() {
        return StringsKt__StringsJVMKt.equals$default(getPrefs().getString(getString(R.string.setting_key_multi_bitrate_streaming), StreamingBitrate.AUTO.getEntryValue()), StreamingBitrate.KATANA.getEntryValue(), false, 2, null);
    }

    public final void updateStreamingSettingPrefs(boolean isChecked) {
        String entryValue;
        if (isChecked) {
            entryValue = StreamingBitrate.KATANA.getEntryValue();
        } else {
            entryValue = StreamingBitrate.AUTO.getEntryValue();
        }
        getPrefs().edit().putString(getString(R.string.setting_key_multi_bitrate_streaming), entryValue).apply();
    }

    private final void setUsernameWithSubscription() {
        getUsernameTv().setText(AccountCredentialStorage.get(this).getUsername());
        SubscriptionSettingsUpdater.CustomerConfiguration userConfig = getUserConfig(UserSubscriptionUtil.getUserSubscription(), AccountDetailUtil.get(this));
        this.userConfig = userConfig;
        if (userConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userConfig");
            userConfig = null;
        }
        if (userConfig == SubscriptionSettingsUpdater.CustomerConfiguration.INVALID) {
            this.invalidSubscription = true;
        }
    }

    private final void setupSettings() {
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.auto.settings.AutomotiveSettingsActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AutomotiveSettingsActivity.m364setupSettings$lambda3(this.f$0, (StyleSheet) obj);
            }
        });
        this.adapter = new SettingItemAdapter(this, this.settingItems);
        Log.debug(TAG, "Get Prefs: " + getStreamingSettingPrefs() + " and " + getLoudnessSettingPrefs());
        SubscriptionSettingsUpdater.CustomerConfiguration customerConfiguration = this.userConfig;
        if (customerConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userConfig");
            customerConfiguration = null;
        }
        boolean z = customerConfiguration == SubscriptionSettingsUpdater.CustomerConfiguration.HAS_HAWKFIRE_KATANA;
        if (UserSubscriptionUtil.getUserSubscription().isInSubscriptionMarketplace() && !UserSubscriptionUtil.isNightwingOnly()) {
            ArrayList<SettingItem> arrayList = this.settingItems;
            String string = getString(R.string.dmusic_automotive_high_quality_streaming);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusi…e_high_quality_streaming)");
            arrayList.add(new SettingItem(string, R.drawable.ic_hd_setting, getStreamingSettingPrefs(), z));
        }
        ArrayList<SettingItem> arrayList2 = this.settingItems;
        String string2 = getString(R.string.dmusic_loudness_normalization_title);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.dmusi…ness_normalization_title)");
        arrayList2.add(new SettingItem(string2, R.drawable.ic_loudness_setting, getLoudnessSettingPrefs(), true ^ this.invalidSubscription));
        getSettingsLv().setAdapter((ListAdapter) this.adapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupSettings$lambda-3, reason: not valid java name */
    public static final void m364setupSettings$lambda3(AutomotiveSettingsActivity this$0, StyleSheet styleSheet) {
        BaseButton.StyleBuilder iconBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.SOLID);
        if (buttonBuilder != null) {
            String string = this$0.getString(R.string.dmusic_automotive_authentication_sign_out_button);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusi…tication_sign_out_button)");
            BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
            if (styleBuilderWithText != null) {
                styleBuilderWithText.applyStyle(this$0.getSignOutBtn());
            }
        }
        Drawable drawable = this$0.getDrawable(R.drawable.ic_action_goback);
        if (drawable == null || (iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.GLASS)) == null || (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(this$0.getBackBtn());
    }

    private final SubscriptionSettingsUpdater.CustomerConfiguration getUserConfig(UserSubscription subscription, AccountDetailUtil accountDetailUtil) {
        if (accountDetailUtil == null || subscription == null) {
            return SubscriptionSettingsUpdater.CustomerConfiguration.INVALID;
        }
        if (subscription.isKatana()) {
            return SubscriptionSettingsUpdater.CustomerConfiguration.HAS_HAWKFIRE_KATANA;
        }
        if (subscription.isHawkfireHome()) {
            return SubscriptionSettingsUpdater.CustomerConfiguration.HAS_HAWKFIRE_HOME;
        }
        if (subscription.isHawkfireAllDevices()) {
            return SubscriptionSettingsUpdater.CustomerConfiguration.HAS_HAWKFIRE_ALL_DEVICES;
        }
        if (accountDetailUtil.isUserInHawkfireMarketplace()) {
            if (subscription.isPrimeOnly()) {
                return SubscriptionSettingsUpdater.CustomerConfiguration.HAS_ONLY_PRIME_IN_HAWKFIRE_MARKET_PLACE;
            }
            return SubscriptionSettingsUpdater.CustomerConfiguration.HAS_NO_SUBSCRIPTION_IN_HAWKFIRE_MARKET_PLACE;
        }
        if (subscription.isPrimeOnly()) {
            return SubscriptionSettingsUpdater.CustomerConfiguration.HAS_ONLY_PRIME_NOT_IN_HAWKFIRE_MARKET_PLACE;
        }
        return SubscriptionSettingsUpdater.CustomerConfiguration.HAS_NO_SUBSCRIPTION_NOT_IN_HAWKFIRE_MARKET_PLACE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLoudnessSetting(boolean isChecked) {
        ActionType actionType;
        Playback.getInstance().getPlaybackConfig().setLoudnessNormalizationEnabled(isChecked);
        updateLoudnessSettingPrefs(isChecked);
        if (isChecked) {
            actionType = ActionType.SELECT_NORMALIZATION_ON;
        } else {
            actionType = ActionType.SELECT_NORMALIZATION_OFF;
        }
        AutoUiEventUtil.sendUiClickEvent(actionType, PageType.AUTOMOTIVE_OS_SETTINGS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateStreamingSetting(boolean isChecked) {
        StreamingBitrate streamingBitrate;
        ActionType actionType;
        if (isChecked) {
            streamingBitrate = StreamingBitrate.KATANA;
        } else {
            streamingBitrate = StreamingBitrate.AUTO;
        }
        Playback.getInstance().getPlaybackConfig().setBitRateSelectionForWifi(streamingBitrate.convertToBitRateSelection());
        updateStreamingSettingPrefs(isChecked);
        if (isChecked) {
            actionType = ActionType.SELECT_AUDIO_QUALITY_HD_WIFI;
        } else {
            actionType = ActionType.SELECT_AUDIO_QUALITY_AUTO_WIFI;
        }
        AutoUiEventUtil.sendUiClickEvent(actionType, PageType.AUTOMOTIVE_OS_SETTINGS);
    }
}

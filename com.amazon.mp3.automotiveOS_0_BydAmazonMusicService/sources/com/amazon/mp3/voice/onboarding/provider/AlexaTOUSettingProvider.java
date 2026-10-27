package com.amazon.mp3.voice.onboarding.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.amazon.mp3.account.AccountOneTimeExperienceProvider;
import com.amazon.music.platform.aom.onboarding.provider.TOUSettingProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaTOUSettingProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/provider/AlexaTOUSettingProvider;", "Lcom/amazon/music/platform/aom/onboarding/provider/TOUSettingProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "KEY_TOU_ACCEPTED", "", "KEY_TOU_ACCEPTED_CID", "accountOneTimeExperienceProvider", "Lcom/amazon/mp3/account/AccountOneTimeExperienceProvider;", "sharedPref", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "clearTOUSetting", "", "isTOUAccepted", "", "markTOUAccepted", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaTOUSettingProvider implements TOUSettingProvider {
    private final String KEY_TOU_ACCEPTED;
    private final String KEY_TOU_ACCEPTED_CID;
    private final AccountOneTimeExperienceProvider accountOneTimeExperienceProvider;
    private final Context context;
    private final SharedPreferences sharedPref;

    public AlexaTOUSettingProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.KEY_TOU_ACCEPTED = "setting_key_alexa_tou_accepted";
        this.KEY_TOU_ACCEPTED_CID = "setting_key_alexa_tou_accepted_cid";
        this.sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        this.accountOneTimeExperienceProvider = new AccountOneTimeExperienceProvider(context);
    }

    @Override // com.amazon.music.platform.aom.onboarding.provider.TOUSettingProvider
    public boolean isTOUAccepted() {
        boolean z = this.sharedPref.getBoolean(this.KEY_TOU_ACCEPTED, false);
        boolean zIsExperienceMarkedForAccount = this.accountOneTimeExperienceProvider.isExperienceMarkedForAccount(this.KEY_TOU_ACCEPTED_CID);
        if (z != zIsExperienceMarkedForAccount) {
            markTOUAccepted();
        }
        return z || zIsExperienceMarkedForAccount;
    }

    @Override // com.amazon.music.platform.aom.onboarding.provider.TOUSettingProvider
    public void markTOUAccepted() {
        SharedPreferences sharedPref = this.sharedPref;
        Intrinsics.checkNotNullExpressionValue(sharedPref, "sharedPref");
        SharedPreferences.Editor editor = sharedPref.edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        editor.putBoolean(this.KEY_TOU_ACCEPTED, true);
        editor.apply();
        this.accountOneTimeExperienceProvider.markExperienceForAccount(this.KEY_TOU_ACCEPTED_CID);
    }

    public final void clearTOUSetting() {
        SharedPreferences sharedPref = this.sharedPref;
        Intrinsics.checkNotNullExpressionValue(sharedPref, "sharedPref");
        SharedPreferences.Editor editor = sharedPref.edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        editor.remove(this.KEY_TOU_ACCEPTED);
        editor.apply();
    }
}

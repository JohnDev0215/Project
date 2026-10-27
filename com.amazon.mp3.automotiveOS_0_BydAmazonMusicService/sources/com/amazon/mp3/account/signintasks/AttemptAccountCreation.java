package com.amazon.mp3.account.signintasks;

import android.content.Context;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.platform.providers.SessionIdProviderImpl;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.account.User;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.signin.SignInTask;
import com.amazon.music.signin.SignInTaskCache;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AttemptAccountCreation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0007R\u0014\u0010\t\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/account/signintasks/AttemptAccountCreation;", "Lcom/amazon/music/signin/SignInTask;", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isAutomotiveOS", "", "()Z", "isEligibleForMusicAccountCreation", "isUserSSO", "emitAttemptAccountCreationMetric", "", "flexEvent", "", "execute", "getName", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AttemptAccountCreation implements SignInTask {
    private static final String TAG;
    private final Context mContext;

    public AttemptAccountCreation(Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
    }

    @Override // com.amazon.music.signin.SignInTask
    public String getName() {
        return TAG;
    }

    @Override // com.amazon.music.signin.SignInTask
    public void execute() throws Exception {
        String str = TAG;
        Log.debug(str, Intrinsics.stringPlus("Executing sign in task ", str));
        if (isEligibleForMusicAccountCreation()) {
            Log.debug(str, "User is eligible for Music Account Creation");
            if (AccountManagerSingleton.get().attemptAccountCreation() == null) {
                Log.debug(str, "Account could not be created.");
                emitAttemptAccountCreationMetric("AAC_CALL_FAILED");
                throw new MusicAccountNotCreatedException("Music account could not be created");
            }
            Log.debug(str, "Account has been successfully created.");
            emitAttemptAccountCreationMetric("AAC_CALL_SUCCESSFULLY_COMPLETED");
            return;
        }
        Log.debug(str, "Customer is not eligible for Music Account Creation");
    }

    private final boolean isEligibleForMusicAccountCreation() {
        if (isAutomotiveOS() || isUserSSO()) {
            Log.debug(TAG, "User not eligible for MusicAccountCreation. isAutomotiveOS: %s, isUserSSO: %s.", Boolean.valueOf(isAutomotiveOS()), Boolean.valueOf(isUserSSO()));
            return false;
        }
        if (!new SignInTaskCache(this.mContext).hasSeenMusicTOU()) {
            Log.debug(TAG, "User not eligible for MusicAccountCreation. User has NOT seen Music TOU.");
            return false;
        }
        SessionIdProviderImpl.INSTANCE.getLoginSessionId();
        AccountCredentialUtil.get(this.mContext).isSignedIn();
        AccountManagerSingleton.get().updateUser();
        User cachedUser = AccountManagerSingleton.get().getCachedUser();
        if (cachedUser == null) {
            return false;
        }
        if (cachedUser.getMusicTerritoryOfResidence() == null) {
            Log.debug(TAG, "RCH call returned empty music territory for user.");
            emitAttemptAccountCreationMetric("RCH_RETURNED_EMPTY_MUSIC_TERRITORY");
            return false;
        }
        if (cachedUser.isAccountNotCreated()) {
            boolean zIsEnabled = Feature.android_app_tou_experiment_enabled.isEnabled();
            Log.debug(TAG, "Triggering experiment weblab. isAndroidTOUexperimentEnabled: %s", Boolean.valueOf(zIsEnabled));
            return zIsEnabled;
        }
        Log.debug(TAG, "User already has Music Account created.");
        return false;
    }

    private final boolean isAutomotiveOS() {
        return Intrinsics.areEqual(this.mContext.getPackageName(), "com.amazon.mp3.automotiveOS");
    }

    private final boolean isUserSSO() {
        return new SignInTaskCache(this.mContext).isUserSSO();
    }

    private final void emitAttemptAccountCreationMetric(String flexEvent) {
        MetricsLogger.sendEvent(FlexEvent.builder(TAG).withHcFlexStr1(flexEvent).build());
    }

    static {
        String simpleName = AttemptAccountCreation.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "AttemptAccountCreation::class.java.simpleName");
        TAG = simpleName;
    }
}

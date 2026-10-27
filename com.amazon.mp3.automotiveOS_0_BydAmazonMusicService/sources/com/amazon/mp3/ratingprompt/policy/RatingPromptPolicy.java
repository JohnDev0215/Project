package com.amazon.mp3.ratingprompt.policy;

import android.content.SharedPreferences;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.ratingprompt.actions.UserRatingStatus;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.health.AppHealthUtil;

/* JADX INFO: loaded from: classes4.dex */
public class RatingPromptPolicy {
    private static final String TAG = "RatingPromptPolicy";
    private final int appOpenNoIncidentThreshold = AmazonApplication.getCapabilities().getAppRatingNoIncidentThreshold();
    private final AppHealthUtil mAppHealthUtil;
    private final SharedPreferences mSharedPreferences;

    public RatingPromptPolicy(final SharedPreferences sharedPreferences, final AppHealthUtil appHealthUtil) {
        this.mAppHealthUtil = appHealthUtil;
        this.mSharedPreferences = sharedPreferences;
    }

    public boolean shouldShowPrompt() {
        UserRatingStatus userRatingStatus = getUserRatingStatus();
        return (userRatingStatus == UserRatingStatus.ACCEPTED || userRatingStatus == UserRatingStatus.REJECTED || this.mAppHealthUtil.getAppOpenedNoIncidentCount() <= this.appOpenNoIncidentThreshold) ? false : true;
    }

    public void updateUserRatingStatus(final UserRatingStatus status) {
        Log.debug(TAG, "User's status on rating the app is: " + status);
        updateUserRatingStatusPreference(status);
        if (AnonymousClass1.$SwitchMap$com$amazon$mp3$ratingprompt$actions$UserRatingStatus[status.ordinal()] != 1) {
            return;
        }
        this.mAppHealthUtil.resetAppOpenedNoIncidentCount();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.ratingprompt.policy.RatingPromptPolicy$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$ratingprompt$actions$UserRatingStatus;

        static {
            int[] iArr = new int[UserRatingStatus.values().length];
            $SwitchMap$com$amazon$mp3$ratingprompt$actions$UserRatingStatus = iArr;
            try {
                iArr[UserRatingStatus.DEFERRED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private UserRatingStatus getUserRatingStatus() {
        String string = this.mSharedPreferences.getString("com.amazon.mp3_key_last_app_rating_action_taken", UserRatingStatus.NOT_RATED.toString());
        try {
            return UserRatingStatus.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException unused) {
            Log.error(TAG, "Invalid rating action string: " + string + " cannot be converted to UserRatingAction");
            UserRatingStatus userRatingStatus = UserRatingStatus.NOT_RATED;
            updateUserRatingStatus(userRatingStatus);
            this.mAppHealthUtil.resetAppOpenedNoIncidentCount();
            return userRatingStatus;
        }
    }

    private void updateUserRatingStatusPreference(UserRatingStatus status) {
        this.mSharedPreferences.edit().putString("com.amazon.mp3_key_last_app_rating_action_taken", status.toString()).apply();
    }
}

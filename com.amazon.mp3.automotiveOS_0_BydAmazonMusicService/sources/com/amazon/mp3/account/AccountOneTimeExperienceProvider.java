package com.amazon.mp3.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.AccountManagerKt;
import com.amazon.music.account.AccountManager;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.User;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AccountOneTimeExperienceProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\t\u001a\u00020\bH\u0017R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/account/AccountOneTimeExperienceProvider;", "", "", "experienceKey", "", "markExperienceForAccount", "", "isExperienceMarkedForAccount", "Landroid/content/SharedPreferences;", "getSharedPreferences", "Landroid/content/Context;", "context", "Landroid/content/Context;", "sharedPref", "Landroid/content/SharedPreferences;", "<init>", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class AccountOneTimeExperienceProvider {
    private static final String TAG = AccountOneTimeExperienceProvider.class.getSimpleName();
    private final Context context;
    private final SharedPreferences sharedPref;

    public AccountOneTimeExperienceProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.sharedPref = getSharedPreferences();
    }

    public void markExperienceForAccount(String experienceKey) {
        Intrinsics.checkNotNullParameter(experienceKey, "experienceKey");
        Set<String> stringSet = this.sharedPref.getStringSet(experienceKey, SetsKt__SetsKt.emptySet());
        AccountManager accountManager = AccountManagerSingleton.get();
        Intrinsics.checkNotNullExpressionValue(accountManager, "get()");
        Object objUserResult = AccountManagerKt.userResult(accountManager);
        if (Result.m2816isFailureimpl(objUserResult)) {
            objUserResult = null;
        }
        User user = (User) objUserResult;
        String customerId = user != null ? user.getCustomerId() : null;
        if (customerId == null) {
            Log.error(TAG, "markExperienceForAccount: current customerId is null");
            return;
        }
        if (stringSet != null && stringSet.contains(customerId)) {
            return;
        }
        Set<String> setMutableSetOf = SetsKt__SetsKt.mutableSetOf(customerId);
        if (stringSet != null) {
            for (String it : stringSet) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                setMutableSetOf.add(it);
            }
        }
        SharedPreferences.Editor editor = this.sharedPref.edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        editor.putStringSet(experienceKey, setMutableSetOf);
        editor.apply();
    }

    public boolean isExperienceMarkedForAccount(String experienceKey) {
        Intrinsics.checkNotNullParameter(experienceKey, "experienceKey");
        AccountManager accountManager = AccountManagerSingleton.get();
        Intrinsics.checkNotNullExpressionValue(accountManager, "get()");
        Object objUserResult = AccountManagerKt.userResult(accountManager);
        if (Result.m2816isFailureimpl(objUserResult)) {
            objUserResult = null;
        }
        User user = (User) objUserResult;
        String customerId = user != null ? user.getCustomerId() : null;
        if (customerId == null) {
            Log.error(TAG, "isExperienceMarkedForAccount: current customerId is null");
            return false;
        }
        Set<String> stringSet = this.sharedPref.getStringSet(experienceKey, SetsKt__SetsKt.emptySet());
        if (stringSet == null || stringSet.isEmpty()) {
            return false;
        }
        Iterator<T> it = stringSet.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual((String) it.next(), customerId)) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    public SharedPreferences getSharedPreferences() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(context)");
        return defaultSharedPreferences;
    }
}

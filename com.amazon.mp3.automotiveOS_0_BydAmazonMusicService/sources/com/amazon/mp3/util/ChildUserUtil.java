package com.amazon.mp3.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import com.amazon.mp3.activity.settings.SettingsUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChildUserUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0006R\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/util/ChildUserUtil;", "", "Landroid/content/Context;", "context", "", "isChildUser", "", "getMusicRequestIdentityContextToken", "token", "", "saveMusicRequestIdentityContextToken", "Landroid/content/SharedPreferences;", "prefs", "Landroid/content/SharedPreferences;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ChildUserUtil {
    public static final ChildUserUtil INSTANCE = new ChildUserUtil();
    private static SharedPreferences prefs;

    private ChildUserUtil() {
    }

    public boolean isChildUser(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Intrinsics.areEqual(Settings.Secure.getString(context.getContentResolver(), "USER_ROLE"), "CHILD");
    }

    public final String getMusicRequestIdentityContextToken(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences prefs2 = SettingsUtil.getPrefs(context);
        Intrinsics.checkNotNullExpressionValue(prefs2, "getPrefs(context)");
        prefs = prefs2;
        if (prefs2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("prefs");
            prefs2 = null;
        }
        return prefs2.getString("mric_token_key", null);
    }

    public final void saveMusicRequestIdentityContextToken(Context context, String token) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(token, "token");
        SharedPreferences prefs2 = SettingsUtil.getPrefs(context);
        Intrinsics.checkNotNullExpressionValue(prefs2, "getPrefs(context)");
        prefs = prefs2;
        if (prefs2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("prefs");
            prefs2 = null;
        }
        SharedPreferences.Editor editorEdit = prefs2.edit();
        editorEdit.putString("mric_token_key", token);
        editorEdit.apply();
    }
}

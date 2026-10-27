package com.amazon.mp3.activity;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausToastUtils;

/* JADX INFO: loaded from: classes3.dex */
public class FailedToAddPrimeTrackToastUtil {
    public static void showToastIfNecessaryAndPossible(final Context context) {
        SharedPreferences prefs = SettingsUtil.getPrefs(context);
        if (AmazonApplication.getApplication().getPauseState() || !prefs.contains("KEY_SHOW_FAILED_TO_ADD_TOAST")) {
            return;
        }
        AmazonApplication.getDefaultHandler().post(new Runnable() { // from class: com.amazon.mp3.activity.FailedToAddPrimeTrackToastUtil.1
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferences prefs2 = SettingsUtil.getPrefs(context);
                int i = prefs2.getInt("KEY_SHOW_FAILED_TO_ADD_TOAST", -1);
                prefs2.edit().remove("KEY_SHOW_FAILED_TO_ADD_TOAST").apply();
                BauhausToastUtils.showDefaultToast(context, context.getResources().getQuantityString(R.plurals.dmusic_unable_to_add_songs_message, i, Integer.valueOf(i)), 0);
            }
        });
    }

    public static void setToShowWhenInForeground(Context context, int numberOfFailedAdds) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putInt("KEY_SHOW_FAILED_TO_ADD_TOAST", numberOfFailedAdds);
        editorEdit.apply();
    }
}

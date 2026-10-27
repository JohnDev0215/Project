package com.amazon.mp3.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.activity.settings.SettingsUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OneTimeExperience.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0019\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0016\u0010\u0017J\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\b\u0010\u0006\u001a\u00020\u0004H\u0016R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR$\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u00118\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/util/OneTimeExperience;", "", "", "canPresentExperience", "", "disableExperiencePresentation", "onDestroy", "", "experienceName", "Ljava/lang/String;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "Landroid/content/SharedPreferences;", "prefs", "Landroid/content/SharedPreferences;", "getPrefs", "()Landroid/content/SharedPreferences;", "<init>", "(Ljava/lang/String;Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class OneTimeExperience {
    private static final String TAG = OneTimeExperience.class.getSimpleName();
    private Context context;
    private final String experienceName;
    private final SharedPreferences prefs;

    public OneTimeExperience(String experienceName, Context context) {
        Intrinsics.checkNotNullParameter(experienceName, "experienceName");
        this.experienceName = experienceName;
        this.context = context;
        SharedPreferences prefs = SettingsUtil.getPrefs(context);
        Intrinsics.checkNotNullExpressionValue(prefs, "getPrefs(context)");
        this.prefs = prefs;
    }

    protected final Context getContext() {
        return this.context;
    }

    protected final SharedPreferences getPrefs() {
        return this.prefs;
    }

    public final boolean canPresentExperience() {
        return !this.prefs.getBoolean(this.experienceName, false);
    }

    public final void disableExperiencePresentation() {
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.putBoolean(this.experienceName, true);
        editorEdit.apply();
    }

    public void onDestroy() {
        if (this.context != null) {
            this.context = null;
        }
    }
}

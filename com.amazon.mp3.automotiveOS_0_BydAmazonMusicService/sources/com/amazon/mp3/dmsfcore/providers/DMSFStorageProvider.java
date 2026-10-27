package com.amazon.mp3.dmsfcore.providers;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DMSFStorageProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFStorageProvider;", "Lcom/amazon/music/skyfire/ui/providers/StorageProvider;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Landroid/content/SharedPreferences;", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "sharedPreferences", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DMSFStorageProvider implements StorageProvider {
    private final Context context;

    public DMSFStorageProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.skyfire.ui.providers.StorageProvider
    public SharedPreferences getSharedPreferences() {
        SharedPreferences prefs = SettingsUtil.getPrefs(this.context);
        Intrinsics.checkNotNullExpressionValue(prefs, "getPrefs(context)");
        return prefs;
    }
}

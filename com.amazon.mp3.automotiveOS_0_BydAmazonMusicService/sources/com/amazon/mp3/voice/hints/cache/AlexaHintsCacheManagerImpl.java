package com.amazon.mp3.voice.hints.cache;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.platform.aom.hints.AlexaHints;
import com.amazon.music.platform.aom.hints.dae.DAE;
import com.amazon.music.platform.aom.hints.provider.AlexaHintsCacheManager;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaHintsCacheManagerImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\nH\u0007J\b\u0010\f\u001a\u00020\nH\u0007J\b\u0010\u000e\u001a\u00020\rH\u0007R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/voice/hints/cache/AlexaHintsCacheManagerImpl;", "Lcom/amazon/music/platform/aom/hints/provider/AlexaHintsCacheManager;", "Lcom/amazon/music/platform/aom/hints/dae/DAE;", "dae", "Lcom/amazon/music/platform/aom/hints/AlexaHints;", "getHints", "", "removeAllHints", "alexaHints", "updateHints", "Landroid/content/SharedPreferences;", "getDAESharedPref", "getDAEExpirationSharedPref", "", "getCurrentTimeMillis", "Landroid/content/Context;", "applicationContext", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AlexaHintsCacheManagerImpl implements AlexaHintsCacheManager {
    private final Context applicationContext;

    public AlexaHintsCacheManagerImpl(Context applicationContext) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        this.applicationContext = applicationContext;
    }

    @Override // com.amazon.music.platform.aom.hints.provider.AlexaHintsCacheManager
    public AlexaHints getHints(DAE dae) {
        Intrinsics.checkNotNullParameter(dae, "dae");
        String key = dae.getKey();
        long currentTimeMillis = getCurrentTimeMillis();
        long j = getDAEExpirationSharedPref().getLong(key, 0L);
        if (j < currentTimeMillis) {
            return null;
        }
        Set<String> stringSet = getDAESharedPref().getStringSet(key, null);
        List list = stringSet == null ? null : CollectionsKt___CollectionsKt.toList(stringSet);
        if (list == null) {
            return null;
        }
        return new AlexaHints(list, j);
    }

    @Override // com.amazon.music.platform.aom.hints.provider.AlexaHintsCacheManager
    public void removeAllHints() {
        SharedPreferences.Editor editor = getDAESharedPref().edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        editor.clear();
        editor.apply();
        SharedPreferences.Editor editor2 = getDAEExpirationSharedPref().edit();
        Intrinsics.checkNotNullExpressionValue(editor2, "editor");
        editor2.clear();
        editor2.apply();
    }

    @Override // com.amazon.music.platform.aom.hints.provider.AlexaHintsCacheManager
    public void updateHints(DAE dae, AlexaHints alexaHints) {
        Intrinsics.checkNotNullParameter(dae, "dae");
        Intrinsics.checkNotNullParameter(alexaHints, "alexaHints");
        String key = dae.getKey();
        SharedPreferences.Editor editor = getDAESharedPref().edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        editor.putStringSet(key, CollectionsKt___CollectionsKt.toSet(alexaHints.getHints()));
        editor.apply();
        SharedPreferences.Editor editor2 = getDAEExpirationSharedPref().edit();
        Intrinsics.checkNotNullExpressionValue(editor2, "editor");
        editor2.putLong(key, alexaHints.getExpirationTime());
        editor2.apply();
    }

    @VisibleForTesting
    public final SharedPreferences getDAESharedPref() {
        SharedPreferences sharedPreferences = this.applicationContext.getSharedPreferences("AlexaHintServiceDAE", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "applicationContext.getSh…ME, Context.MODE_PRIVATE)");
        return sharedPreferences;
    }

    @VisibleForTesting
    public final SharedPreferences getDAEExpirationSharedPref() {
        SharedPreferences sharedPreferences = this.applicationContext.getSharedPreferences("AlexaHintServiceDAEExpiration", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "applicationContext.getSh…ME, Context.MODE_PRIVATE)");
        return sharedPreferences;
    }

    @VisibleForTesting
    public final long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}

package com.amazon.mp3.util;

import androidx.collection.LruCache;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: EligibilityConverterUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u0005H\u0007R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/util/EligibilityConverterUtil;", "", "()V", "cachePlaymodeEligibility", "Landroidx/collection/LruCache;", "", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "getPlaymodeEligibilityFromJson", "jsonString", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EligibilityConverterUtil {
    public static final EligibilityConverterUtil INSTANCE = new EligibilityConverterUtil();
    private static final LruCache<String, WeakReference<PlaymodeEligibility>> cachePlaymodeEligibility = new LruCache<>(36);

    private EligibilityConverterUtil() {
    }

    @JvmStatic
    public static final PlaymodeEligibility getPlaymodeEligibilityFromJson(String jsonString) {
        if (jsonString == null || StringsKt__StringsJVMKt.isBlank(jsonString)) {
            return null;
        }
        LruCache<String, WeakReference<PlaymodeEligibility>> lruCache = cachePlaymodeEligibility;
        WeakReference<PlaymodeEligibility> weakReference = lruCache.get(jsonString);
        if ((weakReference == null ? null : weakReference.get()) != null) {
            return weakReference.get();
        }
        PlaymodeEligibility playmodeEligibilityFromJson = PlaymodeEligibility.INSTANCE.getPlaymodeEligibilityFromJson(jsonString);
        if (playmodeEligibilityFromJson == null) {
            return null;
        }
        lruCache.put(jsonString, new WeakReference<>(playmodeEligibilityFromJson));
        return playmodeEligibilityFromJson;
    }
}

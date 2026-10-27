package com.amazon.mp3.library.cache.likes;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.AmazonApplication;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LikesStateCacheManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rJ\u001a\u0010\u000e\u001a\u00020\n2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0010J\u0006\u0010\u0011\u001a\u00020\nJ\u0015\u0010\u0012\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\u0013J\u0015\u0010\u0014\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\u0013J\r\u0010\u0015\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/library/cache/likes/LikesStateCacheManager;", "", "context", "Landroid/content/Context;", "sourceName", "", "(Landroid/content/Context;Ljava/lang/String;)V", "mLikesCache", "Landroid/content/SharedPreferences;", "add", "", "asin", "liked", "", "addMap", "likesMap", "", "clear", "contains", "(Ljava/lang/String;)Ljava/lang/Boolean;", "get", "isEmpty", "()Ljava/lang/Boolean;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LikesStateCacheManager {
    private static LikesStateCacheManager INSTANCE;
    private SharedPreferences mLikesCache;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String INIT_KEY = "Initialized";
    private static final String CACHE_SOURCE_NAME = "LikesStateCache";

    public /* synthetic */ LikesStateCacheManager(Context context, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str);
    }

    private LikesStateCacheManager(Context context, String str) {
        this.mLikesCache = context.getSharedPreferences(str, 0);
    }

    /* JADX INFO: compiled from: LikesStateCacheManager.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/library/cache/likes/LikesStateCacheManager$Companion;", "", "()V", "CACHE_SOURCE_NAME", "", "INIT_KEY", "INSTANCE", "Lcom/amazon/mp3/library/cache/likes/LikesStateCacheManager;", "getInstance", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ LikesStateCacheManager getInstance$default(Companion companion, Context context, int i, Object obj) {
            if ((i & 1) != 0) {
                context = null;
            }
            return companion.getInstance(context);
        }

        public final LikesStateCacheManager getInstance(Context context) {
            if (LikesStateCacheManager.INSTANCE == null) {
                if (context == null) {
                    context = AmazonApplication.getContext();
                }
                Intrinsics.checkNotNullExpressionValue(context, "mContext");
                LikesStateCacheManager.INSTANCE = new LikesStateCacheManager(context, LikesStateCacheManager.CACHE_SOURCE_NAME, null);
            }
            return LikesStateCacheManager.INSTANCE;
        }
    }

    public final Boolean contains(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        SharedPreferences sharedPreferences = this.mLikesCache;
        if (sharedPreferences == null) {
            return null;
        }
        return Boolean.valueOf(sharedPreferences.contains(asin));
    }

    public final void add(String asin, boolean liked) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        SharedPreferences sharedPreferences = this.mLikesCache;
        SharedPreferences.Editor editorEdit = sharedPreferences == null ? null : sharedPreferences.edit();
        if (editorEdit != null) {
            editorEdit.putBoolean(asin, liked);
        }
        if (Intrinsics.areEqual(isEmpty(), Boolean.TRUE) && editorEdit != null) {
            editorEdit.putBoolean(INIT_KEY, true);
        }
        if (editorEdit == null) {
            return;
        }
        editorEdit.commit();
    }

    public final void addMap(Map<String, Boolean> likesMap) {
        Intrinsics.checkNotNullParameter(likesMap, "likesMap");
        SharedPreferences sharedPreferences = this.mLikesCache;
        SharedPreferences.Editor editorEdit = sharedPreferences == null ? null : sharedPreferences.edit();
        for (Map.Entry<String, Boolean> entry : likesMap.entrySet()) {
            if (editorEdit != null) {
                editorEdit.putBoolean(entry.getKey(), entry.getValue().booleanValue());
            }
        }
        if (Intrinsics.areEqual(isEmpty(), Boolean.TRUE) && editorEdit != null) {
            editorEdit.putBoolean(INIT_KEY, true);
        }
        if (editorEdit == null) {
            return;
        }
        editorEdit.commit();
    }

    public final Boolean get(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        SharedPreferences sharedPreferences = this.mLikesCache;
        if (sharedPreferences == null) {
            return null;
        }
        return Boolean.valueOf(sharedPreferences.getBoolean(asin, false));
    }

    public final void clear() {
        SharedPreferences sharedPreferences = this.mLikesCache;
        SharedPreferences.Editor editorEdit = sharedPreferences == null ? null : sharedPreferences.edit();
        if (editorEdit != null) {
            editorEdit.clear();
        }
        if (editorEdit != null) {
            editorEdit.putBoolean(INIT_KEY, false);
        }
        if (editorEdit == null) {
            return;
        }
        editorEdit.commit();
    }

    public final Boolean isEmpty() {
        SharedPreferences sharedPreferences = this.mLikesCache;
        Intrinsics.checkNotNull(sharedPreferences == null ? null : Boolean.valueOf(sharedPreferences.getBoolean(INIT_KEY, false)));
        return Boolean.valueOf(!r0.booleanValue());
    }
}

package com.amazon.music.find.cache;

import android.os.SystemClock;
import com.amazon.music.find.performance.TimestampSupplier;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Cache.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002:\u0001\u001bJ\u0017\u0010\u0004\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006JU\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u00002\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00028\u00010\bj\b\u0012\u0004\u0012\u00028\u0001`\t2&\u0010\n\u001a\"\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u000bj\n\u0012\u0004\u0012\u00028\u0001\u0018\u0001`\u000eH&¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\rH&J\u001d\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0013J\u0015\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\rH&J\b\u0010\u0017\u001a\u00020\u0018H&J\u0016\u0010\u0019\u001a\u0010\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001aH&¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/find/cache/Cache;", "Key", "", "Value", "get", "key", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "loader", "Lkotlin/Function0;", "Lcom/amazon/music/find/cache/CacheLoader;", "callback", "Lkotlin/Function2;", "", "", "Lcom/amazon/music/find/cache/CacheCallback;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "invalidate", "put", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "remove", "(Ljava/lang/Object;)V", "removeAll", "size", "", "toMap", "", "Builder", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface Cache<Key, Value> {
    Value get(Key key);

    Value get(Key key, Function0<? extends Value> loader, Function2<? super Boolean, ? super Value, Unit> callback);

    void invalidate();

    void put(Key key, Value value);

    void remove(Key key);

    void removeAll();

    int size();

    Map<? super Key, Value> toMap();

    /* JADX INFO: compiled from: Cache.kt */
    @Metadata(d1 = {"\u0000-\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t*\u0001\t\u0018\u0000*\n\b\u0002\u0010\u0001 \u0000*\u00020\u0002*\b\b\u0003\u0010\u0003*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0010J\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002\u0006\u0010\u0012\u001a\u00020\u0006J\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002\u0006\u0010\u0014\u001a\u00020\u0006J\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002\u0006\u0010\u0016\u001a\u00020\rJ\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002\u0006\u0010\u0018\u001a\u00020\u0006R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u001c\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/find/cache/Cache$Builder;", "Key", "", "Value", "()V", "mAccessExpireTimeMilliseconds", "", "Ljava/lang/Long;", "mDefaultTimestampSupplier", "com/amazon/music/find/cache/Cache$Builder$mDefaultTimestampSupplier$1", "Lcom/amazon/music/find/cache/Cache$Builder$mDefaultTimestampSupplier$1;", "mMaxCacheSize", "mTimestampSupplier", "Lcom/amazon/music/find/performance/TimestampSupplier;", "mWriteExpireTimeMilliseconds", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/find/cache/Cache;", "withAccessExpireTimeMilliseconds", "accessExpireTimeMilliseconds", "withMaxCacheSize", "size", "withTimestampSupplier", "timestampSupplier", "withWriteExpireTimeMilliseconds", "writeExpireTimeMilliseconds", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder<Key, Value> {
        private Long mAccessExpireTimeMilliseconds;
        private final Cache$Builder$mDefaultTimestampSupplier$1 mDefaultTimestampSupplier = new TimestampSupplier() { // from class: com.amazon.music.find.cache.Cache$Builder$mDefaultTimestampSupplier$1
            @Override // com.amazon.music.find.performance.TimestampSupplier
            public String getUnits() {
                return "milliseconds";
            }

            @Override // com.amazon.music.find.performance.TimestampSupplier
            public long toMilliseconds(long interval) {
                return interval;
            }

            @Override // com.amazon.music.find.performance.TimestampSupplier
            public long now() {
                return SystemClock.elapsedRealtime();
            }
        };
        private Long mMaxCacheSize;
        private TimestampSupplier mTimestampSupplier;
        private Long mWriteExpireTimeMilliseconds;

        public final Builder<Key, Value> withTimestampSupplier(TimestampSupplier timestampSupplier) {
            Intrinsics.checkNotNullParameter(timestampSupplier, "timestampSupplier");
            this.mTimestampSupplier = timestampSupplier;
            return this;
        }

        public final Builder<Key, Value> withAccessExpireTimeMilliseconds(long accessExpireTimeMilliseconds) {
            this.mAccessExpireTimeMilliseconds = Long.valueOf(accessExpireTimeMilliseconds);
            return this;
        }

        public final Builder<Key, Value> withWriteExpireTimeMilliseconds(long writeExpireTimeMilliseconds) {
            this.mWriteExpireTimeMilliseconds = Long.valueOf(writeExpireTimeMilliseconds);
            return this;
        }

        public final Builder<Key, Value> withMaxCacheSize(long size) {
            this.mMaxCacheSize = Long.valueOf(size);
            return this;
        }

        public final Cache<Key, Value> build() {
            Long l = this.mAccessExpireTimeMilliseconds;
            Long l2 = this.mWriteExpireTimeMilliseconds;
            Long l3 = this.mMaxCacheSize;
            TimestampSupplier timestampSupplier = this.mTimestampSupplier;
            if (timestampSupplier == null) {
                timestampSupplier = this.mDefaultTimestampSupplier;
            }
            return new SynchronizedCache(l, l2, l3, timestampSupplier);
        }
    }
}

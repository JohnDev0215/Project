package kotlinx.serialization.internal;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: Caching.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\n\u0010\u000bR7\u0010\b\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00070\u00060\u00038\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlinx/serialization/internal/ParametrizedCacheEntry;", "T", "", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lkotlin/reflect/KType;", "Lkotlin/Result;", "Lkotlinx/serialization/KSerializer;", "serializers", "Ljava/util/concurrent/ConcurrentHashMap;", "<init>", "()V", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
final class ParametrizedCacheEntry<T> {
    private final ConcurrentHashMap<List<KType>, Result<KSerializer<T>>> serializers = new ConcurrentHashMap<>();
}

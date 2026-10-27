package co.touchlab.stately.collections;

import co.touchlab.stately.isolate.IsoStateKt;
import co.touchlab.stately.isolate.IsolateState;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableMap;

/* JADX INFO: compiled from: IsoMutableMap.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010$\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B!\u0012\u001a\b\u0002\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0015\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001cJ\u0015\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001f\u001a\u00020\u001a2\b\u0010 \u001a\u0004\u0018\u00010!H\u0096\u0002J\u0018\u0010\"\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u001b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010#J\b\u0010$\u001a\u00020\u0010H\u0016J\b\u0010%\u001a\u00020\u001aH\u0016J\u001f\u0010&\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001e\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010'J\u001e\u0010(\u001a\u00020\u00182\u0014\u0010)\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010*H\u0016J\u0017\u0010+\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u001b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010#R&\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006,"}, d2 = {"Lco/touchlab/stately/collections/IsoMutableMap;", "K", "V", "Lco/touchlab/stately/isolate/IsolateState;", "", "producer", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "clear", "", "containsKey", "", "key", "(Ljava/lang/Object;)Z", "containsValue", "value", "equals", "other", "", "get", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "hashCode", "isEmpty", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putAll", "from", "", "remove", "stately-iso-collections"}, k = 1, mv = {1, 4, 0})
public class IsoMutableMap<K, V> extends IsolateState<Map<K, V>> implements Map<K, V>, KMutableMap {
    public IsoMutableMap() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ IsoMutableMap(Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function0<Map<K, V>>() { // from class: co.touchlab.stately.collections.IsoMutableMap.1
            @Override // kotlin.jvm.functions.Function0
            public final Map<K, V> invoke() {
                return new LinkedHashMap();
            }
        } : function0);
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return getValues();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IsoMutableMap(Function0<? extends Map<K, V>> producer) {
        super(IsoStateKt.createState$default(producer, null, 2, null));
        Intrinsics.checkNotNullParameter(producer, "producer");
    }

    public int getSize() {
        return ((Number) access(new Function1<Map<K, V>, Integer>() { // from class: co.touchlab.stately.collections.IsoMutableMap.size.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Object obj) {
                return Integer.valueOf(invoke((Map) obj));
            }

            public final int invoke(Map<K, V> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.size();
            }
        })).intValue();
    }

    @Override // java.util.Map
    public boolean containsKey(final Object key) {
        return ((Boolean) access(new Function1<Map<K, V>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableMap.containsKey.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Map) obj));
            }

            public final boolean invoke(Map<K, V> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.containsKey(key);
            }
        })).booleanValue();
    }

    @Override // java.util.Map
    public boolean containsValue(final Object value) {
        return ((Boolean) access(new Function1<Map<K, V>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableMap.containsValue.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Map) obj));
            }

            public final boolean invoke(Map<K, V> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.containsValue(value);
            }
        })).booleanValue();
    }

    @Override // java.util.Map
    public V get(final Object key) {
        return (V) access(new Function1<Map<K, V>, V>() { // from class: co.touchlab.stately.collections.IsoMutableMap.get.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final V invoke(Map<K, V> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.get(key);
            }
        });
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return ((Boolean) access(new Function1<Map<K, V>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableMap.isEmpty.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Map) obj));
            }

            public final boolean invoke(Map<K, V> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.isEmpty();
            }
        })).booleanValue();
    }

    public Set<Map.Entry<K, V>> getEntries() {
        throw new UnsupportedOperationException("Can't leak mutable reference");
    }

    public Set<K> getKeys() {
        return (Set) access(new Function1<Map<K, V>, IsoMutableSet<K>>() { // from class: co.touchlab.stately.collections.IsoMutableMap$keys$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final IsoMutableSet<K> invoke(Map<K, V> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new IsoMutableSet<>(this.this$0.fork(it.keySet()));
            }
        });
    }

    public Collection<V> getValues() {
        return (Collection) access(new Function1<Map<K, V>, IsoMutableCollection<V>>() { // from class: co.touchlab.stately.collections.IsoMutableMap.values.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final IsoMutableCollection<V> invoke(Map<K, V> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new IsoMutableCollection<>(IsoMutableMap.this.fork(it.values()));
            }
        });
    }

    @Override // java.util.Map
    public void clear() {
        access(new Function1<Map<K, V>, Unit>() { // from class: co.touchlab.stately.collections.IsoMutableMap.clear.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Map) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Map<K, V> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.clear();
            }
        });
    }

    @Override // java.util.Map
    public V put(final K key, final V value) {
        return (V) access(new Function1<Map<K, V>, V>() { // from class: co.touchlab.stately.collections.IsoMutableMap.put.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public final V invoke(Map<K, V> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return (V) it.put(key, value);
            }
        });
    }

    @Override // java.util.Map
    public void putAll(final Map<? extends K, ? extends V> from) {
        Intrinsics.checkNotNullParameter(from, "from");
        access(new Function1<Map<K, V>, Unit>() { // from class: co.touchlab.stately.collections.IsoMutableMap.putAll.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Map) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Map<K, V> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.putAll(from);
            }
        });
    }

    @Override // java.util.Map
    public V remove(final Object key) {
        return (V) access(new Function1<Map<K, V>, V>() { // from class: co.touchlab.stately.collections.IsoMutableMap.remove.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final V invoke(Map<K, V> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.remove(key);
            }
        });
    }

    @Override // java.util.Map
    public boolean equals(final Object other) {
        return ((Boolean) access(new Function1<Map<K, V>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableMap.equals.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Map) obj));
            }

            public final boolean invoke(Map<K, V> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.equals(other);
            }
        })).booleanValue();
    }

    @Override // java.util.Map
    public int hashCode() {
        return ((Number) access(new Function1<Map<K, V>, Integer>() { // from class: co.touchlab.stately.collections.IsoMutableMap.hashCode.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Object obj) {
                return Integer.valueOf(invoke((Map) obj));
            }

            public final int invoke(Map<K, V> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.hashCode();
            }
        })).intValue();
    }
}

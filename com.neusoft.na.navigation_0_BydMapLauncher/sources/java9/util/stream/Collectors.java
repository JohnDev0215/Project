package java9.util.stream;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java9.util.DoubleSummaryStatistics;
import java9.util.IntSummaryStatistics;
import java9.util.LongSummaryStatistics;
import java9.util.Maps;
import java9.util.Objects;
import java9.util.Optional;
import java9.util.StringJoiner;
import java9.util.concurrent.ConcurrentMaps;
import java9.util.function.BiConsumer;
import java9.util.function.BiFunction;
import java9.util.function.BinaryOperator;
import java9.util.function.BinaryOperators;
import java9.util.function.Consumer;
import java9.util.function.Function;
import java9.util.function.Functions;
import java9.util.function.Predicate;
import java9.util.function.Supplier;
import java9.util.function.ToDoubleFunction;
import java9.util.function.ToIntFunction;
import java9.util.function.ToLongFunction;
import java9.util.stream.Collector;

/* JADX INFO: loaded from: classes3.dex */
public final class Collectors {
    static final Set<Collector.Characteristics> CH_CONCURRENT_ID = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT, Collector.Characteristics.UNORDERED, Collector.Characteristics.IDENTITY_FINISH));
    static final Set<Collector.Characteristics> CH_CONCURRENT_NOID = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT, Collector.Characteristics.UNORDERED));
    static final Set<Collector.Characteristics> CH_ID = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
    static final Set<Collector.Characteristics> CH_UNORDERED_ID = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.UNORDERED, Collector.Characteristics.IDENTITY_FINISH));
    static final Set<Collector.Characteristics> CH_NOID = Collections.emptySet();
    static final Set<Collector.Characteristics> CH_UNORDERED_NOID = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.UNORDERED));
    static final Supplier<DoubleSummaryStatistics> DBL_SUM_STATS = Collectors$$Lambda$91.instance;
    static final Supplier<IntSummaryStatistics> INT_SUM_STATS = Collectors$$Lambda$92.instance;
    static final Supplier<LongSummaryStatistics> LNG_SUM_STATS = Collectors$$Lambda$93.instance;
    private static final Function<Map<?, ?>, Map<?, ?>> UNMOD_MAP_FINISHER = Collectors$$Lambda$94.instance;
    private static final BiConsumer<List<Object>, ?> LIST_ADD = Collectors$$Lambda$95.instance;
    private static final BiConsumer<Set<Object>, ?> SET_ADD = Collectors$$Lambda$96.instance;

    static /* synthetic */ double[] lambda$averagingDouble$39() {
        return new double[4];
    }

    static /* synthetic */ long[] lambda$averagingInt$31() {
        return new long[2];
    }

    static /* synthetic */ long[] lambda$averagingLong$35() {
        return new long[2];
    }

    static /* synthetic */ long lambda$counting$18(Object obj) {
        return 1L;
    }

    static /* synthetic */ double[] lambda$summingDouble$27() {
        return new double[3];
    }

    static /* synthetic */ int[] lambda$summingInt$19() {
        return new int[1];
    }

    static /* synthetic */ long[] lambda$summingLong$23() {
        return new long[1];
    }

    private Collectors() {
    }

    private static <K, V> Supplier<Map<K, V>> hashMapNew() {
        return Collectors$$Lambda$1.instance;
    }

    private static <K, V> Supplier<ConcurrentMap<K, V>> concHashMapNew() {
        return Collectors$$Lambda$2.instance;
    }

    private static <K, V> Supplier<Map<K, V>> concHashMapNew2() {
        return concHashMapNew();
    }

    private static <T> Supplier<List<T>> arrayListNew() {
        return Collectors$$Lambda$3.instance;
    }

    private static <T> Supplier<Set<T>> hashSetNew() {
        return Collectors$$Lambda$4.instance;
    }

    static /* synthetic */ Map lambda$static$0(Map map) {
        return Maps.ofEntries((Map.Entry[]) map.entrySet().toArray(new Map.Entry[0]));
    }

    private static final <K, U> Function<Map<K, U>, Map<K, U>> unmodMapFinisher() {
        return (Function<Map<K, U>, Map<K, U>>) UNMOD_MAP_FINISHER;
    }

    private static final <T> BiConsumer<List<T>, T> listAdd() {
        return (BiConsumer<List<T>, T>) LIST_ADD;
    }

    private static final <T> BiConsumer<Set<T>, T> setAdd() {
        return (BiConsumer<Set<T>, T>) SET_ADD;
    }

    public static <T, R> Collector<T, R, R> of(Supplier<R> supplier, BiConsumer<R, T> biConsumer, BinaryOperator<R> binaryOperator, Collector.Characteristics... characteristicsArr) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(biConsumer);
        Objects.requireNonNull(binaryOperator);
        Objects.requireNonNull(characteristicsArr);
        return new CollectorImpl(supplier, biConsumer, binaryOperator, characteristicsArr.length == 0 ? CH_ID : Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH, characteristicsArr)));
    }

    public static <T, A, R> Collector<T, A, R> of(Supplier<A> supplier, BiConsumer<A, T> biConsumer, BinaryOperator<A> binaryOperator, Function<A, R> function, Collector.Characteristics... characteristicsArr) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(biConsumer);
        Objects.requireNonNull(binaryOperator);
        Objects.requireNonNull(function);
        Objects.requireNonNull(characteristicsArr);
        Set<Collector.Characteristics> setUnmodifiableSet = CH_NOID;
        if (characteristicsArr.length > 0) {
            EnumSet enumSetNoneOf = EnumSet.noneOf(Collector.Characteristics.class);
            Collections.addAll(enumSetNoneOf, characteristicsArr);
            setUnmodifiableSet = Collections.unmodifiableSet(enumSetNoneOf);
        }
        return new CollectorImpl(supplier, biConsumer, binaryOperator, function, setUnmodifiableSet);
    }

    private static IllegalStateException duplicateKeyException(Object obj, Object obj2, Object obj3) {
        return new IllegalStateException(String.format("Duplicate key %s (attempted merging values %s and %s)", obj, obj2, obj3));
    }

    private static <K, V, M extends Map<K, V>> BinaryOperator<M> uniqKeysMapMerger() {
        return Collectors$$Lambda$5.instance;
    }

    static /* synthetic */ Map lambda$uniqKeysMapMerger$1(Map map, Map map2) {
        Object objPutIfAbsent;
        for (Map.Entry entry : map2.entrySet()) {
            Object key = entry.getKey();
            Object objRequireNonNull = Objects.requireNonNull(entry.getValue());
            if (map instanceof ConcurrentMap) {
                objPutIfAbsent = ((ConcurrentMap) map).putIfAbsent(key, objRequireNonNull);
            } else {
                objPutIfAbsent = Maps.putIfAbsent(map, key, objRequireNonNull);
            }
            if (objPutIfAbsent != null) {
                throw duplicateKeyException(key, objPutIfAbsent, objRequireNonNull);
            }
        }
        return map;
    }

    private static <T, K, V> BiConsumer<Map<K, V>, T> uniqKeysMapAccumulator(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        return Collectors$$Lambda$6.lambdaFactory$(function, function2);
    }

    static /* synthetic */ void lambda$uniqKeysMapAccumulator$2(Function function, Function function2, Map map, Object obj) {
        Object objPutIfAbsent;
        Object objApply = function.apply(obj);
        Object objRequireNonNull = Objects.requireNonNull(function2.apply(obj));
        if (map instanceof ConcurrentMap) {
            objPutIfAbsent = ((ConcurrentMap) map).putIfAbsent(objApply, objRequireNonNull);
        } else {
            objPutIfAbsent = Maps.putIfAbsent(map, objApply, objRequireNonNull);
        }
        if (objPutIfAbsent != null) {
            throw duplicateKeyException(objApply, objPutIfAbsent, objRequireNonNull);
        }
    }

    static class CollectorImpl<T, A, R> implements Collector<T, A, R> {
        private final BiConsumer<A, T> accumulator;
        private final Set<Collector.Characteristics> characteristics;
        private final BinaryOperator<A> combiner;
        private final Function<A, R> finisher;
        private final Supplier<A> supplier;

        static /* synthetic */ Object lambda$new$0(Object obj) {
            return obj;
        }

        CollectorImpl(Supplier<A> supplier, BiConsumer<A, T> biConsumer, BinaryOperator<A> binaryOperator, Function<A, R> function, Set<Collector.Characteristics> set) {
            this.supplier = supplier;
            this.accumulator = biConsumer;
            this.combiner = binaryOperator;
            this.finisher = function;
            this.characteristics = set;
        }

        CollectorImpl(Supplier<A> supplier, BiConsumer<A, T> biConsumer, BinaryOperator<A> binaryOperator, Set<Collector.Characteristics> set) {
            this(supplier, biConsumer, binaryOperator, Collectors$CollectorImpl$$Lambda$1.instance, set);
        }

        @Override // java9.util.stream.Collector
        public BiConsumer<A, T> accumulator() {
            return this.accumulator;
        }

        @Override // java9.util.stream.Collector
        public Supplier<A> supplier() {
            return this.supplier;
        }

        @Override // java9.util.stream.Collector
        public BinaryOperator<A> combiner() {
            return this.combiner;
        }

        @Override // java9.util.stream.Collector
        public Function<A, R> finisher() {
            return this.finisher;
        }

        @Override // java9.util.stream.Collector
        public Set<Collector.Characteristics> characteristics() {
            return this.characteristics;
        }
    }

    public static <T, C extends Collection<T>> Collector<T, ?, C> toCollection(Supplier<C> supplier) {
        return new CollectorImpl(supplier, Collectors$$Lambda$7.instance, Collectors$$Lambda$8.instance, CH_ID);
    }

    static /* synthetic */ Collection lambda$toCollection$3(Collection collection, Collection collection2) {
        collection.addAll(collection2);
        return collection;
    }

    public static <T> Collector<T, ?, List<T>> toList() {
        return new CollectorImpl(arrayListNew(), listAdd(), Collectors$$Lambda$9.instance, CH_ID);
    }

    static /* synthetic */ List lambda$toList$4(List list, List list2) {
        list.addAll(list2);
        return list;
    }

    public static <T> Collector<T, ?, List<T>> toUnmodifiableList() {
        return new CollectorImpl(arrayListNew(), listAdd(), Collectors$$Lambda$10.instance, Collectors$$Lambda$11.instance, CH_NOID);
    }

    static /* synthetic */ List lambda$toUnmodifiableList$5(List list, List list2) {
        list.addAll(list2);
        return list;
    }

    public static <T> Collector<T, ?, Set<T>> toSet() {
        return new CollectorImpl(hashSetNew(), setAdd(), Collectors$$Lambda$12.instance, CH_UNORDERED_ID);
    }

    static /* synthetic */ Set lambda$toSet$7(Set set, Set set2) {
        if (set.size() < set2.size()) {
            set2.addAll(set);
            return set2;
        }
        set.addAll(set2);
        return set;
    }

    public static <T> Collector<T, ?, Set<T>> toUnmodifiableSet() {
        return new CollectorImpl(hashSetNew(), setAdd(), Collectors$$Lambda$13.instance, Collectors$$Lambda$14.instance, CH_UNORDERED_NOID);
    }

    static /* synthetic */ Set lambda$toUnmodifiableSet$8(Set set, Set set2) {
        if (set.size() < set2.size()) {
            set2.addAll(set);
            return set2;
        }
        set.addAll(set2);
        return set;
    }

    public static Collector<CharSequence, ?, String> joining() {
        return new CollectorImpl(Collectors$$Lambda$15.instance, Collectors$$Lambda$16.instance, Collectors$$Lambda$17.instance, Collectors$$Lambda$18.instance, CH_NOID);
    }

    static /* synthetic */ StringBuilder lambda$joining$10(StringBuilder sb, StringBuilder sb2) {
        sb.append((CharSequence) sb2);
        return sb;
    }

    public static Collector<CharSequence, ?, String> joining(CharSequence charSequence) {
        return joining(charSequence, "", "");
    }

    public static Collector<CharSequence, ?, String> joining(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return new CollectorImpl(Collectors$$Lambda$19.lambdaFactory$(charSequence, charSequence2, charSequence3), Collectors$$Lambda$20.instance, Collectors$$Lambda$21.instance, Collectors$$Lambda$22.instance, CH_NOID);
    }

    static /* synthetic */ StringJoiner lambda$joining$11(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return new StringJoiner(charSequence, charSequence2, charSequence3);
    }

    private static <K, V, M extends Map<K, V>> BinaryOperator<M> mapMerger(BinaryOperator<V> binaryOperator) {
        return Collectors$$Lambda$23.lambdaFactory$(binaryOperator);
    }

    static /* synthetic */ Map lambda$mapMerger$12(BinaryOperator binaryOperator, Map map, Map map2) {
        for (Map.Entry entry : map2.entrySet()) {
            Maps.merge(map, entry.getKey(), entry.getValue(), binaryOperator);
        }
        return map;
    }

    private static <K, V, M extends ConcurrentMap<K, V>> BinaryOperator<M> mapMergerConcurrent(BinaryOperator<V> binaryOperator) {
        return Collectors$$Lambda$24.lambdaFactory$(binaryOperator);
    }

    static /* synthetic */ ConcurrentMap lambda$mapMergerConcurrent$13(BinaryOperator binaryOperator, ConcurrentMap concurrentMap, ConcurrentMap concurrentMap2) {
        for (Map.Entry entry : concurrentMap2.entrySet()) {
            ConcurrentMaps.merge(concurrentMap, entry.getKey(), entry.getValue(), binaryOperator);
        }
        return concurrentMap;
    }

    public static <T, U, A, R> Collector<T, ?, R> mapping(Function<? super T, ? extends U> function, Collector<? super U, A, R> collector) {
        return new CollectorImpl(collector.supplier(), Collectors$$Lambda$25.lambdaFactory$(collector.accumulator(), function), collector.combiner(), collector.finisher(), collector.characteristics());
    }

    static /* synthetic */ void lambda$mapping$14(BiConsumer biConsumer, Function function, Object obj, Object obj2) {
        biConsumer.accept(obj, function.apply(obj2));
    }

    public static <T, U, A, R> Collector<T, ?, R> flatMapping(Function<? super T, ? extends Stream<? extends U>> function, Collector<? super U, A, R> collector) {
        return new CollectorImpl(collector.supplier(), Collectors$$Lambda$26.lambdaFactory$(function, collector.accumulator()), collector.combiner(), collector.finisher(), collector.characteristics());
    }

    static /* synthetic */ void lambda$flatMapping$16(Function function, BiConsumer biConsumer, Object obj, Object obj2) throws Throwable {
        Stream stream = null;
        try {
            Stream stream2 = (Stream) function.apply(obj2);
            if (stream2 != null) {
                try {
                    stream2.sequential().forEach(Collectors$$Lambda$90.lambdaFactory$(biConsumer, obj));
                } catch (Throwable th) {
                    th = th;
                    stream = stream2;
                    if (stream != null) {
                        try {
                            stream.close();
                        } catch (Exception unused) {
                        }
                    }
                    throw th;
                }
            }
            if (stream2 != null) {
                try {
                    stream2.close();
                } catch (Exception unused2) {
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static <T, A, R> Collector<T, ?, R> filtering(Predicate<? super T> predicate, Collector<? super T, A, R> collector) {
        return new CollectorImpl(collector.supplier(), Collectors$$Lambda$27.lambdaFactory$(predicate, collector.accumulator()), collector.combiner(), collector.finisher(), collector.characteristics());
    }

    static /* synthetic */ void lambda$filtering$17(Predicate predicate, BiConsumer biConsumer, Object obj, Object obj2) {
        if (predicate.test(obj2)) {
            biConsumer.accept(obj, obj2);
        }
    }

    public static <T, A, R, RR> Collector<T, A, RR> collectingAndThen(Collector<T, A, R> collector, Function<R, RR> function) {
        Set<Collector.Characteristics> setCharacteristics = collector.characteristics();
        if (setCharacteristics.contains(Collector.Characteristics.IDENTITY_FINISH)) {
            if (setCharacteristics.size() == 1) {
                setCharacteristics = CH_NOID;
            } else {
                EnumSet enumSetCopyOf = EnumSet.copyOf((Collection) setCharacteristics);
                enumSetCopyOf.remove(Collector.Characteristics.IDENTITY_FINISH);
                setCharacteristics = Collections.unmodifiableSet(enumSetCopyOf);
            }
        }
        return new CollectorImpl(collector.supplier(), collector.accumulator(), collector.combiner(), Functions.andThen(collector.finisher(), function), setCharacteristics);
    }

    public static <T> Collector<T, ?, Long> counting() {
        return summingLong(Collectors$$Lambda$28.instance);
    }

    public static <T> Collector<T, ?, Optional<T>> minBy(Comparator<? super T> comparator) {
        return reducing(BinaryOperators.minBy(comparator));
    }

    public static <T> Collector<T, ?, Optional<T>> maxBy(Comparator<? super T> comparator) {
        return reducing(BinaryOperators.maxBy(comparator));
    }

    public static <T> Collector<T, ?, Integer> summingInt(ToIntFunction<? super T> toIntFunction) {
        return new CollectorImpl(Collectors$$Lambda$29.instance, Collectors$$Lambda$30.lambdaFactory$(toIntFunction), Collectors$$Lambda$31.instance, Collectors$$Lambda$32.instance, CH_NOID);
    }

    static /* synthetic */ void lambda$summingInt$20(ToIntFunction toIntFunction, int[] iArr, Object obj) {
        iArr[0] = iArr[0] + toIntFunction.applyAsInt(obj);
    }

    static /* synthetic */ int[] lambda$summingInt$21(int[] iArr, int[] iArr2) {
        iArr[0] = iArr[0] + iArr2[0];
        return iArr;
    }

    public static <T> Collector<T, ?, Long> summingLong(ToLongFunction<? super T> toLongFunction) {
        return new CollectorImpl(Collectors$$Lambda$33.instance, Collectors$$Lambda$34.lambdaFactory$(toLongFunction), Collectors$$Lambda$35.instance, Collectors$$Lambda$36.instance, CH_NOID);
    }

    static /* synthetic */ void lambda$summingLong$24(ToLongFunction toLongFunction, long[] jArr, Object obj) {
        jArr[0] = jArr[0] + toLongFunction.applyAsLong(obj);
    }

    static /* synthetic */ long[] lambda$summingLong$25(long[] jArr, long[] jArr2) {
        jArr[0] = jArr[0] + jArr2[0];
        return jArr;
    }

    public static <T> Collector<T, ?, Double> summingDouble(ToDoubleFunction<? super T> toDoubleFunction) {
        return new CollectorImpl(Collectors$$Lambda$37.instance, Collectors$$Lambda$38.lambdaFactory$(toDoubleFunction), Collectors$$Lambda$39.instance, Collectors$$Lambda$40.instance, CH_NOID);
    }

    static /* synthetic */ void lambda$summingDouble$28(ToDoubleFunction toDoubleFunction, double[] dArr, Object obj) {
        double dApplyAsDouble = toDoubleFunction.applyAsDouble(obj);
        sumWithCompensation(dArr, dApplyAsDouble);
        dArr[2] = dArr[2] + dApplyAsDouble;
    }

    static /* synthetic */ double[] lambda$summingDouble$29(double[] dArr, double[] dArr2) {
        sumWithCompensation(dArr, dArr2[0]);
        dArr[2] = dArr[2] + dArr2[2];
        return sumWithCompensation(dArr, -dArr2[1]);
    }

    static /* synthetic */ Double lambda$summingDouble$30(double[] dArr) {
        return Double.valueOf(computeFinalSum(dArr));
    }

    static double[] sumWithCompensation(double[] dArr, double d) {
        double d2 = d - dArr[1];
        double d3 = dArr[0];
        double d4 = d3 + d2;
        dArr[1] = (d4 - d3) - d2;
        dArr[0] = d4;
        return dArr;
    }

    static double computeFinalSum(double[] dArr) {
        double d = dArr[0] - dArr[1];
        double d2 = dArr[dArr.length - 1];
        return (Double.isNaN(d) && Double.isInfinite(d2)) ? d2 : d;
    }

    public static <T> Collector<T, ?, Double> averagingInt(ToIntFunction<? super T> toIntFunction) {
        return new CollectorImpl(Collectors$$Lambda$41.instance, Collectors$$Lambda$42.lambdaFactory$(toIntFunction), Collectors$$Lambda$43.instance, Collectors$$Lambda$44.instance, CH_NOID);
    }

    static /* synthetic */ void lambda$averagingInt$32(ToIntFunction toIntFunction, long[] jArr, Object obj) {
        jArr[0] = jArr[0] + ((long) toIntFunction.applyAsInt(obj));
        jArr[1] = jArr[1] + 1;
    }

    static /* synthetic */ long[] lambda$averagingInt$33(long[] jArr, long[] jArr2) {
        jArr[0] = jArr[0] + jArr2[0];
        jArr[1] = jArr[1] + jArr2[1];
        return jArr;
    }

    static /* synthetic */ Double lambda$averagingInt$34(long[] jArr) {
        long j = jArr[1];
        return Double.valueOf(j == 0 ? 0.0d : jArr[0] / j);
    }

    public static <T> Collector<T, ?, Double> averagingLong(ToLongFunction<? super T> toLongFunction) {
        return new CollectorImpl(Collectors$$Lambda$45.instance, Collectors$$Lambda$46.lambdaFactory$(toLongFunction), Collectors$$Lambda$47.instance, Collectors$$Lambda$48.instance, CH_NOID);
    }

    static /* synthetic */ void lambda$averagingLong$36(ToLongFunction toLongFunction, long[] jArr, Object obj) {
        jArr[0] = jArr[0] + toLongFunction.applyAsLong(obj);
        jArr[1] = jArr[1] + 1;
    }

    static /* synthetic */ long[] lambda$averagingLong$37(long[] jArr, long[] jArr2) {
        jArr[0] = jArr[0] + jArr2[0];
        jArr[1] = jArr[1] + jArr2[1];
        return jArr;
    }

    static /* synthetic */ Double lambda$averagingLong$38(long[] jArr) {
        long j = jArr[1];
        return Double.valueOf(j == 0 ? 0.0d : jArr[0] / j);
    }

    public static <T> Collector<T, ?, Double> averagingDouble(ToDoubleFunction<? super T> toDoubleFunction) {
        return new CollectorImpl(Collectors$$Lambda$49.instance, Collectors$$Lambda$50.lambdaFactory$(toDoubleFunction), Collectors$$Lambda$51.instance, Collectors$$Lambda$52.instance, CH_NOID);
    }

    static /* synthetic */ void lambda$averagingDouble$40(ToDoubleFunction toDoubleFunction, double[] dArr, Object obj) {
        double dApplyAsDouble = toDoubleFunction.applyAsDouble(obj);
        sumWithCompensation(dArr, dApplyAsDouble);
        dArr[2] = dArr[2] + 1.0d;
        dArr[3] = dArr[3] + dApplyAsDouble;
    }

    static /* synthetic */ double[] lambda$averagingDouble$41(double[] dArr, double[] dArr2) {
        sumWithCompensation(dArr, dArr2[0]);
        sumWithCompensation(dArr, -dArr2[1]);
        dArr[2] = dArr[2] + dArr2[2];
        dArr[3] = dArr[3] + dArr2[3];
        return dArr;
    }

    static /* synthetic */ Double lambda$averagingDouble$42(double[] dArr) {
        return Double.valueOf(dArr[2] != 0.0d ? computeFinalSum(dArr) / dArr[2] : 0.0d);
    }

    public static <T> Collector<T, ?, T> reducing(T t, BinaryOperator<T> binaryOperator) {
        return new CollectorImpl(boxSupplier(t), Collectors$$Lambda$53.lambdaFactory$(binaryOperator), Collectors$$Lambda$54.lambdaFactory$(binaryOperator), Collectors$$Lambda$55.instance, CH_NOID);
    }

    static /* synthetic */ void lambda$reducing$43(BinaryOperator binaryOperator, Object[] objArr, Object obj) {
        objArr[0] = binaryOperator.apply(objArr[0], obj);
    }

    static /* synthetic */ Object[] lambda$reducing$44(BinaryOperator binaryOperator, Object[] objArr, Object[] objArr2) {
        objArr[0] = binaryOperator.apply(objArr[0], objArr2[0]);
        return objArr;
    }

    static /* synthetic */ Object lambda$reducing$45(Object[] objArr) {
        return objArr[0];
    }

    private static <T> Supplier<T[]> boxSupplier(T t) {
        return Collectors$$Lambda$56.lambdaFactory$(t);
    }

    static /* synthetic */ Object[] lambda$boxSupplier$46(Object obj) {
        return new Object[]{obj};
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: java9.util.stream.Collectors$1OptionalBox, reason: invalid class name */
    class C1OptionalBox<T> implements Consumer<T> {
        final /* synthetic */ BinaryOperator val$op;
        T value = null;
        boolean present = false;

        C1OptionalBox(BinaryOperator binaryOperator) {
            this.val$op = binaryOperator;
        }

        @Override // java9.util.function.Consumer
        public void accept(T t) {
            if (this.present) {
                this.value = this.val$op.apply(this.value, t);
            } else {
                this.value = t;
                this.present = true;
            }
        }
    }

    public static <T> Collector<T, ?, Optional<T>> reducing(BinaryOperator<T> binaryOperator) {
        return new CollectorImpl(Collectors$$Lambda$57.lambdaFactory$(binaryOperator), Collectors$$Lambda$58.instance, Collectors$$Lambda$59.instance, Collectors$$Lambda$60.instance, CH_NOID);
    }

    static /* synthetic */ C1OptionalBox lambda$reducing$47(BinaryOperator binaryOperator) {
        return new C1OptionalBox(binaryOperator);
    }

    static /* synthetic */ C1OptionalBox lambda$reducing$48(C1OptionalBox c1OptionalBox, C1OptionalBox c1OptionalBox2) {
        if (c1OptionalBox2.present) {
            c1OptionalBox.accept(c1OptionalBox2.value);
        }
        return c1OptionalBox;
    }

    static /* synthetic */ Optional lambda$reducing$49(C1OptionalBox c1OptionalBox) {
        return Optional.ofNullable(c1OptionalBox.value);
    }

    public static <T, U> Collector<T, ?, U> reducing(U u, Function<? super T, ? extends U> function, BinaryOperator<U> binaryOperator) {
        return new CollectorImpl(boxSupplier(u), Collectors$$Lambda$61.lambdaFactory$(binaryOperator, function), Collectors$$Lambda$62.lambdaFactory$(binaryOperator), Collectors$$Lambda$63.instance, CH_NOID);
    }

    static /* synthetic */ void lambda$reducing$50(BinaryOperator binaryOperator, Function function, Object[] objArr, Object obj) {
        objArr[0] = binaryOperator.apply(objArr[0], function.apply(obj));
    }

    static /* synthetic */ Object[] lambda$reducing$51(BinaryOperator binaryOperator, Object[] objArr, Object[] objArr2) {
        objArr[0] = binaryOperator.apply(objArr[0], objArr2[0]);
        return objArr;
    }

    static /* synthetic */ Object lambda$reducing$52(Object[] objArr) {
        return objArr[0];
    }

    public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> function) {
        return groupingBy(function, toList());
    }

    public static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> function, Collector<? super T, A, D> collector) {
        return groupingBy(function, hashMapNew(), collector);
    }

    public static <T, K, D, A, M extends Map<K, D>> Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> function, Supplier<M> supplier, Collector<? super T, A, D> collector) {
        BiConsumer biConsumerLambdaFactory$ = Collectors$$Lambda$64.lambdaFactory$(function, collector.supplier(), collector.accumulator());
        BinaryOperator binaryOperatorMapMerger = mapMerger(collector.combiner());
        if (collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)) {
            return new CollectorImpl(supplier, biConsumerLambdaFactory$, binaryOperatorMapMerger, CH_ID);
        }
        return new CollectorImpl(supplier, biConsumerLambdaFactory$, binaryOperatorMapMerger, Collectors$$Lambda$65.lambdaFactory$(collector.finisher()), CH_NOID);
    }

    static /* synthetic */ void lambda$groupingBy$54(Function function, Supplier supplier, BiConsumer biConsumer, Map map, Object obj) {
        biConsumer.accept(Maps.computeIfAbsent(map, Objects.requireNonNull(function.apply(obj), "element cannot be mapped to a null key"), Collectors$$Lambda$89.lambdaFactory$(supplier)), obj);
    }

    static /* synthetic */ Object lambda$null$53(Supplier supplier, Object obj) {
        return supplier.get();
    }

    static /* synthetic */ Map lambda$groupingBy$56(Function function, Map map) {
        Maps.replaceAll(map, Collectors$$Lambda$88.lambdaFactory$(function));
        return map;
    }

    static /* synthetic */ Object lambda$null$55(Function function, Object obj, Object obj2) {
        return function.apply(obj2);
    }

    public static <T, K> Collector<T, ?, ConcurrentMap<K, List<T>>> groupingByConcurrent(Function<? super T, ? extends K> function) {
        return groupingByConcurrent(function, concHashMapNew(), toList());
    }

    public static <T, K, A, D> Collector<T, ?, ConcurrentMap<K, D>> groupingByConcurrent(Function<? super T, ? extends K> function, Collector<? super T, A, D> collector) {
        return groupingByConcurrent(function, concHashMapNew(), collector);
    }

    public static <T, K, A, D, M extends ConcurrentMap<K, D>> Collector<T, ?, M> groupingByConcurrent(Function<? super T, ? extends K> function, Supplier<M> supplier, Collector<? super T, A, D> collector) {
        BiConsumer biConsumerLambdaFactory$;
        Supplier<A> supplier2 = collector.supplier();
        BiConsumer<A, ? super T> biConsumerAccumulator = collector.accumulator();
        BinaryOperator binaryOperatorMapMergerConcurrent = mapMergerConcurrent(collector.combiner());
        if (collector.characteristics().contains(Collector.Characteristics.CONCURRENT)) {
            biConsumerLambdaFactory$ = Collectors$$Lambda$66.lambdaFactory$(function, supplier2, biConsumerAccumulator);
        } else {
            biConsumerLambdaFactory$ = Collectors$$Lambda$67.lambdaFactory$(function, supplier2, biConsumerAccumulator);
        }
        BiConsumer biConsumer = biConsumerLambdaFactory$;
        if (collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)) {
            return new CollectorImpl(supplier, biConsumer, binaryOperatorMapMergerConcurrent, CH_CONCURRENT_ID);
        }
        return new CollectorImpl(supplier, biConsumer, binaryOperatorMapMergerConcurrent, Collectors$$Lambda$68.lambdaFactory$(collector.finisher()), CH_CONCURRENT_NOID);
    }

    static /* synthetic */ void lambda$groupingByConcurrent$58(Function function, Supplier supplier, BiConsumer biConsumer, ConcurrentMap concurrentMap, Object obj) {
        biConsumer.accept(ConcurrentMaps.computeIfAbsent(concurrentMap, Objects.requireNonNull(function.apply(obj), "element cannot be mapped to a null key"), Collectors$$Lambda$87.lambdaFactory$(supplier)), obj);
    }

    static /* synthetic */ Object lambda$null$57(Supplier supplier, Object obj) {
        return supplier.get();
    }

    static /* synthetic */ void lambda$groupingByConcurrent$60(Function function, Supplier supplier, BiConsumer biConsumer, ConcurrentMap concurrentMap, Object obj) {
        Object objComputeIfAbsent = ConcurrentMaps.computeIfAbsent(concurrentMap, Objects.requireNonNull(function.apply(obj), "element cannot be mapped to a null key"), Collectors$$Lambda$86.lambdaFactory$(supplier));
        synchronized (objComputeIfAbsent) {
            biConsumer.accept(objComputeIfAbsent, obj);
        }
    }

    static /* synthetic */ Object lambda$null$59(Supplier supplier, Object obj) {
        return supplier.get();
    }

    static /* synthetic */ ConcurrentMap lambda$groupingByConcurrent$62(Function function, ConcurrentMap concurrentMap) {
        ConcurrentMaps.replaceAll(concurrentMap, Collectors$$Lambda$85.lambdaFactory$(function));
        return concurrentMap;
    }

    static /* synthetic */ Object lambda$null$61(Function function, Object obj, Object obj2) {
        return function.apply(obj2);
    }

    public static <T> Collector<T, ?, Map<Boolean, List<T>>> partitioningBy(Predicate<? super T> predicate) {
        return partitioningBy(predicate, toList());
    }

    public static <T, D, A> Collector<T, ?, Map<Boolean, D>> partitioningBy(Predicate<? super T> predicate, Collector<? super T, A, D> collector) {
        BiConsumer biConsumerLambdaFactory$ = Collectors$$Lambda$69.lambdaFactory$(collector.accumulator(), predicate);
        BinaryOperator binaryOperatorLambdaFactory$ = Collectors$$Lambda$70.lambdaFactory$(collector.combiner());
        Supplier supplierLambdaFactory$ = Collectors$$Lambda$71.lambdaFactory$(collector);
        if (collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)) {
            return new CollectorImpl(supplierLambdaFactory$, biConsumerLambdaFactory$, binaryOperatorLambdaFactory$, CH_ID);
        }
        return new CollectorImpl(supplierLambdaFactory$, biConsumerLambdaFactory$, binaryOperatorLambdaFactory$, Collectors$$Lambda$72.lambdaFactory$(collector), CH_NOID);
    }

    static /* synthetic */ void lambda$partitioningBy$63(BiConsumer biConsumer, Predicate predicate, Partition partition, Object obj) {
        biConsumer.accept(predicate.test(obj) ? partition.forTrue : partition.forFalse, obj);
    }

    static /* synthetic */ Partition lambda$partitioningBy$64(BinaryOperator binaryOperator, Partition partition, Partition partition2) {
        return new Partition(binaryOperator.apply(partition.forTrue, partition2.forTrue), binaryOperator.apply(partition.forFalse, partition2.forFalse));
    }

    static /* synthetic */ Partition lambda$partitioningBy$65(Collector collector) {
        return new Partition(collector.supplier().get(), collector.supplier().get());
    }

    static /* synthetic */ Map lambda$partitioningBy$66(Collector collector, Partition partition) {
        return new Partition(collector.finisher().apply(partition.forTrue), collector.finisher().apply(partition.forFalse));
    }

    public static <T, K, U> Collector<T, ?, Map<K, U>> toMap(Function<? super T, ? extends K> function, Function<? super T, ? extends U> function2) {
        return new CollectorImpl(hashMapNew(), uniqKeysMapAccumulator(function, function2), uniqKeysMapMerger(), CH_ID);
    }

    public static <T, K, U> Collector<T, ?, Map<K, U>> toUnmodifiableMap(Function<? super T, ? extends K> function, Function<? super T, ? extends U> function2) {
        Objects.requireNonNull(function, "keyMapper");
        Objects.requireNonNull(function2, "valueMapper");
        return collectingAndThen(toMap(function, function2), unmodMapFinisher());
    }

    public static <T, K, U> Collector<T, ?, Map<K, U>> toMap(Function<? super T, ? extends K> function, Function<? super T, ? extends U> function2, BinaryOperator<U> binaryOperator) {
        return toMap(function, function2, binaryOperator, hashMapNew());
    }

    public static <T, K, U> Collector<T, ?, Map<K, U>> toUnmodifiableMap(Function<? super T, ? extends K> function, Function<? super T, ? extends U> function2, BinaryOperator<U> binaryOperator) {
        Objects.requireNonNull(function, "keyMapper");
        Objects.requireNonNull(function2, "valueMapper");
        Objects.requireNonNull(binaryOperator, "mergeFunction");
        return collectingAndThen(toMap(function, function2, binaryOperator, hashMapNew()), unmodMapFinisher());
    }

    public static <T, K, U, M extends Map<K, U>> Collector<T, ?, M> toMap(Function<? super T, ? extends K> function, Function<? super T, ? extends U> function2, BinaryOperator<U> binaryOperator, Supplier<M> supplier) {
        return new CollectorImpl(supplier, Collectors$$Lambda$73.lambdaFactory$(function, function2, binaryOperator), mapMerger(binaryOperator), CH_ID);
    }

    static /* synthetic */ void lambda$toMap$67(Function function, Function function2, BinaryOperator binaryOperator, Map map, Object obj) {
        Maps.merge(map, function.apply(obj), function2.apply(obj), binaryOperator);
    }

    public static <T, K, U> Collector<T, ?, ConcurrentMap<K, U>> toConcurrentMap(Function<? super T, ? extends K> function, Function<? super T, ? extends U> function2) {
        return new CollectorImpl(concHashMapNew2(), uniqKeysMapAccumulator(function, function2), uniqKeysMapMerger(), CH_CONCURRENT_ID);
    }

    public static <T, K, U> Collector<T, ?, ConcurrentMap<K, U>> toConcurrentMap(Function<? super T, ? extends K> function, Function<? super T, ? extends U> function2, BinaryOperator<U> binaryOperator) {
        return toConcurrentMap(function, function2, binaryOperator, concHashMapNew());
    }

    public static <T, K, U, M extends ConcurrentMap<K, U>> Collector<T, ?, M> toConcurrentMap(Function<? super T, ? extends K> function, Function<? super T, ? extends U> function2, BinaryOperator<U> binaryOperator, Supplier<M> supplier) {
        return new CollectorImpl(supplier, Collectors$$Lambda$74.lambdaFactory$(function, function2, binaryOperator), mapMergerConcurrent(binaryOperator), CH_CONCURRENT_ID);
    }

    static /* synthetic */ void lambda$toConcurrentMap$68(Function function, Function function2, BinaryOperator binaryOperator, ConcurrentMap concurrentMap, Object obj) {
        ConcurrentMaps.merge(concurrentMap, function.apply(obj), function2.apply(obj), binaryOperator);
    }

    public static <T> Collector<T, ?, IntSummaryStatistics> summarizingInt(ToIntFunction<? super T> toIntFunction) {
        return new CollectorImpl(INT_SUM_STATS, Collectors$$Lambda$75.lambdaFactory$(toIntFunction), Collectors$$Lambda$76.instance, CH_ID);
    }

    static /* synthetic */ void lambda$summarizingInt$69(ToIntFunction toIntFunction, IntSummaryStatistics intSummaryStatistics, Object obj) {
        intSummaryStatistics.accept(toIntFunction.applyAsInt(obj));
    }

    static /* synthetic */ IntSummaryStatistics lambda$summarizingInt$70(IntSummaryStatistics intSummaryStatistics, IntSummaryStatistics intSummaryStatistics2) {
        intSummaryStatistics.combine(intSummaryStatistics2);
        return intSummaryStatistics;
    }

    public static <T> Collector<T, ?, LongSummaryStatistics> summarizingLong(ToLongFunction<? super T> toLongFunction) {
        return new CollectorImpl(LNG_SUM_STATS, Collectors$$Lambda$77.lambdaFactory$(toLongFunction), Collectors$$Lambda$78.instance, CH_ID);
    }

    static /* synthetic */ void lambda$summarizingLong$71(ToLongFunction toLongFunction, LongSummaryStatistics longSummaryStatistics, Object obj) {
        longSummaryStatistics.accept(toLongFunction.applyAsLong(obj));
    }

    static /* synthetic */ LongSummaryStatistics lambda$summarizingLong$72(LongSummaryStatistics longSummaryStatistics, LongSummaryStatistics longSummaryStatistics2) {
        longSummaryStatistics.combine(longSummaryStatistics2);
        return longSummaryStatistics;
    }

    public static <T> Collector<T, ?, DoubleSummaryStatistics> summarizingDouble(ToDoubleFunction<? super T> toDoubleFunction) {
        return new CollectorImpl(DBL_SUM_STATS, Collectors$$Lambda$79.lambdaFactory$(toDoubleFunction), Collectors$$Lambda$80.instance, CH_ID);
    }

    static /* synthetic */ void lambda$summarizingDouble$73(ToDoubleFunction toDoubleFunction, DoubleSummaryStatistics doubleSummaryStatistics, Object obj) {
        doubleSummaryStatistics.accept(toDoubleFunction.applyAsDouble(obj));
    }

    static /* synthetic */ DoubleSummaryStatistics lambda$summarizingDouble$74(DoubleSummaryStatistics doubleSummaryStatistics, DoubleSummaryStatistics doubleSummaryStatistics2) {
        doubleSummaryStatistics.combine(doubleSummaryStatistics2);
        return doubleSummaryStatistics;
    }

    public static <T, R1, R2, R> Collector<T, ?, R> teeing(Collector<? super T, ?, R1> collector, Collector<? super T, ?, R2> collector2, BiFunction<? super R1, ? super R2, R> biFunction) {
        return teeing0(collector, collector2, biFunction);
    }

    private static <T, A1, A2, R1, R2, R> Collector<T, ?, R> teeing0(Collector<? super T, A1, R1> collector, Collector<? super T, A2, R2> collector2, BiFunction<? super R1, ? super R2, R> biFunction) {
        Set<Collector.Characteristics> setUnmodifiableSet;
        Objects.requireNonNull(collector, "downstream1");
        Objects.requireNonNull(collector2, "downstream2");
        Objects.requireNonNull(biFunction, "merger");
        Supplier supplier = (Supplier) Objects.requireNonNull(collector.supplier(), "downstream1 supplier");
        Supplier supplier2 = (Supplier) Objects.requireNonNull(collector2.supplier(), "downstream2 supplier");
        BiConsumer biConsumer = (BiConsumer) Objects.requireNonNull(collector.accumulator(), "downstream1 accumulator");
        BiConsumer biConsumer2 = (BiConsumer) Objects.requireNonNull(collector2.accumulator(), "downstream2 accumulator");
        BinaryOperator binaryOperator = (BinaryOperator) Objects.requireNonNull(collector.combiner(), "downstream1 combiner");
        BinaryOperator binaryOperator2 = (BinaryOperator) Objects.requireNonNull(collector2.combiner(), "downstream2 combiner");
        Function function = (Function) Objects.requireNonNull(collector.finisher(), "downstream1 finisher");
        Function function2 = (Function) Objects.requireNonNull(collector2.finisher(), "downstream2 finisher");
        Set<Collector.Characteristics> setCharacteristics = collector.characteristics();
        Set<Collector.Characteristics> setCharacteristics2 = collector2.characteristics();
        Set<Collector.Characteristics> set = CH_ID;
        if (set.containsAll(setCharacteristics) || set.containsAll(setCharacteristics2)) {
            setUnmodifiableSet = CH_NOID;
        } else {
            EnumSet enumSetNoneOf = EnumSet.noneOf(Collector.Characteristics.class);
            enumSetNoneOf.addAll(setCharacteristics);
            enumSetNoneOf.retainAll(setCharacteristics2);
            enumSetNoneOf.remove(Collector.Characteristics.IDENTITY_FINISH);
            setUnmodifiableSet = Collections.unmodifiableSet(enumSetNoneOf);
        }
        return new CollectorImpl(Collectors$$Lambda$81.lambdaFactory$(supplier, supplier2, biConsumer, biConsumer2, binaryOperator, binaryOperator2, function, function2, biFunction), Collectors$$Lambda$82.instance, Collectors$$Lambda$83.instance, Collectors$$Lambda$84.instance, setUnmodifiableSet);
    }

    /* JADX INFO: renamed from: java9.util.stream.Collectors$1PairBox, reason: invalid class name */
    class C1PairBox {
        A1 left;
        A2 right;
        final /* synthetic */ BiConsumer val$c1Accumulator;
        final /* synthetic */ BinaryOperator val$c1Combiner;
        final /* synthetic */ Function val$c1Finisher;
        final /* synthetic */ Supplier val$c1Supplier;
        final /* synthetic */ BiConsumer val$c2Accumulator;
        final /* synthetic */ BinaryOperator val$c2Combiner;
        final /* synthetic */ Function val$c2Finisher;
        final /* synthetic */ Supplier val$c2Supplier;
        final /* synthetic */ BiFunction val$merger;

        /* JADX WARN: Type inference failed for: r1v1, types: [A1, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v2, types: [A2, java.lang.Object] */
        C1PairBox(Supplier supplier, Supplier supplier2, BiConsumer biConsumer, BiConsumer biConsumer2, BinaryOperator binaryOperator, BinaryOperator binaryOperator2, Function function, Function function2, BiFunction biFunction) {
            this.val$c1Supplier = supplier;
            this.val$c2Supplier = supplier2;
            this.val$c1Accumulator = biConsumer;
            this.val$c2Accumulator = biConsumer2;
            this.val$c1Combiner = binaryOperator;
            this.val$c2Combiner = binaryOperator2;
            this.val$c1Finisher = function;
            this.val$c2Finisher = function2;
            this.val$merger = biFunction;
            this.left = supplier.get();
            this.right = supplier2.get();
        }

        void add(T t) {
            this.val$c1Accumulator.accept(this.left, t);
            this.val$c2Accumulator.accept(this.right, t);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [A1, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r4v2, types: [A2, java.lang.Object] */
        C1PairBox combine(C1PairBox c1PairBox) {
            this.left = this.val$c1Combiner.apply(this.left, c1PairBox.left);
            this.right = this.val$c2Combiner.apply(this.right, c1PairBox.right);
            return this;
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [R, java.lang.Object] */
        R get() {
            return this.val$merger.apply(this.val$c1Finisher.apply(this.left), this.val$c2Finisher.apply(this.right));
        }
    }

    static /* synthetic */ C1PairBox lambda$teeing0$75(Supplier supplier, Supplier supplier2, BiConsumer biConsumer, BiConsumer biConsumer2, BinaryOperator binaryOperator, BinaryOperator binaryOperator2, Function function, Function function2, BiFunction biFunction) {
        return new C1PairBox(supplier, supplier2, biConsumer, biConsumer2, binaryOperator, binaryOperator2, function, function2, biFunction);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class Partition<T> extends AbstractMap<Boolean, T> implements Map<Boolean, T> {
        final T forFalse;
        final T forTrue;

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return 2;
        }

        Partition(T t, T t2) {
            this.forTrue = t;
            this.forFalse = t2;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<Boolean, T>> entrySet() {
            return new AbstractSet<Map.Entry<Boolean, T>>() { // from class: java9.util.stream.Collectors.Partition.1
                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return 2;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<Map.Entry<Boolean, T>> iterator() {
                    return Arrays.asList(new AbstractMap.SimpleImmutableEntry(false, Partition.this.forFalse), new AbstractMap.SimpleImmutableEntry(true, Partition.this.forTrue)).iterator();
                }
            };
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return obj instanceof Boolean;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public T get(Object obj) {
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue() ? this.forTrue : this.forFalse;
            }
            return null;
        }
    }
}

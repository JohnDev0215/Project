package kotlinx.coroutines.flow;

import g5.r;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import k5.d;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.channels.c0;
import kotlinx.coroutines.channels.g;
import kotlinx.coroutines.channels.g0;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.h0;
import kotlinx.coroutines.k0;
import kotlinx.coroutines.l1;
import r5.l;
import r5.p;
import r5.q;
import r5.s;
import r5.t;
import r5.u;
import v5.c;
import v5.e;
import z5.a;

/* JADX INFO: loaded from: classes.dex */
public final class FlowKt {
    public static final String DEFAULT_CONCURRENCY_PROPERTY_NAME = "kotlinx.coroutines.flow.defaultConcurrency";

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SharingCommand.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[SharingCommand.START.ordinal()] = 1;
            iArr[SharingCommand.STOP.ordinal()] = 2;
            iArr[SharingCommand.STOP_AND_RESET_REPLAY_CACHE.ordinal()] = 3;
        }
    }

    public static final <T> Flow<T> asFlow(Iterable<? extends T> iterable) {
        return FlowKt__BuildersKt.asFlow(iterable);
    }

    public static final <T> SharedFlow<T> asSharedFlow(MutableSharedFlow<T> mutableSharedFlow) {
        return FlowKt__ShareKt.asSharedFlow(mutableSharedFlow);
    }

    public static final <T> StateFlow<T> asStateFlow(MutableStateFlow<T> mutableStateFlow) {
        return FlowKt__ShareKt.asStateFlow(mutableStateFlow);
    }

    public static final <T> g<T> broadcastIn(Flow<? extends T> flow, h0 h0Var, k0 k0Var) {
        return FlowKt__ChannelsKt.broadcastIn(flow, h0Var, k0Var);
    }

    public static final <T> Flow<T> cache(Flow<? extends T> flow) {
        return FlowKt__MigrationKt.cache(flow);
    }

    public static final <T> Flow<T> callbackFlow(p<? super a0<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        return FlowKt__BuildersKt.callbackFlow(pVar);
    }

    public static final <T> Flow<T> cancellable(Flow<? extends T> flow) {
        return FlowKt__ContextKt.cancellable(flow);
    }

    /* JADX INFO: renamed from: catch, reason: not valid java name */
    public static final <T> Flow<T> m3catch(Flow<? extends T> flow, q<? super FlowCollector<? super T>, ? super Throwable, ? super d<? super r>, ? extends Object> qVar) {
        return FlowKt__ErrorsKt.m8catch(flow, qVar);
    }

    public static final <T> Object catchImpl(Flow<? extends T> flow, FlowCollector<? super T> flowCollector, d<? super Throwable> dVar) {
        return FlowKt__ErrorsKt.catchImpl(flow, flowCollector, dVar);
    }

    public static final <T> Flow<T> channelFlow(p<? super a0<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        return FlowKt__BuildersKt.channelFlow(pVar);
    }

    public static final Object collect(Flow<?> flow, d<? super r> dVar) {
        return FlowKt__CollectKt.collect(flow, dVar);
    }

    public static final <T> Object collectIndexed(Flow<? extends T> flow, q<? super Integer, ? super T, ? super d<? super r>, ? extends Object> qVar, d<? super r> dVar) {
        return FlowKt__CollectKt.collectIndexed(flow, qVar, dVar);
    }

    public static final <T> Object collectLatest(Flow<? extends T> flow, p<? super T, ? super d<? super r>, ? extends Object> pVar, d<? super r> dVar) {
        return FlowKt__CollectKt.collectLatest(flow, pVar, dVar);
    }

    public static final <T> Object collectWhile(Flow<? extends T> flow, p<? super T, ? super d<? super Boolean>, ? extends Object> pVar, d<? super r> dVar) {
        return FlowKt__LimitKt.collectWhile(flow, pVar, dVar);
    }

    public static final <T1, T2, T3, T4, T5, R> Flow<R> combineLatest(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, Flow<? extends T5> flow5, t<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super d<? super R>, ? extends Object> tVar) {
        return FlowKt__MigrationKt.combineLatest(flow, flow2, flow3, flow4, flow5, tVar);
    }

    public static final <T, R> Flow<R> compose(Flow<? extends T> flow, l<? super Flow<? extends T>, ? extends Flow<? extends R>> lVar) {
        return FlowKt__MigrationKt.compose(flow, lVar);
    }

    public static final <T, R> Flow<R> concatMap(Flow<? extends T> flow, l<? super T, ? extends Flow<? extends R>> lVar) {
        return FlowKt__MigrationKt.concatMap(flow, lVar);
    }

    public static final <T> Flow<T> concatWith(Flow<? extends T> flow, T t6) {
        return FlowKt__MigrationKt.concatWith(flow, t6);
    }

    public static final <T> Flow<T> conflate(Flow<? extends T> flow) {
        return FlowKt__ContextKt.conflate(flow);
    }

    public static final <T> Flow<T> consumeAsFlow(c0<? extends T> c0Var) {
        return FlowKt__ChannelsKt.consumeAsFlow(c0Var);
    }

    public static final <T> Object count(Flow<? extends T> flow, d<? super Integer> dVar) {
        return FlowKt__CountKt.count(flow, dVar);
    }

    public static final <T> Flow<T> debounce(Flow<? extends T> flow, long j7) {
        return FlowKt__DelayKt.debounce(flow, j7);
    }

    /* JADX INFO: renamed from: debounce-8GFy2Ro, reason: not valid java name */
    public static final <T> Flow<T> m4debounce8GFy2Ro(Flow<? extends T> flow, double d7) {
        return FlowKt__DelayKt.m6debounce8GFy2Ro(flow, d7);
    }

    public static final <T> Flow<T> debounceDuration(Flow<? extends T> flow, l<? super T, a> lVar) {
        return FlowKt__DelayKt.debounceDuration(flow, lVar);
    }

    public static final <T> Flow<T> delayEach(Flow<? extends T> flow, long j7) {
        return FlowKt__MigrationKt.delayEach(flow, j7);
    }

    public static final <T> Flow<T> delayFlow(Flow<? extends T> flow, long j7) {
        return FlowKt__MigrationKt.delayFlow(flow, j7);
    }

    public static final <T> Flow<T> distinctUntilChanged(Flow<? extends T> flow) {
        return FlowKt__DistinctKt.distinctUntilChanged(flow);
    }

    public static final <T, K> Flow<T> distinctUntilChangedBy(Flow<? extends T> flow, l<? super T, ? extends K> lVar) {
        return FlowKt__DistinctKt.distinctUntilChangedBy(flow, lVar);
    }

    public static final <T> Flow<T> drop(Flow<? extends T> flow, int i7) {
        return FlowKt__LimitKt.drop(flow, i7);
    }

    public static final <T> Flow<T> dropWhile(Flow<? extends T> flow, p<? super T, ? super d<? super Boolean>, ? extends Object> pVar) {
        return FlowKt__LimitKt.dropWhile(flow, pVar);
    }

    public static final <T> Object emitAll(FlowCollector<? super T> flowCollector, c0<? extends T> c0Var, d<? super r> dVar) throws Throwable {
        return FlowKt__ChannelsKt.emitAll(flowCollector, c0Var, dVar);
    }

    public static final <T> Flow<T> emptyFlow() {
        return FlowKt__BuildersKt.emptyFlow();
    }

    public static final <T> Flow<T> filter(Flow<? extends T> flow, p<? super T, ? super d<? super Boolean>, ? extends Object> pVar) {
        return FlowKt__TransformKt.filter(flow, pVar);
    }

    public static final <T> Flow<T> filterNot(Flow<? extends T> flow, p<? super T, ? super d<? super Boolean>, ? extends Object> pVar) {
        return FlowKt__TransformKt.filterNot(flow, pVar);
    }

    public static final <T> Flow<T> filterNotNull(Flow<? extends T> flow) {
        return FlowKt__TransformKt.filterNotNull(flow);
    }

    public static final <T> Object first(Flow<? extends T> flow, d<? super T> dVar) {
        return FlowKt__ReduceKt.first(flow, dVar);
    }

    public static final <T> Object firstOrNull(Flow<? extends T> flow, d<? super T> dVar) {
        return FlowKt__ReduceKt.firstOrNull(flow, dVar);
    }

    public static final c0<r> fixedPeriodTicker(h0 h0Var, long j7, long j8) {
        return FlowKt__DelayKt.fixedPeriodTicker(h0Var, j7, j8);
    }

    public static final <T, R> Flow<R> flatMap(Flow<? extends T> flow, p<? super T, ? super d<? super Flow<? extends R>>, ? extends Object> pVar) {
        return FlowKt__MigrationKt.flatMap(flow, pVar);
    }

    public static final <T, R> Flow<R> flatMapConcat(Flow<? extends T> flow, p<? super T, ? super d<? super Flow<? extends R>>, ? extends Object> pVar) {
        return FlowKt__MergeKt.flatMapConcat(flow, pVar);
    }

    public static final <T, R> Flow<R> flatMapLatest(Flow<? extends T> flow, p<? super T, ? super d<? super Flow<? extends R>>, ? extends Object> pVar) {
        return FlowKt__MergeKt.flatMapLatest(flow, pVar);
    }

    public static final <T, R> Flow<R> flatMapMerge(Flow<? extends T> flow, int i7, p<? super T, ? super d<? super Flow<? extends R>>, ? extends Object> pVar) {
        return FlowKt__MergeKt.flatMapMerge(flow, i7, pVar);
    }

    public static final <T> Flow<T> flatten(Flow<? extends Flow<? extends T>> flow) {
        return FlowKt__MigrationKt.flatten(flow);
    }

    public static final <T> Flow<T> flattenConcat(Flow<? extends Flow<? extends T>> flow) {
        return FlowKt__MergeKt.flattenConcat(flow);
    }

    public static final <T> Flow<T> flattenMerge(Flow<? extends Flow<? extends T>> flow, int i7) {
        return FlowKt__MergeKt.flattenMerge(flow, i7);
    }

    public static final <T> Flow<T> flow(p<? super FlowCollector<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        return FlowKt__BuildersKt.flow(pVar);
    }

    public static final <T1, T2, R> Flow<R> flowCombine(Flow<? extends T1> flow, Flow<? extends T2> flow2, q<? super T1, ? super T2, ? super d<? super R>, ? extends Object> qVar) {
        return FlowKt__ZipKt.flowCombine(flow, flow2, qVar);
    }

    public static final <T1, T2, R> Flow<R> flowCombineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, r5.r<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super d<? super r>, ? extends Object> rVar) {
        return FlowKt__ZipKt.flowCombineTransform(flow, flow2, rVar);
    }

    public static final <T> Flow<T> flowOf(T t6) {
        return FlowKt__BuildersKt.flowOf(t6);
    }

    public static final <T> Flow<T> flowOn(Flow<? extends T> flow, k5.g gVar) {
        return FlowKt__ContextKt.flowOn(flow, gVar);
    }

    public static final <T> Flow<T> flowViaChannel(int i7, p<? super h0, ? super g0<? super T>, r> pVar) {
        return FlowKt__BuildersKt.flowViaChannel(i7, pVar);
    }

    public static final <T, R> Flow<R> flowWith(Flow<? extends T> flow, k5.g gVar, int i7, l<? super Flow<? extends T>, ? extends Flow<? extends R>> lVar) {
        return FlowKt__ContextKt.flowWith(flow, gVar, i7, lVar);
    }

    public static final <T, R> Object fold(Flow<? extends T> flow, R r7, q<? super R, ? super T, ? super d<? super R>, ? extends Object> qVar, d<? super R> dVar) {
        return FlowKt__ReduceKt.fold(flow, r7, qVar, dVar);
    }

    public static final <T> void forEach(Flow<? extends T> flow, p<? super T, ? super d<? super r>, ? extends Object> pVar) {
        FlowKt__MigrationKt.forEach(flow, pVar);
    }

    public static final int getDEFAULT_CONCURRENCY() {
        return FlowKt__MergeKt.getDEFAULT_CONCURRENCY();
    }

    public static /* synthetic */ void getDEFAULT_CONCURRENCY_PROPERTY_NAME$annotations() {
    }

    public static final <T> l1 launchIn(Flow<? extends T> flow, h0 h0Var) {
        return FlowKt__CollectKt.launchIn(flow, h0Var);
    }

    public static final <T, R> Flow<R> map(Flow<? extends T> flow, p<? super T, ? super d<? super R>, ? extends Object> pVar) {
        return FlowKt__TransformKt.map(flow, pVar);
    }

    public static final <T, R> Flow<R> mapLatest(Flow<? extends T> flow, p<? super T, ? super d<? super R>, ? extends Object> pVar) {
        return FlowKt__MergeKt.mapLatest(flow, pVar);
    }

    public static final <T, R> Flow<R> mapNotNull(Flow<? extends T> flow, p<? super T, ? super d<? super R>, ? extends Object> pVar) {
        return FlowKt__TransformKt.mapNotNull(flow, pVar);
    }

    public static final <T> Flow<T> merge(Iterable<? extends Flow<? extends T>> iterable) {
        return FlowKt__MergeKt.merge(iterable);
    }

    public static final Void noImpl() {
        return FlowKt__MigrationKt.noImpl();
    }

    public static final <T> Flow<T> observeOn(Flow<? extends T> flow, k5.g gVar) {
        return FlowKt__MigrationKt.observeOn(flow, gVar);
    }

    public static final <T> Flow<T> onCompletion(Flow<? extends T> flow, q<? super FlowCollector<? super T>, ? super Throwable, ? super d<? super r>, ? extends Object> qVar) {
        return FlowKt__EmittersKt.onCompletion(flow, qVar);
    }

    public static final <T> Flow<T> onEach(Flow<? extends T> flow, p<? super T, ? super d<? super r>, ? extends Object> pVar) {
        return FlowKt__TransformKt.onEach(flow, pVar);
    }

    public static final <T> Flow<T> onEmpty(Flow<? extends T> flow, p<? super FlowCollector<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        return FlowKt__EmittersKt.onEmpty(flow, pVar);
    }

    public static final <T> Flow<T> onErrorCollect(Flow<? extends T> flow, Flow<? extends T> flow2, l<? super Throwable, Boolean> lVar) {
        return FlowKt__ErrorsKt.onErrorCollect(flow, flow2, lVar);
    }

    public static final <T> Flow<T> onErrorResume(Flow<? extends T> flow, Flow<? extends T> flow2) {
        return FlowKt__MigrationKt.onErrorResume(flow, flow2);
    }

    public static final <T> Flow<T> onErrorResumeNext(Flow<? extends T> flow, Flow<? extends T> flow2) {
        return FlowKt__MigrationKt.onErrorResumeNext(flow, flow2);
    }

    public static final <T> Flow<T> onErrorReturn(Flow<? extends T> flow, T t6) {
        return FlowKt__MigrationKt.onErrorReturn(flow, t6);
    }

    public static final <T> Flow<T> onStart(Flow<? extends T> flow, p<? super FlowCollector<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        return FlowKt__EmittersKt.onStart(flow, pVar);
    }

    public static final <T> SharedFlow<T> onSubscription(SharedFlow<? extends T> sharedFlow, p<? super FlowCollector<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        return FlowKt__ShareKt.onSubscription(sharedFlow, pVar);
    }

    public static final <T> c0<T> produceIn(Flow<? extends T> flow, h0 h0Var) {
        return FlowKt__ChannelsKt.produceIn(flow, h0Var);
    }

    public static final <T> Flow<T> publish(Flow<? extends T> flow) {
        return FlowKt__MigrationKt.publish(flow);
    }

    public static final <T> Flow<T> publishOn(Flow<? extends T> flow, k5.g gVar) {
        return FlowKt__MigrationKt.publishOn(flow, gVar);
    }

    public static final <T> Flow<T> receiveAsFlow(c0<? extends T> c0Var) {
        return FlowKt__ChannelsKt.receiveAsFlow(c0Var);
    }

    public static final <S, T extends S> Object reduce(Flow<? extends T> flow, q<? super S, ? super T, ? super d<? super S>, ? extends Object> qVar, d<? super S> dVar) {
        return FlowKt__ReduceKt.reduce(flow, qVar, dVar);
    }

    public static final <T> Flow<T> replay(Flow<? extends T> flow) {
        return FlowKt__MigrationKt.replay(flow);
    }

    public static final <T> Flow<T> retryWhen(Flow<? extends T> flow, r5.r<? super FlowCollector<? super T>, ? super Throwable, ? super Long, ? super d<? super Boolean>, ? extends Object> rVar) {
        return FlowKt__ErrorsKt.retryWhen(flow, rVar);
    }

    public static final <T> Flow<T> runningReduce(Flow<? extends T> flow, q<? super T, ? super T, ? super d<? super T>, ? extends Object> qVar) {
        return FlowKt__TransformKt.runningReduce(flow, qVar);
    }

    public static final <T> Flow<T> sample(Flow<? extends T> flow, long j7) {
        return FlowKt__DelayKt.sample(flow, j7);
    }

    /* JADX INFO: renamed from: sample-8GFy2Ro, reason: not valid java name */
    public static final <T> Flow<T> m5sample8GFy2Ro(Flow<? extends T> flow, double d7) {
        return FlowKt__DelayKt.m7sample8GFy2Ro(flow, d7);
    }

    public static final <T, R> Flow<R> scan(Flow<? extends T> flow, R r7, q<? super R, ? super T, ? super d<? super R>, ? extends Object> qVar) {
        return FlowKt__TransformKt.scan(flow, r7, qVar);
    }

    public static final <T, R> Flow<R> scanFold(Flow<? extends T> flow, R r7, q<? super R, ? super T, ? super d<? super R>, ? extends Object> qVar) {
        return FlowKt__MigrationKt.scanFold(flow, r7, qVar);
    }

    public static final <T> Flow<T> scanReduce(Flow<? extends T> flow, q<? super T, ? super T, ? super d<? super T>, ? extends Object> qVar) {
        return FlowKt__MigrationKt.scanReduce(flow, qVar);
    }

    public static final <T> SharedFlow<T> shareIn(Flow<? extends T> flow, h0 h0Var, SharingStarted sharingStarted, int i7) {
        return FlowKt__ShareKt.shareIn(flow, h0Var, sharingStarted, i7);
    }

    public static final <T> Object single(Flow<? extends T> flow, d<? super T> dVar) {
        return FlowKt__ReduceKt.single(flow, dVar);
    }

    public static final <T> Object singleOrNull(Flow<? extends T> flow, d<? super T> dVar) {
        return FlowKt__ReduceKt.singleOrNull(flow, dVar);
    }

    public static final <T> Flow<T> skip(Flow<? extends T> flow, int i7) {
        return FlowKt__MigrationKt.skip(flow, i7);
    }

    public static final <T> Flow<T> startWith(Flow<? extends T> flow, T t6) {
        return FlowKt__MigrationKt.startWith(flow, t6);
    }

    public static final <T> Object stateIn(Flow<? extends T> flow, h0 h0Var, d<? super StateFlow<? extends T>> dVar) {
        return FlowKt__ShareKt.stateIn(flow, h0Var, dVar);
    }

    public static final <T> void subscribe(Flow<? extends T> flow) {
        FlowKt__MigrationKt.subscribe(flow);
    }

    public static final <T> Flow<T> subscribeOn(Flow<? extends T> flow, k5.g gVar) {
        return FlowKt__MigrationKt.subscribeOn(flow, gVar);
    }

    public static final <T, R> Flow<R> switchMap(Flow<? extends T> flow, p<? super T, ? super d<? super Flow<? extends R>>, ? extends Object> pVar) {
        return FlowKt__MigrationKt.switchMap(flow, pVar);
    }

    public static final <T> Flow<T> take(Flow<? extends T> flow, int i7) {
        return FlowKt__LimitKt.take(flow, i7);
    }

    public static final <T> Flow<T> takeWhile(Flow<? extends T> flow, p<? super T, ? super d<? super Boolean>, ? extends Object> pVar) {
        return FlowKt__LimitKt.takeWhile(flow, pVar);
    }

    public static final <T, C extends Collection<? super T>> Object toCollection(Flow<? extends T> flow, C c7, d<? super C> dVar) {
        return FlowKt__CollectionKt.toCollection(flow, c7, dVar);
    }

    public static final <T> Object toList(Flow<? extends T> flow, List<T> list, d<? super List<? extends T>> dVar) {
        return FlowKt__CollectionKt.toList(flow, list, dVar);
    }

    public static final <T> Object toSet(Flow<? extends T> flow, Set<T> set, d<? super Set<? extends T>> dVar) {
        return FlowKt__CollectionKt.toSet(flow, set, dVar);
    }

    public static final <T, R> Flow<R> transform(Flow<? extends T> flow, q<? super FlowCollector<? super R>, ? super T, ? super d<? super r>, ? extends Object> qVar) {
        return FlowKt__EmittersKt.transform(flow, qVar);
    }

    public static final <T, R> Flow<R> transformLatest(Flow<? extends T> flow, q<? super FlowCollector<? super R>, ? super T, ? super d<? super r>, ? extends Object> qVar) {
        return FlowKt__MergeKt.transformLatest(flow, qVar);
    }

    public static final <T, R> Flow<R> transformWhile(Flow<? extends T> flow, q<? super FlowCollector<? super R>, ? super T, ? super d<? super Boolean>, ? extends Object> qVar) {
        return FlowKt__LimitKt.transformWhile(flow, qVar);
    }

    public static final <T, R> Flow<R> unsafeTransform(Flow<? extends T> flow, q<? super FlowCollector<? super R>, ? super T, ? super d<? super r>, ? extends Object> qVar) {
        return FlowKt__EmittersKt.unsafeTransform(flow, qVar);
    }

    public static final <T> Flow<h5.c0<T>> withIndex(Flow<? extends T> flow) {
        return FlowKt__TransformKt.withIndex(flow);
    }

    public static final <T1, T2, R> Flow<R> zip(Flow<? extends T1> flow, Flow<? extends T2> flow2, q<? super T1, ? super T2, ? super d<? super R>, ? extends Object> qVar) {
        return FlowKt__ZipKt.zip(flow, flow2, qVar);
    }

    public static final <T> Flow<T> asFlow(Iterator<? extends T> it) {
        return FlowKt__BuildersKt.asFlow(it);
    }

    public static final <T> Flow<T> buffer(Flow<? extends T> flow, int i7, k kVar) {
        return FlowKt__ContextKt.buffer(flow, i7, kVar);
    }

    public static final <T> Object collect(Flow<? extends T> flow, p<? super T, ? super d<? super r>, ? extends Object> pVar, d<? super r> dVar) {
        return FlowKt__CollectKt.collect(flow, pVar, dVar);
    }

    public static final <T1, T2, T3, T4, T5, R> Flow<R> combine(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, Flow<? extends T5> flow5, t<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super d<? super R>, ? extends Object> tVar) {
        return FlowKt__ZipKt.combine(flow, flow2, flow3, flow4, flow5, tVar);
    }

    public static final <T1, T2, T3, T4, R> Flow<R> combineLatest(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, s<? super T1, ? super T2, ? super T3, ? super T4, ? super d<? super R>, ? extends Object> sVar) {
        return FlowKt__MigrationKt.combineLatest(flow, flow2, flow3, flow4, sVar);
    }

    public static final <T1, T2, T3, T4, T5, R> Flow<R> combineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, Flow<? extends T5> flow5, u<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super d<? super r>, ? extends Object> uVar) {
        return FlowKt__ZipKt.combineTransform(flow, flow2, flow3, flow4, flow5, uVar);
    }

    public static final <T> Flow<T> concatWith(Flow<? extends T> flow, Flow<? extends T> flow2) {
        return FlowKt__MigrationKt.concatWith((Flow) flow, (Flow) flow2);
    }

    public static final <T> Object count(Flow<? extends T> flow, p<? super T, ? super d<? super Boolean>, ? extends Object> pVar, d<? super Integer> dVar) {
        return FlowKt__CountKt.count(flow, pVar, dVar);
    }

    public static final <T> Flow<T> debounce(Flow<? extends T> flow, l<? super T, Long> lVar) {
        return FlowKt__DelayKt.debounce(flow, lVar);
    }

    public static final <T> Flow<T> distinctUntilChanged(Flow<? extends T> flow, p<? super T, ? super T, Boolean> pVar) {
        return FlowKt__DistinctKt.distinctUntilChanged(flow, pVar);
    }

    public static final <T> Object emitAll(FlowCollector<? super T> flowCollector, Flow<? extends T> flow, d<? super r> dVar) {
        return FlowKt__CollectKt.emitAll(flowCollector, flow, dVar);
    }

    public static final <T> Object first(Flow<? extends T> flow, p<? super T, ? super d<? super Boolean>, ? extends Object> pVar, d<? super T> dVar) {
        return FlowKt__ReduceKt.first(flow, pVar, dVar);
    }

    public static final <T> Object firstOrNull(Flow<? extends T> flow, p<? super T, ? super d<? super Boolean>, ? extends Object> pVar, d<? super T> dVar) {
        return FlowKt__ReduceKt.firstOrNull(flow, pVar, dVar);
    }

    public static final <T> Flow<T> flowOf(T... tArr) {
        return FlowKt__BuildersKt.flowOf((Object[]) tArr);
    }

    public static final <T> Flow<T> merge(Flow<? extends Flow<? extends T>> flow) {
        return FlowKt__MigrationKt.merge(flow);
    }

    public static final <T> Flow<T> onErrorReturn(Flow<? extends T> flow, T t6, l<? super Throwable, Boolean> lVar) {
        return FlowKt__MigrationKt.onErrorReturn(flow, t6, lVar);
    }

    public static final <T> Flow<T> publish(Flow<? extends T> flow, int i7) {
        return FlowKt__MigrationKt.publish(flow, i7);
    }

    public static final <T> Flow<T> replay(Flow<? extends T> flow, int i7) {
        return FlowKt__MigrationKt.replay(flow, i7);
    }

    public static final <T> Flow<T> retry(Flow<? extends T> flow, long j7, p<? super Throwable, ? super d<? super Boolean>, ? extends Object> pVar) {
        return FlowKt__ErrorsKt.retry(flow, j7, pVar);
    }

    public static final <T> Flow<T> startWith(Flow<? extends T> flow, Flow<? extends T> flow2) {
        return FlowKt__MigrationKt.startWith((Flow) flow, (Flow) flow2);
    }

    public static final <T> StateFlow<T> stateIn(Flow<? extends T> flow, h0 h0Var, SharingStarted sharingStarted, T t6) {
        return FlowKt__ShareKt.stateIn(flow, h0Var, sharingStarted, t6);
    }

    public static final <T> void subscribe(Flow<? extends T> flow, p<? super T, ? super d<? super r>, ? extends Object> pVar) {
        FlowKt__MigrationKt.subscribe(flow, pVar);
    }

    public static final <T> Flow<T> asFlow(g<T> gVar) {
        return FlowKt__ChannelsKt.asFlow(gVar);
    }

    public static final <T1, T2, T3, T4, R> Flow<R> combine(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, s<? super T1, ? super T2, ? super T3, ? super T4, ? super d<? super R>, ? extends Object> sVar) {
        return FlowKt__ZipKt.combine(flow, flow2, flow3, flow4, sVar);
    }

    public static final <T1, T2, T3, R> Flow<R> combineLatest(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, r5.r<? super T1, ? super T2, ? super T3, ? super d<? super R>, ? extends Object> rVar) {
        return FlowKt__MigrationKt.combineLatest(flow, flow2, flow3, rVar);
    }

    public static final <T1, T2, T3, T4, R> Flow<R> combineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, t<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super T3, ? super T4, ? super d<? super r>, ? extends Object> tVar) {
        return FlowKt__ZipKt.combineTransform(flow, flow2, flow3, flow4, tVar);
    }

    public static final <T> Flow<T> merge(Flow<? extends T>... flowArr) {
        return FlowKt__MergeKt.merge(flowArr);
    }

    public static final <T> void subscribe(Flow<? extends T> flow, p<? super T, ? super d<? super r>, ? extends Object> pVar, p<? super Throwable, ? super d<? super r>, ? extends Object> pVar2) {
        FlowKt__MigrationKt.subscribe(flow, pVar, pVar2);
    }

    public static final <T> Flow<T> asFlow(r5.a<? extends T> aVar) {
        return FlowKt__BuildersKt.asFlow(aVar);
    }

    public static final <T1, T2, T3, R> Flow<R> combine(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, r5.r<? super T1, ? super T2, ? super T3, ? super d<? super R>, ? extends Object> rVar) {
        return FlowKt__ZipKt.combine(flow, flow2, flow3, rVar);
    }

    public static final <T1, T2, R> Flow<R> combineLatest(Flow<? extends T1> flow, Flow<? extends T2> flow2, q<? super T1, ? super T2, ? super d<? super R>, ? extends Object> qVar) {
        return FlowKt__MigrationKt.combineLatest(flow, flow2, qVar);
    }

    public static final <T1, T2, T3, R> Flow<R> combineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, s<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super T3, ? super d<? super r>, ? extends Object> sVar) {
        return FlowKt__ZipKt.combineTransform(flow, flow2, flow3, sVar);
    }

    public static final <T> Flow<T> asFlow(l<? super d<? super T>, ? extends Object> lVar) {
        return FlowKt__BuildersKt.asFlow(lVar);
    }

    public static final <T1, T2, R> Flow<R> combine(Flow<? extends T1> flow, Flow<? extends T2> flow2, q<? super T1, ? super T2, ? super d<? super R>, ? extends Object> qVar) {
        return FlowKt__ZipKt.combine(flow, flow2, qVar);
    }

    public static final <T1, T2, R> Flow<R> combineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, r5.r<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super d<? super r>, ? extends Object> rVar) {
        return FlowKt__ZipKt.combineTransform(flow, flow2, rVar);
    }

    public static final Flow<Integer> asFlow(c cVar) {
        return FlowKt__BuildersKt.asFlow(cVar);
    }

    public static final Flow<Long> asFlow(e eVar) {
        return FlowKt__BuildersKt.asFlow(eVar);
    }

    public static final <T> Flow<T> asFlow(x5.d<? extends T> dVar) {
        return FlowKt__BuildersKt.asFlow(dVar);
    }

    public static final Flow<Integer> asFlow(int[] iArr) {
        return FlowKt__BuildersKt.asFlow(iArr);
    }

    public static final Flow<Long> asFlow(long[] jArr) {
        return FlowKt__BuildersKt.asFlow(jArr);
    }

    public static final <T> Flow<T> asFlow(T[] tArr) {
        return FlowKt__BuildersKt.asFlow(tArr);
    }
}

package java9.util.stream;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java9.util.Objects;
import java9.util.Spliterator;
import java9.util.concurrent.ForkJoinPool;
import java9.util.function.IntFunction;
import java9.util.stream.ReferencePipeline;
import java9.util.stream.Sink;
import java9.util.stream.StreamSpliterators;

/* JADX INFO: loaded from: classes3.dex */
final class DistinctOps {
    private DistinctOps() {
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: java9.util.stream.DistinctOps$1, reason: invalid class name */
    static class AnonymousClass1<T> extends ReferencePipeline.StatefulOp<T, T> {
        AnonymousClass1(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, streamShape, i);
        }

        <P_IN> Node<T> reduce(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator) {
            return Nodes.node((Collection) ReduceOps.makeRef(DistinctOps$1$$Lambda$1.instance, DistinctOps$1$$Lambda$2.instance, DistinctOps$1$$Lambda$3.instance).evaluateParallel(pipelineHelper, spliterator));
        }

        @Override // java9.util.stream.ReferencePipeline.StatefulOp, java9.util.stream.AbstractPipeline
        <P_IN> Node<T> opEvaluateParallel(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<T[]> intFunction) {
            Collection keysAndNullSet;
            if (StreamOpFlag.DISTINCT.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return pipelineHelper.evaluate(spliterator, false, intFunction);
            }
            if (StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return reduce(pipelineHelper, spliterator);
            }
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(512, 0.75f, ForkJoinPool.getCommonPoolParallelism() + 1);
            ForEachOps.makeRef(DistinctOps$1$$Lambda$4.lambdaFactory$(atomicBoolean, concurrentHashMap), false).evaluateParallel(pipelineHelper, spliterator);
            Set setKeySet = concurrentHashMap.keySet();
            Collection collection = setKeySet;
            if (atomicBoolean.get()) {
                int size = setKeySet.size();
                if (size >= 127) {
                    keysAndNullSet = new KeysAndNullSet(setKeySet, size);
                } else {
                    HashSet hashSet = new HashSet(Math.max(((int) ((size + 1) / 0.75f)) + 1, 16));
                    hashSet.addAll(setKeySet);
                    hashSet.add(null);
                    keysAndNullSet = hashSet;
                }
                collection = keysAndNullSet;
            }
            return Nodes.node(collection);
        }

        static /* synthetic */ void lambda$opEvaluateParallel$0(AtomicBoolean atomicBoolean, ConcurrentMap concurrentMap, Object obj) {
            if (obj == null) {
                atomicBoolean.set(true);
            } else {
                concurrentMap.putIfAbsent(obj, Boolean.TRUE);
            }
        }

        @Override // java9.util.stream.AbstractPipeline
        <P_IN> Spliterator<T> opEvaluateParallelLazy(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator) {
            if (StreamOpFlag.DISTINCT.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return pipelineHelper.wrapSpliterator(spliterator);
            }
            if (StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return reduce(pipelineHelper, spliterator).spliterator();
            }
            return new StreamSpliterators.DistinctSpliterator(pipelineHelper.wrapSpliterator(spliterator));
        }

        @Override // java9.util.stream.AbstractPipeline
        Sink<T> opWrapSink(int i, Sink<T> sink) {
            Objects.requireNonNull(sink);
            if (StreamOpFlag.DISTINCT.isKnown(i)) {
                return sink;
            }
            if (StreamOpFlag.SORTED.isKnown(i)) {
                return new Sink.ChainedReference<T, T>(sink) { // from class: java9.util.stream.DistinctOps.1.1
                    T lastSeen;
                    boolean seenNull;

                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public void begin(long j) {
                        this.seenNull = false;
                        this.lastSeen = null;
                        this.downstream.begin(-1L);
                    }

                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public void end() {
                        this.seenNull = false;
                        this.lastSeen = null;
                        this.downstream.end();
                    }

                    /* JADX WARN: Type inference incomplete: some casts might be missing */
                    @Override // java9.util.function.Consumer
                    public void accept(T t) {
                        if (t == null) {
                            if (this.seenNull) {
                                return;
                            }
                            this.seenNull = true;
                            Sink<? super E_OUT> sink2 = this.downstream;
                            this.lastSeen = null;
                            sink2.accept((Object) null);
                            return;
                        }
                        T t2 = this.lastSeen;
                        if (t2 == null || !t.equals(t2)) {
                            Sink<? super E_OUT> sink3 = this.downstream;
                            this.lastSeen = t;
                            sink3.accept((Object) t);
                        }
                    }
                };
            }
            return new Sink.ChainedReference<T, T>(sink) { // from class: java9.util.stream.DistinctOps.1.2
                Set<T> seen;

                @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                public void begin(long j) {
                    this.seen = new HashSet();
                    this.downstream.begin(-1L);
                }

                @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                public void end() {
                    this.seen = null;
                    this.downstream.end();
                }

                /* JADX WARN: Type inference incomplete: some casts might be missing */
                @Override // java9.util.function.Consumer
                public void accept(T t) {
                    if (this.seen.add(t)) {
                        this.downstream.accept((Object) t);
                    }
                }
            };
        }
    }

    static <T> ReferencePipeline<T, T> makeRef(AbstractPipeline<?, T, ?> abstractPipeline) {
        return new AnonymousClass1(abstractPipeline, StreamShape.REFERENCE, StreamOpFlag.IS_DISTINCT | StreamOpFlag.NOT_SIZED);
    }

    static final class KeysAndNullSet<E> extends AbstractSet<E> {
        final Set<E> keys;
        final int size;

        KeysAndNullSet(Set<E> set, int i) {
            this.keys = set;
            this.size = i + 1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return new Iterator<E>() { // from class: java9.util.stream.DistinctOps.KeysAndNullSet.1
                Iterator<E> it;
                boolean nullDelivered = false;

                {
                    this.it = KeysAndNullSet.this.keys.iterator();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    if (this.nullDelivered) {
                        return this.it.hasNext();
                    }
                    return true;
                }

                @Override // java.util.Iterator
                public E next() {
                    if (!this.nullDelivered) {
                        this.nullDelivered = true;
                        return null;
                    }
                    return this.it.next();
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.size;
        }
    }
}

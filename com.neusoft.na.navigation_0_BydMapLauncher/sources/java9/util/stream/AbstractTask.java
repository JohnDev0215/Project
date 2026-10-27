package java9.util.stream;

import java9.util.Spliterator;
import java9.util.concurrent.CountedCompleter;
import java9.util.concurrent.ForkJoinPool;
import java9.util.concurrent.ForkJoinWorkerThread;
import java9.util.stream.AbstractTask;

/* JADX INFO: loaded from: classes3.dex */
abstract class AbstractTask<P_IN, P_OUT, R, K extends AbstractTask<P_IN, P_OUT, R, K>> extends CountedCompleter<R> {
    private static final int LEAF_TARGET = ForkJoinPool.getCommonPoolParallelism() << 2;
    protected final PipelineHelper<P_OUT> helper;
    protected K leftChild;
    private R localResult;
    protected K rightChild;
    protected Spliterator<P_IN> spliterator;
    protected long targetSize;

    protected abstract R doLeaf();

    protected abstract K makeChild(Spliterator<P_IN> spliterator);

    protected AbstractTask(PipelineHelper<P_OUT> pipelineHelper, Spliterator<P_IN> spliterator) {
        super(null);
        this.helper = pipelineHelper;
        this.spliterator = spliterator;
        this.targetSize = 0L;
    }

    protected AbstractTask(K k, Spliterator<P_IN> spliterator) {
        super(k);
        this.spliterator = spliterator;
        this.helper = k.helper;
        this.targetSize = k.targetSize;
    }

    public static int getLeafTarget() {
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof ForkJoinWorkerThread) {
            return ((ForkJoinWorkerThread) threadCurrentThread).getPool().getParallelism() << 2;
        }
        return LEAF_TARGET;
    }

    public static long suggestTargetSize(long j) {
        long leafTarget = j / ((long) getLeafTarget());
        if (leafTarget > 0) {
            return leafTarget;
        }
        return 1L;
    }

    protected final long getTargetSize(long j) {
        long j2 = this.targetSize;
        if (j2 != 0) {
            return j2;
        }
        long jSuggestTargetSize = suggestTargetSize(j);
        this.targetSize = jSuggestTargetSize;
        return jSuggestTargetSize;
    }

    @Override // java9.util.concurrent.CountedCompleter, java9.util.concurrent.ForkJoinTask
    public R getRawResult() {
        return this.localResult;
    }

    @Override // java9.util.concurrent.CountedCompleter, java9.util.concurrent.ForkJoinTask
    protected void setRawResult(R r) {
        if (r != null) {
            throw new IllegalStateException();
        }
    }

    protected R getLocalResult() {
        return this.localResult;
    }

    protected void setLocalResult(R r) {
        this.localResult = r;
    }

    protected boolean isLeaf() {
        return this.leftChild == null;
    }

    protected boolean isRoot() {
        return getParent() == null;
    }

    protected K getParent() {
        return (K) getCompleter();
    }

    @Override // java9.util.concurrent.CountedCompleter
    public void compute() {
        Spliterator<P_IN> spliteratorTrySplit;
        Spliterator<P_IN> spliterator = this.spliterator;
        long jEstimateSize = spliterator.estimateSize();
        long targetSize = getTargetSize(jEstimateSize);
        boolean z = false;
        AbstractTask<P_IN, P_OUT, R, K> abstractTask = this;
        while (jEstimateSize > targetSize && (spliteratorTrySplit = spliterator.trySplit()) != null) {
            AbstractTask<P_IN, P_OUT, R, K> abstractTaskMakeChild = abstractTask.makeChild(spliteratorTrySplit);
            abstractTask.leftChild = abstractTaskMakeChild;
            AbstractTask<P_IN, P_OUT, R, K> abstractTaskMakeChild2 = abstractTask.makeChild(spliterator);
            abstractTask.rightChild = abstractTaskMakeChild2;
            abstractTask.setPendingCount(1);
            if (z) {
                spliterator = spliteratorTrySplit;
                abstractTask = abstractTaskMakeChild;
                abstractTaskMakeChild = abstractTaskMakeChild2;
            } else {
                abstractTask = abstractTaskMakeChild2;
            }
            z = !z;
            abstractTaskMakeChild.fork();
            jEstimateSize = spliterator.estimateSize();
        }
        abstractTask.setLocalResult(abstractTask.doLeaf());
        abstractTask.tryComplete();
    }

    @Override // java9.util.concurrent.CountedCompleter
    public void onCompletion(CountedCompleter<?> countedCompleter) {
        this.spliterator = null;
        this.rightChild = null;
        this.leftChild = null;
    }

    protected boolean isLeftmostNode() {
        AbstractTask<P_IN, P_OUT, R, K> abstractTask = this;
        while (abstractTask != null) {
            AbstractTask<P_IN, P_OUT, R, K> parent = abstractTask.getParent();
            if (parent != null && parent.leftChild != abstractTask) {
                return false;
            }
            abstractTask = parent;
        }
        return true;
    }
}

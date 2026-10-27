package java9.util.stream;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;
import java9.util.Spliterator;
import java9.util.stream.AbstractShortCircuitTask;

/* JADX INFO: loaded from: classes3.dex */
abstract class AbstractShortCircuitTask<P_IN, P_OUT, R, K extends AbstractShortCircuitTask<P_IN, P_OUT, R, K>> extends AbstractTask<P_IN, P_OUT, R, K> {
    protected volatile boolean canceled;
    protected final AtomicReference<R> sharedResult;

    protected abstract R getEmptyResult();

    protected AbstractShortCircuitTask(PipelineHelper<P_OUT> pipelineHelper, Spliterator<P_IN> spliterator) {
        super(pipelineHelper, spliterator);
        this.sharedResult = new AtomicReference<>(null);
    }

    protected AbstractShortCircuitTask(K k, Spliterator<P_IN> spliterator) {
        super(k, spliterator);
        this.sharedResult = k.sharedResult;
    }

    @Override // java9.util.stream.AbstractTask, java9.util.concurrent.CountedCompleter
    public void compute() {
        R emptyResult;
        Spliterator<P_IN> spliteratorTrySplit;
        Spliterator<P_IN> spliterator = this.spliterator;
        long jEstimateSize = spliterator.estimateSize();
        long targetSize = getTargetSize(jEstimateSize);
        AtomicReference<R> atomicReference = this.sharedResult;
        boolean z = false;
        AbstractShortCircuitTask<P_IN, P_OUT, R, K> abstractShortCircuitTask = this;
        while (true) {
            emptyResult = atomicReference.get();
            if (emptyResult != null) {
                break;
            }
            if (abstractShortCircuitTask.taskCanceled()) {
                emptyResult = abstractShortCircuitTask.getEmptyResult();
                break;
            }
            if (jEstimateSize <= targetSize || (spliteratorTrySplit = spliterator.trySplit()) == null) {
                break;
            }
            K kMakeChild = abstractShortCircuitTask.makeChild(spliteratorTrySplit);
            abstractShortCircuitTask.leftChild = kMakeChild;
            K kMakeChild2 = abstractShortCircuitTask.makeChild(spliterator);
            abstractShortCircuitTask.rightChild = kMakeChild2;
            abstractShortCircuitTask.setPendingCount(1);
            if (z) {
                spliterator = spliteratorTrySplit;
                abstractShortCircuitTask = kMakeChild;
                kMakeChild = kMakeChild2;
            } else {
                abstractShortCircuitTask = kMakeChild2;
            }
            z = !z;
            kMakeChild.fork();
            jEstimateSize = spliterator.estimateSize();
        }
        emptyResult = abstractShortCircuitTask.doLeaf();
        abstractShortCircuitTask.setLocalResult(emptyResult);
        abstractShortCircuitTask.tryComplete();
    }

    protected void shortCircuit(R r) {
        if (r != null) {
            LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.sharedResult, null, r);
        }
    }

    @Override // java9.util.stream.AbstractTask
    protected void setLocalResult(R r) {
        if (!isRoot()) {
            super.setLocalResult(r);
        } else if (r != null) {
            LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.sharedResult, null, r);
        }
    }

    @Override // java9.util.stream.AbstractTask, java9.util.concurrent.CountedCompleter, java9.util.concurrent.ForkJoinTask
    public R getRawResult() {
        return getLocalResult();
    }

    @Override // java9.util.stream.AbstractTask
    public R getLocalResult() {
        if (isRoot()) {
            R r = this.sharedResult.get();
            return r == null ? getEmptyResult() : r;
        }
        return (R) super.getLocalResult();
    }

    protected void cancel() {
        this.canceled = true;
    }

    protected boolean taskCanceled() {
        boolean z = this.canceled;
        if (!z) {
            K parent = getParent();
            while (true) {
                AbstractShortCircuitTask abstractShortCircuitTask = parent;
                if (z || abstractShortCircuitTask == null) {
                    break;
                }
                z = abstractShortCircuitTask.canceled;
                parent = (K) abstractShortCircuitTask.getParent();
            }
        }
        return z;
    }

    protected void cancelLaterNodes() {
        AbstractShortCircuitTask<P_IN, P_OUT, R, K> abstractShortCircuitTask = this;
        for (K parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent.leftChild == abstractShortCircuitTask) {
                AbstractShortCircuitTask abstractShortCircuitTask2 = (AbstractShortCircuitTask) parent.rightChild;
                if (!abstractShortCircuitTask2.canceled) {
                    abstractShortCircuitTask2.cancel();
                }
            }
            abstractShortCircuitTask = parent;
        }
    }
}

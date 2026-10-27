package com.amazon.identity.mobi.common.ui;

import com.amazon.identity.mobi.common.ui.UIType;
import com.amazon.identity.mobi.common.utils.MetricsEmitter;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingDeque;

/* JADX INFO: loaded from: classes3.dex */
public class MultiLayerPriorityUITaskQueue {
    private final MetricsEmitter mMetricsEmitter;
    private final TreeMap<UIType, LinkedBlockingDeque<UITask>> mPendingQueueTreeMap = new TreeMap<>(new UIType.UITypeComparator());
    private int mTotalUITasksSizeAcrossUIType = 0;

    public MultiLayerPriorityUITaskQueue(MetricsEmitter metricsEmitter) {
        this.mMetricsEmitter = metricsEmitter;
    }

    private LinkedBlockingDeque<UITask> getQueueForUIType(UIType uIType) {
        LinkedBlockingDeque<UITask> linkedBlockingDeque = this.mPendingQueueTreeMap.get(uIType);
        if (linkedBlockingDeque != null) {
            return linkedBlockingDeque;
        }
        LinkedBlockingDeque<UITask> linkedBlockingDeque2 = new LinkedBlockingDeque<>(uIType.getUiPendingQueueCapacity());
        this.mPendingQueueTreeMap.put(uIType, linkedBlockingDeque2);
        return linkedBlockingDeque2;
    }

    public synchronized void addToLayerFirst(UITask uITask) {
        LinkedBlockingDeque<UITask> queueForUIType = getQueueForUIType(uITask.getUIType());
        if (queueForUIType.remainingCapacity() == 0) {
            this.mMetricsEmitter.incrementCounterAndRecord(UIMetrics.buildMetricForUiTask(UIMetrics.DROP_MESSAGE_FROM_QUEUE_TAIL, queueForUIType.pollLast()));
            this.mTotalUITasksSizeAcrossUIType--;
        }
        queueForUIType.offerFirst(uITask);
        this.mTotalUITasksSizeAcrossUIType++;
    }

    public synchronized void addToLayerLast(UITask uITask) {
        LinkedBlockingDeque<UITask> queueForUIType = getQueueForUIType(uITask.getUIType());
        if (queueForUIType.remainingCapacity() == 0) {
            this.mMetricsEmitter.incrementCounterAndRecord(UIMetrics.buildMetricForUiTask(UIMetrics.DROP_MESSAGE_FROM_QUEUE_HEAD, queueForUIType.pollFirst()));
            this.mTotalUITasksSizeAcrossUIType--;
        }
        queueForUIType.offerLast(uITask);
        this.mTotalUITasksSizeAcrossUIType++;
    }

    public synchronized void clear() {
        this.mPendingQueueTreeMap.clear();
        this.mTotalUITasksSizeAcrossUIType = 0;
    }

    public synchronized boolean contains(UITask uITask) {
        return getQueueForUIType(uITask.getUIType()).contains(uITask);
    }

    public synchronized boolean isEmpty() {
        return this.mTotalUITasksSizeAcrossUIType == 0;
    }

    public synchronized UITask removeFirst() {
        Iterator<Map.Entry<UIType, LinkedBlockingDeque<UITask>>> it = this.mPendingQueueTreeMap.entrySet().iterator();
        while (it.hasNext()) {
            LinkedBlockingDeque<UITask> value = it.next().getValue();
            if (!value.isEmpty()) {
                this.mTotalUITasksSizeAcrossUIType--;
                return value.pollFirst();
            }
        }
        return null;
    }

    public synchronized UITask removeLast() {
        Iterator<UIType> it = this.mPendingQueueTreeMap.descendingKeySet().iterator();
        while (it.hasNext()) {
            LinkedBlockingDeque<UITask> linkedBlockingDeque = this.mPendingQueueTreeMap.get(it.next());
            if (linkedBlockingDeque != null && !linkedBlockingDeque.isEmpty()) {
                this.mTotalUITasksSizeAcrossUIType--;
                return linkedBlockingDeque.pollLast();
            }
        }
        return null;
    }

    public synchronized int size() {
        return this.mTotalUITasksSizeAcrossUIType;
    }
}

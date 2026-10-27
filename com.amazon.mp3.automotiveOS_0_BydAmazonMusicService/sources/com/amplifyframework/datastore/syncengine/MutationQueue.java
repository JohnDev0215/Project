package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.core.model.Model;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class MutationQueue {
    private final Node dummyHead;
    private final Node dummyTail;
    private final Map<TimeBasedUuid, Node> mutationMap = new HashMap();

    public MutationQueue() {
        Node node = new Node();
        this.dummyHead = node;
        Node node2 = new Node();
        this.dummyTail = node2;
        node.next = node2;
        node2.prev = node;
    }

    synchronized PendingMutation<? extends Model> nextMutationForModelId(String str) {
        Node node = this.dummyHead.next;
        if (node == this.dummyTail) {
            return null;
        }
        while (node != this.dummyTail) {
            if (node.mutation.getMutatedItem().getPrimaryKeyString().equals(str)) {
                return node.mutation;
            }
            node = node.next;
        }
        return null;
    }

    synchronized boolean removeById(TimeBasedUuid timeBasedUuid) {
        if (!this.mutationMap.containsKey(timeBasedUuid)) {
            return false;
        }
        Node node = this.mutationMap.get(timeBasedUuid);
        Node node2 = node.prev;
        Node node3 = node.next;
        this.mutationMap.remove(timeBasedUuid);
        node2.next = node3;
        node3.prev = node2;
        return true;
    }

    private synchronized boolean addToTail(@NonNull PendingMutation<? extends Model> pendingMutation) {
        Node node = new Node();
        node.id = pendingMutation.getMutationId();
        node.mutation = pendingMutation;
        Node node2 = this.dummyTail.prev;
        node2.next = node;
        this.dummyTail.prev = node;
        node.prev = node2;
        node.next = this.dummyTail;
        this.mutationMap.put(pendingMutation.getMutationId(), node);
        return true;
    }

    synchronized void updateExistingQueueItemOrAppendNew(@NonNull TimeBasedUuid timeBasedUuid, @NonNull PendingMutation<? extends Model> pendingMutation) {
        if (!this.mutationMap.containsKey(timeBasedUuid)) {
            addToTail(pendingMutation);
        } else {
            this.mutationMap.get(timeBasedUuid).mutation = pendingMutation;
        }
    }

    @Nullable
    synchronized PendingMutation<? extends Model> getMutationById(TimeBasedUuid timeBasedUuid) {
        if (!this.mutationMap.containsKey(timeBasedUuid)) {
            return null;
        }
        return this.mutationMap.get(timeBasedUuid).mutation;
    }

    public synchronized boolean isEmpty() {
        return this.mutationMap.isEmpty();
    }

    public boolean add(PendingMutation<? extends Model> pendingMutation) {
        if (pendingMutation == null || this.mutationMap.containsKey(pendingMutation.getMutationId())) {
            return false;
        }
        return addToTail(pendingMutation);
    }

    public int size() {
        return this.mutationMap.size();
    }

    public boolean remove(@Nullable Object obj) {
        if (obj instanceof PendingMutation) {
            return removeById(((PendingMutation) obj).getMutationId());
        }
        return false;
    }

    public synchronized void clear() {
        this.dummyHead.next = this.dummyTail;
        this.dummyTail.prev = this.dummyHead;
        this.mutationMap.clear();
    }

    @Nullable
    public synchronized PendingMutation<? extends Model> peek() {
        return this.mutationMap.isEmpty() ? null : this.dummyHead.next.mutation;
    }

    private class Node {
        private TimeBasedUuid id;
        private PendingMutation<? extends Model> mutation;
        private Node next;
        private Node prev;

        private Node() {
        }
    }
}

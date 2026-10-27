package com.amazon.avod.content.smoothstream.storage;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class AllocationInfo {
    private final AllocationNode mAllocations = new AllocationNode();

    public interface RemoveRunnable {
        void run(long j);
    }

    private static class AllocationNode {
        private Map<Integer, AllocationNode> mChildren;
        private long mHandle;

        private AllocationNode() {
            this.mHandle = 0L;
        }

        public void setHandle(long j) {
            this.mHandle = j;
        }

        public long getHandle() {
            return this.mHandle;
        }

        @Nullable
        public Set<Integer> getChildItems() {
            Map<Integer, AllocationNode> map = this.mChildren;
            if (map == null) {
                return null;
            }
            return map.keySet();
        }

        @Nullable
        public Collection<AllocationNode> getChildNodes() {
            Map<Integer, AllocationNode> map = this.mChildren;
            if (map == null) {
                return null;
            }
            return map.values();
        }

        @Nullable
        public AllocationNode get(@Nonnull Integer num) {
            Map<Integer, AllocationNode> map = this.mChildren;
            if (map == null) {
                return null;
            }
            return map.get(num);
        }

        @Nonnull
        public AllocationNode put(@Nonnull Integer num) {
            if (this.mChildren == null) {
                this.mChildren = Maps.newHashMap();
            }
            AllocationNode allocationNode = new AllocationNode();
            this.mChildren.put(num, allocationNode);
            return allocationNode;
        }

        @Nullable
        public AllocationNode remove(@Nonnull Integer num) {
            Map<Integer, AllocationNode> map = this.mChildren;
            if (map == null) {
                return null;
            }
            return map.remove(num);
        }

        public void clear() {
            Map<Integer, AllocationNode> map = this.mChildren;
            if (map != null) {
                map.clear();
            }
        }
    }

    public void clear() {
        this.mAllocations.clear();
    }

    public void remove(@Nonnull RemoveRunnable removeRunnable, @Nonnull int... iArr) {
        Preconditions.checkNotNull(removeRunnable, "removeRunnable");
        Preconditions.checkNotNull(iArr);
        AllocationNode allocationNode = this.mAllocations;
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        AllocationNode allocationNode2 = allocationNode;
        while (i < length) {
            i2 = iArr[i];
            AllocationNode allocationNode3 = allocationNode2.get(Integer.valueOf(i2));
            if (allocationNode3 == null) {
                return;
            }
            i++;
            AllocationNode allocationNode4 = allocationNode2;
            allocationNode2 = allocationNode3;
            allocationNode = allocationNode4;
        }
        LinkedBlockingQueue linkedBlockingQueueNewLinkedBlockingQueue = Queues.newLinkedBlockingQueue();
        linkedBlockingQueueNewLinkedBlockingQueue.add(allocationNode.remove(Integer.valueOf(i2)));
        while (true) {
            AllocationNode allocationNode5 = (AllocationNode) linkedBlockingQueueNewLinkedBlockingQueue.poll();
            if (allocationNode5 == null) {
                return;
            }
            Collection<AllocationNode> childNodes = allocationNode5.getChildNodes();
            if (childNodes != null) {
                linkedBlockingQueueNewLinkedBlockingQueue.addAll(childNodes);
            }
            long handle = allocationNode5.getHandle();
            if (handle != 0) {
                removeRunnable.run(handle);
            }
        }
    }

    public boolean contains(@Nonnull int... iArr) {
        return getNode(iArr) != null;
    }

    @Nullable
    public Set<Integer> getChildren(@Nonnull int... iArr) {
        AllocationNode node = getNode(iArr);
        if (node == null) {
            return null;
        }
        return node.getChildItems();
    }

    public long get(@Nonnull int... iArr) {
        AllocationNode node = getNode(iArr);
        if (node != null) {
            return node.getHandle();
        }
        return 0L;
    }

    public void put(long j, @Nonnull int... iArr) {
        Preconditions.checkNotNull(iArr);
        AllocationNode allocationNodePut = this.mAllocations;
        for (int i : iArr) {
            AllocationNode allocationNode = allocationNodePut.get(Integer.valueOf(i));
            allocationNodePut = allocationNode == null ? allocationNodePut.put(Integer.valueOf(i)) : allocationNode;
        }
        allocationNodePut.setHandle(j);
    }

    @Nullable
    private AllocationNode getNode(@Nonnull int... iArr) {
        Preconditions.checkNotNull(iArr);
        AllocationNode allocationNode = this.mAllocations;
        for (int i : iArr) {
            allocationNode = allocationNode.get(Integer.valueOf(i));
            if (allocationNode == null) {
                return null;
            }
        }
        return allocationNode;
    }
}

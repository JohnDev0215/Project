package com.amazon.musicplayqueueservice.client.v2.queue;

import com.amazon.musicplayqueueservice.client.v2.track.QueuedEntityMetadataBase;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateQueueResponse implements Comparable<CreateQueueResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.queue.CreateQueueResponse");
    private Queue queue;
    private List<QueuedEntityMetadataBase> queuedEntityMetadataList;
    private String status;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CreateQueueResponse createQueueResponse) {
        if (createQueueResponse == null) {
            return -1;
        }
        if (createQueueResponse == this) {
            return 0;
        }
        Queue queue = getQueue();
        Queue queue2 = createQueueResponse.getQueue();
        if (queue != queue2) {
            if (queue == null) {
                return -1;
            }
            if (queue2 == null) {
                return 1;
            }
            int iCompareTo = queue.compareTo(queue2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<QueuedEntityMetadataBase> queuedEntityMetadataList = getQueuedEntityMetadataList();
        List<QueuedEntityMetadataBase> queuedEntityMetadataList2 = createQueueResponse.getQueuedEntityMetadataList();
        if (queuedEntityMetadataList != queuedEntityMetadataList2) {
            if (queuedEntityMetadataList == null) {
                return -1;
            }
            if (queuedEntityMetadataList2 == null) {
                return 1;
            }
            if (queuedEntityMetadataList instanceof Comparable) {
                int iCompareTo2 = ((Comparable) queuedEntityMetadataList).compareTo(queuedEntityMetadataList2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!queuedEntityMetadataList.equals(queuedEntityMetadataList2)) {
                int iHashCode = queuedEntityMetadataList.hashCode();
                int iHashCode2 = queuedEntityMetadataList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String status = getStatus();
        String status2 = createQueueResponse.getStatus();
        if (status != status2) {
            if (status == null) {
                return -1;
            }
            if (status2 == null) {
                return 1;
            }
            int iCompareTo3 = status.compareTo(status2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CreateQueueResponse)) {
            return false;
        }
        CreateQueueResponse createQueueResponse = (CreateQueueResponse) obj;
        return internalEqualityCheck(getQueue(), createQueueResponse.getQueue()) && internalEqualityCheck(getQueuedEntityMetadataList(), createQueueResponse.getQueuedEntityMetadataList()) && internalEqualityCheck(getStatus(), createQueueResponse.getStatus());
    }

    public Queue getQueue() {
        return this.queue;
    }

    public List<QueuedEntityMetadataBase> getQueuedEntityMetadataList() {
        return this.queuedEntityMetadataList;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getQueue(), getQueuedEntityMetadataList(), getStatus());
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void setQueuedEntityMetadataList(List<QueuedEntityMetadataBase> list) {
        this.queuedEntityMetadataList = list;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}

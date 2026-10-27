package Podcast.CompletedInterface.v1_0;

import SOACoreInterface.v1_0.ClientState;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class CompletedLastSyncTimeClientState extends ClientState {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.CompletedInterface.v1_0.CompletedLastSyncTimeClientState");
    private Long lastSyncTime;

    public static class Builder extends ClientState.Builder {
        protected Long lastSyncTime;

        public CompletedLastSyncTimeClientState build() {
            CompletedLastSyncTimeClientState completedLastSyncTimeClientState = new CompletedLastSyncTimeClientState();
            populate(completedLastSyncTimeClientState);
            return completedLastSyncTimeClientState;
        }

        protected void populate(CompletedLastSyncTimeClientState completedLastSyncTimeClientState) {
            super.populate((ClientState) completedLastSyncTimeClientState);
            completedLastSyncTimeClientState.setLastSyncTime(this.lastSyncTime);
        }

        public Builder withLastSyncTime(Long l) {
            this.lastSyncTime = l;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof CompletedLastSyncTimeClientState)) {
            return 1;
        }
        Long lastSyncTime = getLastSyncTime();
        Long lastSyncTime2 = ((CompletedLastSyncTimeClientState) sOAObject).getLastSyncTime();
        if (lastSyncTime != lastSyncTime2) {
            if (lastSyncTime == null) {
                return -1;
            }
            if (lastSyncTime2 == null) {
                return 1;
            }
            int iCompareTo = lastSyncTime.compareTo(lastSyncTime2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof CompletedLastSyncTimeClientState) {
            return super.equals(obj) && internalEqualityCheck(getLastSyncTime(), ((CompletedLastSyncTimeClientState) obj).getLastSyncTime());
        }
        return false;
    }

    public Long getLastSyncTime() {
        return this.lastSyncTime;
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getLastSyncTime());
    }

    public void setLastSyncTime(Long l) {
        this.lastSyncTime = l;
    }
}

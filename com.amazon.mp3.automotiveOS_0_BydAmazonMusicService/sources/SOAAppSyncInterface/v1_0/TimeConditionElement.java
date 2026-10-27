package SOAAppSyncInterface.v1_0;

import SOAAppSyncInterface.v1_0.ConditionElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TimeConditionElement extends ConditionElement {
    private static final int classNameHashCode = internalHashCodeCompute("SOAAppSyncInterface.v1_0.TimeConditionElement");
    private long updatedTime;

    public static class Builder extends ConditionElement.Builder {
        protected long updatedTime;

        public TimeConditionElement build() {
            TimeConditionElement timeConditionElement = new TimeConditionElement();
            populate(timeConditionElement);
            return timeConditionElement;
        }

        protected void populate(TimeConditionElement timeConditionElement) {
            super.populate((ConditionElement) timeConditionElement);
            timeConditionElement.setUpdatedTime(this.updatedTime);
        }

        public Builder withUpdatedTime(long j) {
            this.updatedTime = j;
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
    @Override // SOAAppSyncInterface.v1_0.ConditionElement, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ConditionElement conditionElement) {
        if (conditionElement == null) {
            return -1;
        }
        if (conditionElement == this) {
            return 0;
        }
        if (!(conditionElement instanceof TimeConditionElement)) {
            return 1;
        }
        TimeConditionElement timeConditionElement = (TimeConditionElement) conditionElement;
        if (getUpdatedTime() < timeConditionElement.getUpdatedTime()) {
            return -1;
        }
        if (getUpdatedTime() > timeConditionElement.getUpdatedTime()) {
            return 1;
        }
        return super.compareTo(conditionElement);
    }

    @Override // SOAAppSyncInterface.v1_0.ConditionElement
    public boolean equals(Object obj) {
        if (obj instanceof TimeConditionElement) {
            return super.equals(obj) && internalEqualityCheck(Long.valueOf(getUpdatedTime()), Long.valueOf(((TimeConditionElement) obj).getUpdatedTime()));
        }
        return false;
    }

    public long getUpdatedTime() {
        return this.updatedTime;
    }

    @Override // SOAAppSyncInterface.v1_0.ConditionElement
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), Long.valueOf(getUpdatedTime()));
    }

    public void setUpdatedTime(long j) {
        this.updatedTime = j;
    }
}

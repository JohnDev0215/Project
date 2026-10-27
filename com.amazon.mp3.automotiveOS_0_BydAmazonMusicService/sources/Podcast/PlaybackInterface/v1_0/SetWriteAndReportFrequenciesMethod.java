package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SetWriteAndReportFrequenciesMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.SetWriteAndReportFrequenciesMethod");
    private Long writeAndReportFrequencySeconds;
    private Long writeFrequencySeconds;

    public static class Builder extends Method.Builder {
        protected Long writeAndReportFrequencySeconds;
        protected Long writeFrequencySeconds;

        public SetWriteAndReportFrequenciesMethod build() {
            SetWriteAndReportFrequenciesMethod setWriteAndReportFrequenciesMethod = new SetWriteAndReportFrequenciesMethod();
            populate(setWriteAndReportFrequenciesMethod);
            return setWriteAndReportFrequenciesMethod;
        }

        protected void populate(SetWriteAndReportFrequenciesMethod setWriteAndReportFrequenciesMethod) {
            super.populate((Method) setWriteAndReportFrequenciesMethod);
            setWriteAndReportFrequenciesMethod.setWriteFrequencySeconds(this.writeFrequencySeconds);
            setWriteAndReportFrequenciesMethod.setWriteAndReportFrequencySeconds(this.writeAndReportFrequencySeconds);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withQueue(Queue queue) {
            super.withQueue(queue);
            return this;
        }

        public Builder withWriteAndReportFrequencySeconds(Long l) {
            this.writeAndReportFrequencySeconds = l;
            return this;
        }

        public Builder withWriteFrequencySeconds(Long l) {
            this.writeFrequencySeconds = l;
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
    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof SetWriteAndReportFrequenciesMethod)) {
            return 1;
        }
        SetWriteAndReportFrequenciesMethod setWriteAndReportFrequenciesMethod = (SetWriteAndReportFrequenciesMethod) sOAObject;
        Long writeFrequencySeconds = getWriteFrequencySeconds();
        Long writeFrequencySeconds2 = setWriteAndReportFrequenciesMethod.getWriteFrequencySeconds();
        if (writeFrequencySeconds != writeFrequencySeconds2) {
            if (writeFrequencySeconds == null) {
                return -1;
            }
            if (writeFrequencySeconds2 == null) {
                return 1;
            }
            int iCompareTo = writeFrequencySeconds.compareTo(writeFrequencySeconds2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Long writeAndReportFrequencySeconds = getWriteAndReportFrequencySeconds();
        Long writeAndReportFrequencySeconds2 = setWriteAndReportFrequenciesMethod.getWriteAndReportFrequencySeconds();
        if (writeAndReportFrequencySeconds != writeAndReportFrequencySeconds2) {
            if (writeAndReportFrequencySeconds == null) {
                return -1;
            }
            if (writeAndReportFrequencySeconds2 == null) {
                return 1;
            }
            int iCompareTo2 = writeAndReportFrequencySeconds.compareTo(writeAndReportFrequencySeconds2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof SetWriteAndReportFrequenciesMethod)) {
            return false;
        }
        SetWriteAndReportFrequenciesMethod setWriteAndReportFrequenciesMethod = (SetWriteAndReportFrequenciesMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getWriteFrequencySeconds(), setWriteAndReportFrequenciesMethod.getWriteFrequencySeconds()) && internalEqualityCheck(getWriteAndReportFrequencySeconds(), setWriteAndReportFrequenciesMethod.getWriteAndReportFrequencySeconds());
    }

    public Long getWriteAndReportFrequencySeconds() {
        return this.writeAndReportFrequencySeconds;
    }

    public Long getWriteFrequencySeconds() {
        return this.writeFrequencySeconds;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getWriteFrequencySeconds(), getWriteAndReportFrequencySeconds());
    }

    public void setWriteAndReportFrequencySeconds(Long l) {
        this.writeAndReportFrequencySeconds = l;
    }

    public void setWriteFrequencySeconds(Long l) {
        this.writeFrequencySeconds = l;
    }
}

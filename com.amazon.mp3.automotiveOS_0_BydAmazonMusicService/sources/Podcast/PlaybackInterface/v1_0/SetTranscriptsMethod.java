package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SetTranscriptsMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.SetTranscriptsMethod");
    private String transcriptsFileUrl;

    public static class Builder extends Method.Builder {
        protected String transcriptsFileUrl;

        public SetTranscriptsMethod build() {
            SetTranscriptsMethod setTranscriptsMethod = new SetTranscriptsMethod();
            populate(setTranscriptsMethod);
            return setTranscriptsMethod;
        }

        protected void populate(SetTranscriptsMethod setTranscriptsMethod) {
            super.populate((Method) setTranscriptsMethod);
            setTranscriptsMethod.setTranscriptsFileUrl(this.transcriptsFileUrl);
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

        public Builder withTranscriptsFileUrl(String str) {
            this.transcriptsFileUrl = str;
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
        if (!(sOAObject instanceof SetTranscriptsMethod)) {
            return 1;
        }
        String transcriptsFileUrl = getTranscriptsFileUrl();
        String transcriptsFileUrl2 = ((SetTranscriptsMethod) sOAObject).getTranscriptsFileUrl();
        if (transcriptsFileUrl != transcriptsFileUrl2) {
            if (transcriptsFileUrl == null) {
                return -1;
            }
            if (transcriptsFileUrl2 == null) {
                return 1;
            }
            int iCompareTo = transcriptsFileUrl.compareTo(transcriptsFileUrl2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof SetTranscriptsMethod) {
            return super.equals(obj) && internalEqualityCheck(getTranscriptsFileUrl(), ((SetTranscriptsMethod) obj).getTranscriptsFileUrl());
        }
        return false;
    }

    public String getTranscriptsFileUrl() {
        return this.transcriptsFileUrl;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTranscriptsFileUrl());
    }

    public void setTranscriptsFileUrl(String str) {
        this.transcriptsFileUrl = str;
    }
}

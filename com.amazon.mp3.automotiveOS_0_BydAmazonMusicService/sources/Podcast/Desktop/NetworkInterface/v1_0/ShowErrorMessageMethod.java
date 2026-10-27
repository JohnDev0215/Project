package Podcast.Desktop.NetworkInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ShowErrorMessageMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.NetworkInterface.v1_0.ShowErrorMessageMethod");

    public static class Builder extends Method.Builder {
        public ShowErrorMessageMethod build() {
            ShowErrorMessageMethod showErrorMessageMethod = new ShowErrorMessageMethod();
            populate(showErrorMessageMethod);
            return showErrorMessageMethod;
        }

        protected void populate(ShowErrorMessageMethod showErrorMessageMethod) {
            super.populate((Method) showErrorMessageMethod);
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
    }

    public static Builder builder() {
        return new Builder();
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
        if (sOAObject instanceof ShowErrorMessageMethod) {
            return super.compareTo(sOAObject);
        }
        return 1;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof ShowErrorMessageMethod) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}

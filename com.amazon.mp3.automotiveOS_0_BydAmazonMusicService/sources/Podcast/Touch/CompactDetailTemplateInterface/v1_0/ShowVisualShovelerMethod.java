package Podcast.Touch.CompactDetailTemplateInterface.v1_0;

import Podcast.Touch.WidgetsInterface.v1_0.VisualShovelerCompactElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ShowVisualShovelerMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShowVisualShovelerMethod");
    private VisualShovelerCompactElement visualShoveler;

    public static class Builder extends Method.Builder {
        protected VisualShovelerCompactElement visualShoveler;

        public ShowVisualShovelerMethod build() {
            ShowVisualShovelerMethod showVisualShovelerMethod = new ShowVisualShovelerMethod();
            populate(showVisualShovelerMethod);
            return showVisualShovelerMethod;
        }

        protected void populate(ShowVisualShovelerMethod showVisualShovelerMethod) {
            super.populate((Method) showVisualShovelerMethod);
            showVisualShovelerMethod.setVisualShoveler(this.visualShoveler);
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

        public Builder withVisualShoveler(VisualShovelerCompactElement visualShovelerCompactElement) {
            this.visualShoveler = visualShovelerCompactElement;
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
        if (!(sOAObject instanceof ShowVisualShovelerMethod)) {
            return 1;
        }
        VisualShovelerCompactElement visualShoveler = getVisualShoveler();
        VisualShovelerCompactElement visualShoveler2 = ((ShowVisualShovelerMethod) sOAObject).getVisualShoveler();
        if (visualShoveler != visualShoveler2) {
            if (visualShoveler == null) {
                return -1;
            }
            if (visualShoveler2 == null) {
                return 1;
            }
            int iCompareTo = visualShoveler.compareTo(visualShoveler2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof ShowVisualShovelerMethod) {
            return super.equals(obj) && internalEqualityCheck(getVisualShoveler(), ((ShowVisualShovelerMethod) obj).getVisualShoveler());
        }
        return false;
    }

    public VisualShovelerCompactElement getVisualShoveler() {
        return this.visualShoveler;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getVisualShoveler());
    }

    public void setVisualShoveler(VisualShovelerCompactElement visualShovelerCompactElement) {
        this.visualShoveler = visualShovelerCompactElement;
    }
}

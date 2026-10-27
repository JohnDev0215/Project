package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AddWidgetsMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.AddWidgetsMethod");
    private List<Method> onEndOfList;
    private List<WidgetElement> widgets;

    public static class Builder extends Method.Builder {
        protected List<Method> onEndOfList;
        protected List<WidgetElement> widgets;

        public AddWidgetsMethod build() {
            AddWidgetsMethod addWidgetsMethod = new AddWidgetsMethod();
            populate(addWidgetsMethod);
            return addWidgetsMethod;
        }

        protected void populate(AddWidgetsMethod addWidgetsMethod) {
            super.populate((Method) addWidgetsMethod);
            addWidgetsMethod.setOnEndOfList(this.onEndOfList);
            addWidgetsMethod.setWidgets(this.widgets);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withOnEndOfList(List<Method> list) {
            this.onEndOfList = list;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withQueue(Queue queue) {
            super.withQueue(queue);
            return this;
        }

        public Builder withWidgets(List<WidgetElement> list) {
            this.widgets = list;
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
        if (!(sOAObject instanceof AddWidgetsMethod)) {
            return 1;
        }
        AddWidgetsMethod addWidgetsMethod = (AddWidgetsMethod) sOAObject;
        List<Method> onEndOfList = getOnEndOfList();
        List<Method> onEndOfList2 = addWidgetsMethod.getOnEndOfList();
        if (onEndOfList != onEndOfList2) {
            if (onEndOfList == null) {
                return -1;
            }
            if (onEndOfList2 == null) {
                return 1;
            }
            if (onEndOfList instanceof Comparable) {
                int iCompareTo = ((Comparable) onEndOfList).compareTo(onEndOfList2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onEndOfList.equals(onEndOfList2)) {
                int iHashCode = onEndOfList.hashCode();
                int iHashCode2 = onEndOfList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<WidgetElement> widgets = getWidgets();
        List<WidgetElement> widgets2 = addWidgetsMethod.getWidgets();
        if (widgets != widgets2) {
            if (widgets == null) {
                return -1;
            }
            if (widgets2 == null) {
                return 1;
            }
            if (widgets instanceof Comparable) {
                int iCompareTo2 = ((Comparable) widgets).compareTo(widgets2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!widgets.equals(widgets2)) {
                int iHashCode3 = widgets.hashCode();
                int iHashCode4 = widgets2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof AddWidgetsMethod)) {
            return false;
        }
        AddWidgetsMethod addWidgetsMethod = (AddWidgetsMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getOnEndOfList(), addWidgetsMethod.getOnEndOfList()) && internalEqualityCheck(getWidgets(), addWidgetsMethod.getWidgets());
    }

    public List<Method> getOnEndOfList() {
        return this.onEndOfList;
    }

    public List<WidgetElement> getWidgets() {
        return this.widgets;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnEndOfList(), getWidgets());
    }

    public void setOnEndOfList(List<Method> list) {
        this.onEndOfList = list;
    }

    public void setWidgets(List<WidgetElement> list) {
        this.widgets = list;
    }
}

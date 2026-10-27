package Podcast.Touch.WidgetsInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class WidgetElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.WidgetsInterface.v1_0.WidgetElement");
    private String id;
    private List<Method> onContentFirstViewed;
    private List<Method> onViewed;

    public static abstract class Builder extends TemplateElement.Builder {
        protected String id;
        protected List<Method> onContentFirstViewed;
        protected List<Method> onViewed;

        protected void populate(WidgetElement widgetElement) {
            super.populate((TemplateElement) widgetElement);
            widgetElement.setId(this.id);
            widgetElement.setOnViewed(this.onViewed);
            widgetElement.setOnContentFirstViewed(this.onContentFirstViewed);
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withOnContentFirstViewed(List<Method> list) {
            this.onContentFirstViewed = list;
            return this;
        }

        public Builder withOnViewed(List<Method> list) {
            this.onViewed = list;
            return this;
        }
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof WidgetElement)) {
            return 1;
        }
        WidgetElement widgetElement = (WidgetElement) sOAObject;
        String id = getId();
        String id2 = widgetElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo = id.compareTo(id2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Method> onViewed = getOnViewed();
        List<Method> onViewed2 = widgetElement.getOnViewed();
        if (onViewed != onViewed2) {
            if (onViewed == null) {
                return -1;
            }
            if (onViewed2 == null) {
                return 1;
            }
            if (onViewed instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onViewed).compareTo(onViewed2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!onViewed.equals(onViewed2)) {
                int iHashCode = onViewed.hashCode();
                int iHashCode2 = onViewed2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Method> onContentFirstViewed = getOnContentFirstViewed();
        List<Method> onContentFirstViewed2 = widgetElement.getOnContentFirstViewed();
        if (onContentFirstViewed != onContentFirstViewed2) {
            if (onContentFirstViewed == null) {
                return -1;
            }
            if (onContentFirstViewed2 == null) {
                return 1;
            }
            if (onContentFirstViewed instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onContentFirstViewed).compareTo(onContentFirstViewed2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onContentFirstViewed.equals(onContentFirstViewed2)) {
                int iHashCode3 = onContentFirstViewed.hashCode();
                int iHashCode4 = onContentFirstViewed2.hashCode();
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

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof WidgetElement)) {
            return false;
        }
        WidgetElement widgetElement = (WidgetElement) obj;
        return super.equals(obj) && internalEqualityCheck(getId(), widgetElement.getId()) && internalEqualityCheck(getOnViewed(), widgetElement.getOnViewed()) && internalEqualityCheck(getOnContentFirstViewed(), widgetElement.getOnContentFirstViewed());
    }

    public String getId() {
        return this.id;
    }

    public List<Method> getOnContentFirstViewed() {
        return this.onContentFirstViewed;
    }

    public List<Method> getOnViewed() {
        return this.onViewed;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getId(), getOnViewed(), getOnContentFirstViewed());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOnContentFirstViewed(List<Method> list) {
        this.onContentFirstViewed = list;
    }

    public void setOnViewed(List<Method> list) {
        this.onViewed = list;
    }
}

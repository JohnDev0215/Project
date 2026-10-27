package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AddVerticalItemsToVisualListingElementMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.AddVerticalItemsToVisualListingElementMethod");
    private List<VerticalItemElement> items;
    private List<Method> onEndOfWidget;
    private List<Method> onViewed;

    public static class Builder extends Method.Builder {
        protected List<VerticalItemElement> items;
        protected List<Method> onEndOfWidget;
        protected List<Method> onViewed;

        public AddVerticalItemsToVisualListingElementMethod build() {
            AddVerticalItemsToVisualListingElementMethod addVerticalItemsToVisualListingElementMethod = new AddVerticalItemsToVisualListingElementMethod();
            populate(addVerticalItemsToVisualListingElementMethod);
            return addVerticalItemsToVisualListingElementMethod;
        }

        protected void populate(AddVerticalItemsToVisualListingElementMethod addVerticalItemsToVisualListingElementMethod) {
            super.populate((Method) addVerticalItemsToVisualListingElementMethod);
            addVerticalItemsToVisualListingElementMethod.setItems(this.items);
            addVerticalItemsToVisualListingElementMethod.setOnViewed(this.onViewed);
            addVerticalItemsToVisualListingElementMethod.setOnEndOfWidget(this.onEndOfWidget);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withItems(List<VerticalItemElement> list) {
            this.items = list;
            return this;
        }

        public Builder withOnEndOfWidget(List<Method> list) {
            this.onEndOfWidget = list;
            return this;
        }

        public Builder withOnViewed(List<Method> list) {
            this.onViewed = list;
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
        if (!(sOAObject instanceof AddVerticalItemsToVisualListingElementMethod)) {
            return 1;
        }
        AddVerticalItemsToVisualListingElementMethod addVerticalItemsToVisualListingElementMethod = (AddVerticalItemsToVisualListingElementMethod) sOAObject;
        List<VerticalItemElement> items = getItems();
        List<VerticalItemElement> items2 = addVerticalItemsToVisualListingElementMethod.getItems();
        if (items != items2) {
            if (items == null) {
                return -1;
            }
            if (items2 == null) {
                return 1;
            }
            if (items instanceof Comparable) {
                int iCompareTo = ((Comparable) items).compareTo(items2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!items.equals(items2)) {
                int iHashCode = items.hashCode();
                int iHashCode2 = items2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Method> onViewed = getOnViewed();
        List<Method> onViewed2 = addVerticalItemsToVisualListingElementMethod.getOnViewed();
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
                int iHashCode3 = onViewed.hashCode();
                int iHashCode4 = onViewed2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<Method> onEndOfWidget = getOnEndOfWidget();
        List<Method> onEndOfWidget2 = addVerticalItemsToVisualListingElementMethod.getOnEndOfWidget();
        if (onEndOfWidget != onEndOfWidget2) {
            if (onEndOfWidget == null) {
                return -1;
            }
            if (onEndOfWidget2 == null) {
                return 1;
            }
            if (onEndOfWidget instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onEndOfWidget).compareTo(onEndOfWidget2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onEndOfWidget.equals(onEndOfWidget2)) {
                int iHashCode5 = onEndOfWidget.hashCode();
                int iHashCode6 = onEndOfWidget2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof AddVerticalItemsToVisualListingElementMethod)) {
            return false;
        }
        AddVerticalItemsToVisualListingElementMethod addVerticalItemsToVisualListingElementMethod = (AddVerticalItemsToVisualListingElementMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getItems(), addVerticalItemsToVisualListingElementMethod.getItems()) && internalEqualityCheck(getOnViewed(), addVerticalItemsToVisualListingElementMethod.getOnViewed()) && internalEqualityCheck(getOnEndOfWidget(), addVerticalItemsToVisualListingElementMethod.getOnEndOfWidget());
    }

    public List<VerticalItemElement> getItems() {
        return this.items;
    }

    public List<Method> getOnEndOfWidget() {
        return this.onEndOfWidget;
    }

    public List<Method> getOnViewed() {
        return this.onViewed;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getItems(), getOnViewed(), getOnEndOfWidget());
    }

    public void setItems(List<VerticalItemElement> list) {
        this.items = list;
    }

    public void setOnEndOfWidget(List<Method> list) {
        this.onEndOfWidget = list;
    }

    public void setOnViewed(List<Method> list) {
        this.onViewed = list;
    }
}

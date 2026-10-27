package Podcast.Touch.LatestTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AddLatestItemElementsMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.LatestTemplateInterface.v1_0.AddLatestItemElementsMethod");
    private List<LatestItemElement> items;
    private List<Method> onEndOfList;
    private List<Method> onViewed;

    public static class Builder extends Method.Builder {
        protected List<LatestItemElement> items;
        protected List<Method> onEndOfList;
        protected List<Method> onViewed;

        public AddLatestItemElementsMethod build() {
            AddLatestItemElementsMethod addLatestItemElementsMethod = new AddLatestItemElementsMethod();
            populate(addLatestItemElementsMethod);
            return addLatestItemElementsMethod;
        }

        protected void populate(AddLatestItemElementsMethod addLatestItemElementsMethod) {
            super.populate((Method) addLatestItemElementsMethod);
            addLatestItemElementsMethod.setItems(this.items);
            addLatestItemElementsMethod.setOnViewed(this.onViewed);
            addLatestItemElementsMethod.setOnEndOfList(this.onEndOfList);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withItems(List<LatestItemElement> list) {
            this.items = list;
            return this;
        }

        public Builder withOnEndOfList(List<Method> list) {
            this.onEndOfList = list;
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
        if (!(sOAObject instanceof AddLatestItemElementsMethod)) {
            return 1;
        }
        AddLatestItemElementsMethod addLatestItemElementsMethod = (AddLatestItemElementsMethod) sOAObject;
        List<LatestItemElement> items = getItems();
        List<LatestItemElement> items2 = addLatestItemElementsMethod.getItems();
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
        List<Method> onViewed2 = addLatestItemElementsMethod.getOnViewed();
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
        List<Method> onEndOfList = getOnEndOfList();
        List<Method> onEndOfList2 = addLatestItemElementsMethod.getOnEndOfList();
        if (onEndOfList != onEndOfList2) {
            if (onEndOfList == null) {
                return -1;
            }
            if (onEndOfList2 == null) {
                return 1;
            }
            if (onEndOfList instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onEndOfList).compareTo(onEndOfList2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onEndOfList.equals(onEndOfList2)) {
                int iHashCode5 = onEndOfList.hashCode();
                int iHashCode6 = onEndOfList2.hashCode();
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
        if (!(obj instanceof AddLatestItemElementsMethod)) {
            return false;
        }
        AddLatestItemElementsMethod addLatestItemElementsMethod = (AddLatestItemElementsMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getItems(), addLatestItemElementsMethod.getItems()) && internalEqualityCheck(getOnViewed(), addLatestItemElementsMethod.getOnViewed()) && internalEqualityCheck(getOnEndOfList(), addLatestItemElementsMethod.getOnEndOfList());
    }

    public List<LatestItemElement> getItems() {
        return this.items;
    }

    public List<Method> getOnEndOfList() {
        return this.onEndOfList;
    }

    public List<Method> getOnViewed() {
        return this.onViewed;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getItems(), getOnViewed(), getOnEndOfList());
    }

    public void setItems(List<LatestItemElement> list) {
        this.items = list;
    }

    public void setOnEndOfList(List<Method> list) {
        this.onEndOfList = list;
    }

    public void setOnViewed(List<Method> list) {
        this.onViewed = list;
    }
}

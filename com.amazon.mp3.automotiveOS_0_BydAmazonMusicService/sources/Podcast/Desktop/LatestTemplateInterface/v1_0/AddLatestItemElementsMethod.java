package Podcast.Desktop.LatestTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AddLatestItemElementsMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.LatestTemplateInterface.v1_0.AddLatestItemElementsMethod");
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
            addLatestItemElementsMethod.setOnViewed(this.onViewed);
            addLatestItemElementsMethod.setOnEndOfList(this.onEndOfList);
            addLatestItemElementsMethod.setItems(this.items);
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
                int iCompareTo = ((Comparable) onViewed).compareTo(onViewed2);
                if (iCompareTo != 0) {
                    return iCompareTo;
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
                int iCompareTo2 = ((Comparable) onEndOfList).compareTo(onEndOfList2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!onEndOfList.equals(onEndOfList2)) {
                int iHashCode3 = onEndOfList.hashCode();
                int iHashCode4 = onEndOfList2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
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
                int iCompareTo3 = ((Comparable) items).compareTo(items2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!items.equals(items2)) {
                int iHashCode5 = items.hashCode();
                int iHashCode6 = items2.hashCode();
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
        return super.equals(obj) && internalEqualityCheck(getOnViewed(), addLatestItemElementsMethod.getOnViewed()) && internalEqualityCheck(getOnEndOfList(), addLatestItemElementsMethod.getOnEndOfList()) && internalEqualityCheck(getItems(), addLatestItemElementsMethod.getItems());
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnViewed(), getOnEndOfList(), getItems());
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

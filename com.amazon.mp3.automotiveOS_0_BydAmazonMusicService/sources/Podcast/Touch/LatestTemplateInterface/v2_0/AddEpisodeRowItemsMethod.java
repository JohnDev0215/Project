package Podcast.Touch.LatestTemplateInterface.v2_0;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AddEpisodeRowItemsMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.LatestTemplateInterface.v2_0.AddEpisodeRowItemsMethod");
    private List<EpisodeRowItemElement> items;
    private List<Method> onEndOfList;
    private List<Method> onViewed;

    public static class Builder extends Method.Builder {
        protected List<EpisodeRowItemElement> items;
        protected List<Method> onEndOfList;
        protected List<Method> onViewed;

        public AddEpisodeRowItemsMethod build() {
            AddEpisodeRowItemsMethod addEpisodeRowItemsMethod = new AddEpisodeRowItemsMethod();
            populate(addEpisodeRowItemsMethod);
            return addEpisodeRowItemsMethod;
        }

        protected void populate(AddEpisodeRowItemsMethod addEpisodeRowItemsMethod) {
            super.populate((Method) addEpisodeRowItemsMethod);
            addEpisodeRowItemsMethod.setOnEndOfList(this.onEndOfList);
            addEpisodeRowItemsMethod.setOnViewed(this.onViewed);
            addEpisodeRowItemsMethod.setItems(this.items);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withItems(List<EpisodeRowItemElement> list) {
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
        if (!(sOAObject instanceof AddEpisodeRowItemsMethod)) {
            return 1;
        }
        AddEpisodeRowItemsMethod addEpisodeRowItemsMethod = (AddEpisodeRowItemsMethod) sOAObject;
        List<Method> onEndOfList = getOnEndOfList();
        List<Method> onEndOfList2 = addEpisodeRowItemsMethod.getOnEndOfList();
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
        List<Method> onViewed = getOnViewed();
        List<Method> onViewed2 = addEpisodeRowItemsMethod.getOnViewed();
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
        List<EpisodeRowItemElement> items = getItems();
        List<EpisodeRowItemElement> items2 = addEpisodeRowItemsMethod.getItems();
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
        if (!(obj instanceof AddEpisodeRowItemsMethod)) {
            return false;
        }
        AddEpisodeRowItemsMethod addEpisodeRowItemsMethod = (AddEpisodeRowItemsMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getOnEndOfList(), addEpisodeRowItemsMethod.getOnEndOfList()) && internalEqualityCheck(getOnViewed(), addEpisodeRowItemsMethod.getOnViewed()) && internalEqualityCheck(getItems(), addEpisodeRowItemsMethod.getItems());
    }

    public List<EpisodeRowItemElement> getItems() {
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnEndOfList(), getOnViewed(), getItems());
    }

    public void setItems(List<EpisodeRowItemElement> list) {
        this.items = list;
    }

    public void setOnEndOfList(List<Method> list) {
        this.onEndOfList = list;
    }

    public void setOnViewed(List<Method> list) {
        this.onViewed = list;
    }
}

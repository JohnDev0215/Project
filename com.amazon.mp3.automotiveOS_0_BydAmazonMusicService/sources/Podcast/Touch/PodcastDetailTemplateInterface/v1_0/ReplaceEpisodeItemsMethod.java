package Podcast.Touch.PodcastDetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ReplaceEpisodeItemsMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PodcastDetailTemplateInterface.v1_0.ReplaceEpisodeItemsMethod");
    private List<EpisodeItemElement> items;
    private List<Method> onEndOfList;
    private List<Method> onViewed;

    public static class Builder extends Method.Builder {
        protected List<EpisodeItemElement> items;
        protected List<Method> onEndOfList;
        protected List<Method> onViewed;

        public ReplaceEpisodeItemsMethod build() {
            ReplaceEpisodeItemsMethod replaceEpisodeItemsMethod = new ReplaceEpisodeItemsMethod();
            populate(replaceEpisodeItemsMethod);
            return replaceEpisodeItemsMethod;
        }

        protected void populate(ReplaceEpisodeItemsMethod replaceEpisodeItemsMethod) {
            super.populate((Method) replaceEpisodeItemsMethod);
            replaceEpisodeItemsMethod.setOnEndOfList(this.onEndOfList);
            replaceEpisodeItemsMethod.setOnViewed(this.onViewed);
            replaceEpisodeItemsMethod.setItems(this.items);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withItems(List<EpisodeItemElement> list) {
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
        if (!(sOAObject instanceof ReplaceEpisodeItemsMethod)) {
            return 1;
        }
        ReplaceEpisodeItemsMethod replaceEpisodeItemsMethod = (ReplaceEpisodeItemsMethod) sOAObject;
        List<Method> onEndOfList = getOnEndOfList();
        List<Method> onEndOfList2 = replaceEpisodeItemsMethod.getOnEndOfList();
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
        List<Method> onViewed2 = replaceEpisodeItemsMethod.getOnViewed();
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
        List<EpisodeItemElement> items = getItems();
        List<EpisodeItemElement> items2 = replaceEpisodeItemsMethod.getItems();
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
        if (!(obj instanceof ReplaceEpisodeItemsMethod)) {
            return false;
        }
        ReplaceEpisodeItemsMethod replaceEpisodeItemsMethod = (ReplaceEpisodeItemsMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getOnEndOfList(), replaceEpisodeItemsMethod.getOnEndOfList()) && internalEqualityCheck(getOnViewed(), replaceEpisodeItemsMethod.getOnViewed()) && internalEqualityCheck(getItems(), replaceEpisodeItemsMethod.getItems());
    }

    public List<EpisodeItemElement> getItems() {
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

    public void setItems(List<EpisodeItemElement> list) {
        this.items = list;
    }

    public void setOnEndOfList(List<Method> list) {
        this.onEndOfList = list;
    }

    public void setOnViewed(List<Method> list) {
        this.onViewed = list;
    }
}

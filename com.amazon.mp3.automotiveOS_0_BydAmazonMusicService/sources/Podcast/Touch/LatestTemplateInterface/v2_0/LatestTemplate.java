package Podcast.Touch.LatestTemplateInterface.v2_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LatestTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.LatestTemplateInterface.v2_0.LatestTemplate");
    private FeaturedPinnedElement featuredPinnedElement;
    private String header;
    private List<EpisodeRowItemElement> items;
    private List<Method> onEndOfList;

    public static class Builder extends Template.Builder {
        protected FeaturedPinnedElement featuredPinnedElement;
        protected String header;
        protected List<EpisodeRowItemElement> items;
        protected List<Method> onEndOfList;

        public LatestTemplate build() {
            LatestTemplate latestTemplate = new LatestTemplate();
            populate(latestTemplate);
            return latestTemplate;
        }

        protected void populate(LatestTemplate latestTemplate) {
            super.populate((Template) latestTemplate);
            latestTemplate.setItems(this.items);
            latestTemplate.setHeader(this.header);
            latestTemplate.setOnEndOfList(this.onEndOfList);
            latestTemplate.setFeaturedPinnedElement(this.featuredPinnedElement);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withCacheSeconds(Long l) {
            super.withCacheSeconds(l);
            return this;
        }

        public Builder withFeaturedPinnedElement(FeaturedPinnedElement featuredPinnedElement) {
            this.featuredPinnedElement = featuredPinnedElement;
            return this;
        }

        public Builder withHeader(String str) {
            this.header = str;
            return this;
        }

        public Builder withItems(List<EpisodeRowItemElement> list) {
            this.items = list;
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withLatencyMetricElement(LatencyMetricElement latencyMetricElement) {
            super.withLatencyMetricElement(latencyMetricElement);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withLaunchMode(String str) {
            super.withLaunchMode(str);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnBound(List<Method> list) {
            super.withOnBound(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnBound(List list) {
            return withOnBound((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnCreated(List<Method> list) {
            super.withOnCreated(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnCreated(List list) {
            return withOnCreated((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnDismissed(List<Method> list) {
            super.withOnDismissed(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnDismissed(List list) {
            return withOnDismissed((List<Method>) list);
        }

        public Builder withOnEndOfList(List<Method> list) {
            this.onEndOfList = list;
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnViewed(List list) {
            return withOnViewed((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        @Deprecated
        public Builder withPageType(String str) {
            super.withPageType(str);
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
    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof LatestTemplate)) {
            return 1;
        }
        LatestTemplate latestTemplate = (LatestTemplate) sOAObject;
        List<EpisodeRowItemElement> items = getItems();
        List<EpisodeRowItemElement> items2 = latestTemplate.getItems();
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
        String header = getHeader();
        String header2 = latestTemplate.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo2 = header.compareTo(header2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Method> onEndOfList = getOnEndOfList();
        List<Method> onEndOfList2 = latestTemplate.getOnEndOfList();
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
        FeaturedPinnedElement featuredPinnedElement = getFeaturedPinnedElement();
        FeaturedPinnedElement featuredPinnedElement2 = latestTemplate.getFeaturedPinnedElement();
        if (featuredPinnedElement != featuredPinnedElement2) {
            if (featuredPinnedElement == null) {
                return -1;
            }
            if (featuredPinnedElement2 == null) {
                return 1;
            }
            int iCompareTo4 = featuredPinnedElement.compareTo(featuredPinnedElement2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof LatestTemplate)) {
            return false;
        }
        LatestTemplate latestTemplate = (LatestTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getItems(), latestTemplate.getItems()) && internalEqualityCheck(getHeader(), latestTemplate.getHeader()) && internalEqualityCheck(getOnEndOfList(), latestTemplate.getOnEndOfList()) && internalEqualityCheck(getFeaturedPinnedElement(), latestTemplate.getFeaturedPinnedElement());
    }

    public FeaturedPinnedElement getFeaturedPinnedElement() {
        return this.featuredPinnedElement;
    }

    public String getHeader() {
        return this.header;
    }

    public List<EpisodeRowItemElement> getItems() {
        return this.items;
    }

    public List<Method> getOnEndOfList() {
        return this.onEndOfList;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getItems(), getHeader(), getOnEndOfList(), getFeaturedPinnedElement());
    }

    public void setFeaturedPinnedElement(FeaturedPinnedElement featuredPinnedElement) {
        this.featuredPinnedElement = featuredPinnedElement;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setItems(List<EpisodeRowItemElement> list) {
        this.items = list;
    }

    public void setOnEndOfList(List<Method> list) {
        this.onEndOfList = list;
    }
}

package Podcast.Desktop.EpisodesTemplateInterface.v1_0;

import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElement;
import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeRowTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.EpisodesTemplateInterface.v1_0.EpisodeRowTemplate");
    private String header;
    private List<EpisodeRowItemElement> items;
    private TemplateMetadataElement metadata;
    private List<Method> onEndOfList;

    public static class Builder extends Template.Builder {
        protected String header;
        protected List<EpisodeRowItemElement> items;
        protected TemplateMetadataElement metadata;
        protected List<Method> onEndOfList;

        public EpisodeRowTemplate build() {
            EpisodeRowTemplate episodeRowTemplate = new EpisodeRowTemplate();
            populate(episodeRowTemplate);
            return episodeRowTemplate;
        }

        protected void populate(EpisodeRowTemplate episodeRowTemplate) {
            super.populate((Template) episodeRowTemplate);
            episodeRowTemplate.setMetadata(this.metadata);
            episodeRowTemplate.setOnEndOfList(this.onEndOfList);
            episodeRowTemplate.setHeader(this.header);
            episodeRowTemplate.setItems(this.items);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withCacheSeconds(Long l) {
            super.withCacheSeconds(l);
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

        public Builder withMetadata(TemplateMetadataElement templateMetadataElement) {
            this.metadata = templateMetadataElement;
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
        if (!(sOAObject instanceof EpisodeRowTemplate)) {
            return 1;
        }
        EpisodeRowTemplate episodeRowTemplate = (EpisodeRowTemplate) sOAObject;
        TemplateMetadataElement metadata = getMetadata();
        TemplateMetadataElement metadata2 = episodeRowTemplate.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo = metadata.compareTo(metadata2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Method> onEndOfList = getOnEndOfList();
        List<Method> onEndOfList2 = episodeRowTemplate.getOnEndOfList();
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
        String header = getHeader();
        String header2 = episodeRowTemplate.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo3 = header.compareTo(header2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<EpisodeRowItemElement> items = getItems();
        List<EpisodeRowItemElement> items2 = episodeRowTemplate.getItems();
        if (items != items2) {
            if (items == null) {
                return -1;
            }
            if (items2 == null) {
                return 1;
            }
            if (items instanceof Comparable) {
                int iCompareTo4 = ((Comparable) items).compareTo(items2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!items.equals(items2)) {
                int iHashCode3 = items.hashCode();
                int iHashCode4 = items2.hashCode();
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

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof EpisodeRowTemplate)) {
            return false;
        }
        EpisodeRowTemplate episodeRowTemplate = (EpisodeRowTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getMetadata(), episodeRowTemplate.getMetadata()) && internalEqualityCheck(getOnEndOfList(), episodeRowTemplate.getOnEndOfList()) && internalEqualityCheck(getHeader(), episodeRowTemplate.getHeader()) && internalEqualityCheck(getItems(), episodeRowTemplate.getItems());
    }

    public String getHeader() {
        return this.header;
    }

    public List<EpisodeRowItemElement> getItems() {
        return this.items;
    }

    public TemplateMetadataElement getMetadata() {
        return this.metadata;
    }

    public List<Method> getOnEndOfList() {
        return this.onEndOfList;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMetadata(), getOnEndOfList(), getHeader(), getItems());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setItems(List<EpisodeRowItemElement> list) {
        this.items = list;
    }

    public void setMetadata(TemplateMetadataElement templateMetadataElement) {
        this.metadata = templateMetadataElement;
    }

    public void setOnEndOfList(List<Method> list) {
        this.onEndOfList = list;
    }
}

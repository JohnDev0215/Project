package Podcast.Touch.GalleryTemplateInterface.v1_0;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeDescriptiveShovelerElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeDescriptiveShovelerElement");
    private String header;
    private List<EpisodeRowItemElement> items;
    private EpisodeDescriptiveSeeMoreItemElement seeMore;

    public static class Builder extends WidgetElement.Builder {
        protected String header;
        protected List<EpisodeRowItemElement> items;
        protected EpisodeDescriptiveSeeMoreItemElement seeMore;

        public EpisodeDescriptiveShovelerElement build() {
            EpisodeDescriptiveShovelerElement episodeDescriptiveShovelerElement = new EpisodeDescriptiveShovelerElement();
            populate(episodeDescriptiveShovelerElement);
            return episodeDescriptiveShovelerElement;
        }

        protected void populate(EpisodeDescriptiveShovelerElement episodeDescriptiveShovelerElement) {
            super.populate((WidgetElement) episodeDescriptiveShovelerElement);
            episodeDescriptiveShovelerElement.setHeader(this.header);
            episodeDescriptiveShovelerElement.setSeeMore(this.seeMore);
            episodeDescriptiveShovelerElement.setItems(this.items);
        }

        public Builder withHeader(String str) {
            this.header = str;
            return this;
        }

        @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public Builder withId(String str) {
            super.withId(str);
            return this;
        }

        public Builder withItems(List<EpisodeRowItemElement> list) {
            this.items = list;
            return this;
        }

        @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public Builder withOnContentFirstViewed(List<Method> list) {
            super.withOnContentFirstViewed(list);
            return this;
        }

        @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnContentFirstViewed(List list) {
            return withOnContentFirstViewed((List<Method>) list);
        }

        @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnViewed(List list) {
            return withOnViewed((List<Method>) list);
        }

        public Builder withSeeMore(EpisodeDescriptiveSeeMoreItemElement episodeDescriptiveSeeMoreItemElement) {
            this.seeMore = episodeDescriptiveSeeMoreItemElement;
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
    @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof EpisodeDescriptiveShovelerElement)) {
            return 1;
        }
        EpisodeDescriptiveShovelerElement episodeDescriptiveShovelerElement = (EpisodeDescriptiveShovelerElement) sOAObject;
        String header = getHeader();
        String header2 = episodeDescriptiveShovelerElement.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo = header.compareTo(header2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        EpisodeDescriptiveSeeMoreItemElement seeMore = getSeeMore();
        EpisodeDescriptiveSeeMoreItemElement seeMore2 = episodeDescriptiveShovelerElement.getSeeMore();
        if (seeMore != seeMore2) {
            if (seeMore == null) {
                return -1;
            }
            if (seeMore2 == null) {
                return 1;
            }
            int iCompareTo2 = seeMore.compareTo(seeMore2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<EpisodeRowItemElement> items = getItems();
        List<EpisodeRowItemElement> items2 = episodeDescriptiveShovelerElement.getItems();
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
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof EpisodeDescriptiveShovelerElement)) {
            return false;
        }
        EpisodeDescriptiveShovelerElement episodeDescriptiveShovelerElement = (EpisodeDescriptiveShovelerElement) obj;
        return super.equals(obj) && internalEqualityCheck(getHeader(), episodeDescriptiveShovelerElement.getHeader()) && internalEqualityCheck(getSeeMore(), episodeDescriptiveShovelerElement.getSeeMore()) && internalEqualityCheck(getItems(), episodeDescriptiveShovelerElement.getItems());
    }

    public String getHeader() {
        return this.header;
    }

    public List<EpisodeRowItemElement> getItems() {
        return this.items;
    }

    public EpisodeDescriptiveSeeMoreItemElement getSeeMore() {
        return this.seeMore;
    }

    @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHeader(), getSeeMore(), getItems());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setItems(List<EpisodeRowItemElement> list) {
        this.items = list;
    }

    public void setSeeMore(EpisodeDescriptiveSeeMoreItemElement episodeDescriptiveSeeMoreItemElement) {
        this.seeMore = episodeDescriptiveSeeMoreItemElement;
    }
}

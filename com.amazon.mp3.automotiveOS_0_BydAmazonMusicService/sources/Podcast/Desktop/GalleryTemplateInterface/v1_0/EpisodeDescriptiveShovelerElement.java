package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeDescriptiveShovelerElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeDescriptiveShovelerElement");
    private String header;
    private List<EpisodeHorizontalItemElement> items;
    private EpisodeHorizontalSeeMoreItemElement seeMore;

    public static class Builder extends WidgetElement.Builder {
        protected String header;
        protected List<EpisodeHorizontalItemElement> items;
        protected EpisodeHorizontalSeeMoreItemElement seeMore;

        public EpisodeDescriptiveShovelerElement build() {
            EpisodeDescriptiveShovelerElement episodeDescriptiveShovelerElement = new EpisodeDescriptiveShovelerElement();
            populate(episodeDescriptiveShovelerElement);
            return episodeDescriptiveShovelerElement;
        }

        protected void populate(EpisodeDescriptiveShovelerElement episodeDescriptiveShovelerElement) {
            super.populate((WidgetElement) episodeDescriptiveShovelerElement);
            episodeDescriptiveShovelerElement.setItems(this.items);
            episodeDescriptiveShovelerElement.setHeader(this.header);
            episodeDescriptiveShovelerElement.setSeeMore(this.seeMore);
        }

        public Builder withHeader(String str) {
            this.header = str;
            return this;
        }

        public Builder withItems(List<EpisodeHorizontalItemElement> list) {
            this.items = list;
            return this;
        }

        @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnViewed(List list) {
            return withOnViewed((List<Method>) list);
        }

        public Builder withSeeMore(EpisodeHorizontalSeeMoreItemElement episodeHorizontalSeeMoreItemElement) {
            this.seeMore = episodeHorizontalSeeMoreItemElement;
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
    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
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
        List<EpisodeHorizontalItemElement> items = getItems();
        List<EpisodeHorizontalItemElement> items2 = episodeDescriptiveShovelerElement.getItems();
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
        String header2 = episodeDescriptiveShovelerElement.getHeader();
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
        EpisodeHorizontalSeeMoreItemElement seeMore = getSeeMore();
        EpisodeHorizontalSeeMoreItemElement seeMore2 = episodeDescriptiveShovelerElement.getSeeMore();
        if (seeMore != seeMore2) {
            if (seeMore == null) {
                return -1;
            }
            if (seeMore2 == null) {
                return 1;
            }
            int iCompareTo3 = seeMore.compareTo(seeMore2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof EpisodeDescriptiveShovelerElement)) {
            return false;
        }
        EpisodeDescriptiveShovelerElement episodeDescriptiveShovelerElement = (EpisodeDescriptiveShovelerElement) obj;
        return super.equals(obj) && internalEqualityCheck(getItems(), episodeDescriptiveShovelerElement.getItems()) && internalEqualityCheck(getHeader(), episodeDescriptiveShovelerElement.getHeader()) && internalEqualityCheck(getSeeMore(), episodeDescriptiveShovelerElement.getSeeMore());
    }

    public String getHeader() {
        return this.header;
    }

    public List<EpisodeHorizontalItemElement> getItems() {
        return this.items;
    }

    public EpisodeHorizontalSeeMoreItemElement getSeeMore() {
        return this.seeMore;
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getItems(), getHeader(), getSeeMore());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setItems(List<EpisodeHorizontalItemElement> list) {
        this.items = list;
    }

    public void setSeeMore(EpisodeHorizontalSeeMoreItemElement episodeHorizontalSeeMoreItemElement) {
        this.seeMore = episodeHorizontalSeeMoreItemElement;
    }
}

package Podcast.Touch.EpisodeGroupItemsInterface.v1_0;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeGroupItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.EpisodeGroupItemsInterface.v1_0.EpisodeGroupItemElement");
    private List<EpisodeRowItemElement> episodes;
    private HeaderRowItemElement header;

    public static class Builder extends TemplateElement.Builder {
        protected List<EpisodeRowItemElement> episodes;
        protected HeaderRowItemElement header;

        public EpisodeGroupItemElement build() {
            EpisodeGroupItemElement episodeGroupItemElement = new EpisodeGroupItemElement();
            populate(episodeGroupItemElement);
            return episodeGroupItemElement;
        }

        protected void populate(EpisodeGroupItemElement episodeGroupItemElement) {
            super.populate((TemplateElement) episodeGroupItemElement);
            episodeGroupItemElement.setHeader(this.header);
            episodeGroupItemElement.setEpisodes(this.episodes);
        }

        public Builder withEpisodes(List<EpisodeRowItemElement> list) {
            this.episodes = list;
            return this;
        }

        public Builder withHeader(HeaderRowItemElement headerRowItemElement) {
            this.header = headerRowItemElement;
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
    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof EpisodeGroupItemElement)) {
            return 1;
        }
        EpisodeGroupItemElement episodeGroupItemElement = (EpisodeGroupItemElement) sOAObject;
        HeaderRowItemElement header = getHeader();
        HeaderRowItemElement header2 = episodeGroupItemElement.getHeader();
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
        List<EpisodeRowItemElement> episodes = getEpisodes();
        List<EpisodeRowItemElement> episodes2 = episodeGroupItemElement.getEpisodes();
        if (episodes != episodes2) {
            if (episodes == null) {
                return -1;
            }
            if (episodes2 == null) {
                return 1;
            }
            if (episodes instanceof Comparable) {
                int iCompareTo2 = ((Comparable) episodes).compareTo(episodes2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!episodes.equals(episodes2)) {
                int iHashCode = episodes.hashCode();
                int iHashCode2 = episodes2.hashCode();
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

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof EpisodeGroupItemElement)) {
            return false;
        }
        EpisodeGroupItemElement episodeGroupItemElement = (EpisodeGroupItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getHeader(), episodeGroupItemElement.getHeader()) && internalEqualityCheck(getEpisodes(), episodeGroupItemElement.getEpisodes());
    }

    public List<EpisodeRowItemElement> getEpisodes() {
        return this.episodes;
    }

    public HeaderRowItemElement getHeader() {
        return this.header;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHeader(), getEpisodes());
    }

    public void setEpisodes(List<EpisodeRowItemElement> list) {
        this.episodes = list;
    }

    public void setHeader(HeaderRowItemElement headerRowItemElement) {
        this.header = headerRowItemElement;
    }
}

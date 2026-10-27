package Podcast.Touch.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class AboutElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.GalleryTemplateInterface.v1_0.AboutElement");
    private CompactPillNavigator compactPillNavigator;
    private String description;
    private TrailerElement trailer;

    public static class Builder extends TemplateElement.Builder {
        protected CompactPillNavigator compactPillNavigator;
        protected String description;
        protected TrailerElement trailer;

        public AboutElement build() {
            AboutElement aboutElement = new AboutElement();
            populate(aboutElement);
            return aboutElement;
        }

        protected void populate(AboutElement aboutElement) {
            super.populate((TemplateElement) aboutElement);
            aboutElement.setTrailer(this.trailer);
            aboutElement.setCompactPillNavigator(this.compactPillNavigator);
            aboutElement.setDescription(this.description);
        }

        public Builder withCompactPillNavigator(CompactPillNavigator compactPillNavigator) {
            this.compactPillNavigator = compactPillNavigator;
            return this;
        }

        public Builder withDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder withTrailer(TrailerElement trailerElement) {
            this.trailer = trailerElement;
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
        if (!(sOAObject instanceof AboutElement)) {
            return 1;
        }
        AboutElement aboutElement = (AboutElement) sOAObject;
        TrailerElement trailer = getTrailer();
        TrailerElement trailer2 = aboutElement.getTrailer();
        if (trailer != trailer2) {
            if (trailer == null) {
                return -1;
            }
            if (trailer2 == null) {
                return 1;
            }
            int iCompareTo = trailer.compareTo(trailer2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        CompactPillNavigator compactPillNavigator = getCompactPillNavigator();
        CompactPillNavigator compactPillNavigator2 = aboutElement.getCompactPillNavigator();
        if (compactPillNavigator != compactPillNavigator2) {
            if (compactPillNavigator == null) {
                return -1;
            }
            if (compactPillNavigator2 == null) {
                return 1;
            }
            int iCompareTo2 = compactPillNavigator.compareTo(compactPillNavigator2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String description = getDescription();
        String description2 = aboutElement.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo3 = description.compareTo(description2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof AboutElement)) {
            return false;
        }
        AboutElement aboutElement = (AboutElement) obj;
        return super.equals(obj) && internalEqualityCheck(getTrailer(), aboutElement.getTrailer()) && internalEqualityCheck(getCompactPillNavigator(), aboutElement.getCompactPillNavigator()) && internalEqualityCheck(getDescription(), aboutElement.getDescription());
    }

    public CompactPillNavigator getCompactPillNavigator() {
        return this.compactPillNavigator;
    }

    public String getDescription() {
        return this.description;
    }

    public TrailerElement getTrailer() {
        return this.trailer;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTrailer(), getCompactPillNavigator(), getDescription());
    }

    public void setCompactPillNavigator(CompactPillNavigator compactPillNavigator) {
        this.compactPillNavigator = compactPillNavigator;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setTrailer(TrailerElement trailerElement) {
        this.trailer = trailerElement;
    }
}

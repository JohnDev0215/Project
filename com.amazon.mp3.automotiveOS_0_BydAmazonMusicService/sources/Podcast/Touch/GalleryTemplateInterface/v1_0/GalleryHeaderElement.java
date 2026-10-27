package Podcast.Touch.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class GalleryHeaderElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.GalleryTemplateInterface.v1_0.GalleryHeaderElement");
    private String navBarHeader;
    private String pageHeader;
    private String pageSubheader;
    private Boolean showNavBarTextOnScroll;

    public static class Builder extends TemplateElement.Builder {
        protected String navBarHeader;
        protected String pageHeader;
        protected String pageSubheader;
        protected Boolean showNavBarTextOnScroll;

        public GalleryHeaderElement build() {
            GalleryHeaderElement galleryHeaderElement = new GalleryHeaderElement();
            populate(galleryHeaderElement);
            return galleryHeaderElement;
        }

        protected void populate(GalleryHeaderElement galleryHeaderElement) {
            super.populate((TemplateElement) galleryHeaderElement);
            galleryHeaderElement.setPageSubheader(this.pageSubheader);
            galleryHeaderElement.setShowNavBarTextOnScroll(this.showNavBarTextOnScroll);
            galleryHeaderElement.setNavBarHeader(this.navBarHeader);
            galleryHeaderElement.setPageHeader(this.pageHeader);
        }

        public Builder withNavBarHeader(String str) {
            this.navBarHeader = str;
            return this;
        }

        public Builder withPageHeader(String str) {
            this.pageHeader = str;
            return this;
        }

        public Builder withPageSubheader(String str) {
            this.pageSubheader = str;
            return this;
        }

        public Builder withShowNavBarTextOnScroll(Boolean bool) {
            this.showNavBarTextOnScroll = bool;
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
        if (!(sOAObject instanceof GalleryHeaderElement)) {
            return 1;
        }
        GalleryHeaderElement galleryHeaderElement = (GalleryHeaderElement) sOAObject;
        String pageSubheader = getPageSubheader();
        String pageSubheader2 = galleryHeaderElement.getPageSubheader();
        if (pageSubheader != pageSubheader2) {
            if (pageSubheader == null) {
                return -1;
            }
            if (pageSubheader2 == null) {
                return 1;
            }
            int iCompareTo = pageSubheader.compareTo(pageSubheader2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsShowNavBarTextOnScroll = isShowNavBarTextOnScroll();
        Boolean boolIsShowNavBarTextOnScroll2 = galleryHeaderElement.isShowNavBarTextOnScroll();
        if (boolIsShowNavBarTextOnScroll != boolIsShowNavBarTextOnScroll2) {
            if (boolIsShowNavBarTextOnScroll == null) {
                return -1;
            }
            if (boolIsShowNavBarTextOnScroll2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsShowNavBarTextOnScroll.compareTo(boolIsShowNavBarTextOnScroll2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String navBarHeader = getNavBarHeader();
        String navBarHeader2 = galleryHeaderElement.getNavBarHeader();
        if (navBarHeader != navBarHeader2) {
            if (navBarHeader == null) {
                return -1;
            }
            if (navBarHeader2 == null) {
                return 1;
            }
            int iCompareTo3 = navBarHeader.compareTo(navBarHeader2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String pageHeader = getPageHeader();
        String pageHeader2 = galleryHeaderElement.getPageHeader();
        if (pageHeader != pageHeader2) {
            if (pageHeader == null) {
                return -1;
            }
            if (pageHeader2 == null) {
                return 1;
            }
            int iCompareTo4 = pageHeader.compareTo(pageHeader2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof GalleryHeaderElement)) {
            return false;
        }
        GalleryHeaderElement galleryHeaderElement = (GalleryHeaderElement) obj;
        return super.equals(obj) && internalEqualityCheck(getPageSubheader(), galleryHeaderElement.getPageSubheader()) && internalEqualityCheck(isShowNavBarTextOnScroll(), galleryHeaderElement.isShowNavBarTextOnScroll()) && internalEqualityCheck(getNavBarHeader(), galleryHeaderElement.getNavBarHeader()) && internalEqualityCheck(getPageHeader(), galleryHeaderElement.getPageHeader());
    }

    public String getNavBarHeader() {
        return this.navBarHeader;
    }

    public String getPageHeader() {
        return this.pageHeader;
    }

    public String getPageSubheader() {
        return this.pageSubheader;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPageSubheader(), isShowNavBarTextOnScroll(), getNavBarHeader(), getPageHeader());
    }

    public Boolean isShowNavBarTextOnScroll() {
        return this.showNavBarTextOnScroll;
    }

    public void setNavBarHeader(String str) {
        this.navBarHeader = str;
    }

    public void setPageHeader(String str) {
        this.pageHeader = str;
    }

    public void setPageSubheader(String str) {
        this.pageSubheader = str;
    }

    public void setShowNavBarTextOnScroll(Boolean bool) {
        this.showNavBarTextOnScroll = bool;
    }
}

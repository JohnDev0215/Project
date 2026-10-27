package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BookmarksElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.BookmarksElement");
    private String header;
    private List<Method> onSeeMoreSelected;
    private String seeMoreText;

    public static class Builder extends WidgetElement.Builder {
        protected String header;
        protected List<Method> onSeeMoreSelected;
        protected String seeMoreText;

        public BookmarksElement build() {
            BookmarksElement bookmarksElement = new BookmarksElement();
            populate(bookmarksElement);
            return bookmarksElement;
        }

        protected void populate(BookmarksElement bookmarksElement) {
            super.populate((WidgetElement) bookmarksElement);
            bookmarksElement.setHeader(this.header);
            bookmarksElement.setSeeMoreText(this.seeMoreText);
            bookmarksElement.setOnSeeMoreSelected(this.onSeeMoreSelected);
        }

        public Builder withHeader(String str) {
            this.header = str;
            return this;
        }

        public Builder withOnSeeMoreSelected(List<Method> list) {
            this.onSeeMoreSelected = list;
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

        public Builder withSeeMoreText(String str) {
            this.seeMoreText = str;
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
        if (!(sOAObject instanceof BookmarksElement)) {
            return 1;
        }
        BookmarksElement bookmarksElement = (BookmarksElement) sOAObject;
        String header = getHeader();
        String header2 = bookmarksElement.getHeader();
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
        String seeMoreText = getSeeMoreText();
        String seeMoreText2 = bookmarksElement.getSeeMoreText();
        if (seeMoreText != seeMoreText2) {
            if (seeMoreText == null) {
                return -1;
            }
            if (seeMoreText2 == null) {
                return 1;
            }
            int iCompareTo2 = seeMoreText.compareTo(seeMoreText2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Method> onSeeMoreSelected = getOnSeeMoreSelected();
        List<Method> onSeeMoreSelected2 = bookmarksElement.getOnSeeMoreSelected();
        if (onSeeMoreSelected != onSeeMoreSelected2) {
            if (onSeeMoreSelected == null) {
                return -1;
            }
            if (onSeeMoreSelected2 == null) {
                return 1;
            }
            if (onSeeMoreSelected instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onSeeMoreSelected).compareTo(onSeeMoreSelected2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onSeeMoreSelected.equals(onSeeMoreSelected2)) {
                int iHashCode = onSeeMoreSelected.hashCode();
                int iHashCode2 = onSeeMoreSelected2.hashCode();
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

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof BookmarksElement)) {
            return false;
        }
        BookmarksElement bookmarksElement = (BookmarksElement) obj;
        return super.equals(obj) && internalEqualityCheck(getHeader(), bookmarksElement.getHeader()) && internalEqualityCheck(getSeeMoreText(), bookmarksElement.getSeeMoreText()) && internalEqualityCheck(getOnSeeMoreSelected(), bookmarksElement.getOnSeeMoreSelected());
    }

    public String getHeader() {
        return this.header;
    }

    public List<Method> getOnSeeMoreSelected() {
        return this.onSeeMoreSelected;
    }

    public String getSeeMoreText() {
        return this.seeMoreText;
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHeader(), getSeeMoreText(), getOnSeeMoreSelected());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setOnSeeMoreSelected(List<Method> list) {
        this.onSeeMoreSelected = list;
    }

    public void setSeeMoreText(String str) {
        this.seeMoreText = str;
    }
}

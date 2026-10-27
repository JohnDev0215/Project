package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SavesElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.SavesElement");
    private String header;
    private String seeMoreText;

    public static class Builder extends WidgetElement.Builder {
        protected String header;
        protected String seeMoreText;

        public SavesElement build() {
            SavesElement savesElement = new SavesElement();
            populate(savesElement);
            return savesElement;
        }

        protected void populate(SavesElement savesElement) {
            super.populate((WidgetElement) savesElement);
            savesElement.setSeeMoreText(this.seeMoreText);
            savesElement.setHeader(this.header);
        }

        public Builder withHeader(String str) {
            this.header = str;
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
        if (!(sOAObject instanceof SavesElement)) {
            return 1;
        }
        SavesElement savesElement = (SavesElement) sOAObject;
        String seeMoreText = getSeeMoreText();
        String seeMoreText2 = savesElement.getSeeMoreText();
        if (seeMoreText != seeMoreText2) {
            if (seeMoreText == null) {
                return -1;
            }
            if (seeMoreText2 == null) {
                return 1;
            }
            int iCompareTo = seeMoreText.compareTo(seeMoreText2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String header = getHeader();
        String header2 = savesElement.getHeader();
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
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof SavesElement)) {
            return false;
        }
        SavesElement savesElement = (SavesElement) obj;
        return super.equals(obj) && internalEqualityCheck(getSeeMoreText(), savesElement.getSeeMoreText()) && internalEqualityCheck(getHeader(), savesElement.getHeader());
    }

    public String getHeader() {
        return this.header;
    }

    public String getSeeMoreText() {
        return this.seeMoreText;
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSeeMoreText(), getHeader());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setSeeMoreText(String str) {
        this.seeMoreText = str;
    }
}

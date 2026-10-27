package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RegularBarkerElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.RegularBarkerElement");
    private String backgroundImageLarge;
    private String backgroundImageSmall;
    private List<Method> onItemSelected;
    private String title;

    public static class Builder extends WidgetElement.Builder {
        protected String backgroundImageLarge;
        protected String backgroundImageSmall;
        protected List<Method> onItemSelected;
        protected String title;

        public RegularBarkerElement build() {
            RegularBarkerElement regularBarkerElement = new RegularBarkerElement();
            populate(regularBarkerElement);
            return regularBarkerElement;
        }

        protected void populate(RegularBarkerElement regularBarkerElement) {
            super.populate((WidgetElement) regularBarkerElement);
            regularBarkerElement.setBackgroundImageLarge(this.backgroundImageLarge);
            regularBarkerElement.setTitle(this.title);
            regularBarkerElement.setBackgroundImageSmall(this.backgroundImageSmall);
            regularBarkerElement.setOnItemSelected(this.onItemSelected);
        }

        public Builder withBackgroundImageLarge(String str) {
            this.backgroundImageLarge = str;
            return this;
        }

        public Builder withBackgroundImageSmall(String str) {
            this.backgroundImageSmall = str;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
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

        public Builder withTitle(String str) {
            this.title = str;
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
        if (!(sOAObject instanceof RegularBarkerElement)) {
            return 1;
        }
        RegularBarkerElement regularBarkerElement = (RegularBarkerElement) sOAObject;
        String backgroundImageLarge = getBackgroundImageLarge();
        String backgroundImageLarge2 = regularBarkerElement.getBackgroundImageLarge();
        if (backgroundImageLarge != backgroundImageLarge2) {
            if (backgroundImageLarge == null) {
                return -1;
            }
            if (backgroundImageLarge2 == null) {
                return 1;
            }
            int iCompareTo = backgroundImageLarge.compareTo(backgroundImageLarge2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String title = getTitle();
        String title2 = regularBarkerElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo2 = title.compareTo(title2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String backgroundImageSmall = getBackgroundImageSmall();
        String backgroundImageSmall2 = regularBarkerElement.getBackgroundImageSmall();
        if (backgroundImageSmall != backgroundImageSmall2) {
            if (backgroundImageSmall == null) {
                return -1;
            }
            if (backgroundImageSmall2 == null) {
                return 1;
            }
            int iCompareTo3 = backgroundImageSmall.compareTo(backgroundImageSmall2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = regularBarkerElement.getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo4 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!onItemSelected.equals(onItemSelected2)) {
                int iHashCode = onItemSelected.hashCode();
                int iHashCode2 = onItemSelected2.hashCode();
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
        if (!(obj instanceof RegularBarkerElement)) {
            return false;
        }
        RegularBarkerElement regularBarkerElement = (RegularBarkerElement) obj;
        return super.equals(obj) && internalEqualityCheck(getBackgroundImageLarge(), regularBarkerElement.getBackgroundImageLarge()) && internalEqualityCheck(getTitle(), regularBarkerElement.getTitle()) && internalEqualityCheck(getBackgroundImageSmall(), regularBarkerElement.getBackgroundImageSmall()) && internalEqualityCheck(getOnItemSelected(), regularBarkerElement.getOnItemSelected());
    }

    public String getBackgroundImageLarge() {
        return this.backgroundImageLarge;
    }

    public String getBackgroundImageSmall() {
        return this.backgroundImageSmall;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // Podcast.Desktop.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getBackgroundImageLarge(), getTitle(), getBackgroundImageSmall(), getOnItemSelected());
    }

    public void setBackgroundImageLarge(String str) {
        this.backgroundImageLarge = str;
    }

    public void setBackgroundImageSmall(String str) {
        this.backgroundImageSmall = str;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}

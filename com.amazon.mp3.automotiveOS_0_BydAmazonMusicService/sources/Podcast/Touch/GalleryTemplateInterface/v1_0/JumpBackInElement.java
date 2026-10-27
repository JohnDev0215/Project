package Podcast.Touch.GalleryTemplateInterface.v1_0;

import Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class JumpBackInElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.GalleryTemplateInterface.v1_0.JumpBackInElement");
    private String header;
    private List<Method> onSeeMoreSelected;
    private String seeMoreText;

    public static class Builder extends WidgetElement.Builder {
        protected String header;
        protected List<Method> onSeeMoreSelected;
        protected String seeMoreText;

        public JumpBackInElement build() {
            JumpBackInElement jumpBackInElement = new JumpBackInElement();
            populate(jumpBackInElement);
            return jumpBackInElement;
        }

        protected void populate(JumpBackInElement jumpBackInElement) {
            super.populate((WidgetElement) jumpBackInElement);
            jumpBackInElement.setHeader(this.header);
            jumpBackInElement.setOnSeeMoreSelected(this.onSeeMoreSelected);
            jumpBackInElement.setSeeMoreText(this.seeMoreText);
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

        @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public Builder withOnContentFirstViewed(List<Method> list) {
            super.withOnContentFirstViewed(list);
            return this;
        }

        @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement.Builder
        public /* bridge */ /* synthetic */ WidgetElement.Builder withOnContentFirstViewed(List list) {
            return withOnContentFirstViewed((List<Method>) list);
        }

        public Builder withOnSeeMoreSelected(List<Method> list) {
            this.onSeeMoreSelected = list;
            return this;
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
    @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof JumpBackInElement)) {
            return 1;
        }
        JumpBackInElement jumpBackInElement = (JumpBackInElement) sOAObject;
        String header = getHeader();
        String header2 = jumpBackInElement.getHeader();
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
        List<Method> onSeeMoreSelected = getOnSeeMoreSelected();
        List<Method> onSeeMoreSelected2 = jumpBackInElement.getOnSeeMoreSelected();
        if (onSeeMoreSelected != onSeeMoreSelected2) {
            if (onSeeMoreSelected == null) {
                return -1;
            }
            if (onSeeMoreSelected2 == null) {
                return 1;
            }
            if (onSeeMoreSelected instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onSeeMoreSelected).compareTo(onSeeMoreSelected2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
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
        String seeMoreText = getSeeMoreText();
        String seeMoreText2 = jumpBackInElement.getSeeMoreText();
        if (seeMoreText != seeMoreText2) {
            if (seeMoreText == null) {
                return -1;
            }
            if (seeMoreText2 == null) {
                return 1;
            }
            int iCompareTo3 = seeMoreText.compareTo(seeMoreText2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof JumpBackInElement)) {
            return false;
        }
        JumpBackInElement jumpBackInElement = (JumpBackInElement) obj;
        return super.equals(obj) && internalEqualityCheck(getHeader(), jumpBackInElement.getHeader()) && internalEqualityCheck(getOnSeeMoreSelected(), jumpBackInElement.getOnSeeMoreSelected()) && internalEqualityCheck(getSeeMoreText(), jumpBackInElement.getSeeMoreText());
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

    @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHeader(), getOnSeeMoreSelected(), getSeeMoreText());
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

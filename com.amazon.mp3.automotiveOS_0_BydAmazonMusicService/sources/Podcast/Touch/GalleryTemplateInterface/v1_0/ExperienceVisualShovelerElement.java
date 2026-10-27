package Podcast.Touch.GalleryTemplateInterface.v1_0;

import Podcast.Touch.CommonButtonElementInterface.v1_0.PrimaryButtonElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement;
import Podcast.Touch.MediaItemsInterface.v1_0.MediaItemElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ExperienceVisualShovelerElement extends WidgetElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.GalleryTemplateInterface.v1_0.ExperienceVisualShovelerElement");
    private String header;
    private List<MediaItemElement> items;
    private List<Method> onItemSelected;
    private PrimaryButtonElement primaryButton;
    private String secondaryText;

    public static class Builder extends WidgetElement.Builder {
        protected String header;
        protected List<MediaItemElement> items;
        protected List<Method> onItemSelected;
        protected PrimaryButtonElement primaryButton;
        protected String secondaryText;

        public ExperienceVisualShovelerElement build() {
            ExperienceVisualShovelerElement experienceVisualShovelerElement = new ExperienceVisualShovelerElement();
            populate(experienceVisualShovelerElement);
            return experienceVisualShovelerElement;
        }

        protected void populate(ExperienceVisualShovelerElement experienceVisualShovelerElement) {
            super.populate((WidgetElement) experienceVisualShovelerElement);
            experienceVisualShovelerElement.setItems(this.items);
            experienceVisualShovelerElement.setHeader(this.header);
            experienceVisualShovelerElement.setSecondaryText(this.secondaryText);
            experienceVisualShovelerElement.setOnItemSelected(this.onItemSelected);
            experienceVisualShovelerElement.setPrimaryButton(this.primaryButton);
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

        public Builder withItems(List<MediaItemElement> list) {
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

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
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

        public Builder withPrimaryButton(PrimaryButtonElement primaryButtonElement) {
            this.primaryButton = primaryButtonElement;
            return this;
        }

        public Builder withSecondaryText(String str) {
            this.secondaryText = str;
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
        if (!(sOAObject instanceof ExperienceVisualShovelerElement)) {
            return 1;
        }
        ExperienceVisualShovelerElement experienceVisualShovelerElement = (ExperienceVisualShovelerElement) sOAObject;
        List<MediaItemElement> items = getItems();
        List<MediaItemElement> items2 = experienceVisualShovelerElement.getItems();
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
        String header2 = experienceVisualShovelerElement.getHeader();
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
        String secondaryText = getSecondaryText();
        String secondaryText2 = experienceVisualShovelerElement.getSecondaryText();
        if (secondaryText != secondaryText2) {
            if (secondaryText == null) {
                return -1;
            }
            if (secondaryText2 == null) {
                return 1;
            }
            int iCompareTo3 = secondaryText.compareTo(secondaryText2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = experienceVisualShovelerElement.getOnItemSelected();
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
                int iHashCode3 = onItemSelected.hashCode();
                int iHashCode4 = onItemSelected2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        PrimaryButtonElement primaryButton = getPrimaryButton();
        PrimaryButtonElement primaryButton2 = experienceVisualShovelerElement.getPrimaryButton();
        if (primaryButton != primaryButton2) {
            if (primaryButton == null) {
                return -1;
            }
            if (primaryButton2 == null) {
                return 1;
            }
            int iCompareTo5 = primaryButton.compareTo(primaryButton2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof ExperienceVisualShovelerElement)) {
            return false;
        }
        ExperienceVisualShovelerElement experienceVisualShovelerElement = (ExperienceVisualShovelerElement) obj;
        return super.equals(obj) && internalEqualityCheck(getItems(), experienceVisualShovelerElement.getItems()) && internalEqualityCheck(getHeader(), experienceVisualShovelerElement.getHeader()) && internalEqualityCheck(getSecondaryText(), experienceVisualShovelerElement.getSecondaryText()) && internalEqualityCheck(getOnItemSelected(), experienceVisualShovelerElement.getOnItemSelected()) && internalEqualityCheck(getPrimaryButton(), experienceVisualShovelerElement.getPrimaryButton());
    }

    public String getHeader() {
        return this.header;
    }

    public List<MediaItemElement> getItems() {
        return this.items;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public PrimaryButtonElement getPrimaryButton() {
        return this.primaryButton;
    }

    public String getSecondaryText() {
        return this.secondaryText;
    }

    @Override // Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getItems(), getHeader(), getSecondaryText(), getOnItemSelected(), getPrimaryButton());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setItems(List<MediaItemElement> list) {
        this.items = list;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setPrimaryButton(PrimaryButtonElement primaryButtonElement) {
        this.primaryButton = primaryButtonElement;
    }

    public void setSecondaryText(String str) {
        this.secondaryText = str;
    }
}

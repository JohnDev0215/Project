package Podcast.Touch.RibbonNotificationElementInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RibbonMessageElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonMessageElement");
    private String text;
    private List<TextWithLinkElement> textWithLinks;

    public static class Builder extends TemplateElement.Builder {
        protected String text;
        protected List<TextWithLinkElement> textWithLinks;

        public RibbonMessageElement build() {
            RibbonMessageElement ribbonMessageElement = new RibbonMessageElement();
            populate(ribbonMessageElement);
            return ribbonMessageElement;
        }

        protected void populate(RibbonMessageElement ribbonMessageElement) {
            super.populate((TemplateElement) ribbonMessageElement);
            ribbonMessageElement.setTextWithLinks(this.textWithLinks);
            ribbonMessageElement.setText(this.text);
        }

        public Builder withText(String str) {
            this.text = str;
            return this;
        }

        public Builder withTextWithLinks(List<TextWithLinkElement> list) {
            this.textWithLinks = list;
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
        if (!(sOAObject instanceof RibbonMessageElement)) {
            return 1;
        }
        RibbonMessageElement ribbonMessageElement = (RibbonMessageElement) sOAObject;
        List<TextWithLinkElement> textWithLinks = getTextWithLinks();
        List<TextWithLinkElement> textWithLinks2 = ribbonMessageElement.getTextWithLinks();
        if (textWithLinks != textWithLinks2) {
            if (textWithLinks == null) {
                return -1;
            }
            if (textWithLinks2 == null) {
                return 1;
            }
            if (textWithLinks instanceof Comparable) {
                int iCompareTo = ((Comparable) textWithLinks).compareTo(textWithLinks2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!textWithLinks.equals(textWithLinks2)) {
                int iHashCode = textWithLinks.hashCode();
                int iHashCode2 = textWithLinks2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String text = getText();
        String text2 = ribbonMessageElement.getText();
        if (text != text2) {
            if (text == null) {
                return -1;
            }
            if (text2 == null) {
                return 1;
            }
            int iCompareTo2 = text.compareTo(text2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof RibbonMessageElement)) {
            return false;
        }
        RibbonMessageElement ribbonMessageElement = (RibbonMessageElement) obj;
        return super.equals(obj) && internalEqualityCheck(getTextWithLinks(), ribbonMessageElement.getTextWithLinks()) && internalEqualityCheck(getText(), ribbonMessageElement.getText());
    }

    public String getText() {
        return this.text;
    }

    public List<TextWithLinkElement> getTextWithLinks() {
        return this.textWithLinks;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTextWithLinks(), getText());
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setTextWithLinks(List<TextWithLinkElement> list) {
        this.textWithLinks = list;
    }
}

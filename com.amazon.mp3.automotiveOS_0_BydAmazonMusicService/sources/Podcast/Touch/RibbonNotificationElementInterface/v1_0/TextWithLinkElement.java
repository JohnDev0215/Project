package Podcast.Touch.RibbonNotificationElementInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TextWithLinkElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.RibbonNotificationElementInterface.v1_0.TextWithLinkElement");
    private String link;
    private String text;

    public static class Builder extends TemplateElement.Builder {
        protected String link;
        protected String text;

        public TextWithLinkElement build() {
            TextWithLinkElement textWithLinkElement = new TextWithLinkElement();
            populate(textWithLinkElement);
            return textWithLinkElement;
        }

        protected void populate(TextWithLinkElement textWithLinkElement) {
            super.populate((TemplateElement) textWithLinkElement);
            textWithLinkElement.setText(this.text);
            textWithLinkElement.setLink(this.link);
        }

        public Builder withLink(String str) {
            this.link = str;
            return this;
        }

        public Builder withText(String str) {
            this.text = str;
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
        if (!(sOAObject instanceof TextWithLinkElement)) {
            return 1;
        }
        TextWithLinkElement textWithLinkElement = (TextWithLinkElement) sOAObject;
        String text = getText();
        String text2 = textWithLinkElement.getText();
        if (text != text2) {
            if (text == null) {
                return -1;
            }
            if (text2 == null) {
                return 1;
            }
            int iCompareTo = text.compareTo(text2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String link = getLink();
        String link2 = textWithLinkElement.getLink();
        if (link != link2) {
            if (link == null) {
                return -1;
            }
            if (link2 == null) {
                return 1;
            }
            int iCompareTo2 = link.compareTo(link2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof TextWithLinkElement)) {
            return false;
        }
        TextWithLinkElement textWithLinkElement = (TextWithLinkElement) obj;
        return super.equals(obj) && internalEqualityCheck(getText(), textWithLinkElement.getText()) && internalEqualityCheck(getLink(), textWithLinkElement.getLink());
    }

    public String getLink() {
        return this.link;
    }

    public String getText() {
        return this.text;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getText(), getLink());
    }

    public void setLink(String str) {
        this.link = str;
    }

    public void setText(String str) {
        this.text = str;
    }
}

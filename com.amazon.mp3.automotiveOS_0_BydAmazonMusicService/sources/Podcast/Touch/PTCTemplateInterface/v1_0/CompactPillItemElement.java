package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class CompactPillItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.CompactPillItemElement");
    private String text;

    public static class Builder extends TemplateElement.Builder {
        protected String text;

        public CompactPillItemElement build() {
            CompactPillItemElement compactPillItemElement = new CompactPillItemElement();
            populate(compactPillItemElement);
            return compactPillItemElement;
        }

        protected void populate(CompactPillItemElement compactPillItemElement) {
            super.populate((TemplateElement) compactPillItemElement);
            compactPillItemElement.setText(this.text);
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
        if (!(sOAObject instanceof CompactPillItemElement)) {
            return 1;
        }
        String text = getText();
        String text2 = ((CompactPillItemElement) sOAObject).getText();
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
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof CompactPillItemElement) {
            return super.equals(obj) && internalEqualityCheck(getText(), ((CompactPillItemElement) obj).getText());
        }
        return false;
    }

    public String getText() {
        return this.text;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getText());
    }

    public void setText(String str) {
        this.text = str;
    }
}

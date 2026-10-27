package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class DidYouMeanCorrection extends BaseCorrection {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.DidYouMeanCorrection");
    private String text;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.tenzing.textsearch.v1_1.BaseCorrection, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated BaseCorrection baseCorrection) {
        if (baseCorrection == null) {
            return -1;
        }
        if (baseCorrection == this) {
            return 0;
        }
        if (!(baseCorrection instanceof DidYouMeanCorrection)) {
            return 1;
        }
        String text = getText();
        String text2 = ((DidYouMeanCorrection) baseCorrection).getText();
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
        return super.compareTo(baseCorrection);
    }

    @Override // com.amazon.tenzing.textsearch.v1_1.BaseCorrection
    public boolean equals(Object obj) {
        if (obj instanceof DidYouMeanCorrection) {
            return super.equals(obj) && internalEqualityCheck(getText(), ((DidYouMeanCorrection) obj).getText());
        }
        return false;
    }

    public String getText() {
        return this.text;
    }

    @Override // com.amazon.tenzing.textsearch.v1_1.BaseCorrection
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getText());
    }

    public void setText(String str) {
        this.text = str;
    }
}

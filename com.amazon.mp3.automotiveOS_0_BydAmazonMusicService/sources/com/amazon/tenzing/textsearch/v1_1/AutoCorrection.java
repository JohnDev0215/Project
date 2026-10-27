package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AutoCorrection extends BaseCorrection {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.AutoCorrection");
    private List<String> text;

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
        if (!(baseCorrection instanceof AutoCorrection)) {
            return 1;
        }
        List<String> text = getText();
        List<String> text2 = ((AutoCorrection) baseCorrection).getText();
        if (text != text2) {
            if (text == null) {
                return -1;
            }
            if (text2 == null) {
                return 1;
            }
            if (text instanceof Comparable) {
                int iCompareTo = ((Comparable) text).compareTo(text2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!text.equals(text2)) {
                int iHashCode = text.hashCode();
                int iHashCode2 = text2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(baseCorrection);
    }

    @Override // com.amazon.tenzing.textsearch.v1_1.BaseCorrection
    public boolean equals(Object obj) {
        if (obj instanceof AutoCorrection) {
            return super.equals(obj) && internalEqualityCheck(getText(), ((AutoCorrection) obj).getText());
        }
        return false;
    }

    public List<String> getText() {
        return this.text;
    }

    @Override // com.amazon.tenzing.textsearch.v1_1.BaseCorrection
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getText());
    }

    public void setText(List<String> list) {
        this.text = list;
    }
}

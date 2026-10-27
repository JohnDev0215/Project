package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class SubTitle implements Comparable<SubTitle> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.SubTitle");
    private String colorDecorator;
    private String text;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SubTitle subTitle) {
        if (subTitle == null) {
            return -1;
        }
        if (subTitle == this) {
            return 0;
        }
        String text = getText();
        String text2 = subTitle.getText();
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
        String colorDecorator = getColorDecorator();
        String colorDecorator2 = subTitle.getColorDecorator();
        if (colorDecorator != colorDecorator2) {
            if (colorDecorator == null) {
                return -1;
            }
            if (colorDecorator2 == null) {
                return 1;
            }
            int iCompareTo2 = colorDecorator.compareTo(colorDecorator2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SubTitle)) {
            return false;
        }
        SubTitle subTitle = (SubTitle) obj;
        return internalEqualityCheck(getText(), subTitle.getText()) && internalEqualityCheck(getColorDecorator(), subTitle.getColorDecorator());
    }

    public String getColorDecorator() {
        return this.colorDecorator;
    }

    public String getText() {
        return this.text;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getText(), getColorDecorator());
    }

    public void setColorDecorator(String str) {
        this.colorDecorator = str;
    }

    public void setText(String str) {
        this.text = str;
    }
}

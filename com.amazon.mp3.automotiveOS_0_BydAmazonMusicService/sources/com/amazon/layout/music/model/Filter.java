package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class Filter implements Comparable<Filter> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.Filter");
    private String colorDecorator;
    private String image;
    private Integer listIndex;
    private String text;
    private String value;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Filter filter) {
        if (filter == null) {
            return -1;
        }
        if (filter == this) {
            return 0;
        }
        String text = getText();
        String text2 = filter.getText();
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
        Integer listIndex = getListIndex();
        Integer listIndex2 = filter.getListIndex();
        if (listIndex != listIndex2) {
            if (listIndex == null) {
                return -1;
            }
            if (listIndex2 == null) {
                return 1;
            }
            int iCompareTo2 = listIndex.compareTo(listIndex2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String value = getValue();
        String value2 = filter.getValue();
        if (value != value2) {
            if (value == null) {
                return -1;
            }
            if (value2 == null) {
                return 1;
            }
            int iCompareTo3 = value.compareTo(value2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String colorDecorator = getColorDecorator();
        String colorDecorator2 = filter.getColorDecorator();
        if (colorDecorator != colorDecorator2) {
            if (colorDecorator == null) {
                return -1;
            }
            if (colorDecorator2 == null) {
                return 1;
            }
            int iCompareTo4 = colorDecorator.compareTo(colorDecorator2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String image = getImage();
        String image2 = filter.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo5 = image.compareTo(image2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Filter)) {
            return false;
        }
        Filter filter = (Filter) obj;
        return internalEqualityCheck(getText(), filter.getText()) && internalEqualityCheck(getListIndex(), filter.getListIndex()) && internalEqualityCheck(getValue(), filter.getValue()) && internalEqualityCheck(getColorDecorator(), filter.getColorDecorator()) && internalEqualityCheck(getImage(), filter.getImage());
    }

    public String getColorDecorator() {
        return this.colorDecorator;
    }

    public String getImage() {
        return this.image;
    }

    public Integer getListIndex() {
        return this.listIndex;
    }

    public String getText() {
        return this.text;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getText(), getListIndex(), getValue(), getColorDecorator(), getImage());
    }

    public void setColorDecorator(String str) {
        this.colorDecorator = str;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setListIndex(Integer num) {
        this.listIndex = num;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setValue(String str) {
        this.value = str;
    }
}

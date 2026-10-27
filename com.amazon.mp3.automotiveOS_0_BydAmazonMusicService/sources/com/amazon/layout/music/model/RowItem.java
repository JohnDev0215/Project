package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class RowItem extends Block {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.RowItem");
    private Hint hint;
    private String label;
    private String leftText;
    private String rowItemTarget;
    private String title1;
    private String title1Target;
    private String title2;
    private String title2Target;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.Block, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Block block) {
        if (block == null) {
            return -1;
        }
        if (block == this) {
            return 0;
        }
        if (!(block instanceof RowItem)) {
            return 1;
        }
        RowItem rowItem = (RowItem) block;
        String title1Target = getTitle1Target();
        String title1Target2 = rowItem.getTitle1Target();
        if (title1Target != title1Target2) {
            if (title1Target == null) {
                return -1;
            }
            if (title1Target2 == null) {
                return 1;
            }
            int iCompareTo = title1Target.compareTo(title1Target2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String rowItemTarget = getRowItemTarget();
        String rowItemTarget2 = rowItem.getRowItemTarget();
        if (rowItemTarget != rowItemTarget2) {
            if (rowItemTarget == null) {
                return -1;
            }
            if (rowItemTarget2 == null) {
                return 1;
            }
            int iCompareTo2 = rowItemTarget.compareTo(rowItemTarget2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String label = getLabel();
        String label2 = rowItem.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo3 = label.compareTo(label2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String title2Target = getTitle2Target();
        String title2Target2 = rowItem.getTitle2Target();
        if (title2Target != title2Target2) {
            if (title2Target == null) {
                return -1;
            }
            if (title2Target2 == null) {
                return 1;
            }
            int iCompareTo4 = title2Target.compareTo(title2Target2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Hint hint = getHint();
        Hint hint2 = rowItem.getHint();
        if (hint != hint2) {
            if (hint == null) {
                return -1;
            }
            if (hint2 == null) {
                return 1;
            }
            int iCompareTo5 = hint.compareTo(hint2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String title2 = getTitle2();
        String title22 = rowItem.getTitle2();
        if (title2 != title22) {
            if (title2 == null) {
                return -1;
            }
            if (title22 == null) {
                return 1;
            }
            int iCompareTo6 = title2.compareTo(title22);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String leftText = getLeftText();
        String leftText2 = rowItem.getLeftText();
        if (leftText != leftText2) {
            if (leftText == null) {
                return -1;
            }
            if (leftText2 == null) {
                return 1;
            }
            int iCompareTo7 = leftText.compareTo(leftText2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String title1 = getTitle1();
        String title12 = rowItem.getTitle1();
        if (title1 != title12) {
            if (title1 == null) {
                return -1;
            }
            if (title12 == null) {
                return 1;
            }
            int iCompareTo8 = title1.compareTo(title12);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof RowItem)) {
            return false;
        }
        RowItem rowItem = (RowItem) obj;
        return super.equals(obj) && internalEqualityCheck(getTitle1Target(), rowItem.getTitle1Target()) && internalEqualityCheck(getRowItemTarget(), rowItem.getRowItemTarget()) && internalEqualityCheck(getLabel(), rowItem.getLabel()) && internalEqualityCheck(getTitle2Target(), rowItem.getTitle2Target()) && internalEqualityCheck(getHint(), rowItem.getHint()) && internalEqualityCheck(getTitle2(), rowItem.getTitle2()) && internalEqualityCheck(getLeftText(), rowItem.getLeftText()) && internalEqualityCheck(getTitle1(), rowItem.getTitle1());
    }

    public Hint getHint() {
        return this.hint;
    }

    public String getLabel() {
        return this.label;
    }

    public String getLeftText() {
        return this.leftText;
    }

    public String getRowItemTarget() {
        return this.rowItemTarget;
    }

    public String getTitle1() {
        return this.title1;
    }

    public String getTitle1Target() {
        return this.title1Target;
    }

    public String getTitle2() {
        return this.title2;
    }

    public String getTitle2Target() {
        return this.title2Target;
    }

    @Override // com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTitle1Target(), getRowItemTarget(), getLabel(), getTitle2Target(), getHint(), getTitle2(), getLeftText(), getTitle1());
    }

    public void setHint(Hint hint) {
        this.hint = hint;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setLeftText(String str) {
        this.leftText = str;
    }

    public void setRowItemTarget(String str) {
        this.rowItemTarget = str;
    }

    public void setTitle1(String str) {
        this.title1 = str;
    }

    public void setTitle1Target(String str) {
        this.title1Target = str;
    }

    public void setTitle2(String str) {
        this.title2 = str;
    }

    public void setTitle2Target(String str) {
        this.title2Target = str;
    }
}

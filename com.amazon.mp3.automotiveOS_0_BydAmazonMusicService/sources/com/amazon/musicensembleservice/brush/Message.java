package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class Message extends Entity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Message");
    private String text;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof Message)) {
            return 1;
        }
        String text = getText();
        String text2 = ((Message) entity).getText();
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
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (obj instanceof Message) {
            return super.equals(obj) && internalEqualityCheck(getText(), ((Message) obj).getText());
        }
        return false;
    }

    public String getText() {
        return this.text;
    }

    @Override // com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getText());
    }

    public void setText(String str) {
        this.text = str;
    }
}

package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class LogMessage implements Comparable<LogMessage> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.LogMessage");
    private String id;
    private String text;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated LogMessage logMessage) {
        if (logMessage == null) {
            return -1;
        }
        if (logMessage == this) {
            return 0;
        }
        String id = getId();
        String id2 = logMessage.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo = id.compareTo(id2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String text = getText();
        String text2 = logMessage.getText();
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
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LogMessage)) {
            return false;
        }
        LogMessage logMessage = (LogMessage) obj;
        return internalEqualityCheck(getId(), logMessage.getId()) && internalEqualityCheck(getText(), logMessage.getText());
    }

    public String getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getId(), getText());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setText(String str) {
        this.text = str;
    }
}

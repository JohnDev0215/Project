package com.amazon.dp.logger;

/* JADX INFO: loaded from: classes2.dex */
public class DPFormattedMessage {
    private final String mFormattedString;
    private Throwable mThrowable;

    public DPFormattedMessage(String str, String str2, Object... objArr) {
        this.mFormattedString = toDPFormat(str, str2, objArr);
        if (objArr.length <= 0 || !(objArr[objArr.length - 1] instanceof Throwable)) {
            return;
        }
        setThrowable((Throwable) objArr[objArr.length - 1]);
    }

    public Throwable getThrowable() {
        return this.mThrowable;
    }

    public void setThrowable(Throwable th) {
        this.mThrowable = th;
    }

    public String toString() {
        return this.mFormattedString;
    }

    public static String toDPFormat(String str, String str2, Object... objArr) {
        int length = objArr.length;
        if (str == null) {
            str = "<null>";
        }
        if (str2 == null) {
            str2 = "<null>";
        }
        StringBuilder sb = new StringBuilder(str.length() + str2.length() + (length * 40));
        sb.append(str);
        sb.append(" - ");
        sb.append(str2);
        sb.append(";");
        if (length > 0 && (objArr[length - 1] instanceof Throwable)) {
            length--;
        }
        int i = 0;
        while (i < length) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(" ");
            sb.append(objArr[i]);
            int i2 = i + 1;
            if (i2 < length) {
                sb.append(": ");
                sb.append(objArr[i2]);
            }
            i = i2 + 1;
        }
        return sb.toString();
    }
}

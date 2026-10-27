package com.amazon.musicensembleservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class Error implements Comparable<Error> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.Error");
    private String id;
    private String message;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Error error) {
        if (error == null) {
            return -1;
        }
        if (error == this) {
            return 0;
        }
        String id = getId();
        String id2 = error.getId();
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
        String message = getMessage();
        String message2 = error.getMessage();
        if (message != message2) {
            if (message == null) {
                return -1;
            }
            if (message2 == null) {
                return 1;
            }
            int iCompareTo2 = message.compareTo(message2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Error)) {
            return false;
        }
        Error error = (Error) obj;
        return internalEqualityCheck(getId(), error.getId()) && internalEqualityCheck(getMessage(), error.getMessage());
    }

    public String getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getId(), getMessage());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}

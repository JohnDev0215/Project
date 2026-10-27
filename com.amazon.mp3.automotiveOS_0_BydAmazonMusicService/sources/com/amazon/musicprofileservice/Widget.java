package com.amazon.musicprofileservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class Widget implements Comparable<Widget> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicprofileservice.Widget");
    private String name;
    private String status;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Widget widget) {
        if (widget == null) {
            return -1;
        }
        if (widget == this) {
            return 0;
        }
        String name = getName();
        String name2 = widget.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo = name.compareTo(name2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String status = getStatus();
        String status2 = widget.getStatus();
        if (status != status2) {
            if (status == null) {
                return -1;
            }
            if (status2 == null) {
                return 1;
            }
            int iCompareTo2 = status.compareTo(status2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Widget)) {
            return false;
        }
        Widget widget = (Widget) obj;
        return internalEqualityCheck(getName(), widget.getName()) && internalEqualityCheck(getStatus(), widget.getStatus());
    }

    public String getName() {
        return this.name;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getName(), getStatus());
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}

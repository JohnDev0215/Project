package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class Header implements Comparable<Header> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Header");
    private Content content;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Header header) {
        Content content;
        Content content2;
        if (header == null) {
            return -1;
        }
        if (header != this && (content = getContent()) != (content2 = header.getContent())) {
            if (content == null) {
                return -1;
            }
            if (content2 == null) {
                return 1;
            }
            int iCompareTo = content.compareTo(content2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Header) {
            return internalEqualityCheck(getContent(), ((Header) obj).getContent());
        }
        return false;
    }

    public Content getContent() {
        return this.content;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getContent());
    }

    public void setContent(Content content) {
        this.content = content;
    }
}

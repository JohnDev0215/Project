package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class AlphabetBucket implements Comparable<AlphabetBucket> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.AlphabetBucket");
    private String bucketName;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AlphabetBucket alphabetBucket) {
        String bucketName;
        String bucketName2;
        if (alphabetBucket == null) {
            return -1;
        }
        if (alphabetBucket != this && (bucketName = getBucketName()) != (bucketName2 = alphabetBucket.getBucketName())) {
            if (bucketName == null) {
                return -1;
            }
            if (bucketName2 == null) {
                return 1;
            }
            int iCompareTo = bucketName.compareTo(bucketName2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AlphabetBucket) {
            return internalEqualityCheck(getBucketName(), ((AlphabetBucket) obj).getBucketName());
        }
        return false;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getBucketName());
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }
}

package com.ctc.wstx.util;

/* JADX INFO: loaded from: classes5.dex */
public class SymbolTable {
    protected Bucket[] mBuckets;
    protected boolean mDirty;
    protected int mIndexMask;
    protected boolean mInternStrings;
    protected int mSize;
    protected int mSizeThreshold;
    protected String[] mSymbols;
    protected int mThisVersion;

    public SymbolTable() {
        this(true);
    }

    public SymbolTable(boolean z) {
        this(z, 128);
    }

    public SymbolTable(boolean z, int i) {
        this(z, i, 0.75f);
    }

    public SymbolTable(boolean z, int i, float f) {
        this.mInternStrings = z;
        this.mThisVersion = 1;
        this.mDirty = true;
        if (i < 1) {
            throw new IllegalArgumentException("Can not use negative/zero initial size: " + i);
        }
        int i2 = 4;
        while (i2 < i) {
            i2 += i2;
        }
        this.mSymbols = new String[i2];
        this.mBuckets = new Bucket[i2 >> 1];
        this.mIndexMask = i2 - 1;
        this.mSize = 0;
        if (f < 0.01f) {
            throw new IllegalArgumentException("Fill factor can not be lower than 0.01.");
        }
        if (f > 10.0f) {
            throw new IllegalArgumentException("Fill factor can not be higher than 10.0.");
        }
        this.mSizeThreshold = (int) (((double) (i2 * f)) + 0.5d);
    }

    private SymbolTable(boolean z, String[] strArr, Bucket[] bucketArr, int i, int i2, int i3, int i4) {
        this.mInternStrings = z;
        this.mSymbols = strArr;
        this.mBuckets = bucketArr;
        this.mSize = i;
        this.mSizeThreshold = i2;
        this.mIndexMask = i3;
        this.mThisVersion = i4;
        this.mDirty = false;
    }

    public SymbolTable makeChild() {
        boolean z;
        String[] strArr;
        Bucket[] bucketArr;
        int i;
        int i2;
        int i3;
        int i4;
        synchronized (this) {
            z = this.mInternStrings;
            strArr = this.mSymbols;
            bucketArr = this.mBuckets;
            i = this.mSize;
            i2 = this.mSizeThreshold;
            i3 = this.mIndexMask;
            i4 = this.mThisVersion + 1;
        }
        return new SymbolTable(z, strArr, bucketArr, i, i2, i3, i4);
    }

    public void setInternStrings(boolean z) {
        this.mInternStrings = z;
    }

    public String findSymbol(String str) {
        String strFind;
        int length = str.length();
        if (length < 1) {
            return "";
        }
        int iCalcHash = calcHash(str) & this.mIndexMask;
        String str2 = this.mSymbols[iCalcHash];
        if (str2 != null) {
            if (str2.length() == length) {
                int i = 0;
                while (i < length && str2.charAt(i) == str.charAt(i)) {
                    i++;
                }
                if (i == length) {
                    return str2;
                }
            }
            Bucket bucket = this.mBuckets[iCalcHash >> 1];
            if (bucket != null && (strFind = bucket.find(str)) != null) {
                return strFind;
            }
        }
        if (this.mSize >= this.mSizeThreshold) {
            rehash();
            iCalcHash = this.mIndexMask & calcHash(str);
        } else if (!this.mDirty) {
            copyArrays();
            this.mDirty = true;
        }
        this.mSize++;
        if (this.mInternStrings) {
            str = str.intern();
        }
        String[] strArr = this.mSymbols;
        if (strArr[iCalcHash] == null) {
            strArr[iCalcHash] = str;
        } else {
            int i2 = iCalcHash >> 1;
            Bucket[] bucketArr = this.mBuckets;
            bucketArr[i2] = new Bucket(str, bucketArr[i2]);
        }
        return str;
    }

    public static int calcHash(String str) {
        int iCharAt = str.charAt(0);
        int length = str.length();
        for (int i = 1; i < length; i++) {
            iCharAt = (iCharAt * 31) + str.charAt(i);
        }
        return iCharAt;
    }

    private void copyArrays() {
        String[] strArr = this.mSymbols;
        int length = strArr.length;
        String[] strArr2 = new String[length];
        this.mSymbols = strArr2;
        System.arraycopy(strArr, 0, strArr2, 0, length);
        Bucket[] bucketArr = this.mBuckets;
        int length2 = bucketArr.length;
        Bucket[] bucketArr2 = new Bucket[length2];
        this.mBuckets = bucketArr2;
        System.arraycopy(bucketArr, 0, bucketArr2, 0, length2);
    }

    private void rehash() {
        String[] strArr = this.mSymbols;
        int length = strArr.length;
        int i = length + length;
        Bucket[] bucketArr = this.mBuckets;
        this.mSymbols = new String[i];
        this.mBuckets = new Bucket[i >> 1];
        this.mIndexMask = i - 1;
        int i2 = this.mSizeThreshold;
        this.mSizeThreshold = i2 + i2;
        int i3 = 0;
        for (String str : strArr) {
            if (str != null) {
                i3++;
                int iCalcHash = calcHash(str) & this.mIndexMask;
                String[] strArr2 = this.mSymbols;
                if (strArr2[iCalcHash] == null) {
                    strArr2[iCalcHash] = str;
                } else {
                    int i4 = iCalcHash >> 1;
                    Bucket[] bucketArr2 = this.mBuckets;
                    bucketArr2[i4] = new Bucket(str, bucketArr2[i4]);
                }
            }
        }
        int i5 = length >> 1;
        for (int i6 = 0; i6 < i5; i6++) {
            for (Bucket next = bucketArr[i6]; next != null; next = next.getNext()) {
                i3++;
                String symbol = next.getSymbol();
                int iCalcHash2 = calcHash(symbol) & this.mIndexMask;
                String[] strArr3 = this.mSymbols;
                if (strArr3[iCalcHash2] == null) {
                    strArr3[iCalcHash2] = symbol;
                } else {
                    int i7 = iCalcHash2 >> 1;
                    Bucket[] bucketArr3 = this.mBuckets;
                    bucketArr3[i7] = new Bucket(symbol, bucketArr3[i7]);
                }
            }
        }
        if (i3 == this.mSize) {
            return;
        }
        throw new IllegalStateException("Internal error on SymbolTable.rehash(): had " + this.mSize + " entries; now have " + i3 + ".");
    }

    static final class Bucket {
        private final Bucket mNext;
        private final String mSymbol;

        public Bucket(String str, Bucket bucket) {
            this.mSymbol = str;
            this.mNext = bucket;
        }

        public String getSymbol() {
            return this.mSymbol;
        }

        public Bucket getNext() {
            return this.mNext;
        }

        public String find(String str) {
            String symbol = this.mSymbol;
            Bucket next = this.mNext;
            while (!symbol.equals(str)) {
                if (next == null) {
                    return null;
                }
                symbol = next.getSymbol();
                next = next.getNext();
            }
            return symbol;
        }
    }
}

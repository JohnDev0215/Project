package org.apache.commons.compress.harmony.pack200;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CPSignature extends ConstantPoolEntry implements Comparable {
    private final List classes;
    private final boolean formStartsWithBracket;
    private final String signature;
    private final CPUTF8 signatureForm;

    public CPSignature(String str, CPUTF8 cputf8, List list) {
        this.signature = str;
        this.signatureForm = cputf8;
        this.classes = list;
        this.formStartsWithBracket = cputf8.toString().startsWith("(");
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        CPSignature cPSignature = (CPSignature) obj;
        if (this.signature.equals(cPSignature.signature)) {
            return 0;
        }
        boolean z = this.formStartsWithBracket;
        if (z && !cPSignature.formStartsWithBracket) {
            return 1;
        }
        if (cPSignature.formStartsWithBracket && !z) {
            return -1;
        }
        if (this.classes.size() - cPSignature.classes.size() != 0) {
            return this.classes.size() - cPSignature.classes.size();
        }
        if (this.classes.size() > 0) {
            for (int size = this.classes.size() - 1; size >= 0; size--) {
                int iCompareTo = ((CPClass) this.classes.get(size)).compareTo((CPClass) cPSignature.classes.get(size));
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            }
        }
        return this.signature.compareTo(cPSignature.signature);
    }

    public int getIndexInCpUtf8() {
        return this.signatureForm.getIndex();
    }

    public List getClasses() {
        return this.classes;
    }

    public String toString() {
        return this.signature;
    }

    public String getUnderlyingString() {
        return this.signature;
    }

    public CPUTF8 getSignatureForm() {
        return this.signatureForm;
    }
}

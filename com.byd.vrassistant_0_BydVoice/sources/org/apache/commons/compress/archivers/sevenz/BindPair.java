package org.apache.commons.compress.archivers.sevenz;

/* JADX INFO: loaded from: classes4.dex */
class BindPair {
    long inIndex;
    long outIndex;

    BindPair() {
    }

    public String toString() {
        return "BindPair binding input " + this.inIndex + " to output " + this.outIndex;
    }
}

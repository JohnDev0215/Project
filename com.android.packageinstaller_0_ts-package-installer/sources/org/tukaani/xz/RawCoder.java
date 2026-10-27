package org.tukaani.xz;

/* JADX INFO: loaded from: classes2.dex */
class RawCoder {
    RawCoder() {
    }

    static void validate(FilterCoder[] filters) throws UnsupportedOptionsException {
        for (int i = 0; i < filters.length - 1; i++) {
            if (!filters[i].nonLastOK()) {
                throw new UnsupportedOptionsException("Unsupported XZ filter chain");
            }
        }
        int i2 = filters.length;
        if (!filters[i2 - 1].lastOK()) {
            throw new UnsupportedOptionsException("Unsupported XZ filter chain");
        }
        int changesSizeCount = 0;
        for (FilterCoder filterCoder : filters) {
            if (filterCoder.changesSize()) {
                changesSizeCount++;
            }
        }
        if (changesSizeCount > 3) {
            throw new UnsupportedOptionsException("Unsupported XZ filter chain");
        }
    }
}

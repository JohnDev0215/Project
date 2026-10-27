package org.apache.commons.compress.parallel;

import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public interface ScatterGatherBackingStoreSupplier {
    ScatterGatherBackingStore get() throws IOException;
}

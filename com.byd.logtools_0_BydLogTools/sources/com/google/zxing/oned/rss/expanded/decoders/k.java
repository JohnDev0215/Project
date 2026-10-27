package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;

/* JADX INFO: compiled from: AnyAIDecoder.java */
/* JADX INFO: loaded from: classes.dex */
final class k extends j {
    k(com.google.zxing.common.a aVar) {
        super(aVar);
    }

    @Override // com.google.zxing.oned.rss.expanded.decoders.j
    public String d() throws NotFoundException, FormatException {
        return b().a(new StringBuilder(), 5);
    }
}

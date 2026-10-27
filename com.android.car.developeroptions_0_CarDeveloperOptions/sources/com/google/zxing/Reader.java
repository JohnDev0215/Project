package com.google.zxing;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface Reader {
    Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    void reset();
}

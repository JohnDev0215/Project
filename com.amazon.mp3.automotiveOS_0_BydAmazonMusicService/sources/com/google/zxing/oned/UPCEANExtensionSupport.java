package com.google.zxing.oned;

/* JADX INFO: loaded from: classes7.dex */
final class UPCEANExtensionSupport {
    private static final int[] EXTENSION_START_PATTERN = {1, 1, 2};
    private final UPCEANExtension2Support twoSupport = new UPCEANExtension2Support();
    private final UPCEANExtension5Support fiveSupport = new UPCEANExtension5Support();

    UPCEANExtensionSupport() {
    }
}

package org.chromium.base;

import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("base::android")
public class ImportantFileWriterAndroid {
    private static native boolean nativeWriteFileAtomically(String str, byte[] bArr);

    public static boolean writeFileAtomically(String str, byte[] bArr) {
        return nativeWriteFileAtomically(str, bArr);
    }
}

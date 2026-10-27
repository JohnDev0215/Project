package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.util.IonStreamUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes3.dex */
public final class _Private_IonReaderFactory {
    public static final IonReader makeReader(IonCatalog ionCatalog, byte[] bArr, int i, int i2, _Private_LocalSymbolTableFactory _private_localsymboltablefactory) {
        try {
            return makeReader(ionCatalog, makeUnifiedStream(bArr, i, i2), i, _private_localsymboltablefactory);
        } catch (IOException e) {
            throw new IonException(e);
        }
    }

    public static final IonReader makeIncrementalReader(IonReaderBuilder ionReaderBuilder, InputStream inputStream) {
        return new IonReaderBinaryIncremental(ionReaderBuilder, inputStream);
    }

    private static IonReader makeReader(IonCatalog ionCatalog, UnifiedInputStreamX unifiedInputStreamX, int i, _Private_LocalSymbolTableFactory _private_localsymboltablefactory) throws IOException {
        if (has_binary_cookie(unifiedInputStreamX)) {
            return new IonReaderBinaryUserX(ionCatalog, _private_localsymboltablefactory, unifiedInputStreamX, i);
        }
        return new IonReaderTextUserX(ionCatalog, _private_localsymboltablefactory, unifiedInputStreamX, i);
    }

    private static UnifiedInputStreamX makeUnifiedStream(byte[] bArr, int i, int i2) throws IOException {
        if (IonStreamUtils.isGzip(bArr, i, i2)) {
            return UnifiedInputStreamX.makeStream(new GZIPInputStream(new ByteArrayInputStream(bArr, i, i2)));
        }
        return UnifiedInputStreamX.makeStream(bArr, i, i2);
    }

    private static final boolean has_binary_cookie(UnifiedInputStreamX unifiedInputStreamX) throws IOException {
        int i;
        byte[] bArr = new byte[_Private_IonConstants.BINARY_VERSION_MARKER_SIZE];
        int i2 = 0;
        while (i2 < _Private_IonConstants.BINARY_VERSION_MARKER_SIZE && (i = unifiedInputStreamX.read()) != -1) {
            bArr[i2] = (byte) i;
            i2++;
        }
        int i3 = i2;
        while (i3 > 0) {
            i3--;
            unifiedInputStreamX.unread(bArr[i3] & 255);
        }
        return IonStreamUtils.isIonBinary(bArr, 0, i2);
    }
}

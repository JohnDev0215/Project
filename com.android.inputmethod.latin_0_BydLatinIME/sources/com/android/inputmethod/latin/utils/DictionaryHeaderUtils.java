package com.android.inputmethod.latin.utils;

import com.android.inputmethod.latin.AssetFileAddress;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class DictionaryHeaderUtils {
    public static int getContentVersion(AssetFileAddress fileAddress) {
        return Integer.parseInt(DictionaryInfoUtils.getDictionaryFileHeaderOrNull(new File(fileAddress.mFilename), fileAddress.mOffset, fileAddress.mLength).mVersionString);
    }
}

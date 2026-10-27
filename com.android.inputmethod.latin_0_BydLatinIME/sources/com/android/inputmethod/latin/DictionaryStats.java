package com.android.inputmethod.latin;

import java.io.File;
import java.math.BigDecimal;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DictionaryStats {
    public static final int NOT_AN_ENTRY_COUNT = -1;
    public final int mContentVersion;
    public final String mDictFileName;
    public final long mDictFileSize;
    public final String mDictType;
    public final Locale mLocale;
    public final int mWordCount;

    public DictionaryStats(@Nonnull final Locale locale, @Nonnull final String dictType, @Nullable final String dictFileName, @Nullable final File dictFile, final int contentVersion) {
        this.mLocale = locale;
        this.mDictType = dictType;
        this.mDictFileSize = (dictFile == null || !dictFile.exists()) ? 0L : dictFile.length();
        this.mDictFileName = dictFileName;
        this.mContentVersion = contentVersion;
        this.mWordCount = -1;
    }

    public DictionaryStats(@Nonnull final Locale locale, @Nonnull final String dictType, final int wordCount) {
        this.mLocale = locale;
        this.mDictType = dictType;
        this.mDictFileSize = wordCount;
        this.mDictFileName = null;
        this.mContentVersion = 0;
        this.mWordCount = wordCount;
    }

    public String getFileSizeString() {
        BigDecimal bigDecimal = new BigDecimal(this.mDictFileSize);
        BigDecimal bigDecimalDivide = bigDecimal.divide(new BigDecimal(1024), 2, 4);
        if (bigDecimalDivide.longValue() == 0) {
            return bigDecimal.toString() + " bytes";
        }
        BigDecimal bigDecimalDivide2 = bigDecimalDivide.divide(new BigDecimal(1024), 2, 4);
        if (bigDecimalDivide2.longValue() == 0) {
            return bigDecimalDivide.toString() + " kb";
        }
        return bigDecimalDivide2.toString() + " Mb";
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.mDictType);
        if (this.mDictType.equals("main")) {
            sb.append(" (");
            sb.append(this.mContentVersion);
            sb.append(")");
        }
        sb.append(": ");
        int i = this.mWordCount;
        if (i > -1) {
            sb.append(i);
            sb.append(" words");
        } else {
            sb.append(this.mDictFileName);
            sb.append(" / ");
            sb.append(getFileSizeString());
        }
        return sb.toString();
    }

    public static String toString(final Iterable<DictionaryStats> stats) {
        StringBuilder sb = new StringBuilder("LM Stats");
        for (DictionaryStats dictionaryStats : stats) {
            sb.append("\n    ");
            sb.append(dictionaryStats.toString());
        }
        return sb.toString();
    }
}

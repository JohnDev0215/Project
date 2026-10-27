package com.android.launcher3.compat;

import android.content.Context;
import android.icu.text.AlphabeticIndex;
import android.os.LocaleList;
import android.util.Log;
import com.android.launcher3.Utilities;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class AlphabeticIndexCompat {
    private static final String MID_DOT = "∙";
    private static final String TAG = "AlphabeticIndexCompat";
    private final BaseIndex mBaseIndex;
    private final String mDefaultMiscLabel;

    public AlphabeticIndexCompat(Context context) {
        BaseIndex alphabeticIndexVN;
        try {
            alphabeticIndexVN = new AlphabeticIndexVN(context);
        } catch (Exception e) {
            Log.d(TAG, "Unable to load the system index", e);
            alphabeticIndexVN = null;
        }
        this.mBaseIndex = alphabeticIndexVN == null ? new BaseIndex() : alphabeticIndexVN;
        if (context.getResources().getConfiguration().locale.getLanguage().equals(Locale.JAPANESE.getLanguage())) {
            this.mDefaultMiscLabel = "他";
        } else {
            this.mDefaultMiscLabel = MID_DOT;
        }
    }

    public String computeSectionName(CharSequence cs) {
        String strTrim = Utilities.trim(cs);
        BaseIndex baseIndex = this.mBaseIndex;
        String bucketLabel = baseIndex.getBucketLabel(baseIndex.getBucketIndex(strTrim));
        if (!Utilities.trim(bucketLabel).isEmpty() || strTrim.length() <= 0) {
            return bucketLabel;
        }
        int iCodePointAt = strTrim.codePointAt(0);
        return Character.isDigit(iCodePointAt) ? "#" : Character.isLetter(iCodePointAt) ? this.mDefaultMiscLabel : MID_DOT;
    }

    private static class BaseIndex {
        private static final String BUCKETS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-";
        private static final int UNKNOWN_BUCKET_INDEX = 36;

        private BaseIndex() {
        }

        protected int getBucketIndex(String s) {
            if (s.isEmpty()) {
                return UNKNOWN_BUCKET_INDEX;
            }
            int iIndexOf = BUCKETS.indexOf(s.substring(0, 1).toUpperCase());
            return iIndexOf != -1 ? iIndexOf : UNKNOWN_BUCKET_INDEX;
        }

        protected String getBucketLabel(int index) {
            return BUCKETS.substring(index, index + 1);
        }
    }

    private static class AlphabeticIndexVN extends BaseIndex {
        private final AlphabeticIndex.ImmutableIndex mAlphabeticIndex;

        public AlphabeticIndexVN(Context context) {
            super();
            LocaleList locales = context.getResources().getConfiguration().getLocales();
            int size = locales.size();
            AlphabeticIndex alphabeticIndex = new AlphabeticIndex(size == 0 ? Locale.ENGLISH : locales.get(0));
            for (int i = 1; i < size; i++) {
                alphabeticIndex.addLabels(locales.get(i));
            }
            alphabeticIndex.addLabels(Locale.ENGLISH);
            this.mAlphabeticIndex = alphabeticIndex.buildImmutableIndex();
        }

        @Override // com.android.launcher3.compat.AlphabeticIndexCompat.BaseIndex
        protected int getBucketIndex(String s) {
            return this.mAlphabeticIndex.getBucketIndex(s);
        }

        @Override // com.android.launcher3.compat.AlphabeticIndexCompat.BaseIndex
        protected String getBucketLabel(int index) {
            return this.mAlphabeticIndex.getBucket(index).getLabel();
        }
    }
}

package com.android.inputmethod.latin.utils;

import android.content.Context;
import com.android.inputmethod.latin.R;

/* JADX INFO: loaded from: classes2.dex */
public class MetadataFileUriGetter {
    public static String getMetadataAdditionalId(final Context context) {
        return "";
    }

    private MetadataFileUriGetter() {
    }

    public static String getMetadataUri(final Context context) {
        return context.getString(R.string.dictionary_pack_metadata_uri);
    }
}

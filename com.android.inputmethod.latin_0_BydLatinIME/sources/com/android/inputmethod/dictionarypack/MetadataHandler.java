package com.android.inputmethod.dictionarypack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MetadataHandler {
    public static final String METADATA_FILENAME = "metadata.json";
    public static final String TAG = "MetadataHandler";

    private static List<WordListMetadata> makeMetadataObject(final Cursor results) {
        ArrayList arrayList = new ArrayList();
        if (results != null && results.moveToFirst()) {
            int columnIndex = results.getColumnIndex("locale");
            int columnIndex2 = results.getColumnIndex(MetadataDbHelper.TYPE_COLUMN);
            int columnIndex3 = results.getColumnIndex("description");
            int columnIndex4 = results.getColumnIndex(MetadataDbHelper.WORDLISTID_COLUMN);
            int columnIndex5 = results.getColumnIndex("date");
            int columnIndex6 = results.getColumnIndex(MetadataDbHelper.FILESIZE_COLUMN);
            int columnIndex7 = results.getColumnIndex(MetadataDbHelper.RAW_CHECKSUM_COLUMN);
            int columnIndex8 = results.getColumnIndex(MetadataDbHelper.CHECKSUM_COLUMN);
            int columnIndex9 = results.getColumnIndex(MetadataDbHelper.RETRY_COUNT_COLUMN);
            int columnIndex10 = results.getColumnIndex(MetadataDbHelper.LOCAL_FILENAME_COLUMN);
            int columnIndex11 = results.getColumnIndex(MetadataDbHelper.REMOTE_FILENAME_COLUMN);
            int columnIndex12 = results.getColumnIndex("version");
            int columnIndex13 = results.getColumnIndex(MetadataDbHelper.FORMATVERSION_COLUMN);
            do {
                arrayList.add(new WordListMetadata(results.getString(columnIndex4), results.getInt(columnIndex2), results.getString(columnIndex3), results.getLong(columnIndex5), results.getLong(columnIndex6), results.getString(columnIndex7), results.getString(columnIndex8), results.getInt(columnIndex9), results.getString(columnIndex10), results.getString(columnIndex11), results.getInt(columnIndex12), results.getInt(columnIndex13), 0, results.getString(columnIndex)));
            } while (results.moveToNext());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static List<WordListMetadata> getCurrentMetadata(final Context context, final String clientId) {
        Cursor cursorQueryCurrentMetadata = MetadataDbHelper.queryCurrentMetadata(context, clientId);
        try {
            return makeMetadataObject(cursorQueryCurrentMetadata);
        } finally {
            if (cursorQueryCurrentMetadata != null) {
                cursorQueryCurrentMetadata.close();
            }
        }
    }

    public static WordListMetadata getCurrentMetadataForWordList(final Context context, final String clientId, final String wordListId, final int version) {
        ContentValues contentValuesByWordListId = MetadataDbHelper.getContentValuesByWordListId(MetadataDbHelper.getDb(context, clientId), wordListId, version);
        if (contentValuesByWordListId == null) {
            Log.e(TAG, String.format("Unable to find the current metadata for wordlist (clientId=%s, wordListId=%s, version=%d) on the database", clientId, wordListId, Integer.valueOf(version)));
            return null;
        }
        return WordListMetadata.createFromContentValues(contentValuesByWordListId);
    }

    public static List<WordListMetadata> readMetadata(final InputStreamReader input) throws IOException, BadFormatException {
        return MetadataParser.parseMetadata(input);
    }

    public static WordListMetadata findWordListById(final List<WordListMetadata> metadata, final String id) {
        WordListMetadata wordListMetadata = null;
        int i = Integer.MIN_VALUE;
        for (WordListMetadata wordListMetadata2 : metadata) {
            if (id.equals(wordListMetadata2.mId) && wordListMetadata2.mFormatVersion <= 86736212 && wordListMetadata2.mFormatVersion > i) {
                i = wordListMetadata2.mFormatVersion;
                wordListMetadata = wordListMetadata2;
            }
        }
        return wordListMetadata;
    }
}

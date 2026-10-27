package com.android.inputmethod.dictionarypack;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.res.AssetFileDescriptor;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import com.android.inputmethod.dictionarypack.ActionBatch;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.utils.DebugLogUtils;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Collections;

/* JADX INFO: loaded from: classes2.dex */
public final class DictionaryProvider extends ContentProvider {
    public static final Uri CONTENT_URI = Uri.parse("content://com.android.inputmethod.dictionarypack.aosp");
    public static final boolean DEBUG = false;
    private static final int DICTIONARY_V1_DICT_INFO = 2;
    private static final int DICTIONARY_V1_WHOLE_LIST = 1;
    private static final int DICTIONARY_V2_DATAFILE = 6;
    private static final int DICTIONARY_V2_DICT_INFO = 5;
    private static final int DICTIONARY_V2_METADATA = 3;
    private static final int DICTIONARY_V2_WHOLE_LIST = 4;
    public static final String DICT_DATAFILE_MIME_TYPE = "vnd.android.cursor.item/vnd.google.dictionary";
    public static final String DICT_LIST_MIME_TYPE = "vnd.android.cursor.item/vnd.google.dictionarylist";
    public static final String ID_CATEGORY_SEPARATOR = ":";
    private static final int NO_MATCH = 0;
    private static final String QUERY_PARAMETER_DELETE_RESULT = "result";
    private static final String QUERY_PARAMETER_FAILURE = "failure";
    private static final String QUERY_PARAMETER_MAY_PROMPT_USER = "mayPrompt";
    public static final String QUERY_PARAMETER_PROTOCOL_VERSION = "protocol";
    private static final String QUERY_PARAMETER_TRUE = "true";
    private static final String TAG = "DictionaryProvider";
    private static final UriMatcher sUriMatcherV1;
    private static final UriMatcher sUriMatcherV2;

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(0);
        sUriMatcherV1 = uriMatcher;
        UriMatcher uriMatcher2 = new UriMatcher(0);
        sUriMatcherV2 = uriMatcher2;
        uriMatcher.addURI(DictionaryPackConstants.AUTHORITY, "list", 1);
        uriMatcher.addURI(DictionaryPackConstants.AUTHORITY, "*", 2);
        uriMatcher2.addURI(DictionaryPackConstants.AUTHORITY, "*/metadata", 3);
        uriMatcher2.addURI(DictionaryPackConstants.AUTHORITY, "*/list", 4);
        uriMatcher2.addURI(DictionaryPackConstants.AUTHORITY, "*/dict/*", 5);
        uriMatcher2.addURI(DictionaryPackConstants.AUTHORITY, "*/datafile/*", 6);
    }

    private static final class WordListInfo {
        public final String mId;
        public final String mLocale;
        public final int mMatchLevel;
        public final String mRawChecksum;

        public WordListInfo(final String id, final String locale, final String rawChecksum, final int matchLevel) {
            this.mId = id;
            this.mLocale = locale;
            this.mRawChecksum = rawChecksum;
            this.mMatchLevel = matchLevel;
        }
    }

    private static final class ResourcePathCursor extends AbstractCursor {
        private static final String[] columnNames = {MetadataDbHelper.WORDLISTID_COLUMN, "locale", MetadataDbHelper.RAW_CHECKSUM_COLUMN};
        final WordListInfo[] mWordLists;

        @Override // android.database.AbstractCursor, android.database.Cursor
        public double getDouble(int column) {
            return 0.0d;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public float getFloat(int column) {
            return 0.0f;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public int getInt(int column) {
            return 0;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public long getLong(int column) {
            return 0L;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public short getShort(int column) {
            return (short) 0;
        }

        public ResourcePathCursor(final Collection<WordListInfo> wordLists) {
            this.mWordLists = (WordListInfo[]) wordLists.toArray(new WordListInfo[0]);
            this.mPos = 0;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public String[] getColumnNames() {
            return columnNames;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public int getCount() {
            return this.mWordLists.length;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public String getString(final int column) {
            if (column == 0) {
                return this.mWordLists[this.mPos].mId;
            }
            if (column == 1) {
                return this.mWordLists[this.mPos].mLocale;
            }
            if (column != 2) {
                return null;
            }
            return this.mWordLists[this.mPos].mRawChecksum;
        }

        @Override // android.database.AbstractCursor, android.database.Cursor
        public boolean isNull(final int column) {
            return this.mPos >= this.mWordLists.length || column != 0;
        }
    }

    private static int matchUri(final Uri uri) {
        char c = "2".equals(uri.getQueryParameter(QUERY_PARAMETER_PROTOCOL_VERSION)) ? (char) 2 : (char) 1;
        if (c == 1) {
            return sUriMatcherV1.match(uri);
        }
        if (c != 2) {
            return 0;
        }
        return sUriMatcherV2.match(uri);
    }

    private static String getClientId(final Uri uri) {
        if (("2".equals(uri.getQueryParameter(QUERY_PARAMETER_PROTOCOL_VERSION)) ? (char) 2 : (char) 1) != 2) {
            return null;
        }
        return uri.getPathSegments().get(0);
    }

    @Override // android.content.ContentProvider
    public String getType(final Uri uri) {
        PrivateLog.log("Asked for type of : " + uri);
        int iMatchUri = matchUri(uri);
        if (iMatchUri == 1 || iMatchUri == 2 || iMatchUri == 4 || iMatchUri == 5) {
            return DICT_LIST_MIME_TYPE;
        }
        if (iMatchUri != 6) {
            return null;
        }
        return DICT_DATAFILE_MIME_TYPE;
    }

    @Override // android.content.ContentProvider
    public Cursor query(final Uri uri, final String[] projection, final String selection, final String[] selectionArgs, final String sortOrder) {
        DebugLogUtils.l("Uri =", uri);
        PrivateLog.log("Query : " + uri);
        String clientId = getClientId(uri);
        int iMatchUri = matchUri(uri);
        if (iMatchUri != 1) {
            if (iMatchUri != 2) {
                if (iMatchUri != 4) {
                    if (iMatchUri != 5 || !MetadataDbHelper.isClientKnown(getContext(), clientId)) {
                        return null;
                    }
                }
            }
            Collection<WordListInfo> dictionaryWordListsForLocale = getDictionaryWordListsForLocale(clientId, uri.getLastPathSegment());
            DictionaryService.updateNowIfNotUpdatedInAVeryLongTime(getContext());
            if (dictionaryWordListsForLocale != null && dictionaryWordListsForLocale.size() > 0) {
                PrivateLog.log("Returned " + dictionaryWordListsForLocale.size() + " files");
                return new ResourcePathCursor(dictionaryWordListsForLocale);
            }
            PrivateLog.log("No dictionary files for this URL");
            return new ResourcePathCursor(Collections.emptyList());
        }
        Cursor cursorQueryDictionaries = MetadataDbHelper.queryDictionaries(getContext(), clientId);
        DebugLogUtils.l("List of dictionaries with count", Integer.valueOf(cursorQueryDictionaries.getCount()));
        PrivateLog.log("Returned a list of " + cursorQueryDictionaries.getCount() + " items");
        return cursorQueryDictionaries;
    }

    private ContentValues getWordlistMetadataForWordlistId(final String clientId, final String wordlistId) {
        Context context = getContext();
        if (TextUtils.isEmpty(wordlistId)) {
            return null;
        }
        return MetadataDbHelper.getInstalledOrDeletingWordListContentValuesByWordListId(MetadataDbHelper.getDb(context, clientId), wordlistId);
    }

    @Override // android.content.ContentProvider
    public AssetFileDescriptor openAssetFile(final Uri uri, final String mode) {
        if (mode != null && "r".equals(mode)) {
            int iMatchUri = matchUri(uri);
            if (2 != iMatchUri && 6 != iMatchUri) {
                Log.w(TAG, "Unsupported URI for openAssetFile : " + uri);
                return null;
            }
            ContentValues wordlistMetadataForWordlistId = getWordlistMetadataForWordlistId(getClientId(uri), uri.getLastPathSegment());
            if (wordlistMetadataForWordlistId == null) {
                return null;
            }
            try {
                if (5 == wordlistMetadataForWordlistId.getAsInteger("status").intValue()) {
                    return getContext().getResources().openRawResourceFd(R.raw.empty);
                }
                ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(getContext().getFileStreamPath(wordlistMetadataForWordlistId.getAsString(MetadataDbHelper.LOCAL_FILENAME_COLUMN)), SuggestedWords.SuggestedWordInfo.KIND_FLAG_APPROPRIATE_FOR_AUTO_CORRECTION);
                return new AssetFileDescriptor(parcelFileDescriptorOpen, 0L, parcelFileDescriptorOpen.getStatSize());
            } catch (FileNotFoundException unused) {
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0095 A[Catch: all -> 0x00c1, TryCatch #0 {all -> 0x00c1, blocks: (B:6:0x0011, B:8:0x003a, B:32:0x00a9, B:12:0x004b, B:14:0x0055, B:16:0x005b, B:21:0x007f, B:27:0x0095, B:29:0x009d, B:31:0x00a1, B:26:0x0091, B:36:0x00b5), top: B:42:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b0 A[LOOP:0: B:8:0x003a->B:35:0x00b0, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00af A[EDGE_INSN: B:44:0x00af->B:34:0x00af BREAK  A[LOOP:0: B:8:0x003a->B:35:0x00b0], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.Collection<com.android.inputmethod.dictionarypack.DictionaryProvider.WordListInfo> getDictionaryWordListsForLocale(final java.lang.String r19, final java.lang.String r20) {
        /*
            r18 = this;
            r0 = r19
            android.content.Context r1 = r18.getContext()
            android.database.Cursor r2 = com.android.inputmethod.dictionarypack.MetadataDbHelper.queryInstalledOrDeletingOrAvailableDictionaryMetadata(r1, r0)
            if (r2 != 0) goto L11
            java.util.List r0 = java.util.Collections.emptyList()
            return r0
        L11:
            java.util.HashMap r3 = new java.util.HashMap     // Catch: java.lang.Throwable -> Lc1
            r3.<init>()     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r4 = "id"
            int r4 = r2.getColumnIndex(r4)     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r5 = "locale"
            int r5 = r2.getColumnIndex(r5)     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r6 = "filename"
            int r6 = r2.getColumnIndex(r6)     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r7 = "rawChecksum"
            int r7 = r2.getColumnIndex(r7)     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r8 = "status"
            int r8 = r2.getColumnIndex(r8)     // Catch: java.lang.Throwable -> Lc1
            boolean r9 = r2.moveToFirst()     // Catch: java.lang.Throwable -> Lc1
            if (r9 == 0) goto Lb5
        L3a:
            java.lang.String r9 = r2.getString(r4)     // Catch: java.lang.Throwable -> Lc1
            boolean r10 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Throwable -> Lc1
            if (r10 == 0) goto L4b
            r15 = r20
            r16 = r4
        L48:
            r17 = r5
            goto La9
        L4b:
            java.lang.String r10 = ":"
            java.lang.String[] r10 = android.text.TextUtils.split(r9, r10)     // Catch: java.lang.Throwable -> Lc1
            r11 = 2
            int r12 = r10.length     // Catch: java.lang.Throwable -> Lc1
            if (r11 != r12) goto L59
            r11 = 0
            r10 = r10[r11]     // Catch: java.lang.Throwable -> Lc1
            goto L5b
        L59:
            java.lang.String r10 = "main"
        L5b:
            java.lang.String r11 = r2.getString(r5)     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r12 = r2.getString(r6)     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r13 = r2.getString(r7)     // Catch: java.lang.Throwable -> Lc1
            int r14 = r2.getInt(r8)     // Catch: java.lang.Throwable -> Lc1
            r15 = r20
            r16 = r4
            int r4 = com.android.inputmethod.latin.common.LocaleUtils.getMatchLevel(r11, r15)     // Catch: java.lang.Throwable -> Lc1
            boolean r17 = com.android.inputmethod.latin.common.LocaleUtils.isMatch(r4)     // Catch: java.lang.Throwable -> Lc1
            if (r17 != 0) goto L7a
            goto L48
        L7a:
            r17 = r5
            r5 = 3
            if (r5 != r14) goto L8e
            android.content.Context r5 = r18.getContext()     // Catch: java.lang.Throwable -> Lc1
            java.io.File r5 = r5.getFileStreamPath(r12)     // Catch: java.lang.Throwable -> Lc1
            boolean r5 = r5.isFile()     // Catch: java.lang.Throwable -> Lc1
            if (r5 != 0) goto L95
            goto La9
        L8e:
            r5 = 1
            if (r5 != r14) goto L95
            com.android.inputmethod.dictionarypack.UpdateHandler.installIfNeverRequested(r1, r0, r9)     // Catch: java.lang.Throwable -> Lc1
            goto La9
        L95:
            java.lang.Object r5 = r3.get(r10)     // Catch: java.lang.Throwable -> Lc1
            com.android.inputmethod.dictionarypack.DictionaryProvider$WordListInfo r5 = (com.android.inputmethod.dictionarypack.DictionaryProvider.WordListInfo) r5     // Catch: java.lang.Throwable -> Lc1
            if (r5 == 0) goto La1
            int r5 = r5.mMatchLevel     // Catch: java.lang.Throwable -> Lc1
            if (r5 >= r4) goto La9
        La1:
            com.android.inputmethod.dictionarypack.DictionaryProvider$WordListInfo r5 = new com.android.inputmethod.dictionarypack.DictionaryProvider$WordListInfo     // Catch: java.lang.Throwable -> Lc1
            r5.<init>(r9, r11, r13, r4)     // Catch: java.lang.Throwable -> Lc1
            r3.put(r10, r5)     // Catch: java.lang.Throwable -> Lc1
        La9:
            boolean r4 = r2.moveToNext()     // Catch: java.lang.Throwable -> Lc1
            if (r4 != 0) goto Lb0
            goto Lb5
        Lb0:
            r4 = r16
            r5 = r17
            goto L3a
        Lb5:
            java.util.Collection r0 = r3.values()     // Catch: java.lang.Throwable -> Lc1
            java.util.Collection r0 = java.util.Collections.unmodifiableCollection(r0)     // Catch: java.lang.Throwable -> Lc1
            r2.close()
            return r0
        Lc1:
            r0 = move-exception
            r2.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.dictionarypack.DictionaryProvider.getDictionaryWordListsForLocale(java.lang.String, java.lang.String):java.util.Collection");
    }

    @Override // android.content.ContentProvider
    public int delete(final Uri uri, final String selection, final String[] selectionArgs) throws UnsupportedOperationException {
        int iMatchUri = matchUri(uri);
        if (2 == iMatchUri || 6 == iMatchUri) {
            return deleteDataFile(uri);
        }
        return (3 == iMatchUri && MetadataDbHelper.deleteClient(getContext(), getClientId(uri))) ? 1 : 0;
    }

    private int deleteDataFile(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        String clientId = getClientId(uri);
        ContentValues wordlistMetadataForWordlistId = getWordlistMetadataForWordlistId(clientId, lastPathSegment);
        if (wordlistMetadataForWordlistId == null) {
            return 0;
        }
        int iIntValue = wordlistMetadataForWordlistId.getAsInteger("status").intValue();
        int iIntValue2 = wordlistMetadataForWordlistId.getAsInteger("version").intValue();
        if (5 == iIntValue) {
            UpdateHandler.markAsDeleted(getContext(), clientId, lastPathSegment, iIntValue2, iIntValue);
            return 1;
        }
        if (3 == iIntValue) {
            if (QUERY_PARAMETER_FAILURE.equals(uri.getQueryParameter(QUERY_PARAMETER_DELETE_RESULT))) {
                UpdateHandler.markAsBrokenOrRetrying(getContext(), clientId, lastPathSegment, iIntValue2);
            }
            return getContext().getFileStreamPath(wordlistMetadataForWordlistId.getAsString(MetadataDbHelper.LOCAL_FILENAME_COLUMN)).delete() ? 1 : 0;
        }
        Log.e(TAG, "Attempt to delete a file whose status is " + iIntValue);
        return 0;
    }

    @Override // android.content.ContentProvider
    public Uri insert(final Uri uri, final ContentValues values) throws UnsupportedOperationException {
        if (uri == null || values == null) {
            return null;
        }
        PrivateLog.log("Insert, uri = " + uri.toString());
        String clientId = getClientId(uri);
        int iMatchUri = matchUri(uri);
        if (iMatchUri == 1 || iMatchUri == 2) {
            PrivateLog.log("Attempt to insert : " + uri);
            throw new UnsupportedOperationException("Insertion in the dictionary is not supported in this version");
        }
        if (iMatchUri == 3) {
            MetadataDbHelper.updateClientInfo(getContext(), clientId, values);
        } else if (iMatchUri == 5) {
            try {
                new ActionBatch.MarkPreInstalledAction(clientId, WordListMetadata.createFromContentValues(MetadataDbHelper.completeWithDefaultValues(values))).execute(getContext());
            } catch (BadFormatException e) {
                Log.w(TAG, "Not enough information to insert this dictionary " + values, e);
            }
            UpdateHandler.publishUpdateMetadataCompleted(getContext(), true);
        }
        return uri;
    }

    @Override // android.content.ContentProvider
    public int update(final Uri uri, final ContentValues values, final String selection, final String[] selectionArgs) throws UnsupportedOperationException {
        PrivateLog.log("Attempt to update : " + uri);
        throw new UnsupportedOperationException("Updating dictionary words is not supported");
    }
}

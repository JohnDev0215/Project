package com.android.inputmethod.latin;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.android.inputmethod.dictionarypack.DictionaryPackConstants;
import com.android.inputmethod.dictionarypack.MetadataDbHelper;
import com.android.inputmethod.dictionarypack.UpdateHandler;
import com.android.inputmethod.latin.utils.DictionaryInfoUtils;
import com.android.inputmethod.latin.utils.MetadataFileUriGetter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class BinaryDictionaryFileDumper {
    private static final boolean DEBUG = false;
    private static final int FILE_READ_BUFFER_SIZE = 8192;
    private static final String INSERT_METADATA_CLIENT_ID_COLUMN = "clientid";
    private static final String INSERT_METADATA_METADATA_ADDITIONAL_ID_COLUMN = "additionalid";
    private static final String INSERT_METADATA_METADATA_URI_COLUMN = "uri";
    private static final String QUERY_PARAMETER_DELETE_RESULT = "result";
    private static final String QUERY_PARAMETER_FAILURE = "failure";
    private static final String QUERY_PARAMETER_MAY_PROMPT_USER = "mayPrompt";
    private static final String QUERY_PARAMETER_PROTOCOL = "protocol";
    private static final String QUERY_PARAMETER_PROTOCOL_VALUE = "2";
    private static final String QUERY_PARAMETER_SUCCESS = "success";
    private static final String QUERY_PARAMETER_TRUE = "true";
    private static final String QUERY_PATH_DATAFILE = "datafile";
    private static final String QUERY_PATH_DICT_INFO = "dict";
    private static final String QUERY_PATH_METADATA = "metadata";
    private static final boolean SHOULD_VERIFY_CHECKSUM = true;
    private static final boolean SHOULD_VERIFY_MAGIC_NUMBER = true;
    private static final String TAG = "BinaryDictionaryFileDumper";
    private static final byte[] MAGIC_NUMBER_VERSION_1 = {120, -79, 0, 0};
    private static final byte[] MAGIC_NUMBER_VERSION_2 = {-101, -63, 58, -2};
    private static final String[] DICTIONARY_PROJECTION = {MetadataDbHelper.WORDLISTID_COLUMN};

    private BinaryDictionaryFileDumper() {
    }

    public static Uri.Builder getProviderUriBuilder(final String path) {
        return new Uri.Builder().scheme("content").authority(DictionaryPackConstants.AUTHORITY).appendPath(path);
    }

    private static Uri.Builder getContentUriBuilderForType(final String clientId, final ContentProviderClient contentProviderClient, final String queryPathType, final String extraPath) throws RemoteException {
        Uri.Builder providerUriBuilder = getProviderUriBuilder(clientId);
        providerUriBuilder.appendPath(queryPathType);
        providerUriBuilder.appendPath(extraPath);
        providerUriBuilder.appendQueryParameter("protocol", QUERY_PARAMETER_PROTOCOL_VALUE);
        return contentProviderClient.getType(providerUriBuilder.build()) != null ? providerUriBuilder : getProviderUriBuilder(extraPath);
    }

    private static List<WordListInfo> getWordListWordListInfos(final Locale locale, final Context context, final boolean hasDefaultWordList) {
        String string = context.getString(R.string.dictionary_pack_client_id);
        ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(getProviderUriBuilder("").build());
        if (contentProviderClientAcquireContentProviderClient == null) {
            return Collections.emptyList();
        }
        Cursor cursor = null;
        try {
            try {
                Uri.Builder contentUriBuilderForType = getContentUriBuilderForType(string, contentProviderClientAcquireContentProviderClient, QUERY_PATH_DICT_INFO, locale.toString());
                if (!hasDefaultWordList) {
                    contentUriBuilderForType.appendQueryParameter(QUERY_PARAMETER_MAY_PROMPT_USER, "true");
                }
                Uri uriBuild = contentUriBuilderForType.build();
                boolean zEquals = QUERY_PARAMETER_PROTOCOL_VALUE.equals(uriBuild.getQueryParameter("protocol"));
                String[] strArr = DICTIONARY_PROJECTION;
                Cursor cursorQuery = contentProviderClientAcquireContentProviderClient.query(uriBuild, strArr, null, null, null);
                if (zEquals && cursorQuery == null) {
                    reinitializeClientRecordInDictionaryContentProvider(context, contentProviderClientAcquireContentProviderClient, string);
                    cursorQuery = contentProviderClientAcquireContentProviderClient.query(uriBuild, strArr, null, null, null);
                }
                if (cursorQuery == null) {
                    List<WordListInfo> listEmptyList = Collections.emptyList();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    contentProviderClientAcquireContentProviderClient.release();
                    return listEmptyList;
                }
                if (cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                    ArrayList arrayList = new ArrayList();
                    do {
                        String string2 = cursorQuery.getString(0);
                        String string3 = cursorQuery.getString(1);
                        String string4 = cursorQuery.getString(2);
                        if (!TextUtils.isEmpty(string2)) {
                            arrayList.add(new WordListInfo(string2, string3, string4));
                        }
                    } while (cursorQuery.moveToNext());
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    contentProviderClientAcquireContentProviderClient.release();
                    return arrayList;
                }
                List<WordListInfo> listEmptyList2 = Collections.emptyList();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                contentProviderClientAcquireContentProviderClient.release();
                return listEmptyList2;
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException: communication with the dictionary pack cut", e);
                List<WordListInfo> listEmptyList3 = Collections.emptyList();
                if (0 != 0) {
                    cursor.close();
                }
                contentProviderClientAcquireContentProviderClient.release();
                return listEmptyList3;
            } catch (Exception e2) {
                Log.e(TAG, "Unexpected exception communicating with the dictionary pack", e2);
                List<WordListInfo> listEmptyList4 = Collections.emptyList();
                if (0 != 0) {
                    cursor.close();
                }
                contentProviderClientAcquireContentProviderClient.release();
                return listEmptyList4;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            contentProviderClientAcquireContentProviderClient.release();
            throw th;
        }
    }

    private static AssetFileDescriptor openAssetFileDescriptor(final ContentProviderClient providerClient, final Uri uri) {
        try {
            return providerClient.openAssetFile(uri, "r");
        } catch (RemoteException e) {
            Log.e(TAG, "Can't communicate with the dictionary pack", e);
            return null;
        } catch (FileNotFoundException unused) {
            Log.e(TAG, "Could not find a word list from the dictionary provider.");
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:142:0x022f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0248 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v19, types: [java.io.Closeable, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r12v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r12v21 */
    /* JADX WARN: Type inference failed for: r12v22 */
    /* JADX WARN: Type inference failed for: r12v28 */
    /* JADX WARN: Type inference failed for: r12v29 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v30 */
    /* JADX WARN: Type inference failed for: r12v31 */
    /* JADX WARN: Type inference failed for: r12v32 */
    /* JADX WARN: Type inference failed for: r12v33 */
    /* JADX WARN: Type inference failed for: r12v34 */
    /* JADX WARN: Type inference failed for: r12v35 */
    /* JADX WARN: Type inference failed for: r12v36 */
    /* JADX WARN: Type inference failed for: r12v37 */
    /* JADX WARN: Type inference failed for: r12v38 */
    /* JADX WARN: Type inference failed for: r12v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v11 */
    /* JADX WARN: Type inference failed for: r15v12 */
    /* JADX WARN: Type inference failed for: r15v13 */
    /* JADX WARN: Type inference failed for: r15v14 */
    /* JADX WARN: Type inference failed for: r15v15 */
    /* JADX WARN: Type inference failed for: r15v16, types: [java.io.BufferedInputStream, java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r15v17 */
    /* JADX WARN: Type inference failed for: r15v18 */
    /* JADX WARN: Type inference failed for: r15v19 */
    /* JADX WARN: Type inference failed for: r15v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r15v20 */
    /* JADX WARN: Type inference failed for: r15v21 */
    /* JADX WARN: Type inference failed for: r15v22 */
    /* JADX WARN: Type inference failed for: r15v23 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r23v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23, types: [java.io.BufferedOutputStream, java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v30 */
    /* JADX WARN: Type inference failed for: r7v31 */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r7v33 */
    /* JADX WARN: Type inference failed for: r7v34 */
    /* JADX WARN: Type inference failed for: r7v35 */
    /* JADX WARN: Type inference failed for: r7v36 */
    /* JADX WARN: Type inference failed for: r7v37 */
    /* JADX WARN: Type inference failed for: r7v38 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v40 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void installWordListToStaging(java.lang.String r21, java.lang.String r22, java.lang.String r23, android.content.ContentProviderClient r24, android.content.Context r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 644
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.latin.BinaryDictionaryFileDumper.installWordListToStaging(java.lang.String, java.lang.String, java.lang.String, android.content.ContentProviderClient, android.content.Context):void");
    }

    public static boolean reportBrokenFileToDictionaryProvider(final ContentProviderClient providerClient, final String clientId, final String wordlistId) {
        try {
            Uri.Builder contentUriBuilderForType = getContentUriBuilderForType(clientId, providerClient, QUERY_PATH_DATAFILE, wordlistId);
            contentUriBuilderForType.appendQueryParameter(QUERY_PARAMETER_DELETE_RESULT, QUERY_PARAMETER_FAILURE);
            if (providerClient.delete(contentUriBuilderForType.build(), null, null) > 0) {
                return true;
            }
            Log.e(TAG, "Unable to delete a word list.");
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "Communication with the dictionary provider was cut", e);
            return false;
        }
    }

    private static void closeAssetFileDescriptorAndReportAnyException(final AssetFileDescriptor file) {
        if (file != null) {
            try {
                file.close();
            } catch (Exception e) {
                Log.e(TAG, "Exception while closing a file", e);
            }
        }
    }

    private static void closeCloseableAndReportAnyException(final Closeable file) {
        if (file != null) {
            try {
                file.close();
            } catch (Exception e) {
                Log.e(TAG, "Exception while closing a file", e);
            }
        }
    }

    public static void installDictToStagingFromContentProvider(final Locale locale, final Context context, final boolean hasDefaultWordList) {
        try {
            ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(getProviderUriBuilder("").build());
            if (contentProviderClientAcquireContentProviderClient == null) {
                Log.e(TAG, "Can't establish communication with the dictionary provider");
                return;
            }
            try {
                for (WordListInfo wordListInfo : getWordListWordListInfos(locale, context, hasDefaultWordList)) {
                    installWordListToStaging(wordListInfo.mId, wordListInfo.mLocale, wordListInfo.mRawChecksum, contentProviderClientAcquireContentProviderClient, context);
                }
            } finally {
                contentProviderClientAcquireContentProviderClient.release();
            }
        } catch (SecurityException e) {
            Log.e(TAG, "No permission to communicate with the dictionary provider", e);
        }
    }

    public static void downloadDictIfNeverRequested(final Locale locale, final Context context, final boolean hasDefaultWordList) {
        getWordListWordListInfos(locale, context, hasDefaultWordList);
    }

    public static void checkMagicAndCopyFileTo(final BufferedInputStream input, final BufferedOutputStream output) throws IOException {
        byte[] bArr = MAGIC_NUMBER_VERSION_2;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        if (input.read(bArr2, 0, length) < length) {
            throw new IOException("Less bytes to read than the magic number length");
        }
        if (!Arrays.equals(bArr, bArr2) && !Arrays.equals(MAGIC_NUMBER_VERSION_1, bArr2)) {
            throw new IOException("Wrong magic number for downloaded file");
        }
        output.write(bArr2);
        byte[] bArr3 = new byte[8192];
        while (true) {
            int i = input.read(bArr3);
            if (i >= 0) {
                output.write(bArr3, 0, i);
            } else {
                input.close();
                return;
            }
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x00c6 -> B:30:0x00db). Please report as a decompilation issue!!! */
    private static void reinitializeClientRecordInDictionaryContentProvider(final Context context, final ContentProviderClient client, final String clientId) throws RemoteException {
        String metadataUri = MetadataFileUriGetter.getMetadataUri(context);
        Log.i(TAG, "reinitializeClientRecordInDictionaryContentProvider() : MetadataFileUri = " + metadataUri);
        String metadataAdditionalId = MetadataFileUriGetter.getMetadataAdditionalId(context);
        Uri uriBuild = getProviderUriBuilder(clientId).appendPath("metadata").appendQueryParameter("protocol", QUERY_PARAMETER_PROTOCOL_VALUE).build();
        InputStream inputStreamOpenRawResource = null;
        client.delete(uriBuild, null, null);
        ContentValues contentValues = new ContentValues();
        contentValues.put(INSERT_METADATA_CLIENT_ID_COLUMN, clientId);
        contentValues.put(INSERT_METADATA_METADATA_URI_COLUMN, metadataUri);
        contentValues.put(INSERT_METADATA_METADATA_ADDITIONAL_ID_COLUMN, metadataAdditionalId);
        client.insert(uriBuild, contentValues);
        Uri uriBuild2 = getProviderUriBuilder(clientId).appendPath(QUERY_PATH_DICT_INFO).appendQueryParameter("protocol", QUERY_PARAMETER_PROTOCOL_VALUE).build();
        ArrayList<DictionaryInfoUtils.DictionaryInfo> currentDictionaryFileNameAndVersionInfo = DictionaryInfoUtils.getCurrentDictionaryFileNameAndVersionInfo(context);
        int size = currentDictionaryFileNameAndVersionInfo.size();
        for (int i = 0; i < size; i++) {
            DictionaryInfoUtils.DictionaryInfo dictionaryInfo = currentDictionaryFileNameAndVersionInfo.get(i);
            Log.i(TAG, "reinitializeClientRecordInDictionaryContentProvider() : Insert " + dictionaryInfo);
            client.insert(Uri.withAppendedPath(uriBuild2, dictionaryInfo.mId), dictionaryInfo.toContentValues());
        }
        int identifier = context.getResources().getIdentifier("metadata", "raw", DictionaryInfoUtils.RESOURCE_PACKAGE_NAME);
        if (identifier == 0) {
            Log.w(TAG, "Missing metadata.json resource");
            return;
        }
        try {
            try {
                try {
                    inputStreamOpenRawResource = context.getResources().openRawResource(identifier);
                    UpdateHandler.handleMetadata(context, inputStreamOpenRawResource, clientId);
                    if (inputStreamOpenRawResource != null) {
                        inputStreamOpenRawResource.close();
                    }
                } catch (Throwable th) {
                    if (inputStreamOpenRawResource != null) {
                        try {
                            inputStreamOpenRawResource.close();
                        } catch (IOException e) {
                            Log.w(TAG, "Failed to close metadata.json", e);
                        }
                    }
                    throw th;
                }
            } catch (Exception e2) {
                Log.w(TAG, "Failed to read metadata.json from resources", e2);
                if (inputStreamOpenRawResource != null) {
                    inputStreamOpenRawResource.close();
                }
            }
        } catch (IOException e3) {
            Log.w(TAG, "Failed to close metadata.json", e3);
        }
    }

    public static void initializeClientRecordHelper(final Context context, final String clientId) {
        try {
            ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(getProviderUriBuilder("").build());
            if (contentProviderClientAcquireContentProviderClient == null) {
                return;
            }
            reinitializeClientRecordInDictionaryContentProvider(context, contentProviderClientAcquireContentProviderClient, clientId);
        } catch (RemoteException e) {
            Log.e(TAG, "Cannot contact the dictionary content provider", e);
        }
    }
}

package com.android.inputmethod.latin;

import android.content.ContentProviderClient;
import android.content.Context;
import android.util.Log;
import com.android.inputmethod.latin.utils.DictionaryInfoUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class DictionaryFactory {
    private static final String TAG = "DictionaryFactory";

    public static DictionaryCollection createMainDictionaryFromManager(final Context context, final Locale locale) {
        if (locale == null) {
            Log.e(TAG, "No locale defined for dictionary");
            return new DictionaryCollection("main", locale, createReadOnlyBinaryDictionary(context, locale));
        }
        LinkedList linkedList = new LinkedList();
        ArrayList<AssetFileAddress> dictionaryFiles = BinaryDictionaryGetter.getDictionaryFiles(locale, context, true);
        if (dictionaryFiles != null) {
            for (AssetFileAddress assetFileAddress : dictionaryFiles) {
                ReadOnlyBinaryDictionary readOnlyBinaryDictionary = new ReadOnlyBinaryDictionary(assetFileAddress.mFilename, assetFileAddress.mOffset, assetFileAddress.mLength, false, locale, "main");
                if (readOnlyBinaryDictionary.isValidDictionary()) {
                    linkedList.add(readOnlyBinaryDictionary);
                } else {
                    readOnlyBinaryDictionary.close();
                    killDictionary(context, assetFileAddress);
                }
            }
        }
        return new DictionaryCollection("main", locale, linkedList);
    }

    public static void killDictionary(final Context context, final AssetFileAddress f) {
        if (f.pointsToPhysicalFile()) {
            f.deleteUnderlyingFile();
            try {
                ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(BinaryDictionaryFileDumper.getProviderUriBuilder("").build());
                if (contentProviderClientAcquireContentProviderClient == null) {
                    Log.e(TAG, "Can't establish communication with the dictionary provider");
                } else {
                    BinaryDictionaryFileDumper.reportBrokenFileToDictionaryProvider(contentProviderClientAcquireContentProviderClient, context.getString(R.string.dictionary_pack_client_id), DictionaryInfoUtils.getWordListIdFromFileName(new File(f.mFilename).getName()));
                }
            } catch (SecurityException e) {
                Log.e(TAG, "No permission to communicate with the dictionary provider", e);
            }
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x008c: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:30:0x008c */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.android.inputmethod.latin.ReadOnlyBinaryDictionary createReadOnlyBinaryDictionary(final android.content.Context r12, final java.util.Locale r13) throws java.lang.Throwable {
        /*
            r0 = 0
            android.content.res.Resources r1 = r12.getResources()     // Catch: java.lang.Throwable -> L7b android.content.res.Resources.NotFoundException -> L7d
            int r1 = com.android.inputmethod.latin.utils.DictionaryInfoUtils.getMainDictionaryResourceIdIfAvailableForLocale(r1, r13)     // Catch: java.lang.Throwable -> L7b android.content.res.Resources.NotFoundException -> L7d
            if (r1 != 0) goto Lc
            return r0
        Lc:
            android.content.res.Resources r2 = r12.getResources()     // Catch: java.lang.Throwable -> L7b android.content.res.Resources.NotFoundException -> L7d
            android.content.res.AssetFileDescriptor r2 = r2.openRawResourceFd(r1)     // Catch: java.lang.Throwable -> L7b android.content.res.Resources.NotFoundException -> L7d
            if (r2 != 0) goto L34
            java.lang.String r12 = com.android.inputmethod.latin.DictionaryFactory.TAG     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            r13.<init>()     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            java.lang.String r3 = "Found the resource but it is compressed. resId="
            java.lang.StringBuilder r13 = r13.append(r3)     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            java.lang.StringBuilder r13 = r13.append(r1)     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            java.lang.String r13 = r13.toString()     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            android.util.Log.e(r12, r13)     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            if (r2 == 0) goto L33
            r2.close()     // Catch: java.io.IOException -> L33
        L33:
            return r0
        L34:
            android.content.pm.ApplicationInfo r12 = r12.getApplicationInfo()     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            java.lang.String r4 = r12.sourceDir     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            java.io.File r12 = new java.io.File     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            r12.<init>(r4)     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            boolean r12 = r12.isFile()     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            if (r12 != 0) goto L63
            java.lang.String r12 = com.android.inputmethod.latin.DictionaryFactory.TAG     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            r13.<init>()     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            java.lang.String r1 = "sourceDir is not a file: "
            java.lang.StringBuilder r13 = r13.append(r1)     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            java.lang.StringBuilder r13 = r13.append(r4)     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            java.lang.String r13 = r13.toString()     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            android.util.Log.e(r12, r13)     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            if (r2 == 0) goto L62
            r2.close()     // Catch: java.io.IOException -> L62
        L62:
            return r0
        L63:
            com.android.inputmethod.latin.ReadOnlyBinaryDictionary r12 = new com.android.inputmethod.latin.ReadOnlyBinaryDictionary     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            long r5 = r2.getStartOffset()     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            long r7 = r2.getLength()     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            r9 = 0
            java.lang.String r11 = "main"
            r3 = r12
            r10 = r13
            r3.<init>(r4, r5, r7, r9, r10, r11)     // Catch: android.content.res.Resources.NotFoundException -> L7e java.lang.Throwable -> L8b
            if (r2 == 0) goto L7a
            r2.close()     // Catch: java.io.IOException -> L7a
        L7a:
            return r12
        L7b:
            r12 = move-exception
            goto L8d
        L7d:
            r2 = r0
        L7e:
            java.lang.String r12 = com.android.inputmethod.latin.DictionaryFactory.TAG     // Catch: java.lang.Throwable -> L8b
            java.lang.String r13 = "Could not find the resource"
            android.util.Log.e(r12, r13)     // Catch: java.lang.Throwable -> L8b
            if (r2 == 0) goto L8a
            r2.close()     // Catch: java.io.IOException -> L8a
        L8a:
            return r0
        L8b:
            r12 = move-exception
            r0 = r2
        L8d:
            if (r0 == 0) goto L92
            r0.close()     // Catch: java.io.IOException -> L92
        L92:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.latin.DictionaryFactory.createReadOnlyBinaryDictionary(android.content.Context, java.util.Locale):com.android.inputmethod.latin.ReadOnlyBinaryDictionary");
    }
}

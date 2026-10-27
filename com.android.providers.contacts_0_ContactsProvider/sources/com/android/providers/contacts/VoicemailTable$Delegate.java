package com.android.providers.contacts;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.ArraySet;
import com.android.providers.contacts.VoicemailContentProvider;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes.dex */
public interface VoicemailTable$Delegate {
    int bulkInsert(VoicemailContentProvider.UriData uriData, ContentValues[] contentValuesArr);

    int delete(VoicemailContentProvider.UriData uriData, String str, String[] strArr);

    ArraySet<String> getSourcePackages();

    String getType(VoicemailContentProvider.UriData uriData);

    Uri insert(VoicemailContentProvider.UriData uriData, ContentValues contentValues);

    ParcelFileDescriptor openFile(VoicemailContentProvider.UriData uriData, String str) throws FileNotFoundException;

    Cursor query(VoicemailContentProvider.UriData uriData, String[] strArr, String str, String[] strArr2, String str2);

    int update(VoicemailContentProvider.UriData uriData, ContentValues contentValues, String str, String[] strArr);
}

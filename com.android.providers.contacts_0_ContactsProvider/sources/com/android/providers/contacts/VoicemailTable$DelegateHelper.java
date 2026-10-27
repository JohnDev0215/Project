package com.android.providers.contacts;

import android.content.ContentValues;
import android.os.ParcelFileDescriptor;
import com.android.providers.contacts.VoicemailContentProvider;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes.dex */
public interface VoicemailTable$DelegateHelper {
    void checkAndAddSourcePackageIntoValues(VoicemailContentProvider.UriData uriData, ContentValues contentValues);

    ParcelFileDescriptor openDataFile(VoicemailContentProvider.UriData uriData, String str) throws FileNotFoundException;
}

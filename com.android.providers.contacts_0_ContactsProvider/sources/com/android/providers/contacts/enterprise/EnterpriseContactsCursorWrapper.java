package com.android.providers.contacts.enterprise;

import android.content.ContentUris;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import com.android.providers.contacts.ContactsProvider2;

/* JADX INFO: loaded from: classes.dex */
public class EnterpriseContactsCursorWrapper extends CursorWrapper {
    private static final boolean VERBOSE_LOGGING = Log.isLoggable("EnterpriseCursorWrapper", 2);
    private static final UriMatcher sUriMatcher = ContactsProvider2.sUriMatcher;
    private final int[] contactIdIndices;
    private final Long mDirectoryId;
    private final boolean mIsDirectoryRemote;
    private final String[] originalColumnNames;

    public EnterpriseContactsCursorWrapper(Cursor cursor, String[] strArr, int[] iArr, Long l) {
        super(cursor);
        this.contactIdIndices = iArr;
        this.originalColumnNames = strArr;
        this.mDirectoryId = l;
        this.mIsDirectoryRemote = l != null && ContactsContract.Directory.isRemoteDirectoryId(l.longValue());
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getColumnCount() {
        return this.originalColumnNames.length;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public String[] getColumnNames() {
        return this.originalColumnNames;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0059  */
    @Override // android.database.CursorWrapper, android.database.Cursor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getString(int r12) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.enterprise.EnterpriseContactsCursorWrapper.getString(int):java.lang.String");
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getInt(int i) {
        return (int) getLong(i);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public long getLong(int i) {
        long j = super.getLong(i);
        if (ArrayUtils.contains(this.contactIdIndices, i)) {
            return j + ContactsContract.Contacts.ENTERPRISE_CONTACT_ID_BASE;
        }
        String columnName = getColumnName(i);
        byte b = -1;
        int iHashCode = columnName.hashCode();
        if (iHashCode != -1274270136) {
            if (iHashCode == 1563708849 && columnName.equals("photo_file_id")) {
                b = 0;
            }
        } else if (columnName.equals("photo_id")) {
            b = 1;
        }
        if (b == 0 || b == 1) {
            return 0L;
        }
        return j;
    }

    private String getRemoteDirectoryFileUri(String str) {
        if (str == null) {
            return null;
        }
        Uri.Builder builderBuildUpon = ContactsContract.Directory.ENTERPRISE_FILE_URI.buildUpon();
        builderBuildUpon.appendPath(str);
        builderBuildUpon.appendQueryParameter("directory", Long.toString(this.mDirectoryId.longValue()));
        String string = builderBuildUpon.build().toString();
        if (VERBOSE_LOGGING) {
            Log.v("EnterpriseCursorWrapper", "getCorpDirectoryFileUri: output URI=" + string);
        }
        return string;
    }

    private static String getCorpThumbnailUri(long j, Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("photo_thumb_uri"));
        if (string == null) {
            return null;
        }
        if (sUriMatcher.match(Uri.parse(string)) == 1009) {
            return ContentUris.appendId(ContactsContract.Contacts.CORP_CONTENT_URI.buildUpon(), j).appendPath("photo").build().toString();
        }
        Log.e("EnterpriseCursorWrapper", "EnterpriseContactsCursorWrapper contains invalid PHOTO_THUMBNAIL_URI");
        return null;
    }

    private static String getCorpDisplayPhotoUri(long j, Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("photo_uri"));
        if (string == null) {
            return null;
        }
        int iMatch = sUriMatcher.match(Uri.parse(string));
        if (iMatch == 1009) {
            return ContentUris.appendId(ContactsContract.Contacts.CORP_CONTENT_URI.buildUpon(), j).appendPath("photo").build().toString();
        }
        if (iMatch == 1012 || iMatch == 22000) {
            return ContentUris.appendId(ContactsContract.Contacts.CORP_CONTENT_URI.buildUpon(), j).appendPath("display_photo").build().toString();
        }
        Log.e("EnterpriseCursorWrapper", "EnterpriseContactsCursorWrapper contains invalid PHOTO_URI");
        return null;
    }
}

package com.android.providers.contacts;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Binder;
import android.provider.CallLog;
import android.provider.VoicemailContract;
import android.util.ArraySet;
import com.android.common.io.MoreCloseables;
import com.android.internal.annotations.VisibleForTesting;
import com.android.providers.contacts.util.DbQueryUtils;
import com.google.android.collect.Lists;
import com.google.common.collect.Iterables;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class DbModifierWithNotification implements DatabaseModifier {
    private static final String[] PROJECTION = {"source_package"};
    private static VoicemailNotifier sVoicemailNotifierForTest;
    private final Uri mBaseUri;
    private final Context mContext;
    private final SQLiteDatabase mDb;
    private final DatabaseUtils.InsertHelper mInsertHelper;
    private boolean mIsBulkOperation;
    private final boolean mIsCallsTable;
    private final String mTableName;
    private final VoicemailNotifier mVoicemailNotifier;

    public DbModifierWithNotification(String str, SQLiteDatabase sQLiteDatabase, Context context) {
        this(str, sQLiteDatabase, null, context);
    }

    public DbModifierWithNotification(String str, DatabaseUtils.InsertHelper insertHelper, Context context) {
        this(str, null, insertHelper, context);
    }

    private DbModifierWithNotification(String str, SQLiteDatabase sQLiteDatabase, DatabaseUtils.InsertHelper insertHelper, Context context) {
        this.mIsBulkOperation = false;
        this.mTableName = str;
        this.mDb = sQLiteDatabase;
        this.mInsertHelper = insertHelper;
        this.mContext = context;
        this.mBaseUri = str.equals("voicemail_status") ? VoicemailContract.Status.CONTENT_URI : VoicemailContract.Voicemails.CONTENT_URI;
        this.mIsCallsTable = this.mTableName.equals("calls");
        VoicemailNotifier voicemailNotifier = sVoicemailNotifierForTest;
        this.mVoicemailNotifier = voicemailNotifier == null ? new VoicemailNotifier(this.mContext, this.mBaseUri) : voicemailNotifier;
    }

    @Override // com.android.providers.contacts.DatabaseModifier
    public long insert(String str, String str2, ContentValues contentValues) {
        Set<String> modifiedPackages = getModifiedPackages(contentValues);
        if (this.mIsCallsTable) {
            contentValues.put("last_modified", Long.valueOf(getTimeMillis()));
        }
        long jInsert = this.mDb.insert(str, str2, contentValues);
        if (jInsert > 0 && modifiedPackages.size() != 0) {
            notifyVoicemailChangeOnInsert(ContentUris.withAppendedId(this.mBaseUri, jInsert), modifiedPackages);
        }
        if (jInsert > 0 && this.mIsCallsTable) {
            notifyCallLogChange(this.mContext);
        }
        return jInsert;
    }

    @Override // com.android.providers.contacts.DatabaseModifier
    public long insert(ContentValues contentValues) {
        Set<String> modifiedPackages = getModifiedPackages(contentValues);
        if (this.mIsCallsTable) {
            contentValues.put("last_modified", Long.valueOf(getTimeMillis()));
        }
        long jInsert = this.mInsertHelper.insert(contentValues);
        if (jInsert > 0 && modifiedPackages.size() != 0) {
            notifyVoicemailChangeOnInsert(ContentUris.withAppendedId(this.mBaseUri, jInsert), modifiedPackages);
        }
        if (jInsert > 0 && this.mIsCallsTable) {
            notifyCallLogChange(this.mContext);
        }
        return jInsert;
    }

    public static void notifyCallLogChange(Context context) {
        context.getContentResolver().notifyChange(CallLog.Calls.CONTENT_URI, (ContentObserver) null, false);
        Intent intent = new Intent("com.android.internal.action.CALL_LOG_CHANGE");
        intent.setComponent(new ComponentName("com.android.calllogbackup", "com.android.calllogbackup.CallLogChangeReceiver"));
        if (context.getPackageManager().queryBroadcastReceivers(intent, 0).isEmpty()) {
            return;
        }
        context.sendBroadcast(intent);
    }

    private void notifyVoicemailChangeOnInsert(Uri uri, Set<String> set) {
        if (this.mIsCallsTable) {
            this.mVoicemailNotifier.addIntentActions("android.intent.action.NEW_VOICEMAIL");
        }
        notifyVoicemailChange(uri, set);
    }

    private void notifyVoicemailChange(Uri uri, Set<String> set) {
        this.mVoicemailNotifier.addUri(uri);
        this.mVoicemailNotifier.addModifiedPackages(set);
        this.mVoicemailNotifier.addIntentActions("android.intent.action.PROVIDER_CHANGED");
        if (this.mIsBulkOperation) {
            return;
        }
        this.mVoicemailNotifier.sendNotification();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006d  */
    @Override // com.android.providers.contacts.DatabaseModifier
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int update(android.net.Uri r7, java.lang.String r8, android.content.ContentValues r9, java.lang.String r10, java.lang.String[] r11) {
        /*
            r6 = this;
            java.util.Set r0 = r6.getModifiedPackages(r10, r11)
            java.util.Set r1 = r6.getModifiedPackages(r9)
            r0.addAll(r1)
            int r1 = r0.size()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L1b
            boolean r1 = r6.isUpdatingVoicemailColumns(r9)
            if (r1 == 0) goto L1b
            r1 = r2
            goto L1c
        L1b:
            r1 = r3
        L1c:
            boolean r4 = r6.mIsCallsTable
            if (r4 == 0) goto L6d
            java.lang.String r4 = "deleted"
            boolean r5 = r9.containsKey(r4)
            if (r5 == 0) goto L40
            java.lang.Boolean r4 = r9.getAsBoolean(r4)
            boolean r4 = r4.booleanValue()
            if (r4 != 0) goto L40
            long r4 = r6.getTimeMillis()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.lang.String r5 = "last_modified"
            r9.put(r5, r4)
            goto L43
        L40:
            r6.updateLastModified(r8, r10, r11)
        L43:
            if (r1 == 0) goto L6d
            boolean r4 = r6.updateDirtyFlag(r9, r0)
            if (r4 == 0) goto L6d
            java.lang.String r4 = "is_read"
            boolean r5 = r9.containsKey(r4)
            if (r5 == 0) goto L6d
            java.lang.Boolean r4 = getAsBoolean(r9, r4)
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L6d
            java.lang.String r4 = "new"
            boolean r5 = r9.containsKey(r4)
            if (r5 != 0) goto L6d
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            r9.put(r4, r5)
            goto L6e
        L6d:
            r2 = r3
        L6e:
            boolean r4 = r9.isEmpty()
            if (r4 == 0) goto L75
            return r3
        L75:
            android.database.sqlite.SQLiteDatabase r3 = r6.mDb
            int r9 = r3.update(r8, r9, r10, r11)
            if (r9 <= 0) goto L7f
            if (r1 != 0) goto L87
        L7f:
            java.lang.String r10 = "voicemail_status"
            boolean r8 = r10.equals(r8)
            if (r8 == 0) goto L8c
        L87:
            android.net.Uri r8 = r6.mBaseUri
            r6.notifyVoicemailChange(r8, r0)
        L8c:
            if (r9 <= 0) goto L97
            boolean r8 = r6.mIsCallsTable
            if (r8 == 0) goto L97
            android.content.Context r8 = r6.mContext
            notifyCallLogChange(r8)
        L97:
            if (r2 == 0) goto La7
            android.content.Context r6 = r6.mContext
            android.content.Intent r8 = new android.content.Intent
            java.lang.String r10 = "android.intent.action.NEW_VOICEMAIL"
            r8.<init>(r10, r7)
            java.lang.String r7 = "com.android.voicemail.permission.READ_VOICEMAIL"
            r6.sendBroadcast(r8, r7)
        La7:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.DbModifierWithNotification.update(android.net.Uri, java.lang.String, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean updateDirtyFlag(ContentValues contentValues, Set<String> set) {
        int i;
        Integer asInteger = contentValues.getAsInteger("dirty");
        if (asInteger != null) {
            i = 0;
            if (asInteger.intValue() == -1) {
                contentValues.remove("dirty");
                return false;
            }
            if (asInteger.intValue() != 0) {
                i = 1;
            }
        } else {
            i = !isSelfModifyingOrInternal(set) ? 1 : 0;
        }
        contentValues.put("dirty", Integer.valueOf(i));
        return i ^ 1;
    }

    private boolean isUpdatingVoicemailColumns(ContentValues contentValues) {
        Iterator<String> it = contentValues.keySet().iterator();
        while (it.hasNext()) {
            if (VoicemailContentTable.ALLOWED_COLUMNS.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    private void updateLastModified(String str, String str2, String[] strArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("last_modified", Long.valueOf(getTimeMillis()));
        this.mDb.update(str, contentValues, DbQueryUtils.concatenateClauses("deleted == 0", str2), strArr);
    }

    @Override // com.android.providers.contacts.DatabaseModifier
    public int delete(String str, String str2, String[] strArr) {
        int iDelete;
        Set<String> modifiedPackages = getModifiedPackages(str2, strArr);
        boolean z = modifiedPackages.size() != 0;
        if (this.mIsCallsTable && z && !isSelfModifyingOrInternal(modifiedPackages)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("dirty", (Integer) 1);
            contentValues.put("deleted", (Integer) 1);
            contentValues.put("last_modified", Long.valueOf(getTimeMillis()));
            iDelete = this.mDb.update(str, contentValues, str2, strArr);
        } else {
            iDelete = this.mDb.delete(str, str2, strArr);
        }
        if (iDelete > 0 && z) {
            notifyVoicemailChange(this.mBaseUri, modifiedPackages);
        }
        if (iDelete > 0 && this.mIsCallsTable) {
            notifyCallLogChange(this.mContext);
        }
        return iDelete;
    }

    @Override // com.android.providers.contacts.DatabaseModifier
    public void startBulkOperation() {
        this.mIsBulkOperation = true;
        this.mDb.beginTransaction();
    }

    @Override // com.android.providers.contacts.DatabaseModifier
    public void yieldBulkOperation() {
        this.mDb.yieldIfContendedSafely();
    }

    @Override // com.android.providers.contacts.DatabaseModifier
    public void finishBulkOperation() {
        this.mDb.setTransactionSuccessful();
        this.mDb.endTransaction();
        this.mIsBulkOperation = false;
        this.mVoicemailNotifier.sendNotification();
    }

    private Set<String> getModifiedPackages(String str, String[] strArr) {
        ArraySet arraySet = new ArraySet();
        Cursor cursorQuery = this.mDb.query(this.mTableName, PROJECTION, DbQueryUtils.concatenateClauses("source_package IS NOT NULL", str), strArr, null, null, null);
        while (cursorQuery.moveToNext()) {
            arraySet.add(cursorQuery.getString(0));
        }
        MoreCloseables.closeQuietly(cursorQuery);
        return arraySet;
    }

    private Set<String> getModifiedPackages(ContentValues contentValues) {
        ArraySet arraySet = new ArraySet();
        if (contentValues.containsKey("source_package")) {
            arraySet.add(contentValues.getAsString("source_package"));
        }
        return arraySet;
    }

    private boolean isSelfModifyingOrInternal(Set<String> set) {
        Collection<String> callingPackages = getCallingPackages();
        if (callingPackages != null && set.size() == 1) {
            return callingPackages.contains(Iterables.getOnlyElement(set)) || callingPackages.contains(this.mContext.getPackageName());
        }
        return false;
    }

    private Collection<String> getCallingPackages() {
        int callingUid = Binder.getCallingUid();
        if (callingUid == 0) {
            return null;
        }
        return Lists.newArrayList(this.mContext.getPackageManager().getPackagesForUid(callingUid));
    }

    private static Boolean getAsBoolean(ContentValues contentValues, String str) {
        Object obj = contentValues.get(str);
        if (obj instanceof CharSequence) {
            try {
                return Boolean.valueOf(Integer.parseInt(obj.toString()) != 0);
            } catch (NumberFormatException unused) {
            }
        }
        return contentValues.getAsBoolean(str);
    }

    private long getTimeMillis() {
        if (CallLogProvider.getTimeForTestMillis() == null) {
            return System.currentTimeMillis();
        }
        return CallLogProvider.getTimeForTestMillis().longValue();
    }

    @VisibleForTesting
    static void setVoicemailNotifierForTest(VoicemailNotifier voicemailNotifier) {
        sVoicemailNotifierForTest = voicemailNotifier;
    }
}

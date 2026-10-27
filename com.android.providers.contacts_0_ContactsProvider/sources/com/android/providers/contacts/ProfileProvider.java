package com.android.providers.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.CancellationSignal;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class ProfileProvider extends AbstractContactsProvider {
    private final ContactsProvider2 mDelegate;

    public String toString() {
        return "ProfileProvider";
    }

    public ProfileProvider(ContactsProvider2 contactsProvider2) {
        this.mDelegate = contactsProvider2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.providers.contacts.AbstractContactsProvider
    public ProfileDatabaseHelper newDatabaseHelper(Context context) {
        return ProfileDatabaseHelper.getInstance(context);
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider
    public ProfileDatabaseHelper getDatabaseHelper() {
        return (ProfileDatabaseHelper) super.getDatabaseHelper();
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider
    protected ThreadLocal<ContactsTransaction> getTransactionHolder() {
        return this.mDelegate.getTransactionHolder();
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return query(uri, strArr, str, strArr2, str2, null);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        int callingUid = Binder.getCallingUid();
        this.mStats.incrementQueryStats(callingUid);
        try {
            return this.mDelegate.queryLocal(uri, strArr, str, strArr2, str2, -1L, cancellationSignal);
        } finally {
            this.mStats.finishOperation(callingUid);
        }
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider
    protected Uri insertInTransaction(Uri uri, ContentValues contentValues) {
        useProfileDbForTransaction();
        return this.mDelegate.insertInTransaction(uri, contentValues);
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider
    protected int updateInTransaction(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        useProfileDbForTransaction();
        return this.mDelegate.updateInTransaction(uri, contentValues, str, strArr);
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider
    protected int deleteInTransaction(Uri uri, String str, String[] strArr) {
        useProfileDbForTransaction();
        return this.mDelegate.deleteInTransaction(uri, str, strArr);
    }

    @Override // android.content.ContentProvider
    public AssetFileDescriptor openAssetFile(Uri uri, String str) throws FileNotFoundException {
        return this.mDelegate.openAssetFileLocal(uri, str);
    }

    private void useProfileDbForTransaction() {
        getCurrentTransaction().startTransactionForDb(getDatabaseHelper().getWritableDatabase(), "profile", this);
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider
    protected void notifyChange() {
        this.mDelegate.notifyChange();
    }

    @Override // android.database.sqlite.SQLiteTransactionListener
    public void onBegin() {
        this.mDelegate.onBeginTransactionInternal(true);
    }

    @Override // android.database.sqlite.SQLiteTransactionListener
    public void onCommit() {
        this.mDelegate.onCommitTransactionInternal(true);
        sendProfileChangedBroadcast();
    }

    @Override // android.database.sqlite.SQLiteTransactionListener
    public void onRollback() {
        this.mDelegate.onRollbackTransactionInternal(true);
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider
    protected boolean yield(ContactsTransaction contactsTransaction) {
        return this.mDelegate.yield(contactsTransaction);
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return this.mDelegate.getType(uri);
    }

    private void sendProfileChangedBroadcast() {
        Intent intent = new Intent("android.provider.Contacts.PROFILE_CHANGED");
        this.mDelegate.getContext().sendBroadcast(intent, "android.permission.READ_CONTACTS");
        intent.setPackage("com.android.settings");
        this.mDelegate.getContext().sendBroadcast(intent, "android.permission.READ_CONTACTS");
    }

    @Override // android.content.ContentProvider
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        dump(printWriter, "Profile");
    }
}

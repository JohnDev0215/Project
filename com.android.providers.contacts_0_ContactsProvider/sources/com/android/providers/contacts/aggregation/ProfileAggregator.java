package com.android.providers.contacts.aggregation;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteStatement;
import com.android.providers.contacts.ContactsDatabaseHelper;
import com.android.providers.contacts.ContactsProvider2;
import com.android.providers.contacts.NameSplitter;
import com.android.providers.contacts.PhotoPriorityResolver;
import com.android.providers.contacts.TransactionContext;
import com.android.providers.contacts.aggregation.util.CommonNicknameCache;

/* JADX INFO: loaded from: classes.dex */
public class ProfileAggregator extends ContactAggregator {
    private long mContactId;

    @Override // com.android.providers.contacts.aggregation.AbstractContactAggregator
    public void aggregateInTransaction(TransactionContext transactionContext, SQLiteDatabase sQLiteDatabase) {
    }

    @Override // com.android.providers.contacts.aggregation.AbstractContactAggregator
    protected String computeLookupKeyForContact(SQLiteDatabase sQLiteDatabase, long j) {
        return "profile";
    }

    public ProfileAggregator(ContactsProvider2 contactsProvider2, ContactsDatabaseHelper contactsDatabaseHelper, PhotoPriorityResolver photoPriorityResolver, NameSplitter nameSplitter, CommonNicknameCache commonNicknameCache) {
        super(contactsProvider2, contactsDatabaseHelper, photoPriorityResolver, nameSplitter, commonNicknameCache);
    }

    @Override // com.android.providers.contacts.aggregation.AbstractContactAggregator
    protected void appendLookupKey(StringBuilder sb, String str, String str2, long j, String str3, String str4) {
        sb.setLength(0);
        sb.append("profile");
    }

    @Override // com.android.providers.contacts.aggregation.AbstractContactAggregator
    public long onRawContactInsert(TransactionContext transactionContext, SQLiteDatabase sQLiteDatabase, long j) {
        aggregateContact(transactionContext, sQLiteDatabase, j);
        return this.mContactId;
    }

    @Override // com.android.providers.contacts.aggregation.AbstractContactAggregator
    public void aggregateContact(TransactionContext transactionContext, SQLiteDatabase sQLiteDatabase, long j) {
        SQLiteStatement sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement("SELECT _id FROM contacts ORDER BY _id LIMIT 1");
        try {
            try {
                long jSimpleQueryForLong = sQLiteStatementCompileStatement.simpleQueryForLong();
                this.mContactId = jSimpleQueryForLong;
                updateAggregateData(transactionContext, jSimpleQueryForLong);
            } catch (SQLiteDoneException unused) {
                this.mContactId = insertContact(sQLiteDatabase, j);
            }
            sQLiteStatementCompileStatement.close();
            setContactId(j, this.mContactId);
        } catch (Throwable th) {
            sQLiteStatementCompileStatement.close();
            throw th;
        }
    }
}

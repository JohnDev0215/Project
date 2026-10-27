package com.android.bluetooth.pbapclient;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class CallLogPullRequest extends PullRequest {
    private static final boolean DBG = false;
    private static final String TAG = "PbapCallLogPullRequest";
    private static final String TIMESTAMP_FORMAT = "yyyyMMdd'T'HHmmss";
    private static final String TIMESTAMP_PROPERTY = "X-IRMC-CALL-DATETIME";
    private static final boolean VDBG = false;
    private final Account mAccount;
    private HashMap<String, Integer> mCallCounter;
    private Context mContext;

    public CallLogPullRequest(Context context, String path, HashMap<String, Integer> map, Account account) {
        this.mContext = context;
        this.path = path;
        this.mCallCounter = map;
        this.mAccount = account;
    }

    @Override // com.android.bluetooth.pbapclient.PullRequest
    public void onPullComplete() {
        if (this.mEntries == null) {
            Log.e(TAG, "onPullComplete entries is null.");
        }
    }

    private void updateTimesContacted() {
        for (String key : this.mCallCounter.keySet()) {
            ContentValues values = new ContentValues();
            values.put("times_contacted", this.mCallCounter.get(key));
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(key));
            Cursor c = this.mContext.getContentResolver().query(uri, null, null, null);
            if (c != null && c.getCount() > 0) {
                c.moveToNext();
                String contactId = c.getString(c.getColumnIndex("contact_id"));
                String where = "contact_id=" + contactId;
                this.mContext.getContentResolver().update(ContactsContract.RawContacts.CONTENT_URI, values, where, null);
            }
        }
    }
}

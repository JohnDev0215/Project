package com.android.inputmethod.latin;

import android.content.Context;
import android.database.ContentObserver;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.util.Log;
import com.android.inputmethod.latin.ContactsManager;
import com.android.inputmethod.latin.permissions.PermissionsUtil;
import com.android.inputmethod.latin.utils.ExecutorUtils;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class ContactsContentObserver implements Runnable {
    private static final String TAG = "ContactsContentObserver";
    private ContactsManager.ContactsChangedListener mContactsChangedListener;
    private ContentObserver mContentObserver;
    private final Context mContext;
    private final ContactsManager mManager;
    private final AtomicBoolean mRunning = new AtomicBoolean(false);

    public ContactsContentObserver(final ContactsManager manager, final Context context) {
        this.mManager = manager;
        this.mContext = context;
    }

    public void registerObserver(final ContactsManager.ContactsChangedListener listener) {
        if (!PermissionsUtil.checkAllPermissionsGranted(this.mContext, "android.permission.READ_CONTACTS")) {
            Log.i(TAG, "No permission to read contacts. Not registering the observer.");
            return;
        }
        this.mContactsChangedListener = listener;
        this.mContentObserver = new ContentObserver(null) { // from class: com.android.inputmethod.latin.ContactsContentObserver.1
            @Override // android.database.ContentObserver
            public void onChange(boolean self) {
                ExecutorUtils.getBackgroundExecutor(ExecutorUtils.KEYBOARD).execute(ContactsContentObserver.this);
            }
        };
        this.mContext.getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, this.mContentObserver);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!PermissionsUtil.checkAllPermissionsGranted(this.mContext, "android.permission.READ_CONTACTS")) {
            Log.i(TAG, "No permission to read contacts. Not updating the contacts.");
            unregister();
        } else if (this.mRunning.compareAndSet(false, true)) {
            if (haveContentsChanged()) {
                this.mContactsChangedListener.onContactsChange();
            }
            this.mRunning.set(false);
        }
    }

    boolean haveContentsChanged() {
        if (!PermissionsUtil.checkAllPermissionsGranted(this.mContext, "android.permission.READ_CONTACTS")) {
            Log.i(TAG, "No permission to read contacts. Marking contacts as not changed.");
            return false;
        }
        SystemClock.uptimeMillis();
        int contactCount = this.mManager.getContactCount();
        if (contactCount > 10000) {
            return false;
        }
        return (contactCount == this.mManager.getContactCountAtLastRebuild() && this.mManager.getValidNames(ContactsContract.Contacts.CONTENT_URI).hashCode() == this.mManager.getHashCodeAtLastRebuild()) ? false : true;
    }

    public void unregister() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mContentObserver);
    }
}

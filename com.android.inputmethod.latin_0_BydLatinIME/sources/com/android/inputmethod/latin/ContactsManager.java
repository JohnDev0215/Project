package com.android.inputmethod.latin;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public class ContactsManager {
    public static final int MAX_CONTACT_NAMES = 200;
    private static final String TAG = "ContactsManager";
    private final Context mContext;
    private final ContactsContentObserver mObserver;
    private AtomicInteger mContactCountAtLastRebuild = new AtomicInteger(0);
    private AtomicInteger mHashCodeAtLastRebuild = new AtomicInteger(0);

    public interface ContactsChangedListener {
        void onContactsChange();
    }

    protected static class RankedContact {
        private float mAffinity = 0.0f;
        public final boolean mInVisibleGroup;
        public final long mLastContactedTime;
        public final String mName;
        public final int mTimesContacted;

        RankedContact(final Cursor cursor) {
            this.mName = cursor.getString(1);
            this.mTimesContacted = cursor.getInt(2);
            this.mLastContactedTime = cursor.getLong(3);
            this.mInVisibleGroup = cursor.getInt(4) == 1;
        }

        float getAffinity() {
            return this.mAffinity;
        }

        void computeAffinity(final int maxTimesContacted, final long currentTime) {
            this.mAffinity = ((((this.mTimesContacted + 1.0f) / (maxTimesContacted + 1)) + ((float) Math.pow(0.5d, Math.min(Math.max(0L, currentTime - this.mLastContactedTime), TimeUnit.MILLISECONDS.convert(180L, TimeUnit.DAYS)) / TimeUnit.MILLISECONDS.convert(10L, TimeUnit.DAYS)))) + (this.mInVisibleGroup ? 1.0f : 0.0f)) / 3.0f;
        }
    }

    private static class AffinityComparator implements Comparator<RankedContact> {
        private AffinityComparator() {
        }

        @Override // java.util.Comparator
        public int compare(RankedContact contact1, RankedContact contact2) {
            return Float.compare(contact2.getAffinity(), contact1.getAffinity());
        }
    }

    public ContactsManager(final Context context) {
        this.mContext = context;
        this.mObserver = new ContactsContentObserver(this, context);
    }

    public void registerForUpdates(final ContactsChangedListener listener) {
        this.mObserver.registerObserver(listener);
    }

    public int getContactCountAtLastRebuild() {
        return this.mContactCountAtLastRebuild.get();
    }

    public int getHashCodeAtLastRebuild() {
        return this.mHashCodeAtLastRebuild.get();
    }

    public ArrayList<String> getValidNames(final Uri uri) {
        int i;
        Cursor cursorQuery = this.mContext.getContentResolver().query(uri, ContactsDictionaryConstants.PROJECTION, null, null, null);
        ArrayList arrayList = new ArrayList();
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    i = 0;
                    while (!cursorQuery.isAfterLast()) {
                        if (isValidName(cursorQuery.getString(1))) {
                            int i2 = cursorQuery.getInt(2);
                            if (i2 > i) {
                                i = i2;
                            }
                            arrayList.add(new RankedContact(cursorQuery));
                        }
                        cursorQuery.moveToNext();
                    }
                } else {
                    i = 0;
                }
            } finally {
                cursorQuery.close();
            }
        } else {
            i = 0;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((RankedContact) it.next()).computeAffinity(i, jCurrentTimeMillis);
        }
        Collections.sort(arrayList, new AffinityComparator());
        HashSet hashSet = new HashSet();
        for (int i3 = 0; i3 < arrayList.size() && hashSet.size() < 200; i3++) {
            hashSet.add(((RankedContact) arrayList.get(i3)).mName);
        }
        return new ArrayList<>(hashSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getContactCount() {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            android.content.Context r2 = r9.mContext     // Catch: java.lang.Throwable -> L25 android.database.sqlite.SQLiteException -> L27
            android.content.ContentResolver r3 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L25 android.database.sqlite.SQLiteException -> L27
            android.net.Uri r4 = android.provider.ContactsContract.Contacts.CONTENT_URI     // Catch: java.lang.Throwable -> L25 android.database.sqlite.SQLiteException -> L27
            java.lang.String[] r5 = com.android.inputmethod.latin.ContactsDictionaryConstants.PROJECTION_ID_ONLY     // Catch: java.lang.Throwable -> L25 android.database.sqlite.SQLiteException -> L27
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L25 android.database.sqlite.SQLiteException -> L27
            if (r1 != 0) goto L1b
            if (r1 == 0) goto L1a
            r1.close()
        L1a:
            return r0
        L1b:
            int r0 = r1.getCount()     // Catch: java.lang.Throwable -> L25 android.database.sqlite.SQLiteException -> L27
            if (r1 == 0) goto L24
            r1.close()
        L24:
            return r0
        L25:
            r0 = move-exception
            goto L35
        L27:
            r2 = move-exception
            java.lang.String r3 = "ContactsManager"
            java.lang.String r4 = "SQLiteException in the remote Contacts process."
            android.util.Log.e(r3, r4, r2)     // Catch: java.lang.Throwable -> L25
            if (r1 == 0) goto L34
            r1.close()
        L34:
            return r0
        L35:
            if (r1 == 0) goto L3a
            r1.close()
        L3a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.latin.ContactsManager.getContactCount():int");
    }

    private static boolean isValidName(final String name) {
        if (TextUtils.isEmpty(name) || name.indexOf(64) != -1) {
            return false;
        }
        return (name.indexOf(32) != -1) || name.indexOf(45) == -1;
    }

    public void updateLocalState(final ArrayList<String> names) {
        this.mContactCountAtLastRebuild.set(getContactCount());
        this.mHashCodeAtLastRebuild.set(names.hashCode());
    }

    public void close() {
        this.mObserver.unregister();
    }
}

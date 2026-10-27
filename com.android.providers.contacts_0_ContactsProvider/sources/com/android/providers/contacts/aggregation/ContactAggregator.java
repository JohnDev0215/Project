package com.android.providers.contacts.aggregation;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import com.android.providers.contacts.ContactsDatabaseHelper;
import com.android.providers.contacts.ContactsProvider2;
import com.android.providers.contacts.NameSplitter;
import com.android.providers.contacts.PhotoPriorityResolver;
import com.android.providers.contacts.TransactionContext;
import com.android.providers.contacts.aggregation.AbstractContactAggregator;
import com.android.providers.contacts.aggregation.util.CommonNicknameCache;
import com.android.providers.contacts.aggregation.util.ContactMatcher;
import com.android.providers.contacts.aggregation.util.MatchScore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ContactAggregator extends AbstractContactAggregator {
    private final ContactMatcher mMatcher;

    interface AggregateExceptionQuery {
        public static final String[] COLUMNS = {"type", "raw_contact_id1", "raw_contacts1.contact_id", "raw_contacts1.aggregation_needed", "raw_contacts2.contact_id", "raw_contacts2.aggregation_needed"};
    }

    private interface ContactNameLookupQuery {
        public static final String[] COLUMNS = {"contact_id", "normalized_name", "name_type"};
    }

    private interface IdentityLookupMatchQuery {
        public static final String[] COLUMNS = {"contact_id"};
    }

    private interface NameLookupMatchQuery {
        public static final String[] COLUMNS = {"contact_id", "nameA.normalized_name", "nameA.name_type", "nameB.name_type"};
    }

    public ContactAggregator(ContactsProvider2 contactsProvider2, ContactsDatabaseHelper contactsDatabaseHelper, PhotoPriorityResolver photoPriorityResolver, NameSplitter nameSplitter, CommonNicknameCache commonNicknameCache) {
        super(contactsProvider2, contactsDatabaseHelper, photoPriorityResolver, nameSplitter, commonNicknameCache);
        this.mMatcher = new ContactMatcher();
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0140 A[Catch: all -> 0x0262, TRY_ENTER, TryCatch #0 {, blocks: (B:4:0x000b, B:6:0x000f, B:7:0x002d, B:9:0x003c, B:11:0x0043, B:13:0x0059, B:17:0x006b, B:26:0x0091, B:37:0x00d8, B:39:0x00e8, B:41:0x00ec, B:61:0x0140, B:73:0x0168, B:75:0x0173, B:78:0x0180, B:80:0x0195, B:81:0x0198, B:83:0x019c, B:85:0x01b6, B:91:0x01cc, B:93:0x01e0, B:96:0x0206, B:97:0x0213, B:99:0x023b, B:100:0x023e, B:102:0x0242, B:43:0x010c, B:49:0x0127, B:50:0x012a, B:21:0x0079, B:28:0x00ab, B:29:0x00ae, B:31:0x00b4, B:33:0x00c0, B:34:0x00ca), top: B:108:0x000b, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0159 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0168 A[Catch: all -> 0x0262, TryCatch #0 {, blocks: (B:4:0x000b, B:6:0x000f, B:7:0x002d, B:9:0x003c, B:11:0x0043, B:13:0x0059, B:17:0x006b, B:26:0x0091, B:37:0x00d8, B:39:0x00e8, B:41:0x00ec, B:61:0x0140, B:73:0x0168, B:75:0x0173, B:78:0x0180, B:80:0x0195, B:81:0x0198, B:83:0x019c, B:85:0x01b6, B:91:0x01cc, B:93:0x01e0, B:96:0x0206, B:97:0x0213, B:99:0x023b, B:100:0x023e, B:102:0x0242, B:43:0x010c, B:49:0x0127, B:50:0x012a, B:21:0x0079, B:28:0x00ab, B:29:0x00ae, B:31:0x00b4, B:33:0x00c0, B:34:0x00ca), top: B:108:0x000b, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x017c  */
    @Override // com.android.providers.contacts.aggregation.AbstractContactAggregator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    synchronized void aggregateContact(com.android.providers.contacts.TransactionContext r28, android.database.sqlite.SQLiteDatabase r29, long r30, long r32, long r34, com.android.providers.contacts.aggregation.AbstractContactAggregator.MatchCandidateList r36) {
        /*
            Method dump skipped, instruction units count: 613
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.aggregation.ContactAggregator.aggregateContact(com.android.providers.contacts.TransactionContext, android.database.sqlite.SQLiteDatabase, long, long, long, com.android.providers.contacts.aggregation.AbstractContactAggregator$MatchCandidateList):void");
    }

    private void clearSuperPrimarySetting(SQLiteDatabase sQLiteDatabase, long j, long j2) {
        String[] strArr = {String.valueOf(j), String.valueOf(j2)};
        StringBuilder sb = new StringBuilder();
        sb.append(" AND mimetype_id IN (");
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT DISTINCT(a.mimetype_id) FROM (SELECT mimetype_id FROM data WHERE is_super_primary =1 AND raw_contact_id IN (SELECT _id FROM raw_contacts WHERE contact_id=?1)) AS a JOIN  (SELECT mimetype_id FROM data WHERE is_super_primary =1 AND raw_contact_id=?2) AS b ON a.mimetype_id=b.mimetype_id", strArr);
        try {
            cursorRawQuery.moveToPosition(-1);
            int i = 0;
            while (cursorRawQuery.moveToNext()) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(cursorRawQuery.getLong(0));
                i++;
            }
            if (i == 0) {
                return;
            }
            sb.append(')');
            sQLiteDatabase.execSQL("UPDATE data SET is_super_primary=0 WHERE (raw_contact_id IN (SELECT _id FROM raw_contacts WHERE contact_id=?1) OR raw_contact_id=?2)" + sb.toString(), strArr);
        } finally {
            cursorRawQuery.close();
        }
    }

    private int canJoinIntoContact(SQLiteDatabase sQLiteDatabase, long j, Set<Long> set, Set<Long> set2) {
        if (set.isEmpty()) {
            String strValueOf = String.valueOf(j);
            String strJoin = TextUtils.join(",", set2);
            if (DatabaseUtils.longForQuery(sQLiteDatabase, buildIdentityMatchingSql(strValueOf, strJoin, true, true), null) == 0 && DatabaseUtils.longForQuery(sQLiteDatabase, buildIdentityMatchingSql(strValueOf, strJoin, false, true), null) > 0) {
                if (AbstractContactAggregator.VERBOSE_LOGGING) {
                    Log.v("ContactAggregator", "canJoinIntoContact: no duplicates, but has no matching identity and has mis-matching identity on the same namespace between rid=" + strValueOf + " and ridsInOtherAccts=" + strJoin);
                }
                return 0;
            }
            if (AbstractContactAggregator.VERBOSE_LOGGING) {
                Log.v("ContactAggregator", "canJoinIntoContact: can join the first raw contact from the same account without any identity mismatch.");
            }
            return 1;
        }
        if (AbstractContactAggregator.VERBOSE_LOGGING) {
            Log.v("ContactAggregator", "canJoinIntoContact: " + set.size() + " duplicate(s) found");
        }
        ArraySet arraySet = new ArraySet();
        arraySet.add(Long.valueOf(j));
        if (set.size() > 0 && isDataMaching(sQLiteDatabase, arraySet, set)) {
            if (AbstractContactAggregator.VERBOSE_LOGGING) {
                Log.v("ContactAggregator", "canJoinIntoContact: join if there is a data matching found in the same account");
            }
            return 1;
        }
        if (!AbstractContactAggregator.VERBOSE_LOGGING) {
            return -1;
        }
        Log.v("ContactAggregator", "canJoinIntoContact: re-aggregate rid=" + j + " with its best matching contact to connected component");
        return -1;
    }

    private boolean isDataMaching(SQLiteDatabase sQLiteDatabase, Set<Long> set, Set<Long> set2) {
        String strJoin = TextUtils.join(",", set);
        String strJoin2 = TextUtils.join(",", set2);
        if (isFirstColumnGreaterThanZero(sQLiteDatabase, buildIdentityMatchingSql(strJoin, strJoin2, true, true))) {
            if (AbstractContactAggregator.VERBOSE_LOGGING) {
                Log.v("ContactAggregator", "canJoinIntoContact: identity match found between " + strJoin + " and " + strJoin2);
            }
            return true;
        }
        if (isFirstColumnGreaterThanZero(sQLiteDatabase, buildEmailMatchingSql(strJoin, strJoin2, true))) {
            if (AbstractContactAggregator.VERBOSE_LOGGING) {
                Log.v("ContactAggregator", "canJoinIntoContact: email match found between " + strJoin + " and " + strJoin2);
            }
            return true;
        }
        if (!isFirstColumnGreaterThanZero(sQLiteDatabase, buildPhoneMatchingSql(strJoin, strJoin2, true))) {
            return false;
        }
        if (AbstractContactAggregator.VERBOSE_LOGGING) {
            Log.v("ContactAggregator", "canJoinIntoContact: phone match found between " + strJoin + " and " + strJoin2);
        }
        return true;
    }

    private void reAggregateRawContacts(TransactionContext transactionContext, SQLiteDatabase sQLiteDatabase, long j, long j2, long j3, Set<Long> set) {
        ArraySet arraySet = new ArraySet();
        arraySet.add(Long.valueOf(j3));
        arraySet.addAll(set);
        Set<Set<Long>> setFindConnectedRawContacts = findConnectedRawContacts(sQLiteDatabase, arraySet);
        if (setFindConnectedRawContacts.size() == 1) {
            createContactForRawContacts(sQLiteDatabase, transactionContext, setFindConnectedRawContacts.iterator().next(), Long.valueOf(j));
            return;
        }
        Iterator<Set<Long>> it = setFindConnectedRawContacts.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Set<Long> next = it.next();
            if (next.contains(Long.valueOf(j3))) {
                createContactForRawContacts(sQLiteDatabase, transactionContext, next, j2 == 0 ? null : Long.valueOf(j2));
                setFindConnectedRawContacts.remove(next);
            }
        }
        int size = setFindConnectedRawContacts.size();
        for (Set<Long> set2 : setFindConnectedRawContacts) {
            if (size > 1) {
                createContactForRawContacts(sQLiteDatabase, transactionContext, set2, null);
                size--;
            } else {
                createContactForRawContacts(sQLiteDatabase, transactionContext, set2, Long.valueOf(j));
            }
        }
    }

    @Override // com.android.providers.contacts.aggregation.AbstractContactAggregator
    public void updateAggregationAfterVisibilityChange(long j) {
        SQLiteDatabase writableDatabase = this.mDbHelper.getWritableDatabase();
        if (this.mDbHelper.isContactInDefaultDirectory(writableDatabase, j)) {
            markContactForAggregation(writableDatabase, j);
            return;
        }
        this.mSelectionArgs1[0] = String.valueOf(j);
        Cursor cursorQuery = writableDatabase.query("raw_contacts", AbstractContactAggregator.RawContactIdQuery.COLUMNS, "contact_id=?", this.mSelectionArgs1, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                long j2 = cursorQuery.getLong(0);
                this.mMatcher.clear();
                updateMatchScoresBasedOnIdentityMatch(writableDatabase, j2, this.mMatcher);
                updateMatchScoresBasedOnNameMatches(writableDatabase, j2, this.mMatcher);
                Iterator<MatchScore> it = this.mMatcher.pickBestMatches(70).iterator();
                while (it.hasNext()) {
                    markContactForAggregation(writableDatabase, it.next().getContactId());
                }
                this.mMatcher.clear();
                updateMatchScoresBasedOnEmailMatches(writableDatabase, j2, this.mMatcher);
                updateMatchScoresBasedOnPhoneMatches(writableDatabase, j2, this.mMatcher);
                Iterator<MatchScore> it2 = this.mMatcher.pickBestMatches(50).iterator();
                while (it2.hasNext()) {
                    markContactForAggregation(writableDatabase, it2.next().getContactId());
                }
            } finally {
                cursorQuery.close();
            }
        }
    }

    private void setContactIdAndMarkAggregated(long j, long j2) {
        this.mContactIdAndMarkAggregatedUpdate.bindLong(1, j2);
        this.mContactIdAndMarkAggregatedUpdate.bindLong(2, j);
        this.mContactIdAndMarkAggregatedUpdate.execute();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private long pickBestMatchBasedOnExceptions(android.database.sqlite.SQLiteDatabase r11, long r12, com.android.providers.contacts.aggregation.util.ContactMatcher r14) {
        /*
            r10 = this;
            boolean r0 = r10.mAggregationExceptionIdsValid
            if (r0 != 0) goto L7
            r10.prefetchAggregationExceptionIds(r11)
        L7:
            android.util.ArraySet<java.lang.Long> r10 = r10.mAggregationExceptionIds
            java.lang.Long r0 = java.lang.Long.valueOf(r12)
            boolean r10 = r10.contains(r0)
            r0 = -1
            if (r10 != 0) goto L16
            return r0
        L16:
            java.lang.String[] r4 = com.android.providers.contacts.aggregation.ContactAggregator.AggregateExceptionQuery.COLUMNS
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r2 = "raw_contact_id1="
            r10.append(r2)
            r10.append(r12)
            java.lang.String r2 = " OR "
            r10.append(r2)
            java.lang.String r2 = "raw_contact_id2"
            r10.append(r2)
            java.lang.String r2 = "="
            r10.append(r2)
            r10.append(r12)
            java.lang.String r5 = r10.toString()
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r3 = "agg_exceptions JOIN raw_contacts raw_contacts1  ON (agg_exceptions.raw_contact_id1 = raw_contacts1._id)  JOIN raw_contacts raw_contacts2  ON (agg_exceptions.raw_contact_id2 = raw_contacts2._id) "
            r2 = r11
            android.database.Cursor r10 = r2.query(r3, r4, r5, r6, r7, r8, r9)
        L46:
            boolean r11 = r10.moveToNext()     // Catch: java.lang.Throwable -> L99
            r2 = 1
            if (r11 == 0) goto L8f
            r11 = 0
            int r11 = r10.getInt(r11)     // Catch: java.lang.Throwable -> L99
            long r3 = r10.getLong(r2)     // Catch: java.lang.Throwable -> L99
            int r3 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r3 != 0) goto L6d
            r3 = 5
            int r3 = r10.getInt(r3)     // Catch: java.lang.Throwable -> L99
            if (r3 != 0) goto L80
            r3 = 4
            boolean r4 = r10.isNull(r3)     // Catch: java.lang.Throwable -> L99
            if (r4 != 0) goto L80
            long r3 = r10.getLong(r3)     // Catch: java.lang.Throwable -> L99
            goto L81
        L6d:
            r3 = 3
            int r3 = r10.getInt(r3)     // Catch: java.lang.Throwable -> L99
            if (r3 != 0) goto L80
            r3 = 2
            boolean r4 = r10.isNull(r3)     // Catch: java.lang.Throwable -> L99
            if (r4 != 0) goto L80
            long r3 = r10.getLong(r3)     // Catch: java.lang.Throwable -> L99
            goto L81
        L80:
            r3 = r0
        L81:
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 == 0) goto L46
            if (r11 != r2) goto L8b
            r14.keepIn(r3)     // Catch: java.lang.Throwable -> L99
            goto L46
        L8b:
            r14.keepOut(r3)     // Catch: java.lang.Throwable -> L99
            goto L46
        L8f:
            r10.close()
            r10 = 100
            long r10 = r14.pickBestMatch(r10, r2)
            return r10
        L99:
            r11 = move-exception
            r10.close()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.aggregation.ContactAggregator.pickBestMatchBasedOnExceptions(android.database.sqlite.SQLiteDatabase, long, com.android.providers.contacts.aggregation.util.ContactMatcher):long");
    }

    private long pickBestMatchBasedOnData(SQLiteDatabase sQLiteDatabase, long j, AbstractContactAggregator.MatchCandidateList matchCandidateList, ContactMatcher contactMatcher) {
        long jUpdateMatchScoresBasedOnDataMatches = updateMatchScoresBasedOnDataMatches(sQLiteDatabase, j, contactMatcher);
        if (jUpdateMatchScoresBasedOnDataMatches == -2) {
            return -1L;
        }
        if (jUpdateMatchScoresBasedOnDataMatches == -1) {
            jUpdateMatchScoresBasedOnDataMatches = pickBestMatchBasedOnSecondaryData(sQLiteDatabase, j, matchCandidateList, contactMatcher);
            if (jUpdateMatchScoresBasedOnDataMatches == -2) {
                return -1L;
            }
        }
        return jUpdateMatchScoresBasedOnDataMatches;
    }

    private long pickBestMatchBasedOnSecondaryData(SQLiteDatabase sQLiteDatabase, long j, AbstractContactAggregator.MatchCandidateList matchCandidateList, ContactMatcher contactMatcher) {
        List<Long> listPrepareSecondaryMatchCandidates = contactMatcher.prepareSecondaryMatchCandidates(70);
        if (listPrepareSecondaryMatchCandidates == null || listPrepareSecondaryMatchCandidates.size() > 20) {
            return -1L;
        }
        loadNameMatchCandidates(sQLiteDatabase, j, matchCandidateList, true);
        this.mSb.setLength(0);
        StringBuilder sb = this.mSb;
        sb.append("contact_id");
        sb.append(" IN (");
        for (int i = 0; i < listPrepareSecondaryMatchCandidates.size(); i++) {
            if (i != 0) {
                this.mSb.append(',');
            }
            this.mSb.append(listPrepareSecondaryMatchCandidates.get(i));
        }
        this.mSb.append(") AND name_type IN (0,1,2)");
        matchAllCandidates(sQLiteDatabase, this.mSb.toString(), matchCandidateList, contactMatcher, 1, null);
        return contactMatcher.pickBestMatch(50, false);
    }

    private long updateMatchScoresBasedOnDataMatches(SQLiteDatabase sQLiteDatabase, long j, ContactMatcher contactMatcher) {
        updateMatchScoresBasedOnIdentityMatch(sQLiteDatabase, j, contactMatcher);
        updateMatchScoresBasedOnNameMatches(sQLiteDatabase, j, contactMatcher);
        long jPickBestMatch = contactMatcher.pickBestMatch(70, false);
        if (jPickBestMatch != -1) {
            return jPickBestMatch;
        }
        updateMatchScoresBasedOnEmailMatches(sQLiteDatabase, j, contactMatcher);
        updateMatchScoresBasedOnPhoneMatches(sQLiteDatabase, j, contactMatcher);
        return -1L;
    }

    private void updateMatchScoresBasedOnIdentityMatch(SQLiteDatabase sQLiteDatabase, long j, ContactMatcher contactMatcher) {
        this.mSelectionArgs2[0] = String.valueOf(j);
        this.mSelectionArgs2[1] = String.valueOf(this.mMimeTypeIdIdentity);
        Cursor cursorQuery = sQLiteDatabase.query("data dataA JOIN data dataB ON (dataA.data2=dataB.data2 AND dataA.data1=dataB.data1) JOIN raw_contacts ON (dataB.raw_contact_id = raw_contacts._id)", IdentityLookupMatchQuery.COLUMNS, "dataA.raw_contact_id=?1 AND dataA.mimetype_id=?2 AND dataA.data2 NOT NULL AND dataA.data1 NOT NULL AND dataB.mimetype_id=?2 AND aggregation_needed=0 AND contact_id IN default_directory", this.mSelectionArgs2, "contact_id", null, null);
        while (cursorQuery.moveToNext()) {
            try {
                contactMatcher.matchIdentity(cursorQuery.getLong(0));
            } finally {
                cursorQuery.close();
            }
        }
    }

    private void updateMatchScoresBasedOnNameMatches(SQLiteDatabase sQLiteDatabase, long j, ContactMatcher contactMatcher) {
        this.mSelectionArgs1[0] = String.valueOf(j);
        Cursor cursorQuery = sQLiteDatabase.query("name_lookup nameA JOIN name_lookup nameB ON (nameA.normalized_name=nameB.normalized_name) JOIN raw_contacts ON (nameB.raw_contact_id = raw_contacts._id)", NameLookupMatchQuery.COLUMNS, "nameA.raw_contact_id=? AND aggregation_needed=0 AND contact_id IN default_directory", this.mSelectionArgs1, null, null, null, AbstractContactAggregator.PRIMARY_HIT_LIMIT_STRING);
        while (cursorQuery.moveToNext()) {
            try {
                long j2 = cursorQuery.getLong(0);
                String string = cursorQuery.getString(1);
                int i = cursorQuery.getInt(2);
                int i2 = cursorQuery.getInt(3);
                contactMatcher.matchName(j2, i, string, i2, string, 0);
                if (i == 3 && i2 == 3) {
                    contactMatcher.updateScoreWithNicknameMatch(j2);
                }
            } finally {
                cursorQuery.close();
            }
        }
    }

    private void updateMatchScoresBasedOnEmailMatches(SQLiteDatabase sQLiteDatabase, long j, ContactMatcher contactMatcher) {
        this.mSelectionArgs2[0] = String.valueOf(j);
        this.mSelectionArgs2[1] = String.valueOf(this.mMimeTypeIdEmail);
        Cursor cursorQuery = sQLiteDatabase.query("data dataA JOIN data dataB ON dataA.data1= dataB.data1 JOIN raw_contacts ON (dataB.raw_contact_id = raw_contacts._id)", AbstractContactAggregator.EmailLookupQuery.COLUMNS, "dataA.raw_contact_id=?1 AND dataA.mimetype_id=?2 AND dataA.data1 NOT NULL AND dataB.mimetype_id=?2 AND aggregation_needed=0 AND contact_id IN default_directory", this.mSelectionArgs2, null, null, null, AbstractContactAggregator.SECONDARY_HIT_LIMIT_STRING);
        while (cursorQuery.moveToNext()) {
            try {
                contactMatcher.updateScoreWithEmailMatch(cursorQuery.getLong(1));
            } finally {
                cursorQuery.close();
            }
        }
    }

    private void updateMatchScoresBasedOnPhoneMatches(SQLiteDatabase sQLiteDatabase, long j, ContactMatcher contactMatcher) {
        Cursor cursorQuery;
        String useStrictPhoneNumberComparisonParameter = this.mDbHelper.getUseStrictPhoneNumberComparisonParameter();
        if (useStrictPhoneNumberComparisonParameter.equals("1")) {
            this.mSelectionArgs2[0] = String.valueOf(j);
            String[] strArr = this.mSelectionArgs2;
            strArr[1] = useStrictPhoneNumberComparisonParameter;
            cursorQuery = sQLiteDatabase.query("phone_lookup phoneA JOIN data dataA ON (dataA._id=phoneA.data_id) JOIN phone_lookup phoneB ON (phoneA.min_match=phoneB.min_match) JOIN data dataB ON (dataB._id=phoneB.data_id) JOIN raw_contacts ON (dataB.raw_contact_id = raw_contacts._id)", AbstractContactAggregator.PhoneLookupQuery.COLUMNS, "dataA.raw_contact_id=? AND PHONE_NUMBERS_EQUAL(dataA.data1, dataB.data1,?) AND aggregation_needed=0 AND contact_id IN default_directory", strArr, null, null, null, AbstractContactAggregator.SECONDARY_HIT_LIMIT_STRING);
        } else {
            this.mSelectionArgs3[0] = String.valueOf(j);
            String[] strArr2 = this.mSelectionArgs3;
            strArr2[1] = useStrictPhoneNumberComparisonParameter;
            strArr2[2] = this.mDbHelper.getMinMatchParameter();
            cursorQuery = sQLiteDatabase.query("phone_lookup phoneA JOIN data dataA ON (dataA._id=phoneA.data_id) JOIN phone_lookup phoneB ON (phoneA.min_match=phoneB.min_match) JOIN data dataB ON (dataB._id=phoneB.data_id) JOIN raw_contacts ON (dataB.raw_contact_id = raw_contacts._id)", AbstractContactAggregator.PhoneLookupQuery.COLUMNS, "dataA.raw_contact_id=? AND PHONE_NUMBERS_EQUAL(dataA.data1, dataB.data1,?,?) AND aggregation_needed=0 AND contact_id IN default_directory", this.mSelectionArgs3, null, null, null, AbstractContactAggregator.SECONDARY_HIT_LIMIT_STRING);
        }
        Cursor cursor = cursorQuery;
        while (cursor.moveToNext()) {
            try {
                contactMatcher.updateScoreWithPhoneNumberMatch(cursor.getLong(1));
            } finally {
                cursor.close();
            }
        }
    }

    private void lookupApproximateNameMatches(SQLiteDatabase sQLiteDatabase, AbstractContactAggregator.MatchCandidateList matchCandidateList, ContactMatcher contactMatcher) {
        ArraySet arraySet = new ArraySet();
        for (int i = 0; i < matchCandidateList.mCount; i++) {
            AbstractContactAggregator.NameMatchCandidate nameMatchCandidate = matchCandidateList.mList.get(i);
            if (nameMatchCandidate.mName.length() >= 2) {
                String strSubstring = nameMatchCandidate.mName.substring(0, 2);
                if (!arraySet.contains(strSubstring)) {
                    arraySet.add(strSubstring);
                    matchAllCandidates(sQLiteDatabase, "(normalized_name GLOB '" + strSubstring + "*') AND (name_type IN(2,4,3)) AND contact_id IN default_directory", matchCandidateList, contactMatcher, 2, String.valueOf(100));
                }
            }
        }
    }

    private void matchAllCandidates(SQLiteDatabase sQLiteDatabase, String str, AbstractContactAggregator.MatchCandidateList matchCandidateList, ContactMatcher contactMatcher, int i, String str2) {
        Cursor cursorQuery = sQLiteDatabase.query("name_lookup INNER JOIN view_raw_contacts ON (name_lookup.raw_contact_id = view_raw_contacts._id)", ContactNameLookupQuery.COLUMNS, str, null, null, null, null, str2);
        while (cursorQuery.moveToNext()) {
            try {
                Long lValueOf = Long.valueOf(cursorQuery.getLong(0));
                String string = cursorQuery.getString(1);
                int i2 = cursorQuery.getInt(2);
                for (int i3 = 0; i3 < matchCandidateList.mCount; i3++) {
                    AbstractContactAggregator.NameMatchCandidate nameMatchCandidate = matchCandidateList.mList.get(i3);
                    contactMatcher.matchName(lValueOf.longValue(), nameMatchCandidate.mLookupType, nameMatchCandidate.mName, i2, string, i);
                }
            } finally {
                cursorQuery.close();
            }
        }
    }

    @Override // com.android.providers.contacts.aggregation.AbstractContactAggregator
    protected List<MatchScore> findMatchingContacts(SQLiteDatabase sQLiteDatabase, long j, ArrayList<AbstractContactAggregator.AggregationSuggestionParameter> arrayList) {
        AbstractContactAggregator.MatchCandidateList matchCandidateList = new AbstractContactAggregator.MatchCandidateList();
        ContactMatcher contactMatcher = new ContactMatcher();
        contactMatcher.keepOut(j);
        if (arrayList == null || arrayList.size() == 0) {
            Cursor cursorQuery = sQLiteDatabase.query("raw_contacts", AbstractContactAggregator.RawContactIdQuery.COLUMNS, "contact_id=" + j, null, null, null, null);
            while (cursorQuery.moveToNext()) {
                try {
                    updateMatchScoresForSuggestionsBasedOnDataMatches(sQLiteDatabase, cursorQuery.getLong(0), matchCandidateList, contactMatcher);
                } finally {
                    cursorQuery.close();
                }
            }
        } else {
            updateMatchScoresForSuggestionsBasedOnDataMatches(sQLiteDatabase, matchCandidateList, contactMatcher, arrayList);
        }
        return contactMatcher.pickBestMatches(50);
    }

    private void updateMatchScoresForSuggestionsBasedOnDataMatches(SQLiteDatabase sQLiteDatabase, long j, AbstractContactAggregator.MatchCandidateList matchCandidateList, ContactMatcher contactMatcher) {
        updateMatchScoresBasedOnIdentityMatch(sQLiteDatabase, j, contactMatcher);
        updateMatchScoresBasedOnNameMatches(sQLiteDatabase, j, contactMatcher);
        updateMatchScoresBasedOnEmailMatches(sQLiteDatabase, j, contactMatcher);
        updateMatchScoresBasedOnPhoneMatches(sQLiteDatabase, j, contactMatcher);
        loadNameMatchCandidates(sQLiteDatabase, j, matchCandidateList, false);
        lookupApproximateNameMatches(sQLiteDatabase, matchCandidateList, contactMatcher);
    }

    private void updateMatchScoresForSuggestionsBasedOnDataMatches(SQLiteDatabase sQLiteDatabase, AbstractContactAggregator.MatchCandidateList matchCandidateList, ContactMatcher contactMatcher, ArrayList<AbstractContactAggregator.AggregationSuggestionParameter> arrayList) {
        for (AbstractContactAggregator.AggregationSuggestionParameter aggregationSuggestionParameter : arrayList) {
            if ("name".equals(aggregationSuggestionParameter.kind)) {
                updateMatchScoresBasedOnNameMatches(sQLiteDatabase, aggregationSuggestionParameter.value, matchCandidateList, contactMatcher);
            }
        }
    }
}

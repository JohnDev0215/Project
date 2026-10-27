package com.android.providers.contacts.aggregation;

import android.database.Cursor;
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
import com.android.providers.contacts.aggregation.util.ContactAggregatorHelper;
import com.android.providers.contacts.aggregation.util.MatchScore;
import com.android.providers.contacts.aggregation.util.RawContactMatcher;
import com.android.providers.contacts.aggregation.util.RawContactMatchingCandidates;
import com.android.providers.contacts.database.ContactsTableUtil;
import com.google.common.collect.HashMultimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ContactAggregator2 extends AbstractContactAggregator {
    private final RawContactMatcher mMatcher;

    private interface ContactNameLookupQuery {
        public static final String[] COLUMNS = {"_id", "contact_id", "account_id", "normalized_name", "name_type"};
    }

    protected interface EmailLookupQuery {
        public static final String[] COLUMNS = {"raw_contacts._id", "contact_id", "account_id"};
    }

    protected interface IdentityLookupMatchQuery {
        public static final String[] COLUMNS = {"raw_contacts._id", "contact_id", "account_id"};
    }

    protected interface NameLookupMatchQuery {
        public static final String[] COLUMNS = {"_id", "contact_id", "account_id", "nameA.normalized_name", "nameA.name_type", "nameB.name_type"};
    }

    protected interface NullNameRawContactsIdsQuery {
        public static final String[] COLUMNS = {"_id", "contact_id", "account_id", "normalized_name"};
    }

    protected interface PhoneLookupQuery {
        public static final String[] COLUMNS = {"raw_contacts._id", "contact_id", "account_id"};
    }

    public ContactAggregator2(ContactsProvider2 contactsProvider2, ContactsDatabaseHelper contactsDatabaseHelper, PhotoPriorityResolver photoPriorityResolver, NameSplitter nameSplitter, CommonNicknameCache commonNicknameCache) {
        super(contactsProvider2, contactsDatabaseHelper, photoPriorityResolver, nameSplitter, commonNicknameCache);
        this.mMatcher = new RawContactMatcher();
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00ec A[Catch: all -> 0x017c, TryCatch #0 {, blocks: (B:4:0x000b, B:6:0x0011, B:8:0x0015, B:11:0x0032, B:13:0x0036, B:14:0x0054, B:16:0x0063, B:18:0x006a, B:22:0x007c, B:32:0x009e, B:34:0x00b3, B:36:0x00bb, B:38:0x00bf, B:49:0x00ec, B:51:0x00f0, B:52:0x00f7, B:54:0x0102, B:56:0x0106, B:57:0x011c, B:59:0x0130, B:60:0x0133, B:61:0x013b, B:63:0x013f, B:64:0x0167, B:24:0x0084), top: B:70:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0100  */
    @Override // com.android.providers.contacts.aggregation.AbstractContactAggregator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    synchronized void aggregateContact(com.android.providers.contacts.TransactionContext r21, android.database.sqlite.SQLiteDatabase r22, long r23, long r25, long r27, com.android.providers.contacts.aggregation.AbstractContactAggregator.MatchCandidateList r29) {
        /*
            Method dump skipped, instruction units count: 383
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.aggregation.ContactAggregator2.aggregateContact(com.android.providers.contacts.TransactionContext, android.database.sqlite.SQLiteDatabase, long, long, long, com.android.providers.contacts.aggregation.AbstractContactAggregator$MatchCandidateList):void");
    }

    private boolean needAggregate(SQLiteDatabase sQLiteDatabase, long j) {
        this.mSelectionArgs1[0] = String.valueOf(j);
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT _id FROM raw_contacts WHERE aggregation_needed=1 AND _id=?", this.mSelectionArgs1);
        try {
            return cursorRawQuery.getCount() != 0;
        } finally {
            cursorRawQuery.close();
        }
    }

    private RawContactMatchingCandidates findRawContactMatchingCandidates(SQLiteDatabase sQLiteDatabase, long j, AbstractContactAggregator.MatchCandidateList matchCandidateList, RawContactMatcher rawContactMatcher) {
        updateMatchScores(sQLiteDatabase, j, matchCandidateList, rawContactMatcher);
        RawContactMatchingCandidates rawContactMatchingCandidates = new RawContactMatchingCandidates(rawContactMatcher.pickBestMatches());
        ArraySet arraySet = new ArraySet();
        arraySet.addAll(rawContactMatchingCandidates.getRawContactIdSet());
        while (!arraySet.isEmpty() && rawContactMatchingCandidates.getCount() < 50) {
            ArraySet arraySet2 = new ArraySet();
            Iterator it = arraySet.iterator();
            while (it.hasNext()) {
                long jLongValue = ((Long) it.next()).longValue();
                RawContactMatcher rawContactMatcher2 = new RawContactMatcher();
                updateMatchScores(sQLiteDatabase, jLongValue, new AbstractContactAggregator.MatchCandidateList(), rawContactMatcher2);
                for (MatchScore matchScore : rawContactMatcher2.pickBestMatches()) {
                    long rawContactId = matchScore.getRawContactId();
                    if (!rawContactMatchingCandidates.getRawContactIdSet().contains(Long.valueOf(rawContactId))) {
                        arraySet2.add(Long.valueOf(rawContactId));
                        rawContactMatchingCandidates.add(matchScore);
                    }
                }
            }
            arraySet.clear();
            arraySet.addAll((Collection) arraySet2);
        }
        return rawContactMatchingCandidates;
    }

    private void clearSuperPrimarySetting(SQLiteDatabase sQLiteDatabase, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(" AND mimetype_id IN (");
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT mimetype_id, count(1) c  FROM data WHERE is_super_primary = 1 AND raw_contact_id IN (" + str + ") group by mimetype_id HAVING c > 1", null);
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
            sQLiteDatabase.execSQL(("UPDATE data SET is_super_primary=0 WHERE raw_contact_id IN (" + str + ")") + sb.toString());
        } finally {
            cursorRawQuery.close();
        }
    }

    private String buildExceptionMatchingSql(String str, String str2, int i, boolean z) {
        String str3 = " FROM agg_exceptions WHERE raw_contact_id1 IN (" + str + ") AND raw_contact_id2 IN (" + str2 + ") AND type=" + i;
        if (z) {
            return "SELECT count(*) " + str3;
        }
        return "SELECT raw_contact_id1, raw_contact_id2" + str3;
    }

    private void reAggregateRawContacts(TransactionContext transactionContext, SQLiteDatabase sQLiteDatabase, long j, long j2, long j3, long j4, RawContactMatchingCandidates rawContactMatchingCandidates) {
        long jSimpleQueryForLong;
        ArraySet arraySet = new ArraySet();
        arraySet.add(Long.valueOf(j2));
        arraySet.addAll(rawContactMatchingCandidates.getRawContactIdSet());
        Set<Set<Long>> setFindConnectedRawContacts = findConnectedRawContacts(sQLiteDatabase, arraySet);
        Map<Long, Long> rawContactToAccount = rawContactMatchingCandidates.getRawContactToAccount();
        rawContactToAccount.put(Long.valueOf(j2), Long.valueOf(j3));
        ContactAggregatorHelper.mergeComponentsWithDisjointAccounts(setFindConnectedRawContacts, rawContactToAccount);
        breakComponentsByExceptions(sQLiteDatabase, setFindConnectedRawContacts);
        for (Set<Long> set : setFindConnectedRawContacts) {
            Long lValueOf = null;
            ArraySet<Long> arraySet2 = new ArraySet();
            if (set.contains(Long.valueOf(j2))) {
                if ((j != 0 && j4 == 0) || canBeReused(sQLiteDatabase, Long.valueOf(j), set)) {
                    lValueOf = Long.valueOf(j);
                    Iterator<Long> it = set.iterator();
                    while (it.hasNext()) {
                        Long contactId = rawContactMatchingCandidates.getContactId(it.next());
                        if (contactId != null && !contactId.equals(lValueOf)) {
                            arraySet2.add(contactId);
                        }
                    }
                } else if (j != 0) {
                    arraySet2.add(Long.valueOf(j));
                }
            } else {
                boolean z = false;
                Iterator<Long> it2 = set.iterator();
                while (it2.hasNext()) {
                    Long contactId2 = rawContactMatchingCandidates.getContactId(it2.next());
                    if (z || contactId2 == null || !canBeReused(sQLiteDatabase, contactId2, set)) {
                        arraySet2.add(contactId2);
                    } else {
                        z = true;
                        lValueOf = contactId2;
                    }
                }
            }
            String strJoin = TextUtils.join(",", set);
            clearSuperPrimarySetting(sQLiteDatabase, strJoin);
            createContactForRawContacts(sQLiteDatabase, transactionContext, set, lValueOf);
            if (AbstractContactAggregator.VERBOSE_LOGGING) {
                Log.v("ContactAggregator", "Aggregating rids=" + set);
            }
            markAggregated(sQLiteDatabase, strJoin);
            for (Long l : arraySet2) {
                if (l.longValue() != 0) {
                    this.mRawContactCountQuery.bindLong(1, l.longValue());
                    this.mRawContactCountQuery.bindLong(2, 0L);
                    jSimpleQueryForLong = this.mRawContactCountQuery.simpleQueryForLong();
                } else {
                    jSimpleQueryForLong = 0;
                }
                if (jSimpleQueryForLong == 0) {
                    ContactsTableUtil.deleteContact(sQLiteDatabase, l.longValue());
                    this.mAggregatedPresenceDelete.bindLong(1, l.longValue());
                    this.mAggregatedPresenceDelete.execute();
                } else {
                    updateAggregateData(transactionContext, l.longValue());
                }
            }
        }
    }

    private boolean canBeReused(SQLiteDatabase sQLiteDatabase, Long l, Set<Long> set) {
        this.mSelectionArgs1[0] = String.valueOf(l);
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT raw_contacts._id FROM raw_contacts WHERE contact_id=? AND deleted=0", this.mSelectionArgs1);
        try {
            cursorRawQuery.moveToPosition(-1);
            while (cursorRawQuery.moveToNext()) {
                if (!set.contains(Long.valueOf(cursorRawQuery.getLong(0)))) {
                    return false;
                }
            }
            cursorRawQuery.close();
            return true;
        } finally {
            cursorRawQuery.close();
        }
    }

    private void breakComponentsByExceptions(SQLiteDatabase sQLiteDatabase, Set<Set<Long>> set) {
        for (Set set2 : new ArraySet(set)) {
            String strJoin = TextUtils.join(",", set2);
            if (isFirstColumnGreaterThanZero(sQLiteDatabase, buildExceptionMatchingSql(strJoin, strJoin, 2, true))) {
                HashMultimap hashMultimapCreate = HashMultimap.create();
                findIdPairs(sQLiteDatabase, buildExceptionMatchingSql(strJoin, strJoin), hashMultimapCreate);
                set.remove(set2);
                set.addAll(ContactAggregatorHelper.findConnectedComponents(set2, hashMultimapCreate));
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

    /* JADX WARN: Removed duplicated region for block: B:19:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateMatchScoresBasedOnExceptions(android.database.sqlite.SQLiteDatabase r11, long r12, com.android.providers.contacts.aggregation.util.RawContactMatcher r14) {
        /*
            r10 = this;
            boolean r0 = r10.mAggregationExceptionIdsValid
            if (r0 != 0) goto L7
            r10.prefetchAggregationExceptionIds(r11)
        L7:
            android.util.ArraySet<java.lang.Long> r10 = r10.mAggregationExceptionIds
            java.lang.Long r0 = java.lang.Long.valueOf(r12)
            boolean r10 = r10.contains(r0)
            if (r10 != 0) goto L14
            return
        L14:
            java.lang.String[] r2 = com.android.providers.contacts.aggregation.AbstractContactAggregator.AggregateExceptionQuery.COLUMNS
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "raw_contact_id1="
            r10.append(r0)
            r10.append(r12)
            java.lang.String r0 = " OR "
            r10.append(r0)
            java.lang.String r0 = "raw_contact_id2"
            r10.append(r0)
            java.lang.String r0 = "="
            r10.append(r0)
            r10.append(r12)
            java.lang.String r3 = r10.toString()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.String r1 = "agg_exceptions JOIN raw_contacts raw_contacts1  ON (agg_exceptions.raw_contact_id1 = raw_contacts1._id)  JOIN raw_contacts raw_contacts2  ON (agg_exceptions.raw_contact_id2 = raw_contacts2._id) "
            r0 = r11
            android.database.Cursor r10 = r0.query(r1, r2, r3, r4, r5, r6, r7)
        L44:
            boolean r11 = r10.moveToNext()     // Catch: java.lang.Throwable -> La2
            if (r11 == 0) goto L9e
            r11 = 0
            int r11 = r10.getInt(r11)     // Catch: java.lang.Throwable -> La2
            r0 = 1
            long r1 = r10.getLong(r0)     // Catch: java.lang.Throwable -> La2
            int r1 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            r2 = -1
            if (r1 != 0) goto L70
            r1 = 5
            boolean r4 = r10.isNull(r1)     // Catch: java.lang.Throwable -> La2
            if (r4 != 0) goto L85
            long r4 = r10.getLong(r1)     // Catch: java.lang.Throwable -> La2
            r1 = 6
            long r6 = r10.getLong(r1)     // Catch: java.lang.Throwable -> La2
            r1 = 7
            long r8 = r10.getLong(r1)     // Catch: java.lang.Throwable -> La2
            goto L88
        L70:
            boolean r1 = r10.isNull(r0)     // Catch: java.lang.Throwable -> La2
            if (r1 != 0) goto L85
            long r4 = r10.getLong(r0)     // Catch: java.lang.Throwable -> La2
            r1 = 2
            long r6 = r10.getLong(r1)     // Catch: java.lang.Throwable -> La2
            r1 = 3
            long r8 = r10.getLong(r1)     // Catch: java.lang.Throwable -> La2
            goto L88
        L85:
            r4 = r2
            r6 = r4
            r8 = r6
        L88:
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 == 0) goto L44
            if (r11 != r0) goto L96
            r0 = r14
            r1 = r4
            r3 = r6
            r5 = r8
            r0.keepIn(r1, r3, r5)     // Catch: java.lang.Throwable -> La2
            goto L44
        L96:
            r0 = r14
            r1 = r4
            r3 = r6
            r5 = r8
            r0.keepOut(r1, r3, r5)     // Catch: java.lang.Throwable -> La2
            goto L44
        L9e:
            r10.close()
            return
        La2:
            r11 = move-exception
            r10.close()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.aggregation.ContactAggregator2.updateMatchScoresBasedOnExceptions(android.database.sqlite.SQLiteDatabase, long, com.android.providers.contacts.aggregation.util.RawContactMatcher):void");
    }

    private void updateMatchScoresBasedOnIdentityMatch(SQLiteDatabase sQLiteDatabase, long j, RawContactMatcher rawContactMatcher) {
        this.mSelectionArgs2[0] = String.valueOf(j);
        this.mSelectionArgs2[1] = String.valueOf(this.mMimeTypeIdIdentity);
        Cursor cursorQuery = sQLiteDatabase.query("data dataA JOIN data dataB ON (dataA.data2=dataB.data2 AND dataA.data1=dataB.data1) JOIN raw_contacts ON (dataB.raw_contact_id = raw_contacts._id)", IdentityLookupMatchQuery.COLUMNS, "dataA.raw_contact_id=?1 AND dataA.mimetype_id=?2 AND dataA.data2 NOT NULL AND dataA.data1 NOT NULL AND dataB.mimetype_id=?2 AND contact_id IN default_directory", this.mSelectionArgs2, "contact_id", null, null);
        while (cursorQuery.moveToNext()) {
            try {
                long j2 = cursorQuery.getLong(0);
                if (j2 != j) {
                    rawContactMatcher.matchIdentity(j2, cursorQuery.getLong(1), cursorQuery.getLong(2));
                }
            } finally {
                cursorQuery.close();
            }
        }
    }

    private void updateMatchScoresBasedOnNameMatches(SQLiteDatabase sQLiteDatabase, long j, RawContactMatcher rawContactMatcher) {
        int i = 0;
        this.mSelectionArgs1[0] = String.valueOf(j);
        Cursor cursorQuery = sQLiteDatabase.query("name_lookup nameA JOIN name_lookup nameB ON (nameA.normalized_name=nameB.normalized_name) JOIN raw_contacts ON (nameB.raw_contact_id = raw_contacts._id)", NameLookupMatchQuery.COLUMNS, "nameA.raw_contact_id=? AND contact_id IN default_directory", this.mSelectionArgs1, null, null, null, AbstractContactAggregator.PRIMARY_HIT_LIMIT_STRING);
        while (cursorQuery.moveToNext()) {
            try {
                long j2 = cursorQuery.getLong(i);
                if (j2 != j) {
                    long j3 = cursorQuery.getLong(1);
                    long j4 = cursorQuery.getLong(2);
                    String string = cursorQuery.getString(3);
                    int i2 = cursorQuery.getInt(4);
                    int i3 = cursorQuery.getInt(5);
                    rawContactMatcher.matchName(j2, j3, j4, i2, string, i3, string, 0);
                    if (i2 == 3 && i3 == 3) {
                        rawContactMatcher.updateScoreWithNicknameMatch(j2, j3, j4);
                    }
                    i = 0;
                }
            } finally {
                cursorQuery.close();
            }
        }
    }

    private void updateMatchScoresBasedOnEmailMatches(SQLiteDatabase sQLiteDatabase, long j, RawContactMatcher rawContactMatcher) {
        this.mSelectionArgs2[0] = String.valueOf(j);
        this.mSelectionArgs2[1] = String.valueOf(this.mMimeTypeIdEmail);
        Cursor cursorQuery = sQLiteDatabase.query("data dataA JOIN data dataB ON dataA.data1= dataB.data1 JOIN raw_contacts ON (dataB.raw_contact_id = raw_contacts._id)", EmailLookupQuery.COLUMNS, "dataA.raw_contact_id=?1 AND dataA.mimetype_id=?2 AND dataA.data1 NOT NULL AND dataB.mimetype_id=?2 AND contact_id IN default_directory", this.mSelectionArgs2, null, null, null, AbstractContactAggregator.SECONDARY_HIT_LIMIT_STRING);
        while (cursorQuery.moveToNext()) {
            try {
                long j2 = cursorQuery.getLong(0);
                if (j2 != j) {
                    rawContactMatcher.updateScoreWithEmailMatch(j2, cursorQuery.getLong(1), cursorQuery.getLong(2));
                }
            } finally {
                cursorQuery.close();
            }
        }
    }

    private void updateMatchScoresBasedOnNameMatches(SQLiteDatabase sQLiteDatabase, String str, AbstractContactAggregator.MatchCandidateList matchCandidateList, RawContactMatcher rawContactMatcher) {
        matchCandidateList.clear();
        AbstractContactAggregator.NameLookupSelectionBuilder nameLookupSelectionBuilder = new AbstractContactAggregator.NameLookupSelectionBuilder(this, this.mNameSplitter, matchCandidateList);
        nameLookupSelectionBuilder.insertNameLookup(0L, 0L, str, 0);
        if (nameLookupSelectionBuilder.isEmpty()) {
            return;
        }
        Cursor cursorQuery = sQLiteDatabase.query("name_lookup JOIN raw_contacts ON (raw_contact_id = raw_contacts._id)", AbstractContactAggregator.NameLookupMatchQueryWithParameter.COLUMNS, nameLookupSelectionBuilder.getSelection(), null, null, null, null, AbstractContactAggregator.PRIMARY_HIT_LIMIT_STRING);
        while (cursorQuery.moveToNext()) {
            try {
                long j = cursorQuery.getLong(0);
                long j2 = cursorQuery.getLong(1);
                long j3 = cursorQuery.getLong(2);
                String string = cursorQuery.getString(3);
                int lookupType = nameLookupSelectionBuilder.getLookupType(string);
                int i = cursorQuery.getInt(4);
                rawContactMatcher.matchName(j, j2, j3, lookupType, string, i, string, 0);
                if (lookupType == 3 && i == 3) {
                    rawContactMatcher.updateScoreWithNicknameMatch(j, j2, j3);
                }
            } finally {
                cursorQuery.close();
            }
        }
    }

    private void updateMatchScoresBasedOnPhoneMatches(SQLiteDatabase sQLiteDatabase, long j, RawContactMatcher rawContactMatcher) {
        Cursor cursorQuery;
        String useStrictPhoneNumberComparisonParameter = this.mDbHelper.getUseStrictPhoneNumberComparisonParameter();
        if (useStrictPhoneNumberComparisonParameter.equals("1")) {
            this.mSelectionArgs2[0] = String.valueOf(j);
            String[] strArr = this.mSelectionArgs2;
            strArr[1] = useStrictPhoneNumberComparisonParameter;
            cursorQuery = sQLiteDatabase.query("phone_lookup phoneA JOIN data dataA ON (dataA._id=phoneA.data_id) JOIN phone_lookup phoneB ON (phoneA.min_match=phoneB.min_match) JOIN data dataB ON (dataB._id=phoneB.data_id) JOIN raw_contacts ON (dataB.raw_contact_id = raw_contacts._id)", PhoneLookupQuery.COLUMNS, "dataA.raw_contact_id=? AND PHONE_NUMBERS_EQUAL(dataA.data1, dataB.data1,?) AND contact_id IN default_directory", strArr, null, null, null, AbstractContactAggregator.SECONDARY_HIT_LIMIT_STRING);
        } else {
            this.mSelectionArgs3[0] = String.valueOf(j);
            String[] strArr2 = this.mSelectionArgs3;
            strArr2[1] = useStrictPhoneNumberComparisonParameter;
            strArr2[2] = this.mDbHelper.getMinMatchParameter();
            cursorQuery = sQLiteDatabase.query("phone_lookup phoneA JOIN data dataA ON (dataA._id=phoneA.data_id) JOIN phone_lookup phoneB ON (phoneA.min_match=phoneB.min_match) JOIN data dataB ON (dataB._id=phoneB.data_id) JOIN raw_contacts ON (dataB.raw_contact_id = raw_contacts._id)", PhoneLookupQuery.COLUMNS, "dataA.raw_contact_id=? AND PHONE_NUMBERS_EQUAL(dataA.data1, dataB.data1,?,?) AND contact_id IN default_directory", this.mSelectionArgs3, null, null, null, AbstractContactAggregator.SECONDARY_HIT_LIMIT_STRING);
        }
        Cursor cursor = cursorQuery;
        while (cursor.moveToNext()) {
            try {
                long j2 = cursor.getLong(0);
                if (j2 != j) {
                    rawContactMatcher.updateScoreWithPhoneNumberMatch(j2, cursor.getLong(1), cursor.getLong(2));
                }
            } finally {
                cursor.close();
            }
        }
    }

    private void lookupApproximateNameMatches(SQLiteDatabase sQLiteDatabase, AbstractContactAggregator.MatchCandidateList matchCandidateList, RawContactMatcher rawContactMatcher) {
        ArraySet arraySet = new ArraySet();
        for (int i = 0; i < matchCandidateList.mCount; i++) {
            AbstractContactAggregator.NameMatchCandidate nameMatchCandidate = matchCandidateList.mList.get(i);
            if (nameMatchCandidate.mName.length() >= 2) {
                String strSubstring = nameMatchCandidate.mName.substring(0, 2);
                if (!arraySet.contains(strSubstring)) {
                    arraySet.add(strSubstring);
                    matchAllCandidates(sQLiteDatabase, "(normalized_name GLOB '" + strSubstring + "*') AND (name_type IN(2,4,3)) AND contact_id IN default_directory", matchCandidateList, rawContactMatcher, 2, String.valueOf(100));
                }
            }
        }
    }

    private void matchAllCandidates(SQLiteDatabase sQLiteDatabase, String str, AbstractContactAggregator.MatchCandidateList matchCandidateList, RawContactMatcher rawContactMatcher, int i, String str2) {
        Cursor cursorQuery = sQLiteDatabase.query("name_lookup INNER JOIN view_raw_contacts ON (name_lookup.raw_contact_id = view_raw_contacts._id)", ContactNameLookupQuery.COLUMNS, str, null, null, null, null, str2);
        while (cursorQuery.moveToNext()) {
            try {
                Long lValueOf = Long.valueOf(cursorQuery.getLong(0));
                Long lValueOf2 = Long.valueOf(cursorQuery.getLong(1));
                Long lValueOf3 = Long.valueOf(cursorQuery.getLong(2));
                String string = cursorQuery.getString(3);
                int i2 = cursorQuery.getInt(4);
                for (int i3 = 0; i3 < matchCandidateList.mCount; i3++) {
                    AbstractContactAggregator.NameMatchCandidate nameMatchCandidate = matchCandidateList.mList.get(i3);
                    rawContactMatcher.matchName(lValueOf.longValue(), lValueOf2.longValue(), lValueOf3.longValue(), nameMatchCandidate.mLookupType, nameMatchCandidate.mName, i2, string, i);
                }
            } finally {
                cursorQuery.close();
            }
        }
    }

    @Override // com.android.providers.contacts.aggregation.AbstractContactAggregator
    protected List<MatchScore> findMatchingContacts(SQLiteDatabase sQLiteDatabase, long j, ArrayList<AbstractContactAggregator.AggregationSuggestionParameter> arrayList) throws Throwable {
        Cursor cursor;
        long j2;
        AbstractContactAggregator.MatchCandidateList matchCandidateList = new AbstractContactAggregator.MatchCandidateList();
        RawContactMatcher rawContactMatcher = new RawContactMatcher();
        if (arrayList == null || arrayList.size() == 0) {
            Cursor cursorQuery = sQLiteDatabase.query("raw_contacts", AbstractContactAggregator.RawContactIdQuery.COLUMNS, "contact_id=" + j, null, null, null, null);
            while (cursorQuery.moveToNext()) {
                try {
                    j2 = cursorQuery.getLong(0);
                    rawContactMatcher.keepOut(j2, j, cursorQuery.getLong(1));
                    cursor = cursorQuery;
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                }
                try {
                    updateMatchScoresForSuggestionsBasedOnDataMatches(sQLiteDatabase, j2, matchCandidateList, rawContactMatcher);
                    cursorQuery = cursor;
                } catch (Throwable th2) {
                    th = th2;
                    cursor.close();
                    throw th;
                }
            }
            cursorQuery.close();
        } else {
            updateMatchScoresForSuggestionsBasedOnDataMatches(sQLiteDatabase, matchCandidateList, rawContactMatcher, arrayList);
        }
        return rawContactMatcher.pickBestMatches(50);
    }

    private void updateMatchScoresForSuggestionsBasedOnDataMatches(SQLiteDatabase sQLiteDatabase, long j, AbstractContactAggregator.MatchCandidateList matchCandidateList, RawContactMatcher rawContactMatcher) {
        updateMatchScoresBasedOnIdentityMatch(sQLiteDatabase, j, rawContactMatcher);
        updateMatchScoresBasedOnNameMatches(sQLiteDatabase, j, rawContactMatcher);
        updateMatchScoresBasedOnEmailMatches(sQLiteDatabase, j, rawContactMatcher);
        updateMatchScoresBasedOnPhoneMatches(sQLiteDatabase, j, rawContactMatcher);
        loadNameMatchCandidates(sQLiteDatabase, j, matchCandidateList, false);
        lookupApproximateNameMatches(sQLiteDatabase, matchCandidateList, rawContactMatcher);
    }

    private void updateMatchScores(SQLiteDatabase sQLiteDatabase, long j, AbstractContactAggregator.MatchCandidateList matchCandidateList, RawContactMatcher rawContactMatcher) {
        updateMatchScoresBasedOnExceptions(sQLiteDatabase, j, rawContactMatcher);
        updateMatchScoresBasedOnNameMatches(sQLiteDatabase, j, rawContactMatcher);
        if (rawContactWithoutName(sQLiteDatabase, j)) {
            updateMatchScoresBasedOnIdentityMatch(sQLiteDatabase, j, rawContactMatcher);
            updateMatchScoresBasedOnEmailMatches(sQLiteDatabase, j, rawContactMatcher);
            updateMatchScoresBasedOnPhoneMatches(sQLiteDatabase, j, rawContactMatcher);
            List<Long> listPrepareSecondaryMatchCandidates = rawContactMatcher.prepareSecondaryMatchCandidates();
            if (listPrepareSecondaryMatchCandidates == null || listPrepareSecondaryMatchCandidates.size() > 20) {
                return;
            }
            updateScoreForCandidatesWithoutName(sQLiteDatabase, listPrepareSecondaryMatchCandidates, rawContactMatcher);
        }
    }

    private void updateMatchScoresForSuggestionsBasedOnDataMatches(SQLiteDatabase sQLiteDatabase, AbstractContactAggregator.MatchCandidateList matchCandidateList, RawContactMatcher rawContactMatcher, ArrayList<AbstractContactAggregator.AggregationSuggestionParameter> arrayList) {
        for (AbstractContactAggregator.AggregationSuggestionParameter aggregationSuggestionParameter : arrayList) {
            if ("name".equals(aggregationSuggestionParameter.kind)) {
                updateMatchScoresBasedOnNameMatches(sQLiteDatabase, aggregationSuggestionParameter.value, matchCandidateList, rawContactMatcher);
            }
        }
    }

    private boolean rawContactWithoutName(SQLiteDatabase sQLiteDatabase, long j) {
        Cursor cursorQuery = sQLiteDatabase.query("raw_contacts LEFT OUTER JOIN name_lookup ON _id = raw_contact_id AND name_type = 0", NullNameRawContactsIdsQuery.COLUMNS, "_id =" + j, null, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                return TextUtils.isEmpty(cursorQuery.getString(3));
            }
            cursorQuery.close();
            return false;
        } finally {
            cursorQuery.close();
        }
    }

    private void updateScoreForCandidatesWithoutName(SQLiteDatabase sQLiteDatabase, List<Long> list, RawContactMatcher rawContactMatcher) {
        this.mSb.setLength(0);
        StringBuilder sb = this.mSb;
        sb.append("_id");
        sb.append(" IN (");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                this.mSb.append(",");
            }
            this.mSb.append(list.get(i));
        }
        this.mSb.append(")");
        Cursor cursorQuery = sQLiteDatabase.query("raw_contacts LEFT OUTER JOIN name_lookup ON _id = raw_contact_id AND name_type = 0", NullNameRawContactsIdsQuery.COLUMNS, this.mSb.toString(), null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                Long lValueOf = Long.valueOf(cursorQuery.getLong(0));
                Long lValueOf2 = Long.valueOf(cursorQuery.getLong(1));
                Long lValueOf3 = Long.valueOf(cursorQuery.getLong(2));
                if (TextUtils.isEmpty(cursorQuery.getString(3))) {
                    rawContactMatcher.matchNoName(lValueOf, lValueOf2, lValueOf3);
                }
            } finally {
                cursorQuery.close();
            }
        }
    }
}

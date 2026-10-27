package com.android.inputmethod.latin;

/* JADX INFO: loaded from: classes2.dex */
public class ContactsDictionaryConstants {
    public static final int FREQUENCY_FOR_CONTACTS = 40;
    public static final int FREQUENCY_FOR_CONTACTS_BIGRAM = 90;
    public static final int IN_VISIBLE_GROUP_INDEX = 4;
    public static final int LAST_TIME_CONTACTED_INDEX = 3;
    public static final int MAX_CONTACTS_PROVIDER_QUERY_LIMIT = 10000;
    public static final int NAME_INDEX = 1;
    public static final String[] PROJECTION = {"_id", "display_name", "times_contacted", "last_time_contacted", "in_visible_group"};
    public static final String[] PROJECTION_ID_ONLY = {"_id"};
    public static final int TIMES_CONTACTED_INDEX = 2;
}

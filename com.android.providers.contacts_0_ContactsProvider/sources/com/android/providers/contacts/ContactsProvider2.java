package com.android.providers.contacts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.OnAccountsUpdateListener;
import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.database.AbstractCursor;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.SystemClock;
import android.os.UserHandle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.sysprop.ContactsProperties;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import com.android.common.content.ProjectionMap;
import com.android.common.io.MoreCloseables;
import com.android.internal.util.ArrayUtils;
import com.android.providers.contacts.ContactLookupKey;
import com.android.providers.contacts.ContactsDatabaseHelper;
import com.android.providers.contacts.DataRowHandler;
import com.android.providers.contacts.MetadataEntryParser;
import com.android.providers.contacts.PhotoStore;
import com.android.providers.contacts.SearchIndexManager;
import com.android.providers.contacts.aggregation.AbstractContactAggregator;
import com.android.providers.contacts.aggregation.ContactAggregator;
import com.android.providers.contacts.aggregation.ContactAggregator2;
import com.android.providers.contacts.aggregation.ProfileAggregator;
import com.android.providers.contacts.aggregation.util.CommonNicknameCache;
import com.android.providers.contacts.database.ContactsTableUtil;
import com.android.providers.contacts.database.DeletedContactsTableUtil;
import com.android.providers.contacts.database.MoreDatabaseUtils;
import com.android.providers.contacts.enterprise.EnterpriseContactsCursorWrapper;
import com.android.providers.contacts.enterprise.EnterprisePolicyGuard;
import com.android.providers.contacts.util.Clock;
import com.android.providers.contacts.util.ContactsPermissions;
import com.android.providers.contacts.util.DbQueryUtils;
import com.android.providers.contacts.util.UserUtils;
import com.android.vcard.VCardComposer;
import com.android.vcard.VCardConfig;
import com.google.android.collect.Lists;
import com.google.android.collect.Maps;
import com.google.android.collect.Sets;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes.dex */
public class ContactsProvider2 extends AbstractContactsProvider implements OnAccountsUpdateListener {
    private static final String[] DISTINCT_DATA_PROHIBITING_COLUMNS;
    private static final String[] EMPTY_STRING_ARRAY;
    private static final Map<Integer, String> INSERT_URI_ID_VALUE_MAP;
    private static final Set<String> MODIFIED_KEY_SET_FOR_ENTERPRISE_FILTER;
    private static final String[] PROJECTION_GROUP_ID;
    private static int PROPERTY_AGGREGATION_ALGORITHM_VERSION;
    private static final ProjectionMap sAggregationExceptionsProjectionMap;
    private static final ProjectionMap sContactPresenceColumns;
    private static final ProjectionMap sContactsColumns;
    private static final ProjectionMap sContactsPresenceColumns;
    private static final ProjectionMap sContactsProjectionMap;
    private static final ProjectionMap sContactsProjectionWithSnippetMap;
    private static final ProjectionMap sContactsVCardProjectionMap;
    private static final ProjectionMap sCountProjectionMap;
    private static final ProjectionMap sDataColumns;
    private static final ProjectionMap sDataPresenceColumns;
    private static final ProjectionMap sDataProjectionMap;
    private static final ProjectionMap sDataSipLookupProjectionMap;
    private static final ProjectionMap sDataUsageColumns;
    private static final ProjectionMap sDeletedContactsProjectionMap;
    private static final ProjectionMap sDirectoryProjectionMap;
    private static final ProjectionMap sDistinctDataProjectionMap;
    private static final ProjectionMap sDistinctDataSipLookupProjectionMap;
    private static final ProjectionMap sEntityProjectionMap;
    private static final ProjectionMap sGroupsProjectionMap;
    private static final ProjectionMap sGroupsSummaryProjectionMap;
    private static final ProjectionMap sPhoneLookupProjectionMap;
    private static final ProjectionMap sRawContactColumns;
    private static final ProjectionMap sRawContactSyncColumns;
    private static final ProjectionMap sRawContactsProjectionMap;
    private static final ProjectionMap sRawEntityProjectionMap;
    private static final ProjectionMap sSettingsProjectionMap;
    private static final ProjectionMap sSipLookupColumns;
    private static final ProjectionMap sSnippetColumns;
    private static final ProjectionMap sStatusUpdatesProjectionMap;
    private static final ProjectionMap sStreamItemPhotosProjectionMap;
    private static final ProjectionMap sStreamItemsProjectionMap;
    private static final ProjectionMap sStrequentFrequentProjectionMap;
    private static final ProjectionMap sStrequentPhoneOnlyProjectionMap;
    private static final ProjectionMap sStrequentStarredProjectionMap;
    public static final ProfileAwareUriMatcher sUriMatcher = new ProfileAwareUriMatcher(-1);
    private Account mAccount;
    private boolean mAccountUpdateListenerRegistered;
    private CommonNicknameCache mCommonNicknameCache;
    private AbstractContactAggregator mContactAggregator;
    private ContactDirectoryManager mContactDirectoryManager;
    private int mContactsAccountCount;
    private ContactsDatabaseHelper mContactsHelper;
    private PhotoStore mContactsPhotoStore;
    private LocaleSet mCurrentLocales;
    private ArrayMap<String, DataRowHandler> mDataRowHandlers;
    private EnterprisePolicyGuard mEnterprisePolicyGuard;
    private FastScrollingIndexCache mFastScrollingIndexCache;
    private int mFastScrollingIndexCacheMissCount;
    private int mFastScrollingIndexCacheRequestCount;
    private GlobalSearchSupport mGlobalSearchSupport;
    private boolean mIsPhone;
    private boolean mIsPhoneInitialized;
    private LegacyApiSupport mLegacyApiSupport;
    private NameLookupBuilder mNameLookupBuilder;
    private NameSplitter mNameSplitter;
    private PostalSplitter mPostalSplitter;
    private long mPreAuthorizedUriDuration;
    private AbstractContactAggregator mProfileAggregator;
    private ArrayMap<String, DataRowHandler> mProfileDataRowHandlers;
    private ProfileDatabaseHelper mProfileHelper;
    private PhotoStore mProfilePhotoStore;
    private ProfileProvider mProfileProvider;
    private boolean mProviderStatusUpdateNeeded;
    private volatile CountDownLatch mReadAccessLatch;
    private SearchIndexManager mSearchIndexManager;
    private boolean mSyncToMetadataNetWork;
    private boolean mSyncToNetwork;
    private ContactsTaskScheduler mTaskScheduler;
    private long mTotalTimeFastScrollingIndexGenerate;
    private volatile CountDownLatch mWriteAccessLatch;
    private final StringBuilder mSb = new StringBuilder();
    private final String[] mSelectionArgs1 = new String[1];
    private final String[] mSelectionArgs2 = new String[2];
    private final ArrayList<String> mSelectionArgs = Lists.newArrayList();
    private final ThreadLocal<ContactsTransaction> mTransactionHolder = new ThreadLocal<>();
    private final ThreadLocal<Boolean> mInProfileMode = new ThreadLocal<>();
    private final ThreadLocal<ContactsDatabaseHelper> mDbHelper = new ThreadLocal<>();
    private final ThreadLocal<AbstractContactAggregator> mAggregator = new ThreadLocal<>();
    private final ThreadLocal<PhotoStore> mPhotoStore = new ThreadLocal<>();
    private final TransactionContext mContactTransactionContext = new TransactionContext(false);
    private final TransactionContext mProfileTransactionContext = new TransactionContext(true);
    private final ThreadLocal<TransactionContext> mTransactionContext = new ThreadLocal<>();
    private final SecureRandom mRandom = new SecureRandom();
    private final ArrayMap<String, Boolean> mAccountWritability = new ArrayMap<>();
    private ArrayMap<String, DirectoryInfo> mDirectoryCache = new ArrayMap<>();
    private boolean mDirectoryCacheValid = false;
    private ArrayMap<String, ArrayList<GroupIdCacheEntry>> mGroupIdCache = new ArrayMap<>();
    private int mProviderStatus = 0;
    private boolean mOkToOpenAccess = true;
    private boolean mVisibleTouched = false;
    private long mLastPhotoCleanup = 0;

    private static final class AddressBookIndexQuery {
        public static final String[] COLUMNS = {"name", "bucket", "label", "count"};
    }

    interface AggregationExceptionQuery {
        public static final String[] COLUMNS = {"raw_contact_id1", "raw_contact_id2"};
    }

    private interface DataContactsQuery {
        public static final String[] PROJECTION = {"raw_contacts._id", "accounts.account_type", "accounts.account_name", "accounts.data_set", "data._id", "contacts._id"};
    }

    interface DataHashQuery {
        public static final String[] COLUMNS = {"_id"};
    }

    private static final class DirectoryQuery {
        public static final String[] COLUMNS = {"_id", "authority", "accountName", "accountType"};
    }

    private interface GroupAccountQuery {
        public static final String[] COLUMNS = {"_id", "account_type", "account_name", "data_set"};
    }

    public static class GroupIdCacheEntry {
        long accountId;
        long groupId;
    }

    private interface LookupByDisplayNameQuery {
        public static final String[] COLUMNS = {"contact_id", "account_type_and_data_set", "account_name", "normalized_name"};
    }

    private interface LookupByRawContactIdQuery {
        public static final String[] COLUMNS = {"contact_id", "account_type_and_data_set", "account_name", "_id"};
    }

    private interface LookupBySourceIdQuery {
        public static final String[] COLUMNS = {"contact_id", "account_type_and_data_set", "account_name", "sourceid"};
    }

    interface RawContactsBackupQuery {
        public static final String[] COLUMNS = {"_id"};
    }

    interface RawContactsQuery {
        public static final String[] COLUMNS = {"deleted", "account_id", "accounts.account_type", "accounts.account_name", "accounts.data_set"};
    }

    void setMetadataSyncForTest(boolean z) {
    }

    protected boolean shouldThrowExceptionForInitializationError() {
        return false;
    }

    public String toString() {
        return "ContactsProvider2";
    }

    static {
        HashMap mapNewHashMap = Maps.newHashMap();
        INSERT_URI_ID_VALUE_MAP = mapNewHashMap;
        mapNewHashMap.put(3000, "raw_contact_id");
        INSERT_URI_ID_VALUE_MAP.put(2004, "raw_contact_id");
        INSERT_URI_ID_VALUE_MAP.put(7000, "presence_data_id");
        INSERT_URI_ID_VALUE_MAP.put(21000, "raw_contact_id");
        INSERT_URI_ID_VALUE_MAP.put(2007, "raw_contact_id");
        INSERT_URI_ID_VALUE_MAP.put(21001, "stream_item_id");
        INSERT_URI_ID_VALUE_MAP.put(21003, "stream_item_id");
        Lists.newArrayList(new Integer[]{1022, 1023, 1024, 2007, 2008, 21000, 21001, 21002, 21003, 21004});
        PROJECTION_GROUP_ID = new String[]{"groups._id"};
        DISTINCT_DATA_PROHIBITING_COLUMNS = new String[]{"_id", "raw_contact_id", "name_raw_contact_id", "account_name", "account_type", "data_set", "account_type_and_data_set", "dirty", "sourceid", "version"};
        ProjectionMap.Builder builder = ProjectionMap.builder();
        builder.add("custom_ringtone");
        builder.add("display_name");
        builder.add("display_name_alt");
        builder.add("display_name_source");
        builder.add("in_default_directory");
        builder.add("in_visible_group");
        builder.add("last_time_contacted", "0");
        builder.add("lookup");
        builder.add("phonetic_name");
        builder.add("phonetic_name_style");
        builder.add("photo_id");
        builder.add("photo_file_id");
        builder.add("photo_uri");
        builder.add("photo_thumb_uri");
        builder.add("send_to_voicemail");
        builder.add("sort_key_alt");
        builder.add("sort_key");
        builder.add("phonebook_label");
        builder.add("phonebook_bucket");
        builder.add("phonebook_label_alt");
        builder.add("phonebook_bucket_alt");
        builder.add("starred");
        builder.add("pinned");
        builder.add("times_contacted", "0");
        builder.add("has_phone_number");
        builder.add("contact_last_updated_timestamp");
        sContactsColumns = builder.build();
        ProjectionMap.Builder builder2 = ProjectionMap.builder();
        builder2.add("contact_presence", "agg_presence.mode");
        builder2.add("contact_chat_capability", "agg_presence.chat_capability");
        builder2.add("contact_status", "contacts_status_updates.status");
        builder2.add("contact_status_ts", "contacts_status_updates.status_ts");
        builder2.add("contact_status_res_package", "contacts_status_updates.status_res_package");
        builder2.add("contact_status_label", "contacts_status_updates.status_label");
        builder2.add("contact_status_icon", "contacts_status_updates.status_icon");
        sContactsPresenceColumns = builder2.build();
        ProjectionMap.Builder builder3 = ProjectionMap.builder();
        builder3.add("snippet");
        sSnippetColumns = builder3.build();
        ProjectionMap.Builder builder4 = ProjectionMap.builder();
        builder4.add("account_name");
        builder4.add("account_type");
        builder4.add("data_set");
        builder4.add("account_type_and_data_set");
        builder4.add("dirty");
        builder4.add("sourceid");
        builder4.add("backup_id");
        builder4.add("version");
        sRawContactColumns = builder4.build();
        ProjectionMap.Builder builder5 = ProjectionMap.builder();
        builder5.add("sync1");
        builder5.add("sync2");
        builder5.add("sync3");
        builder5.add("sync4");
        sRawContactSyncColumns = builder5.build();
        ProjectionMap.Builder builder6 = ProjectionMap.builder();
        builder6.add("data1");
        builder6.add("data2");
        builder6.add("data3");
        builder6.add("data4");
        builder6.add("data5");
        builder6.add("data6");
        builder6.add("data7");
        builder6.add("data8");
        builder6.add("data9");
        builder6.add("data10");
        builder6.add("data11");
        builder6.add("data12");
        builder6.add("data13");
        builder6.add("data14");
        builder6.add("data15");
        builder6.add("carrier_presence");
        builder6.add("preferred_phone_account_component_name");
        builder6.add("preferred_phone_account_id");
        builder6.add("data_version");
        builder6.add("is_primary");
        builder6.add("is_super_primary");
        builder6.add("mimetype");
        builder6.add("res_package");
        builder6.add("data_sync1");
        builder6.add("data_sync2");
        builder6.add("data_sync3");
        builder6.add("data_sync4");
        builder6.add("group_sourceid");
        sDataColumns = builder6.build();
        ProjectionMap.Builder builder7 = ProjectionMap.builder();
        builder7.add("contact_presence", "agg_presence.mode");
        builder7.add("contact_chat_capability", "agg_presence.chat_capability");
        builder7.add("contact_status", "contacts_status_updates.status");
        builder7.add("contact_status_ts", "contacts_status_updates.status_ts");
        builder7.add("contact_status_res_package", "contacts_status_updates.status_res_package");
        builder7.add("contact_status_label", "contacts_status_updates.status_label");
        builder7.add("contact_status_icon", "contacts_status_updates.status_icon");
        sContactPresenceColumns = builder7.build();
        ProjectionMap.Builder builder8 = ProjectionMap.builder();
        builder8.add("mode", "presence.mode");
        builder8.add("chat_capability", "presence.chat_capability");
        builder8.add("status", "status_updates.status");
        builder8.add("status_ts", "status_updates.status_ts");
        builder8.add("status_res_package", "status_updates.status_res_package");
        builder8.add("status_label", "status_updates.status_label");
        builder8.add("status_icon", "status_updates.status_icon");
        sDataPresenceColumns = builder8.build();
        ProjectionMap.Builder builder9 = ProjectionMap.builder();
        builder9.add("times_used", "0");
        builder9.add("last_time_used", "0");
        sDataUsageColumns = builder9.build();
        ProjectionMap.Builder builder10 = ProjectionMap.builder();
        builder10.add("_count", "COUNT(*)");
        sCountProjectionMap = builder10.build();
        ProjectionMap.Builder builder11 = ProjectionMap.builder();
        builder11.add("_id");
        builder11.add("has_phone_number");
        builder11.add("name_raw_contact_id");
        builder11.add("is_user_profile");
        builder11.addAll(sContactsColumns);
        builder11.addAll(sContactsPresenceColumns);
        sContactsProjectionMap = builder11.build();
        ProjectionMap.Builder builder12 = ProjectionMap.builder();
        builder12.addAll(sContactsProjectionMap);
        builder12.addAll(sSnippetColumns);
        sContactsProjectionWithSnippetMap = builder12.build();
        ProjectionMap.Builder builder13 = ProjectionMap.builder();
        builder13.addAll(sContactsProjectionMap);
        builder13.add("times_used", String.valueOf(Long.MAX_VALUE));
        builder13.add("last_time_used", String.valueOf(Long.MAX_VALUE));
        sStrequentStarredProjectionMap = builder13.build();
        ProjectionMap.Builder builder14 = ProjectionMap.builder();
        builder14.addAll(sContactsProjectionMap);
        builder14.add("times_used", "0");
        builder14.add("last_time_used", "0");
        sStrequentFrequentProjectionMap = builder14.build();
        ProjectionMap.Builder builder15 = ProjectionMap.builder();
        builder15.addAll(sContactsProjectionMap);
        builder15.add("times_used", "0");
        builder15.add("last_time_used", "0");
        builder15.add("data1");
        builder15.add("data2");
        builder15.add("data3");
        builder15.add("is_super_primary");
        builder15.add("contact_id");
        builder15.add("is_user_profile", "NULL");
        sStrequentPhoneOnlyProjectionMap = builder15.build();
        ProjectionMap.Builder builder16 = ProjectionMap.builder();
        builder16.add("_id");
        builder16.add("_display_name", "display_name || '.vcf'");
        builder16.add("_size", "NULL");
        sContactsVCardProjectionMap = builder16.build();
        ProjectionMap.Builder builder17 = ProjectionMap.builder();
        builder17.add("_id");
        builder17.add("contact_id");
        builder17.add("deleted");
        builder17.add("display_name");
        builder17.add("display_name_alt");
        builder17.add("display_name_source");
        builder17.add("phonetic_name");
        builder17.add("phonetic_name_style");
        builder17.add("sort_key");
        builder17.add("sort_key_alt");
        builder17.add("phonebook_label");
        builder17.add("phonebook_bucket");
        builder17.add("phonebook_label_alt");
        builder17.add("phonebook_bucket_alt");
        builder17.add("times_contacted");
        builder17.add("last_time_contacted");
        builder17.add("custom_ringtone");
        builder17.add("send_to_voicemail");
        builder17.add("starred");
        builder17.add("pinned");
        builder17.add("aggregation_mode");
        builder17.add("raw_contact_is_user_profile");
        builder17.add("metadata_dirty");
        builder17.addAll(sRawContactColumns);
        builder17.addAll(sRawContactSyncColumns);
        sRawContactsProjectionMap = builder17.build();
        ProjectionMap.Builder builder18 = ProjectionMap.builder();
        builder18.add("_id");
        builder18.add("contact_id");
        builder18.add("data_id");
        builder18.add("deleted");
        builder18.add("starred");
        builder18.add("raw_contact_is_user_profile");
        builder18.addAll(sRawContactColumns);
        builder18.addAll(sRawContactSyncColumns);
        builder18.addAll(sDataColumns);
        sRawEntityProjectionMap = builder18.build();
        ProjectionMap.Builder builder19 = ProjectionMap.builder();
        builder19.add("_id");
        builder19.add("contact_id");
        builder19.add("raw_contact_id");
        builder19.add("data_id");
        builder19.add("name_raw_contact_id");
        builder19.add("deleted");
        builder19.add("is_user_profile");
        builder19.addAll(sContactsColumns);
        builder19.addAll(sContactPresenceColumns);
        builder19.addAll(sRawContactColumns);
        builder19.addAll(sRawContactSyncColumns);
        builder19.addAll(sDataColumns);
        builder19.addAll(sDataPresenceColumns);
        builder19.addAll(sDataUsageColumns);
        sEntityProjectionMap = builder19.build();
        ProjectionMap.Builder builder20 = ProjectionMap.builder();
        builder20.add("data_id", "_id");
        builder20.add("number", "data1");
        builder20.add("type", "0");
        builder20.add("label", "NULL");
        builder20.add("normalized_number", "NULL");
        sSipLookupColumns = builder20.build();
        ProjectionMap.Builder builder21 = ProjectionMap.builder();
        builder21.add("_id");
        builder21.add("raw_contact_id");
        builder21.add("hash_id");
        builder21.add("contact_id");
        builder21.add("name_raw_contact_id");
        builder21.add("raw_contact_is_user_profile");
        builder21.addAll(sDataColumns);
        builder21.addAll(sDataPresenceColumns);
        builder21.addAll(sRawContactColumns);
        builder21.addAll(sContactsColumns);
        builder21.addAll(sContactPresenceColumns);
        builder21.addAll(sDataUsageColumns);
        sDataProjectionMap = builder21.build();
        ProjectionMap.Builder builder22 = ProjectionMap.builder();
        builder22.addAll(sDataProjectionMap);
        builder22.addAll(sSipLookupColumns);
        sDataSipLookupProjectionMap = builder22.build();
        ProjectionMap.Builder builder23 = ProjectionMap.builder();
        builder23.add("_id", "MIN(_id)");
        builder23.add("contact_id");
        builder23.add("raw_contact_is_user_profile");
        builder23.add("hash_id");
        builder23.addAll(sDataColumns);
        builder23.addAll(sDataPresenceColumns);
        builder23.addAll(sContactsColumns);
        builder23.addAll(sContactPresenceColumns);
        builder23.addAll(sDataUsageColumns);
        sDistinctDataProjectionMap = builder23.build();
        ProjectionMap.Builder builder24 = ProjectionMap.builder();
        builder24.addAll(sDistinctDataProjectionMap);
        builder24.addAll(sSipLookupColumns);
        sDistinctDataSipLookupProjectionMap = builder24.build();
        ProjectionMap.Builder builder25 = ProjectionMap.builder();
        builder25.add("_id", "contacts_view._id");
        builder25.add("contact_id", "contacts_view._id");
        builder25.add("data_id", "data_id");
        builder25.add("lookup", "contacts_view.lookup");
        builder25.add("display_name_source", "contacts_view.display_name_source");
        builder25.add("display_name", "contacts_view.display_name");
        builder25.add("display_name_alt", "contacts_view.display_name_alt");
        builder25.add("phonetic_name", "contacts_view.phonetic_name");
        builder25.add("phonetic_name_style", "contacts_view.phonetic_name_style");
        builder25.add("sort_key", "contacts_view.sort_key");
        builder25.add("sort_key_alt", "contacts_view.sort_key_alt");
        builder25.add("last_time_contacted", "contacts_view.last_time_contacted");
        builder25.add("times_contacted", "contacts_view.times_contacted");
        builder25.add("starred", "contacts_view.starred");
        builder25.add("in_default_directory", "contacts_view.in_default_directory");
        builder25.add("in_visible_group", "contacts_view.in_visible_group");
        builder25.add("photo_id", "contacts_view.photo_id");
        builder25.add("photo_file_id", "contacts_view.photo_file_id");
        builder25.add("photo_uri", "contacts_view.photo_uri");
        builder25.add("photo_thumb_uri", "contacts_view.photo_thumb_uri");
        builder25.add("custom_ringtone", "contacts_view.custom_ringtone");
        builder25.add("has_phone_number", "contacts_view.has_phone_number");
        builder25.add("send_to_voicemail", "contacts_view.send_to_voicemail");
        builder25.add("number", "data1");
        builder25.add("type", "data2");
        builder25.add("label", "data3");
        builder25.add("normalized_number", "data4");
        builder25.add("preferred_phone_account_component_name");
        builder25.add("preferred_phone_account_id");
        sPhoneLookupProjectionMap = builder25.build();
        ProjectionMap.Builder builder26 = ProjectionMap.builder();
        builder26.add("_id");
        builder26.add("account_name");
        builder26.add("account_type");
        builder26.add("data_set");
        builder26.add("account_type_and_data_set");
        builder26.add("sourceid");
        builder26.add("dirty");
        builder26.add("version");
        builder26.add("res_package");
        builder26.add("title");
        builder26.add("title_res");
        builder26.add("group_visible");
        builder26.add("system_id");
        builder26.add("deleted");
        builder26.add("notes");
        builder26.add("should_sync");
        builder26.add("favorites");
        builder26.add("auto_add");
        builder26.add("group_is_read_only");
        builder26.add("sync1");
        builder26.add("sync2");
        builder26.add("sync3");
        builder26.add("sync4");
        sGroupsProjectionMap = builder26.build();
        ProjectionMap.Builder builder27 = ProjectionMap.builder();
        builder27.add("contact_id");
        builder27.add("contact_deleted_timestamp");
        sDeletedContactsProjectionMap = builder27.build();
        ProjectionMap.Builder builder28 = ProjectionMap.builder();
        builder28.addAll(sGroupsProjectionMap);
        builder28.add("summ_count", "ifnull(group_member_count, 0)");
        builder28.add("summ_phones", "(SELECT COUNT(contacts._id) FROM contacts INNER JOIN raw_contacts ON (raw_contacts.contact_id=contacts._id) INNER JOIN data ON (data.data1=groups._id AND data.raw_contact_id=raw_contacts._id AND data.mimetype_id=(SELECT _id FROM mimetypes WHERE mimetypes.mimetype='vnd.android.cursor.item/group_membership')) WHERE has_phone_number)");
        builder28.add("group_count_per_account", "0");
        sGroupsSummaryProjectionMap = builder28.build();
        ProjectionMap.Builder builder29 = ProjectionMap.builder();
        builder29.add("_id", "agg_exceptions._id");
        builder29.add("type");
        builder29.add("raw_contact_id1");
        builder29.add("raw_contact_id2");
        sAggregationExceptionsProjectionMap = builder29.build();
        ProjectionMap.Builder builder30 = ProjectionMap.builder();
        builder30.add("account_name");
        builder30.add("account_type");
        builder30.add("data_set");
        builder30.add("ungrouped_visible");
        builder30.add("should_sync");
        builder30.add("any_unsynced", "(CASE WHEN MIN(should_sync,(SELECT (CASE WHEN MIN(should_sync) IS NULL THEN 1 ELSE MIN(should_sync) END) FROM view_groups WHERE view_groups.account_name=settings.account_name AND view_groups.account_type=settings.account_type AND ((view_groups.data_set IS NULL AND settings.data_set IS NULL) OR (view_groups.data_set=settings.data_set))))=0 THEN 1 ELSE 0 END)");
        builder30.add("summ_count", "(SELECT COUNT(*) FROM (SELECT 1 FROM settings LEFT OUTER JOIN raw_contacts ON (raw_contacts.account_id=(SELECT accounts._id FROM accounts WHERE (accounts.account_name=settings.account_name) AND (accounts.account_type=settings.account_type)))LEFT OUTER JOIN data ON (data.mimetype_id=? AND data.raw_contact_id = raw_contacts._id) LEFT OUTER JOIN contacts ON (raw_contacts.contact_id = contacts._id) GROUP BY settings.account_name,settings.account_type,contact_id HAVING COUNT(data.data1) == 0))");
        builder30.add("summ_phones", "(SELECT COUNT(*) FROM (SELECT 1 FROM settings LEFT OUTER JOIN raw_contacts ON (raw_contacts.account_id=(SELECT accounts._id FROM accounts WHERE (accounts.account_name=settings.account_name) AND (accounts.account_type=settings.account_type)))LEFT OUTER JOIN data ON (data.mimetype_id=? AND data.raw_contact_id = raw_contacts._id) LEFT OUTER JOIN contacts ON (raw_contacts.contact_id = contacts._id) WHERE has_phone_number GROUP BY settings.account_name,settings.account_type,contact_id HAVING COUNT(data.data1) == 0))");
        sSettingsProjectionMap = builder30.build();
        ProjectionMap.Builder builder31 = ProjectionMap.builder();
        builder31.add("presence_raw_contact_id");
        builder31.add("presence_data_id", "data._id");
        builder31.add("im_account");
        builder31.add("im_handle");
        builder31.add("protocol");
        builder31.add("custom_protocol", "(CASE WHEN custom_protocol='' THEN NULL ELSE custom_protocol END)");
        builder31.add("mode");
        builder31.add("chat_capability");
        builder31.add("status");
        builder31.add("status_ts");
        builder31.add("status_res_package");
        builder31.add("status_icon");
        builder31.add("status_label");
        sStatusUpdatesProjectionMap = builder31.build();
        ProjectionMap.Builder builder32 = ProjectionMap.builder();
        builder32.add("_id");
        builder32.add("contact_id");
        builder32.add("contact_lookup");
        builder32.add("account_name");
        builder32.add("account_type");
        builder32.add("data_set");
        builder32.add("raw_contact_id");
        builder32.add("raw_contact_source_id");
        builder32.add("res_package");
        builder32.add("icon");
        builder32.add("label");
        builder32.add("text");
        builder32.add("timestamp");
        builder32.add("comments");
        builder32.add("stream_item_sync1");
        builder32.add("stream_item_sync2");
        builder32.add("stream_item_sync3");
        builder32.add("stream_item_sync4");
        sStreamItemsProjectionMap = builder32.build();
        ProjectionMap.Builder builder33 = ProjectionMap.builder();
        builder33.add("_id", "stream_item_photos._id");
        builder33.add("raw_contact_id");
        builder33.add("raw_contact_source_id", "raw_contacts.sourceid");
        builder33.add("stream_item_id");
        builder33.add("sort_index");
        builder33.add("photo_file_id");
        builder33.add("photo_uri", "'" + ContactsContract.DisplayPhoto.CONTENT_URI + "'||'/'||photo_file_id");
        builder33.add("height");
        builder33.add("width");
        builder33.add("filesize");
        builder33.add("stream_item_photo_sync1");
        builder33.add("stream_item_photo_sync2");
        builder33.add("stream_item_photo_sync3");
        builder33.add("stream_item_photo_sync4");
        sStreamItemPhotosProjectionMap = builder33.build();
        ProjectionMap.Builder builder34 = ProjectionMap.builder();
        builder34.add("_id");
        builder34.add("packageName");
        builder34.add("typeResourceId");
        builder34.add("displayName");
        builder34.add("authority");
        builder34.add("accountType");
        builder34.add("accountName");
        builder34.add("exportSupport");
        builder34.add("shortcutSupport");
        builder34.add("photoSupport");
        sDirectoryProjectionMap = builder34.build();
        EMPTY_STRING_ARRAY = new String[0];
        ProfileAwareUriMatcher profileAwareUriMatcher = sUriMatcher;
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts", 1000);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/#", 1001);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/#/data", 1004);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/#/entities", 1019);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/#/suggestions", 8000);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/#/suggestions/*", 8000);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/#/photo", 1009);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/#/display_photo", 1012);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts_corp/#/photo", 1027);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts_corp/#/display_photo", 1028);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/#/stream_items", 1022);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/filter", 1005);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/filter/*", 1005);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/lookup/*", 1002);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/lookup/*/data", 1017);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/lookup/*/photo", 1010);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/lookup/*/#", 1003);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/lookup/*/#/data", 1018);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/lookup/*/#/photo", 1011);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/lookup/*/display_photo", 1013);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/lookup/*/#/display_photo", 1014);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/lookup/*/entities", 1020);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/lookup/*/#/entities", 1021);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/lookup/*/stream_items", 1023);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/lookup/*/#/stream_items", 1024);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/as_vcard/*", 1015);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/as_multi_vcard/*", 1016);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/strequent/", 1006);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/strequent/filter/*", 1007);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/group/*", 1008);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/frequent", 1025);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/delete_usage", 1026);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/filter_enterprise", 1029);
        profileAwareUriMatcher.addURI("com.android.contacts", "contacts/filter_enterprise/*", 1029);
        profileAwareUriMatcher.addURI("com.android.contacts", "raw_contacts", 2002);
        profileAwareUriMatcher.addURI("com.android.contacts", "raw_contacts/#", 2003);
        profileAwareUriMatcher.addURI("com.android.contacts", "raw_contacts/#/data", 2004);
        profileAwareUriMatcher.addURI("com.android.contacts", "raw_contacts/#/display_photo", 2006);
        profileAwareUriMatcher.addURI("com.android.contacts", "raw_contacts/#/entity", 2005);
        profileAwareUriMatcher.addURI("com.android.contacts", "raw_contacts/#/stream_items", 2007);
        profileAwareUriMatcher.addURI("com.android.contacts", "raw_contacts/#/stream_items/#", 2008);
        profileAwareUriMatcher.addURI("com.android.contacts", "raw_contact_entities", 15001);
        profileAwareUriMatcher.addURI("com.android.contacts", "raw_contact_entities_corp", 15002);
        profileAwareUriMatcher.addURI("com.android.contacts", "data", 3000);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/#", 3001);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/phones", 3002);
        profileAwareUriMatcher.addURI("com.android.contacts", "data_enterprise/phones", 3016);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/phones/#", 3003);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/phones/filter", 3004);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/phones/filter/*", 3004);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/phones/filter_enterprise", 3018);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/phones/filter_enterprise/*", 3018);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/emails", 3005);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/emails/#", 3006);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/emails/lookup", 3007);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/emails/lookup/*", 3007);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/emails/filter", 3008);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/emails/filter/*", 3008);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/emails/filter_enterprise", 3020);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/emails/filter_enterprise/*", 3020);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/emails/lookup_enterprise", 3017);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/emails/lookup_enterprise/*", 3017);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/postals", 3009);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/postals/#", 3010);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/usagefeedback/*", 20001);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/callables/", 3011);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/callables/#", 3012);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/callables/filter", 3013);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/callables/filter/*", 3013);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/callables/filter_enterprise", 3019);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/callables/filter_enterprise/*", 3019);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/contactables/", 3014);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/contactables/filter", 3015);
        profileAwareUriMatcher.addURI("com.android.contacts", "data/contactables/filter/*", 3015);
        profileAwareUriMatcher.addURI("com.android.contacts", "groups", 10000);
        profileAwareUriMatcher.addURI("com.android.contacts", "groups/#", 10001);
        profileAwareUriMatcher.addURI("com.android.contacts", "groups_summary", 10003);
        profileAwareUriMatcher.addURI("com.android.contacts", "syncstate", 11000);
        profileAwareUriMatcher.addURI("com.android.contacts", "syncstate/#", 11001);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/syncstate", 11002);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/syncstate/#", 11003);
        profileAwareUriMatcher.addURI("com.android.contacts", "phone_lookup/*", 4000);
        profileAwareUriMatcher.addURI("com.android.contacts", "phone_lookup_enterprise/*", 4001);
        profileAwareUriMatcher.addURI("com.android.contacts", "aggregation_exceptions", 6000);
        profileAwareUriMatcher.addURI("com.android.contacts", "aggregation_exceptions/*", 6001);
        profileAwareUriMatcher.addURI("com.android.contacts", "settings", 9000);
        profileAwareUriMatcher.addURI("com.android.contacts", "status_updates", 7000);
        profileAwareUriMatcher.addURI("com.android.contacts", "status_updates/#", 7001);
        profileAwareUriMatcher.addURI("com.android.contacts", "search_suggest_query", 12001);
        profileAwareUriMatcher.addURI("com.android.contacts", "search_suggest_query/*", 12001);
        profileAwareUriMatcher.addURI("com.android.contacts", "search_suggest_shortcut/*", 12002);
        profileAwareUriMatcher.addURI("com.android.contacts", "provider_status", 16001);
        profileAwareUriMatcher.addURI("com.android.contacts", "directories", 17001);
        profileAwareUriMatcher.addURI("com.android.contacts", "directories/#", 17002);
        profileAwareUriMatcher.addURI("com.android.contacts", "directories_enterprise", 17003);
        profileAwareUriMatcher.addURI("com.android.contacts", "directories_enterprise/#", 17004);
        profileAwareUriMatcher.addURI("com.android.contacts", "complete_name", 18000);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile", 19000);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/entities", 19001);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/data", 19002);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/data/#", 19003);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/photo", 19011);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/display_photo", 19012);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/as_vcard", 19004);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/raw_contacts", 19005);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/raw_contacts/#", 19006);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/raw_contacts/#/data", 19007);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/raw_contacts/#/entity", 19008);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/status_updates", 19009);
        profileAwareUriMatcher.addURI("com.android.contacts", "profile/raw_contact_entities", 19010);
        profileAwareUriMatcher.addURI("com.android.contacts", "stream_items", 21000);
        profileAwareUriMatcher.addURI("com.android.contacts", "stream_items/photo", 21001);
        profileAwareUriMatcher.addURI("com.android.contacts", "stream_items/#", 21002);
        profileAwareUriMatcher.addURI("com.android.contacts", "stream_items/#/photo", 21003);
        profileAwareUriMatcher.addURI("com.android.contacts", "stream_items/#/photo/#", 21004);
        profileAwareUriMatcher.addURI("com.android.contacts", "stream_items_limit", 21005);
        profileAwareUriMatcher.addURI("com.android.contacts", "display_photo/#", 22000);
        profileAwareUriMatcher.addURI("com.android.contacts", "photo_dimensions", 22001);
        profileAwareUriMatcher.addURI("com.android.contacts", "deleted_contacts", 23000);
        profileAwareUriMatcher.addURI("com.android.contacts", "deleted_contacts/#", 23001);
        profileAwareUriMatcher.addURI("com.android.contacts", "directory_file_enterprise/*", 24000);
        MODIFIED_KEY_SET_FOR_ENTERPRISE_FILTER = new ArraySet(Arrays.asList("directory"));
    }

    private static class DirectoryInfo {
        String accountName;
        String accountType;
        String authority;

        private DirectoryInfo() {
        }
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider, android.content.ContentProvider
    public boolean onCreate() {
        if (AbstractContactsProvider.VERBOSE_LOGGING) {
            Log.v("ContactsProvider", "onCreate user=" + Process.myUserHandle().getIdentifier());
        }
        if (Log.isLoggable("ContactsPerf", 3)) {
            Log.d("ContactsPerf", "ContactsProvider2.onCreate start");
        }
        super.onCreate();
        setAppOps(4, 5);
        try {
            try {
                boolean zInitialize = initialize();
                if (Log.isLoggable("ContactsPerf", 3)) {
                    Log.d("ContactsPerf", "ContactsProvider2.onCreate finish");
                }
                return zInitialize;
            } catch (RuntimeException e) {
                Log.e("ContactsProvider", "Cannot start provider", e);
                if (this.shouldThrowExceptionForInitializationError()) {
                    throw e;
                }
                if (Log.isLoggable("ContactsPerf", 3)) {
                    Log.d("ContactsPerf", "ContactsProvider2.onCreate finish");
                }
                return false;
            }
        } catch (Throwable th) {
            if (Log.isLoggable("ContactsPerf", 3)) {
                Log.d("ContactsPerf", "ContactsProvider2.onCreate finish");
            }
            throw th;
        }
    }

    private boolean initialize() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        this.mFastScrollingIndexCache = FastScrollingIndexCache.getInstance(getContext());
        Settings.Global.getInt(getContext().getContentResolver(), "contact_metadata_sync_enabled", 0);
        ContactsDatabaseHelper databaseHelper = getDatabaseHelper();
        this.mContactsHelper = databaseHelper;
        this.mDbHelper.set(databaseHelper);
        setDbHelperToSerializeOn(this.mContactsHelper, "contacts", this);
        this.mContactDirectoryManager = new ContactDirectoryManager(this);
        this.mGlobalSearchSupport = new GlobalSearchSupport(this);
        this.mReadAccessLatch = new CountDownLatch(1);
        this.mWriteAccessLatch = new CountDownLatch(1);
        this.mTaskScheduler = new ContactsTaskScheduler(ContactsProvider2.class.getSimpleName()) { // from class: com.android.providers.contacts.ContactsProvider2.1
            @Override // com.android.providers.contacts.ContactsTaskScheduler
            public void onPerformTask(int i, Object obj) {
                ContactsProvider2.this.performBackgroundTask(i, obj);
            }
        };
        ProfileProvider profileProviderNewProfileProvider = newProfileProvider();
        this.mProfileProvider = profileProviderNewProfileProvider;
        profileProviderNewProfileProvider.setDbHelperToSerializeOn(this.mContactsHelper, "contacts", this);
        ProviderInfo providerInfo = new ProviderInfo();
        providerInfo.authority = "com.android.contacts";
        this.mProfileProvider.attachInfo(getContext(), providerInfo);
        this.mProfileHelper = this.mProfileProvider.getDatabaseHelper();
        this.mEnterprisePolicyGuard = new EnterprisePolicyGuard(getContext());
        this.mPreAuthorizedUriDuration = 300000L;
        scheduleBackgroundTask(0);
        scheduleBackgroundTask(3);
        scheduleBackgroundTask(4);
        scheduleBackgroundTask(5);
        scheduleBackgroundTask(6);
        scheduleBackgroundTask(7);
        scheduleBackgroundTask(1);
        scheduleBackgroundTask(10);
        scheduleBackgroundTask(11);
        ContactsPackageMonitor.start(getContext());
        return true;
    }

    public void setNewAggregatorForTest(boolean z) {
        AbstractContactAggregator contactAggregator;
        if (z) {
            contactAggregator = new ContactAggregator2(this, this.mContactsHelper, createPhotoPriorityResolver(getContext()), this.mNameSplitter, this.mCommonNicknameCache);
        } else {
            contactAggregator = new ContactAggregator(this, this.mContactsHelper, createPhotoPriorityResolver(getContext()), this.mNameSplitter, this.mCommonNicknameCache);
        }
        this.mContactAggregator = contactAggregator;
        contactAggregator.setEnabled(((Boolean) ContactsProperties.aggregate_contacts().orElse(Boolean.TRUE)).booleanValue());
        initDataRowHandlers(this.mDataRowHandlers, this.mContactsHelper, this.mContactAggregator, this.mContactsPhotoStore);
    }

    private void initForDefaultLocale() {
        AbstractContactAggregator contactAggregator2;
        Boolean bool = Boolean.TRUE;
        Context context = getContext();
        this.mLegacyApiSupport = new LegacyApiSupport(context, this.mContactsHelper, this, this.mGlobalSearchSupport);
        LocaleSet localeSetNewDefault = LocaleSet.newDefault();
        this.mCurrentLocales = localeSetNewDefault;
        this.mNameSplitter = this.mContactsHelper.createNameSplitter(localeSetNewDefault.getPrimaryLocale());
        this.mNameLookupBuilder = new StructuredNameLookupBuilder(this.mNameSplitter);
        this.mPostalSplitter = new PostalSplitter(this.mCurrentLocales.getPrimaryLocale());
        this.mCommonNicknameCache = new CommonNicknameCache(this.mContactsHelper.getReadableDatabase());
        ContactLocaleUtils.setLocales(this.mCurrentLocales);
        int i = Settings.Global.getInt(context.getContentResolver(), "new_contact_aggregator", 1);
        PROPERTY_AGGREGATION_ALGORITHM_VERSION = i == 0 ? 4 : 5;
        if (i == 0) {
            contactAggregator2 = new ContactAggregator(this, this.mContactsHelper, createPhotoPriorityResolver(context), this.mNameSplitter, this.mCommonNicknameCache);
        } else {
            contactAggregator2 = new ContactAggregator2(this, this.mContactsHelper, createPhotoPriorityResolver(context), this.mNameSplitter, this.mCommonNicknameCache);
        }
        this.mContactAggregator = contactAggregator2;
        contactAggregator2.setEnabled(((Boolean) ContactsProperties.aggregate_contacts().orElse(bool)).booleanValue());
        ProfileAggregator profileAggregator = new ProfileAggregator(this, this.mProfileHelper, createPhotoPriorityResolver(context), this.mNameSplitter, this.mCommonNicknameCache);
        this.mProfileAggregator = profileAggregator;
        profileAggregator.setEnabled(((Boolean) ContactsProperties.aggregate_contacts().orElse(bool)).booleanValue());
        this.mSearchIndexManager = new SearchIndexManager(this);
        this.mContactsPhotoStore = new PhotoStore(getContext().getFilesDir(), this.mContactsHelper);
        this.mProfilePhotoStore = new PhotoStore(new File(getContext().getFilesDir(), "profile"), this.mProfileHelper);
        ArrayMap<String, DataRowHandler> arrayMap = new ArrayMap<>();
        this.mDataRowHandlers = arrayMap;
        initDataRowHandlers(arrayMap, this.mContactsHelper, this.mContactAggregator, this.mContactsPhotoStore);
        ArrayMap<String, DataRowHandler> arrayMap2 = new ArrayMap<>();
        this.mProfileDataRowHandlers = arrayMap2;
        initDataRowHandlers(arrayMap2, this.mProfileHelper, this.mProfileAggregator, this.mProfilePhotoStore);
        switchToContactMode();
    }

    private void initDataRowHandlers(Map<String, DataRowHandler> map, ContactsDatabaseHelper contactsDatabaseHelper, AbstractContactAggregator abstractContactAggregator, PhotoStore photoStore) {
        Context context = getContext();
        map.put("vnd.android.cursor.item/email_v2", new DataRowHandlerForEmail(context, contactsDatabaseHelper, abstractContactAggregator));
        map.put("vnd.android.cursor.item/im", new DataRowHandlerForIm(context, contactsDatabaseHelper, abstractContactAggregator));
        map.put("vnd.android.cursor.item/organization", new DataRowHandlerForOrganization(context, contactsDatabaseHelper, abstractContactAggregator));
        map.put("vnd.android.cursor.item/phone_v2", new DataRowHandlerForPhoneNumber(context, contactsDatabaseHelper, abstractContactAggregator));
        map.put("vnd.android.cursor.item/nickname", new DataRowHandlerForNickname(context, contactsDatabaseHelper, abstractContactAggregator));
        map.put("vnd.android.cursor.item/name", new DataRowHandlerForStructuredName(context, contactsDatabaseHelper, abstractContactAggregator, this.mNameSplitter, this.mNameLookupBuilder));
        map.put("vnd.android.cursor.item/postal-address_v2", new DataRowHandlerForStructuredPostal(context, contactsDatabaseHelper, abstractContactAggregator, this.mPostalSplitter));
        map.put("vnd.android.cursor.item/group_membership", new DataRowHandlerForGroupMembership(context, contactsDatabaseHelper, abstractContactAggregator, this.mGroupIdCache));
        map.put("vnd.android.cursor.item/photo", new DataRowHandlerForPhoto(context, contactsDatabaseHelper, abstractContactAggregator, photoStore, getMaxDisplayPhotoDim(), getMaxThumbnailDim()));
        map.put("vnd.android.cursor.item/note", new DataRowHandlerForNote(context, contactsDatabaseHelper, abstractContactAggregator));
        map.put("vnd.android.cursor.item/identity", new DataRowHandlerForIdentity(context, contactsDatabaseHelper, abstractContactAggregator));
    }

    PhotoPriorityResolver createPhotoPriorityResolver(Context context) {
        return new PhotoPriorityResolver(context);
    }

    protected void scheduleBackgroundTask(int i) {
        scheduleBackgroundTask(i, null);
    }

    protected void scheduleBackgroundTask(int i, Object obj) {
        this.mTaskScheduler.scheduleTask(i, obj);
    }

    protected void performBackgroundTask(int i, Object obj) {
        switchToContactMode();
        switch (i) {
            case 0:
                initForDefaultLocale();
                this.mReadAccessLatch.countDown();
                this.mReadAccessLatch = null;
                break;
            case 1:
                if (this.mOkToOpenAccess) {
                    this.mWriteAccessLatch.countDown();
                    this.mWriteAccessLatch = null;
                }
                break;
            case 3:
                Context context = getContext();
                if (!this.mAccountUpdateListenerRegistered) {
                    AccountManager.get(context).addOnAccountsUpdatedListener(this, null, false);
                    this.mAccountUpdateListenerRegistered = true;
                }
                Account[] accounts = AccountManager.get(context).getAccounts();
                switchToContactMode();
                boolean zUpdateAccountsInBackground = updateAccountsInBackground(accounts);
                switchToProfileMode();
                boolean zUpdateAccountsInBackground2 = zUpdateAccountsInBackground | updateAccountsInBackground(accounts);
                switchToContactMode();
                updateContactsAccountCount(accounts);
                updateDirectoriesInBackground(zUpdateAccountsInBackground2);
                break;
            case 4:
                updateLocaleInBackground();
                break;
            case 5:
                if (isAggregationUpgradeNeeded()) {
                    upgradeAggregationAlgorithmInBackground();
                    invalidateFastScrollingIndexCache();
                }
                break;
            case 6:
                updateSearchIndexInBackground();
                break;
            case 7:
                updateProviderStatus();
                break;
            case 9:
                changeLocaleInBackground();
                break;
            case 10:
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.mLastPhotoCleanup > 86400000) {
                    this.mLastPhotoCleanup = jCurrentTimeMillis;
                    switchToContactMode();
                    cleanupPhotoStore();
                    switchToProfileMode();
                    cleanupPhotoStore();
                    switchToContactMode();
                }
                break;
            case 11:
                DeletedContactsTableUtil.deleteOldLogs(this.mDbHelper.get().getWritableDatabase());
                break;
            case 12:
                updateDirectoriesInBackground(true);
                break;
        }
    }

    public void onLocaleChanged() {
        int i = this.mProviderStatus;
        if (i == 0 || i == 3) {
            scheduleBackgroundTask(9);
        }
    }

    private static boolean needsToUpdateLocaleData(SharedPreferences sharedPreferences, LocaleSet localeSet, ContactsDatabaseHelper contactsDatabaseHelper, ProfileDatabaseHelper profileDatabaseHelper) {
        String string = sharedPreferences.getString("locale", null);
        if (localeSet.toString().equals(string)) {
            return contactsDatabaseHelper.needsToUpdateLocaleData(localeSet) || profileDatabaseHelper.needsToUpdateLocaleData(localeSet);
        }
        Log.i("ContactsProvider", "Locale has changed from " + string + " to " + localeSet);
        return true;
    }

    protected void updateLocaleInBackground() {
        if (this.mProviderStatus == 2) {
            return;
        }
        LocaleSet localeSet = this.mCurrentLocales;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (needsToUpdateLocaleData(defaultSharedPreferences, localeSet, this.mContactsHelper, this.mProfileHelper)) {
            int i = this.mProviderStatus;
            setProviderStatus(2);
            this.mContactsHelper.setLocale(localeSet);
            this.mProfileHelper.setLocale(localeSet);
            this.mSearchIndexManager.updateIndex(true);
            defaultSharedPreferences.edit().putString("locale", localeSet.toString()).commit();
            setProviderStatus(i);
            if (this.mCurrentLocales.isCurrent()) {
                return;
            }
            scheduleBackgroundTask(9);
        }
    }

    protected static void updateLocaleOffline(Context context, ContactsDatabaseHelper contactsDatabaseHelper, ProfileDatabaseHelper profileDatabaseHelper) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        LocaleSet localeSetNewDefault = LocaleSet.newDefault();
        if (needsToUpdateLocaleData(defaultSharedPreferences, localeSetNewDefault, contactsDatabaseHelper, profileDatabaseHelper)) {
            contactsDatabaseHelper.setLocale(localeSetNewDefault);
            profileDatabaseHelper.setLocale(localeSetNewDefault);
            contactsDatabaseHelper.rebuildSearchIndex();
            defaultSharedPreferences.edit().putString("locale", localeSetNewDefault.toString()).commit();
        }
    }

    private void changeLocaleInBackground() {
        SQLiteDatabase writableDatabase = this.mContactsHelper.getWritableDatabase();
        SQLiteDatabase writableDatabase2 = this.mProfileHelper.getWritableDatabase();
        writableDatabase.beginTransaction();
        writableDatabase2.beginTransaction();
        try {
            initForDefaultLocale();
            writableDatabase.setTransactionSuccessful();
            writableDatabase2.setTransactionSuccessful();
            writableDatabase.endTransaction();
            writableDatabase2.endTransaction();
            updateLocaleInBackground();
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            writableDatabase2.endTransaction();
            throw th;
        }
    }

    protected void updateSearchIndexInBackground() {
        this.mSearchIndexManager.updateIndex(false);
    }

    protected void updateDirectoriesInBackground(boolean z) {
        this.mContactDirectoryManager.scanAllPackages(z);
    }

    private void updateProviderStatus() {
        int i = this.mProviderStatus;
        if (i == 0 || i == 3) {
            if (this.mContactsAccountCount == 0) {
                boolean zQueryIsEmpty = DatabaseUtils.queryIsEmpty(this.mContactsHelper.getReadableDatabase(), "contacts");
                long jQueryNumEntries = DatabaseUtils.queryNumEntries(this.mProfileHelper.getReadableDatabase(), "contacts", null);
                if (zQueryIsEmpty && jQueryNumEntries <= 1) {
                    setProviderStatus(3);
                    return;
                } else {
                    setProviderStatus(0);
                    return;
                }
            }
            setProviderStatus(0);
        }
    }

    protected void cleanupPhotoStore() {
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        Cursor cursorQuery = writableDatabase.query("view_data", new String[]{"_id", "data14"}, "mimetype_id=" + this.mDbHelper.get().getMimeTypeId("vnd.android.cursor.item/photo") + " AND data14 IS NOT NULL", null, null, null, null);
        HashSet hashSetNewHashSet = Sets.newHashSet();
        HashMap mapNewHashMap = Maps.newHashMap();
        while (cursorQuery.moveToNext()) {
            try {
                long j = cursorQuery.getLong(0);
                long j2 = cursorQuery.getLong(1);
                hashSetNewHashSet.add(Long.valueOf(j2));
                mapNewHashMap.put(Long.valueOf(j2), Long.valueOf(j));
            } finally {
            }
        }
        cursorQuery.close();
        cursorQuery = writableDatabase.query("stream_item_photos JOIN stream_items ON stream_item_id=stream_items._id", new String[]{"stream_item_photos._id", "stream_item_photos.stream_item_id", "photo_file_id"}, null, null, null, null, null);
        HashMap mapNewHashMap2 = Maps.newHashMap();
        HashMap mapNewHashMap3 = Maps.newHashMap();
        while (cursorQuery.moveToNext()) {
            try {
                long j3 = cursorQuery.getLong(0);
                long j4 = cursorQuery.getLong(1);
                long j5 = cursorQuery.getLong(2);
                hashSetNewHashSet.add(Long.valueOf(j5));
                mapNewHashMap2.put(Long.valueOf(j5), Long.valueOf(j3));
                mapNewHashMap3.put(Long.valueOf(j3), Long.valueOf(j4));
            } finally {
            }
        }
        cursorQuery.close();
        Set<Long> setCleanup = this.mPhotoStore.get().cleanup(hashSetNewHashSet);
        if (setCleanup.isEmpty()) {
            return;
        }
        try {
            try {
                writableDatabase.beginTransactionWithListener(inProfileMode() ? this.mProfileProvider : this);
                Iterator<Long> it = setCleanup.iterator();
                while (it.hasNext()) {
                    long jLongValue = it.next().longValue();
                    if (mapNewHashMap.containsKey(Long.valueOf(jLongValue))) {
                        long jLongValue2 = ((Long) mapNewHashMap.get(Long.valueOf(jLongValue))).longValue();
                        ContentValues contentValues = new ContentValues();
                        contentValues.putNull("data14");
                        updateData(ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, jLongValue2), contentValues, null, null, false, false);
                    }
                    if (mapNewHashMap2.containsKey(Long.valueOf(jLongValue))) {
                        writableDatabase.delete("stream_item_photos", "_id=?", new String[]{String.valueOf(((Long) mapNewHashMap2.get(Long.valueOf(jLongValue))).longValue())});
                    }
                }
                writableDatabase.setTransactionSuccessful();
            } finally {
                writableDatabase.endTransaction();
            }
        } catch (Exception e) {
            Log.e("ContactsProvider", "Failed to clean up outdated photo references", e);
        }
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider
    public ContactsDatabaseHelper newDatabaseHelper(Context context) {
        return ContactsDatabaseHelper.getInstance(context);
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider
    protected ThreadLocal<ContactsTransaction> getTransactionHolder() {
        return this.mTransactionHolder;
    }

    public ProfileProvider newProfileProvider() {
        return new ProfileProvider(this);
    }

    PhotoStore getPhotoStore() {
        return this.mContactsPhotoStore;
    }

    PhotoStore getProfilePhotoStore() {
        return this.mProfilePhotoStore;
    }

    public int getMaxThumbnailDim() {
        return PhotoProcessor.getMaxThumbnailSize();
    }

    public int getMaxDisplayPhotoDim() {
        return PhotoProcessor.getMaxDisplayPhotoSize();
    }

    public ContactDirectoryManager getContactDirectoryManagerForTest() {
        return this.mContactDirectoryManager;
    }

    protected Locale getLocale() {
        return Locale.getDefault();
    }

    final boolean inProfileMode() {
        Boolean bool = this.mInProfileMode.get();
        return bool != null && bool.booleanValue();
    }

    void wipeData() {
        invalidateFastScrollingIndexCache();
        this.mContactsHelper.wipeData();
        this.mProfileHelper.wipeData();
        this.mContactsPhotoStore.clear();
        this.mProfilePhotoStore.clear();
        this.mProviderStatus = 3;
        initForDefaultLocale();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForAccess(CountDownLatch countDownLatch) {
        if (countDownLatch == null) {
            return;
        }
        while (true) {
            try {
                countDownLatch.await();
                return;
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private int getIntValue(ContentValues contentValues, String str, int i) {
        Integer asInteger = contentValues.getAsInteger(str);
        return asInteger != null ? asInteger.intValue() : i;
    }

    private boolean flagExists(ContentValues contentValues, String str) {
        return contentValues.getAsInteger(str) != null;
    }

    private boolean flagIsSet(ContentValues contentValues, String str) {
        return getIntValue(contentValues, str, 0) != 0;
    }

    private boolean flagIsClear(ContentValues contentValues, String str) {
        return getIntValue(contentValues, str, 1) == 0;
    }

    private boolean mapsToProfileDb(Uri uri) {
        return sUriMatcher.mapsToProfile(uri);
    }

    private boolean mapsToProfileDbWithInsertedValues(Uri uri, ContentValues contentValues) {
        Long asLong;
        if (mapsToProfileDb(uri)) {
            return true;
        }
        int iMatch = sUriMatcher.match(uri);
        return INSERT_URI_ID_VALUE_MAP.containsKey(Integer.valueOf(iMatch)) && (asLong = contentValues.getAsLong(INSERT_URI_ID_VALUE_MAP.get(Integer.valueOf(iMatch)))) != null && ContactsContract.isProfileId(asLong.longValue());
    }

    private void switchToProfileMode() {
        this.mDbHelper.set(this.mProfileHelper);
        this.mTransactionContext.set(this.mProfileTransactionContext);
        this.mAggregator.set(this.mProfileAggregator);
        this.mPhotoStore.set(this.mProfilePhotoStore);
        this.mInProfileMode.set(Boolean.TRUE);
    }

    private void switchToContactMode() {
        this.mDbHelper.set(this.mContactsHelper);
        this.mTransactionContext.set(this.mContactTransactionContext);
        this.mAggregator.set(this.mContactAggregator);
        this.mPhotoStore.set(this.mContactsPhotoStore);
        this.mInProfileMode.set(Boolean.FALSE);
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider, android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        waitForAccess(this.mWriteAccessLatch);
        this.mContactsHelper.validateContentValues(getCallingPackage(), contentValues);
        if (mapsToProfileDbWithInsertedValues(uri, contentValues)) {
            switchToProfileMode();
            return this.mProfileProvider.insert(uri, contentValues);
        }
        switchToContactMode();
        return super.insert(uri, contentValues);
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider, android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        waitForAccess(this.mWriteAccessLatch);
        this.mContactsHelper.validateContentValues(getCallingPackage(), contentValues);
        this.mContactsHelper.validateSql(getCallingPackage(), str);
        if (mapsToProfileDb(uri)) {
            switchToProfileMode();
            return this.mProfileProvider.update(uri, contentValues, str, strArr);
        }
        switchToContactMode();
        return super.update(uri, contentValues, str, strArr);
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider, android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        waitForAccess(this.mWriteAccessLatch);
        this.mContactsHelper.validateSql(getCallingPackage(), str);
        if (mapsToProfileDb(uri)) {
            switchToProfileMode();
            return this.mProfileProvider.delete(uri, str, strArr);
        }
        switchToContactMode();
        return super.delete(uri, str, strArr);
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        waitForAccess(this.mReadAccessLatch);
        switchToContactMode();
        if ("authorize".equals(str)) {
            Uri uri = (Uri) bundle.getParcelable("uri_to_authorize");
            ContactsPermissions.enforceCallingOrSelfPermission(getContext(), "android.permission.READ_CONTACTS");
            Uri uriPreAuthorizeUri = preAuthorizeUri(uri);
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("authorized_uri", uriPreAuthorizeUri);
            return bundle2;
        }
        if (!"undemote".equals(str)) {
            return null;
        }
        ContactsPermissions.enforceCallingOrSelfPermission(getContext(), "android.permission.WRITE_CONTACTS");
        try {
            undemoteContact(this.mDbHelper.get().getWritableDatabase(), Long.valueOf(str2).longValue());
            return null;
        } catch (NumberFormatException unused) {
            throw new IllegalArgumentException("Contact ID must be a valid long number.");
        }
    }

    private Uri preAuthorizeUri(Uri uri) {
        Uri uriBuild = uri.buildUpon().appendQueryParameter("perm_token", String.valueOf(this.mRandom.nextLong())).build();
        long jCurrentTimeMillis = Clock.getInstance().currentTimeMillis() + this.mPreAuthorizedUriDuration;
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("expiration", Long.valueOf(jCurrentTimeMillis));
        contentValues.put("uri", uriBuild.toString());
        writableDatabase.insert("pre_authorized_uris", null, contentValues);
        return uriBuild;
    }

    public boolean isValidPreAuthorizedUri(Uri uri) {
        if (uri.getQueryParameter("perm_token") == null) {
            return false;
        }
        long jCurrentTimeMillis = Clock.getInstance().currentTimeMillis();
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        writableDatabase.beginTransactionNonExclusive();
        try {
            writableDatabase.delete("pre_authorized_uris", "expiration < ?1", new String[]{String.valueOf(jCurrentTimeMillis)});
            boolean z = writableDatabase.query("pre_authorized_uris", null, "uri=?1", new String[]{uri.toString()}, null, null, null).getCount() != 0;
            writableDatabase.setTransactionSuccessful();
            return z;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider
    protected boolean yield(ContactsTransaction contactsTransaction) {
        SQLiteDatabase sQLiteDatabaseRemoveDbForTag = contactsTransaction.removeDbForTag("profile");
        if (sQLiteDatabaseRemoveDbForTag != null) {
            sQLiteDatabaseRemoveDbForTag.setTransactionSuccessful();
            sQLiteDatabaseRemoveDbForTag.endTransaction();
        }
        SQLiteDatabase dbForTag = contactsTransaction.getDbForTag("contacts");
        return dbForTag != null && dbForTag.yieldIfContendedSafely(4000L);
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider, android.content.ContentProvider
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        waitForAccess(this.mWriteAccessLatch);
        return super.applyBatch(arrayList);
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider, android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        waitForAccess(this.mWriteAccessLatch);
        return super.bulkInsert(uri, contentValuesArr);
    }

    @Override // android.database.sqlite.SQLiteTransactionListener
    public void onBegin() {
        onBeginTransactionInternal(false);
    }

    protected void onBeginTransactionInternal(boolean z) {
        if (z) {
            switchToProfileMode();
            this.mProfileAggregator.clearPendingAggregations();
            this.mProfileTransactionContext.clearExceptSearchIndexUpdates();
        } else {
            switchToContactMode();
            this.mContactAggregator.clearPendingAggregations();
            this.mContactTransactionContext.clearExceptSearchIndexUpdates();
        }
    }

    @Override // android.database.sqlite.SQLiteTransactionListener
    public void onCommit() {
        onCommitTransactionInternal(false);
    }

    protected void onCommitTransactionInternal(boolean z) {
        if (z) {
            switchToProfileMode();
        } else {
            switchToContactMode();
        }
        flushTransactionalChanges();
        this.mAggregator.get().aggregateInTransaction(this.mTransactionContext.get(), this.mDbHelper.get().getWritableDatabase());
        if (this.mVisibleTouched) {
            this.mVisibleTouched = false;
            this.mDbHelper.get().updateAllVisible();
            invalidateFastScrollingIndexCache();
        }
        updateSearchIndexInTransaction();
        if (this.mProviderStatusUpdateNeeded) {
            updateProviderStatus();
            this.mProviderStatusUpdateNeeded = false;
        }
    }

    @Override // android.database.sqlite.SQLiteTransactionListener
    public void onRollback() {
        onRollbackTransactionInternal(false);
    }

    protected void onRollbackTransactionInternal(boolean z) {
        if (z) {
            switchToProfileMode();
        } else {
            switchToContactMode();
        }
    }

    private void updateSearchIndexInTransaction() {
        Set<Long> staleSearchIndexContactIds = this.mTransactionContext.get().getStaleSearchIndexContactIds();
        Set<Long> staleSearchIndexRawContactIds = this.mTransactionContext.get().getStaleSearchIndexRawContactIds();
        if (staleSearchIndexContactIds.isEmpty() && staleSearchIndexRawContactIds.isEmpty()) {
            return;
        }
        this.mSearchIndexManager.updateIndexForRawContacts(staleSearchIndexContactIds, staleSearchIndexRawContactIds);
        this.mTransactionContext.get().clearSearchIndexUpdates();
    }

    private void flushTransactionalChanges() {
        if (AbstractContactsProvider.VERBOSE_LOGGING) {
            StringBuilder sb = new StringBuilder();
            sb.append("flushTransactionalChanges: ");
            sb.append(inProfileMode() ? "profile" : "contacts");
            Log.v("ContactsProvider", sb.toString());
        }
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        Iterator<Long> it = this.mTransactionContext.get().getInsertedRawContactIds().iterator();
        while (it.hasNext()) {
            long jLongValue = it.next().longValue();
            this.mDbHelper.get().updateRawContactDisplayName(writableDatabase, jLongValue);
            this.mAggregator.get().onRawContactInsert(this.mTransactionContext.get(), writableDatabase, jLongValue);
        }
        Set<Long> dirtyRawContactIds = this.mTransactionContext.get().getDirtyRawContactIds();
        if (!dirtyRawContactIds.isEmpty()) {
            this.mSb.setLength(0);
            this.mSb.append("UPDATE raw_contacts SET dirty=1 WHERE _id IN (");
            appendIds(this.mSb, dirtyRawContactIds);
            this.mSb.append(")");
            writableDatabase.execSQL(this.mSb.toString());
        }
        Set<Long> updatedRawContactIds = this.mTransactionContext.get().getUpdatedRawContactIds();
        if (!updatedRawContactIds.isEmpty()) {
            this.mSb.setLength(0);
            this.mSb.append("UPDATE raw_contacts SET version = version + 1 WHERE _id IN (");
            appendIds(this.mSb, updatedRawContactIds);
            this.mSb.append(")");
            writableDatabase.execSQL(this.mSb.toString());
        }
        ContactsTableUtil.updateContactLastUpdateByRawContactId(writableDatabase, this.mTransactionContext.get().getChangedRawContactIds());
        for (Map.Entry<Long, Object> entry : this.mTransactionContext.get().getUpdatedSyncStates()) {
            if (this.mDbHelper.get().getSyncState().update(writableDatabase, entry.getKey().longValue(), entry.getValue()) <= 0) {
                throw new IllegalStateException("unable to update sync state, does it still exist?");
            }
        }
        this.mTransactionContext.get().clearExceptSearchIndexUpdates();
    }

    private void appendIds(StringBuilder sb, Set<Long> set) {
        Iterator<Long> it = set.iterator();
        while (it.hasNext()) {
            sb.append(it.next().longValue());
            sb.append(',');
        }
        sb.setLength(sb.length() - 1);
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider
    protected void notifyChange() {
        notifyChange(this.mSyncToNetwork, this.mSyncToMetadataNetWork);
        this.mSyncToNetwork = false;
        this.mSyncToMetadataNetWork = false;
    }

    protected void notifyChange(boolean z, boolean z2) {
        getContext().getContentResolver().notifyChange(ContactsContract.AUTHORITY_URI, (ContentObserver) null, z);
    }

    protected void setProviderStatus(int i) {
        if (this.mProviderStatus != i) {
            this.mProviderStatus = i;
            ContactsDatabaseHelper.notifyProviderStatusChange(getContext());
        }
    }

    public DataRowHandler getDataRowHandler(String str) {
        if (inProfileMode()) {
            return getDataRowHandlerForProfile(str);
        }
        DataRowHandler dataRowHandler = this.mDataRowHandlers.get(str);
        if (dataRowHandler != null) {
            return dataRowHandler;
        }
        DataRowHandlerForCustomMimetype dataRowHandlerForCustomMimetype = new DataRowHandlerForCustomMimetype(getContext(), this.mContactsHelper, this.mContactAggregator, str);
        this.mDataRowHandlers.put(str, dataRowHandlerForCustomMimetype);
        return dataRowHandlerForCustomMimetype;
    }

    public DataRowHandler getDataRowHandlerForProfile(String str) {
        DataRowHandler dataRowHandler = this.mProfileDataRowHandlers.get(str);
        if (dataRowHandler != null) {
            return dataRowHandler;
        }
        DataRowHandlerForCustomMimetype dataRowHandlerForCustomMimetype = new DataRowHandlerForCustomMimetype(getContext(), this.mProfileHelper, this.mProfileAggregator, str);
        this.mProfileDataRowHandlers.put(str, dataRowHandlerForCustomMimetype);
        return dataRowHandlerForCustomMimetype;
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider
    protected Uri insertInTransaction(Uri uri, ContentValues contentValues) throws Throwable {
        long jInsertRawContact;
        if (AbstractContactsProvider.VERBOSE_LOGGING) {
            Log.v("ContactsProvider", "insertInTransaction: uri=" + uri + "  values=[" + contentValues + "] CPID=" + Binder.getCallingPid() + " CUID=" + Binder.getCallingUid());
        }
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        boolean booleanQueryParameter = readBooleanQueryParameter(uri, "caller_is_syncadapter", false);
        int iMatch = sUriMatcher.match(uri);
        switch (iMatch) {
            case 1000:
                invalidateFastScrollingIndexCache();
                insertContact(contentValues);
                throw null;
            case 2002:
            case 19005:
                invalidateFastScrollingIndexCache();
                jInsertRawContact = insertRawContact(uri, contentValues, booleanQueryParameter);
                this.mSyncToNetwork |= !booleanQueryParameter;
                break;
            case 2004:
            case 19007:
                invalidateFastScrollingIndexCache();
                contentValues.put("raw_contact_id", uri.getPathSegments().get(iMatch == 2004 ? 1 : 2));
                jInsertRawContact = insertData(contentValues, booleanQueryParameter);
                this.mSyncToNetwork |= !booleanQueryParameter;
                break;
            case 2007:
                contentValues.put("raw_contact_id", uri.getPathSegments().get(1));
                jInsertRawContact = insertStreamItem(uri, contentValues);
                this.mSyncToNetwork |= !booleanQueryParameter;
                break;
            case 3000:
            case 19002:
                invalidateFastScrollingIndexCache();
                jInsertRawContact = insertData(contentValues, booleanQueryParameter);
                this.mSyncToNetwork |= !booleanQueryParameter;
                break;
            case 7000:
            case 19009:
                jInsertRawContact = insertStatusUpdate(contentValues);
                break;
            case 9000:
                jInsertRawContact = insertSettings(contentValues);
                this.mSyncToNetwork |= !booleanQueryParameter;
                break;
            case 10000:
                jInsertRawContact = insertGroup(uri, contentValues, booleanQueryParameter);
                this.mSyncToNetwork |= !booleanQueryParameter;
                break;
            case 11000:
            case 11002:
                jInsertRawContact = this.mDbHelper.get().getSyncState().insert(writableDatabase, contentValues);
                break;
            case 19000:
                throw new UnsupportedOperationException("The profile contact is created automatically");
            case 21000:
                jInsertRawContact = insertStreamItem(uri, contentValues);
                this.mSyncToNetwork |= !booleanQueryParameter;
                break;
            case 21001:
                jInsertRawContact = insertStreamItemPhoto(uri, contentValues);
                this.mSyncToNetwork |= !booleanQueryParameter;
                break;
            case 21003:
                contentValues.put("stream_item_id", uri.getPathSegments().get(1));
                jInsertRawContact = insertStreamItemPhoto(uri, contentValues);
                this.mSyncToNetwork |= !booleanQueryParameter;
                break;
            default:
                this.mSyncToNetwork = true;
                return this.mLegacyApiSupport.insert(uri, contentValues);
        }
        if (jInsertRawContact < 0) {
            return null;
        }
        return ContentUris.withAppendedId(uri, jInsertRawContact);
    }

    private Account resolveAccount(Uri uri, ContentValues contentValues) throws IllegalArgumentException {
        String queryParameter = getQueryParameter(uri, "account_name");
        String queryParameter2 = getQueryParameter(uri, "account_type");
        boolean zIsEmpty = TextUtils.isEmpty(queryParameter) ^ TextUtils.isEmpty(queryParameter2);
        String asString = contentValues.getAsString("account_name");
        String asString2 = contentValues.getAsString("account_type");
        boolean zIsEmpty2 = TextUtils.isEmpty(asString) ^ TextUtils.isEmpty(asString2);
        if (zIsEmpty || zIsEmpty2) {
            throw new IllegalArgumentException(this.mDbHelper.get().exceptionMessage("Must specify both or neither of ACCOUNT_NAME and ACCOUNT_TYPE", uri));
        }
        boolean z = !TextUtils.isEmpty(queryParameter);
        boolean z2 = !TextUtils.isEmpty(asString);
        if (z2 && z) {
            if (!(TextUtils.equals(queryParameter, asString) && TextUtils.equals(queryParameter2, asString2))) {
                throw new IllegalArgumentException(this.mDbHelper.get().exceptionMessage("When both specified, ACCOUNT_NAME and ACCOUNT_TYPE must match", uri));
            }
        } else if (z) {
            contentValues.put("account_name", queryParameter);
            contentValues.put("account_type", queryParameter2);
        } else {
            if (!z2) {
                return null;
            }
            queryParameter = asString;
            queryParameter2 = asString2;
        }
        Account account = this.mAccount;
        if (account == null || !account.name.equals(queryParameter) || !this.mAccount.type.equals(queryParameter2)) {
            this.mAccount = new Account(queryParameter, queryParameter2);
        }
        return this.mAccount;
    }

    private AccountWithDataSet resolveAccountWithDataSet(Uri uri, ContentValues contentValues) {
        Account accountResolveAccount = resolveAccount(uri, contentValues);
        if (accountResolveAccount == null) {
            return null;
        }
        String queryParameter = getQueryParameter(uri, "data_set");
        if (queryParameter == null) {
            queryParameter = contentValues.getAsString("data_set");
        } else {
            contentValues.put("data_set", queryParameter);
        }
        return AccountWithDataSet.get(accountResolveAccount.name, accountResolveAccount.type, queryParameter);
    }

    private long insertContact(ContentValues contentValues) {
        throw new UnsupportedOperationException("Aggregate contacts are created automatically");
    }

    private long insertRawContact(Uri uri, ContentValues contentValues, boolean z) {
        ContentValues contentValues2 = new ContentValues(fixUpUsageColumnsForEdit(contentValues));
        contentValues2.putNull("contact_id");
        long jReplaceAccountInfoByAccountId = replaceAccountInfoByAccountId(uri, contentValues2);
        if (flagIsSet(contentValues2, "deleted")) {
            contentValues2.put("aggregation_mode", (Integer) 3);
        }
        if (!contentValues2.containsKey("pinned")) {
            contentValues2.put("pinned", (Integer) 0);
        }
        long jInsert = this.mDbHelper.get().getWritableDatabase().insert("raw_contacts", "contact_id", contentValues2);
        this.mAggregator.get().markNewForAggregation(jInsert, getIntValue(contentValues2, "aggregation_mode", 0));
        this.mTransactionContext.get().rawContactInserted(jInsert, jReplaceAccountInfoByAccountId);
        if (!z) {
            addAutoAddMembership(jInsert);
            if (flagIsSet(contentValues2, "starred")) {
                updateFavoritesMembership(jInsert, true);
            }
        }
        this.mProviderStatusUpdateNeeded = true;
        return jInsert;
    }

    private void addAutoAddMembership(long j) {
        Long lFindGroupByRawContactId = findGroupByRawContactId("raw_contacts._id=? AND groups.account_id=raw_contacts.account_id AND auto_add != 0", j);
        if (lFindGroupByRawContactId != null) {
            insertDataGroupMembership(j, lFindGroupByRawContactId.longValue());
        }
    }

    private Long findGroupByRawContactId(String str, long j) {
        Cursor cursorQuery = this.mDbHelper.get().getReadableDatabase().query("groups,raw_contacts", PROJECTION_GROUP_ID, str, new String[]{Long.toString(j)}, null, null, null);
        try {
            if (cursorQuery.moveToNext()) {
                return Long.valueOf(cursorQuery.getLong(0));
            }
            return null;
        } finally {
            cursorQuery.close();
        }
    }

    private void updateFavoritesMembership(long j, boolean z) {
        Long lFindGroupByRawContactId = findGroupByRawContactId("raw_contacts._id=? AND groups.account_id=raw_contacts.account_id AND favorites != 0", j);
        if (lFindGroupByRawContactId != null) {
            if (z) {
                insertDataGroupMembership(j, lFindGroupByRawContactId.longValue());
            } else {
                deleteDataGroupMembership(j, lFindGroupByRawContactId.longValue());
            }
        }
    }

    private void insertDataGroupMembership(long j, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("data1", Long.valueOf(j2));
        contentValues.put("raw_contact_id", Long.valueOf(j));
        contentValues.put("mimetype_id", Long.valueOf(this.mDbHelper.get().getMimeTypeId("vnd.android.cursor.item/group_membership")));
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        getDataRowHandler("vnd.android.cursor.item/group_membership").handleHashIdForInsert(contentValues);
        writableDatabase.insert("data", null, contentValues);
    }

    private void deleteDataGroupMembership(long j, long j2) {
        this.mDbHelper.get().getWritableDatabase().delete("data", "mimetype_id=? AND data1=? AND raw_contact_id=?", new String[]{Long.toString(this.mDbHelper.get().getMimeTypeId("vnd.android.cursor.item/group_membership")), Long.toString(j2), Long.toString(j)});
    }

    private long insertData(ContentValues contentValues, boolean z) {
        Long asLong = contentValues.getAsLong("raw_contact_id");
        if (asLong == null) {
            throw new IllegalArgumentException("raw_contact_id is required");
        }
        String asString = contentValues.getAsString("mimetype");
        if (TextUtils.isEmpty(asString)) {
            throw new IllegalArgumentException("mimetype is required");
        }
        if ("vnd.android.cursor.item/phone_v2".equals(asString)) {
            maybeTrimLongPhoneNumber(contentValues);
        }
        ContentValues contentValues2 = new ContentValues(contentValues);
        replacePackageNameByPackageId(contentValues2);
        contentValues2.put("mimetype_id", Long.valueOf(this.mDbHelper.get().getMimeTypeId(asString)));
        contentValues2.remove("mimetype");
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        TransactionContext transactionContext = this.mTransactionContext.get();
        long jInsert = getDataRowHandler(asString).insert(writableDatabase, transactionContext, asLong.longValue(), contentValues2);
        transactionContext.markRawContactDirtyAndChanged(asLong.longValue(), z);
        transactionContext.rawContactUpdated(asLong.longValue());
        return jInsert;
    }

    private long insertStreamItem(Uri uri, ContentValues contentValues) {
        Long asLong = contentValues.getAsLong("raw_contact_id");
        if (asLong == null) {
            throw new IllegalArgumentException("raw_contact_id is required");
        }
        ContentValues contentValues2 = new ContentValues(contentValues);
        contentValues2.remove("account_name");
        contentValues2.remove("account_type");
        long jInsert = this.mDbHelper.get().getWritableDatabase().insert("stream_items", null, contentValues2);
        if (jInsert == -1) {
            return 0L;
        }
        return cleanUpOldStreamItems(asLong.longValue(), jInsert);
    }

    private long insertStreamItemPhoto(Uri uri, ContentValues contentValues) {
        Long asLong = contentValues.getAsLong("stream_item_id");
        if (asLong == null || asLong.longValue() == 0) {
            return 0L;
        }
        ContentValues contentValues2 = new ContentValues(contentValues);
        contentValues2.remove("account_name");
        contentValues2.remove("account_type");
        if (processStreamItemPhoto(contentValues2, false)) {
            return this.mDbHelper.get().getWritableDatabase().insert("stream_item_photos", null, contentValues2);
        }
        return 0L;
    }

    private boolean processStreamItemPhoto(ContentValues contentValues, boolean z) {
        byte[] asByteArray = contentValues.getAsByteArray("photo");
        if (asByteArray == null) {
            return z;
        }
        IOException iOException = null;
        try {
            long jInsert = this.mPhotoStore.get().insert(new PhotoProcessor(asByteArray, getMaxDisplayPhotoDim(), getMaxThumbnailDim(), true), true);
            if (jInsert != 0) {
                contentValues.put("photo_file_id", Long.valueOf(jInsert));
                contentValues.remove("photo");
                return true;
            }
        } catch (IOException e) {
            iOException = e;
        }
        Log.e("ContactsProvider", "Could not process stream item photo for insert", iOException);
        return false;
    }

    private long cleanUpOldStreamItems(long j, long j2) {
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        Cursor cursorQuery = writableDatabase.query("stream_items", new String[]{"_id"}, "raw_contact_id=?", new String[]{String.valueOf(j)}, null, null, "timestamp DESC, _id DESC");
        try {
            if (cursorQuery.getCount() <= 5) {
                return j2;
            }
            cursorQuery.moveToLast();
            long j3 = j2;
            while (cursorQuery.getPosition() >= 5) {
                if (j2 == cursorQuery.getLong(0)) {
                    j3 = 0;
                }
                deleteStreamItem(writableDatabase, cursorQuery.getLong(0));
                cursorQuery.moveToPrevious();
            }
            return j3;
        } finally {
            cursorQuery.close();
        }
    }

    private int deleteData(String str, String[] strArr, boolean z) {
        Uri uriWithAppendedPath;
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        if (inProfileMode()) {
            uriWithAppendedPath = Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, "data");
        } else {
            uriWithAppendedPath = ContactsContract.Data.CONTENT_URI;
        }
        Cursor cursorQuery = query(uriWithAppendedPath, DataRowHandler.DataDeleteQuery.COLUMNS, str, strArr, null);
        int iDelete = 0;
        while (cursorQuery.moveToNext()) {
            try {
                long j = cursorQuery.getLong(2);
                iDelete += getDataRowHandler(cursorQuery.getString(1)).delete(writableDatabase, this.mTransactionContext.get(), cursorQuery);
                this.mTransactionContext.get().markRawContactDirtyAndChanged(j, z);
            } finally {
                cursorQuery.close();
            }
        }
        return iDelete;
    }

    public int deleteData(long j, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        boolean z = false;
        this.mSelectionArgs1[0] = String.valueOf(j);
        Cursor cursorQuery = query(ContactsContract.Data.CONTENT_URI, DataRowHandler.DataDeleteQuery.COLUMNS, "_id=?", this.mSelectionArgs1, null);
        try {
            if (!cursorQuery.moveToFirst()) {
                return 0;
            }
            String string = cursorQuery.getString(1);
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (TextUtils.equals(string, strArr[i])) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                throw new IllegalArgumentException("Data type mismatch: expected " + Lists.newArrayList(strArr));
            }
            return getDataRowHandler(string).delete(writableDatabase, this.mTransactionContext.get(), cursorQuery);
        } finally {
            cursorQuery.close();
        }
    }

    private long insertGroup(Uri uri, ContentValues contentValues, boolean z) {
        long j;
        ContentValues contentValues2 = new ContentValues(contentValues);
        long jReplaceAccountInfoByAccountId = replaceAccountInfoByAccountId(uri, contentValues2);
        replacePackageNameByPackageId(contentValues2);
        if (!z) {
            contentValues2.put("dirty", (Integer) 1);
        }
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        long jInsert = writableDatabase.insert("groups", "title", contentValues2);
        boolean zFlagIsSet = flagIsSet(contentValues2, "favorites");
        if (z || !zFlagIsSet) {
            j = jInsert;
        } else {
            this.mSelectionArgs1[0] = Long.toString(jReplaceAccountInfoByAccountId);
            long j2 = jInsert;
            Cursor cursorQuery = writableDatabase.query("raw_contacts", new String[]{"_id", "starred"}, "raw_contacts.account_id=?", this.mSelectionArgs1, null, null, null);
            while (cursorQuery.moveToNext()) {
                try {
                    if (cursorQuery.getLong(1) != 0) {
                        long j3 = cursorQuery.getLong(0);
                        long j4 = j2;
                        insertDataGroupMembership(j3, j4);
                        this.mTransactionContext.get().markRawContactDirtyAndChanged(j3, z);
                        j2 = j4;
                    }
                } finally {
                    cursorQuery.close();
                }
            }
            j = j2;
        }
        if (contentValues2.containsKey("group_visible")) {
            this.mVisibleTouched = true;
        }
        return j;
    }

    private long insertSettings(ContentValues contentValues) {
        String[] strArr;
        String asString = contentValues.getAsString("account_name");
        String asString2 = contentValues.getAsString("account_type");
        String asString3 = contentValues.getAsString("data_set");
        Uri.Builder builderBuildUpon = ContactsContract.Settings.CONTENT_URI.buildUpon();
        if (asString != null) {
            builderBuildUpon.appendQueryParameter("account_name", asString);
        }
        if (asString2 != null) {
            builderBuildUpon.appendQueryParameter("account_type", asString2);
        }
        if (asString3 != null) {
            builderBuildUpon.appendQueryParameter("data_set", asString3);
        }
        Cursor cursorQueryLocal = queryLocal(builderBuildUpon.build(), null, null, null, null, 0L, null);
        try {
            String str = null;
            if (cursorQueryLocal.getCount() > 0) {
                if (asString == null || asString2 == null) {
                    strArr = null;
                } else if (asString3 == null) {
                    strArr = new String[]{asString, asString2};
                    str = "account_name=? AND account_type=? AND data_set IS NULL";
                } else {
                    str = "account_name=? AND account_type=? AND data_set=?";
                    strArr = new String[]{asString, asString2, asString3};
                }
                return updateSettings(contentValues, str, strArr);
            }
            cursorQueryLocal.close();
            long jInsert = this.mDbHelper.get().getWritableDatabase().insert("settings", null, contentValues);
            if (contentValues.containsKey("ungrouped_visible")) {
                this.mVisibleTouched = true;
            }
            return jInsert;
        } finally {
            cursorQueryLocal.close();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:99:0x031c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private long insertStatusUpdate(android.content.ContentValues r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 836
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.ContactsProvider2.insertStatusUpdate(android.content.ContentValues):long");
    }

    private String statusUpdateToHtml(String str) {
        return TextUtils.htmlEncode(str);
    }

    private String getResourceName(Resources resources, String str, Integer num) {
        if (num != null) {
            try {
                if (num.intValue() != 0) {
                    String resourceEntryName = resources.getResourceEntryName(num.intValue());
                    String resourceTypeName = resources.getResourceTypeName(num.intValue());
                    if (str.equals(resourceTypeName)) {
                        return resourceEntryName;
                    }
                    Log.w("ContactsProvider", "Resource " + num + " (" + resourceEntryName + ") is of type " + resourceTypeName + " but " + str + " is required.");
                    return null;
                }
            } catch (Resources.NotFoundException unused) {
            }
        }
        return null;
    }

    @Override // com.android.providers.contacts.AbstractContactsProvider
    protected int deleteInTransaction(Uri uri, String str, String[] strArr) {
        Cursor cursorQuery;
        char c;
        String[] strArr2;
        if (AbstractContactsProvider.VERBOSE_LOGGING) {
            Log.v("ContactsProvider", "deleteInTransaction: uri=" + uri + "  selection=[" + str + "]  args=" + Arrays.toString(strArr) + " CPID=" + Binder.getCallingPid() + " CUID=" + Binder.getCallingUid() + " User=" + UserUtils.getCurrentUserHandle(getContext()));
        }
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        flushTransactionalChanges();
        boolean booleanQueryParameter = readBooleanQueryParameter(uri, "caller_is_syncadapter", false);
        int iMatch = sUriMatcher.match(uri);
        if (iMatch == 1026) {
            return deleteDataUsage(writableDatabase);
        }
        if (iMatch != 2008) {
            if (iMatch != 3003 && iMatch != 3006 && iMatch != 3010 && iMatch != 3012) {
                if (iMatch != 7000) {
                    if (iMatch == 9000) {
                        this.mSyncToNetwork |= !booleanQueryParameter;
                        return deleteSettings(appendAccountToSelection(uri, str), strArr);
                    }
                    if (iMatch != 19009) {
                        if (iMatch != 21000) {
                            if (iMatch != 2002) {
                                if (iMatch != 2003) {
                                    if (iMatch != 3000) {
                                        if (iMatch != 3001) {
                                            if (iMatch == 10000) {
                                                cursorQuery = writableDatabase.query("view_groups", ContactsDatabaseHelper.Projections.ID, appendAccountIdToSelection(uri, str), strArr, null, null, null);
                                                int iDeleteGroup = 0;
                                                while (cursorQuery.moveToNext()) {
                                                    try {
                                                        iDeleteGroup += deleteGroup(uri, cursorQuery.getLong(0), booleanQueryParameter);
                                                    } finally {
                                                    }
                                                }
                                                if (iDeleteGroup > 0) {
                                                    this.mSyncToNetwork |= !booleanQueryParameter;
                                                }
                                                return iDeleteGroup;
                                            }
                                            if (iMatch == 10001) {
                                                this.mSyncToNetwork |= !booleanQueryParameter;
                                                return deleteGroup(uri, ContentUris.parseId(uri), booleanQueryParameter);
                                            }
                                            if (iMatch != 19002) {
                                                if (iMatch != 19003) {
                                                    if (iMatch != 19005) {
                                                        if (iMatch != 19006) {
                                                            switch (iMatch) {
                                                                case 1000:
                                                                    invalidateFastScrollingIndexCache();
                                                                    return 0;
                                                                case 1001:
                                                                    invalidateFastScrollingIndexCache();
                                                                    return deleteContact(ContentUris.parseId(uri), booleanQueryParameter);
                                                                case 1002:
                                                                    invalidateFastScrollingIndexCache();
                                                                    List<String> pathSegments = uri.getPathSegments();
                                                                    if (pathSegments.size() < 3) {
                                                                        throw new IllegalArgumentException(this.mDbHelper.get().exceptionMessage("Missing a lookup key", uri));
                                                                    }
                                                                    return deleteContact(lookupContactIdByLookupKey(writableDatabase, pathSegments.get(2)), booleanQueryParameter);
                                                                case 1003:
                                                                    invalidateFastScrollingIndexCache();
                                                                    String str2 = uri.getPathSegments().get(2);
                                                                    SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
                                                                    setTablesAndProjectionMapForContacts(sQLiteQueryBuilder, null);
                                                                    long id = ContentUris.parseId(uri);
                                                                    if (strArr == null) {
                                                                        strArr2 = new String[2];
                                                                        c = 0;
                                                                    } else {
                                                                        String[] strArr3 = new String[strArr.length + 2];
                                                                        c = 0;
                                                                        System.arraycopy(strArr, 0, strArr3, 2, strArr.length);
                                                                        strArr2 = strArr3;
                                                                    }
                                                                    strArr2[c] = String.valueOf(id);
                                                                    strArr2[1] = Uri.encode(str2);
                                                                    sQLiteQueryBuilder.appendWhere("_id=? AND lookup=?");
                                                                    Cursor cursorDoQuery = doQuery(writableDatabase, sQLiteQueryBuilder, null, str, strArr2, null, null, null, null, null);
                                                                    try {
                                                                        if (cursorDoQuery.getCount() == 1) {
                                                                            return deleteContact(id, booleanQueryParameter);
                                                                        }
                                                                        cursorDoQuery.close();
                                                                        return 0;
                                                                    } finally {
                                                                        cursorDoQuery.close();
                                                                    }
                                                                default:
                                                                    String str3 = "";
                                                                    switch (iMatch) {
                                                                        case 11000:
                                                                        case 11002:
                                                                            return this.mDbHelper.get().getSyncState().delete(writableDatabase, str, strArr);
                                                                        case 11001:
                                                                            StringBuilder sb = new StringBuilder();
                                                                            sb.append("_id=");
                                                                            sb.append(ContentUris.parseId(uri));
                                                                            sb.append(" ");
                                                                            if (str != null) {
                                                                                str3 = " AND (" + str + ")";
                                                                            }
                                                                            sb.append(str3);
                                                                            return this.mDbHelper.get().getSyncState().delete(writableDatabase, sb.toString(), strArr);
                                                                        case 11003:
                                                                            StringBuilder sb2 = new StringBuilder();
                                                                            sb2.append("_id=");
                                                                            sb2.append(ContentUris.parseId(uri));
                                                                            sb2.append(" ");
                                                                            if (str != null) {
                                                                                str3 = " AND (" + str + ")";
                                                                            }
                                                                            sb2.append(str3);
                                                                            return this.mProfileHelper.getSyncState().delete(writableDatabase, sb2.toString(), strArr);
                                                                        default:
                                                                            switch (iMatch) {
                                                                                case 21002:
                                                                                    this.mSyncToNetwork |= !booleanQueryParameter;
                                                                                    return deleteStreamItems("_id=?", new String[]{uri.getLastPathSegment()});
                                                                                case 21003:
                                                                                    this.mSyncToNetwork |= !booleanQueryParameter;
                                                                                    String str4 = uri.getPathSegments().get(1);
                                                                                    StringBuilder sb3 = new StringBuilder();
                                                                                    sb3.append("stream_item_id=");
                                                                                    sb3.append(str4);
                                                                                    sb3.append(" ");
                                                                                    if (str != null) {
                                                                                        str3 = " AND (" + str + ")";
                                                                                    }
                                                                                    sb3.append(str3);
                                                                                    return deleteStreamItemPhotos(sb3.toString(), strArr);
                                                                                case 21004:
                                                                                    this.mSyncToNetwork |= !booleanQueryParameter;
                                                                                    return deleteStreamItemPhotos("stream_item_photos._id=? AND stream_item_id=?", new String[]{uri.getPathSegments().get(3), uri.getPathSegments().get(1)});
                                                                                default:
                                                                                    this.mSyncToNetwork = true;
                                                                                    return this.mLegacyApiSupport.delete(uri, str, strArr);
                                                                            }
                                                                    }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    invalidateFastScrollingIndexCache();
                                    this.mSyncToNetwork |= !booleanQueryParameter;
                                    return deleteData(appendAccountToSelection(uri, str), strArr, booleanQueryParameter);
                                }
                                invalidateFastScrollingIndexCache();
                                long id2 = ContentUris.parseId(uri);
                                return deleteRawContact(id2, this.mDbHelper.get().getContactId(id2), booleanQueryParameter);
                            }
                            invalidateFastScrollingIndexCache();
                            cursorQuery = writableDatabase.query("view_raw_contacts", new String[]{"_id", "contact_id"}, appendAccountIdToSelection(uri, str), strArr, null, null, null);
                            int iDeleteRawContact = 0;
                            while (cursorQuery.moveToNext()) {
                                try {
                                    iDeleteRawContact += deleteRawContact(cursorQuery.getLong(0), cursorQuery.getLong(1), booleanQueryParameter);
                                } finally {
                                }
                            }
                            return iDeleteRawContact;
                        }
                        this.mSyncToNetwork |= !booleanQueryParameter;
                        return deleteStreamItems(str, strArr);
                    }
                }
                return deleteStatusUpdates(str, strArr);
            }
            invalidateFastScrollingIndexCache();
            long id3 = ContentUris.parseId(uri);
            this.mSyncToNetwork |= !booleanQueryParameter;
            this.mSelectionArgs1[0] = String.valueOf(id3);
            return deleteData("_id=?", this.mSelectionArgs1, booleanQueryParameter);
        }
        this.mSyncToNetwork |= !booleanQueryParameter;
        return deleteStreamItems("raw_contact_id=? AND _id=?", new String[]{uri.getPathSegments().get(1), uri.getLastPathSegment()});
    }

    public int deleteGroup(Uri uri, long j, boolean z) {
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        this.mGroupIdCache.clear();
        writableDatabase.delete("data", "mimetype_id=" + this.mDbHelper.get().getMimeTypeId("vnd.android.cursor.item/group_membership") + " AND data1=" + j, null);
        try {
            if (z) {
                return writableDatabase.delete("groups", "_id=" + j, null);
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("deleted", (Integer) 1);
            contentValues.put("dirty", (Integer) 1);
            return writableDatabase.update("groups", contentValues, "_id=" + j, null);
        } finally {
            this.mVisibleTouched = true;
        }
    }

    private int deleteSettings(String str, String[] strArr) {
        int iDelete = this.mDbHelper.get().getWritableDatabase().delete("settings", str, strArr);
        this.mVisibleTouched = true;
        return iDelete;
    }

    private int deleteContact(long j, boolean z) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        this.mSelectionArgs1[0] = Long.toString(j);
        Cursor cursorQuery = writableDatabase.query("raw_contacts", new String[]{"_id"}, "contact_id=?", this.mSelectionArgs1, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                long j2 = cursorQuery.getLong(0);
                if (rawContactIsLocal(j2)) {
                    arrayList.add(Long.valueOf(j2));
                } else {
                    markRawContactAsDeleted(writableDatabase, j2, z);
                }
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        }
        cursorQuery.close();
        this.mProviderStatusUpdateNeeded = true;
        int iDeleteContact = ContactsTableUtil.deleteContact(writableDatabase, j);
        deleteRawContactsImmediately(writableDatabase, arrayList);
        scheduleBackgroundTask(11);
        return iDeleteContact;
    }

    public int deleteRawContact(long j, long j2, boolean z) {
        int iDeleteRawContactsImmediately;
        this.mAggregator.get().invalidateAggregationExceptionCache();
        this.mProviderStatusUpdateNeeded = true;
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        Cursor cursorQuery = writableDatabase.query("stream_items", new String[]{"_id"}, "raw_contact_id=?", new String[]{String.valueOf(j)}, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                deleteStreamItem(writableDatabase, cursorQuery.getLong(0));
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        }
        cursorQuery.close();
        boolean z2 = ContactsTableUtil.deleteContactIfSingleton(writableDatabase, j) == 1;
        if (z || rawContactIsLocal(j)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Long.valueOf(j));
            iDeleteRawContactsImmediately = deleteRawContactsImmediately(writableDatabase, arrayList);
        } else {
            iDeleteRawContactsImmediately = markRawContactAsDeleted(writableDatabase, j, z);
        }
        if (!z2) {
            this.mAggregator.get().updateAggregateData(this.mTransactionContext.get(), j2);
        }
        return iDeleteRawContactsImmediately;
    }

    private int deleteRawContactsImmediately(SQLiteDatabase sQLiteDatabase, List<Long> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder((list.size() * 2) - 1);
        sb.append(" IN (?");
        arrayList.add(String.valueOf(list.get(0)));
        for (int i = 1; i < list.size(); i++) {
            sb.append(",?");
            arrayList.add(String.valueOf(list.get(i)));
        }
        sb.append(")");
        sQLiteDatabase.delete("presence", "presence_raw_contact_id" + sb.toString(), (String[]) arrayList.toArray(new String[0]));
        int iDelete = sQLiteDatabase.delete("raw_contacts", "_id" + sb.toString(), (String[]) arrayList.toArray(new String[0]));
        if (iDelete > 0) {
            Iterator<Long> it = list.iterator();
            while (it.hasNext()) {
                this.mTransactionContext.get().markRawContactChangedOrDeletedOrInserted(it.next().longValue());
            }
        }
        return iDelete;
    }

    private boolean rawContactIsLocal(long j) {
        Cursor cursorQuery = this.mDbHelper.get().getReadableDatabase().query("raw_contacts", ContactsDatabaseHelper.Projections.LITERAL_ONE, "raw_contacts._id=? AND account_id=(SELECT _id FROM accounts WHERE account_name IS NULL AND account_type IS NULL AND data_set IS NULL)", new String[]{String.valueOf(j)}, null, null, null);
        try {
            return cursorQuery.getCount() > 0;
        } finally {
            cursorQuery.close();
        }
    }

    private int deleteStatusUpdates(String str, String[] strArr) {
        if (AbstractContactsProvider.VERBOSE_LOGGING) {
            Log.v("ContactsProvider", "deleting data from status_updates for " + str);
        }
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        writableDatabase.delete("status_updates", getWhereClauseForStatusUpdatesTable(str), strArr);
        return writableDatabase.delete("presence", str, strArr);
    }

    private int deleteStreamItems(String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        Cursor cursorQuery = writableDatabase.query("view_stream_items", ContactsDatabaseHelper.Projections.ID, str, strArr, null, null, null);
        try {
            cursorQuery.moveToPosition(-1);
            int iDeleteStreamItem = 0;
            while (cursorQuery.moveToNext()) {
                iDeleteStreamItem += deleteStreamItem(writableDatabase, cursorQuery.getLong(0));
            }
            return iDeleteStreamItem;
        } finally {
            cursorQuery.close();
        }
    }

    private int deleteStreamItem(SQLiteDatabase sQLiteDatabase, long j) {
        deleteStreamItemPhotos(j);
        return sQLiteDatabase.delete("stream_items", "_id=?", new String[]{String.valueOf(j)});
    }

    private int deleteStreamItemPhotos(String str, String[] strArr) {
        return this.mDbHelper.get().getWritableDatabase().delete("stream_item_photos", str, strArr);
    }

    private int deleteStreamItemPhotos(long j) {
        return this.mDbHelper.get().getWritableDatabase().delete("stream_item_photos", "stream_item_id=?", new String[]{String.valueOf(j)});
    }

    private int markRawContactAsDeleted(SQLiteDatabase sQLiteDatabase, long j, boolean z) {
        this.mSyncToNetwork = true;
        ContentValues contentValues = new ContentValues();
        contentValues.put("deleted", (Integer) 1);
        contentValues.put("aggregation_mode", (Integer) 3);
        contentValues.put("aggregation_needed", (Integer) 1);
        contentValues.putNull("contact_id");
        contentValues.put("dirty", (Integer) 1);
        return updateRawContact(sQLiteDatabase, j, contentValues, z, false);
    }

    static int deleteDataUsage(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("UPDATE raw_contacts SET x_times_contacted=0,x_last_time_contacted=NULL");
        sQLiteDatabase.execSQL("UPDATE contacts SET x_times_contacted=0,x_last_time_contacted=NULL");
        sQLiteDatabase.delete("data_usage_stat", null, null);
        return 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0368  */
    @Override // com.android.providers.contacts.AbstractContactsProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int updateInTransaction(android.net.Uri r17, android.content.ContentValues r18, java.lang.String r19, java.lang.String[] r20) {
        /*
            Method dump skipped, instruction units count: 1014
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.ContactsProvider2.updateInTransaction(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    private void scanPackagesByUid(int i) {
        String[] packagesForUid = getContext().getPackageManager().getPackagesForUid(i);
        if (packagesForUid != null) {
            for (String str : packagesForUid) {
                onPackageChanged(str);
            }
        }
    }

    private int updateStatusUpdate(ContentValues contentValues, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        ContentValues settableColumnsForStatusUpdatesTable = getSettableColumnsForStatusUpdatesTable(contentValues);
        int iUpdate = settableColumnsForStatusUpdatesTable.size() > 0 ? writableDatabase.update("status_updates", settableColumnsForStatusUpdatesTable, getWhereClauseForStatusUpdatesTable(str), strArr) : 0;
        ContentValues settableColumnsForPresenceTable = getSettableColumnsForPresenceTable(contentValues);
        return settableColumnsForPresenceTable.size() > 0 ? writableDatabase.update("presence", settableColumnsForPresenceTable, str, strArr) : iUpdate;
    }

    private int updateStreamItems(ContentValues contentValues, String str, String[] strArr) {
        contentValues.remove("raw_contact_id");
        contentValues.remove("account_name");
        contentValues.remove("account_type");
        return this.mDbHelper.get().getWritableDatabase().update("stream_items", contentValues, str, strArr);
    }

    private int updateStreamItemPhotos(ContentValues contentValues, String str, String[] strArr) {
        contentValues.remove("stream_item_id");
        contentValues.remove("account_name");
        contentValues.remove("account_type");
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        if (processStreamItemPhoto(contentValues, true)) {
            return writableDatabase.update("stream_item_photos", contentValues, str, strArr);
        }
        return 0;
    }

    private String getWhereClauseForStatusUpdatesTable(String str) {
        this.mSb.setLength(0);
        this.mSb.append("status_update_data_id IN (SELECT Distinct presence_data_id FROM status_updates LEFT OUTER JOIN presence ON status_update_data_id = presence_data_id WHERE ");
        this.mSb.append(str);
        this.mSb.append(")");
        return this.mSb.toString();
    }

    private ContentValues getSettableColumnsForStatusUpdatesTable(ContentValues contentValues) {
        ContentValues contentValues2 = new ContentValues();
        ContactsDatabaseHelper.copyStringValue(contentValues2, "status", contentValues, "status");
        ContactsDatabaseHelper.copyStringValue(contentValues2, "status_ts", contentValues, "status_ts");
        ContactsDatabaseHelper.copyStringValue(contentValues2, "status_res_package", contentValues, "status_res_package");
        ContactsDatabaseHelper.copyStringValue(contentValues2, "status_label", contentValues, "status_label");
        ContactsDatabaseHelper.copyStringValue(contentValues2, "status_icon", contentValues, "status_icon");
        return contentValues2;
    }

    private ContentValues getSettableColumnsForPresenceTable(ContentValues contentValues) {
        ContentValues contentValues2 = new ContentValues();
        ContactsDatabaseHelper.copyStringValue(contentValues2, "mode", contentValues, "mode");
        ContactsDatabaseHelper.copyStringValue(contentValues2, "chat_capability", contentValues, "chat_capability");
        return contentValues2;
    }

    /* JADX WARN: Finally extract failed */
    private int updateGroups(ContentValues contentValues, String str, String[] strArr, boolean z) {
        HashSet hashSet;
        this.mGroupIdCache.clear();
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        ContactsDatabaseHelper contactsDatabaseHelper = this.mDbHelper.get();
        ContentValues contentValues2 = new ContentValues();
        contentValues2.putAll(contentValues);
        if (!z && !contentValues2.containsKey("dirty")) {
            contentValues2.put("dirty", (Integer) 1);
        }
        if (contentValues2.containsKey("group_visible")) {
            this.mVisibleTouched = true;
        }
        boolean zContainsKey = contentValues2.containsKey("account_name");
        boolean zContainsKey2 = contentValues2.containsKey("account_type");
        boolean zContainsKey3 = contentValues2.containsKey("data_set");
        boolean z2 = zContainsKey || zContainsKey2 || zContainsKey3;
        String asString = contentValues2.getAsString("account_name");
        String asString2 = contentValues2.getAsString("account_type");
        String asString3 = contentValues2.getAsString("data_set");
        contentValues2.remove("account_name");
        contentValues2.remove("account_type");
        contentValues2.remove("data_set");
        HashSet hashSetNewHashSet = Sets.newHashSet();
        Cursor cursorQuery = writableDatabase.query("view_groups", GroupAccountQuery.COLUMNS, str, strArr, null, null, null);
        try {
            cursorQuery.moveToPosition(-1);
            int i = 0;
            while (cursorQuery.moveToNext()) {
                this.mSelectionArgs1[0] = Long.toString(cursorQuery.getLong(0));
                String string = zContainsKey ? asString : cursorQuery.getString(2);
                String string2 = zContainsKey2 ? asString2 : cursorQuery.getString(1);
                String string3 = zContainsKey3 ? asString3 : cursorQuery.getString(3);
                if (z2) {
                    contentValues2.put("account_id", Long.valueOf(contactsDatabaseHelper.getOrCreateAccountIdInTransaction(AccountWithDataSet.get(string, string2, string3))));
                }
                int iUpdate = writableDatabase.update("groups", contentValues2, "groups._id=?", this.mSelectionArgs1);
                if (iUpdate <= 0 || TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                    hashSet = hashSetNewHashSet;
                } else {
                    Account account = new Account(string, string2);
                    hashSet = hashSetNewHashSet;
                    hashSet.add(account);
                }
                i += iUpdate;
                hashSetNewHashSet = hashSet;
            }
            HashSet hashSet2 = hashSetNewHashSet;
            cursorQuery.close();
            if (flagIsSet(contentValues2, "should_sync")) {
                Iterator it = hashSet2.iterator();
                while (it.hasNext()) {
                    ContentResolver.requestSync((Account) it.next(), "com.android.contacts", new Bundle());
                }
            }
            return i;
        } catch (Throwable th) {
            cursorQuery.close();
            throw th;
        }
    }

    private int updateSettings(ContentValues contentValues, String str, String[] strArr) {
        int iUpdate = this.mDbHelper.get().getWritableDatabase().update("settings", contentValues, str, strArr);
        if (contentValues.containsKey("ungrouped_visible")) {
            this.mVisibleTouched = true;
        }
        return iUpdate;
    }

    private int updateRawContacts(ContentValues contentValues, String str, String[] strArr, boolean z) {
        if (contentValues.containsKey("contact_id")) {
            throw new IllegalArgumentException("contact_id should not be included in content values. Contact IDs are assigned automatically");
        }
        if (!z) {
            str = DatabaseUtils.concatenateWhere(str, "raw_contact_is_read_only=0");
        }
        String str2 = str;
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        Cursor cursorQuery = writableDatabase.query("view_raw_contacts", ContactsDatabaseHelper.Projections.ID, str2, strArr, null, null, null);
        int i = 0;
        while (cursorQuery.moveToNext()) {
            try {
                updateRawContact(writableDatabase, cursorQuery.getLong(0), contentValues, z, false);
                i++;
            } finally {
                cursorQuery.close();
            }
        }
        return i;
    }

    private ContentValues fixUpUsageColumnsForEdit(ContentValues contentValues) {
        boolean zContainsKey = contentValues.containsKey("last_time_contacted");
        boolean zContainsKey2 = contentValues.containsKey("times_contacted");
        if (!zContainsKey && !zContainsKey2) {
            return contentValues;
        }
        ContentValues contentValues2 = new ContentValues(contentValues);
        if (zContainsKey) {
            contentValues2.putNull("x_last_time_contacted");
            contentValues2.remove("last_time_contacted");
        }
        if (zContainsKey2) {
            contentValues2.put("x_times_contacted", (Integer) 0);
            contentValues2.remove("times_contacted");
        }
        return contentValues2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int updateRawContact(SQLiteDatabase sQLiteDatabase, long j, ContentValues contentValues, boolean z, boolean z2) {
        int i;
        String asString;
        String asString2;
        int i2;
        long j2;
        String str;
        String str2;
        String str3;
        this.mSelectionArgs1[0] = Long.toString(j);
        ContentValues contentValuesFixUpUsageColumnsForEdit = fixUpUsageColumnsForEdit(contentValues);
        if (contentValuesFixUpUsageColumnsForEdit.size() == 0) {
            return 0;
        }
        ContactsDatabaseHelper contactsDatabaseHelper = this.mDbHelper.get();
        boolean zFlagIsClear = flagIsClear(contentValuesFixUpUsageColumnsForEdit, "deleted");
        boolean zContainsKey = contentValuesFixUpUsageColumnsForEdit.containsKey("account_name");
        boolean zContainsKey2 = contentValuesFixUpUsageColumnsForEdit.containsKey("account_type");
        boolean zContainsKey3 = contentValuesFixUpUsageColumnsForEdit.containsKey("data_set");
        boolean z3 = zContainsKey || zContainsKey2 || zContainsKey3;
        if (zFlagIsClear || z3) {
            Cursor cursorQuery = sQLiteDatabase.query("raw_contacts JOIN accounts ON (accounts._id=raw_contacts.account_id)", RawContactsQuery.COLUMNS, "raw_contacts._id = ?", this.mSelectionArgs1, null, null, null);
            try {
                String asString3 = null;
                if (cursorQuery.moveToFirst()) {
                    i2 = cursorQuery.getInt(0);
                    i = 1;
                    long j3 = cursorQuery.getLong(1);
                    String string = cursorQuery.getString(2);
                    String string2 = cursorQuery.getString(3);
                    asString2 = cursorQuery.getString(4);
                    asString = string;
                    asString3 = string2;
                    j2 = j3;
                } else {
                    i = 1;
                    asString = null;
                    asString2 = null;
                    i2 = 0;
                    j2 = 0;
                }
                if (z3) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.clear();
                    contentValues2.putAll(contentValuesFixUpUsageColumnsForEdit);
                    if (zContainsKey) {
                        str = "account_name";
                        asString3 = contentValues2.getAsString(str);
                    } else {
                        str = "account_name";
                    }
                    if (zContainsKey2) {
                        str2 = "account_type";
                        asString = contentValues2.getAsString(str2);
                    } else {
                        str2 = "account_type";
                    }
                    if (zContainsKey3) {
                        str3 = "data_set";
                        asString2 = contentValues2.getAsString(str3);
                    } else {
                        str3 = "data_set";
                    }
                    long orCreateAccountIdInTransaction = contactsDatabaseHelper.getOrCreateAccountIdInTransaction(AccountWithDataSet.get(asString3, asString, asString2));
                    contentValues2.put("account_id", Long.valueOf(orCreateAccountIdInTransaction));
                    contentValues2.remove(str);
                    contentValues2.remove(str2);
                    contentValues2.remove(str3);
                    contentValuesFixUpUsageColumnsForEdit = contentValues2;
                    j2 = orCreateAccountIdInTransaction;
                }
            } finally {
                cursorQuery.close();
            }
        } else {
            i = 1;
            i2 = 0;
            j2 = 0;
        }
        if (zFlagIsClear) {
            contentValuesFixUpUsageColumnsForEdit.put("aggregation_mode", (Integer) 0);
        }
        int iUpdate = sQLiteDatabase.update("raw_contacts", contentValuesFixUpUsageColumnsForEdit, "raw_contacts._id = ?", this.mSelectionArgs1);
        if (iUpdate != 0) {
            AbstractContactAggregator abstractContactAggregator = this.mAggregator.get();
            int intValue = getIntValue(contentValuesFixUpUsageColumnsForEdit, "aggregation_mode", 0);
            if (intValue != 0) {
                abstractContactAggregator.markForAggregation(j, intValue, false);
            }
            if (flagExists(contentValuesFixUpUsageColumnsForEdit, "starred")) {
                if (!z) {
                    updateFavoritesMembership(j, flagIsSet(contentValuesFixUpUsageColumnsForEdit, "starred"));
                    this.mTransactionContext.get().markRawContactDirtyAndChanged(j, z);
                    this.mSyncToNetwork |= !z;
                }
                abstractContactAggregator.updateStarred(j);
                abstractContactAggregator.updatePinned(j);
            } else if (!z && z3) {
                String[] strArr = new String[i];
                strArr[0] = Long.toString(j);
                updateFavoritesMembership(j, 0 != DatabaseUtils.longForQuery(sQLiteDatabase, "SELECT starred FROM raw_contacts WHERE _id=?", strArr) ? i : 0);
                this.mTransactionContext.get().markRawContactDirtyAndChanged(j, z);
                this.mSyncToNetwork |= !z;
            }
            if (flagExists(contentValuesFixUpUsageColumnsForEdit, "send_to_voicemail")) {
                abstractContactAggregator.updateSendToVoicemail(j);
            }
            if (!z && z3) {
                addAutoAddMembership(j);
            }
            if (contentValuesFixUpUsageColumnsForEdit.containsKey("sourceid")) {
                abstractContactAggregator.updateLookupKeyForRawContact(sQLiteDatabase, j);
            }
            if (zFlagIsClear && i2 == i) {
                this.mTransactionContext.get().rawContactInserted(j, j2);
            }
            this.mTransactionContext.get().markRawContactChangedOrDeletedOrInserted(j);
        }
        return iUpdate;
    }

    private int updateData(Uri uri, ContentValues contentValues, String str, String[] strArr, boolean z, boolean z2) {
        ContentValues contentValues2 = new ContentValues(contentValues);
        contentValues2.remove("_id");
        contentValues2.remove("raw_contact_id");
        contentValues2.remove("mimetype");
        String asString = contentValues.getAsString("res_package");
        if (asString != null) {
            contentValues2.remove("res_package");
            contentValues2.put("package_id", Long.valueOf(this.mDbHelper.get().getPackageId(asString)));
        }
        int iUpdateData = 0;
        Cursor cursorQueryLocal = queryLocal(uri, DataRowHandler.DataUpdateQuery.COLUMNS, !z ? DatabaseUtils.concatenateWhere(str, "is_read_only=0") : str, strArr, null, -1L, null);
        while (cursorQueryLocal.moveToNext()) {
            try {
                iUpdateData += updateData(contentValues2, cursorQueryLocal, z, z2);
            } finally {
                cursorQueryLocal.close();
            }
        }
        return iUpdateData;
    }

    private void maybeTrimLongPhoneNumber(ContentValues contentValues) {
        String asString = contentValues.getAsString("data1");
        if (asString == null || asString.length() <= 1000) {
            return;
        }
        contentValues.put("data1", asString.substring(0, 1000));
    }

    private int updateData(ContentValues contentValues, Cursor cursor, boolean z, boolean z2) {
        if (contentValues.size() == 0) {
            return 0;
        }
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        String string = cursor.getString(2);
        if ("vnd.android.cursor.item/phone_v2".equals(string)) {
            maybeTrimLongPhoneNumber(contentValues);
        }
        boolean zUpdate = getDataRowHandler(string).update(writableDatabase, this.mTransactionContext.get(), contentValues, cursor, z, z2);
        if ("vnd.android.cursor.item/photo".equals(string)) {
            scheduleBackgroundTask(10);
        }
        return zUpdate ? 1 : 0;
    }

    private int updateContactOptions(ContentValues contentValues, String str, String[] strArr, boolean z) {
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        Cursor cursorQuery = writableDatabase.query("view_contacts", new String[]{"_id"}, str, strArr, null, null, null);
        int i = 0;
        while (cursorQuery.moveToNext()) {
            try {
                updateContactOptions(writableDatabase, cursorQuery.getLong(0), contentValues, z);
                i++;
            } finally {
                cursorQuery.close();
            }
        }
        return i;
    }

    private int updateContactOptions(SQLiteDatabase sQLiteDatabase, long j, ContentValues contentValues, boolean z) {
        String str;
        String str2;
        int i;
        ContentValues contentValuesFixUpUsageColumnsForEdit = fixUpUsageColumnsForEdit(contentValues);
        ContentValues contentValues2 = new ContentValues();
        ContactsDatabaseHelper.copyStringValue(contentValues2, "custom_ringtone", contentValuesFixUpUsageColumnsForEdit, "custom_ringtone");
        ContactsDatabaseHelper.copyLongValue(contentValues2, "send_to_voicemail", contentValuesFixUpUsageColumnsForEdit, "send_to_voicemail");
        if (contentValuesFixUpUsageColumnsForEdit.containsKey("x_last_time_contacted")) {
            contentValues2.putNull("x_last_time_contacted");
        }
        if (contentValuesFixUpUsageColumnsForEdit.containsKey("x_times_contacted")) {
            contentValues2.put("x_times_contacted", (Integer) 0);
        }
        ContactsDatabaseHelper.copyLongValue(contentValues2, "starred", contentValuesFixUpUsageColumnsForEdit, "starred");
        ContactsDatabaseHelper.copyLongValue(contentValues2, "pinned", contentValuesFixUpUsageColumnsForEdit, "pinned");
        if (contentValues2.size() == 0) {
            return 0;
        }
        boolean zFlagExists = flagExists(contentValues2, "starred");
        flagExists(contentValues2, "pinned");
        flagExists(contentValues2, "send_to_voicemail");
        if (zFlagExists) {
            contentValues2.put("dirty", (Integer) 1);
        }
        this.mSelectionArgs1[0] = String.valueOf(j);
        sQLiteDatabase.update("raw_contacts", contentValues2, "contact_id=? AND raw_contact_is_read_only=0", this.mSelectionArgs1);
        if (z) {
            str = "pinned";
            str2 = "starred";
            i = 0;
        } else {
            str = "pinned";
            String str3 = "starred";
            i = 0;
            Cursor cursorQuery = sQLiteDatabase.query("view_raw_contacts", new String[]{"_id"}, "contact_id=?", this.mSelectionArgs1, null, null, null);
            while (cursorQuery.moveToNext()) {
                try {
                    long j2 = cursorQuery.getLong(0);
                    String str4 = str3;
                    if (zFlagExists) {
                        updateFavoritesMembership(j2, flagIsSet(contentValues2, str4));
                        this.mSyncToNetwork |= !z;
                    }
                    str3 = str4;
                } finally {
                    cursorQuery.close();
                }
            }
            str2 = str3;
        }
        contentValues2.clear();
        ContactsDatabaseHelper.copyStringValue(contentValues2, "custom_ringtone", contentValuesFixUpUsageColumnsForEdit, "custom_ringtone");
        ContactsDatabaseHelper.copyLongValue(contentValues2, "send_to_voicemail", contentValuesFixUpUsageColumnsForEdit, "send_to_voicemail");
        if (contentValuesFixUpUsageColumnsForEdit.containsKey("x_last_time_contacted")) {
            contentValues2.putNull("x_last_time_contacted");
        }
        if (contentValuesFixUpUsageColumnsForEdit.containsKey("x_times_contacted")) {
            contentValues2.put("x_times_contacted", Integer.valueOf(i));
        }
        ContactsDatabaseHelper.copyLongValue(contentValues2, str2, contentValuesFixUpUsageColumnsForEdit, str2);
        String str5 = str;
        ContactsDatabaseHelper.copyLongValue(contentValues2, str5, contentValuesFixUpUsageColumnsForEdit, str5);
        contentValues2.put("contact_last_updated_timestamp", Long.valueOf(Clock.getInstance().currentTimeMillis()));
        return sQLiteDatabase.update("contacts", contentValues2, "_id=?", this.mSelectionArgs1);
    }

    private int updateAggregationException(SQLiteDatabase sQLiteDatabase, ContentValues contentValues, boolean z) {
        long jLongValue;
        long jLongValue2;
        Integer asInteger = contentValues.getAsInteger("type");
        Long asLong = contentValues.getAsLong("raw_contact_id1");
        Long asLong2 = contentValues.getAsLong("raw_contact_id2");
        if (asInteger == null || asLong == null || asLong2 == null) {
            return 0;
        }
        if (asLong.longValue() < asLong2.longValue()) {
            jLongValue2 = asLong.longValue();
            jLongValue = asLong2.longValue();
        } else {
            jLongValue = asLong.longValue();
            jLongValue2 = asLong2.longValue();
        }
        if (asInteger.intValue() == 0) {
            this.mSelectionArgs2[0] = String.valueOf(jLongValue2);
            this.mSelectionArgs2[1] = String.valueOf(jLongValue);
            sQLiteDatabase.delete("agg_exceptions", "raw_contact_id1=? AND raw_contact_id2=?", this.mSelectionArgs2);
        } else {
            ContentValues contentValues2 = new ContentValues(3);
            contentValues2.put("type", asInteger);
            contentValues2.put("raw_contact_id1", Long.valueOf(jLongValue2));
            contentValues2.put("raw_contact_id2", Long.valueOf(jLongValue));
            sQLiteDatabase.replace("agg_exceptions", "_id", contentValues2);
        }
        AbstractContactAggregator abstractContactAggregator = this.mAggregator.get();
        abstractContactAggregator.invalidateAggregationExceptionCache();
        abstractContactAggregator.markForAggregation(jLongValue2, 0, true);
        abstractContactAggregator.markForAggregation(jLongValue, 0, true);
        abstractContactAggregator.aggregateContact(this.mTransactionContext.get(), sQLiteDatabase, jLongValue2);
        abstractContactAggregator.aggregateContact(this.mTransactionContext.get(), sQLiteDatabase, jLongValue);
        return 1;
    }

    @Override // android.accounts.OnAccountsUpdateListener
    public void onAccountsUpdated(Account[] accountArr) {
        scheduleBackgroundTask(3);
    }

    public void scheduleRescanDirectories() {
        scheduleBackgroundTask(12);
    }

    private long queryRawContactId(SQLiteDatabase sQLiteDatabase, String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        String[] strArr = this.mSelectionArgs2;
        strArr[0] = str;
        strArr[1] = String.valueOf(j);
        Cursor cursorQuery = sQLiteDatabase.query("raw_contacts", RawContactsBackupQuery.COLUMNS, "deleted=0 AND backup_id=? AND account_id=?", this.mSelectionArgs2, null, null, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getLong(0) : 0L;
        } finally {
            cursorQuery.close();
        }
    }

    private ArrayList<Long> queryDataId(SQLiteDatabase sQLiteDatabase, long j, String str) {
        if (j == 0 || TextUtils.isEmpty(str)) {
            return new ArrayList<>();
        }
        this.mSelectionArgs2[0] = String.valueOf(j);
        this.mSelectionArgs2[1] = str;
        ArrayList<Long> arrayList = new ArrayList<>();
        Cursor cursorQuery = sQLiteDatabase.query("data", DataHashQuery.COLUMNS, "raw_contact_id=? AND hash_id=?", this.mSelectionArgs2, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                arrayList.add(Long.valueOf(cursorQuery.getLong(0)));
            } finally {
                cursorQuery.close();
            }
        }
        return arrayList;
    }

    private long searchRawContactIdForRawContactInfo(SQLiteDatabase sQLiteDatabase, MetadataEntryParser.RawContactInfo rawContactInfo) {
        if (rawContactInfo == null) {
            return 0L;
        }
        String str = rawContactInfo.mBackupId;
        String str2 = rawContactInfo.mAccountType;
        String str3 = rawContactInfo.mAccountName;
        String str4 = rawContactInfo.mDataSet;
        ContentValues contentValues = new ContentValues();
        contentValues.put("account_type", str2);
        contentValues.put("account_name", str3);
        if (str4 != null) {
            contentValues.put("data_set", str4);
        }
        return queryRawContactId(sQLiteDatabase, str, replaceAccountInfoByAccountId(ContactsContract.RawContacts.CONTENT_URI, contentValues));
    }

    private Set<Long> queryAggregationRawContactIds(SQLiteDatabase sQLiteDatabase, long j) {
        this.mSelectionArgs2[0] = String.valueOf(j);
        this.mSelectionArgs2[1] = String.valueOf(j);
        ArraySet arraySet = new ArraySet();
        Cursor cursorQuery = sQLiteDatabase.query("agg_exceptions", AggregationExceptionQuery.COLUMNS, "raw_contact_id1=? OR raw_contact_id2=?", this.mSelectionArgs2, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                long j2 = cursorQuery.getLong(0);
                long j3 = cursorQuery.getLong(1);
                if (j2 != j) {
                    arraySet.add(Long.valueOf(j2));
                }
                if (j3 != j) {
                    arraySet.add(Long.valueOf(j3));
                }
            } finally {
                cursorQuery.close();
            }
        }
        return arraySet;
    }

    void updateFromMetaDataEntry(SQLiteDatabase sQLiteDatabase, MetadataEntryParser.MetadataEntry metadataEntry) {
        long jSearchRawContactIdForRawContactInfo = searchRawContactIdForRawContactInfo(sQLiteDatabase, metadataEntry.mRawContactInfo);
        if (jSearchRawContactIdForRawContactInfo == 0) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("send_to_voicemail", Integer.valueOf(metadataEntry.mSendToVoicemail));
        contentValues.put("starred", Integer.valueOf(metadataEntry.mStarred));
        contentValues.put("pinned", Integer.valueOf(metadataEntry.mPinned));
        updateRawContact(sQLiteDatabase, jSearchRawContactIdForRawContactInfo, contentValues, true, true);
        for (int i = 0; i < metadataEntry.mFieldDatas.size(); i++) {
            MetadataEntryParser.FieldData fieldData = metadataEntry.mFieldDatas.get(i);
            Iterator<Long> it = queryDataId(sQLiteDatabase, jSearchRawContactIdForRawContactInfo, fieldData.mDataHashId).iterator();
            while (it.hasNext()) {
                long jLongValue = it.next().longValue();
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("is_primary", Integer.valueOf(fieldData.mIsPrimary ? 1 : 0));
                contentValues2.put("is_super_primary", Integer.valueOf(fieldData.mIsSuperPrimary ? 1 : 0));
                updateData(ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, jLongValue), contentValues2, null, null, true, true);
                fieldData = fieldData;
            }
        }
        ArraySet arraySet = new ArraySet();
        for (int i2 = 0; i2 < metadataEntry.mAggregationDatas.size(); i2++) {
            MetadataEntryParser.AggregationData aggregationData = metadataEntry.mAggregationDatas.get(i2);
            int aggregationType = getAggregationType(aggregationData.mType, null);
            MetadataEntryParser.RawContactInfo rawContactInfo = aggregationData.mRawContactInfo1;
            MetadataEntryParser.RawContactInfo rawContactInfo2 = aggregationData.mRawContactInfo2;
            long jSearchRawContactIdForRawContactInfo2 = searchRawContactIdForRawContactInfo(sQLiteDatabase, rawContactInfo);
            long jSearchRawContactIdForRawContactInfo3 = searchRawContactIdForRawContactInfo(sQLiteDatabase, rawContactInfo2);
            if (jSearchRawContactIdForRawContactInfo2 != 0 && jSearchRawContactIdForRawContactInfo3 != 0) {
                ContentValues contentValues3 = new ContentValues();
                contentValues3.put("raw_contact_id1", Long.valueOf(jSearchRawContactIdForRawContactInfo2));
                contentValues3.put("raw_contact_id2", Long.valueOf(jSearchRawContactIdForRawContactInfo3));
                contentValues3.put("type", Integer.valueOf(aggregationType));
                updateAggregationException(sQLiteDatabase, contentValues3, true);
                if (jSearchRawContactIdForRawContactInfo2 != jSearchRawContactIdForRawContactInfo) {
                    arraySet.add(Long.valueOf(jSearchRawContactIdForRawContactInfo2));
                }
                if (jSearchRawContactIdForRawContactInfo3 != jSearchRawContactIdForRawContactInfo) {
                    arraySet.add(Long.valueOf(jSearchRawContactIdForRawContactInfo3));
                }
            }
        }
        for (Long l : com.google.common.collect.Sets.difference(queryAggregationRawContactIds(sQLiteDatabase, jSearchRawContactIdForRawContactInfo), arraySet)) {
            ContentValues contentValues4 = new ContentValues();
            contentValues4.put("raw_contact_id1", Long.valueOf(jSearchRawContactIdForRawContactInfo));
            contentValues4.put("raw_contact_id2", l);
            contentValues4.put("type", (Integer) 0);
            updateAggregationException(sQLiteDatabase, contentValues4, true);
        }
    }

    static String accountsToString(Set<Account> set) {
        StringBuilder sb = new StringBuilder();
        for (Account account : set) {
            if (sb.length() > 0) {
                sb.append("\u0001");
            }
            sb.append(account.name);
            sb.append("\u0002");
            sb.append(account.type);
        }
        return sb.toString();
    }

    static Set<Account> stringToAccounts(String str) {
        HashSet hashSetNewHashSet = Sets.newHashSet();
        if (str.length() == 0) {
            return hashSetNewHashSet;
        }
        try {
            for (String str2 : str.split("\u0001")) {
                String[] strArrSplit = str2.split("\u0002");
                hashSetNewHashSet.add(new Account(strArrSplit[0], strArrSplit[1]));
            }
            return hashSetNewHashSet;
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Malformed string", e);
        }
    }

    boolean haveAccountsChanged(Account[] accountArr) {
        try {
            return !stringToAccounts(this.mDbHelper.get().getProperty("known_accounts", "")).equals(Sets.newHashSet(accountArr));
        } catch (IllegalArgumentException unused) {
            return true;
        }
    }

    void saveAccounts(Account[] accountArr) {
        this.mDbHelper.get().setProperty("known_accounts", accountsToString(Sets.newHashSet(accountArr)));
    }

    private boolean updateAccountsInBackground(Account[] accountArr) {
        if (!haveAccountsChanged(accountArr)) {
            return false;
        }
        if (((Boolean) ContactsProperties.keep_stale_account_data().orElse(Boolean.FALSE)).booleanValue()) {
            Log.w("ContactsProvider", "Accounts changed, but not removing stale data for debug.contacts.ksad");
            return true;
        }
        Log.i("ContactsProvider", "Accounts changed");
        invalidateFastScrollingIndexCache();
        ContactsDatabaseHelper contactsDatabaseHelper = this.mDbHelper.get();
        SQLiteDatabase writableDatabase = contactsDatabaseHelper.getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            Set<AccountWithDataSet> allAccountsWithDataSets = contactsDatabaseHelper.getAllAccountsWithDataSets();
            ArrayList arrayListNewArrayList = Lists.newArrayList();
            for (AccountWithDataSet accountWithDataSet : allAccountsWithDataSets) {
                if (!accountWithDataSet.isLocalAccount() && !accountWithDataSet.inSystemAccounts(accountArr)) {
                    arrayListNewArrayList.add(accountWithDataSet);
                }
            }
            if (!arrayListNewArrayList.isEmpty()) {
                Iterator it = arrayListNewArrayList.iterator();
                while (it.hasNext()) {
                    Long accountIdOrNull = contactsDatabaseHelper.getAccountIdOrNull((AccountWithDataSet) it.next());
                    if (accountIdOrNull != null) {
                        String[] strArr = {Long.toString(accountIdOrNull.longValue())};
                        writableDatabase.execSQL("DELETE FROM groups WHERE account_id = ?", strArr);
                        writableDatabase.execSQL("DELETE FROM presence WHERE presence_raw_contact_id IN (SELECT _id FROM raw_contacts WHERE account_id = ?)", strArr);
                        writableDatabase.execSQL("DELETE FROM stream_item_photos WHERE stream_item_id IN (SELECT _id FROM stream_items WHERE raw_contact_id IN (SELECT _id FROM raw_contacts WHERE account_id=?))", strArr);
                        writableDatabase.execSQL("DELETE FROM stream_items WHERE raw_contact_id IN (SELECT _id FROM raw_contacts WHERE account_id = ?)", strArr);
                        writableDatabase.execSQL("DELETE FROM metadata_sync WHERE account_id = ?", strArr);
                        writableDatabase.execSQL("DELETE FROM metadata_sync_state WHERE account_id = ?", strArr);
                        if (!inProfileMode()) {
                            Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT raw_contacts.contact_id FROM raw_contacts WHERE account_id = ?1 AND raw_contacts.contact_id IS NOT NULL AND raw_contacts.contact_id NOT IN (    SELECT raw_contacts.contact_id    FROM raw_contacts    WHERE account_id != ?1  AND raw_contacts.contact_id    IS NOT NULL)", strArr);
                            while (cursorRawQuery.moveToNext()) {
                                try {
                                    ContactsTableUtil.deleteContact(writableDatabase, cursorRawQuery.getLong(0));
                                } finally {
                                }
                            }
                            MoreCloseables.closeQuietly(cursorRawQuery);
                            cursorRawQuery = writableDatabase.rawQuery("SELECT DISTINCT raw_contacts.contact_id FROM raw_contacts WHERE account_id = ?1 AND raw_contacts.contact_id IN (    SELECT raw_contacts.contact_id    FROM raw_contacts    WHERE account_id != ?1)", strArr);
                            while (cursorRawQuery.moveToNext()) {
                                try {
                                    ContactsTableUtil.updateContactLastUpdateByContactId(writableDatabase, cursorRawQuery.getLong(0));
                                } finally {
                                }
                            }
                            MoreCloseables.closeQuietly(cursorRawQuery);
                        }
                        writableDatabase.execSQL("DELETE FROM raw_contacts WHERE account_id = ?", strArr);
                        writableDatabase.execSQL("DELETE FROM accounts WHERE _id=?", strArr);
                    }
                }
                ArraySet arraySet = new ArraySet();
                Cursor cursorRawQuery2 = writableDatabase.rawQuery("SELECT _id FROM contacts WHERE (name_raw_contact_id NOT NULL AND name_raw_contact_id NOT IN (SELECT _id FROM raw_contacts)) OR (photo_id NOT NULL AND photo_id NOT IN (SELECT _id FROM data))", null);
                while (cursorRawQuery2.moveToNext()) {
                    try {
                        arraySet.add(Long.valueOf(cursorRawQuery2.getLong(0)));
                    } catch (Throwable th) {
                        cursorRawQuery2.close();
                        throw th;
                    }
                }
                cursorRawQuery2.close();
                Iterator it2 = arraySet.iterator();
                while (it2.hasNext()) {
                    this.mAggregator.get().updateAggregateData(this.mTransactionContext.get(), ((Long) it2.next()).longValue());
                }
                contactsDatabaseHelper.updateAllVisible();
                if (!inProfileMode()) {
                    updateSearchIndexInTransaction();
                }
            }
            removeStaleAccountRows("settings", "account_name", "account_type", accountArr);
            removeStaleAccountRows("directories", "accountName", "accountType", accountArr);
            contactsDatabaseHelper.getSyncState().onAccountsChanged(writableDatabase, accountArr);
            saveAccounts(accountArr);
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            this.mAccountWritability.clear();
            updateContactsAccountCount(accountArr);
            updateProviderStatus();
            return true;
        } catch (Throwable th2) {
            writableDatabase.endTransaction();
            throw th2;
        }
    }

    private void updateContactsAccountCount(Account[] accountArr) {
        int i = 0;
        for (Account account : accountArr) {
            if (isContactsAccount(account)) {
                i++;
            }
        }
        this.mContactsAccountCount = i;
    }

    protected boolean isContactsAccount(Account account) {
        try {
            return ContentResolver.getContentService().getIsSyncable(account, "com.android.contacts") > 0;
        } catch (RemoteException e) {
            Log.e("ContactsProvider", "Cannot obtain sync flag for account", e);
            return false;
        }
    }

    public void onPackageChanged(String str) {
        this.mContactDirectoryManager.onPackageChanged(str);
    }

    private void removeStaleAccountRows(String str, String str2, String str3, Account[] accountArr) {
        SQLiteDatabase writableDatabase = this.mDbHelper.get().getWritableDatabase();
        Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT DISTINCT " + str2 + "," + str3 + " FROM " + str, null);
        try {
            cursorRawQuery.moveToPosition(-1);
            while (cursorRawQuery.moveToNext()) {
                AccountWithDataSet accountWithDataSet = AccountWithDataSet.get(cursorRawQuery.getString(0), cursorRawQuery.getString(1), null);
                if (!accountWithDataSet.isLocalAccount() && !accountWithDataSet.inSystemAccounts(accountArr)) {
                    writableDatabase.execSQL("DELETE FROM " + str + " WHERE " + str2 + "=? AND " + str3 + "=?", new String[]{accountWithDataSet.getAccountName(), accountWithDataSet.getAccountType()});
                }
            }
        } finally {
            cursorRawQuery.close();
        }
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return query(uri, strArr, str, strArr2, str2, null);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        if (AbstractContactsProvider.VERBOSE_LOGGING) {
            Log.v("ContactsProvider", "query: uri=" + uri + "  projection=" + Arrays.toString(strArr) + "  selection=[" + str + "]  args=" + Arrays.toString(strArr2) + "  order=[" + str2 + "] CPID=" + Binder.getCallingPid() + " CUID=" + Binder.getCallingUid() + " User=" + UserUtils.getCurrentUserHandle(getContext()));
        }
        this.mContactsHelper.validateProjection(getCallingPackage(), strArr);
        this.mContactsHelper.validateSql(getCallingPackage(), str);
        this.mContactsHelper.validateSql(getCallingPackage(), str2);
        waitForAccess(this.mReadAccessLatch);
        if (!isDirectoryParamValid(uri) || !queryAllowedByEnterprisePolicy(uri)) {
            return null;
        }
        if (mapsToProfileDb(uri)) {
            switchToProfileMode();
            return this.mProfileProvider.query(uri, strArr, str, strArr2, str2, cancellationSignal);
        }
        int callingUid = Binder.getCallingUid();
        this.mStats.incrementQueryStats(callingUid);
        try {
            switchToContactMode();
            return queryDirectoryIfNecessary(uri, strArr, str, strArr2, str2, cancellationSignal);
        } finally {
            this.mStats.finishOperation(callingUid);
        }
    }

    private boolean queryAllowedByEnterprisePolicy(Uri uri) {
        if (isCallerFromSameUser()) {
            return true;
        }
        if (!doesCallerHoldInteractAcrossUserPermission()) {
            return false;
        }
        if (isCallerAnotherSelf()) {
            return this.mEnterprisePolicyGuard.isCrossProfileAllowed(uri);
        }
        return true;
    }

    private boolean isCallerFromSameUser() {
        return UserHandle.getUserId(Binder.getCallingUid()) == UserHandle.myUserId();
    }

    private boolean isCallerAnotherSelf() {
        int iMyUid = Process.myUid();
        int callingUid = Binder.getCallingUid();
        return iMyUid != callingUid && UserHandle.isSameApp(iMyUid, callingUid);
    }

    private boolean doesCallerHoldInteractAcrossUserPermission() {
        Context context = getContext();
        return context.checkCallingPermission("android.permission.INTERACT_ACROSS_USERS_FULL") == 0 || context.checkCallingPermission("android.permission.INTERACT_ACROSS_USERS") == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.database.Cursor queryDirectoryIfNecessary(android.net.Uri r13, java.lang.String[] r14, java.lang.String r15, java.lang.String[] r16, java.lang.String r17, android.os.CancellationSignal r18) {
        /*
            r12 = this;
            r9 = r12
            r10 = r13
            java.lang.String r0 = "directory"
            java.lang.String r6 = getQueryParameter(r13, r0)
            r0 = -9223372036854775808
            if (r6 != 0) goto L10
            r2 = -1
        Le:
            r7 = r2
            goto L27
        L10:
            java.lang.String r2 = "0"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L1b
            r2 = 0
            goto Le
        L1b:
            java.lang.String r2 = "1"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L26
            r2 = 1
            goto Le
        L26:
            r7 = r0
        L27:
            com.android.providers.contacts.enterprise.EnterprisePolicyGuard r2 = r9.mEnterprisePolicyGuard
            boolean r11 = r2.isValidEnterpriseUri(r13)
            if (r11 != 0) goto L43
            int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r0 <= 0) goto L34
            goto L43
        L34:
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r7 = r18
            android.database.Cursor r0 = r0.queryDirectoryAuthority(r1, r2, r3, r4, r5, r6, r7)
            return r0
        L43:
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r7
            r8 = r18
            android.database.Cursor r0 = r0.queryLocal(r1, r2, r3, r4, r5, r6, r8)
            if (r11 == 0) goto L55
            goto L58
        L55:
            r12.addSnippetExtrasToCursor(r13, r0)
        L58:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.ContactsProvider2.queryDirectoryIfNecessary(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, android.os.CancellationSignal):android.database.Cursor");
    }

    protected static boolean isDirectoryParamValid(Uri uri) {
        String queryParameter = getQueryParameter(uri, "directory");
        if (queryParameter == null) {
            return true;
        }
        try {
            Long.parseLong(queryParameter);
            return true;
        } catch (NumberFormatException unused) {
            Log.e("ContactsProvider", "Invalid directory ID: " + queryParameter);
            return false;
        }
    }

    private static Cursor createEmptyCursor(Uri uri, String[] strArr) {
        if (strArr == null) {
            strArr = getDefaultProjection(uri);
        }
        if (strArr == null) {
            return null;
        }
        return new MatrixCursor(strArr);
    }

    private String getRealCallerPackageName(Uri uri) {
        if (isCallerAnotherSelf()) {
            String queryParameter = uri.getQueryParameter("callerPackage");
            if (!TextUtils.isEmpty(queryParameter)) {
                return queryParameter;
            }
            Log.wtfStack("ContactsProvider", "Cross-profile query with no callerPackage");
            return "UNKNOWN";
        }
        return getCallingPackage();
    }

    private Cursor queryDirectoryAuthority(Uri uri, String[] strArr, String str, String[] strArr2, String str2, String str3, CancellationSignal cancellationSignal) {
        DirectoryInfo directoryAuthority = getDirectoryAuthority(str3);
        if (directoryAuthority == null) {
            Log.e("ContactsProvider", "Invalid directory ID");
            return null;
        }
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content");
        builder.authority(directoryAuthority.authority);
        builder.encodedPath(uri.getEncodedPath());
        String str4 = directoryAuthority.accountName;
        if (str4 != null) {
            builder.appendQueryParameter("account_name", str4);
        }
        String str5 = directoryAuthority.accountType;
        if (str5 != null) {
            builder.appendQueryParameter("account_type", str5);
        }
        builder.appendQueryParameter("callerPackage", getRealCallerPackageName(uri));
        String limit = getLimit(uri);
        if (limit != null) {
            builder.appendQueryParameter("limit", limit);
        }
        Uri uriBuild = builder.build();
        String[] defaultProjection = strArr == null ? getDefaultProjection(uri) : strArr;
        try {
            if (AbstractContactsProvider.VERBOSE_LOGGING) {
                Log.v("ContactsProvider", "Making directory query: uri=" + uriBuild + "  projection=" + Arrays.toString(defaultProjection) + "  selection=[" + str + "]  args=" + Arrays.toString(strArr2) + "  order=[" + str2 + "]  Caller=" + getCallingPackage() + "  User=" + UserUtils.getCurrentUserHandle(getContext()));
            }
            Cursor cursorQuery = getContext().getContentResolver().query(uriBuild, defaultProjection, str, strArr2, str2);
            if (cursorQuery == null) {
                return null;
            }
            if (cursorQuery.getCount() > 0) {
                int callingUid = Binder.getCallingUid();
                String str6 = directoryAuthority.authority;
                if (AbstractContactsProvider.VERBOSE_LOGGING) {
                    Log.v("ContactsProvider", "Making authority " + str6 + " visible to UID " + callingUid);
                }
                getContext().getPackageManager().grantImplicitAccess(callingUid, str6);
            }
            try {
                MemoryCursor memoryCursor = new MemoryCursor(null, cursorQuery.getColumnNames());
                memoryCursor.fillFromCursor(cursorQuery);
                return memoryCursor;
            } finally {
                cursorQuery.close();
            }
        } catch (RuntimeException e) {
            Log.w("ContactsProvider", "Directory query failed", e);
            return null;
        }
    }

    protected Cursor queryCorpContactsProvider(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        int corpUserId = UserUtils.getCorpUserId(getContext());
        if (corpUserId < 0) {
            return createEmptyCursor(uri, strArr);
        }
        if (!"com.android.contacts".equals(uri.getAuthority())) {
            Log.w("ContactsProvider", "Invalid authority: " + uri.getAuthority());
            throw new IllegalArgumentException("Authority " + uri.getAuthority() + " is not a valid CP2 authority.");
        }
        Cursor cursorQuery = getContext().getContentResolver().query(ContentProvider.maybeAddUserId(uri, corpUserId).buildUpon().appendQueryParameter("callerPackage", getCallingPackage()).build(), strArr, str, strArr2, str2, cancellationSignal);
        return cursorQuery == null ? createEmptyCursor(uri, strArr) : cursorQuery;
    }

    private Cursor addSnippetExtrasToCursor(Uri uri, Cursor cursor) {
        if (cursor.getColumnIndex("snippet") < 0) {
            return cursor;
        }
        String lastPathSegment = uri.getLastPathSegment();
        if ((cursor instanceof AbstractCursor) && deferredSnippetingRequested(uri)) {
            Bundle extras = cursor.getExtras();
            Bundle bundle = new Bundle();
            if (extras != null) {
                bundle.putAll(extras);
            }
            bundle.putString("deferred_snippeting_query", lastPathSegment);
            ((AbstractCursor) cursor).setExtras(bundle);
        }
        return cursor;
    }

    private Cursor addDeferredSnippetingExtra(Cursor cursor) {
        if (cursor instanceof AbstractCursor) {
            Bundle extras = cursor.getExtras();
            Bundle bundle = new Bundle();
            if (extras != null) {
                bundle.putAll(extras);
            }
            bundle.putBoolean("deferred_snippeting", true);
            ((AbstractCursor) cursor).setExtras(bundle);
        }
        return cursor;
    }

    private DirectoryInfo getDirectoryAuthority(String str) {
        DirectoryInfo directoryInfo;
        synchronized (this.mDirectoryCache) {
            if (!this.mDirectoryCacheValid) {
                this.mDirectoryCache.clear();
                Cursor cursorQuery = this.mDbHelper.get().getReadableDatabase().query("directories", DirectoryQuery.COLUMNS, null, null, null, null, null);
                while (cursorQuery.moveToNext()) {
                    try {
                        DirectoryInfo directoryInfo2 = new DirectoryInfo();
                        String string = cursorQuery.getString(0);
                        directoryInfo2.authority = cursorQuery.getString(1);
                        directoryInfo2.accountName = cursorQuery.getString(2);
                        directoryInfo2.accountType = cursorQuery.getString(3);
                        this.mDirectoryCache.put(string, directoryInfo2);
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                }
                cursorQuery.close();
                this.mDirectoryCacheValid = true;
            }
            directoryInfo = this.mDirectoryCache.get(str);
        }
        return directoryInfo;
    }

    public void resetDirectoryCache() {
        synchronized (this.mDirectoryCache) {
            this.mDirectoryCacheValid = false;
        }
    }

    /*  JADX ERROR: Type inference failed with stack overflow
        jadx.core.utils.exceptions.JadxOverflowException
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    protected android.database.Cursor queryLocal(android.net.Uri r45, java.lang.String[] r46, java.lang.String r47, java.lang.String[] r48, java.lang.String r49, long r50, android.os.CancellationSignal r52) {
        /*
            Method dump skipped, instruction units count: 5504
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.ContactsProvider2.queryLocal(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, long, android.os.CancellationSignal):android.database.Cursor");
    }

    protected static String getLocalizedSortOrder(String str) {
        String strSubstring;
        String strSubstring2;
        if (str == null) {
            return str;
        }
        int iIndexOf = str.indexOf(32);
        if (iIndexOf != -1) {
            strSubstring2 = str.substring(0, iIndexOf);
            strSubstring = str.substring(iIndexOf);
        } else {
            strSubstring = "";
            strSubstring2 = str;
        }
        if (TextUtils.equals(strSubstring2, "sort_key")) {
            return "phonebook_bucket" + strSubstring + ", " + str;
        }
        if (!TextUtils.equals(strSubstring2, "sort_key_alt")) {
            return str;
        }
        return "phonebook_bucket_alt" + strSubstring + ", " + str;
    }

    private Cursor doQuery(SQLiteDatabase sQLiteDatabase, SQLiteQueryBuilder sQLiteQueryBuilder, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5, CancellationSignal cancellationSignal) {
        if (strArr != null && strArr.length == 1 && "_count".equals(strArr[0])) {
            sQLiteQueryBuilder.setProjectionMap(sCountProjectionMap);
        }
        Cursor cursorQuery = sQLiteQueryBuilder.query(sQLiteDatabase, strArr, str, strArr2, str3, str4, str2, str5, cancellationSignal);
        if (cursorQuery != null) {
            cursorQuery.setNotificationUri(getContext().getContentResolver(), ContactsContract.AUTHORITY_URI);
        }
        return cursorQuery;
    }

    private Cursor queryMergedDirectories(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        Cursor cursorQueryCorpContactsProvider;
        Uri uri2 = ContactsContract.Directory.CONTENT_URI;
        Cursor cursorQueryLocal = queryLocal(uri2, strArr, str, strArr2, str2, 0L, cancellationSignal);
        try {
            cursorQueryCorpContactsProvider = queryCorpContactsProvider(uri2, strArr, str, strArr2, str2, cancellationSignal);
            if (cursorQueryCorpContactsProvider == null) {
                return cursorQueryLocal;
            }
            try {
                MergeCursor mergeCursor = new MergeCursor(new Cursor[]{cursorQueryLocal, rewriteCorpDirectories(cursorQueryCorpContactsProvider)});
                if (cursorQueryCorpContactsProvider != null) {
                    cursorQueryCorpContactsProvider.close();
                }
                return mergeCursor;
            } catch (Throwable th) {
                th = th;
                if (cursorQueryLocal != null) {
                    try {
                        cursorQueryLocal.close();
                    } finally {
                        if (cursorQueryCorpContactsProvider != null) {
                            cursorQueryCorpContactsProvider.close();
                        }
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursorQueryCorpContactsProvider = null;
        }
    }

    private Cursor queryMergedDataPhones(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        long j;
        Cursor cursorQueryCorpContacts;
        List<String> pathSegments = uri.getPathSegments();
        int size = pathSegments.size();
        StringBuilder sb = new StringBuilder(ContactsContract.CommonDataKinds.Phone.CONTENT_URI.getPath());
        for (int i = 2; i < size; i++) {
            sb.append('/');
            sb.append(pathSegments.get(i));
        }
        Uri uriBuild = uri.buildUpon().path(sb.toString()).build();
        String queryParameter = getQueryParameter(uri, "directory");
        if (queryParameter == null) {
            j = -1;
        } else if (queryParameter.equals("0")) {
            j = 0;
        } else {
            j = queryParameter.equals("1") ? 1L : Long.MIN_VALUE;
        }
        Cursor cursorQueryLocal = queryLocal(uriBuild, strArr, str, strArr2, str2, j, null);
        try {
            return (UserUtils.getCorpUserId(getContext()) >= 0 && (cursorQueryCorpContacts = queryCorpContacts(uriBuild, strArr, str, strArr2, str2, new String[]{"contact_id"}, null, cancellationSignal)) != null) ? new MergeCursor(new Cursor[]{cursorQueryLocal, cursorQueryCorpContacts}) : cursorQueryLocal;
        } catch (Throwable th) {
            if (cursorQueryLocal != null) {
                cursorQueryLocal.close();
            }
            throw th;
        }
    }

    private static String[] addContactIdColumnIfNotPresent(String[] strArr, String[] strArr2) {
        if (strArr == null) {
            return null;
        }
        int length = strArr.length;
        for (String str : strArr) {
            if (ArrayUtils.contains(strArr2, str)) {
                return strArr;
            }
        }
        String[] strArr3 = new String[length + 1];
        System.arraycopy(strArr, 0, strArr3, 0, length);
        strArr3[strArr.length] = strArr2[0];
        return strArr3;
    }

    private Cursor queryCorpContacts(Uri uri, String[] strArr, String str, String[] strArr2, String str2, String[] strArr3, Long l, CancellationSignal cancellationSignal) {
        String[] strArrAddContactIdColumnIfNotPresent = addContactIdColumnIfNotPresent(strArr, strArr3);
        boolean z = false;
        if (strArr != null && strArrAddContactIdColumnIfNotPresent.length != strArr.length) {
            z = true;
        }
        boolean z2 = z;
        Cursor cursorQueryCorpContactsProvider = queryCorpContactsProvider(uri, strArrAddContactIdColumnIfNotPresent, str, strArr2, str2, cancellationSignal);
        int[] contactIdColumnIndices = getContactIdColumnIndices(cursorQueryCorpContactsProvider, strArr3);
        if (contactIdColumnIndices.length == 0) {
            throw new IllegalStateException("column id is missing in the returned cursor.");
        }
        return new EnterpriseContactsCursorWrapper(cursorQueryCorpContactsProvider, z2 ? removeLastColumn(cursorQueryCorpContactsProvider.getColumnNames()) : cursorQueryCorpContactsProvider.getColumnNames(), contactIdColumnIndices, l);
    }

    private static String[] removeLastColumn(String[] strArr) {
        int length = strArr.length - 1;
        String[] strArr2 = new String[length];
        System.arraycopy(strArr, 0, strArr2, 0, length);
        return strArr2;
    }

    private Cursor queryCorpLookupIfNecessary(Uri uri, String[] strArr, String str, String[] strArr2, String str2, String[] strArr3, CancellationSignal cancellationSignal) {
        String queryParameter = getQueryParameter(uri, "directory");
        long j = queryParameter != null ? Long.parseLong(queryParameter) : 0L;
        if (ContactsContract.Directory.isEnterpriseDirectoryId(j)) {
            throw new IllegalArgumentException("Directory id must be a current profile id");
        }
        if (ContactsContract.Directory.isRemoteDirectoryId(j)) {
            throw new IllegalArgumentException("Directory id must be a local directory id");
        }
        int corpUserId = UserUtils.getCorpUserId(getContext());
        if (AbstractContactsProvider.VERBOSE_LOGGING) {
            Log.v("ContactsProvider", "queryCorpLookupIfNecessary: local query URI=" + uri);
        }
        Cursor cursorQueryLocal = queryLocal(uri, strArr, str, strArr2, str2, j, null);
        try {
            if (AbstractContactsProvider.VERBOSE_LOGGING) {
                MoreDatabaseUtils.dumpCursor("ContactsProvider", "local", cursorQueryLocal);
            }
            if (cursorQueryLocal.getCount() > 0 || corpUserId < 0) {
                return cursorQueryLocal;
            }
            try {
                Cursor cursorQueryCorpContacts = queryCorpContacts(uri, strArr, str, strArr2, str2, strArr3, null, cancellationSignal);
                return cursorQueryCorpContacts != null ? cursorQueryCorpContacts : cursorQueryLocal;
            } finally {
            }
        } finally {
        }
    }

    private Cursor queryFilterEnterprise(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal, Uri uri2, String str3) {
        String queryParameter = getQueryParameter(uri, "directory");
        if (queryParameter == null) {
            throw new IllegalArgumentException("Directory id missing in URI: " + uri);
        }
        long j = Long.parseLong(queryParameter);
        Uri uriConvertToLocalUri = convertToLocalUri(uri, uri2);
        if (ContactsContract.Directory.isEnterpriseDirectoryId(j)) {
            return queryCorpContacts(uriConvertToLocalUri, strArr, str, strArr2, str2, new String[]{str3}, Long.valueOf(j), cancellationSignal);
        }
        return queryDirectoryIfNecessary(uriConvertToLocalUri, strArr, str, strArr2, str2, cancellationSignal);
    }

    public static Uri convertToLocalUri(Uri uri, Uri uri2) {
        Uri.Builder builderAppendPath = uri2.buildUpon().appendPath(uri.getPathSegments().size() > uri2.getPathSegments().size() ? uri.getLastPathSegment() : "");
        addQueryParametersFromUri(builderAppendPath, uri, MODIFIED_KEY_SET_FOR_ENTERPRISE_FILTER);
        String queryParameter = getQueryParameter(uri, "directory");
        if (!TextUtils.isEmpty(queryParameter)) {
            long j = Long.parseLong(queryParameter);
            if (ContactsContract.Directory.isEnterpriseDirectoryId(j)) {
                builderAppendPath.appendQueryParameter("directory", String.valueOf(j - 1000000000));
            } else {
                builderAppendPath.appendQueryParameter("directory", String.valueOf(j));
            }
        }
        return builderAppendPath.build();
    }

    protected static final Uri.Builder addQueryParametersFromUri(Uri.Builder builder, Uri uri, Set<String> set) {
        for (String str : uri.getQueryParameterNames()) {
            if (set == null || !set.contains(str)) {
                builder.appendQueryParameter(str, getQueryParameter(uri, str));
            }
        }
        return builder;
    }

    private Cursor queryPhoneLookupEnterprise(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        String[] strArr3;
        if (uri.getBooleanQueryParameter("sip", false)) {
            strArr3 = new String[]{"contact_id"};
        } else {
            strArr3 = new String[]{"_id", "contact_id"};
        }
        return queryLookupEnterprise(uri, strArr, str, strArr2, str2, cancellationSignal, ContactsContract.PhoneLookup.CONTENT_FILTER_URI, strArr3);
    }

    private Cursor queryEmailsLookupEnterprise(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        return queryLookupEnterprise(uri, strArr, str, strArr2, str2, cancellationSignal, ContactsContract.CommonDataKinds.Email.CONTENT_LOOKUP_URI, new String[]{"contact_id"});
    }

    private Cursor queryLookupEnterprise(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal, Uri uri2, String[] strArr3) {
        Uri uriConvertToLocalUri = convertToLocalUri(uri, uri2);
        String queryParameter = getQueryParameter(uri, "directory");
        if (!TextUtils.isEmpty(queryParameter)) {
            long j = Long.parseLong(queryParameter);
            if (ContactsContract.Directory.isEnterpriseDirectoryId(j)) {
                return queryCorpContacts(uriConvertToLocalUri, strArr, str, strArr2, str2, strArr3, Long.valueOf(j), cancellationSignal);
            }
            return queryDirectoryIfNecessary(uriConvertToLocalUri, strArr, str, strArr2, str2, cancellationSignal);
        }
        return queryCorpLookupIfNecessary(uriConvertToLocalUri, strArr, str, strArr2, str2, strArr3, cancellationSignal);
    }

    static Cursor rewriteCorpDirectories(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        String[] columnNames = cursor.getColumnNames();
        MatrixCursor matrixCursor = new MatrixCursor(columnNames);
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
            for (String str : columnNames) {
                int columnIndex = cursor.getColumnIndex(str);
                if (str.equals("_id")) {
                    rowBuilderNewRow.add(Long.valueOf(cursor.getLong(columnIndex) + 1000000000));
                } else {
                    int type = cursor.getType(columnIndex);
                    if (type == 0) {
                        rowBuilderNewRow.add(null);
                    } else if (type == 1) {
                        rowBuilderNewRow.add(Long.valueOf(cursor.getLong(columnIndex)));
                    } else if (type == 2) {
                        rowBuilderNewRow.add(Float.valueOf(cursor.getFloat(columnIndex)));
                    } else if (type == 3) {
                        rowBuilderNewRow.add(cursor.getString(columnIndex));
                    } else if (type == 4) {
                        rowBuilderNewRow.add(cursor.getBlob(columnIndex));
                    }
                }
            }
        }
        return matrixCursor;
    }

    private static int[] getContactIdColumnIndices(Cursor cursor, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        if (cursor != null) {
            for (String str : strArr) {
                int columnIndex = cursor.getColumnIndex(str);
                if (columnIndex != -1) {
                    arrayList.add(Integer.valueOf(columnIndex));
                }
            }
        }
        return Ints.toArray(arrayList);
    }

    private Cursor queryWithContactIdAndLookupKey(SQLiteQueryBuilder sQLiteQueryBuilder, SQLiteDatabase sQLiteDatabase, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5, long j, String str6, String str7, CancellationSignal cancellationSignal) {
        String[] strArr3;
        if (strArr2 == null) {
            strArr3 = new String[2];
        } else {
            String[] strArr4 = new String[strArr2.length + 2];
            System.arraycopy(strArr2, 0, strArr4, 2, strArr2.length);
            strArr3 = strArr4;
        }
        strArr3[0] = String.valueOf(j);
        strArr3[1] = Uri.encode(str7);
        sQLiteQueryBuilder.appendWhere(str5 + "=? AND " + str6 + "=?");
        Cursor cursorDoQuery = doQuery(sQLiteDatabase, sQLiteQueryBuilder, strArr, str, strArr3, str2, str3, null, str4, cancellationSignal);
        if (cursorDoQuery.getCount() != 0) {
            return cursorDoQuery;
        }
        cursorDoQuery.close();
        return null;
    }

    private void invalidateFastScrollingIndexCache() {
        this.mFastScrollingIndexCache.invalidate();
    }

    private void bundleFastScrollingIndexExtras(Cursor cursor, Uri uri, SQLiteDatabase sQLiteDatabase, SQLiteQueryBuilder sQLiteQueryBuilder, String str, String[] strArr, String str2, String str3, CancellationSignal cancellationSignal) {
        Bundle bundle;
        if (!(cursor instanceof AbstractCursor)) {
            Log.w("ContactsProvider", "Unable to bundle extras.  Cursor is not AbstractCursor.");
            return;
        }
        synchronized (this.mFastScrollingIndexCache) {
            this.mFastScrollingIndexCacheRequestCount++;
            bundle = this.mFastScrollingIndexCache.get(uri, str, strArr, str2, str3);
            if (bundle == null) {
                this.mFastScrollingIndexCacheMissCount++;
                long jCurrentTimeMillis = System.currentTimeMillis();
                Bundle fastScrollingIndexExtras = getFastScrollingIndexExtras(sQLiteDatabase, sQLiteQueryBuilder, str, strArr, str2, str3, cancellationSignal);
                int iCurrentTimeMillis = (int) (System.currentTimeMillis() - jCurrentTimeMillis);
                this.mTotalTimeFastScrollingIndexGenerate += (long) iCurrentTimeMillis;
                if (AbstractContactsProvider.VERBOSE_LOGGING) {
                    Log.v("ContactsProvider", "getLetterCountExtraBundle took " + iCurrentTimeMillis + "ms");
                }
                this.mFastScrollingIndexCache.put(uri, str, strArr, str2, str3, fastScrollingIndexExtras);
                bundle = fastScrollingIndexExtras;
            }
        }
        ((AbstractCursor) cursor).setExtras(bundle);
    }

    private static Bundle getFastScrollingIndexExtras(SQLiteDatabase sQLiteDatabase, SQLiteQueryBuilder sQLiteQueryBuilder, String str, String[] strArr, String str2, String str3, CancellationSignal cancellationSignal) {
        String str4;
        String str5;
        String str6 = str2;
        String strSubstring = "";
        if (str6 != null) {
            int iIndexOf = str6.indexOf(32);
            if (iIndexOf != -1) {
                String strSubstring2 = str6.substring(0, iIndexOf);
                strSubstring = str6.substring(iIndexOf);
                str6 = strSubstring2;
            }
        } else {
            str6 = "sort_key";
        }
        if (TextUtils.equals(str6, "sort_key")) {
            str4 = "phonebook_bucket";
            str5 = "phonebook_label";
        } else {
            if (!TextUtils.equals(str6, "sort_key_alt")) {
                return null;
            }
            str4 = "phonebook_bucket_alt";
            str5 = "phonebook_label_alt";
        }
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("name", str6 + " AS name");
        arrayMap.put("bucket", str4 + " AS bucket");
        arrayMap.put("label", str5 + " AS label");
        arrayMap.put("count", "COUNT(" + (TextUtils.isEmpty(str3) ? "*" : str3) + ") AS count");
        sQLiteQueryBuilder.setProjectionMap(arrayMap);
        Cursor cursorQuery = sQLiteQueryBuilder.query(sQLiteDatabase, AddressBookIndexQuery.COLUMNS, str, strArr, "bucket, label", null, "bucket" + strSubstring + ", name COLLATE PHONEBOOK" + strSubstring, null, cancellationSignal);
        try {
            int count = cursorQuery.getCount();
            String[] strArr2 = new String[count];
            int[] iArr = new int[count];
            for (int i = 0; i < count; i++) {
                cursorQuery.moveToNext();
                strArr2[i] = cursorQuery.getString(2);
                iArr[i] = cursorQuery.getInt(3);
            }
            return FastScrollingIndexCache.buildExtraBundle(strArr2, iArr);
        } finally {
            cursorQuery.close();
        }
    }

    public long lookupContactIdByLookupKey(SQLiteDatabase sQLiteDatabase, String str) {
        ArrayList<ContactLookupKey.LookupKeySegment> arrayList = new ContactLookupKey().parse(str);
        long jLookupSingleContactId = lookupKeyContainsType(arrayList, 3) ? lookupSingleContactId(sQLiteDatabase) : -1L;
        if (lookupKeyContainsType(arrayList, 0)) {
            jLookupSingleContactId = lookupContactIdBySourceIds(sQLiteDatabase, arrayList);
            if (jLookupSingleContactId != -1) {
                return jLookupSingleContactId;
            }
        }
        boolean zLookupKeyContainsType = lookupKeyContainsType(arrayList, 2);
        if (zLookupKeyContainsType) {
            jLookupSingleContactId = lookupContactIdByRawContactIds(sQLiteDatabase, arrayList);
            if (jLookupSingleContactId != -1) {
                return jLookupSingleContactId;
            }
        }
        return (zLookupKeyContainsType || lookupKeyContainsType(arrayList, 1)) ? lookupContactIdByDisplayNames(sQLiteDatabase, arrayList) : jLookupSingleContactId;
    }

    private long lookupSingleContactId(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("contacts", new String[]{"_id"}, null, null, null, null, null, "1");
        try {
            if (cursorQuery.moveToFirst()) {
                return cursorQuery.getLong(0);
            }
            return -1L;
        } finally {
            cursorQuery.close();
        }
    }

    private long lookupContactIdBySourceIds(SQLiteDatabase sQLiteDatabase, ArrayList<ContactLookupKey.LookupKeySegment> arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append("sourceid IN (");
        for (ContactLookupKey.LookupKeySegment lookupKeySegment : arrayList) {
            if (lookupKeySegment.lookupType == 0) {
                DatabaseUtils.appendEscapedSQLString(sb, lookupKeySegment.key);
                sb.append(",");
            }
        }
        sb.setLength(sb.length() - 1);
        sb.append(") AND contact_id NOT NULL");
        Cursor cursorQuery = sQLiteDatabase.query("view_raw_contacts", LookupBySourceIdQuery.COLUMNS, sb.toString(), null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                int accountHashCode = ContactLookupKey.getAccountHashCode(cursorQuery.getString(1), cursorQuery.getString(2));
                String string = cursorQuery.getString(3);
                int i = 0;
                while (true) {
                    if (i < arrayList.size()) {
                        ContactLookupKey.LookupKeySegment lookupKeySegment2 = arrayList.get(i);
                        if (lookupKeySegment2.lookupType == 0 && accountHashCode == lookupKeySegment2.accountHashCode && lookupKeySegment2.key.equals(string)) {
                            lookupKeySegment2.contactId = cursorQuery.getLong(0);
                            break;
                        }
                        i++;
                    }
                }
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        }
        cursorQuery.close();
        return getMostReferencedContactId(arrayList);
    }

    private long lookupContactIdByRawContactIds(SQLiteDatabase sQLiteDatabase, ArrayList<ContactLookupKey.LookupKeySegment> arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append("_id IN (");
        for (ContactLookupKey.LookupKeySegment lookupKeySegment : arrayList) {
            if (lookupKeySegment.lookupType == 2) {
                sb.append(lookupKeySegment.rawContactId);
                sb.append(",");
            }
        }
        sb.setLength(sb.length() - 1);
        sb.append(") AND contact_id NOT NULL");
        Cursor cursorQuery = sQLiteDatabase.query("view_raw_contacts", LookupByRawContactIdQuery.COLUMNS, sb.toString(), null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                int accountHashCode = ContactLookupKey.getAccountHashCode(cursorQuery.getString(1), cursorQuery.getString(2));
                String string = cursorQuery.getString(3);
                Iterator<ContactLookupKey.LookupKeySegment> it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        ContactLookupKey.LookupKeySegment next = it.next();
                        if (next.lookupType == 2 && accountHashCode == next.accountHashCode && next.rawContactId.equals(string)) {
                            next.contactId = cursorQuery.getLong(0);
                            break;
                        }
                    }
                }
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        }
        cursorQuery.close();
        return getMostReferencedContactId(arrayList);
    }

    private long lookupContactIdByDisplayNames(SQLiteDatabase sQLiteDatabase, ArrayList<ContactLookupKey.LookupKeySegment> arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append("normalized_name IN (");
        for (ContactLookupKey.LookupKeySegment lookupKeySegment : arrayList) {
            int i = lookupKeySegment.lookupType;
            if (i == 1 || i == 2) {
                DatabaseUtils.appendEscapedSQLString(sb, lookupKeySegment.key);
                sb.append(",");
            }
        }
        sb.setLength(sb.length() - 1);
        sb.append(") AND name_type=2 AND contact_id NOT NULL");
        Cursor cursorQuery = sQLiteDatabase.query("name_lookup INNER JOIN view_raw_contacts ON (name_lookup.raw_contact_id = view_raw_contacts._id)", LookupByDisplayNameQuery.COLUMNS, sb.toString(), null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                int accountHashCode = ContactLookupKey.getAccountHashCode(cursorQuery.getString(1), cursorQuery.getString(2));
                String string = cursorQuery.getString(3);
                Iterator<ContactLookupKey.LookupKeySegment> it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        ContactLookupKey.LookupKeySegment next = it.next();
                        if (next.lookupType == 1 || next.lookupType == 2) {
                            if (accountHashCode == next.accountHashCode && next.key.equals(string)) {
                                next.contactId = cursorQuery.getLong(0);
                                break;
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        }
        cursorQuery.close();
        return getMostReferencedContactId(arrayList);
    }

    private boolean lookupKeyContainsType(ArrayList<ContactLookupKey.LookupKeySegment> arrayList, int i) {
        Iterator<ContactLookupKey.LookupKeySegment> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().lookupType == i) {
                return true;
            }
        }
        return false;
    }

    private long getMostReferencedContactId(ArrayList<ContactLookupKey.LookupKeySegment> arrayList) {
        Collections.sort(arrayList);
        int i = 0;
        int i2 = 0;
        long j = -1;
        long j2 = -1;
        for (ContactLookupKey.LookupKeySegment lookupKeySegment : arrayList) {
            long j3 = lookupKeySegment.contactId;
            if (j3 != -1) {
                if (j3 == j2) {
                    i++;
                } else {
                    if (i > i2) {
                        j = j2;
                    } else {
                        i = i2;
                    }
                    j2 = lookupKeySegment.contactId;
                    i2 = i;
                    i = 1;
                }
            }
        }
        return i > i2 ? j2 : j;
    }

    private void setTablesAndProjectionMapForContacts(SQLiteQueryBuilder sQLiteQueryBuilder, String[] strArr) {
        setTablesAndProjectionMapForContacts(sQLiteQueryBuilder, strArr, false);
    }

    private void setTablesAndProjectionMapForContacts(SQLiteQueryBuilder sQLiteQueryBuilder, String[] strArr, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("data_usage_stat");
            sb.append(" INNER JOIN ");
        }
        sb.append("view_contacts");
        if (z) {
            sb.append(" ON (" + DbQueryUtils.concatenateClauses("(0)", "contact_id=view_contacts._id") + ")");
        }
        appendContactPresenceJoin(sb, strArr, "_id");
        appendContactStatusUpdateJoin(sb, strArr, "status_update_id");
        sQLiteQueryBuilder.setTables(sb.toString());
        sQLiteQueryBuilder.setProjectionMap(sContactsProjectionMap);
    }

    private void setTablesAndProjectionMapForContactsWithSnippet(SQLiteQueryBuilder sQLiteQueryBuilder, Uri uri, String[] strArr, String str, long j, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("view_contacts");
        if (str != null) {
            str = str.trim();
        }
        String str2 = str;
        if (TextUtils.isEmpty(str2) || (j != -1 && j != 0)) {
            sb.append(" JOIN (SELECT NULL AS snippet WHERE 0)");
        } else {
            appendSearchIndexJoin(sb, uri, strArr, str2, z);
        }
        appendContactPresenceJoin(sb, strArr, "_id");
        appendContactStatusUpdateJoin(sb, strArr, "status_update_id");
        sQLiteQueryBuilder.setTables(sb.toString());
        sQLiteQueryBuilder.setProjectionMap(sContactsProjectionWithSnippetMap);
    }

    private void appendSearchIndexJoin(StringBuilder sb, Uri uri, String[] strArr, String str, boolean z) {
        if (snippetNeeded(strArr)) {
            String queryParameter = getQueryParameter(uri, "snippet_args");
            String[] strArrSplit = queryParameter != null ? queryParameter.split(",") : null;
            appendSearchIndexJoin(sb, str, true, (strArrSplit == null || strArrSplit.length <= 0) ? "[" : strArrSplit[0], (strArrSplit == null || strArrSplit.length <= 1) ? "]" : strArrSplit[1], (strArrSplit == null || strArrSplit.length <= 2) ? "…" : strArrSplit[2], (strArrSplit == null || strArrSplit.length <= 3) ? 5 : Integer.parseInt(strArrSplit[3]), z);
            return;
        }
        appendSearchIndexJoin(sb, str, false, null, null, null, 0, false);
    }

    public void appendSearchIndexJoin(StringBuilder sb, String str, boolean z, String str2, String str3, String str4, int i, boolean z2) {
        boolean z3;
        boolean z4;
        String str5;
        String numberToE164;
        String str6;
        String str7;
        String strSanitizeMatch;
        if (str.indexOf(64) != -1) {
            String strExtractAddressFromEmailAddress = this.mDbHelper.get().extractAddressFromEmailAddress(str);
            z4 = !TextUtils.isEmpty(strExtractAddressFromEmailAddress);
            str6 = strExtractAddressFromEmailAddress;
            z3 = false;
            str5 = null;
            numberToE164 = null;
        } else {
            boolean zIsPhoneNumber = isPhoneNumber(str);
            if (zIsPhoneNumber) {
                String strNormalizeNumber = PhoneNumberUtils.normalizeNumber(str);
                str6 = null;
                str5 = strNormalizeNumber;
                numberToE164 = PhoneNumberUtils.formatNumberToE164(strNormalizeNumber, this.mDbHelper.get().getCurrentCountryIso());
                z3 = zIsPhoneNumber;
                z4 = false;
            } else {
                z3 = zIsPhoneNumber;
                z4 = false;
                str5 = null;
                numberToE164 = null;
                str6 = null;
            }
        }
        sb.append(" JOIN (SELECT contact_id AS snippet_contact_id");
        if (z) {
            sb.append(", ");
            str7 = str6;
            if (z4) {
                sb.append("ifnull(");
                if (!z2) {
                    DatabaseUtils.appendEscapedSQLString(sb, str2);
                    sb.append("||");
                }
                sb.append("(SELECT MIN(data1)");
                sb.append(" FROM data JOIN raw_contacts ON (data.raw_contact_id = raw_contacts._id)");
                sb.append(" WHERE  search_index.contact_id");
                sb.append("=contact_id AND data1 LIKE ");
                DatabaseUtils.appendEscapedSQLString(sb, str + "%");
                sb.append(")");
                if (!z2) {
                    sb.append("||");
                    DatabaseUtils.appendEscapedSQLString(sb, str3);
                }
                sb.append(",");
                if (z2) {
                    sb.append("content");
                } else {
                    appendSnippetFunction(sb, str2, str3, str4, i);
                }
                sb.append(")");
            } else if (z3) {
                sb.append("ifnull(");
                if (!z2) {
                    DatabaseUtils.appendEscapedSQLString(sb, str2);
                    sb.append("||");
                }
                sb.append("(SELECT MIN(data1)");
                sb.append(" FROM data JOIN raw_contacts ON (data.raw_contact_id = raw_contacts._id) JOIN phone_lookup");
                sb.append(" ON data._id");
                sb.append("=phone_lookup.data_id");
                sb.append(" WHERE  search_index.contact_id");
                sb.append("=contact_id");
                sb.append(" AND normalized_number LIKE '");
                sb.append(str5);
                sb.append("%'");
                if (!TextUtils.isEmpty(numberToE164)) {
                    sb.append(" OR normalized_number LIKE '");
                    sb.append(numberToE164);
                    sb.append("%'");
                }
                sb.append(")");
                if (!z2) {
                    sb.append("||");
                    DatabaseUtils.appendEscapedSQLString(sb, str3);
                }
                sb.append(",");
                if (z2) {
                    sb.append("content");
                } else {
                    appendSnippetFunction(sb, str2, str3, str4, i);
                }
                sb.append(")");
            } else {
                String strNormalize = NameNormalizer.normalize(str);
                if (TextUtils.isEmpty(strNormalize)) {
                    sb.append("NULL");
                } else if (z2) {
                    sb.append("content");
                } else {
                    sb.append("(CASE WHEN EXISTS (SELECT 1 FROM ");
                    sb.append("raw_contacts AS rc INNER JOIN ");
                    sb.append("name_lookup AS nl ON (rc._id");
                    sb.append("=nl.raw_contact_id");
                    sb.append(") WHERE nl.normalized_name");
                    sb.append(" GLOB '" + strNormalize + "*' AND ");
                    sb.append("nl.name_type=");
                    sb.append("2 AND ");
                    sb.append("search_index.contact_id");
                    sb.append("=rc.contact_id");
                    sb.append(") THEN NULL ELSE ");
                    appendSnippetFunction(sb, str2, str3, str4, i);
                    sb.append(" END)");
                }
            }
            sb.append(" AS snippet");
        } else {
            str7 = str6;
        }
        sb.append(" FROM search_index");
        sb.append(" WHERE ");
        sb.append("search_index MATCH '");
        strSanitizeMatch = "";
        if (z4) {
            strSanitizeMatch = str7 != null ? sanitizeMatch(str7) : "";
            sb.append("\"");
            sb.append(strSanitizeMatch);
            sb.append("*\"");
        } else if (z3) {
            String str8 = " OR tokens:" + str5 + "*";
            if (numberToE164 != null && !TextUtils.equals(numberToE164, str5)) {
                strSanitizeMatch = " OR tokens:" + numberToE164 + "*";
            }
            sb.append(SearchIndexManager.getFtsMatchQuery(str, SearchIndexManager.FtsQueryBuilder.getDigitsQueryBuilder(str8 + strSanitizeMatch)));
        } else {
            sb.append(SearchIndexManager.getFtsMatchQuery(str, SearchIndexManager.FtsQueryBuilder.SCOPED_NAME_NORMALIZING));
        }
        sb.append("' AND snippet_contact_id IN default_directory)");
        sb.append(" ON (_id=snippet_contact_id)");
    }

    private static String sanitizeMatch(String str) {
        return str.replace("'", "").replace("*", "").replace("-", "").replace("\"", "");
    }

    private void appendSnippetFunction(StringBuilder sb, String str, String str2, String str3, int i) {
        sb.append("snippet(search_index,");
        DatabaseUtils.appendEscapedSQLString(sb, str);
        sb.append(",");
        DatabaseUtils.appendEscapedSQLString(sb, str2);
        sb.append(",");
        DatabaseUtils.appendEscapedSQLString(sb, str3);
        sb.append(",1,");
        sb.append(i);
        sb.append(")");
    }

    private void setTablesAndProjectionMapForRawContacts(SQLiteQueryBuilder sQLiteQueryBuilder, Uri uri) {
        sQLiteQueryBuilder.setTables("view_raw_contacts");
        sQLiteQueryBuilder.setProjectionMap(sRawContactsProjectionMap);
        appendAccountIdFromParameter(sQLiteQueryBuilder, uri);
    }

    private void setTablesAndProjectionMapForRawEntities(SQLiteQueryBuilder sQLiteQueryBuilder, Uri uri) {
        sQLiteQueryBuilder.setTables("view_raw_entities");
        sQLiteQueryBuilder.setProjectionMap(sRawEntityProjectionMap);
        appendAccountIdFromParameter(sQLiteQueryBuilder, uri);
    }

    private void setTablesAndProjectionMapForData(SQLiteQueryBuilder sQLiteQueryBuilder, Uri uri, String[] strArr, boolean z) {
        setTablesAndProjectionMapForData(sQLiteQueryBuilder, uri, strArr, z, false, null);
    }

    private void setTablesAndProjectionMapForData(SQLiteQueryBuilder sQLiteQueryBuilder, Uri uri, String[] strArr, boolean z, boolean z2) {
        setTablesAndProjectionMapForData(sQLiteQueryBuilder, uri, strArr, z, z2, null);
    }

    private void setTablesAndProjectionMapForData(SQLiteQueryBuilder sQLiteQueryBuilder, Uri uri, String[] strArr, boolean z, Integer num) {
        setTablesAndProjectionMapForData(sQLiteQueryBuilder, uri, strArr, z, false, num);
    }

    private void setTablesAndProjectionMapForData(SQLiteQueryBuilder sQLiteQueryBuilder, Uri uri, String[] strArr, boolean z, boolean z2, Integer num) {
        ProjectionMap projectionMap;
        StringBuilder sb = new StringBuilder();
        sb.append("view_data");
        sb.append(" data");
        appendContactPresenceJoin(sb, strArr, "contact_id");
        appendContactStatusUpdateJoin(sb, strArr, "status_update_id");
        appendDataPresenceJoin(sb, strArr, "data._id");
        appendDataStatusUpdateJoin(sb, strArr, "data._id");
        appendDataUsageStatJoin(sb, num == null ? -1 : num.intValue(), "data._id");
        sQLiteQueryBuilder.setTables(sb.toString());
        boolean z3 = z || !ContactsDatabaseHelper.isInProjection(strArr, DISTINCT_DATA_PROHIBITING_COLUMNS);
        sQLiteQueryBuilder.setDistinct(z3);
        if (z2) {
            projectionMap = z3 ? sDistinctDataSipLookupProjectionMap : sDataSipLookupProjectionMap;
        } else {
            projectionMap = z3 ? sDistinctDataProjectionMap : sDataProjectionMap;
        }
        sQLiteQueryBuilder.setProjectionMap(projectionMap);
        appendAccountIdFromParameter(sQLiteQueryBuilder, uri);
    }

    private void setTableAndProjectionMapForStatusUpdates(SQLiteQueryBuilder sQLiteQueryBuilder, String[] strArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("view_data");
        sb.append(" data");
        appendDataPresenceJoin(sb, strArr, "data._id");
        appendDataStatusUpdateJoin(sb, strArr, "data._id");
        sQLiteQueryBuilder.setTables(sb.toString());
        sQLiteQueryBuilder.setProjectionMap(sStatusUpdatesProjectionMap);
    }

    private void setTablesAndProjectionMapForStreamItems(SQLiteQueryBuilder sQLiteQueryBuilder) {
        sQLiteQueryBuilder.setTables("view_stream_items");
        sQLiteQueryBuilder.setProjectionMap(sStreamItemsProjectionMap);
    }

    private void setTablesAndProjectionMapForStreamItemPhotos(SQLiteQueryBuilder sQLiteQueryBuilder) {
        sQLiteQueryBuilder.setTables("photo_files JOIN stream_item_photos ON (stream_item_photos.photo_file_id=photo_files._id) JOIN stream_items ON (stream_item_photos.stream_item_id=stream_items._id) JOIN raw_contacts ON (stream_items.raw_contact_id=raw_contacts._id)");
        sQLiteQueryBuilder.setProjectionMap(sStreamItemPhotosProjectionMap);
    }

    private void setTablesAndProjectionMapForEntities(SQLiteQueryBuilder sQLiteQueryBuilder, Uri uri, String[] strArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("view_entities");
        sb.append(" data");
        appendContactPresenceJoin(sb, strArr, "contact_id");
        appendContactStatusUpdateJoin(sb, strArr, "status_update_id");
        appendDataPresenceJoin(sb, strArr, "data_id");
        appendDataStatusUpdateJoin(sb, strArr, "data_id");
        appendDataUsageStatJoin(sb, -1, "data_id");
        sQLiteQueryBuilder.setTables(sb.toString());
        sQLiteQueryBuilder.setProjectionMap(sEntityProjectionMap);
        appendAccountIdFromParameter(sQLiteQueryBuilder, uri);
    }

    private void appendContactStatusUpdateJoin(StringBuilder sb, String[] strArr, String str) {
        if (ContactsDatabaseHelper.isInProjection(strArr, "contact_status", "contact_status_res_package", "contact_status_icon", "contact_status_label", "contact_status_ts")) {
            sb.append(" LEFT OUTER JOIN status_updates contacts_status_updates ON (" + str + "=contacts_status_updates.status_update_data_id)");
        }
    }

    private void appendDataStatusUpdateJoin(StringBuilder sb, String[] strArr, String str) {
        if (ContactsDatabaseHelper.isInProjection(strArr, "status", "status_res_package", "status_icon", "status_label", "status_ts")) {
            sb.append(" LEFT OUTER JOIN status_updates ON (status_updates.status_update_data_id=" + str + ")");
        }
    }

    private void appendDataUsageStatJoin(StringBuilder sb, int i, String str) {
        sb.append(" LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat");
        sb.append(" ON (STAT_DATA_ID=");
        sb.append(str);
        sb.append(")");
    }

    private void appendContactPresenceJoin(StringBuilder sb, String[] strArr, String str) {
        if (ContactsDatabaseHelper.isInProjection(strArr, "contact_presence", "contact_chat_capability")) {
            sb.append(" LEFT OUTER JOIN agg_presence ON (" + str + " = agg_presence.presence_contact_id)");
        }
    }

    private void appendDataPresenceJoin(StringBuilder sb, String[] strArr, String str) {
        if (ContactsDatabaseHelper.isInProjection(strArr, "mode", "chat_capability")) {
            sb.append(" LEFT OUTER JOIN presence ON (presence_data_id=" + str + ")");
        }
    }

    private void appendLocalDirectoryAndAccountSelectionIfNeeded(SQLiteQueryBuilder sQLiteQueryBuilder, long j, Uri uri) {
        StringBuilder sb = new StringBuilder();
        if (j == 0) {
            sb.append("(_id IN default_directory)");
        } else if (j == 1) {
            sb.append("(_id NOT IN default_directory)");
        } else {
            sb.append("(1)");
        }
        AccountWithDataSet accountWithDataSetFromUri = getAccountWithDataSetFromUri(uri);
        if (!TextUtils.isEmpty(accountWithDataSetFromUri.getAccountName())) {
            Long accountIdOrNull = this.mDbHelper.get().getAccountIdOrNull(accountWithDataSetFromUri);
            if (accountIdOrNull == null) {
                sb.setLength(0);
                sb.append("(1=2)");
            } else {
                sb.append(" AND (_id IN (SELECT contact_id FROM raw_contacts WHERE account_id=" + accountIdOrNull.toString() + "))");
            }
        }
        sQLiteQueryBuilder.appendWhere(sb.toString());
    }

    private void appendAccountFromParameter(SQLiteQueryBuilder sQLiteQueryBuilder, Uri uri) {
        String str;
        AccountWithDataSet accountWithDataSetFromUri = getAccountWithDataSetFromUri(uri);
        if (!TextUtils.isEmpty(accountWithDataSetFromUri.getAccountName())) {
            String str2 = "(account_name=" + DatabaseUtils.sqlEscapeString(accountWithDataSetFromUri.getAccountName()) + " AND account_type=" + DatabaseUtils.sqlEscapeString(accountWithDataSetFromUri.getAccountType());
            if (accountWithDataSetFromUri.getDataSet() == null) {
                str = str2 + " AND data_set IS NULL";
            } else {
                str = str2 + " AND data_set=" + DatabaseUtils.sqlEscapeString(accountWithDataSetFromUri.getDataSet());
            }
            sQLiteQueryBuilder.appendWhere(str + ")");
            return;
        }
        sQLiteQueryBuilder.appendWhere("1");
    }

    private void appendAccountIdFromParameter(SQLiteQueryBuilder sQLiteQueryBuilder, Uri uri) {
        AccountWithDataSet accountWithDataSetFromUri = getAccountWithDataSetFromUri(uri);
        if (!TextUtils.isEmpty(accountWithDataSetFromUri.getAccountName())) {
            Long accountIdOrNull = this.mDbHelper.get().getAccountIdOrNull(accountWithDataSetFromUri);
            if (accountIdOrNull == null) {
                sQLiteQueryBuilder.appendWhere("(1=2)");
                return;
            }
            sQLiteQueryBuilder.appendWhere("(account_id=" + accountIdOrNull.toString() + ")");
            return;
        }
        sQLiteQueryBuilder.appendWhere("1");
    }

    private AccountWithDataSet getAccountWithDataSetFromUri(Uri uri) {
        String queryParameter = getQueryParameter(uri, "account_name");
        String queryParameter2 = getQueryParameter(uri, "account_type");
        String queryParameter3 = getQueryParameter(uri, "data_set");
        if (TextUtils.isEmpty(queryParameter) ^ TextUtils.isEmpty(queryParameter2)) {
            throw new IllegalArgumentException(this.mDbHelper.get().exceptionMessage("Must specify both or neither of ACCOUNT_NAME and ACCOUNT_TYPE", uri));
        }
        return AccountWithDataSet.get(queryParameter, queryParameter2, queryParameter3);
    }

    private String appendAccountToSelection(Uri uri, String str) {
        AccountWithDataSet accountWithDataSetFromUri = getAccountWithDataSetFromUri(uri);
        if (!(!TextUtils.isEmpty(accountWithDataSetFromUri.getAccountName()))) {
            return str;
        }
        StringBuilder sb = new StringBuilder("account_name=");
        sb.append(DatabaseUtils.sqlEscapeString(accountWithDataSetFromUri.getAccountName()));
        sb.append(" AND account_type=");
        sb.append(DatabaseUtils.sqlEscapeString(accountWithDataSetFromUri.getAccountType()));
        if (accountWithDataSetFromUri.getDataSet() == null) {
            sb.append(" AND data_set IS NULL");
        } else {
            sb.append(" AND data_set=");
            sb.append(DatabaseUtils.sqlEscapeString(accountWithDataSetFromUri.getDataSet()));
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append(" AND (");
            sb.append(str);
            sb.append(')');
        }
        return sb.toString();
    }

    private String appendAccountIdToSelection(Uri uri, String str) {
        AccountWithDataSet accountWithDataSetFromUri = getAccountWithDataSetFromUri(uri);
        if (!(!TextUtils.isEmpty(accountWithDataSetFromUri.getAccountName()))) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        Long accountIdOrNull = this.mDbHelper.get().getAccountIdOrNull(accountWithDataSetFromUri);
        if (accountIdOrNull == null) {
            sb.append("(1=2)");
        } else {
            sb.append("account_id=");
            sb.append(Long.toString(accountIdOrNull.longValue()));
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append(" AND (");
            sb.append(str);
            sb.append(')');
        }
        return sb.toString();
    }

    static String getLimit(Uri uri) {
        String queryParameter = getQueryParameter(uri, "limit");
        if (queryParameter == null) {
            return null;
        }
        try {
            int i = Integer.parseInt(queryParameter);
            if (i < 0) {
                Log.w("ContactsProvider", "Invalid limit parameter: " + queryParameter);
                return null;
            }
            return String.valueOf(i);
        } catch (NumberFormatException unused) {
            Log.w("ContactsProvider", "Invalid limit parameter: " + queryParameter);
            return null;
        }
    }

    @Override // android.content.ContentProvider
    public AssetFileDescriptor openAssetFile(Uri uri, String str) throws FileNotFoundException {
        AssetFileDescriptor assetFileDescriptorOpenAssetFileLocal;
        try {
            if (!isDirectoryParamValid(uri)) {
                return null;
            }
            if (!queryAllowedByEnterprisePolicy(uri)) {
                if (AbstractContactsProvider.VERBOSE_LOGGING) {
                    Log.v("ContactsProvider", "openAssetFile uri=" + uri + " mode=" + str + " success=false CPID=" + Binder.getCallingPid() + " CUID=" + Binder.getCallingUid() + " User=" + UserUtils.getCurrentUserHandle(getContext()));
                }
                return null;
            }
            waitForAccess(str.equals("r") ? this.mReadAccessLatch : this.mWriteAccessLatch);
            if (mapsToProfileDb(uri)) {
                switchToProfileMode();
                assetFileDescriptorOpenAssetFileLocal = this.mProfileProvider.openAssetFile(uri, str);
            } else {
                switchToContactMode();
                assetFileDescriptorOpenAssetFileLocal = openAssetFileLocal(uri, str);
            }
            if (AbstractContactsProvider.VERBOSE_LOGGING) {
                Log.v("ContactsProvider", "openAssetFile uri=" + uri + " mode=" + str + " success=true CPID=" + Binder.getCallingPid() + " CUID=" + Binder.getCallingUid() + " User=" + UserUtils.getCurrentUserHandle(getContext()));
            }
            return assetFileDescriptorOpenAssetFileLocal;
        } finally {
            if (AbstractContactsProvider.VERBOSE_LOGGING) {
                Log.v("ContactsProvider", "openAssetFile uri=" + uri + " mode=" + str + " success=false CPID=" + Binder.getCallingPid() + " CUID=" + Binder.getCallingUid() + " User=" + UserUtils.getCurrentUserHandle(getContext()));
            }
        }
    }

    public AssetFileDescriptor openAssetFileLocal(Uri uri, String str) throws FileNotFoundException {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return openAssetFileInner(uri, str);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private AssetFileDescriptor openAssetFileInner(Uri uri, String str) throws Throwable {
        Cursor cursorQuery;
        long j;
        long j2;
        Cursor cursorQuery2;
        String[] strArr;
        String str2;
        String str3;
        String str4;
        int i;
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase database = this.mDbHelper.get().getDatabase(str.contains("w"));
        int iMatch = sUriMatcher.match(uri);
        if (iMatch == 1027) {
            return openCorpContactPicture(Long.parseLong(uri.getPathSegments().get(1)), uri, str, false);
        }
        if (iMatch == 1028) {
            return openCorpContactPicture(Long.parseLong(uri.getPathSegments().get(1)), uri, str, true);
        }
        int i2 = 0;
        if (iMatch == 2006) {
            long j3 = Long.parseLong(uri.getPathSegments().get(1));
            boolean zContains = str.contains("w");
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            String[] strArr2 = {"_id", "data14"};
            setTablesAndProjectionMapForData(sQLiteQueryBuilder, uri, strArr2, false);
            cursorQuery = sQLiteQueryBuilder.query(database, strArr2, "raw_contact_id=? AND mimetype_id=?", new String[]{String.valueOf(j3), String.valueOf(this.mDbHelper.get().getMimeTypeId("vnd.android.cursor.item/photo"))}, null, null, "is_primary DESC");
            try {
                if (cursorQuery.getCount() >= 1) {
                    cursorQuery.moveToFirst();
                    j2 = cursorQuery.getLong(0);
                    j = cursorQuery.getLong(1);
                } else {
                    j = 0;
                    j2 = 0;
                }
                if (zContains) {
                    return openDisplayPhotoForWrite(j3, j2, uri, str);
                }
                return openDisplayPhotoForRead(j);
            } finally {
            }
        }
        if (iMatch == 3001) {
            long j4 = Long.parseLong(uri.getPathSegments().get(1));
            return openPhotoAssetFile(database, uri, str, "_id=? AND mimetype_id=" + this.mDbHelper.get().getMimeTypeId("vnd.android.cursor.item/photo"), new String[]{String.valueOf(j4)});
        }
        if (iMatch == 19004) {
            if (!str.equals("r")) {
                throw new IllegalArgumentException("Write is not supported.");
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            outputRawContactsAsVCard(uri, byteArrayOutputStream, null, null);
            return buildAssetFileDescriptor(byteArrayOutputStream);
        }
        if (iMatch == 19012) {
            if (!str.equals("r")) {
                throw new IllegalArgumentException("Display photos retrieved by contact ID can only be read.");
            }
            cursorQuery2 = database.query("contacts", new String[]{"photo_file_id"}, null, null, null, null, null);
            try {
                if (cursorQuery2.moveToFirst()) {
                    return openDisplayPhotoForRead(cursorQuery2.getLong(0));
                }
                throw new FileNotFoundException(uri.toString());
            } finally {
            }
        }
        if (iMatch == 22000) {
            long id = ContentUris.parseId(uri);
            if (!str.equals("r")) {
                throw new IllegalArgumentException("Display photos retrieved by key can only be read.");
            }
            return openDisplayPhotoForRead(id);
        }
        if (iMatch != 24000) {
            switch (iMatch) {
                case 1009:
                    return openPhotoAssetFile(database, uri, str, "_id=photo_id AND contact_id=?", new String[]{String.valueOf(Long.parseLong(uri.getPathSegments().get(1)))});
                case 1010:
                case 1011:
                case 1013:
                case 1014:
                    if (!str.equals("r")) {
                        throw new IllegalArgumentException("Photos retrieved by contact lookup key can only be read.");
                    }
                    List<String> pathSegments = uri.getPathSegments();
                    int size = pathSegments.size();
                    if (size < 4) {
                        throw new IllegalArgumentException(this.mDbHelper.get().exceptionMessage("Missing a lookup key", uri));
                    }
                    boolean z = iMatch == 1014 || iMatch == 1013;
                    String str5 = pathSegments.get(2);
                    String[] strArr3 = {"photo_id", "photo_file_id"};
                    if (size == 5) {
                        long j5 = Long.parseLong(pathSegments.get(3));
                        SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
                        setTablesAndProjectionMapForContacts(sQLiteQueryBuilder2, strArr3);
                        strArr = strArr3;
                        str3 = str5;
                        sQLiteDatabase = database;
                        Cursor cursorQueryWithContactIdAndLookupKey = queryWithContactIdAndLookupKey(sQLiteQueryBuilder2, database, strArr3, null, null, null, null, null, "_id", j5, "lookup", str3, null);
                        if (cursorQueryWithContactIdAndLookupKey != null) {
                            try {
                                cursorQueryWithContactIdAndLookupKey.moveToFirst();
                                if (z) {
                                    return openDisplayPhotoForRead(cursorQueryWithContactIdAndLookupKey.getLong(cursorQueryWithContactIdAndLookupKey.getColumnIndex("photo_file_id")));
                                }
                                return openPhotoAssetFile(sQLiteDatabase, uri, str, "_id=?", new String[]{String.valueOf(cursorQueryWithContactIdAndLookupKey.getLong(cursorQueryWithContactIdAndLookupKey.getColumnIndex("photo_id")))});
                            } finally {
                                cursorQueryWithContactIdAndLookupKey.close();
                            }
                        }
                        str2 = "photo_id";
                        str4 = "photo_file_id";
                        i = 1;
                        i2 = 0;
                    } else {
                        strArr = strArr3;
                        str2 = "photo_id";
                        str3 = str5;
                        str4 = "photo_file_id";
                        i = 1;
                        sQLiteDatabase = database;
                    }
                    SQLiteQueryBuilder sQLiteQueryBuilder3 = new SQLiteQueryBuilder();
                    String[] strArr4 = strArr;
                    setTablesAndProjectionMapForContacts(sQLiteQueryBuilder3, strArr4);
                    SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                    String[] strArr5 = new String[i];
                    strArr5[i2] = String.valueOf(lookupContactIdByLookupKey(sQLiteDatabase2, str3));
                    cursorQuery = sQLiteQueryBuilder3.query(sQLiteDatabase2, strArr4, "_id=?", strArr5, null, null, null);
                    try {
                        cursorQuery.moveToFirst();
                        if (z) {
                            return openDisplayPhotoForRead(cursorQuery.getLong(cursorQuery.getColumnIndex(str4)));
                        }
                        String[] strArr6 = new String[i];
                        strArr6[i2] = String.valueOf(cursorQuery.getLong(cursorQuery.getColumnIndex(str2)));
                        return openPhotoAssetFile(sQLiteDatabase2, uri, str, "_id=?", strArr6);
                    } finally {
                    }
                case 1012:
                    if (!str.equals("r")) {
                        throw new IllegalArgumentException("Display photos retrieved by contact ID can only be read.");
                    }
                    cursorQuery2 = database.query("contacts", new String[]{"photo_file_id"}, "_id=?", new String[]{String.valueOf(Long.parseLong(uri.getPathSegments().get(1)))}, null, null, null);
                    try {
                        if (cursorQuery2.moveToFirst()) {
                            return openDisplayPhotoForRead(cursorQuery2.getLong(0));
                        }
                        throw new FileNotFoundException(uri.toString());
                    } finally {
                    }
                case 1015:
                    if (!str.equals("r")) {
                        throw new IllegalArgumentException("Write is not supported.");
                    }
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    outputRawContactsAsVCard(uri, byteArrayOutputStream2, null, null);
                    return buildAssetFileDescriptor(byteArrayOutputStream2);
                case 1016:
                    if (!str.equals("r")) {
                        throw new IllegalArgumentException("Write is not supported.");
                    }
                    String[] strArrSplit = uri.getPathSegments().get(2).split(":");
                    StringBuilder sb = new StringBuilder();
                    Uri uri2 = ContactsContract.Contacts.CONTENT_URI;
                    int length = strArrSplit.length;
                    int i3 = 0;
                    while (i2 < length) {
                        String strDecode = Uri.decode(strArrSplit[i2]);
                        sb.append(i3 == 0 ? "(" : ",");
                        sb.append(lookupContactIdByLookupKey(database, strDecode));
                        i3++;
                        i2++;
                    }
                    sb.append(')');
                    String str6 = "_id IN " + sb.toString();
                    ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
                    outputRawContactsAsVCard(uri2, byteArrayOutputStream3, str6, null);
                    return buildAssetFileDescriptor(byteArrayOutputStream3);
                default:
                    throw new FileNotFoundException(this.mDbHelper.get().exceptionMessage("Stream I/O not supported on this URI.", uri));
            }
        }
        return openDirectoryFileEnterprise(uri, str);
    }

    private AssetFileDescriptor openDirectoryFileEnterprise(Uri uri, String str) throws FileNotFoundException {
        Uri uriBuild;
        String queryParameter = getQueryParameter(uri, "directory");
        if (queryParameter == null) {
            throw new IllegalArgumentException("Directory id missing in URI: " + uri);
        }
        long j = Long.parseLong(queryParameter);
        if (!ContactsContract.Directory.isRemoteDirectoryId(j)) {
            throw new IllegalArgumentException("Directory is not a remote directory: " + uri);
        }
        if (ContactsContract.Directory.isEnterpriseDirectoryId(j)) {
            int corpUserId = UserUtils.getCorpUserId(getContext());
            if (corpUserId < 0) {
                throw new FileNotFoundException(uri.toString());
            }
            Uri.Builder builderBuildUpon = ContactsContract.AUTHORITY_URI.buildUpon();
            builderBuildUpon.encodedPath(uri.getEncodedPath());
            builderBuildUpon.appendQueryParameter("directory", String.valueOf(j - 1000000000));
            addQueryParametersFromUri(builderBuildUpon, uri, MODIFIED_KEY_SET_FOR_ENTERPRISE_FILTER);
            uriBuild = ContentProvider.maybeAddUserId(builderBuildUpon.build(), corpUserId);
        } else {
            DirectoryInfo directoryAuthority = getDirectoryAuthority(queryParameter);
            if (directoryAuthority == null) {
                Log.e("ContactsProvider", "Invalid directory ID: " + uri);
                return null;
            }
            Uri uri2 = Uri.parse(uri.getLastPathSegment());
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("content");
            builder.authority(directoryAuthority.authority);
            builder.encodedPath(uri2.getEncodedPath());
            addQueryParametersFromUri(builder, uri2, null);
            uriBuild = builder.build();
        }
        if (AbstractContactsProvider.VERBOSE_LOGGING) {
            Log.v("ContactsProvider", "openDirectoryFileEnterprise: " + uriBuild);
        }
        return getContext().getContentResolver().openAssetFileDescriptor(uriBuild, str);
    }

    private AssetFileDescriptor openCorpContactPicture(long j, Uri uri, String str, boolean z) throws FileNotFoundException {
        if (!str.equals("r")) {
            throw new IllegalArgumentException("Photos retrieved by contact ID can only be read.");
        }
        int corpUserId = UserUtils.getCorpUserId(getContext());
        if (corpUserId < 0) {
            throw new FileNotFoundException(uri.toString());
        }
        return getContext().getContentResolver().openAssetFileDescriptor(ContentProvider.maybeAddUserId(ContentUris.appendId(ContactsContract.Contacts.CONTENT_URI.buildUpon(), j).appendPath(z ? "display_photo" : "photo").build(), corpUserId), str);
    }

    private AssetFileDescriptor openPhotoAssetFile(SQLiteDatabase sQLiteDatabase, Uri uri, String str, String str2, String[] strArr) throws FileNotFoundException {
        if (!"r".equals(str)) {
            throw new FileNotFoundException(this.mDbHelper.get().exceptionMessage("Mode " + str + " not supported.", uri));
        }
        try {
            return makeAssetFileDescriptor(DatabaseUtils.blobFileDescriptorForQuery(sQLiteDatabase, "SELECT data15 FROM view_data WHERE " + str2, strArr));
        } catch (SQLiteDoneException unused) {
            throw new FileNotFoundException(uri.toString());
        }
    }

    private AssetFileDescriptor openDisplayPhotoForRead(long j) throws FileNotFoundException {
        PhotoStore.Entry entry = this.mPhotoStore.get().get(j);
        if (entry != null) {
            try {
                return makeAssetFileDescriptor(ParcelFileDescriptor.open(new File(entry.path), 268435456), entry.size);
            } catch (FileNotFoundException e) {
                this.scheduleBackgroundTask(10);
                throw e;
            }
        }
        scheduleBackgroundTask(10);
        throw new FileNotFoundException("No photo file found for ID " + j);
    }

    private AssetFileDescriptor openDisplayPhotoForWrite(long j, long j2, Uri uri, String str) {
        try {
            ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = ParcelFileDescriptor.createPipe();
            new PipeMonitor(j, j2, parcelFileDescriptorArrCreatePipe[0]).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
            return new AssetFileDescriptor(parcelFileDescriptorArrCreatePipe[1], 0L, -1L);
        } catch (IOException unused) {
            Log.e("ContactsProvider", "Could not create temp image file in mode " + str);
            return null;
        }
    }

    private class PipeMonitor extends AsyncTask<Object, Object, Object> {
        private final long mDataId;
        private final ParcelFileDescriptor mDescriptor;
        private final long mRawContactId;

        private PipeMonitor(long j, long j2, ParcelFileDescriptor parcelFileDescriptor) {
            this.mRawContactId = j;
            this.mDataId = j2;
            this.mDescriptor = parcelFileDescriptor;
        }

        @Override // android.os.AsyncTask
        protected Object doInBackground(Object... objArr) {
            Boolean bool = Boolean.TRUE;
            ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(this.mDescriptor);
            try {
                try {
                    Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(autoCloseInputStream);
                    if (bitmapDecodeStream != null) {
                        ContactsProvider2.this.waitForAccess(ContactsProvider2.this.mWriteAccessLatch);
                        PhotoProcessor photoProcessor = new PhotoProcessor(bitmapDecodeStream, ContactsProvider2.this.getMaxDisplayPhotoDim(), ContactsProvider2.this.getMaxThumbnailDim());
                        long jInsert = (ContactsContract.isProfileId(this.mRawContactId) ? ContactsProvider2.this.mProfilePhotoStore : ContactsProvider2.this.mContactsPhotoStore).insert(photoProcessor);
                        if (this.mDataId != 0) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("skip_processing", bool);
                            if (jInsert != 0) {
                                contentValues.put("data14", Long.valueOf(jInsert));
                            }
                            contentValues.put("data15", photoProcessor.getThumbnailPhotoBytes());
                            ContactsProvider2.this.update(ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, this.mDataId), contentValues, null, null);
                        } else {
                            ContentValues contentValues2 = new ContentValues();
                            contentValues2.put("skip_processing", bool);
                            contentValues2.put("mimetype", "vnd.android.cursor.item/photo");
                            contentValues2.put("is_primary", (Integer) 1);
                            if (jInsert != 0) {
                                contentValues2.put("data14", Long.valueOf(jInsert));
                            }
                            contentValues2.put("data15", photoProcessor.getThumbnailPhotoBytes());
                            ContactsProvider2.this.insert(ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendPath(String.valueOf(this.mRawContactId)).appendPath("data").build(), contentValues2);
                        }
                    }
                    return null;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                IoUtils.closeQuietly(autoCloseInputStream);
            }
        }
    }

    private AssetFileDescriptor buildAssetFileDescriptor(final ByteArrayOutputStream byteArrayOutputStream) {
        try {
            byteArrayOutputStream.flush();
            ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = ParcelFileDescriptor.createPipe();
            final FileDescriptor fileDescriptor = parcelFileDescriptorArrCreatePipe[1].getFileDescriptor();
            new AsyncTask<Object, Object, Object>(this) { // from class: com.android.providers.contacts.ContactsProvider2.2
                @Override // android.os.AsyncTask
                protected Object doInBackground(Object... objArr) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(fileDescriptor);
                        try {
                            fileOutputStream.write(byteArrayOutputStream.toByteArray());
                            fileOutputStream.close();
                        } catch (Throwable th) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } catch (IOException | RuntimeException e) {
                        Log.w("ContactsProvider", "Failure closing pipe", e);
                    }
                    IoUtils.closeQuietly(fileDescriptor);
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
            return makeAssetFileDescriptor(parcelFileDescriptorArrCreatePipe[0]);
        } catch (IOException e) {
            Log.w("ContactsProvider", "Problem writing stream into an ParcelFileDescriptor: " + e.toString());
            return null;
        }
    }

    private AssetFileDescriptor makeAssetFileDescriptor(ParcelFileDescriptor parcelFileDescriptor) {
        return makeAssetFileDescriptor(parcelFileDescriptor, -1L);
    }

    private AssetFileDescriptor makeAssetFileDescriptor(ParcelFileDescriptor parcelFileDescriptor, long j) {
        if (parcelFileDescriptor != null) {
            return new AssetFileDescriptor(parcelFileDescriptor, 0L, j);
        }
        return null;
    }

    private void outputRawContactsAsVCard(Uri uri, OutputStream outputStream, String str, String[] strArr) throws Throwable {
        IOException iOException;
        StringBuilder sb;
        Context context = getContext();
        int i = VCardConfig.VCARD_TYPE_DEFAULT;
        if (uri.getBooleanQueryParameter("no_photo", false)) {
            i |= 8388608;
        }
        VCardComposer vCardComposer = new VCardComposer(context, i, false);
        BufferedWriter bufferedWriter = null;
        Uri uriPreAuthorizeUri = mapsToProfileDb(uri) ? preAuthorizeUri(ContactsContract.RawContactsEntity.PROFILE_CONTENT_URI) : ContactsContract.RawContactsEntity.CONTENT_URI;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream));
                try {
                    if (!vCardComposer.init(uri, str, strArr, null, uriPreAuthorizeUri)) {
                        Log.w("ContactsProvider", "Failed to init VCardComposer");
                        vCardComposer.terminate();
                        try {
                            bufferedWriter2.close();
                            return;
                        } catch (IOException e) {
                            Log.w("ContactsProvider", "IOException during closing output stream: " + e);
                            return;
                        }
                    }
                    while (!vCardComposer.isAfterLast()) {
                        bufferedWriter2.write(vCardComposer.createOneEntry());
                    }
                    vCardComposer.terminate();
                    try {
                        bufferedWriter2.close();
                    } catch (IOException e2) {
                        iOException = e2;
                        sb = new StringBuilder();
                        sb.append("IOException during closing output stream: ");
                        sb.append(iOException);
                        Log.w("ContactsProvider", sb.toString());
                    }
                } catch (IOException e3) {
                    e = e3;
                    bufferedWriter = bufferedWriter2;
                    Log.e("ContactsProvider", "IOException: " + e);
                    vCardComposer.terminate();
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e4) {
                            iOException = e4;
                            sb = new StringBuilder();
                            sb.append("IOException during closing output stream: ");
                            sb.append(iOException);
                            Log.w("ContactsProvider", sb.toString());
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    Throwable th2 = th;
                    vCardComposer.terminate();
                    if (bufferedWriter == null) {
                        throw th2;
                    }
                    try {
                        bufferedWriter.close();
                        throw th2;
                    } catch (IOException e5) {
                        Log.w("ContactsProvider", "IOException during closing output stream: " + e5);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e6) {
            e = e6;
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int iMatch = sUriMatcher.match(uri);
        if (iMatch == 2002) {
            return "vnd.android.cursor.dir/raw_contact";
        }
        if (iMatch == 2003) {
            return "vnd.android.cursor.item/raw_contact";
        }
        if (iMatch == 3005) {
            return "vnd.android.cursor.dir/email_v2";
        }
        if (iMatch == 3006) {
            return "vnd.android.cursor.item/email_v2";
        }
        if (iMatch == 3009) {
            return "vnd.android.cursor.dir/postal-address_v2";
        }
        if (iMatch == 3010) {
            return "vnd.android.cursor.item/postal-address_v2";
        }
        if (iMatch == 4000 || iMatch == 4001) {
            return "vnd.android.cursor.dir/phone_lookup";
        }
        if (iMatch == 6000) {
            return "vnd.android.cursor.dir/aggregation_exception";
        }
        if (iMatch == 6001) {
            return "vnd.android.cursor.item/aggregation_exception";
        }
        if (iMatch == 12001) {
            return "vnd.android.cursor.dir/vnd.android.search.suggest";
        }
        if (iMatch == 12002) {
            return "vnd.android.cursor.item/vnd.android.search.suggest";
        }
        switch (iMatch) {
            case 1000:
                return "vnd.android.cursor.dir/contact";
            case 1001:
            case 1002:
            case 1003:
                return "vnd.android.cursor.item/contact";
            default:
                switch (iMatch) {
                    case 1009:
                    case 1010:
                    case 1011:
                    case 1012:
                    case 1013:
                    case 1014:
                    case 2006:
                    case 22000:
                        return "image/jpeg";
                    case 1015:
                    case 1016:
                    case 19004:
                        return "text/x-vcard";
                    case 3016:
                        return "vnd.android.cursor.dir/phone_v2";
                    case 8000:
                        return "vnd.android.cursor.dir/contact";
                    case 9000:
                        return "vnd.android.cursor.dir/setting";
                    case 16001:
                        return "vnd.android.cursor.dir/provider_status";
                    case 19000:
                        return "vnd.android.cursor.item/contact";
                    case 19002:
                        return "vnd.android.cursor.dir/data";
                    case 19005:
                        return "vnd.android.cursor.dir/raw_contact";
                    case 19006:
                        return "vnd.android.cursor.item/raw_contact";
                    case 21000:
                        return "vnd.android.cursor.dir/stream_item";
                    case 21001:
                        throw new UnsupportedOperationException("Not supported for write-only URI " + uri);
                    case 21002:
                        return "vnd.android.cursor.item/stream_item";
                    case 21003:
                        return "vnd.android.cursor.dir/stream_item_photo";
                    case 21004:
                        return "vnd.android.cursor.item/stream_item_photo";
                    default:
                        switch (iMatch) {
                            case 3000:
                                return "vnd.android.cursor.dir/data";
                            case 3001:
                                waitForAccess(this.mReadAccessLatch);
                                long id = ContentUris.parseId(uri);
                                if (ContactsContract.isProfileId(id)) {
                                    return this.mProfileHelper.getDataMimeType(id);
                                }
                                return this.mContactsHelper.getDataMimeType(id);
                            case 3002:
                                return "vnd.android.cursor.dir/phone_v2";
                            case 3003:
                                return "vnd.android.cursor.item/phone_v2";
                            default:
                                switch (iMatch) {
                                    case 17001:
                                    case 17003:
                                        return "vnd.android.cursor.dir/contact_directories";
                                    case 17002:
                                    case 17004:
                                        return "vnd.android.cursor.item/contact_directory";
                                    default:
                                        waitForAccess(this.mReadAccessLatch);
                                        return this.mLegacyApiSupport.getType(uri);
                                }
                        }
                }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:33:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String[] getDefaultProjection(android.net.Uri r1) {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.ContactsProvider2.getDefaultProjection(android.net.Uri):java.lang.String[]");
    }

    private class StructuredNameLookupBuilder extends NameLookupBuilder {
        public StructuredNameLookupBuilder(NameSplitter nameSplitter) {
            super(nameSplitter);
        }

        @Override // com.android.providers.contacts.NameLookupBuilder
        protected void insertNameLookup(long j, long j2, int i, String str) {
            ((ContactsDatabaseHelper) ContactsProvider2.this.mDbHelper.get()).insertNameLookup(j, j2, i, str);
        }

        @Override // com.android.providers.contacts.NameLookupBuilder
        protected String[] getCommonNicknameClusters(String str) {
            return ContactsProvider2.this.mCommonNicknameCache.getCommonNicknameClusters(str);
        }
    }

    public void appendContactFilterAsNestedQuery(StringBuilder sb, String str) {
        sb.append("(SELECT DISTINCT contact_id FROM raw_contacts JOIN name_lookup ON(raw_contacts._id=raw_contact_id) WHERE normalized_name GLOB '");
        sb.append(NameNormalizer.normalize(str));
        sb.append("*' AND name_type IN(2,4,3))");
    }

    private boolean isPhoneNumber(String str) {
        return !TextUtils.isEmpty(str) && countPhoneNumberDigits(str) > 0;
    }

    public static int countPhoneNumberDigits(String str) {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (Character.isDigit(cCharAt)) {
                i++;
            } else if (cCharAt != '*' && cCharAt != '#' && cCharAt != 'N' && cCharAt != '.' && cCharAt != ';' && cCharAt != '-' && cCharAt != '(' && cCharAt != ')' && cCharAt != ' ' && (cCharAt != '+' || i != 0)) {
                return 0;
            }
        }
        return i;
    }

    private Cursor completeName(Uri uri, String[] strArr) {
        if (strArr == null) {
            strArr = sDataProjectionMap.getColumnNames();
        }
        ContentValues contentValues = new ContentValues();
        DataRowHandlerForStructuredName dataRowHandlerForStructuredName = (DataRowHandlerForStructuredName) getDataRowHandler("vnd.android.cursor.item/name");
        copyQueryParamsToContentValues(contentValues, uri, "data1", "data4", "data2", "data5", "data3", "data6", "phonetic_name", "data9", "data8", "data7");
        dataRowHandlerForStructuredName.fixStructuredNameComponents(contentValues, contentValues);
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        Object[] objArr = new Object[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            objArr[i] = contentValues.get(strArr[i]);
        }
        matrixCursor.addRow(objArr);
        return matrixCursor;
    }

    private void copyQueryParamsToContentValues(ContentValues contentValues, Uri uri, String... strArr) {
        for (String str : strArr) {
            String queryParameter = uri.getQueryParameter(str);
            if (queryParameter != null) {
                contentValues.put(str, queryParameter);
            }
        }
    }

    private String[] insertSelectionArg(String[] strArr, String str) {
        if (strArr == null) {
            return new String[]{str};
        }
        String[] strArr2 = new String[strArr.length + 1];
        strArr2[0] = str;
        System.arraycopy(strArr, 0, strArr2, 1, strArr.length);
        return strArr2;
    }

    private String[] appendSelectionArg(String[] strArr, String str) {
        if (strArr == null) {
            return new String[]{str};
        }
        int length = strArr.length + 1;
        String[] strArr2 = new String[length];
        strArr2[length] = str;
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length - 1);
        return strArr2;
    }

    protected Account getDefaultAccount() {
        try {
            Account[] accountsByType = AccountManager.get(getContext()).getAccountsByType("com.google");
            if (accountsByType == null || accountsByType.length <= 0) {
                return null;
            }
            return accountsByType[0];
        } catch (Throwable th) {
            Log.e("ContactsProvider", "Cannot determine the default account for contacts compatibility", th);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0033, code lost:
    
        r0 = java.lang.Boolean.valueOf(r4.supportsUploading());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isWritableAccountWithDataSet(java.lang.String r8) {
        /*
            r7 = this;
            if (r8 != 0) goto L4
            r7 = 1
            return r7
        L4:
            android.util.ArrayMap<java.lang.String, java.lang.Boolean> r0 = r7.mAccountWritability
            java.lang.Object r0 = r0.get(r8)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            if (r0 == 0) goto L13
            boolean r7 = r0.booleanValue()
            return r7
        L13:
            android.content.IContentService r1 = android.content.ContentResolver.getContentService()
            android.content.SyncAdapterType[] r1 = r1.getSyncAdapterTypes()     // Catch: android.os.RemoteException -> L3f
            int r2 = r1.length     // Catch: android.os.RemoteException -> L3f
            r3 = 0
        L1d:
            if (r3 >= r2) goto L46
            r4 = r1[r3]     // Catch: android.os.RemoteException -> L3f
            java.lang.String r5 = "com.android.contacts"
            java.lang.String r6 = r4.authority     // Catch: android.os.RemoteException -> L3f
            boolean r5 = r5.equals(r6)     // Catch: android.os.RemoteException -> L3f
            if (r5 == 0) goto L3c
            java.lang.String r5 = r4.accountType     // Catch: android.os.RemoteException -> L3f
            boolean r5 = r8.equals(r5)     // Catch: android.os.RemoteException -> L3f
            if (r5 == 0) goto L3c
            boolean r1 = r4.supportsUploading()     // Catch: android.os.RemoteException -> L3f
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)     // Catch: android.os.RemoteException -> L3f
            goto L46
        L3c:
            int r3 = r3 + 1
            goto L1d
        L3f:
            java.lang.String r1 = "ContactsProvider"
            java.lang.String r2 = "Could not acquire sync adapter types"
            android.util.Log.e(r1, r2)
        L46:
            if (r0 != 0) goto L4a
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
        L4a:
            android.util.ArrayMap<java.lang.String, java.lang.Boolean> r7 = r7.mAccountWritability
            r7.put(r8, r0)
            boolean r7 = r0.booleanValue()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.ContactsProvider2.isWritableAccountWithDataSet(java.lang.String):boolean");
    }

    static boolean readBooleanQueryParameter(Uri uri, String str, boolean z) {
        int iIndexOf;
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null || (iIndexOf = encodedQuery.indexOf(str)) == -1) {
            return z;
        }
        int length = iIndexOf + str.length();
        return (matchQueryParameter(encodedQuery, length, "=0", false) || matchQueryParameter(encodedQuery, length, "=false", true)) ? false : true;
    }

    private static boolean matchQueryParameter(String str, int i, String str2, boolean z) {
        int i2;
        int length = str2.length();
        return str.regionMatches(z, i, str2, 0, length) && (str.length() == (i2 = i + length) || str.charAt(i2) == '&');
    }

    static String getQueryParameter(Uri uri, String str) {
        String strSubstring;
        char cCharAt;
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null) {
            return null;
        }
        int length = encodedQuery.length();
        int length2 = str.length();
        int i = 0;
        while (true) {
            int iIndexOf = encodedQuery.indexOf(str, i);
            if (iIndexOf == -1) {
                return null;
            }
            if (iIndexOf <= 0 || (cCharAt = encodedQuery.charAt(iIndexOf - 1)) == '?' || cCharAt == '&') {
                i = iIndexOf + length2;
                if (length == i) {
                    return null;
                }
                if (encodedQuery.charAt(i) == '=') {
                    int i2 = i + 1;
                    int iIndexOf2 = encodedQuery.indexOf(38, i2);
                    if (iIndexOf2 == -1) {
                        strSubstring = encodedQuery.substring(i2);
                    } else {
                        strSubstring = encodedQuery.substring(i2, iIndexOf2);
                    }
                    return Uri.decode(strSubstring);
                }
            } else {
                i = iIndexOf + length2;
            }
        }
    }

    private boolean isAggregationUpgradeNeeded() {
        return this.mContactAggregator.isEnabled() && Integer.parseInt(this.mContactsHelper.getProperty("aggregation_v2", "1")) < PROPERTY_AGGREGATION_ALGORITHM_VERSION;
    }

    /* JADX WARN: Finally extract failed */
    private void upgradeAggregationAlgorithmInBackground() {
        int iMarkAllVisibleForAggregation;
        Log.i("ContactsProvider", "Upgrading aggregation algorithm");
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean z = true;
        setProviderStatus(1);
        SQLiteDatabase writableDatabase = null;
        try {
            try {
                try {
                    switchToContactMode();
                    writableDatabase = this.mContactsHelper.getWritableDatabase();
                    writableDatabase.beginTransaction();
                } catch (Throwable th) {
                    th = th;
                    z = false;
                    iMarkAllVisibleForAggregation = 0;
                }
            } catch (RuntimeException e) {
                Log.e("ContactsProvider", "Failed to upgrade aggregation algorithm; continuing anyway.", e);
                try {
                    SQLiteDatabase writableDatabase2 = this.mContactsHelper.getWritableDatabase();
                    writableDatabase2.beginTransactionNonExclusive();
                    try {
                        updateAggregationAlgorithmVersion();
                        writableDatabase2.setTransactionSuccessful();
                        writableDatabase2.endTransaction();
                    } catch (Throwable th2) {
                        writableDatabase2.endTransaction();
                        throw th2;
                    }
                } catch (RuntimeException e2) {
                    Log.e("ContactsProvider", "Failed to bump aggregation algorithm version; continuing anyway.", e2);
                }
            }
            try {
                iMarkAllVisibleForAggregation = this.mContactAggregator.markAllVisibleForAggregation(writableDatabase);
                try {
                    this.mContactAggregator.aggregateInTransaction(this.mTransactionContext.get(), writableDatabase);
                    updateSearchIndexInTransaction();
                    updateAggregationAlgorithmVersion();
                    writableDatabase.setTransactionSuccessful();
                    this.mTransactionContext.get().clearAll();
                    writableDatabase.endTransaction();
                    long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Aggregation algorithm upgraded for ");
                    sb.append(iMarkAllVisibleForAggregation);
                    sb.append(" raw contacts");
                    sb.append(" in " + (jElapsedRealtime2 - jElapsedRealtime) + "ms");
                    Log.i("ContactsProvider", sb.toString());
                } catch (Throwable th3) {
                    th = th3;
                    this.mTransactionContext.get().clearAll();
                    if (z) {
                        writableDatabase.endTransaction();
                    }
                    SystemClock.elapsedRealtime();
                    Log.i("ContactsProvider", "Aggregation algorithm upgraded for " + iMarkAllVisibleForAggregation + " raw contacts failed");
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                iMarkAllVisibleForAggregation = 0;
            }
        } finally {
            setProviderStatus(0);
        }
    }

    private void updateAggregationAlgorithmVersion() {
        this.mContactsHelper.setProperty("aggregation_v2", String.valueOf(PROPERTY_AGGREGATION_ALGORITHM_VERSION));
    }

    protected boolean isPhone() {
        if (!this.mIsPhoneInitialized) {
            this.mIsPhone = isVoiceCapable();
            this.mIsPhoneInitialized = true;
        }
        return this.mIsPhone;
    }

    protected boolean isVoiceCapable() {
        return ((TelephonyManager) getContext().getSystemService(TelephonyManager.class)).isVoiceCapable();
    }

    private void undemoteContact(SQLiteDatabase sQLiteDatabase, long j) {
        String[] strArr = {String.valueOf(j)};
        sQLiteDatabase.execSQL("UPDATE contacts SET pinned = 0 WHERE _id = ?1 AND pinned <= -1", strArr);
        sQLiteDatabase.execSQL("UPDATE raw_contacts SET pinned = 0 WHERE contact_id = ?1 AND pinned <= -1", strArr);
    }

    private String getAccountPromotionSortOrder(Uri uri) {
        String queryParameter = uri.getQueryParameter("name_for_primary_account");
        String queryParameter2 = uri.getQueryParameter("type_for_primary_account");
        if (TextUtils.isEmpty(queryParameter)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(CASE WHEN account_name=");
        DatabaseUtils.appendEscapedSQLString(sb, queryParameter);
        if (!TextUtils.isEmpty(queryParameter2)) {
            sb.append(" AND account_type=");
            DatabaseUtils.appendEscapedSQLString(sb, queryParameter2);
        }
        sb.append(" THEN 0 ELSE 1 END)");
        return sb.toString();
    }

    private boolean deferredSnippetingRequested(Uri uri) {
        String queryParameter = getQueryParameter(uri, "deferred_snippeting");
        return !TextUtils.isEmpty(queryParameter) && queryParameter.equals("1");
    }

    private boolean isSingleWordQuery(String str) {
        int i = 0;
        for (String str2 : str.split("[^\\w@]+", 0)) {
            if (!"".equals(str2)) {
                i++;
            }
        }
        return i == 1;
    }

    private boolean snippetNeeded(String[] strArr) {
        return ContactsDatabaseHelper.isInProjection(strArr, "snippet");
    }

    private void replacePackageNameByPackageId(ContentValues contentValues) {
        if (contentValues != null) {
            String asString = contentValues.getAsString("res_package");
            if (asString != null) {
                contentValues.put("package_id", Long.valueOf(this.mDbHelper.get().getPackageId(asString)));
            }
            contentValues.remove("res_package");
        }
    }

    private long replaceAccountInfoByAccountId(Uri uri, ContentValues contentValues) {
        long orCreateAccountIdInTransaction = this.mDbHelper.get().getOrCreateAccountIdInTransaction(resolveAccountWithDataSet(uri, contentValues));
        contentValues.put("account_id", Long.valueOf(orCreateAccountIdInTransaction));
        contentValues.remove("account_name");
        contentValues.remove("account_type");
        contentValues.remove("data_set");
        return orCreateAccountIdInTransaction;
    }

    static Cursor buildSingleRowResult(String[] strArr, String[] strArr2, Object[] objArr) {
        boolean z;
        Preconditions.checkArgument(strArr2.length == objArr.length);
        if (strArr == null) {
            strArr = strArr2;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr, 1);
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        for (int i = 0; i < matrixCursor.getColumnCount(); i++) {
            String columnName = matrixCursor.getColumnName(i);
            int i2 = 0;
            while (true) {
                if (i2 >= strArr2.length) {
                    z = false;
                    break;
                }
                if (strArr2[i2].equals(columnName)) {
                    rowBuilderNewRow.add(objArr[i2]);
                    z = true;
                    break;
                }
                i2++;
            }
            if (!z) {
                throw new IllegalArgumentException("Invalid column " + strArr[i]);
            }
        }
        return matrixCursor;
    }

    public ContactsDatabaseHelper getThreadActiveDatabaseHelperForTest() {
        return this.mDbHelper.get();
    }

    @Override // android.content.ContentProvider
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (this.mContactAggregator != null) {
            printWriter.println();
            printWriter.print("Contact aggregator type: " + this.mContactAggregator.getClass() + "\n");
        }
        printWriter.println();
        printWriter.print("FastScrollingIndex stats:\n");
        printWriter.printf("  request=%d  miss=%d (%d%%)  avg time=%dms\n", Integer.valueOf(this.mFastScrollingIndexCacheRequestCount), Integer.valueOf(this.mFastScrollingIndexCacheMissCount), Long.valueOf(safeDiv(this.mFastScrollingIndexCacheMissCount * 100, this.mFastScrollingIndexCacheRequestCount)), Long.valueOf(safeDiv(this.mTotalTimeFastScrollingIndexGenerate, this.mFastScrollingIndexCacheMissCount)));
        printWriter.println();
        ContactsDatabaseHelper contactsDatabaseHelper = this.mContactsHelper;
        if (contactsDatabaseHelper != null) {
            contactsDatabaseHelper.dump(printWriter);
        }
        dump(printWriter, "Contacts");
        printWriter.println();
        this.mProfileProvider.dump(fileDescriptor, printWriter, strArr);
    }

    private static final long safeDiv(long j, long j2) {
        if (j2 == 0) {
            return 0L;
        }
        return j / j2;
    }

    private static final int getDataUsageFeedbackType(String str, Integer num) {
        if ("call".equals(str)) {
            return 0;
        }
        if ("long_text".equals(str)) {
            return 1;
        }
        if ("short_text".equals(str)) {
            return 2;
        }
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("Invalid usage type " + str);
    }

    private static final int getAggregationType(String str, Integer num) {
        if ("TOGETHER".equalsIgnoreCase(str)) {
            return 1;
        }
        if ("SEPARATE".equalsIgnoreCase(str)) {
            return 2;
        }
        if ("AUTOMATIC".equalsIgnoreCase(str)) {
            return 0;
        }
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("Invalid aggregation type " + str);
    }

    public void switchToProfileModeForTest() {
        switchToProfileMode();
    }

    @Override // android.content.ContentProvider
    public void shutdown() {
        this.mTaskScheduler.shutdownForTest();
    }

    public ContactsDatabaseHelper getContactsDatabaseHelperForTest() {
        return this.mContactsHelper;
    }

    public ProfileProvider getProfileProviderForTest() {
        return this.mProfileProvider;
    }
}

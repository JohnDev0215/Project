package com.android.common.contacts;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import com.android.common.speech.LoggingEvents;
import com.android.common.widget.CompositeCursorAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseEmailAddressAdapter extends CompositeCursorAdapter implements Filterable {
    private static final int ALLOWANCE_FOR_DUPLICATES = 5;
    private static final int DEFAULT_PREFERRED_MAX_RESULT_COUNT = 10;
    private static final long DIRECTORY_LOCAL_INVISIBLE = 1;
    private static final String DIRECTORY_PARAM_KEY = "directory";
    private static final String LIMIT_PARAM_KEY = "limit";
    private static final int MESSAGE_SEARCH_PENDING = 1;
    private static final int MESSAGE_SEARCH_PENDING_DELAY = 1000;
    private static final String PRIMARY_ACCOUNT_NAME = "name_for_primary_account";
    private static final String PRIMARY_ACCOUNT_TYPE = "type_for_primary_account";
    private static final String SEARCHING_CURSOR_MARKER = "searching";
    private static final String TAG = "BaseEmailAddressAdapter";
    private Account mAccount;
    protected final ContentResolver mContentResolver;
    private boolean mDirectoriesLoaded;
    private Handler mHandler;
    private int mPreferredMaxResultCount;

    protected abstract void bindView(View view, String str, String str2, String str3, String str4);

    protected abstract void bindViewLoading(View view, String str, String str2);

    protected abstract View inflateItemView(ViewGroup viewGroup);

    protected abstract View inflateItemViewLoading(ViewGroup viewGroup);

    public static final class DirectoryPartition extends CompositeCursorAdapter.Partition {
        public String accountName;
        public String accountType;
        public CharSequence constraint;
        public long directoryId;
        public String directoryType;
        public String displayName;
        public DirectoryPartitionFilter filter;
        public boolean loading;

        public DirectoryPartition() {
            super(false, false);
        }
    }

    private static class EmailQuery {
        public static final int ADDRESS = 1;
        public static final int NAME = 0;
        public static final String[] PROJECTION = {"display_name", "data1"};

        private EmailQuery() {
        }
    }

    private static class DirectoryListQuery {
        public static final int ACCOUNT_NAME = 1;
        public static final int ACCOUNT_TYPE = 2;
        public static final int DISPLAY_NAME = 3;
        public static final int ID = 0;
        public static final int PACKAGE_NAME = 4;
        public static final int TYPE_RESOURCE_ID = 5;
        public static final Uri URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "directories");
        private static final String DIRECTORY_ID = "_id";
        private static final String DIRECTORY_ACCOUNT_NAME = "accountName";
        private static final String DIRECTORY_ACCOUNT_TYPE = "accountType";
        private static final String DIRECTORY_DISPLAY_NAME = "displayName";
        private static final String DIRECTORY_PACKAGE_NAME = "packageName";
        private static final String DIRECTORY_TYPE_RESOURCE_ID = "typeResourceId";
        public static final String[] PROJECTION = {DIRECTORY_ID, DIRECTORY_ACCOUNT_NAME, DIRECTORY_ACCOUNT_TYPE, DIRECTORY_DISPLAY_NAME, DIRECTORY_PACKAGE_NAME, DIRECTORY_TYPE_RESOURCE_ID};

        private DirectoryListQuery() {
        }
    }

    private final class DefaultPartitionFilter extends Filter {
        private DefaultPartitionFilter() {
        }

        @Override // android.widget.Filter
        protected Filter.FilterResults performFiltering(CharSequence constraint) {
            Cursor directoryCursor = null;
            if (!BaseEmailAddressAdapter.this.mDirectoriesLoaded) {
                directoryCursor = BaseEmailAddressAdapter.this.mContentResolver.query(DirectoryListQuery.URI, DirectoryListQuery.PROJECTION, null, null, null);
                BaseEmailAddressAdapter.this.mDirectoriesLoaded = true;
            }
            Filter.FilterResults results = new Filter.FilterResults();
            Cursor cursor = null;
            if (!TextUtils.isEmpty(constraint)) {
                Uri.Builder builder = ContactsContract.CommonDataKinds.Email.CONTENT_FILTER_URI.buildUpon().appendPath(constraint.toString()).appendQueryParameter(BaseEmailAddressAdapter.LIMIT_PARAM_KEY, String.valueOf(BaseEmailAddressAdapter.this.mPreferredMaxResultCount));
                if (BaseEmailAddressAdapter.this.mAccount != null) {
                    builder.appendQueryParameter(BaseEmailAddressAdapter.PRIMARY_ACCOUNT_NAME, BaseEmailAddressAdapter.this.mAccount.name);
                    builder.appendQueryParameter(BaseEmailAddressAdapter.PRIMARY_ACCOUNT_TYPE, BaseEmailAddressAdapter.this.mAccount.type);
                }
                Uri uri = builder.build();
                cursor = BaseEmailAddressAdapter.this.mContentResolver.query(uri, EmailQuery.PROJECTION, null, null, null);
                results.count = cursor.getCount();
            }
            results.values = new Cursor[]{directoryCursor, cursor};
            return results;
        }

        @Override // android.widget.Filter
        protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
            if (results.values != null) {
                Cursor[] cursors = (Cursor[]) results.values;
                BaseEmailAddressAdapter.this.onDirectoryLoadFinished(constraint, cursors[0], cursors[1]);
            }
            results.count = BaseEmailAddressAdapter.this.getCount();
        }

        @Override // android.widget.Filter
        public CharSequence convertResultToString(Object resultValue) {
            return BaseEmailAddressAdapter.this.makeDisplayString((Cursor) resultValue);
        }
    }

    private final class DirectoryPartitionFilter extends Filter {
        private final long mDirectoryId;
        private int mLimit;
        private final int mPartitionIndex;

        public DirectoryPartitionFilter(int partitionIndex, long directoryId) {
            this.mPartitionIndex = partitionIndex;
            this.mDirectoryId = directoryId;
        }

        public synchronized void setLimit(int limit) {
            this.mLimit = limit;
        }

        public synchronized int getLimit() {
            return this.mLimit;
        }

        @Override // android.widget.Filter
        protected Filter.FilterResults performFiltering(CharSequence constraint) {
            Filter.FilterResults results = new Filter.FilterResults();
            if (!TextUtils.isEmpty(constraint)) {
                Uri uri = ContactsContract.CommonDataKinds.Email.CONTENT_FILTER_URI.buildUpon().appendPath(constraint.toString()).appendQueryParameter(BaseEmailAddressAdapter.DIRECTORY_PARAM_KEY, String.valueOf(this.mDirectoryId)).appendQueryParameter(BaseEmailAddressAdapter.LIMIT_PARAM_KEY, String.valueOf(getLimit() + 5)).build();
                Cursor cursor = BaseEmailAddressAdapter.this.mContentResolver.query(uri, EmailQuery.PROJECTION, null, null, null);
                results.values = cursor;
            }
            return results;
        }

        @Override // android.widget.Filter
        protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
            Cursor cursor = (Cursor) results.values;
            BaseEmailAddressAdapter.this.onPartitionLoadFinished(constraint, this.mPartitionIndex, cursor);
            results.count = BaseEmailAddressAdapter.this.getCount();
        }
    }

    public BaseEmailAddressAdapter(Context context) {
        this(context, 10);
    }

    public BaseEmailAddressAdapter(Context context, int preferredMaxResultCount) {
        super(context);
        this.mContentResolver = context.getContentResolver();
        this.mPreferredMaxResultCount = preferredMaxResultCount;
        this.mHandler = new Handler() { // from class: com.android.common.contacts.BaseEmailAddressAdapter.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                BaseEmailAddressAdapter.this.showSearchPendingIfNotComplete(msg.arg1);
            }
        };
    }

    public void setAccount(Account account) {
        this.mAccount = account;
    }

    @Override // com.android.common.widget.CompositeCursorAdapter
    protected int getItemViewType(int i, int i2) {
        return ((DirectoryPartition) getPartition(i)).loading ? 1 : 0;
    }

    @Override // com.android.common.widget.CompositeCursorAdapter
    protected View newView(Context context, int partitionIndex, Cursor cursor, int position, ViewGroup parent) {
        DirectoryPartition partition = (DirectoryPartition) getPartition(partitionIndex);
        if (partition.loading) {
            return inflateItemViewLoading(parent);
        }
        return inflateItemView(parent);
    }

    @Override // com.android.common.widget.CompositeCursorAdapter
    protected void bindView(View v, int partition, Cursor cursor, int position) {
        String displayName;
        String emailAddress;
        DirectoryPartition directoryPartition = (DirectoryPartition) getPartition(partition);
        String directoryType = directoryPartition.directoryType;
        String directoryName = directoryPartition.displayName;
        if (directoryPartition.loading) {
            bindViewLoading(v, directoryType, directoryName);
            return;
        }
        String displayName2 = cursor.getString(0);
        String emailAddress2 = cursor.getString(1);
        if (TextUtils.isEmpty(displayName2) || TextUtils.equals(displayName2, emailAddress2)) {
            displayName = emailAddress2;
            emailAddress = null;
        } else {
            displayName = displayName2;
            emailAddress = emailAddress2;
        }
        bindView(v, directoryType, directoryName, displayName, emailAddress);
    }

    @Override // com.android.common.widget.CompositeCursorAdapter, android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override // com.android.common.widget.CompositeCursorAdapter
    protected boolean isEnabled(int partitionIndex, int position) {
        return !isLoading(partitionIndex);
    }

    private boolean isLoading(int partitionIndex) {
        return ((DirectoryPartition) getPartition(partitionIndex)).loading;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        return new DefaultPartitionFilter();
    }

    protected void onDirectoryLoadFinished(CharSequence constraint, Cursor directoryCursor, Cursor defaultPartitionCursor) {
        Cursor cursor = directoryCursor;
        int i = 0;
        int i2 = 1;
        if (cursor != null) {
            PackageManager packageManager = getContext().getPackageManager();
            List<DirectoryPartition> directories = new ArrayList<>();
            DirectoryPartition preferredDirectory = null;
            while (directoryCursor.moveToNext()) {
                long id = cursor.getLong(i);
                if (id != DIRECTORY_LOCAL_INVISIBLE) {
                    DirectoryPartition partition = new DirectoryPartition();
                    partition.directoryId = id;
                    partition.displayName = cursor.getString(3);
                    partition.accountName = cursor.getString(i2);
                    partition.accountType = cursor.getString(2);
                    String packageName = cursor.getString(4);
                    int resourceId = cursor.getInt(5);
                    if (packageName != null && resourceId != 0) {
                        try {
                            Resources resources = packageManager.getResourcesForApplication(packageName);
                            partition.directoryType = resources.getString(resourceId);
                            if (partition.directoryType == null) {
                                Log.e(TAG, "Cannot resolve directory name: " + resourceId + "@" + packageName);
                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            Log.e(TAG, "Cannot resolve directory name: " + resourceId + "@" + packageName, e);
                        }
                    }
                    Account account = this.mAccount;
                    if (account != null && account.name.equals(partition.accountName) && this.mAccount.type.equals(partition.accountType)) {
                        preferredDirectory = partition;
                    } else {
                        directories.add(partition);
                    }
                    cursor = directoryCursor;
                    i = 0;
                    i2 = 1;
                }
            }
            if (preferredDirectory != null) {
                directories.add(1, preferredDirectory);
            }
            Iterator<DirectoryPartition> it = directories.iterator();
            while (it.hasNext()) {
                addPartition(it.next());
            }
        }
        int count = getPartitionCount();
        setNotificationsEnabled(false);
        if (defaultPartitionCursor != null) {
            try {
                if (getPartitionCount() > 0) {
                    changeCursor(0, defaultPartitionCursor);
                }
            } catch (Throwable th) {
                setNotificationsEnabled(true);
                throw th;
            }
        }
        int defaultPartitionCount = defaultPartitionCursor == null ? 0 : defaultPartitionCursor.getCount();
        int limit = this.mPreferredMaxResultCount - defaultPartitionCount;
        for (int i3 = 1; i3 < count; i3++) {
            DirectoryPartition partition2 = (DirectoryPartition) getPartition(i3);
            partition2.constraint = constraint;
            if (limit > 0) {
                if (!partition2.loading) {
                    partition2.loading = true;
                    changeCursor(i3, null);
                }
            } else {
                partition2.loading = false;
                changeCursor(i3, null);
            }
        }
        setNotificationsEnabled(true);
        for (int i4 = 1; i4 < count; i4++) {
            DirectoryPartition partition3 = (DirectoryPartition) getPartition(i4);
            if (partition3.loading) {
                this.mHandler.removeMessages(1, partition3);
                Message msg = this.mHandler.obtainMessage(1, i4, 0, partition3);
                this.mHandler.sendMessageDelayed(msg, 1000L);
                if (partition3.filter == null) {
                    partition3.filter = new DirectoryPartitionFilter(i4, partition3.directoryId);
                }
                partition3.filter.setLimit(limit);
                partition3.filter.filter(constraint);
            } else if (partition3.filter != null) {
                partition3.filter.filter(null);
            }
        }
    }

    void showSearchPendingIfNotComplete(int partitionIndex) {
        if (partitionIndex < getPartitionCount()) {
            DirectoryPartition partition = (DirectoryPartition) getPartition(partitionIndex);
            if (partition.loading) {
                changeCursor(partitionIndex, createLoadingCursor());
            }
        }
    }

    private Cursor createLoadingCursor() {
        MatrixCursor cursor = new MatrixCursor(new String[]{SEARCHING_CURSOR_MARKER});
        cursor.addRow(new Object[]{LoggingEvents.EXTRA_CALLING_APP_NAME});
        return cursor;
    }

    public void onPartitionLoadFinished(CharSequence constraint, int partitionIndex, Cursor cursor) {
        if (partitionIndex < getPartitionCount()) {
            DirectoryPartition partition = (DirectoryPartition) getPartition(partitionIndex);
            if (partition.loading && TextUtils.equals(constraint, partition.constraint)) {
                partition.loading = false;
                this.mHandler.removeMessages(1, partition);
                changeCursor(partitionIndex, removeDuplicatesAndTruncate(partitionIndex, cursor));
                return;
            } else {
                if (cursor != null) {
                    cursor.close();
                    return;
                }
                return;
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    private Cursor removeDuplicatesAndTruncate(int partition, Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        if (cursor.getCount() <= 10 && !hasDuplicates(cursor, partition)) {
            return cursor;
        }
        int count = 0;
        MatrixCursor newCursor = new MatrixCursor(EmailQuery.PROJECTION);
        cursor.moveToPosition(-1);
        while (cursor.moveToNext() && count < 10) {
            String displayName = cursor.getString(0);
            String emailAddress = cursor.getString(1);
            if (!isDuplicate(emailAddress, partition)) {
                newCursor.addRow(new Object[]{displayName, emailAddress});
                count++;
            }
        }
        cursor.close();
        return newCursor;
    }

    private boolean hasDuplicates(Cursor cursor, int partition) {
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            String emailAddress = cursor.getString(1);
            if (isDuplicate(emailAddress, partition)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicate(String emailAddress, int excludePartition) {
        Cursor cursor;
        int partitionCount = getPartitionCount();
        for (int partition = 0; partition < partitionCount; partition++) {
            if (partition != excludePartition && !isLoading(partition) && (cursor = getCursor(partition)) != null) {
                cursor.moveToPosition(-1);
                while (cursor.moveToNext()) {
                    String address = cursor.getString(1);
                    if (TextUtils.equals(emailAddress, address)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String makeDisplayString(Cursor cursor) {
        if (cursor.getColumnName(0).equals(SEARCHING_CURSOR_MARKER)) {
            return LoggingEvents.EXTRA_CALLING_APP_NAME;
        }
        String displayName = cursor.getString(0);
        String emailAddress = cursor.getString(1);
        if (TextUtils.isEmpty(displayName) || TextUtils.equals(displayName, emailAddress)) {
            return emailAddress;
        }
        return new Rfc822Token(displayName, emailAddress, null).toString();
    }
}

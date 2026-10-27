package com.android.bluetooth.mapapi;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class BluetoothMapIMProvider extends ContentProvider {
    private static final boolean D = true;
    private static final int MATCH_ACCOUNT = 1;
    private static final int MATCH_CONVERSATION = 4;
    private static final int MATCH_CONVOCONTACT = 5;
    private static final int MATCH_MESSAGE = 3;
    private static final String TAG = "BluetoothMapIMProvider";
    private Uri CONTENT_URI = null;
    private String mAuthority;
    private UriMatcher mMatcher;
    protected ContentResolver mResolver;

    protected abstract int deleteMessage(String str, String str2);

    protected abstract Uri getContentUri();

    protected abstract String insertMessage(String str, ContentValues contentValues);

    protected abstract Cursor queryAccount(String[] strArr, String str, String[] strArr2, String str2);

    protected abstract Cursor queryConversation(String str, Long l, Boolean bool, Long l2, Long l3, String str2, String[] strArr, String str3);

    protected abstract Cursor queryConvoContact(String str, Long l, String[] strArr, String str2, String[] strArr2, String str3);

    protected abstract Cursor queryMessage(String str, String[] strArr, String str2, String[] strArr2, String str3);

    protected abstract int setBluetoothStatus(boolean z);

    protected abstract int setOwnerStatus(int i, String str, long j, int i2, String str2);

    protected abstract int syncFolder(long j, long j2);

    protected abstract int updateAccount(String str, Integer num);

    protected abstract int updateMessage(String str, Long l, Long l2, Boolean bool);

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo info) {
        this.mAuthority = info.authority;
        UriMatcher uriMatcher = new UriMatcher(-1);
        this.mMatcher = uriMatcher;
        uriMatcher.addURI(this.mAuthority, BluetoothMapContract.TABLE_ACCOUNT, 1);
        this.mMatcher.addURI(this.mAuthority, "#/Message", 3);
        this.mMatcher.addURI(this.mAuthority, "#/Conversation", 4);
        this.mMatcher.addURI(this.mAuthority, "#/ConvoContact", 5);
        if (!info.exported) {
            throw new SecurityException("Provider must be exported");
        }
        if (!"android.permission.BLUETOOTH_MAP".equals(info.writePermission)) {
            throw new SecurityException("Provider must be protected by android.permission.BLUETOOTH_MAP");
        }
        Log.d(TAG, "attachInfo() mAuthority = " + this.mAuthority);
        this.mResolver = context.getContentResolver();
        super.attachInfo(context, info);
    }

    protected void onAccountChanged(String accountId) {
        Uri newUri;
        String str = this.mAuthority;
        if (str == null) {
            return;
        }
        if (accountId == null) {
            newUri = BluetoothMapContract.buildAccountUri(str);
        } else {
            newUri = BluetoothMapContract.buildAccountUriwithId(str, accountId);
        }
        Log.d(TAG, "onAccountChanged() accountId = " + accountId + " URI: " + newUri);
        this.mResolver.notifyChange(newUri, null);
    }

    protected void onMessageChanged(String accountId, String messageId) {
        Uri newUri;
        String str = this.mAuthority;
        if (str == null) {
            return;
        }
        if (accountId == null) {
            newUri = BluetoothMapContract.buildMessageUri(str);
        } else if (messageId == null) {
            newUri = BluetoothMapContract.buildMessageUri(str, accountId);
        } else {
            newUri = BluetoothMapContract.buildMessageUriWithId(str, accountId, messageId);
        }
        Log.d(TAG, "onMessageChanged() accountId = " + accountId + " messageId = " + messageId + " URI: " + newUri);
        this.mResolver.notifyChange(newUri, null);
    }

    protected void onContactChanged(String accountId, String contactId) {
        Uri newUri;
        String str = this.mAuthority;
        if (str == null) {
            return;
        }
        if (accountId == null) {
            newUri = BluetoothMapContract.buildConvoContactsUri(str);
        } else if (contactId == null) {
            newUri = BluetoothMapContract.buildConvoContactsUri(str, accountId);
        } else {
            newUri = BluetoothMapContract.buildConvoContactsUriWithId(str, accountId, contactId);
        }
        Log.d(TAG, "onContactChanged() accountId = " + accountId + " contactId = " + contactId + " URI: " + newUri);
        this.mResolver.notifyChange(newUri, null);
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "InstantMessage";
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String where, String[] selectionArgs) {
        Log.d(TAG, "delete(): uri=" + uri.toString());
        String table = uri.getPathSegments().get(1);
        if (table == null) {
            throw new IllegalArgumentException("Table missing in URI");
        }
        String messageId = uri.getLastPathSegment();
        if (messageId == null) {
            throw new IllegalArgumentException("Message ID missing in update values!");
        }
        String accountId = getAccountId(uri);
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID missing in update values!");
        }
        long callingId = Binder.clearCallingIdentity();
        try {
            if (table.equals(BluetoothMapContract.TABLE_MESSAGE)) {
                return deleteMessage(accountId, messageId);
            }
            Log.w(TAG, "Unknown table name: " + table);
            return 0;
        } finally {
            Binder.restoreCallingIdentity(callingId);
        }
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        String table = uri.getLastPathSegment();
        if (table == null) {
            throw new IllegalArgumentException("Table missing in URI");
        }
        String accountId = getAccountId(uri);
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID missing in URI");
        }
        long callingId = Binder.clearCallingIdentity();
        Log.d(TAG, "insert(): uri=" + uri.toString() + " - getLastPathSegment() = " + uri.getLastPathSegment());
        try {
            if (table.equals(BluetoothMapContract.TABLE_MESSAGE)) {
                String id = insertMessage(accountId, values);
                Log.i(TAG, "insert() ID: " + id);
                return Uri.parse(uri.toString() + "/" + id);
            }
            Log.w(TAG, "Unknown table name: " + table);
            return null;
        } finally {
            Binder.restoreCallingIdentity(callingId);
        }
    }

    protected String[] convertProjection(String[] projection, Map<String, String> projectionMap) {
        String[] newProjection = new String[projection.length];
        for (int i = 0; i < projection.length; i++) {
            newProjection[i] = projectionMap.get(projection[i]) + " as " + projection[i];
        }
        return newProjection;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) throws Throwable {
        Long periodEnd;
        Boolean read;
        Long threadId;
        long callingId = Binder.clearCallingIdentity();
        try {
            Log.w(TAG, "query(): uri =" + this.mAuthority + " uri=" + uri.toString());
            int iMatch = this.mMatcher.match(uri);
            if (iMatch == 1) {
                try {
                    Cursor cursorQueryAccount = queryAccount(projection, selection, selectionArgs, sortOrder);
                    Binder.restoreCallingIdentity(callingId);
                    return cursorQueryAccount;
                } catch (Throwable th) {
                    th = th;
                    Binder.restoreCallingIdentity(callingId);
                    throw th;
                }
            }
            if (iMatch == 3) {
                String accountId = getAccountId(uri);
                Cursor cursorQueryMessage = queryMessage(accountId, projection, selection, selectionArgs, sortOrder);
                Binder.restoreCallingIdentity(callingId);
                return cursorQueryMessage;
            }
            if (iMatch != 4) {
                if (iMatch == 5) {
                    String accountId2 = getAccountId(uri);
                    Cursor cursorQueryConvoContact = queryConvoContact(accountId2, 0L, projection, selection, selectionArgs, sortOrder);
                    Binder.restoreCallingIdentity(callingId);
                    return cursorQueryConvoContact;
                }
                throw new UnsupportedOperationException("Unsupported Uri " + uri);
            }
            String accountId3 = getAccountId(uri);
            String searchString = uri.getQueryParameter(BluetoothMapContract.FILTER_ORIGINATOR_SUBSTRING);
            String value = uri.getQueryParameter(BluetoothMapContract.FILTER_PERIOD_BEGIN);
            Long periodBegin = value != null ? Long.valueOf(Long.parseLong(value)) : null;
            String value2 = uri.getQueryParameter(BluetoothMapContract.FILTER_PERIOD_END);
            if (value2 != null) {
                Long periodEnd2 = Long.valueOf(Long.parseLong(value2));
                periodEnd = periodEnd2;
            } else {
                periodEnd = null;
            }
            String value3 = uri.getQueryParameter(BluetoothMapContract.FILTER_READ_STATUS);
            if (value3 != null) {
                Boolean read2 = Boolean.valueOf(value3.equalsIgnoreCase("true"));
                read = read2;
            } else {
                read = null;
            }
            String value4 = uri.getQueryParameter("thread_id");
            if (value4 != null) {
                Long threadId2 = Long.valueOf(Long.parseLong(value4));
                threadId = threadId2;
            } else {
                threadId = null;
            }
            Cursor cursorQueryConversation = queryConversation(accountId3, threadId, read, periodEnd, periodBegin, searchString, projection, sortOrder);
            Binder.restoreCallingIdentity(callingId);
            return cursorQueryConversation;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String table = uri.getLastPathSegment();
        if (table == null) {
            throw new IllegalArgumentException("Table missing in URI");
        }
        if (selection != null) {
            throw new IllegalArgumentException("selection shall not be used, ContentValues shall contain the data");
        }
        long callingId = Binder.clearCallingIdentity();
        Log.w(TAG, "update(): uri=" + uri.toString() + " - getLastPathSegment() = " + uri.getLastPathSegment());
        try {
            if (table.equals(BluetoothMapContract.TABLE_ACCOUNT)) {
                String accountId = values.getAsString("_id");
                if (accountId == null) {
                    throw new IllegalArgumentException("Account ID missing in update values!");
                }
                Integer exposeFlag = values.getAsInteger(BluetoothMapContract.AccountColumns.FLAG_EXPOSE);
                if (exposeFlag != null) {
                    return updateAccount(accountId, exposeFlag);
                }
                throw new IllegalArgumentException("Expose flag missing in update values!");
            }
            if (table.equals(BluetoothMapContract.TABLE_FOLDER)) {
                return 0;
            }
            if (table.equals(BluetoothMapContract.TABLE_MESSAGE)) {
                String accountId2 = getAccountId(uri);
                if (accountId2 == null) {
                    throw new IllegalArgumentException("Account ID missing in update values!");
                }
                Long messageId = values.getAsLong("_id");
                if (messageId == null) {
                    throw new IllegalArgumentException("Message ID missing in update values!");
                }
                Long folderId = values.getAsLong(BluetoothMapContract.MessageColumns.FOLDER_ID);
                Boolean flagRead = values.getAsBoolean(BluetoothMapContract.MessageColumns.FLAG_READ);
                return updateMessage(accountId2, messageId, folderId, flagRead);
            }
            if (table.equals(BluetoothMapContract.TABLE_CONVERSATION)) {
                return 0;
            }
            if (table.equals(BluetoothMapContract.TABLE_CONVOCONTACT)) {
                return 0;
            }
            Log.w(TAG, "Unknown table name: " + table);
            return 0;
        } finally {
            Binder.restoreCallingIdentity(callingId);
        }
    }

    protected ContentValues createContentValues(Set<Map.Entry<String, Object>> valueSet, Map<String, String> keyMap) {
        ContentValues values = new ContentValues(valueSet.size());
        for (Map.Entry<String, Object> ent : valueSet) {
            String key = keyMap.get(ent.getKey());
            Object value = ent.getValue();
            if (value == null) {
                values.putNull(key);
            } else if (ent.getValue() instanceof Boolean) {
                values.put(key, (Boolean) value);
            } else if (ent.getValue() instanceof Byte) {
                values.put(key, (Byte) value);
            } else if (ent.getValue() instanceof byte[]) {
                values.put(key, (byte[]) value);
            } else if (ent.getValue() instanceof Double) {
                values.put(key, (Double) value);
            } else if (ent.getValue() instanceof Float) {
                values.put(key, (Float) value);
            } else if (ent.getValue() instanceof Integer) {
                values.put(key, (Integer) value);
            } else if (ent.getValue() instanceof Long) {
                values.put(key, (Long) value);
            } else if (ent.getValue() instanceof Short) {
                values.put(key, (Short) value);
            } else if (ent.getValue() instanceof String) {
                values.put(key, (String) value);
            } else {
                throw new IllegalArgumentException("Unknown data type in content value");
            }
        }
        return values;
    }

    @Override // android.content.ContentProvider
    public Bundle call(String method, String arg, Bundle extras) {
        long callingId = Binder.clearCallingIdentity();
        Log.w(TAG, "call(): method=" + method + " arg=" + arg + "ThreadId: " + Thread.currentThread().getId());
        int ret = -1;
        try {
            if (method.equals(BluetoothMapContract.METHOD_UPDATE_FOLDER)) {
                long accountId = extras.getLong(BluetoothMapContract.EXTRA_UPDATE_ACCOUNT_ID, -1L);
                if (accountId == -1) {
                    Log.w(TAG, "No account ID in CALL");
                    return null;
                }
                long folderId = extras.getLong(BluetoothMapContract.EXTRA_UPDATE_FOLDER_ID, -1L);
                if (folderId == -1) {
                    Log.w(TAG, "No folder ID in CALL");
                    return null;
                }
                ret = syncFolder(accountId, folderId);
            } else if (method.equals(BluetoothMapContract.METHOD_SET_OWNER_STATUS)) {
                int presenceState = extras.getInt(BluetoothMapContract.EXTRA_PRESENCE_STATE);
                String presenceStatus = extras.getString(BluetoothMapContract.EXTRA_PRESENCE_STATUS);
                long lastActive = extras.getLong(BluetoothMapContract.EXTRA_LAST_ACTIVE);
                int chatState = extras.getInt(BluetoothMapContract.EXTRA_CHAT_STATE);
                String convoId = extras.getString(BluetoothMapContract.EXTRA_CONVERSATION_ID);
                ret = setOwnerStatus(presenceState, presenceStatus, lastActive, chatState, convoId);
            } else if (method.equals(BluetoothMapContract.METHOD_SET_BLUETOOTH_STATE)) {
                boolean bluetoothState = extras.getBoolean(BluetoothMapContract.EXTRA_BLUETOOTH_STATE);
                ret = setBluetoothStatus(bluetoothState);
            }
            if (ret == 0) {
                return new Bundle();
            }
            return null;
        } finally {
            Binder.restoreCallingIdentity(callingId);
        }
    }

    @Override // android.content.ContentProvider
    public void shutdown() {
    }

    public static String getAccountId(Uri uri) {
        List<String> segments = uri.getPathSegments();
        if (segments.size() < 1) {
            throw new IllegalArgumentException("No AccountId pressent in URI: " + uri);
        }
        return segments.get(0);
    }
}

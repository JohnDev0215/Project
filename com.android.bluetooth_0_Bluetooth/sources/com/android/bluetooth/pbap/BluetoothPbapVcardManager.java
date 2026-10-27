package com.android.bluetooth.pbap;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWindowAllocationException;
import android.database.MatrixCursor;
import android.net.INetd;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import com.android.bluetooth.R;
import com.android.bluetooth.util.DevicePolicyUtils;
import com.android.vcard.VCardComposer;
import com.android.vcard.VCardConfig;
import com.android.vcard.VCardConstants;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import javax.obex.Operation;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPbapVcardManager {
    static final String CALLLOG_SORT_ORDER = "_id DESC";
    private static final int CALLS_NAME_COLUMN_INDEX = 1;
    private static final int CALLS_NUMBER_COLUMN_INDEX = 0;
    private static final int CALLS_NUMBER_PRESENTATION_COLUMN_INDEX = 2;
    static final int CONTACTS_ID_COLUMN_INDEX = 0;
    static final int CONTACTS_NAME_COLUMN_INDEX = 1;
    private static final int ID_COLUMN_INDEX = 0;
    private static final int NEED_SEND_BODY = -1;
    private static final int PHONE_NUMBER_COLUMN_INDEX = 3;
    static final String SORT_ORDER_PHONE_NUMBER = "data1 ASC";
    private static final String TAG = "BluetoothPbapVcardManager";
    private static final boolean V = false;
    static long sLastFetchedTimeStamp;
    private Context mContext;
    private ContentResolver mResolver;
    static final String[] PHONES_CONTACTS_PROJECTION = {"contact_id", "display_name"};
    static final String[] PHONE_LOOKUP_PROJECTION = {"_id", "display_name"};
    private static final String[] CALLLOG_PROJECTION = {"_id"};

    public BluetoothPbapVcardManager(Context context) {
        this.mContext = context;
        this.mResolver = context.getContentResolver();
        sLastFetchedTimeStamp = System.currentTimeMillis();
    }

    private String getOwnerPhoneNumberVcardFromProfile(boolean vcardType21, byte[] filter) {
        int vcardType;
        if (vcardType21) {
            vcardType = VCardConfig.VCARD_TYPE_V21_GENERIC;
        } else {
            vcardType = VCardConfig.VCARD_TYPE_V30_GENERIC;
        }
        if (!BluetoothPbapConfig.includePhotosInVcard()) {
            vcardType |= 8388608;
        }
        return BluetoothPbapUtils.createProfileVCard(this.mContext, vcardType, filter);
    }

    public final String getOwnerPhoneNumberVcard(boolean vcardType21, byte[] filter) {
        String vcard;
        if (BluetoothPbapConfig.useProfileForOwnerVcard() && (vcard = getOwnerPhoneNumberVcardFromProfile(vcardType21, filter)) != null && vcard.length() != 0) {
            return vcard;
        }
        BluetoothPbapCallLogComposer composer = new BluetoothPbapCallLogComposer(this.mContext);
        String name = BluetoothPbapService.getLocalPhoneName();
        String number = BluetoothPbapService.getLocalPhoneNum();
        return composer.composeVCardForPhoneOwnNumber(2, name, number, vcardType21);
    }

    public final int getPhonebookSize(int type) {
        if (type == 1 || type == 6) {
            int size = getContactsSize(type);
            return size;
        }
        int size2 = getCallHistorySize(type);
        return size2;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int getContactsSize(int r11) {
        /*
            r10 = this;
            android.content.Context r0 = r10.mContext
            android.net.Uri r0 = com.android.bluetooth.util.DevicePolicyUtils.getEnterprisePhoneUri(r0)
            r7 = 0
            r1 = 0
            r2 = 6
            if (r11 != r2) goto Lf
            java.lang.String r1 = "starred = 1"
            r8 = r1
            goto L10
        Lf:
            r8 = r1
        L10:
            r9 = 0
            android.content.ContentResolver r1 = r10.mResolver     // Catch: java.lang.Throwable -> L3c android.database.CursorWindowAllocationException -> L3e
            java.lang.String r2 = "contact_id"
            java.lang.String[] r3 = new java.lang.String[]{r2}     // Catch: java.lang.Throwable -> L3c android.database.CursorWindowAllocationException -> L3e
            r5 = 0
            java.lang.String r6 = "contact_id"
            r2 = r0
            r4 = r8
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L3c android.database.CursorWindowAllocationException -> L3e
            r7 = r1
            if (r7 != 0) goto L2c
        L26:
            if (r7 == 0) goto L2b
            r7.close()
        L2b:
            return r9
        L2c:
            int r1 = getDistinctContactIdSize(r7)     // Catch: java.lang.Throwable -> L3c android.database.CursorWindowAllocationException -> L3e
            r2 = 1
            if (r11 != r2) goto L35
            int r1 = r1 + 1
        L35:
            if (r7 == 0) goto L3b
            r7.close()
        L3b:
            return r1
        L3c:
            r1 = move-exception
            goto L4d
        L3e:
            r1 = move-exception
            java.lang.String r2 = "BluetoothPbapVcardManager"
            java.lang.String r3 = "CursorWindowAllocationException while getting Contacts size"
            android.util.Log.e(r2, r3)     // Catch: java.lang.Throwable -> L3c
            if (r7 == 0) goto L4c
            r7.close()
        L4c:
            return r9
        L4d:
            if (r7 == 0) goto L52
            r7.close()
        L52:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.pbap.BluetoothPbapVcardManager.getContactsSize(int):int");
    }

    public final int getCallHistorySize(int type) {
        Uri myUri = CallLog.Calls.CONTENT_URI;
        String selection = BluetoothPbapObexServer.createSelectionPara(type);
        Cursor callCursor = null;
        try {
            try {
                callCursor = this.mResolver.query(myUri, null, selection, null, "date DESC");
                size = callCursor != null ? callCursor.getCount() : 0;
            } catch (CursorWindowAllocationException e) {
                Log.e(TAG, "CursorWindowAllocationException while getting CallHistory size");
                if (callCursor != null) {
                }
            }
            return size;
        } finally {
            if (callCursor != null) {
                callCursor.close();
            }
        }
    }

    public final ArrayList<String> loadCallHistoryList(int type) {
        Uri myUri = CallLog.Calls.CONTENT_URI;
        String selection = BluetoothPbapObexServer.createSelectionPara(type);
        String[] projection = {"number", "name", "presentation"};
        Cursor callCursor = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            try {
                callCursor = this.mResolver.query(myUri, projection, selection, null, CALLLOG_SORT_ORDER);
                if (callCursor != null) {
                    callCursor.moveToFirst();
                    while (!callCursor.isAfterLast()) {
                        String name = callCursor.getString(1);
                        if (TextUtils.isEmpty(name)) {
                            int numberPresentation = callCursor.getInt(2);
                            name = numberPresentation != 1 ? this.mContext.getString(R.string.unknownNumber) : callCursor.getString(0);
                        }
                        list.add(name);
                        callCursor.moveToNext();
                    }
                }
            } catch (CursorWindowAllocationException e) {
                Log.e(TAG, "CursorWindowAllocationException while loading CallHistory");
                if (callCursor != null) {
                }
            }
            return list;
        } finally {
            if (callCursor != null) {
                callCursor.close();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x007f  */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.ArrayList<java.lang.String> getPhonebookNameList(int r13) throws java.lang.Throwable {
        /*
            r12 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            boolean r2 = com.android.bluetooth.pbap.BluetoothPbapConfig.useProfileForOwnerVcard()
            if (r2 == 0) goto L12
            android.content.Context r2 = r12.mContext
            java.lang.String r1 = com.android.bluetooth.pbap.BluetoothPbapUtils.getProfileName(r2)
        L12:
            if (r1 == 0) goto L1a
            int r2 = r1.length()
            if (r2 != 0) goto L1e
        L1a:
            java.lang.String r1 = com.android.bluetooth.pbap.BluetoothPbapService.getLocalPhoneName()
        L1e:
            r0.add(r1)
            android.content.Context r2 = r12.mContext
            android.net.Uri r2 = com.android.bluetooth.util.DevicePolicyUtils.getEnterprisePhoneUri(r2)
            r9 = 0
            java.lang.String r3 = "contact_id"
            r4 = 1
            java.lang.String r10 = "BluetoothPbapVcardManager"
            if (r13 != r4) goto L3a
            java.lang.String r4 = "display_name"
            r3 = r4
            r11 = r3
            goto L3b
        L34:
            r4 = move-exception
            goto L7d
        L36:
            r4 = move-exception
            goto L62
        L38:
            r4 = move-exception
            goto L6d
        L3a:
            r11 = r3
        L3b:
            android.content.ContentResolver r3 = r12.mResolver     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60 android.database.CursorWindowAllocationException -> L6b
            java.lang.String[] r5 = com.android.bluetooth.pbap.BluetoothPbapVcardManager.PHONES_CONTACTS_PROJECTION     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60 android.database.CursorWindowAllocationException -> L6b
            r6 = 0
            r7 = 0
            r4 = r2
            r8 = r11
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60 android.database.CursorWindowAllocationException -> L6b
            r9 = r3
            if (r9 == 0) goto L56
            android.content.Context r3 = r12.mContext     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60 android.database.CursorWindowAllocationException -> L6b
            r4 = 17039374(0x104000e, float:2.424461E-38)
            java.lang.String r3 = r3.getString(r4)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60 android.database.CursorWindowAllocationException -> L6b
            appendDistinctNameIdList(r0, r3, r9)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60 android.database.CursorWindowAllocationException -> L6b
        L56:
            if (r9 == 0) goto L7c
            r9.close()
            r9 = 0
            goto L7c
        L5d:
            r4 = move-exception
            r3 = r11
            goto L7d
        L60:
            r4 = move-exception
            r3 = r11
        L62:
            java.lang.String r5 = "Exception while getting phonebook name list"
            android.util.Log.e(r10, r5, r4)     // Catch: java.lang.Throwable -> L34
            if (r9 == 0) goto L7b
            goto L75
        L6b:
            r4 = move-exception
            r3 = r11
        L6d:
            java.lang.String r5 = "CursorWindowAllocationException while getting phonebook name list"
            android.util.Log.e(r10, r5)     // Catch: java.lang.Throwable -> L34
            if (r9 == 0) goto L7b
        L75:
            r9.close()
            r9 = 0
            r11 = r3
            goto L7c
        L7b:
            r11 = r3
        L7c:
            return r0
        L7d:
            if (r9 == 0) goto L83
            r9.close()
            r9 = 0
        L83:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.pbap.BluetoothPbapVcardManager.getPhonebookNameList(int):java.util.ArrayList");
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x015f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final java.util.ArrayList<java.lang.String> getSelectedPhonebookNameList(int r20, boolean r21, int r22, int r23, byte[] r24, java.lang.String r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 357
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.pbap.BluetoothPbapVcardManager.getSelectedPhonebookNameList(int, boolean, int, int, byte[], java.lang.String):java.util.ArrayList");
    }

    public final ArrayList<String> getContactNamesByNumber(String phoneNumber) {
        Uri uri;
        String[] projection;
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> tempNameList = new ArrayList<>();
        Cursor contactCursor = null;
        if (TextUtils.isEmpty(phoneNumber)) {
            uri = DevicePolicyUtils.getEnterprisePhoneUri(this.mContext);
            projection = PHONES_CONTACTS_PROJECTION;
        } else {
            uri = Uri.withAppendedPath(getPhoneLookupFilterUri(), Uri.encode(phoneNumber));
            projection = PHONE_LOOKUP_PROJECTION;
        }
        try {
            try {
                contactCursor = this.mResolver.query(uri, projection, null, null, "contact_id");
                if (contactCursor != null) {
                    appendDistinctNameIdList(nameList, this.mContext.getString(android.R.string.unknownName), contactCursor);
                }
            } catch (CursorWindowAllocationException e) {
                Log.e(TAG, "CursorWindowAllocationException while getting contact names");
                if (contactCursor != null) {
                }
            }
            int tempListSize = tempNameList.size();
            for (int index = 0; index < tempListSize; index++) {
                String object = tempNameList.get(index);
                if (!nameList.contains(object)) {
                    nameList.add(object);
                }
            }
            return nameList;
        } finally {
            if (contactCursor != null) {
                contactCursor.close();
            }
        }
    }

    byte[] getCallHistoryPrimaryFolderVersion(int type) {
        Uri myUri = CallLog.Calls.CONTENT_URI;
        String selection = BluetoothPbapObexServer.createSelectionPara(type);
        String selection2 = selection + " AND date >= " + sLastFetchedTimeStamp;
        Log.d(TAG, "LAST_FETCHED_TIME_STAMP is " + sLastFetchedTimeStamp);
        Cursor callCursor = null;
        long count = 0;
        new ArrayList();
        try {
            try {
                callCursor = this.mResolver.query(myUri, null, selection2, null, null);
                while (callCursor != null) {
                    if (!callCursor.moveToNext()) {
                        break;
                    }
                    count++;
                }
            } catch (Exception e) {
                Log.e(TAG, "exception while fetching callHistory pvc");
                if (callCursor != null) {
                }
            }
            sLastFetchedTimeStamp = System.currentTimeMillis();
            Log.d(TAG, "getCallHistoryPrimaryFolderVersion count is " + count + " type is " + type);
            ByteBuffer pvc = ByteBuffer.allocate(16);
            pvc.putLong(0L);
            Log.d(TAG, "primaryVersionCounter is " + BluetoothPbapUtils.sPrimaryVersionCounter);
            pvc.putLong(count);
            return pvc.array();
        } finally {
            if (callCursor != null) {
                callCursor.close();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0054 A[PHI: r12 r13 r15
  0x0054: PHI (r12v4 'callsCursor' android.database.Cursor) = (r12v1 'callsCursor' android.database.Cursor), (r12v6 'callsCursor' android.database.Cursor) binds: [B:20:0x0052, B:14:0x0045] A[DONT_GENERATE, DONT_INLINE]
  0x0054: PHI (r13v2 'startPointId' long) = (r13v1 'startPointId' long), (r13v5 'startPointId' long) binds: [B:20:0x0052, B:14:0x0045] A[DONT_GENERATE, DONT_INLINE]
  0x0054: PHI (r15v1 'endPointId' long) = (r15v0 'endPointId' long), (r15v3 'endPointId' long) binds: [B:20:0x0052, B:14:0x0045] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int composeAndSendSelectedCallLogVcards(int r30, javax.obex.Operation r31, int r32, int r33, boolean r34, int r35, int r36, boolean r37, byte[] r38, byte[] r39, java.lang.String r40, boolean r41) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.pbap.BluetoothPbapVcardManager.composeAndSendSelectedCallLogVcards(int, javax.obex.Operation, int, int, boolean, int, int, boolean, byte[], byte[], java.lang.String, boolean):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0051 A[PHI: r8 r9
  0x0051: PHI (r8v3 'contactCursor' android.database.Cursor) = (r8v2 'contactCursor' android.database.Cursor), (r8v6 'contactCursor' android.database.Cursor) binds: [B:20:0x004f, B:14:0x0043] A[DONT_GENERATE, DONT_INLINE]
  0x0051: PHI (r9v1 'contactIdCursor' android.database.Cursor) = (r9v0 'contactIdCursor' android.database.Cursor), (r9v4 'contactIdCursor' android.database.Cursor) binds: [B:20:0x004f, B:14:0x0043] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int composeAndSendPhonebookVcards(javax.obex.Operation r19, int r20, int r21, boolean r22, java.lang.String r23, int r24, int r25, boolean r26, byte[] r27, byte[] r28, java.lang.String r29, boolean r30, boolean r31) {
        /*
            r18 = this;
            r12 = r18
            r13 = r20
            r14 = r21
            java.lang.String r1 = "BluetoothPbapVcardManager"
            r0 = 1
            if (r13 < r0) goto L8d
            if (r13 <= r14) goto Lf
            goto L8d
        Lf:
            android.content.Context r0 = r12.mContext
            android.net.Uri r15 = com.android.bluetooth.util.DevicePolicyUtils.getEnterprisePhoneUri(r0)
            r8 = 0
            android.database.MatrixCursor r0 = new android.database.MatrixCursor
            java.lang.String r2 = "contact_id"
            java.lang.String[] r2 = new java.lang.String[]{r2}
            r0.<init>(r2)
            r9 = r0
            r0 = 0
            if (r31 == 0) goto L2a
            java.lang.String r0 = "starred = 1"
            r16 = r0
            goto L2c
        L2a:
            r16 = r0
        L2c:
            android.content.ContentResolver r2 = r12.mResolver     // Catch: java.lang.Throwable -> L46 android.database.CursorWindowAllocationException -> L48
            java.lang.String[] r4 = com.android.bluetooth.pbap.BluetoothPbapVcardManager.PHONES_CONTACTS_PROJECTION     // Catch: java.lang.Throwable -> L46 android.database.CursorWindowAllocationException -> L48
            r6 = 0
            java.lang.String r7 = "contact_id"
            r3 = r15
            r5 = r16
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L46 android.database.CursorWindowAllocationException -> L48
            r8 = r0
            if (r8 == 0) goto L43
        L3e:
            android.database.Cursor r0 = com.android.bluetooth.pbap.BluetoothPbapVcardManager.ContactCursorFilter.filterByRange(r8, r13, r14)     // Catch: java.lang.Throwable -> L46 android.database.CursorWindowAllocationException -> L48
            r9 = r0
        L43:
            if (r8 == 0) goto L54
            goto L51
        L46:
            r0 = move-exception
            goto L87
        L48:
            r0 = move-exception
            java.lang.String r2 = "CursorWindowAllocationException while composing phonebook vcards"
            android.util.Log.e(r1, r2)     // Catch: java.lang.Throwable -> L46
            if (r8 == 0) goto L54
        L51:
            r8.close()
        L54:
            r0 = r8
            r17 = r9
            if (r30 == 0) goto L74
            r1 = r18
            r2 = r19
            r3 = r17
            r4 = r22
            r5 = r23
            r6 = r24
            r7 = r25
            r8 = r26
            r9 = r27
            r10 = r28
            r11 = r29
            int r1 = r1.composeContactsAndSendSelectedVCards(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return r1
        L74:
            r1 = r18
            r2 = r19
            r3 = r17
            r4 = r22
            r5 = r23
            r6 = r26
            r7 = r27
            int r1 = r1.composeContactsAndSendVCards(r2, r3, r4, r5, r6, r7)
            return r1
        L87:
            if (r8 == 0) goto L8c
            r8.close()
        L8c:
            throw r0
        L8d:
            java.lang.String r0 = "internal error: startPoint or endPoint is not correct."
            android.util.Log.e(r1, r0)
            r0 = 208(0xd0, float:2.91E-43)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.pbap.BluetoothPbapVcardManager.composeAndSendPhonebookVcards(javax.obex.Operation, int, int, boolean, java.lang.String, int, int, boolean, byte[], byte[], java.lang.String, boolean, boolean):int");
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0048 A[PHI: r2
  0x0048: PHI (r2v3 'contactCursor' android.database.Cursor) = (r2v1 'contactCursor' android.database.Cursor), (r2v7 'contactCursor' android.database.Cursor) binds: [B:18:0x0055, B:11:0x003a] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int composeAndSendPhonebookOneVcard(javax.obex.Operation r18, int r19, boolean r20, java.lang.String r21, int r22, boolean r23, byte[] r24) {
        /*
            r17 = this;
            r8 = r17
            r9 = r19
            java.lang.String r1 = "BluetoothPbapVcardManager"
            r0 = 1
            if (r9 >= r0) goto L11
            java.lang.String r0 = "Internal error: offset is not correct."
            android.util.Log.e(r1, r0)
            r0 = 208(0xd0, float:2.91E-43)
            return r0
        L11:
            android.content.Context r2 = r8.mContext
            android.net.Uri r16 = com.android.bluetooth.util.DevicePolicyUtils.getEnterprisePhoneUri(r2)
            r2 = 0
            android.database.MatrixCursor r3 = new android.database.MatrixCursor
            java.lang.String r4 = "contact_id"
            java.lang.String[] r4 = new java.lang.String[]{r4}
            r3.<init>(r4)
            java.lang.String r4 = "contact_id"
            r7 = r22
            if (r7 != r0) goto L2c
            java.lang.String r0 = "display_name"
            r4 = r0
        L2c:
            android.content.ContentResolver r10 = r8.mResolver     // Catch: java.lang.Throwable -> L4c android.database.CursorWindowAllocationException -> L4e
            java.lang.String[] r12 = com.android.bluetooth.pbap.BluetoothPbapVcardManager.PHONES_CONTACTS_PROJECTION     // Catch: java.lang.Throwable -> L4c android.database.CursorWindowAllocationException -> L4e
            r13 = 0
            r14 = 0
            r11 = r16
            r15 = r4
            android.database.Cursor r0 = r10.query(r11, r12, r13, r14, r15)     // Catch: java.lang.Throwable -> L4c android.database.CursorWindowAllocationException -> L4e
            r2 = r0
            if (r2 == 0) goto L48
        L3c:
            android.database.Cursor r3 = com.android.bluetooth.pbap.BluetoothPbapVcardManager.ContactCursorFilter.filterByOffset(r2, r9)
            r2.close()
            r2 = 0
            r0 = r2
            r10 = r3
            r11 = r4
            goto L58
        L48:
            r0 = r2
            r10 = r3
            r11 = r4
            goto L58
        L4c:
            r0 = move-exception
            goto L6a
        L4e:
            r0 = move-exception
            java.lang.String r5 = "CursorWindowAllocationException while composing phonebook one vcard"
            android.util.Log.e(r1, r5)     // Catch: java.lang.Throwable -> L4c
            if (r2 == 0) goto L48
            goto L3c
        L58:
            r1 = r17
            r2 = r18
            r3 = r10
            r4 = r20
            r5 = r21
            r6 = r23
            r7 = r24
            int r1 = r1.composeContactsAndSendVCards(r2, r3, r4, r5, r6, r7)
            return r1
        L6a:
            if (r2 == 0) goto L74
            android.database.Cursor r3 = com.android.bluetooth.pbap.BluetoothPbapVcardManager.ContactCursorFilter.filterByOffset(r2, r9)
            r2.close()
            r2 = 0
        L74:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.pbap.BluetoothPbapVcardManager.composeAndSendPhonebookOneVcard(javax.obex.Operation, int, boolean, java.lang.String, int, boolean, byte[]):int");
    }

    private static final class ContactCursorFilter {
        private ContactCursorFilter() {
        }

        public static Cursor filterByOffset(Cursor contactCursor, int offset) {
            return filterByRange(contactCursor, offset, offset);
        }

        public static Cursor filterByRange(Cursor contactCursor, int startPoint, int endPoint) {
            int contactIdColumn = contactCursor.getColumnIndex("contact_id");
            long previousContactId = -1;
            int currentOffset = 1;
            MatrixCursor contactIdsCursor = new MatrixCursor(new String[]{"contact_id"});
            while (contactCursor.moveToNext() && currentOffset <= endPoint) {
                long currentContactId = contactCursor.getLong(contactIdColumn);
                if (previousContactId != currentContactId) {
                    previousContactId = currentContactId;
                    if (currentOffset >= startPoint) {
                        contactIdsCursor.addRow(new Long[]{Long.valueOf(currentContactId)});
                    }
                    currentOffset++;
                }
            }
            return contactIdsCursor;
        }
    }

    private static class EnterpriseRawContactEntitlesInfoCallback implements VCardComposer.RawContactEntitlesInfoCallback {
        private EnterpriseRawContactEntitlesInfoCallback() {
        }

        @Override // com.android.vcard.VCardComposer.RawContactEntitlesInfoCallback
        public VCardComposer.RawContactEntitlesInfo getRawContactEntitlesInfo(long contactId) {
            if (ContactsContract.Contacts.isEnterpriseContactId(contactId)) {
                return new VCardComposer.RawContactEntitlesInfo(ContactsContract.RawContactsEntity.CORP_CONTENT_URI, contactId - ContactsContract.Contacts.ENTERPRISE_CONTACT_ID_BASE);
            }
            return new VCardComposer.RawContactEntitlesInfo(ContactsContract.RawContactsEntity.CONTENT_URI, contactId);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00dc, code lost:
    
        if (r6 == null) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00de, code lost:
    
        r6.terminate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e1, code lost:
    
        r9.onTerminate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00e7, code lost:
    
        return com.android.bluetooth.BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int composeContactsAndSendVCards(javax.obex.Operation r17, android.database.Cursor r18, boolean r19, java.lang.String r20, boolean r21, byte[] r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 265
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.pbap.BluetoothPbapVcardManager.composeContactsAndSendVCards(javax.obex.Operation, android.database.Cursor, boolean, java.lang.String, boolean, byte[]):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0118, code lost:
    
        if (r25 == (-1)) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x011b, code lost:
    
        if (r7 == null) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x011d, code lost:
    
        r7.terminate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0120, code lost:
    
        r12.onTerminate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0124, code lost:
    
        return r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0125, code lost:
    
        if (r7 == null) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0127, code lost:
    
        r7.terminate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x012a, code lost:
    
        r12.onTerminate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0130, code lost:
    
        return com.android.bluetooth.BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int composeContactsAndSendSelectedVCards(javax.obex.Operation r21, android.database.Cursor r22, boolean r23, java.lang.String r24, int r25, int r26, boolean r27, byte[] r28, byte[] r29, java.lang.String r30) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.pbap.BluetoothPbapVcardManager.composeContactsAndSendSelectedVCards(javax.obex.Operation, android.database.Cursor, boolean, java.lang.String, int, int, boolean, byte[], byte[], java.lang.String):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00e5, code lost:
    
        if (r21 == (-1)) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00e7, code lost:
    
        if (r28 == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00eb, code lost:
    
        r7.terminate();
        r8.onTerminate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00f2, code lost:
    
        return r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f3, code lost:
    
        r7.terminate();
        r8.onTerminate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00fd, code lost:
    
        return com.android.bluetooth.BluetoothStatsLog.BLUETOOTH_HCI_TIMEOUT_REPORTED;
     */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int composeCallLogsAndSendSelectedVCards(javax.obex.Operation r18, java.lang.String r19, boolean r20, int r21, int r22, java.lang.String r23, boolean r24, byte[] r25, byte[] r26, java.lang.String r27, boolean r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.pbap.BluetoothPbapVcardManager.composeCallLogsAndSendSelectedVCards(javax.obex.Operation, java.lang.String, boolean, int, int, java.lang.String, boolean, byte[], byte[], java.lang.String, boolean):int");
    }

    public String stripTelephoneNumber(String vCard) {
        String[] attr = vCard.split(System.getProperty("line.separator"));
        String stripedVCard = INetd.NEXTHOP_NONE;
        for (int i = 0; i < attr.length; i++) {
            if (attr[i].startsWith(VCardConstants.PROPERTY_TEL)) {
                String[] vTagAndTel = attr[i].split(":", 2);
                int telLenBefore = vTagAndTel[1].length();
                vTagAndTel[1] = vTagAndTel[1].replace("-", INetd.NEXTHOP_NONE).replace("(", INetd.NEXTHOP_NONE).replace(")", INetd.NEXTHOP_NONE).replace(" ", INetd.NEXTHOP_NONE);
                if (vTagAndTel[1].length() < telLenBefore) {
                    attr[i] = vTagAndTel[0] + ":" + vTagAndTel[1];
                }
            }
        }
        for (int i2 = 0; i2 < attr.length; i2++) {
            if (!attr[i2].isEmpty()) {
                stripedVCard = stripedVCard.concat(attr[i2] + "\n");
            }
        }
        return stripedVCard;
    }

    public class HandlerForStringBuffer {
        private Operation mOperation;
        private OutputStream mOutputStream;
        private String mPhoneOwnVCard;

        public HandlerForStringBuffer(Operation op, String ownerVCard) {
            this.mPhoneOwnVCard = null;
            this.mOperation = op;
            if (ownerVCard != null) {
                this.mPhoneOwnVCard = ownerVCard;
            }
        }

        private boolean write(String vCard) {
            if (vCard != null) {
                try {
                    this.mOutputStream.write(vCard.getBytes());
                    return true;
                } catch (IOException e) {
                    Log.e(BluetoothPbapVcardManager.TAG, "write outputstrem failed" + e.toString());
                    return false;
                }
            }
            return false;
        }

        public boolean onInit(Context context) {
            try {
                this.mOutputStream = this.mOperation.openOutputStream();
                if (this.mPhoneOwnVCard != null) {
                    return write(this.mPhoneOwnVCard);
                }
                return true;
            } catch (IOException e) {
                Log.e(BluetoothPbapVcardManager.TAG, "open outputstrem failed" + e.toString());
                return false;
            }
        }

        public boolean onEntryCreated(String vcard) {
            return write(vcard);
        }

        public void onTerminate() {
            BluetoothPbapObexServer.closeStream(this.mOutputStream, this.mOperation);
        }
    }

    public static class VCardFilter {
        private static final String SEPARATOR = System.getProperty("line.separator");
        private final byte[] mFilter;

        private enum FilterBit {
            FN(1, VCardConstants.PROPERTY_FN, true, false),
            PHOTO(3, VCardConstants.PROPERTY_PHOTO, false, false),
            BDAY(4, VCardConstants.PROPERTY_BDAY, false, false),
            ADR(5, VCardConstants.PROPERTY_ADR, false, false),
            EMAIL(8, "EMAIL", false, false),
            TITLE(12, VCardConstants.PROPERTY_TITLE, false, false),
            ORG(16, VCardConstants.PROPERTY_ORG, false, false),
            NOTE(17, VCardConstants.PROPERTY_NOTE, false, false),
            SOUND(19, VCardConstants.PROPERTY_SOUND, false, false),
            URL(20, VCardConstants.PROPERTY_URL, false, false),
            NICKNAME(23, VCardConstants.PROPERTY_NICKNAME, false, true),
            DATETIME(28, "X-IRMC-CALL-DATETIME", false, false);

            public final boolean excludeForV21;
            public final boolean onlyCheckV21;
            public final int pos;
            public final String prop;

            FilterBit(int pos, String prop, boolean onlyCheckV21, boolean excludeForV21) {
                this.pos = pos;
                this.prop = prop;
                this.onlyCheckV21 = onlyCheckV21;
                this.excludeForV21 = excludeForV21;
            }
        }

        private boolean isFilteredIn(FilterBit bit, boolean vCardType21) {
            int offset = (bit.pos / 8) + 1;
            int bitPos = bit.pos % 8;
            if (!vCardType21 && bit.onlyCheckV21) {
                return true;
            }
            if (vCardType21 && bit.excludeForV21) {
                return false;
            }
            byte[] bArr = this.mFilter;
            return bArr == null || offset >= bArr.length || ((bArr[bArr.length - offset] >> bitPos) & 1) != 0;
        }

        VCardFilter(byte[] filter) {
            this.mFilter = filter;
        }

        public boolean isPhotoEnabled() {
            return isFilteredIn(FilterBit.PHOTO, false);
        }

        public String apply(String vCard, boolean vCardType21) {
            if (this.mFilter == null) {
                return vCard;
            }
            String[] lines = vCard.split(SEPARATOR);
            StringBuilder filteredVCard = new StringBuilder();
            boolean filteredIn = false;
            for (String line : lines) {
                if (!Character.isWhitespace(line.charAt(0)) && !line.startsWith("=")) {
                    String currentProp = line.split("[;:]")[0];
                    filteredIn = true;
                    FilterBit[] filterBitArrValues = FilterBit.values();
                    int length = filterBitArrValues.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        FilterBit bit = filterBitArrValues[i];
                        if (!bit.prop.equals(currentProp)) {
                            i++;
                        } else {
                            filteredIn = isFilteredIn(bit, vCardType21);
                            break;
                        }
                    }
                    if (currentProp.startsWith("X-")) {
                        filteredIn = false;
                        if (currentProp.equals("X-IRMC-CALL-DATETIME")) {
                            filteredIn = true;
                        }
                    }
                }
                if (filteredIn) {
                    filteredVCard.append(line + SEPARATOR);
                }
            }
            return filteredVCard.toString();
        }
    }

    private static class PropertySelector {
        private static final String SEPARATOR = System.getProperty("line.separator");
        private final byte[] mSelector;

        private enum PropertyMask {
            VERSION(0, VCardConstants.PROPERTY_VERSION),
            FN(1, VCardConstants.PROPERTY_FN),
            NAME(2, VCardConstants.PROPERTY_N),
            PHOTO(3, VCardConstants.PROPERTY_PHOTO),
            BDAY(4, VCardConstants.PROPERTY_BDAY),
            ADR(5, VCardConstants.PROPERTY_ADR),
            LABEL(6, "LABEL"),
            TEL(7, VCardConstants.PROPERTY_TEL),
            EMAIL(8, "EMAIL"),
            TITLE(12, VCardConstants.PROPERTY_TITLE),
            ORG(16, VCardConstants.PROPERTY_ORG),
            NOTE(17, VCardConstants.PROPERTY_NOTE),
            URL(20, VCardConstants.PROPERTY_URL),
            NICKNAME(23, VCardConstants.PROPERTY_NICKNAME),
            DATETIME(28, "DATETIME");

            public final int pos;
            public final String prop;

            PropertyMask(int pos, String prop) {
                this.pos = pos;
                this.prop = prop;
            }
        }

        PropertySelector(byte[] selector) {
            this.mSelector = selector;
        }

        private boolean checkbit(int attrBit, byte[] selector) {
            int selectorlen = selector.length;
            return ((selector[(selectorlen + (-1)) - (attrBit / 8)] >> (attrBit % 8)) & 1) != 0;
        }

        private boolean checkprop(String vcard, String prop) {
            String[] lines = vcard.split(SEPARATOR);
            for (String line : lines) {
                if (!Character.isWhitespace(line.charAt(0)) && !line.startsWith("=")) {
                    String currentProp = line.split("[;:]")[0];
                    if (prop.equals(currentProp)) {
                        Log.d(BluetoothPbapVcardManager.TAG, "bit.prop.equals current prop :" + prop);
                        return true;
                    }
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean checkVCardSelector(String vcard, String vcardselectorop) {
            boolean selectedIn = true;
            for (PropertyMask bit : PropertyMask.values()) {
                if (checkbit(bit.pos, this.mSelector)) {
                    Log.d(BluetoothPbapVcardManager.TAG, "checking for prop :" + bit.prop);
                    if (vcardselectorop.equals("0")) {
                        if (checkprop(vcard, bit.prop)) {
                            Log.d(BluetoothPbapVcardManager.TAG, "bit.prop.equals current prop :" + bit.prop);
                            return true;
                        }
                        selectedIn = false;
                    } else if (!vcardselectorop.equals("1")) {
                        continue;
                    } else {
                        if (!checkprop(vcard, bit.prop)) {
                            Log.d(BluetoothPbapVcardManager.TAG, "bit.prop.notequals current prop" + bit.prop);
                            return false;
                        }
                        selectedIn = true;
                    }
                }
            }
            return selectedIn;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getName(String vcard) {
            String[] lines = vcard.split(SEPARATOR);
            String name = INetd.NEXTHOP_NONE;
            for (String line : lines) {
                if (!Character.isWhitespace(line.charAt(0)) && !line.startsWith("=") && line.startsWith("N:")) {
                    name = line.substring(line.lastIndexOf(58), line.length());
                }
            }
            Log.d(BluetoothPbapVcardManager.TAG, "returning name: " + name);
            return name;
        }
    }

    private static Uri getPhoneLookupFilterUri() {
        return ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI;
    }

    private static int getDistinctContactIdSize(Cursor cursor) {
        int contactIdColumn = cursor.getColumnIndex("contact_id");
        int idColumn = cursor.getColumnIndex("_id");
        long previousContactId = -1;
        int count = 0;
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            long contactId = cursor.getLong(contactIdColumn != -1 ? contactIdColumn : idColumn);
            if (previousContactId != contactId) {
                count++;
                previousContactId = contactId;
            }
        }
        return count;
    }

    private static void appendDistinctNameIdList(ArrayList<String> resultList, String defaultName, Cursor cursor) {
        int contactIdColumn = cursor.getColumnIndex("contact_id");
        int idColumn = cursor.getColumnIndex("_id");
        int nameColumn = cursor.getColumnIndex("display_name");
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            long contactId = cursor.getLong(contactIdColumn != -1 ? contactIdColumn : idColumn);
            String displayName = nameColumn != -1 ? cursor.getString(nameColumn) : defaultName;
            if (TextUtils.isEmpty(displayName)) {
                displayName = defaultName;
            }
            String newString = displayName + "," + contactId;
            if (!resultList.contains(newString)) {
                resultList.add(newString);
            }
        }
    }
}

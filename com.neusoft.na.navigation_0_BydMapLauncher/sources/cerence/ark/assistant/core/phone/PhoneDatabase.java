package cerence.ark.assistant.core.phone;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.TextUtils;
import cerence.ark.assistant.api.dynamic.Contact;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.core.phone.data.CallLog;
import cerence.ark.assistant.core.phone.data.PhoneNumber;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.library.Utils;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneDatabase {
    protected static final long INVALID_CONTACT_ID = -1;
    private static final String TAG = "PhoneDatabase";
    protected final Map<CallLog.CallLogType, List<Integer>> mCallLogTypeMap;
    protected final Map<Integer, PhoneNumber.PhoneNumberType> mPhoneTypeMap;
    protected static final String[] CALL_LOG_PROJECTION = {"_id", "name", "number", "type", "date", "numbertype", "lookup_uri"};
    protected static final String[] CONTACT_PROJECTION = {"_id", "display_name"};
    protected static final String[] PHONE_NUMBER_PROJECTION = {"data1", "data2"};

    public PhoneDatabase() {
        HashMap map = new HashMap(16);
        this.mPhoneTypeMap = map;
        EnumMap enumMap = new EnumMap(CallLog.CallLogType.class);
        this.mCallLogTypeMap = enumMap;
        map.put(1, PhoneNumber.PhoneNumberType.HOME);
        map.put(2, PhoneNumber.PhoneNumberType.MOBILE);
        map.put(3, PhoneNumber.PhoneNumberType.WORK);
        map.put(4, PhoneNumber.PhoneNumberType.FAX_WORK);
        map.put(5, PhoneNumber.PhoneNumberType.FAX_HOME);
        map.put(6, PhoneNumber.PhoneNumberType.PAGER);
        map.put(8, PhoneNumber.PhoneNumberType.CALLBACK);
        map.put(17, PhoneNumber.PhoneNumberType.WORK_MOBILE);
        map.put(13, PhoneNumber.PhoneNumberType.OTHER_FAX);
        map.put(18, PhoneNumber.PhoneNumberType.WORK_PAGER);
        map.put(9, PhoneNumber.PhoneNumberType.CAR);
        map.put(12, PhoneNumber.PhoneNumberType.MAIN);
        map.put(10, PhoneNumber.PhoneNumberType.COMPANY_MAIN);
        map.put(11, PhoneNumber.PhoneNumberType.ISDN);
        map.put(14, PhoneNumber.PhoneNumberType.RADIO);
        map.put(15, PhoneNumber.PhoneNumberType.TELEX);
        map.put(16, PhoneNumber.PhoneNumberType.TTY_TDD);
        map.put(19, PhoneNumber.PhoneNumberType.ASSISTANT);
        map.put(20, PhoneNumber.PhoneNumberType.MMS);
        map.put(7, PhoneNumber.PhoneNumberType.OTHER);
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(7);
        enumMap.put(CallLog.CallLogType.ANSWERED_CALL_LOG, arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(1);
        arrayList2.add(3);
        arrayList2.add(7);
        enumMap.put(CallLog.CallLogType.INCOMING_CALL_LOG, arrayList2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(3);
        arrayList3.add(4);
        arrayList3.add(6);
        arrayList3.add(5);
        enumMap.put(CallLog.CallLogType.MISSED_CALL_LOG, arrayList3);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(2);
        enumMap.put(CallLog.CallLogType.OUTGOING_CALL_LOG, arrayList4);
    }

    public List<Contact> getContacts(Context context, CancellationSignal cancellationSignal) {
        return getContacts(context, null, cancellationSignal);
    }

    public List<Contact> getContacts(Context context, List<Long> list, CancellationSignal cancellationSignal) {
        Cursor cursorQuery;
        ArrayList arrayList = new ArrayList();
        if (!Utils.checkSelfPermission(context, "android.permission.READ_CONTACTS")) {
            Logger.error(TAG, "getContacts: the android.permission.READ_CONTACTS was denied.");
            return arrayList;
        }
        try {
            cursorQuery = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, CONTACT_PROJECTION, (list == null || list.isEmpty()) ? null : "_id IN (" + TextUtils.join(ListUtils.DEFAULT_JOIN_SEPARATOR, list) + ")", null, null, cancellationSignal);
            try {
            } finally {
            }
        } catch (Exception e) {
            Logger.error(TAG, "getContacts: exception", e);
        }
        if (cursorQuery == null) {
            Logger.error(TAG, "getContacts: query fail, cursor is null");
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return arrayList;
        }
        Logger.info(TAG, "getContacts: begin, contact count = " + cursorQuery.getCount());
        int columnIndex = cursorQuery.getColumnIndex("_id");
        int columnIndex2 = cursorQuery.getColumnIndex("display_name");
        while (cursorQuery.moveToNext() && (cancellationSignal == null || !cancellationSignal.isCanceled())) {
            Contact contact = new Contact();
            contact.setId(cursorQuery.getLong(columnIndex));
            contact.setName(cursorQuery.getString(columnIndex2));
            arrayList.add(contact);
        }
        Logger.info(TAG, "getContacts: finish, count = " + arrayList.size());
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    public Result<List<PhoneNumber>, ErrorCode> getPhoneNumbersById(Context context, long j) {
        if (!Utils.checkSelfPermission(context, "android.permission.READ_CONTACTS")) {
            Logger.error(TAG, "getPhoneNumberListById: the android.permission.READ_CONTACTS was denied.");
            return new Result.Builder().buildError();
        }
        try {
            Cursor cursorQuery = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONE_NUMBER_PROJECTION, "contact_id = ?", new String[]{String.valueOf(j)}, null);
            try {
                if (cursorQuery == null) {
                    Logger.error(TAG, "getPhoneNumberListById: query fail, cursor is null, contactId = " + j);
                    Result<List<PhoneNumber>, ErrorCode> resultBuildError = new Result.Builder().buildError();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return resultBuildError;
                }
                ArrayList arrayList = new ArrayList();
                int columnIndex = cursorQuery.getColumnIndex("data1");
                int columnIndex2 = cursorQuery.getColumnIndex("data2");
                while (cursorQuery.moveToNext()) {
                    PhoneNumber.Builder builder = PhoneNumber.builder();
                    builder.setNumber(cursorQuery.getString(columnIndex));
                    builder.setNumberType(this.mPhoneTypeMap.getOrDefault(Integer.valueOf(cursorQuery.getInt(columnIndex2)), PhoneNumber.PhoneNumberType.OTHER));
                    arrayList.add(builder.build());
                }
                Result<List<PhoneNumber>, ErrorCode> resultBuildSuccess = new Result.Builder().buildSuccess(arrayList);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return resultBuildSuccess;
            } finally {
            }
        } catch (Exception e) {
            Logger.error(TAG, "getPhoneNumberListById: exception, contactId = " + j, e);
            return new Result.Builder().buildError();
        }
    }

    public List<PhoneNumber> getPhoneNumberListById(Context context, long j) {
        Result<List<PhoneNumber>, ErrorCode> phoneNumbersById = getPhoneNumbersById(context, j);
        return phoneNumbersById.isSuccess() ? phoneNumbersById.getData() : new ArrayList();
    }

    public List<CallLog> getCallLogsByType(Context context, CallLog.CallLogType callLogType, int i) {
        String string;
        String[] strArr;
        Cursor cursorQuery;
        List<Integer> list;
        ArrayList arrayList = new ArrayList();
        if (!Utils.checkSelfPermission(context, "android.permission.READ_CALL_LOG")) {
            Logger.error(TAG, "getCallLogsByType: the android.permission.READ_CALL_LOG was denied.");
            return arrayList;
        }
        if (CallLog.CallLogType.ALL_CALL_LOG == callLogType || (list = this.mCallLogTypeMap.get(callLogType)) == null) {
            string = null;
            strArr = null;
        } else {
            int size = list.size();
            ArrayList arrayList2 = new ArrayList();
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList2.add(String.valueOf(list.get(i2)));
                if (i2 == 0) {
                    sb.append("type = ?");
                } else {
                    sb.append(" OR type = ?");
                }
            }
            string = sb.toString();
            strArr = (String[]) arrayList2.toArray(new String[0]);
        }
        Uri uriBuild = CallLog.Calls.CONTENT_URI;
        if (i > 0) {
            uriBuild = CallLog.Calls.CONTENT_URI.buildUpon().appendQueryParameter("limit", String.valueOf(i)).build();
        }
        try {
            cursorQuery = context.getContentResolver().query(uriBuild, CALL_LOG_PROJECTION, string, strArr, "date DESC");
            try {
            } finally {
            }
        } catch (Exception e) {
            Logger.error(TAG, "getCallLogsByType: exception", e);
        }
        if (cursorQuery == null) {
            Logger.error(TAG, "getCallLogsByType: query fail, cursor is null");
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return arrayList;
        }
        Logger.info(TAG, Utils.format("getCallLogsByType: begin, callLogType = %s, count = %s", callLogType, Integer.valueOf(cursorQuery.getCount())));
        int columnIndex = cursorQuery.getColumnIndex("_id");
        int columnIndex2 = cursorQuery.getColumnIndex("number");
        int columnIndex3 = cursorQuery.getColumnIndex("date");
        int columnIndex4 = cursorQuery.getColumnIndex("name");
        int columnIndex5 = cursorQuery.getColumnIndex("numbertype");
        int columnIndex6 = cursorQuery.getColumnIndex("type");
        int columnIndex7 = cursorQuery.getColumnIndex("lookup_uri");
        while (cursorQuery.moveToNext()) {
            CallLog.Builder builder = cerence.ark.assistant.core.phone.data.CallLog.builder();
            builder.setCallLogId(cursorQuery.getLong(columnIndex));
            builder.setTimestamp(cursorQuery.getLong(columnIndex3));
            Contact contact = new Contact();
            String string2 = cursorQuery.getString(columnIndex4);
            String string3 = cursorQuery.getString(columnIndex2);
            long contactId = parseContactId(cursorQuery.getString(columnIndex7));
            if (contactId == -1) {
                contactId = getContactIdByNumber(context, string3);
            }
            int i3 = columnIndex2;
            int i4 = columnIndex3;
            long j = contactId;
            if (j != -1) {
                contact.setId(j);
            }
            contact.setName(string2);
            builder.setContact(contact);
            PhoneNumber.PhoneNumberType orDefault = this.mPhoneTypeMap.getOrDefault(Integer.valueOf(cursorQuery.getInt(columnIndex5)), PhoneNumber.PhoneNumberType.OTHER);
            PhoneNumber.Builder builder2 = PhoneNumber.builder();
            builder2.setNumber(string3);
            builder2.setNumberType(orDefault);
            builder.setPhoneNumber(builder2.build());
            switch (cursorQuery.getInt(columnIndex6)) {
                case 1:
                case 7:
                    builder.setCallLogType(CallLog.CallLogType.ANSWERED_CALL_LOG);
                    break;
                case 2:
                    builder.setCallLogType(CallLog.CallLogType.OUTGOING_CALL_LOG);
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                    builder.setCallLogType(CallLog.CallLogType.MISSED_CALL_LOG);
                    break;
            }
            arrayList.add(builder.build());
            columnIndex2 = i3;
            columnIndex3 = i4;
        }
        Logger.info(TAG, "getCallLogsByType: finish, count = " + arrayList.size());
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    private long parseContactId(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.warn(TAG, "The lookupUri content is empty!");
            return -1L;
        }
        String lastPathSegment = Uri.parse(str).getLastPathSegment();
        if (TextUtils.isEmpty(lastPathSegment)) {
            Logger.warn(TAG, Utils.format("The last path segment is empty! lookupUri is %s", str));
            return -1L;
        }
        try {
            return Long.parseLong(lastPathSegment);
        } catch (NumberFormatException unused) {
            Logger.error(TAG, Utils.format("The '%s' does not contain a parsable long.", lastPathSegment));
            return -1L;
        }
    }

    public long getContactIdByNumber(Context context, String str) {
        Cursor cursorQuery;
        try {
            cursorQuery = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str)), new String[]{"contact_id"}, null, null, null);
            try {
            } finally {
            }
        } catch (Exception e) {
            Logger.error(TAG, "getContactIdByNumber: exception", e);
        }
        if (cursorQuery == null) {
            Logger.error(TAG, "getContactIdByNumber: query fail, cursor is null");
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return -1L;
        }
        int columnIndex = cursorQuery.getColumnIndex("contact_id");
        if (!cursorQuery.moveToNext()) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return -1L;
        }
        long j = cursorQuery.getLong(columnIndex);
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return j;
    }

    public int getContactsCount(Context context) {
        Cursor cursorQuery;
        int count = -1;
        if (!Utils.checkSelfPermission(context, "android.permission.READ_CONTACTS")) {
            Logger.error(TAG, "getContactsCount: the android.permission.READ_CONTACTS was denied.");
            return -1;
        }
        try {
            cursorQuery = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, CONTACT_PROJECTION, null, null, null, null);
            try {
            } finally {
            }
        } catch (Exception e) {
            Logger.error(TAG, "getContactsCount: exception", e);
        }
        if (cursorQuery == null) {
            Logger.error(TAG, "getContactsCount: query fail, cursor is null");
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return -1;
        }
        count = cursorQuery.getCount();
        Logger.info(TAG, "getContactsCount: contact count = " + count);
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return count;
    }
}

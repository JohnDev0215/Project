package cerence.ark.assistant.core.phone;

import android.content.Context;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.provider.CallLog;
import android.provider.ContactsContract;
import cerence.ark.assistant.api.dynamic.Contact;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.core.phone.data.CallLog;
import cerence.ark.assistant.core.phone.data.PhoneNumber;
import cerence.ark.assistant.library.Utils;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class PhoneDatabase {
    private static final String[] CALL_LOG_PROJECTION = {"name", "number", "type", "date", "numbertype"};
    private static final String[] CONTACT_PROJECTION = {"_id", "display_name"};
    private static final String[] PHONE_NUMBER_PROJECTION = {"data1", "data2"};
    private static final String TAG = "PhoneDatabase";
    private final Map<CallLog.CallLogType, List<Integer>> mCallLogTypeMap;
    private final Map<Integer, PhoneNumber.PhoneNumberType> mPhoneTypeMap;

    public PhoneDatabase() {
        HashMap map = new HashMap(16);
        this.mPhoneTypeMap = map;
        EnumMap enumMap = new EnumMap(CallLog.CallLogType.class);
        this.mCallLogTypeMap = enumMap;
        map.put(1, PhoneNumber.PhoneNumberType.HOME);
        map.put(2, PhoneNumber.PhoneNumberType.MOBILE);
        map.put(3, PhoneNumber.PhoneNumberType.WORK);
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(7);
        enumMap.put(CallLog.CallLogType.ANSWERED_CALL_LOG, arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(1);
        arrayList2.add(3);
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
        Cursor cursorQuery;
        ArrayList arrayList = new ArrayList();
        if (!Utils.checkSelfPermission(context, "android.permission.READ_CONTACTS")) {
            Logger.error(TAG, "getContacts: the android.permission.READ_CONTACTS was denied.");
            return arrayList;
        }
        try {
            cursorQuery = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, CONTACT_PROJECTION, null, null, null, cancellationSignal);
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
        Logger.debug(TAG, "getContacts: begin, contact count = " + cursorQuery.getCount());
        int columnIndex = cursorQuery.getColumnIndex("_id");
        int columnIndex2 = cursorQuery.getColumnIndex("display_name");
        while (cursorQuery.moveToNext() && (cancellationSignal == null || !cancellationSignal.isCanceled())) {
            Contact contact = new Contact();
            contact.setId(cursorQuery.getLong(columnIndex));
            contact.setName(cursorQuery.getString(columnIndex2));
            arrayList.add(contact);
        }
        Logger.debug(TAG, "getContacts: finish, count = " + arrayList.size());
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    public List<PhoneNumber> getPhoneNumberListById(Context context, long j) {
        Cursor cursorQuery;
        PhoneNumber.PhoneNumberType phoneNumberType;
        ArrayList arrayList = new ArrayList();
        try {
            cursorQuery = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONE_NUMBER_PROJECTION, "contact_id = ?", new String[]{String.valueOf(j)}, null);
            try {
            } finally {
            }
        } catch (Exception e) {
            Logger.error(TAG, "getPhoneNumberListById: exception, contactId = " + j, e);
        }
        if (cursorQuery == null) {
            Logger.error(TAG, "getPhoneNumberListById: query fail, cursor is null, contactId = " + j);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return arrayList;
        }
        int columnIndex = cursorQuery.getColumnIndex("data1");
        int columnIndex2 = cursorQuery.getColumnIndex("data2");
        while (cursorQuery.moveToNext()) {
            PhoneNumber.Builder builder = PhoneNumber.builder();
            builder.setNumber(cursorQuery.getString(columnIndex));
            int i = cursorQuery.getInt(columnIndex2);
            if (this.mPhoneTypeMap.containsKey(Integer.valueOf(i))) {
                phoneNumberType = this.mPhoneTypeMap.get(Integer.valueOf(i));
            } else {
                phoneNumberType = PhoneNumber.PhoneNumberType.OTHER;
            }
            builder.setNumberType(phoneNumberType);
            arrayList.add(builder.build());
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    public List<CallLog> getCallLogsByType(Context context, CallLog.CallLogType callLogType, int i) {
        String string;
        String[] strArr;
        Cursor cursorQuery;
        PhoneNumber.PhoneNumberType phoneNumberType;
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
        try {
            cursorQuery = context.getContentResolver().query(i > 0 ? CallLog.Calls.CONTENT_URI.buildUpon().appendQueryParameter("limit", String.valueOf(i)).build() : CallLog.Calls.CONTENT_URI, CALL_LOG_PROJECTION, string, strArr, "date DESC");
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
        Logger.debug(TAG, String.format("getCallLogsByType: begin, callLogType = %s, count = %s", callLogType, Integer.valueOf(cursorQuery.getCount())));
        int columnIndex = cursorQuery.getColumnIndex("number");
        int columnIndex2 = cursorQuery.getColumnIndex("date");
        int columnIndex3 = cursorQuery.getColumnIndex("name");
        int columnIndex4 = cursorQuery.getColumnIndex("numbertype");
        int columnIndex5 = cursorQuery.getColumnIndex("type");
        while (cursorQuery.moveToNext()) {
            CallLog.Builder builder = cerence.ark.assistant.core.phone.data.CallLog.builder();
            builder.setTimestamp(cursorQuery.getLong(columnIndex2));
            Contact contact = new Contact();
            contact.setName(cursorQuery.getString(columnIndex3));
            builder.setContact(contact);
            int i3 = cursorQuery.getInt(columnIndex4);
            if (this.mPhoneTypeMap.containsKey(Integer.valueOf(i3))) {
                phoneNumberType = this.mPhoneTypeMap.get(Integer.valueOf(i3));
            } else {
                phoneNumberType = PhoneNumber.PhoneNumberType.OTHER;
            }
            PhoneNumber.Builder builder2 = PhoneNumber.builder();
            builder2.setNumber(cursorQuery.getString(columnIndex));
            builder2.setNumberType(phoneNumberType);
            builder.setPhoneNumber(builder2.build());
            switch (cursorQuery.getInt(columnIndex5)) {
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
        }
        Logger.debug(TAG, "getCallLogsByType: finish, count = " + arrayList.size());
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }
}

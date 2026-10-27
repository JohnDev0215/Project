package com.dee.app.sync.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import androidx.annotation.NonNull;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.interfaces.models.data.ContactUploadInfo;
import com.dee.app.contacts.interfaces.models.data.PhoneNumberType;
import com.dee.app.contacts.interfaces.models.data.enums.EmailAddressType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class DeviceContactsFetcher {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", DeviceContactsFetcher.class);
    private final Context mContext;
    private final String mDeviceId;

    public interface ContactFetchCallback {
        void onContactsFetched(List<ContactUploadInfo> list);
    }

    public DeviceContactsFetcher(Context context, String str) {
        this.mContext = context;
        this.mDeviceId = str;
    }

    @NonNull
    public List<ContactUploadInfo> getDeviceContacts() {
        return fetchDeviceContactsInternal(null, 100, "_id > ? ", new String[]{String.valueOf(-1)});
    }

    public int getDeviceContactsCount() {
        Cursor cursorQuery = this.mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id"}, null, null, null);
        try {
            int count = cursorQuery.getCount();
            cursorQuery.close();
            return count;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public int getLastDeviceContactId() {
        Cursor cursorQuery = this.mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id"}, null, null, "_id DESC LIMIT 1");
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToNext()) {
                    int i = cursorQuery.getInt(cursorQuery.getColumnIndex("_id"));
                    cursorQuery.close();
                    return i;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (cursorQuery == null) {
            return 0;
        }
        cursorQuery.close();
        return 0;
    }

    @NonNull
    private List<ContactUploadInfo> fetchDeviceContactsInternal(ContactFetchCallback contactFetchCallback, int i, String str, String[] strArr) {
        int count;
        LOG.i("Fetching device contacts.");
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        long jCurrentTimeMillis = System.currentTimeMillis();
        String str2 = "_id";
        Cursor cursorQuery = this.mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id"}, str, strArr, null);
        if (cursorQuery != null) {
            try {
                count = cursorQuery.getCount();
            } catch (Throwable th) {
                try {
                    throw th;
                } finally {
                }
            }
        } else {
            count = 0;
        }
        if (count == 0) {
            if (contactFetchCallback != null) {
                contactFetchCallback.onContactsFetched(arrayList);
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return arrayList;
        }
        long j = 0;
        while (cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndex(str2));
            ContactUploadInfo contactUploadInfo = new ContactUploadInfo();
            contactUploadInfo.setDeviceContactId(string);
            String str3 = str2;
            contactUploadInfo.setSourceDeviceId(this.mDeviceId);
            map.put(string, contactUploadInfo);
            if (map.size() == i) {
                fetchNamesAndCompany(map);
                fetchPhoneNumbers(map);
                fetchEmailAddresses(map);
                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                j += jCurrentTimeMillis2;
                LOG.d("Contacts read: " + i + ". Time taken: " + jCurrentTimeMillis2);
                if (contactFetchCallback != null) {
                    contactFetchCallback.onContactsFetched(new ArrayList(map.values()));
                } else {
                    arrayList.addAll(map.values());
                }
                map.clear();
                jCurrentTimeMillis = System.currentTimeMillis();
            }
            str2 = str3;
        }
        if (!map.isEmpty()) {
            fetchNamesAndCompany(map);
            fetchPhoneNumbers(map);
            fetchEmailAddresses(map);
            long jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
            j += jCurrentTimeMillis3;
            LOG.d("Contacts read: " + i + ". Time taken: " + jCurrentTimeMillis3);
            if (contactFetchCallback != null) {
                contactFetchCallback.onContactsFetched(new ArrayList(map.values()));
            } else {
                arrayList.addAll(map.values());
            }
            map.clear();
        }
        cursorQuery.close();
        LOG.d("Time taken to fetch " + count + "contacts is " + j + "ms");
        return arrayList;
    }

    @TargetApi(21)
    private void fetchNamesAndCompany(Map<String, ContactUploadInfo> map) {
        String str = "name_raw_contact_id = raw_contact_id AND contact_id IN (" + getInListPlaceholder(map.size()) + ")";
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(map.keySet());
        String[] strArr = (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]);
        linkedHashSet.clear();
        Cursor cursorQuery = this.mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, str, strArr, null);
        while (cursorQuery.moveToNext()) {
            try {
                ContactUploadInfo contactUploadInfo = map.get(cursorQuery.getString(cursorQuery.getColumnIndex("contact_id")));
                String string = cursorQuery.getString(cursorQuery.getColumnIndex("mimetype"));
                byte b = -1;
                int iHashCode = string.hashCode();
                if (iHashCode != -1079224304) {
                    if (iHashCode != 689862072) {
                        if (iHashCode == 2034973555 && string.equals("vnd.android.cursor.item/nickname")) {
                            b = 2;
                        }
                    } else if (string.equals("vnd.android.cursor.item/organization")) {
                        b = 1;
                    }
                } else if (string.equals("vnd.android.cursor.item/name")) {
                    b = 0;
                }
                if (b == 0) {
                    String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("data2"));
                    String string3 = cursorQuery.getString(cursorQuery.getColumnIndex("data3"));
                    String string4 = cursorQuery.getString(cursorQuery.getColumnIndex("data7"));
                    String string5 = cursorQuery.getString(cursorQuery.getColumnIndex("data9"));
                    if (contactUploadInfo.getName() == null) {
                        contactUploadInfo.setContactName(string2, string3, string4, string5, null);
                    } else {
                        contactUploadInfo.getName().setFirstName(string2);
                        contactUploadInfo.getName().setLastName(string3);
                        contactUploadInfo.getName().setPhoneticFirstName(string4);
                        contactUploadInfo.getName().setPhoneticLastName(string5);
                    }
                } else if (b == 1) {
                    contactUploadInfo.setCompany(cursorQuery.getString(cursorQuery.getColumnIndex("data1")));
                } else if (b == 2) {
                    String string6 = cursorQuery.getString(cursorQuery.getColumnIndex("data1"));
                    if (contactUploadInfo.getName() == null) {
                        contactUploadInfo.setContactName(null, null, string6);
                    } else {
                        contactUploadInfo.getName().setNickName(string6);
                    }
                } else {
                    LOG.d("The cursor does not hold information required for names/company");
                }
            } finally {
            }
        }
        cursorQuery.close();
    }

    private void fetchPhoneNumbers(Map<String, ContactUploadInfo> map) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{"contact_id", "data1", "data2", "data3"}, "contact_id IN (" + getInListPlaceholder(map.size()) + ")", (String[]) map.keySet().toArray(new String[map.size()]), null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() != 0) {
                    cursorQuery.moveToFirst();
                    do {
                        map.get(cursorQuery.getString(cursorQuery.getColumnIndex("contact_id"))).addPhoneNumber(cursorQuery.getString(cursorQuery.getColumnIndex("data1")), getPhoneNumberType(cursorQuery.getInt(cursorQuery.getColumnIndex("data2")), cursorQuery.getString(cursorQuery.getColumnIndex("data3"))));
                    } while (cursorQuery.moveToNext());
                    cursorQuery.close();
                    return;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
    }

    private void fetchEmailAddresses(Map<String, ContactUploadInfo> map) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, new String[]{"contact_id", "data1", "data2", "data3"}, "contact_id IN (" + getInListPlaceholder(map.size()) + ")", (String[]) map.keySet().toArray(new String[map.size()]), null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() != 0) {
                    cursorQuery.moveToFirst();
                    do {
                        map.get(cursorQuery.getString(cursorQuery.getColumnIndex("contact_id"))).addEmailAddress(cursorQuery.getString(cursorQuery.getColumnIndex("data1")), getEmailAddressType(cursorQuery.getInt(cursorQuery.getColumnIndex("data2")), cursorQuery.getString(cursorQuery.getColumnIndex("data3"))));
                    } while (cursorQuery.moveToNext());
                    cursorQuery.close();
                    return;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
    }

    String getPhoneNumberType(int i, String str) {
        PhoneNumberType phoneNumberTypeFromAddressBookType = PhoneNumberType.fromAddressBookType(i);
        if (phoneNumberTypeFromAddressBookType == PhoneNumberType.Custom) {
            return str != null ? str : "";
        }
        return phoneNumberTypeFromAddressBookType.getAcmsType();
    }

    String getEmailAddressType(int i, String str) {
        EmailAddressType emailAddressTypeFromAddressBookType = EmailAddressType.fromAddressBookType(i);
        if (emailAddressTypeFromAddressBookType == EmailAddressType.Custom) {
            return str != null ? str : "";
        }
        return emailAddressTypeFromAddressBookType.getAcmsType();
    }

    private String getInListPlaceholder(int i) {
        if (i < 1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        for (int i2 = 1; i2 < i; i2++) {
            sb.append(",?");
        }
        return sb.toString();
    }
}

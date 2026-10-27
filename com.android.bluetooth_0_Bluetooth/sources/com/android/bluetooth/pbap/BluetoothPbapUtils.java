package com.android.bluetooth.pbap;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import com.android.bluetooth.opp.BluetoothShare;
import com.android.vcard.VCardComposer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
class BluetoothPbapUtils {
    private static final int FILTER_PHOTO = 3;
    private static final long QUERY_CONTACT_RETRY_INTERVAL = 4000;
    private static final String TAG = "BluetoothPbapUtils";
    private static final String TYPE_ADDRESS = "address";
    private static final String TYPE_EMAIL = "email";
    private static final String TYPE_NAME = "name";
    private static final String TYPE_PHONE = "phone";
    private static final boolean V = false;
    static AtomicLong sDbIdentifier = new AtomicLong();
    static long sPrimaryVersionCounter = 0;
    static long sSecondaryVersionCounter = 0;
    private static long sTotalContacts = 0;
    private static long sTotalFields = 0;
    private static long sTotalSvcFields = 0;
    private static long sContactsLastUpdated = 0;
    private static HashMap<String, ContactData> sContactDataset = new HashMap<>();
    private static HashSet<String> sContactSet = new HashSet<>();

    BluetoothPbapUtils() {
    }

    private static class ContactData {
        private ArrayList<String> mAddress;
        private ArrayList<String> mEmail;
        private String mName;
        private ArrayList<String> mPhone;

        ContactData() {
            this.mPhone = new ArrayList<>();
            this.mEmail = new ArrayList<>();
            this.mAddress = new ArrayList<>();
        }

        ContactData(String name, ArrayList<String> phone, ArrayList<String> email, ArrayList<String> address) {
            this.mName = name;
            this.mPhone = phone;
            this.mEmail = email;
            this.mAddress = address;
        }
    }

    private static boolean hasFilter(byte[] filter) {
        return filter != null && filter.length > 0;
    }

    private static boolean isFilterBitSet(byte[] filter, int filterBit) {
        if (hasFilter(filter)) {
            int byteNumber = 7 - (filterBit / 8);
            int bitNumber = filterBit % 8;
            return byteNumber < filter.length && (filter[byteNumber] & (1 << bitNumber)) > 0;
        }
        return false;
    }

    static VCardComposer createFilteredVCardComposer(Context ctx, int vcardType, byte[] filter) {
        int vType = vcardType;
        boolean includePhoto = BluetoothPbapConfig.includePhotosInVcard() && (!hasFilter(filter) || isFilterBitSet(filter, 3));
        if (!includePhoto) {
            vType |= 8388608;
        }
        return new VCardComposer(ctx, vType, true);
    }

    public static String getProfileName(Context context) {
        Cursor c = context.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, new String[]{"display_name"}, null, null, null);
        String ownerName = null;
        if (c != null && c.moveToFirst()) {
            ownerName = c.getString(0);
        }
        if (c != null) {
            c.close();
        }
        return ownerName;
    }

    static String createProfileVCard(Context ctx, int vcardType, byte[] filter) {
        VCardComposer composer;
        Throwable t;
        String strCreateOneEntry = TAG;
        String vcard = null;
        try {
            composer = createFilteredVCardComposer(ctx, vcardType, filter);
            try {
                if (composer.init(ContactsContract.Profile.CONTENT_URI, null, null, null, null, Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, ContactsContract.RawContactsEntity.CONTENT_URI.getLastPathSegment()))) {
                    strCreateOneEntry = composer.createOneEntry();
                    vcard = strCreateOneEntry;
                } else {
                    Log.e(TAG, "Unable to create profile vcard. Error initializing composer: " + composer.getErrorReason());
                }
            } catch (Throwable th) {
                t = th;
                Log.e(strCreateOneEntry, "Unable to create profile vcard.", t);
            }
        } catch (Throwable th2) {
            composer = null;
            t = th2;
        }
        if (composer != null) {
            composer.terminate();
        }
        return vcard;
    }

    static void savePbapParams(Context ctx) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ctx);
        long dbIdentifier = sDbIdentifier.get();
        SharedPreferences.Editor edit = pref.edit();
        edit.putLong("primary", sPrimaryVersionCounter);
        edit.putLong("secondary", sSecondaryVersionCounter);
        edit.putLong("dbIdentifier", dbIdentifier);
        edit.putLong("totalContacts", sTotalContacts);
        edit.putLong("lastUpdatedTimestamp", sContactsLastUpdated);
        edit.putLong("totalFields", sTotalFields);
        edit.putLong("totalSvcFields", sTotalSvcFields);
        edit.apply();
    }

    static void fetchPbapParams(Context ctx) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ctx);
        long timeStamp = Calendar.getInstance().getTimeInMillis();
        sDbIdentifier.set(pref.getLong("DbIdentifier", timeStamp));
        sPrimaryVersionCounter = pref.getLong("primary", 0L);
        sSecondaryVersionCounter = pref.getLong("secondary", 0L);
        sTotalFields = pref.getLong("totalContacts", 0L);
        sContactsLastUpdated = pref.getLong("lastUpdatedTimestamp", timeStamp);
        sTotalFields = pref.getLong("totalFields", 0L);
        sTotalSvcFields = pref.getLong("totalSvcFields", 0L);
    }

    static void loadAllContacts(Context context, Handler handler) {
        String[] projection = {"contact_id", "data1", BluetoothShare.MIMETYPE};
        long jFetchAndSetContacts = fetchAndSetContacts(context, handler, projection, null, null, true);
        sTotalContacts = jFetchAndSetContacts;
        if (jFetchAndSetContacts < 0) {
            sTotalContacts = 0L;
        } else {
            handler.sendMessage(handler.obtainMessage(5));
        }
    }

    static void updateSecondaryVersionCounter(Context context, Handler handler) {
        byte b;
        String str;
        ArrayList<String> phoneTmp;
        ArrayList<String> addressTmp;
        ArrayList<String> emailTmp;
        long j;
        ArrayList<String> updatedList = new ArrayList<>();
        HashSet<String> currentContactSet = new HashSet<>();
        String[] projection = {"_id", "contact_last_updated_timestamp"};
        Cursor c = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, projection, null, null, null);
        String str2 = "Failed to fetch data from contact database";
        if (c == null) {
            Log.d(TAG, "Failed to fetch data from contact database");
            return;
        }
        while (c.moveToNext()) {
            String contactId = c.getString(0);
            long lastUpdatedTime = c.getLong(1);
            if (lastUpdatedTime > sContactsLastUpdated) {
                updatedList.add(contactId);
            }
            currentContactSet.add(contactId);
        }
        int currentContactCount = c.getCount();
        c.close();
        String data = "data1";
        String str3 = BluetoothShare.MIMETYPE;
        String[] dataProjection = {"contact_id", "data1", BluetoothShare.MIMETYPE};
        long j2 = currentContactCount;
        long j3 = sTotalContacts;
        if (j2 > j3) {
            Iterator<String> it = updatedList.iterator();
            while (it.hasNext()) {
                String[] selectionArgs = {it.next()};
                fetchAndSetContacts(context, handler, dataProjection, "contact_id=?", selectionArgs, false);
                sSecondaryVersionCounter++;
                sPrimaryVersionCounter++;
                sTotalContacts = currentContactCount;
            }
        } else {
            String str4 = "vnd.android.cursor.item/postal-address_v2";
            String str5 = "vnd.android.cursor.item/name";
            if (currentContactCount < j3) {
                sTotalContacts = currentContactCount;
                ArrayList<String> svcFields = new ArrayList<>(Arrays.asList("vnd.android.cursor.item/name", "vnd.android.cursor.item/phone_v2", "vnd.android.cursor.item/email_v2", "vnd.android.cursor.item/postal-address_v2"));
                HashSet<String> deletedContacts = new HashSet<>(sContactSet);
                deletedContacts.removeAll(currentContactSet);
                sPrimaryVersionCounter += (long) deletedContacts.size();
                sSecondaryVersionCounter += (long) deletedContacts.size();
                for (String deletedContact : deletedContacts) {
                    sContactSet.remove(deletedContact);
                    String[] selectionArgs2 = {deletedContact};
                    Cursor dataCursor = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, dataProjection, "contact_id=?", selectionArgs2, null);
                    if (dataCursor == null) {
                        Log.d(TAG, "Failed to fetch data from contact database");
                        return;
                    }
                    while (dataCursor.moveToNext()) {
                        if (svcFields.contains(dataCursor.getString(dataCursor.getColumnIndex(BluetoothShare.MIMETYPE)))) {
                            j = 1;
                            sTotalSvcFields--;
                        } else {
                            j = 1;
                        }
                        sTotalFields -= j;
                    }
                    dataCursor.close();
                }
            } else {
                ArrayList<String> updatedList2 = updatedList;
                Iterator<String> it2 = updatedList2.iterator();
                while (it2.hasNext()) {
                    String contact = it2.next();
                    sPrimaryVersionCounter++;
                    ArrayList<String> phoneTmp2 = new ArrayList<>();
                    ArrayList<String> emailTmp2 = new ArrayList<>();
                    ArrayList<String> addressTmp2 = new ArrayList<>();
                    boolean updated = false;
                    ArrayList<String> updatedList3 = updatedList2;
                    Iterator<String> it3 = it2;
                    String[] selectionArgs3 = {contact};
                    String str6 = str5;
                    Cursor dataCursor2 = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, dataProjection, "contact_id=?", selectionArgs3, null);
                    if (dataCursor2 == null) {
                        Log.d(TAG, str2);
                        return;
                    }
                    int indexData = dataCursor2.getColumnIndex(data);
                    String str7 = str2;
                    int indexMimeType = dataCursor2.getColumnIndex(str3);
                    String[] dataProjection2 = dataProjection;
                    String nameTmp = null;
                    while (dataCursor2.moveToNext()) {
                        String str8 = data;
                        String data2 = dataCursor2.getString(indexData);
                        String str9 = str3;
                        String mimeType = dataCursor2.getString(indexMimeType);
                        int indexMimeType2 = indexMimeType;
                        switch (mimeType.hashCode()) {
                            case -1569536764:
                                b = mimeType.equals("vnd.android.cursor.item/email_v2") ? (byte) 0 : (byte) -1;
                                break;
                            case -1079224304:
                                b = mimeType.equals(str6) ? (byte) 3 : (byte) -1;
                                break;
                            case -601229436:
                                b = mimeType.equals(str4) ? (byte) 2 : (byte) -1;
                                break;
                            case 684173810:
                                b = mimeType.equals("vnd.android.cursor.item/phone_v2") ? (byte) 1 : (byte) -1;
                                break;
                            default:
                                b = -1;
                                break;
                        }
                        if (b != 0) {
                            str = str6;
                            if (b == 1) {
                                addressTmp = addressTmp2;
                                phoneTmp = phoneTmp2;
                                phoneTmp.add(data2);
                                emailTmp = emailTmp2;
                            } else if (b == 2) {
                                addressTmp = addressTmp2;
                                addressTmp.add(data2);
                                phoneTmp = phoneTmp2;
                                emailTmp = emailTmp2;
                            } else if (b != 3) {
                                phoneTmp = phoneTmp2;
                                addressTmp = addressTmp2;
                                emailTmp = emailTmp2;
                            } else {
                                nameTmp = data2;
                                phoneTmp = phoneTmp2;
                                addressTmp = addressTmp2;
                                emailTmp = emailTmp2;
                            }
                        } else {
                            str = str6;
                            phoneTmp = phoneTmp2;
                            addressTmp = addressTmp2;
                            emailTmp = emailTmp2;
                            emailTmp.add(data2);
                        }
                        addressTmp2 = addressTmp;
                        phoneTmp2 = phoneTmp;
                        emailTmp2 = emailTmp;
                        data = str8;
                        str3 = str9;
                        str6 = str;
                        indexMimeType = indexMimeType2;
                    }
                    String str10 = str6;
                    String str11 = data;
                    String str12 = str3;
                    ArrayList<String> phoneTmp3 = phoneTmp2;
                    ArrayList<String> emailTmp3 = emailTmp2;
                    ArrayList<String> addressTmp3 = addressTmp2;
                    ContactData cData = new ContactData(nameTmp, phoneTmp3, emailTmp3, addressTmp3);
                    dataCursor2.close();
                    String str13 = str4;
                    ContactData currentContactData = sContactDataset.get(contact);
                    if (currentContactData != null) {
                        HashSet<String> currentContactSet2 = currentContactSet;
                        if (!Objects.equals(nameTmp, currentContactData.mName) || checkFieldUpdates(currentContactData.mPhone, phoneTmp3) || checkFieldUpdates(currentContactData.mEmail, emailTmp3) || checkFieldUpdates(currentContactData.mAddress, addressTmp3)) {
                            updated = true;
                        }
                        if (updated) {
                            sSecondaryVersionCounter++;
                            sContactDataset.put(contact, cData);
                        }
                        str2 = str7;
                        dataProjection = dataProjection2;
                        data = str11;
                        str3 = str12;
                        str4 = str13;
                        currentContactSet = currentContactSet2;
                        str5 = str10;
                        updatedList2 = updatedList3;
                        it2 = it3;
                    } else {
                        Log.e(TAG, "Null contact in the updateList: " + contact);
                        str2 = str7;
                        dataProjection = dataProjection2;
                        data = str11;
                        str3 = str12;
                        str4 = str13;
                        currentContactSet = currentContactSet;
                        str5 = str10;
                        updatedList2 = updatedList3;
                        it2 = it3;
                    }
                }
            }
        }
        Log.d(TAG, "primaryVersionCounter = " + sPrimaryVersionCounter + ", secondaryVersionCounter=" + sSecondaryVersionCounter);
        if (sSecondaryVersionCounter < 0 || sPrimaryVersionCounter < 0) {
            handler.sendMessage(handler.obtainMessage(7));
        }
    }

    private static boolean checkFieldUpdates(ArrayList<String> oldFields, ArrayList<String> newFields) {
        if (newFields != null && oldFields != null) {
            if (newFields.size() != oldFields.size()) {
                sTotalSvcFields += (long) Math.abs(newFields.size() - oldFields.size());
                sTotalFields += (long) Math.abs(newFields.size() - oldFields.size());
                return true;
            }
            for (String newField : newFields) {
                if (!oldFields.contains(newField)) {
                    return true;
                }
            }
            return false;
        }
        if (newFields == null && oldFields != null && oldFields.size() > 0) {
            sTotalSvcFields += (long) oldFields.size();
            sTotalFields += (long) oldFields.size();
            return true;
        }
        if (oldFields == null && newFields != null && newFields.size() > 0) {
            sTotalSvcFields += (long) newFields.size();
            sTotalFields += (long) newFields.size();
            return true;
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int fetchAndSetContacts(android.content.Context r17, android.os.Handler r18, java.lang.String[] r19, java.lang.String r20, java.lang.String[] r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.pbap.BluetoothPbapUtils.fetchAndSetContacts(android.content.Context, android.os.Handler, java.lang.String[], java.lang.String, java.lang.String[], boolean):int");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static void setContactFields(String fieldType, String contactId, String data) {
        ContactData cData;
        if (sContactDataset.containsKey(contactId)) {
            cData = sContactDataset.get(contactId);
        } else {
            cData = new ContactData();
        }
        byte b = -1;
        switch (fieldType.hashCode()) {
            case -1147692044:
                if (fieldType.equals(TYPE_ADDRESS)) {
                    b = 3;
                }
                break;
            case 3373707:
                if (fieldType.equals("name")) {
                    b = 0;
                }
                break;
            case 96619420:
                if (fieldType.equals("email")) {
                    b = 2;
                }
                break;
            case 106642798:
                if (fieldType.equals(TYPE_PHONE)) {
                    b = 1;
                }
                break;
        }
        if (b == 0) {
            cData.mName = data;
        } else if (b == 1) {
            cData.mPhone.add(data);
        } else if (b == 2) {
            cData.mEmail.add(data);
        } else if (b == 3) {
            cData.mAddress.add(data);
        }
        sContactDataset.put(contactId, cData);
    }

    static void rolloverCounters() {
        sDbIdentifier.set(Calendar.getInstance().getTimeInMillis());
        long j = sPrimaryVersionCounter;
        if (j < 0) {
            j = 0;
        }
        sPrimaryVersionCounter = j;
        long j2 = sSecondaryVersionCounter;
        sSecondaryVersionCounter = j2 >= 0 ? j2 : 0L;
    }
}

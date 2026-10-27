package com.android.providers.contacts;

import android.text.TextUtils;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MetadataEntryParser {

    public static class UsageStats {
        final long mLastTimeUsed;
        final int mTimesUsed;
        final String mUsageType;

        public UsageStats(String str, long j, int i) {
            this.mUsageType = str;
            this.mLastTimeUsed = j;
            this.mTimesUsed = i;
        }
    }

    public static class FieldData {
        final String mDataHashId;
        final boolean mIsPrimary;
        final boolean mIsSuperPrimary;
        final ArrayList<UsageStats> mUsageStatsList;

        public FieldData(String str, boolean z, boolean z2, ArrayList<UsageStats> arrayList) {
            this.mDataHashId = str;
            this.mIsPrimary = z;
            this.mIsSuperPrimary = z2;
            this.mUsageStatsList = arrayList;
        }
    }

    public static class RawContactInfo {
        final String mAccountName;
        final String mAccountType;
        final String mBackupId;
        final String mDataSet;

        public RawContactInfo(String str, String str2, String str3, String str4) {
            this.mBackupId = str;
            this.mAccountType = str2;
            this.mAccountName = str3;
            this.mDataSet = str4;
        }
    }

    public static class AggregationData {
        final RawContactInfo mRawContactInfo1;
        final RawContactInfo mRawContactInfo2;
        final String mType;

        public AggregationData(RawContactInfo rawContactInfo, RawContactInfo rawContactInfo2, String str) {
            this.mRawContactInfo1 = rawContactInfo;
            this.mRawContactInfo2 = rawContactInfo2;
            this.mType = str;
        }
    }

    public static class MetadataEntry {
        final ArrayList<AggregationData> mAggregationDatas;
        final ArrayList<FieldData> mFieldDatas;
        final int mPinned;
        final RawContactInfo mRawContactInfo;
        final int mSendToVoicemail;
        final int mStarred;

        public MetadataEntry(RawContactInfo rawContactInfo, int i, int i2, int i3, ArrayList<FieldData> arrayList, ArrayList<AggregationData> arrayList2) {
            this.mRawContactInfo = rawContactInfo;
            this.mSendToVoicemail = i;
            this.mStarred = i2;
            this.mPinned = i3;
            this.mFieldDatas = arrayList;
            this.mAggregationDatas = arrayList2;
        }
    }

    static MetadataEntry parseDataToMetaDataEntry(String str) {
        String str2 = "usage_stats";
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            RawContactInfo uniqueContact = parseUniqueContact(jSONObject.getJSONObject("unique_contact_id"));
            JSONObject jSONObject2 = jSONObject.getJSONObject("contact_prefs");
            int i = 0;
            boolean z = jSONObject2.has("send_to_voicemail") ? jSONObject2.getBoolean("send_to_voicemail") : false;
            boolean z2 = jSONObject2.has("starred") ? jSONObject2.getBoolean("starred") : false;
            int i2 = jSONObject2.has("pinned") ? jSONObject2.getInt("pinned") : 0;
            ArrayList arrayList = new ArrayList();
            if (jSONObject.has("aggregation_data")) {
                JSONArray jSONArray = jSONObject.getJSONArray("aggregation_data");
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i3);
                    JSONArray jSONArray2 = jSONObject3.getJSONArray("contact_ids");
                    if (jSONArray2.length() != 2) {
                        throw new IllegalArgumentException("There should be two contacts for each aggregation.");
                    }
                    RawContactInfo uniqueContact2 = parseUniqueContact(jSONArray2.getJSONObject(0));
                    RawContactInfo uniqueContact3 = parseUniqueContact(jSONArray2.getJSONObject(1));
                    String string = jSONObject3.getString("type");
                    if (TextUtils.isEmpty(string)) {
                        throw new IllegalArgumentException("Aggregation type cannot be empty.");
                    }
                    arrayList.add(new AggregationData(uniqueContact2, uniqueContact3, string));
                }
            }
            ArrayList arrayList2 = new ArrayList();
            if (jSONObject.has("field_data")) {
                JSONArray jSONArray3 = jSONObject.getJSONArray("field_data");
                int i4 = 0;
                while (i4 < jSONArray3.length()) {
                    JSONObject jSONObject4 = jSONArray3.getJSONObject(i4);
                    String string2 = jSONObject4.getString("field_data_id");
                    if (TextUtils.isEmpty(string2)) {
                        throw new IllegalArgumentException("Field data hash id cannot be empty.");
                    }
                    JSONObject jSONObject5 = jSONObject4.getJSONObject("field_data_prefs");
                    boolean z3 = jSONObject5.getBoolean("is_primary");
                    boolean z4 = jSONObject5.getBoolean("is_super_primary");
                    ArrayList arrayList3 = new ArrayList();
                    if (jSONObject4.has(str2)) {
                        int i5 = i;
                        for (JSONArray jSONArray4 = jSONObject4.getJSONArray(str2); i5 < jSONArray4.length(); jSONArray4 = jSONArray4) {
                            JSONObject jSONObject6 = jSONArray4.getJSONObject(i5);
                            String str3 = str2;
                            String string3 = jSONObject6.getString("usage_type");
                            if (TextUtils.isEmpty(string3)) {
                                throw new IllegalArgumentException("Usage type cannot be empty.");
                            }
                            arrayList3.add(new UsageStats(string3, jSONObject6.getLong("last_time_used"), jSONObject6.getInt("usage_count")));
                            i5++;
                            str2 = str3;
                            jSONArray3 = jSONArray3;
                            z2 = z2;
                        }
                    }
                    arrayList2.add(new FieldData(string2, z3, z4, arrayList3));
                    i4++;
                    str2 = str2;
                    jSONArray3 = jSONArray3;
                    z2 = z2;
                    i = 0;
                }
            }
            return new MetadataEntry(uniqueContact, z ? 1 : 0, z2 ? 1 : 0, i2, arrayList2, arrayList);
        } catch (JSONException e) {
            throw new IllegalArgumentException("JSON Exception.", e);
        }
    }

    private static RawContactInfo parseUniqueContact(JSONObject jSONObject) {
        String string;
        try {
            String string2 = jSONObject.getString("contact_id");
            String string3 = jSONObject.getString("account_name");
            String string4 = jSONObject.getString("account_type");
            if ("GOOGLE_ACCOUNT".equals(string4)) {
                string = "com.google";
            } else if ("CUSTOM_ACCOUNT".equals(string4)) {
                string = jSONObject.getString("custom_account_type");
            } else {
                throw new IllegalArgumentException("Unknown account type.");
            }
            String string5 = null;
            String string6 = jSONObject.getString("data_set");
            byte b = -1;
            int iHashCode = string6.hashCode();
            if (iHashCode != 1847683040) {
                if (iHashCode == 1999208305 && string6.equals("CUSTOM")) {
                    b = 1;
                }
            } else if (string6.equals("GOOGLE_PLUS")) {
                b = 0;
            }
            if (b == 0) {
                string5 = "plus";
            } else if (b == 1) {
                string5 = jSONObject.getString("custom_data_set");
            }
            if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string) || TextUtils.isEmpty(string3)) {
                throw new IllegalArgumentException("Contact backup id, account type, account name cannot be empty.");
            }
            return new RawContactInfo(string2, string, string3, string5);
        } catch (JSONException e) {
            throw new IllegalArgumentException("JSON Exception.", e);
        }
    }
}

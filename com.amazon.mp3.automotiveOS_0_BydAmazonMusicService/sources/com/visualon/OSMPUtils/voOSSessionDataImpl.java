package com.visualon.OSMPUtils;

import android.os.Parcel;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes7.dex */
public class voOSSessionDataImpl implements voOSSessionData {
    private static final String TAG = "@@@voOSSessionDataImpl";
    private ArrayList<Item> items = new ArrayList<>();

    public class Item {
        private String mDataID;
        private String mLanguage;
        private String mURI;
        private String mValue;

        public Item() {
        }

        public String getDataID() {
            return this.mDataID;
        }

        public String getValue() {
            return this.mValue;
        }

        public String getURI() {
            return this.mURI;
        }

        public String getLanguage() {
            return this.mLanguage;
        }
    }

    protected voOSSessionDataImpl() {
    }

    @Override // com.visualon.OSMPUtils.voOSSessionData
    public int getCount() {
        return this.items.size();
    }

    @Override // com.visualon.OSMPUtils.voOSSessionData
    public String getDataID(int i) {
        return this.items.get(i).getDataID();
    }

    @Override // com.visualon.OSMPUtils.voOSSessionData
    public String getValue(int i) {
        return this.items.get(i).getValue();
    }

    @Override // com.visualon.OSMPUtils.voOSSessionData
    public String getURI(int i) {
        return this.items.get(i).getURI();
    }

    @Override // com.visualon.OSMPUtils.voOSSessionData
    public String getLanguage(int i) {
        return this.items.get(i).getLanguage();
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public static voOSSessionDataImpl parse(Parcel parcel) {
        if (parcel == null) {
            return null;
        }
        voOSSessionDataImpl voossessiondataimpl = new voOSSessionDataImpl();
        parcel.setDataPosition(0);
        while (parcel.dataPosition() < parcel.dataSize()) {
            Item item = voossessiondataimpl.new Item();
            item.mDataID = parcel.readString();
            if (parcel.dataPosition() >= parcel.dataSize()) {
                break;
            }
            item.mValue = parcel.readString();
            if (parcel.dataPosition() >= parcel.dataSize()) {
                break;
            }
            item.mURI = parcel.readString();
            if (parcel.dataPosition() >= parcel.dataSize()) {
                break;
            }
            item.mLanguage = parcel.readString();
            voossessiondataimpl.items.add(item);
            voLog.i(TAG, "DataID: " + item.mDataID + " | Value:" + item.mValue + " | URI:" + item.mURI + " | Language:" + item.mLanguage, new Object[0]);
        }
        parcel.recycle();
        return voossessiondataimpl;
    }
}

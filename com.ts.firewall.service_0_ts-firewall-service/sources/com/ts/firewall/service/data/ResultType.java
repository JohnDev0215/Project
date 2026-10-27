package com.ts.firewall.service.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public enum ResultType implements Parcelable {
    ADD_APP_RULE(1),
    REMOVE_APP_RULE(2),
    ADD_IP_RULE(3),
    REMOVE_IP_RULE(4),
    ADD_PORT_RULE(5),
    REMOVE_PORT_RULE(6),
    ADD_URL_RULE(7),
    REMOVE_URL_RULE(8);

    public static final Parcelable.Creator<ResultType> CREATOR = new Parcelable.Creator<ResultType>() { // from class: com.ts.firewall.service.data.ResultType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResultType createFromParcel(Parcel in) {
            return ResultType.valueOf(in.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResultType[] newArray(int size) {
            return new ResultType[size];
        }
    };
    private static final int TYPE_ADD_APP_RULE = 1;
    private static final int TYPE_ADD_IP_RULE = 3;
    private static final int TYPE_ADD_PORT_RULE = 5;
    private static final int TYPE_ADD_URL_RULE = 7;
    private static final int TYPE_REMOVE_APP_RULE = 2;
    private static final int TYPE_REMOVE_IP_RULE = 4;
    private static final int TYPE_REMOVE_PORT_RULE = 6;
    private static final int TYPE_REMOVE_URL_RULE = 8;
    private final int value;

    ResultType(int value) {
        this.value = value;
    }

    public static ResultType valueOf(int type) {
        switch (type) {
            case 1:
                return ADD_APP_RULE;
            case 2:
                return REMOVE_APP_RULE;
            case 3:
                return ADD_IP_RULE;
            case 4:
                return REMOVE_IP_RULE;
            case 5:
                return ADD_PORT_RULE;
            case 6:
                return REMOVE_PORT_RULE;
            case 7:
                return ADD_URL_RULE;
            case 8:
                return REMOVE_URL_RULE;
            default:
                return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name());
    }
}

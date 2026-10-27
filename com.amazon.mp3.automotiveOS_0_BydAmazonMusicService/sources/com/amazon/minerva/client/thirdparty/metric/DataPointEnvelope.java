package com.amazon.minerva.client.thirdparty.metric;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DataPointEnvelope implements Parcelable {
    public static final Parcelable.Creator<DataPointEnvelope> CREATOR = new Parcelable.Creator<DataPointEnvelope>() { // from class: com.amazon.minerva.client.thirdparty.metric.DataPointEnvelope.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataPointEnvelope createFromParcel(Parcel parcel) {
            return new DataPointEnvelope(new DataPoint(parcel.readString(), ValueType.fromInt(parcel.readInt()), parcel.readString()));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataPointEnvelope[] newArray(int i) {
            return new DataPointEnvelope[i];
        }
    };
    private DataPoint mDataPoint;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 1;
    }

    DataPointEnvelope(DataPoint dataPoint) {
        this.mDataPoint = dataPoint;
    }

    public static List<DataPointEnvelope> convertToEnvelopes(List<DataPoint> list) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new DataPointEnvelope(list.get(i)));
        }
        return arrayList;
    }

    public static List<DataPoint> convertFromEnvelopes(List<DataPointEnvelope> list) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(list.get(i).toDataPoint());
        }
        return arrayList;
    }

    public DataPoint toDataPoint() {
        DataPoint dataPoint = this.mDataPoint;
        this.mDataPoint = null;
        return dataPoint;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDataPoint.getName());
        parcel.writeInt(this.mDataPoint.getType().ordinal());
        parcel.writeString(this.mDataPoint.getValue());
    }
}

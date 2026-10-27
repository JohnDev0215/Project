package com.byd.music.protocol.model.base;

import android.os.Parcel;
import android.os.Parcelable;
import com.byd.music.protocol.model.base.ProtocolBaseModel;

/* JADX INFO: loaded from: classes5.dex */
public class ProtocolModel<T extends ProtocolBaseModel> implements Parcelable {
    public static final Parcelable.Creator<ProtocolModel> CREATOR = new Parcelable.Creator<ProtocolModel>() { // from class: com.byd.music.protocol.model.base.ProtocolModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProtocolModel createFromParcel(Parcel parcel) {
            return new ProtocolModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProtocolModel[] newArray(int i) {
            return new ProtocolModel[i];
        }
    };
    private T protocolModel;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ProtocolModel() {
    }

    public T getProtocolModel() {
        return this.protocolModel;
    }

    public void setProtocolModel(T t) {
        this.protocolModel = t;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.protocolModel.getClass().getName());
        parcel.writeParcelable(this.protocolModel, i);
    }

    public ProtocolModel(Parcel parcel) {
    }
}

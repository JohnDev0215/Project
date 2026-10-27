package com.byd.vrdownload.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LanguageOTAResult implements Parcelable {
    public static final Parcelable.Creator<LanguageOTAResult> CREATOR = new Parcelable.Creator<LanguageOTAResult>() { // from class: com.byd.vrdownload.model.LanguageOTAResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LanguageOTAResult createFromParcel(Parcel parcel) {
            return new LanguageOTAResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LanguageOTAResult[] newArray(int i) {
            return new LanguageOTAResult[i];
        }
    };
    Exception exception;
    List<LanguageUpdate> list;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private LanguageOTAResult(Result<List<LanguageUpdate>> result) {
        if (result.isSuccess()) {
            this.list = result.getOrNull();
        } else {
            this.exception = result.exceptionOrNull();
        }
    }

    public List<LanguageUpdate> getList() {
        return this.list;
    }

    public void setList(List<LanguageUpdate> list) {
        this.list = list;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setException(Exception exc) {
        this.exception = exc;
    }

    public boolean isSuccess() {
        return getList() != null;
    }

    public static LanguageOTAResult success(List<LanguageUpdate> list) {
        return new LanguageOTAResult(list);
    }

    public static LanguageOTAResult fail(Exception exc) {
        return new LanguageOTAResult(exc);
    }

    protected LanguageOTAResult(Parcel parcel) {
        this.list = parcel.createTypedArrayList(LanguageUpdate.CREATOR);
        this.exception = (Exception) parcel.readSerializable();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.list);
        parcel.writeSerializable(this.exception);
    }

    public LanguageOTAResult(List<LanguageUpdate> list) {
        this.list = list;
    }

    public LanguageOTAResult(Exception exc) {
        this.exception = exc;
    }
}

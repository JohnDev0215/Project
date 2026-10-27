package com.byd.vrdownload.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadTask implements IDownloadTask, Parcelable {
    public static final Parcelable.Creator<DownloadTask> CREATOR = new Parcelable.Creator<DownloadTask>() { // from class: com.byd.vrdownload.model.DownloadTask.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadTask createFromParcel(Parcel parcel) {
            return new DownloadTask(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadTask[] newArray(int i) {
            return new DownloadTask[i];
        }
    };
    String fileName;
    int id;
    LanguageUpdate languageUpdate;
    String path;
    boolean reDownload;
    long sofar;
    byte status;
    long total;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DownloadTask() {
    }

    protected DownloadTask(Parcel parcel) {
        this.reDownload = parcel.readByte() != 0;
        this.id = parcel.readInt();
        this.sofar = parcel.readLong();
        this.total = parcel.readLong();
        this.status = parcel.readByte();
        this.fileName = parcel.readString();
        this.languageUpdate = (LanguageUpdate) parcel.readParcelable(LanguageUpdate.class.getClassLoader());
        this.path = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.reDownload ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.id);
        parcel.writeLong(this.sofar);
        parcel.writeLong(this.total);
        parcel.writeByte(this.status);
        parcel.writeString(this.fileName);
        parcel.writeParcelable(this.languageUpdate, i);
        parcel.writeString(this.path);
    }

    public void setSofar(long j) {
        this.sofar = j;
    }

    public void setTotal(long j) {
        this.total = j;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setStatus(byte b) {
        this.status = b;
    }

    public void setReDownload(boolean z) {
        this.reDownload = z;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    @Override // com.byd.vrdownload.model.IDownloadTask
    public int getId() {
        return this.id;
    }

    @Override // com.byd.vrdownload.model.IDownloadTask
    public byte getStatus() {
        return this.status;
    }

    @Override // com.byd.vrdownload.model.IDownloadTask
    public boolean isReDownload() {
        return this.reDownload;
    }

    @Override // com.byd.vrdownload.model.IDownloadTask
    public long getSofarBytes() {
        return this.sofar;
    }

    @Override // com.byd.vrdownload.model.IDownloadTask
    public long getTotalBytes() {
        return this.total;
    }

    @Override // com.byd.vrdownload.model.IDownloadTask
    public String getFileName() {
        return this.fileName;
    }

    public long getSofar() {
        return this.sofar;
    }

    public long getTotal() {
        return this.total;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public LanguageUpdate getLanguageUpdate() {
        return this.languageUpdate;
    }

    public void setLanguageUpdate(LanguageUpdate languageUpdate) {
        this.languageUpdate = languageUpdate;
    }

    public String toString() {
        return "DownloadTask{sofar=" + this.sofar + ", total=" + this.total + ", id=" + this.id + ", status=" + ((int) this.status) + ", reDownload=" + this.reDownload + ", fileName='" + this.fileName + "', path='" + this.path + "', languageUpdate=" + this.languageUpdate + '}';
    }
}

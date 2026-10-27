package net.sf.sevenzipjbinding;

/* JADX INFO: loaded from: classes3.dex */
public interface IArchiveOpenVolumeCallback {
    Object getProperty(PropID propID) throws SevenZipException;

    IInStream getStream(String str) throws SevenZipException;
}

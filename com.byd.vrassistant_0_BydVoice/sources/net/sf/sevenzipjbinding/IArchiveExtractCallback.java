package net.sf.sevenzipjbinding;

/* JADX INFO: loaded from: classes3.dex */
public interface IArchiveExtractCallback extends IProgress {
    ISequentialOutStream getStream(int i, ExtractAskMode extractAskMode) throws SevenZipException;

    void prepareOperation(ExtractAskMode extractAskMode) throws SevenZipException;

    void setOperationResult(ExtractOperationResult extractOperationResult) throws SevenZipException;
}

package net.sf.sevenzipjbinding;

/* JADX INFO: loaded from: classes3.dex */
public interface IOutCreateArchiveZip extends IOutCreateArchive<IOutItemZip>, IOutFeatureSetLevel {
    void createArchive(IOutStream iOutStream, int i, IOutCreateCallback<? extends IOutItemZip> iOutCreateCallback) throws SevenZipException;
}

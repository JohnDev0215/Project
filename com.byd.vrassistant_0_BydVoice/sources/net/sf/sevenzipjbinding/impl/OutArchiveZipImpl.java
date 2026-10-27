package net.sf.sevenzipjbinding.impl;

import net.sf.sevenzipjbinding.IOutCreateArchiveZip;
import net.sf.sevenzipjbinding.IOutCreateCallback;
import net.sf.sevenzipjbinding.IOutItemZip;
import net.sf.sevenzipjbinding.IOutStream;
import net.sf.sevenzipjbinding.IOutUpdateArchiveZip;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZipException;

/* JADX INFO: loaded from: classes3.dex */
public class OutArchiveZipImpl extends OutArchiveImpl<IOutItemZip> implements IOutCreateArchiveZip, IOutUpdateArchiveZip {
    @Override // net.sf.sevenzipjbinding.IOutFeatureSetLevel
    public void setLevel(int i) {
        featureSetLevel(i);
    }

    @Override // net.sf.sevenzipjbinding.IOutCreateArchiveZip
    public void createArchive(IOutStream iOutStream, int i, IOutCreateCallback<? extends IOutItemZip> iOutCreateCallback) throws SevenZipException {
        createArchive((ISequentialOutStream) iOutStream, i, (IOutCreateCallback) iOutCreateCallback);
    }
}

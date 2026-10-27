package net.sf.sevenzipjbinding.simple.impl;

import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleInArchiveImpl implements ISimpleInArchive {
    private final IInArchive sevenZipInArchive;
    private boolean wasClosed = false;

    public SimpleInArchiveImpl(IInArchive iInArchive) {
        this.sevenZipInArchive = iInArchive;
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchive
    public void close() throws SevenZipException {
        this.sevenZipInArchive.close();
        this.wasClosed = true;
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchive
    public ISimpleInArchiveItem[] getArchiveItems() throws SevenZipException {
        int numberOfItems = getNumberOfItems();
        ISimpleInArchiveItem[] iSimpleInArchiveItemArr = new ISimpleInArchiveItem[numberOfItems];
        for (int i = 0; i < numberOfItems; i++) {
            iSimpleInArchiveItemArr[i] = new SimpleInArchiveItemImpl(this, i);
        }
        return iSimpleInArchiveItemArr;
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchive
    public int getNumberOfItems() throws SevenZipException {
        return testAndGetSafeSevenZipInArchive().getNumberOfItems();
    }

    public IInArchive testAndGetSafeSevenZipInArchive() throws SevenZipException {
        if (this.wasClosed) {
            throw new SevenZipException("Archive was closed");
        }
        return this.sevenZipInArchive;
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchive
    public ISimpleInArchiveItem getArchiveItem(int i) throws SevenZipException {
        if (i < 0 || i >= this.sevenZipInArchive.getNumberOfItems()) {
            throw new SevenZipException("Index " + i + " is out of range. Number of items in archive: " + this.sevenZipInArchive.getNumberOfItems());
        }
        return new SimpleInArchiveItemImpl(this, i);
    }
}

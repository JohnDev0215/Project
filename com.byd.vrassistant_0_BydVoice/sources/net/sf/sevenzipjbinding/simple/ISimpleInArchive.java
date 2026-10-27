package net.sf.sevenzipjbinding.simple;

import net.sf.sevenzipjbinding.SevenZipException;

/* JADX INFO: loaded from: classes3.dex */
public interface ISimpleInArchive {
    void close() throws SevenZipException;

    ISimpleInArchiveItem getArchiveItem(int i) throws SevenZipException;

    ISimpleInArchiveItem[] getArchiveItems() throws SevenZipException;

    int getNumberOfItems() throws SevenZipException;
}

package net.sf.sevenzipjbinding.impl;

import java.util.Date;
import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.IOutArchive;
import net.sf.sevenzipjbinding.IOutItem7z;
import net.sf.sevenzipjbinding.IOutItemBZip2;
import net.sf.sevenzipjbinding.IOutItemBase;
import net.sf.sevenzipjbinding.IOutItemGZip;
import net.sf.sevenzipjbinding.IOutItemTar;
import net.sf.sevenzipjbinding.IOutItemZip;
import net.sf.sevenzipjbinding.PropID;
import net.sf.sevenzipjbinding.SevenZipException;

/* JADX INFO: loaded from: classes3.dex */
public class OutItemFactory<T extends IOutItemBase> {
    private static final Long ZERO = 0L;
    private int index;
    private IOutArchive<?> outArchive;

    private void fillDefaultValuesBZip2(IOutItemBZip2 iOutItemBZip2) {
    }

    private void fillDefaultValuesGZip(IOutItemGZip iOutItemGZip) {
    }

    OutItemFactory(IOutArchive<?> iOutArchive, int i) {
        this.outArchive = iOutArchive;
        this.index = i;
    }

    public T createOutItem() {
        return createOutItemIntern();
    }

    private OutItem createOutItemIntern() {
        OutItem outItem = new OutItem(this.outArchive, this.index);
        fillDefaultValues(outItem);
        return outItem;
    }

    private void fillDefaultValues(OutItem outItem) {
        if (this.outArchive.getConnectedInArchive() != null) {
            outItem.setUpdateOldArchiveItemIndex(-1);
            outItem.setUpdateIsNewData(Boolean.TRUE);
            outItem.setUpdateIsNewProperties(Boolean.TRUE);
        }
        outItem.setDataSize(ZERO);
        outItem.setPropertyLastModificationTime(new Date());
        int i = AnonymousClass1.$SwitchMap$net$sf$sevenzipjbinding$ArchiveFormat[outItem.getArchiveFormat().ordinal()];
        if (i == 1) {
            fillDefaultValues7z(outItem);
            return;
        }
        if (i == 2) {
            fillDefaultValuesZip(outItem);
            return;
        }
        if (i == 3) {
            fillDefaultValuesBZip2(outItem);
        } else if (i == 4) {
            fillDefaultValuesGZip(outItem);
        } else {
            if (i == 5) {
                fillDefaultValuesTar(outItem);
                return;
            }
            throw new RuntimeException("No default values strategy for the archive format '" + outItem.getArchiveFormat() + "'");
        }
    }

    /* JADX INFO: renamed from: net.sf.sevenzipjbinding.impl.OutItemFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$sf$sevenzipjbinding$ArchiveFormat;

        static {
            int[] iArr = new int[ArchiveFormat.values().length];
            $SwitchMap$net$sf$sevenzipjbinding$ArchiveFormat = iArr;
            try {
                iArr[ArchiveFormat.SEVEN_ZIP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$sf$sevenzipjbinding$ArchiveFormat[ArchiveFormat.ZIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$sf$sevenzipjbinding$ArchiveFormat[ArchiveFormat.BZIP2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$net$sf$sevenzipjbinding$ArchiveFormat[ArchiveFormat.GZIP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$net$sf$sevenzipjbinding$ArchiveFormat[ArchiveFormat.TAR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private void fillDefaultValues7z(IOutItem7z iOutItem7z) {
        iOutItem7z.setPropertyIsAnti(Boolean.FALSE);
        iOutItem7z.setPropertyIsDir(Boolean.FALSE);
        iOutItem7z.setPropertyAttributes(0);
    }

    private void fillDefaultValuesZip(IOutItemZip iOutItemZip) {
        iOutItemZip.setPropertyIsDir(Boolean.FALSE);
        iOutItemZip.setPropertyAttributes(0);
    }

    private void fillDefaultValuesTar(IOutItemTar iOutItemTar) {
        iOutItemTar.setPropertyIsDir(Boolean.FALSE);
    }

    public T createOutItem(int i) throws SevenZipException {
        if (this.outArchive.getConnectedInArchive() == null) {
            throw new RuntimeException("Not an update operation");
        }
        OutItem outItemCreateOutItemIntern = createOutItemIntern();
        outItemCreateOutItemIntern.setUpdateOldArchiveItemIndex(Integer.valueOf(i));
        outItemCreateOutItemIntern.setUpdateIsNewData(Boolean.FALSE);
        outItemCreateOutItemIntern.setUpdateIsNewProperties(Boolean.FALSE);
        return outItemCreateOutItemIntern;
    }

    public T createOutItemAndCloneProperties(int i) throws SevenZipException {
        IInArchive connectedInArchive = this.outArchive.getConnectedInArchive();
        if (connectedInArchive == null) {
            throw new RuntimeException("Not an update operation");
        }
        OutItem outItemCreateOutItemIntern = createOutItemIntern();
        outItemCreateOutItemIntern.setUpdateOldArchiveItemIndex(Integer.valueOf(i));
        outItemCreateOutItemIntern.setUpdateIsNewData(Boolean.FALSE);
        outItemCreateOutItemIntern.setUpdateIsNewProperties(Boolean.TRUE);
        outItemCreateOutItemIntern.setPropertyPath((String) connectedInArchive.getProperty(i, PropID.PATH));
        outItemCreateOutItemIntern.setPropertyAttributes((Integer) connectedInArchive.getProperty(i, PropID.ATTRIBUTES));
        outItemCreateOutItemIntern.setPropertyPosixAttributes((Integer) connectedInArchive.getProperty(i, PropID.POSIX_ATTRIB));
        outItemCreateOutItemIntern.setPropertyUser((String) connectedInArchive.getProperty(i, PropID.USER));
        outItemCreateOutItemIntern.setPropertyGroup((String) connectedInArchive.getProperty(i, PropID.GROUP));
        outItemCreateOutItemIntern.setPropertyCreationTime((Date) connectedInArchive.getProperty(i, PropID.CREATION_TIME));
        outItemCreateOutItemIntern.setPropertyLastModificationTime((Date) connectedInArchive.getProperty(i, PropID.LAST_MODIFICATION_TIME));
        outItemCreateOutItemIntern.setPropertyLastAccessTime((Date) connectedInArchive.getProperty(i, PropID.LAST_ACCESS_TIME));
        outItemCreateOutItemIntern.setPropertyIsAnti((Boolean) connectedInArchive.getProperty(i, PropID.IS_ANTI));
        outItemCreateOutItemIntern.setPropertyIsDir((Boolean) connectedInArchive.getProperty(i, PropID.IS_FOLDER));
        return outItemCreateOutItemIntern;
    }
}

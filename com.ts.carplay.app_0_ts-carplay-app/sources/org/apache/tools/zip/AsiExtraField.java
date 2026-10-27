package org.apache.tools.zip;

import java.util.zip.CRC32;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes.dex */
public class AsiExtraField implements ZipExtraField, UnixStat, Cloneable {
    private static final ZipShort HEADER_ID = new ZipShort(30062);
    private int mode = 0;
    private int uid = 0;
    private int gid = 0;
    private String link = "";
    private boolean dirFlag = false;
    private CRC32 crc = new CRC32();

    @Override // org.apache.tools.zip.ZipExtraField
    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    @Override // org.apache.tools.zip.ZipExtraField
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(getLinkedFile().getBytes().length + 14);
    }

    @Override // org.apache.tools.zip.ZipExtraField
    public ZipShort getCentralDirectoryLength() {
        return getLocalFileDataLength();
    }

    @Override // org.apache.tools.zip.ZipExtraField
    public byte[] getLocalFileDataData() {
        byte[] data = new byte[getLocalFileDataLength().getValue() - 4];
        System.arraycopy(new ZipShort(getMode()).getBytes(), 0, data, 0, 2);
        byte[] linkArray = getLinkedFile().getBytes();
        System.arraycopy(new ZipLong(linkArray.length).getBytes(), 0, data, 2, 4);
        System.arraycopy(new ZipShort(getUserId()).getBytes(), 0, data, 6, 2);
        System.arraycopy(new ZipShort(getGroupId()).getBytes(), 0, data, 8, 2);
        System.arraycopy(linkArray, 0, data, 10, linkArray.length);
        this.crc.reset();
        this.crc.update(data);
        long checksum = this.crc.getValue();
        byte[] result = new byte[data.length + 4];
        System.arraycopy(new ZipLong(checksum).getBytes(), 0, result, 0, 4);
        System.arraycopy(data, 0, result, 4, data.length);
        return result;
    }

    @Override // org.apache.tools.zip.ZipExtraField
    public byte[] getCentralDirectoryData() {
        return getLocalFileDataData();
    }

    public void setUserId(int uid) {
        this.uid = uid;
    }

    public int getUserId() {
        return this.uid;
    }

    public void setGroupId(int gid) {
        this.gid = gid;
    }

    public int getGroupId() {
        return this.gid;
    }

    public void setLinkedFile(String name) {
        this.link = name;
        this.mode = getMode(this.mode);
    }

    public String getLinkedFile() {
        return this.link;
    }

    public boolean isLink() {
        return getLinkedFile().length() != 0;
    }

    public void setMode(int mode) {
        this.mode = getMode(mode);
    }

    public int getMode() {
        return this.mode;
    }

    public void setDirectory(boolean dirFlag) {
        this.dirFlag = dirFlag;
        this.mode = getMode(this.mode);
    }

    public boolean isDirectory() {
        return this.dirFlag && !isLink();
    }

    @Override // org.apache.tools.zip.ZipExtraField
    public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
        long givenChecksum = new ZipLong(data, offset).getValue();
        byte[] tmp = new byte[length - 4];
        System.arraycopy(data, offset + 4, tmp, 0, length - 4);
        this.crc.reset();
        this.crc.update(tmp);
        long realChecksum = this.crc.getValue();
        if (givenChecksum != realChecksum) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("bad CRC checksum ");
            stringBuffer.append(Long.toHexString(givenChecksum));
            stringBuffer.append(" instead of ");
            stringBuffer.append(Long.toHexString(realChecksum));
            throw new ZipException(stringBuffer.toString());
        }
        int newMode = new ZipShort(tmp, 0).getValue();
        byte[] linkArray = new byte[(int) new ZipLong(tmp, 2).getValue()];
        this.uid = new ZipShort(tmp, 6).getValue();
        this.gid = new ZipShort(tmp, 8).getValue();
        if (linkArray.length == 0) {
            this.link = "";
        } else {
            System.arraycopy(tmp, 10, linkArray, 0, linkArray.length);
            this.link = new String(linkArray);
        }
        setDirectory((newMode & UnixStat.DIR_FLAG) != 0);
        setMode(newMode);
    }

    protected int getMode(int mode) {
        int type = UnixStat.FILE_FLAG;
        if (isLink()) {
            type = UnixStat.LINK_FLAG;
        } else if (isDirectory()) {
            type = UnixStat.DIR_FLAG;
        }
        return (mode & 4095) | type;
    }
}

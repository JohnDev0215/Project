package org.apache.tools.tar;

import java.io.File;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class TarEntry implements TarConstants {
    public static final int DEFAULT_DIR_MODE = 16877;
    public static final int DEFAULT_FILE_MODE = 33188;
    public static final int MAX_NAMELEN = 31;
    public static final int MILLIS_PER_SECOND = 1000;
    private int checkSum;
    private int devMajor;
    private int devMinor;
    private File file;
    private int groupId;
    private StringBuffer groupName;
    private byte linkFlag;
    private StringBuffer linkName;
    private StringBuffer magic;
    private long modTime;
    private int mode;
    private StringBuffer name;
    private long size;
    private int userId;
    private StringBuffer userName;

    private TarEntry() {
        this.magic = new StringBuffer(TarConstants.TMAGIC);
        this.name = new StringBuffer();
        this.linkName = new StringBuffer();
        String user = System.getProperty("user.name", "");
        user = user.length() > 31 ? user.substring(0, 31) : user;
        this.userId = 0;
        this.groupId = 0;
        this.userName = new StringBuffer(user);
        this.groupName = new StringBuffer("");
        this.file = null;
    }

    public TarEntry(String name) {
        this();
        boolean isDir = name.endsWith("/");
        this.devMajor = 0;
        this.devMinor = 0;
        this.name = new StringBuffer(name);
        this.mode = isDir ? 16877 : 33188;
        this.linkFlag = isDir ? TarConstants.LF_DIR : TarConstants.LF_NORMAL;
        this.userId = 0;
        this.groupId = 0;
        this.size = 0L;
        this.checkSum = 0;
        this.modTime = new Date().getTime() / 1000;
        this.linkName = new StringBuffer("");
        this.userName = new StringBuffer("");
        this.groupName = new StringBuffer("");
        this.devMajor = 0;
        this.devMinor = 0;
    }

    public TarEntry(String name, byte linkFlag) {
        this(name);
        this.linkFlag = linkFlag;
    }

    public TarEntry(File file) {
        int colon;
        this();
        this.file = file;
        String name = file.getPath();
        String osname = System.getProperty("os.name").toLowerCase(Locale.US);
        if (osname != null) {
            if (osname.startsWith("windows")) {
                if (name.length() > 2) {
                    char ch1 = name.charAt(0);
                    char ch2 = name.charAt(1);
                    if (ch2 == ':' && ((ch1 >= 'a' && ch1 <= 'z') || (ch1 >= 'A' && ch1 <= 'Z'))) {
                        name = name.substring(2);
                    }
                }
            } else if (osname.indexOf("netware") > -1 && (colon = name.indexOf(58)) != -1) {
                name = name.substring(colon + 1);
            }
        }
        String name2 = name.replace(File.separatorChar, '/');
        while (name2.startsWith("/")) {
            name2 = name2.substring(1);
        }
        this.linkName = new StringBuffer("");
        this.name = new StringBuffer(name2);
        if (file.isDirectory()) {
            this.mode = 16877;
            this.linkFlag = TarConstants.LF_DIR;
            StringBuffer stringBuffer = this.name;
            if (stringBuffer.charAt(stringBuffer.length() - 1) != '/') {
                this.name.append("/");
            }
        } else {
            this.mode = 33188;
            this.linkFlag = TarConstants.LF_NORMAL;
        }
        this.size = file.length();
        this.modTime = file.lastModified() / 1000;
        this.checkSum = 0;
        this.devMajor = 0;
        this.devMinor = 0;
    }

    public TarEntry(byte[] headerBuf) {
        this();
        parseTarHeader(headerBuf);
    }

    public boolean equals(TarEntry it) {
        return getName().equals(it.getName());
    }

    public boolean equals(Object it) {
        if (it == null || getClass() != it.getClass()) {
            return false;
        }
        return equals((TarEntry) it);
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean isDescendent(TarEntry desc) {
        return desc.getName().startsWith(getName());
    }

    public String getName() {
        return this.name.toString();
    }

    public void setName(String name) {
        this.name = new StringBuffer(name);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getLinkName() {
        return this.linkName.toString();
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getUserName() {
        return this.userName.toString();
    }

    public void setUserName(String userName) {
        this.userName = new StringBuffer(userName);
    }

    public String getGroupName() {
        return this.groupName.toString();
    }

    public void setGroupName(String groupName) {
        this.groupName = new StringBuffer(groupName);
    }

    public void setIds(int userId, int groupId) {
        setUserId(userId);
        setGroupId(groupId);
    }

    public void setNames(String userName, String groupName) {
        setUserName(userName);
        setGroupName(groupName);
    }

    public void setModTime(long time) {
        this.modTime = time / 1000;
    }

    public void setModTime(Date time) {
        this.modTime = time.getTime() / 1000;
    }

    public Date getModTime() {
        return new Date(this.modTime * 1000);
    }

    public File getFile() {
        return this.file;
    }

    public int getMode() {
        return this.mode;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isGNULongNameEntry() {
        return this.linkFlag == 76 && this.name.toString().equals(TarConstants.GNU_LONGLINK);
    }

    public boolean isDirectory() {
        File file = this.file;
        if (file != null) {
            return file.isDirectory();
        }
        return this.linkFlag == 53 || getName().endsWith("/");
    }

    public TarEntry[] getDirectoryEntries() {
        File file = this.file;
        if (file == null || !file.isDirectory()) {
            return new TarEntry[0];
        }
        String[] list = this.file.list();
        TarEntry[] result = new TarEntry[list.length];
        for (int i = 0; i < list.length; i++) {
            result[i] = new TarEntry(new File(this.file, list[i]));
        }
        return result;
    }

    public void writeEntryHeader(byte[] outbuf) {
        int offset = TarUtils.getLongOctalBytes(this.modTime, outbuf, TarUtils.getLongOctalBytes(this.size, outbuf, TarUtils.getOctalBytes(this.groupId, outbuf, TarUtils.getOctalBytes(this.userId, outbuf, TarUtils.getOctalBytes(this.mode, outbuf, TarUtils.getNameBytes(this.name, outbuf, 0, 100), 8), 8), 8), 12), 12);
        int c = 0;
        while (c < 8) {
            outbuf[offset] = 32;
            c++;
            offset++;
        }
        outbuf[offset] = this.linkFlag;
        for (int offset2 = TarUtils.getOctalBytes(this.devMinor, outbuf, TarUtils.getOctalBytes(this.devMajor, outbuf, TarUtils.getNameBytes(this.groupName, outbuf, TarUtils.getNameBytes(this.userName, outbuf, TarUtils.getNameBytes(this.magic, outbuf, TarUtils.getNameBytes(this.linkName, outbuf, offset + 1, 100), 8), 32), 32), 8), 8); offset2 < outbuf.length; offset2++) {
            outbuf[offset2] = 0;
        }
        long checkSum = TarUtils.computeCheckSum(outbuf);
        TarUtils.getCheckSumOctalBytes(checkSum, outbuf, offset, 8);
    }

    public void parseTarHeader(byte[] header) {
        this.name = TarUtils.parseName(header, 0, 100);
        int offset = 0 + 100;
        this.mode = (int) TarUtils.parseOctal(header, offset, 8);
        int offset2 = offset + 8;
        this.userId = (int) TarUtils.parseOctal(header, offset2, 8);
        int offset3 = offset2 + 8;
        this.groupId = (int) TarUtils.parseOctal(header, offset3, 8);
        int offset4 = offset3 + 8;
        this.size = TarUtils.parseOctal(header, offset4, 12);
        int offset5 = offset4 + 12;
        this.modTime = TarUtils.parseOctal(header, offset5, 12);
        int offset6 = offset5 + 12;
        this.checkSum = (int) TarUtils.parseOctal(header, offset6, 8);
        int offset7 = offset6 + 8;
        int offset8 = offset7 + 1;
        this.linkFlag = header[offset7];
        this.linkName = TarUtils.parseName(header, offset8, 100);
        int offset9 = offset8 + 100;
        this.magic = TarUtils.parseName(header, offset9, 8);
        int offset10 = offset9 + 8;
        this.userName = TarUtils.parseName(header, offset10, 32);
        int offset11 = offset10 + 32;
        this.groupName = TarUtils.parseName(header, offset11, 32);
        int offset12 = offset11 + 32;
        this.devMajor = (int) TarUtils.parseOctal(header, offset12, 8);
        this.devMinor = (int) TarUtils.parseOctal(header, offset12 + 8, 8);
    }
}

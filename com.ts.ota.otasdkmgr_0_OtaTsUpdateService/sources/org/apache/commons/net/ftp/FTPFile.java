package org.apache.commons.net.ftp;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class FTPFile implements Serializable {
    public static final int DIRECTORY_TYPE = 1;
    public static final int EXECUTE_PERMISSION = 2;
    public static final int FILE_TYPE = 0;
    public static final int GROUP_ACCESS = 1;
    public static final int READ_PERMISSION = 0;
    public static final int SYMBOLIC_LINK_TYPE = 2;
    public static final int UNKNOWN_TYPE = 3;
    public static final int USER_ACCESS = 0;
    public static final int WORLD_ACCESS = 2;
    public static final int WRITE_PERMISSION = 1;
    private static final long serialVersionUID = 9010790363003271996L;
    private Calendar date;
    private String group;
    private int hardLinkCount;
    private String link;
    private String name;
    private final boolean[][] permissions;
    private String rawListing;
    private long size;
    private int type;
    private String user;

    public FTPFile() {
        this.type = 3;
        this.size = -1L;
        this.user = "";
        this.group = "";
        this.permissions = (boolean[][]) Array.newInstance((Class<?>) boolean.class, 3, 3);
    }

    FTPFile(String rawListing) {
        this.type = 3;
        this.size = -1L;
        this.user = "";
        this.group = "";
        this.permissions = (boolean[][]) null;
        this.rawListing = rawListing;
    }

    private char formatType() {
        int i = this.type;
        if (i == 0) {
            return '-';
        }
        if (i == 1) {
            return 'd';
        }
        if (i == 2) {
            return 'l';
        }
        return '?';
    }

    public String getGroup() {
        return this.group;
    }

    public int getHardLinkCount() {
        return this.hardLinkCount;
    }

    public String getLink() {
        return this.link;
    }

    public String getName() {
        return this.name;
    }

    public String getRawListing() {
        return this.rawListing;
    }

    public long getSize() {
        return this.size;
    }

    public Calendar getTimestamp() {
        return this.date;
    }

    public int getType() {
        return this.type;
    }

    public String getUser() {
        return this.user;
    }

    public boolean hasPermission(int access, int permission) {
        boolean[][] zArr = this.permissions;
        if (zArr == null) {
            return false;
        }
        return zArr[access][permission];
    }

    public boolean isDirectory() {
        return this.type == 1;
    }

    public boolean isFile() {
        return this.type == 0;
    }

    public boolean isSymbolicLink() {
        return this.type == 2;
    }

    public boolean isUnknown() {
        return this.type == 3;
    }

    public boolean isValid() {
        return this.permissions != null;
    }

    private String permissionToString(int access) {
        StringBuilder sb = new StringBuilder();
        if (hasPermission(access, 0)) {
            sb.append('r');
        } else {
            sb.append('-');
        }
        if (hasPermission(access, 1)) {
            sb.append('w');
        } else {
            sb.append('-');
        }
        if (hasPermission(access, 2)) {
            sb.append('x');
        } else {
            sb.append('-');
        }
        return sb.toString();
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setHardLinkCount(int links) {
        this.hardLinkCount = links;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPermission(int access, int permission, boolean value) {
        this.permissions[access][permission] = value;
    }

    public void setRawListing(String rawListing) {
        this.rawListing = rawListing;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setTimestamp(Calendar date) {
        this.date = date;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String toFormattedString() {
        return toFormattedString(null);
    }

    public String toFormattedString(String timezone) {
        if (!isValid()) {
            return "[Invalid: could not parse file entry]";
        }
        StringBuilder sb = new StringBuilder();
        Formatter fmt = new Formatter(sb);
        try {
            sb.append(formatType());
            sb.append(permissionToString(0));
            sb.append(permissionToString(1));
            sb.append(permissionToString(2));
            fmt.format(" %4d", Integer.valueOf(getHardLinkCount()));
            fmt.format(" %-8s %-8s", getUser(), getGroup());
            fmt.format(" %8d", Long.valueOf(getSize()));
            Calendar timestamp = getTimestamp();
            if (timestamp != null) {
                if (timezone != null) {
                    TimeZone newZone = TimeZone.getTimeZone(timezone);
                    if (!newZone.equals(timestamp.getTimeZone())) {
                        Date original = timestamp.getTime();
                        Calendar newStamp = Calendar.getInstance(newZone);
                        newStamp.setTime(original);
                        timestamp = newStamp;
                    }
                }
                fmt.format(" %1$tY-%1$tm-%1$td", timestamp);
                if (timestamp.isSet(11)) {
                    fmt.format(" %1$tH", timestamp);
                    if (timestamp.isSet(12)) {
                        fmt.format(":%1$tM", timestamp);
                        if (timestamp.isSet(13)) {
                            fmt.format(":%1$tS", timestamp);
                            if (timestamp.isSet(14)) {
                                fmt.format(".%1$tL", timestamp);
                            }
                        }
                    }
                    fmt.format(" %1$tZ", timestamp);
                }
            }
            sb.append(' ');
            sb.append(getName());
            fmt.close();
            return sb.toString();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    fmt.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public String toString() {
        return getRawListing();
    }
}

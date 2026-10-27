package org.apache.commons.net.ftp.parser;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileEntryParserImpl;
import org.apache.commons.net.nntp.NNTPReply;

/* JADX INFO: loaded from: classes.dex */
public class MLSxEntryParser extends FTPFileEntryParserImpl {
    private static final MLSxEntryParser INSTANCE = new MLSxEntryParser();
    private static final HashMap<String, Integer> TYPE_TO_INT;
    private static final int[] UNIX_GROUPS;
    private static final int[][] UNIX_PERMS;

    static {
        HashMap<String, Integer> map = new HashMap<>();
        TYPE_TO_INT = map;
        map.put("file", 0);
        TYPE_TO_INT.put("cdir", 1);
        TYPE_TO_INT.put("pdir", 1);
        TYPE_TO_INT.put("dir", 1);
        UNIX_GROUPS = new int[]{0, 1, 2};
        UNIX_PERMS = new int[][]{new int[0], new int[]{2}, new int[]{1}, new int[]{2, 1}, new int[]{0}, new int[]{0, 2}, new int[]{0, 1}, new int[]{0, 1, 2}};
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    public FTPFile parseFTPEntry(String entry) {
        String[] parts;
        int off;
        String[] parts2;
        String unixfact;
        FTPFile fTPFile = null;
        int i = 1;
        if (entry.startsWith(" ")) {
            if (entry.length() <= 1) {
                return null;
            }
            FTPFile file = new FTPFile();
            file.setRawListing(entry);
            file.setName(entry.substring(1));
            return file;
        }
        int i2 = 2;
        String[] parts3 = entry.split(" ", 2);
        if (parts3.length == 2 && !parts3[1].isEmpty()) {
            char c = 0;
            String factList = parts3[0];
            if (!factList.endsWith(";")) {
                return null;
            }
            FTPFile file2 = new FTPFile();
            file2.setRawListing(entry);
            file2.setName(parts3[1]);
            String[] facts = factList.split(";");
            boolean hasUnixMode = parts3[0].toLowerCase(Locale.ENGLISH).contains("unix.mode=");
            int length = facts.length;
            int i3 = 0;
            while (i3 < length) {
                String fact = facts[i3];
                String[] factparts = fact.split("=", -1);
                if (factparts.length != i2) {
                    return fTPFile;
                }
                String factname = factparts[c].toLowerCase(Locale.ENGLISH);
                String factvalue = factparts[i];
                if (factvalue.isEmpty()) {
                    parts = parts3;
                    off = i;
                } else {
                    String valueLowerCase = factvalue.toLowerCase(Locale.ENGLISH);
                    if ("size".equals(factname) || "sizd".equals(factname)) {
                        parts = parts3;
                        off = i;
                        file2.setSize(Long.parseLong(factvalue));
                    } else if ("modify".equals(factname)) {
                        Calendar parsed = parseGMTdateTime(factvalue);
                        if (parsed == null) {
                            return fTPFile;
                        }
                        file2.setTimestamp(parsed);
                        parts = parts3;
                        off = i;
                    } else {
                        if ("type".equals(factname)) {
                            Integer intType = TYPE_TO_INT.get(valueLowerCase);
                            if (intType == null) {
                                file2.setType(3);
                            } else {
                                file2.setType(intType.intValue());
                            }
                            parts = parts3;
                            off = i;
                        } else if (factname.startsWith("unix.")) {
                            String unixfact2 = factname.substring("unix.".length()).toLowerCase(Locale.ENGLISH);
                            if ("group".equals(unixfact2)) {
                                file2.setGroup(factvalue);
                                parts = parts3;
                                off = 1;
                            } else if ("owner".equals(unixfact2)) {
                                file2.setUser(factvalue);
                                parts = parts3;
                                off = 1;
                            } else if (!"mode".equals(unixfact2)) {
                                parts = parts3;
                                off = 1;
                            } else {
                                int off2 = factvalue.length() - 3;
                                int i4 = 0;
                                for (int i5 = 3; i4 < i5; i5 = 3) {
                                    int ch = factvalue.charAt(off2 + i4) - '0';
                                    if (ch >= 0) {
                                        parts2 = parts3;
                                        if (ch <= 7) {
                                            int[] iArr = UNIX_PERMS[ch];
                                            int ch2 = iArr.length;
                                            unixfact = unixfact2;
                                            int i6 = 0;
                                            while (i6 < ch2) {
                                                int i7 = ch2;
                                                int p = iArr[i6];
                                                file2.setPermission(UNIX_GROUPS[i4], p, true);
                                                i6++;
                                                ch2 = i7;
                                                off2 = off2;
                                                iArr = iArr;
                                            }
                                        } else {
                                            unixfact = unixfact2;
                                        }
                                    } else {
                                        parts2 = parts3;
                                        unixfact = unixfact2;
                                    }
                                    i4++;
                                    parts3 = parts2;
                                    unixfact2 = unixfact;
                                    off2 = off2;
                                }
                                parts = parts3;
                                off = 1;
                            }
                        } else {
                            parts = parts3;
                            off = i;
                            if (!hasUnixMode && "perm".equals(factname)) {
                                doUnixPerms(file2, valueLowerCase);
                            }
                        }
                    }
                }
                i3++;
                i = off;
                parts3 = parts;
                i2 = 2;
                fTPFile = null;
                c = 0;
            }
            return file2;
        }
        return null;
    }

    public static Calendar parseGMTdateTime(String timestamp) {
        SimpleDateFormat sdf;
        boolean hasMillis;
        if (timestamp.contains(".")) {
            sdf = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
            hasMillis = true;
        } else {
            sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            hasMillis = false;
        }
        TimeZone GMT = TimeZone.getTimeZone("GMT");
        sdf.setTimeZone(GMT);
        GregorianCalendar gc = new GregorianCalendar(GMT);
        ParsePosition pos = new ParsePosition(0);
        sdf.setLenient(false);
        Date parsed = sdf.parse(timestamp, pos);
        if (pos.getIndex() != timestamp.length()) {
            return null;
        }
        gc.setTime(parsed);
        if (!hasMillis) {
            gc.clear(14);
        }
        return gc;
    }

    private void doUnixPerms(FTPFile file, String valueLowerCase) {
        for (char c : valueLowerCase.toCharArray()) {
            if (c == 'a') {
                file.setPermission(0, 1, true);
            } else if (c == 'p') {
                file.setPermission(0, 1, true);
            } else if (c == 'r') {
                file.setPermission(0, 0, true);
            } else if (c == 'w') {
                file.setPermission(0, 1, true);
            } else if (c == 'l') {
                file.setPermission(0, 2, true);
            } else if (c != 'm') {
                switch (c) {
                    case 'c':
                        file.setPermission(0, 1, true);
                        break;
                    case NNTPReply.HELP_TEXT_FOLLOWS /* 100 */:
                        file.setPermission(0, 1, true);
                        break;
                    case 'e':
                        file.setPermission(0, 0, true);
                        break;
                }
            } else {
                file.setPermission(0, 1, true);
            }
        }
    }

    public static FTPFile parseEntry(String entry) {
        return INSTANCE.parseFTPEntry(entry);
    }

    public static MLSxEntryParser getInstance() {
        return INSTANCE;
    }
}

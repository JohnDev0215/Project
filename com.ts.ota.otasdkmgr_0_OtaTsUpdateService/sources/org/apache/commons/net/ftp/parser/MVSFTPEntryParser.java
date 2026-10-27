package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import java.util.List;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

/* JADX INFO: loaded from: classes.dex */
public class MVSFTPEntryParser extends ConfigurableFTPFileEntryParserImpl {
    static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd HH:mm";
    static final String FILE_LIST_REGEX = "\\S+\\s+\\S+\\s+\\S+\\s+\\S+\\s+(?:\\S+\\s+)?(?:F|FB|V|VB|U)\\s+\\S+\\s+\\S+\\s+(PS|PO|PO-E)\\s+(\\S+)\\s*";
    static final int FILE_LIST_TYPE = 0;
    static final String JES_LEVEL_1_LIST_REGEX = "(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*";
    static final int JES_LEVEL_1_LIST_TYPE = 3;
    static final String JES_LEVEL_2_LIST_REGEX = "(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+).*";
    static final int JES_LEVEL_2_LIST_TYPE = 4;
    static final String MEMBER_LIST_REGEX = "(\\S+)\\s+\\S+\\s+\\S+\\s+(\\S+)\\s+(\\S+)\\s+\\S+\\s+\\S+\\s+\\S+\\s+\\S+\\s*";
    static final int MEMBER_LIST_TYPE = 1;
    static final int UNIX_LIST_TYPE = 2;
    static final int UNKNOWN_LIST_TYPE = -1;
    private int isType;
    private UnixFTPEntryParser unixFTPEntryParser;

    public MVSFTPEntryParser() {
        super("");
        this.isType = -1;
        super.configure(null);
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    public FTPFile parseFTPEntry(String entry) {
        int i = this.isType;
        if (i == 0) {
            return parseFileList(entry);
        }
        if (i == 1) {
            return parseMemberList(entry);
        }
        if (i == 2) {
            return this.unixFTPEntryParser.parseFTPEntry(entry);
        }
        if (i == 3) {
            return parseJeslevel1List(entry);
        }
        if (i == 4) {
            return parseJeslevel2List(entry);
        }
        return null;
    }

    private FTPFile parseFileList(String entry) {
        if (!matches(entry)) {
            return null;
        }
        FTPFile file = new FTPFile();
        file.setRawListing(entry);
        String name = group(2);
        String dsorg = group(1);
        file.setName(name);
        if ("PS".equals(dsorg)) {
            file.setType(0);
        } else {
            if (!"PO".equals(dsorg) && !"PO-E".equals(dsorg)) {
                return null;
            }
            file.setType(1);
        }
        return file;
    }

    private FTPFile parseMemberList(String entry) {
        FTPFile file = new FTPFile();
        if (matches(entry)) {
            file.setRawListing(entry);
            String name = group(1);
            String datestr = group(2) + " " + group(3);
            file.setName(name);
            file.setType(0);
            try {
                file.setTimestamp(super.parseTimestamp(datestr));
            } catch (ParseException e) {
            }
            return file;
        }
        if (entry != null && !entry.trim().isEmpty()) {
            file.setRawListing(entry);
            String name2 = entry.split(" ")[0];
            file.setName(name2);
            file.setType(0);
            return file;
        }
        return null;
    }

    private FTPFile parseJeslevel1List(String entry) {
        if (matches(entry)) {
            FTPFile file = new FTPFile();
            if (group(3).equalsIgnoreCase("OUTPUT")) {
                file.setRawListing(entry);
                String name = group(2);
                file.setName(name);
                file.setType(0);
                return file;
            }
            return null;
        }
        return null;
    }

    private FTPFile parseJeslevel2List(String entry) {
        if (matches(entry)) {
            FTPFile file = new FTPFile();
            if (group(4).equalsIgnoreCase("OUTPUT")) {
                file.setRawListing(entry);
                String name = group(2);
                file.setName(name);
                file.setType(0);
                return file;
            }
            return null;
        }
        return null;
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParserImpl, org.apache.commons.net.ftp.FTPFileEntryParser
    public List<String> preParse(List<String> orig) {
        if (orig != null && !orig.isEmpty()) {
            String header = orig.get(0);
            if (header.indexOf("Volume") >= 0 && header.indexOf("Dsname") >= 0) {
                setType(0);
                super.setRegex(FILE_LIST_REGEX);
            } else if (header.indexOf("Name") >= 0 && header.indexOf("Id") >= 0) {
                setType(1);
                super.setRegex(MEMBER_LIST_REGEX);
            } else if (header.indexOf("total") == 0) {
                setType(2);
                this.unixFTPEntryParser = new UnixFTPEntryParser();
            } else if (header.indexOf("Spool Files") >= 30) {
                setType(3);
                super.setRegex(JES_LEVEL_1_LIST_REGEX);
            } else if (header.indexOf("JOBNAME") == 0 && header.indexOf("JOBID") > 8) {
                setType(4);
                super.setRegex(JES_LEVEL_2_LIST_REGEX);
            } else {
                setType(-1);
            }
            if (this.isType != 3) {
                orig.remove(0);
            }
        }
        return orig;
    }

    void setType(int type) {
        this.isType = type;
    }

    @Override // org.apache.commons.net.ftp.parser.ConfigurableFTPFileEntryParserImpl
    protected FTPClientConfig getDefaultConfiguration() {
        return new FTPClientConfig(FTPClientConfig.SYST_MVS, DEFAULT_DATE_FORMAT, null);
    }
}

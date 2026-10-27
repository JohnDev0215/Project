package org.apache.commons.net.ftp.parser;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileEntryParser;
import org.apache.commons.net.ftp.FTPFileEntryParserImpl;

/* JADX INFO: loaded from: classes.dex */
public class CompositeFileEntryParser extends FTPFileEntryParserImpl {
    private FTPFileEntryParser cachedFtpFileEntryParser = null;
    private final FTPFileEntryParser[] ftpFileEntryParsers;

    public CompositeFileEntryParser(FTPFileEntryParser[] ftpFileEntryParsers) {
        this.ftpFileEntryParsers = ftpFileEntryParsers;
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    public FTPFile parseFTPEntry(String listEntry) {
        FTPFileEntryParser fTPFileEntryParser = this.cachedFtpFileEntryParser;
        if (fTPFileEntryParser != null) {
            FTPFile matched = fTPFileEntryParser.parseFTPEntry(listEntry);
            if (matched != null) {
                return matched;
            }
            return null;
        }
        for (FTPFileEntryParser ftpFileEntryParser : this.ftpFileEntryParsers) {
            FTPFile matched2 = ftpFileEntryParser.parseFTPEntry(listEntry);
            if (matched2 != null) {
                this.cachedFtpFileEntryParser = ftpFileEntryParser;
                return matched2;
            }
        }
        return null;
    }
}

package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

/* JADX INFO: loaded from: classes.dex */
public class NTFTPEntryParser extends ConfigurableFTPFileEntryParserImpl {
    private static final String DEFAULT_DATE_FORMAT = "MM-dd-yy hh:mma";
    private static final String DEFAULT_DATE_FORMAT2 = "MM-dd-yy kk:mm";
    private static final String REGEX = "(\\S+)\\s+(\\S+)\\s+(?:(<DIR>)|([0-9]+))\\s+(\\S.*)";
    private final FTPTimestampParser timestampParser;

    public NTFTPEntryParser() {
        this(null);
    }

    public NTFTPEntryParser(FTPClientConfig config) {
        super(REGEX, 32);
        configure(config);
        FTPClientConfig config2 = new FTPClientConfig(FTPClientConfig.SYST_NT, DEFAULT_DATE_FORMAT2, null);
        config2.setDefaultDateFormatStr(DEFAULT_DATE_FORMAT2);
        FTPTimestampParserImpl fTPTimestampParserImpl = new FTPTimestampParserImpl();
        this.timestampParser = fTPTimestampParserImpl;
        fTPTimestampParserImpl.configure(config2);
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    public FTPFile parseFTPEntry(String entry) {
        FTPFile f = new FTPFile();
        f.setRawListing(entry);
        if (!matches(entry)) {
            return null;
        }
        String datestr = group(1) + " " + group(2);
        String dirString = group(3);
        String size = group(4);
        String name = group(5);
        try {
            f.setTimestamp(super.parseTimestamp(datestr));
        } catch (ParseException e) {
            try {
                f.setTimestamp(this.timestampParser.parseTimestamp(datestr));
            } catch (ParseException e2) {
            }
        }
        if (name == null || name.equals(".") || name.equals("..")) {
            return null;
        }
        f.setName(name);
        if ("<DIR>".equals(dirString)) {
            f.setType(1);
            f.setSize(0L);
        } else {
            f.setType(0);
            if (size != null) {
                f.setSize(Long.parseLong(size));
            }
        }
        return f;
    }

    @Override // org.apache.commons.net.ftp.parser.ConfigurableFTPFileEntryParserImpl
    public FTPClientConfig getDefaultConfiguration() {
        return new FTPClientConfig(FTPClientConfig.SYST_NT, DEFAULT_DATE_FORMAT, null);
    }
}

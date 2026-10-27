package org.apache.commons.net.ftp.parser;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.apache.commons.net.ftp.FTPClientConfig;

/* JADX INFO: loaded from: classes.dex */
public class VMSVersioningFTPEntryParser extends VMSFTPEntryParser {
    private static final String PRE_PARSE_REGEX = "(.*?);([0-9]+)\\s*.*";
    private final Pattern preparsePattern;

    public VMSVersioningFTPEntryParser() {
        this(null);
    }

    public VMSVersioningFTPEntryParser(FTPClientConfig config) {
        configure(config);
        try {
            this.preparsePattern = Pattern.compile(PRE_PARSE_REGEX);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Unparseable regex supplied:  (.*?);([0-9]+)\\s*.*");
        }
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParserImpl, org.apache.commons.net.ftp.FTPFileEntryParser
    public List<String> preParse(List<String> original) {
        HashMap<String, Integer> existingEntries = new HashMap<>();
        ListIterator<String> iter = original.listIterator();
        while (iter.hasNext()) {
            String entry = iter.next().trim();
            Matcher _preparse_matcher_ = this.preparsePattern.matcher(entry);
            if (_preparse_matcher_.matches()) {
                MatchResult result = _preparse_matcher_.toMatchResult();
                String name = result.group(1);
                String version = result.group(2);
                Integer nv = Integer.valueOf(version);
                Integer existing = existingEntries.get(name);
                if (existing != null && nv.intValue() < existing.intValue()) {
                    iter.remove();
                } else {
                    existingEntries.put(name, nv);
                }
            }
        }
        while (iter.hasPrevious()) {
            String entry2 = iter.previous().trim();
            Matcher _preparse_matcher_2 = this.preparsePattern.matcher(entry2);
            if (_preparse_matcher_2.matches()) {
                MatchResult result2 = _preparse_matcher_2.toMatchResult();
                String name2 = result2.group(1);
                String version2 = result2.group(2);
                Integer nv2 = Integer.valueOf(version2);
                Integer existing2 = existingEntries.get(name2);
                if (existing2 != null && nv2.intValue() < existing2.intValue()) {
                    iter.remove();
                }
            }
        }
        return original;
    }

    @Override // org.apache.commons.net.ftp.parser.VMSFTPEntryParser
    protected boolean isVersioning() {
        return true;
    }
}

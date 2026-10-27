package org.apache.commons.net.ftp.parser;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.apache.commons.net.ftp.FTPFileEntryParserImpl;

/* JADX INFO: loaded from: classes3.dex */
public abstract class RegexFTPFileEntryParserImpl extends FTPFileEntryParserImpl {
    private Pattern pattern = null;
    private MatchResult result = null;
    protected Matcher _matcher_ = null;

    public RegexFTPFileEntryParserImpl(String regex) {
        compileRegex(regex, 0);
    }

    public RegexFTPFileEntryParserImpl(String regex, int flags) {
        compileRegex(regex, flags);
    }

    public boolean matches(String s) {
        this.result = null;
        Matcher matcher = this.pattern.matcher(s);
        this._matcher_ = matcher;
        if (matcher.matches()) {
            this.result = this._matcher_.toMatchResult();
        }
        return this.result != null;
    }

    public int getGroupCnt() {
        MatchResult matchResult = this.result;
        if (matchResult == null) {
            return 0;
        }
        return matchResult.groupCount();
    }

    public String group(int matchnum) {
        MatchResult matchResult = this.result;
        if (matchResult == null) {
            return null;
        }
        return matchResult.group(matchnum);
    }

    public String getGroupsAsString() {
        StringBuilder b = new StringBuilder();
        for (int i = 1; i <= this.result.groupCount(); i++) {
            b.append(i);
            b.append(") ");
            b.append(this.result.group(i));
            b.append(System.getProperty("line.separator"));
        }
        return b.toString();
    }

    public boolean setRegex(String regex) {
        compileRegex(regex, 0);
        return true;
    }

    public boolean setRegex(String regex, int flags) {
        compileRegex(regex, flags);
        return true;
    }

    private void compileRegex(String regex, int flags) {
        try {
            this.pattern = Pattern.compile(regex, flags);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Unparseable regex supplied: " + regex);
        }
    }
}

package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import java.util.Vector;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.RegularExpression;
import org.apache.tools.ant.util.regexp.Regexp;

/* JADX INFO: loaded from: classes.dex */
public final class LineContainsRegExp extends BaseParamFilterReader implements ChainableReader {
    private static final String REGEXP_KEY = "regexp";
    private String line;
    private Vector regexps;

    public LineContainsRegExp() {
        this.regexps = new Vector();
        this.line = null;
    }

    public LineContainsRegExp(Reader in) {
        super(in);
        this.regexps = new Vector();
        this.line = null;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public final int read() throws IOException {
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        int ch = -1;
        String str = this.line;
        if (str != null) {
            ch = str.charAt(0);
            if (this.line.length() == 1) {
                this.line = null;
            } else {
                this.line = this.line.substring(1);
            }
        } else {
            this.line = readLine();
            int regexpsSize = this.regexps.size();
            while (this.line != null) {
                int i = 0;
                while (true) {
                    if (i >= regexpsSize) {
                        break;
                    }
                    RegularExpression regexp = (RegularExpression) this.regexps.elementAt(i);
                    Regexp re = regexp.getRegexp(getProject());
                    boolean matches = re.matches(this.line);
                    if (matches) {
                        i++;
                    } else {
                        this.line = null;
                        break;
                    }
                }
                if (this.line != null) {
                    break;
                }
                this.line = readLine();
            }
            if (this.line != null) {
                return read();
            }
        }
        return ch;
    }

    public final void addConfiguredRegexp(RegularExpression regExp) {
        this.regexps.addElement(regExp);
    }

    private void setRegexps(Vector regexps) {
        this.regexps = regexps;
    }

    private final Vector getRegexps() {
        return this.regexps;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader rdr) {
        LineContainsRegExp newFilter = new LineContainsRegExp(rdr);
        newFilter.setRegexps(getRegexps());
        newFilter.setInitialized(true);
        return newFilter;
    }

    private final void initialize() {
        Parameter[] params = getParameters();
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if ("regexp".equals(params[i].getType())) {
                    String pattern = params[i].getValue();
                    RegularExpression regexp = new RegularExpression();
                    regexp.setPattern(pattern);
                    this.regexps.addElement(regexp);
                }
            }
        }
    }
}

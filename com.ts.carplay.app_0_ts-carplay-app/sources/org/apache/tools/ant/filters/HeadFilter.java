package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.util.LineTokenizer;

/* JADX INFO: loaded from: classes.dex */
public final class HeadFilter extends BaseParamFilterReader implements ChainableReader {
    private static final int DEFAULT_NUM_LINES = 10;
    private static final String LINES_KEY = "lines";
    private static final String SKIP_KEY = "skip";
    private String line;
    private int linePos;
    private LineTokenizer lineTokenizer;
    private long lines;
    private long linesRead;
    private long skip;

    public HeadFilter() {
        this.linesRead = 0L;
        this.lines = 10L;
        this.skip = 0L;
        this.lineTokenizer = null;
        this.line = null;
        this.linePos = 0;
    }

    public HeadFilter(Reader in) {
        super(in);
        this.linesRead = 0L;
        this.lines = 10L;
        this.skip = 0L;
        this.lineTokenizer = null;
        this.line = null;
        this.linePos = 0;
        LineTokenizer lineTokenizer = new LineTokenizer();
        this.lineTokenizer = lineTokenizer;
        lineTokenizer.setIncludeDelims(true);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public final int read() throws IOException {
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        while (true) {
            String str = this.line;
            if (str == null || str.length() == 0) {
                String token = this.lineTokenizer.getToken(this.in);
                this.line = token;
                if (token == null) {
                    return -1;
                }
                this.line = headFilter(token);
                this.linePos = 0;
            } else {
                int ch = this.line.charAt(this.linePos);
                int i = this.linePos + 1;
                this.linePos = i;
                if (i == this.line.length()) {
                    this.line = null;
                }
                return ch;
            }
        }
    }

    public final void setLines(long lines) {
        this.lines = lines;
    }

    private final long getLines() {
        return this.lines;
    }

    public final void setSkip(long skip) {
        this.skip = skip;
    }

    private final long getSkip() {
        return this.skip;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader rdr) {
        HeadFilter newFilter = new HeadFilter(rdr);
        newFilter.setLines(getLines());
        newFilter.setSkip(getSkip());
        newFilter.setInitialized(true);
        return newFilter;
    }

    private final void initialize() {
        Parameter[] params = getParameters();
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (LINES_KEY.equals(params[i].getName())) {
                    this.lines = new Long(params[i].getValue()).longValue();
                } else if (SKIP_KEY.equals(params[i].getName())) {
                    this.skip = new Long(params[i].getValue()).longValue();
                }
            }
        }
    }

    private String headFilter(String line) {
        long j = this.linesRead + 1;
        this.linesRead = j;
        long j2 = this.skip;
        if (j2 > 0 && j - 1 < j2) {
            return null;
        }
        long j3 = this.lines;
        if (j3 <= 0 || this.linesRead <= j3 + this.skip) {
            return line;
        }
        return null;
    }
}

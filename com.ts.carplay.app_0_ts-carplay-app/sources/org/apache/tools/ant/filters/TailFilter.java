package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.util.LineTokenizer;

/* JADX INFO: loaded from: classes.dex */
public final class TailFilter extends BaseParamFilterReader implements ChainableReader {
    private static final int DEFAULT_NUM_LINES = 10;
    private static final String LINES_KEY = "lines";
    private static final String SKIP_KEY = "skip";
    private boolean completedReadAhead;
    private String line;
    private LinkedList lineList;
    private int linePos;
    private LineTokenizer lineTokenizer;
    private long lines;
    private long skip;

    public TailFilter() {
        this.lines = 10L;
        this.skip = 0L;
        this.completedReadAhead = false;
        this.lineTokenizer = null;
        this.line = null;
        this.linePos = 0;
        this.lineList = new LinkedList();
    }

    public TailFilter(Reader in) {
        super(in);
        this.lines = 10L;
        this.skip = 0L;
        this.completedReadAhead = false;
        this.lineTokenizer = null;
        this.line = null;
        this.linePos = 0;
        this.lineList = new LinkedList();
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
                String strTailFilter = tailFilter(token);
                this.line = strTailFilter;
                if (strTailFilter == null) {
                    return -1;
                }
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
        TailFilter newFilter = new TailFilter(rdr);
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
                    setLines(new Long(params[i].getValue()).longValue());
                } else if (SKIP_KEY.equals(params[i].getName())) {
                    this.skip = new Long(params[i].getValue()).longValue();
                }
            }
        }
    }

    private String tailFilter(String line) {
        if (!this.completedReadAhead) {
            if (line != null) {
                this.lineList.add(line);
                long j = this.lines;
                if (j == -1) {
                    if (this.lineList.size() > this.skip) {
                        return (String) this.lineList.removeFirst();
                    }
                    return "";
                }
                long j2 = this.skip;
                long linesToKeep = j + (j2 > 0 ? j2 : 0L);
                if (linesToKeep < this.lineList.size()) {
                    this.lineList.removeFirst();
                    return "";
                }
                return "";
            }
            this.completedReadAhead = true;
            if (this.skip > 0) {
                for (int i = 0; i < this.skip; i++) {
                    this.lineList.removeLast();
                }
            }
            if (this.lines > -1) {
                while (this.lineList.size() > this.lines) {
                    this.lineList.removeFirst();
                }
            }
        }
        if (this.lineList.size() > 0) {
            return (String) this.lineList.removeFirst();
        }
        return null;
    }
}

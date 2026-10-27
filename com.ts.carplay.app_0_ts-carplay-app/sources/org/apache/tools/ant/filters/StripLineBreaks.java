package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.types.Parameter;

/* JADX INFO: loaded from: classes.dex */
public final class StripLineBreaks extends BaseParamFilterReader implements ChainableReader {
    private static final String DEFAULT_LINE_BREAKS = "\r\n";
    private static final String LINE_BREAKS_KEY = "linebreaks";
    private String lineBreaks;

    public StripLineBreaks() {
        this.lineBreaks = "\r\n";
    }

    public StripLineBreaks(Reader in) {
        super(in);
        this.lineBreaks = "\r\n";
    }

    @Override // java.io.FilterReader, java.io.Reader
    public final int read() throws IOException {
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        int ch = this.in.read();
        while (ch != -1 && this.lineBreaks.indexOf(ch) != -1) {
            ch = this.in.read();
        }
        return ch;
    }

    public final void setLineBreaks(String lineBreaks) {
        this.lineBreaks = lineBreaks;
    }

    private final String getLineBreaks() {
        return this.lineBreaks;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader rdr) {
        StripLineBreaks newFilter = new StripLineBreaks(rdr);
        newFilter.setLineBreaks(getLineBreaks());
        newFilter.setInitialized(true);
        return newFilter;
    }

    private final void initialize() {
        String userDefinedLineBreaks = null;
        Parameter[] params = getParameters();
        if (params != null) {
            int i = 0;
            while (true) {
                if (i >= params.length) {
                    break;
                }
                if (!LINE_BREAKS_KEY.equals(params[i].getName())) {
                    i++;
                } else {
                    userDefinedLineBreaks = params[i].getValue();
                    break;
                }
            }
        }
        if (userDefinedLineBreaks != null) {
            this.lineBreaks = userDefinedLineBreaks;
        }
    }
}

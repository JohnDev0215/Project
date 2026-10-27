package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.types.Parameter;

/* JADX INFO: loaded from: classes.dex */
public final class PrefixLines extends BaseParamFilterReader implements ChainableReader {
    private static final String PREFIX_KEY = "prefix";
    private String prefix;
    private String queuedData;

    public PrefixLines() {
        this.prefix = null;
        this.queuedData = null;
    }

    public PrefixLines(Reader in) {
        super(in);
        this.prefix = null;
        this.queuedData = null;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public final int read() throws IOException {
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        String str = this.queuedData;
        if (str != null && str.length() == 0) {
            this.queuedData = null;
        }
        String str2 = this.queuedData;
        if (str2 != null) {
            int ch = str2.charAt(0);
            String strSubstring = this.queuedData.substring(1);
            this.queuedData = strSubstring;
            if (strSubstring.length() == 0) {
                this.queuedData = null;
                return ch;
            }
            return ch;
        }
        String line = readLine();
        this.queuedData = line;
        if (line == null) {
            return -1;
        }
        if (this.prefix != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.prefix);
            stringBuffer.append(this.queuedData);
            this.queuedData = stringBuffer.toString();
        }
        return read();
    }

    public final void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    private final String getPrefix() {
        return this.prefix;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader rdr) {
        PrefixLines newFilter = new PrefixLines(rdr);
        newFilter.setPrefix(getPrefix());
        newFilter.setInitialized(true);
        return newFilter;
    }

    private final void initialize() {
        Parameter[] params = getParameters();
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (PREFIX_KEY.equals(params[i].getName())) {
                    this.prefix = params[i].getValue();
                    return;
                }
            }
        }
    }
}

package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: classes.dex */
public final class StripJavaComments extends BaseFilterReader implements ChainableReader {
    private boolean inString;
    private boolean quoted;
    private int readAheadCh;

    public StripJavaComments() {
        this.readAheadCh = -1;
        this.inString = false;
        this.quoted = false;
    }

    public StripJavaComments(Reader in) {
        super(in);
        this.readAheadCh = -1;
        this.inString = false;
        this.quoted = false;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public final int read() throws IOException {
        if (this.readAheadCh != -1) {
            int ch = this.readAheadCh;
            this.readAheadCh = -1;
            return ch;
        }
        int ch2 = this.in.read();
        if (ch2 == 34 && !this.quoted) {
            this.inString = !this.inString;
            this.quoted = false;
            return ch2;
        }
        if (ch2 == 92) {
            this.quoted = !this.quoted;
            return ch2;
        }
        this.quoted = false;
        if (!this.inString && ch2 == 47) {
            int ch3 = this.in.read();
            if (ch3 != 47) {
                if (ch3 != 42) {
                    this.readAheadCh = ch3;
                    return 47;
                }
                while (ch3 != -1) {
                    ch3 = this.in.read();
                    if (ch3 == 42) {
                        ch3 = this.in.read();
                        while (ch3 == 42 && ch3 != -1) {
                            ch3 = this.in.read();
                        }
                        if (ch3 == 47) {
                            return read();
                        }
                    }
                }
                return ch3;
            }
            while (ch3 != 10 && ch3 != -1 && ch3 != 13) {
                ch3 = this.in.read();
            }
            return ch3;
        }
        return ch2;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader rdr) {
        StripJavaComments newFilter = new StripJavaComments(rdr);
        return newFilter;
    }
}

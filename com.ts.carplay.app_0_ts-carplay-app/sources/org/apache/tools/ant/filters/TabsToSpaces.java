package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.types.Parameter;

/* JADX INFO: loaded from: classes.dex */
public final class TabsToSpaces extends BaseParamFilterReader implements ChainableReader {
    private static final int DEFAULT_TAB_LENGTH = 8;
    private static final String TAB_LENGTH_KEY = "tablength";
    private int spacesRemaining;
    private int tabLength;

    public TabsToSpaces() {
        this.tabLength = 8;
        this.spacesRemaining = 0;
    }

    public TabsToSpaces(Reader in) {
        super(in);
        this.tabLength = 8;
        this.spacesRemaining = 0;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public final int read() throws IOException {
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        int i = this.spacesRemaining;
        if (i > 0) {
            this.spacesRemaining = i - 1;
            return 32;
        }
        int ch = this.in.read();
        if (ch == 9) {
            this.spacesRemaining = this.tabLength - 1;
            return 32;
        }
        return ch;
    }

    public final void setTablength(int tabLength) {
        this.tabLength = tabLength;
    }

    private final int getTablength() {
        return this.tabLength;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader rdr) {
        TabsToSpaces newFilter = new TabsToSpaces(rdr);
        newFilter.setTablength(getTablength());
        newFilter.setInitialized(true);
        return newFilter;
    }

    private final void initialize() {
        Parameter[] params = getParameters();
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] != null && TAB_LENGTH_KEY.equals(params[i].getName())) {
                    this.tabLength = new Integer(params[i].getValue()).intValue();
                    return;
                }
            }
        }
    }
}

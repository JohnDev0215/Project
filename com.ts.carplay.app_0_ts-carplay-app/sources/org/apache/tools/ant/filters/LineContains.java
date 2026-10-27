package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import java.util.Vector;
import org.apache.tools.ant.types.Parameter;

/* JADX INFO: loaded from: classes.dex */
public final class LineContains extends BaseParamFilterReader implements ChainableReader {
    private static final String CONTAINS_KEY = "contains";
    private Vector contains;
    private String line;

    public LineContains() {
        this.contains = new Vector();
        this.line = null;
    }

    public LineContains(Reader in) {
        super(in);
        this.contains = new Vector();
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
            int containsSize = this.contains.size();
            while (this.line != null) {
                int i = 0;
                while (true) {
                    if (i >= containsSize) {
                        break;
                    }
                    String containsStr = (String) this.contains.elementAt(i);
                    if (this.line.indexOf(containsStr) != -1) {
                        i++;
                    } else {
                        this.line = null;
                        break;
                    }
                }
                String containsStr2 = this.line;
                if (containsStr2 != null) {
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

    public final void addConfiguredContains(Contains contains) {
        this.contains.addElement(contains.getValue());
    }

    private void setContains(Vector contains) {
        this.contains = contains;
    }

    private final Vector getContains() {
        return this.contains;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader rdr) {
        LineContains newFilter = new LineContains(rdr);
        newFilter.setContains(getContains());
        newFilter.setInitialized(true);
        return newFilter;
    }

    private final void initialize() {
        Parameter[] params = getParameters();
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (CONTAINS_KEY.equals(params[i].getType())) {
                    this.contains.addElement(params[i].getValue());
                }
            }
        }
    }

    public static class Contains {
        private String value;

        public final void setValue(String contains) {
            this.value = contains;
        }

        public final String getValue() {
            return this.value;
        }
    }
}

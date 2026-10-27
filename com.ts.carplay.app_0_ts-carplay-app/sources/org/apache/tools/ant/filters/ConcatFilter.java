package org.apache.tools.ant.filters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.types.Parameter;

/* JADX INFO: loaded from: classes.dex */
public final class ConcatFilter extends BaseParamFilterReader implements ChainableReader {
    private File append;
    private Reader appendReader;
    private File prepend;
    private Reader prependReader;

    public ConcatFilter() {
        this.prependReader = null;
        this.appendReader = null;
    }

    public ConcatFilter(Reader in) {
        super(in);
        this.prependReader = null;
        this.appendReader = null;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        Reader reader;
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        int ch = -1;
        Reader reader2 = this.prependReader;
        if (reader2 != null && (ch = reader2.read()) == -1) {
            this.prependReader.close();
            this.prependReader = null;
        }
        if (ch == -1) {
            ch = super.read();
        }
        if (ch == -1 && (reader = this.appendReader) != null && (ch = reader.read()) == -1) {
            this.appendReader.close();
            this.appendReader = null;
        }
        return ch;
    }

    public void setPrepend(File prepend) {
        this.prepend = prepend;
    }

    public File getPrepend() {
        return this.prepend;
    }

    public void setAppend(File append) {
        this.append = append;
    }

    public File getAppend() {
        return this.append;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public Reader chain(Reader rdr) {
        ConcatFilter newFilter = new ConcatFilter(rdr);
        newFilter.setPrepend(getPrepend());
        newFilter.setAppend(getAppend());
        return newFilter;
    }

    private void initialize() throws IOException {
        Parameter[] params = getParameters();
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if ("prepend".equals(params[i].getName())) {
                    setPrepend(new File(params[i].getValue()));
                } else if ("append".equals(params[i].getName())) {
                    setAppend(new File(params[i].getValue()));
                }
            }
        }
        File file = this.prepend;
        if (file != null) {
            if (!file.isAbsolute()) {
                this.prepend = new File(getProject().getBaseDir(), this.prepend.getPath());
            }
            this.prependReader = new BufferedReader(new FileReader(this.prepend));
        }
        File file2 = this.append;
        if (file2 != null) {
            if (!file2.isAbsolute()) {
                this.append = new File(getProject().getBaseDir(), this.append.getPath());
            }
            this.appendReader = new BufferedReader(new FileReader(this.append));
        }
    }
}

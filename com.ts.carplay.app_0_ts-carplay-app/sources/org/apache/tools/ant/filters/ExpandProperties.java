package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.Project;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandProperties extends BaseFilterReader implements ChainableReader {
    private String queuedData;

    public ExpandProperties() {
        this.queuedData = null;
    }

    public ExpandProperties(Reader in) {
        super(in);
        this.queuedData = null;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public final int read() throws IOException {
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
        String fully = readFully();
        this.queuedData = fully;
        if (fully == null) {
            return -1;
        }
        Project project = getProject();
        this.queuedData = project.replaceProperties(this.queuedData);
        return read();
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader rdr) {
        ExpandProperties newFilter = new ExpandProperties(rdr);
        newFilter.setProject(getProject());
        return newFilter;
    }
}

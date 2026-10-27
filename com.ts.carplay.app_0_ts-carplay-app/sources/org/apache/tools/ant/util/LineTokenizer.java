package org.apache.tools.ant.util;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.taskdefs.Manifest;

/* JADX INFO: loaded from: classes.dex */
public class LineTokenizer extends ProjectComponent implements Tokenizer {
    private String lineEnd = "";
    private int pushed = -2;
    private boolean includeDelims = false;

    public void setIncludeDelims(boolean includeDelims) {
        this.includeDelims = includeDelims;
    }

    @Override // org.apache.tools.ant.util.Tokenizer
    public String getToken(Reader in) throws IOException {
        int ch;
        if (this.pushed != -2) {
            ch = this.pushed;
            this.pushed = -2;
        } else {
            ch = in.read();
        }
        if (ch == -1) {
            return null;
        }
        this.lineEnd = "";
        StringBuffer line = new StringBuffer();
        int state = 0;
        while (true) {
            if (ch == -1) {
                break;
            }
            if (state == 0) {
                if (ch == 13) {
                    state = 1;
                } else {
                    if (ch == 10) {
                        this.lineEnd = "\n";
                        break;
                    }
                    line.append((char) ch);
                }
                ch = in.read();
            } else {
                state = 0;
                if (ch == 10) {
                    this.lineEnd = Manifest.EOL;
                } else {
                    this.pushed = ch;
                    this.lineEnd = "\r";
                }
            }
        }
        if (ch == -1 && state == 1) {
            this.lineEnd = "\r";
        }
        if (this.includeDelims) {
            line.append(this.lineEnd);
        }
        return line.toString();
    }

    @Override // org.apache.tools.ant.util.Tokenizer
    public String getPostToken() {
        if (this.includeDelims) {
            return "";
        }
        return this.lineEnd;
    }
}

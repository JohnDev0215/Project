package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import java.util.Vector;
import org.apache.tools.ant.types.Parameter;

/* JADX INFO: loaded from: classes.dex */
public final class StripLineComments extends BaseParamFilterReader implements ChainableReader {
    private static final String COMMENTS_KEY = "comment";
    private Vector comments;
    private String line;

    public StripLineComments() {
        this.comments = new Vector();
        this.line = null;
    }

    public StripLineComments(Reader in) {
        super(in);
        this.comments = new Vector();
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
            int commentsSize = this.comments.size();
            while (this.line != null) {
                int i = 0;
                while (true) {
                    if (i >= commentsSize) {
                        break;
                    }
                    String comment = (String) this.comments.elementAt(i);
                    if (!this.line.startsWith(comment)) {
                        i++;
                    } else {
                        this.line = null;
                        break;
                    }
                }
                String comment2 = this.line;
                if (comment2 != null) {
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

    public final void addConfiguredComment(Comment comment) {
        this.comments.addElement(comment.getValue());
    }

    private void setComments(Vector comments) {
        this.comments = comments;
    }

    private final Vector getComments() {
        return this.comments;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader rdr) {
        StripLineComments newFilter = new StripLineComments(rdr);
        newFilter.setComments(getComments());
        newFilter.setInitialized(true);
        return newFilter;
    }

    private final void initialize() {
        Parameter[] params = getParameters();
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (COMMENTS_KEY.equals(params[i].getType())) {
                    this.comments.addElement(params[i].getValue());
                }
            }
        }
    }

    public static class Comment {
        private String value;

        public final void setValue(String comment) {
            this.value = comment;
        }

        public final String getValue() {
            return this.value;
        }
    }
}

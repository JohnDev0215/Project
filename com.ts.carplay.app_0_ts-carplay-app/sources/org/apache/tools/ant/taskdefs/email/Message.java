package org.apache.tools.ant.taskdefs.email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.apache.tools.ant.ProjectComponent;

/* JADX INFO: loaded from: classes.dex */
public class Message extends ProjectComponent {
    private StringBuffer buffer;
    private String charset;
    private File messageSource;
    private String mimeType;
    private boolean specified;

    public Message() {
        this.messageSource = null;
        this.buffer = new StringBuffer();
        this.mimeType = "text/plain";
        this.specified = false;
        this.charset = null;
    }

    public Message(String text) {
        this.messageSource = null;
        this.buffer = new StringBuffer();
        this.mimeType = "text/plain";
        this.specified = false;
        this.charset = null;
        addText(text);
    }

    public Message(File file) {
        this.messageSource = null;
        this.buffer = new StringBuffer();
        this.mimeType = "text/plain";
        this.specified = false;
        this.charset = null;
        this.messageSource = file;
    }

    public void addText(String text) {
        this.buffer.append(text);
    }

    public void setSrc(File src) {
        this.messageSource = src;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
        this.specified = true;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void print(PrintStream ps) throws IOException {
        PrintWriter out = this.charset != null ? new PrintWriter(new OutputStreamWriter(ps, this.charset)) : new PrintWriter(ps);
        if (this.messageSource != null) {
            FileReader freader = new FileReader(this.messageSource);
            try {
                BufferedReader in = new BufferedReader(freader);
                while (true) {
                    String line = in.readLine();
                    if (line == null) {
                        break;
                    } else {
                        out.println(getProject().replaceProperties(line));
                    }
                }
            } finally {
                freader.close();
            }
        } else {
            out.println(getProject().replaceProperties(this.buffer.substring(0)));
        }
        out.flush();
    }

    public boolean isMimeTypeSpecified() {
        return this.specified;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCharset() {
        return this.charset;
    }
}

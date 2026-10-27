package com.android.textclassifier.utils;

import com.google.common.base.Preconditions;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public final class IndentingPrintWriter {
    private final PrintWriter writer;
    private final StringBuilder indentBuilder = new StringBuilder();
    private String currentIndent = "";

    public IndentingPrintWriter(PrintWriter printWriter) {
        Preconditions.checkNotNull(printWriter);
        this.writer = printWriter;
    }

    public IndentingPrintWriter println(String str) {
        this.writer.print(this.currentIndent);
        this.writer.print(str);
        this.writer.println();
        return this;
    }

    public IndentingPrintWriter println() {
        this.writer.println();
        return this;
    }

    public IndentingPrintWriter increaseIndent() {
        this.indentBuilder.append("  ");
        this.currentIndent = this.indentBuilder.toString();
        return this;
    }

    public IndentingPrintWriter decreaseIndent() {
        this.indentBuilder.delete(0, 2);
        this.currentIndent = this.indentBuilder.toString();
        return this;
    }

    public IndentingPrintWriter printPair(String str, Object obj) {
        println(String.format("%s=%s", str, String.valueOf(obj)));
        return this;
    }

    public void flush() {
        this.writer.flush();
    }
}

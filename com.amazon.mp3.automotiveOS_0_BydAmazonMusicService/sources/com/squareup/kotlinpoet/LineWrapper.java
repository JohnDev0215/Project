package com.squareup.kotlinpoet;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: LineWrapper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB!\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\"\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\u0006J\u000e\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006J\b\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u0018\u001a\u00020\u0014H\u0002J\u0018\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\bH\u0002J\b\u0010\u001c\u001a\u00020\u0014H\u0002J\u0006\u0010\u001d\u001a\u00020\u0014R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/squareup/kotlinpoet/LineWrapper;", "Ljava/io/Closeable;", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "indent", "", "columnLimit", "", "(Ljava/lang/Appendable;Ljava/lang/String;I)V", "closed", "", "hasPendingSegments", "getHasPendingSegments", "()Z", "indentLevel", "linePrefix", "segments", "", "append", "", "s", "appendNonWrapping", "close", "emitCurrentLine", "emitSegmentRange", "startIndex", "endIndex", "foldUnsafeBreaks", "newline", "Companion", "kotlinpoet"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LineWrapper implements Closeable {
    private static final char[] SPECIAL_CHARACTERS;
    private boolean closed;
    private final int columnLimit;
    private final String indent;
    private int indentLevel;
    private String linePrefix;
    private final Appendable out;
    private final List<String> segments;
    private static final Regex UNSAFE_LINE_START = new Regex("\\s*[-+].*");

    public LineWrapper(Appendable out, String indent, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(indent, "indent");
        this.out = out;
        this.indent = indent;
        this.columnLimit = i;
        this.segments = CollectionsKt__CollectionsKt.mutableListOf("");
        this.indentLevel = -1;
        this.linePrefix = "";
    }

    public final boolean getHasPendingSegments() {
        if (this.segments.size() == 1) {
            if (!(this.segments.get(0).length() > 0)) {
                return false;
            }
        }
        return true;
    }

    public final void append(String s, int indentLevel, String linePrefix) {
        Intrinsics.checkNotNullParameter(s, "s");
        Intrinsics.checkNotNullParameter(linePrefix, "linePrefix");
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        int i = 0;
        while (i < s.length()) {
            char cCharAt = s.charAt(i);
            if (cCharAt == ' ') {
                this.indentLevel = indentLevel;
                this.linePrefix = linePrefix;
                this.segments.add("");
            } else if (cCharAt == '\n') {
                newline();
            } else if (cCharAt == 183) {
                List<String> list = this.segments;
                int size = list.size() - 1;
                list.set(size, Intrinsics.stringPlus(list.get(size), " "));
            } else {
                int iIndexOfAny$default = StringsKt__StringsKt.indexOfAny$default((CharSequence) s, SPECIAL_CHARACTERS, i, false, 4, (Object) null);
                if (iIndexOfAny$default == -1) {
                    iIndexOfAny$default = s.length();
                }
                List<String> list2 = this.segments;
                int size2 = list2.size() - 1;
                String str = list2.get(size2);
                String strSubstring = s.substring(i, iIndexOfAny$default);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                list2.set(size2, Intrinsics.stringPlus(str, strSubstring));
                i = iIndexOfAny$default;
            }
            i++;
        }
    }

    public final void appendNonWrapping(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if (!(!StringsKt__StringsKt.contains$default((CharSequence) s, (CharSequence) "\n", false, 2, (Object) null))) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        List<String> list = this.segments;
        int size = list.size() - 1;
        list.set(size, Intrinsics.stringPlus(list.get(size), s));
    }

    public final void newline() {
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        emitCurrentLine();
        this.out.append("\n");
        this.indentLevel = -1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        emitCurrentLine();
        this.closed = true;
    }

    private final void emitCurrentLine() throws IOException {
        foldUnsafeBreaks();
        int length = this.segments.get(0).length();
        int size = this.segments.size();
        int i = 0;
        int i2 = 1;
        while (i2 < size) {
            int i3 = i2 + 1;
            String str = this.segments.get(i2);
            length = length + 1 + str.length();
            if (length > this.columnLimit) {
                emitSegmentRange(i, i2);
                length = str.length() + (this.indent.length() * this.indentLevel);
                i = i2;
            }
            i2 = i3;
        }
        emitSegmentRange(i, this.segments.size());
        this.segments.clear();
        this.segments.add("");
    }

    private final void emitSegmentRange(int startIndex, int endIndex) throws IOException {
        if (startIndex > 0) {
            this.out.append("\n");
            int i = 0;
            int i2 = this.indentLevel;
            while (i < i2) {
                i++;
                this.out.append(this.indent);
            }
            this.out.append(this.linePrefix);
        }
        this.out.append(this.segments.get(startIndex));
        for (int i3 = startIndex + 1; i3 < endIndex; i3++) {
            this.out.append(" ");
            this.out.append(this.segments.get(i3));
        }
    }

    private final void foldUnsafeBreaks() {
        int i = 1;
        while (i < this.segments.size()) {
            if (UNSAFE_LINE_START.matches(this.segments.get(i))) {
                int i2 = i - 1;
                this.segments.set(i2, this.segments.get(i2) + ' ' + this.segments.get(i));
                this.segments.remove(i);
                if (i > 1) {
                    i--;
                }
            } else {
                i++;
            }
        }
    }

    static {
        char[] charArray = " \n·".toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
        SPECIAL_CHARACTERS = charArray;
    }
}

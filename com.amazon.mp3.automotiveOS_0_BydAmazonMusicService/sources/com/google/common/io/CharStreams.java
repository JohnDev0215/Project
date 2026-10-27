package com.google.common.io;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;

/* JADX INFO: loaded from: classes6.dex */
public final class CharStreams {
    static CharBuffer createBuffer() {
        return CharBuffer.allocate(2048);
    }

    private CharStreams() {
    }

    public static long copy(Readable readable, Appendable appendable) throws IOException {
        if (readable instanceof Reader) {
            if (appendable instanceof StringBuilder) {
                return copyReaderToBuilder((Reader) readable, (StringBuilder) appendable);
            }
            return copyReaderToWriter((Reader) readable, asWriter(appendable));
        }
        Preconditions.checkNotNull(readable);
        Preconditions.checkNotNull(appendable);
        long jRemaining = 0;
        CharBuffer charBufferCreateBuffer = createBuffer();
        while (readable.read(charBufferCreateBuffer) != -1) {
            Java8Compatibility.flip(charBufferCreateBuffer);
            appendable.append(charBufferCreateBuffer);
            jRemaining += (long) charBufferCreateBuffer.remaining();
            Java8Compatibility.clear(charBufferCreateBuffer);
        }
        return jRemaining;
    }

    static long copyReaderToBuilder(Reader reader, StringBuilder sb) throws IOException {
        Preconditions.checkNotNull(reader);
        Preconditions.checkNotNull(sb);
        char[] cArr = new char[2048];
        long j = 0;
        while (true) {
            int i = reader.read(cArr);
            if (i == -1) {
                return j;
            }
            sb.append(cArr, 0, i);
            j += (long) i;
        }
    }

    static long copyReaderToWriter(Reader reader, Writer writer) throws IOException {
        Preconditions.checkNotNull(reader);
        Preconditions.checkNotNull(writer);
        char[] cArr = new char[2048];
        long j = 0;
        while (true) {
            int i = reader.read(cArr);
            if (i == -1) {
                return j;
            }
            writer.write(cArr, 0, i);
            j += (long) i;
        }
    }

    public static String toString(Readable readable) throws IOException {
        return toStringBuilder(readable).toString();
    }

    private static StringBuilder toStringBuilder(Readable readable) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (readable instanceof Reader) {
            copyReaderToBuilder((Reader) readable, sb);
        } else {
            copy(readable, sb);
        }
        return sb;
    }

    public static Writer asWriter(Appendable appendable) {
        if (appendable instanceof Writer) {
            return (Writer) appendable;
        }
        return new AppendableWriter(appendable);
    }
}

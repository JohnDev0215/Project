package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes7.dex */
public abstract class CharSequenceTranslator {
    static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public abstract int translate(CharSequence charSequence, int i, Writer writer) throws IOException;

    public final String translate(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter(charSequence.length() * 2);
            translate(charSequence, stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final void translate(CharSequence charSequence, Writer writer) throws IOException {
        Validate.isTrue(writer != null, "The Writer must not be null", new Object[0]);
        if (charSequence == null) {
            return;
        }
        int length = charSequence.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iTranslate = translate(charSequence, iCharCount, writer);
            if (iTranslate == 0) {
                char cCharAt = charSequence.charAt(iCharCount);
                writer.write(cCharAt);
                iCharCount++;
                if (Character.isHighSurrogate(cCharAt) && iCharCount < length) {
                    char cCharAt2 = charSequence.charAt(iCharCount);
                    if (Character.isLowSurrogate(cCharAt2)) {
                        writer.write(cCharAt2);
                        iCharCount++;
                    }
                }
            } else {
                for (int i = 0; i < iTranslate; i++) {
                    iCharCount += Character.charCount(Character.codePointAt(charSequence, iCharCount));
                }
            }
        }
    }

    public static String hex(int i) {
        return Integer.toHexString(i).toUpperCase(Locale.ENGLISH);
    }
}

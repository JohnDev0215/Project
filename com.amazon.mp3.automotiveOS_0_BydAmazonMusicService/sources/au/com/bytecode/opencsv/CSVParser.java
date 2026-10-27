package au.com.bytecode.opencsv;

import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class CSVParser {
    private final char escape;
    private final boolean ignoreLeadingWhiteSpace;
    private boolean inField;
    private String pending;
    private final char quotechar;
    private final char separator;
    private final boolean strictQuotes;

    public CSVParser() {
        this(',', '\"', '\\');
    }

    public CSVParser(char c, char c2, char c3) {
        this(c, c2, c3, false);
    }

    public CSVParser(char c, char c2, char c3, boolean z) {
        this(c, c2, c3, z, true);
    }

    public CSVParser(char c, char c2, char c3, boolean z, boolean z2) {
        this.inField = false;
        if (anyCharactersAreTheSame(c, c2, c3)) {
            throw new UnsupportedOperationException("The separator, quote, and escape characters must be different!");
        }
        if (c == 0) {
            throw new UnsupportedOperationException("The separator character must be defined!");
        }
        this.separator = c;
        this.quotechar = c2;
        this.escape = c3;
        this.strictQuotes = z;
        this.ignoreLeadingWhiteSpace = z2;
    }

    private boolean anyCharactersAreTheSame(char c, char c2, char c3) {
        return isSameCharacter(c, c2) || isSameCharacter(c, c3) || isSameCharacter(c2, c3);
    }

    private boolean isNextCharacterEscapedQuote(String str, boolean z, int i) {
        int i2;
        return z && str.length() > (i2 = i + 1) && str.charAt(i2) == this.quotechar;
    }

    private boolean isSameCharacter(char c, char c2) {
        return c != 0 && c == c2;
    }

    private String[] parseLine(String str, boolean z) throws IOException {
        boolean z2;
        int i;
        StringBuilder sb = null;
        if (!z && this.pending != null) {
            this.pending = null;
        }
        if (str == null) {
            String str2 = this.pending;
            if (str2 == null) {
                return null;
            }
            this.pending = null;
            return new String[]{str2};
        }
        ArrayList arrayList = new ArrayList();
        StringBuilder sb2 = new StringBuilder(128);
        String str3 = this.pending;
        if (str3 != null) {
            sb2.append(str3);
            this.pending = null;
            z2 = true;
        } else {
            z2 = false;
        }
        int i2 = 0;
        while (i2 < str.length()) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == this.escape) {
                if (isNextCharacterEscapable(str, z2 || this.inField, i2)) {
                    i2++;
                    sb2.append(str.charAt(i2));
                }
            } else if (cCharAt == this.quotechar) {
                if (isNextCharacterEscapedQuote(str, z2 || this.inField, i2)) {
                    i2++;
                    sb2.append(str.charAt(i2));
                } else {
                    if (!this.strictQuotes && i2 > 2 && str.charAt(i2 - 1) != this.separator && str.length() > (i = i2 + 1) && str.charAt(i) != this.separator) {
                        if (this.ignoreLeadingWhiteSpace && sb2.length() > 0 && isAllWhiteSpace(sb2)) {
                            sb2.setLength(0);
                        } else {
                            sb2.append(cCharAt);
                        }
                    }
                    z2 = !z2;
                }
                this.inField = !this.inField;
            } else if (cCharAt == this.separator && !z2) {
                arrayList.add(sb2.toString());
                sb2.setLength(0);
                this.inField = false;
            } else if (!this.strictQuotes || z2) {
                sb2.append(cCharAt);
                this.inField = true;
            }
            i2++;
        }
        if (!z2) {
            sb = sb2;
        } else {
            if (!z) {
                throw new IOException("Un-terminated quoted field at end of CSV line");
            }
            sb2.append("\n");
            this.pending = sb2.toString();
        }
        if (sb != null) {
            arrayList.add(sb.toString());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    protected boolean isAllWhiteSpace(CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    protected boolean isNextCharacterEscapable(String str, boolean z, int i) {
        int i2;
        return z && str.length() > (i2 = i + 1) && (str.charAt(i2) == this.quotechar || str.charAt(i2) == this.escape);
    }

    public boolean isPending() {
        return this.pending != null;
    }

    public String[] parseLineMulti(String str) throws IOException {
        return parseLine(str, true);
    }
}

package org.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* JADX INFO: loaded from: classes6.dex */
public class JSONTokener {
    private long character;
    private long characterPreviousLine;
    private boolean eof;
    private long index;
    private long line;
    private char previous;
    private final Reader reader;
    private boolean usePrevious;

    public JSONTokener(Reader reader) {
        this.reader = reader.markSupported() ? reader : new BufferedReader(reader);
        this.eof = false;
        this.usePrevious = false;
        this.previous = (char) 0;
        this.index = 0L;
        this.character = 1L;
        this.characterPreviousLine = 0L;
        this.line = 1L;
    }

    public JSONTokener(String str) {
        this(new StringReader(str));
    }

    public void back() throws JSONException {
        if (this.usePrevious || this.index <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        decrementIndexes();
        this.usePrevious = true;
        this.eof = false;
    }

    private void decrementIndexes() {
        this.index--;
        char c = this.previous;
        if (c == '\r' || c == '\n') {
            this.line--;
            this.character = this.characterPreviousLine;
        } else {
            long j = this.character;
            if (j > 0) {
                this.character = j - 1;
            }
        }
    }

    public boolean end() {
        return this.eof && !this.usePrevious;
    }

    public char next() throws JSONException {
        int i;
        if (this.usePrevious) {
            this.usePrevious = false;
            i = this.previous;
        } else {
            try {
                i = this.reader.read();
            } catch (IOException e) {
                throw new JSONException(e);
            }
        }
        if (i <= 0) {
            this.eof = true;
            return (char) 0;
        }
        incrementIndexes(i);
        char c = (char) i;
        this.previous = c;
        return c;
    }

    protected char getPrevious() {
        return this.previous;
    }

    private void incrementIndexes(int i) {
        if (i > 0) {
            this.index++;
            if (i == 13) {
                this.line++;
                this.characterPreviousLine = this.character;
                this.character = 0L;
            } else {
                if (i == 10) {
                    if (this.previous != '\r') {
                        this.line++;
                        this.characterPreviousLine = this.character;
                    }
                    this.character = 0L;
                    return;
                }
                this.character++;
            }
        }
    }

    public String next(int i) throws JSONException {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = next();
            if (end()) {
                throw syntaxError("Substring bounds error");
            }
        }
        return new String(cArr);
    }

    public char nextClean() throws JSONException {
        char next;
        do {
            next = next();
            if (next == 0) {
                break;
            }
        } while (next <= ' ');
        return next;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x008e, code lost:
    
        throw syntaxError("Unterminated string");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String nextString(char r7) throws org.json.JSONException {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L5:
            char r1 = r6.next()
            if (r1 == 0) goto L88
            r2 = 10
            if (r1 == r2) goto L88
            r3 = 13
            if (r1 == r3) goto L88
            r4 = 92
            if (r1 == r4) goto L22
            if (r1 != r7) goto L1e
            java.lang.String r7 = r0.toString()
            return r7
        L1e:
            r0.append(r1)
            goto L5
        L22:
            char r1 = r6.next()
            r5 = 34
            if (r1 == r5) goto L83
            r5 = 39
            if (r1 == r5) goto L83
            r5 = 47
            if (r1 == r5) goto L83
            if (r1 == r4) goto L83
            r4 = 98
            if (r1 == r4) goto L7d
            r4 = 102(0x66, float:1.43E-43)
            if (r1 == r4) goto L77
            r4 = 110(0x6e, float:1.54E-43)
            if (r1 == r4) goto L73
            r2 = 114(0x72, float:1.6E-43)
            if (r1 == r2) goto L6f
            r2 = 116(0x74, float:1.63E-43)
            if (r1 == r2) goto L69
            r2 = 117(0x75, float:1.64E-43)
            java.lang.String r3 = "Illegal escape."
            if (r1 != r2) goto L64
            r1 = 4
            java.lang.String r1 = r6.next(r1)     // Catch: java.lang.NumberFormatException -> L5e
            r2 = 16
            int r1 = java.lang.Integer.parseInt(r1, r2)     // Catch: java.lang.NumberFormatException -> L5e
            char r1 = (char) r1     // Catch: java.lang.NumberFormatException -> L5e
            r0.append(r1)     // Catch: java.lang.NumberFormatException -> L5e
            goto L5
        L5e:
            r7 = move-exception
            org.json.JSONException r7 = r6.syntaxError(r3, r7)
            throw r7
        L64:
            org.json.JSONException r7 = r6.syntaxError(r3)
            throw r7
        L69:
            r1 = 9
            r0.append(r1)
            goto L5
        L6f:
            r0.append(r3)
            goto L5
        L73:
            r0.append(r2)
            goto L5
        L77:
            r1 = 12
            r0.append(r1)
            goto L5
        L7d:
            r1 = 8
            r0.append(r1)
            goto L5
        L83:
            r0.append(r1)
            goto L5
        L88:
            java.lang.String r7 = "Unterminated string"
            org.json.JSONException r7 = r6.syntaxError(r7)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONTokener.nextString(char):java.lang.String");
    }

    public Object nextValue() throws JSONException {
        char cNextClean = nextClean();
        if (cNextClean == '\"' || cNextClean == '\'') {
            return nextString(cNextClean);
        }
        if (cNextClean == '[') {
            back();
            try {
                return new JSONArray(this);
            } catch (StackOverflowError e) {
                throw new JSONException("JSON Array or Object depth too large to process.", e);
            }
        }
        if (cNextClean == '{') {
            back();
            try {
                return new JSONObject(this);
            } catch (StackOverflowError e2) {
                throw new JSONException("JSON Array or Object depth too large to process.", e2);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (cNextClean >= ' ' && ",:]}/\\\"[{;=#".indexOf(cNextClean) < 0) {
            sb.append(cNextClean);
            cNextClean = next();
        }
        if (!this.eof) {
            back();
        }
        String strTrim = sb.toString().trim();
        if ("".equals(strTrim)) {
            throw syntaxError("Missing value");
        }
        return JSONObject.stringToValue(strTrim);
    }

    public JSONException syntaxError(String str) {
        return new JSONException(str + toString());
    }

    public JSONException syntaxError(String str, Throwable th) {
        return new JSONException(str + toString(), th);
    }

    public String toString() {
        return " at " + this.index + " [character " + this.character + " line " + this.line + "]";
    }
}

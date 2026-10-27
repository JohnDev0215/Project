package com.amazon.alexa.voice.core.internal.http;

import com.fasterxml.jackson.core.JsonPointer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class Header {
    private final Parameter[] parameters;
    private final String value;

    private Header(String str, Parameter[] parameterArr) {
        this.value = str;
        this.parameters = parameterArr;
    }

    public static Header parse(String str) throws IOException {
        if (str == null) {
            throw new IOException("string == null");
        }
        Parser parser = new Parser(str);
        String str2 = parser.requireType() + parser.requireCharacter(JsonPointer.SEPARATOR) + parser.requireToken();
        ArrayList arrayList = new ArrayList();
        while (true) {
            parser.skipCharacter(';');
            String strNextToken = parser.nextToken();
            if (strNextToken != null) {
                arrayList.add(new Parameter(strNextToken, parser.skipCharacter('=') ? parser.nextValue() : null));
            } else {
                return new Header(str2, (Parameter[]) arrayList.toArray(new Parameter[arrayList.size()]));
            }
        }
    }

    public String getValue() {
        return this.value;
    }

    public int getCount() {
        return this.parameters.length;
    }

    public String get(String str) {
        int i = 0;
        while (true) {
            Parameter[] parameterArr = this.parameters;
            if (i >= parameterArr.length) {
                return null;
            }
            Parameter parameter = parameterArr[i];
            if (parameter.isSameName(str)) {
                return parameter.value;
            }
            i++;
        }
    }

    public String toString() {
        return "Header{value='" + this.value + "', parameters=" + Arrays.toString(this.parameters) + '}';
    }

    public static class Parameter {
        final String name;
        final String value;

        public Parameter(String str, String str2) {
            this.name = str;
            this.value = str2;
        }

        public boolean isSameName(String str) {
            if (str == null) {
                return this.name == null;
            }
            return str.equalsIgnoreCase(this.name);
        }

        public String toString() {
            return "Parameter{name='" + this.name + "', value='" + this.value + "'}";
        }
    }

    static class Parser {
        int position;
        String string;

        boolean isAllowedCharacter(char c) {
            return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || ((c >= 'A' && c <= 'Z') || c == '-');
        }

        public Parser(String str) {
            this.string = str;
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x003e, code lost:
        
            r1 = r4.position;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0040, code lost:
        
            if (r0 == r1) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0048, code lost:
        
            return r4.string.substring(r0, r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0050, code lost:
        
            throw new java.io.IOException("Type is required");
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String requireType() throws java.io.IOException {
            /*
                r4 = this;
                r4.skipWhitespace()
                int r0 = r4.position
                java.lang.String r1 = r4.string
                int r1 = r1.length()
            Lb:
                int r2 = r4.position
                if (r2 >= r1) goto L3e
                java.lang.String r3 = r4.string
                char r2 = r3.charAt(r2)
                r3 = 47
                if (r2 != r3) goto L1a
                goto L3e
            L1a:
                boolean r3 = r4.isAllowedCharacter(r2)
                if (r3 == 0) goto L27
                int r2 = r4.position
                int r2 = r2 + 1
                r4.position = r2
                goto Lb
            L27:
                java.io.IOException r0 = new java.io.IOException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r3 = "Unexpected character: "
                r1.append(r3)
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L3e:
                int r1 = r4.position
                if (r0 == r1) goto L49
                java.lang.String r2 = r4.string
                java.lang.String r0 = r2.substring(r0, r1)
                return r0
            L49:
                java.io.IOException r0 = new java.io.IOException
                java.lang.String r1 = "Type is required"
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.voice.core.internal.http.Header.Parser.requireType():java.lang.String");
        }

        public String nextValue() throws IOException {
            skipWhitespace();
            if (this.position == this.string.length()) {
                return null;
            }
            if (this.string.charAt(this.position) == '\"') {
                return nextQuotedString();
            }
            return nextToken();
        }

        public String nextQuotedString() throws IOException {
            skipWhitespace();
            int i = this.position;
            int length = this.string.length();
            int i2 = this.position;
            if (i2 == length || this.string.charAt(i2) != '\"') {
                throw new IOException("Quoted string is required");
            }
            this.position++;
            while (true) {
                int i3 = this.position;
                if (i3 >= length || this.string.charAt(i3) == '\"') {
                    break;
                }
                this.position++;
            }
            int i4 = this.position;
            if (i4 == length) {
                return null;
            }
            this.position = i4 + 1;
            return this.string.substring(i + 1, i4);
        }

        private void skipWhitespace() {
            int length = this.string.length();
            while (true) {
                int i = this.position;
                if (i >= length || !Character.isWhitespace(this.string.charAt(i))) {
                    return;
                } else {
                    this.position++;
                }
            }
        }

        public String nextToken() throws IOException {
            char cCharAt;
            skipWhitespace();
            int i = this.position;
            int length = this.string.length();
            while (true) {
                int i2 = this.position;
                if (i2 >= length || (cCharAt = this.string.charAt(i2)) == ';' || cCharAt == '=' || Character.isWhitespace(cCharAt)) {
                    break;
                }
                this.position++;
            }
            int i3 = this.position;
            if (i == i3) {
                return null;
            }
            return this.string.substring(i, i3);
        }

        public String requireToken() throws IOException {
            String strNextToken = nextToken();
            if (strNextToken != null) {
                return strNextToken;
            }
            throw new IOException("Token is required");
        }

        public boolean skipCharacter(char c) throws IOException {
            skipWhitespace();
            if (this.position >= this.string.length() || this.string.charAt(this.position) != c) {
                return false;
            }
            this.position++;
            return true;
        }

        public char requireCharacter(char c) throws IOException {
            skipWhitespace();
            if (this.position == this.string.length() || this.string.charAt(this.position) != c) {
                throw new IOException("Character '" + c + "' is required");
            }
            this.position++;
            return c;
        }
    }
}

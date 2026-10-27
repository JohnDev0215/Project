package androidx.constraintlayout.core.parser;

import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class CLToken extends CLElement {
    public int index;
    public char[] tokenFalse;
    public char[] tokenNull;
    public char[] tokenTrue;
    public Type type;

    /* JADX INFO: renamed from: androidx.constraintlayout.core.parser.CLToken$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$parser$CLToken$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$androidx$constraintlayout$core$parser$CLToken$Type = iArr;
            try {
                iArr[Type.TRUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$parser$CLToken$Type[Type.FALSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$parser$CLToken$Type[Type.NULL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$parser$CLToken$Type[Type.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public enum Type {
        UNKNOWN,
        TRUE,
        FALSE,
        NULL
    }

    public CLToken(char[] cArr) {
        super(cArr);
        this.index = 0;
        this.type = Type.UNKNOWN;
        this.tokenTrue = "true".toCharArray();
        this.tokenFalse = "false".toCharArray();
        this.tokenNull = "null".toCharArray();
    }

    public static CLElement allocate(char[] cArr) {
        return new CLToken(cArr);
    }

    public boolean getBoolean() throws CLParsingException {
        Type type = this.type;
        if (type == Type.TRUE) {
            return true;
        }
        if (type == Type.FALSE) {
            return false;
        }
        StringBuilder sbK = e.k("this token is not a boolean: <");
        sbK.append(content());
        sbK.append(">");
        throw new CLParsingException(sbK.toString(), this);
    }

    public Type getType() {
        return this.type;
    }

    public boolean isNull() throws CLParsingException {
        if (this.type == Type.NULL) {
            return true;
        }
        StringBuilder sbK = e.k("this token is not a null: <");
        sbK.append(content());
        sbK.append(">");
        throw new CLParsingException(sbK.toString(), this);
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        addIndent(sb, i);
        sb.append(content());
        return sb.toString();
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        if (!CLParser.DEBUG) {
            return content();
        }
        StringBuilder sbK = e.k("<");
        sbK.append(content());
        sbK.append(">");
        return sbK.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0047 A[PHI: r1
  0x0047: PHI (r1v5 boolean) = (r1v3 boolean), (r1v7 boolean), (r1v9 boolean) binds: [B:40:0x0068, B:33:0x0058, B:26:0x0045] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean validate(char r6, long r7) {
        /*
            r5 = this;
            int[] r0 = androidx.constraintlayout.core.parser.CLToken.AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$parser$CLToken$Type
            androidx.constraintlayout.core.parser.CLToken$Type r1 = r5.type
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L5b
            r3 = 2
            if (r0 == r3) goto L4b
            r3 = 3
            if (r0 == r3) goto L38
            r7 = 4
            if (r0 == r7) goto L18
            goto L6b
        L18:
            char[] r7 = r5.tokenTrue
            int r8 = r5.index
            char r7 = r7[r8]
            if (r7 != r6) goto L26
            androidx.constraintlayout.core.parser.CLToken$Type r6 = androidx.constraintlayout.core.parser.CLToken.Type.TRUE
        L22:
            r5.type = r6
            r1 = r2
            goto L6b
        L26:
            char[] r7 = r5.tokenFalse
            char r7 = r7[r8]
            if (r7 != r6) goto L2f
            androidx.constraintlayout.core.parser.CLToken$Type r6 = androidx.constraintlayout.core.parser.CLToken.Type.FALSE
            goto L22
        L2f:
            char[] r7 = r5.tokenNull
            char r7 = r7[r8]
            if (r7 != r6) goto L6b
            androidx.constraintlayout.core.parser.CLToken$Type r6 = androidx.constraintlayout.core.parser.CLToken.Type.NULL
            goto L22
        L38:
            char[] r0 = r5.tokenNull
            int r3 = r5.index
            char r4 = r0[r3]
            if (r4 != r6) goto L41
            r1 = r2
        L41:
            if (r1 == 0) goto L6b
            int r3 = r3 + r2
            int r6 = r0.length
            if (r3 != r6) goto L6b
        L47:
            r5.setEnd(r7)
            goto L6b
        L4b:
            char[] r0 = r5.tokenFalse
            int r3 = r5.index
            char r4 = r0[r3]
            if (r4 != r6) goto L54
            r1 = r2
        L54:
            if (r1 == 0) goto L6b
            int r3 = r3 + r2
            int r6 = r0.length
            if (r3 != r6) goto L6b
            goto L47
        L5b:
            char[] r0 = r5.tokenTrue
            int r3 = r5.index
            char r4 = r0[r3]
            if (r4 != r6) goto L64
            r1 = r2
        L64:
            if (r1 == 0) goto L6b
            int r3 = r3 + r2
            int r6 = r0.length
            if (r3 != r6) goto L6b
            goto L47
        L6b:
            int r6 = r5.index
            int r6 = r6 + r2
            r5.index = r6
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.parser.CLToken.validate(char, long):boolean");
    }
}

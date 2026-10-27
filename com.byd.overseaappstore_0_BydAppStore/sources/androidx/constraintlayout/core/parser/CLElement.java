package androidx.constraintlayout.core.parser;

import java.io.PrintStream;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class CLElement {
    public static int BASE_INDENT = 2;
    public static int MAX_LINE = 80;
    private int line;
    public CLContainer mContainer;
    private final char[] mContent;
    public long start = -1;
    public long end = Long.MAX_VALUE;

    public CLElement(char[] cArr) {
        this.mContent = cArr;
    }

    public void addIndent(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(' ');
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String content() {
        /*
            r6 = this;
            java.lang.String r0 = new java.lang.String
            char[] r1 = r6.mContent
            r0.<init>(r1)
            long r1 = r6.end
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 == 0) goto L22
            long r3 = r6.start
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 >= 0) goto L19
            goto L22
        L19:
            int r3 = (int) r3
        L1a:
            int r1 = (int) r1
            int r1 = r1 + 1
            java.lang.String r0 = r0.substring(r3, r1)
            return r0
        L22:
            long r1 = r6.start
            int r3 = (int) r1
            goto L1a
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.parser.CLElement.content():java.lang.String");
    }

    public CLElement getContainer() {
        return this.mContainer;
    }

    public String getDebugName() {
        if (!CLParser.DEBUG) {
            return "";
        }
        return getStrClass() + " -> ";
    }

    public long getEnd() {
        return this.end;
    }

    public float getFloat() {
        if (this instanceof CLNumber) {
            return ((CLNumber) this).getFloat();
        }
        return Float.NaN;
    }

    public int getInt() {
        if (this instanceof CLNumber) {
            return ((CLNumber) this).getInt();
        }
        return 0;
    }

    public int getLine() {
        return this.line;
    }

    public long getStart() {
        return this.start;
    }

    public String getStrClass() {
        String string = getClass().toString();
        return string.substring(string.lastIndexOf(46) + 1);
    }

    public boolean isDone() {
        return this.end != Long.MAX_VALUE;
    }

    public boolean isStarted() {
        return this.start > -1;
    }

    public boolean notStarted() {
        return this.start == -1;
    }

    public void setContainer(CLContainer cLContainer) {
        this.mContainer = cLContainer;
    }

    public void setEnd(long j) {
        if (this.end != Long.MAX_VALUE) {
            return;
        }
        this.end = j;
        if (CLParser.DEBUG) {
            PrintStream printStream = System.out;
            StringBuilder sbK = e.k("closing ");
            sbK.append(hashCode());
            sbK.append(" -> ");
            sbK.append(this);
            printStream.println(sbK.toString());
        }
        CLContainer cLContainer = this.mContainer;
        if (cLContainer != null) {
            cLContainer.add(this);
        }
    }

    public void setLine(int i) {
        this.line = i;
    }

    public void setStart(long j) {
        this.start = j;
    }

    public String toFormattedJSON(int i, int i2) {
        return "";
    }

    public String toJSON() {
        return "";
    }

    public String toString() {
        long j = this.start;
        long j2 = this.end;
        if (j > j2 || j2 == Long.MAX_VALUE) {
            return getClass() + " (INVALID, " + this.start + "-" + this.end + ")";
        }
        return getStrClass() + " (" + this.start + " : " + this.end + ") <<" + new String(this.mContent).substring((int) this.start, ((int) this.end) + 1) + ">>";
    }
}

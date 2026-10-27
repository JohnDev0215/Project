package androidx.constraintlayout.core.parser;

import spi.d;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class CLParsingException extends Exception {
    private final String mElementClass;
    private final int mLineNumber;
    private final String mReason;

    public CLParsingException(String str, CLElement cLElement) {
        int line;
        this.mReason = str;
        if (cLElement != null) {
            this.mElementClass = cLElement.getStrClass();
            line = cLElement.getLine();
        } else {
            this.mElementClass = "unknown";
            line = 0;
        }
        this.mLineNumber = line;
    }

    public String reason() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mReason);
        sb.append(" (");
        sb.append(this.mElementClass);
        sb.append(" at line ");
        return d.h(sb, this.mLineNumber, ")");
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sbK = e.k("CLParsingException (");
        sbK.append(hashCode());
        sbK.append(") : ");
        sbK.append(reason());
        return sbK.toString();
    }
}

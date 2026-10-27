package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.ContentReference;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class JsonLocation implements Serializable {
    private static final long serialVersionUID = 2;
    protected final int _columnNr;
    protected final ContentReference _contentReference;
    protected final int _lineNr;
    protected final long _totalBytes;
    protected final long _totalChars;
    public transient String c;

    public JsonLocation(ContentReference contentReference, long j, long j2, int i, int i2) {
        this._contentReference = contentReference == null ? ContentReference.e : contentReference;
        this._totalBytes = j;
        this._totalChars = j2;
        this._lineNr = i;
        this._columnNr = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof JsonLocation)) {
            return false;
        }
        JsonLocation jsonLocation = (JsonLocation) obj;
        ContentReference contentReference = this._contentReference;
        if (contentReference == null) {
            if (jsonLocation._contentReference != null) {
                return false;
            }
        } else if (!contentReference.equals(jsonLocation._contentReference)) {
            return false;
        }
        return this._lineNr == jsonLocation._lineNr && this._columnNr == jsonLocation._columnNr && this._totalChars == jsonLocation._totalChars && this._totalBytes == jsonLocation._totalBytes;
    }

    public final int hashCode() {
        return ((((this._contentReference == null ? 1 : 2) ^ this._lineNr) + this._columnNr) ^ ((int) this._totalChars)) + ((int) this._totalBytes);
    }

    public final String toString() {
        if (this.c == null) {
            this.c = this._contentReference.b();
        }
        String str = this.c;
        StringBuilder sb = new StringBuilder(str.length() + 40);
        sb.append("[Source: ");
        sb.append(str);
        sb.append("; ");
        if (this._contentReference.c()) {
            sb.append("line: ");
            int i = this._lineNr;
            if (i >= 0) {
                sb.append(i);
            } else {
                sb.append("UNKNOWN");
            }
            sb.append(", column: ");
            int i2 = this._columnNr;
            if (i2 >= 0) {
                sb.append(i2);
            } else {
                sb.append("UNKNOWN");
            }
        } else if (this._lineNr > 0) {
            sb.append("line: ");
            sb.append(this._lineNr);
            if (this._columnNr > 0) {
                sb.append(", column: ");
                sb.append(this._columnNr);
            }
        } else {
            sb.append("byte offset: #");
            long j = this._totalBytes;
            if (j >= 0) {
                sb.append(j);
            } else {
                sb.append("UNKNOWN");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}

package org.apache.http.conn.ssl;

import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
final class SubjectName {
    private final int type;
    private final String value;

    SubjectName(String str, int i) {
        this.value = (String) Args.notNull(str, "Value");
        this.type = Args.positive(i, "Type");
    }

    public int getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }
}

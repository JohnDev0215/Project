package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: PatternMatcher.java */
/* JADX INFO: loaded from: classes3.dex */
public final class gy1 {
    public final ArrayList a = new ArrayList();

    /* JADX INFO: compiled from: PatternMatcher.java */
    public static class a extends Exception {
        public a(String str) {
            super(String.format("Illegal pattern '%s': %s", str, "Unmatched escape symbol at the end"));
        }
    }

    public gy1(String str) throws a {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (!z && cCharAt == '\\') {
                z = true;
            } else if (z || cCharAt != '*') {
                sb.append(cCharAt);
                z = false;
            } else {
                this.a.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        if (z) {
            throw new a(str);
        }
        this.a.add(sb.toString());
    }
}

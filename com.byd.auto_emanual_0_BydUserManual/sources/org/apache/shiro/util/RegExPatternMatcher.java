package org.apache.shiro.util;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class RegExPatternMatcher implements PatternMatcher {
    private static final int CASE_INSENSITIVE = 34;
    private static final int DEFAULT = 32;
    private boolean caseInsensitive = false;

    @Override // org.apache.shiro.util.PatternMatcher
    public boolean matches(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("pattern argument cannot be null.");
        }
        return Pattern.compile(str, this.caseInsensitive ? 34 : 32).matcher(str2).matches();
    }

    public boolean isCaseInsensitive() {
        return this.caseInsensitive;
    }

    public void setCaseInsensitive(boolean z) {
        this.caseInsensitive = z;
    }
}

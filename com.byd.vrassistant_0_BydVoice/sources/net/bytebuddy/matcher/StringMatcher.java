package net.bytebuddy.matcher;

import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class StringMatcher extends ElementMatcher.Junction.AbstractBase<String> {
    private final Mode mode;
    private final String value;

    protected boolean canEqual(Object obj) {
        return obj instanceof StringMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StringMatcher)) {
            return false;
        }
        StringMatcher stringMatcher = (StringMatcher) obj;
        if (!stringMatcher.canEqual(this)) {
            return false;
        }
        String str = this.value;
        String str2 = stringMatcher.value;
        if (str != null ? !str.equals(str2) : str2 != null) {
            return false;
        }
        Mode mode = this.mode;
        Mode mode2 = stringMatcher.mode;
        return mode != null ? mode.equals(mode2) : mode2 == null;
    }

    public int hashCode() {
        String str = this.value;
        int iHashCode = str == null ? 43 : str.hashCode();
        Mode mode = this.mode;
        return ((iHashCode + 59) * 59) + (mode != null ? mode.hashCode() : 43);
    }

    public StringMatcher(String str, Mode mode) {
        this.value = str;
        this.mode = mode;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(String str) {
        return this.mode.matches(this.value, str);
    }

    public String toString() {
        return this.mode.getDescription() + '(' + this.value + ')';
    }

    public enum Mode {
        EQUALS_FULLY("equals") { // from class: net.bytebuddy.matcher.StringMatcher.Mode.1
            @Override // net.bytebuddy.matcher.StringMatcher.Mode
            protected boolean matches(String str, String str2) {
                return str2.equals(str);
            }
        },
        EQUALS_FULLY_IGNORE_CASE("equalsIgnoreCase") { // from class: net.bytebuddy.matcher.StringMatcher.Mode.2
            @Override // net.bytebuddy.matcher.StringMatcher.Mode
            protected boolean matches(String str, String str2) {
                return str2.equalsIgnoreCase(str);
            }
        },
        STARTS_WITH("startsWith") { // from class: net.bytebuddy.matcher.StringMatcher.Mode.3
            @Override // net.bytebuddy.matcher.StringMatcher.Mode
            protected boolean matches(String str, String str2) {
                return str2.startsWith(str);
            }
        },
        STARTS_WITH_IGNORE_CASE("startsWithIgnoreCase") { // from class: net.bytebuddy.matcher.StringMatcher.Mode.4
            @Override // net.bytebuddy.matcher.StringMatcher.Mode
            protected boolean matches(String str, String str2) {
                return str2.toLowerCase().startsWith(str.toLowerCase());
            }
        },
        ENDS_WITH("endsWith") { // from class: net.bytebuddy.matcher.StringMatcher.Mode.5
            @Override // net.bytebuddy.matcher.StringMatcher.Mode
            protected boolean matches(String str, String str2) {
                return str2.endsWith(str);
            }
        },
        ENDS_WITH_IGNORE_CASE("endsWithIgnoreCase") { // from class: net.bytebuddy.matcher.StringMatcher.Mode.6
            @Override // net.bytebuddy.matcher.StringMatcher.Mode
            protected boolean matches(String str, String str2) {
                return str2.toLowerCase().endsWith(str.toLowerCase());
            }
        },
        CONTAINS("contains") { // from class: net.bytebuddy.matcher.StringMatcher.Mode.7
            @Override // net.bytebuddy.matcher.StringMatcher.Mode
            protected boolean matches(String str, String str2) {
                return str2.contains(str);
            }
        },
        CONTAINS_IGNORE_CASE("containsIgnoreCase") { // from class: net.bytebuddy.matcher.StringMatcher.Mode.8
            @Override // net.bytebuddy.matcher.StringMatcher.Mode
            protected boolean matches(String str, String str2) {
                return str2.toLowerCase().contains(str.toLowerCase());
            }
        },
        MATCHES("matches") { // from class: net.bytebuddy.matcher.StringMatcher.Mode.9
            @Override // net.bytebuddy.matcher.StringMatcher.Mode
            protected boolean matches(String str, String str2) {
                return str2.matches(str);
            }
        };

        private final String description;

        protected abstract boolean matches(String str, String str2);

        Mode(String str) {
            this.description = str;
        }

        protected String getDescription() {
            return this.description;
        }
    }
}

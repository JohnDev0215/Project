package com.google.common.base;

import java.util.Arrays;

/* JADX INFO: loaded from: classes6.dex */
public abstract class CharMatcher implements Predicate<Character> {
    public abstract boolean matches(char c);

    public static CharMatcher any() {
        return Any.INSTANCE;
    }

    public static CharMatcher none() {
        return None.INSTANCE;
    }

    public static CharMatcher whitespace() {
        return Whitespace.INSTANCE;
    }

    public static CharMatcher ascii() {
        return Ascii.INSTANCE;
    }

    @Deprecated
    public static CharMatcher digit() {
        return Digit.INSTANCE;
    }

    public static CharMatcher is(char c) {
        return new Is(c);
    }

    public static CharMatcher isNot(char c) {
        return new IsNot(c);
    }

    public static CharMatcher anyOf(CharSequence charSequence) {
        int length = charSequence.length();
        if (length == 0) {
            return none();
        }
        if (length == 1) {
            return is(charSequence.charAt(0));
        }
        if (length == 2) {
            return isEither(charSequence.charAt(0), charSequence.charAt(1));
        }
        return new AnyOf(charSequence);
    }

    public static CharMatcher inRange(char c, char c2) {
        return new InRange(c, c2);
    }

    protected CharMatcher() {
    }

    public CharMatcher negate() {
        return new Negated(this);
    }

    public CharMatcher or(CharMatcher charMatcher) {
        return new Or(this, charMatcher);
    }

    public boolean matchesAllOf(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!matches(charSequence.charAt(length))) {
                return false;
            }
        }
        return true;
    }

    public boolean matchesNoneOf(CharSequence charSequence) {
        return indexIn(charSequence) == -1;
    }

    public int indexIn(CharSequence charSequence) {
        return indexIn(charSequence, 0);
    }

    public int indexIn(CharSequence charSequence, int i) {
        int length = charSequence.length();
        Preconditions.checkPositionIndex(i, length);
        while (i < length) {
            if (matches(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public String removeFrom(CharSequence charSequence) {
        String string = charSequence.toString();
        int iIndexIn = indexIn(string);
        if (iIndexIn == -1) {
            return string;
        }
        char[] charArray = string.toCharArray();
        int i = 1;
        while (true) {
            iIndexIn++;
            while (iIndexIn != charArray.length) {
                if (matches(charArray[iIndexIn])) {
                    break;
                }
                charArray[iIndexIn - i] = charArray[iIndexIn];
                iIndexIn++;
            }
            return new String(charArray, 0, iIndexIn - i);
            i++;
        }
    }

    public String retainFrom(CharSequence charSequence) {
        return negate().removeFrom(charSequence);
    }

    public String replaceFrom(CharSequence charSequence, char c) {
        String string = charSequence.toString();
        int iIndexIn = indexIn(string);
        if (iIndexIn == -1) {
            return string;
        }
        char[] charArray = string.toCharArray();
        charArray[iIndexIn] = c;
        while (true) {
            iIndexIn++;
            if (iIndexIn < charArray.length) {
                if (matches(charArray[iIndexIn])) {
                    charArray[iIndexIn] = c;
                }
            } else {
                return new String(charArray);
            }
        }
    }

    @Override // com.google.common.base.Predicate
    @Deprecated
    public boolean apply(Character ch) {
        return matches(ch.charValue());
    }

    public String toString() {
        return super.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String showCharacter(char c) {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(cArr);
    }

    static abstract class FastMatcher extends CharMatcher {
        FastMatcher() {
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return new NegatedFastMatcher(this);
        }
    }

    static abstract class NamedFastMatcher extends FastMatcher {
        private final String description;

        NamedFastMatcher(String str) {
            this.description = (String) Preconditions.checkNotNull(str);
        }

        @Override // com.google.common.base.CharMatcher
        public final String toString() {
            return this.description;
        }
    }

    static class NegatedFastMatcher extends Negated {
        NegatedFastMatcher(CharMatcher charMatcher) {
            super(charMatcher);
        }
    }

    private static final class Any extends NamedFastMatcher {
        static final Any INSTANCE = new Any();

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return true;
        }

        private Any() {
            super("CharMatcher.any()");
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence) {
            return charSequence.length() == 0 ? -1 : 0;
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence, int i) {
            int length = charSequence.length();
            Preconditions.checkPositionIndex(i, length);
            if (i == length) {
                return -1;
            }
            return i;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence charSequence) {
            return charSequence.length() == 0;
        }

        @Override // com.google.common.base.CharMatcher
        public String removeFrom(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return "";
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, char c) {
            char[] cArr = new char[charSequence.length()];
            Arrays.fill(cArr, c);
            return new String(cArr);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher charMatcher) {
            Preconditions.checkNotNull(charMatcher);
            return this;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return CharMatcher.none();
        }
    }

    private static final class None extends NamedFastMatcher {
        static final None INSTANCE = new None();

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return false;
        }

        private None() {
            super("CharMatcher.none()");
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence, int i) {
            Preconditions.checkPositionIndex(i, charSequence.length());
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence charSequence) {
            return charSequence.length() == 0;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public String removeFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, char c) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher charMatcher) {
            return (CharMatcher) Preconditions.checkNotNull(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return CharMatcher.any();
        }
    }

    static final class Whitespace extends NamedFastMatcher {
        static final int SHIFT = Integer.numberOfLeadingZeros(31);
        static final Whitespace INSTANCE = new Whitespace();

        Whitespace() {
            super("CharMatcher.whitespace()");
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return "\u2002\u3000\r\u0085\u200a\u2005\u2000\u3000\u2029\u000b\u3000\u2008\u2003\u205f\u3000\u1680\t \u2006\u2001  \f\u2009\u3000\u2004\u3000\u3000\u2028\n \u3000".charAt((48906 * c) >>> SHIFT) == c;
        }
    }

    private static final class Ascii extends NamedFastMatcher {
        static final Ascii INSTANCE = new Ascii();

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return c <= 127;
        }

        Ascii() {
            super("CharMatcher.ascii()");
        }
    }

    private static class RangesMatcher extends CharMatcher {
        private final String description;
        private final char[] rangeEnds;
        private final char[] rangeStarts;

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        RangesMatcher(String str, char[] cArr, char[] cArr2) {
            this.description = str;
            this.rangeStarts = cArr;
            this.rangeEnds = cArr2;
            Preconditions.checkArgument(cArr.length == cArr2.length);
            int i = 0;
            while (i < cArr.length) {
                Preconditions.checkArgument(cArr[i] <= cArr2[i]);
                int i2 = i + 1;
                if (i2 < cArr.length) {
                    Preconditions.checkArgument(cArr2[i] < cArr[i2]);
                }
                i = i2;
            }
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            int iBinarySearch = Arrays.binarySearch(this.rangeStarts, c);
            if (iBinarySearch >= 0) {
                return true;
            }
            int i = (~iBinarySearch) - 1;
            return i >= 0 && c <= this.rangeEnds[i];
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return this.description;
        }
    }

    private static final class Digit extends RangesMatcher {
        static final Digit INSTANCE = new Digit();

        private static char[] zeroes() {
            return "0٠۰߀०০੦૦୦௦౦೦൦෦๐໐༠၀႐០᠐᥆᧐᪀᪐᭐᮰᱀᱐꘠꣐꤀꧐꧰꩐꯰０".toCharArray();
        }

        private static char[] nines() {
            char[] cArr = new char[37];
            for (int i = 0; i < 37; i++) {
                cArr[i] = (char) ("0٠۰߀०০੦૦୦௦౦೦൦෦๐໐༠၀႐០᠐᥆᧐᪀᪐᭐᮰᱀᱐꘠꣐꤀꧐꧰꩐꯰０".charAt(i) + '\t');
            }
            return cArr;
        }

        private Digit() {
            super("CharMatcher.digit()", zeroes(), nines());
        }
    }

    private static class Negated extends CharMatcher {
        final CharMatcher original;

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        Negated(CharMatcher charMatcher) {
            this.original = (CharMatcher) Preconditions.checkNotNull(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return !this.original.matches(c);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence charSequence) {
            return this.original.matchesNoneOf(charSequence);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence charSequence) {
            return this.original.matchesAllOf(charSequence);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return this.original;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strValueOf = String.valueOf(this.original);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 9);
            sb.append(strValueOf);
            sb.append(".negate()");
            return sb.toString();
        }
    }

    private static final class Or extends CharMatcher {
        final CharMatcher first;
        final CharMatcher second;

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        Or(CharMatcher charMatcher, CharMatcher charMatcher2) {
            this.first = (CharMatcher) Preconditions.checkNotNull(charMatcher);
            this.second = (CharMatcher) Preconditions.checkNotNull(charMatcher2);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.first.matches(c) || this.second.matches(c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strValueOf = String.valueOf(this.first);
            String strValueOf2 = String.valueOf(this.second);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 18 + strValueOf2.length());
            sb.append("CharMatcher.or(");
            sb.append(strValueOf);
            sb.append(", ");
            sb.append(strValueOf2);
            sb.append(")");
            return sb.toString();
        }
    }

    private static final class Is extends FastMatcher {
        private final char match;

        Is(char c) {
            this.match = c;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return c == this.match;
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, char c) {
            return charSequence.toString().replace(this.match, c);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? charMatcher : super.or(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return CharMatcher.isNot(this.match);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strShowCharacter = CharMatcher.showCharacter(this.match);
            StringBuilder sb = new StringBuilder(String.valueOf(strShowCharacter).length() + 18);
            sb.append("CharMatcher.is('");
            sb.append(strShowCharacter);
            sb.append("')");
            return sb.toString();
        }
    }

    private static final class IsNot extends FastMatcher {
        private final char match;

        IsNot(char c) {
            this.match = c;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return c != this.match;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? CharMatcher.any() : this;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return CharMatcher.is(this.match);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strShowCharacter = CharMatcher.showCharacter(this.match);
            StringBuilder sb = new StringBuilder(String.valueOf(strShowCharacter).length() + 21);
            sb.append("CharMatcher.isNot('");
            sb.append(strShowCharacter);
            sb.append("')");
            return sb.toString();
        }
    }

    private static IsEither isEither(char c, char c2) {
        return new IsEither(c, c2);
    }

    private static final class IsEither extends FastMatcher {
        private final char match1;
        private final char match2;

        IsEither(char c, char c2) {
            this.match1 = c;
            this.match2 = c2;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return c == this.match1 || c == this.match2;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strShowCharacter = CharMatcher.showCharacter(this.match1);
            String strShowCharacter2 = CharMatcher.showCharacter(this.match2);
            StringBuilder sb = new StringBuilder(String.valueOf(strShowCharacter).length() + 21 + String.valueOf(strShowCharacter2).length());
            sb.append("CharMatcher.anyOf(\"");
            sb.append(strShowCharacter);
            sb.append(strShowCharacter2);
            sb.append("\")");
            return sb.toString();
        }
    }

    private static final class AnyOf extends CharMatcher {
        private final char[] chars;

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        public AnyOf(CharSequence charSequence) {
            char[] charArray = charSequence.toString().toCharArray();
            this.chars = charArray;
            Arrays.sort(charArray);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Arrays.binarySearch(this.chars, c) >= 0;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            StringBuilder sb = new StringBuilder("CharMatcher.anyOf(\"");
            for (char c : this.chars) {
                sb.append(CharMatcher.showCharacter(c));
            }
            sb.append("\")");
            return sb.toString();
        }
    }

    private static final class InRange extends FastMatcher {
        private final char endInclusive;
        private final char startInclusive;

        InRange(char c, char c2) {
            Preconditions.checkArgument(c2 >= c);
            this.startInclusive = c;
            this.endInclusive = c2;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.startInclusive <= c && c <= this.endInclusive;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            String strShowCharacter = CharMatcher.showCharacter(this.startInclusive);
            String strShowCharacter2 = CharMatcher.showCharacter(this.endInclusive);
            StringBuilder sb = new StringBuilder(String.valueOf(strShowCharacter).length() + 27 + String.valueOf(strShowCharacter2).length());
            sb.append("CharMatcher.inRange('");
            sb.append(strShowCharacter);
            sb.append("', '");
            sb.append(strShowCharacter2);
            sb.append("')");
            return sb.toString();
        }
    }
}

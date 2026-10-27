package java9.util;

/* JADX INFO: loaded from: classes3.dex */
public final class StringJoiner {
    private final String delimiter;
    private String emptyValue;
    private final String prefix;
    private final String suffix;
    private StringBuilder value;

    public StringJoiner(CharSequence charSequence) {
        this(charSequence, "", "");
    }

    public StringJoiner(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        Objects.requireNonNull(charSequence2, "The prefix must not be null");
        Objects.requireNonNull(charSequence, "The delimiter must not be null");
        Objects.requireNonNull(charSequence3, "The suffix must not be null");
        String string = charSequence2.toString();
        this.prefix = string;
        this.delimiter = charSequence.toString();
        String string2 = charSequence3.toString();
        this.suffix = string2;
        this.emptyValue = string + string2;
    }

    public StringJoiner setEmptyValue(CharSequence charSequence) {
        this.emptyValue = ((CharSequence) Objects.requireNonNull(charSequence, "The empty value must not be null")).toString();
        return this;
    }

    public String toString() {
        if (this.value == null) {
            return this.emptyValue;
        }
        if (this.suffix.equals("")) {
            return this.value.toString();
        }
        int length = this.value.length();
        String string = this.value.append(this.suffix).toString();
        this.value.setLength(length);
        return string;
    }

    public StringJoiner add(CharSequence charSequence) {
        prepareBuilder().append(charSequence);
        return this;
    }

    public StringJoiner merge(StringJoiner stringJoiner) {
        Objects.requireNonNull(stringJoiner);
        StringBuilder sb = stringJoiner.value;
        if (sb != null) {
            prepareBuilder().append((CharSequence) stringJoiner.value, stringJoiner.prefix.length(), sb.length());
        }
        return this;
    }

    private StringBuilder prepareBuilder() {
        StringBuilder sb = this.value;
        if (sb != null) {
            sb.append(this.delimiter);
        } else {
            this.value = new StringBuilder().append(this.prefix);
        }
        return this.value;
    }

    public int length() {
        StringBuilder sb = this.value;
        return sb != null ? sb.length() + this.suffix.length() : this.emptyValue.length();
    }
}

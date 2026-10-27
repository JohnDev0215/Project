package N0;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final w f4418b = new w(false);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f4419a;

    public w() {
        this.f4419a = false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof w) {
            return this.f4419a == ((w) obj).f4419a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(0) + (Boolean.hashCode(this.f4419a) * 31);
    }

    public final String toString() {
        return "PlatformParagraphStyle(includeFontPadding=" + this.f4419a + ", emojiSupportMatch=EmojiSupportMatch.Default)";
    }

    public w(boolean z6) {
        this.f4419a = z6;
    }
}

package O0;

import java.text.CharacterIterator;

/* JADX INFO: loaded from: classes.dex */
public final class j implements CharacterIterator {
    public final CharSequence l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f5016m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5017n = 0;

    public j(int i7, CharSequence charSequence) {
        this.l = charSequence;
        this.f5016m = i7;
    }

    @Override // java.text.CharacterIterator
    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    @Override // java.text.CharacterIterator
    public final char current() {
        int i7 = this.f5017n;
        if (i7 == this.f5016m) {
            return (char) 65535;
        }
        return this.l.charAt(i7);
    }

    @Override // java.text.CharacterIterator
    public final char first() {
        this.f5017n = 0;
        return current();
    }

    @Override // java.text.CharacterIterator
    public final int getBeginIndex() {
        return 0;
    }

    @Override // java.text.CharacterIterator
    public final int getEndIndex() {
        return this.f5016m;
    }

    @Override // java.text.CharacterIterator
    public final int getIndex() {
        return this.f5017n;
    }

    @Override // java.text.CharacterIterator
    public final char last() {
        int i7 = this.f5016m;
        if (i7 == 0) {
            this.f5017n = i7;
            return (char) 65535;
        }
        int i10 = i7 - 1;
        this.f5017n = i10;
        return this.l.charAt(i10);
    }

    @Override // java.text.CharacterIterator
    public final char next() {
        int i7 = this.f5017n + 1;
        this.f5017n = i7;
        int i10 = this.f5016m;
        if (i7 < i10) {
            return this.l.charAt(i7);
        }
        this.f5017n = i10;
        return (char) 65535;
    }

    @Override // java.text.CharacterIterator
    public final char previous() {
        int i7 = this.f5017n;
        if (i7 <= 0) {
            return (char) 65535;
        }
        int i10 = i7 - 1;
        this.f5017n = i10;
        return this.l.charAt(i10);
    }

    @Override // java.text.CharacterIterator
    public final char setIndex(int i7) {
        if (i7 > this.f5016m || i7 < 0) {
            throw new IllegalArgumentException("invalid position");
        }
        this.f5017n = i7;
        return current();
    }
}

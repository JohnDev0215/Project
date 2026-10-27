package g4;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class s implements Appendable {
    public final Appendable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f14951m = true;

    public s(Appendable appendable) {
        this.l = appendable;
    }

    @Override // java.lang.Appendable
    public final Appendable append(char c) throws IOException {
        boolean z6 = this.f14951m;
        Appendable appendable = this.l;
        if (z6) {
            this.f14951m = false;
            appendable.append("  ");
        }
        this.f14951m = c == '\n';
        appendable.append(c);
        return this;
    }

    @Override // java.lang.Appendable
    public final Appendable append(CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            charSequence = "";
        }
        append(charSequence, 0, charSequence.length());
        return this;
    }

    @Override // java.lang.Appendable
    public final Appendable append(CharSequence charSequence, int i7, int i10) throws IOException {
        if (charSequence == null) {
            charSequence = "";
        }
        boolean z6 = this.f14951m;
        Appendable appendable = this.l;
        boolean z10 = false;
        if (z6) {
            this.f14951m = false;
            appendable.append("  ");
        }
        if (charSequence.length() > 0 && charSequence.charAt(i10 - 1) == '\n') {
            z10 = true;
        }
        this.f14951m = z10;
        appendable.append(charSequence, i7, i10);
        return this;
    }
}

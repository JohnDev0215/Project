package t9;

/* JADX INFO: loaded from: classes.dex */
public final class g extends y9.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w9.f f22537a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f22538b;
    public final StringBuilder c;

    public g(char c, int i7, int i10) {
        w9.f fVar = new w9.f();
        this.f22537a = fVar;
        this.c = new StringBuilder();
        fVar.f23366g = c;
        fVar.f23367h = i7;
        fVar.f23368i = i10;
    }

    @Override // y9.a
    public final void a(CharSequence charSequence) {
        if (this.f22538b == null) {
            this.f22538b = charSequence.toString();
            return;
        }
        StringBuilder sb = this.c;
        sb.append(charSequence);
        sb.append('\n');
    }

    @Override // y9.a
    public final void c() {
        String strA = v9.a.a(this.f22538b.trim());
        w9.f fVar = this.f22537a;
        fVar.f23369j = strA;
        fVar.f23370k = this.c.toString();
    }

    @Override // y9.a
    public final w9.a d() {
        return this.f22537a;
    }

    @Override // y9.a
    public final P2.g g(f fVar) {
        int i7 = fVar.f22528e;
        int i10 = fVar.f22527b;
        CharSequence charSequence = fVar.f22526a;
        int i11 = fVar.f22530g;
        w9.f fVar2 = this.f22537a;
        if (i11 < 4) {
            char c = fVar2.f23366g;
            int i12 = fVar2.f23367h;
            int iA = h6.i.A(c, i7, charSequence.length(), charSequence) - i7;
            if (iA >= i12 && h6.i.B(charSequence, i7 + iA, charSequence.length()) == charSequence.length()) {
                return new P2.g(-1, -1, true);
            }
        }
        int length = charSequence.length();
        for (int i13 = fVar2.f23368i; i13 > 0 && i10 < length && charSequence.charAt(i10) == ' '; i13--) {
            i10++;
        }
        return P2.g.a(i10);
    }
}

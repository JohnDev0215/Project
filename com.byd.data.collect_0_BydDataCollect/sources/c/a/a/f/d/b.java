package c.a.a.f.d;

import c.a.a.d.d;

/* JADX INFO: compiled from: StrFinder.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends c {
    private static final long serialVersionUID = 1;
    private final boolean caseInsensitive;
    private final CharSequence strToFind;

    public b(CharSequence charSequence, boolean z) {
        d.f(charSequence);
        this.strToFind = charSequence;
        this.caseInsensitive = z;
    }

    @Override // c.a.a.f.d.a
    public int a(int i2) {
        d.j(this.text, "Text to find must be not null!", new Object[0]);
        int length = this.strToFind.length();
        if (i2 < 0) {
            i2 = 0;
        }
        int iB = b();
        if (this.negative) {
            while (i2 > iB) {
                if (c.a.a.f.b.j(this.text, i2, this.strToFind, 0, length, this.caseInsensitive)) {
                    return i2;
                }
                i2--;
            }
            return -1;
        }
        int i3 = (iB - length) + 1;
        while (i2 < i3) {
            if (c.a.a.f.b.j(this.text, i2, this.strToFind, 0, length, this.caseInsensitive)) {
                return i2;
            }
            i2++;
        }
        return -1;
    }
}

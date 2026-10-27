package cn.hutool.core.text.finder;

import c.a.a.c.d;
import c.a.a.d.b;

/* JADX INFO: loaded from: classes.dex */
public class StrFinder extends TextFinder {
    private static final long serialVersionUID = 1;
    private final boolean caseInsensitive;
    private final CharSequence strToFind;

    public StrFinder(CharSequence charSequence, boolean z) {
        d.f(charSequence);
        this.strToFind = charSequence;
        this.caseInsensitive = z;
    }

    @Override // cn.hutool.core.text.finder.TextFinder
    public int end(int i) {
        if (i < 0) {
            return -1;
        }
        return i + this.strToFind.length();
    }

    @Override // cn.hutool.core.text.finder.TextFinder, cn.hutool.core.text.finder.a
    public /* bridge */ /* synthetic */ a reset() {
        return super.reset();
    }

    @Override // cn.hutool.core.text.finder.TextFinder
    public int start(int i) {
        d.j(this.text, "Text to find must be not null!", new Object[0]);
        int length = this.strToFind.length();
        if (i < 0) {
            i = 0;
        }
        int validEndIndex = getValidEndIndex();
        if (this.negative) {
            while (i > validEndIndex) {
                if (b.k(this.text, i, this.strToFind, 0, length, this.caseInsensitive)) {
                    return i;
                }
                i--;
            }
            return -1;
        }
        int i2 = (validEndIndex - length) + 1;
        while (i < i2) {
            if (b.k(this.text, i, this.strToFind, 0, length, this.caseInsensitive)) {
                return i;
            }
            i++;
        }
        return -1;
    }
}

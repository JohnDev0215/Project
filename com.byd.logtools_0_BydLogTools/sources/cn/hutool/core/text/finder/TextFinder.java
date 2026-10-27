package cn.hutool.core.text.finder;

import c.a.a.c.d;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class TextFinder implements a, Serializable {
    private static final long serialVersionUID = 1;
    protected int endIndex = -1;
    protected boolean negative;
    protected CharSequence text;

    public abstract /* synthetic */ int end(int i);

    protected int getValidEndIndex() {
        if (this.negative && -1 == this.endIndex) {
            return -1;
        }
        int i = this.endIndex;
        return i < 0 ? i + this.text.length() + 1 : Math.min(i, this.text.length());
    }

    @Override // cn.hutool.core.text.finder.a
    public /* bridge */ /* synthetic */ a reset() {
        return super.reset();
    }

    public TextFinder setEndIndex(int i) {
        this.endIndex = i;
        return this;
    }

    public TextFinder setNegative(boolean z) {
        this.negative = z;
        return this;
    }

    public TextFinder setText(CharSequence charSequence) {
        this.text = (CharSequence) d.j(charSequence, "Text must be not null!", new Object[0]);
        return this;
    }

    public abstract /* synthetic */ int start(int i);
}

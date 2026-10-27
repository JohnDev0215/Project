package c.a.a.f.d;

import c.a.a.d.d;
import java.io.Serializable;

/* JADX INFO: compiled from: TextFinder.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c implements a, Serializable {
    private static final long serialVersionUID = 1;
    protected int endIndex = -1;
    protected boolean negative;
    protected CharSequence text;

    protected int b() {
        if (this.negative && -1 == this.endIndex) {
            return -1;
        }
        int i2 = this.endIndex;
        return i2 < 0 ? i2 + this.text.length() + 1 : Math.min(i2, this.text.length());
    }

    public c d(CharSequence charSequence) {
        this.text = (CharSequence) d.j(charSequence, "Text must be not null!", new Object[0]);
        return this;
    }
}

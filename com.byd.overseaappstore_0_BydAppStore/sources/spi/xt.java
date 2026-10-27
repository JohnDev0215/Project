package spi;

import spi.xt;

/* JADX INFO: loaded from: classes.dex */
public abstract class xt<CHILD extends xt<CHILD, TranscodeType>, TranscodeType> implements Cloneable {
    public wt<? super TranscodeType> a = (wt<? super TranscodeType>) sj.b;

    public final CHILD a() {
        try {
            return (CHILD) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public final Object clone() throws CloneNotSupportedException {
        try {
            return (xt) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

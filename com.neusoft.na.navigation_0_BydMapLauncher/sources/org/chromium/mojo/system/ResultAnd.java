package org.chromium.mojo.system;

/* JADX INFO: loaded from: classes3.dex */
public class ResultAnd<A> {
    private final int mMojoResult;
    private final A mValue;

    public ResultAnd(int i, A a) {
        this.mMojoResult = i;
        this.mValue = a;
    }

    public int getMojoResult() {
        return this.mMojoResult;
    }

    public A getValue() {
        return this.mValue;
    }
}

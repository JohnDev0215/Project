package com.google.common.base;

/* JADX INFO: loaded from: classes.dex */
abstract class CommonMatcher {
    public abstract int end();

    public abstract boolean find(int i);

    public abstract boolean matches();

    public abstract int start();

    CommonMatcher() {
    }
}

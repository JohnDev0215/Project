package com.byd.vrassistant.livedata;

import com.byd.vrassistant.livedata.LiveDataUtils;

/* JADX INFO: loaded from: classes.dex */
public class SafeZipper<A, B, R> implements LiveDataUtils.Zipper<A, B, R> {
    LiveDataUtils.Zipper<A, B, R> zipper;

    public SafeZipper(LiveDataUtils.Zipper<A, B, R> zipper) {
        this.zipper = zipper;
    }

    @Override // com.byd.vrassistant.livedata.LiveDataUtils.Zipper
    public R apply(A a, B b) {
        try {
            return this.zipper.apply(a, b);
        } catch (Exception unused) {
            return null;
        }
    }
}

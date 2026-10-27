package com.byd.vrassistant.livedata;

import com.byd.vrassistant.livedata.LiveDataUtils;

/* JADX INFO: loaded from: classes.dex */
public class SafeZipper3<A, B, C, R> implements LiveDataUtils.Zipper3<A, B, C, R> {
    LiveDataUtils.Zipper3<A, B, C, R> zipper3;

    public SafeZipper3(LiveDataUtils.Zipper3<A, B, C, R> zipper3) {
        this.zipper3 = zipper3;
    }

    @Override // com.byd.vrassistant.livedata.LiveDataUtils.Zipper3
    public R apply(A a, B b, C c) {
        try {
            return this.zipper3.apply(a, b, c);
        } catch (Exception unused) {
            return null;
        }
    }
}

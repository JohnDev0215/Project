package com.google.android.material.datepicker;

import android.os.Parcelable;
import java.util.Collection;
import k0.d;

/* JADX INFO: loaded from: classes.dex */
public interface DateSelector<S> extends Parcelable {
    S a();

    Collection<d<Long, Long>> b();

    void d(long j3);

    Collection<Long> e();
}

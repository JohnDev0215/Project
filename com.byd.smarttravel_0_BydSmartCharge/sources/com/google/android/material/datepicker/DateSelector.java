package com.google.android.material.datepicker;

import android.os.Parcelable;
import b.g.i.d;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public interface DateSelector<S> extends Parcelable {
    S a();

    Collection<d<Long, Long>> b();

    void d(long j);

    Collection<Long> e();
}

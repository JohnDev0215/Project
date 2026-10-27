package com.google.android.material.datepicker;

import android.os.Parcelable;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public interface DateSelector<S> extends Parcelable {
    S a();

    Collection<b.g.j.d<Long, Long>> b();

    void d(long j);

    Collection<Long> e();
}

package com.google.android.material.datepicker;

import android.os.Parcelable;
import java.util.Collection;
import n0.d;

/* JADX INFO: loaded from: classes2.dex */
public interface DateSelector<S> extends Parcelable {
    Collection<d<Long, Long>> a();

    Collection<Long> c();

    S d();

    void e(long j8);
}

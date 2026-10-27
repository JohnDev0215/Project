package com.google.android.material.datepicker;

import android.os.Parcelable;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public interface DateSelector<S> extends Parcelable {
    S b();

    Collection<b.g.j.d<Long, Long>> d();

    void g(long j);

    Collection<Long> h();
}

package com.google.android.material.datepicker;

import android.os.Parcelable;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public interface DateSelector<S> extends Parcelable {
    Collection<c.g.j.d<Long, Long>> a();

    Collection<Long> c();

    S d();

    void e(long j);
}

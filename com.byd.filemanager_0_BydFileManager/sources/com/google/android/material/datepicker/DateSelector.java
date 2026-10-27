package com.google.android.material.datepicker;

import android.os.Parcelable;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public interface DateSelector<S> extends Parcelable {
    S d();

    Collection<b.g.j.d<Long, Long>> i();

    void p(long j);

    Collection<Long> s();
}

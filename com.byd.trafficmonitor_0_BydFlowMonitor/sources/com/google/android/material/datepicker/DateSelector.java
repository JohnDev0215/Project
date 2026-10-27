package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import b.h.l.c;
import c.b.a.a.x.k;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public interface DateSelector<S> extends Parcelable {
    S a();

    String b(Context context);

    int c(Context context);

    Collection<c<Long, Long>> d();

    boolean f();

    void g(long j);

    View h(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints calendarConstraints, k<S> kVar);

    Collection<Long> i();
}

package com.amazon.music.platform.aom.observables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PropertyObservable.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0005\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0006\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u0003J\u0006\u0010\u0007\u001a\u00020\u0004J\u0015\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0004\b\t\u0010\nR&\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u00030\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/aom/observables/PropertyObservable;", "T", "", "Lkotlin/Function1;", "", "observer", "addObserver", "clearObservers", "newVal", "notifyObservers", "(Ljava/lang/Object;)V", "", "observers", "Ljava/util/List;", "<init>", "()V", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public abstract class PropertyObservable<T> {
    private final List<Function1<T, Unit>> observers = new ArrayList();

    public final void addObserver(Function1<? super T, Unit> observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        this.observers.add(observer);
    }

    public final void clearObservers() {
        this.observers.clear();
    }

    public final void notifyObservers(T newVal) {
        Iterator<T> it = this.observers.iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(newVal);
        }
    }
}

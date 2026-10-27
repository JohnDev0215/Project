package d.a.t;

import d.a.g;
import d.a.i;

/* JADX INFO: compiled from: Subject.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<T> extends g<T> implements i<T> {
    public final c<T> x() {
        return this instanceof b ? this : new b(this);
    }
}

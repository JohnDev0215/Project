package io.reactivex.rxjava3.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import p.q30;

/* JADX INFO: loaded from: classes.dex */
public final class a extends q30 {
    public final /* synthetic */ int i;
    public final Object j;

    public /* synthetic */ a(int i, Object obj) {
        this.i = i;
        this.j = obj;
    }

    public final a w(Object obj) {
        int i = this.i;
        Object obj2 = this.j;
        switch (i) {
            case 0:
                ((PrintStream) obj2).print(obj);
                break;
            default:
                ((PrintWriter) obj2).print(obj);
                break;
        }
        return this;
    }
}

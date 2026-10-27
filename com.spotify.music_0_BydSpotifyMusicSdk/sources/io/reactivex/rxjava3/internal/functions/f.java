package io.reactivex.rxjava3.internal.functions;

import io.reactivex.rxjava3.core.Notification;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class f implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final io.reactivex.rxjava3.functions.g e;

    public /* synthetic */ f(io.reactivex.rxjava3.functions.g gVar, int i) {
        this.c = i;
        this.e = gVar;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        int i = this.c;
        io.reactivex.rxjava3.functions.g gVar = this.e;
        switch (i) {
            case 0:
                gVar.accept(Notification.a((Throwable) obj));
                break;
            default:
                Objects.requireNonNull(obj, "value is null");
                gVar.accept(new Notification(obj));
                break;
        }
    }
}

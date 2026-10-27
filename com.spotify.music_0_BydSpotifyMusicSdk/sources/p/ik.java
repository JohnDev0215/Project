package p;

import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException;
import io.reactivex.rxjava3.exceptions.UndeliverableException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.io.IOException;
import retrofit2.HttpException;

/* JADX INFO: loaded from: classes.dex */
public final class ik implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;

    public ik() {
        this.c = 0;
    }

    public static void b(Throwable th) {
        Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
    }

    public final void a(Throwable th) {
        switch (this.c) {
            case 0:
                if (!(th instanceof UndeliverableException)) {
                    if (th instanceof CompositeException) {
                        b((CompositeException) th);
                    } else if (th instanceof MissingBackpressureException) {
                        b((MissingBackpressureException) th);
                    } else if (!(th instanceof OnErrorNotImplementedException)) {
                        b(th);
                    } else {
                        b(((OnErrorNotImplementedException) th).getCause());
                    }
                    break;
                } else {
                    UndeliverableException undeliverableException = (UndeliverableException) th;
                    v03.j(undeliverableException, "error");
                    if (!(undeliverableException.getCause() instanceof HttpException)) {
                        Throwable cause = undeliverableException.getCause();
                        if (!(cause instanceof IOException) && !(cause instanceof InterruptedException)) {
                            b(cause);
                        }
                        break;
                    }
                }
                break;
            case 1:
            default:
                RxJavaPlugins.b(new OnErrorNotImplementedException(th));
                break;
            case 2:
                RxJavaPlugins.b(th);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        switch (this.c) {
            case 0:
                a((Throwable) obj);
                break;
            case 1:
                break;
            case 2:
                a((Throwable) obj);
                break;
            default:
                a((Throwable) obj);
                break;
        }
    }

    public final String toString() {
        switch (this.c) {
            case 1:
                return "EmptyConsumer";
            default:
                return super.toString();
        }
    }

    public /* synthetic */ ik(int i) {
        this.c = i;
    }
}

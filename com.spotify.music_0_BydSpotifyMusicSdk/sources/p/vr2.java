package p;

import android.net.UrlQuerySanitizer;
import com.google.common.base.Optional;
import com.google.protobuf.ByteString;
import com.spotify.common.uri.SpotifyUriParserException;
import com.spotify.player.model.PlayerState;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.a;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlinx.coroutines.CoroutineStart;
import p.fz0;
import p.he1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class vr2 implements uw1 {
    public static final c00[] c = new c00[0];
    public static final vk0 e = new vk0(0);
    public static final ke2 k = new ke2(11);
    public static final ke2 l = new ke2(0);
    public static final yn m = new yn(14);

    public static String a(char c2) {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c2 & 15);
            c2 = (char) (c2 >> 4);
        }
        return String.copyValueOf(cArr);
    }

    public static void b(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static c00 c(final Object obj, final c00 c00Var, final fz0 fz0Var) {
        v03.j(fz0Var, "<this>");
        v03.j(c00Var, "completion");
        if (fz0Var instanceof BaseContinuationImpl) {
            return ((BaseContinuationImpl) fz0Var).a(obj, c00Var);
        }
        final y10 context = c00Var.getContext();
        return context == EmptyCoroutineContext.c ? new RestrictedContinuationImpl(obj, c00Var, fz0Var) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$3
            final /* synthetic */ Object $receiver$inlined;
            final /* synthetic */ fz0 $this_createCoroutineUnintercepted$inlined;
            private int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(c00Var);
                this.$this_createCoroutineUnintercepted$inlined = fz0Var;
                v03.g(c00Var, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object i(Object obj2) {
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("This coroutine had already completed".toString());
                    }
                    this.label = 2;
                    a.d(obj2);
                    return obj2;
                }
                this.label = 1;
                a.d(obj2);
                v03.g(this.$this_createCoroutineUnintercepted$inlined, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                fz0 fz0Var2 = this.$this_createCoroutineUnintercepted$inlined;
                he1.d(2, fz0Var2);
                return fz0Var2.g(this.$receiver$inlined, this);
            }
        } : new ContinuationImpl(c00Var, context, fz0Var, obj) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$4
            final /* synthetic */ Object $receiver$inlined;
            final /* synthetic */ fz0 $this_createCoroutineUnintercepted$inlined;
            private int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(c00Var, context);
                this.$this_createCoroutineUnintercepted$inlined = fz0Var;
                this.$receiver$inlined = obj;
                v03.g(c00Var, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object i(Object obj2) {
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("This coroutine had already completed".toString());
                    }
                    this.label = 2;
                    a.d(obj2);
                    return obj2;
                }
                this.label = 1;
                a.d(obj2);
                v03.g(this.$this_createCoroutineUnintercepted$inlined, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                fz0 fz0Var2 = this.$this_createCoroutineUnintercepted$inlined;
                he1.d(2, fz0Var2);
                return fz0Var2.g(this.$receiver$inlined, this);
            }
        };
    }

    public static long d(Optional optional) {
        if (!optional.d()) {
            return 142852L;
        }
        ju1 ju1Var = (ju1) optional.c();
        long j = (ju1Var.a.w().o().isEmpty() ? 256L : 0L) | 142854;
        PlayerState playerState = ju1Var.a;
        return j | (playerState.w().r().isEmpty() ? 32L : 0L) | ((playerState.w().s().isEmpty() || playerState.w().o().isEmpty()) ? 16L : 0L) | (ju1Var.a() ? 2097152L : 0L) | (((!playerState.w().u().isEmpty() || ju1Var.e()) && (!playerState.w().t().isEmpty() || ju1Var.e())) ? 0L : 262144L) | (playerState.w().g().isEmpty() ? 4096L : 0L);
    }

    public static final Integer e(String str) {
        String value;
        if (str == null || (value = p(str).getValue("pos")) == null) {
            return null;
        }
        return Integer.valueOf(Integer.parseInt(value));
    }

    public static final String f(String str) {
        if (str != null) {
            return p(str).getValue("ref");
        }
        return null;
    }

    public static final if2 g(String str) {
        int iN0;
        if (str == null || str.length() == 0) {
            return null;
        }
        if (str.length() != 0 && (iN0 = kotlin.text.b.n0(str, '?', 0, false, 6)) != -1) {
            str = str.substring(0, iN0);
            v03.i(str, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        try {
            return new if2(str);
        } catch (SpotifyUriParserException unused) {
            return null;
        }
    }

    public static final if2 h(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String strSanitize = UrlQuerySanitizer.getUrlAndSpaceLegal().sanitize(str);
        v03.i(strSanitize, "sanitizedQuery");
        if (strSanitize.length() == 0) {
            return null;
        }
        if (strSanitize.length() >= 50) {
            strSanitize = strSanitize.substring(0, 50);
            v03.i(strSanitize, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        try {
            return new if2("spotify:search:" + if2.b(strSanitize));
        } catch (SpotifyUriParserException unused) {
            return null;
        }
    }

    public static c00 k(c00 c00Var) {
        v03.j(c00Var, "<this>");
        ContinuationImpl continuationImpl = c00Var instanceof ContinuationImpl ? (ContinuationImpl) c00Var : null;
        if (continuationImpl == null) {
            return c00Var;
        }
        c00 c00Var2 = continuationImpl.c;
        if (c00Var2 != null) {
            return c00Var2;
        }
        f00 f00Var = (f00) continuationImpl.getContext().E0(qo.l);
        c00 ea0Var = f00Var != null ? new ea0((kotlinx.coroutines.b) f00Var, continuationImpl) : continuationImpl;
        continuationImpl.c = ea0Var;
        return ea0Var;
    }

    public static uf2 l(e20 e20Var, kotlinx.coroutines.android.a aVar, CoroutineStart coroutineStart, fz0 fz0Var, int i) {
        y10 y10Var = aVar;
        if ((i & 1) != 0) {
            y10Var = EmptyCoroutineContext.c;
        }
        if ((i & 2) != 0) {
            coroutineStart = CoroutineStart.c;
        }
        y10 y10VarC = kotlinx.coroutines.a.c(e20Var, y10Var);
        coroutineStart.getClass();
        uf2 z81Var = coroutineStart == CoroutineStart.e ? new z81(y10VarC, fz0Var) : new uf2(y10VarC, true, true);
        z81Var.a0(coroutineStart, z81Var, fz0Var);
        return z81Var;
    }

    public static UrlQuerySanitizer p(String str) {
        UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
        urlQuerySanitizer.registerParameter("contextUri", UrlQuerySanitizer.getAllIllegal());
        urlQuerySanitizer.registerParameter("ref", UrlQuerySanitizer.getAllIllegal());
        urlQuerySanitizer.registerParameter("pos", UrlQuerySanitizer.getAllIllegal());
        urlQuerySanitizer.setPreferFirstRepeatedParameter(true);
        urlQuerySanitizer.parseUrl(str);
        return urlQuerySanitizer;
    }

    public static final ByteString q(String str) {
        cl clVar = el.d;
        Locale locale = Locale.US;
        v03.i(locale, "US");
        String upperCase = str.toUpperCase(locale);
        v03.i(upperCase, "this as java.lang.String).toUpperCase(locale)");
        byte[] bArrA = clVar.a(upperCase);
        return ByteString.c(bArrA, 0, bArrA.length);
    }

    public static g51 t(i51 i51Var, int i) {
        v03.j(i51Var, "<this>");
        boolean z = i > 0;
        Integer numValueOf = Integer.valueOf(i);
        v03.j(numValueOf, "step");
        if (z) {
            if (i51Var.k <= 0) {
                i = -i;
            }
            return new g51(i51Var.c, i51Var.e, i);
        }
        throw new IllegalArgumentException("Step must be positive, was: " + numValueOf + '.');
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean u(Observable observable, io.reactivex.rxjava3.functions.o oVar, CompletableObserver completableObserver) {
        CompletableSource completableSource;
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        if (!(observable instanceof io.reactivex.rxjava3.functions.q)) {
            return false;
        }
        try {
            Object obj = ((io.reactivex.rxjava3.functions.q) observable).get();
            if (obj != null) {
                Object objApply = oVar.apply(obj);
                Objects.requireNonNull(objApply, "The mapper returned a null CompletableSource");
                completableSource = (CompletableSource) objApply;
            } else {
                completableSource = null;
            }
            if (completableSource == null) {
                completableObserver.onSubscribe(emptyDisposable);
                completableObserver.onComplete();
            } else {
                completableSource.subscribe(completableObserver);
            }
            return true;
        } catch (Throwable th) {
            he1.R(th);
            completableObserver.onSubscribe(emptyDisposable);
            completableObserver.onError(th);
            return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean v(Observable observable, io.reactivex.rxjava3.functions.o oVar, Observer observer) {
        MaybeSource maybeSource;
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        if (!(observable instanceof io.reactivex.rxjava3.functions.q)) {
            return false;
        }
        try {
            Object obj = ((io.reactivex.rxjava3.functions.q) observable).get();
            if (obj != null) {
                Object objApply = oVar.apply(obj);
                Objects.requireNonNull(objApply, "The mapper returned a null MaybeSource");
                maybeSource = (MaybeSource) objApply;
            } else {
                maybeSource = null;
            }
            if (maybeSource == null) {
                observer.onSubscribe(emptyDisposable);
                observer.onComplete();
            } else {
                maybeSource.subscribe(io.reactivex.rxjava3.internal.operators.maybe.h.a(observer));
            }
            return true;
        } catch (Throwable th) {
            he1.R(th);
            observer.onSubscribe(emptyDisposable);
            observer.onError(th);
            return true;
        }
    }

    public static boolean w(ObservableSource observableSource, io.reactivex.rxjava3.functions.o oVar, Observer observer) {
        SingleSource singleSource;
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        if (!(observableSource instanceof io.reactivex.rxjava3.functions.q)) {
            return false;
        }
        try {
            Object obj = ((io.reactivex.rxjava3.functions.q) observableSource).get();
            if (obj != null) {
                Object objApply = oVar.apply(obj);
                Objects.requireNonNull(objApply, "The mapper returned a null SingleSource");
                singleSource = (SingleSource) objApply;
            } else {
                singleSource = null;
            }
            if (singleSource == null) {
                observer.onSubscribe(emptyDisposable);
                observer.onComplete();
            } else {
                singleSource.subscribe(io.reactivex.rxjava3.internal.operators.single.m0.a(observer));
            }
            return true;
        } catch (Throwable th) {
            he1.R(th);
            observer.onSubscribe(emptyDisposable);
            observer.onError(th);
            return true;
        }
    }

    public static i51 x(int i, int i2) {
        if (i2 > Integer.MIN_VALUE) {
            return new i51(i, i2 - 1, 1);
        }
        i51 i51Var = i51.l;
        return i51.l;
    }

    public static final Object y(y10 y10Var, fz0 fz0Var, c00 c00Var) throws Throwable {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        y10 context = c00Var.getContext();
        y10 y10VarB = kotlinx.coroutines.a.b(context, y10Var);
        zu.a0(y10VarB);
        if (y10VarB == context) {
            o72 o72Var = new o72(c00Var, y10VarB);
            return q30.v(o72Var, o72Var, fz0Var);
        }
        qo qoVar = qo.l;
        if (v03.b(y10VarB.E0(qoVar), context.E0(qoVar))) {
            jq2 jq2Var = new jq2(c00Var, y10VarB);
            y10 y10Var2 = jq2Var.k;
            Object objC = kotlinx.coroutines.internal.c.c(y10Var2, null);
            try {
                return q30.v(jq2Var, jq2Var, fz0Var);
            } finally {
                kotlinx.coroutines.internal.c.a(y10Var2, objC);
            }
        }
        kotlinx.coroutines.d dVar = new kotlinx.coroutines.d(c00Var, y10VarB);
        zu.F0(fz0Var, dVar, dVar);
        do {
            atomicIntegerFieldUpdater = kotlinx.coroutines.d.m;
            int i = atomicIntegerFieldUpdater.get(dVar);
            if (i != 0) {
                if (i != 2) {
                    throw new IllegalStateException("Already suspended".toString());
                }
                Object objA = y61.a(dVar.I());
                if (objA instanceof hv) {
                    throw ((hv) objA).a;
                }
                return objA;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(dVar, 0, 1));
        return CoroutineSingletons.c;
    }

    public int i(String str) {
        return j(str);
    }

    public int j(String str) {
        int length = str.length();
        ci1.l(0, length);
        for (int i = 0; i < length; i++) {
            if (m(str.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public abstract boolean m(char c2);

    public abstract void n(Throwable th);

    public abstract void o(gh2 gh2Var);

    public abstract Object r(q71 q71Var, Class cls);

    public String s() {
        String string = "4.4.1.0".toString();
        int i = i(string);
        if (i == -1) {
            return string;
        }
        char[] charArray = string.toCharArray();
        int i2 = 1;
        while (true) {
            i++;
            while (i != charArray.length) {
                if (m(charArray[i])) {
                    break;
                }
                charArray[i - i2] = charArray[i];
                i++;
            }
            return new String(charArray, 0, i - i2);
            i2++;
        }
    }
}

package spi;

import android.os.SystemClock;
import android.view.View;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;

/* JADX INFO: loaded from: classes.dex */
public final class u5 implements View.OnClickListener {
    public boolean a;
    public long b;

    @NotNull
    public Function1<? super View, Unit> c;
    public long d;
    public long e;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        new a(null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public u5(@NotNull Function1<? super View, Unit> listener) {
        this(true, 300L, listener);
        Intrinsics.checkNotNullParameter(listener, "listener");
    }

    public u5(boolean z, long j, @NotNull Function1<? super View, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a = z;
        this.b = j;
        this.c = listener;
    }

    public /* synthetic */ u5(boolean z, long j, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? 300L : j, function1);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(@Nullable View view) {
        boolean z;
        if (this.b <= 0) {
            z = true;
        } else {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long jAbs = Math.abs(jElapsedRealtime - (this.a ? b.a : this.d));
            this.e = jAbs;
            if (jAbs >= this.b) {
                if (this.a) {
                    b.a = jElapsedRealtime;
                } else {
                    this.d = jElapsedRealtime;
                }
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            if (view != null) {
                this.c.invoke(view);
                return;
            }
            return;
        }
        String str = "Click too fast, cancel the trigger, and there is a " + this.e + " millisecond interval from the last time";
        Object[] objArr = new Object[0];
        Objects.requireNonNull(ct.c);
        for (ct.c cVar : ct.b) {
            cVar.d(str, objArr);
        }
    }
}

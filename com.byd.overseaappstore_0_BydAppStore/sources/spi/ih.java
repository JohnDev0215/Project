package spi;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import spi.gh;
import spi.ni;

/* JADX INFO: loaded from: classes.dex */
public final class ih extends gh<ig, wm<?>> implements ni {
    public ni.a d;

    public ih(long j) {
        super(j);
    }

    @Override // spi.ni
    @SuppressLint({"InlinedApi"})
    public final void a(int i) {
        long j;
        if (i >= 40) {
            j(0L);
        } else if (i >= 20 || i == 15) {
            synchronized (this) {
                j = this.b;
            }
            j(j / 2);
        }
    }

    @Override // spi.ni
    public final void c(@NonNull ni.a aVar) {
        this.d = aVar;
    }

    @Override // spi.ni
    @Nullable
    public final wm d(@NonNull ig igVar) {
        Object obj;
        synchronized (this) {
            gh.a aVar = (gh.a) this.a.remove(igVar);
            if (aVar == null) {
                obj = null;
            } else {
                this.c -= (long) aVar.b;
                obj = aVar.a;
            }
        }
        return (wm) obj;
    }

    @Override // spi.gh
    public final int g(@Nullable wm<?> wmVar) {
        wm<?> wmVar2 = wmVar;
        if (wmVar2 == null) {
            return 1;
        }
        return wmVar2.a();
    }

    @Override // spi.gh
    public final void h(@NonNull ig igVar, @Nullable wm<?> wmVar) {
        wm<?> wmVar2 = wmVar;
        ni.a aVar = this.d;
        if (aVar == null || wmVar2 == null) {
            return;
        }
        ((i8) aVar).e.a(wmVar2, true);
    }
}

package defpackage;

import android.os.Environment;
import android.os.StatFs;
import java.io.File;

/* JADX INFO: compiled from: AsyncFeedbackSourceAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class x8<Result> implements w8 {
    public x8<Result>.a a;

    /* JADX INFO: compiled from: AsyncFeedbackSourceAdapter.java */
    public class a extends i9<Result> {
        public final Runnable h;

        public a(em0 em0Var) {
            this.h = em0Var;
        }

        @Override // defpackage.i9
        public final Result b() {
            ((ey2) x8.this).getClass();
            File dataDirectory = Environment.getDataDirectory();
            if (dataDirectory.exists()) {
                return (Result) new StatFs(dataDirectory.getPath());
            }
            return null;
        }

        @Override // defpackage.i9
        public final void l(Result result) {
            this.h.run();
        }
    }

    @Override // defpackage.w8
    public final boolean a() {
        x8<Result>.a aVar = this.a;
        return aVar != null && aVar.b == 2;
    }

    @Override // defpackage.w8
    public final void b(em0 em0Var) {
        if (this.a != null) {
            return;
        }
        x8<Result>.a aVar = new a(em0Var);
        this.a = aVar;
        aVar.c(i9.e);
    }
}

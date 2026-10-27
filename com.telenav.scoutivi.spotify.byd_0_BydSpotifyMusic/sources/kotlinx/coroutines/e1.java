package kotlinx.coroutines;

import java.io.Closeable;
import k5.g;

/* JADX INFO: compiled from: Executors.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class e1 extends e0 implements Closeable {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a f7604g = new a(null);

    /* JADX INFO: compiled from: Executors.kt */
    public static final class a extends k5.b<e0, e1> {

        /* JADX INFO: renamed from: kotlinx.coroutines.e1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Executors.kt */
        static final class C0107a extends kotlin.jvm.internal.m implements r5.l<g.b, e1> {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public static final C0107a f7605e = new C0107a();

            C0107a() {
                super(1);
            }

            @Override // r5.l
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final e1 invoke(g.b bVar) {
                if (!(bVar instanceof e1)) {
                    bVar = null;
                }
                return (e1) bVar;
            }
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        private a() {
            super(e0.f7602e, C0107a.f7605e);
        }
    }
}

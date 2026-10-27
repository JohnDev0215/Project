package defpackage;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.chromium.mojo.system.ResultAnd;

/* JADX INFO: compiled from: MessagePipeHandle.java */
/* JADX INFO: loaded from: classes3.dex */
public interface ch1 extends tt0 {

    /* JADX INFO: compiled from: MessagePipeHandle.java */
    public static class a extends mn0<a> {
        public static final a c;

        static {
            a aVar = new a();
            aVar.b = true;
            c = aVar;
        }
    }

    /* JADX INFO: compiled from: MessagePipeHandle.java */
    public static class b {
        public final a a = a.c;
    }

    /* JADX INFO: compiled from: MessagePipeHandle.java */
    public static class c extends mn0<c> {
        public static final c c;

        static {
            c cVar = new c();
            cVar.b = true;
            c = cVar;
        }
    }

    /* JADX INFO: compiled from: MessagePipeHandle.java */
    public static class d {
        public byte[] a;
        public long[] b;
        public ArrayList c;
    }

    /* JADX INFO: compiled from: MessagePipeHandle.java */
    public static class e extends mn0<e> {
        public static final e c;

        static {
            e eVar = new e();
            eVar.b = true;
            c = eVar;
        }
    }

    ch1 P0();

    void l(ByteBuffer byteBuffer, List list);

    ResultAnd u();
}

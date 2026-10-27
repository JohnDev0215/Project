package k4;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: renamed from: k4.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1686d implements com.bumptech.glide.load.data.e {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f18186m;

    public /* synthetic */ C1686d(Object obj, int i7) {
        this.l = i7;
        this.f18186m = obj;
    }

    private final void c() {
    }

    private final void d() {
    }

    private final void g() {
    }

    private final void h() {
    }

    @Override // com.bumptech.glide.load.data.e
    public final Class a() {
        switch (this.l) {
            case 0:
                return ByteBuffer.class;
            default:
                return this.f18186m.getClass();
        }
    }

    @Override // com.bumptech.glide.load.data.e
    public final void b() {
        int i7 = this.l;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void cancel() {
        int i7 = this.l;
    }

    @Override // com.bumptech.glide.load.data.e
    public final int e() {
        switch (this.l) {
        }
        return 1;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void f(com.bumptech.glide.f fVar, com.bumptech.glide.load.data.d dVar) {
        switch (this.l) {
            case 0:
                try {
                    dVar.d(A4.c.a((File) this.f18186m));
                } catch (IOException e10) {
                    if (Log.isLoggable("ByteBufferFileLoader", 3)) {
                        Log.d("ByteBufferFileLoader", "Failed to obtain ByteBuffer for file", e10);
                    }
                    dVar.c(e10);
                    return;
                }
                break;
            default:
                dVar.d(this.f18186m);
                break;
        }
    }
}

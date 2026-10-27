package S0;

import S8.C0712i;
import android.graphics.Typeface;
import java.lang.reflect.InvocationTargetException;
import x1.AbstractC2663b;

/* JADX INFO: renamed from: S0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0690b extends AbstractC2663b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f7821e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f7822f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f7823g;

    public C0690b(C0712i c0712i, G g7) {
        this.f7822f = c0712i;
        this.f7823g = g7;
    }

    @Override // x1.AbstractC2663b
    public final void g(int i7) throws IllegalAccessException, InvocationTargetException {
        switch (this.f7821e) {
            case 0:
                ((C0712i) this.f7822f).l(new IllegalStateException("Unable to load font " + ((G) this.f7823g) + " (reason=" + i7 + ')'));
                break;
            default:
                ((V4.c) this.f7823g).f9474m = true;
                ((com.bumptech.glide.d) this.f7822f).I(i7);
                break;
        }
    }

    @Override // x1.AbstractC2663b
    public final void h(Typeface typeface) {
        switch (this.f7821e) {
            case 0:
                ((C0712i) this.f7822f).resumeWith(typeface);
                break;
            default:
                V4.c cVar = (V4.c) this.f7823g;
                cVar.f9475n = Typeface.create(typeface, cVar.d);
                cVar.f9474m = true;
                ((com.bumptech.glide.d) this.f7822f).J(cVar.f9475n, false);
                break;
        }
    }

    public C0690b(V4.c cVar, com.bumptech.glide.d dVar) {
        this.f7823g = cVar;
        this.f7822f = dVar;
    }
}

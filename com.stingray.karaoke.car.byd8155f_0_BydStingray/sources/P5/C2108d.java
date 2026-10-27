package p5;

import j5.y;
import java.sql.Timestamp;
import java.util.Date;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: p5.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2108d extends y {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2107c f20904b = new C2107c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y f20905a;

    public C2108d(y yVar) {
        this.f20905a = yVar;
    }

    @Override // j5.y
    public final Object a(C2297a c2297a) {
        Date date = (Date) this.f20905a.a(c2297a);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) {
        this.f20905a.b(c2298b, (Timestamp) obj);
    }
}

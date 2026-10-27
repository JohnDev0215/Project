package g4;

import h4.C1362d;
import h4.C1363e;
import h4.InterfaceC1365g;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes.dex */
public final class z implements e4.e {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final A4.m f14969j = new A4.m(50);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final F.l f14970b;
    public final e4.e c;
    public final e4.e d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f14971e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f14972f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Class f14973g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e4.h f14974h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final e4.l f14975i;

    public z(F.l lVar, e4.e eVar, e4.e eVar2, int i7, int i10, e4.l lVar2, Class cls, e4.h hVar) {
        this.f14970b = lVar;
        this.c = eVar;
        this.d = eVar2;
        this.f14971e = i7;
        this.f14972f = i10;
        this.f14975i = lVar2;
        this.f14973g = cls;
        this.f14974h = hVar;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // e4.e
    public final void b(MessageDigest messageDigest) {
        Object objG;
        F.l lVar = this.f14970b;
        synchronized (lVar) {
            C1363e c1363e = (C1363e) lVar.d;
            InterfaceC1365g interfaceC1365gA1 = (InterfaceC1365g) ((ArrayDeque) c1363e.f476m).poll();
            if (interfaceC1365gA1 == null) {
                interfaceC1365gA1 = c1363e.a1();
            }
            C1362d c1362d = (C1362d) interfaceC1365gA1;
            c1362d.f15514b = 8;
            c1362d.c = byte[].class;
            objG = lVar.g(c1362d, byte[].class);
        }
        byte[] bArr = (byte[]) objG;
        ByteBuffer.wrap(bArr).putInt(this.f14971e).putInt(this.f14972f).array();
        this.d.b(messageDigest);
        this.c.b(messageDigest);
        messageDigest.update(bArr);
        e4.l lVar2 = this.f14975i;
        if (lVar2 != null) {
            lVar2.b(messageDigest);
        }
        this.f14974h.b(messageDigest);
        A4.m mVar = f14969j;
        Class cls = this.f14973g;
        byte[] bytes = (byte[]) mVar.a(cls);
        if (bytes == null) {
            bytes = cls.getName().getBytes(e4.e.f14430a);
            mVar.d(cls, bytes);
        }
        messageDigest.update(bytes);
        this.f14970b.i(bArr);
    }

    @Override // e4.e
    public final boolean equals(Object obj) {
        if (!(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        return this.f14972f == zVar.f14972f && this.f14971e == zVar.f14971e && A4.q.b(this.f14975i, zVar.f14975i) && this.f14973g.equals(zVar.f14973g) && this.c.equals(zVar.c) && this.d.equals(zVar.d) && this.f14974h.equals(zVar.f14974h);
    }

    @Override // e4.e
    public final int hashCode() {
        int iHashCode = ((((this.d.hashCode() + (this.c.hashCode() * 31)) * 31) + this.f14971e) * 31) + this.f14972f;
        e4.l lVar = this.f14975i;
        if (lVar != null) {
            iHashCode = (iHashCode * 31) + lVar.hashCode();
        }
        int iHashCode2 = this.f14973g.hashCode();
        return this.f14974h.f14434b.hashCode() + ((iHashCode2 + (iHashCode * 31)) * 31);
    }

    public final String toString() {
        return "ResourceCacheKey{sourceKey=" + this.c + ", signature=" + this.d + ", width=" + this.f14971e + ", height=" + this.f14972f + ", decodedResourceClass=" + this.f14973g + ", transformation='" + this.f14975i + "', options=" + this.f14974h + '}';
    }
}

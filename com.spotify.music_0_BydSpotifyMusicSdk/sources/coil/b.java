package coil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Looper;
import coil.target.GenericViewTarget;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Pair;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.CoroutineStart;
import okhttp3.HttpUrl;
import p.a1;
import p.a12;
import p.ai2;
import p.am;
import p.av2;
import p.de1;
import p.dw;
import p.eh2;
import p.eu0;
import p.ew;
import p.ha0;
import p.hi0;
import p.i;
import p.k80;
import p.kk0;
import p.l13;
import p.l31;
import p.ma0;
import p.mr2;
import p.n31;
import p.nc;
import p.ou;
import p.p21;
import p.p70;
import p.q30;
import p.q31;
import p.qo;
import p.s70;
import p.uf2;
import p.v03;
import p.v81;
import p.vo;
import p.yi2;
import p.yz;
import p.zb0;

/* JADX INFO: loaded from: classes.dex */
public final class b implements l31 {
    public final p70 a;
    public final v81 b;
    public final hi0 c;
    public final yz d;
    public final kk0 e;
    public final ew f;
    public final ArrayList g;

    public b(Context context, p70 p70Var, v81 v81Var, v81 v81Var2, v81 v81Var3, hi0 hi0Var, ew ewVar, n31 n31Var) {
        this.a = p70Var;
        this.b = v81Var;
        this.c = hi0Var;
        eh2 eh2Var = new eh2(null);
        s70 s70Var = ha0.a;
        this.d = q30.b(eh2Var.o0(((kotlinx.coroutines.android.a) de1.a).n).o0(new a12(this)));
        ai2 ai2Var = new ai2(this, context, n31Var.b);
        kk0 kk0Var = new kk0(this, ai2Var);
        this.e = kk0Var;
        dw dwVar = new dw(ewVar);
        dwVar.a(new vo(2), HttpUrl.class);
        dwVar.a(new vo(5), String.class);
        dwVar.a(new vo(1), Uri.class);
        dwVar.a(new vo(4), Uri.class);
        dwVar.a(new vo(3), Integer.class);
        dwVar.a(new vo(0), byte[].class);
        Pair pair = new Pair(new mr2(), Uri.class);
        ArrayList arrayList = dwVar.c;
        arrayList.add(pair);
        arrayList.add(new Pair(new eu0(n31Var.a), File.class));
        dwVar.b(new p21(v81Var3, v81Var2, n31Var.c), Uri.class);
        dwVar.b(new nc(5), File.class);
        dwVar.b(new nc(0), Uri.class);
        dwVar.b(new nc(3), Uri.class);
        dwVar.b(new nc(6), Uri.class);
        dwVar.b(new nc(4), Drawable.class);
        dwVar.b(new nc(1), Bitmap.class);
        dwVar.b(new nc(2), ByteBuffer.class);
        am amVar = new am(n31Var.d, n31Var.e);
        ArrayList arrayList2 = dwVar.e;
        arrayList2.add(amVar);
        List listQ = l13.q(dwVar.a);
        this.f = new ew(listQ, l13.q(dwVar.b), l13.q(arrayList), l13.q(dwVar.d), l13.q(arrayList2));
        this.g = ou.j0(listQ, new coil.intercept.a(this, kk0Var));
        new AtomicBoolean(false);
        context.registerComponentCallbacks(ai2Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x010b A[Catch: all -> 0x0133, TryCatch #2 {all -> 0x0133, blocks: (B:66:0x0195, B:68:0x019c, B:71:0x01ae, B:73:0x01bd, B:75:0x01c4, B:72:0x01b2, B:76:0x01c8, B:78:0x01cc, B:63:0x016b, B:37:0x0101, B:39:0x010b, B:41:0x010f, B:43:0x0117, B:46:0x0121, B:49:0x0127, B:53:0x0142, B:55:0x0146, B:56:0x0149, B:58:0x0150, B:59:0x0153, B:52:0x0136, B:32:0x00e4, B:34:0x00ea, B:80:0x01db, B:81:0x01e0), top: B:97:0x00e4 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0127 A[Catch: all -> 0x0133, TryCatch #2 {all -> 0x0133, blocks: (B:66:0x0195, B:68:0x019c, B:71:0x01ae, B:73:0x01bd, B:75:0x01c4, B:72:0x01b2, B:76:0x01c8, B:78:0x01cc, B:63:0x016b, B:37:0x0101, B:39:0x010b, B:41:0x010f, B:43:0x0117, B:46:0x0121, B:49:0x0127, B:53:0x0142, B:55:0x0146, B:56:0x0149, B:58:0x0150, B:59:0x0153, B:52:0x0136, B:32:0x00e4, B:34:0x00ea, B:80:0x01db, B:81:0x01e0), top: B:97:0x00e4 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0136 A[Catch: all -> 0x0133, TryCatch #2 {all -> 0x0133, blocks: (B:66:0x0195, B:68:0x019c, B:71:0x01ae, B:73:0x01bd, B:75:0x01c4, B:72:0x01b2, B:76:0x01c8, B:78:0x01cc, B:63:0x016b, B:37:0x0101, B:39:0x010b, B:41:0x010f, B:43:0x0117, B:46:0x0121, B:49:0x0127, B:53:0x0142, B:55:0x0146, B:56:0x0149, B:58:0x0150, B:59:0x0153, B:52:0x0136, B:32:0x00e4, B:34:0x00ea, B:80:0x01db, B:81:0x01e0), top: B:97:0x00e4 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0146 A[Catch: all -> 0x0133, TryCatch #2 {all -> 0x0133, blocks: (B:66:0x0195, B:68:0x019c, B:71:0x01ae, B:73:0x01bd, B:75:0x01c4, B:72:0x01b2, B:76:0x01c8, B:78:0x01cc, B:63:0x016b, B:37:0x0101, B:39:0x010b, B:41:0x010f, B:43:0x0117, B:46:0x0121, B:49:0x0127, B:53:0x0142, B:55:0x0146, B:56:0x0149, B:58:0x0150, B:59:0x0153, B:52:0x0136, B:32:0x00e4, B:34:0x00ea, B:80:0x01db, B:81:0x01e0), top: B:97:0x00e4 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0150 A[Catch: all -> 0x0133, TryCatch #2 {all -> 0x0133, blocks: (B:66:0x0195, B:68:0x019c, B:71:0x01ae, B:73:0x01bd, B:75:0x01c4, B:72:0x01b2, B:76:0x01c8, B:78:0x01cc, B:63:0x016b, B:37:0x0101, B:39:0x010b, B:41:0x010f, B:43:0x0117, B:46:0x0121, B:49:0x0127, B:53:0x0142, B:55:0x0146, B:56:0x0149, B:58:0x0150, B:59:0x0153, B:52:0x0136, B:32:0x00e4, B:34:0x00ea, B:80:0x01db, B:81:0x01e0), top: B:97:0x00e4 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x019c A[Catch: all -> 0x0133, TryCatch #2 {all -> 0x0133, blocks: (B:66:0x0195, B:68:0x019c, B:71:0x01ae, B:73:0x01bd, B:75:0x01c4, B:72:0x01b2, B:76:0x01c8, B:78:0x01cc, B:63:0x016b, B:37:0x0101, B:39:0x010b, B:41:0x010f, B:43:0x0117, B:46:0x0121, B:49:0x0127, B:53:0x0142, B:55:0x0146, B:56:0x0149, B:58:0x0150, B:59:0x0153, B:52:0x0136, B:32:0x00e4, B:34:0x00ea, B:80:0x01db, B:81:0x01e0), top: B:97:0x00e4 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01c4 A[Catch: all -> 0x0133, TryCatch #2 {all -> 0x0133, blocks: (B:66:0x0195, B:68:0x019c, B:71:0x01ae, B:73:0x01bd, B:75:0x01c4, B:72:0x01b2, B:76:0x01c8, B:78:0x01cc, B:63:0x016b, B:37:0x0101, B:39:0x010b, B:41:0x010f, B:43:0x0117, B:46:0x0121, B:49:0x0127, B:53:0x0142, B:55:0x0146, B:56:0x0149, B:58:0x0150, B:59:0x0153, B:52:0x0136, B:32:0x00e4, B:34:0x00ea, B:80:0x01db, B:81:0x01e0), top: B:97:0x00e4 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01c8 A[Catch: all -> 0x0133, TryCatch #2 {all -> 0x0133, blocks: (B:66:0x0195, B:68:0x019c, B:71:0x01ae, B:73:0x01bd, B:75:0x01c4, B:72:0x01b2, B:76:0x01c8, B:78:0x01cc, B:63:0x016b, B:37:0x0101, B:39:0x010b, B:41:0x010f, B:43:0x0117, B:46:0x0121, B:49:0x0127, B:53:0x0142, B:55:0x0146, B:56:0x0149, B:58:0x0150, B:59:0x0153, B:52:0x0136, B:32:0x00e4, B:34:0x00ea, B:80:0x01db, B:81:0x01e0), top: B:97:0x00e4 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01e5 A[Catch: all -> 0x01f3, TryCatch #1 {all -> 0x01f3, blocks: (B:82:0x01e1, B:84:0x01e5, B:86:0x01ef, B:87:0x01f2, B:90:0x01f5), top: B:96:0x01e1 }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01f5 A[Catch: all -> 0x01f3, TRY_LEAVE, TryCatch #1 {all -> 0x01f3, blocks: (B:82:0x01e1, B:84:0x01e5, B:86:0x01ef, B:87:0x01f2, B:90:0x01f5), top: B:96:0x01e1 }] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v12, types: [coil.b, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v14, types: [p.p31] */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Object, p.ii0] */
    /* JADX WARN: Type inference failed for: r3v10, types: [p.q31] */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r4v32 */
    /* JADX WARN: Type inference failed for: r4v33 */
    /* JADX WARN: Type inference failed for: r4v34 */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v8, types: [p.o32] */
    /* JADX WARN: Type inference failed for: r4v9, types: [p.o32] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object a(coil.b r22, p.q31 r23, p.c00 r24) {
        /*
            Method dump skipped, instruction units count: 521
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.b.a(coil.b, p.q31, p.c00):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c(p.kf0 r4, p.yi2 r5, p.ii0 r6) {
        /*
            p.q31 r0 = r4.b
            boolean r1 = r5 instanceof coil.target.GenericViewTarget
            android.graphics.drawable.Drawable r2 = r4.a
            if (r1 != 0) goto Lb
            if (r5 == 0) goto L18
            goto L15
        Lb:
            p.un2 r1 = r0.m
            r3 = r5
            coil.target.GenericViewTarget r3 = (coil.target.GenericViewTarget) r3
            p.tn1 r1 = (p.tn1) r1
            r1.getClass()
        L15:
            r5.g(r2)
        L18:
            r6.c(r0, r4)
            p.p31 r5 = r0.d
            if (r5 == 0) goto L22
            r5.c(r0, r4)
        L22:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.b.c(p.kf0, p.yi2, p.ii0):void");
    }

    public final ma0 b(q31 q31Var) {
        yz yzVar = this.d;
        RealImageLoader$enqueue$job$1 realImageLoader$enqueue$job$1 = new RealImageLoader$enqueue$job$1(this, q31Var, null);
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.c;
        CoroutineStart coroutineStart = CoroutineStart.c;
        a1 k80Var = new k80(kotlinx.coroutines.a.c(yzVar, emptyCoroutineContext), true, true);
        k80Var.a0(coroutineStart, k80Var, realImageLoader$enqueue$job$1);
        yi2 yi2Var = q31Var.c;
        if (!(yi2Var instanceof GenericViewTarget)) {
            return new qo();
        }
        av2 av2VarC = i.c(((GenericViewTarget) yi2Var).i());
        synchronized (av2VarC) {
            zb0 zb0Var = av2VarC.c;
            if (zb0Var != null && v03.b(Looper.myLooper(), Looper.getMainLooper()) && av2VarC.l) {
                av2VarC.l = false;
                return zb0Var;
            }
            uf2 uf2Var = av2VarC.e;
            if (uf2Var != null) {
                uf2Var.i(null);
            }
            av2VarC.e = null;
            zb0 zb0Var2 = new zb0();
            av2VarC.c = zb0Var2;
            return zb0Var2;
        }
    }
}

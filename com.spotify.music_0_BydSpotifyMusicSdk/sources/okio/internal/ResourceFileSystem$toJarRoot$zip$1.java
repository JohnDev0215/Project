package okio.internal;

import kotlin.jvm.internal.Lambda;
import p.mz2;
import p.os1;
import p.ry0;
import p.u11;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class ResourceFileSystem$toJarRoot$zip$1 extends Lambda implements ry0 {
    public static final ResourceFileSystem$toJarRoot$zip$1 c = new ResourceFileSystem$toJarRoot$zip$1();

    public ResourceFileSystem$toJarRoot$zip$1() {
        super(1);
    }

    @Override // p.ry0
    public final Object invoke(Object obj) {
        mz2 mz2Var = (mz2) obj;
        v03.j(mz2Var, "entry");
        os1 os1Var = a.e;
        return Boolean.valueOf(u11.m(mz2Var.a));
    }
}

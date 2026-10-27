package p;

import com.google.common.collect.ImmutableMap;
import com.spotify.player.esperanto.proto.EsContext$Context;
import com.spotify.player.esperanto.proto.EsRestrictions$Restrictions;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class nf0 extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(ArrayList arrayList) {
        copyOnWrite();
        EsContext$Context.h((EsContext$Context) this.instance, arrayList);
    }

    public final void e(ImmutableMap immutableMap) {
        copyOnWrite();
        EsContext$Context.i((EsContext$Context) this.instance).putAll(immutableMap);
    }

    public final void f(boolean z) {
        copyOnWrite();
        EsContext$Context.f((EsContext$Context) this.instance, z);
    }

    public final void g(EsRestrictions$Restrictions esRestrictions$Restrictions) {
        copyOnWrite();
        EsContext$Context.g((EsContext$Context) this.instance, esRestrictions$Restrictions);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final void h(String str) {
        copyOnWrite();
        EsContext$Context.j((EsContext$Context) this.instance, str);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((com.google.protobuf.i) aVar);
    }

    public final void j(String str) {
        copyOnWrite();
        EsContext$Context.e((EsContext$Context) this.instance, str);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
        return super.mergeFrom(auVar, rl0Var);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
        return super.mergeFrom(bArr, 0, i2);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
        return super.mergeFrom(bArr, 0, i2, rl0Var);
    }
}

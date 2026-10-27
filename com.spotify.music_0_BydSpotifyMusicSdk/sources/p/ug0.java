package p;

import com.spotify.player.esperanto.proto.EsCommandOptions$CommandOptions;
import com.spotify.player.esperanto.proto.EsLoggingParams$LoggingParams;
import com.spotify.player.esperanto.proto.EsOptional$OptionalBoolean;
import com.spotify.player.esperanto.proto.EsSetOptions$SetOptionsRequest;

/* JADX INFO: loaded from: classes.dex */
public final class ug0 extends h01 implements yj1 {
    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        return super.buildPartial();
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        return super.clone();
    }

    public final void d(EsLoggingParams$LoggingParams esLoggingParams$LoggingParams) {
        copyOnWrite();
        EsSetOptions$SetOptionsRequest.g((EsSetOptions$SetOptionsRequest) this.instance, esLoggingParams$LoggingParams);
    }

    public final void e(EsCommandOptions$CommandOptions esCommandOptions$CommandOptions) {
        copyOnWrite();
        EsSetOptions$SetOptionsRequest.f((EsSetOptions$SetOptionsRequest) this.instance, esCommandOptions$CommandOptions);
    }

    public final void f(float f) {
        copyOnWrite();
        EsSetOptions$SetOptionsRequest.h((EsSetOptions$SetOptionsRequest) this.instance, f);
    }

    public final void g(EsOptional$OptionalBoolean esOptional$OptionalBoolean) {
        copyOnWrite();
        EsSetOptions$SetOptionsRequest.i((EsSetOptions$SetOptionsRequest) this.instance, esOptional$OptionalBoolean);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final void h(EsOptional$OptionalBoolean esOptional$OptionalBoolean) {
        copyOnWrite();
        EsSetOptions$SetOptionsRequest.e((EsSetOptions$SetOptionsRequest) this.instance, esOptional$OptionalBoolean);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((com.google.protobuf.i) aVar);
    }

    public final void j(EsOptional$OptionalBoolean esOptional$OptionalBoolean) {
        copyOnWrite();
        EsSetOptions$SetOptionsRequest.j((EsSetOptions$SetOptionsRequest) this.instance, esOptional$OptionalBoolean);
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

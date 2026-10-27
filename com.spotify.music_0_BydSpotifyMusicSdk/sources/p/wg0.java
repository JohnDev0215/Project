package p;

import com.spotify.player.esperanto.proto.EsCommandOptions$CommandOptions;
import com.spotify.player.esperanto.proto.EsLoggingParams$LoggingParams;
import com.spotify.player.esperanto.proto.EsSetQueueRequest$SetQueueRequest;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class wg0 extends h01 implements yj1 {
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
        EsSetQueueRequest$SetQueueRequest.i((EsSetQueueRequest$SetQueueRequest) this.instance, arrayList);
    }

    public final void e(ArrayList arrayList) {
        copyOnWrite();
        EsSetQueueRequest$SetQueueRequest.e((EsSetQueueRequest$SetQueueRequest) this.instance, arrayList);
    }

    public final void f(EsLoggingParams$LoggingParams esLoggingParams$LoggingParams) {
        copyOnWrite();
        EsSetQueueRequest$SetQueueRequest.h((EsSetQueueRequest$SetQueueRequest) this.instance, esLoggingParams$LoggingParams);
    }

    public final void g(EsCommandOptions$CommandOptions esCommandOptions$CommandOptions) {
        copyOnWrite();
        EsSetQueueRequest$SetQueueRequest.g((EsSetQueueRequest$SetQueueRequest) this.instance, esCommandOptions$CommandOptions);
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final void h(long j) {
        copyOnWrite();
        EsSetQueueRequest$SetQueueRequest.f((EsSetQueueRequest$SetQueueRequest) this.instance, j);
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        return super.internalMergeFrom((com.google.protobuf.i) aVar);
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

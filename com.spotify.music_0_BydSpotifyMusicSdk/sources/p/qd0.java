package p;

import com.google.protobuf.Empty;
import com.spotify.bootstrap.v1.proto.Bootstrap$TrialsFacadeRequestV1;
import com.spotify.connect.esperanto.proto.CommonMessages$LoggingParams;
import com.spotify.connect.esperanto.proto.CommonMessages$StatusResponse;
import com.spotify.ondemandselector.proto.OndemandResponse;
import com.spotify.player.esperanto.proto.EsContextPlayerOptions$ContextPlayerOptions;
import com.spotify.player.esperanto.proto.EsContextPlayerState$ContextIndex;
import com.spotify.player.esperanto.proto.EsGetErrorRequest$GetErrorRequest;
import com.spotify.player.esperanto.proto.EsGetQueueRequest$GetQueueRequest;
import com.spotify.player.esperanto.proto.EsOptional$OptionalDouble;
import com.spotify.player.esperanto.proto.EsQueue$Queue;
import com.spotify.player.esperanto.proto.EsRestrictions$ModeRestrictions;
import com.spotify.player.esperanto.proto.EsRestrictions$ModeValueRestrictions;
import com.spotify.prefs.esperanto.proto.EsPrefs$PrefValues;
import com.spotify.rcs.admin.grpc.v0.PropertyDefinition$Identifier;
import com.spotify.rcs.admin.grpc.v0.proto.Publish$PropertyDefinition$Identifier;
import com.spotify.rcs.resolver.grpc.v0.ResolveResponse;
import com.spotify.rcs.resolver.grpc.v0.proto.Resolve$ResolveResponse;
import com.spotify.remoteconfig.esperanto.proto.EsRemoteConfig$LookupRequest;
import com.spotify.shoreline.esperanto.proto.EsShorelineLogger$PlaybackIds;
import com.spotify.shoreline.esperanto.proto.EsShorelineLogger$SetContextValueRequest;
import com.spotify.useraccount.v1.proto.Useraccount$AccountAttribute;

/* JADX INFO: loaded from: classes.dex */
public final class qd0 extends h01 implements yj1 {
    public final /* synthetic */ int c;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(dd0 dd0Var) {
        this(8);
        this.c = 8;
    }

    @Override // p.h01, p.vj1
    public final /* bridge */ /* synthetic */ wj1 buildPartial() {
        switch (this.c) {
            case 1:
                return super.buildPartial();
            case 2:
                return super.buildPartial();
            case 3:
                return super.buildPartial();
            case 4:
                return super.buildPartial();
            case 5:
                return super.buildPartial();
            case 6:
                return super.buildPartial();
            case 7:
                return super.buildPartial();
            case 8:
                return super.buildPartial();
            case 9:
                return super.buildPartial();
            case 10:
                return super.buildPartial();
            case 11:
                return super.buildPartial();
            case 12:
                return super.buildPartial();
            case 13:
                return super.buildPartial();
            case 14:
                return super.buildPartial();
            case 15:
                return super.buildPartial();
            case 16:
                return super.buildPartial();
            case 17:
                return super.buildPartial();
            case 18:
                return super.buildPartial();
            case 19:
                return super.buildPartial();
            case 20:
                return super.buildPartial();
            case 21:
                return super.buildPartial();
            default:
                return buildPartial();
        }
    }

    @Override // p.h01
    public final /* bridge */ /* synthetic */ Object clone() {
        switch (this.c) {
            case 1:
                return super.clone();
            case 2:
                return super.clone();
            case 3:
                return super.clone();
            case 4:
                return super.clone();
            case 5:
                return super.clone();
            case 6:
                return super.clone();
            case 7:
                return super.clone();
            case 8:
                return super.clone();
            case 9:
                return super.clone();
            case 10:
                return super.clone();
            case 11:
                return super.clone();
            case 12:
                return super.clone();
            case 13:
                return super.clone();
            case 14:
                return super.clone();
            case 15:
                return super.clone();
            case 16:
                return super.clone();
            case 17:
                return super.clone();
            case 18:
                return super.clone();
            case 19:
                return super.clone();
            case 20:
                return super.clone();
            case 21:
                return super.clone();
            default:
                return clone();
        }
    }

    @Override // p.h01, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        switch (this.c) {
            case 1:
                return super.getDefaultInstanceForType();
            case 2:
                return super.getDefaultInstanceForType();
            case 3:
                return super.getDefaultInstanceForType();
            case 4:
                return super.getDefaultInstanceForType();
            case 5:
                return super.getDefaultInstanceForType();
            case 6:
                return super.getDefaultInstanceForType();
            case 7:
                return super.getDefaultInstanceForType();
            case 8:
                return super.getDefaultInstanceForType();
            case 9:
                return super.getDefaultInstanceForType();
            case 10:
                return super.getDefaultInstanceForType();
            case 11:
                return super.getDefaultInstanceForType();
            case 12:
                return super.getDefaultInstanceForType();
            case 13:
                return super.getDefaultInstanceForType();
            case 14:
                return super.getDefaultInstanceForType();
            case 15:
                return super.getDefaultInstanceForType();
            case 16:
                return super.getDefaultInstanceForType();
            case 17:
                return super.getDefaultInstanceForType();
            case 18:
                return super.getDefaultInstanceForType();
            case 19:
                return super.getDefaultInstanceForType();
            case 20:
                return super.getDefaultInstanceForType();
            case 21:
                return super.getDefaultInstanceForType();
            default:
                return getDefaultInstanceForType();
        }
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 internalMergeFrom(com.google.protobuf.a aVar) {
        switch (this.c) {
            case 1:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 2:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 3:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 4:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 5:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 6:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 7:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 8:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 9:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 10:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 11:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 12:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 13:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 14:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 15:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 16:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 17:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 18:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 19:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 20:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            case 21:
                return super.internalMergeFrom((com.google.protobuf.i) aVar);
            default:
                return internalMergeFrom((com.google.protobuf.i) aVar);
        }
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
        switch (this.c) {
            case 1:
                return super.mergeFrom(auVar, rl0Var);
            case 2:
                return super.mergeFrom(auVar, rl0Var);
            case 3:
                return super.mergeFrom(auVar, rl0Var);
            case 4:
                return super.mergeFrom(auVar, rl0Var);
            case 5:
                return super.mergeFrom(auVar, rl0Var);
            case 6:
                return super.mergeFrom(auVar, rl0Var);
            case 7:
                return super.mergeFrom(auVar, rl0Var);
            case 8:
                return super.mergeFrom(auVar, rl0Var);
            case 9:
                return super.mergeFrom(auVar, rl0Var);
            case 10:
                return super.mergeFrom(auVar, rl0Var);
            case 11:
                return super.mergeFrom(auVar, rl0Var);
            case 12:
                return super.mergeFrom(auVar, rl0Var);
            case 13:
                return super.mergeFrom(auVar, rl0Var);
            case 14:
                return super.mergeFrom(auVar, rl0Var);
            case 15:
                return super.mergeFrom(auVar, rl0Var);
            case 16:
                return super.mergeFrom(auVar, rl0Var);
            case 17:
                return super.mergeFrom(auVar, rl0Var);
            case 18:
                return super.mergeFrom(auVar, rl0Var);
            case 19:
                return super.mergeFrom(auVar, rl0Var);
            case 20:
                return super.mergeFrom(auVar, rl0Var);
            case 21:
                return super.mergeFrom(auVar, rl0Var);
            default:
                return mergeFrom(auVar, rl0Var);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(od0 od0Var) {
        this(7);
        this.c = 7;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0() {
        this(0);
        this.c = 0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qd0(int i) {
        super(Empty.DEFAULT_INSTANCE);
        this.c = i;
        switch (i) {
            case 1:
                super(Bootstrap$TrialsFacadeRequestV1.DEFAULT_INSTANCE);
                break;
            case 2:
                super(CommonMessages$LoggingParams.DEFAULT_INSTANCE);
                break;
            case 3:
                super(CommonMessages$StatusResponse.DEFAULT_INSTANCE);
                break;
            case 4:
                super(OndemandResponse.DEFAULT_INSTANCE);
                break;
            case 5:
                super(EsContextPlayerOptions$ContextPlayerOptions.DEFAULT_INSTANCE);
                break;
            case 6:
                super(EsContextPlayerState$ContextIndex.DEFAULT_INSTANCE);
                break;
            case 7:
                super(EsGetErrorRequest$GetErrorRequest.DEFAULT_INSTANCE);
                break;
            case 8:
                super(EsGetQueueRequest$GetQueueRequest.DEFAULT_INSTANCE);
                break;
            case 9:
                super(EsOptional$OptionalDouble.DEFAULT_INSTANCE);
                break;
            case 10:
                super(EsQueue$Queue.DEFAULT_INSTANCE);
                break;
            case 11:
                super(EsRestrictions$ModeRestrictions.DEFAULT_INSTANCE);
                break;
            case 12:
                super(EsRestrictions$ModeValueRestrictions.DEFAULT_INSTANCE);
                break;
            case 13:
                super(EsPrefs$PrefValues.DEFAULT_INSTANCE);
                break;
            case 14:
                super(PropertyDefinition$Identifier.DEFAULT_INSTANCE);
                break;
            case 15:
                super(Publish$PropertyDefinition$Identifier.DEFAULT_INSTANCE);
                break;
            case 16:
                super(ResolveResponse.DEFAULT_INSTANCE);
                break;
            case 17:
                super(Resolve$ResolveResponse.DEFAULT_INSTANCE);
                break;
            case 18:
                super(EsRemoteConfig$LookupRequest.DEFAULT_INSTANCE);
                break;
            case 19:
                super(EsShorelineLogger$PlaybackIds.DEFAULT_INSTANCE);
                break;
            case 20:
                super(EsShorelineLogger$SetContextValueRequest.DEFAULT_INSTANCE);
                break;
            case 21:
                super(Useraccount$AccountAttribute.DEFAULT_INSTANCE);
                break;
            default:
                break;
        }
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
        switch (this.c) {
            case 1:
                return super.mergeFrom(bArr, 0, i2);
            case 2:
                return super.mergeFrom(bArr, 0, i2);
            case 3:
                return super.mergeFrom(bArr, 0, i2);
            case 4:
                return super.mergeFrom(bArr, 0, i2);
            case 5:
                return super.mergeFrom(bArr, 0, i2);
            case 6:
                return super.mergeFrom(bArr, 0, i2);
            case 7:
                return super.mergeFrom(bArr, 0, i2);
            case 8:
                return super.mergeFrom(bArr, 0, i2);
            case 9:
                return super.mergeFrom(bArr, 0, i2);
            case 10:
                return super.mergeFrom(bArr, 0, i2);
            case 11:
                return super.mergeFrom(bArr, 0, i2);
            case 12:
                return super.mergeFrom(bArr, 0, i2);
            case 13:
                return super.mergeFrom(bArr, 0, i2);
            case 14:
                return super.mergeFrom(bArr, 0, i2);
            case 15:
                return super.mergeFrom(bArr, 0, i2);
            case 16:
                return super.mergeFrom(bArr, 0, i2);
            case 17:
                return super.mergeFrom(bArr, 0, i2);
            case 18:
                return super.mergeFrom(bArr, 0, i2);
            case 19:
                return super.mergeFrom(bArr, 0, i2);
            case 20:
                return super.mergeFrom(bArr, 0, i2);
            case 21:
                return super.mergeFrom(bArr, 0, i2);
            default:
                return mergeFrom(bArr, 0, i2);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(gd0 gd0Var) {
        this(16);
        this.c = 16;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(hd0 hd0Var) {
        this(6);
        this.c = 6;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(u4 u4Var) {
        this(4);
        this.c = 4;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(ld0 ld0Var) {
        this(18);
        this.c = 18;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(id0 id0Var) {
        this(21);
        this.c = 21;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(md0 md0Var) {
        this(5);
        this.c = 5;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(nd0 nd0Var) {
        this(9);
        this.c = 9;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(ed0 ed0Var) {
        this(10);
        this.c = 10;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(Object obj) {
        this(1);
        this.c = 1;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(int i, Object obj) {
        this(11);
        this.c = i;
        if (i != 12) {
        } else {
            this(12);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(fd0 fd0Var) {
        this(14);
        this.c = 14;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(int i, int i2) {
        this(2);
        this.c = i;
        if (i != 3) {
        } else {
            this(3);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(int i, u4 u4Var) {
        this(19);
        this.c = i;
        if (i != 20) {
        } else {
            this(20);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(pd0 pd0Var) {
        this(15);
        this.c = 15;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(jd0 jd0Var) {
        this(17);
        this.c = 17;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qd0(kd0 kd0Var) {
        this(13);
        this.c = 13;
    }

    @Override // p.h01, p.r1
    public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
        switch (this.c) {
            case 1:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 2:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 3:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 4:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 5:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 6:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 7:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 8:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 9:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 10:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 11:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 12:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 13:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 14:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 15:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 16:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 17:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 18:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 19:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 20:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            case 21:
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            default:
                return mergeFrom(bArr, 0, i2, rl0Var);
        }
    }
}

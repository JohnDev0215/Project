package com.spotify.connectivity.sessionesperanto;

import com.google.protobuf.Empty;
import com.google.protobuf.i;
import com.spotify.connectivity.auth.esperanto.proto.EsSession;
import com.spotify.connectivity.sessionapi.APPermanentErrorResult;
import com.spotify.connectivity.sessionapi.SendEndSongsResult;
import com.spotify.connectivity.sessionapi.SessionClient;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.o;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;
import p.lq2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SessionClientEsperanto implements SessionClient {
    private final com.spotify.connectivity.auth.esperanto.proto.SessionClient esperantoClient;

    public SessionClientEsperanto(com.spotify.connectivity.auth.esperanto.proto.SessionClient sessionClient) {
        v03.j(sessionClient, "esperantoClient");
        this.esperantoClient = sessionClient;
    }

    @Override // com.spotify.connectivity.sessionapi.SessionClient
    public Observable<APPermanentErrorResult> apPermanentError() {
        com.spotify.connectivity.auth.esperanto.proto.SessionClient sessionClient = this.esperantoClient;
        Empty emptyF = Empty.f();
        v03.i(emptyF, "getDefaultInstance()");
        Observable map = sessionClient.apPermanentError(emptyF).map(new o() { // from class: com.spotify.connectivity.sessionesperanto.SessionClientEsperanto.apPermanentError.1

            /* JADX INFO: renamed from: com.spotify.connectivity.sessionesperanto.SessionClientEsperanto$apPermanentError$1$WhenMappings */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EsSession.APPermanentErrorResult.APPermanentError.values().length];
                    try {
                        iArr[EsSession.APPermanentErrorResult.APPermanentError.BAD_CREDENTIALS.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[EsSession.APPermanentErrorResult.APPermanentError.PASSWORD_CHANGED.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[EsSession.APPermanentErrorResult.APPermanentError.REGION_MISMATCH.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    try {
                        iArr[EsSession.APPermanentErrorResult.APPermanentError.UNRECOGNIZED.ordinal()] = 4;
                    } catch (NoSuchFieldError unused4) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // io.reactivex.rxjava3.functions.o
            public final APPermanentErrorResult apply(EsSession.APPermanentErrorResult aPPermanentErrorResult) {
                EsSession.APPermanentErrorResult.APPermanentError code = aPPermanentErrorResult.getCode();
                int i = code == null ? -1 : WhenMappings.$EnumSwitchMapping$0[code.ordinal()];
                if (i != -1) {
                    if (i == 1) {
                        return APPermanentErrorResult.APPermanentErrorBadCredentials.INSTANCE;
                    }
                    if (i == 2) {
                        return APPermanentErrorResult.APPermanentErrorPasswordChanged.INSTANCE;
                    }
                    if (i == 3) {
                        return APPermanentErrorResult.APPermanentErrorRegionMismatch.INSTANCE;
                    }
                    if (i != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                }
                EsSession.APPermanentErrorResult.GenericError genericError = aPPermanentErrorResult.getGenericError();
                int errorCode = genericError.getErrorCode();
                String errorMessage = genericError.getErrorMessage();
                v03.i(errorMessage, "generic.errorMessage");
                return new APPermanentErrorResult.APPermanentGenericError(errorCode, errorMessage);
            }
        });
        v03.i(map, "esperantoClient.apPerman…}\n            }\n        }");
        return map;
    }

    @Override // com.spotify.connectivity.sessionapi.SessionClient
    public Single<SendEndSongsResult> sendEndSongs() {
        com.spotify.connectivity.auth.esperanto.proto.SessionClient sessionClient = this.esperantoClient;
        Empty emptyF = Empty.f();
        v03.i(emptyF, "getDefaultInstance()");
        Single map = sessionClient.sendEndSongs(emptyF).map(new o() { // from class: com.spotify.connectivity.sessionesperanto.SessionClientEsperanto.sendEndSongs.1
            @Override // io.reactivex.rxjava3.functions.o
            public final SendEndSongsResult apply(EsSession.SendEndSongsResult sendEndSongsResult) {
                if (sendEndSongsResult.getSuccess()) {
                    return SendEndSongsResult.SendEndSongsSuccess.INSTANCE;
                }
                int errorCode = sendEndSongsResult.getErrorCode();
                String errorDescription = sendEndSongsResult.getErrorDescription();
                v03.i(errorDescription, "it.errorDescription");
                return new SendEndSongsResult.SendEndSongsFailure(errorCode, errorDescription);
            }
        });
        v03.i(map, "esperantoClient.sendEndS…)\n            }\n        }");
        return map;
    }

    @Override // com.spotify.connectivity.sessionapi.SessionClient
    public Observable<lq2> willLogoutAndForgetCurrentUser() {
        com.spotify.connectivity.auth.esperanto.proto.SessionClient sessionClient = this.esperantoClient;
        Empty emptyF = Empty.f();
        v03.i(emptyF, "getDefaultInstance()");
        Observable map = sessionClient.willLogoutAndForgetCurrentUser(emptyF).map(new o() { // from class: com.spotify.connectivity.sessionesperanto.SessionClientEsperanto.willLogoutAndForgetCurrentUser.1
            public final void apply(Empty empty) {
            }

            @Override // io.reactivex.rxjava3.functions.o
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                apply((Empty) obj);
                return lq2.a;
            }
        });
        v03.i(map, "esperantoClient.willLogo…nstance()).map { unit() }");
        return map;
    }

    @Override // com.spotify.connectivity.sessionapi.SessionClient
    public Single<lq2> writeProductStateToLegacyStorage(Map<String, String> map) {
        v03.j(map, "productState");
        com.spotify.connectivity.auth.esperanto.proto.SessionClient sessionClient = this.esperantoClient;
        i iVarBuild = EsSession.ProductStateMap.newBuilder().putAllProductStateMap(map).build();
        v03.i(iVarBuild, "newBuilder().putAllProdu…Map(productState).build()");
        Single map2 = sessionClient.writeProductStateToLegacyStorage((EsSession.ProductStateMap) iVarBuild).map(new o() { // from class: com.spotify.connectivity.sessionesperanto.SessionClientEsperanto.writeProductStateToLegacyStorage.1
            public final void apply(Empty empty) {
            }

            @Override // io.reactivex.rxjava3.functions.o
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                apply((Empty) obj);
                return lq2.a;
            }
        });
        v03.i(map2, "esperantoClient.writePro…).build()).map { unit() }");
        return map2;
    }
}

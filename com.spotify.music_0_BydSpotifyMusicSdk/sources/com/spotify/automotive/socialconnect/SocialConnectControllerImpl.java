package com.spotify.automotive.socialconnect;

import com.google.common.base.Optional;
import com.spotify.automotive.socialconnect.endpoint.SocialConnectEndpoint;
import com.spotify.automotive.socialconnect.endpoint.SocialConnectSession;
import com.spotify.base.java.logging.Logger;
import com.spotify.common.uri.SpotifyUriParserException;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.functions.p;
import io.reactivex.rxjava3.internal.operators.completable.d;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.EmptyList;
import okhttp3.Response;
import p.a01;
import p.if2;
import p.mx;
import p.ng2;
import p.st1;
import p.u42;
import p.uw1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SocialConnectControllerImpl implements SocialConnectController {
    private String physicalIdentifier;
    private final st1 playbackController;
    private final SocialConnectEndpoint socialConnectEndpoint;

    public SocialConnectControllerImpl(st1 st1Var, SocialConnectEndpoint socialConnectEndpoint) {
        v03.j(st1Var, "playbackController");
        v03.j(socialConnectEndpoint, "socialConnectEndpoint");
        this.playbackController = st1Var;
        this.socialConnectEndpoint = socialConnectEndpoint;
        this.physicalIdentifier = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Single<String> localDeviceId() {
        String str = this.physicalIdentifier;
        if (!(!ng2.c0(str))) {
            str = null;
        }
        Single<String> singleJust = str != null ? Single.just(str) : null;
        if (singleJust != null) {
            return singleJust;
        }
        Single<String> singleSingleOrError = this.playbackController.j().map(new o() { // from class: com.spotify.automotive.socialconnect.SocialConnectControllerImpl.localDeviceId.3
            @Override // io.reactivex.rxjava3.functions.o
            public final List<mx> apply(a01 a01Var) {
                List<mx> list = a01Var.e;
                return list == null ? EmptyList.c : list;
            }
        }).map(new o() { // from class: com.spotify.automotive.socialconnect.SocialConnectControllerImpl.localDeviceId.4
            @Override // io.reactivex.rxjava3.functions.o
            public final Optional<mx> apply(List<mx> list) {
                AnonymousClass1<T> anonymousClass1 = new uw1() { // from class: com.spotify.automotive.socialconnect.SocialConnectControllerImpl.localDeviceId.4.1
                    @Override // p.uw1
                    /* JADX INFO: renamed from: apply, reason: merged with bridge method [inline-methods] */
                    public final boolean mo348apply(mx mxVar) {
                        return mxVar.b;
                    }
                };
                Iterator<T> it = list.iterator();
                it.getClass();
                anonymousClass1.getClass();
                while (it.hasNext()) {
                    T next = it.next();
                    if (anonymousClass1.mo348apply(next)) {
                        return Optional.e(next);
                    }
                }
                return Optional.a();
            }
        }).filter(new p() { // from class: com.spotify.automotive.socialconnect.SocialConnectControllerImpl.localDeviceId.5
            @Override // io.reactivex.rxjava3.functions.p
            public final boolean test(Optional<mx> optional) {
                return optional.d();
            }
        }).map(new o() { // from class: com.spotify.automotive.socialconnect.SocialConnectControllerImpl.localDeviceId.6
            @Override // io.reactivex.rxjava3.functions.o
            public final mx apply(Optional<mx> optional) {
                return (mx) optional.c();
            }
        }).map(new o() { // from class: com.spotify.automotive.socialconnect.SocialConnectControllerImpl.localDeviceId.7
            @Override // io.reactivex.rxjava3.functions.o
            public final String apply(mx mxVar) {
                String str2 = mxVar.e;
                return str2 == null ? "" : str2;
            }
        }).take(1L).doOnNext(new g() { // from class: com.spotify.automotive.socialconnect.SocialConnectControllerImpl.localDeviceId.8
            @Override // io.reactivex.rxjava3.functions.g
            public final void accept(String str2) {
                SocialConnectControllerImpl socialConnectControllerImpl = SocialConnectControllerImpl.this;
                v03.i(str2, "id");
                socialConnectControllerImpl.physicalIdentifier = str2;
            }
        }).singleOrError();
        v03.i(singleSingleOrError, "private fun localDeviceI…   .singleOrError()\n    }");
        return singleSingleOrError;
    }

    @Override // com.spotify.automotive.socialconnect.SocialConnectController
    public Single<SocialConnectSession> getCurrentSession() {
        Single<SocialConnectSession> map = localDeviceId().flatMap(new o() { // from class: com.spotify.automotive.socialconnect.SocialConnectControllerImpl.getCurrentSession.1
            @Override // io.reactivex.rxjava3.functions.o
            public final SingleSource<? extends u42<SocialConnectSession>> apply(String str) {
                v03.j(str, "localDeviceId");
                return SocialConnectControllerImpl.this.socialConnectEndpoint.getCurrentSession(str);
            }
        }).map(new o() { // from class: com.spotify.automotive.socialconnect.SocialConnectControllerImpl.getCurrentSession.2
            @Override // io.reactivex.rxjava3.functions.o
            public final SocialConnectSession apply(u42<SocialConnectSession> u42Var) {
                v03.j(u42Var, "response");
                Object obj = u42Var.b;
                SocialConnectSession socialConnectSession = (SocialConnectSession) obj;
                Response response = u42Var.a;
                if (response.i() && socialConnectSession != null) {
                    String.valueOf(obj);
                    return socialConnectSession;
                }
                int i = response.l;
                if (i == 404) {
                    return SocialConnectSession.EMPTY;
                }
                Logger.b("Unexpected error %d when getting current session.", Integer.valueOf(i));
                return SocialConnectSession.EMPTY;
            }
        });
        v03.i(map, "override fun getCurrentS…    }\n            }\n    }");
        return map;
    }

    @Override // com.spotify.automotive.socialconnect.SocialConnectController
    public Single<SocialConnectSession> getOrCreateSession() {
        Single<SocialConnectSession> map = localDeviceId().flatMap(new o() { // from class: com.spotify.automotive.socialconnect.SocialConnectControllerImpl.getOrCreateSession.1
            @Override // io.reactivex.rxjava3.functions.o
            public final SingleSource<? extends u42<SocialConnectSession>> apply(String str) {
                v03.j(str, "deviceId");
                return SocialConnectControllerImpl.this.socialConnectEndpoint.getCurrentOrNewSession(str, "IN_PERSON");
            }
        }).map(new o() { // from class: com.spotify.automotive.socialconnect.SocialConnectControllerImpl.getOrCreateSession.2
            @Override // io.reactivex.rxjava3.functions.o
            public final SocialConnectSession apply(u42<SocialConnectSession> u42Var) {
                v03.j(u42Var, "response");
                Object obj = u42Var.b;
                SocialConnectSession socialConnectSession = (SocialConnectSession) obj;
                Response response = u42Var.a;
                if (!response.i() || socialConnectSession == null) {
                    Logger.b("Unexpected error %d when getting or creating session.", Integer.valueOf(response.l));
                    return SocialConnectSession.EMPTY;
                }
                String.valueOf(obj);
                return socialConnectSession;
            }
        });
        v03.i(map, "override fun getOrCreate…    }\n            }\n    }");
        return map;
    }

    @Override // com.spotify.automotive.socialconnect.SocialConnectController
    public String getScannableUrl(SocialConnectSession socialConnectSession) {
        v03.j(socialConnectSession, "session");
        if (socialConnectSession.joinSessionUri == null) {
            throw new IllegalArgumentException("Session does not have a URI.".toString());
        }
        try {
            StringBuilder sb = new StringBuilder();
            String str = socialConnectSession.joinSessionUri;
            v03.h(str);
            sb.append(new if2(str).e());
            sb.append(SocialConnectControllerImplKt.IN_PERSON_LISTENING_SUFFIX);
            return sb.toString();
        } catch (SpotifyUriParserException e) {
            throw new IllegalArgumentException("Session URI is not a valid Spotify URI.", e);
        }
    }

    @Override // com.spotify.automotive.socialconnect.SocialConnectController
    public Completable leaveSession() {
        Completable completableFlatMapCompletable = getCurrentSession().flatMapCompletable(new o() { // from class: com.spotify.automotive.socialconnect.SocialConnectControllerImpl.leaveSession.1
            @Override // io.reactivex.rxjava3.functions.o
            public final CompletableSource apply(final SocialConnectSession socialConnectSession) {
                v03.j(socialConnectSession, "session");
                socialConnectSession.toString();
                final String str = socialConnectSession.sessionId;
                if (!socialConnectSession.isValid() || str == null) {
                    Logger.g("Requested to leave session when no session is active, doing nothing.", new Object[0]);
                    return d.c;
                }
                Single singleLocalDeviceId = SocialConnectControllerImpl.this.localDeviceId();
                final SocialConnectControllerImpl socialConnectControllerImpl = SocialConnectControllerImpl.this;
                return singleLocalDeviceId.flatMapCompletable(new o() { // from class: com.spotify.automotive.socialconnect.SocialConnectControllerImpl.leaveSession.1.1
                    @Override // io.reactivex.rxjava3.functions.o
                    public final CompletableSource apply(String str2) {
                        v03.j(str2, "localDeviceId");
                        return socialConnectSession.isSessionOwner ? socialConnectControllerImpl.socialConnectEndpoint.deleteSession(str, str2) : socialConnectControllerImpl.socialConnectEndpoint.leaveSession(str, str2);
                    }
                });
            }
        });
        v03.i(completableFlatMapCompletable, "override fun leaveSessio…    }\n            }\n    }");
        return completableFlatMapCompletable;
    }
}

package p;

import com.spotify.base.java.logging.Logger;
import com.spotify.pinpairing.pinpairingimpl.OAuthTokenResponse;
import com.spotify.pinpairing.pinpairingimpl.StartFlowResponseV2;
import java.io.InvalidObjectException;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class at1 implements io.reactivex.rxjava3.functions.g, io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ com.spotify.pinpairing.pinpairingimpl.c e;

    public /* synthetic */ at1(com.spotify.pinpairing.pinpairingimpl.c cVar, int i) {
        this.c = i;
        this.e = cVar;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public void accept(Object obj) {
        int i = this.c;
        com.spotify.pinpairing.pinpairingimpl.c cVar = this.e;
        switch (i) {
            case 0:
                StartFlowResponseV2 startFlowResponseV2 = (StartFlowResponseV2) obj;
                for (ys1 ys1Var : cVar.c) {
                    try {
                        String strC = startFlowResponseV2.c();
                        String strD = startFlowResponseV2.d();
                        String strE = startFlowResponseV2.e();
                        pd1 pd1Var = (pd1) ys1Var;
                        pd1Var.getClass();
                        pd1Var.b.post(new g72(pd1Var, strC, strD.replace("http://", "").replace("https://", ""), strE, 3));
                    } catch (Exception e) {
                        Logger.c(e, "Exception raised by Listener.onUserCodeReceived.", new Object[0]);
                    }
                }
                break;
            case 1:
                StartFlowResponseV2 startFlowResponseV22 = (StartFlowResponseV2) obj;
                cVar.getClass();
                cVar.d(startFlowResponseV22, Math.max(1, startFlowResponseV22.b()) * 1000);
                break;
            case 2:
                cVar.a((Throwable) obj);
                break;
            default:
                OAuthTokenResponse oAuthTokenResponse = (OAuthTokenResponse) obj;
                cVar.getClass();
                Logger.e("OAuth token received. Authentication flow completed.", new Object[0]);
                for (ys1 ys1Var2 : cVar.c) {
                    try {
                        String str = oAuthTokenResponse.a;
                        ((pd1) ys1Var2).b(oAuthTokenResponse.d);
                    } catch (Exception e2) {
                        Logger.c(e2, "Exception raised by Listener.onOAuthTokenReceived.", new Object[0]);
                    }
                }
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) throws InvalidObjectException {
        int i = this.c;
        com.spotify.pinpairing.pinpairingimpl.c cVar = this.e;
        switch (i) {
            case 0:
                cVar.getClass();
                return StartFlowResponseV2.f(cVar.a, ((Response) obj).o);
            default:
                cVar.getClass();
                ResponseBody responseBody = ((Response) obj).o;
                if (responseBody == null) {
                    throw new InvalidObjectException("Response is null.");
                }
                String strQ = responseBody.q();
                if (v03.y(strQ)) {
                    throw new InvalidObjectException("Response is empty.");
                }
                OAuthTokenResponse oAuthTokenResponse = (OAuthTokenResponse) cVar.a.b(OAuthTokenResponse.class, strQ);
                if (v03.y(oAuthTokenResponse.a)) {
                    throw new InvalidObjectException("accessToken is not present.");
                }
                if (v03.y(oAuthTokenResponse.d)) {
                    throw new InvalidObjectException("refreshToken is not present.");
                }
                return oAuthTokenResponse;
        }
    }
}

package p;

import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import com.spotify.player.esperanto.proto.EsContextPlayerError$ContextPlayerError;
import com.spotify.player.model.ErrorType;
import com.spotify.player.model.PlayerError;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xh0 implements io.reactivex.rxjava3.functions.o {
    public static final xh0 c = new xh0();

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        EsContextPlayerError$ContextPlayerError esContextPlayerError$ContextPlayerError = (EsContextPlayerError$ContextPlayerError) obj;
        v03.j(esContextPlayerError$ContextPlayerError, "p0");
        int iOrdinal = esContextPlayerError$ContextPlayerError.e().ordinal();
        ErrorType errorType = ErrorType.X;
        switch (iOrdinal) {
            case 0:
                errorType = ErrorType.c;
                break;
            case 1:
                errorType = ErrorType.e;
                break;
            case 2:
                errorType = ErrorType.k;
                break;
            case 3:
                errorType = ErrorType.l;
                break;
            case 4:
                errorType = ErrorType.n;
                break;
            case 5:
                errorType = ErrorType.o;
                break;
            case 6:
                errorType = ErrorType.f61p;
                break;
            case 7:
                errorType = ErrorType.q;
                break;
            case 8:
                errorType = ErrorType.r;
                break;
            case 9:
                errorType = ErrorType.s;
                break;
            case 10:
                errorType = ErrorType.t;
                break;
            case 11:
                errorType = ErrorType.u;
                break;
            case 12:
                errorType = ErrorType.D;
                break;
            case 13:
                errorType = ErrorType.w;
                break;
            case 14:
                errorType = ErrorType.x;
                break;
            case 15:
                errorType = ErrorType.z;
                break;
            case 16:
                errorType = ErrorType.y;
                break;
            case 17:
                errorType = ErrorType.A;
                break;
            case 18:
                errorType = ErrorType.C;
                break;
            case 19:
                errorType = ErrorType.E;
                break;
            case 20:
                errorType = ErrorType.F;
                break;
            case 21:
                errorType = ErrorType.G;
                break;
            case 22:
                errorType = ErrorType.v;
                break;
            case 23:
                errorType = ErrorType.H;
                break;
            case 24:
                errorType = ErrorType.I;
                break;
            case 25:
                errorType = ErrorType.J;
                break;
            case 26:
                errorType = ErrorType.K;
                break;
            case 27:
                errorType = ErrorType.L;
                break;
            case 28:
                errorType = ErrorType.M;
                break;
            case 29:
                errorType = ErrorType.Q;
                break;
            case 30:
                errorType = ErrorType.N;
                break;
            case 31:
                errorType = ErrorType.O;
                break;
            case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                errorType = ErrorType.P;
                break;
            case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                errorType = ErrorType.W;
                break;
            case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                errorType = ErrorType.V;
                break;
            case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                errorType = ErrorType.m;
                break;
            case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                errorType = ErrorType.B;
                break;
            case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                errorType = ErrorType.R;
                break;
            case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
                errorType = ErrorType.S;
                break;
            case 40:
                errorType = ErrorType.T;
                break;
            case 41:
                errorType = ErrorType.U;
                break;
        }
        PlayerError.Builder builderA = PlayerError.a(errorType);
        String str = (String) esContextPlayerError$ContextPlayerError.f().get("track_uri");
        if (str != null) {
            builderA.d(str);
        }
        String str2 = (String) esContextPlayerError$ContextPlayerError.f().get("context_uri");
        if (str2 != null) {
            builderA.b(str2);
        }
        String str3 = (String) esContextPlayerError$ContextPlayerError.f().get("reasons");
        if (str3 != null) {
            builderA.c(str3);
        }
        PlayerError playerErrorA = builderA.a();
        v03.i(playerErrorA, "builder(errorTypeFromPro…(::reasons)\n    }.build()");
        return playerErrorA;
    }
}

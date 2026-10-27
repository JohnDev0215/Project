package p;

import com.spotify.esperanto.esperanto.ServiceBase;
import com.spotify.remoteconfig.esperanto.proto.EsRemoteConfig$BoolResponse;
import com.spotify.remoteconfig.esperanto.proto.EsRemoteConfig$EnumResponse;
import com.spotify.remoteconfig.esperanto.proto.EsRemoteConfig$IntResponse;
import com.spotify.remoteconfig.esperanto.proto.EsRemoteConfig$LookupRequest;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public final class d32 implements ServiceBase {
    public final String a;
    public final a42 b;

    public d32(a42 a42Var) {
        v03.j(a42Var, "resolver");
        this.a = "spotify.remote_config.esperanto.proto.RemoteConfig";
        this.b = a42Var;
    }

    public final pc a(EsRemoteConfig$LookupRequest esRemoteConfig$LookupRequest) {
        String strF = esRemoteConfig$LookupRequest.f();
        v03.i(strF, "request.scope");
        String name = esRemoteConfig$LookupRequest.getName();
        v03.i(name, "request.name");
        return this.b.d(strF, name);
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public final Single callSingle(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        String str3 = this.a;
        if (v03.b(str, str3)) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        throw new RuntimeException("Attempted to access mismatched [" + str + "], but this service is [" + str3 + ']');
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public final Observable callStream(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        String str3 = this.a;
        if (v03.b(str, str3)) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        throw new RuntimeException("Attempted to access mismatched [" + str + "], but this service is [" + str3 + ']');
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public final byte[] callSync(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        String str3 = this.a;
        if (!v03.b(str, str3)) {
            throw new RuntimeException("Attempted to access mismatched [" + str + "], but this service is [" + str3 + ']');
        }
        if (v03.b(str2, "lookupBool")) {
            EsRemoteConfig$LookupRequest esRemoteConfig$LookupRequestG = EsRemoteConfig$LookupRequest.g(bArr);
            v03.i(esRemoteConfig$LookupRequestG, "request_msg");
            pg0 pg0VarF = EsRemoteConfig$BoolResponse.f();
            Boolean bool = a(esRemoteConfig$LookupRequestG).c;
            if (bool != null) {
                pg0VarF.d(bool.booleanValue());
            }
            com.google.protobuf.i iVarBuild = pg0VarF.build();
            v03.i(iVarBuild, "response.build()");
            byte[] byteArray = ((EsRemoteConfig$BoolResponse) iVarBuild).toByteArray();
            v03.i(byteArray, "lookupBool(request_msg).toByteArray()");
            return byteArray;
        }
        if (v03.b(str2, "lookupInt")) {
            EsRemoteConfig$LookupRequest esRemoteConfig$LookupRequestG2 = EsRemoteConfig$LookupRequest.g(bArr);
            v03.i(esRemoteConfig$LookupRequestG2, "request_msg");
            rg0 rg0VarF = EsRemoteConfig$IntResponse.f();
            Integer num = a(esRemoteConfig$LookupRequestG2).d;
            if (num != null) {
                rg0VarF.d(num.intValue());
            }
            com.google.protobuf.i iVarBuild2 = rg0VarF.build();
            v03.i(iVarBuild2, "response.build()");
            byte[] byteArray2 = ((EsRemoteConfig$IntResponse) iVarBuild2).toByteArray();
            v03.i(byteArray2, "lookupInt(request_msg).toByteArray()");
            return byteArray2;
        }
        if (!v03.b(str2, "lookupEnum")) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        EsRemoteConfig$LookupRequest esRemoteConfig$LookupRequestG3 = EsRemoteConfig$LookupRequest.g(bArr);
        v03.i(esRemoteConfig$LookupRequestG3, "request_msg");
        qg0 qg0VarF = EsRemoteConfig$EnumResponse.f();
        String str4 = a(esRemoteConfig$LookupRequestG3).e;
        if (str4 != null) {
            qg0VarF.d(str4);
        }
        com.google.protobuf.i iVarBuild3 = qg0VarF.build();
        v03.i(iVarBuild3, "response.build()");
        byte[] byteArray3 = ((EsRemoteConfig$EnumResponse) iVarBuild3).toByteArray();
        v03.i(byteArray3, "lookupEnum(request_msg).toByteArray()");
        return byteArray3;
    }

    @Override // com.spotify.esperanto.esperanto.ServiceBase
    public final String getName() {
        return this.a;
    }
}

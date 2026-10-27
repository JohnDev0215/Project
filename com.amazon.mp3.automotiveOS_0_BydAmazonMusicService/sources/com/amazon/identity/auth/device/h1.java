package com.amazon.identity.auth.device;

import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.dcp.sso.IAmazonAccountAuthenticator;
import com.amazon.dcp.sso.IWebserviceCallback;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class h1 implements g0 {
    public static final String b = "com.amazon.identity.auth.device.h1";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final IAmazonAccountAuthenticator f356a;

    /* JADX INFO: compiled from: DCP */
    public class a extends IWebserviceCallback.Stub {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h0 f357a;

        public a(h0 h0Var) {
            this.f357a = h0Var;
        }

        @Override // com.amazon.dcp.sso.IWebserviceCallback
        public void onAuthenticationFailed() throws RemoteException {
            ((j0) this.f357a).a();
        }

        @Override // com.amazon.dcp.sso.IWebserviceCallback
        public void onBadResponse() throws RemoteException {
            ((j0) this.f357a).b();
        }

        @Override // com.amazon.dcp.sso.IWebserviceCallback
        public void onInvalidRequest() throws RemoteException {
            j0 j0Var = (j0) this.f357a;
            j0Var.getClass();
            try {
                Log.e(ga.a(n0.A), "Bad request when registering the child app.");
                j0Var.f389a.onError(8, "Received bad request");
            } catch (RemoteException unused) {
                Log.e(ga.a(n0.A), "RemoteException during bad request callback for registerChildApplication");
            }
        }

        @Override // com.amazon.dcp.sso.IWebserviceCallback
        public void onNetworkError() throws RemoteException {
            ((j0) this.f357a).c();
        }

        @Override // com.amazon.dcp.sso.IWebserviceCallback
        public void onResponseReceived(long j, Map map, byte[] bArr) throws RemoteException {
            h1.this.getClass();
            mc mcVar = new mc();
            md mdVar = new md();
            mdVar.c = j;
            for (Map.Entry entry : map.entrySet()) {
                mdVar.a((String) entry.getKey(), (String) entry.getValue());
            }
            mcVar.a(mdVar);
            if (mcVar.d()) {
                mcVar.b(bArr, bArr.length);
            }
            mcVar.a();
            ((j0) this.f357a).a(mcVar.i);
        }
    }

    public h1(IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
        this.f356a = iAmazonAccountAuthenticator;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.amazon.identity.auth.device.g0
    public void a(String str, ld ldVar, h0 h0Var, y5 y5Var) {
        try {
            IAmazonAccountAuthenticator iAmazonAccountAuthenticator = this.f356a;
            Uri uri = Uri.parse(ldVar.b());
            HttpVerb httpVerb = ldVar.h;
            String value = httpVerb != null ? httpVerb.getValue() : null;
            int size = ldVar.b.size();
            HashMap map = new HashMap(size);
            for (int i = 0; i < size; i++) {
                String strA = ldVar.a(i);
                String strA2 = ldVar.a(i);
                map.put(strA, strA2 == null ? null : ldVar.f423a.get(strA2.toLowerCase(Locale.US)));
            }
            iAmazonAccountAuthenticator.callGetCredentialsWebservice(uri, value, map, ldVar.i, new a(h0Var));
        } catch (RemoteException unused) {
            Log.e(ga.a(b), "RemoteException calling AmazonAccountAuthenticator.callGetCredentialsWebservice");
        }
    }
}

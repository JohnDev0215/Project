package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import com.amazon.device.information.contract.DeviceInformationContract;
import com.amazon.identity.auth.device.framework.RemoteMAPException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class m3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f427a = "com.amazon.identity.auth.device.m3";

    /* JADX INFO: compiled from: DCP */
    public static final class a extends j4 {
        public final s5 f;
        public String g;

        public a(Context context) {
            super(context);
            this.f = new s5(context);
        }

        @Override // com.amazon.identity.auth.device.j4, com.amazon.identity.auth.device.o5
        public synchronized String d() {
            String str = this.g;
            if (str != null) {
                return str;
            }
            try {
                this.g = m3.a(this.f, "dsn");
            } catch (RemoteMAPException e) {
                Log.e(ga.a(m3.f427a), "Unable to retrieve Device Serial Number from Amazon Device Information Component. Falling back to 3P value.", e);
                this.g = super.d();
            }
            return this.g;
        }
    }

    public static String a(s5 s5Var, String str) throws RemoteMAPException {
        try {
            return (String) s5Var.a(DeviceInformationContract.AUTHORITY_URI, new l3(str));
        } catch (RemoteMAPException e) {
            yd.a("CouldNotContactADIP:" + str, new String[0]);
            throw e;
        }
    }
}

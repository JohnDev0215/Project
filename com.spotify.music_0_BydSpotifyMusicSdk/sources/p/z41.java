package p;

import com.google.protobuf.ByteString;
import com.spotify.contexts.InstallationId;
import com.spotify.contexts.Sdk;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class z41 implements di0 {
    public final /* synthetic */ int a;
    public final Object b;

    public /* synthetic */ z41(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // p.di0
    public final String a() {
        switch (this.a) {
            case 0:
                return "context_installation_id";
            default:
                return "context_sdk";
        }
    }

    @Override // p.di0
    public final com.google.protobuf.i getData() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                y41 y41VarF = InstallationId.f();
                byte[] bytes = ((v4) obj).l().getBytes();
                ByteString byteString = ByteString.c;
                y41VarF.d(ByteString.c(bytes, 0, bytes.length));
                return y41VarF.build();
            default:
                zl1 zl1Var = (zl1) ((zi0) ((xy1) obj).get());
                String strH = !zl1Var.c ? "1.1.0-essopt" : "1.1.0";
                Integer num = zl1Var.b;
                if (zl1.b(num) != null) {
                    strH = strH + String.format(Locale.US, "-sendinterval%d.0s", zl1.b(num));
                }
                if (zl1Var.d) {
                    strH = u4.h(strH, "-startupsend");
                }
                if (zl1Var.e) {
                    strH = u4.h(strH, "-onlinesend");
                }
                if (zl1Var.f) {
                    strH = u4.h(strH, "-dynamicmixedauth");
                }
                if (zl1Var.g) {
                    strH = u4.h(strH, "-bgsend");
                }
                if (zl1Var.h) {
                    strH = u4.h(strH, "-sendonbcd");
                }
                if (zl1Var.i) {
                    strH = u4.h(strH, "-authFlow");
                }
                int i2 = zl1Var.m;
                if (i2 > 0) {
                    strH = strH + String.format(Locale.US, "-heartbeat%d/%d", Integer.valueOf(i2), Integer.valueOf(zl1Var.n));
                }
                r72 r72VarG = Sdk.g();
                r72VarG.e(strH);
                r72VarG.d();
                return r72VarG.build();
        }
    }
}

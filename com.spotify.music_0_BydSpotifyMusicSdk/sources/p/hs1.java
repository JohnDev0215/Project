package p;

import com.spotify.automotive.eventsender.events.proto.AutomotivePartnerVoiceCommand;

/* JADX INFO: loaded from: classes.dex */
public final class hs1 {
    public final wi0 a;
    public final up2 b;

    public hs1(wi0 wi0Var, up2 up2Var) {
        v03.j(wi0Var, "eventSender");
        v03.j(up2Var, "ubiLogger");
        this.a = wi0Var;
        this.b = up2Var;
    }

    public final void a(String str, js1 js1Var) {
        String strB;
        v03.j(js1Var, "params");
        if (str != null) {
            strB = this.b.b(new dq2(str));
        } else {
            strB = null;
        }
        b(strB, js1Var);
    }

    public final void b(String str, js1 js1Var) {
        sj sjVarK = AutomotivePartnerVoiceCommand.k();
        if (str != null) {
            sjVarK.h(str);
        }
        sjVarK.d(js1Var.f.toString());
        sjVarK.g(js1Var.a);
        sjVarK.j(js1Var.c);
        sjVarK.e(js1Var.e);
        sjVarK.f(js1Var.d);
        AutomotivePartnerVoiceCommand automotivePartnerVoiceCommand = (AutomotivePartnerVoiceCommand) sjVarK.build();
        v03.i(automotivePartnerVoiceCommand, "event");
        this.a.a(automotivePartnerVoiceCommand);
    }
}

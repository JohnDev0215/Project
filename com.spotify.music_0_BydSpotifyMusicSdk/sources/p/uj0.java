package p;

import com.google.protobuf.ByteString;
import com.spotify.eventsender.Fragment;
import com.spotify.eventsender.eventsender.SequenceNumberProvider$SequenceNumberType;
import com.spotify.messages.EventSenderStats2NonAuth;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class uj0 implements fz0 {
    public final ei0 c;
    public final mb2 e;
    public final zb0 k;
    public final xt l;

    public uj0(xt xtVar, zb0 zb0Var, mb2 mb2Var, ei0 ei0Var) {
        this.c = ei0Var;
        this.e = mb2Var;
        this.k = zb0Var;
        this.l = xtVar;
    }

    @Override // p.fz0
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final ai0 g(EventSenderStats2NonAuth eventSenderStats2NonAuth, byte[] bArr) {
        v03.j(eventSenderStats2NonAuth, "input");
        v03.j(bArr, "sequenceId");
        long jA = this.e.a("EventSenderStats2NonAuth", bArr, SequenceNumberProvider$SequenceNumberType.e);
        byte[] byteArray = eventSenderStats2NonAuth.toByteArray();
        v03.i(byteArray, "input.toByteArray()");
        ((u11) this.l).getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        ei0 ei0Var = this.c;
        zb0 zb0Var = this.k;
        zb0Var.getClass();
        v51<Fragment> v51VarH = ux2.f(byteArray, jCurrentTimeMillis, ei0Var, zb0Var, null).h();
        v03.i(v51VarH, "fragmentsContainer.fragmentList");
        ArrayList arrayList = new ArrayList(lu.W(v51VarH));
        for (Fragment fragment : v51VarH) {
            arrayList.add(new pr1(fragment.getName(), fragment.getData()));
        }
        return new ai0(-100L, "EventSenderStats2NonAuth", ByteString.c(bArr, 0, bArr.length), jA, arrayList);
    }
}

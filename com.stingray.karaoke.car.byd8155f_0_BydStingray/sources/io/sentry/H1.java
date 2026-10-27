package io.sentry;

import h2.AbstractC1347d;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final class H1 extends X0 implements InterfaceC1485m0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public File f15870A;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f15873E;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public Date f15875G;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public HashMap f15879K;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public io.sentry.protocol.t f15872D = new io.sentry.protocol.t((UUID) null);
    public String B = "replay_event";

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public G1 f15871C = G1.SESSION;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public List f15877I = new ArrayList();

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public List f15878J = new ArrayList();

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public List f15876H = new ArrayList();

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public Date f15874F = AbstractC1985B.f();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || H1.class != obj.getClass()) {
            return false;
        }
        H1 h12 = (H1) obj;
        return this.f15873E == h12.f15873E && h6.e.i(this.B, h12.B) && this.f15871C == h12.f15871C && h6.e.i(this.f15872D, h12.f15872D) && h6.e.i(this.f15876H, h12.f15876H) && h6.e.i(this.f15877I, h12.f15877I) && h6.e.i(this.f15878J, h12.f15878J);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.B, this.f15871C, this.f15872D, Integer.valueOf(this.f15873E), this.f15876H, this.f15877I, this.f15878J});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("type");
        c1489n1.L(this.B);
        c1489n1.w("replay_type");
        c1489n1.I(k10, this.f15871C);
        c1489n1.w("segment_id");
        c1489n1.H(this.f15873E);
        c1489n1.w("timestamp");
        c1489n1.I(k10, this.f15874F);
        if (this.f15872D != null) {
            c1489n1.w("replay_id");
            c1489n1.I(k10, this.f15872D);
        }
        if (this.f15875G != null) {
            c1489n1.w("replay_start_timestamp");
            c1489n1.I(k10, this.f15875G);
        }
        if (this.f15876H != null) {
            c1489n1.w("urls");
            c1489n1.I(k10, this.f15876H);
        }
        if (this.f15877I != null) {
            c1489n1.w("error_ids");
            c1489n1.I(k10, this.f15877I);
        }
        if (this.f15878J != null) {
            c1489n1.w("trace_ids");
            c1489n1.I(k10, this.f15878J);
        }
        h6.f.t(this, c1489n1, k10);
        HashMap map = this.f15879K;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f15879K, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}

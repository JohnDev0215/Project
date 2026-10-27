package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.me;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class oe extends me {
    public static final String l = "oe";

    /* JADX INFO: compiled from: DCP */
    public static class a extends me.a<oe> {
        @Override // com.amazon.identity.auth.device.me.a
        public me a() {
            return new oe(this.f438a, this.b, this.c, this.d, null, null, this.e, this.f, this.g);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public oe(String str, String str2, String str3, String str4, String str5, String str6, Long l2, Double d, Boolean bool) {
        super(str, str2, str3, str4, null, null, l2, d, bool, "20220722N", ga.a());
        boolean z = ha.f;
    }

    @Override // com.amazon.identity.auth.device.me
    public void a() {
        toString();
        ga.a(l);
    }
}

package com.amazon.identity.auth.device;

import android.os.Bundle;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class x6 extends r8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f658a;
    public final /* synthetic */ JSONObject b;
    public final /* synthetic */ v6 c;

    public x6(v6 v6Var, String str, JSONObject jSONObject) {
        this.c = v6Var;
        this.f658a = str;
        this.b = jSONObject;
    }

    @Override // com.amazon.identity.auth.device.r8
    public void finish(Bundle bundle) {
        this.c.b("mapJSCallback", this.f658a, this.b.toString());
    }
}

package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.actor.ActorManagerCommunication;
import com.amazon.identity.auth.device.me;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ha {
    public static final long e = wa.a(1, TimeUnit.MILLISECONDS);
    public static volatile boolean f = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f8 f370a;
    public final Context b;
    public final i5 c;
    public final w5 d = new w5();

    public ha(Context context) {
        this.f370a = new f8(context, "map_version_cache");
        this.b = context;
        this.c = new i5(context);
    }

    public JSONObject a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("current_version", "20220722N");
            jSONObject.put(ActorManagerCommunication.SwitchActorAction.KEY_PACAKGE_NAME, this.b.getPackageName());
            jSONObject.put("platform", "Android");
            jSONObject.put("client_metrics_integrated", yd.b(this.b) || yd.c());
            synchronized (ha.class) {
                String string = this.f370a.f313a.getString("map_version_recorded_server", null);
                if ("20220722N".equals(string)) {
                    f = false;
                } else {
                    jSONObject.put("previous_version", string);
                    f = true;
                }
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public void b() {
        if (d()) {
            if (qe.m(this.b)) {
                yd.a(yd.f685a).a("MAP_3P", "Daily_Version_Distribution", "20220722N");
                yd.b("20220722N", new String[0]);
                me.a aVar = (me.a) yd.a();
                aVar.f438a = "Daily_Version_Distribution";
                aVar.a().a();
            } else if (t9.f(this.c.f382a)) {
                yd.a(yd.f685a).a("MAP_FireOS", "Daily_Version_Distribution", "20220722N");
                yd.b("20220722N", new String[0]);
                me.a aVar2 = (me.a) yd.a();
                aVar2.f438a = "Daily_Version_Distribution";
                aVar2.a().a();
            }
        }
        if (c()) {
            if (qe.m(this.b)) {
                yd.a(yd.f685a).a("MAP_3P", "Bump_Version_Statistics", "20220722N");
                me.a aVar3 = (me.a) yd.a();
                aVar3.f438a = "Bump_Version_Statistics";
                aVar3.a().a();
                return;
            }
            if (t9.f(this.c.f382a)) {
                yd.a(yd.f685a).a("MAP_FireOS", "Bump_Version_Statistics", "20220722N");
                me.a aVar4 = (me.a) yd.a();
                aVar4.f438a = "Bump_Version_Statistics";
                aVar4.a().a();
            }
        }
    }

    public boolean c() {
        boolean z;
        synchronized (ha.class) {
            z = !"20220722N".equals(this.f370a.f313a.getString("map_version_recorded_client", null));
            if (z) {
                this.f370a.a("map_version_recorded_client", "20220722N");
            }
        }
        return z;
    }

    public boolean d() {
        boolean z;
        synchronized (ha.class) {
            long j = this.f370a.f313a.getLong("last_time_report_version", 0L);
            this.d.getClass();
            z = j + e <= System.currentTimeMillis();
            if (z) {
                this.d.getClass();
                this.f370a.a("last_time_report_version", System.currentTimeMillis());
            }
        }
        return z;
    }
}

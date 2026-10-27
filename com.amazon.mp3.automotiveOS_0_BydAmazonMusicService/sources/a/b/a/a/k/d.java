package a.b.a.a.k;

import com.netinsight.sye.syeClient.generated.enums.PlaybackType;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f74a = new a();
    public final PlaybackType b;
    public final long c;
    public final long d;
    public final double e;

    public static final class a {
    }

    public d(String str) {
        JSONObject jSONObject = new JSONObject(str);
        this.b = PlaybackType.INSTANCE.a(jSONObject.getInt("playbackType"));
        this.c = jSONObject.getLong("currentPosUtcMillis");
        this.d = jSONObject.getLong("livePosUtcMillis");
        this.e = jSONObject.getDouble("thumbnailFillrate");
    }

    public final long a() {
        return this.c;
    }

    public final long b() {
        return this.d;
    }

    public final PlaybackType c() {
        return this.b;
    }

    public final double d() {
        return this.e;
    }
}

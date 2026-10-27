package a.b.a.a.k;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f72a = new a();
    public final String b;
    public final String c;
    public final String d;

    public static final class a {
    }

    public b(String str) {
        JSONObject jSONObject = new JSONObject(str);
        String string = jSONObject.getString("channelId");
        Intrinsics.checkExpressionValueIsNotNull(string, "jsonObject.getString(\"channelId\")");
        this.b = string;
        String string2 = jSONObject.getString("EPGId");
        Intrinsics.checkExpressionValueIsNotNull(string2, "jsonObject.getString(\"EPGId\")");
        this.c = string2;
        String string3 = jSONObject.getString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        Intrinsics.checkExpressionValueIsNotNull(string3, "jsonObject.getString(\"type\")");
        this.d = string3;
        jSONObject.getBoolean("timeshift");
        jSONObject.getLong("timeshiftDurationMillis");
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.c;
    }

    public final String c() {
        return this.d;
    }
}

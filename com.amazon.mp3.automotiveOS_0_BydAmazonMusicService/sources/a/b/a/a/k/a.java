package a.b.a.a.k;

import com.netinsight.sye.syeClient.generated.enums.AudioCodecProfile;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0013a f71a = new C0013a();
    public final String b;
    public final String c;
    public final a.b.a.a.k.e.a d;
    public final AudioCodecProfile e;
    public final int f;

    /* JADX INFO: renamed from: a.b.a.a.k.a$a, reason: collision with other inner class name */
    public static final class C0013a {
    }

    public a(String str) {
        a.b.a.a.k.e.a aVar;
        JSONObject jSONObject = new JSONObject(str);
        String string = jSONObject.getString("language");
        Intrinsics.checkExpressionValueIsNotNull(string, "jsonObject.getString(\"language\")");
        this.b = string;
        String string2 = jSONObject.getString("languageDescription");
        Intrinsics.checkExpressionValueIsNotNull(string2, "jsonObject.getString(\"languageDescription\")");
        this.c = string2;
        int i = jSONObject.getInt("codec");
        if (i == 0) {
            aVar = a.b.a.a.k.e.a.Undefined;
        } else if (i == 1) {
            aVar = a.b.a.a.k.e.a.AAC;
        } else {
            if (i != 2) {
                throw new IllegalArgumentException("No AudioCodec with value " + i);
            }
            aVar = a.b.a.a.k.e.a.EC3;
        }
        this.d = aVar;
        this.e = AudioCodecProfile.INSTANCE.a(jSONObject.getInt("codecProfile"));
        this.f = jSONObject.getInt("numChannels");
    }
}

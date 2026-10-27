package com.netinsight.sye.syeClient.audio;

import a.b.a.a.k.e.a;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u0000 \u001d2\u00020\u0001:\u0001\rB\u001f\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u0013¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004R\"\u0010\f\u001a\u00020\u00058\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\u0012\u001a\u00020\u00028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0004\"\u0004\b\u0010\u0010\u0011R\"\u0010\u001a\u001a\u00020\u00138\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001e"}, d2 = {"Lcom/netinsight/sye/syeClient/audio/SyeAudioPreferences;", "Lcom/netinsight/sye/syeClient/audio/ISyeAudioPreferences;", "", "toJson", "()Ljava/lang/String;", "Lcom/netinsight/sye/syeClient/audio/SyeAudioCodec;", "b", "Lcom/netinsight/sye/syeClient/audio/SyeAudioCodec;", "getCodec", "()Lcom/netinsight/sye/syeClient/audio/SyeAudioCodec;", "setCodec", "(Lcom/netinsight/sye/syeClient/audio/SyeAudioCodec;)V", "codec", "a", "Ljava/lang/String;", "getLanguage", "setLanguage", "(Ljava/lang/String;)V", "language", "", "c", "I", "getChannels", "()I", "setChannels", "(I)V", "channels", "<init>", "(Ljava/lang/String;Lcom/netinsight/sye/syeClient/audio/SyeAudioCodec;I)V", "Companion", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeAudioPreferences implements ISyeAudioPreferences {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion();

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public String language;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public SyeAudioCodec codec;

    /* JADX INFO: renamed from: c, reason: from kotlin metadata */
    public int channels;

    /* JADX INFO: renamed from: com.netinsight.sye.syeClient.audio.SyeAudioPreferences$a, reason: from kotlin metadata */
    public static final class Companion {
    }

    public SyeAudioPreferences(String str, SyeAudioCodec syeAudioCodec, int i) {
        this.language = str;
        this.codec = syeAudioCodec;
        this.channels = i;
    }

    @JvmStatic
    public static final SyeAudioPreferences parseJson(String str) {
        a aVar;
        INSTANCE.getClass();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("language");
            Intrinsics.checkExpressionValueIsNotNull(string, "jsonObject.getString(LANGUAGE)");
            int i = jSONObject.getInt("codec");
            if (i == 0) {
                aVar = a.Undefined;
            } else if (i == 1) {
                aVar = a.AAC;
            } else {
                if (i != 2) {
                    throw new IllegalArgumentException("No AudioCodec with value " + i);
                }
                aVar = a.EC3;
            }
            return new SyeAudioPreferences(string, a.b.a.a.n.a.a(aVar), jSONObject.getInt("channels"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioPreferences
    public int getChannels() {
        return this.channels;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioPreferences
    public SyeAudioCodec getCodec() {
        return this.codec;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioPreferences
    public String getLanguage() {
        return this.language;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioPreferences
    public void setChannels(int i) {
        this.channels = i;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioPreferences
    public void setCodec(SyeAudioCodec syeAudioCodec) {
        this.codec = syeAudioCodec;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioPreferences
    public void setLanguage(String str) {
        this.language = str;
    }

    public final String toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("language", getLanguage());
        jSONObject.put("codec", getCodec().getValue());
        jSONObject.put("channels", getChannels());
        String string = jSONObject.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "JSONObject().apply {\n   …els)\n        }.toString()");
        return string;
    }
}

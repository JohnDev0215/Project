package a.b.a.a.e;

import com.netinsight.sye.syeClient.audio.ISyeAudioTrack;
import com.netinsight.sye.syeClient.audio.SyeAudioCodec;
import com.netinsight.sye.syeClient.audio.SyeAudioCodecProfile;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public final class h implements ISyeAudioTrack {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f54a = new a();
    public final String b;
    public final String c;
    public final int d;
    public final SyeAudioCodec e;
    public final SyeAudioCodecProfile f;
    public final String g;

    public static final class a {
        @JvmStatic
        public final List<ISyeAudioTrack> a(String str) {
            a.b.a.a.k.a aVar;
            try {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    ArrayList<a.b.a.a.k.a> arrayList = new ArrayList(jSONArray.length());
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        String string = jSONArray.getJSONObject(i).toString();
                        Intrinsics.checkExpressionValueIsNotNull(string, "array.getJSONObject(i).toString()");
                        if (Intrinsics.areEqual(string, "null")) {
                            aVar = null;
                        } else {
                            try {
                                aVar = new a.b.a.a.k.a(string);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (aVar != null) {
                            arrayList.add(aVar);
                        }
                    }
                    ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
                    for (a.b.a.a.k.a aVar2 : arrayList) {
                        arrayList2.add(new h(aVar2.b, aVar2.c, aVar2.f, a.b.a.a.n.a.a(aVar2.d), a.b.a.a.n.a.a(aVar2.e), a.b.a.a.n.a.a(aVar2.d).name()));
                    }
                    return CollectionsKt___CollectionsKt.toList(arrayList2);
                } catch (JSONException e2) {
                    throw new RuntimeException(e2);
                }
            } catch (JSONException e3) {
                throw new RuntimeException(e3);
            }
        }
    }

    public h(String str, String str2, int i, SyeAudioCodec syeAudioCodec, SyeAudioCodecProfile syeAudioCodecProfile, String str3) {
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = syeAudioCodec;
        this.f = syeAudioCodecProfile;
        this.g = str3;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioTrack
    public SyeAudioCodec getAudioCodec() {
        return this.e;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioTrack
    public SyeAudioCodecProfile getAudioCodecProfile() {
        return this.f;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioTrack
    public String getCodecName() {
        return this.g;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioTrack
    public String getLanguage() {
        return this.b;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioTrack
    public String getLanguageDescription() {
        return this.c;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioTrack
    public int getNumChannels() {
        return this.d;
    }
}

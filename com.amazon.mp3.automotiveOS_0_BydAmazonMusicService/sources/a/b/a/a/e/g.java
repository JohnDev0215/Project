package a.b.a.a.e;

import com.netinsight.sye.syeClient.audio.ISyeAudioStreamInfo;
import com.netinsight.sye.syeClient.audio.SyeAudioCodec;
import com.netinsight.sye.syeClient.audio.SyeAudioCodecProfile;

/* JADX INFO: loaded from: classes.dex */
public final class g implements ISyeAudioStreamInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f53a = new a();
    public final String b;
    public final String c;
    public final SyeAudioCodec d;
    public final SyeAudioCodecProfile e;
    public final int f;
    public final int g;

    public static final class a {
    }

    public g(String str, String str2, SyeAudioCodec syeAudioCodec, SyeAudioCodecProfile syeAudioCodecProfile, int i, int i2) {
        this.b = str;
        this.c = str2;
        this.d = syeAudioCodec;
        this.e = syeAudioCodecProfile;
        this.f = i;
        this.g = i2;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioStreamInfo
    public SyeAudioCodec getCodec() {
        return this.d;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioStreamInfo
    public SyeAudioCodecProfile getCodecProfile() {
        return this.e;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioStreamInfo
    public String getLanguage() {
        return this.b;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioStreamInfo
    public String getLanguageDescription() {
        return this.c;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioStreamInfo
    public int getNumChannels() {
        return this.f;
    }

    @Override // com.netinsight.sye.syeClient.audio.ISyeAudioStreamInfo
    public int getSampleRate() {
        return this.g;
    }
}

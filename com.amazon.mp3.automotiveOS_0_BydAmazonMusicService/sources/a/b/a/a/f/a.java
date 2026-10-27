package a.b.a.a.f;

import a.b.a.a.k.b;
import com.netinsight.sye.syeClient.channels.ISyeChannel;

/* JADX INFO: loaded from: classes.dex */
public final class a implements ISyeChannel {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0008a f55a = new C0008a();
    public final String b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: a.b.a.a.f.a$a, reason: collision with other inner class name */
    public static final class C0008a {
    }

    public a(b bVar) {
        this.b = bVar.a();
        this.c = bVar.b();
        this.d = bVar.c();
    }

    @Override // com.netinsight.sye.syeClient.channels.ISyeChannel
    public String getChannelId() {
        return this.b;
    }

    @Override // com.netinsight.sye.syeClient.channels.ISyeChannel
    public String getEpgId() {
        return this.c;
    }

    @Override // com.netinsight.sye.syeClient.channels.ISyeChannel
    public String getType() {
        return this.d;
    }
}

package a.b.a.a.o.d.b;

import a.b.a.a.o.d.b.a;
import com.netinsight.sye.syeClient.SyeAPI;
import com.netinsight.sye.syeClient.video.SyeVideoSample;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a f114a;
    public final /* synthetic */ SyeVideoSample b;

    public e(a aVar, SyeVideoSample syeVideoSample) {
        this.f114a = aVar;
        this.b = syeVideoSample;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a aVar;
        SyeVideoSample syeVideoSample;
        a.b.a.a.i.b bVar;
        String str;
        String string;
        StringBuilder sb;
        String str2;
        a aVar2 = this.f114a;
        if (aVar2.d == a.e.STOPPED) {
            aVar2.c.a(aVar2.b, "enterExecutingState: begin");
            a.e eVar = aVar2.d;
            if (eVar == a.e.TERMINATED) {
                bVar = aVar2.c;
                str = aVar2.b;
                sb = new StringBuilder();
                sb.append("Unable to start an ");
                sb.append("TERMINATED");
                str2 = " instance";
            } else {
                a.e eVar2 = a.e.EXECUTING;
                if (eVar == eVar2) {
                    bVar = aVar2.c;
                    str = aVar2.b;
                    sb = new StringBuilder();
                    sb.append("Attempting to enter already current state: ");
                    str2 = "EXECUTING";
                } else {
                    aVar2.a(eVar2);
                    bVar = aVar2.c;
                    str = aVar2.b;
                    string = "enterExecutingState: end";
                    bVar.a(str, string);
                }
            }
            sb.append(str2);
            string = sb.toString();
            bVar.a(str, string);
        }
        a aVar3 = this.f114a;
        if (aVar3.d != a.e.EXECUTING) {
            aVar3.c.a(aVar3.b, "Received videoSample in state: " + this.f114a.d.name());
            return;
        }
        if (aVar3.i.size() >= 1000) {
            a aVar4 = this.f114a;
            aVar4.g.addAndGet(aVar4.i.size());
            this.f114a.i.clear();
            this.f114a.a(a.b.a.a.k.e.f.a.PlatformException, -1L, "Video queue exceeded max count");
            return;
        }
        long syncTimeMicros = SyeAPI.INSTANCE.getSyncTimeMicros();
        a aVar5 = this.f114a;
        if (aVar5.k != null) {
            a.c cVar = aVar5.e;
            if (cVar != null) {
                if (((syncTimeMicros > cVar.d ? 1 : (syncTimeMicros == cVar.d ? 0 : -1)) > 0) || syncTimeMicros - cVar.c >= ((long) cVar.f102a)) {
                    aVar5.c.a(aVar5.b, "Draining has timed out: starting new decoding session");
                    aVar = this.f114a;
                    syeVideoSample = this.b;
                }
            }
            this.f114a.i.offer(this.b);
            this.f114a.a(syncTimeMicros);
        }
        aVar5.c.a(aVar5.b, "Decoder is null: starting new decoding session");
        aVar = this.f114a;
        syeVideoSample = this.b;
        a.a(aVar, syeVideoSample, aVar.v, true);
        this.f114a.i.offer(this.b);
        this.f114a.a(syncTimeMicros);
    }
}

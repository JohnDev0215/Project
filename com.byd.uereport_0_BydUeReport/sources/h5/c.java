package h5;

import com.byd.uereport.bean.ReportBean;

/* JADX INFO: compiled from: VoiceManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f5364a;

    public c(d dVar) {
        this.f5364a = dVar;
    }

    public void a() {
        this.f5364a.a();
    }

    public void b(ReportBean reportBean, b bVar) {
        d dVar = this.f5364a;
        if (dVar == null) {
            g5.a.c("voiceStrategy is not init.");
        } else {
            dVar.b(reportBean, bVar);
        }
    }
}

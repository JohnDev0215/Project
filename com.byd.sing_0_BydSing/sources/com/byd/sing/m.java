package com.byd.sing;

import com.micphone.TsBydMicManager;

/* JADX INFO: compiled from: TsBydMicManagerProxy.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: compiled from: TsBydMicManagerProxy.java */
    class a implements TsBydMicManager.TsDongleListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f3258a;

        a(d dVar) {
            this.f3258a = dVar;
        }

        @Override // com.micphone.TsBydMicManager.TsDongleListener
        public void attach() {
            d dVar = this.f3258a;
            if (dVar != null) {
                dVar.attach();
            }
        }

        @Override // com.micphone.TsBydMicManager.TsDongleListener
        public void detach() {
            d dVar = this.f3258a;
            if (dVar != null) {
                dVar.detach();
            }
        }

        @Override // com.micphone.TsBydMicManager.TsDongleListener
        public void onMicPowerChanged(int i, boolean z) {
            d dVar = this.f3258a;
            if (dVar != null) {
                dVar.onMicPowerChanged(i, z);
            }
        }
    }

    public static void a() {
        com.byd.sing.a.a("init");
        try {
            TsBydMicManager.getInstance(SingApplication.a(), j.f().h()).micInit();
        } catch (Exception e) {
            com.byd.sing.a.g("init error", e);
        }
    }

    public static boolean b() {
        try {
            return TsBydMicManager.getInstance(SingApplication.a(), j.f().h()).isThunderMic();
        } catch (Exception e) {
            com.byd.sing.a.g("isMicConnect error", e);
            return false;
        }
    }

    public static void c(int i) {
        com.byd.sing.a.a("setKaraokeEffect value = " + i);
        try {
            TsBydMicManager.getInstance(SingApplication.a(), j.f().h()).setEffect(i, false);
        } catch (Exception e) {
            com.byd.sing.a.g("setKaraokeEffect error", e);
        }
    }

    public static void d(d dVar) {
        TsBydMicManager.getInstance(SingApplication.a(), j.f().h()).setDongleListener(new a(dVar));
    }

    public static void e(int i) {
        com.byd.sing.a.a("setMicPercentage value = " + i);
        try {
            TsBydMicManager.getInstance(SingApplication.a(), j.f().h()).setVolume(i);
        } catch (Exception e) {
            com.byd.sing.a.g("setMicPercentage error", e);
        }
    }

    public static void f(int i) {
        com.byd.sing.a.a("setReverbPercentage value = " + i);
        try {
            TsBydMicManager.getInstance(SingApplication.a(), j.f().h()).setReverb(i);
        } catch (Exception e) {
            com.byd.sing.a.g("setReverbPercentage error", e);
        }
    }
}

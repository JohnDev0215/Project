package b.b.g.o.h;

import com.byd.carsettings.sound.model.Manufactor;
import com.byd.carsettings.sound.model.SpeakerLayoutMode;

/* JADX INFO: compiled from: AmpConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Manufactor f1342a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1343b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public SpeakerLayoutMode f1344c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f1345d;

    public static Manufactor a(a aVar) {
        return aVar != null ? aVar.f1342a : Manufactor.DYNAUDIO;
    }

    public static boolean b(a aVar) {
        return a(aVar) == Manufactor.BS;
    }

    public static boolean c(a aVar) {
        return a(aVar) == Manufactor.DYNAUDIO;
    }

    public String toString() {
        return "AmpConfig{manufactor=" + this.f1342a + ", level=" + this.f1343b + ", mode=" + this.f1344c + ", ampLevel=" + this.f1345d + '}';
    }
}

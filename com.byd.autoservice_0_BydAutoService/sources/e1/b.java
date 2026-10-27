package e1;

import android.content.Context;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.media.AudioManager;
import android.view.KeyEvent;
import com.bydux.api.common.tools.log.BydLogHelper;
import o1.b;

/* JADX INFO: compiled from: MusicFunction.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2803b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2802a = "MusicFunction";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2804c = -1;

    public b(Context context) {
        this.f2803b = null;
        this.f2803b = context;
    }

    public final int a() {
        Context context = this.f2803b;
        if (context == null) {
            return 0;
        }
        return BYDAutoSettingDevice.getInstance(context).getRearviewMirrorIsState();
    }

    public void b() {
        BydLogHelper.logD("MusicFunction", "mediaPlayPause");
        AudioManager audioManager = (AudioManager) this.f2803b.getSystemService("audio");
        BydLogHelper.logD("MusicFunction", "nextMusic am.isMusicActive() =" + audioManager.isMusicActive());
        KeyEvent keyEvent = new KeyEvent(0, 85);
        KeyEvent keyEvent2 = new KeyEvent(1, 85);
        keyEvent2.setSource(2023);
        audioManager.dispatchMediaKeyEvent(keyEvent);
        audioManager.dispatchMediaKeyEvent(keyEvent2);
    }

    public void c(int i3) {
        BydLogHelper.logD("MusicFunction", "nextMusic  click =" + i3);
        if (a() == 1) {
            BydLogHelper.logD("MusicFunction", "getRearviewMirrorIsState =" + a());
            return;
        }
        int i4 = this.f2804c;
        if (i4 == 7 && i3 == 2) {
            b.c cVar = new b.c();
            cVar.c("foreground", o1.b.f4451b);
            o1.b.c(this.f2803b, 11, 27, cVar, null);
            return;
        }
        if (i4 == 18 && i3 == 2) {
            b.c cVar2 = new b.c();
            cVar2.c("foreground", o1.b.f4451b);
            o1.b.c(this.f2803b, 12, 27, cVar2, null);
            return;
        }
        AudioManager audioManager = (AudioManager) this.f2803b.getSystemService("audio");
        BydLogHelper.logD("MusicFunction", "nextMusic am.isMusicActive() =" + audioManager.isMusicActive());
        KeyEvent keyEvent = new KeyEvent(0, 87);
        KeyEvent keyEvent2 = new KeyEvent(1, 87);
        keyEvent2.setSource(2023);
        audioManager.dispatchMediaKeyEvent(keyEvent);
        audioManager.dispatchMediaKeyEvent(keyEvent2);
    }

    public void d() {
    }

    public void e(int i3) {
        BydLogHelper.logE("MusicFunction", "preMusic click =" + i3);
        if (a() == 1) {
            BydLogHelper.logD("MusicFunction", "getRearviewMirrorIsState =" + a());
            return;
        }
        int i4 = this.f2804c;
        if (i4 == 7 && i3 == 2) {
            b.c cVar = new b.c();
            cVar.c("foreground", o1.b.f4450a);
            o1.b.c(this.f2803b, 11, 27, cVar, null);
            return;
        }
        if (i4 == 18 && i3 == 2) {
            b.c cVar2 = new b.c();
            cVar2.c("foreground", o1.b.f4450a);
            o1.b.c(this.f2803b, 12, 27, cVar2, null);
            return;
        }
        AudioManager audioManager = (AudioManager) this.f2803b.getSystemService("audio");
        BydLogHelper.logE("MusicFunction", "preMusic am.isMusicActive() =" + audioManager.isMusicActive());
        KeyEvent keyEvent = new KeyEvent(0, 88);
        KeyEvent keyEvent2 = new KeyEvent(1, 88);
        keyEvent2.setSource(2023);
        audioManager.dispatchMediaKeyEvent(keyEvent);
        audioManager.dispatchMediaKeyEvent(keyEvent2);
    }
}

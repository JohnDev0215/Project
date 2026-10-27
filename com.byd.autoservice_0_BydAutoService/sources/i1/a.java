package i1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: AutoAudioManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AudioFocusRequest f3272a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f3273b = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public MediaPlayer f3275d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f3276e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public AudioManager f3277f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public BYDAutoAudioDevice f3280i;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f3274c = "AutoAudioManager";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3278g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3279h = 1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f3281j = "action.byd.sysui.volume.dismiss";

    /* JADX INFO: renamed from: i1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AutoAudioManager.java */
    public class C0020a implements AudioManager.OnAudioFocusChangeListener {
        public C0020a() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i3) {
            BydLogHelper.logD("AutoAudioManager", "action =" + i3);
        }
    }

    public a(Context context) {
        this.f3280i = null;
        this.f3276e = context;
        this.f3277f = (AudioManager) context.getSystemService("audio");
        this.f3280i = BYDAutoAudioDevice.getInstance(this.f3276e);
    }

    @SuppressLint({"NewApi"})
    public final void a(AudioFocusRequest audioFocusRequest, AudioManager audioManager) {
        if (audioFocusRequest == null) {
            return;
        }
        BydLogHelper.logD("AutoAudioManager", "abandonAudioFocus");
        audioManager.abandonAudioFocusRequest(audioFocusRequest);
    }

    public boolean b() {
        return f3273b;
    }

    public void c() {
        this.f3275d = new MediaPlayer();
        this.f3275d.setAudioAttributes(new AudioAttributes.Builder().setUsage(22).build());
        e(22, this.f3277f);
    }

    public void d() {
        a(f3272a, this.f3277f);
    }

    @SuppressLint({"NewApi"})
    public final AudioFocusRequest e(int i3, AudioManager audioManager) {
        AudioAttributes audioAttributesBuild = new AudioAttributes.Builder().setUsage(i3).build();
        AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(1).setAudioAttributes(audioAttributesBuild).setOnAudioFocusChangeListener(new C0020a()).build();
        f3272a = audioFocusRequestBuild;
        int iRequestAudioFocus = audioManager.requestAudioFocus(audioFocusRequestBuild);
        if (iRequestAudioFocus == 0) {
            BydLogHelper.logD("AutoAudioManager", "AUDIOFOCUS_REQUEST_FAILED");
        } else if (iRequestAudioFocus == 1) {
            BydLogHelper.logD("AutoAudioManager", "AUDIOFOCUS_REQUEST_GRANTED ");
        } else if (iRequestAudioFocus == 2) {
            BydLogHelper.logD("AutoAudioManager", "AUDIOFOCUS_REQUEST_DELAYED ");
        }
        return f3272a;
    }

    public final void f() {
        Intent intent = new Intent("action.byd.sysui.volume.dismiss");
        Context context = this.f3276e;
        if (context != null) {
            context.sendBroadcast(intent);
        }
    }

    public final void g(boolean z3) {
        Intent intent = new Intent("com.byd.autoservice.ACTION_POWER");
        intent.putExtra("power_mode", z3);
        Context context = this.f3276e;
        if (context != null) {
            context.sendBroadcast(intent);
        }
    }

    public void h(boolean z3) {
        if (z3) {
            this.f3280i.setAllMute(this.f3279h);
            c();
        } else {
            this.f3280i.setAllMute(this.f3278g);
            d();
        }
    }

    public void i(boolean z3) {
        BydLogHelper.logD("AutoAudioManager", "setPowerActive active =" + z3);
        f3273b = z3;
        g(z3);
        if (z3) {
            f();
        }
    }
}

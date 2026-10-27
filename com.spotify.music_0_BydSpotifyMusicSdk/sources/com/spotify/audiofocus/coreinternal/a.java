package com.spotify.audiofocus.coreinternal;

import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import com.google.protobuf.i;
import com.spotify.audiomanager.audiomanagerproxy.AudioManagerProxyUser;
import com.spotify.base.java.logging.Logger;
import com.spotify.messages.AudioManagerProxyEvent;
import com.spotify.playbacknative.AudioDriver;
import com.spotify.playbacknative.AudioFeatures;
import p.ad;
import p.cd;
import p.ci1;
import p.fd;
import p.hd;
import p.id;
import p.jd;
import p.lc;
import p.ld;
import p.md;
import p.pv0;
import p.rv;
import p.u0;
import p.v03;
import p.w1;
import p.y1;
import p.yc;
import p.z1;
import p.zc;

/* JADX INFO: loaded from: classes.dex */
public final class a implements AudioManager.OnAudioFocusChangeListener, AudioDriver.AudioDriverCallbacks, AudioDriver.AudioDriverListener, u0 {
    public final md a;
    public final Handler b;
    public final ad c;
    public fd d;
    public AudioFeatures e;
    public pv0 f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public AudioFocusController$FocusState k = AudioFocusController$FocusState.c;

    public a(Handler handler, ad adVar, md mdVar) {
        this.a = mdVar;
        this.b = handler;
        this.c = adVar;
    }

    public final void a() {
        fd fdVar = this.d;
        if (fdVar == null) {
            lc.c("Player callbacks are not set.");
        } else {
            fdVar.a.h();
        }
    }

    @Override // com.spotify.playbacknative.AudioDriver.AudioDriverCallbacks
    public final synchronized void abandonAudioFocus() {
        try {
            Logger.e("abandonAudioFocus", new Object[0]);
            this.k = AudioFocusController$FocusState.c;
            this.j = false;
            pv0 pv0Var = this.f;
            if (pv0Var != null) {
                this.a.a(pv0Var);
            }
            if (this.h) {
                ((zc) this.c).getClass();
                Integer currentAudioSession = AudioDriver.getCurrentAudioSession();
                if (currentAudioSession != null) {
                    AudioDriverAudioDuckingController$stopDucking$1.c.invoke(currentAudioSession);
                }
                this.h = false;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final int b() {
        String str;
        String str2;
        String str3;
        String str4;
        yc ycVar = new yc(Integer.valueOf(this.e.getAudioType().getAttr()), Integer.valueOf(this.e.getAudioUsage().getAttr()));
        Handler handler = this.b;
        handler.removeCallbacksAndMessages(null);
        int i = this.e.getAudioUsage() == AudioFeatures.AudioUsage.ALARM ? 2 : 1;
        Boolean bool = Boolean.TRUE;
        pv0 pv0Var = new pv0(i, bool, ycVar, this, handler, Boolean.valueOf(this.e.getAudioType() == AudioFeatures.AudioType.SPEECH), 0);
        this.f = pv0Var;
        md mdVar = this.a;
        mdVar.getClass();
        AudioManagerProxyUser audioManagerProxyUser = AudioManagerProxyUser.c;
        a aVar = this;
        Object objComputeIfAbsent = mdVar.c.computeIfAbsent(new jd(aVar), new ld(mdVar, aVar));
        v03.i(objComputeIfAbsent, "private fun getOrCreateO…        }\n        }\n    }");
        int iRequestAudioFocus = mdVar.a.requestAudioFocus(ci1.c(pv0Var, (AudioManager.OnAudioFocusChangeListener) objComputeIfAbsent));
        id idVar = mdVar.b;
        idVar.getClass();
        hd hdVarM = AudioManagerProxyEvent.m();
        hdVarM.f(audioManagerProxyUser.a());
        hdVarM.l("REQUEST_AUDIO_FOCUS");
        if (iRequestAudioFocus == 0) {
            str = "AUDIOFOCUS_REQUEST_FAILED";
        } else if (iRequestAudioFocus == 1) {
            str = "AUDIOFOCUS_REQUEST_GRANTED";
        } else if (iRequestAudioFocus != 2) {
            str = "UNKNOWN(" + iRequestAudioFocus + ')';
        } else {
            str = "AUDIOFOCUS_REQUEST_DELAYED";
        }
        hdVarM.k(str);
        if (i == 1) {
            str2 = "AUDIOFOCUS_GAIN";
        } else if (i != 2) {
            str2 = "UNKNOWN(" + i + ')';
        } else {
            str2 = "AUDIOFOCUS_GAIN_TRANSIENT";
        }
        hdVarM.j(str2);
        Integer num = ycVar.a;
        if (num != null) {
            int iIntValue = num.intValue();
            if (iIntValue == 1) {
                str4 = "CONTENT_TYPE_SPEECH";
            } else if (iIntValue != 2) {
                str4 = "UNKNOWN(" + iIntValue + ')';
            } else {
                str4 = "CONTENT_TYPE_MUSIC";
            }
            hdVarM.g(str4);
        }
        Integer num2 = ycVar.b;
        if (num2 != null) {
            int iIntValue2 = num2.intValue();
            if (iIntValue2 == 1) {
                str3 = "USAGE_MEDIA";
            } else if (iIntValue2 != 4) {
                str3 = "UNKNOWN(" + iIntValue2 + ')';
            } else {
                str3 = "USAGE_ALARM";
            }
            hdVarM.e(str3);
        }
        if (bool != null) {
            hdVarM.d(bool.booleanValue());
        }
        i iVarBuild = hdVarM.build();
        idVar.a.a(iVarBuild);
        return iRequestAudioFocus;
    }

    @Override // com.spotify.playbacknative.AudioDriver.AudioDriverCallbacks
    public final synchronized boolean maybeAbandonAudioFocus() {
        if (this.k != AudioFocusController$FocusState.e || this.g) {
            return true;
        }
        abandonAudioFocus();
        return false;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public final synchronized void onAudioFocusChange(int i) {
        String str;
        try {
            Object[] objArr = new Object[2];
            if (i == -3) {
                str = "LOSS_TRANSIENT_CAN_DUCK";
            } else if (i == -2) {
                str = "LOSS_TRANSIENT";
            } else if (i == -1) {
                str = "LOSS";
            } else if (i != 1) {
                str = "UNKNOWN(" + i + ')';
            } else {
                str = "GAIN";
            }
            objArr[0] = str;
            objArr[1] = this.k;
            Logger.e("onAudioFocusChange focusChange: %s, previous state: %s", objArr);
            if (i != -3) {
                if (i == -2) {
                    this.k = AudioFocusController$FocusState.k;
                    if (this.i) {
                        this.j = true;
                    }
                    a();
                } else if (i == -1) {
                    a();
                    abandonAudioFocus();
                } else if (i == 1) {
                    this.k = AudioFocusController$FocusState.e;
                    if (this.h) {
                        ((zc) this.c).getClass();
                        Integer currentAudioSession = AudioDriver.getCurrentAudioSession();
                        if (currentAudioSession != null) {
                            AudioDriverAudioDuckingController$stopDucking$1.c.invoke(currentAudioSession);
                        }
                        this.h = false;
                    }
                    if (this.j) {
                        this.j = false;
                        fd fdVar = this.d;
                        if (fdVar == null) {
                            lc.c("Player callbacks are not set.");
                        } else {
                            this.e.getAudioUsage();
                            AudioFeatures.AudioUsage audioUsage = AudioFeatures.AudioUsage.MEDIA;
                            z1 z1Var = (z1) fdVar.a;
                            z1Var.l().subscribe(y1.c, w1.n, z1Var.g);
                        }
                    }
                }
            } else if (this.e.getAudioType() == AudioFeatures.AudioType.SPEECH) {
                this.k = AudioFocusController$FocusState.k;
                if (this.i) {
                    this.j = true;
                }
                a();
            } else {
                zc zcVar = (zc) this.c;
                AudioDriverAudioDuckingController$startDucking$1 audioDriverAudioDuckingController$startDucking$1 = AudioDriverAudioDuckingController$startDucking$1.c;
                zcVar.getClass();
                Integer currentAudioSession2 = AudioDriver.getCurrentAudioSession();
                if (currentAudioSession2 != null) {
                    audioDriverAudioDuckingController$startDucking$1.invoke(currentAudioSession2);
                }
                this.h = true;
            }
        } finally {
        }
    }

    @Override // com.spotify.playbacknative.AudioDriver.AudioDriverListener
    public final void onAudioTrackCreated(int i) {
    }

    @Override // com.spotify.playbacknative.AudioDriver.AudioDriverListener
    public final void onAudioTrackDestroyed(int i) {
    }

    @Override // com.spotify.playbacknative.AudioDriver.AudioDriverListener
    public final void onFlushComplete(int i) {
    }

    @Override // com.spotify.playbacknative.AudioDriver.AudioDriverListener
    public final void onFlushStart(int i) {
    }

    @Override // com.spotify.playbacknative.AudioDriver.AudioDriverListener
    public final synchronized void onPaused(boolean z, Integer num) {
        this.i = !z;
    }

    @Override // com.spotify.playbacknative.AudioDriver.AudioDriverCallbacks
    public final synchronized boolean requestAudioFocus(AudioFeatures audioFeatures) {
        String str;
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(Build.VERSION.SDK_INT);
        objArr[1] = this.k;
        int i = cd.b[audioFeatures.getAudioUsage().ordinal()];
        objArr[2] = i != 1 ? i != 2 ? "UNKNOWN" : "MEDIA" : "ALARM";
        int i2 = cd.a[audioFeatures.getAudioType().ordinal()];
        objArr[3] = i2 != 1 ? i2 != 2 ? "UNKNOWN" : "MUSIC" : "SPEECH";
        Logger.e("requestAudioFocus SDK: %s, focusState: %s, audioStreamType: %s, audioContentType: %s", objArr);
        AudioFocusController$FocusState audioFocusController$FocusState = this.k;
        AudioFocusController$FocusState audioFocusController$FocusState2 = AudioFocusController$FocusState.e;
        if (audioFocusController$FocusState == audioFocusController$FocusState2) {
            if (this.e.getAudioUsage() == audioFeatures.getAudioUsage() && this.e.getAudioType() == audioFeatures.getAudioType()) {
                return true;
            }
            abandonAudioFocus();
        }
        this.e = audioFeatures;
        this.j = false;
        int iB = b();
        Object[] objArr2 = new Object[1];
        if (iB == 0) {
            str = "FAILED";
        } else if (iB == 1) {
            str = "GRANTED";
        } else if (iB != 2) {
            str = "UNKNOWN(" + iB + ')';
        } else {
            str = "DELAYED";
        }
        objArr2[0] = str;
        Logger.e("requestAudioFocus result: %s", objArr2);
        int i3 = 17;
        if (iB == 0) {
            this.k = AudioFocusController$FocusState.c;
            this.b.post(new rv(i3, this));
            return false;
        }
        if (iB == 1) {
            this.k = audioFocusController$FocusState2;
            return true;
        }
        if (iB != 2) {
            return false;
        }
        this.j = true;
        this.k = AudioFocusController$FocusState.c;
        this.b.post(new rv(i3, this));
        return false;
    }
}

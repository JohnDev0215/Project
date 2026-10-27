package n2;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Handler;
import c5.InterfaceC1031h;
import e2.C1195c;
import h2.AbstractC1343A;

/* JADX INFO: renamed from: n2.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1904d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC1031h f19629a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1903c f19630b;
    public SurfaceHolderCallbackC1920u c;
    public C1195c d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f19632f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public AudioFocusRequest f19634h;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f19633g = 1.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f19631e = 0;

    public C1904d(Context context, Handler handler, SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u) {
        this.f19629a = com.bumptech.glide.d.H(new C1902b(context, 0));
        this.c = surfaceHolderCallbackC1920u;
        this.f19630b = new C1903c(this, handler);
    }

    public final void a() {
        int i7 = this.f19631e;
        if (i7 == 1 || i7 == 0) {
            return;
        }
        int i10 = AbstractC1343A.f15195a;
        InterfaceC1031h interfaceC1031h = this.f19629a;
        if (i10 < 26) {
            ((AudioManager) interfaceC1031h.get()).abandonAudioFocus(this.f19630b);
        } else if (this.f19634h != null) {
            ((AudioManager) interfaceC1031h.get()).abandonAudioFocusRequest(this.f19634h);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(e2.C1195c r7) {
        /*
            r6 = this;
            e2.c r0 = r6.d
            int r1 = h2.AbstractC1343A.f15195a
            boolean r0 = java.util.Objects.equals(r0, r7)
            if (r0 != 0) goto L3d
            r6.d = r7
            r0 = 0
            r1 = 1
            if (r7 != 0) goto L12
        L10:
            r2 = r0
            goto L31
        L12:
            r2 = 3
            r3 = 2
            java.lang.String r4 = "AudioFocusManager"
            int r5 = r7.c
            switch(r5) {
                case 0: goto L2b;
                case 1: goto L29;
                case 2: goto L27;
                case 3: goto L10;
                case 4: goto L27;
                case 5: goto L31;
                case 6: goto L31;
                case 7: goto L31;
                case 8: goto L31;
                case 9: goto L31;
                case 10: goto L31;
                case 11: goto L23;
                case 12: goto L31;
                case 13: goto L31;
                case 14: goto L29;
                case 15: goto L1b;
                case 16: goto L21;
                default: goto L1b;
            }
        L1b:
            java.lang.String r7 = "Unidentified audio usage: "
            h2.AbstractC1347d.p(r7, r5, r4)
            goto L10
        L21:
            r2 = 4
            goto L31
        L23:
            int r7 = r7.f14051a
            if (r7 != r1) goto L31
        L27:
            r2 = r3
            goto L31
        L29:
            r2 = r1
            goto L31
        L2b:
            java.lang.String r7 = "Specify a proper usage in the audio attributes for audio focus handling. Using AUDIOFOCUS_GAIN by default."
            h2.AbstractC1346c.z(r4, r7)
            goto L29
        L31:
            r6.f19632f = r2
            if (r2 == r1) goto L37
            if (r2 != 0) goto L38
        L37:
            r0 = r1
        L38:
            java.lang.String r6 = "Automatic handling of audio focus is only available for USAGE_MEDIA and USAGE_GAME."
            h2.AbstractC1346c.b(r6, r0)
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: n2.C1904d.b(e2.c):void");
    }

    public final void c(int i7) {
        if (this.f19631e == i7) {
            return;
        }
        this.f19631e = i7;
        float f10 = i7 == 4 ? 0.2f : 1.0f;
        if (this.f19633g == f10) {
            return;
        }
        this.f19633g = f10;
        SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u = this.c;
        if (surfaceHolderCallbackC1920u != null) {
            C1923x c1923x = surfaceHolderCallbackC1920u.l;
            c1923x.r1(1, 2, Float.valueOf(c1923x.f19751Z * c1923x.f19728C.f19633g));
        }
    }

    public final int d(int i7, boolean z6) {
        int iRequestAudioFocus;
        int i10 = 0;
        z = false;
        boolean z10 = false;
        if (i7 == 1 || this.f19632f != 1) {
            a();
            c(0);
            return 1;
        }
        if (!z6) {
            int i11 = this.f19631e;
            if (i11 != 1) {
                return i11 != 3 ? 1 : 0;
            }
            return -1;
        }
        if (this.f19631e == 2) {
            return 1;
        }
        int i12 = AbstractC1343A.f15195a;
        InterfaceC1031h interfaceC1031h = this.f19629a;
        C1903c c1903c = this.f19630b;
        if (i12 >= 26) {
            AudioFocusRequest audioFocusRequest = this.f19634h;
            if (audioFocusRequest == null) {
                AudioFocusRequest.Builder builder = audioFocusRequest == null ? new AudioFocusRequest.Builder(this.f19632f) : new AudioFocusRequest.Builder(this.f19634h);
                C1195c c1195c = this.d;
                if (c1195c != null && c1195c.f14051a == 1) {
                    z10 = true;
                }
                c1195c.getClass();
                this.f19634h = builder.setAudioAttributes((AudioAttributes) c1195c.b().f13998a).setWillPauseWhenDucked(z10).setOnAudioFocusChangeListener(c1903c).build();
            }
            iRequestAudioFocus = ((AudioManager) interfaceC1031h.get()).requestAudioFocus(this.f19634h);
        } else {
            AudioManager audioManager = (AudioManager) interfaceC1031h.get();
            C1195c c1195c2 = this.d;
            c1195c2.getClass();
            int i13 = c1195c2.c;
            if (i13 != 13) {
                switch (i13) {
                    case 2:
                        break;
                    case 3:
                        i10 = 8;
                        break;
                    case 4:
                        i10 = 4;
                        break;
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        i10 = 5;
                        break;
                    case 6:
                        i10 = 2;
                        break;
                    default:
                        i10 = 3;
                        break;
                }
            } else {
                i10 = 1;
            }
            iRequestAudioFocus = audioManager.requestAudioFocus(c1903c, i10, this.f19632f);
        }
        if (iRequestAudioFocus == 1) {
            c(2);
            return 1;
        }
        c(1);
        return -1;
    }
}

package r3;

import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import com.byd.displaymode.bean.SoundEffectBean;
import com.byd.displaymode.present.IStateChangeCallback;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: DisplayAudioListener.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends AbsBYDAutoAudioListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IStateChangeCallback f7793a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public SoundEffectBean f7794b;

    public b(IStateChangeCallback iStateChangeCallback, SoundEffectBean soundEffectBean) {
        this.f7793a = iStateChangeCallback;
        int factoryType = soundEffectBean != null ? soundEffectBean.getFactoryType() : 0;
        int configLevel = soundEffectBean != null ? soundEffectBean.getConfigLevel() : 0;
        BydLogHelper.logI(AbsBYDAutoAudioListener.TAG, "Initial ", "factory =" + factoryType + ", configLevel =" + configLevel);
        this.f7794b = new SoundEffectBean(factoryType, configLevel);
    }

    public final boolean a(int i8) {
        return i8 <= 0;
    }

    @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
    public void onDiracLiveModeChanged(int i8) {
        super.onDiracLiveModeChanged(i8);
        int factoryType = this.f7794b.getFactoryType();
        IStateChangeCallback iStateChangeCallback = this.f7793a;
        if (iStateChangeCallback == null || !iStateChangeCallback.a() || a(factoryType)) {
            return;
        }
        BydLogHelper.logI(AbsBYDAutoAudioListener.TAG, "onDiracLiveStateChanged", "factory =" + factoryType + ", mode =" + i8);
        if (this.f7794b.getConfigLevel() == 2 && i8 > 0) {
            if (i8 != 1) {
                this.f7793a.c(64);
            } else if (this.f7794b.getLoudness() > 0) {
                this.f7793a.b(64);
            }
        }
        this.f7794b.setEffectType(i8);
    }

    @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
    public void onDiracLiveStateChanged(int i8) {
        super.onDiracLiveStateChanged(i8);
        int factoryType = this.f7794b.getFactoryType();
        IStateChangeCallback iStateChangeCallback = this.f7793a;
        if (iStateChangeCallback == null || !iStateChangeCallback.a() || a(factoryType)) {
            return;
        }
        BydLogHelper.logI(AbsBYDAutoAudioListener.TAG, "onDiracLiveStateChanged", "factory =" + factoryType + ", state =" + i8);
        if (this.f7794b.getConfigLevel() == 1) {
            if (i8 == 1) {
                this.f7793a.c(64);
            } else if (i8 != 3 && this.f7794b.getLoudness() > 0) {
                this.f7793a.b(64);
            }
        }
        this.f7794b.setEffectType(i8);
    }

    @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
    public void onEqualizerChanged(int i8, int i9) {
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x006c  */
    @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLoudnessStateChanged(int r7) {
        /*
            r6 = this;
            super.onLoudnessStateChanged(r7)
            com.byd.displaymode.bean.SoundEffectBean r0 = r6.f7794b
            int r0 = r0.getFactoryType()
            com.byd.displaymode.present.IStateChangeCallback r1 = r6.f7793a
            if (r1 == 0) goto L7a
            boolean r1 = r1.a()
            if (r1 == 0) goto L7a
            boolean r1 = r6.a(r0)
            if (r1 == 0) goto L1a
            goto L7a
        L1a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "factory ="
            r1.append(r2)
            r1.append(r0)
            java.lang.String r2 = ", state ="
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "AbsBYDAutoAudioListener"
            java.lang.String r3 = "onLoudnessStateChanged"
            com.bydux.api.common.tools.log.BydLogHelper.logD(r2, r3, r1)
            com.byd.displaymode.bean.SoundEffectBean r1 = r6.f7794b
            int r1 = r1.getConfigLevel()
            r2 = 2
            r3 = 1
            if (r0 == r3) goto L46
            if (r0 != r2) goto L75
        L46:
            r4 = 64
            if (r7 != 0) goto L50
            com.byd.displaymode.present.IStateChangeCallback r0 = r6.f7793a
            r0.c(r4)
            goto L75
        L50:
            r5 = 0
            if (r0 != r3) goto L6e
            if (r1 <= 0) goto L6e
            com.byd.displaymode.bean.SoundEffectBean r0 = r6.f7794b
            int r0 = r0.getEffectType()
            if (r1 == r3) goto L69
            if (r1 == r2) goto L66
            r2 = 3
            if (r1 == r2) goto L63
            goto L6e
        L63:
            if (r3 == r0) goto L6c
            goto L6d
        L66:
            if (r3 == r0) goto L6c
            goto L6d
        L69:
            if (r3 == r0) goto L6c
            goto L6d
        L6c:
            r3 = r5
        L6d:
            r5 = r3
        L6e:
            if (r5 == 0) goto L75
            com.byd.displaymode.present.IStateChangeCallback r0 = r6.f7793a
            r0.b(r4)
        L75:
            com.byd.displaymode.bean.SoundEffectBean r0 = r6.f7794b
            r0.setLoudness(r7)
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: r3.b.onLoudnessStateChanged(int):void");
    }

    @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
    public void onSoundEffectChanged(int i8) {
        int factoryType = this.f7794b.getFactoryType();
        IStateChangeCallback iStateChangeCallback = this.f7793a;
        if (iStateChangeCallback == null || !iStateChangeCallback.a() || a(factoryType)) {
            return;
        }
        BydLogHelper.logI(AbsBYDAutoAudioListener.TAG, "onSoundEffectChanged", "factory =" + factoryType + ", effect =" + i8);
        if (factoryType == 2) {
            if (i8 != 19) {
                this.f7793a.c(64);
            } else if (this.f7794b.getDynPosition() == 1) {
                this.f7793a.b(64);
            }
            this.f7794b.setDynaSensual(i8);
        }
    }

    @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
    public void onSoundFieldPositionChanged(int i8) {
        int factoryType = this.f7794b.getFactoryType();
        IStateChangeCallback iStateChangeCallback = this.f7793a;
        if (iStateChangeCallback == null || !iStateChangeCallback.a() || a(factoryType)) {
            return;
        }
        BydLogHelper.logI(AbsBYDAutoAudioListener.TAG, "onDiracPerformanceMode", "factory =" + factoryType + ", pos =" + i8);
        if (factoryType == 2) {
            if (i8 != 1) {
                this.f7793a.c(64);
            } else if (this.f7794b.getLoudness() > 0) {
                this.f7793a.b(64);
            }
        } else if (factoryType == 3) {
            if (i8 != 17) {
                this.f7793a.c(64);
            } else {
                this.f7793a.b(64);
            }
        }
        this.f7794b.setDynPosition(i8);
    }
}

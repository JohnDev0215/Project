package c.a.d.t;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.byd.trafficmonitor.view.WaveView;
import java.util.ArrayList;

/* JADX INFO: compiled from: WaveHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WaveView f3691a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public AnimatorSet f3692b;

    public a(WaveView waveView) {
        this.f3691a = waveView;
        b();
    }

    public void a() {
        AnimatorSet animatorSet = this.f3692b;
        if (animatorSet != null) {
            animatorSet.end();
        }
    }

    public final void b() {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.f3691a, "waveShiftRatio", 0.0f, 1.0f);
        objectAnimatorOfFloat.setRepeatCount(-1);
        objectAnimatorOfFloat.setDuration(2000L);
        objectAnimatorOfFloat.setInterpolator(new LinearInterpolator());
        arrayList.add(objectAnimatorOfFloat);
        WaveView waveView = this.f3691a;
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(waveView, "waterLevelRatio", 0.0f, waveView.getWaterLevelRatio());
        objectAnimatorOfFloat2.setDuration(4000L);
        objectAnimatorOfFloat2.setInterpolator(new DecelerateInterpolator());
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.f3691a, "amplitudeRatio", 1.0E-4f, 0.05f);
        objectAnimatorOfFloat3.setRepeatCount(1);
        objectAnimatorOfFloat3.setRepeatMode(2);
        objectAnimatorOfFloat3.setDuration(2000L);
        objectAnimatorOfFloat3.setInterpolator(new LinearInterpolator());
        arrayList.add(objectAnimatorOfFloat3);
        AnimatorSet animatorSet = new AnimatorSet();
        this.f3692b = animatorSet;
        animatorSet.playTogether(arrayList);
    }

    public void c() {
        this.f3691a.setShowWave(true);
        AnimatorSet animatorSet = this.f3692b;
        if (animatorSet != null) {
            animatorSet.start();
        }
    }
}

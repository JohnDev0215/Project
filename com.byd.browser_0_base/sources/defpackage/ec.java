package defpackage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Property;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.appcompat.widget.AppCompatImageView;
import java.util.ArrayList;
import org.chromium.chrome.features.tasks.TasksView;

/* JADX INFO: compiled from: BackgroundTabAnimation.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ec {
    public static ObjectAnimator a(View view, Property property, float f, float f2, long j) {
        return b(view, property, f, f2, j, null);
    }

    public static ObjectAnimator b(View view, Property property, float f, float f2, long j, Interpolator interpolator) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, f, f2).setDuration(j);
        if (interpolator != null) {
            duration.setInterpolator(interpolator);
        }
        return duration;
    }

    public static AnimatorSet c(y51 y51Var, TasksView tasksView, float f, float f2, boolean z) {
        float f3;
        float f4;
        Context context = y51Var.i;
        float fMin = (Math.min(y51Var.e, y51Var.f) * 0.100000024f) / 2.0f;
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(a(tasksView, View.SCALE_X, 1.0f, 0.9f, 300L));
        arrayList.add(a(tasksView, View.SCALE_Y, 1.0f, 0.9f, 300L));
        arrayList.add(a(tasksView, View.X, 0.0f, fMin, 300L));
        arrayList.add(a(tasksView, View.Y, 0.0f, fMin, 300L));
        arrayList.add(a(tasksView, View.Z, 0.0f, 12.0f, 300L));
        AnimatorSet animatorSet = new AnimatorSet();
        jk0 jk0Var = h21.e;
        animatorSet.setInterpolator(jk0Var);
        animatorSet.playTogether(arrayList);
        if (z) {
            f4 = y51Var.f * 0.5f;
            f3 = fMin;
        } else {
            f3 = y51Var.e * 0.5f;
            f4 = fMin;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setSize(tasksView.getWidth(), tasksView.getHeight());
        gradientDrawable.setColor(yp.b(context, false));
        AppCompatImageView appCompatImageView = new AppCompatImageView(context);
        appCompatImageView.setImageDrawable(gradientDrawable);
        tasksView.addView(appCompatImageView);
        ArrayList arrayList2 = new ArrayList(5);
        arrayList2.add(a(appCompatImageView, View.ALPHA, 0.0f, 1.0f, 150L));
        arrayList2.add(a(appCompatImageView, View.SCALE_X, 0.0f, 0.9f, 300L));
        arrayList2.add(a(appCompatImageView, View.SCALE_Y, 0.0f, 0.9f, 300L));
        arrayList2.add(a(appCompatImageView, View.X, f, f3, 300L));
        arrayList2.add(a(appCompatImageView, View.Y, f2, f4, 300L));
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setInterpolator(h21.g);
        animatorSet2.playTogether(arrayList2);
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playTogether(animatorSet2, animatorSet);
        ArrayList arrayList3 = new ArrayList(7);
        arrayList3.add(b(tasksView, View.SCALE_X, 0.9f, 1.0f, 300L, jk0Var));
        arrayList3.add(b(tasksView, View.SCALE_Y, 0.9f, 1.0f, 300L, jk0Var));
        arrayList3.add(b(tasksView, View.X, fMin, 0.0f, 300L, jk0Var));
        arrayList3.add(b(tasksView, View.Y, fMin, 0.0f, 300L, jk0Var));
        arrayList3.add(b(tasksView, View.Z, 12.0f, 0.0f, 300L, jk0Var));
        arrayList3.add(a(appCompatImageView, View.ALPHA, 1.0f, 0.0f, 300L));
        if (z) {
            arrayList3.add(b(appCompatImageView, View.Y, f4, -tasksView.getHeight(), 300L, h21.d));
        } else {
            arrayList3.add(b(appCompatImageView, View.X, f3, tasksView.getWidth(), 300L, h21.d));
        }
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.setStartDelay(150L);
        animatorSet4.playTogether(arrayList3);
        AnimatorSet animatorSet5 = new AnimatorSet();
        animatorSet5.playSequentially(animatorSet3, animatorSet4);
        animatorSet5.addListener(new dc(tasksView, appCompatImageView));
        return animatorSet5;
    }
}

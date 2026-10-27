package androidx.vectordrawable.a.a;

import android.animation.TypeEvaluator;
import androidx.annotation.RestrictTo;
import com.byd.mediacenter.api.media.MediaControl;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class e implements TypeEvaluator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final e f1242a = new e();

    public static e a() {
        return f1242a;
    }

    @Override // android.animation.TypeEvaluator
    public Object evaluate(float f, Object obj, Object obj2) {
        int iIntValue = ((Integer) obj).intValue();
        float f2 = ((iIntValue >> 24) & MediaControl.ACTION_NONE) / 255.0f;
        float f3 = ((iIntValue >> 16) & MediaControl.ACTION_NONE) / 255.0f;
        float f4 = ((iIntValue >> 8) & MediaControl.ACTION_NONE) / 255.0f;
        int iIntValue2 = ((Integer) obj2).intValue();
        float f5 = ((iIntValue2 >> 24) & MediaControl.ACTION_NONE) / 255.0f;
        float f6 = ((iIntValue2 >> 16) & MediaControl.ACTION_NONE) / 255.0f;
        float f7 = ((iIntValue2 >> 8) & MediaControl.ACTION_NONE) / 255.0f;
        float fPow = (float) Math.pow(f3, 2.2d);
        float fPow2 = (float) Math.pow(f4, 2.2d);
        float fPow3 = (float) Math.pow((iIntValue & MediaControl.ACTION_NONE) / 255.0f, 2.2d);
        float fPow4 = (float) Math.pow(f6, 2.2d);
        float f8 = f2 + ((f5 - f2) * f);
        float fPow5 = fPow2 + ((((float) Math.pow(f7, 2.2d)) - fPow2) * f);
        float fPow6 = fPow3 + (f * (((float) Math.pow((iIntValue2 & MediaControl.ACTION_NONE) / 255.0f, 2.2d)) - fPow3));
        float fPow7 = ((float) Math.pow(fPow + ((fPow4 - fPow) * f), 0.45454545454545453d)) * 255.0f;
        float fPow8 = ((float) Math.pow(fPow5, 0.45454545454545453d)) * 255.0f;
        return Integer.valueOf(Math.round(((float) Math.pow(fPow6, 0.45454545454545453d)) * 255.0f) | (Math.round(fPow7) << 16) | (Math.round(f8 * 255.0f) << 24) | (Math.round(fPow8) << 8));
    }
}

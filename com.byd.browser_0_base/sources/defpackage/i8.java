package defpackage;

import android.animation.TypeEvaluator;

/* JADX INFO: compiled from: ArgbEvaluatorCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class i8 implements TypeEvaluator<Integer> {
    public static final i8 a = new i8();

    public static Integer a(float f, Integer num, Integer num2) {
        int iIntValue = num.intValue();
        float f2 = ((iIntValue >> 24) & 255) / 255.0f;
        int iIntValue2 = num2.intValue();
        float f3 = ((iIntValue2 >> 24) & 255) / 255.0f;
        float fPow = (float) Math.pow(((iIntValue >> 16) & 255) / 255.0f, 2.2d);
        float fPow2 = (float) Math.pow(((iIntValue >> 8) & 255) / 255.0f, 2.2d);
        float fPow3 = (float) Math.pow((iIntValue & 255) / 255.0f, 2.2d);
        float fPow4 = (float) Math.pow(((iIntValue2 >> 16) & 255) / 255.0f, 2.2d);
        float fPow5 = (float) Math.pow(((iIntValue2 >> 8) & 255) / 255.0f, 2.2d);
        float fPow6 = (float) Math.pow((iIntValue2 & 255) / 255.0f, 2.2d);
        float f4 = C0173a.f(f3, f2, f, f2);
        float f5 = C0173a.f(fPow4, fPow, f, fPow);
        float f6 = C0173a.f(fPow5, fPow2, f, fPow2);
        float f7 = C0173a.f(fPow6, fPow3, f, fPow3);
        float fPow7 = ((float) Math.pow(f5, 0.45454545454545453d)) * 255.0f;
        float fPow8 = ((float) Math.pow(f6, 0.45454545454545453d)) * 255.0f;
        return Integer.valueOf(Math.round(((float) Math.pow(f7, 0.45454545454545453d)) * 255.0f) | (Math.round(fPow7) << 16) | (Math.round(f4 * 255.0f) << 24) | (Math.round(fPow8) << 8));
    }

    @Override // android.animation.TypeEvaluator
    public final /* bridge */ /* synthetic */ Integer evaluate(float f, Integer num, Integer num2) {
        return a(f, num, num2);
    }
}

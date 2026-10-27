package i0;

import A7.m;
import F0.AbstractC0207i;
import F0.Q0;
import L0.i;
import L0.o;
import L0.r;
import N0.C0411f;
import android.os.Build;
import android.os.Looper;
import android.util.LongSparseArray;
import android.view.translation.TranslationRequestValue;
import android.view.translation.TranslationResponseValue;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;
import java.util.List;
import java.util.function.Consumer;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: i0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1398b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C1398b f15648a = new C1398b();

    public static void a(ViewOnAttachStateChangeListenerC1400d viewOnAttachStateChangeListenerC1400d, LongSparseArray longSparseArray) {
        TranslationResponseValue value;
        CharSequence text;
        Q0 q02;
        o oVar;
        InterfaceC2782k interfaceC2782k;
        int i7 = 0;
        while (i7 < longSparseArray.size()) {
            int i10 = i7 + 1;
            long jKeyAt = longSparseArray.keyAt(i7);
            ViewTranslationResponse viewTranslationResponseM = AbstractC1397a.m(longSparseArray.get(jKeyAt));
            if (viewTranslationResponseM != null && (value = viewTranslationResponseM.getValue("android:text")) != null && (text = value.getText()) != null && (q02 = (Q0) viewOnAttachStateChangeListenerC1400d.c().f((int) jKeyAt)) != null && (oVar = q02.f2107a) != null) {
                Object obj = oVar.d.l.get(i.f3894j);
                if (obj == null) {
                    obj = null;
                }
                L0.a aVar = (L0.a) obj;
                if (aVar != null && (interfaceC2782k = (InterfaceC2782k) aVar.f3876b) != null) {
                }
            }
            i7 = i10;
        }
    }

    public final void b(ViewOnAttachStateChangeListenerC1400d viewOnAttachStateChangeListenerC1400d, long[] jArr, int[] iArr, Consumer<ViewTranslationRequest> consumer) {
        o oVar;
        String strL;
        for (long j3 : jArr) {
            Q0 q02 = (Q0) viewOnAttachStateChangeListenerC1400d.c().f((int) j3);
            if (q02 != null && (oVar = q02.f2107a) != null) {
                AbstractC0207i.o();
                ViewTranslationRequest.Builder builderN = AbstractC0207i.n(viewOnAttachStateChangeListenerC1400d.l.getAutofillId(), oVar.f3920g);
                Object obj = oVar.d.l.get(r.f3957u);
                if (obj == null) {
                    obj = null;
                }
                List list = (List) obj;
                if (list != null && (strL = w0.c.L(list, "\n", null, 62)) != null) {
                    builderN.setValue("android:text", TranslationRequestValue.forText(new C0411f(strL, null, 6)));
                    consumer.accept(builderN.build());
                }
            }
        }
    }

    public final void c(ViewOnAttachStateChangeListenerC1400d viewOnAttachStateChangeListenerC1400d, LongSparseArray<ViewTranslationResponse> longSparseArray) {
        if (Build.VERSION.SDK_INT < 31) {
            return;
        }
        if (m.a(Looper.getMainLooper().getThread(), Thread.currentThread())) {
            a(viewOnAttachStateChangeListenerC1400d, longSparseArray);
        } else {
            viewOnAttachStateChangeListenerC1400d.l.post(new A2.m(viewOnAttachStateChangeListenerC1400d, 6, longSparseArray));
        }
    }
}

package spi;

import android.text.PrecomputedText;
import android.widget.TextView;
import androidx.core.view.OneShotPreDrawListener;
import com.android.car.ui.widget.CarUiTextViewImpl;
import java.lang.ref.WeakReference;
import java.util.Objects;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class w1 implements Runnable {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ CarUiTextViewImpl b;
    public final /* synthetic */ WeakReference c;
    public final /* synthetic */ CharSequence d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Object f;

    public /* synthetic */ w1(CarUiTextViewImpl carUiTextViewImpl, WeakReference weakReference, PrecomputedText precomputedText, CharSequence charSequence, boolean z) {
        this.b = carUiTextViewImpl;
        this.c = weakReference;
        this.f = precomputedText;
        this.d = charSequence;
        this.e = z;
    }

    public /* synthetic */ w1(CarUiTextViewImpl carUiTextViewImpl, WeakReference weakReference, CharSequence charSequence, PrecomputedText.Params params, boolean z) {
        this.b = carUiTextViewImpl;
        this.c = weakReference;
        this.d = charSequence;
        this.f = params;
        this.e = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                CarUiTextViewImpl carUiTextViewImpl = this.b;
                WeakReference weakReference = this.c;
                CharSequence charSequence = this.d;
                PrecomputedText.Params params = (PrecomputedText.Params) this.f;
                boolean z = this.e;
                int i = CarUiTextViewImpl.d;
                Objects.requireNonNull(carUiTextViewImpl);
                TextView textView = (TextView) weakReference.get();
                if (textView != null) {
                    textView.post(new w1(carUiTextViewImpl, weakReference, PrecomputedText.create(charSequence, params), charSequence, z));
                    break;
                }
                break;
            default:
                CarUiTextViewImpl carUiTextViewImpl2 = this.b;
                WeakReference weakReference2 = this.c;
                PrecomputedText precomputedText = (PrecomputedText) this.f;
                CharSequence charSequence2 = this.d;
                boolean z2 = this.e;
                int i2 = CarUiTextViewImpl.d;
                Objects.requireNonNull(carUiTextViewImpl2);
                TextView textView2 = (TextView) weakReference2.get();
                if (textView2 != null) {
                    try {
                        textView2.setTextMetricsParams(precomputedText.getParams());
                        textView2.setText(precomputedText);
                    } catch (IllegalArgumentException unused) {
                        textView2.setText(charSequence2);
                    }
                    if (z2 && carUiTextViewImpl2.c == null) {
                        carUiTextViewImpl2.c = OneShotPreDrawListener.add(carUiTextViewImpl2, new v1(carUiTextViewImpl2, 0));
                        break;
                    }
                    break;
                }
                break;
        }
    }
}

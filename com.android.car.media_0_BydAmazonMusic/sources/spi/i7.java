package spi;

import android.text.TextUtils;
import android.widget.TextView;
import androidx.core.util.Pair;
import androidx.lifecycle.Observer;
import com.android.car.media.common.MediaItemMetadata;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class i7 implements Observer {
    public final /* synthetic */ int a;
    public final /* synthetic */ TextView b;

    public /* synthetic */ i7(TextView textView, int i) {
        this.a = i;
        this.b = textView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.lifecycle.Observer
    public final void onChanged(Object obj) {
        switch (this.a) {
            case 0:
                uc.e(this.b, ((Boolean) obj).booleanValue());
                break;
            case 1:
                TextView textView = this.b;
                Pair pair = (Pair) obj;
                F f = pair.first;
                String str = f == 0 ? null : ((MediaItemMetadata) f).e;
                if (!textView.getText().equals(str)) {
                    textView.setText(str);
                }
                if (textView.getVisibility() != 0) {
                    uc.e(textView, true);
                }
                boolean z = !TextUtils.isEmpty(str);
                boolean zD = ((u8) pair.second).d();
                if (z) {
                    uc.e(textView, true);
                } else if (!zD) {
                    uc.e(textView, false);
                } else {
                    textView.setVisibility(4);
                }
                break;
            case 2:
                this.b.setText((CharSequence) obj);
                break;
            case 3:
                this.b.setText((CharSequence) obj);
                break;
            default:
                this.b.setText((CharSequence) obj);
                break;
        }
    }
}

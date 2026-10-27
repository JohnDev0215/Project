package m3;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.stingray.drivemodedetector.R;
import e2.W;
import s3.AbstractC2405y;
import s3.C2368G;
import s3.U;

/* JADX INFO: renamed from: m3.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1814n extends AbstractC2405y {
    public final String[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String[] f19068e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Drawable[] f19069f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ s f19070g;

    public C1814n(s sVar, String[] strArr, Drawable[] drawableArr) {
        this.f19070g = sVar;
        this.d = strArr;
        this.f19068e = new String[strArr.length];
        this.f19069f = drawableArr;
    }

    @Override // s3.AbstractC2405y
    public final int a() {
        return this.d.length;
    }

    @Override // s3.AbstractC2405y
    public final long b(int i7) {
        return i7;
    }

    @Override // s3.AbstractC2405y
    public final void c(U u4, int i7) {
        C1813m c1813m = (C1813m) u4;
        boolean zE = e(i7);
        View view = c1813m.f22219a;
        if (zE) {
            view.setLayoutParams(new C2368G(-1, -2));
        } else {
            view.setLayoutParams(new C2368G(0, 0));
        }
        c1813m.f19064u.setText(this.d[i7]);
        String str = this.f19068e[i7];
        TextView textView = c1813m.f19065v;
        if (str == null) {
            textView.setVisibility(8);
        } else {
            textView.setText(str);
        }
        Drawable drawable = this.f19069f[i7];
        ImageView imageView = c1813m.f19066w;
        if (drawable == null) {
            imageView.setVisibility(8);
        } else {
            imageView.setImageDrawable(drawable);
        }
    }

    @Override // s3.AbstractC2405y
    public final U d(ViewGroup viewGroup) {
        s sVar = this.f19070g;
        return new C1813m(sVar, LayoutInflater.from(sVar.getContext()).inflate(R.layout.exo_styled_settings_list_item, viewGroup, false));
    }

    public final boolean e(int i7) {
        s sVar = this.f19070g;
        W w10 = sVar.f19137u0;
        if (w10 == null) {
            return false;
        }
        if (i7 == 0) {
            return w10.R0(13);
        }
        if (i7 != 1) {
            return true;
        }
        return w10.R0(30) && sVar.f19137u0.R0(29);
    }
}

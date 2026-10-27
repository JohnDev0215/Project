package d4;

import android.support.v4.media.MediaBrowserCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import com.vivid.spotify.common.ui.DoubleGridViewModel;
import e4.a;
import java.util.List;

/* JADX INFO: compiled from: RvDoubleGridItemBindingW840dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class c5 extends y4 implements a.InterfaceC0071a {
    private static final ViewDataBinding.i S = null;
    private static final SparseIntArray T;
    private final ConstraintLayout L;
    private final TextView M;
    private final TextView N;
    private final ImageView O;
    private final View.OnClickListener P;
    private final View.OnClickListener Q;
    private long R;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        T = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.title, 6);
        sparseIntArray.put(com.vivid.spotify.t.subtitle, 7);
    }

    public c5(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 8, S, T));
    }

    private boolean g0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.R |= 4;
        }
        return true;
    }

    private boolean h0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.R |= 16;
        }
        return true;
    }

    private boolean i0(LiveData<List<MediaBrowserCompat.MediaItem>> liveData, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.R |= 32;
        }
        return true;
    }

    private boolean j0(LiveData<Integer> liveData, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.R |= 1;
        }
        return true;
    }

    private boolean k0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.R |= 2;
        }
        return true;
    }

    private boolean l0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.R |= 8;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.R != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.R = 128L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 == 0) {
            return j0((LiveData) obj, i8);
        }
        if (i7 == 1) {
            return k0((androidx.databinding.l) obj, i8);
        }
        if (i7 == 2) {
            return g0((ObservableBoolean) obj, i8);
        }
        if (i7 == 3) {
            return l0((androidx.databinding.l) obj, i8);
        }
        if (i7 == 4) {
            return h0((ObservableBoolean) obj, i8);
        }
        if (i7 != 5) {
            return false;
        }
        return i0((LiveData) obj, i8);
    }

    @Override // e4.a.InterfaceC0071a
    public final void c(int i7, View view) {
        if (i7 == 1) {
            DoubleGridViewModel doubleGridViewModel = this.K;
            if (doubleGridViewModel != null) {
                doubleGridViewModel.onItemClicked(view);
                return;
            }
            return;
        }
        if (i7 != 2) {
            return;
        }
        DoubleGridViewModel doubleGridViewModel2 = this.K;
        if (doubleGridViewModel2 != null) {
            doubleGridViewModel2.onPlayButtonClicked();
        }
    }

    @Override // d4.y4
    public void f0(DoubleGridViewModel doubleGridViewModel) {
        this.K = doubleGridViewModel;
        synchronized (this) {
            this.R |= 64;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f8  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 372
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.c5.x():void");
    }

    private c5(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 6, (LinearLayout) objArr[4], (FrameLayout) objArr[7], (FrameLayout) objArr[6], (TextView) objArr[1]);
        this.R = -1L;
        this.G.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.L = constraintLayout;
        constraintLayout.setTag(null);
        TextView textView = (TextView) objArr[2];
        this.M = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) objArr[3];
        this.N = textView2;
        textView2.setTag(null);
        ImageView imageView = (ImageView) objArr[5];
        this.O = imageView;
        imageView.setTag(null);
        this.J.setTag(null);
        X(view);
        this.P = new e4.a(this, 1);
        this.Q = new e4.a(this, 2);
        K();
    }
}

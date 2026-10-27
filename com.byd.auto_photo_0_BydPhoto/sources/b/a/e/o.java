package b.a.e;

import android.content.Context;
import androidx.core.view.ViewCompat;
import b.a.e.m;
import b.a.e.y;
import com.byd.view.XImageView;

/* JADX INFO: loaded from: classes.dex */
public class o extends m {
    private XImageView n;

    public o(Context context, int i) {
        super(context, i);
    }

    public XImageView D() {
        return this.n;
    }

    public boolean E() {
        XImageView xImageView = this.n;
        if (xImageView != null) {
            return xImageView.isMatrixChanged();
        }
        return false;
    }

    @Override // b.a.e.m, b.a.e.y
    protected void d(int i, y.a aVar) {
        if (i != 0 && i != 2) {
            super.d(i, aVar);
        } else {
            aVar.f461a.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            ((m.f) aVar).g.setVisibility(0);
        }
    }

    @Override // b.a.e.m
    protected void u(int i, m.f fVar) {
        this.n = fVar.f429c;
    }
}

package g4;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.l;

/* JADX INFO: compiled from: ObservableControlAction.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ObservableBoolean f6549a = new ObservableBoolean(true);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ObservableBoolean f6550b = new ObservableBoolean(true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final l<Drawable> f6551c = new l<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ObservableInt f6552d = new ObservableInt();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private View.OnClickListener f6553e;

    public void a() {
        this.f6549a.r(false);
        this.f6550b.r(true);
        this.f6553e = null;
        this.f6551c.r(null);
        this.f6552d.r(0);
    }

    public ObservableBoolean b() {
        return this.f6550b;
    }

    public l<Drawable> c() {
        return this.f6551c;
    }

    public ObservableInt d() {
        return this.f6552d;
    }

    public ObservableBoolean e() {
        return this.f6549a;
    }

    public void f(View view) {
        View.OnClickListener onClickListener = this.f6553e;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public void g(a aVar) {
        this.f6549a.r(true);
        this.f6551c.r(aVar.a());
        this.f6552d.r(aVar.e());
        this.f6553e = aVar.c();
        this.f6550b.r(aVar.f());
    }
}

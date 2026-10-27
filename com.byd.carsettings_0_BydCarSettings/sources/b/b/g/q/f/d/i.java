package b.b.g.q.f.d;

import android.content.Context;
import android.widget.CompoundButton;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.sound.model.DiracMode;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SoundPager.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class i extends b.b.m.e.f<b.b.g.n.p.e> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SegmentedButtonGroup.f f1780a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f1781b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f1782c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f1783d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b.b.m.f.c.a.e f1784e;

    /* JADX INFO: compiled from: SoundPager.java */
    public interface a {
        void a(DiracMode diracMode);
    }

    /* JADX INFO: compiled from: SoundPager.java */
    public interface b {
        void a(boolean z);
    }

    /* JADX INFO: compiled from: SoundPager.java */
    public interface c {
        void a(int i, boolean z);
    }

    /* JADX INFO: compiled from: SoundPager.java */
    public class d implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<i> f1785a;

        public d(i iVar) {
            this.f1785a = new WeakReference<>(iVar);
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            WeakReference<i> weakReference = this.f1785a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1785a.get().e(compoundButton, z);
        }
    }

    public i(Context context) {
        super(context);
    }

    public void b(int i) {
        BydLogHelper.logV(this.TAG + "----checkSubindexOnResume");
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.p.e newP() {
        return new b.b.g.n.p.e();
    }

    public void e(CompoundButton compoundButton, boolean z) {
    }

    public void f(a aVar) {
        this.f1781b = aVar;
    }

    public void h(b bVar) {
        this.f1782c = bVar;
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
        BydLogHelper.logV(this.TAG + "----initData");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initViews(Context context) {
        ((b.b.g.n.p.e) mo7getP()).w();
    }

    public void k(c cVar) {
        this.f1783d = cVar;
    }

    public void setOnItemClickedListener(b.b.m.f.c.a.e eVar) {
        this.f1784e = eVar;
    }

    public void setOnPositionChangedListener(SegmentedButtonGroup.f fVar) {
        this.f1780a = fVar;
    }
}

package a.r;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static i f1339a = new a.r.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ThreadLocal<WeakReference<a.e.a<ViewGroup, ArrayList<i>>>> f1340b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static ArrayList<ViewGroup> f1341c = new ArrayList<>();

    public static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public i f1342a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ViewGroup f1343b;

        /* JADX INFO: renamed from: a.r.m$a$a, reason: collision with other inner class name */
        public class C0029a extends l {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ a.e.a f1344a;

            public C0029a(a.e.a aVar) {
                this.f1344a = aVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // a.r.i.d
            public void c(i iVar) {
                ((ArrayList) this.f1344a.get(a.this.f1343b)).remove(iVar);
                iVar.w(this);
            }
        }

        public a(i iVar, ViewGroup viewGroup) {
            this.f1342a = iVar;
            this.f1343b = viewGroup;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:139:0x02a7  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0078  */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean onPreDraw() {
            /*
                Method dump skipped, instruction units count: 681
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: a.r.m.a.onPreDraw():boolean");
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            this.f1343b.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f1343b.removeOnAttachStateChangeListener(this);
            m.f1341c.remove(this.f1343b);
            ArrayList<i> arrayList = m.a().get(this.f1343b);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<i> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().y(this.f1343b);
                }
            }
            this.f1342a.j(true);
        }
    }

    public static a.e.a<ViewGroup, ArrayList<i>> a() {
        a.e.a<ViewGroup, ArrayList<i>> aVar;
        WeakReference<a.e.a<ViewGroup, ArrayList<i>>> weakReference = f1340b.get();
        if (weakReference != null && (aVar = weakReference.get()) != null) {
            return aVar;
        }
        a.e.a<ViewGroup, ArrayList<i>> aVar2 = new a.e.a<>();
        f1340b.set(new WeakReference<>(aVar2));
        return aVar2;
    }
}

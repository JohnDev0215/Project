package d.a.a.f.c.a.c;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import d.a.a.f.c.a.a.g;
import d.a.a.f.c.a.a.l;
import d.a.a.f.c.a.a.m;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: SkinInflaterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements LayoutInflater.Factory {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f4659b = b.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<m> f4660c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f4661d = -1;

    private View c(Context context, String str, AttributeSet attributeSet) {
        View viewCreateView;
        try {
            if (str.indexOf(46) == -1) {
                View viewCreateView2 = ("View".equals(str) || "ViewStub".equals(str)) ? LayoutInflater.from(context).createView(str, "android.view.", attributeSet) : null;
                if (viewCreateView2 == null) {
                    viewCreateView2 = LayoutInflater.from(context).createView(str, "android.widget.", attributeSet);
                }
                if (viewCreateView2 != null) {
                    return viewCreateView2;
                }
                viewCreateView = LayoutInflater.from(context).createView(str, "android.webkit.", attributeSet);
            } else {
                viewCreateView = LayoutInflater.from(context).createView(str, null, attributeSet);
            }
            return viewCreateView;
        } catch (Exception unused) {
            return null;
        }
    }

    private void d(Context context, AttributeSet attributeSet, View view) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < attributeSet.getAttributeCount(); i2++) {
            String attributeName = attributeSet.getAttributeName(i2);
            String attributeValue = attributeSet.getAttributeValue(i2);
            l lVarB = g.b(attributeName);
            if (lVarB != null && attributeValue.startsWith("@")) {
                try {
                    int i3 = Integer.parseInt(attributeValue.substring(1));
                    l lVarA = g.a(lVarB, attributeName, i3, context.getResources().getResourceEntryName(i3), context.getResources().getResourceTypeName(i3));
                    if (lVarA != null) {
                        arrayList.add(lVarA);
                    }
                } catch (Resources.NotFoundException | NumberFormatException unused) {
                }
            }
        }
        if (d.a.a.f.b.c.a.a(arrayList)) {
            return;
        }
        m mVar = new m();
        mVar.f4655a = view;
        mVar.f4657c = arrayList;
        mVar.f4656b = this.f4661d;
        this.f4660c.add(mVar);
        if (c.n().t()) {
            return;
        }
        mVar.a();
    }

    public void a() {
        if (d.a.a.f.b.c.a.a(this.f4660c)) {
            return;
        }
        for (m mVar : this.f4660c) {
            if (mVar.f4655a != null) {
                mVar.a();
            }
        }
    }

    public void b() {
        if (d.a.a.f.b.c.a.a(this.f4660c)) {
            return;
        }
        for (m mVar : this.f4660c) {
            if (mVar.f4655a != null) {
                mVar.b();
            }
        }
        this.f4660c.clear();
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        if (!c.n().q()) {
            return null;
        }
        d.a.a.f.b.b.a.l(this.f4659b, "onCreateView", "skin function enable");
        View viewC = c(context, str, attributeSet);
        if (viewC == null) {
            return null;
        }
        d(context, attributeSet, viewC);
        return viewC;
    }
}

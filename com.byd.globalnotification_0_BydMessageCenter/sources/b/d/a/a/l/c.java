package b.d.a.a.l;

import android.util.Property;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class c extends Property<ViewGroup, Float> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Property<ViewGroup, Float> f1972a = new c("childrenAlpha");

    public c(String str) {
        super(Float.class, str);
    }

    @Override // android.util.Property
    public Float get(ViewGroup viewGroup) {
        Float f2 = (Float) viewGroup.getTag(b.d.a.a.f.mtrl_internal_children_alpha_tag);
        return f2 != null ? f2 : Float.valueOf(1.0f);
    }

    @Override // android.util.Property
    public void set(ViewGroup viewGroup, Float f2) {
        ViewGroup viewGroup2 = viewGroup;
        float fFloatValue = f2.floatValue();
        viewGroup2.setTag(b.d.a.a.f.mtrl_internal_children_alpha_tag, Float.valueOf(fFloatValue));
        int childCount = viewGroup2.getChildCount();
        for (int i = 0; i < childCount; i++) {
            viewGroup2.getChildAt(i).setAlpha(fFloatValue);
        }
    }
}

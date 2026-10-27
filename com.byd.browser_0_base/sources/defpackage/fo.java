package defpackage;

import android.util.Property;
import android.view.ViewGroup;
import com.google.android.material.R$id;

/* JADX INFO: compiled from: ChildrenAlphaProperty.java */
/* JADX INFO: loaded from: classes.dex */
public final class fo extends Property<ViewGroup, Float> {
    public static final fo a = new fo();

    public fo() {
        super(Float.class, "childrenAlpha");
    }

    @Override // android.util.Property
    public final Float get(ViewGroup viewGroup) {
        Float f = (Float) viewGroup.getTag(R$id.mtrl_internal_children_alpha_tag);
        return f != null ? f : Float.valueOf(1.0f);
    }

    @Override // android.util.Property
    public final void set(ViewGroup viewGroup, Float f) {
        ViewGroup viewGroup2 = viewGroup;
        float fFloatValue = f.floatValue();
        viewGroup2.setTag(R$id.mtrl_internal_children_alpha_tag, Float.valueOf(fFloatValue));
        int childCount = viewGroup2.getChildCount();
        for (int i = 0; i < childCount; i++) {
            viewGroup2.getChildAt(i).setAlpha(fFloatValue);
        }
    }
}

package org.chromium.ui.modelutil;

import android.animation.ObjectAnimator;
import android.util.FloatProperty;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PropertyModelAnimatorFactory.java */
/* JADX INFO: loaded from: classes3.dex */
public final class i {

    /* JADX INFO: compiled from: PropertyModelAnimatorFactory.java */
    public static class a extends FloatProperty<PropertyModel> {
        public final PropertyModel.o a;

        public a(PropertyModel.o oVar) {
            super(oVar.toString());
            this.a = oVar;
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((PropertyModel) obj).g(this.a));
        }

        @Override // android.util.FloatProperty
        public final void setValue(PropertyModel propertyModel, float f) {
            propertyModel.m(this.a, f);
        }
    }

    public static ObjectAnimator a(PropertyModel propertyModel, PropertyModel.o oVar, float f) {
        return ObjectAnimator.ofFloat(propertyModel, new a(oVar), f);
    }
}

package com.android.car.ui.toolbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.android.car.ui.R$styleable;
import com.android.car.ui.toolbar.b;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import spi.b2;

/* JADX INFO: compiled from: MenuItemXmlParserUtil.java */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public final class c {
    private c() {
    }

    public static b a(Context context, XmlResourceParser xmlResourceParser, AttributeSet attributeSet) throws Throwable {
        TypedArray typedArray;
        boolean z;
        boolean z2;
        boolean z3;
        b.e eVar;
        boolean z4;
        xmlResourceParser.require(2, null, "MenuItem");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CarUiToolbarMenuItem);
        try {
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CarUiToolbarMenuItem_id, -1);
            String string = typedArrayObtainStyledAttributes.getString(R$styleable.CarUiToolbarMenuItem_title);
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.CarUiToolbarMenuItem_carUiIcon);
            boolean z5 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiToolbarMenuItem_search, false);
            boolean z6 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiToolbarMenuItem_settings, false);
            boolean z7 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiToolbarMenuItem_tinted, true);
            boolean z8 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiToolbarMenuItem_visible, true);
            boolean z9 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiToolbarMenuItem_showIconAndTitle, false);
            boolean z10 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiToolbarMenuItem_checkable, false);
            int i = R$styleable.CarUiToolbarMenuItem_checked;
            boolean z11 = typedArrayObtainStyledAttributes.getBoolean(i, false);
            boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i);
            boolean z12 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiToolbarMenuItem_activatable, false);
            int i2 = R$styleable.CarUiToolbarMenuItem_activated;
            boolean z13 = typedArrayObtainStyledAttributes.getBoolean(i2, false);
            boolean zHasValue2 = typedArrayObtainStyledAttributes.hasValue(i2);
            int i3 = typedArrayObtainStyledAttributes.getInt(R$styleable.CarUiToolbarMenuItem_displayBehavior, 0);
            int i4 = typedArrayObtainStyledAttributes.getInt(R$styleable.CarUiToolbarMenuItem_uxRestrictions, 0);
            String string2 = typedArrayObtainStyledAttributes.getString(R$styleable.CarUiToolbarMenuItem_onClick);
            if (string2 != null) {
                typedArray = typedArrayObtainStyledAttributes;
                try {
                    final Activity activityE = b2.e(context);
                    if (activityE == null) {
                        throw new RuntimeException("Couldn't find an activity for the MenuItem");
                    }
                    z3 = z10;
                    try {
                        z = z5;
                        z2 = z6;
                        final Method method = activityE.getClass().getMethod(string2, b.class);
                        eVar = new b.e() { // from class: spi.g7
                            @Override // com.android.car.ui.toolbar.b.e
                            public final void b(com.android.car.ui.toolbar.b bVar) {
                                try {
                                    method.invoke(activityE, bVar);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    throw new RuntimeException("Couldn't call the MenuItem's listener", e);
                                }
                            }
                        };
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException("OnClick method " + string2 + "(MenuItem) not found in your activity", e);
                    }
                } catch (Throwable th) {
                    th = th;
                    typedArray.recycle();
                    throw th;
                }
            } else {
                typedArray = typedArrayObtainStyledAttributes;
                z = z5;
                z2 = z6;
                z3 = z10;
                eVar = null;
            }
            b.c cVar = i3 == 0 ? b.c.ALWAYS : b.c.NEVER;
            xmlResourceParser.next();
            xmlResourceParser.require(3, null, "MenuItem");
            b.C0013b c0013b = new b.C0013b(context);
            c0013b.f = resourceId;
            c0013b.g = string;
            c0013b.h = drawable;
            c0013b.i = eVar;
            c0013b.u = i4;
            c0013b.k = z7;
            c0013b.p = z8;
            c0013b.l = z9;
            c0013b.j = cVar;
            if (z) {
                c0013b.d();
            }
            if (z2) {
                c0013b.e();
            }
            if (z3 || zHasValue) {
                z4 = true;
                c0013b.n = true;
                c0013b.o = z11;
            } else {
                z4 = true;
            }
            if (z12 || zHasValue2) {
                c0013b.q = z4;
                c0013b.r = z13;
            }
            b bVarA = c0013b.a();
            typedArray.recycle();
            return bVarA;
        } catch (Throwable th2) {
            th = th2;
            typedArray = typedArrayObtainStyledAttributes;
        }
    }
}

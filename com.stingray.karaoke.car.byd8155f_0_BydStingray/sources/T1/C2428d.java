package t1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import s1.AbstractC2360a;

/* JADX INFO: renamed from: t1.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2428d extends ViewGroup.MarginLayoutParams {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbstractC2425a f22429a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f22430b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f22431e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f22432f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f22433g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f22434h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f22435i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f22436j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public View f22437k;
    public View l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f22438m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f22439n;
    public final Rect o;

    public C2428d() {
        super(-2, -2);
        this.f22430b = false;
        this.c = 0;
        this.d = 0;
        this.f22431e = -1;
        this.f22432f = -1;
        this.f22433g = 0;
        this.f22434h = 0;
        this.o = new Rect();
    }

    public final boolean a(int i7) {
        if (i7 == 0) {
            return this.f22438m;
        }
        if (i7 != 1) {
            return false;
        }
        return this.f22439n;
    }

    public C2428d(Context context, AttributeSet attributeSet) {
        AbstractC2425a abstractC2425a;
        super(context, attributeSet);
        this.f22430b = false;
        this.c = 0;
        this.d = 0;
        this.f22431e = -1;
        this.f22432f = -1;
        this.f22433g = 0;
        this.f22434h = 0;
        this.o = new Rect();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC2360a.f22066b);
        this.c = typedArrayObtainStyledAttributes.getInteger(0, 0);
        this.f22432f = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        this.d = typedArrayObtainStyledAttributes.getInteger(2, 0);
        this.f22431e = typedArrayObtainStyledAttributes.getInteger(6, -1);
        this.f22433g = typedArrayObtainStyledAttributes.getInt(5, 0);
        this.f22434h = typedArrayObtainStyledAttributes.getInt(4, 0);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(3);
        this.f22430b = zHasValue;
        if (zHasValue) {
            String string = typedArrayObtainStyledAttributes.getString(3);
            String str = CoordinatorLayout.f11715E;
            if (TextUtils.isEmpty(string)) {
                abstractC2425a = null;
            } else {
                if (string.startsWith(".")) {
                    string = context.getPackageName() + string;
                } else if (string.indexOf(46) < 0) {
                    String str2 = CoordinatorLayout.f11715E;
                    if (!TextUtils.isEmpty(str2)) {
                        string = str2 + '.' + string;
                    }
                }
                try {
                    ThreadLocal threadLocal = CoordinatorLayout.f11717G;
                    Map map = (Map) threadLocal.get();
                    if (map == null) {
                        map = new HashMap();
                        threadLocal.set(map);
                    }
                    Constructor<?> constructor = (Constructor) map.get(string);
                    if (constructor == null) {
                        constructor = Class.forName(string, false, context.getClassLoader()).getConstructor(CoordinatorLayout.f11716F);
                        constructor.setAccessible(true);
                        map.put(string, constructor);
                    }
                    abstractC2425a = (AbstractC2425a) constructor.newInstance(context, attributeSet);
                } catch (Exception e10) {
                    throw new RuntimeException(M5.b.g("Could not inflate Behavior subclass ", string), e10);
                }
            }
            this.f22429a = abstractC2425a;
        }
        typedArrayObtainStyledAttributes.recycle();
        AbstractC2425a abstractC2425a2 = this.f22429a;
        if (abstractC2425a2 != null) {
            abstractC2425a2.c(this);
        }
    }

    public C2428d(C2428d c2428d) {
        super((ViewGroup.MarginLayoutParams) c2428d);
        this.f22430b = false;
        this.c = 0;
        this.d = 0;
        this.f22431e = -1;
        this.f22432f = -1;
        this.f22433g = 0;
        this.f22434h = 0;
        this.o = new Rect();
    }

    public C2428d(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
        this.f22430b = false;
        this.c = 0;
        this.d = 0;
        this.f22431e = -1;
        this.f22432f = -1;
        this.f22433g = 0;
        this.f22434h = 0;
        this.o = new Rect();
    }

    public C2428d(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.f22430b = false;
        this.c = 0;
        this.d = 0;
        this.f22431e = -1;
        this.f22432f = -1;
        this.f22433g = 0;
        this.f22434h = 0;
        this.o = new Rect();
    }
}

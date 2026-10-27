package b.c.a.g;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import java.io.InputStream;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class i extends Resources {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Resources f1767a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f1768b;

    public i(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        Class<?> superclass = resources.getClass();
        while (!superclass.isAssignableFrom(Resources.class)) {
            superclass = superclass.getSuperclass();
        }
        Class<i> superclass2 = i.class;
        while (!superclass2.isAssignableFrom(Resources.class)) {
            superclass2 = superclass2.getSuperclass();
        }
        for (Field field : superclass.getDeclaredFields()) {
            if (field != null) {
                StringBuilder sbD = b.a.a.a.a.d("resetFields name={?}");
                sbD.append(field.getName());
                Log.d("UiStyleResourceWrapper", sbD.toString());
                field.setAccessible(true);
                try {
                    Object obj = field.get(resources);
                    Field declaredField = superclass2.getDeclaredField(field.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(this, obj);
                } catch (Exception e2) {
                    Log.e("UiStyleResourceWrapper", "Exception={?}", e2);
                }
            }
        }
        this.f1767a = resources;
        this.f1768b = context;
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getAnimation(int i) {
        return super.getAnimation(g.f1765b.b(this.f1768b, i));
    }

    @Override // android.content.res.Resources
    public int getColor(int i) {
        return getColor(i, null);
    }

    @Override // android.content.res.Resources
    public int getColor(int i, Resources.Theme theme) {
        g gVar = g.f1765b;
        return super.getColor(gVar.f1766a.f(this.f1768b, i), theme);
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int i) {
        return getColorStateList(i, null);
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int i, Resources.Theme theme) {
        g gVar = g.f1765b;
        return super.getColorStateList(gVar.f1766a.f(this.f1768b, i), theme);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) {
        return getDrawableForDensity(i, 0, null);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i, Resources.Theme theme) {
        return getDrawableForDensity(i, 0, theme);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i, int i2) {
        return getDrawableForDensity(i, i2, null);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i, int i2, Resources.Theme theme) {
        g gVar = g.f1765b;
        return super.getDrawableForDensity(gVar.f1766a.b(this.f1768b, i), i2, theme);
    }

    @Override // android.content.res.Resources
    public int[] getIntArray(int i) {
        return super.getIntArray(g.f1765b.a(this.f1768b, i));
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getLayout(int i) {
        g gVar = g.f1765b;
        return super.getLayout(gVar.f1766a.e(this.f1768b, i));
    }

    @Override // android.content.res.Resources
    public String[] getStringArray(int i) {
        return super.getStringArray(g.f1765b.a(this.f1768b, i));
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i) {
        g gVar = g.f1765b;
        return super.getText(gVar.f1766a.c(this.f1768b, i));
    }

    @Override // android.content.res.Resources
    public CharSequence[] getTextArray(int i) {
        return super.getTextArray(g.f1765b.a(this.f1768b, i));
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i) {
        return super.openRawResource(g.f1765b.b(this.f1768b, i));
    }

    @Override // android.content.res.Resources
    public void updateConfiguration(Configuration configuration, DisplayMetrics displayMetrics) {
        super.updateConfiguration(configuration, displayMetrics);
        Resources resources = this.f1767a;
        if (resources != null) {
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i, TypedValue typedValue) {
        return super.openRawResource(g.f1765b.b(this.f1768b, i), typedValue);
    }
}

package android.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.InflateException;
import com.bydux.api.common.views.skin.entity.AttrFactory;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class DrawableInflater {
    private static final HashMap<String, Constructor<? extends Drawable>> CONSTRUCTOR_MAP = new HashMap<>();
    private final ClassLoader mClassLoader;
    private final Resources mRes;

    public static Drawable loadDrawable(Context context, int i) {
        return loadDrawable(context.getResources(), context.getTheme(), i);
    }

    public static Drawable loadDrawable(Resources resources, Resources.Theme theme, int i) {
        return resources.getDrawable(i, theme);
    }

    public DrawableInflater(Resources resources, ClassLoader classLoader) {
        this.mRes = resources;
        this.mClassLoader = classLoader;
    }

    public Drawable inflateFromXml(String str, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        return inflateFromXmlForDensity(str, xmlPullParser, attributeSet, 0, theme);
    }

    Drawable inflateFromXmlForDensity(String str, XmlPullParser xmlPullParser, AttributeSet attributeSet, int i, Resources.Theme theme) throws XmlPullParserException, IOException {
        if (str.equals(AttrFactory.DRAWABLE) && (str = attributeSet.getAttributeValue(null, "class")) == null) {
            throw new InflateException("<drawable> tag must specify class attribute");
        }
        Drawable drawableInflateFromTag = inflateFromTag(str);
        if (drawableInflateFromTag == null) {
            drawableInflateFromTag = inflateFromClass(str);
        }
        drawableInflateFromTag.setSrcDensityOverride(i);
        drawableInflateFromTag.inflate(this.mRes, xmlPullParser, attributeSet, theme);
        return drawableInflateFromTag;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.drawable.Drawable inflateFromTag(java.lang.String r2) {
        /*
            Method dump skipped, instruction units count: 492
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.graphics.drawable.DrawableInflater.inflateFromTag(java.lang.String):android.graphics.drawable.Drawable");
    }

    private Drawable inflateFromClass(String str) {
        Constructor<? extends Drawable> constructor;
        try {
            synchronized (CONSTRUCTOR_MAP) {
                constructor = CONSTRUCTOR_MAP.get(str);
                if (constructor == null) {
                    constructor = this.mClassLoader.loadClass(str).asSubclass(Drawable.class).getConstructor(new Class[0]);
                    CONSTRUCTOR_MAP.put(str, constructor);
                }
            }
            return constructor.newInstance(new Object[0]);
        } catch (ClassCastException e) {
            InflateException inflateException = new InflateException("Class is not a Drawable " + str);
            inflateException.initCause(e);
            throw inflateException;
        } catch (ClassNotFoundException e2) {
            InflateException inflateException2 = new InflateException("Class not found " + str);
            inflateException2.initCause(e2);
            throw inflateException2;
        } catch (NoSuchMethodException e3) {
            InflateException inflateException3 = new InflateException("Error inflating class " + str);
            inflateException3.initCause(e3);
            throw inflateException3;
        } catch (Exception e4) {
            InflateException inflateException4 = new InflateException("Error inflating class " + str);
            inflateException4.initCause(e4);
            throw inflateException4;
        }
    }
}

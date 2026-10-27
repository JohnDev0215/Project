package android.graphics.drawable;

import android.bluetooth.BluetoothHidDevice;
import android.content.Context;
import android.content.res.Resources;
import android.hardware.gnss.visibility_control.V1_0.IGnssVisibilityControlCallback;
import android.os.BatteryManager;
import android.util.AttributeSet;
import android.view.InflateException;
import com.android.internal.midi.MidiConstants;
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
        if (str.equals("drawable") && (str = attributeSet.getAttributeValue(null, "class")) == null) {
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

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private Drawable inflateFromTag(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -2024464016:
                if (str.equals("adaptive-icon")) {
                    b = 0;
                }
                break;
            case -1724158635:
                if (str.equals("transition")) {
                    b = 1;
                }
                break;
            case -1671889043:
                if (str.equals("nine-patch")) {
                    b = 2;
                }
                break;
            case -1493546681:
                if (str.equals("animation-list")) {
                    b = 3;
                }
                break;
            case -1388777169:
                if (str.equals("bitmap")) {
                    b = 4;
                }
                break;
            case -930826704:
                if (str.equals("ripple")) {
                    b = 5;
                }
                break;
            case -925180581:
                if (str.equals("rotate")) {
                    b = 6;
                }
                break;
            case -820387517:
                if (str.equals("vector")) {
                    b = 7;
                }
                break;
            case -510364471:
                if (str.equals("animated-selector")) {
                    b = 8;
                }
                break;
            case -94197862:
                if (str.equals("layer-list")) {
                    b = 9;
                }
                break;
            case 3056464:
                if (str.equals("clip")) {
                    b = 10;
                }
                break;
            case 94842723:
                if (str.equals("color")) {
                    b = 11;
                }
                break;
            case 100360477:
                if (str.equals("inset")) {
                    b = 12;
                }
                break;
            case 109250890:
                if (str.equals(BatteryManager.EXTRA_SCALE)) {
                    b = IGnssVisibilityControlCallback.NfwRequestor.OTHER_CHIPSET_VENDOR;
                }
                break;
            case 109399969:
                if (str.equals("shape")) {
                    b = BluetoothHidDevice.ERROR_RSP_UNKNOWN;
                }
                break;
            case 160680263:
                if (str.equals("level-list")) {
                    b = MidiConstants.STATUS_CHANNEL_MASK;
                }
                break;
            case 1191572447:
                if (str.equals("selector")) {
                    b = 16;
                }
                break;
            case 1442046129:
                if (str.equals("animated-image")) {
                    b = 17;
                }
                break;
            case 2013827269:
                if (str.equals("animated-rotate")) {
                    b = 18;
                }
                break;
            case 2118620333:
                if (str.equals("animated-vector")) {
                    b = 19;
                }
                break;
        }
        switch (b) {
            case 0:
                return new AdaptiveIconDrawable();
            case 1:
                return new TransitionDrawable();
            case 2:
                return new NinePatchDrawable();
            case 3:
                return new AnimationDrawable();
            case 4:
                return new BitmapDrawable();
            case 5:
                return new RippleDrawable();
            case 6:
                return new RotateDrawable();
            case 7:
                return new VectorDrawable();
            case 8:
                return new AnimatedStateListDrawable();
            case 9:
                return new LayerDrawable();
            case 10:
                return new ClipDrawable();
            case 11:
                return new ColorDrawable();
            case 12:
                return new InsetDrawable();
            case 13:
                return new ScaleDrawable();
            case 14:
                return new GradientDrawable();
            case 15:
                return new LevelListDrawable();
            case 16:
                return new StateListDrawable();
            case 17:
                return new AnimatedImageDrawable();
            case 18:
                return new AnimatedRotateDrawable();
            case 19:
                return new AnimatedVectorDrawable();
            default:
                return null;
        }
    }

    private Drawable inflateFromClass(String str) {
        Constructor<? extends Drawable> constructor;
        try {
            HashMap<String, Constructor<? extends Drawable>> map = CONSTRUCTOR_MAP;
            synchronized (map) {
                constructor = map.get(str);
                if (constructor == null) {
                    constructor = this.mClassLoader.loadClass(str).asSubclass(Drawable.class).getConstructor(new Class[0]);
                    map.put(str, constructor);
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

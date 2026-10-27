package f0;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ComplexColorCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Shader f4805a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f4806b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4807c;

    public b(Shader shader, ColorStateList colorStateList, int i8) {
        this.f4805a = shader;
        this.f4806b = colorStateList;
        this.f4807c = i8;
    }

    public static b a(Resources resources, int i8, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        XmlResourceParser xml = resources.getXml(i8);
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
        do {
            next = xml.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        String name = xml.getName();
        name.hashCode();
        if (name.equals("gradient")) {
            return d(d.b(resources, xml, attributeSetAsAttributeSet, theme));
        }
        if (name.equals("selector")) {
            return c(a.b(resources, xml, attributeSetAsAttributeSet, theme));
        }
        throw new XmlPullParserException(xml.getPositionDescription() + ": unsupported complex color tag " + name);
    }

    public static b b(int i8) {
        return new b(null, null, i8);
    }

    public static b c(ColorStateList colorStateList) {
        return new b(null, colorStateList, colorStateList.getDefaultColor());
    }

    public static b d(Shader shader) {
        return new b(shader, null, 0);
    }

    public static b g(Resources resources, int i8, Resources.Theme theme) {
        try {
            return a(resources, i8, theme);
        } catch (Exception e8) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e8);
            return null;
        }
    }

    public int e() {
        return this.f4807c;
    }

    public Shader f() {
        return this.f4805a;
    }

    public boolean h() {
        return this.f4805a != null;
    }

    public boolean i() {
        ColorStateList colorStateList;
        return this.f4805a == null && (colorStateList = this.f4806b) != null && colorStateList.isStateful();
    }

    public boolean j(int[] iArr) {
        if (i()) {
            ColorStateList colorStateList = this.f4806b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.f4807c) {
                this.f4807c = colorForState;
                return true;
            }
        }
        return false;
    }

    public void k(int i8) {
        this.f4807c = i8;
    }

    public boolean l() {
        return h() || this.f4807c != 0;
    }
}

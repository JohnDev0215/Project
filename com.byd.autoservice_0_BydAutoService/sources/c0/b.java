package c0;

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
    public final Shader f1788a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f1789b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1790c;

    public b(Shader shader, ColorStateList colorStateList, int i3) {
        this.f1788a = shader;
        this.f1789b = colorStateList;
        this.f1790c = i3;
    }

    public static b a(Resources resources, int i3, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        XmlResourceParser xml = resources.getXml(i3);
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

    public static b b(int i3) {
        return new b(null, null, i3);
    }

    public static b c(ColorStateList colorStateList) {
        return new b(null, colorStateList, colorStateList.getDefaultColor());
    }

    public static b d(Shader shader) {
        return new b(shader, null, 0);
    }

    public static b g(Resources resources, int i3, Resources.Theme theme) {
        try {
            return a(resources, i3, theme);
        } catch (Exception e3) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e3);
            return null;
        }
    }

    public int e() {
        return this.f1790c;
    }

    public Shader f() {
        return this.f1788a;
    }

    public boolean h() {
        return this.f1788a != null;
    }

    public boolean i() {
        ColorStateList colorStateList;
        return this.f1788a == null && (colorStateList = this.f1789b) != null && colorStateList.isStateful();
    }

    public boolean j(int[] iArr) {
        if (i()) {
            ColorStateList colorStateList = this.f1789b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.f1790c) {
                this.f1790c = colorForState;
                return true;
            }
        }
        return false;
    }

    public void k(int i3) {
        this.f1790c = i3;
    }

    public boolean l() {
        return h() || this.f1790c != 0;
    }
}

package androidx.core.content.c;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Shader f1199a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ColorStateList f1200b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1201c;

    private b(Shader shader, ColorStateList colorStateList, int i) {
        this.f1199a = shader;
        this.f1200b = colorStateList;
        this.f1201c = i;
    }

    private static b a(Resources resources, int i, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        XmlResourceParser xml = resources.getXml(i);
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

    static b b(int i) {
        return new b(null, null, i);
    }

    static b c(ColorStateList colorStateList) {
        return new b(null, colorStateList, colorStateList.getDefaultColor());
    }

    static b d(Shader shader) {
        return new b(shader, null, 0);
    }

    public static b g(Resources resources, int i, Resources.Theme theme) {
        try {
            return a(resources, i, theme);
        } catch (Exception e) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e);
            return null;
        }
    }

    public int e() {
        return this.f1201c;
    }

    public Shader f() {
        return this.f1199a;
    }

    public boolean h() {
        return this.f1199a != null;
    }

    public boolean i() {
        ColorStateList colorStateList;
        return this.f1199a == null && (colorStateList = this.f1200b) != null && colorStateList.isStateful();
    }

    public boolean j(int[] iArr) {
        if (i()) {
            ColorStateList colorStateList = this.f1200b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.f1201c) {
                this.f1201c = colorForState;
                return true;
            }
        }
        return false;
    }

    public void k(int i) {
        this.f1201c = i;
    }

    public boolean l() {
        return h() || this.f1201c != 0;
    }
}

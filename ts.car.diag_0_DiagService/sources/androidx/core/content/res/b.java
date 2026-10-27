package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Shader f460a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ColorStateList f461b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f462c;

    private b(Shader shader, ColorStateList colorStateList, @ColorInt int i) {
        this.f460a = shader;
        this.f461b = colorStateList;
        this.f462c = i;
    }

    @NonNull
    private static b a(@NonNull Resources resources, @ColorRes int i, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
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
        byte b2 = -1;
        int iHashCode = name.hashCode();
        if (iHashCode != 89650992) {
            if (iHashCode == 1191572447 && name.equals("selector")) {
                b2 = 0;
            }
        } else if (name.equals("gradient")) {
            b2 = 1;
        }
        if (b2 == 0) {
            return c(a.b(resources, xml, attributeSetAsAttributeSet, theme));
        }
        if (b2 == 1) {
            return d(c.b(resources, xml, attributeSetAsAttributeSet, theme));
        }
        throw new XmlPullParserException(xml.getPositionDescription() + ": unsupported complex color tag " + name);
    }

    static b b(@ColorInt int i) {
        return new b(null, null, i);
    }

    static b c(@NonNull ColorStateList colorStateList) {
        return new b(null, colorStateList, colorStateList.getDefaultColor());
    }

    static b d(@NonNull Shader shader) {
        return new b(shader, null, 0);
    }

    @Nullable
    public static b g(@NonNull Resources resources, @ColorRes int i, @Nullable Resources.Theme theme) {
        try {
            return a(resources, i, theme);
        } catch (Exception e) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e);
            return null;
        }
    }

    @ColorInt
    public int e() {
        return this.f462c;
    }

    @Nullable
    public Shader f() {
        return this.f460a;
    }

    public boolean h() {
        return this.f460a != null;
    }

    public boolean i() {
        ColorStateList colorStateList;
        return this.f460a == null && (colorStateList = this.f461b) != null && colorStateList.isStateful();
    }

    public boolean j(int[] iArr) {
        if (i()) {
            ColorStateList colorStateList = this.f461b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.f462c) {
                this.f462c = colorForState;
                return true;
            }
        }
        return false;
    }

    public void k(@ColorInt int i) {
        this.f462c = i;
    }

    public boolean l() {
        return h() || this.f462c != 0;
    }
}

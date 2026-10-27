package p;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.util.TypedValue;
import android.webkit.MimeTypeMap;
import coil.decode.DataSource;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class j42 implements qt0 {
    public final Uri a;
    public final br1 b;

    public j42(Uri uri, br1 br1Var) {
        this.a = uri;
        this.b = br1Var;
    }

    @Override // p.qt0
    public final Object a(c00 c00Var) throws XmlPullParserException, IOException {
        Integer numY;
        Drawable drawableA;
        Uri uri = this.a;
        String authority = uri.getAuthority();
        if (authority != null) {
            boolean z = true;
            if (!(!ng2.c0(authority))) {
                authority = null;
            }
            if (authority != null) {
                List<String> pathSegments = uri.getPathSegments();
                v03.j(pathSegments, "<this>");
                String str = pathSegments.isEmpty() ? null : pathSegments.get(pathSegments.size() - 1);
                if (str == null || (numY = mg2.Y(str)) == null) {
                    throw new IllegalStateException("Invalid android.resource URI: " + uri);
                }
                int iIntValue = numY.intValue();
                br1 br1Var = this.b;
                Context context = br1Var.a;
                Resources resources = v03.b(authority, context.getPackageName()) ? context.getResources() : context.getPackageManager().getResourcesForApplication(authority);
                TypedValue typedValue = new TypedValue();
                resources.getValue(iIntValue, typedValue, true);
                CharSequence charSequence = typedValue.string;
                String strB = i.b(MimeTypeMap.getSingleton(), charSequence.subSequence(kotlin.text.b.q0(charSequence, '/', 0, 6), charSequence.length()).toString());
                if (!v03.b(strB, "text/xml")) {
                    TypedValue typedValue2 = new TypedValue();
                    return new fe2(new ee2(v03.d(v03.L(resources.openRawResource(iIntValue, typedValue2))), new i42(typedValue2.density)), strB, DataSource.k);
                }
                if (v03.b(authority, context.getPackageName())) {
                    drawableA = l13.f(context, iIntValue);
                    if (drawableA == null) {
                        throw new IllegalStateException(u4.e("Invalid resource ID: ", iIntValue).toString());
                    }
                } else {
                    XmlResourceParser xml = resources.getXml(iIntValue);
                    int next = xml.next();
                    while (next != 2 && next != 1) {
                        next = xml.next();
                    }
                    if (next != 2) {
                        throw new XmlPullParserException("No start tag found.");
                    }
                    Resources.Theme theme = context.getTheme();
                    ThreadLocal threadLocal = s42.a;
                    drawableA = k42.a(resources, iIntValue, theme);
                    if (drawableA == null) {
                        throw new IllegalStateException(u4.e("Invalid resource ID: ", iIntValue).toString());
                    }
                }
                if (!(drawableA instanceof VectorDrawable) && !(drawableA instanceof vs2)) {
                    z = false;
                }
                if (z) {
                    drawableA = new BitmapDrawable(context.getResources(), ln1.d(drawableA, br1Var.b, br1Var.d, br1Var.e, br1Var.f));
                }
                return new bb0(drawableA, z, DataSource.k);
            }
        }
        throw new IllegalStateException("Invalid android.resource URI: " + uri);
    }
}

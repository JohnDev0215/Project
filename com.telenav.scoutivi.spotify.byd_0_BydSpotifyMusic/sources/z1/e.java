package z1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import java.util.List;
import n1.i;
import n1.k;
import q1.v;

/* JADX INFO: compiled from: ResourceDrawableDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements k<Uri, Drawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f10045a;

    public e(Context context) {
        this.f10045a = context.getApplicationContext();
    }

    private Context d(Uri uri, String str) {
        if (str.equals(this.f10045a.getPackageName())) {
            return this.f10045a;
        }
        try {
            return this.f10045a.createPackageContext(str, 0);
        } catch (PackageManager.NameNotFoundException e7) {
            if (str.contains(this.f10045a.getPackageName())) {
                return this.f10045a;
            }
            throw new IllegalArgumentException("Failed to obtain context or unrecognized Uri format for: " + uri, e7);
        }
    }

    private int e(Uri uri) {
        try {
            return Integer.parseInt(uri.getPathSegments().get(0));
        } catch (NumberFormatException e7) {
            throw new IllegalArgumentException("Unrecognized Uri format: " + uri, e7);
        }
    }

    private int f(Context context, Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        String authority = uri.getAuthority();
        String str = pathSegments.get(0);
        String str2 = pathSegments.get(1);
        int identifier = context.getResources().getIdentifier(str2, str, authority);
        if (identifier == 0) {
            identifier = Resources.getSystem().getIdentifier(str2, str, "android");
        }
        if (identifier != 0) {
            return identifier;
        }
        throw new IllegalArgumentException("Failed to find resource id for: " + uri);
    }

    private int g(Context context, Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() == 2) {
            return f(context, uri);
        }
        if (pathSegments.size() == 1) {
            return e(uri);
        }
        throw new IllegalArgumentException("Unrecognized Uri format: " + uri);
    }

    @Override // n1.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public v<Drawable> b(Uri uri, int i7, int i8, i iVar) {
        Context contextD = d(uri, uri.getAuthority());
        return d.f(a.b(this.f10045a, contextD, g(contextD, uri)));
    }

    @Override // n1.k
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public boolean a(Uri uri, i iVar) {
        return uri.getScheme().equals("android.resource");
    }
}

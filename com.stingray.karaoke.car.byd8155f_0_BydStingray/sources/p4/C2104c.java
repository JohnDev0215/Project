package p4;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import e4.g;
import e4.h;
import e4.j;
import g4.x;
import java.util.List;
import n7.z;

/* JADX INFO: renamed from: p4.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2104c implements j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final g f20898b = new g("com.bumptech.glide.load.resource.bitmap.Downsampler.Theme", null, g.f14431e);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f20899a;

    public C2104c(Context context) {
        this.f20899a = context.getApplicationContext();
    }

    @Override // e4.j
    public final /* bridge */ /* synthetic */ x a(Object obj, int i7, int i10, h hVar) {
        return c((Uri) obj, hVar);
    }

    @Override // e4.j
    public final boolean b(Object obj, h hVar) {
        String scheme = ((Uri) obj).getScheme();
        return scheme != null && scheme.equals("android.resource");
    }

    public final x c(Uri uri, h hVar) {
        Context contextCreatePackageContext;
        int identifier;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new IllegalStateException("Package name for " + uri + " is null or empty");
        }
        Context context = this.f20899a;
        if (authority.equals(context.getPackageName())) {
            contextCreatePackageContext = context;
        } else {
            try {
                contextCreatePackageContext = context.createPackageContext(authority, 0);
            } catch (PackageManager.NameNotFoundException e10) {
                if (!authority.contains(context.getPackageName())) {
                    throw new IllegalArgumentException("Failed to obtain context or unrecognized Uri format for: " + uri, e10);
                }
                contextCreatePackageContext = context;
            }
        }
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() == 2) {
            List<String> pathSegments2 = uri.getPathSegments();
            String authority2 = uri.getAuthority();
            String str = pathSegments2.get(0);
            String str2 = pathSegments2.get(1);
            identifier = contextCreatePackageContext.getResources().getIdentifier(str2, str, authority2);
            if (identifier == 0) {
                identifier = Resources.getSystem().getIdentifier(str2, str, "android");
            }
            if (identifier == 0) {
                throw new IllegalArgumentException("Failed to find resource id for: " + uri);
            }
        } else {
            if (pathSegments.size() != 1) {
                throw new IllegalArgumentException("Unrecognized Uri format: " + uri);
            }
            try {
                identifier = Integer.parseInt(uri.getPathSegments().get(0));
            } catch (NumberFormatException e11) {
                throw new IllegalArgumentException("Unrecognized Uri format: " + uri, e11);
            }
        }
        Resources.Theme theme = authority.equals(context.getPackageName()) ? (Resources.Theme) hVar.c(f20898b) : null;
        Drawable drawableH = theme == null ? z.h(context, contextCreatePackageContext, identifier, null) : z.h(context, context, identifier, theme);
        if (drawableH != null) {
            return new C2103b(drawableH, 0);
        }
        return null;
    }
}

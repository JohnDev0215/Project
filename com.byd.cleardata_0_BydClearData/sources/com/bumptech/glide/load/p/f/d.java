package com.bumptech.glide.load.p.f;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.k;
import com.bumptech.glide.load.n.v;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d implements k<Uri, Drawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f2385a;

    public d(Context context) {
        this.f2385a = context.getApplicationContext();
    }

    private Context d(Uri uri, String str) {
        if (str.equals(this.f2385a.getPackageName())) {
            return this.f2385a;
        }
        try {
            return this.f2385a.createPackageContext(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            if (str.contains(this.f2385a.getPackageName())) {
                return this.f2385a;
            }
            throw new IllegalArgumentException("Failed to obtain context or unrecognized Uri format for: " + uri, e);
        }
    }

    private int e(Uri uri) {
        try {
            return Integer.parseInt(uri.getPathSegments().get(0));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Unrecognized Uri format: " + uri, e);
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

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public v<Drawable> a(Uri uri, int i, int i2, i iVar) {
        Context contextD = d(uri, uri.getAuthority());
        return c.g(a.b(this.f2385a, contextD, g(contextD, uri)));
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri, i iVar) {
        return uri.getScheme().equals("android.resource");
    }
}

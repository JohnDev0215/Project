package com.bumptech.glide;

import android.content.Context;
import android.util.Log;
import com.vivid.glide.TnGlideModule;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
final class GeneratedAppGlideModuleImpl extends GeneratedAppGlideModule {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TnGlideModule f3934a = new TnGlideModule();

    public GeneratedAppGlideModuleImpl(Context context) {
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Discovered AppGlideModule from annotation: com.vivid.glide.TnGlideModule");
            Log.d("Glide", "AppGlideModule excludes LibraryGlideModule from annotation: com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule");
        }
    }

    @Override // e2.c
    public void a(Context context, c cVar, i iVar) {
        this.f3934a.a(context, cVar, iVar);
    }

    @Override // e2.a
    public void b(Context context, d dVar) {
        this.f3934a.b(context, dVar);
    }

    @Override // e2.a
    public boolean c() {
        return this.f3934a.c();
    }

    @Override // com.bumptech.glide.GeneratedAppGlideModule
    public Set<Class<?>> d() {
        HashSet hashSet = new HashSet();
        hashSet.add(com.bumptech.glide.integration.okhttp3.a.class);
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bumptech.glide.GeneratedAppGlideModule
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public a e() {
        return new a();
    }
}

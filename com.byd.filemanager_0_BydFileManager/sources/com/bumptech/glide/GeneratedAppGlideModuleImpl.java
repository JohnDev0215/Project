package com.bumptech.glide;

import android.content.Context;
import android.util.Log;
import com.byd.filemanager.glide.VideoGlideModule;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
final class GeneratedAppGlideModuleImpl extends GeneratedAppGlideModule {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final VideoGlideModule f2832a = new VideoGlideModule();

    public GeneratedAppGlideModuleImpl(Context context) {
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Discovered AppGlideModule from annotation: com.byd.filemanager.glide.VideoGlideModule");
        }
    }

    @Override // com.bumptech.glide.l.c
    public void a(Context context, c cVar, Registry registry) {
        this.f2832a.a(context, cVar, registry);
    }

    @Override // com.bumptech.glide.l.a
    public void b(Context context, d dVar) {
        this.f2832a.b(context, dVar);
    }

    @Override // com.bumptech.glide.l.a
    public boolean c() {
        return this.f2832a.c();
    }

    @Override // com.bumptech.glide.GeneratedAppGlideModule
    public Set<Class<?>> d() {
        return Collections.emptySet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bumptech.glide.GeneratedAppGlideModule
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public a e() {
        return new a();
    }
}

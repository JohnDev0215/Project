package spi;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;

/* JADX INFO: loaded from: classes.dex */
public final class od<TranscodeType> extends nm<TranscodeType> {
    public od(@NonNull com.bumptech.glide.a aVar, @NonNull qm qmVar, @NonNull Class<TranscodeType> cls, @NonNull Context context) {
        super(aVar, qmVar, cls, context);
    }

    public od(@NonNull Class<TranscodeType> cls, @NonNull nm<?> nmVar) {
        super(cls, nmVar);
    }

    @Override // spi.nm
    @NonNull
    @CheckResult
    public final nm A(@Nullable pm pmVar) {
        return (od) super.A(pmVar);
    }

    @Override // spi.nm
    @NonNull
    @CheckResult
    /* JADX INFO: renamed from: B */
    public final nm a(@NonNull i1 i1Var) {
        return (od) super.a(i1Var);
    }

    @Override // spi.nm
    @NonNull
    @CheckResult
    public final nm H(@Nullable Drawable drawable) {
        return (od) K(drawable).a(tm.A(i6.a));
    }

    @Override // spi.nm
    @NonNull
    @CheckResult
    public final nm I(@Nullable Object obj) {
        return (od) K(obj);
    }

    @Override // spi.nm
    @NonNull
    @CheckResult
    public final nm J(@Nullable String str) {
        return (od) K(str);
    }

    @Override // spi.nm, spi.i1
    @CheckResult
    /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
    public final od<TranscodeType> clone() {
        return (od) super.clone();
    }

    @Override // spi.nm, spi.i1
    @NonNull
    @CheckResult
    public final i1 a(@NonNull i1 i1Var) {
        return (od) super.a(i1Var);
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 d(@NonNull Class cls) {
        return (od) super.d(cls);
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 f(@NonNull i6 i6Var) {
        return (od) super.f(i6Var);
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 g(@NonNull DownsampleStrategy downsampleStrategy) {
        return (od) super.g(downsampleStrategy);
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 h(@DrawableRes int i) {
        return (od) super.h(i);
    }

    @Override // spi.i1
    @NonNull
    public final i1 j() {
        this.t = true;
        return this;
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 k() {
        return (od) super.k();
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 l() {
        return (od) super.l();
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 m() {
        return (od) super.m();
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 o(int i, int i2) {
        return (od) super.o(i, i2);
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 p(@DrawableRes int i) {
        return (od) super.p(i);
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 q(@NonNull Priority priority) {
        return (od) super.q(priority);
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 s(@NonNull jk jkVar, @NonNull Object obj) {
        return (od) super.s(jkVar, obj);
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 t(@NonNull ig igVar) {
        return (od) super.t(igVar);
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 u() {
        return (od) super.u();
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 w(@NonNull qt qtVar) {
        return (od) x(qtVar, true);
    }

    @Override // spi.i1
    @NonNull
    @CheckResult
    public final i1 z() {
        return (od) super.z();
    }
}

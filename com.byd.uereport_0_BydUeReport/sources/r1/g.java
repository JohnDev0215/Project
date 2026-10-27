package r1;

import android.graphics.Path;
import com.airbnb.lottie.model.content.Mask;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: MaskKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<a<v1.h, Path>> f7756a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List<a<Integer, Integer>> f7757b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<Mask> f7758c;

    public g(List<Mask> list) {
        this.f7758c = list;
        this.f7756a = new ArrayList(list.size());
        this.f7757b = new ArrayList(list.size());
        for (int i8 = 0; i8 < list.size(); i8++) {
            this.f7756a.add(list.get(i8).b().a());
            this.f7757b.add(list.get(i8).c().a());
        }
    }

    public List<a<v1.h, Path>> a() {
        return this.f7756a;
    }

    public List<Mask> b() {
        return this.f7758c;
    }

    public List<a<Integer, Integer>> c() {
        return this.f7757b;
    }
}

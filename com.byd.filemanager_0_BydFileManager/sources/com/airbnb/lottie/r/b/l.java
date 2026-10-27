package com.airbnb.lottie.r.b;

import android.annotation.TargetApi;
import android.graphics.Path;
import android.os.Build;
import com.airbnb.lottie.model.content.MergePaths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: MergePathsContent.java */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(19)
public class l implements m, j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f2696d;
    private final MergePaths f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f2693a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Path f2694b = new Path();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Path f2695c = new Path();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<m> f2697e = new ArrayList();

    /* JADX INFO: compiled from: MergePathsContent.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2698a;

        static {
            int[] iArr = new int[MergePaths.MergePathsMode.values().length];
            f2698a = iArr;
            try {
                iArr[MergePaths.MergePathsMode.MERGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2698a[MergePaths.MergePathsMode.ADD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2698a[MergePaths.MergePathsMode.SUBTRACT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2698a[MergePaths.MergePathsMode.INTERSECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2698a[MergePaths.MergePathsMode.EXCLUDE_INTERSECTIONS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public l(MergePaths mergePaths) {
        if (Build.VERSION.SDK_INT < 19) {
            throw new IllegalStateException("Merge paths are not supported pre-KitKat.");
        }
        this.f2696d = mergePaths.c();
        this.f = mergePaths;
    }

    private void b() {
        for (int i = 0; i < this.f2697e.size(); i++) {
            this.f2695c.addPath(this.f2697e.get(i).h());
        }
    }

    @TargetApi(19)
    private void e(Path.Op op) {
        this.f2694b.reset();
        this.f2693a.reset();
        for (int size = this.f2697e.size() - 1; size >= 1; size--) {
            m mVar = this.f2697e.get(size);
            if (mVar instanceof d) {
                d dVar = (d) mVar;
                List<m> listK = dVar.k();
                for (int size2 = listK.size() - 1; size2 >= 0; size2--) {
                    Path pathH = listK.get(size2).h();
                    pathH.transform(dVar.l());
                    this.f2694b.addPath(pathH);
                }
            } else {
                this.f2694b.addPath(mVar.h());
            }
        }
        m mVar2 = this.f2697e.get(0);
        if (mVar2 instanceof d) {
            d dVar2 = (d) mVar2;
            List<m> listK2 = dVar2.k();
            for (int i = 0; i < listK2.size(); i++) {
                Path pathH2 = listK2.get(i).h();
                pathH2.transform(dVar2.l());
                this.f2693a.addPath(pathH2);
            }
        } else {
            this.f2693a.set(mVar2.h());
        }
        this.f2695c.op(this.f2693a, this.f2694b, op);
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
        for (int i = 0; i < this.f2697e.size(); i++) {
            this.f2697e.get(i).c(list, list2);
        }
    }

    @Override // com.airbnb.lottie.r.b.j
    public void d(ListIterator<c> listIterator) {
        while (listIterator.hasPrevious() && listIterator.previous() != this) {
        }
        while (listIterator.hasPrevious()) {
            c cVarPrevious = listIterator.previous();
            if (cVarPrevious instanceof m) {
                this.f2697e.add((m) cVarPrevious);
                listIterator.remove();
            }
        }
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path h() {
        this.f2695c.reset();
        if (this.f.d()) {
            return this.f2695c;
        }
        int i = a.f2698a[this.f.b().ordinal()];
        if (i == 1) {
            b();
        } else if (i == 2) {
            e(Path.Op.UNION);
        } else if (i == 3) {
            e(Path.Op.REVERSE_DIFFERENCE);
        } else if (i == 4) {
            e(Path.Op.INTERSECT);
        } else if (i == 5) {
            e(Path.Op.XOR);
        }
        return this.f2695c;
    }
}

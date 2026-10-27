package q1;

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
    public final String f7317d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final MergePaths f7319f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f7314a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path f7315b = new Path();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Path f7316c = new Path();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<m> f7318e = new ArrayList();

    /* JADX INFO: compiled from: MergePathsContent.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7320a;

        static {
            int[] iArr = new int[MergePaths.MergePathsMode.values().length];
            f7320a = iArr;
            try {
                iArr[MergePaths.MergePathsMode.MERGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7320a[MergePaths.MergePathsMode.ADD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7320a[MergePaths.MergePathsMode.SUBTRACT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7320a[MergePaths.MergePathsMode.INTERSECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7320a[MergePaths.MergePathsMode.EXCLUDE_INTERSECTIONS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public l(MergePaths mergePaths) {
        if (Build.VERSION.SDK_INT < 19) {
            throw new IllegalStateException("Merge paths are not supported pre-KitKat.");
        }
        this.f7317d = mergePaths.c();
        this.f7319f = mergePaths;
    }

    @Override // q1.m
    public Path a() {
        this.f7316c.reset();
        if (this.f7319f.d()) {
            return this.f7316c;
        }
        int i8 = a.f7320a[this.f7319f.b().ordinal()];
        if (i8 == 1) {
            c();
        } else if (i8 == 2) {
            f(Path.Op.UNION);
        } else if (i8 == 3) {
            f(Path.Op.REVERSE_DIFFERENCE);
        } else if (i8 == 4) {
            f(Path.Op.INTERSECT);
        } else if (i8 == 5) {
            f(Path.Op.XOR);
        }
        return this.f7316c;
    }

    public final void c() {
        for (int i8 = 0; i8 < this.f7318e.size(); i8++) {
            this.f7316c.addPath(this.f7318e.get(i8).a());
        }
    }

    @Override // q1.c
    public void d(List<c> list, List<c> list2) {
        for (int i8 = 0; i8 < this.f7318e.size(); i8++) {
            this.f7318e.get(i8).d(list, list2);
        }
    }

    @Override // q1.j
    public void e(ListIterator<c> listIterator) {
        while (listIterator.hasPrevious() && listIterator.previous() != this) {
        }
        while (listIterator.hasPrevious()) {
            c cVarPrevious = listIterator.previous();
            if (cVarPrevious instanceof m) {
                this.f7318e.add((m) cVarPrevious);
                listIterator.remove();
            }
        }
    }

    @TargetApi(19)
    public final void f(Path.Op op) {
        this.f7315b.reset();
        this.f7314a.reset();
        for (int size = this.f7318e.size() - 1; size >= 1; size--) {
            m mVar = this.f7318e.get(size);
            if (mVar instanceof d) {
                d dVar = (d) mVar;
                List<m> listK = dVar.k();
                for (int size2 = listK.size() - 1; size2 >= 0; size2--) {
                    Path pathA = listK.get(size2).a();
                    pathA.transform(dVar.l());
                    this.f7315b.addPath(pathA);
                }
            } else {
                this.f7315b.addPath(mVar.a());
            }
        }
        m mVar2 = this.f7318e.get(0);
        if (mVar2 instanceof d) {
            d dVar2 = (d) mVar2;
            List<m> listK2 = dVar2.k();
            for (int i8 = 0; i8 < listK2.size(); i8++) {
                Path pathA2 = listK2.get(i8).a();
                pathA2.transform(dVar2.l());
                this.f7314a.addPath(pathA2);
            }
        } else {
            this.f7314a.set(mVar2.a());
        }
        this.f7316c.op(this.f7314a, this.f7315b, op);
    }
}

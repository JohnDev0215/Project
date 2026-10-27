package androidx.recyclerview.widget;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class c implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final i f1157a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1158b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1159c = -1;
    int d = -1;
    Object e = null;

    public c(@NonNull i iVar) {
        this.f1157a = iVar;
    }

    @Override // androidx.recyclerview.widget.i
    public void a(int i, int i2) {
        int i3;
        if (this.f1158b == 2 && (i3 = this.f1159c) >= i && i3 <= i + i2) {
            this.d += i2;
            this.f1159c = i;
        } else {
            e();
            this.f1159c = i;
            this.d = i2;
            this.f1158b = 2;
        }
    }

    @Override // androidx.recyclerview.widget.i
    public void b(int i, int i2) {
        int i3;
        if (this.f1158b == 1 && i >= (i3 = this.f1159c)) {
            int i4 = this.d;
            if (i <= i3 + i4) {
                this.d = i4 + i2;
                this.f1159c = Math.min(i, i3);
                return;
            }
        }
        e();
        this.f1159c = i;
        this.d = i2;
        this.f1158b = 1;
    }

    @Override // androidx.recyclerview.widget.i
    public void c(int i, int i2) {
        e();
        this.f1157a.c(i, i2);
    }

    @Override // androidx.recyclerview.widget.i
    public void d(int i, int i2, Object obj) {
        int i3;
        if (this.f1158b == 3) {
            int i4 = this.f1159c;
            int i5 = this.d;
            if (i <= i4 + i5 && (i3 = i + i2) >= i4 && this.e == obj) {
                this.f1159c = Math.min(i, i4);
                this.d = Math.max(i5 + i4, i3) - this.f1159c;
                return;
            }
        }
        e();
        this.f1159c = i;
        this.d = i2;
        this.e = obj;
        this.f1158b = 3;
    }

    public void e() {
        int i = this.f1158b;
        if (i == 0) {
            return;
        }
        if (i == 1) {
            this.f1157a.b(this.f1159c, this.d);
        } else if (i == 2) {
            this.f1157a.a(this.f1159c, this.d);
        } else if (i == 3) {
            this.f1157a.d(this.f1159c, this.d, this.e);
        }
        this.e = null;
        this.f1158b = 0;
    }
}

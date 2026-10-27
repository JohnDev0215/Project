package com.byd.blockingqueue;

/* JADX INFO: compiled from: BasicTask.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Priority f3214b = Priority.DEFAULT;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3215c;

    @Override // com.byd.blockingqueue.b
    public int a() {
        return this.f3215c;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(b bVar) {
        Priority priorityC = c();
        Priority priorityC2 = bVar.c();
        return priorityC == priorityC2 ? a() - bVar.a() : priorityC2.ordinal() - priorityC.ordinal();
    }

    @Override // com.byd.blockingqueue.b
    public Priority c() {
        return this.f3214b;
    }

    public void d(Priority priority) {
        this.f3214b = priority;
    }

    @Override // com.byd.blockingqueue.b
    public void f(int i) {
        this.f3215c = i;
    }
}

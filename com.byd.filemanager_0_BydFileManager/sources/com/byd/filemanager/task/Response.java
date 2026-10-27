package com.byd.filemanager.task;

import com.byd.filemanager.bean.FileItem;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Response {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f3676a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f3677b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f3678c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<FileItem> f3679d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private FileItem f3680e;

    public Response(long j, long j2) {
        this.f3676a = 2;
        this.f3677b = j;
        this.f3678c = j2;
    }

    public FileItem a() {
        return this.f3680e;
    }

    public List<FileItem> b() {
        return this.f3679d;
    }

    public long c() {
        return this.f3677b;
    }

    public int d() {
        return this.f3676a;
    }

    public long e() {
        return this.f3678c;
    }

    public Response(int i, List<FileItem> list) {
        this.f3676a = i;
        this.f3679d = list;
    }

    public Response(int i, FileItem fileItem) {
        this.f3676a = i;
        this.f3680e = fileItem;
    }
}

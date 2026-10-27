package org.extra.relinker;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: classes3.dex */
public class e implements FilenameFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f3981a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ f f3982b;

    public e(f fVar, String str) {
        this.f3982b = fVar;
        this.f3981a = str;
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return str.startsWith(this.f3981a);
    }
}

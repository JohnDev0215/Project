package io.sentry;

import h8.AbstractC1386c;
import java.io.File;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class T0 implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ File f15956m;

    public /* synthetic */ T0(File file, int i7) {
        this.l = i7;
        this.f15956m = file;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                File[] fileArrListFiles = this.f15956m.listFiles();
                if (fileArrListFiles != null) {
                    for (File file : fileArrListFiles) {
                        if (file.lastModified() < U0.f15967e - TimeUnit.MINUTES.toMillis(5L)) {
                            AbstractC1386c.g(file);
                        }
                    }
                    break;
                }
                break;
            default:
                AbstractC1386c.g(this.f15956m);
                break;
        }
    }
}

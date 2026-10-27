package com.byd.logtools.logservice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.FileObserver;
import com.byd.logtools.application.BydLogApplication;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: FileChangeObserver.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends FileObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<String> f3208a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private File f3209b;

    @SuppressLint({"NewApi"})
    public a(File file) {
        super(file, 1984);
        this.f3208a = new ArrayList();
        BydLogHelper.logD("FileChangeObserver file path = " + file.getAbsoluteFile());
        this.f3209b = file;
    }

    @Override // android.os.FileObserver
    public void onEvent(int i, String str) {
        BydLogHelper.logD("obFile = " + this.f3209b.getPath() + ", onEvent event = " + i + " ,path = " + str);
        boolean z = str != null && (str.endsWith(".zip") || str.endsWith(".gz"));
        if (i == 512 || i == 64) {
            if (z) {
                BydLogHelper.logD(HttpUrl.FRAGMENT_ENCODE_SET, "obFile = " + this.f3209b.getPath() + ",rm = " + this.f3208a.remove(str) + ", mPathList.size() = " + this.f3208a.size());
                return;
            }
            return;
        }
        if (i != 256 && i != 128) {
            if (i == 1024) {
                BydLogHelper.logD("obFile = " + this.f3209b.getPath() + ", DELETE_SELF event = " + i + " ,path = " + str);
                stopWatching();
                return;
            }
            return;
        }
        if (z) {
            BydLogHelper.logD("obFile = " + this.f3209b.getPath() + ", CREATE file add = " + this.f3208a.add(str) + ", mPathList.size() = " + this.f3208a.size());
            if (this.f3208a.size() >= 30) {
                Intent intent = new Intent(BydLogApplication.d(), (Class<?>) BYDLogService.class);
                intent.setAction("action.auto.save.log.anyway");
                BydLogApplication.d().startService(intent);
                this.f3208a.clear();
            }
        }
    }
}

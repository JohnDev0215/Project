package com.byd.filemanager.task;

import android.text.TextUtils;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.e;
import com.byd.filemanager.k.b;
import com.byd.filemanager.k.l;
import d.a.i;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LoadPathTask extends FileOperateTask {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final List<String> f3668e;
    private String f;

    static {
        ArrayList arrayList = new ArrayList();
        f3668e = arrayList;
        String strL = e.f().l(0);
        arrayList.add(strL + "/log");
        arrayList.add(strL + "/logs");
        arrayList.add(strL + "/upLogs");
        arrayList.add(strL + "/blockcanary");
    }

    public LoadPathTask(String str) {
        this.f = str;
    }

    @Override // com.byd.filemanager.task.FileOperateTask
    public void n(i<? super Response> iVar) {
        List<FileItem> listR;
        try {
            listR = r();
        } catch (Throwable th) {
            th = th;
            listR = null;
        }
        try {
            b.b("queryFiles size = " + listR.size());
            if (p()) {
                iVar.onNext(new Response(3, listR));
            } else {
                iVar.onNext(new Response(1, listR));
            }
        } catch (Throwable th2) {
            th = th2;
            b.d("queryFiles error", th);
            iVar.onNext(new Response(4, listR));
        }
    }

    public List<FileItem> r() {
        File[] fileArrListFiles;
        b.b("queryFiles path = " + this.f);
        ArrayList arrayList = new ArrayList();
        try {
            if (!TextUtils.isEmpty(this.f) && (fileArrListFiles = new File(this.f).listFiles()) != null && fileArrListFiles.length > 0) {
                for (File file : fileArrListFiles) {
                    if (p()) {
                        break;
                    }
                    if (file == null || !f3668e.contains(file.getAbsolutePath())) {
                        FileItem fileItem = new FileItem(file);
                        fileItem.I();
                        arrayList.add(fileItem);
                    }
                }
                l.e(arrayList);
            }
        } catch (Throwable th) {
            b.d("queryFiles error", th);
        }
        b.m("queryFiles size=" + arrayList.size());
        return arrayList;
    }
}

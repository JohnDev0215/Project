package com.byd.filemanager.task;

import android.database.Cursor;
import android.text.TextUtils;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.e;
import com.byd.filemanager.k.b;
import com.byd.filemanager.k.d;
import com.byd.filemanager.k.f;
import d.a.i;

/* JADX INFO: loaded from: classes.dex */
public class FindTask extends LoadContentProviderTask {
    private String g;

    public FindTask(String str) {
        this.g = str;
    }

    @Override // com.byd.filemanager.task.LoadContentProviderTask, com.byd.filemanager.task.FileOperateTask
    public void n(i<? super Response> iVar) throws Throwable {
        x(v());
    }

    @Override // com.byd.filemanager.task.LoadContentProviderTask
    protected Request v() {
        return Request.h(this.g);
    }

    protected void x(Request request) throws Throwable {
        String str;
        boolean z = false;
        try {
            Cursor cursorT = t(request.p(), request.m(), request.n(), request.k());
            if (cursorT != null) {
                while (cursorT.moveToNext()) {
                    try {
                        if (p()) {
                            this.f3664b.onNext(new Response(3, (FileItem) null));
                            cursorT.close();
                            return;
                        }
                        String strS = s(cursorT, "_data");
                        if (d.h(strS)) {
                            try {
                                FileItem fileItemZ = FileItem.z(strS);
                                fileItemZ.W(e.f().C(fileItemZ.E()));
                                this.f3664b.onNext(new Response(2, fileItemZ));
                                z = true;
                            } catch (Throwable th) {
                                th = th;
                                z = true;
                                try {
                                    cursorT.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                if (z && (str = this.g) != null && !TextUtils.isEmpty(str.trim())) {
                    f.a(this.g);
                }
                this.f3664b.onNext(new Response(1, (FileItem) null));
            }
            if (cursorT != null) {
                cursorT.close();
            }
        } catch (Exception e2) {
            b.d("cursor error", e2);
            if (z) {
                f.a(this.g);
            }
            this.f3664b.onNext(new Response(4, (FileItem) null));
        }
    }
}

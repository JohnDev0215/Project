package com.byd.filemanager.task;

import com.byd.filemanager.FileApplication;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileType;
import com.byd.filemanager.e;
import d.a.i;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LoadDownloadTask extends LoadContentProviderTask {
    private static final FileType[] g = {FileType.DOCUMENT, FileType.AUDIO, FileType.VIDEO, FileType.IMAGE, FileType.COMPRESS, FileType.APK};
    private FileType h;

    public LoadDownloadTask(int i, FileType fileType) {
        this.h = fileType;
        this.f = e.f().l(0);
    }

    private List<FileItem> x() {
        ArrayList arrayList = new ArrayList();
        String[] stringArray = FileApplication.a().getResources().getStringArray(R.array.downloads);
        for (int i = 0; i < stringArray.length; i++) {
            FileItem fileItemY = FileItem.y(stringArray[i]);
            fileItemY.V("/" + stringArray[i]);
            fileItemY.X(FileType.DOWNLOAD_DIRECTORY);
            fileItemY.Q(g[i]);
            arrayList.add(fileItemY);
        }
        return arrayList;
    }

    @Override // com.byd.filemanager.task.LoadContentProviderTask, com.byd.filemanager.task.FileOperateTask
    public void n(i<? super Response> iVar) {
        Request requestV = v();
        if (requestV == null) {
            iVar.onNext(new Response(4, (List<FileItem>) null));
            return;
        }
        List<FileItem> listU = u(requestV);
        if (p()) {
            iVar.onNext(new Response(3, (List<FileItem>) null));
            return;
        }
        if (listU == null) {
            iVar.onNext(new Response(4, (List<FileItem>) null));
            return;
        }
        if (this.h == FileType.OTHER) {
            List<FileItem> listX = x();
            listX.addAll(listU);
            listU = listX;
        }
        iVar.onNext(new Response(1, listU));
    }

    @Override // com.byd.filemanager.task.LoadContentProviderTask
    protected Request v() {
        return Request.g(this.f, this.h);
    }
}

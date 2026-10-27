package com.byd.filemanager.service;

import android.app.IntentService;
import android.content.Intent;
import com.byd.filemanager.k.d;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class FileOpenService extends IntentService {
    public FileOpenService() {
        super("FileOpenService");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        File file;
        if (intent == null || (file = (File) intent.getSerializableExtra("file")) == null) {
            return;
        }
        d.K(this, file);
    }
}

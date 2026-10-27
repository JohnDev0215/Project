package p;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.play.core.common.LocalTestingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class t13 implements d33 {
    public static final tz2 f = new tz2("FakeAssetPackService", 0);
    public final String a;
    public final com.google.android.play.core.assetpacks.b b;
    public final b23 c;
    public final f13 d;
    public final Handler e = new Handler(Looper.getMainLooper());

    static {
        new AtomicInteger(1);
    }

    public t13(File file, com.google.android.play.core.assetpacks.b bVar, Context context, b23 b23Var, f13 f13Var) {
        this.a = file.getAbsolutePath();
        this.b = bVar;
        this.c = b23Var;
        this.d = f13Var;
    }

    @Override // p.d33
    public final void a(int i) {
        f.e("notifySessionFailed", new Object[0]);
    }

    @Override // p.d33
    public final wc0 b(HashMap map) {
        f.e("syncPacks()", new Object[0]);
        ArrayList arrayList = new ArrayList();
        wc0 wc0Var = new wc0(3);
        wc0Var.h(arrayList);
        return wc0Var;
    }

    @Override // p.d33
    public final wc0 c(int i, String str, int i2, String str2) {
        int i3;
        Object[] objArr = {Integer.valueOf(i), str, str2, Integer.valueOf(i2)};
        tz2 tz2Var = f;
        tz2Var.e("getChunkFileDescriptor(session=%d, %s, %s, %d)", objArr);
        wc0 wc0Var = new wc0(3);
        try {
        } catch (LocalTestingException e) {
            tz2Var.f("getChunkFileDescriptor failed", e);
            wc0Var.g(e);
        } catch (FileNotFoundException e2) {
            tz2Var.f("getChunkFileDescriptor failed", e2);
            wc0Var.g(new LocalTestingException("Asset Slice file not found.", e2));
        }
        for (File file : i(str)) {
            if (f60.W(file).equals(str2)) {
                wc0Var.h(ParcelFileDescriptor.open(file, 268435456));
                return wc0Var;
            }
        }
        throw new LocalTestingException(String.format("Local testing slice for '%s' not found.", str2));
    }

    @Override // p.d33
    public final void d(int i, String str) {
        f.e("notifyModuleCompleted", new Object[0]);
        ((Executor) ((h13) this.d).a()).execute(new a52(this, i, str));
    }

    @Override // p.d33
    public final void e() {
        f.e("keepAlive", new Object[0]);
    }

    @Override // p.d33
    public final void f(List list) {
        f.e("cancelDownload(%s)", list);
    }

    @Override // p.d33
    public final void g(int i, String str, int i2, String str2) {
        f.e("notifyChunkTransferred", new Object[0]);
    }

    public final void h(String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("app_version_code", this.c.a());
        bundle.putInt("session_id", i);
        File[] fileArrI = i(str);
        ArrayList<String> arrayList = new ArrayList<>();
        long length = 0;
        for (File file : fileArrI) {
            length += file.length();
            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
            arrayList2.add(null);
            String strW = f60.W(file);
            bundle.putParcelableArrayList(l13.v("chunk_intents", str, strW), arrayList2);
            try {
                bundle.putString(l13.v("uncompressed_hash_sha256", str, strW), he1.b0(Arrays.asList(file)));
                bundle.putLong(l13.v("uncompressed_size", str, strW), file.length());
                arrayList.add(strW);
            } catch (IOException e) {
                throw new LocalTestingException(String.format("Could not digest file: %s.", file), e);
            } catch (NoSuchAlgorithmException e2) {
                throw new LocalTestingException("SHA256 algorithm not supported.", e2);
            }
        }
        bundle.putStringArrayList(l13.u("slice_ids", str), arrayList);
        bundle.putLong(l13.u("pack_version", str), r1.a());
        bundle.putInt(l13.u("status", str), 4);
        bundle.putInt(l13.u("error_code", str), 0);
        bundle.putLong(l13.u("bytes_downloaded", str), length);
        bundle.putLong(l13.u("total_bytes_to_download", str), length);
        bundle.putStringArrayList("pack_names", new ArrayList<>(Arrays.asList(str)));
        bundle.putLong("bytes_downloaded", length);
        bundle.putLong("total_bytes_to_download", length);
        this.e.post(new yf1(this, new Intent("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE").putExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE", bundle), 25));
    }

    public final File[] i(final String str) throws LocalTestingException {
        File file = new File(this.a);
        if (!file.isDirectory()) {
            throw new LocalTestingException(String.format("Local testing directory '%s' not found.", file));
        }
        File[] fileArrListFiles = file.listFiles(new FilenameFilter() { // from class: p.s13
            @Override // java.io.FilenameFilter
            public final boolean accept(File file2, String str2) {
                return str2.startsWith(String.valueOf(str).concat("-")) && str2.endsWith(".apk");
            }
        });
        if (fileArrListFiles == null) {
            throw new LocalTestingException(String.format("Failed fetching APKs for pack '%s'.", str));
        }
        if (fileArrListFiles.length == 0) {
            throw new LocalTestingException(String.format("No APKs available for pack '%s'.", str));
        }
        for (File file2 : fileArrListFiles) {
            if (f60.W(file2).equals(str)) {
                return fileArrListFiles;
            }
        }
        throw new LocalTestingException(String.format("No main slice available for pack '%s'.", str));
    }
}

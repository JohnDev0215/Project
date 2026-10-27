package spi;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.annotation.NonNull;
import java.io.InputStream;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class gn<Data> implements ti<Integer, Data> {
    public final ti<Uri, Data> a;
    public final Resources b;

    public static final class a implements ui<Integer, AssetFileDescriptor> {
        public final Resources a;

        public a(Resources resources) {
            this.a = resources;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        public final ti<Integer, AssetFileDescriptor> c(aj ajVar) {
            return new gn(this.a, ajVar.c(Uri.class, AssetFileDescriptor.class));
        }
    }

    public static class b implements ui<Integer, ParcelFileDescriptor> {
        public final Resources a;

        public b(Resources resources) {
            this.a = resources;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<Integer, ParcelFileDescriptor> c(aj ajVar) {
            return new gn(this.a, ajVar.c(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public static class c implements ui<Integer, InputStream> {
        public final Resources a;

        public c(Resources resources) {
            this.a = resources;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<Integer, InputStream> c(aj ajVar) {
            return new gn(this.a, ajVar.c(Uri.class, InputStream.class));
        }
    }

    public static class d implements ui<Integer, Uri> {
        public final Resources a;

        public d(Resources resources) {
            this.a = resources;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<Integer, Uri> c(aj ajVar) {
            return new gn(this.a, bu.a);
        }
    }

    public gn(Resources resources, ti<Uri, Data> tiVar) {
        this.b = resources;
        this.a = tiVar;
    }

    @Override // spi.ti
    public final /* bridge */ /* synthetic */ boolean a(@NonNull Integer num) {
        return true;
    }

    @Override // spi.ti
    public final ti.a b(@NonNull Integer num, int i, int i2, @NonNull lk lkVar) {
        Uri uri;
        Integer num2 = num;
        try {
            uri = Uri.parse("android.resource://" + this.b.getResourcePackageName(num2.intValue()) + '/' + this.b.getResourceTypeName(num2.intValue()) + '/' + this.b.getResourceEntryName(num2.intValue()));
        } catch (Resources.NotFoundException e) {
            if (Log.isLoggable("ResourceLoader", 5)) {
                Log.w("ResourceLoader", "Received invalid resource id: " + num2, e);
            }
            uri = null;
        }
        if (uri == null) {
            return null;
        }
        return this.a.b(uri, i, i2, lkVar);
    }
}

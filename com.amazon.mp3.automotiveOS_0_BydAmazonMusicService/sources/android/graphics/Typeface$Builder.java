package android.graphics;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.content.res.AssetManager;
import java.io.FileDescriptor;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Typeface$Builder {
    static {
        throw new NoClassDefFoundError();
    }

    public /* synthetic */ Typeface$Builder(@NonNull AssetManager assetManager, @NonNull String str) {
    }

    public /* synthetic */ Typeface$Builder(@NonNull FileDescriptor fileDescriptor) {
    }

    public native /* synthetic */ Typeface build();

    public native /* synthetic */ Typeface$Builder setFontVariationSettings(@Nullable String str);

    public native /* synthetic */ Typeface$Builder setItalic(boolean z);

    public native /* synthetic */ Typeface$Builder setWeight(int i);
}

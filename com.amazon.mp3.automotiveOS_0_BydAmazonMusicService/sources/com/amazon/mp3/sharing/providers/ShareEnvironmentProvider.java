package com.amazon.mp3.sharing.providers;

import android.content.Context;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.share.menu.providers.EnvironmentProvider;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShareEnvironmentProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/sharing/providers/ShareEnvironmentProvider;", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "authority", "", "fileProviderAuthority", "getFileProviderAuthority", "()Ljava/lang/String;", "fileStoragePath", "Ljava/io/File;", "getFileStoragePath", "()Ljava/io/File;", "storageRoot", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ShareEnvironmentProvider implements EnvironmentProvider {
    private final String authority;
    private final File storageRoot;

    public ShareEnvironmentProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.dmusic_manifest_file_provider);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…c_manifest_file_provider)");
        this.authority = string;
        File file = new File(context.getCacheDir(), "share");
        this.storageRoot = file;
        if (file.exists()) {
            return;
        }
        file.mkdir();
    }

    @Override // com.amazon.music.share.menu.providers.EnvironmentProvider
    /* JADX INFO: renamed from: getFileProviderAuthority, reason: from getter */
    public String getAuthority() {
        return this.authority;
    }

    @Override // com.amazon.music.share.menu.providers.EnvironmentProvider
    /* JADX INFO: renamed from: getFileStoragePath, reason: from getter */
    public File getStorageRoot() {
        return this.storageRoot;
    }
}

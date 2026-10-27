package p;

import com.spotify.localfiles.localfiles.LocalFilesApi;
import com.spotify.localfiles.localfilesimpl.NativeLocalFilesDelegate;

/* JADX INFO: loaded from: classes.dex */
public final class b10 implements LocalFilesApi {
    @Override // com.spotify.localfiles.localfiles.LocalFilesApi
    public final NativeLocalFilesDelegate getNativeLocalFilesDelegate() {
        return NativeLocalFilesDelegate.Companion.noop();
    }
}

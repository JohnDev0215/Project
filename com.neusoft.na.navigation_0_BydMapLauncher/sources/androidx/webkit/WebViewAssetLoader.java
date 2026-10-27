package androidx.webkit;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebResourceResponse;
import androidx.webkit.internal.AssetHelper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class WebViewAssetLoader {
    public static final String DEFAULT_DOMAIN = "appassets.androidplatform.net";
    private static final String TAG = "WebViewAssetLoader";
    private final List<PathMatcher> mMatchers;

    public interface PathHandler {
        WebResourceResponse handle(String str);
    }

    public static final class AssetsPathHandler implements PathHandler {
        private AssetHelper mAssetHelper;

        public AssetsPathHandler(Context context) {
            this.mAssetHelper = new AssetHelper(context);
        }

        AssetsPathHandler(AssetHelper assetHelper) {
            this.mAssetHelper = assetHelper;
        }

        @Override // androidx.webkit.WebViewAssetLoader.PathHandler
        public WebResourceResponse handle(String str) {
            return new WebResourceResponse(AssetHelper.guessMimeType(str), null, this.mAssetHelper.openAsset(str));
        }
    }

    public static final class ResourcesPathHandler implements PathHandler {
        private AssetHelper mAssetHelper;

        public ResourcesPathHandler(Context context) {
            this.mAssetHelper = new AssetHelper(context);
        }

        ResourcesPathHandler(AssetHelper assetHelper) {
            this.mAssetHelper = assetHelper;
        }

        @Override // androidx.webkit.WebViewAssetLoader.PathHandler
        public WebResourceResponse handle(String str) {
            return new WebResourceResponse(AssetHelper.guessMimeType(str), null, this.mAssetHelper.openResource(str));
        }
    }

    public static final class InternalStoragePathHandler implements PathHandler {
        private static final String[] FORBIDDEN_DATA_DIRS = {"app_webview/", "databases/", "lib/", "shared_prefs/", "code_cache/"};
        private final File mDirectory;

        public InternalStoragePathHandler(Context context, File file) {
            if (!isAllowedInternalStorageDir(context, file)) {
                throw new IllegalArgumentException("The given directory \"" + file + "\" doesn't exist under an allowed app internal storage directory");
            }
            this.mDirectory = file;
        }

        private static boolean isAllowedInternalStorageDir(Context context, File file) {
            try {
                String canonicalPath = AssetHelper.getCanonicalPath(file);
                String canonicalPath2 = AssetHelper.getCanonicalPath(context.getCacheDir());
                String canonicalPath3 = AssetHelper.getCanonicalPath(AssetHelper.getDataDir(context));
                if ((canonicalPath.startsWith(canonicalPath2) || canonicalPath.startsWith(canonicalPath3)) && !canonicalPath.equals(canonicalPath2) && !canonicalPath.equals(canonicalPath3)) {
                    for (String str : FORBIDDEN_DATA_DIRS) {
                        if (canonicalPath.startsWith(canonicalPath3 + str)) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (IOException unused) {
            }
            return false;
        }

        @Override // androidx.webkit.WebViewAssetLoader.PathHandler
        public WebResourceResponse handle(String str) {
            InputStream inputStreamOpenFile;
            File file = new File(this.mDirectory, str);
            if (AssetHelper.isCanonicalChildOf(this.mDirectory, file)) {
                inputStreamOpenFile = AssetHelper.openFile(file);
            } else {
                Log.e(WebViewAssetLoader.TAG, "The requested file: " + str + " is outside the mounted directory: " + this.mDirectory);
                inputStreamOpenFile = null;
            }
            return new WebResourceResponse(AssetHelper.guessMimeType(str), null, inputStreamOpenFile);
        }
    }

    static class PathMatcher {
        static final String HTTPS_SCHEME = "https";
        static final String HTTP_SCHEME = "http";
        final String mAuthority;
        final PathHandler mHandler;
        final boolean mHttpEnabled;
        final String mPath;

        PathMatcher(String str, String str2, boolean z, PathHandler pathHandler) {
            if (str2.isEmpty() || str2.charAt(0) != '/') {
                throw new IllegalArgumentException("Path should start with a slash '/'.");
            }
            if (!str2.endsWith("/")) {
                throw new IllegalArgumentException("Path should end with a slash '/'");
            }
            this.mAuthority = str;
            this.mPath = str2;
            this.mHttpEnabled = z;
            this.mHandler = pathHandler;
        }

        public PathHandler match(Uri uri) {
            if (uri.getScheme().equals("http") && !this.mHttpEnabled) {
                return null;
            }
            if ((uri.getScheme().equals("http") || uri.getScheme().equals("https")) && uri.getAuthority().equals(this.mAuthority) && uri.getPath().startsWith(this.mPath)) {
                return this.mHandler;
            }
            return null;
        }

        public String getSuffixPath(String str) {
            return str.replaceFirst(this.mPath, "");
        }
    }

    public static final class Builder {
        private boolean mHttpAllowed = false;
        private String mDomain = WebViewAssetLoader.DEFAULT_DOMAIN;
        private List<PathMatcher> mBuilderMatcherList = new ArrayList();

        public Builder setDomain(String str) {
            this.mDomain = str;
            return this;
        }

        public Builder setHttpAllowed(boolean z) {
            this.mHttpAllowed = z;
            return this;
        }

        public Builder addPathHandler(String str, PathHandler pathHandler) {
            this.mBuilderMatcherList.add(new PathMatcher(this.mDomain, str, this.mHttpAllowed, pathHandler));
            return this;
        }

        public WebViewAssetLoader build() {
            return new WebViewAssetLoader(this.mBuilderMatcherList);
        }
    }

    WebViewAssetLoader(List<PathMatcher> list) {
        this.mMatchers = list;
    }

    public WebResourceResponse shouldInterceptRequest(Uri uri) {
        WebResourceResponse webResourceResponseHandle;
        for (PathMatcher pathMatcher : this.mMatchers) {
            PathHandler pathHandlerMatch = pathMatcher.match(uri);
            if (pathHandlerMatch != null && (webResourceResponseHandle = pathHandlerMatch.handle(pathMatcher.getSuffixPath(uri.getPath()))) != null) {
                return webResourceResponseHandle;
            }
        }
        return null;
    }
}

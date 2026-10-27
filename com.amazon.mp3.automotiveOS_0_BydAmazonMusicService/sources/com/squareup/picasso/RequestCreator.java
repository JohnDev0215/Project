package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes7.dex */
public class RequestCreator {
    private static final AtomicInteger nextId = new AtomicInteger();
    private final Request.Builder data;
    private boolean deferred;
    private Drawable errorDrawable;
    private int errorResId;
    private int memoryPolicy;
    private int networkPolicy;
    private boolean noFade;
    private final Picasso picasso;
    private Drawable placeholderDrawable;
    private int placeholderResId;
    private boolean setPlaceholder;
    private Object tag;

    RequestCreator(Picasso picasso, Uri uri, int i) {
        this.setPlaceholder = true;
        if (picasso.shutdown) {
            throw new IllegalStateException("Picasso instance already shut down. Cannot submit new requests.");
        }
        this.picasso = picasso;
        this.data = new Request.Builder(uri, i, picasso.defaultBitmapConfig);
    }

    RequestCreator() {
        this.setPlaceholder = true;
        this.picasso = null;
        this.data = new Request.Builder(null, 0, null);
    }

    public RequestCreator placeholder(int i) {
        if (!this.setPlaceholder) {
            throw new IllegalStateException("Already explicitly declared as no placeholder.");
        }
        if (i == 0) {
            throw new IllegalArgumentException("Placeholder image resource invalid.");
        }
        if (this.placeholderDrawable != null) {
            throw new IllegalStateException("Placeholder image already set.");
        }
        this.placeholderResId = i;
        return this;
    }

    public RequestCreator placeholder(Drawable drawable) {
        if (!this.setPlaceholder) {
            throw new IllegalStateException("Already explicitly declared as no placeholder.");
        }
        if (this.placeholderResId != 0) {
            throw new IllegalStateException("Placeholder image already set.");
        }
        this.placeholderDrawable = drawable;
        return this;
    }

    public RequestCreator error(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Error image resource invalid.");
        }
        if (this.errorDrawable != null) {
            throw new IllegalStateException("Error image already set.");
        }
        this.errorResId = i;
        return this;
    }

    public RequestCreator tag(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Tag invalid.");
        }
        if (this.tag != null) {
            throw new IllegalStateException("Tag already set.");
        }
        this.tag = obj;
        return this;
    }

    public RequestCreator fit() {
        this.deferred = true;
        return this;
    }

    RequestCreator unfit() {
        this.deferred = false;
        return this;
    }

    RequestCreator clearTag() {
        this.tag = null;
        return this;
    }

    Object getTag() {
        return this.tag;
    }

    public RequestCreator resize(int i, int i2) {
        this.data.resize(i, i2);
        return this;
    }

    public RequestCreator centerCrop() {
        this.data.centerCrop(17);
        return this;
    }

    public RequestCreator centerCrop(int i) {
        this.data.centerCrop(i);
        return this;
    }

    public RequestCreator centerInside() {
        this.data.centerInside();
        return this;
    }

    public RequestCreator onlyScaleDown() {
        this.data.onlyScaleDown();
        return this;
    }

    public RequestCreator rotate(float f) {
        this.data.rotate(f);
        return this;
    }

    public RequestCreator transform(Transformation transformation) {
        this.data.transform(transformation);
        return this;
    }

    public RequestCreator networkPolicy(NetworkPolicy networkPolicy, NetworkPolicy... networkPolicyArr) {
        if (networkPolicy == null) {
            throw new IllegalArgumentException("Network policy cannot be null.");
        }
        this.networkPolicy = networkPolicy.index | this.networkPolicy;
        if (networkPolicyArr == null) {
            throw new IllegalArgumentException("Network policy cannot be null.");
        }
        if (networkPolicyArr.length > 0) {
            for (NetworkPolicy networkPolicy2 : networkPolicyArr) {
                if (networkPolicy2 == null) {
                    throw new IllegalArgumentException("Network policy cannot be null.");
                }
                this.networkPolicy = networkPolicy2.index | this.networkPolicy;
            }
        }
        return this;
    }

    public RequestCreator noFade() {
        this.noFade = true;
        return this;
    }

    public Bitmap get() throws IOException {
        long jNanoTime = System.nanoTime();
        Utils.checkNotMain();
        if (this.deferred) {
            throw new IllegalStateException("Fit cannot be used with get.");
        }
        if (!this.data.hasImage()) {
            return null;
        }
        Request requestCreateRequest = createRequest(jNanoTime);
        GetAction getAction = new GetAction(this.picasso, requestCreateRequest, this.memoryPolicy, this.networkPolicy, this.tag, Utils.createKey(requestCreateRequest, new StringBuilder()));
        Picasso picasso = this.picasso;
        return BitmapHunter.forRequest(picasso, picasso.dispatcher, picasso.cache, picasso.stats, getAction).hunt();
    }

    public void fetch() {
        fetch(null);
    }

    public void fetch(Callback callback) {
        long jNanoTime = System.nanoTime();
        if (this.deferred) {
            throw new IllegalStateException("Fit cannot be used with fetch.");
        }
        if (this.data.hasImage()) {
            if (!this.data.hasPriority()) {
                this.data.priority(Picasso.Priority.LOW);
            }
            Request requestCreateRequest = createRequest(jNanoTime);
            String strCreateKey = Utils.createKey(requestCreateRequest, new StringBuilder());
            if (MemoryPolicy.shouldReadFromMemoryCache(this.memoryPolicy) && this.picasso.quickMemoryCacheCheck(strCreateKey) != null) {
                if (this.picasso.loggingEnabled) {
                    Utils.log("Main", "completed", requestCreateRequest.plainId(), "from " + Picasso.LoadedFrom.MEMORY);
                }
                if (callback != null) {
                    callback.onSuccess();
                    return;
                }
                return;
            }
            this.picasso.submit(new FetchAction(this.picasso, requestCreateRequest, this.memoryPolicy, this.networkPolicy, this.tag, strCreateKey, callback));
        }
    }

    public void into(Target target) {
        Bitmap bitmapQuickMemoryCacheCheck;
        long jNanoTime = System.nanoTime();
        Utils.checkMain();
        if (target == null) {
            throw new IllegalArgumentException("Target must not be null.");
        }
        if (this.deferred) {
            throw new IllegalStateException("Fit cannot be used with a Target.");
        }
        if (!this.data.hasImage()) {
            this.picasso.cancelRequest(target);
            target.onPrepareLoad(this.setPlaceholder ? getPlaceholderDrawable() : null);
            return;
        }
        Request requestCreateRequest = createRequest(jNanoTime);
        String strCreateKey = Utils.createKey(requestCreateRequest);
        if (MemoryPolicy.shouldReadFromMemoryCache(this.memoryPolicy) && (bitmapQuickMemoryCacheCheck = this.picasso.quickMemoryCacheCheck(strCreateKey)) != null) {
            this.picasso.cancelRequest(target);
            target.onBitmapLoaded(bitmapQuickMemoryCacheCheck, Picasso.LoadedFrom.MEMORY);
        } else {
            target.onPrepareLoad(this.setPlaceholder ? getPlaceholderDrawable() : null);
            this.picasso.enqueueAndSubmit(new TargetAction(this.picasso, target, requestCreateRequest, this.memoryPolicy, this.networkPolicy, this.errorDrawable, strCreateKey, this.tag, this.errorResId));
        }
    }

    public void into(ImageView imageView) {
        into(imageView, null);
    }

    public void into(ImageView imageView, Callback callback) {
        Bitmap bitmapQuickMemoryCacheCheck;
        long jNanoTime = System.nanoTime();
        Utils.checkMain();
        if (imageView == null) {
            throw new IllegalArgumentException("Target must not be null.");
        }
        if (!this.data.hasImage()) {
            this.picasso.cancelRequest(imageView);
            if (this.setPlaceholder) {
                PicassoDrawable.setPlaceholder(imageView, getPlaceholderDrawable());
                return;
            }
            return;
        }
        if (this.deferred) {
            if (this.data.hasSize()) {
                throw new IllegalStateException("Fit cannot be used with resize.");
            }
            int width = imageView.getWidth();
            int height = imageView.getHeight();
            if (width == 0 || height == 0) {
                if (this.setPlaceholder) {
                    PicassoDrawable.setPlaceholder(imageView, getPlaceholderDrawable());
                }
                this.picasso.defer(imageView, new DeferredRequestCreator(this, imageView, callback));
                return;
            }
            this.data.resize(width, height);
        }
        Request requestCreateRequest = createRequest(jNanoTime);
        String strCreateKey = Utils.createKey(requestCreateRequest);
        if (MemoryPolicy.shouldReadFromMemoryCache(this.memoryPolicy) && (bitmapQuickMemoryCacheCheck = this.picasso.quickMemoryCacheCheck(strCreateKey)) != null) {
            this.picasso.cancelRequest(imageView);
            Picasso picasso = this.picasso;
            Context context = picasso.context;
            Picasso.LoadedFrom loadedFrom = Picasso.LoadedFrom.MEMORY;
            PicassoDrawable.setBitmap(imageView, context, bitmapQuickMemoryCacheCheck, loadedFrom, this.noFade, picasso.indicatorsEnabled);
            if (this.picasso.loggingEnabled) {
                Utils.log("Main", "completed", requestCreateRequest.plainId(), "from " + loadedFrom);
            }
            if (callback != null) {
                callback.onSuccess();
                return;
            }
            return;
        }
        if (this.setPlaceholder) {
            PicassoDrawable.setPlaceholder(imageView, getPlaceholderDrawable());
        }
        this.picasso.enqueueAndSubmit(new ImageViewAction(this.picasso, imageView, requestCreateRequest, this.memoryPolicy, this.networkPolicy, this.errorResId, this.errorDrawable, strCreateKey, this.tag, callback, this.noFade));
    }

    private Drawable getPlaceholderDrawable() {
        int i = this.placeholderResId;
        if (i != 0) {
            return this.picasso.context.getDrawable(i);
        }
        return this.placeholderDrawable;
    }

    private Request createRequest(long j) {
        int andIncrement = nextId.getAndIncrement();
        Request requestBuild = this.data.build();
        requestBuild.id = andIncrement;
        requestBuild.started = j;
        boolean z = this.picasso.loggingEnabled;
        if (z) {
            Utils.log("Main", "created", requestBuild.plainId(), requestBuild.toString());
        }
        Request requestTransformRequest = this.picasso.transformRequest(requestBuild);
        if (requestTransformRequest != requestBuild) {
            requestTransformRequest.id = andIncrement;
            requestTransformRequest.started = j;
            if (z) {
                Utils.log("Main", "changed", requestTransformRequest.logId(), "into " + requestTransformRequest);
            }
        }
        return requestTransformRequest;
    }
}

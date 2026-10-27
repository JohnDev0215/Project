package org.chromium.ui.resources.async;

import android.os.AsyncTask;
import android.util.SparseArray;
import java.util.concurrent.ExecutionException;
import org.chromium.base.TraceEvent;
import org.chromium.ui.resources.Resource;
import org.chromium.ui.resources.ResourceLoader;

/* JADX INFO: loaded from: classes4.dex */
public class AsyncPreloadResourceLoader extends ResourceLoader {
    private final ResourceCreator mCreator;
    private final SparseArray<AsyncLoadTask> mOutstandingLoads;

    public interface ResourceCreator {
        Resource create(int i);
    }

    public AsyncPreloadResourceLoader(int i, ResourceLoader.ResourceLoaderCallback resourceLoaderCallback, ResourceCreator resourceCreator) {
        super(i, resourceLoaderCallback);
        this.mOutstandingLoads = new SparseArray<>();
        this.mCreator = resourceCreator;
    }

    @Override // org.chromium.ui.resources.ResourceLoader
    public void loadResource(int i) {
        AsyncLoadTask asyncLoadTask = this.mOutstandingLoads.get(i);
        if (asyncLoadTask != null && !asyncLoadTask.cancel(false)) {
            try {
                registerResource(asyncLoadTask.get(), i);
                return;
            } catch (InterruptedException unused) {
                notifyLoadFinished(i, null);
                return;
            } catch (ExecutionException unused2) {
                notifyLoadFinished(i, null);
                return;
            }
        }
        registerResource(createResource(i), i);
    }

    @Override // org.chromium.ui.resources.ResourceLoader
    public void preloadResource(int i) {
        if (this.mOutstandingLoads.get(i) != null) {
            return;
        }
        AsyncLoadTask asyncLoadTask = new AsyncLoadTask(i);
        asyncLoadTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, null);
        this.mOutstandingLoads.put(i, asyncLoadTask);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Resource createResource(int i) {
        try {
            TraceEvent.begin("AsyncPreloadResourceLoader.createResource");
            return this.mCreator.create(i);
        } finally {
            TraceEvent.end("AsyncPreloadResourceLoader.createResource");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerResource(Resource resource, int i) {
        notifyLoadFinished(i, resource);
        if (resource != null) {
            resource.getBitmap().recycle();
        }
        this.mOutstandingLoads.remove(i);
    }

    private class AsyncLoadTask extends AsyncTask<Void, Void, Resource> {
        private final int mResourceId;

        public AsyncLoadTask(int i) {
            this.mResourceId = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Resource doInBackground(Void... voidArr) {
            return AsyncPreloadResourceLoader.this.createResource(this.mResourceId);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Resource resource) {
            if (AsyncPreloadResourceLoader.this.mOutstandingLoads.get(this.mResourceId) == null) {
                return;
            }
            AsyncPreloadResourceLoader.this.registerResource(resource, this.mResourceId);
        }
    }
}

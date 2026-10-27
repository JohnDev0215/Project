package androidx.leanback.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Presenter implements FacetProvider {
    private Map<Class<?>, Object> mFacets;

    public abstract void onBindViewHolder(ViewHolder viewHolder, Object obj);

    public abstract ViewHolder onCreateViewHolder(ViewGroup viewGroup);

    public abstract void onUnbindViewHolder(ViewHolder viewHolder);

    public static class ViewHolder implements FacetProvider {
        private Map<Class<?>, Object> mFacets;
        public final View view;

        public ViewHolder(View view) {
            this.view = view;
        }

        @Override // androidx.leanback.widget.FacetProvider
        public final Object getFacet(Class<?> facetClass) {
            Map<Class<?>, Object> map = this.mFacets;
            if (map == null) {
                return null;
            }
            return map.get(facetClass);
        }

        public final void setFacet(Class<?> facetClass, Object facetImpl) {
            if (this.mFacets == null) {
                this.mFacets = new ArrayMap();
            }
            this.mFacets.put(facetClass, facetImpl);
        }
    }

    public static abstract class ViewHolderTask {
        public void run(ViewHolder holder) {
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, Object item, List<Object> payloads) {
        onBindViewHolder(viewHolder, item);
    }

    public void onViewAttachedToWindow(ViewHolder holder) {
    }

    public void onViewDetachedFromWindow(ViewHolder holder) {
        cancelAnimationsRecursive(holder.view);
    }

    protected static void cancelAnimationsRecursive(View view) {
        if (view != null && view.hasTransientState()) {
            view.animate().cancel();
            if (view instanceof ViewGroup) {
                int count = ((ViewGroup) view).getChildCount();
                for (int i = 0; view.hasTransientState() && i < count; i++) {
                    cancelAnimationsRecursive(((ViewGroup) view).getChildAt(i));
                }
            }
        }
    }

    public void setOnClickListener(ViewHolder holder, View.OnClickListener listener) {
        holder.view.setOnClickListener(listener);
    }

    @Override // androidx.leanback.widget.FacetProvider
    public final Object getFacet(Class<?> facetClass) {
        Map<Class<?>, Object> map = this.mFacets;
        if (map == null) {
            return null;
        }
        return map.get(facetClass);
    }

    public final void setFacet(Class<?> facetClass, Object facetImpl) {
        if (this.mFacets == null) {
            this.mFacets = new ArrayMap();
        }
        this.mFacets.put(facetClass, facetImpl);
    }
}

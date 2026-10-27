package com.android.systemui.recents;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.imageloader.AppThumbnail;
import com.android.systemui.recents.RecentItemTouchCallBack;
import com.android.systemui.util.StyleUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RecentAdapter extends RecyclerView.Adapter<ResultViewHolder> implements RecentItemTouchCallBack.OnItemTouchListener, View.OnClickListener {
    private final String TAG = RecentAdapter.class.getSimpleName();
    private final List<RecentTaskBean> list = new ArrayList();
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private OnItemSwipedListener onItemSwipedListener;
    private RecyclerView recyclerView;

    public interface OnItemClickListener {
        void onItemClick(RecyclerView recyclerView, View view, int position, RecentTaskBean bean);

        void onSplitClick(RecentTaskBean recentTaskBean);
    }

    public interface OnItemSwipedListener {
        void onClearFinished();

        void onItemSwipedDown(int position, RecentTaskBean bean);

        void onItemSwipedUp(int position, RecentTaskBean bean, boolean needFinished);
    }

    @Override // com.android.systemui.recents.RecentItemTouchCallBack.OnItemTouchListener
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    }

    @Override // com.android.systemui.recents.RecentItemTouchCallBack.OnItemTouchListener
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
    }

    public RecentAdapter(Context context) {
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View viewInflate = LayoutInflater.from(parent.getContext()).inflate(StyleUtil.getResLayout(R.layout.item_recent_task, "item_recent_task"), parent, false);
        viewInflate.setOnClickListener(this);
        ResultViewHolder resultViewHolder = new ResultViewHolder(viewInflate);
        resultViewHolder.img_lock.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.recents.RecentAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                RecentAdapter.this.swipedDown(RecentAdapter.this.recyclerView.getChildAdapterPosition(viewInflate));
            }
        });
        resultViewHolder.img_split.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.recents.RecentAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (RecentAdapter.this.onItemClickListener != null) {
                    RecentTaskBean recentTaskBean = (RecentTaskBean) RecentAdapter.this.list.get(RecentAdapter.this.recyclerView.getChildAdapterPosition(viewInflate));
                    Log.i(RecentAdapter.this.TAG, "onClick split -- AppName : " + recentTaskBean.getAppName() + " task id : " + recentTaskBean.getTaskId());
                    RecentAdapter.this.onItemClickListener.onSplitClick(recentTaskBean);
                }
            }
        });
        return resultViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ResultViewHolder holder, int position) {
        RecentTaskBean recentTaskBean = this.list.get(position);
        if (recentTaskBean != null) {
            holder.tv_app_name.setText(recentTaskBean.getAppName());
            holder.img_logo.setImageDrawable(recentTaskBean.getAppLogo());
            Glide.with(this.mContext).asBitmap().load((Object) recentTaskBean).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(new LoadingTarget(holder.img_logo, recentTaskBean.getAppName()));
            Glide.with(this.mContext).asBitmap().load((Object) new AppThumbnail(recentTaskBean.getTaskKey())).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(new LoadingTarget(holder.img_thumb, recentTaskBean.getAppName()));
            if (recentTaskBean.isShowLock()) {
                if (recentTaskBean.isLockAnim()) {
                    holder.img_lock.clearAnimation();
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_lock_btn_enlarge);
                    animationLoadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.android.systemui.recents.RecentAdapter.3
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation) {
                            holder.img_lock.setVisibility(0);
                        }
                    });
                    holder.img_lock.startAnimation(animationLoadAnimation);
                } else {
                    holder.img_lock.setVisibility(0);
                }
            } else if (recentTaskBean.isLockAnim()) {
                holder.img_lock.clearAnimation();
                Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_lock_btn_reduce);
                animationLoadAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.android.systemui.recents.RecentAdapter.4
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        holder.img_lock.setVisibility(4);
                    }
                });
                holder.img_lock.startAnimation(animationLoadAnimation2);
            } else {
                holder.img_lock.setVisibility(4);
            }
            if (recentTaskBean.isShowSplit()) {
                holder.img_split.setVisibility(0);
            } else {
                holder.img_split.setVisibility(8);
            }
            if (recentTaskBean.isRemoveAnim()) {
                if (recentTaskBean.isShowLock()) {
                    if (position % 2 == 0) {
                        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.recents.RecentAdapter.5
                            @Override // java.lang.Runnable
                            public void run() {
                                holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_item_remove_recent_down));
                            }
                        }, 100L);
                        return;
                    } else {
                        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.recents.RecentAdapter.6
                            @Override // java.lang.Runnable
                            public void run() {
                                holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_item_remove_recent_down));
                            }
                        }, 150L);
                        return;
                    }
                }
                if (position % 2 == 0) {
                    holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_item_remove_recent_up));
                    return;
                } else {
                    ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.recents.RecentAdapter.7
                        @Override // java.lang.Runnable
                        public void run() {
                            holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_item_remove_recent_up));
                        }
                    }, 50L);
                    return;
                }
            }
            if (recentTaskBean.isAddAnim()) {
                return;
            }
            recentTaskBean.setAddAnim(true);
            if (position % 2 == 0) {
                holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_item_in));
            } else {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.recents.RecentAdapter.8
                    @Override // java.lang.Runnable
                    public void run() {
                        holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_item_in));
                    }
                }, 50L);
            }
        }
    }

    public void refreshList(List<RecentTaskBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addList(List<RecentTaskBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public List<RecentTaskBean> getList() {
        return this.list;
    }

    public void clear() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void clearRecent() {
        ArrayList arrayList = new ArrayList();
        int size = this.list.size();
        for (int i = 0; i < size; i++) {
            this.list.get(i).setRemoveAnim(true);
            this.list.get(i).setLockAnim(false);
            if (!this.list.get(i).isShowLock()) {
                arrayList.add(this.list.get(i));
                OnItemSwipedListener onItemSwipedListener = this.onItemSwipedListener;
                if (onItemSwipedListener != null) {
                    onItemSwipedListener.onItemSwipedUp(i, this.list.get(i), false);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    @Override // com.android.systemui.recents.RecentItemTouchCallBack.OnItemTouchListener
    public void onMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            int i = fromPosition;
            while (i < toPosition) {
                int i2 = i + 1;
                Collections.swap(this.list, i, i2);
                i = i2;
            }
        } else {
            for (int i3 = fromPosition; i3 > toPosition; i3--) {
                Collections.swap(this.list, i3, i3 - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override // com.android.systemui.recents.RecentItemTouchCallBack.OnItemTouchListener
    public void onSwiped(int position, int direction) {
        Log.i(this.TAG, "onSwiped--position=" + position + ",direction=" + direction);
        if (direction == 1) {
            swipedUp(position);
        } else if (direction == 2) {
            swipedDown(position);
        }
    }

    private void swipedUp(int position) {
        RecentTaskBean recentTaskBean = this.list.get(position);
        this.list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.list.size());
        OnItemSwipedListener onItemSwipedListener = this.onItemSwipedListener;
        if (onItemSwipedListener != null) {
            onItemSwipedListener.onItemSwipedUp(position, recentTaskBean, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void swipedDown(int position) {
        this.list.get(position).setLockAnim(true);
        this.list.get(position).setShowLock(true ^ this.list.get(position).isShowLock());
        notifyItemChanged(position);
        OnItemSwipedListener onItemSwipedListener = this.onItemSwipedListener;
        if (onItemSwipedListener != null) {
            onItemSwipedListener.onItemSwipedDown(position, this.list.get(position));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() != R.id.ll_root) {
            return;
        }
        int childAdapterPosition = this.recyclerView.getChildAdapterPosition(v);
        Log.i(this.TAG, "onClick--position=" + childAdapterPosition + ",size=" + this.list.size());
        if (this.onItemClickListener == null || childAdapterPosition < 0 || childAdapterPosition >= this.list.size()) {
            return;
        }
        this.onItemClickListener.onItemClick(this.recyclerView, v, childAdapterPosition, this.list.get(childAdapterPosition));
    }

    protected static class ResultViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img_lock;
        private final ImageView img_logo;
        private final ImageView img_split;
        private final ImageView img_thumb;
        private final TextView tv_app_name;

        public ResultViewHolder(View itemView) {
            super(itemView);
            this.img_logo = (ImageView) itemView.findViewById(R.id.img_logo);
            this.img_lock = (ImageView) itemView.findViewById(R.id.img_lock);
            this.img_split = (ImageView) itemView.findViewById(R.id.img_split);
            this.img_thumb = (ImageView) itemView.findViewById(R.id.img_thumb);
            this.tv_app_name = (TextView) itemView.findViewById(R.id.tv_app_name);
        }
    }

    public OnItemSwipedListener getOnItemSwipedListener() {
        return this.onItemSwipedListener;
    }

    public void setOnItemSwipedListener(OnItemSwipedListener onItemSwipedListener) {
        this.onItemSwipedListener = onItemSwipedListener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    private static class LoadingTarget extends CustomViewTarget<ImageView, Bitmap> {
        private final String mTag;

        @Override // com.bumptech.glide.request.target.CustomViewTarget
        protected void onResourceCleared(Drawable placeholder) {
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(Object resource, Transition transition) {
            onResourceReady((Bitmap) resource, (Transition<? super Bitmap>) transition);
        }

        public LoadingTarget(ImageView view, String tag) {
            super(view);
            this.mTag = tag;
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadFailed(Drawable errorDrawable) {
            BydLogHelper.logD("onLoadFailed");
        }

        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
            String str = (String) ((ImageView) this.view).getTag();
            if (str == null || str.equals(this.mTag)) {
                try {
                    ((ImageView) this.view).setImageBitmap(resource);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }
}

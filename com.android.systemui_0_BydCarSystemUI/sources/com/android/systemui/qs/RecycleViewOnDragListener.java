package com.android.systemui.qs;

import android.os.SystemProperties;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Constants;
import com.android.systemui.qs.tileimpl.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.util.SharedPreferencesUtil;
import com.byd.card.bean.Constant;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class RecycleViewOnDragListener implements View.OnDragListener {
    private static final String TAG = "RecycleViewOnDragListener";
    private int handleDragCount;
    private Gson mGson = new Gson();
    private int fromPositionOnDrag = -1;
    private boolean positionFromSameView = true;
    private QSPanelAdapter fomAdapterOnDrag = null;
    private QSPanelAdapter endAdapterOnDrag = null;
    private int endPositionOnDrag = -1;
    private QSPanelAdapter fomAdapterNeedSave = null;
    private QSPanelAdapter endAdapterNeedSave = null;
    private int lastFromPosition = -1;
    private int lastToPosition = -1;
    private boolean isDragOutside = false;
    private int originPosition = -1;

    private void handleStart(DragEvent dragEvent) {
        RecyclerView.ViewHolder viewHolder;
        this.handleDragCount = 0;
        try {
            DragData dragData = (DragData) dragEvent.getLocalState();
            if (dragData == null || (viewHolder = dragData.viewHolder) == null || viewHolder.itemView == null) {
                return;
            }
            viewHolder.itemView.setAlpha(0.8f);
            this.endAdapterOnDrag = (QSPanelAdapter) dragData.recyclerView.getAdapter();
            int adapterPosition = viewHolder.getAdapterPosition();
            this.endPositionOnDrag = adapterPosition;
            this.originPosition = adapterPosition;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View.OnDragListener
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case 1:
                this.isDragOutside = false;
                handleStart(dragEvent);
                SystemProperties.set(Constants.SYS_GESTURE_SYSTEMUI_KEY, "1");
                return true;
            case 2:
                handleDrag(view, dragEvent);
                return true;
            case 3:
                SystemProperties.set(Constants.SYS_GESTURE_SYSTEMUI_KEY, Constant.CarType.CAR_TYPE_FUEL);
                return true;
            case 4:
                SystemProperties.set(Constants.SYS_GESTURE_SYSTEMUI_KEY, Constant.CarType.CAR_TYPE_FUEL);
                preHandleEnded(dragEvent);
                handleEnded(view);
                this.isDragOutside = false;
                return true;
            case 5:
                this.isDragOutside = false;
                return true;
            case 6:
                this.isDragOutside = true;
                return true;
            default:
                return true;
        }
    }

    private void preHandleEnded(DragEvent dragEvent) {
        DragData dragData;
        RecyclerView recyclerView;
        int i;
        BydLogHelper.logD("isDragOutside = " + this.isDragOutside);
        if (!this.isDragOutside || (dragData = (DragData) dragEvent.getLocalState()) == null || (recyclerView = ((DragData) dragEvent.getLocalState()).recyclerView) == null) {
            return;
        }
        handleDrag(recyclerView, dragEvent);
        List<QSTile> data = ((QSPanelAdapter) dragData.recyclerView.getAdapter()).getData();
        int size = data.size();
        if (size <= 1 || (i = this.originPosition) >= size) {
            return;
        }
        data.add(i, data.remove(size - 1));
        ((QSTileImpl) data.get(this.originPosition)).setHide(false);
        dragData.recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void handleEnded(View view) {
        try {
            QSPanelAdapter qSPanelAdapter = this.endAdapterOnDrag;
            if (qSPanelAdapter == null || this.endPositionOnDrag < 0 || qSPanelAdapter.getData().size() <= this.endPositionOnDrag) {
                return;
            }
            ((QSTileImpl) this.endAdapterOnDrag.getData().get(this.endPositionOnDrag)).setHide(false);
            this.endAdapterOnDrag.notifyDataSetChanged();
            this.endAdapterOnDrag = null;
            this.endPositionOnDrag = -1;
            this.fromPositionOnDrag = -1;
            this.lastFromPosition = -1;
            this.lastToPosition = -1;
            this.positionFromSameView = true;
            this.fomAdapterOnDrag = null;
            saveData(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData(View view) {
        String categoryLabel;
        boolean z;
        QSPanelAdapter qSPanelAdapter = this.fomAdapterNeedSave;
        boolean z2 = true;
        if (qSPanelAdapter != null) {
            categoryLabel = qSPanelAdapter.getCategoryLabel();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < qSPanelAdapter.getData().size(); i++) {
                arrayList.add(qSPanelAdapter.getData().get(i).getTileId());
            }
            SharedPreferencesUtil.putString(view.getContext(), qSPanelAdapter.getCategoryId(), this.mGson.toJson(arrayList));
            z = true;
        } else {
            categoryLabel = "";
            z = false;
        }
        QSPanelAdapter qSPanelAdapter2 = this.endAdapterNeedSave;
        if (qSPanelAdapter2 == null || qSPanelAdapter2 == qSPanelAdapter) {
            z2 = z;
        } else {
            categoryLabel = qSPanelAdapter2.getCategoryLabel();
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < qSPanelAdapter2.getData().size(); i2++) {
                arrayList2.add(qSPanelAdapter2.getData().get(i2).getTileId());
            }
            SharedPreferencesUtil.putString(view.getContext(), qSPanelAdapter2.getCategoryId(), this.mGson.toJson(arrayList2));
        }
        if (z2) {
            EventBus.getDefault().post(new QSEditEvent(categoryLabel));
        }
        this.fomAdapterNeedSave = null;
        this.endAdapterNeedSave = null;
    }

    private void handleDrag(View view, DragEvent dragEvent) {
        int childLayoutPosition;
        int i;
        int i2;
        int childLayoutPosition2;
        int i3;
        int i4;
        QSPanelAdapter qSPanelAdapter;
        int height;
        DragData dragData = (DragData) dragEvent.getLocalState();
        boolean z = true;
        int i5 = this.handleDragCount + 1;
        this.handleDragCount = i5;
        if (i5 == 2 && dragData.viewHolder != null) {
            dragData.viewHolder.itemView.setVisibility(4);
        }
        try {
            RecyclerView recyclerView = view instanceof RecyclerView ? (RecyclerView) view : null;
            if (recyclerView == null) {
                Log.d(TAG, "endRecycleView is null");
                return;
            }
            boolean z2 = false;
            if (dragData.viewHolder != null && (height = dragData.viewHolder.itemView.getHeight()) > 0) {
                if (recyclerView.canScrollVertically(-1)) {
                    float f = height;
                    if (dragEvent.getY() - f < 0.0f) {
                        BydLogHelper.logD("endRecycleView auto scroll");
                        recyclerView.smoothScrollBy(0, (int) (dragEvent.getY() - f));
                    }
                }
                if (recyclerView.canScrollVertically(1)) {
                    float f2 = height;
                    if (dragEvent.getY() + f2 > recyclerView.getBottom()) {
                        BydLogHelper.logD("endRecycleView auto scroll");
                        recyclerView.smoothScrollBy(0, (int) ((dragEvent.getY() - recyclerView.getBottom()) + f2));
                    }
                }
            }
            QSPanelAdapter qSPanelAdapter2 = (QSPanelAdapter) recyclerView.getAdapter();
            View viewFindChildViewUnder = recyclerView.findChildViewUnder(dragEvent.getX(), dragEvent.getY());
            if (viewFindChildViewUnder == null || isMatchViewUnder(viewFindChildViewUnder, dragEvent.getX(), dragEvent.getY())) {
                if (dragData.recyclerView == recyclerView) {
                    if (viewFindChildViewUnder == null) {
                        Log.d(TAG, "same item is null");
                        childLayoutPosition2 = recyclerView.getAdapter().getItemCount();
                    } else {
                        childLayoutPosition2 = recyclerView.getChildLayoutPosition(viewFindChildViewUnder);
                        z = false;
                    }
                    int adapterPosition = dragData.viewHolder.getAdapterPosition();
                    Log.d(TAG, "same fromP:" + adapterPosition + " , toP:" + childLayoutPosition2 + " , fromPOnDrag:" + this.fromPositionOnDrag);
                    if (adapterPosition < 0 && (i4 = this.fromPositionOnDrag) >= 0 && (qSPanelAdapter = this.fomAdapterOnDrag) != null) {
                        if (notifyDateChange(i4, childLayoutPosition2, qSPanelAdapter, qSPanelAdapter2)) {
                            resetData(childLayoutPosition2, false, null);
                            return;
                        }
                        return;
                    }
                    if (adapterPosition < 0 && (i3 = this.fromPositionOnDrag) >= 0) {
                        adapterPosition = i3;
                    }
                    if (adapterPosition == childLayoutPosition2) {
                        Log.d(TAG, "same not need");
                        return;
                    }
                    if (z) {
                        childLayoutPosition2--;
                    }
                    if (notifyDataSwap(adapterPosition, childLayoutPosition2, qSPanelAdapter2)) {
                        resetData(childLayoutPosition2, false, null);
                        return;
                    }
                    return;
                }
                if (viewFindChildViewUnder == null) {
                    childLayoutPosition = recyclerView.getAdapter().getItemCount();
                    Log.d(TAG, "diff item is null toPosition:" + childLayoutPosition);
                    z2 = true;
                } else {
                    childLayoutPosition = recyclerView.getChildLayoutPosition(viewFindChildViewUnder);
                }
                int adapterPosition2 = dragData.viewHolder.getAdapterPosition();
                Log.d(TAG, "diff fromP:" + adapterPosition2 + " , toP:" + childLayoutPosition + " , fromPOnDrag:" + this.fromPositionOnDrag);
                boolean z3 = this.positionFromSameView;
                if (z3 && adapterPosition2 < 0 && (i2 = this.fromPositionOnDrag) >= 0) {
                    if (z2) {
                        childLayoutPosition--;
                    }
                    if (notifyDataSwap(i2, childLayoutPosition, qSPanelAdapter2)) {
                        resetData(childLayoutPosition, true, qSPanelAdapter2);
                        return;
                    }
                    return;
                }
                if (!z3 && (i = this.fromPositionOnDrag) >= 0) {
                    adapterPosition2 = i;
                }
                if (adapterPosition2 >= 0 && childLayoutPosition >= 0) {
                    if (notifyDateChange(adapterPosition2, childLayoutPosition, (QSPanelAdapter) dragData.recyclerView.getAdapter(), qSPanelAdapter2)) {
                        if (childLayoutPosition == qSPanelAdapter2.getData().size()) {
                            resetData(childLayoutPosition - 1, true, qSPanelAdapter2);
                            return;
                        } else {
                            resetData(childLayoutPosition, true, qSPanelAdapter2);
                            return;
                        }
                    }
                    return;
                }
                Log.d(TAG, "diff position < 0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isMatchViewUnder(View view, float x, float y) {
        float width = view.getWidth() / 4.0f;
        float height = view.getHeight() / 4.0f;
        boolean z = x >= ((float) view.getLeft()) + width && x <= ((float) view.getRight()) - width && y >= ((float) view.getTop()) + height && y <= ((float) view.getBottom()) - height;
        BydLogHelper.logD("isMatchViewUnder : " + z);
        return z;
    }

    private void resetData(int fromPositionOnDrag, boolean positionFromSameView, QSPanelAdapter endAdapter) {
        this.fromPositionOnDrag = fromPositionOnDrag;
        this.positionFromSameView = positionFromSameView;
        this.fomAdapterOnDrag = endAdapter;
    }

    private boolean notifyDateChange(int fromPosition, int toPosition, QSPanelAdapter fromAdapter, QSPanelAdapter endAdapter) {
        Log.d(TAG, "change fromP:" + fromPosition + " , toP:" + toPosition + " , fromPOnDrag:" + this.fromPositionOnDrag);
        if (fromPosition >= fromAdapter.getData().size() || toPosition > endAdapter.getData().size() || fromPosition < 0 || toPosition < 0) {
            return false;
        }
        if (fromPosition < fromAdapter.getData().size() && fromAdapter.getData().get(fromPosition).disableDrag()) {
            return false;
        }
        if (toPosition < endAdapter.getData().size() && endAdapter.getData().get(toPosition).disableDrag()) {
            return false;
        }
        QSTileImpl qSTileImpl = (QSTileImpl) fromAdapter.getData().remove(fromPosition);
        qSTileImpl.setHide(true);
        if (endAdapter.isCountLimited() && endAdapter.getData().size() >= endAdapter.getMaxCount() && endAdapter.getMaxCount() > 0) {
            if (toPosition == endAdapter.getData().size()) {
                toPosition--;
            }
            QSTileImpl qSTileImpl2 = (QSTileImpl) endAdapter.getData().remove(endAdapter.getData().size() - 1);
            fromAdapter.getData().add(0, qSTileImpl2);
            qSTileImpl2.setHide(false);
        }
        endAdapter.getData().add(toPosition, qSTileImpl);
        fromAdapter.notifyDataSetChanged();
        endAdapter.notifyDataSetChanged();
        this.endAdapterOnDrag = endAdapter;
        this.endPositionOnDrag = toPosition;
        this.fomAdapterNeedSave = fromAdapter;
        this.endAdapterNeedSave = endAdapter;
        return true;
    }

    private boolean notifyDataSwap(int fromPosition, int toPosition, QSPanelAdapter endAdapter) {
        Log.d(TAG, "swap  fromP :" + fromPosition + " , toP:" + toPosition + " , lastFromPosition:" + this.lastFromPosition + " , lastToPosition:" + this.lastToPosition);
        if ((this.lastFromPosition == fromPosition && this.lastToPosition == toPosition) || fromPosition < 0 || toPosition < 0 || fromPosition >= endAdapter.getData().size() || toPosition >= endAdapter.getData().size() || toPosition == fromPosition || endAdapter.getData().get(fromPosition).disableDrag() || endAdapter.getData().get(toPosition).disableDrag()) {
            return false;
        }
        List<QSTile> data = endAdapter.getData();
        if (fromPosition < toPosition) {
            int i = fromPosition;
            while (i < toPosition) {
                int i2 = i + 1;
                Collections.swap(data, i, i2);
                i = i2;
            }
        } else {
            for (int i3 = fromPosition; i3 > toPosition; i3--) {
                Collections.swap(data, i3, i3 - 1);
            }
        }
        ((QSTileImpl) endAdapter.getData().get(fromPosition)).setHide(false);
        ((QSTileImpl) endAdapter.getData().get(toPosition)).setHide(true);
        endAdapter.notifyDataSetChanged();
        this.endAdapterOnDrag = endAdapter;
        this.endPositionOnDrag = toPosition;
        this.endAdapterNeedSave = endAdapter;
        this.lastFromPosition = fromPosition;
        this.lastToPosition = toPosition;
        return true;
    }

    public static void startDrag(RecyclerView recyclerView, RecyclerView.ViewHolder vh) {
        vh.itemView.startDragAndDrop(null, new View.DragShadowBuilder(vh.itemView), new DragData(recyclerView, vh), 256);
    }

    public static class DragData {
        public RecyclerView recyclerView;
        public RecyclerView.ViewHolder viewHolder;

        public DragData(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            this.recyclerView = recyclerView;
            this.viewHolder = viewHolder;
            viewHolder.setIsRecyclable(false);
        }
    }
}

package com.android.car.settings.quicksettings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.settings.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class QuickSettingGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StateChangedListener {
    private static final int SEEKBAR_VIEWTYPE = 0;
    private static final int TILE_VIEWTYPE = 1;
    private final int mColumnCount;
    private final Context mContext;
    private final LayoutInflater mInflater;
    private final List<Tile> mTiles = new ArrayList();
    private final List<SeekbarTile> mSeekbarTiles = new ArrayList();
    private final QsSpanSizeLookup mQsSpanSizeLookup = new QsSpanSizeLookup();

    interface SeekbarTile extends SeekBar.OnSeekBarChangeListener {
        int getCurrent();

        int getMax();

        void start();

        void stop();
    }

    interface Tile extends View.OnClickListener {

        public enum State {
            OFF,
            ON
        }

        Drawable getIcon();

        View.OnLongClickListener getOnLongClickListener();

        State getState();

        String getText();

        boolean isAvailable();

        void start();

        void stop();
    }

    public QuickSettingGridAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mColumnCount = this.mContext.getResources().getInteger(R.integer.quick_setting_column_count);
    }

    GridLayoutManager getGridLayoutManager() {
        Context context = this.mContext;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, context.getResources().getInteger(R.integer.quick_setting_column_count));
        gridLayoutManager.setSpanSizeLookup(this.mQsSpanSizeLookup);
        return gridLayoutManager;
    }

    QuickSettingGridAdapter addSeekbarTile(SeekbarTile seekbarTile) {
        this.mSeekbarTiles.add(seekbarTile);
        return this;
    }

    QuickSettingGridAdapter addTile(Tile tile) {
        if (tile.isAvailable()) {
            this.mTiles.add(tile);
        }
        return this;
    }

    void start() {
        for (SeekbarTile tile : this.mSeekbarTiles) {
            tile.start();
        }
        for (Tile tile2 : this.mTiles) {
            tile2.start();
        }
    }

    void stop() {
        for (SeekbarTile tile : this.mSeekbarTiles) {
            tile.stop();
        }
        for (Tile tile2 : this.mTiles) {
            tile2.stop();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new BrightnessViewHolder(this.mInflater.inflate(R.layout.brightness_tile, parent, false));
        }
        if (viewType == 1) {
            return new TileViewHolder(this.mInflater.inflate(R.layout.tile, parent, false));
        }
        throw new RuntimeException("unknown viewType: " + viewType);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == 0) {
            SeekbarTile seekbarTile = this.mSeekbarTiles.get(position);
            SeekBar seekbar = ((BrightnessViewHolder) holder).mSeekBar;
            seekbar.setMax(seekbarTile.getMax());
            seekbar.setProgress(seekbarTile.getCurrent());
            seekbar.setOnSeekBarChangeListener(seekbarTile);
            return;
        }
        if (itemViewType == 1) {
            Tile tile = this.mTiles.get(position - this.mSeekbarTiles.size());
            TileViewHolder vh = (TileViewHolder) holder;
            vh.itemView.setOnClickListener(tile);
            View.OnLongClickListener onLongClickListener = tile.getOnLongClickListener();
            if (onLongClickListener != null) {
                vh.itemView.setOnLongClickListener(onLongClickListener);
            } else {
                vh.itemView.setOnLongClickListener(null);
            }
            vh.mIcon.setImageDrawable(tile.getIcon());
            int i = AnonymousClass1.$SwitchMap$com$android$car$settings$quicksettings$QuickSettingGridAdapter$Tile$State[tile.getState().ordinal()];
            if (i == 1) {
                vh.mIcon.setEnabled(true);
                vh.mIconBackground.setEnabled(true);
            } else if (i == 2) {
                vh.mIcon.setEnabled(false);
                vh.mIconBackground.setEnabled(false);
            }
            String textString = tile.getText();
            if (TextUtils.isEmpty(textString)) {
                return;
            }
            vh.mText.setText(textString);
        }
    }

    /* JADX INFO: renamed from: com.android.car.settings.quicksettings.QuickSettingGridAdapter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$car$settings$quicksettings$QuickSettingGridAdapter$Tile$State;

        static {
            int[] iArr = new int[Tile.State.values().length];
            $SwitchMap$com$android$car$settings$quicksettings$QuickSettingGridAdapter$Tile$State = iArr;
            try {
                iArr[Tile.State.ON.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$car$settings$quicksettings$QuickSettingGridAdapter$Tile$State[Tile.State.OFF.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private class BrightnessViewHolder extends RecyclerView.ViewHolder {
        private final SeekBar mSeekBar;

        BrightnessViewHolder(View view) {
            super(view);
            this.mSeekBar = (SeekBar) view.findViewById(R.id.seekbar);
        }
    }

    private class TileViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mIcon;
        private final View mIconBackground;
        private final TextView mText;

        TileViewHolder(View view) {
            super(view);
            this.mIconBackground = view.findViewById(R.id.icon_background);
            this.mIcon = (ImageView) view.findViewById(R.id.tile_icon);
            this.mText = (TextView) view.findViewById(R.id.tile_text);
        }
    }

    class QsSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        QsSpanSizeLookup() {
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int position) {
            if (position < QuickSettingGridAdapter.this.mSeekbarTiles.size()) {
                return QuickSettingGridAdapter.this.mColumnCount;
            }
            return 1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanIndex(int position, int spanCount) {
            if (position >= QuickSettingGridAdapter.this.mSeekbarTiles.size()) {
                return (position - QuickSettingGridAdapter.this.mSeekbarTiles.size()) % QuickSettingGridAdapter.this.mColumnCount;
            }
            return 1;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return position < this.mSeekbarTiles.size() ? 0 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mTiles.size() + this.mSeekbarTiles.size();
    }

    @Override // com.android.car.settings.quicksettings.StateChangedListener
    public void onStateChanged() {
        notifyDataSetChanged();
    }
}

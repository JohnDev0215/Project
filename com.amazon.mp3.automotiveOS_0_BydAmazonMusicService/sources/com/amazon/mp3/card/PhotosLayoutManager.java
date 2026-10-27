package com.amazon.mp3.card;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.legacy.widget.Space;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.ArtworkSizeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class PhotosLayoutManager extends AbstractCardLayoutManager {
    private final float mColumnSpacing;
    private int mPhotoCount;
    private ViewGroup mRow1;
    private final int mRow1PhotoSize;
    private ViewGroup mRow2;
    private final int mRow2PhotoSize;

    public PhotosLayoutManager(ViewGroup parent, int contentIdx) {
        super(parent, contentIdx);
        this.mPhotoCount = 0;
        this.mColumnSpacing = this.mContainer.getResources().getDimension(R.dimen.prime_card_horiz_padding);
        this.mRow1PhotoSize = calculatePhotoSize(this.mContext, 2);
        this.mRow2PhotoSize = calculatePhotoSize(this.mContext, 3);
    }

    @Override // com.amazon.mp3.card.AbstractCardLayoutManager
    protected ViewGroup createContentContainer() {
        ViewGroup viewGroup = (ViewGroup) View.inflate(this.mContext, R.layout.library_artist_details_photos_card, this.mContainer);
        this.mRow1 = (ViewGroup) viewGroup.findViewById(R.id.row1);
        this.mRow2 = (ViewGroup) viewGroup.findViewById(R.id.row2);
        return viewGroup;
    }

    private int calculatePhotoSize(Activity activity, int nColumns) {
        DisplayMetrics displayMetrics = ArtworkSizeUtil.getDisplayMetrics(activity);
        return (int) ((Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) - ((nColumns - 1) * this.mColumnSpacing)) / nColumns);
    }

    @Override // com.amazon.mp3.card.AbstractCardLayoutManager, com.amazon.mp3.card.LayoutManager
    public void addView(final View itemView) {
        int i;
        int i2 = this.mPhotoCount;
        if (i2 < 2) {
            this.mContainer = this.mRow1;
            i = this.mRow1PhotoSize;
        } else if (i2 < 5) {
            this.mContainer = this.mRow2;
            i = this.mRow2PhotoSize;
        } else {
            i = 0;
        }
        if (this.mContainer != null) {
            itemView.setLayoutParams(new LinearLayout.LayoutParams(-2, i, 1.0f));
            this.mContainer.addView(itemView);
            int i3 = this.mPhotoCount;
            if (i3 == 0 || i3 == 2 || i3 == 3) {
                float dimension = this.mContainer.getResources().getDimension(R.dimen.prime_card_horiz_padding);
                Space space = new Space(this.mContext);
                space.setLayoutParams(new LinearLayout.LayoutParams((int) dimension, -2));
                this.mContainer.addView(space);
            }
            this.mPhotoCount++;
        }
    }

    @Override // com.amazon.mp3.card.AbstractCardLayoutManager, com.amazon.mp3.card.LayoutManager
    public void clearAll() {
        super.clearAll();
        this.mPhotoCount = 0;
        createContentContainer();
    }
}

package com.amazon.mp3.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.mp3.R$styleable;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.CardAdapter;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Card<T, A extends CardAdapter<T>> extends LinearLayout {
    private static final String TAG = Card.class.getSimpleName();
    private A mAdapter;
    private View mFooter;
    private TextView mHeader;
    private LayoutManager mLayoutManager;
    private OnItemClickListener<T> mOnItemClickListener;
    private TextView mSubHeader;

    public interface OnItemClickListener<T> {
        void onItemClick(T item);

        void onLongItemClick(View view, T item);
    }

    public Card(Context context, CardConfiguration configuration) {
        super(context);
        init(context, configuration);
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, getDefaultConfiguration(context, attrs));
    }

    private CardConfiguration getDefaultConfiguration(Context context, AttributeSet attrs) {
        CardLayout cardLayout;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R$styleable.NewCard);
        try {
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(4, -1);
            String string = resourceId != -1 ? context.getString(resourceId) : null;
            CardLayoutType cardLayoutTypeFromValue = CardLayoutType.fromValue(typedArrayObtainStyledAttributes.getInteger(1, 0));
            if (cardLayoutTypeFromValue == CardLayoutType.GRID) {
                cardLayout = new GridCardLayout(typedArrayObtainStyledAttributes.getInteger(0, 1), typedArrayObtainStyledAttributes.getDimension(3, 0.0f));
            } else {
                cardLayout = new CardLayout(cardLayoutTypeFromValue);
            }
            return new CardConfiguration(string, null, cardLayout);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private void init(Context context) {
        setOrientation(1);
        View.inflate(context, R.layout.new_card, this);
        this.mHeader = (TextView) findViewById(R.id.header);
        this.mSubHeader = (TextView) findViewById(R.id.subheader);
        this.mFooter = findViewById(R.id.see_more);
    }

    private void init(Context context, CardConfiguration config) {
        init(context);
        if (config != null) {
            if (config.getHeaderText() != null) {
                this.mHeader.setText(config.getHeaderText());
            }
            if (config.getSubHeaderText() != null) {
                this.mSubHeader.setText(config.getSubHeaderText());
            }
            CardLayout layout = config.getLayout();
            if (layout != null) {
                int i = AnonymousClass4.$SwitchMap$com$amazon$mp3$card$CardLayoutType[config.getLayout().getType().ordinal()];
                if (i == 1) {
                    GridCardLayout gridCardLayout = (GridCardLayout) layout;
                    this.mLayoutManager = new GridLayoutManager(this, 2, gridCardLayout.getCardColumns(), gridCardLayout.getCellSpace());
                } else if (i == 2) {
                    this.mLayoutManager = new PhotosLayoutManager(this, 2);
                } else {
                    this.mLayoutManager = new LinearLayoutManager(this, 2);
                }
            }
            CardStyle style = config.getStyle();
            if (style != null) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.bottomMargin = style.getMarginBottom();
                setLayoutParams(layoutParams);
                setPadding(style.getPaddingLeft(), 0, style.getPaddingRight(), 0);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.card.Card$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$card$CardLayoutType;

        static {
            int[] iArr = new int[CardLayoutType.values().length];
            $SwitchMap$com$amazon$mp3$card$CardLayoutType = iArr;
            try {
                iArr[CardLayoutType.GRID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$card$CardLayoutType[CardLayoutType.PHOTOS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public void setHeaderText(CharSequence headerText) {
        this.mHeader.setText(headerText);
    }

    public void setHeaderFontStyle(FontStyle fontStyle) {
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(this.mHeader, fontStyle);
        }
    }

    public CharSequence getHeaderText() {
        return this.mHeader.getText();
    }

    public void setSubHeaderText(final CharSequence subHeaderText) {
        this.mSubHeader.setText(subHeaderText);
    }

    public CharSequence getSubeaderText() {
        return this.mSubHeader.getText();
    }

    public A getAdapter() {
        return this.mAdapter;
    }

    public void setAdapter(A cardAdapter) {
        this.mLayoutManager.clearAll();
        this.mAdapter = cardAdapter;
        cardAdapter.onAttachedToWindow();
        int i = 0;
        for (T t : cardAdapter.getData()) {
            View viewCreateView = cardAdapter.createView();
            setListeners(viewCreateView, t);
            cardAdapter.bindView(i, t, viewCreateView);
            this.mLayoutManager.addView(viewCreateView);
            i++;
        }
    }

    private void setListeners(View itemView, final T item) {
        itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.card.Card.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                if (Card.this.mOnItemClickListener != null) {
                    Card.this.mOnItemClickListener.onItemClick(item);
                }
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.amazon.mp3.card.Card.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(final View view) {
                if (Card.this.mOnItemClickListener == null) {
                    return false;
                }
                Card.this.mOnItemClickListener.onLongItemClick(view, item);
                return true;
            }
        });
        int overflowMenuButtonResId = this.mAdapter.getOverflowMenuButtonResId();
        if (overflowMenuButtonResId != -1) {
            itemView.findViewById(overflowMenuButtonResId).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.card.Card.3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    if (Card.this.mOnItemClickListener != null) {
                        Card.this.mOnItemClickListener.onLongItemClick(view, item);
                    }
                }
            });
        }
    }

    public void setFooterVisibility(boolean visible) {
        this.mFooter.setVisibility(visible ? 0 : 8);
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mOnItemClickListener = listener;
    }

    public void setFooterClickListener(View.OnClickListener listener) {
        this.mFooter.setOnClickListener(listener);
    }

    public void populate(A cardAdapter, boolean showFooter) {
        List<T> data = cardAdapter.getData();
        setAdapter(cardAdapter);
        setVisibility(data.size() > 0 ? 0 : 8);
        setFooterVisibility(showFooter);
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(final View child) {
        super.onViewAdded(child);
        A a2 = this.mAdapter;
        if (a2 == null) {
            return;
        }
        a2.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(final View child) {
        super.onViewRemoved(child);
        A a2 = this.mAdapter;
        if (a2 == null) {
            return;
        }
        a2.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        A a2 = this.mAdapter;
        if (a2 != null) {
            a2.onDetachedFromWindow();
        }
    }
}

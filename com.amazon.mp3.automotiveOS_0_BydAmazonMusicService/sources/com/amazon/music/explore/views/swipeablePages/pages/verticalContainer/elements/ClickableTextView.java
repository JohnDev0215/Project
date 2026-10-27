package com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements;

import CoreInterface.v1_0.Method;
import Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.IconLinkElement;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.music.explore.ExploreStyleSheet;
import com.amazon.music.explore.R;
import com.amazon.music.explore.utils.NoOpLongClickListener;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageCallback;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageRelativeLayout;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class ClickableTextView extends SwipeablePageRelativeLayout {
    private static final String ELLIPSES = "...";
    private TextView clickableText;
    private final Context context;
    private ImageButton link;
    private int linkSize;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private TextView partitionedClickableText;
    private final Resources resources;
    private final ClickableTextElement.Style style;

    public ClickableTextView(String str, Context context, MethodsDispatcher methodsDispatcher, ClickableTextElement.Style style) {
        super(context);
        this.ownerId = str;
        this.context = context;
        this.methodsDispatcher = methodsDispatcher;
        this.style = style;
        this.resources = getResources();
    }

    public void setup() {
        inflateView(false);
        TextView textView = (TextView) findViewById(R.id.swipeable_pages_partitioned_clickable_text);
        this.partitionedClickableText = textView;
        textView.setOnLongClickListener(new NoOpLongClickListener());
        this.partitionedClickableText.setVisibility(8);
        TextView textView2 = (TextView) findViewById(R.id.swipeable_pages_clickable_text);
        this.clickableText = textView2;
        textView2.setOnLongClickListener(new NoOpLongClickListener());
        ImageButton imageButton = (ImageButton) findViewById(R.id.swipeable_pages_clickable_text_link);
        this.link = imageButton;
        imageButton.setOnLongClickListener(new NoOpLongClickListener());
        setFontFamily();
        setLinkStyle();
        checkXLBreakpoint();
    }

    public void setupForStage(int i, int i2) {
        inflateView(true);
        TextView textView = (TextView) findViewById(R.id.swipeable_pages_clickable_text);
        this.clickableText = textView;
        textView.setOnLongClickListener(new NoOpLongClickListener());
        this.partitionedClickableText = (TextView) findViewById(R.id.swipeable_pages_partitioned_clickable_text);
        if (shouldPartitionTextForStage()) {
            this.partitionedClickableText.setOnLongClickListener(new NoOpLongClickListener());
            this.partitionedClickableText.setVisibility(8);
        }
        ImageButton imageButton = (ImageButton) findViewById(R.id.swipeable_pages_clickable_text_link);
        this.link = imageButton;
        imageButton.setOnLongClickListener(new NoOpLongClickListener());
        setLinkStyleForStage(i2);
        setupFontSizeForStage(i);
        setupFontLineHeightForStage(i2, i);
        setupFontFamilyForStage();
    }

    public ClickableTextElement.Style getStyle() {
        return this.style;
    }

    public int getLineCount() {
        TextView textView = this.clickableText;
        textView.measure(View.MeasureSpec.makeMeasureSpec(textView.getMaxWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        return this.clickableText.getLineCount();
    }

    public void setText(String str, final List<Method> list) {
        if (StringUtils.isEmpty(str)) {
            this.clickableText.setVisibility(8);
            return;
        }
        this.clickableText.setText(str);
        this.clickableText.setVisibility(0);
        this.clickableText.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ClickableTextView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setText$0(list, view);
            }
        });
        this.clickableText.getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass1(str, list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setText$0(List list, View view) {
        this.methodsDispatcher.dispatchMethods(this.ownerId, list);
    }

    /* JADX INFO: renamed from: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ClickableTextView$1, reason: invalid class name */
    class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ List val$onItemSelected;
        final /* synthetic */ String val$text;

        AnonymousClass1(String str, List list) {
            this.val$text = str;
            this.val$onItemSelected = list;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            int lineCount = ClickableTextView.this.clickableText.getLineCount();
            if (ClickableTextView.this.style.equals(ClickableTextElement.Style.PRIMARY) && lineCount > 1) {
                repartitionClickableText(lineCount);
                adjustFontSize();
            }
            ClickableTextView clickableTextView = ClickableTextView.this;
            clickableTextView.updateTextViewGlobalLayoutListener(clickableTextView.clickableText);
            ClickableTextView.this.clickableText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }

        private void repartitionClickableText(int i) {
            int lineStart = ClickableTextView.this.clickableText.getLayout().getLineStart(i - 1);
            String strValueOf = String.valueOf(this.val$text.subSequence(0, lineStart));
            String strValueOf2 = String.valueOf(this.val$text.subSequence(lineStart, this.val$text.length()));
            ClickableTextView.this.partitionedClickableText.setText(strValueOf);
            ClickableTextView.this.partitionedClickableText.setVisibility(0);
            TextView textView = ClickableTextView.this.partitionedClickableText;
            final List list = this.val$onItemSelected;
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ClickableTextView$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$repartitionClickableText$0(list, view);
                }
            });
            ClickableTextView clickableTextView = ClickableTextView.this;
            clickableTextView.updateTextViewGlobalLayoutListener(clickableTextView.partitionedClickableText);
            ClickableTextView.this.clickableText.setText(strValueOf2);
            ClickableTextView.this.clickableText.setVisibility(0);
            TextView textView2 = ClickableTextView.this.clickableText;
            final List list2 = this.val$onItemSelected;
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ClickableTextView$1$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$repartitionClickableText$1(list2, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$repartitionClickableText$0(List list, View view) {
            ClickableTextView.this.methodsDispatcher.dispatchMethods(ClickableTextView.this.ownerId, list);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$repartitionClickableText$1(List list, View view) {
            ClickableTextView.this.methodsDispatcher.dispatchMethods(ClickableTextView.this.ownerId, list);
        }

        private void adjustFontSize() {
            ClickableTextView.this.partitionedClickableText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ClickableTextView.1.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    ClickableTextView.this.clickableText.setTextSize(0, ClickableTextView.this.partitionedClickableText.getTextSize());
                    ClickableTextView.this.partitionedClickableText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    public void setLink(final IconLinkElement iconLinkElement, final SwipeablePageCallback swipeablePageCallback) {
        if (iconLinkElement == null) {
            return;
        }
        String strIcon = iconLinkElement.icon();
        if (StringUtils.isEmpty(strIcon)) {
            this.link.setVisibility(8);
            return;
        }
        this.link.setVisibility(0);
        RequestCreator requestCreatorPlaceholder = Picasso.get().load(strIcon).placeholder(R.drawable.ic_chevron_right);
        int i = this.linkSize;
        requestCreatorPlaceholder.resize(i / 2, i / 2).into(this.link);
        this.link.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ClickableTextView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setLink$1(swipeablePageCallback, iconLinkElement, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setLink$1(SwipeablePageCallback swipeablePageCallback, IconLinkElement iconLinkElement, View view) {
        if (swipeablePageCallback != null) {
            swipeablePageCallback.setIsUserNavigation(true);
        }
        this.methodsDispatcher.dispatchMethods(this.ownerId, iconLinkElement.onItemSelected());
    }

    public void setLink(IconLinkElement iconLinkElement) {
        setLink(iconLinkElement, null);
    }

    public void setMaxWidth(int i) {
        this.partitionedClickableText.setMaxWidth(i);
        this.clickableText.setMaxWidth(i);
    }

    public void setMaxLines(Integer num) {
        if (num != null) {
            if (this.style.equals(ClickableTextElement.Style.SECONDARY)) {
                this.clickableText.setMaxLines(num.intValue());
            } else {
                this.partitionedClickableText.setMaxLines(num.intValue());
            }
        }
    }

    public void setTextForStage(String str, final List<Method> list, IconLinkElement iconLinkElement, int i, boolean z, ClickableTextElement.Style style, final SwipeablePageCallback swipeablePageCallback) {
        if (StringUtils.isEmpty(str)) {
            this.clickableText.setVisibility(8);
            return;
        }
        if (str.length() > i && (!z || style != ClickableTextElement.Style.PRIMARY_TITLE)) {
            str = str.substring(0, i) + ELLIPSES;
        }
        this.clickableText.setText(str);
        this.clickableText.setVisibility(0);
        this.clickableText.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ClickableTextView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setTextForStage$2(list, swipeablePageCallback, view);
            }
        });
        if (shouldPartitionTextForStage() && z) {
            partitionClickableTextForLink(str, list, iconLinkElement, swipeablePageCallback);
        } else {
            if (!z || swipeablePageCallback == null) {
                return;
            }
            setLink(iconLinkElement, swipeablePageCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setTextForStage$2(List list, SwipeablePageCallback swipeablePageCallback, View view) {
        this.methodsDispatcher.dispatchMethods(this.ownerId, list);
        if (swipeablePageCallback != null) {
            swipeablePageCallback.setIsUserNavigation(true);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ClickableTextView$2, reason: invalid class name */
    class AnonymousClass2 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ IconLinkElement val$linkElement;
        final /* synthetic */ List val$onItemSelected;
        final /* synthetic */ SwipeablePageCallback val$swipeablePageCallback;
        final /* synthetic */ String val$text;

        AnonymousClass2(String str, SwipeablePageCallback swipeablePageCallback, List list, IconLinkElement iconLinkElement) {
            this.val$text = str;
            this.val$swipeablePageCallback = swipeablePageCallback;
            this.val$onItemSelected = list;
            this.val$linkElement = iconLinkElement;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            int lineCount = ClickableTextView.this.clickableText.getLineCount();
            if (lineCount > 1) {
                int lineStart = ClickableTextView.this.clickableText.getLayout().getLineStart(lineCount - 1);
                String strSubstring = this.val$text.substring(0, lineStart);
                String strSubstring2 = this.val$text.substring(lineStart);
                ClickableTextView.this.partitionedClickableText.setText(strSubstring);
                ClickableTextView.this.setStylingForPartitionedClickableText();
                TextView textView = ClickableTextView.this.partitionedClickableText;
                final SwipeablePageCallback swipeablePageCallback = this.val$swipeablePageCallback;
                final List list = this.val$onItemSelected;
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ClickableTextView$2$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$onGlobalLayout$0(swipeablePageCallback, list, view);
                    }
                });
                ClickableTextView.this.partitionedClickableText.setVisibility(0);
                ClickableTextView.this.clickableText.setText(strSubstring2);
            }
            ClickableTextView.this.setLink(this.val$linkElement, this.val$swipeablePageCallback);
            ClickableTextView.this.clickableText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onGlobalLayout$0(SwipeablePageCallback swipeablePageCallback, List list, View view) {
            if (swipeablePageCallback != null) {
                swipeablePageCallback.setIsUserNavigation(true);
            }
            ClickableTextView.this.methodsDispatcher.dispatchMethods(ClickableTextView.this.ownerId, list);
        }
    }

    private void partitionClickableTextForLink(String str, List<Method> list, IconLinkElement iconLinkElement, SwipeablePageCallback swipeablePageCallback) {
        this.clickableText.getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass2(str, swipeablePageCallback, list, iconLinkElement));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStylingForPartitionedClickableText() {
        this.partitionedClickableText.setMaxWidth(this.clickableText.getMaxWidth());
        this.partitionedClickableText.setTypeface(this.clickableText.getTypeface());
        this.partitionedClickableText.setTextSize(0, this.clickableText.getTextSize());
        ((RelativeLayout.LayoutParams) this.clickableText.getLayoutParams()).topMargin = (int) this.clickableText.getLineSpacingExtra();
        ((RelativeLayout.LayoutParams) this.link.getLayoutParams()).topMargin = (int) this.clickableText.getLineSpacingExtra();
        if (Build.VERSION.SDK_INT >= 28) {
            this.partitionedClickableText.setLineHeight(this.clickableText.getLineHeight());
        } else {
            this.partitionedClickableText.setLineSpacing(this.clickableText.getLineSpacingExtra(), 1.0f);
        }
    }

    private boolean shouldPartitionTextForStage() {
        return this.partitionedClickableText != null && this.style == ClickableTextElement.Style.PRIMARY;
    }

    public void setMaxWidthForStage(int i) {
        this.clickableText.setMaxWidth(i);
    }

    private void setupFontLineHeightForStage(int i, int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.clickableText.setLineHeight(i);
        } else {
            this.clickableText.setLineSpacing(i - i2, 1.0f);
        }
    }

    private void setupFontSizeForStage(int i) {
        this.clickableText.setTextSize(0, i);
    }

    private void setupFontFamilyForStage() {
        Typeface typefaceCreateFromAsset;
        ClickableTextElement.Style style = this.style;
        if (style == ClickableTextElement.Style.PRIMARY_TITLE) {
            typefaceCreateFromAsset = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_sharp_grotesk_semi_bold));
        } else if (style == ClickableTextElement.Style.DESCRIPTION) {
            typefaceCreateFromAsset = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_amazon_ember_regular));
        } else {
            typefaceCreateFromAsset = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_sharp_grotesk_italic));
        }
        this.clickableText.setTypeface(typefaceCreateFromAsset);
    }

    /* JADX INFO: renamed from: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ClickableTextView$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style;

        static {
            int[] iArr = new int[ClickableTextElement.Style.values().length];
            $SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style = iArr;
            try {
                iArr[ClickableTextElement.Style.PRIMARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style[ClickableTextElement.Style.SECONDARY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style[ClickableTextElement.Style.CONTENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style[ClickableTextElement.Style.PRIMARY_TITLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style[ClickableTextElement.Style.DESCRIPTION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private void inflateView(boolean z) {
        if (z) {
            int i = AnonymousClass4.$SwitchMap$Touch$SwipeablePagesTemplateInterface$v1_0$ClickableTextElement$Style[this.style.ordinal()];
            if (i == 1) {
                View.inflate(this.context, R.layout.stage_swipeable_pages_primary_text_view, this);
                return;
            }
            if (i == 2) {
                View.inflate(this.context, R.layout.stage_swipeable_pages_secondary_text_view, this);
                return;
            }
            if (i == 3) {
                View.inflate(this.context, R.layout.stage_swipeable_pages_content_view, this);
                return;
            } else if (i == 4) {
                View.inflate(this.context, R.layout.stage_swipeable_pages_primary_title_text_view, this);
                return;
            } else {
                if (i != 5) {
                    return;
                }
                View.inflate(this.context, R.layout.stage_swipeable_pages_description_text_view, this);
                return;
            }
        }
        ClickableTextElement.Style style = this.style;
        if (style == ClickableTextElement.Style.CONTENT) {
            View.inflate(this.context, R.layout.swipeable_pages_content_view, this);
        } else if (style == ClickableTextElement.Style.SECONDARY) {
            View.inflate(this.context, R.layout.swipeable_pages_secondary_text_view, this);
        } else {
            View.inflate(this.context, R.layout.swipeable_pages_primary_text_view, this);
        }
    }

    private void setFontFamily() {
        Typeface typefaceCreateFromAsset;
        if (this.style == ClickableTextElement.Style.SECONDARY) {
            typefaceCreateFromAsset = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_amazon_ember_bold));
        } else {
            typefaceCreateFromAsset = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_amazon_ember_display_condensed));
        }
        this.partitionedClickableText.setTypeface(typefaceCreateFromAsset);
        this.clickableText.setTypeface(typefaceCreateFromAsset);
    }

    private void setLinkStyle() {
        if (this.style == ClickableTextElement.Style.CONTENT) {
            this.linkSize = this.resources.getDimensionPixelSize(R.dimen.medium_icon_button);
        } else {
            this.linkSize = this.resources.getDimensionPixelSize(R.dimen.small_icon_button);
        }
    }

    private void setLinkStyleForStage(int i) {
        this.linkSize = this.resources.getDimensionPixelSize(R.dimen.small_icon_button);
        this.link.getLayoutParams().height = i;
    }

    private void checkXLBreakpoint() {
        if (ExploreStyleSheet.isXL(this.context) && this.style == ClickableTextElement.Style.CONTENT) {
            int dimensionPixelSize = this.resources.getDimensionPixelSize(R.dimen.swipeable_pages_label_text_size);
            int dimensionPixelSize2 = this.resources.getDimensionPixelSize(R.dimen.swipeable_pages_line_spacing);
            float f = dimensionPixelSize;
            this.partitionedClickableText.setTextSize(0, f);
            float f2 = dimensionPixelSize2;
            this.partitionedClickableText.setLineSpacing(f2, 1.0f);
            this.clickableText.setTextSize(0, f);
            this.clickableText.setLineSpacing(f2, 1.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTextViewGlobalLayoutListener(final TextView textView) {
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ClickableTextView.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                textView.requestLayout();
                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
}

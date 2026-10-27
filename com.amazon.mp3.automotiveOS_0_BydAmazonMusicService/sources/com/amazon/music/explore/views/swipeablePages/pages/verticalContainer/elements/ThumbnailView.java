package com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements;

import CoreInterface.v1_0.Method;
import Touch.SwipeablePagesTemplateInterface.v1_0.IconLinkElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.amazon.music.explore.R;
import com.amazon.music.explore.picasso.RoundedCornerTransformation;
import com.amazon.music.explore.utils.NoOpLongClickListener;
import com.amazon.music.explore.views.swipeablePages.binders.SwipeableSectionsBinder;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageCallback;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageRelativeLayout;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.ui.foundations.styles.ShadowStyle;
import com.amazon.ui.foundations.views.BaseImageShadow;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class ThumbnailView extends SwipeablePageRelativeLayout {
    private final Context context;
    private boolean isStage;
    private ImageButton link;
    private int linkSize;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final Resources resources;
    private BaseImageShadow shadow;
    private final SwipeablePageCallback swipeablePageCallback;
    private ImageView thumbnail;

    public ThumbnailView(String str, Context context, MethodsDispatcher methodsDispatcher, boolean z, SwipeablePageCallback swipeablePageCallback) {
        super(context);
        this.ownerId = str;
        this.context = context;
        this.methodsDispatcher = methodsDispatcher;
        this.isStage = z;
        this.resources = getResources();
        this.swipeablePageCallback = swipeablePageCallback;
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.thumbnail_view, this);
        ImageView imageView = (ImageView) findViewById(R.id.thumbnail_image);
        this.thumbnail = imageView;
        imageView.setOnLongClickListener(new NoOpLongClickListener());
        this.shadow = (BaseImageShadow) findViewById(R.id.thumbnail_shadow);
        this.link = (ImageButton) findViewById(R.id.thumbnail_link);
        this.linkSize = this.resources.getDimensionPixelSize(R.dimen.small_icon_button);
        this.link.setOnLongClickListener(new NoOpLongClickListener());
        addDropShadow(this.resources.getColor(R.color.secondary_glass_3), 0, this.resources.getDimensionPixelSize(R.dimen.spacer_10));
    }

    public void setThumbnail(String str, final List<Method> list, int i) {
        if (StringUtils.isEmpty(str)) {
            this.thumbnail.setVisibility(8);
            return;
        }
        this.thumbnail.setVisibility(0);
        Picasso.get().load(SwipeableSectionsBinder.getSizedThumbnailUrl(str, i)).transform(new RoundedCornerTransformation(this.resources.getDimensionPixelSize(R.dimen.spacer_8))).placeholder(R.drawable.placeholder).into(this.thumbnail);
        this.thumbnail.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ThumbnailView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setThumbnail$0(list, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setThumbnail$0(List list, View view) {
        SwipeablePageCallback swipeablePageCallback;
        this.methodsDispatcher.dispatchMethods(this.ownerId, list);
        if (!this.isStage || (swipeablePageCallback = this.swipeablePageCallback) == null) {
            return;
        }
        swipeablePageCallback.setIsUserNavigation(true);
    }

    public void setLink(final IconLinkElement iconLinkElement) {
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
        this.link.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ThumbnailView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setLink$1(iconLinkElement, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setLink$1(IconLinkElement iconLinkElement, View view) {
        SwipeablePageCallback swipeablePageCallback;
        this.methodsDispatcher.dispatchMethods(this.ownerId, iconLinkElement.onItemSelected());
        if (!this.isStage || (swipeablePageCallback = this.swipeablePageCallback) == null) {
            return;
        }
        swipeablePageCallback.setIsUserNavigation(true);
    }

    public void setThumbnailSize(int i) {
        ViewGroup.LayoutParams layoutParams = this.thumbnail.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        float f = i;
        this.shadow.setSize(f, f);
    }

    public void overrideForStage(int i) {
        setThumbnailSize(i);
        overrideThumbnailLayout();
        this.shadow.setVisibility(8);
    }

    private void overrideThumbnailLayout() {
        ((RelativeLayout.LayoutParams) ((RelativeLayout) findViewById(R.id.thumbnail)).getLayoutParams()).addRule(13, -1);
    }

    private void addDropShadow(int i, int i2, int i3) {
        ShadowStyle shadowStyle = new ShadowStyle(i, i2, i3);
        int dimensionPixelSize = this.resources.getDimensionPixelSize(R.dimen.spacer_8);
        this.shadow.setStyle(shadowStyle);
        this.shadow.setCornerRadius(dimensionPixelSize);
    }
}

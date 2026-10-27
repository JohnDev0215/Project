package com.amazon.music.castingviews;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.castingviews.button.CastingTileWidget;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.ui.model.castingtile.CastingTileModel;
import com.amazon.music.view.adapter.UniversalBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.squareup.picasso.Picasso;
import java.util.NoSuchElementException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class CastingTileBinder implements UniversalBinder<CastingTileWidget, CastingTileModel> {
    private static String disabledReason;
    private final Context activityContext;
    private boolean isLoading;
    private StyleSheet styleSheet;
    private boolean wrapTitle;
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) CastingTileBinder.class);
    private static boolean hasLocalTracksLoaded = false;
    private static boolean alexaDevicesEnabled = true;
    private static boolean chromecastDevicesEnabled = true;
    private View.OnClickListener onTileClickListener = new View.OnClickListener() { // from class: com.amazon.music.castingviews.CastingTileBinder.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CastingTileBinder.LOG.debug("onTileClickListener.onClick() view: " + view);
            if (CastingTileBinder.this.isLoading()) {
                CastingTileBinder.LOG.debug("Ignoring onTileClickListener since we're loading");
                return;
            }
            final CastingTileWidget castingTileWidget = (CastingTileWidget) view;
            CastingTileModel castingTileModel = (CastingTileModel) castingTileWidget.getTag();
            CastingUIMetricsLogger.recordCastingDeviceClickMetric(castingTileModel.targetId.get(), castingTileWidget.getIndex());
            if (CastingTileBinder.this.castingManager.isConnected()) {
                if (CastingTileBinder.this.castingManager.getCastingDevice() != null) {
                    CastingDialogFactory.createDisconnectDeviceFirstDialog(CastingTileBinder.this.activityContext, CastingTileBinder.this.castingManager.getCastingDevice().getName()).show();
                    return;
                }
                return;
            }
            boolean zBooleanValue = castingTileModel.isPlaying.isPresent() ? castingTileModel.isPlaying.get().booleanValue() : false;
            final CastingDevice.Builder builderWithImageUrl = new CastingDevice.Builder(CastingCategory.fromString(castingTileModel.category.get()), castingTileModel.targetId.get()).withDeviceTypeId(castingTileModel.deviceTypeId.get()).withName(castingTileModel.title.get()).withIsPlaying(zBooleanValue).withImageUrl(castingTileModel.imageUrl.get());
            if (!zBooleanValue || !CastingTileBinder.hasLocalTracksLoaded) {
                builderWithImageUrl.withOverridePlayback(CastingTileBinder.hasLocalTracksLoaded);
                castingTileWidget.showSpinner(true);
                CastingTileBinder.this.castingManager.connect(builderWithImageUrl.build());
                CastingTileBinder.this.setLoading(true);
                return;
            }
            CastingDialogFactory.createRemoteControlDialog(CastingTileBinder.this.activityContext, castingTileModel.title.get(), new DialogInterface.OnClickListener() { // from class: com.amazon.music.castingviews.CastingTileBinder.1.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean z = i == -1;
                    builderWithImageUrl.withOverridePlayback(z);
                    if (z) {
                        CastingUIMetricsLogger.recordCastToDeviceClickMetric();
                    } else {
                        CastingUIMetricsLogger.recordControlDeviceClickMetric();
                    }
                    castingTileWidget.showSpinner(true);
                    CastingTileBinder.this.castingManager.connect(builderWithImageUrl.build());
                    CastingTileBinder.this.setLoading(true);
                }
            }).show();
            CastingUIMetricsLogger.recordRemoteControlDialogContentView();
        }
    };
    private View.OnClickListener onDisconnectClickListener = new View.OnClickListener() { // from class: com.amazon.music.castingviews.CastingTileBinder.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CastingTileBinder.LOG.debug("onDisconnectClickListener.onClick() view: " + view);
            if (CastingTileBinder.this.isLoading()) {
                CastingTileBinder.LOG.debug("Ignoring onDisconnectClickListener since we're loading");
                return;
            }
            CastingTileWidget castingTileWidget = (CastingTileWidget) view.getTag();
            CastingTileModel castingTileModel = (CastingTileModel) castingTileWidget.getTag();
            castingTileWidget.showSpinner(true);
            view.setVisibility(8);
            CastingUIMetricsLogger.recordCastingDeviceDisconnectClickMetric(castingTileModel.targetId.get(), 0);
            CastingTileBinder.this.castingManager.disconnect(!CastingCategory.CHROMECAST.getName().equals(castingTileModel.category.get()) ? TerminationReason.USER_DISCONNECT : null);
            CastingTileBinder.this.setLoading(true);
        }
    };
    private final CastingSessionManager castingManager = CastingSessionManager.getInstance();

    static void setLocalTracksLoaded(boolean z) {
        hasLocalTracksLoaded = z;
    }

    static void disableAlexaDevices(String str) {
        alexaDevicesEnabled = false;
        disabledReason = str;
    }

    static void enableAlexaDevices() {
        alexaDevicesEnabled = true;
    }

    static void disableChromecastDevices(String str) {
        chromecastDevicesEnabled = false;
        disabledReason = str;
    }

    static void enableChromecastDevices() {
        chromecastDevicesEnabled = true;
    }

    public CastingTileBinder(Context context, StyleSheet styleSheet, boolean z) {
        this.activityContext = context;
        this.styleSheet = styleSheet;
        this.wrapTitle = z;
    }

    @Override // com.amazon.music.view.adapter.UniversalBinder
    public Class<CastingTileModel> getModelClass() {
        return CastingTileModel.class;
    }

    @Override // com.amazon.music.view.adapter.UniversalBinder
    public CastingTileWidget createView(Context context) {
        return new CastingTileWidget(context, null, 0, 0, this.wrapTitle);
    }

    @Override // com.amazon.music.view.adapter.UniversalBinder
    public void bind(CastingTileWidget castingTileWidget, CastingTileModel castingTileModel) {
        if (castingTileWidget.getTag() instanceof RecyclerView.ViewHolder) {
            castingTileWidget.setIndex(((RecyclerView.ViewHolder) castingTileWidget.getTag()).getLayoutPosition());
        }
        castingTileWidget.setTag(castingTileModel);
        castingTileWidget.getButton().setTag(castingTileWidget);
        castingTileWidget.initStyle(this.styleSheet);
        bindCastingState(castingTileWidget, castingTileModel);
    }

    public synchronized void setLoading(boolean z) {
        this.isLoading = z;
    }

    public synchronized boolean isLoading() {
        return this.isLoading;
    }

    private boolean isCasting(CastingTileModel castingTileModel) {
        CastingDevice castingDevice;
        return this.castingManager.isConnected() && (castingDevice = this.castingManager.getCastingDevice()) != null && castingDevice.getTargetId().equals(castingTileModel.targetId.get());
    }

    private void bindCastingState(CastingTileWidget castingTileWidget, CastingTileModel castingTileModel) {
        boolean zIsCasting = isCasting(castingTileModel);
        loadTileIcon(castingTileWidget.getImageIconView(), castingTileModel);
        tintImageView(zIsCasting, castingTileWidget.getImageIconView());
        updateTitle(zIsCasting, castingTileWidget, castingTileModel);
        updateSubtitle(zIsCasting, castingTileWidget, castingTileModel);
        boolean zIsEnabled = isEnabled(castingTileModel);
        castingTileWidget.setEnabled(zIsCasting || (!this.castingManager.isConnected() && zIsEnabled));
        castingTileWidget.showButton(zIsCasting);
        castingTileWidget.setOnClickListener((zIsCasting || !zIsEnabled) ? null : this.onTileClickListener);
        String str = castingTileModel.title.get();
        if (zIsCasting) {
            castingTileWidget.getButton().setOnClickListener(this.onDisconnectClickListener);
            castingTileWidget.getButton().setText(this.activityContext.getResources().getString(R$string.casting_tile_button_text));
            castingTileWidget.getButton().setContentDescription(castingTileWidget.getResources().getString(R$string.casting_tile_disconnect_button_content_description, str));
            castingTileWidget.setContentDescription(castingTileWidget.getResources().getString(R$string.casting_tile_connected_content_description, str));
        } else if (castingTileWidget.isEnabled()) {
            castingTileWidget.setContentDescription(castingTileWidget.getResources().getString(R$string.casting_tile_disconnected_content_description, str));
        }
        castingTileWidget.showSpinner(false);
    }

    private void loadTileIcon(ImageView imageView, CastingTileModel castingTileModel) {
        if (CastingCategory.CHROMECAST.equals(CastingCategory.fromString(castingTileModel.category.get()))) {
            imageView.setImageResource(R$drawable.ic_chromecast_inactive);
            return;
        }
        try {
            String str = castingTileModel.imageUrl.get();
            if (StringUtils.isEmpty(str)) {
                return;
            }
            PicassoImageLoader.builder(this.activityContext, Picasso.get()).withTag(this.activityContext).build().loadImage(str, imageView, R$drawable.ic_dot);
        } catch (NullPointerException | NoSuchElementException unused) {
            LOG.info("No imageUrl given!");
        }
    }

    private void tintImageView(boolean z, ImageView imageView) {
        if (z) {
            imageView.setColorFilter(ContextCompat.getColor(this.activityContext, R$color.vivace_blue), PorterDuff.Mode.MULTIPLY);
        } else {
            imageView.clearColorFilter();
        }
    }

    private int getTileColor(boolean z) {
        return ContextCompat.getColor(this.activityContext, z ? R$color.vivace_blue : R$color.white);
    }

    private void updateTitle(boolean z, CastingTileWidget castingTileWidget, CastingTileModel castingTileModel) {
        castingTileWidget.setTitleColor(getTileColor(z));
        if (castingTileModel.title.isPresent()) {
            castingTileWidget.setTitle(castingTileModel.title.get());
        } else {
            castingTileWidget.setTitle(null);
        }
    }

    private void updateSubtitle(boolean z, CastingTileWidget castingTileWidget, CastingTileModel castingTileModel) {
        castingTileWidget.setSubtitleColor(getTileColor(z));
        if (z) {
            castingTileWidget.setSubtitle(this.activityContext.getResources().getString(R$string.casting_connected_text));
            return;
        }
        if (!isEnabled(castingTileModel)) {
            castingTileWidget.setSubtitle(disabledReason);
        } else if (castingTileModel.isPlaying.isPresent() && castingTileModel.isPlaying.get().booleanValue()) {
            castingTileWidget.setSubtitle(this.activityContext.getResources().getString(R$string.casting_tile_playing_music));
        } else {
            castingTileWidget.setSubtitle(null);
        }
    }

    private boolean isEnabled(CastingTileModel castingTileModel) {
        if (castingTileModel.category.isPresent() && CastingCategory.CHROMECAST.getName().equals(castingTileModel.category.get())) {
            return chromecastDevicesEnabled;
        }
        return alexaDevicesEnabled;
    }
}

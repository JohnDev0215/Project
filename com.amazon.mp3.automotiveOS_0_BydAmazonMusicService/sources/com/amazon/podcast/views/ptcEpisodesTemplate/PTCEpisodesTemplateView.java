package com.amazon.podcast.views.ptcEpisodesTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.CloseButtonElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.FooterButtonElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCEpisodesTemplate;
import SOACoreInterface.v1_0.Method;
import android.os.Build;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.UiMetrics;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class PTCEpisodesTemplateView extends BaseView<PTCEpisodesTemplate> {
    private static final String TAG;
    private static final Logger logger;
    private PTCListItemAdapter adapter;
    private EmberTextView footerButton;
    private ImageButton headerCloseButton;
    private List<Method> onViewed;
    private View ptcEpisodesView;
    private RecyclerView recyclerView;
    private TextView subtitle;
    private final TemplateContext templateContext;
    private TextView title;

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
    }

    static {
        String simpleName = PTCEpisodesTemplateView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public PTCEpisodesTemplateView(final TemplateContext templateContext) {
        super(templateContext);
        this.templateContext = templateContext;
        init();
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final PTCEpisodesTemplate template, final boolean hideActionBar) {
        if (PodcastFeatureGating.IS_SONIC_RUSH.isEnabled()) {
            this.title.setText(WordUtils.capitalize(template.getTitle()));
        } else {
            this.title.setText(template.getTitle());
        }
        this.subtitle.setText(template.getSubtitle());
        bindCloseButton(template.getCloseButton());
        bindFooterButton(template.getFooterButton());
        this.adapter.bind(template.getItems());
        bindOnViewed(template);
    }

    private void bindCloseButton(final CloseButtonElement closeButtonElement) {
        if (closeButtonElement == null) {
            return;
        }
        this.headerCloseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcEpisodesTemplate.PTCEpisodesTemplateView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PTCEpisodesTemplateView.this.getMethodsDispatcher().dispatch(PTCEpisodesTemplateView.this.getOwnerId(), closeButtonElement.getOnItemSelected());
            }
        });
        this.headerCloseButton.setVisibility(0);
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    private void init() {
        this.ptcEpisodesView = View.inflate(getContext(), R$layout.ptc_episodes_template_view, this);
        this.headerCloseButton = (ImageButton) findViewById(R$id.ptc_episodes_cancel_button);
        this.title = (TextView) findViewById(R$id.podcast_ptc_episodes_title);
        RuntimeStyleSheet runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        this.title.setTypeface(runtimeStyleSheet.getHeadline2TypeFace());
        this.title.setTextSize(0, runtimeStyleSheet.getHeadline2TextSize());
        this.title.setAllCaps(runtimeStyleSheet.isHeadline2AllCaps());
        if (Build.VERSION.SDK_INT >= 28) {
            this.title.setLineHeight(runtimeStyleSheet.getHeadline2LineHeight());
        }
        this.subtitle = (TextView) findViewById(R$id.podcast_ptc_episodes_subtitle);
        this.recyclerView = (RecyclerView) findViewById(R$id.podcast_ptc_episodes_recycler_view);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.podcast_ptc_episodes_footer);
        this.footerButton = emberTextView;
        emberTextView.setBackground(getResources().getDrawable(Podcast.getRuntimeStyleSheet().getAccentRoundedCornersDrawableId()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        PTCListItemAdapter pTCListItemAdapter = new PTCListItemAdapter(getOwnerId(), getMethodsDispatcher(), getResources());
        this.adapter = pTCListItemAdapter;
        this.recyclerView.setAdapter(pTCListItemAdapter);
        this.recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void bindFooterButton(final FooterButtonElement element) {
        if (element == null) {
            return;
        }
        this.footerButton.setText(element.getText());
        this.footerButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcEpisodesTemplate.PTCEpisodesTemplateView.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                PTCEpisodesTemplateView.this.getMethodsDispatcher().dispatch(PTCEpisodesTemplateView.this.getOwnerId(), element.getOnItemSelected());
            }
        });
    }

    private void bindOnViewed(final PTCEpisodesTemplate template) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            this.onViewed = template.getOnViewed();
            if (UiMetrics.isOnScreen(this.ptcEpisodesView)) {
                handleOnViewed();
            }
        }
    }
}

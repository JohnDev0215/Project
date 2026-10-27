package com.amazon.podcast;

import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplate;
import Podcast.Touch.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplate;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.AddCardsMethod;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.NextCardMethod;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.PreviousCardMethod;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShowVisualShovelerMethod;
import Podcast.Touch.GalleryTemplateInterface.v1_0.AddWidgetsMethod;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.AddHorizontalItemsMethod;
import Podcast.Touch.LatestTemplateInterface.v2_0.AddEpisodeRowItemsMethod;
import Podcast.Touch.NotificationElementInterface.v1_0.CloseNotificationMethod;
import Podcast.Touch.NotificationElementInterface.v1_0.ShowNotificationMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.AddGridElementsMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTrailerPlaybackErrorMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.ShowPTCTrailerMethod;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.AddVerticalItemsMethod;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.AddVisualRowItemsMethod;
import Podcast.UpsellInterface.v1_0.ShowUpsellMethod;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.BindTemplateMethod;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethod;
import SOATemplateListInterface.v1_0.CreateTemplateMethod;
import SOATemplateListInterface.v1_0.RemoveTemplateMethod;
import SOATemplateListInterface.v1_0.Template;
import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.FragmentManager;
import com.amazon.podcast.bootstrap.ActionBarProvider;
import com.amazon.podcast.bootstrap.FragmentInfoProvider;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.views.alertTemplate.AlertTemplateActivity;
import com.amazon.podcast.views.episodeRowTemplate.EpisodeRowTemplateMethods;
import com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateV2Methods;
import com.amazon.podcast.views.stackedButtonsAlertTemplate.StackedButtonsAlertTemplateActivity;
import com.amazon.podcast.views.widgets.WidgetsMethods;
import com.amazon.soa.core.Engine;
import com.amazon.soa.core.MethodCallback;
import com.amazon.soa.core.OnMethodExecutedListener;
import com.amazon.soa.core.OwnerIdGenerator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class Templates implements MethodCallback, MethodsDispatcher, OwnerRegistration {
    private static final Logger logger = LoggerFactory.getLogger(Templates.class.getSimpleName());
    private String cardNowPlayingOwner;
    private Context context;
    private final Engine engine;
    private FragmentInfoProvider fragmentInfoProvider;
    private final Map<String, TemplateList> lookup = new HashMap();
    private FragmentManager nowPlayingFragmentManager;

    public Templates(final Engine engine) {
        this.engine = engine;
        HashSet hashSet = new HashSet();
        hashSet.add(CreateTemplateMethod.class.getCanonicalName());
        hashSet.add(CreateAndBindTemplateMethod.class.getCanonicalName());
        hashSet.add(BindTemplateMethod.class.getCanonicalName());
        hashSet.add(RemoveTemplateMethod.class.getCanonicalName());
        hashSet.add(AddVerticalItemsMethod.class.getCanonicalName());
        hashSet.add(AddHorizontalItemsMethod.class.getCanonicalName());
        hashSet.add(AddVisualRowItemsMethod.class.getCanonicalName());
        hashSet.add(AddWidgetsMethod.class.getCanonicalName());
        hashSet.addAll(WidgetsMethods.methodsForRegistry());
        hashSet.add(AddGridElementsMethod.class.getCanonicalName());
        hashSet.addAll(PodcastDetailTemplateV2Methods.methodsForRegistry());
        hashSet.add(AddEpisodeRowItemsMethod.class.getCanonicalName());
        hashSet.add(ShowVisualShovelerMethod.class.getCanonicalName());
        hashSet.add(ShowPTCTrailerMethod.class.getCanonicalName());
        hashSet.add(PTCTrailerPlaybackErrorMethod.class.getCanonicalName());
        hashSet.add(NextCardMethod.class.getCanonicalName());
        hashSet.add(PreviousCardMethod.class.getCanonicalName());
        hashSet.add(AddCardsMethod.class.getCanonicalName());
        hashSet.add(ShowNotificationMethod.class.getCanonicalName());
        hashSet.add(CloseNotificationMethod.class.getCanonicalName());
        hashSet.addAll(EpisodeRowTemplateMethods.methodsForRegistry());
        hashSet.add(ShowNotificationMethod.class.getCanonicalName());
        hashSet.add(CloseNotificationMethod.class.getCanonicalName());
        HashSet hashSet2 = new HashSet();
        hashSet2.add(ShowUpsellMethod.class.getCanonicalName());
        engine.registerMethods(hashSet, hashSet2, this);
    }

    public void setApplicationContext(final Context context) {
        this.context = context;
    }

    public TemplateList createTemplateList(final Context context, final ActionBarProvider actionBarProvider, final FragmentInfoProvider fragmentInfoProvider, final boolean isRestoringPlayback) {
        this.fragmentInfoProvider = fragmentInfoProvider;
        String strForTemplateList = OwnerIdGenerator.forTemplateList();
        TemplateList templateList = new TemplateList(strForTemplateList, context, actionBarProvider, fragmentInfoProvider, this, this, isRestoringPlayback);
        this.lookup.put(strForTemplateList, templateList);
        this.engine.registerOwner(strForTemplateList);
        return templateList;
    }

    public void setNowPlayingFragmentManager(final FragmentManager fragmentManager) {
        this.nowPlayingFragmentManager = fragmentManager;
    }

    public void setCardNowPlayingOwner(final String cardNowPlayingOwner) {
        this.cardNowPlayingOwner = cardNowPlayingOwner;
    }

    @Override // com.amazon.podcast.MethodsDispatcher
    public void dispatch(final String ownerId, final List<Method> methods) {
        this.engine.handleMethods(ownerId, methods);
    }

    @Override // com.amazon.podcast.OwnerRegistration
    public void registerOwner(final String ownerId) {
        this.engine.registerOwner(ownerId);
    }

    @Override // com.amazon.podcast.OwnerRegistration
    public void deregisterOwner(final String ownerId) {
        this.engine.deregisterOwner(ownerId);
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.Templates.1
            @Override // java.lang.Runnable
            public void run() {
                Templates.this.executeMethodForeground(method, ownerId, listener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeMethodForeground(final Method method, String ownerId, final OnMethodExecutedListener listener) {
        String str;
        if (method instanceof ShowUpsellMethod) {
            if (Podcast.isAlertsDisabled()) {
                logger.debug("Showing alert is disabled,ignoring ShowUpsellMethod execution");
                return;
            } else if (!Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled()) {
                logger.debug("Redhood plus UX is NOT enabled,ignoring ShowUpsellMethod execution");
                return;
            } else {
                ShowUpsellMethod showUpsellMethod = (ShowUpsellMethod) method;
                Podcast.getDynamicMessagingProvider().showDynamicMessage(showUpsellMethod.getTrigger(), showUpsellMethod.getRedirectDeeplinkUrl(), UiMetricAttributes$PageType.NONE.toString());
            }
        }
        if (this.fragmentInfoProvider.fragmentManager() == null) {
            return;
        }
        TemplateList value = null;
        if (isCardPlayingTemplateMethod(method) && (str = this.cardNowPlayingOwner) != null) {
            ownerId = str;
        }
        for (Map.Entry<String, TemplateList> entry : this.lookup.entrySet()) {
            if (ownerId.startsWith(entry.getKey())) {
                value = entry.getValue();
            }
        }
        if (value == null) {
            if (isAlertTemplate(method)) {
                showAlertTemplate(method, ownerId);
                listener.onMethodExecuted(method);
                return;
            } else {
                listener.onMethodExecuted(method);
                return;
            }
        }
        if (method instanceof BindTemplateMethod) {
            value.bindTemplateMethod(ownerId, (BindTemplateMethod) method, listener);
            return;
        }
        if (method instanceof CreateTemplateMethod) {
            value.createTemplateMethod((CreateTemplateMethod) method, listener);
            return;
        }
        if (method instanceof CreateAndBindTemplateMethod) {
            value.createAndBindTemplateMethod((CreateAndBindTemplateMethod) method, listener);
        } else if (method instanceof RemoveTemplateMethod) {
            value.removeTemplateMethod(ownerId, (RemoveTemplateMethod) method, listener);
        } else {
            value.handleTemplateMethod(ownerId, method, listener);
        }
    }

    private boolean isAlertTemplate(final Method method) {
        if (method instanceof BindTemplateMethod) {
            Template template = ((BindTemplateMethod) method).getTemplate();
            return (template instanceof AlertTemplate) || (template instanceof StackedButtonsAlertTemplate);
        }
        if (method instanceof CreateTemplateMethod) {
            Template template2 = ((CreateTemplateMethod) method).getTemplate();
            return (template2 instanceof AlertTemplate) || (template2 instanceof StackedButtonsAlertTemplate);
        }
        if (!(method instanceof CreateAndBindTemplateMethod)) {
            return false;
        }
        Template template3 = ((CreateAndBindTemplateMethod) method).getTemplate();
        return (template3 instanceof AlertTemplate) || (template3 instanceof StackedButtonsAlertTemplate);
    }

    private boolean isCardPlayingTemplateMethod(final Method method) {
        return (method instanceof NextCardMethod) || (method instanceof PreviousCardMethod) || (method instanceof AddCardsMethod);
    }

    private void showAlertTemplate(final Method method, final String ownerId) {
        if (this.context == null) {
            return;
        }
        if (Podcast.isAlertsDisabled()) {
            logger.debug("Showing alert is disabled");
            return;
        }
        Template template = null;
        if (method instanceof BindTemplateMethod) {
            template = ((BindTemplateMethod) method).getTemplate();
        } else if (method instanceof CreateTemplateMethod) {
            template = ((CreateTemplateMethod) method).getTemplate();
        } else if (method instanceof CreateAndBindTemplateMethod) {
            template = ((CreateAndBindTemplateMethod) method).getTemplate();
        }
        String strForTemplateShard = OwnerIdGenerator.forTemplateShard(ownerId);
        TemplateCache.INSTANCE.write(strForTemplateShard, new TemplateCacheEntry(template, true, true, true, null, this, this));
        Intent intentForAlert = getIntentForAlert(template);
        intentForAlert.putExtra("ownerId", strForTemplateShard);
        intentForAlert.addFlags(268435456);
        this.context.startActivity(intentForAlert);
    }

    private Intent getIntentForAlert(final Template template) {
        if (template instanceof AlertTemplate) {
            return new Intent(this.context, (Class<?>) AlertTemplateActivity.class);
        }
        if (template instanceof StackedButtonsAlertTemplate) {
            return new Intent(this.context, (Class<?>) StackedButtonsAlertTemplateActivity.class);
        }
        return null;
    }
}

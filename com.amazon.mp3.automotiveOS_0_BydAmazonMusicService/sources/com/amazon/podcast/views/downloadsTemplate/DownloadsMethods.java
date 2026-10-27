package com.amazon.podcast.views.downloadsTemplate;

import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplate;
import Podcast.Touch.AlertTemplateInterface.v1_0.NegativeButtonElement;
import Podcast.Touch.AlertTemplateInterface.v1_0.PositiveButtonElement;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethod;
import android.content.res.Resources;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$string;
import com.amazon.podcast.downloads.DeleteDownloadMethod;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class DownloadsMethods {
    public static final List<Method> deleteDownloadMethods(final Resources resources, final String id) {
        PositiveButtonElement positiveButtonElementBuild = PositiveButtonElement.builder().withText(resources.getString(R$string.podcast_alert_yes)).withOnItemSelected(Collections.singletonList(new DeleteDownloadMethod(id, Queues.downloadSync()))).build();
        return Collections.singletonList(CreateAndBindTemplateMethod.builder().withTemplate(AlertTemplate.builder().withImage(String.valueOf(R$drawable.ic_action_delete)).withTitle(resources.getString(R$string.podcast_remove_download_title)).withSubtitle(resources.getString(R$string.podcast_remove_download_subtitle)).withPositiveButton(positiveButtonElementBuild).withNegativeButton(NegativeButtonElement.builder().withText(resources.getString(R$string.podcast_alert_no)).withOnItemSelected(Collections.emptyList()).build()).build()).withQueue(Queues.template()).build());
    }
}

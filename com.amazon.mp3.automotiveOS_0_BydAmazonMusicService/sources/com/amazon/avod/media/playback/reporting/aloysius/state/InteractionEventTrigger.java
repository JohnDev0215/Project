package com.amazon.avod.media.playback.reporting.aloysius.state;

import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporter;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class InteractionEventTrigger implements Trigger<AloysiusInteractionReporter.Type> {
    private final AloysiusInteractionReporter.Source mSource;
    private final AloysiusInteractionReporter.Type mType;

    public InteractionEventTrigger(@Nonnull AloysiusInteractionReporter.Type type, @Nonnull AloysiusInteractionReporter.Source source) {
        this.mType = (AloysiusInteractionReporter.Type) Preconditions.checkNotNull(type, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        this.mSource = (AloysiusInteractionReporter.Source) Preconditions.checkNotNull(source, PageUriUtils.SOURCE_QUERY_PARAM);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.fsm.Trigger
    @Nonnull
    public AloysiusInteractionReporter.Type getType() {
        return this.mType;
    }

    @Nonnull
    public AloysiusInteractionReporter.Source getSource() {
        return this.mSource;
    }
}

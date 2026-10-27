package android.service.notification;

import android.content.Context;
import android.media.AudioSystem;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Contacts;
import android.util.proto.ProtoOutputStream;
import java.io.ByteArrayOutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class ZenPolicy implements Parcelable {
    public static final int CONVERSATION_SENDERS_ANYONE = 1;
    public static final int CONVERSATION_SENDERS_IMPORTANT = 2;
    public static final int CONVERSATION_SENDERS_NONE = 3;
    public static final int CONVERSATION_SENDERS_UNSET = 0;
    public static final Parcelable.Creator<ZenPolicy> CREATOR = new Parcelable.Creator<ZenPolicy>() { // from class: android.service.notification.ZenPolicy.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ZenPolicy createFromParcel(Parcel parcel) {
            ZenPolicy zenPolicy = new ZenPolicy();
            zenPolicy.mPriorityCategories = parcel.readArrayList(Integer.class.getClassLoader());
            zenPolicy.mVisualEffects = parcel.readArrayList(Integer.class.getClassLoader());
            zenPolicy.mPriorityCalls = parcel.readInt();
            zenPolicy.mPriorityMessages = parcel.readInt();
            zenPolicy.mConversationSenders = parcel.readInt();
            return zenPolicy;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ZenPolicy[] newArray(int i) {
            return new ZenPolicy[i];
        }
    };
    public static final int PEOPLE_TYPE_ANYONE = 1;
    public static final int PEOPLE_TYPE_CONTACTS = 2;
    public static final int PEOPLE_TYPE_NONE = 4;
    public static final int PEOPLE_TYPE_STARRED = 3;
    public static final int PEOPLE_TYPE_UNSET = 0;
    public static final int PRIORITY_CATEGORY_ALARMS = 5;
    public static final int PRIORITY_CATEGORY_CALLS = 3;
    public static final int PRIORITY_CATEGORY_CONVERSATIONS = 8;
    public static final int PRIORITY_CATEGORY_EVENTS = 1;
    public static final int PRIORITY_CATEGORY_MEDIA = 6;
    public static final int PRIORITY_CATEGORY_MESSAGES = 2;
    public static final int PRIORITY_CATEGORY_REMINDERS = 0;
    public static final int PRIORITY_CATEGORY_REPEAT_CALLERS = 4;
    public static final int PRIORITY_CATEGORY_SYSTEM = 7;
    public static final int STATE_ALLOW = 1;
    public static final int STATE_DISALLOW = 2;
    public static final int STATE_UNSET = 0;
    public static final int VISUAL_EFFECT_AMBIENT = 5;
    public static final int VISUAL_EFFECT_BADGE = 4;
    public static final int VISUAL_EFFECT_FULL_SCREEN_INTENT = 0;
    public static final int VISUAL_EFFECT_LIGHTS = 1;
    public static final int VISUAL_EFFECT_NOTIFICATION_LIST = 6;
    public static final int VISUAL_EFFECT_PEEK = 2;
    public static final int VISUAL_EFFECT_STATUS_BAR = 3;
    private int mPriorityMessages = 0;
    private int mPriorityCalls = 0;
    private int mConversationSenders = 0;
    private ArrayList<Integer> mPriorityCategories = new ArrayList<>(Collections.nCopies(9, 0));
    private ArrayList<Integer> mVisualEffects = new ArrayList<>(Collections.nCopies(7, 0));

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConversationSenders {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PeopleType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PriorityCategory {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VisualEffect {
    }

    private String indexToCategory(int i) {
        switch (i) {
            case 0:
                return "reminders";
            case 1:
                return "events";
            case 2:
                return "messages";
            case 3:
                return "calls";
            case 4:
                return "repeatCallers";
            case 5:
                return "alarms";
            case 6:
                return "media";
            case 7:
                return "system";
            case 8:
                return "convs";
            default:
                return null;
        }
    }

    private String indexToVisualEffect(int i) {
        switch (i) {
            case 0:
                return "fullScreenIntent";
            case 1:
                return Context.LIGHTS_SERVICE;
            case 2:
                return "peek";
            case 3:
                return "statusBar";
            case 4:
                return "badge";
            case 5:
                return AudioSystem.DEVICE_IN_AMBIENT_NAME;
            case 6:
                return "notificationList";
            default:
                return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getPriorityConversationSenders() {
        return this.mConversationSenders;
    }

    public int getPriorityMessageSenders() {
        return this.mPriorityMessages;
    }

    public int getPriorityCallSenders() {
        return this.mPriorityCalls;
    }

    public int getPriorityCategoryConversations() {
        return this.mPriorityCategories.get(8).intValue();
    }

    public int getPriorityCategoryReminders() {
        return this.mPriorityCategories.get(0).intValue();
    }

    public int getPriorityCategoryEvents() {
        return this.mPriorityCategories.get(1).intValue();
    }

    public int getPriorityCategoryMessages() {
        return this.mPriorityCategories.get(2).intValue();
    }

    public int getPriorityCategoryCalls() {
        return this.mPriorityCategories.get(3).intValue();
    }

    public int getPriorityCategoryRepeatCallers() {
        return this.mPriorityCategories.get(4).intValue();
    }

    public int getPriorityCategoryAlarms() {
        return this.mPriorityCategories.get(5).intValue();
    }

    public int getPriorityCategoryMedia() {
        return this.mPriorityCategories.get(6).intValue();
    }

    public int getPriorityCategorySystem() {
        return this.mPriorityCategories.get(7).intValue();
    }

    public int getVisualEffectFullScreenIntent() {
        return this.mVisualEffects.get(0).intValue();
    }

    public int getVisualEffectLights() {
        return this.mVisualEffects.get(1).intValue();
    }

    public int getVisualEffectPeek() {
        return this.mVisualEffects.get(2).intValue();
    }

    public int getVisualEffectStatusBar() {
        return this.mVisualEffects.get(3).intValue();
    }

    public int getVisualEffectBadge() {
        return this.mVisualEffects.get(4).intValue();
    }

    public int getVisualEffectAmbient() {
        return this.mVisualEffects.get(5).intValue();
    }

    public int getVisualEffectNotificationList() {
        return this.mVisualEffects.get(6).intValue();
    }

    public boolean shouldHideAllVisualEffects() {
        for (int i = 0; i < this.mVisualEffects.size(); i++) {
            if (this.mVisualEffects.get(i).intValue() != 2) {
                return false;
            }
        }
        return true;
    }

    public boolean shouldShowAllVisualEffects() {
        for (int i = 0; i < this.mVisualEffects.size(); i++) {
            if (this.mVisualEffects.get(i).intValue() != 1) {
                return false;
            }
        }
        return true;
    }

    public static final class Builder {
        private ZenPolicy mZenPolicy;

        public Builder() {
            this.mZenPolicy = new ZenPolicy();
        }

        public Builder(ZenPolicy zenPolicy) {
            if (zenPolicy != null) {
                this.mZenPolicy = zenPolicy.copy();
            } else {
                this.mZenPolicy = new ZenPolicy();
            }
        }

        public ZenPolicy build() {
            return this.mZenPolicy.copy();
        }

        public Builder allowAllSounds() {
            for (int i = 0; i < this.mZenPolicy.mPriorityCategories.size(); i++) {
                this.mZenPolicy.mPriorityCategories.set(i, 1);
            }
            this.mZenPolicy.mPriorityMessages = 1;
            this.mZenPolicy.mPriorityCalls = 1;
            this.mZenPolicy.mConversationSenders = 1;
            return this;
        }

        public Builder disallowAllSounds() {
            for (int i = 0; i < this.mZenPolicy.mPriorityCategories.size(); i++) {
                this.mZenPolicy.mPriorityCategories.set(i, 2);
            }
            this.mZenPolicy.mPriorityMessages = 4;
            this.mZenPolicy.mPriorityCalls = 4;
            this.mZenPolicy.mConversationSenders = 3;
            return this;
        }

        public Builder showAllVisualEffects() {
            for (int i = 0; i < this.mZenPolicy.mVisualEffects.size(); i++) {
                this.mZenPolicy.mVisualEffects.set(i, 1);
            }
            return this;
        }

        public Builder hideAllVisualEffects() {
            for (int i = 0; i < this.mZenPolicy.mVisualEffects.size(); i++) {
                this.mZenPolicy.mVisualEffects.set(i, 2);
            }
            return this;
        }

        public Builder unsetPriorityCategory(int i) {
            this.mZenPolicy.mPriorityCategories.set(i, 0);
            if (i == 2) {
                this.mZenPolicy.mPriorityMessages = 0;
            } else if (i == 3) {
                this.mZenPolicy.mPriorityCalls = 0;
            } else if (i == 8) {
                this.mZenPolicy.mConversationSenders = 0;
            }
            return this;
        }

        public Builder unsetVisualEffect(int i) {
            this.mZenPolicy.mVisualEffects.set(i, 0);
            return this;
        }

        public Builder allowReminders(boolean z) {
            this.mZenPolicy.mPriorityCategories.set(0, Integer.valueOf(z ? 1 : 2));
            return this;
        }

        public Builder allowEvents(boolean z) {
            this.mZenPolicy.mPriorityCategories.set(1, Integer.valueOf(z ? 1 : 2));
            return this;
        }

        public Builder allowConversations(int i) {
            if (i != 0) {
                if (i == 3) {
                    this.mZenPolicy.mPriorityCategories.set(8, 2);
                } else {
                    if (i != 1 && i != 2) {
                        return this;
                    }
                    this.mZenPolicy.mPriorityCategories.set(8, 1);
                }
                this.mZenPolicy.mConversationSenders = i;
                return this;
            }
            return unsetPriorityCategory(8);
        }

        public Builder allowMessages(int i) {
            if (i != 0) {
                if (i == 4) {
                    this.mZenPolicy.mPriorityCategories.set(2, 2);
                } else {
                    if (i != 1 && i != 2 && i != 3) {
                        return this;
                    }
                    this.mZenPolicy.mPriorityCategories.set(2, 1);
                }
                this.mZenPolicy.mPriorityMessages = i;
                return this;
            }
            return unsetPriorityCategory(2);
        }

        public Builder allowCalls(int i) {
            if (i != 0) {
                if (i == 4) {
                    this.mZenPolicy.mPriorityCategories.set(3, 2);
                } else {
                    if (i != 1 && i != 2 && i != 3) {
                        return this;
                    }
                    this.mZenPolicy.mPriorityCategories.set(3, 1);
                }
                this.mZenPolicy.mPriorityCalls = i;
                return this;
            }
            return unsetPriorityCategory(3);
        }

        public Builder allowRepeatCallers(boolean z) {
            this.mZenPolicy.mPriorityCategories.set(4, Integer.valueOf(z ? 1 : 2));
            return this;
        }

        public Builder allowAlarms(boolean z) {
            this.mZenPolicy.mPriorityCategories.set(5, Integer.valueOf(z ? 1 : 2));
            return this;
        }

        public Builder allowMedia(boolean z) {
            this.mZenPolicy.mPriorityCategories.set(6, Integer.valueOf(z ? 1 : 2));
            return this;
        }

        public Builder allowSystem(boolean z) {
            this.mZenPolicy.mPriorityCategories.set(7, Integer.valueOf(z ? 1 : 2));
            return this;
        }

        public Builder allowCategory(int i, boolean z) {
            if (i == 0) {
                allowReminders(z);
            } else if (i == 1) {
                allowEvents(z);
            } else if (i == 4) {
                allowRepeatCallers(z);
            } else if (i == 5) {
                allowAlarms(z);
            } else if (i == 6) {
                allowMedia(z);
            } else if (i == 7) {
                allowSystem(z);
            }
            return this;
        }

        public Builder showFullScreenIntent(boolean z) {
            this.mZenPolicy.mVisualEffects.set(0, Integer.valueOf(z ? 1 : 2));
            return this;
        }

        public Builder showLights(boolean z) {
            this.mZenPolicy.mVisualEffects.set(1, Integer.valueOf(z ? 1 : 2));
            return this;
        }

        public Builder showPeeking(boolean z) {
            this.mZenPolicy.mVisualEffects.set(2, Integer.valueOf(z ? 1 : 2));
            return this;
        }

        public Builder showStatusBarIcons(boolean z) {
            this.mZenPolicy.mVisualEffects.set(3, Integer.valueOf(z ? 1 : 2));
            return this;
        }

        public Builder showBadges(boolean z) {
            this.mZenPolicy.mVisualEffects.set(4, Integer.valueOf(z ? 1 : 2));
            return this;
        }

        public Builder showInAmbientDisplay(boolean z) {
            this.mZenPolicy.mVisualEffects.set(5, Integer.valueOf(z ? 1 : 2));
            return this;
        }

        public Builder showInNotificationList(boolean z) {
            this.mZenPolicy.mVisualEffects.set(6, Integer.valueOf(z ? 1 : 2));
            return this;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public Builder showVisualEffect(int i, boolean z) {
            switch (i) {
                case 0:
                    showFullScreenIntent(z);
                    return this;
                case 1:
                    showLights(z);
                    return this;
                case 2:
                    showPeeking(z);
                    return this;
                case 3:
                    showStatusBarIcons(z);
                    return this;
                case 4:
                    showBadges(z);
                    return this;
                case 5:
                    showInAmbientDisplay(z);
                    return this;
                case 6:
                    showInNotificationList(z);
                    return this;
                default:
                    return this;
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.mPriorityCategories);
        parcel.writeList(this.mVisualEffects);
        parcel.writeInt(this.mPriorityCalls);
        parcel.writeInt(this.mPriorityMessages);
        parcel.writeInt(this.mConversationSenders);
    }

    public String toString() {
        return ZenPolicy.class.getSimpleName() + "{priorityCategories=[" + priorityCategoriesToString() + "], visualEffects=[" + visualEffectsToString() + "], priorityCallsSenders=" + peopleTypeToString(this.mPriorityCalls) + ", priorityMessagesSenders=" + peopleTypeToString(this.mPriorityMessages) + ", priorityConversationSenders=" + conversationTypeToString(this.mConversationSenders) + '}';
    }

    private String priorityCategoriesToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.mPriorityCategories.size(); i++) {
            if (this.mPriorityCategories.get(i).intValue() != 0) {
                sb.append(indexToCategory(i)).append("=").append(stateToString(this.mPriorityCategories.get(i).intValue())).append(" ");
            }
        }
        return sb.toString();
    }

    private String visualEffectsToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.mVisualEffects.size(); i++) {
            if (this.mVisualEffects.get(i).intValue() != 0) {
                sb.append(indexToVisualEffect(i)).append("=").append(stateToString(this.mVisualEffects.get(i).intValue())).append(" ");
            }
        }
        return sb.toString();
    }

    private String stateToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "invalidState{" + i + "}" : "disallow" : "allow" : "unset";
    }

    private String peopleTypeToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "invalidPeopleType{" + i + "}" : "none" : "starred_contacts" : Contacts.AUTHORITY : "anyone" : "unset";
    }

    public static String conversationTypeToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "invalidConversationType{" + i + "}" : "none" : "important" : "anyone" : "unset";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ZenPolicy)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ZenPolicy zenPolicy = (ZenPolicy) obj;
        return Objects.equals(zenPolicy.mPriorityCategories, this.mPriorityCategories) && Objects.equals(zenPolicy.mVisualEffects, this.mVisualEffects) && zenPolicy.mPriorityCalls == this.mPriorityCalls && zenPolicy.mPriorityMessages == this.mPriorityMessages && zenPolicy.mConversationSenders == this.mConversationSenders;
    }

    public int hashCode() {
        return Objects.hash(this.mPriorityCategories, this.mVisualEffects, Integer.valueOf(this.mPriorityCalls), Integer.valueOf(this.mPriorityMessages), Integer.valueOf(this.mConversationSenders));
    }

    private int getZenPolicyPriorityCategoryState(int i) {
        switch (i) {
            case 0:
                return getPriorityCategoryReminders();
            case 1:
                return getPriorityCategoryEvents();
            case 2:
                return getPriorityCategoryMessages();
            case 3:
                return getPriorityCategoryCalls();
            case 4:
                return getPriorityCategoryRepeatCallers();
            case 5:
                return getPriorityCategoryAlarms();
            case 6:
                return getPriorityCategoryMedia();
            case 7:
                return getPriorityCategorySystem();
            case 8:
                return getPriorityCategoryConversations();
            default:
                return -1;
        }
    }

    private int getZenPolicyVisualEffectState(int i) {
        switch (i) {
            case 0:
                return getVisualEffectFullScreenIntent();
            case 1:
                return getVisualEffectLights();
            case 2:
                return getVisualEffectPeek();
            case 3:
                return getVisualEffectStatusBar();
            case 4:
                return getVisualEffectBadge();
            case 5:
                return getVisualEffectAmbient();
            case 6:
                return getVisualEffectNotificationList();
            default:
                return -1;
        }
    }

    public boolean isCategoryAllowed(int i, boolean z) {
        int zenPolicyPriorityCategoryState = getZenPolicyPriorityCategoryState(i);
        if (zenPolicyPriorityCategoryState == 1) {
            return true;
        }
        if (zenPolicyPriorityCategoryState != 2) {
            return z;
        }
        return false;
    }

    public boolean isVisualEffectAllowed(int i, boolean z) {
        int zenPolicyVisualEffectState = getZenPolicyVisualEffectState(i);
        if (zenPolicyVisualEffectState == 1) {
            return true;
        }
        if (zenPolicyVisualEffectState != 2) {
            return z;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void apply(android.service.notification.ZenPolicy r6) {
        /*
            r5 = this;
            if (r6 != 0) goto L3
            return
        L3:
            r0 = 0
            r1 = r0
        L5:
            java.util.ArrayList<java.lang.Integer> r2 = r5.mPriorityCategories
            int r2 = r2.size()
            r3 = 2
            if (r1 >= r2) goto L5a
            java.util.ArrayList<java.lang.Integer> r2 = r5.mPriorityCategories
            java.lang.Object r2 = r2.get(r1)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            if (r2 != r3) goto L1d
            goto L57
        L1d:
            java.util.ArrayList<java.lang.Integer> r2 = r6.mPriorityCategories
            java.lang.Object r2 = r2.get(r1)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            if (r2 == 0) goto L57
            java.util.ArrayList<java.lang.Integer> r4 = r5.mPriorityCategories
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r4.set(r1, r2)
            if (r1 != r3) goto L3f
            int r2 = r5.mPriorityMessages
            int r3 = r6.mPriorityMessages
            if (r2 >= r3) goto L3f
            r5.mPriorityMessages = r3
            goto L57
        L3f:
            r2 = 3
            if (r1 != r2) goto L4b
            int r2 = r5.mPriorityCalls
            int r3 = r6.mPriorityCalls
            if (r2 >= r3) goto L4b
            r5.mPriorityCalls = r3
            goto L57
        L4b:
            r2 = 8
            if (r1 != r2) goto L57
            int r2 = r5.mConversationSenders
            int r3 = r6.mConversationSenders
            if (r2 >= r3) goto L57
            r5.mConversationSenders = r3
        L57:
            int r1 = r1 + 1
            goto L5
        L5a:
            java.util.ArrayList<java.lang.Integer> r1 = r5.mVisualEffects
            int r1 = r1.size()
            if (r0 >= r1) goto L8f
            java.util.ArrayList<java.lang.Integer> r1 = r5.mVisualEffects
            java.lang.Object r1 = r1.get(r0)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r1 != r3) goto L71
            goto L8c
        L71:
            java.util.ArrayList<java.lang.Integer> r1 = r6.mVisualEffects
            java.lang.Object r1 = r1.get(r0)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r1 == 0) goto L8c
            java.util.ArrayList<java.lang.Integer> r1 = r5.mVisualEffects
            java.util.ArrayList<java.lang.Integer> r2 = r6.mVisualEffects
            java.lang.Object r2 = r2.get(r0)
            java.lang.Integer r2 = (java.lang.Integer) r2
            r1.set(r0, r2)
        L8c:
            int r0 = r0 + 1
            goto L5a
        L8f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.service.notification.ZenPolicy.apply(android.service.notification.ZenPolicy):void");
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1159641169921L, getPriorityCategoryReminders());
        protoOutputStream.write(1159641169922L, getPriorityCategoryEvents());
        protoOutputStream.write(1159641169923L, getPriorityCategoryMessages());
        protoOutputStream.write(1159641169924L, getPriorityCategoryCalls());
        protoOutputStream.write(1159641169925L, getPriorityCategoryRepeatCallers());
        protoOutputStream.write(1159641169926L, getPriorityCategoryAlarms());
        protoOutputStream.write(1159641169927L, getPriorityCategoryMedia());
        protoOutputStream.write(1159641169928L, getPriorityCategorySystem());
        protoOutputStream.write(1159641169929L, getVisualEffectFullScreenIntent());
        protoOutputStream.write(1159641169930L, getVisualEffectLights());
        protoOutputStream.write(1159641169931L, getVisualEffectPeek());
        protoOutputStream.write(1159641169932L, getVisualEffectStatusBar());
        protoOutputStream.write(1159641169933L, getVisualEffectBadge());
        protoOutputStream.write(1159641169934L, getVisualEffectAmbient());
        protoOutputStream.write(1159641169935L, getVisualEffectNotificationList());
        protoOutputStream.write(1159641169937L, getPriorityMessageSenders());
        protoOutputStream.write(1159641169936L, getPriorityCallSenders());
        protoOutputStream.end(jStart);
    }

    public byte[] toProto() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ProtoOutputStream protoOutputStream = new ProtoOutputStream(byteArrayOutputStream);
        protoOutputStream.write(1159641169921L, getPriorityCategoryCalls());
        protoOutputStream.write(1159641169922L, getPriorityCategoryRepeatCallers());
        protoOutputStream.write(1159641169923L, getPriorityCategoryMessages());
        protoOutputStream.write(1159641169924L, getPriorityCategoryConversations());
        protoOutputStream.write(1159641169925L, getPriorityCategoryReminders());
        protoOutputStream.write(1159641169926L, getPriorityCategoryEvents());
        protoOutputStream.write(1159641169927L, getPriorityCategoryAlarms());
        protoOutputStream.write(1159641169928L, getPriorityCategoryMedia());
        protoOutputStream.write(1159641169929L, getPriorityCategorySystem());
        protoOutputStream.write(1159641169930L, getVisualEffectFullScreenIntent());
        protoOutputStream.write(1159641169931L, getVisualEffectLights());
        protoOutputStream.write(1159641169932L, getVisualEffectPeek());
        protoOutputStream.write(1159641169933L, getVisualEffectStatusBar());
        protoOutputStream.write(1159641169934L, getVisualEffectBadge());
        protoOutputStream.write(1159641169935L, getVisualEffectAmbient());
        protoOutputStream.write(1159641169936L, getVisualEffectNotificationList());
        protoOutputStream.write(1159641169937L, getPriorityCallSenders());
        protoOutputStream.write(1159641169938L, getPriorityMessageSenders());
        protoOutputStream.write(1159641169939L, getPriorityConversationSenders());
        protoOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public ZenPolicy copy() {
        Parcel parcelObtain = Parcel.obtain();
        try {
            writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            return CREATOR.createFromParcel(parcelObtain);
        } finally {
            parcelObtain.recycle();
        }
    }
}

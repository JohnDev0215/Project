package com.amazon.music.identity.profiles.datasource.mockprofile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MockProfileManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 &2\u00020\u0001:\u0001&B\u0007¢\u0006\u0004\b$\u0010%J\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0002J\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0004J\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u000fR0\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u0012j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0002`\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0003\u0010\u0018\"\u0004\b\u0006\u0010\u0019R\"\u0010\u001a\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001a\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0016\u0010\r\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u0017R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\"\u0010 ¨\u0006'"}, d2 = {"Lcom/amazon/music/identity/profiles/datasource/mockprofile/MockProfileManager;", "", "Lcom/amazon/music/identity/profiles/datasource/mockprofile/ProfileBaseModel;", "getCurrentProfile", "", "profileID", "setCurrentProfile", "", "addMockProfiles", "profileBaseModel", "", "addProfile", "getNumActiveProfiles", "currentInactiveProfile", "setCurrentInactiveProfile", "", "list", "sortByEnrolmentDate", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "profilesMap", "Ljava/util/HashMap;", "currentProfile", "I", "()I", "(I)V", "isProfileSelected", "Z", "()Z", "setProfileSelected", "(Z)V", "getActiveProfiles", "()Ljava/util/List;", "activeProfiles", "getInactiveProfiles", "inactiveProfiles", "<init>", "()V", "Companion", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0})
public final class MockProfileManager {
    private int currentInactiveProfile;
    private int currentProfile;
    private boolean isProfileSelected;
    private final HashMap<Integer, ProfileBaseModel> profilesMap = new HashMap<>();

    public MockProfileManager() {
        addMockProfiles();
    }

    public final int getCurrentProfile() {
        return this.currentProfile;
    }

    /* JADX INFO: renamed from: setCurrentProfile, reason: collision with other method in class */
    public final void m2128setCurrentProfile(int i) {
        this.currentProfile = i;
    }

    /* JADX INFO: renamed from: isProfileSelected, reason: from getter */
    public final boolean getIsProfileSelected() {
        return this.isProfileSelected;
    }

    /* JADX INFO: renamed from: getCurrentProfile, reason: collision with other method in class */
    public final ProfileBaseModel m2127getCurrentProfile() {
        return this.profilesMap.get(Integer.valueOf(this.currentProfile));
    }

    public final ProfileBaseModel setCurrentProfile(int profileID) {
        ProfileBaseModel profileBaseModelM2127getCurrentProfile;
        if (this.isProfileSelected && (profileBaseModelM2127getCurrentProfile = m2127getCurrentProfile()) != null) {
            profileBaseModelM2127getCurrentProfile.setSelected(false);
        }
        this.currentProfile = profileID;
        ProfileBaseModel profileBaseModelM2127getCurrentProfile2 = m2127getCurrentProfile();
        if (profileBaseModelM2127getCurrentProfile2 != null) {
            profileBaseModelM2127getCurrentProfile2.setSelected(true);
        }
        this.isProfileSelected = true;
        return m2127getCurrentProfile();
    }

    public final void addMockProfiles() {
        ProfileModel profileModel = new ProfileModel("Hieu Nguyen", 1, "@nganhieu", "https://m.media-amazon.com/images/G/02/CerberusPrimeVideo-FN38FSBD/adult-1.png", true, false, null, 96, null);
        boolean z = false;
        Date date = null;
        DefaultConstructorMarker defaultConstructorMarker = null;
        ProfileModel profileModel2 = new ProfileModel("Arnold Martinez", 2, "@armartip", "https://m.media-amazon.com/images/G/02/CerberusPrimeVideo-FN38FSBD/adult-2.png", true, z, date, 96, defaultConstructorMarker);
        boolean z2 = false;
        boolean z3 = false;
        Date date2 = null;
        int i = 112;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        ProfileModel profileModel3 = new ProfileModel("Manjit Singh", 3, "", "https://m.media-amazon.com/images/G/02/CerberusPrimeVideo-FN38FSBD/adult-3.png", z2, z3, date2, i, defaultConstructorMarker2);
        boolean z4 = false;
        ProfileModel profileModel4 = new ProfileModel("Andrew Nguyen", 4, "", "https://m.media-amazon.com/images/G/02/CerberusPrimeVideo-FN38FSBD/adult-4.png", z4, z, date, 112, defaultConstructorMarker);
        ProfileModel profileModel5 = new ProfileModel("Michelle Pham", 5, "", "https://m.media-amazon.com/images/G/02/CerberusPrimeVideo-FN38FSBD/adult-5.png", z2, z3, date2, i, defaultConstructorMarker2);
        ProfileModel profileModel6 = new ProfileModel("Kids", 998, "Unavailable", "https://m.media-amazon.com/images/G/02/CerberusPrimeVideo-FN38FSBD/kid-1.png", z4, z, date, 64, defaultConstructorMarker);
        addProfile(profileModel);
        addProfile(profileModel2);
        addProfile(profileModel3);
        addProfile(profileModel4);
        addProfile(profileModel5);
        addProfile(profileModel6);
    }

    public final boolean addProfile(ProfileBaseModel profileBaseModel) {
        Intrinsics.checkNotNullParameter(profileBaseModel, "profileBaseModel");
        ProfileModel profileModel = (ProfileModel) profileBaseModel;
        if (getNumActiveProfiles() >= 6 || this.profilesMap.containsKey(Integer.valueOf(profileModel.getProfileId()))) {
            return false;
        }
        this.profilesMap.put(Integer.valueOf(profileModel.getProfileId()), profileModel);
        return true;
    }

    public final List<ProfileBaseModel> getActiveProfiles() {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Integer, ProfileBaseModel>> it = this.profilesMap.entrySet().iterator();
        while (it.hasNext()) {
            ProfileModel profileModel = (ProfileModel) it.next().getValue();
            if (profileModel.getIsActivated()) {
                arrayList.add(profileModel);
            }
        }
        return sortByEnrolmentDate(arrayList);
    }

    public final List<ProfileBaseModel> getInactiveProfiles() {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Integer, ProfileBaseModel>> it = this.profilesMap.entrySet().iterator();
        while (it.hasNext()) {
            ProfileModel profileModel = (ProfileModel) it.next().getValue();
            if (!profileModel.getIsActivated()) {
                arrayList.add(profileModel);
            }
        }
        return sortByEnrolmentDate(arrayList);
    }

    public final int getNumActiveProfiles() {
        return getActiveProfiles().size();
    }

    public final void setCurrentInactiveProfile(int currentInactiveProfile) {
        this.currentInactiveProfile = currentInactiveProfile;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<ProfileBaseModel> sortByEnrolmentDate(List<? extends ProfileBaseModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        Collections.sort(list, new Comparator() { // from class: com.amazon.music.identity.profiles.datasource.mockprofile.MockProfileManager$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return MockProfileManager.m2126sortByEnrolmentDate$lambda0((ProfileBaseModel) obj, (ProfileBaseModel) obj2);
            }
        });
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: sortByEnrolmentDate$lambda-0, reason: not valid java name */
    public static final int m2126sortByEnrolmentDate$lambda0(ProfileBaseModel profileBaseModel, ProfileBaseModel profileBaseModel2) {
        if (profileBaseModel != null) {
            Date enrollmentDate = ((ProfileModel) profileBaseModel).getEnrollmentDate();
            if (profileBaseModel2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.identity.profiles.datasource.mockprofile.ProfileModel");
            }
            return enrollmentDate.compareTo(((ProfileModel) profileBaseModel2).getEnrollmentDate());
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.identity.profiles.datasource.mockprofile.ProfileModel");
    }
}

package com.amazon.mp3.customerprofile;

import android.content.Context;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.amazon.mp3.brush.converters.BrushCustomerProfileConverter;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.util.Log;
import com.amazon.music.picassoimageloader.CircleTransformation;
import com.amazon.music.views.library.models.CustomerProfileModel;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.amazonaws.util.StringUtils;
import com.squareup.picasso.Picasso;
import com.visualon.OSMPUtils.voOSType;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import rx.Single;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class CustomerProfileDetailManager {
    private static final String TAG = "CustomerProfileDetailManager";
    private static CustomerProfileDetailManager sInstance;
    private GetProfileIcon mProfileIconTask;
    private CustomerProfileModel mProfileModel = null;

    private static int exifToDegrees(int exifOrientation) {
        if (exifOrientation == 3) {
            return 180;
        }
        if (exifOrientation == 6) {
            return 90;
        }
        if (exifOrientation != 8) {
            return 0;
        }
        return voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_DIALOGUE_ENHANCEMENT;
    }

    public static synchronized CustomerProfileDetailManager getInstance() {
        if (sInstance == null) {
            sInstance = new CustomerProfileDetailManager();
        }
        return sInstance;
    }

    public void loadAvatarImage(Context context, ImageView profileView) {
        CustomerProfileModel customerProfileModel = this.mProfileModel;
        if (customerProfileModel != null && !StringUtils.isBlank(customerProfileModel.getProfileAvatarUrl())) {
            setProfilePhotoFromUrl(profileView, this.mProfileModel.getProfileAvatarUrl(), context);
            return;
        }
        GetProfileIcon getProfileIcon = new GetProfileIcon(context, profileView);
        this.mProfileIconTask = getProfileIcon;
        getProfileIcon.execute(new Void[0]);
    }

    public void syncCustomerInformation(Context context) {
        GetProfileIcon getProfileIcon = new GetProfileIcon(context, null, Boolean.TRUE);
        this.mProfileIconTask = getProfileIcon;
        getProfileIcon.execute(new Void[0]);
    }

    public String getCustomerProfileImageUrl() {
        CustomerProfileModel customerProfileModel = this.mProfileModel;
        if (customerProfileModel == null) {
            Log.error(TAG, "mProfileModel is not set on getCustomerProfileImageUrl");
            return null;
        }
        return customerProfileModel.getProfileAvatarUrl();
    }

    public String getProfileName() {
        CustomerProfileModel customerProfileModel = this.mProfileModel;
        if (customerProfileModel == null) {
            Log.error(TAG, "mProfileModel is not set on getProfileName");
            return null;
        }
        return customerProfileModel.getProfileName();
    }

    public String getProfileId() {
        CustomerProfileModel customerProfileModel = this.mProfileModel;
        if (customerProfileModel == null) {
            Log.error(TAG, "mProfileModel is not set on getProfileId");
            return null;
        }
        return customerProfileModel.getProfileId();
    }

    public String getProfileStatus() {
        CustomerProfileModel customerProfileModel = this.mProfileModel;
        if (customerProfileModel == null) {
            Log.error(TAG, "mProfileModel is not set on getProfileStatus");
            return null;
        }
        return customerProfileModel.getProfileStatus();
    }

    public void setProfileStatus(String status) {
        CustomerProfileModel customerProfileModel = this.mProfileModel;
        if (customerProfileModel == null) {
            Log.error(TAG, "mProfileModel is not set on setProfileStatus");
        } else {
            customerProfileModel.setProfileStatus(status);
        }
    }

    public void setCustomerProfileModel(CustomerProfileModel model) {
        this.mProfileModel = model;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProfilePhotoFromUrl(final ImageView photoView, final String url, final Context context) {
        if (context == null || url == null || photoView == null) {
            return;
        }
        getExifRotation(url, context).subscribe(new Action1() { // from class: com.amazon.mp3.customerprofile.CustomerProfileDetailManager$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CustomerProfileDetailManager.lambda$setProfilePhotoFromUrl$0(url, photoView, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setProfilePhotoFromUrl$0(String str, ImageView imageView, Integer num) {
        Picasso.get().load(str).transform(new CircleTransformation()).rotate(num.intValue()).into(imageView);
    }

    public static Single<Integer> getExifRotation(final String url, final Context context) {
        return Single.fromCallable(new Callable() { // from class: com.amazon.mp3.customerprofile.CustomerProfileDetailManager$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return CustomerProfileDetailManager.lambda$getExifRotation$1(url, context);
            }
        }).doOnError(new Action1() { // from class: com.amazon.mp3.customerprofile.CustomerProfileDetailManager$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CustomerProfileDetailManager.lambda$getExifRotation$2((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$getExifRotation$1(String str, Context context) throws Exception {
        int iExifToDegrees = 0;
        if (str != null && str.startsWith("content://")) {
            try {
                InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(Uri.parse(str));
                try {
                    iExifToDegrees = exifToDegrees(new ExifInterface(inputStreamOpenInputStream).getAttributeInt("Orientation", 1));
                    if (inputStreamOpenInputStream != null) {
                        inputStreamOpenInputStream.close();
                    }
                } finally {
                }
            } catch (IOException | SecurityException e) {
                Log.error(TAG, "Failed to get EXIF rotation for " + str, e);
            }
        }
        return Integer.valueOf(iExifToDegrees);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getExifRotation$2(Throwable th) {
        Log.error(TAG, "getExifRotationFailed to execute ", th);
    }

    public CustomerProfileModel getProfileModel() {
        return this.mProfileModel;
    }

    private class GetProfileIcon extends AsyncTask<Void, Void, Void> {
        Context mContext;
        Boolean mForceUpdate;
        ImageView mProfileView;

        public GetProfileIcon(Context context, ImageView profileView) {
            this.mForceUpdate = Boolean.FALSE;
            this.mContext = context.getApplicationContext();
            this.mProfileView = profileView;
        }

        public GetProfileIcon(Context context, ImageView profileView, Boolean forceUpdate) {
            this.mForceUpdate = Boolean.FALSE;
            this.mContext = context.getApplicationContext();
            this.mProfileView = profileView;
            this.mForceUpdate = forceUpdate;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voids) {
            if (StringUtils.isBlank(CustomerProfileDetailManager.this.getCustomerProfileImageUrl()) || this.mForceUpdate.booleanValue()) {
                try {
                    PageResponse pageResponse = BrowseFactory.createUiPage(this.mContext).get(BrowseFactory.createPageRequest(this.mContext, "profile/details/", null, false));
                    BrushCustomerProfileConverter brushCustomerProfileConverter = new BrushCustomerProfileConverter(this.mContext);
                    if (pageResponse != null) {
                        CustomerProfileDetailManager.this.mProfileModel = brushCustomerProfileConverter.convertCustomerProfileFromPage(pageResponse);
                    }
                    if (CustomerProfileDetailManager.this.mProfileModel == null) {
                        Log.error(CustomerProfileDetailManager.TAG, "BrushCustomerProfileConverter returned a null profile model.");
                        return null;
                    }
                } catch (Exception e) {
                    Log.error(CustomerProfileDetailManager.TAG, "Could not create a profile request or convert the response.", e);
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final Void v) {
            String customerProfileImageUrl = CustomerProfileDetailManager.this.getCustomerProfileImageUrl();
            if (customerProfileImageUrl == null || customerProfileImageUrl.isEmpty()) {
                Log.error(CustomerProfileDetailManager.TAG, "Profile avatar url is null or empty string. Could not set navbar profile photo.");
                return;
            }
            ImageView imageView = this.mProfileView;
            if (imageView != null) {
                CustomerProfileDetailManager.this.setProfilePhotoFromUrl(imageView, customerProfileImageUrl, this.mContext);
            }
        }
    }
}

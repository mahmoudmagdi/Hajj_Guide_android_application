package prefs;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

public class UserInfo {
    private static final String TAG = UserSession.class.getSimpleName();
    private static final String PREF_NAME = "userinfo";

    public static final String KEY_ID = "driverId";
    public static final String KEY_FIRST_NAME = "fName";
    public static final String KEY_LAST_NAME = "lName";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE_NUMBER = "phoneNumber";
    public static final String KEY_IMAGE_PROFILE_URL = "image_profile_url";
    public static final String KEY_TRANSPORTER_ID = "transporterId";
    public static final String KEY_LONGITUDE = "longitude";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_BIRTHDATE = "birthdate";
    public static final String KEY_STATUES = "statues";
    public static final String KEY_TRIP_STATUS = "tripStatus";
    public static final String KEY_TRIP_ID = "tripId";
    public static final String KEY_CREDIT_CARD_NUMBER = "creditCardNo";
    public static final String KEY_CREDIT_CARD_EXP_DATE = "creditCardExpDate";
    public static final String KEY_CREDIT_CARD_CVV = "creditCardCvv";
    public static final String KEY_BANK_NAME = "bankname";
    public static final String KEY_BANK_ACCOUNT_NUMBER = "bankAccountNumber";
    public static final String KEY_BANK_ACCOUNT_HOLDER_NAME = "bankAccountHoldername";
    public static final String KEY_BANK_ACCOUNT_LOCATION = "bankAccountLocation";
    public static final String KEY_BANK_ACCOUNT_SWIFT_CODE = "bankAccountSWIFTCode";
    public static final String KEY_EMAIL_VERIFIED = "emailVerified";
    public static final String KEY_PHONE_NUMBER_VERIFIED = "phoneNumberVerified";
    public static final String KEY_FIREBASE_UID = "firebaseUid";
    public static final String KEY_LOG_OUT = "Logout";

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public UserInfo(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setUserId(String driverId) {
        editor.putString(KEY_ID, driverId);
        editor.apply();
    }

    public void setUserFirstName(String fName) {
        editor.putString(KEY_FIRST_NAME, fName);
        editor.apply();
    }

    public void setUserLastName(String lName) {
        editor.putString(KEY_LAST_NAME, lName);
        editor.apply();
    }

    public void setUserPassword(String password) {
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public void setEmail(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public void setUserPhoneNumber(String phoneNumber) {
        editor.putString(KEY_PHONE_NUMBER, phoneNumber);
        editor.apply();
    }

    public void setImageProfileUrl(String image_profile_url) {
        editor.putString(KEY_IMAGE_PROFILE_URL, image_profile_url);
        editor.apply();
    }

    public void setTransporterId(String transporterId) {
        editor.putString(KEY_TRANSPORTER_ID, transporterId);
        editor.apply();
    }

    public void setLongitude(Double longitude) {
        editor.putFloat(KEY_LONGITUDE, Float.parseFloat(String.valueOf(longitude)));
        editor.apply();
    }

    public void setLatitiude(Double latitude) {
        editor.putFloat(KEY_LATITUDE, Float.parseFloat(String.valueOf(latitude)));
        editor.apply();
    }

    public void setBirthDate(String birthdate) {
        editor.putString(KEY_BIRTHDATE, birthdate);
        editor.apply();
    }

    public void setTripStatues(String tripStatues) {
        editor.putString(KEY_TRIP_STATUS, tripStatues);
        editor.apply();
    }


    public void setTripId(String tripId) {
        editor.putString(KEY_TRIP_ID, tripId);
        editor.apply();
    }

    public void setCreditCardNumber(String creditCardNumber) {
        editor.putString(KEY_CREDIT_CARD_NUMBER, creditCardNumber);
        editor.apply();
    }

    public void setCreditCardExpDate(String creditCardExpDate) {
        editor.putString(KEY_CREDIT_CARD_EXP_DATE, creditCardExpDate);
        editor.apply();
    }

    public void setCreditCardCvv(String creditCardCvv) {
        editor.putString(KEY_CREDIT_CARD_CVV, creditCardCvv);
        editor.apply();
    }

    public void setBankName(String bankName) {
        editor.putString(KEY_BANK_NAME, bankName);
        editor.apply();
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        editor.putString(KEY_BANK_ACCOUNT_NUMBER, bankAccountNumber);
        editor.apply();
    }

    public void setBankAccountHolderName(String bankAccountHolderName) {
        editor.putString(KEY_BANK_ACCOUNT_HOLDER_NAME, bankAccountHolderName);
        editor.apply();
    }

    public void setBankAccountLocation(String bankAccountLocation) {
        editor.putString(KEY_BANK_ACCOUNT_LOCATION, bankAccountLocation);
        editor.apply();
    }

    public void setBankAccountSWIFTCode(String bankAccountSWIFTCode) {
        editor.putString(KEY_BANK_ACCOUNT_SWIFT_CODE, bankAccountSWIFTCode);
        editor.apply();
    }

    public void setEmailVerified(String emailVerified) {
        editor.putString(KEY_EMAIL_VERIFIED, emailVerified);
        editor.apply();
    }

    public void setPhoneNumberVerified(String phoneNumberVerified) {
        editor.putString(KEY_PHONE_NUMBER_VERIFIED, phoneNumberVerified);
        editor.apply();
    }

    public void setKeyFirebaseUid(String firebaseUid) {
        editor.putString(KEY_FIREBASE_UID, firebaseUid);
        editor.apply();
    }

    public void setLogOut(String logOut) {
        editor.putString(KEY_LOG_OUT, logOut);
        editor.apply();
    }

    public void clearUserInfo() {
        editor.clear();
        editor.commit();
    }


    public String getKeyId() {
        return prefs.getString(KEY_ID, "");
    }

    public String getKeyFirstName() {
        return prefs.getString(KEY_FIRST_NAME, "");
    }

    public String getKeyLastName() {
        return prefs.getString(KEY_LAST_NAME, "");
    }

    public String getKeyPassword() {
        return prefs.getString(KEY_PASSWORD, "");
    }

    public String getKeyEmail() {
        return prefs.getString(KEY_EMAIL, "");
    }

    public String getKeyPhoneNumber() {
        return prefs.getString(KEY_PHONE_NUMBER, "");
    }

    public String getKeyImageProfileUrl() {
        return prefs.getString(KEY_IMAGE_PROFILE_URL, "");
    }

    public static String getKeyTransporterId() {
        return KEY_TRANSPORTER_ID;
    }

    public float getKeyLongitude() {
        return prefs.getFloat(KEY_LONGITUDE, 0);
    }

    public float getKeyLatitude() {
        return prefs.getFloat(KEY_LATITUDE, 0);
    }

    public String getKeyBirthdate() {
        return prefs.getString(KEY_BIRTHDATE, "");
    }

    public String getKeyStatues() {
        return prefs.getString(KEY_STATUES, "");
    }

    public String getKeyTripStatues() {
        return prefs.getString(KEY_TRIP_STATUS, "");
    }

    public String getKeyTripId() {
        return prefs.getString(KEY_TRIP_ID, "");
    }

    public String getKeyCreditCardNumber() {
        return prefs.getString(KEY_CREDIT_CARD_NUMBER, "");
    }

    public String getKeyCreditCardExpDate() {
        return prefs.getString(KEY_CREDIT_CARD_EXP_DATE, "");
    }

    public String getKeyCreditCardCvv() {
        return prefs.getString(KEY_CREDIT_CARD_CVV, "");
    }

    public String getKeyBankName() {
        return prefs.getString(KEY_BANK_NAME, "");
    }

    public String getKeyBankAccountNumber() {
        return prefs.getString(KEY_BANK_ACCOUNT_NUMBER, "");
    }

    public String getKeyBankAccountHolderName() {
        return prefs.getString(KEY_BANK_ACCOUNT_HOLDER_NAME, "");
    }

    public String getKeyBankAccountLocation() {
        return prefs.getString(KEY_BANK_ACCOUNT_LOCATION, "");
    }

    public String getKeyBankAccountSwiftCode() {
        return prefs.getString(KEY_BANK_ACCOUNT_SWIFT_CODE, "");
    }

    public String getKeyEmailVerified() {
        return prefs.getString(KEY_EMAIL_VERIFIED, "");
    }

    public String getKeyPhoneNumberVerified() {
        return prefs.getString(KEY_PHONE_NUMBER_VERIFIED, "");
    }

    public String getKeyFirebaseUid() {
        return prefs.getString(KEY_FIREBASE_UID, "");
    }

    public String getKeyLogOut() {
        return prefs.getString(KEY_LOG_OUT, "");
    }
}

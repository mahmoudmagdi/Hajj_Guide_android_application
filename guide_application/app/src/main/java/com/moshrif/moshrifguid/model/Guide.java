package com.moshrif.moshrifguid.model;

public class Guide {

    private String guideId;
    private String fName;
    private String lName;
    private String companyName;
    private String passportNumber;
    private String phoneNumber;
    private String email;
    private String country;
    private String age;
    private String gender;
    private String address;
    private String imageUrl;
    private String passportImageUrl;
    private String language;

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPasportImageUrl() {
        return passportImageUrl;
    }

    public void setPasportImageUrl(String pasportImageUrl) {
        this.passportImageUrl = pasportImageUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public static final class FirebaseStrings {
        public static final String GUIDE_ID_CHILD = "guideId";
        public static final String GUIDE_FIRST_NAME_CHILD = "fName";
        public static final String GUIDE_LAST_NAME_CHILD = "lName";
        public static final String GUIDE_PHONE_NUMBER_CHILD = "phoneNumber";
        public static final String GUIDE_EMAIL_CHILD = "email";
        public static final String GUIDE_COMPANY_NAME_CHILD = "companyName";
        public static final String GUIDE_PASSPORT_NUMBER_CHILD = "passportNumber";
        public static final String GUIDE_COUNTRY_CHILD = "country";
        public static final String GUIDE_AGE_CHILD = "age";
        public static final String GUIDE_GENDER_CHILD = "gender";
        public static final String GUIDE_ADDRESS_CHILD = "address";
        public static final String GUIDE_IMAGE_URL_CHILD = "imageUrl";
        public static final String GUIDE_PASSPORT_IMAGE_URL_CHILD = "pasportImageUrl";
        public static final String GUIDE_LANGUAGE_CHILD = "language";

        public static final String GUIDE_PHONE_NUMBER_DEFAULT = "don't have a phone number";
        public static final String GUIDE_EMAIL_DEFAULT = "don't have an email";
        public static final String GUIDE_COMPANY_NAME_DEFAULT = "no companies, yet.";
        public static final String GUIDE_PASSPORT_NUMBER_DEFAULT = "no number, yet.";
        public static final String GUIDE_COUNTRY_DEFAULT = "no country, yet.";
        public static final String GUIDE_AGE_DEFAULT = "unknown";
        public static final String GUIDE_GENDER_DEFAULT = "unknown";
        public static final String GUIDE_ADDRESS_DEFAULT = "unknown";
        public static final String GUIDE_IMAGE_URL_DEFAULT = "unknown";
        public static final String GUIDE_PASSPORT_IMAGE_URL_DEFAULT = "unknown";
        public static final String GUIDE_LANGUAGE_DEFAULT = "unknown";

        public static final String GUIDE_MANAGED_GROUPS = "managedGroups";
        public static final String GUIDE_POSTS = "posts";
        public static final String GUIDE_TASKS = "tasks";
    }
}

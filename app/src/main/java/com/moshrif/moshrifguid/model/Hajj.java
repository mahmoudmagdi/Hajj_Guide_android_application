package com.moshrif.moshrifguid.model;

public class Hajj {

    private String hajjId;
    private String fName;
    private String lName;
    private String phoneNumber;
    private String email;
    private String groupId;
    private String passportNumber;
    private String country;
    private String age;
    private String gender;
    private String address;
    private String profession;
    private String imageUrl;
    private String pasportImageUrl;
    private String language;
    private String flightNumber;

    public String getHajjId() {
        return hajjId;
    }

    public void setHajjId(String hajjId) {
        this.hajjId = hajjId;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPasportImageUrl() {
        return pasportImageUrl;
    }

    public void setPasportImageUrl(String pasportImageUrl) {
        this.pasportImageUrl = pasportImageUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }


    public static final class FirebaseStrings {
        public static final String HAJJ_ID_CHILD = "hajjId";
        public static final String HAJJ_FIRST_NAME_CHILD = "fName";
        public static final String HAJJ_LAST_NAME_CHILD = "lName";
        public static final String HAJJ_PHONE_NUMBER_CHILD = "phoneNumber";
        public static final String HAJJ_EMAIL_CHILD = "email";
        public static final String HAJJ_GROUP_ID_CHILD = "groupId";
        public static final String HAJJ_PASSPORT_NUMBER_CHILD = "passportNumber";
        public static final String HAJJ_COUNTRY_CHILD = "country";
        public static final String HAJJ_AGE_CHILD = "age";
        public static final String HAJJ_GENDER_CHILD = "gender";
        public static final String HAJJ_ADDRESS_CHILD = "address";
        public static final String HAJJ_PROFESSION_CHILD = "profession";
        public static final String HAJJ_IMAGE_URL_CHILD = "imageUrl";
        public static final String HAJJ_PASSPORT_IMAGE_URL_CHILD = "pasportImageUrl";
        public static final String HAJJ_LANGUAGE_CHILD = "language";
        public static final String HAJJ_FLIGHT_NUMBER_CHILD = "flightNumber";

        public static final String HAJJ_PHONE_NUMBER_DEFAULT = "no phone number, yet.";
        public static final String HAJJ_EMAIL_DEFAULT = "no email, yet.";
        public static final String HAJJ_GROUP_ID_DEFAULT = "0";
        public static final String HAJJ_PASSPORT_NUMBER_DEFAULT = "unknown";
        public static final String HAJJ_COUNTRY_DEFAULT = "unknown";
        public static final String HAJJ_AGE_DEFAULT = "unknown";
        public static final String HAJJ_GENDER_DEFAULT = "unknown";
        public static final String HAJJ_ADDRESS_DEFAULT = "unknown";
        public static final String HAJJ_PROFESSION_DEFAULT = "unknown";
        public static final String HAJJ_IMAGE_URL_DEFAULT = "no image, yet.";
        public static final String HAJJ_PASSPORT_IMAGE_URL_DEFAULT = "no image,yet.";
        public static final String HAJJ_LANGUAGE_DEFAULT = "unknown";
        public static final String HAJJ_FLIGHT_NUMBER_DEFAUALT = "unknown";

        public static final String HAJJ_DONE_TASKS = "doneTasks";
    }
}
